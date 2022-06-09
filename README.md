  <h1 align="center">快速搭建平台</h1>
<p align="center">
<img src="https://img.shields.io/badge/jdk-1.8-blue.svg" /> 
<img src="https://img.shields.io/badge/springboot-2.2.2-red.svg" />
<img src="https://img.shields.io/badge/mysql-8.0-green.svg" />
<a src="https://blog.csdn.net/qq_37437493?spm=1003.2020.3001.5343"><img src="https://img.shields.io/badge/小庆-(〝▼皿▼)-green.svg" />💟</a>
</p>
  
 ## 架构体系
 ![输入图片说明](img/%E7%BD%91%E7%BB%9C%E6%8B%93%E6%89%91%E5%9B%BE%E6%A0%B7%E4%BE%8B.png)

 ## 模块说明
 ### lq-admin (核心模块)
    aop:日志
    config：配置
    exception：异常配置
    module：业务模块
    timer:定时器配置
    utils：工具类
 ### lq-common (公共模块)
     共用类或者是返回值


 ### lq-constructor(代码生成器) 人人代码代码生成器 二开
     需要自定义代码生成器只需要在GenUtils的getFileName方法里面添加文件名
     写法按照其他模板的vm语法

 ### lq-mapper (dao层)
     主要放置我们的sql语句编写
 ### lq-sql (数据库)

### lq-vue (前端)
   采用字节跳动的arco.design框架进行编写目前正在熬夜编写中



