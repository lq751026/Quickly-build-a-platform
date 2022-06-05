package com.object.module.lq.sys.controller;

import com.object.module.lq.sys.entity.TMetaEntity;
import com.object.module.lq.sys.entity.TRouterEntity;
import com.object.utils.Q;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：小庆 🌤
 * @date: 2022/6/3
 * @email:1578442339@qq.com
 **/
@RestController
@RequestMapping("/test")
public class TSysTestController {

    @GetMapping()
    public Q  getAll(){


        List<TRouterEntity> routers=new ArrayList<>();
        TRouterEntity routerEntity = new TRouterEntity(1,"/sys","sys");
        routerEntity.setMeta(new TMetaEntity("menu.sys",true,"icon-qq",2));
        List<TRouterEntity> routerEntities=new ArrayList<>();
        TRouterEntity routerEntity1 = new TRouterEntity(2,"sysUser","sysUser");
        routerEntity1.setMeta(new TMetaEntity(1,"menu.sys.sysUser",true,"icon-list",2,"['*']"));

        TRouterEntity routerEntity2 = new TRouterEntity(3,"sysRole","sysRole");
        routerEntity2.setMeta(new TMetaEntity(2,"menu.sys.sysRole",true,"icon-list",2,"['*']"));

        routerEntities.add(routerEntity1);
        routerEntities.add(routerEntity2);

        routerEntity.setChildren(routerEntities);
        routers.add(routerEntity);
        return Q.ok().put("data",routers);
    }
}
