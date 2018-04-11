/*
Navicat MySQL Data Transfer

Source Server         : dev_db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:22:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for eb_kind
-- ----------------------------
DROP TABLE IF EXISTS `eb_kind`;
CREATE TABLE `eb_kind` (
  `kind_id` int(11) NOT NULL AUTO_INCREMENT,
  `kind` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '图书种类名目',
  `stamp` int(11) DEFAULT '0' COMMENT '图书种类标记',
  PRIMARY KEY (`kind_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of eb_kind
-- ----------------------------
INSERT INTO `eb_kind` VALUES ('1', '精品', '5');
INSERT INTO `eb_kind` VALUES ('2', '普通', '9');
INSERT INTO `eb_kind` VALUES ('3', '课程章节', '1');
INSERT INTO `eb_kind` VALUES ('4', '课程题型', '2');
INSERT INTO `eb_kind` VALUES ('5', '书本章节', '3');
INSERT INTO `eb_kind` VALUES ('6', '书本题型', '4');
INSERT INTO `eb_kind` VALUES ('7', '口袋单词类型', '6');
