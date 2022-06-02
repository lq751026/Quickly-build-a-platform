package com.object.module.lq.chatroom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.utils.PageUtils;
import com.object.module.lq.chatroom.entity.TGoodFriendEntity;

import java.util.Map;

/**
 * 好友表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-09-05 11:14:17
 */
public interface TGoodFriendService extends IService<TGoodFriendEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

