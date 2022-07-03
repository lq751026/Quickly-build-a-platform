package com.object.module.lq.sys.controller;

import com.object.module.lq.sys.service.TRolePermissionService;
import com.object.utils.Q;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者：小庆 🌤
 * @date: 2022/7/3
 * @email:1578442339@qq.com *   权限授别 授权
 */
@RestController
@RequestMapping("/role/permission")
public class TRolePermissionController
{
    @Autowired
    private TRolePermissionService tRolePermissionService;

    @GetMapping("/info/urId")
    public Q infoUrId(@PathVariable("urId") Integer urId)
    {
          return tRolePermissionService.infoUrId(urId);
    }
}
