package com.object.module.lq.sys.controller;


import com.object.module.lq.sys.entity.ChatMesagger;
import com.object.module.lq.sys.service.ChatStorageSerivce;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 聊天纪录
 */
@RestController
@RequestMapping("/chats")
public class TChatStorageController {

    @Autowired
    private ChatStorageSerivce chatStorageSerivce;

    @GetMapping("/strorage/{userId}/{chatUserId}")
    public Q strOage(@PathVariable Integer userId, @PathVariable Integer chatUserId) {
        Q q = chatStorageSerivce.findChatStrorage(userId, chatUserId);
        return q;
    }


    @PostMapping("/save")
    public Q save(@RequestBody ChatMesagger chatMesagger){
        Q q=chatStorageSerivce.save(chatMesagger);
        return q;
    }

}
