package com.object.start;


import com.object.annotation.LiField;
import com.object.annotation.LiTableName;
import com.object.table.LiFieldEntity;
import com.object.table.LiTableEntity;
import com.object.type.LiFieldType;
import com.object.type.LiFieldTypeComparison;
import org.reflections.Reflections;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 注解的处理核心类
 */


public class LiCreateTable {

    protected static Logger log = Logger.getLogger(String.valueOf(LiCreateTable.class));

    public static void createTable(DataSource dataSource, String scannedPackage) {
        List<LiTableEntity> liTableEntities = init(scannedPackage);
        createSql(liTableEntities, dataSource);
    }

    /**
     * 扫描包下的实体类 对我们创建sql语句进行信息的获取
     *
     * @param scannedPackage
     * @return
     */
    private static List<LiTableEntity> init(String scannedPackage) {
        //创建集合装表信息以及表里面的字段信息
        List<LiTableEntity> liTableEntities = new ArrayList<>();
        //扫描当前包下的全部class
        Reflections reflections = new Reflections(scannedPackage);
        //获取class中有LiTableName注解的全部实体类class
        Set<Class<?>> annotatedWith = reflections.getTypesAnnotatedWith(LiTableName.class);
        //遍历class
        for (Class<?> aClass : annotatedWith) {
            //对我们的表名数据的装载
            LiTableEntity liTableEntity = new LiTableEntity();
            List<LiFieldEntity> fieldEntities = new ArrayList<>();
            String table = aClass.getAnnotation(LiTableName.class).value();
            String comment = aClass.getAnnotation(LiTableName.class).comment();
            liTableEntity.setTableName(table, comment);
            Field[] fields = aClass.getDeclaredFields();
            //对我们的字段的数据的装载
            for (Field field : fields) {
                String name = ConvertCamelCaseToUnderscore(field.getName());
                LiField annotation = field.getAnnotation(LiField.class);
                if (annotation == null) continue;
                fieldEntities.add(new LiFieldEntity(name, annotation.isPrimaryKey()
                        , annotation.type(), annotation.size()
                        , annotation.isEmpty(), annotation.comment()));
            }
            liTableEntity.setFieldEntities(fieldEntities);
            liTableEntities.add(liTableEntity);
        }
        return liTableEntities;
    }

    /**
     * 拿到我们的字段和表名信息对我们的sql语句进行拼接
     *
     * @param liTableEntities
     * @param dataSource
     */
    private static void createSql(List<LiTableEntity> liTableEntities, DataSource dataSource) {

        //获取到创表信息过后进行sql语句的生成
        log.warning("开始执行创建表的sql中...");
        StringBuffer sql = new StringBuffer();
        liTableEntities.forEach(tableObj -> {
            sql.append("CREATE TABLE " + tableObj.getTableName() + " (\n");
            String PrimaryKey = "";
            for (int i = 0; i < tableObj.getFieldEntities().size(); i++) {
                LiFieldEntity fieldEntity = tableObj.getFieldEntities().get(i);
                String fid = "";
                fid = fieldEntity.getFieldName() + " " + fieldEntity.getType() + "(" + fieldEntity.getSize() + ")";
                if (!fieldEntity.isEmpty()) fid += " NOT NULL";
                else fid += " NULL DEFAULT NULL";
                if (fieldEntity.isPrimaryKey()) {
                    if (fieldEntity.getType() == LiFieldType.INT
                            || fieldEntity.getType() == LiFieldType.INTEGER) fid += " AUTO_INCREMENT ";
                    PrimaryKey += fieldEntity.getFieldName() + ",";
                }
                fid += " COMMENT '" + fieldEntity.getComment() + "' ,\n";
                sql.append(fid);
            }
            PrimaryKey = PrimaryKey.substring(0, PrimaryKey.lastIndexOf(","));
            sql.append("PRIMARY KEY (" + PrimaryKey + ") USING BTREE\n");
            sql.append(") COMMENT='" + tableObj.getComment() + "'");
            sql.append(";\n\n");

            String sqls = sql.toString();
            sql.delete(0, sql.length() - 1);

            //调用执行创建sql方法
            executeSql(tableObj, sqls, dataSource);

        });
    }

