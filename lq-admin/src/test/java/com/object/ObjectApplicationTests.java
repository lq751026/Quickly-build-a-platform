package com.object;


import com.object.module.lq.sys.entity.ChatMesagger;
import com.object.module.lq.sys.entity.TMetaEntity;
import com.object.module.lq.sys.entity.TRouterEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.utils.NubersUtile;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootTest
class ObjectApplicationTests {
    private final String salt = "你设置的密码";
  @Autowired
        private TRoutingService routingService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Test
    public void testEncrypt() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword("751026lq"); // 盐值
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "751026lq"; // 密码明文
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println(encryptedText);

    }

    @Test
    public void test1() {
        List<TRoutingEntity> list = routingService.list();
        //找出一级分类
        List<TRoutingEntity> collect = list.stream().filter((SortEntity -> {
            return SortEntity.getParentId() == 0;
        })).map(menu -> {
            menu.setChildren(getChider(menu, list));
            return menu;
        }).collect(Collectors.toList());
        System.out.println(collect);
        //return collect;
    }

    public List<TRoutingEntity> getChider(TRoutingEntity menu, List<TRoutingEntity> list) {
        List<TRoutingEntity> data = list.stream().filter(rout -> {
            return rout.getParentId() == menu.getId();
        }).map(rout -> {
            //找到子菜单
            rout.setChildren(getChider(rout, list));
            return rout;
        }).collect(Collectors.toList());
        return data;
    }

    @Test
    public void s() {
        String str = "1,3,4,5,6,7,8,13,15";
        System.out.println(NubersUtile.nuberRouting(str, routingService.list()));

    }

    @Test
    public void saveMessageChatRomm() {
        String key = "1_id_5";
        List<ChatMesagger> list = new ArrayList<>();
        ChatMesagger chatMesagger = new ChatMesagger();
        chatMesagger.setUserId(1);
        chatMesagger.setCharuserId(5);
        chatMesagger.setChatmsg("你好");
        list.add(chatMesagger);
        ChatMesagger chatMesagger1 = new ChatMesagger();
        chatMesagger1.setUserId(5);
        chatMesagger1.setCharuserId(1);
        chatMesagger1.setChatmsg("你也好");
        list.add(chatMesagger1);
        redisTemplate.opsForValue().set(key, list, 100, TimeUnit.DAYS);
        System.out.println("存储成功");
    }

    @Test
    public void getMessageChatRomm() {
        String key = "1_id_5";
        List<ChatMesagger> list = (List<ChatMesagger>) redisTemplate.opsForValue().get(key);
        list.forEach(chatMesagger -> {
            System.out.println(chatMesagger);
        });
    }





    @Test
     public  void  rounter(){
        List<TRoutingEntity> orderBy = routingService.listOrderBy(1);
        List<TRouterEntity> routers=new ArrayList<>();
        for (TRoutingEntity tRoutingEntity : orderBy) {
            String pathName = tRoutingEntity.getPath().replaceAll("/", "");
            TRouterEntity routerEntity = new TRouterEntity(tRoutingEntity.getId(),tRoutingEntity.getPath(),pathName);

            routerEntity.setMeta(new TMetaEntity(tRoutingEntity.getMenuName(),true,tRoutingEntity.getIcon(),2));
            List<TRouterEntity> routerEntities=new ArrayList<>();
            for (TRoutingEntity child : tRoutingEntity.getChildren()) {
                String pathName1 = child.getPath().replaceAll("/", "");
                TRouterEntity routerEntity1 = new TRouterEntity(child.getId(),pathName1,pathName1);
                routerEntity1.setMeta(new TMetaEntity(child.getId(),child.getMenuName(),true,child.getIcon(),2,"['*']"));
                routerEntities.add(routerEntity1);
            }

            routerEntity.setChildren(routerEntities);
            routers.add(routerEntity);
        }

        for (TRouterEntity router : routers) {
            System.out.println(router);
        }
    }

}
