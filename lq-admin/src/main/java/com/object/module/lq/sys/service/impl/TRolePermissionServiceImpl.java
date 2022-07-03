package com.object.module.lq.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TRolePermissionDao;
import com.object.module.lq.sys.entity.TRolePermissionEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.TRolePermissionService;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.module.lq.sys.service.TUserService;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TRolePermissionServiceImpl extends ServiceImpl<TRolePermissionDao, TRolePermissionEntity> implements TRolePermissionService {

    @Autowired
    private TUserService userService;


    @Autowired
    private TRoutingService tRoutingService;

    /**
     * 用户查询id
     *
     * @param urId
     * @return
     */
    @Override
    public List<TRoutingEntity> findUserId(Integer urId) {
        TUserEntity user = userService.getById(urId);
        Integer urReId = user.getUrReId();
        QueryWrapper<TRolePermissionEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("re_id", urReId);
        List<TRolePermissionEntity> list = list(wrapper);
        List<Integer> ids = list.parallelStream().map(item -> {
            return item.getRtId();
        }).collect(Collectors.toList());
        if (ids.isEmpty()) return null;
        List<TRoutingEntity> list1 = tRoutingService.listByIds(ids);
        return list1;
    }

    @Override
    public Q infoUrId(Integer urId)
    {
         //获取当前下用户的全部授权码
        return null;
    }
}
