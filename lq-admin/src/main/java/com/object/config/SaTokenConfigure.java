package com.object.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Log4j2
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
    // 注册Sa-Token的注解拦截器，打开注解式鉴权功能
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义验证规则
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
                    SaRouter.match("/testRole/index", r -> StpUtil.checkRole("sysUser"));
/**
 *  角色
 */
 /**
                    SaRouter.match("/user/**", r -> StpUtil.checkRole("sysUser"));
                    SaRouter.match("/routing/**", r -> StpUtil.checkRole("LuYou"));
                    SaRouter.match("/role/**", r -> StpUtil.checkRole("sysRole"));
                    SaRouter.match("/tonlinelisting/**", r -> StpUtil.checkRole("onlinelisting"));
                    SaRouter.match("/log/**", r -> StpUtil.checkRole("log"));
                    SaRouter.match("/file/**", r -> StpUtil.checkRole("file"));
                    **/

                })).addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/img/**", "/index/**", "/test/**", "/msg/**",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/document.html",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs/**",
                        "/webjars/springfox-swagger-ui/**");
        registry.addInterceptor(new SaAnnotationInterceptor());
    }
}
