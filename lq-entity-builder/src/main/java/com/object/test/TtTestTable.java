package com.object.test;

import com.object.annotation.LiField;
import com.object.annotation.LiTableName;
import com.object.type.LiFieldType;

import java.util.Date;

@LiTableName(value = "tt_test_table", comment = "TT的comment")
public class TtTestTable {
    @LiField(isPrimaryKey = true, type = LiFieldType.INT, size = 0, comment = "TT主键")
    private int tId;
    @LiField(type = LiFieldType.VARCHAR, size = 188, comment = "TT的名字")
    private String tName;
    @LiField(isPrimaryKey = true, type = LiFieldType.DATETIME, size = 0, comment = "TT时间")
    private Date createTime;

     //不写注解就是不加入数据库字段名
    private String testName;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
