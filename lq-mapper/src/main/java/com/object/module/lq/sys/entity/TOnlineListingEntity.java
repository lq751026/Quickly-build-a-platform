package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

    import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户在线列表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2022-06-07 17:32:09
 */
@Data
@TableName("t_online_listing")
public class TOnlineListingEntity implements Serializable {
    private static final long serialVersionUID = 1L;
       @TableId
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

}
