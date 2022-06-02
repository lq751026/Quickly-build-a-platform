package com.object.module.lq.chatroom.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 好友表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-09-05 11:14:17
 */
@Data
@TableName("t_good_friend")
public class TGoodFriendEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自己的id
     */
    @TableId
    private Integer tOwn;
    /**
     * 好友id
     */
    private Integer tGoodFriend;
    /**
     * 分组id
     */
    private Integer tGrouping;
    /**
     * 备注的昵称
     */
    private String tName;
}
