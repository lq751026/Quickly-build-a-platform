-- ----------------------------
-- Determine if there is a database
-- ----------------------------
DROP
DATABASE IF EXISTS `object`;
CREATE
DATABASE object;
USE
object;
-- ----------------------------
-- Table structure for t_add_record
-- ----------------------------
DROP TABLE IF EXISTS `t_add_record`;
CREATE TABLE `t_add_record`
(
    `r_u_in`       int(0) NOT NULL COMMENT '发起id',
    `r_u_out`      int(0) NULL DEFAULT NULL COMMENT '接受id',
    `r_u_time_in`  datetime NULL DEFAULT NULL COMMENT '发起时间',
    `r_u_time_out` datetime NULL DEFAULT NULL COMMENT '接受时间',
    `r_u_remarks`  varchar(255) NULL DEFAULT NULL COMMENT '添加时备注'
) COMMENT = '好友添加记录表';

-- ----------------------------
-- Records of t_add_record
-- ----------------------------
INSERT INTO `t_add_record`
VALUES (1, 5, '2021-09-12 15:26:18', '2021-09-12 16:39:56', '我是admin');

-- ----------------------------
-- Table structure for t_good_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_good_friend`;
CREATE TABLE `t_good_friend`
(
    `t_own`         int(0) NOT NULL COMMENT '自己的id',
    `t_good_friend` int(0) NULL DEFAULT NULL COMMENT '好友id',
    `t_grouping`    int(0) NULL DEFAULT NULL COMMENT '分组id',
    `t_name`        varchar(255) NULL DEFAULT NULL COMMENT '备注的昵称'
) COMMENT = '好友表';

-- ----------------------------
-- Records of t_good_friend
-- ----------------------------
INSERT INTO `t_good_friend`
VALUES (5, 1, NULL, 'admin');
INSERT INTO `t_good_friend`
VALUES (1, 5, NULL, 'admin');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`
(
    `class_name`  varchar(188) NULL DEFAULT NULL COMMENT '类名',
    `method_name` varchar(188) NULL DEFAULT NULL COMMENT '方法名',
    `params`      varchar(388) NULL DEFAULT NULL COMMENT '方法参数',
    `exeu_time`   varchar(188) NULL DEFAULT NULL COMMENT '执行时长',
    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `user_name`   char(20) NULL DEFAULT NULL COMMENT '用户名',
    `ip`          varchar(15) NULL DEFAULT NULL COMMENT 'IP地址',
    `url`         varchar(50) NULL DEFAULT NULL COMMENT '请求路径'
) COMMENT = '日志表';

