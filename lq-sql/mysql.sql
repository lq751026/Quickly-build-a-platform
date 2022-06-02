-- ----------------------------
-- Determine if there is a database
-- ----------------------------
DROP DATABASE IF EXISTS `object`;
CREATE DATABASE object;
USE object;
-- ----------------------------
-- Table structure for t_add_record
-- ----------------------------
DROP TABLE IF EXISTS `t_add_record`;
CREATE TABLE `t_add_record`  (
                                 `r_u_in` int(0) NOT NULL COMMENT '发起id',
                                 `r_u_out` int(0) NULL DEFAULT NULL COMMENT '接受id',
                                 `r_u_time_in` datetime NULL DEFAULT NULL COMMENT '发起时间',
                                 `r_u_time_out` datetime NULL DEFAULT NULL COMMENT '接受时间',
                                 `r_u_remarks` varchar(255)  NULL DEFAULT NULL COMMENT '添加时备注'
)  COMMENT = '好友添加记录表';

-- ----------------------------
-- Records of t_add_record
-- ----------------------------
INSERT INTO `t_add_record` VALUES (1, 5, '2021-09-12 15:26:18', '2021-09-12 16:39:56', '我是admin');

-- ----------------------------
-- Table structure for t_good_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_good_friend`;
CREATE TABLE `t_good_friend`  (
                                  `t_own` int(0) NOT NULL COMMENT '自己的id',
                                  `t_good_friend` int(0) NULL DEFAULT NULL COMMENT '好友id',
                                  `t_grouping` int(0) NULL DEFAULT NULL COMMENT '分组id',
                                  `t_name` varchar(255)  NULL DEFAULT NULL COMMENT '备注的昵称'
)  COMMENT = '好友表' ;

-- ----------------------------
-- Records of t_good_friend
-- ----------------------------
INSERT INTO `t_good_friend` VALUES (5, 1, NULL, 'admin');
INSERT INTO `t_good_friend` VALUES (1, 5, NULL, 'admin');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
                          `class_name` varchar(188)  NULL DEFAULT NULL COMMENT '类名',
                          `method_name` varchar(188)  NULL DEFAULT NULL COMMENT '方法名',
                          `params` varchar(388)  NULL DEFAULT NULL COMMENT '方法参数',
                          `exeu_time` varchar(188)  NULL DEFAULT NULL COMMENT '执行时长',
                          `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                          `user_name` char(20)  NULL DEFAULT NULL COMMENT '用户名',
                          `ip` varchar(15)  NULL DEFAULT NULL COMMENT 'IP地址',
                          `url` varchar(50)  NULL DEFAULT NULL COMMENT '请求路径'
)  COMMENT = '日志表';

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('com.object.module.sys.LogController.TIndexController', 'login', 'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=4, role=null)org.apache.catalina.connector.RequestFacade@5e01f7a5', '0.0秒', '2021-09-05 15:37:27', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log` VALUES ('com.object.module.sys.LogController.TIndexController', 'login', 'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=48, role=null)org.apache.catalina.connector.RequestFacade@4490b2f9', '0.0秒', '2021-09-05 15:47:10', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log` VALUES ('com.object.module.sys.LogController.TIndexController', 'login', 'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=5, role=null)org.apache.catalina.connector.RequestFacade@3650b3b0', '0.0秒', '2021-09-05 15:55:50', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log` VALUES ('com.object.module.sys.LogController.TIndexController', 'login', 'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=12, role=null)org.apache.catalina.connector.RequestFacade@74da5258', '0.0秒', '2021-09-05 16:11:55', NULL, '127.0.0.1', '/object/index/login');
INSERT INTO `t_log` VALUES ('com.object.module.sys.LogController.TIndexController', 'login', 'TUserEntity(urId=null, urName=null, urUsername=admin, urPassword=admin, urEmail=null, urCreateTime=null, urAvatar=null, urStuats=null, urReId=null, urSex=null, captcha=40, role=null)org.apache.catalina.connector.RequestFacade@2b4c94ac', '0.0秒', '2021-09-05 16:19:59', NULL, '127.0.0.1', '/object/index/login');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
                           `re_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `re_name` varchar(255)  NULL DEFAULT NULL COMMENT '角色名',
                           `authority` varchar(188)  NULL DEFAULT NULL COMMENT '授权识别',
                           `purview` varchar(188)  NULL DEFAULT NULL COMMENT '权限控制(增,删，修改)',
                           PRIMARY KEY (`re_id`) USING BTREE
) COMMENT = '角色表' ;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '管理员', 'all', 'all:all');
INSERT INTO `t_role` VALUES (2, '开发者', 'all', 'all:all');
INSERT INTO `t_role` VALUES (3, '测试', '3,4,5,6,8,29', '4:delete_5:delete_29:add_29:update');

-- ----------------------------
-- Table structure for t_routing
-- ----------------------------
DROP TABLE IF EXISTS `t_routing`;
CREATE TABLE `t_routing`  (
                              `id` int(0) NOT NULL AUTO_INCREMENT,
                              `create_time` datetime NOT NULL COMMENT '创建时间',
                              `path` varchar(100)  NOT NULL COMMENT '路由',
                              `hidden` tinyint(1) NULL DEFAULT 1 COMMENT '路由的可见 true:不可见  默认是：false可见',
                              `parent_id` int(0) NULL DEFAULT 0 COMMENT '上级id',
                              `menu_name` varchar(20)  NULL DEFAULT NULL COMMENT '菜单名',
                              `icon` char(50)  NULL DEFAULT NULL COMMENT '图标',
                              `perms` varchar(30)  NULL DEFAULT NULL COMMENT '授权识别',
                              `component_url` varchar(50)  NULL DEFAULT NULL COMMENT '绝对路由的位置',
                              `redirect` varchar(50)  NULL DEFAULT NULL COMMENT '重定向',
                              PRIMARY KEY (`id`) USING BTREE
) COMMENT = '路由表' ;

