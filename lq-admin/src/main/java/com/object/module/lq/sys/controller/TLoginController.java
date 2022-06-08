package com.object.module.lq.sys.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.TUserService;
import com.object.utils.PasswordEncryp;
import com.wf.captcha.ArithmeticCaptcha;
import com.object.utils.Q;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
/**
 *  验证码和登录
 */
@Log4j
@RequestMapping("index")
public class TLoginController {

    @Autowired
    private TUserService userService;

    /**
     * 验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/captcha")
    public Q captcha() {
        //算术验证码 数字加减乘除. 建议2位运算就行:captcha.setLen(2);
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(120, 40);
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        String codeEncryption = PasswordEncryp.getPaawordEncryp(result);
        String captchas = captcha.toBase64();
        return Q.ok().put("captcha", captchas).code(200).put("codeEncryption", codeEncryption);
    }


    /**
     * 登录
     *
     * @param
     * @return
     */
    @PostMapping("/login")
    public Q login(@RequestBody(required = false) TUserEntity user,HttpServletRequest request) {
        return  userService.login(user,request);
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/out")
    public Q out( @RequestHeader("satoken") String satoken) {
        userService.loginOut(satoken);
        return Q.ok();
    }

}
