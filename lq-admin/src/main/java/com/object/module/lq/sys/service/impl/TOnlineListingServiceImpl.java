package com.object.module.lq.sys.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.object.dao.sys.TOnlineListingDao;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.TOnlineListingService;
import com.object.utils.OnlineTools;
import com.object.utils.PageUtils;
import com.object.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
     *
     * @param user1
     * @param request
     */
    @Override
    public void saveLogin(TUserEntity user1, HttpServletRequest request) {
        String ipAddress = OnlineTools.getIpAddress(request);
        TOnlineListingEntity on = new TOnlineListingEntity(new Date(), user1.getUrUsername(),
                user1.getUrId(), OnlineTools.equipment(request.getHeader("user-agent")), ipAddress);
        save(on);
    }

    @Override
    public void deleteUserId(Integer urId) {
        QueryWrapper<TOnlineListingEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("og_ur_id", urId);
        remove(wrapper);
    }

    @Override
    public void outUserLogin(List<Integer> asList) {
        List<TOnlineListingEntity> tOnlineListingEntities = listByIds(asList);
        for (TOnlineListingEntity onlineListing : tOnlineListingEntities) {
            //强制退出
            StpUtil.logout(onlineListing.getOgUrId());
        }
        //再删除掉在线用户列表
        removeByIds(asList);
    }

}
