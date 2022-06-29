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
 * 权限表
 */
@Data
@TableName("t_role_permission")
@LiTableName(value = "t_role_permission", comment = "权限表")
public class TRolePermissionEntity {
    /**
     * 主键
     */
    @LiField(isPrimaryKey = true, type = LiFieldType.INT, comment = "主健")
    @TableId(type = IdType.AUTO)
    private Integer rpId;

    @LiField(type = LiFieldType.INT, size = 200, comment = "角色id")
    private Integer reId;
    @LiField(type = LiFieldType.INT, size = 200, comment = "路由角色")
    private Integer rtId;

    /**
     * 数据排除字段
     */
    @TableField(exist = false)
    private String rtName;
}
