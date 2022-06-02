package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 路由
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-26 13:40:32
 */
@Data
@TableName("t_routing")
public class TRoutingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 路由
     */
    private String path;
    /**
     * 路由的可见 1:不可见  默认是：0可见
     */
    private Integer hidden;

    /**
     * 重定向 一级路由才有的
     */
    private  String redirect;

    /**
     * 上级id
     */
    private Integer parentId;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 图标
     */
    private String icon;
    /**
     * 授权识别
     */
    private String perms;
    /**
     * 绝对路由的位置
     */
    private String componentUrl;

    /**
     * 子路由
     */
    @TableField(exist = false)
    private List<TRoutingEntity> children;

}
