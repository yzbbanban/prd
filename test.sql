/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-01-14 16:01:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(11) NOT NULL,
  `group_name` varchar(2000) DEFAULT NULL,
  `createTime` bigint(11) DEFAULT NULL,
  `amount` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', 'admin', null, null);
INSERT INTO `group` VALUES ('2', 'teacher', null, null);
INSERT INTO `group` VALUES ('3', '11', '20181121101804', null);
INSERT INTO `group` VALUES ('4', '11', '20181121102116', null);
INSERT INTO `group` VALUES ('5', '11', '1542767062000', null);
INSERT INTO `group` VALUES ('6', '11', '1497240005000', '1');
INSERT INTO `group` VALUES ('7', '11', '1542767349364', '1234567891');
INSERT INTO `group` VALUES ('8', '11', '1542767386743', '1234567891');
INSERT INTO `group` VALUES ('9', null, '1542767386743', null);

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_userId_groupId` (`user_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_user
-- ----------------------------
INSERT INTO `group_user` VALUES ('1', '1', '1');
INSERT INTO `group_user` VALUES ('2', '2', '2');
INSERT INTO `group_user` VALUES ('3', '6', '2');

-- ----------------------------
-- Table structure for tb_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_area`;
CREATE TABLE `tb_area` (
  `area_id` int(11) NOT NULL AUTO_INCREMENT,
  `area_name` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `last_edit_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `version` bigint(11) DEFAULT NULL,
  `testSelect` bigint(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_area
-- ----------------------------
INSERT INTO `tb_area` VALUES ('2', '南京', '8', '2018-12-22 13:47:13', '2018-12-22 13:47:13', '0', '0', null);
INSERT INTO `tb_area` VALUES ('3', '南京', '8', '2018-12-22 13:47:14', '2018-12-22 13:47:14', '0', '0', null);
INSERT INTO `tb_area` VALUES ('4', '徐州', '8', '2018-12-22 13:47:15', '2018-12-22 13:47:15', '0', '0', null);
INSERT INTO `tb_area` VALUES ('5', '徐州', '8', '2018-12-22 13:47:17', '2018-12-22 13:47:17', '0', '0', null);
INSERT INTO `tb_area` VALUES ('6', '徐州', '8', '2018-12-22 13:47:18', '2018-12-22 13:47:18', '0', '0', null);
INSERT INTO `tb_area` VALUES ('7', '徐州', '8', '2018-12-22 13:47:19', '2018-12-22 13:47:19', '0', '0', null);
INSERT INTO `tb_area` VALUES ('8', '徐州', '8', '2018-12-22 13:47:20', '2018-12-22 13:47:20', '0', '0', null);
INSERT INTO `tb_area` VALUES ('9', '徐州', '8', '2018-12-22 13:47:21', '2018-12-22 13:47:21', '0', '0', null);
INSERT INTO `tb_area` VALUES ('10', 'test2', '8', '2018-12-22 13:47:22', '2018-12-22 13:47:22', '10', '10', null);
INSERT INTO `tb_area` VALUES ('11', 'test6', '10', '2018-12-22 13:58:15', '2018-12-22 13:58:15', '11', '11', null);
INSERT INTO `tb_area` VALUES ('12', 'test10', '10', '2018-12-22 14:01:15', '2018-12-22 14:01:15', '10', '11', null);
INSERT INTO `tb_area` VALUES ('13', 'test10', '10', '2018-12-22 14:34:00', '2018-12-22 14:34:00', '10', '18', null);
INSERT INTO `tb_area` VALUES ('14', 'test10', '10', '2018-12-22 14:01:15', '2018-12-22 14:01:15', '10', '10', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT '' COMMENT '权限',
  `permission` varchar(255) DEFAULT NULL,
  `deviceId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test5', 'ban', 'ban@ban.com', 'admin', '1545460438250', 'ios');
INSERT INTO `user` VALUES ('2', 'test9', 'ban', 'ban@ban.com', 'admin', '1545460438250', 'ios');
INSERT INTO `user` VALUES ('3', 'test7', 'ban', 'ban@ban.com', 'admin', '1545460438287', 'ios');
INSERT INTO `user` VALUES ('4', 'test3', 'ban', 'ban@ban.com', 'admin', '1545460438618', 'ios');
INSERT INTO `user` VALUES ('5', 'test4', 'ban', 'ban@ban.com', 'admin', '1545460438613', 'ios');
INSERT INTO `user` VALUES ('6', 'test2', 'ban', 'ban@ban.com', 'admin', '1545460438617', 'ios');
INSERT INTO `user` VALUES ('7', 'test6', 'ban', 'ban@ban.com', 'admin', '1545460438614', 'ios');
INSERT INTO `user` VALUES ('8', 'test8', 'ban', 'ban@ban.com', 'admin', '1545460438614', 'ios');
INSERT INTO `user` VALUES ('9', 'test1', 'ban', 'ban@ban.com', 'admin', '1545460438613', 'ios');
INSERT INTO `user` VALUES ('10', 'test10', 'ban', 'ban@ban.com', 'admin', '1545460440586', 'ios');
