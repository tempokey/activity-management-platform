/*
Navicat MySQL Data Transfer

Source Server         : dev_db
Source Server Version : 100124
Source Host           : 192.168.1.236:3306
Source Database       : amp

Target Server Type    : MYSQL
Target Server Version : 100124
File Encoding         : 65001

Date: 2018-03-07 14:46:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dc_flow
-- ----------------------------
DROP TABLE IF EXISTS `dc_flow`;
CREATE TABLE `dc_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付渠道 1微信2支付宝',
  `project_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '项目编码',
  `project` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '项目名称',
  `mch_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商户号',
  `appid` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '公众账号',
  `device_info` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '设备号/功能模块',
  `nonce_str` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '随机字符串',
  `sign` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '签名',
  `sign_type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '签名类型',
  `body` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商品描述',
  `detail` varchar(6000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商品详情',
  `attach` varchar(127) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '附加数据',
  `trade_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '支付宝交易号',
  `out_trade_no` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '商户订单号',
  `transaction_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '微信订单号',
  `total_fee` decimal(11,4) DEFAULT '0.0000' COMMENT '订单总金额,单位分',
  `spbill_create_ip` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '终端IP',
  `time_start` datetime DEFAULT NULL COMMENT '流水生成时间',
  `time_expire` datetime DEFAULT NULL COMMENT '交易结束时间',
  `notify_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '通知校验id',
  `notify_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '异步通知地址',
  `trade_type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '交易类型',
  `product_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `type` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '支付退款1支付2退款',
  `status` int(11) DEFAULT '1' COMMENT '流水状态1待处理2等待3成功4失败',
  `result` int(11) DEFAULT NULL COMMENT '流程状态0待处理1支付结束2退款结束',
  `return_code` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '状态码',
  `return_msg` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '原因',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '修改者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更改时间',
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=426 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='支付流水记录 注意不同支付方式的区分\r\n目前主要针对微信';

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
-- Table structure for dc_message_queue
-- ----------------------------
DROP TABLE IF EXISTS `dc_message_queue`;
CREATE TABLE `dc_message_queue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_code` varchar(10) DEFAULT NULL,
  `queue` varchar(32) DEFAULT NULL,
  `del_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Table structure for dc_order
-- ----------------------------
DROP TABLE IF EXISTS `dc_order`;
CREATE TABLE `dc_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '父订单id',
  `project_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目id',
  `project` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '项目名称',
  `order_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '订单号',
  `flow_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '流水号',
  `out_flow_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '退款流水号',
  `product_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '产品编码',
  `ad_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `user_id` int(11) DEFAULT '0' COMMENT '用户编码',
  `context` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '内容',
  `method` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '支付方式 W微信 A支付宝',
  `price` decimal(10,4) DEFAULT '0.0000' COMMENT '价格',
  `refund` decimal(11,4) DEFAULT '0.0000' COMMENT '退款金额',
  `invoice` int(11) DEFAULT '2' COMMENT '是否要发票 1要 ~1不要',
  `status` int(11) DEFAULT '1' COMMENT '订单状态1待处理2等待3成功4失败',
  `result` int(11) DEFAULT '0' COMMENT '处理状态0待处理1流程结束',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单修改时间',
  `commit_time` datetime DEFAULT NULL COMMENT '支付确认时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `mark` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '创建者',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '更新者',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=456 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';

-- ----------------------------
-- Table structure for dc_permission
-- ----------------------------
DROP TABLE IF EXISTS `dc_permission`;
CREATE TABLE `dc_permission` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for dc_role
-- ----------------------------
DROP TABLE IF EXISTS `dc_role`;
CREATE TABLE `dc_role` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for dc_role_permission_rel
-- ----------------------------
DROP TABLE IF EXISTS `dc_role_permission_rel`;
CREATE TABLE `dc_role_permission_rel` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `rp_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT '0',
  `permission_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `permission_id` int(11) DEFAULT '0',
  PRIMARY KEY (`rp_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for dc_user
-- ----------------------------
DROP TABLE IF EXISTS `dc_user`;
CREATE TABLE `dc_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `ad_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '爱丁号',
  `token_key` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `remote_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT 'ip地址',
  `school_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '学校id',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户姓名',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `account` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '用户名',
  `salt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '随机字符串',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `press_id` int(11) DEFAULT '0' COMMENT '出版社id',
  `course_id` int(11) DEFAULT '0' COMMENT '课程id',
  `type` int(11) DEFAULT '3' COMMENT '1教师2学生3其他',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`),
  UNIQUE KEY `ad_code` (`ad_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for dc_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `dc_user_role_rel`;
CREATE TABLE `dc_user_role_rel` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `ur_rel_code` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ur_rel_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_ask
-- ----------------------------
DROP TABLE IF EXISTS `eb_ask`;
CREATE TABLE `eb_ask` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ask_code` int(11) NOT NULL AUTO_INCREMENT,
  `ask` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '选项',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '内容',
  `answer` bit(1) DEFAULT b'0' COMMENT '1 为该题答案，~1普通选项',
  `instruction` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '解析',
  `question_code` int(11) DEFAULT NULL,
  `question_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ask_code`)
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_book
-- ----------------------------
DROP TABLE IF EXISTS `eb_book`;
CREATE TABLE `eb_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '书名',
  `author_id` int(11) DEFAULT '0',
  `brief` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '简介',
  `major_id` int(11) DEFAULT NULL COMMENT '系编码',
  `cover` text COLLATE utf8mb4_unicode_ci COMMENT '封面路径',
  `divide` decimal(10,4) DEFAULT '0.0000',
  `kind_id` int(11) DEFAULT NULL,
  `length` int(11) DEFAULT '0' COMMENT '字符数',
  `mark` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '备注',
  `num` int(11) DEFAULT '0' COMMENT '下载量',
  `path` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '下载地址',
  `press_id` int(11) DEFAULT '0' COMMENT '出版社编码',
  `price` decimal(10,4) DEFAULT '0.0000' COMMENT '标价',
  `sale` decimal(10,4) DEFAULT '0.0000' COMMENT '促销数',
  `status` int(11) DEFAULT '2' COMMENT '上下架 1上架2下架',
  `type_id` int(11) DEFAULT '0' COMMENT '教材：1是精品课，2是普通书籍 \r\n练习册：3是课程章节，4是课程题型，5书本章节，6书本题型，7口袋单词类型 \r\n（课程是进入之后需要选书，书籍是进入直接显示章节）',
  `tb_parent_id` int(11) DEFAULT '0' COMMENT '迭代tb id',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `attachment` int(11) DEFAULT '1' COMMENT '课文2 习题3 资源5 mod',
  `file` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '0',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='书籍';

-- ----------------------------
-- Table structure for eb_context
-- ----------------------------
DROP TABLE IF EXISTS `eb_context`;
CREATE TABLE `eb_context` (
  `context_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(255) DEFAULT NULL,
  `context` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `order` int(11) DEFAULT '0',
  `type` int(11) DEFAULT '3' COMMENT '1 父菜单 2 子菜单 3 通用菜单',
  `parent_id` int(11) DEFAULT NULL COMMENT '父contextCode',
  `chapter_id` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT '1' COMMENT '权重',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`context_id`)
) ENGINE=InnoDB AUTO_INCREMENT=736 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_data
-- ----------------------------
DROP TABLE IF EXISTS `eb_data`;
CREATE TABLE `eb_data` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `data_code` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT '0',
  `type` int(11) DEFAULT '0' COMMENT '1 章 2 节 3 内容',
  `content` varchar(9999) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `pageno` int(11) DEFAULT '0',
  `parent_id` int(11) DEFAULT '0' COMMENT '父id',
  `test` int(11) DEFAULT '0' COMMENT '0 无测试 1有测试',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`data_code`)
) ENGINE=InnoDB AUTO_INCREMENT=922 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
-- Table structure for eb_num
-- ----------------------------
DROP TABLE IF EXISTS `eb_num`;
CREATE TABLE `eb_num` (
  `num` int(11) DEFAULT NULL,
  `len` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_press
-- ----------------------------
DROP TABLE IF EXISTS `eb_press`;
CREATE TABLE `eb_press` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `press_id` int(11) NOT NULL AUTO_INCREMENT,
  `press` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '出版社',
  `area_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '区域编码',
  `create_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `del_flag` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`press_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='出版社';

-- ----------------------------
-- Table structure for eb_question
-- ----------------------------
DROP TABLE IF EXISTS `eb_question`;
CREATE TABLE `eb_question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL,
  `question` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `score` double(12,2) DEFAULT NULL,
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT '1' COMMENT '1 选择 2 判断',
  `period_id` int(11) DEFAULT '0',
  `resolution` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '解析',
  `context_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_test
-- ----------------------------
DROP TABLE IF EXISTS `eb_test`;
CREATE TABLE `eb_test` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `test_code` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT '-1',
  `data_code` int(11) DEFAULT '-1',
  `subject_type` int(11) DEFAULT '-1',
  `question` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `body` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `answer` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `analysis` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `score` double(12,2) DEFAULT '0.00',
  `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  PRIMARY KEY (`test_code`)
) ENGINE=InnoDB AUTO_INCREMENT=613 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for eb_txt
-- ----------------------------
DROP TABLE IF EXISTS `eb_txt`;
CREATE TABLE `eb_txt` (
  `id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `txt_code` int(11) NOT NULL AUTO_INCREMENT,
  `txt` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `period_code` int(11) DEFAULT NULL,
  `period_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `context_code` int(11) DEFAULT NULL,
  `context_id` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `entry_code` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`txt_code`)
) ENGINE=InnoDB AUTO_INCREMENT=523 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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
-- Table structure for eb_user_book_rel
-- ----------------------------
DROP TABLE IF EXISTS `eb_user_book_rel`;
CREATE TABLE `eb_user_book_rel` (
  `ub_rel_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ad_code` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '爱丁号',
  `book_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ub_rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
