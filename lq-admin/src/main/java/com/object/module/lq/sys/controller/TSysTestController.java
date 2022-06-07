package com.object.module.lq.sys.controller;

import com.object.module.lq.sys.entity.TMetaEntity;
import com.object.module.lq.sys.entity.TRouterEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TRoutingService routingService;
    @GetMapping()
    public Q  getAll(){

        return Q.ok().put("data","null");
    }
}
