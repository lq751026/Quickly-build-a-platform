package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TOnlineListingEntity;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.utils.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户在线列表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2022-06-07 17:32:09
 */
public interface TOnlineListingService extends IService<TOnlineListingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveLogin(TUserEntity user1, HttpServletRequest request);

    void deleteUserId(Integer urId);

    void outUserLogin(List<Integer> asList);
}

