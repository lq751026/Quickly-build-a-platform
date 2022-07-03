package com.object.module.lq.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.object.annotation.LiField;
import com.object.annotation.LiTableName;
import com.object.type.LiFieldType;
import lombok.Data;

/**
 * @作者：小庆 🌤
 * @date: 2022/7/3
 * @email:1578442339@qq.com
 **/

@Data
@TableName("t_role_authorization")
@LiTableName(value = "t_role_authorization", comment = "角色-权限-表")
public class TRoleAuthorizationEntity
{
    /**
     * 主键
     */
    @LiField(isPrimaryKey = true, isEmpty = false, type = LiFieldType.INT, comment = "主健")
    @TableId(type = IdType.AUTO)
    private Integer raId;

    @LiField(type = LiFieldType.INT, size = 200, comment = "角色id")
    private Integer reId;
    @LiField(type = LiFieldType.VARCHAR, size = 30, comment = "授权")
    private String raPermission;

}
