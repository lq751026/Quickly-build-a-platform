package com.object.utils;

import cn.dev33.satoken.stp.StpUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前token的user的id
 */
public class GetUserId {
     public static Integer id(HttpServletRequest request){
         Integer id =  Integer.parseInt(StpUtil.getLoginIdByToken(request.getHeader("satoken")).toString());
      return id;
     }
}
