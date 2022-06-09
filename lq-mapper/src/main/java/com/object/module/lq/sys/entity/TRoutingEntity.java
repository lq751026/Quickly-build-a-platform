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
     * 子路由
     */
    @TableField(exist = false)
    private List<TRoutingEntity> children;

}
