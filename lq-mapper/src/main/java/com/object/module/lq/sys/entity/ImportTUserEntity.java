package com.object.module.lq.sys.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class ImportTUserEntity {

    private String urName, urUsername, urPassword, urEmail;
    private Integer urStuats, urSex;

    private String urAvatar;
    private Integer urReId;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableId(type = IdType.AUTO)
    private Integer urId;
}
