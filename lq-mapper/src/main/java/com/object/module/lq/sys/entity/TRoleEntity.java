package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
@Data
@TableName("t_role")
public class TRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer reId;
    /**
     * 角色名
     */
    private String reName;

    /**
     *  授权识
     */
    private String authority;

    /**
     * 权限控制    增:add   删delete  修改update
     * 1:add,delete-2:add,delete,update-3:add,update
     */
    private String purview;

    @TableField(exist = false)
    private TPurview pur;
}
