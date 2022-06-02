package com.object.aop;

import lombok.extern.slf4j.Slf4j;
import com.object.module.lq.sys.entity.TLogEntity;
import com.object.module.lq.sys.service.TLogService;
import com.object.utils.IpAddressUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 *  日志记录aop
 */
@Configuration
@Aspect  //切面类
@Slf4j
public class LogAop {
     @Autowired
     private TLogService logService;

    @Pointcut("execution(public * com.object.module.lq.sys.controller.LoginController.login(..))")
    public void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //  System.out.println("前置通知");
        long beginTime = System.currentTimeMillis();
        Object proceed = null;
        try {
           // System.out.println("后值通知");
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            //System.out.println("异常通知");
        } finally {
            long time = System.currentTimeMillis() - beginTime;
            //System.out.println("最终通知");
            saveLog(joinPoint,time);
        }
        return proceed;

    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {

        // 获取方法的关键信息，类，包
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        TLogEntity sysLogEntity = new TLogEntity();
        //设置执行时长
         double Time=time/60/60;
        sysLogEntity.setExeuTime(Time+"秒");
        sysLogEntity.setCreateTime(new Date());
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        //设置类名
        sysLogEntity.setClassName(className);
        //设置方法名
        sysLogEntity.setMethodName(methodName);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURI();
        String ip = IpAddressUtil.getIpAddress(request);
        //设置执行的url
        sysLogEntity.setUrl(url);
        //设置来自执行的ip
        sysLogEntity.setIp(ip);
        String  userName = request.getParameter("userName");
        //设置执行人的名
        sysLogEntity.setUserName(userName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params="";
            for (Object o : args) {
                params+=o.toString();
            }
            sysLogEntity.setParams(params);
        } catch (Exception e) {

        }
         //保存日志
        logService.save(sysLogEntity);
    }


}
