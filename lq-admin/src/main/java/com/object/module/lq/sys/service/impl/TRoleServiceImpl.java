package com.object.module.lq.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TRoleDao;
import com.object.module.lq.sys.entity.TPurview;
import com.object.module.lq.sys.entity.TRoleEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.service.TRoleService;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.utils.PageUtils;
import com.object.utils.PurviewUtile;
import com.object.utils.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("TRoleServiceImpl")
public class TRoleServiceImpl extends ServiceImpl<TRoleDao, TRoleEntity> implements TRoleService {
    @Autowired
    private TRoutingService routingService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<TRoleEntity> wrapper = new QueryWrapper<>();
        String id = (String) params.get("id");
        String name = (String) params.get("name");
        if (StringUtils.isNotEmpty(id)) wrapper.eq("re_id", id);
        if (StringUtils.isNotEmpty(name)) wrapper.or().eq("re_name", name);
        IPage<TRoleEntity> page = this.page(
                new Query<TRoleEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    /**
     * 全部的角色查询
     *
     * @return
     */
    @Override
    public List<TPurview> purviewfindAll() {
        List<TRoutingEntity> list = routingService.list();
        List<TRoutingEntity> orderByRout = findOrderByRout(list);
        //找出二级路由过后 在封装权限
        List<TPurview> purviews = PurviewUtile.orderPurview(orderByRout);
        return purviews;
    }

    /**
     * 找出二级子路由
     */
    private List<TRoutingEntity> findOrderByRout(List<TRoutingEntity> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getParentId() == 0) {
                list.remove(i);
            }
        }
        return list;
    }
}
