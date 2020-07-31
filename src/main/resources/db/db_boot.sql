/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50645
Source Host           : localhost:3306
Source Database       : db_boot

Target Server Type    : MYSQL
Target Server Version : 50645
File Encoding         : 65001

Date: 2020-07-21 20:09:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(500) DEFAULT NULL COMMENT '日志主键',
  `user_id` bigint(20) unsigned DEFAULT NULL COMMENT '已登录用户ID',
  `type` enum('SYSTEM','VISIT','ERROR') NOT NULL DEFAULT 'SYSTEM' COMMENT '日志类型（系统操作日志，用户访问日志，异常记录日志）',
  `log_level` enum('ERROR','WARN','INFO') NOT NULL DEFAULT 'INFO' COMMENT '日志级别',
  `exception` varchar(3000) DEFAULT NULL COMMENT '请求异常',
  `content` varchar(2000) DEFAULT NULL COMMENT '日志内容',
  `request_params` varchar(2000) DEFAULT NULL COMMENT '请求参数',
  `cost_time` bigint(20) unsigned DEFAULT NULL COMMENT '请求时间',
  `ip` varchar(64) DEFAULT NULL COMMENT '用户ip',
  `ua` varchar(500) DEFAULT NULL COMMENT '用户user_agent',
  `os` varchar(64) DEFAULT NULL COMMENT '系统类型',
  `browser` varchar(64) DEFAULT NULL COMMENT '浏览器类型',
  `request_type` varchar(64) DEFAULT NULL COMMENT '请求类型',
  `request_url` varchar(3000) DEFAULT NULL COMMENT '请求的路径',
  `referer` varchar(3000) DEFAULT NULL COMMENT '请求来源地址',
  `del_flag` tinyint(1) unsigned DEFAULT '0' COMMENT '删除级别',
  `create_by` varchar(200) DEFAULT NULL COMMENT '日志创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_by` varchar(200) DEFAULT NULL COMMENT '日志修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('0ef52e22b6014f989ed7ada37e5704c2', null, 'SYSTEM', 'INFO', null, '访客: [127.0.0.1] | 操作: 查询用户列表', '{}', '342', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 'Windows 7', 'Chrome', 'GET', 'http://localhost:8080/user/list', null, '0', 'SYSTEM', '2020-07-21 17:25:38', 'SYSTEM', '2020-07-21 17:25:38');
INSERT INTO `sys_log` VALUES ('595d327be2804d31a08dfe046de8dd59', null, 'ERROR', 'ERROR', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.zwd.boot.mapper.SysLogMapper.findUserListByCondition', '访客: [127.0.0.1] | 操作: 获取日志列表', '{}', '29', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 'Windows 7', 'Chrome', 'GET', 'http://localhost:8080/admin/log', null, '0', 'SYSTEM', '2020-07-21 17:44:33', 'SYSTEM', '2020-07-21 17:44:33');
INSERT INTO `sys_log` VALUES ('a81fb05a4d6a40669f920719b110241b', null, 'ERROR', 'ERROR', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.zwd.boot.mapper.SysLogMapper.findUserListByCondition', '访客: [127.0.0.1] | 操作: 获取日志列表', '{}', '1', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 'Windows 7', 'Chrome', 'GET', 'http://localhost:8080/admin/log', null, '0', 'SYSTEM', '2020-07-21 17:45:24', 'SYSTEM', '2020-07-21 17:45:24');
INSERT INTO `sys_log` VALUES ('c84c2755861a470c9ab4e05f531d938d', null, 'SYSTEM', 'INFO', null, '访客: [127.0.0.1] | 操作: 获取日志列表', '{}', '258', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 'Windows 7', 'Chrome', 'GET', 'http://localhost:8080/admin/log', null, '0', 'SYSTEM', '2020-07-21 17:46:06', 'SYSTEM', '2020-07-21 17:46:06');
INSERT INTO `sys_log` VALUES ('4d57b9832c564ca989f36a1b163fd8f7', null, 'SYSTEM', 'INFO', null, '访客: [127.0.0.1] | 操作: 获取日志列表', '{}', '33', '127.0.0.1', 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.88 Safari/537.36', 'Windows 7', 'Chrome', 'GET', 'http://localhost:8080/admin/log', null, '0', 'SYSTEM', '2020-07-21 17:47:10', 'SYSTEM', '2020-07-21 17:47:10');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `nickname` varchar(30) DEFAULT '' COMMENT '昵称',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` smallint(2) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `user_type` enum('ROOT','ADMIN','USER') DEFAULT 'ADMIN' COMMENT '超级管理员、管理员、普通用户',
  `blog` varchar(255) DEFAULT NULL COMMENT '个人博客地址',
  `location` varchar(255) DEFAULT NULL COMMENT '地址',
  `privacy` tinyint(2) DEFAULT NULL COMMENT '隐私（1：公开，0：不公开）',
  `reg_ip` varchar(30) DEFAULT NULL COMMENT '注册IP',
  `last_login_ip` varchar(30) DEFAULT NULL COMMENT '最近登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近登录时间',
  `login_count` int(10) unsigned DEFAULT '0' COMMENT '登录次数',
  `remark` varchar(100) DEFAULT NULL COMMENT '用户备注',
  `del_flag` tinyint(1) unsigned DEFAULT '0' COMMENT '用户状态',
  `create_by` varchar(200) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_by` varchar(200) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'root', 'CGUx1FN++xS+4wNDFeN6DA==', '超级管理员', '15151551516', '843977358@qq.com', '843977358', '2020-07-21', null, '', 'ROOT', '', '', null, '127.0.0.1', '127.0.0.1', '2020-07-21 15:29:53', '2', '', '0', '', '2020-07-21 15:30:34', '', '2020-07-21 15:30:26');
