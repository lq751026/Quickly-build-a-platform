package com.object.module.lq.sys.controller;

import com.object.module.lq.sys.entity.TPurview;
import com.object.module.lq.sys.entity.TRoleEntity;
import com.object.module.lq.sys.service.TRoleService;
import com.object.utils.PageUtils;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-07-24 15:24:06
 */
@RestController
@RequestMapping("/role")
public class TRoleController {
    @Autowired
    private TRoleService roleService;

    /**
     * 查询全部角色
     */
    @GetMapping("findAll")
    public Q findAll() {
        List<TRoleEntity> list = roleService.list();
        return Q.ok().put("data", list);
    }

    /**
     *   全部：权限查询
     */
    @GetMapping("/purview")
     public Q findAllPurview(){
        List<TPurview> list=roleService.purviewfindAll();
        return Q.ok().put("data",list);
     }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Q list(@RequestParam Map<String, Object> params) {
        PageUtils page = roleService.queryPage(params);

        return Q.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{reId}")
    public Q info(@PathVariable("reId") Integer reId) {
        TRoleEntity role = roleService.getById(reId);

        return Q.ok().put("data", role);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TRoleEntity role) {
        roleService.save(role);
        return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TRoleEntity role) {
        roleService.updateById(role);
        return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] reIds) {
        roleService.removeByIds(Arrays.asList(reIds));

        return Q.ok();
    }

}
