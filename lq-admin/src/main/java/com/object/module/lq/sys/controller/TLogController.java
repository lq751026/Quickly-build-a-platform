package com.object.module.lq.sys.controller;


import java.util.Map;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.object.module.lq.sys.service.TLogService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.object.utils.PageUtils;
import com.object.utils.Q;


/**
 * 日志表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-25 17:58:44
 */
@RestController
@SaCheckLogin
@RequestMapping("/log")
public class TLogController {
    @Autowired
    private TLogService logService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Q list(@RequestParam Map<String, Object> params) {
        PageUtils page = logService.queryPage(params);
        return Q.ok().put("page", page);
    }

}