-- ----------------------------
-- Records of t_routing
-- ----------------------------
INSERT INTO `t_routing` VALUES (1, '2021-07-28 14:44:00', '/', 1, 0, '首页', 'el-icon-s-tools', NULL, '', '/sye');
INSERT INTO `t_routing` VALUES (3, '2021-07-27 14:33:00', '/admin', 1, 0, '系统管理', 'el-icon-menu', NULL, NULL, '/admin/user');
INSERT INTO `t_routing` VALUES (4, '2021-07-27 14:35:00', 'user', 1, 3, '管理员', 'el-icon-user-solid', NULL, 'object/index', NULL);
INSERT INTO `t_routing` VALUES (5, '2021-07-27 14:36:00', 'role', 1, 3, '角色', 'el-icon-dessert', NULL, 'object/role', NULL);
INSERT INTO `t_routing` VALUES (6, '2021-07-27 14:36:00', 'log', 1, 3, '日志', 'el-icon-s-order', NULL, 'log/log', NULL);
INSERT INTO `t_routing` VALUES (8, '2021-07-27 14:52:00', 'index', 1, 3, '路由菜单', 'el-icon-tickets', NULL, 'routing/routing', NULL);
INSERT INTO `t_routing` VALUES (13, '2021-07-27 15:56:49', 'home', 0, 50, '个人资料', 'table', '', 'my/home', '');
INSERT INTO `t_routing` VALUES (24, '2021-07-28 14:44:00', '/sye', 0, 1, '首页', 'el-icon-setting', '', 'dashboard/index', '');
INSERT INTO `t_routing` VALUES (28, '2021-07-29 13:48:00', '/admin/sql', 1, 3, 'sql日志', 'el-icon-folder', '', 'log/logsql', '');
INSERT INTO `t_routing` VALUES (50, '2021-07-26 13:34:00', '/home', 0, 0, '我的', 'table', NULL, NULL, '/home/hoome');
INSERT INTO `t_routing` VALUES (51, '2021-08-01 17:22:39', '/edit', 1, 3, '富文本编辑器', 'el-icon-ice-drink', '', 'edit/edit', '');
INSERT INTO `t_routing` VALUES (53, '2021-08-25 16:15:53', '/test/test', 1, 52, '测试页面', 'el-icon-s-order', '', 'test/test', '');
INSERT INTO `t_routing` VALUES (54, '2021-08-25 16:39:38', '/test/role', 1, 52, '角色', 'el-icon-user-solid', NULL, 'test/role', NULL);
INSERT INTO `t_routing` VALUES (56, '2021-08-26 14:22:31', '/test/yh', 1, 55, '用户', 'el-icon-user-solid', '', 'other/tuser', '');
INSERT INTO `t_routing` VALUES (58, '2021-08-28 23:47:07', '/test/index', 1, 57, '测试页面', 'el-icon-setting', '', 'test/index', '');
INSERT INTO `t_routing` VALUES (59, '2022-05-13 15:36:01', '/api', 1, 3, '后端接口', 'el-icon-star-off', '', 'api/api', '');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user`  (
                           `ur_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
                           `ur_name` varchar(255)  NULL DEFAULT NULL COMMENT '昵称',
                           `ur_username` varchar(255)  NULL DEFAULT NULL COMMENT '用户名',
                           `ur_password` varchar(255)  NULL DEFAULT '$2a$10$MeBEconcQ8zcgyUOhc2ouOERiW8ZpGK8T5mtVRIrAetaLqDrFaYi6' COMMENT '密码',
                           `ur_email` varchar(255)  NULL DEFAULT NULL COMMENT '邮箱',
                           `create_time` datetime NULL DEFAULT NULL COMMENT '账号的注册时间',
                           `ur_avatar` varchar(255)  NULL DEFAULT 'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg' COMMENT '头像',
                           `ur_stuats` varchar(255)  NULL DEFAULT '1' COMMENT '状态(0.冻结，1.正常 )',
                           `ur_re_id` int(0) NULL DEFAULT 3 COMMENT '角色id',
                           `ur_sex` int(0) NULL DEFAULT 1 COMMENT '性别',
                           PRIMARY KEY (`ur_id`) USING BTREE
) COMMENT = '用户表' ;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', '$2a$10$QHf3DeD6RzXdL3JAGB4Hxu5kXvpXN40fLFCNGIw.PZD79vpOQKw6O', 'admin@qq.com', '2021-07-14 17:10:00', 'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg', '1', 2, 0);
INSERT INTO `t_user` VALUES (5, '123', '123', '$2a$10$.tBXFDlafF2v7yQ3P1MDEOApVrdwQO3NFQcJDRYIpCZ0TFNNXW/FC', '1578423@qq.com', '2021-09-05 11:34:00', 'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-11-20/a6f4abd0-44d7-40c4-8341-fb3e240ded8d_397fd9b511dc405b9279b37b9c9ada64!400x400.jpeg', '1', 2, 0);
INSERT INTO `t_user` VALUES (18, '321', '321', '$2a$10$MeBEconcQ8zcgyUOhc2ouOERiW8ZpGK8T5mtVRIrAetaLqDrFaYi6', '1578423@qq.com', '2021-11-08 16:55:47', 'https://help-xiao.oss-cn-beijing.aliyuncs.com/2021-07-24/533123c9-2a71-4d82-8101-a82c56b472e2_2.jpg', '1', 3, 1);


