package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TLogEntity;
import com.object.utils.PageUtils;

import java.util.Map;

/**
 * 日志表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-25 17:58:44
 */
public interface TLogService extends IService<TLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

