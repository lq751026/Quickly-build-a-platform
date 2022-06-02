package com.object.module.lq.sys.controller;

import com.object.utils.Q;
import com.object.utils.SysUtile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping
    public Q sysAll(){
       return Q.ok().put("jvm",SysUtile.printSummary());
     }
}
