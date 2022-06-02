package com.object.module.lq.chatroom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 好友添加记录表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-09-05 11:14:17
 */
@Data
@TableName("t_add_record")
public class TAddRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发起id
     */
    @TableId
    private Integer rUIn;
    /**
     * 接受id
     */
    private Integer rUOut;
    /**
     * 发起时间
     */
    private Date rUTimeIn;
    /**
     * 接受时间
     */
    private Date rUTimeOut;


    /**
     * 添加时备注
     */
    private String rURemarks;

}
