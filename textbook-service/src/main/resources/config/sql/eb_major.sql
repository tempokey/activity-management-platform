/*
Navicat MySQL Data Transfer

Source Server         : dev_db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:22:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for eb_major
-- ----------------------------
DROP TABLE IF EXISTS `eb_major`;
CREATE TABLE `eb_major` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `major_num` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `parent_num` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '父类major_code',
  `type` int(64) DEFAULT NULL COMMENT '1,学科门类，2系，2专业',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of eb_major
-- ----------------------------
INSERT INTO `eb_major` VALUES ('1', '02', '经济学', '0', '1', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('2', '12', '管理学', '0', '1', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('3', '0201', '经济学类', '02', '2', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('4', '1202', '工商管理类', '12', '2', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('5', '120213T', '财务会计教育', '1202', '3', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('6', '020101', '经济学', '0201', '3', '', '2018-01-10 18:28:42', '', '2018-01-10 18:28:42', '', '0');
INSERT INTO `eb_major` VALUES ('8', '120204', '财务管理', '1202', '3', '', '2018-01-12 09:04:22', '', '2018-01-12 09:04:45', '', '0');
