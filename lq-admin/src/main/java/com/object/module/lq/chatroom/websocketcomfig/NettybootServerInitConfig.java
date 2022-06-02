package com.object.module.lq.chatroom.websocketcomfig;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * netty服务端启动加载配置
 * @author asus
 *
 */

@Component
public class NettybootServerInitConfig implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
			
		if(event.getApplicationContext().getParent() == null){
			WssServer.getInstance().start();
		}
	}

}