-- ----------------------------
--  t_log insert data
-- ----------------------------
INSERT INTO `t_log`
VALUES ('com.object.module.sys.LogController.TIndexController', 'login',
        'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=4, role=null)org.apache.catalina.connector.RequestFacade@5e01f7a5',
        '0.0秒', '2021-09-05 15:37:27', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log`
VALUES ('com.object.module.sys.LogController.TIndexController', 'login',
        'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=48, role=null)org.apache.catalina.connector.RequestFacade@4490b2f9',
        '0.0秒', '2021-09-05 15:47:10', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log`
VALUES ('com.object.module.sys.LogController.TIndexController', 'login',
        'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=5, role=null)org.apache.catalina.connector.RequestFacade@3650b3b0',
        '0.0秒', '2021-09-05 15:55:50', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log`
VALUES ('com.object.module.sys.LogController.TIndexController', 'login',
        'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=12, role=null)org.apache.catalina.connector.RequestFacade@74da5258',
        '0.0秒', '2021-09-05 16:11:55', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log`
VALUES ('com.object.module.sys.LogController.TIndexController', 'login',
        'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=40, role=null)org.apache.catalina.connector.RequestFacade@2b4c94ac',
        '0.0秒', '2021-09-05 16:19:59', NULL, '127.0.0.1', '/object/index/login');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `re_id`     int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `re_name`   varchar(255) NULL DEFAULT NULL COMMENT '角色名',
    `authority` varchar(188) NULL DEFAULT NULL COMMENT '授权识别',
    `purview`   varchar(188) NULL DEFAULT NULL COMMENT '权限控制(增,删，修改)',
    PRIMARY KEY (`re_id`) USING BTREE
) COMMENT = '角色表';

-- ----------------------------
-- t_role insert data
-- ----------------------------
INSERT INTO `t_role`
VALUES (1, '管理员', 'all', 'all:all');
INSERT INTO `t_role`
VALUES (2, '开发者', 'all', 'all:all');
INSERT INTO `t_role`
VALUES (3, '测试', '3,4,5,6,8,29', '4:delete_5:delete_29:add_29:update');

-- ----------------------------
-- Table structure for t_routing
-- ----------------------------
DROP TABLE IF EXISTS `t_routing`;
CREATE TABLE `t_routing`
(
    `id`          int(0) NOT NULL AUTO_INCREMENT,
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `path`        varchar(100) NOT NULL COMMENT '路由',
    `parent_id`   int(0) NULL DEFAULT 0 COMMENT '上级id',
    `menu_name`   varchar(100) NULL DEFAULT NULL COMMENT '菜单名',
    `icon`        char(50) NULL DEFAULT NULL COMMENT '图标',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT = '路由表';

-- ----------------------------
--  t_routing insert data
-- ----------------------------
INSERT INTO `t_routing`
VALUES (3, '2021-07-27 14:33:00', 'sys', 0, 'menu.sys', 'icon-wifi');
INSERT INTO `t_routing`
VALUES (4, '2021-07-27 14:35:00', 'sysUser', 3, 'menu.sys.sysUser', 'icon-user');
INSERT INTO `t_routing`
VALUES (60, '2021-07-27 14:35:00', 'LuYou', 3, 'menu.sys.sysRouter', 'icon-list');
INSERT INTO `t_routing`
VALUES (63, '2022-06-09 12:05:00', 'sysRole', 3, 'menu.sys.sysRole', 'icon-user-group');
INSERT INTO `t_routing`
VALUES (64, '2022-06-11 15:38:00', 'sysApi', 3, 'menu.sys.swaggerName', 'icon-apps');
INSERT INTO `t_routing`
VALUES (65, '2022-06-11 16:20:26', 'onlinelisting', 3, 'menu.sys.onlinelisting', 'icon-empty');
INSERT INTO `t_routing`
VALUES (66, '2022-06-11 17:19:22', 'log', 3, 'menu.sys.log', 'icon-drag-dot-vertical');
INSERT INTO `t_routing`
VALUES (67, '2022-06-11 17:26:52', 'datasource', 3, 'menu.sys.datasource', 'icon-thunderbolt');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user`
(
    `ur_id`       int(0) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
    `ur_name`     varchar(255) NULL DEFAULT NULL COMMENT '昵称',
    `ur_username` varchar(255) NULL DEFAULT NULL COMMENT '用户名',
    `ur_password` varchar(255) NULL DEFAULT '$2a$10$MeBEconcQ8zcgyUOhc2ouOERiW8ZpGK8T5mtVRIrAetaLqDrFaYi6' COMMENT '密码',
    `ur_email`    varchar(255) NULL DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime NULL DEFAULT NULL COMMENT '账号的注册时间',
    `ur_avatar`   varchar(255) NULL DEFAULT 'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg' COMMENT '头像',
    `ur_stuats`   int(20) NULL DEFAULT '1' COMMENT '状态(0.冻结，1.正常 )',
    `ur_re_id`    int(0) NULL DEFAULT 3 COMMENT '角色id',
    `ur_sex`      int(0) NULL DEFAULT 1 COMMENT '性别',
    PRIMARY KEY (`ur_id`) USING BTREE
) COMMENT = '用户表';

-- ----------------------------
-- t_user insert data
-- ----------------------------
INSERT INTO `t_user`
VALUES (1, 'admin', 'admin', '$2a$10$QHf3DeD6RzXdL3JAGB4Hxu5kXvpXN40fLFCNGIw.PZD79vpOQKw6O', 'admin@qq.com',
        '2021-07-14 17:10:00',
        'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg', '1',1,
        0);
INSERT INTO `t_user`
VALUES (5, '123', '123', '$2a$10$.tBXFDlafF2v7yQ3P1MDEOApVrdwQO3NFQcJDRYIpCZ0TFNNXW/FC', '1578423@qq.com',
        '2021-09-05 11:34:00',
        'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-11-20/a6f4abd0-44d7-40c4-8341-fb3e240ded8d_397fd9b511dc405b9279b37b9c9ada64!400x400.jpeg',
        '1', 2, 0);
INSERT INTO `t_user`
VALUES (18, '321', '321', '$2a$10$MeBEconcQ8zcgyUOhc2ouOERiW8ZpGK8T5mtVRIrAetaLqDrFaYi6', '1578423@qq.com',
        '2021-11-08 16:55:47',
        'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg', '1', 3,
        1);



-- ----------------------------
-- Table structure for t_online_listing
-- ----------------------------
DROP TABLE IF EXISTS `t_online_listing`;
CREATE TABLE `t_online_listing`
(
    `og_id`          int(0) NOT NULL AUTO_INCREMENT,
    `og_online_time` datetime     NOT NULL COMMENT '登录时间',
    `og_user_name`   varchar(100) NOT NULL COMMENT '登录用户名',
    `og_ur_id`       int(0) NULL DEFAULT 0 COMMENT '用户id',
    `og_equipment`   varchar(200) NULL DEFAULT NULL COMMENT '登录设备',
    `og_ip`          varchar(200) NULL DEFAULT NULL COMMENT '登录ip',
    PRIMARY KEY (`og_id`) USING BTREE
) COMMENT = '用户在线列表';


-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`
(
    `rp_id` int(0) NOT NULL AUTO_INCREMENT,
    `re_id` int(200) NOT NULL COMMENT '角色id',
    `rt_id` int(200) NOT NULL COMMENT '路由id',
    PRIMARY KEY (`rp_id`) USING BTREE
) COMMENT = '权限表';

-- ----------------------------
-- t_role_permission insert data
-- ----------------------------
INSERT INTO `t_role_permission`
VALUES (1, 1, 3);
INSERT INTO `t_role_permission`
VALUES (2, 1, 4);
INSERT INTO `t_role_permission`
VALUES (3, 1, 60);
INSERT INTO `t_role_permission`
VALUES (4, 1, 63);
INSERT INTO `t_role_permission`
VALUES (5, 1, 64);
INSERT INTO `t_role_permission`
VALUES (6, 1, 65);
INSERT INTO `t_role_permission`
VALUES (7, 1, 66);
INSERT INTO `t_role_permission`
VALUES (8, 1, 67);
INSERT INTO `t_role_permission`
VALUES (9, 1, 68);
