package com.object.module.lq.sys.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
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
import javax.servlet.http.HttpServletResponse;

@RestController
/**
 *  验证码和登录
 */
@Log4j
@RequestMapping("index")
public class LoginController {

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
    public Q captcha(HttpServletRequest request, HttpServletResponse response) {
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
    public Q login(@RequestBody(required = false) TUserEntity user, HttpServletRequest request) {
        //首页判断验证正确
        if (PasswordEncryp.IfPassowrd(user.getCaptcha(), user.getCodeEncryption())) {
            TUserEntity user1 = userService.findLogin(user);
            if (user1 != null) {
                //登录成功
                StpUtil.login(user1.getUrId());
                StpUtil.getSession().set("userName", user1.getUrName());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                return Q.ok().put("token", tokenInfo).put("userId", user1.getUrId()).put("user",user1);
            } else {
                return Q.error().put("msg", "登录失败检查用户名或者密码!");
            }
        } else {
            return Q.error().put("msg", "验证码错误!");
        }
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @SaCheckLogin
    @GetMapping("/out/{userId}")
    public Q out(@PathVariable("userId") Integer userId) {
        StpUtil.logoutByLoginId(userId);
        return Q.ok();
    }

}
