package com.object.module.lq.sys.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.utils.GetUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.object.utils.Q;

import javax.servlet.http.HttpServletRequest;


/**
 * 路由
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-26 13:40:32
 */
@RestController
@RequestMapping("/routing")
public class TRoutingController {
    @Autowired
    private TRoutingService routingService;

    /**
     * 列表
     */
    @RequestMapping("/treeAll")
    public Q list() {
        return routingService.treeAll();
    }

    /**
     * 全部查询
     */
    @GetMapping("findAll")
    public Q findAll(HttpServletRequest request) {
     return  routingService.listOrderBy(GetUserId.id(request));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Q info(@PathVariable("id") Integer id) {
        TRoutingEntity routing = routingService.getById(id);
        return Q.ok().put("routing", routing);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TRoutingEntity routing) {
        routing.setCreateTime(new Date());
        routingService.save(routing);
        return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TRoutingEntity routing) {
        routingService.updateById(routing);
        return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] ids) {
        routingService.removeByIds(Arrays.asList(ids));
        return Q.ok();
    }

    /**
     * 权限查询
     */
    @GetMapping("/purview")
     public Q purview(Integer userId,Integer routId){
        Map<String,String> map=routingService.findUserIByroutId(userId,routId);
        return Q.ok().put("data",map);
    }
}
