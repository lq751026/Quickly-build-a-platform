package com.object.module.lq.sys.service.impl;

import com.object.module.lq.sys.entity.TLogEntity;
import com.object.module.lq.sys.service.TLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.utils.PageUtils;
import com.object.utils.Query;

import com.object.dao.sys.TLogDao;


@Service("TLogServiceImpl")
public class TLogServiceImpl extends ServiceImpl<TLogDao, TLogEntity> implements TLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<TLogEntity> wrapper = new QueryWrapper<>();
        String key= (String) params.get("key");
        if(!StringUtils.isEmpty(key)){
            wrapper.eq("user_name",key);
        }
        IPage<TLogEntity> page = this.page(
                new Query<TLogEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

}
