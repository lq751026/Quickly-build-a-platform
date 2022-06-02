package com.object.module.lq.chatroom.websocketcomfig;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * 聊天的ehandler
 * TextWebSocketFrame  用于为websockt处理文本的对象
 *
 * @author asus
 */
@Slf4j
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channel
    private static Map<Object, Channel> map = new ConcurrentHashMap<>();


    private static Map getUrlParams(String url) {
        Map<String, String> map = new HashMap<>();
        url = url.replace("?", ";");
        if (!url.contains(";")) {
            return map;
        }
        if (url.split(";").length > 0) {
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr) {
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key, value);
            }
            return map;

        } else {
            return map;
        }
    }

    /**
     * 处理   ws连接上的参数
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //首次连接是FullHttpRequest，处理参数 by zhengkai.blog.csdn.net
        if (null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            Map paramMap = getUrlParams(uri);
            //System.out.println("接收到的参数是："+ JSON.toJSONString(paramMap));
            Integer userId = Integer.parseInt((String) paramMap.get("userId"));
            map.put(userId, ctx.channel());
            //如果url包含参数，需要处理
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                //System.out.println(newUri);
                request.setUri(newUri);
            }

        } else if (msg instanceof TextWebSocketFrame) {
            //正常的TEXT消息类型
            TextWebSocketFrame frame = (TextWebSocketFrame) msg;
            //	System.out.println("客户端收到服务器数据：" +frame.text());
        }
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
            throws Exception {
        //客户端传递过来的消息
/*		String content = msg.text();
		String[] strings = content.split("_-_");*/
        String text = msg.text();
        //通知好友有添加信息
        if(text.contains("addUsers_--_")){
             //添加好丫的发送消息
            int userId = Integer.parseInt(text.substring(12));
            //获取用户id
            map.get(userId).writeAndFlush(new TextWebSocketFrame("addUser"));
            return;
        }

         /* 通知好友添加成功了 列表更新 */
        if(text.contains("addfriends_--_")){
            log.info(text,"..........");
             //通知 添加好友的人让他的好友列表刷
            int userId = Integer.parseInt(text.substring(14));
            //找到添加好友用户的通道
            map.get(userId).writeAndFlush(new TextWebSocketFrame("addfriends"));
            return;
        }

        /*  好友之间的聊天  */
        Integer userId = Integer.valueOf(text.substring(text.indexOf("_::_") + 4));
        Integer usercharid = Integer.valueOf(text.substring(text.indexOf(":,:_")+4,text.indexOf("_::_")));
        Channel channel = map.get(usercharid);
        String sendMsg = text.substring(0, text.indexOf(":,:_"));
        if(channel!=null){
             //用户在线直接发送消息
            channel.writeAndFlush(new TextWebSocketFrame(sendMsg));
        }else {
            // 用户不在线 需要把聊天记录存储到缓存里面去  告诉前端 去存储聊天记录去
            map.get(userId).writeAndFlush(new TextWebSocketFrame("savechatMsg:<<"+sendMsg));
        }

    }

    //客户端创建的时候触发，当客户端连接上服务端之后，就可以获取该channel，然后放到channelGroup中进行统一管理
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }


    //客户端销毁的时候触发，
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //	System.out.println("客户端断开，当前被移除的channel的短ID是：" +ctx.channel().id().asShortText());
    }

}
