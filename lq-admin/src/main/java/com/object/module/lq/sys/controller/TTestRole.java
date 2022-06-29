package com.object.module.lq.sys.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testRole")
public class TTestRole {

   // @SaCheckRole("sysUser")
    @GetMapping("index")
    public String index() {
        return "我进来了。。。";
    }

    @SaCheckPermission("-read")
    @GetMapping("login/{id}")
    public String login(@PathVariable("id")Long id) {
        StpUtil.login(id);
        return StpUtil.getTokenValue();
    }

}
