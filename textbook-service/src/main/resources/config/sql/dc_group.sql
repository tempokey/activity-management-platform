/*
Navicat MySQL Data Transfer

Source Server         : 236Db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:23:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dc_group
-- ----------------------------
DROP TABLE IF EXISTS `dc_group`;
CREATE TABLE `dc_group` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dc_group
-- ----------------------------
INSERT INTO `dc_group` VALUES (null, '1', '大连商校');
