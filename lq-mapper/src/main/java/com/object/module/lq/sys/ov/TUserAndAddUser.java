package com.object.module.lq.sys.ov;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/* 好友申请列表实体类 */
@Data
public class TUserAndAddUser {
    private String urName;
    private String urUsername;
    private String urAvatar;
    /**
     * 发起时间
     */
    private Date rUTimeIn;
    private Integer rUIn;
    /**
     *  状态码  0 没有接收  1是同意了
     */
    private Date rUTimeOut;
    @TableField(exist = false)
    private int status;

    public int getStatus() {
        if(this.rUTimeOut==null){
             status=0;
        }else{
             status=1;
        }
        return status;
    }
}
