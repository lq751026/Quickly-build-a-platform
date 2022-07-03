package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TRolePermissionEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.utils.Q;

import java.util.List;

/**
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
public interface TRolePermissionService extends IService<TRolePermissionEntity> {
    List<TRoutingEntity> findUserId(Integer urId);

    Q infoUrId(Integer urId);
}

