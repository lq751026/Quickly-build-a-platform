package com.object.config;

import cn.dev33.satoken.stp.StpInterface;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.service.TRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */

@Component    // 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展
@Configuration
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private TRolePermissionService rolePermissionService;

    /**
     * 返回一个账号所拥有的角色集合  获取权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<String>();
        List<TRoutingEntity> routingEntities = rolePermissionService.findUserId(Integer.parseInt(loginId.toString()));
        if (routingEntities == null) return null;
        log.debug("账号角色查询长度 :" + routingEntities.size());
        for (TRoutingEntity routingEntity : routingEntities) {
            //判断当前的路不可以说父级目录
            if (routingEntity.getParentId() != 0) list.add(routingEntity.getPath());
        }
        return list;
    }


    /**
     * 返回一个账号所拥有的权限标识集合 (权限与角色可分开校验)  获取角色列表
     */

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        List<TRoutingEntity> routingEntities = rolePermissionService.findUserId(Integer.parseInt(loginId.toString()));
        for (TRoutingEntity routingEntity : routingEntities) {
            //判断当前的路不可以说父级目录
            if (routingEntity.getParentId() != 0) {
                list.add(routingEntity.getPath() + "-read");
                list.add(routingEntity.getPath() + "-write");
                list.add(routingEntity.getPath() + "-update");
            }
        }
        log.debug("账号权限查询长度 :" + list.size());
        return list;
    }

}

