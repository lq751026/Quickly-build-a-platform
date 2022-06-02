package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.ov.TUserAndAddUser;
import com.object.utils.PageUtils;
import com.object.utils.Q;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
public interface TUserService extends IService<TUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    TUserEntity findLogin(TUserEntity user);

    boolean updatePassword(String oldpassword, String newpassword, String userId);

    TUserEntity findById(Integer urId);

    TUserEntity infoUrUsername(String urUsername);


    String addUser(Integer userId, Integer addUserId);

    List<TUserAndAddUser>  getAddUsers(Integer userId);

    Q agreeUserId(Integer userId, Integer agreeUserId);

    Q getFriends(Integer userId);
}

