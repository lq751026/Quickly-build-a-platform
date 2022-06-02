package com.object.module.lq.chatroom.controller;

import java.util.Arrays;
import java.util.Map;

import com.object.module.lq.chatroom.entity.TGoodFriendEntity;
import com.object.module.lq.chatroom.service.TGoodFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.object.utils.PageUtils;
import com.object.utils.Q;


/**
 * 好友表
 *
 * @author 小🌤
 * @email 1578442339@qq.com
 * @date 2021-09-05 11:14:17
 */
@RestController
@RequestMapping("/tgoodfriend")
public class TGoodFriendController {
    @Autowired
    private TGoodFriendService tGoodFriendService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Q list(@RequestParam Map<String, Object> params) {
        PageUtils page = tGoodFriendService.queryPage(params);
        return Q.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{tOwn}")
    public Q info(@PathVariable("tOwn") Integer tOwn) {
            TGoodFriendEntity tGoodFriend = tGoodFriendService.getById(tOwn);
            return Q.ok().put("tGoodFriend", tGoodFriend);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Q save(@RequestBody TGoodFriendEntity tGoodFriend) {
            tGoodFriendService.save(tGoodFriend);
            return Q.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Q update(@RequestBody TGoodFriendEntity tGoodFriend) {
            tGoodFriendService.updateById(tGoodFriend);
            return Q.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public Q delete(@RequestBody Integer[] tOwns) {
            tGoodFriendService.removeByIds(Arrays.asList(tOwns));
            return Q.ok();
    }

}
