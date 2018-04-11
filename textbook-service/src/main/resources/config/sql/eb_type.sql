/*
Navicat MySQL Data Transfer

Source Server         : dev_db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:22:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for eb_type
-- ----------------------------
DROP TABLE IF EXISTS `eb_type`;
CREATE TABLE `eb_type` (
  `type_id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of eb_type
-- ----------------------------
INSERT INTO `eb_type` VALUES ('1', '精品');
INSERT INTO `eb_type` VALUES ('2', '普通');
INSERT INTO `eb_type` VALUES ('3', '课程章节');
INSERT INTO `eb_type` VALUES ('4', '课程题型');
INSERT INTO `eb_type` VALUES ('5', '书本章节');
INSERT INTO `eb_type` VALUES ('6', '书本题型');
INSERT INTO `eb_type` VALUES ('7', '口袋单词类型');
