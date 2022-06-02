package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 路由
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-26 13:40:32
 */
public interface TRoutingService extends IService<TRoutingEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TRoutingEntity> listOrderBy(Integer userId);

    Map<String, String> findUserIByroutId(Integer userId, Integer routId);
}

