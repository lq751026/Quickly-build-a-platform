package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户在线列表
 *
 * @author å°ð¤
 * @email 1578442339@qq.com
 * @date 2022-06-11 16:15:55
 */
@Data
@TableName("t_online_listing")
@AllArgsConstructor
@NoArgsConstructor
public class TOnlineListingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer ogId;
    /**
     * 登录时间
     */
    private Date ogOnlineTime;
    /**
     * 登录用户名
     */
    private String ogUserName;
    /**
     * 用户id
     */
    private Integer ogUrId;
    /**
     * 登录设备
     */
    private String ogEquipment;
    /**
     * 登录ip
     */
    private String ogIp;

    public TOnlineListingEntity(Date ogOnlineTime, String ogUserName, Integer ogUrId, String ogEquipment, String ogIp) {
        this.ogOnlineTime = ogOnlineTime;
        this.ogUserName = ogUserName;
        this.ogUrId = ogUrId;
        this.ogEquipment = ogEquipment;
        this.ogIp = ogIp;
    }
}
