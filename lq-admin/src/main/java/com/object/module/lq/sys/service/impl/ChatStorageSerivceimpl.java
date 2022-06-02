package com.object.module.lq.sys.service.impl;

import com.object.module.lq.sys.entity.ChatMesagger;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.ChatStorageSerivce;
import com.object.module.lq.sys.service.TUserService;
import com.object.utils.Q;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChatStorageSerivceimpl implements ChatStorageSerivce {

    @Value("${redis.survival.time}")
    private Integer time;

    @Autowired
    private TUserService tUserService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 查询 redis的 聊天记录
     *
     * @param userId
     * @param chatUserId
     * @return
     */
    @Override
    public Q findChatStrorage(Integer userId, Integer chatUserId) {
        String key = userId > chatUserId ? chatUserId + "_id_" + userId : userId + "_id_" + chatUserId;
        List<ChatMesagger> list = (List<ChatMesagger>) redisTemplate.opsForValue().get(key);
        //如果当前还没聊天记录或者是聊天记录被删了我们还是需要把跟用户聊天的用户信息传入到前端去
        TUserEntity userEntity;
        TUserEntity user;
        if (list != null) {
            list.forEach(chat -> {
                chat.setUser(tUserService.getById(userId));
                chat.setChatuser(tUserService.getById(chatUserId));
            });
        }
        userEntity = tUserService.getById(chatUserId);
        user = tUserService.getById(userId);
        return Q.ok().put("data", list).put("userChat", userEntity).put("user", user);
    }

    /**
     * 保存 聊天记录
     *
     * @param chatMesagger
     * @return
     */
    @Override
    public Q save(ChatMesagger chatMesagger) {
        Integer userId = chatMesagger.getUserId();
        Integer charuserId = chatMesagger.getCharuserId();
        String key = userId > charuserId ? charuserId + "_id_" + userId : userId + "_id_" + charuserId;
        List<ChatMesagger> list = (List<ChatMesagger>) redisTemplate.opsForValue().get(key);
       if(list!=null){
           list.add(chatMesagger);
       }else{
           list=new ArrayList<>();
           list.add(chatMesagger);
       }
        redisTemplate.opsForValue().set(key, list, time, TimeUnit.DAYS);
        return Q.ok();
    }


}
