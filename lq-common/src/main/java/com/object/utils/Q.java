package com.object.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

//定义具体返回数据的使用状态码格式
public class Q {

    private Boolean success;

    private Integer code;

    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    private Map<String, Object> data = new HashMap<>();

    private Q() {
    }

    public static Q ok() {
        Q r = new Q();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("操作成功");
        return r;
    }

    public static Q error() {
        Q r = new Q();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("操作失败");
        return r;
    }

    public static Q no(int i, String s) {
        Q q = new Q();
        q.setCode(i);
        q.setMessage(s);
        return q;
    }

    //使用链式编程
    public Q success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Q message(String message) {
        this.setMessage(message);
        return this;
    }

    public Q code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Q put(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Q q(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Q put(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
