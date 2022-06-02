package com.object.module.lq.chatroom.service.impl;

import com.object.module.lq.chatroom.entity.TAddRecordEntity;
import com.object.module.lq.chatroom.service.TAddRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.utils.PageUtils;
import com.object.utils.Query;

import com.object.dao.chatroom.TAddRecordDao;


@Service("tAddRecordService")
public class TAddRecordServiceImpl extends ServiceImpl<TAddRecordDao, TAddRecordEntity> implements TAddRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TAddRecordEntity> page = this.page(
                new Query<TAddRecordEntity>().getPage(params),
                new QueryWrapper<TAddRecordEntity>()
        );

        return new PageUtils(page);
    }

}
