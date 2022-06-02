package com.object.module.lq.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.object.module.lq.sys.entity.TPurview;
import com.object.module.lq.sys.entity.TRoleEntity;
import com.object.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
public interface TRoleService extends IService<TRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TPurview> purviewfindAll();
}

