package com.object;


import com.alibaba.excel.EasyExcel;
import com.object.module.lq.sys.entity.ChatMesagger;
import com.object.module.lq.sys.entity.ImportTUserEntity;
import com.object.module.lq.sys.entity.TRoutingEntity;
import com.object.module.lq.sys.entity.TUserEntity;
import com.object.module.lq.sys.service.TRoutingService;
import com.object.module.lq.sys.service.TUserService;
import com.object.utils.ExcelUtile;
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
        String str = "4";
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




  @Autowired
  private TUserService userService;

    @Test
     public  void  rounter(){
        ExcelUtile<ImportTUserEntity> user=new ExcelUtile<>();
        String fileName = "D:\\LiQingCode\\code\\导入测试数据.xlsx";
        user.imports(fileName,userService,ImportTUserEntity.class);
    }

    @Test
    public void wirder(){
        String fileName = System.getProperty("user.dir")+ "write" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去读，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, TUserEntity.class).sheet("模板").doWrite(userService.list());
    }



    @Test
    public  void dada(){

    }

}
