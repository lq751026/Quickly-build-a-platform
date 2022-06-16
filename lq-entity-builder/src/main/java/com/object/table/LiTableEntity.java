package com.object.table;

import java.util.List;


public class LiTableEntity {
    private String tableName;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private List<LiFieldEntity> fieldEntities;

    public List<LiFieldEntity> getFieldEntities() {
        return fieldEntities;
    }

    public void setFieldEntities(List<LiFieldEntity> fieldEntities) {
        this.fieldEntities = fieldEntities;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName, String comment) {
        this.tableName = tableName;
        this.comment = comment;
    }
}
