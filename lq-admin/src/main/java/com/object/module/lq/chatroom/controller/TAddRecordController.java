package com.object.module.lq.chatroom.controller;

import java.util.Arrays;
import java.util.Map;

import com.object.module.lq.chatroom.entity.TAddRecordEntity;
import com.object.module.lq.chatroom.service.TAddRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.object.utils.PageUtils;
import com.object.utils.Q;


/**
 * 好友添加记录表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-09-05 11:14:17
 */
@RestController
@RequestMapping("/taddrecord")
public class TAddRecordController {
    @Autowired
    private TAddRecordService tAddRecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Q list(@RequestParam Map<String, Object> params) {
        PageUtils page = tAddRecordService.queryPage(params);
        return Q.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{rUIn}")
    public Q info(@PathVariable("rUIn") Integer rUIn) {
            TAddRecordEntity tAddRecord = tAddRecordService.getById(rUIn);
            return Q.ok().put("tAddRecord", tAddRecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TAddRecordEntity tAddRecord) {
            tAddRecordService.save(tAddRecord);
            return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TAddRecordEntity tAddRecord) {
            tAddRecordService.updateById(tAddRecord);
            return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] rUIns) {
            tAddRecordService.removeByIds(Arrays.asList(rUIns));
            return Q.ok();
    }

}
