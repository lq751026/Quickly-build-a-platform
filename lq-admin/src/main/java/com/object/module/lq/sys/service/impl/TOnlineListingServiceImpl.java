package com.object.module.lq.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TOnlineListingDao;
import com.object.module.lq.sys.service.TOnlineListingService;
import com.object.utils.PageUtils;
import com.object.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.object.module.lq.sys.entity.TOnlineListingEntity;



@Service("tOnlineListingService")
public class TOnlineListingServiceImpl extends ServiceImpl<TOnlineListingDao, TOnlineListingEntity> implements TOnlineListingService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TOnlineListingEntity> page = this.page(
                new Query<TOnlineListingEntity>().getPage(params),
                new QueryWrapper<TOnlineListingEntity>()
        );

        return new PageUtils(page);
    }

}
