/*
Navicat MySQL Data Transfer

Source Server         : 236Db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:23:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dc_group_school_rel
-- ----------------------------
DROP TABLE IF EXISTS `dc_group_school_rel`;
CREATE TABLE `dc_group_school_rel` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gs_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_id` int(11) DEFAULT '0',
  `school_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`gs_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of dc_group_school_rel
-- ----------------------------
INSERT INTO `dc_group_school_rel` VALUES (null, '1', '1', '096d87a42e3544f5abe2879d6c30b2fa');
