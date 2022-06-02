package com.object.module.lq.sys.entity;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.baomidou.mybatisplus.annotation.*;
import com.object.excelhandler.UserSexHandler;
import com.object.excelhandler.UserStateHandler;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
@Data
@ExcelIgnoreUnannotated
@ContentStyle(shrinkToFit = true,horizontalAlignment = HorizontalAlignment.CENTER,dataFormat = 1)
@HeadStyle(shrinkToFit = true,horizontalAlignment = HorizontalAlignment.CENTER,fillBackgroundColor=50)
@TableName("t_user")
public class TUserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id，主键
     */
    @TableId(type = IdType.AUTO)
    private Integer urId;
    /**
     * 昵称
     */
    @ExcelProperty("昵称")
    private String urName;
    /**
     * 用户名
     */
    @ExcelProperty("用户名")
    private String urUsername;
    /**
     * 密码
     */
    private String urPassword;
    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String urEmail;
    /**
     * 账号的注册时间
     */
    @ExcelProperty("账号的注册时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 头像
     */
    private String urAvatar;
    /**
     * 状态(0.冻结，1.正常 )
     */
    @ExcelProperty(value = "状态",converter = UserStateHandler.class)
    private String urStuats;
    /**
     * 角色id
     */
    private Integer urReId;
    /**
     * 性别
     */
    @ExcelProperty(value = "状态",converter = UserSexHandler.class)
    private Integer urSex;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private String captcha;
    /**
     * 验证码加密值
     */
    @TableField(exist = false)
    private String codeEncryption;
    /**
     *  角色
     */
    @TableField(exist = false)
    private TRoleEntity role;
}
