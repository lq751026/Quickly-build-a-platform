package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TOnlineListingEntity;
import com.object.utils.PageUtils;

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
}

