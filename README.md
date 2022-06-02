  <h1 align="center">自定义快速搭建平台</h1>
<p align="center">
<img src="https://img.shields.io/badge/jdk-1.8-blue.svg" /> 
<img src="https://img.shields.io/badge/springboot-2.2.2-red.svg" />
<img src="https://img.shields.io/badge/mysql-8.0-green.svg" />
<a src="https://blog.csdn.net/qq_37437493?spm=1003.2020.3001.5343"><img src="https://img.shields.io/badge/小庆-(〝▼皿▼)-green.svg" />💟</a>
</p>
          架构体系 ：通过代码生产器生成增删改查和vue页面  通过后台传入数据渲染动态路由 可以方便写页面访问权限  和页面的操作权限  从而到达动态控制权限   可以大大提高我们的开发效率 ！ 觉得不错就来一个star

### 后台框架：


1. 代码生产器：人人开源 二次开源修改 主要修改了 生成的vue的调用接口和新增了一个api调用vue页面生成  
1. 阿里云oss
1. mybatis-plus的集成
1. 验证码：captcha
1. swagger2：swagger2
1. 密码加密 spring-security
1. 配置文件加密：jasypt
1. 聊天通讯:neety +websocket 实现聊天 +redis存储我们的聊天记录
1. Druid数据源
1. 认证as-token框架 官方文档 http://sa-token.dev33.cn/

[使用教程视频第一集](https://www.bilibili.com/video/BV1pQ4y1h7Jj/)

[使用教程视频第二集](https://www.bilibili.com/video/BV1KL4y1Y7b1/)

页面截图 ：

![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175121_6f1a33fe_8019393.png "屏幕截图.png")     
![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175158_c72c43ef_8019393.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175209_a5e8f7c5_8019393.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175223_7140662e_8019393.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175231_4b483ed4_8019393.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/175253_bf27f131_8019393.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2021/0801/175454_52dfae09_8019393.png "屏幕截图.png")
![输入图片说明](image.png)

![输入图片说明](https://images.gitee.com/uploads/images/2021/0921/154644_582f5245_8019393.png "屏幕截图.png")![输入图片说明](https://images.gitee.com/uploads/images/2021/0921/154644_110f0da1_8019393.png "屏幕截图.png")![输入图片说明](https://images.gitee.com/uploads/images/2021/0921/154644_70ca8d8c_8019393.png "屏幕截图.png")![输入图片说明](https://images.gitee.com/uploads/images/2021/0921/154644_59b989c3_8019393.png "屏幕截图.png")

### 前端
[前端页面源码](https://gitee.com/CodeLiQing/rapid-serivce-vue)
：vue和elementUi 集成 vue-element-admin 框架

皮肤包的Swagger-ui
访问接口:
 http://localhost:8889/object/doc.html
![输入图片说明](.mvn/image.png)

![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/173244_55bd3ac9_8019393.png "屏幕截图.png")
-     api：访问调用地址
-     src的config的role：定义的是一个全局的权限配置
-     router：路由采用的动态路由 路由地址从数据库渲染出
-     utils：工具
-   其他可以参考 [vue-element-admin官网](https://panjiachen.github.io/vue-element-admin-site/#/)


![输入图片说明](https://images.gitee.com/uploads/images/2021/0730/173332_d6d41d7b_8019393.png "屏幕截图.png")   
aop：事务的日志记录
config：配置
utils：工具
xss：过滤防止slq注入
         
         感谢开源项目！
| 版本  | 前端    | 后端 |
|-----|-------|----|
| 1.1 | 更新富文本 | 无  |
| 1.2 | 加入图表 |  satken换成配置  |
| 1.3 | 表格导出 |  数据的表格导出  |
| 2.0 | 加入聊天界面 |  实现聊天通讯  |
| 2.1 | 加入大数据展示  |  无  |
  