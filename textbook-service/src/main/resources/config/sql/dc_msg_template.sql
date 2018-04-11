/*
Navicat MySQL Data Transfer

Source Server         : 236Db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-08 13:23:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dc_msg_template
-- ----------------------------
DROP TABLE IF EXISTS `dc_msg_template`;
CREATE TABLE `dc_msg_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `biz_code` varchar(20) DEFAULT '',
  `template_code` varchar(64) DEFAULT '',
  `channel` varchar(10) DEFAULT '',
  `sign_name` varchar(64) DEFAULT '',
  `del_flag` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dc_msg_template
-- ----------------------------
INSERT INTO `dc_msg_template` VALUES ('1', 'tb_sign_up', 'SMS_122282858', 'mc_ali', '爱丁集团', '1');
INSERT INTO `dc_msg_template` VALUES ('2', 'tb_log_in_password', 'SMS_122282858', 'mc_ali', '爱丁集团', '1');
INSERT INTO `dc_msg_template` VALUES ('3', 'tb_recover_password', 'SMS_122282858', 'mc_ali', '爱丁集团', '1');
INSERT INTO `dc_msg_template` VALUES ('4', 'tb_reset_password', 'SMS_122282858', 'mc_ali', '爱丁集团', '1');
