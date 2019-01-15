/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : 127.0.0.1
 Source Database       : product

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 01/16/2019 01:48:14 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `app_message`
-- ----------------------------
DROP TABLE IF EXISTS `app_message`;
CREATE TABLE `app_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(100) DEFAULT NULL,
  `copyright` varchar(100) DEFAULT NULL,
  `remark` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  `createTime` bigint(11) DEFAULT NULL,
  `updateTime` bigint(11) DEFAULT NULL,
  `version` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `ip_message`
-- ----------------------------
DROP TABLE IF EXISTS `ip_message`;
CREATE TABLE `ip_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL COMMENT '领用次数',
  `remark` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `createTime` bigint(20) DEFAULT NULL,
  `updateTime` bigint(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL COMMENT '0 不可用，1可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `ip_message`
-- ----------------------------
BEGIN;
INSERT INTO `ip_message` VALUES ('1', '192.168.1.117', '0', '这是啥', '1547568978', '1547572304', b'0'), ('2', '123.213.43.55', '0', 'dgnrtj', '1547568978', '1547568978', b'0'), ('3', '192.168.1.123', '0', '1213', '1547570132', '1547570132', b'1'), ('4', '192.31.34.12', '0', '撒手', '1547570293', '1547570293', b'1'), ('5', '192.31.22.14', '0', '动动我', '1547570335', '1547570335', b'1'), ('6', '142.55.65.81', '0', '阿威锋网', '1547570358', '1547570358', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `manage_resource`
-- ----------------------------
DROP TABLE IF EXISTS `manage_resource`;
CREATE TABLE `manage_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resName` varchar(100) DEFAULT NULL,
  `resKey` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `resType` varchar(255) DEFAULT NULL,
  `resUrl` varchar(255) DEFAULT NULL,
  `useable` bit(1) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `createTime` bigint(11) DEFAULT NULL,
  `updateTime` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `manage_role`
-- ----------------------------
DROP TABLE IF EXISTS `manage_role`;
CREATE TABLE `manage_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) DEFAULT NULL,
  `roleKey` varchar(100) DEFAULT NULL,
  `roleStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `manage_role_relation`
-- ----------------------------
DROP TABLE IF EXISTS `manage_role_relation`;
CREATE TABLE `manage_role_relation` (
  `sysUserId` int(11) NOT NULL,
  `sysRoled` int(11) NOT NULL,
  PRIMARY KEY (`sysUserId`,`sysRoled`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `manage_user`
-- ----------------------------
DROP TABLE IF EXISTS `manage_user`;
CREATE TABLE `manage_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  `realName` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `locked` bit(1) DEFAULT NULL,
  `createTime` bigint(11) DEFAULT NULL,
  `updateTime` bigint(11) DEFAULT NULL,
  `mobile` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imageCount` int(11) DEFAULT NULL,
  `ssid` varchar(100) DEFAULT NULL COMMENT '唯一标示',
  `userId` int(11) DEFAULT NULL,
  `createTime` bigint(11) DEFAULT NULL,
  `updateTime` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `resource_role_relation`
-- ----------------------------
DROP TABLE IF EXISTS `resource_role_relation`;
CREATE TABLE `resource_role_relation` (
  `sysResourceId` int(11) NOT NULL,
  `sysRoleId` int(11) NOT NULL,
  PRIMARY KEY (`sysResourceId`,`sysRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dicName` varchar(255) DEFAULT NULL,
  `dicKey` varchar(255) DEFAULT NULL,
  `dicValue` varchar(255) DEFAULT NULL,
  `dicExplain` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `useable` bit(1) DEFAULT NULL,
  `createTime` int(11) DEFAULT NULL,
  `updateTime` int(11) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '用户名，默认手机号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `createTime` bigint(11) DEFAULT NULL COMMENT '创建时间',
  `updateTime` bigint(11) DEFAULT NULL COMMENT '更新时间',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机号，登录账号',
  `countryCode` varchar(20) DEFAULT NULL COMMENT '国家代码',
  `deviceId` varchar(100) DEFAULT NULL COMMENT '设备 id',
  `from` varchar(50) DEFAULT NULL COMMENT '终端',
  `status` varchar(255) DEFAULT NULL COMMENT '0可用，1不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Table structure for `version_record`
-- ----------------------------
DROP TABLE IF EXISTS `version_record`;
CREATE TABLE `version_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `version` varchar(50) DEFAULT NULL COMMENT '版本',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `url` varchar(255) DEFAULT NULL COMMENT '下载路径',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `type` bit(1) DEFAULT NULL COMMENT '终端类型：android 1,ios 2',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
