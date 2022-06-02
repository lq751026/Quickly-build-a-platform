package com.object.module.lq.sys.service;

import com.object.utils.Q;
import com.object.module.lq.sys.entity.ChatMesagger;

public interface ChatStorageSerivce {
    Q findChatStrorage(Integer userId, Integer chatUserId);

    Q save(ChatMesagger chatMesagger);
}
