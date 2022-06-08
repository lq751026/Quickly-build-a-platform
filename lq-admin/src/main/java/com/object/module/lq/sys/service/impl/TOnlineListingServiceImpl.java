package com.object.module.lq.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TOnlineListingDao;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.TOnlineListingService;
import com.object.utils.IpAddressUtil;
import com.object.utils.PageUtils;
import com.object.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.object.module.lq.sys.entity.TOnlineListingEntity;

import javax.servlet.http.HttpServletRequest;


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

    /**
     * 保存登录上的用户
     * @param user1
     * @param request
     */
    @Override
    public void saveLogin(TUserEntity user1, HttpServletRequest request) {
        String ipAddress = IpAddressUtil.getIpAddress(request);
        TOnlineListingEntity on = new TOnlineListingEntity(new Date(), user1.getUrUsername(),
                user1.getUrId(), request.getHeader("user-agent"),ipAddress);
        System.out.println(save(on));
    }

    @Override
    public void deleteUserId(Integer urId) {
        QueryWrapper<TOnlineListingEntity> wrapper=new QueryWrapper<>();
        wrapper.eq("og_ur_id",urId);
        remove(wrapper);
    }

}
