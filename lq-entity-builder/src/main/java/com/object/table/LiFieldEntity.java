package com.object.table;


import com.object.type.LiFieldType;

public class LiFieldEntity {
    private String fieldName;

    private boolean isPrimaryKey;

    private LiFieldType type;

    private int size;

    private boolean isEmpty;

    private String comment;


    public LiFieldEntity() {
    }

    public LiFieldEntity(String fieldName, boolean isPrimaryKey, LiFieldType type, int size, boolean isEmpty, String comment) {
        this.fieldName = fieldName;
        this.isPrimaryKey = isPrimaryKey;
        this.type = type;
        this.size = size;
        this.isEmpty = isEmpty;
        this.comment = comment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public LiFieldType getType() {
        return type;
    }

    public void setType(LiFieldType type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
