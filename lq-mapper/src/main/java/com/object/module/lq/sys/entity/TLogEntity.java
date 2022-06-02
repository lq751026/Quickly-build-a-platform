package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-25 17:58:44
 */
@Data
@TableName("t_log")
public class TLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类名
     */
    @TableId
    private String className;
    /**
     * 方法明
     */
    private String methodName;
    /**
     * 方法参数
     */
    private String params;
    /**
     * 执行时长
     */
    private String exeuTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 用户名
     */
    private String userName;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 请求路径
     */
    private String url;

}
