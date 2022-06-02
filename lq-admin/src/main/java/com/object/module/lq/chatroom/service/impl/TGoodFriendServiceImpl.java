package com.object.module.lq.chatroom.service.impl;

import com.object.module.lq.chatroom.entity.TGoodFriendEntity;
import com.object.module.lq.chatroom.service.TGoodFriendService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.utils.PageUtils;
import com.object.utils.Query;

import com.object.dao.chatroom.TGoodFriendDao;


@Service("tGoodFriendService")
public class TGoodFriendServiceImpl extends ServiceImpl<TGoodFriendDao, TGoodFriendEntity> implements TGoodFriendService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TGoodFriendEntity> page = this.page(
                new Query<TGoodFriendEntity>().getPage(params),
                new QueryWrapper<TGoodFriendEntity>()
        );

        return new PageUtils(page);
    }

}
