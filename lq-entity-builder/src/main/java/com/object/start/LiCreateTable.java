package com.object.start;


import com.object.annotation.LiField;
import com.object.annotation.LiTableName;
import com.object.table.LiFieldEntity;
import com.object.table.LiTableEntity;
import com.object.type.LiFieldType;
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
            log.warning("开始创建表名:" + tableObj.getTableName());
            connection = dataSource.getConnection();
            String selectTableName = "select * from information_schema.TABLES  where TABLE_NAME = '" + tableObj.getTableName() + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(selectTableName);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (!next) {
                connection.prepareStatement(sqls).execute();
                log.warning("创建表名:" + tableObj.getTableName() + "成功");
            } else {
                log.warning("表名:" + tableObj.getTableName() + "已存在...");
                //TOOD    如果是新增了字段或者是 修改了字段 或者是删除了字段
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