    /**
     * 执行数据库的sql方法
     *
     * @param tableObj
     * @param sqls
     * @param dataSource
     */
    private static void executeSql(LiTableEntity tableObj, String sqls, DataSource dataSource) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            String selectTableName = " SELECT COUNT(*) as count FROM information_schema.TABLES WHERE table_name = '" + tableObj.getTableName() + "' and TABLE_SCHEMA = '" + connection.getCatalog() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectTableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = true;
            while (resultSet.next()) next = resultSet.getInt(1) == 0;
            if (next) {
                connection.prepareStatement(sqls).execute();
                log.warning("创建表名:" + tableObj.getTableName() + "成功");
            } else {
                //TOOD    如果是新增了字段或者是 修改了字段 或者是删除了字段
                checkField(tableObj, dataSource, sqls);
            }
        } catch (SQLException e) {
            log.info("创建表名:" + tableObj.getTableName() + "失败,失败原因:" + e);
        } finally {

            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 检查字段
     *
     * @param tableObj
     * @param dataSource
     * @param sqls
     * @throws SQLException
     */
    private static void checkField(LiTableEntity tableObj, DataSource dataSource, String sqls) throws SQLException {
        //拿到我们的当前选择的数据名
        String tableName = tableObj.getTableName();
        Connection connection = dataSource.getConnection();
        String dataBaseName = connection.getCatalog();
        String sql = "SELECT\n" +
                " C.COLUMN_NAME AS fieldName,  C.COLUMN_TYPE as lengthStr,c.COLUMN_COMMENT as comment,C.DATA_TYPE as type\n" +
                "FROM\n" +
                "information_schema.`TABLES` T\n" +
                "LEFT JOIN information_schema.`COLUMNS` C ON T.TABLE_NAME = C.TABLE_NAME AND T.TABLE_SCHEMA = C.TABLE_SCHEMA\n" +
                "where  T.TABLE_SCHEMA = ? and T.TABLE_NAME=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, dataBaseName);
        preparedStatement.setString(2, tableName);
        ResultSet res = preparedStatement.executeQuery();


        //拿到我们的数据库的消息字段
        ArrayList<LiFieldEntity> fieldEntities = new ArrayList<>();
        //拿到我们的当前表的信息
        while (res.next()) {
            String fieldName = res.getString(1);
            String lengthStr = res.getString(2);
            String substring = lengthStr.substring(lengthStr.indexOf("(") + 1, lengthStr.indexOf(")"));
            int length = Integer.parseInt(substring);
            String comment = res.getString(3);
            String type = res.getString(4);
            fieldEntities.add(new LiFieldEntity(fieldName, LiFieldTypeComparison.typeComparison(type), length, comment));
        }
        List<LiFieldEntity> nowFieldEntities = tableObj.getFieldEntities();

        //字段比对
        List<LiFieldEntity> newFieldEntities = FieldAttributeComparison(nowFieldEntities, fieldEntities);
        String infos = "info   " + tableName;
        if (newFieldEntities == null) {
            //直接使用现在的表创建
            //第一步需要删除原来的表
            PreparedStatement preparedStatement1 = connection.prepareStatement("drop table " + tableName);
            preparedStatement1.executeUpdate();
            //第二步就是重新执行我们的sql
            connection.prepareStatement(sqls).executeUpdate();
            infos += "   表创建完毕  ";
        } else {
            //走我们的修改表的属性
            if (newFieldEntities.size() == 0) {
                //没有修改我们的字段直接返回
                connection.close();
                log.info(infos + "  表没有修改   ");
                return;
            }
            String updateSQL = "";
            for (LiFieldEntity newFieldEntity : newFieldEntities) {
                updateSQL = "alter table " + tableName + "   CHANGE " + newFieldEntity.getOldFieldName() + "   " + newFieldEntity.getFieldName() + "   " + newFieldEntity.getType() + "(" + newFieldEntity.getSize() + ") comment \"" + newFieldEntity.getComment() + "\" NOT NULL ";
                connection.prepareStatement(updateSQL).executeUpdate();
            }
            infos += "  表创建修改了" + newFieldEntities.size() + "个字段  执行完毕...";
        }
        connection.close();
        log.info(infos);
    }

    /**
     * 字段属性比对 拿到最新的字段属性
     *
     * @param nowFieldEntities
     * @param fieldEntities
     * @return
     */
    private static List<LiFieldEntity> FieldAttributeComparison(List<LiFieldEntity> nowFieldEntities, ArrayList<LiFieldEntity> fieldEntities) {
        //如果新的字段属性的 长度和之前的不一样就直接返回null
        if (nowFieldEntities.size() != fieldEntities.size())
            return null;
        //如果是相同我们就检查字段又修改的地方吗
        List<LiFieldEntity> newFieldEntities = new ArrayList<>();
        for (int i = 0; i < nowFieldEntities.size(); i++) {
            LiFieldEntity nowFieldEntity = nowFieldEntities.get(i);
            LiFieldEntity liFieldEntity = fieldEntities.get(i);
            if (!DeterminingNewAndOldFieldProperties(nowFieldEntity, liFieldEntity)) {
                //只要不一样了
                nowFieldEntity.setOldFieldName(liFieldEntity.getFieldName());
                newFieldEntities.add(nowFieldEntity);
            }
        }
        return newFieldEntities;
    }

    /**
     * 确定新旧字段属性
     *
     * @param nowFieldEntity
     * @param liFieldEntity
     * @return
     */
    private static boolean DeterminingNewAndOldFieldProperties(LiFieldEntity nowFieldEntity, LiFieldEntity liFieldEntity) {
        log.info(String.valueOf(nowFieldEntity.getType().name().equals(liFieldEntity.getType().name())));
        if (nowFieldEntity.getComment().equals(liFieldEntity.getComment()) &&
                nowFieldEntity.getFieldName().equals(liFieldEntity.getFieldName()) &&
                nowFieldEntity.getSize() == liFieldEntity.getSize() &&
                nowFieldEntity.getType().name().equals(liFieldEntity.getType().name())) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     * @return java.lang.String
     * @Description 将驼峰转为下划线
     */
    public static String ConvertCamelCaseToUnderscore(String str) {
        Pattern compile = Pattern.compile("[A-Z]");
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
