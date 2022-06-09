package com.object.module.lq.sys.controller;

import java.util.Arrays;
import java.util.Map;

import com.object.module.lq.sys.service.TOnlineListingService;
import com.object.utils.PageUtils;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.object.module.lq.sys.entity.TOnlineListingEntity;



/**
 * 用户在线列表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2022-06-07 17:32:09
 */
@RestController
@RequestMapping("/tonlinelisting")
public class TOnlineListingController {
    @Autowired
    private TOnlineListingService tOnlineListingService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Q list(@RequestParam Map<String, Object> params) {
        PageUtils page = tOnlineListingService.queryPage(params);
        return Q.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ogId}")
    public Q info(@PathVariable("ogId") Integer ogId) {
            TOnlineListingEntity tOnlineListing = tOnlineListingService.getById(ogId);
            return Q.ok().put("tOnlineListing", tOnlineListing);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TOnlineListingEntity tOnlineListing) {
            tOnlineListingService.save(tOnlineListing);
            return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TOnlineListingEntity tOnlineListing) {
            tOnlineListingService.updateById(tOnlineListing);
            return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] ogIds) {
            tOnlineListingService.outUserLogin(Arrays.asList(ogIds));
            return Q.ok();
    }

}
