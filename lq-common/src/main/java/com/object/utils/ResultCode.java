package com.object.utils;

/**
 * 定义返回数据使用的状态码
 */
public interface ResultCode {
    int SUCCESS = 200; //成功状态码
    int ERROR = 201;//失败状态码
    int AUTH = 300;//没有操作权限状态码

}
