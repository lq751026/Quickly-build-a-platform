package com.object.module.lq.sys.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @作者：小庆 🌤
 * @date: 2022/6/3
 * @email:1578442339@qq.com
 **/
@RestController
@RequestMapping("/test")
public class TSysTestController {

    @GetMapping()
    public Q  getAll(String token){
        return Q.ok().put("data", StpUtil.getLoginIdByToken(token));
    }
}
