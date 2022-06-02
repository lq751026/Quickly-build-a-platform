package com.object.module.lq.sys.entity;

import lombok.Data;

@Data
public class ChatMesagger {

    private String chatmsg;
    private Integer userId;
    private Integer charuserId;
    private TUserEntity chatuser;
    private TUserEntity user;

}
