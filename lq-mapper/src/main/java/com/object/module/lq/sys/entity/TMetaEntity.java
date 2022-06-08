package com.object.module.lq.sys.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @作者：小庆 🌤
 * @date: 2022/6/3
 * @email:1578442339@qq.com
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TMetaEntity {
    private Integer id;
    private String locale;
    private boolean requiresAuth;
    private String icon;
    private int order;
    private String roles;

    public TMetaEntity(String locale, boolean requiresAuth, String icon, int order) {
        this.locale = locale;
        this.requiresAuth = requiresAuth;
        this.icon = icon;
        this.order = order;
    }
}
