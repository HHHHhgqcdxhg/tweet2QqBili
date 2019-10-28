/*
 Navicat MySQL Data Transfer

 Source Server         : 
 Source Server Type    : MySQL
 Source Server Version : 
 Source Host           : 
 Source Schema         : tweet5

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 28/10/2019 12:35:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bili_account
-- ----------------------------
DROP TABLE IF EXISTS `bili_account`;
CREATE TABLE `bili_account`  (
  `bili_id` int(20) NOT NULL,
  `cookie` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `back_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注名',
  PRIMARY KEY (`bili_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qq_group_info
-- ----------------------------
DROP TABLE IF EXISTS `qq_group_info`;
CREATE TABLE `qq_group_info`  (
  `group_id` int(20) NOT NULL COMMENT '裙号',
  `group_nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '群名,让bot管理员能认出是哪个群的名字',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tweet2bili
-- ----------------------------
DROP TABLE IF EXISTS `tweet2bili`;
CREATE TABLE `tweet2bili`  (
  `tweet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bili_id` int(20) NOT NULL COMMENT '转推至 的bilibili账号id',
  `follow` int(1) NOT NULL DEFAULT 1,
  `trans` int(1) NOT NULL COMMENT 'boolean, 是否翻译',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '{content}' COMMENT '转推的格式',
  PRIMARY KEY (`tweet_id`, `bili_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tweet2qq
-- ----------------------------
DROP TABLE IF EXISTS `tweet2qq`;
CREATE TABLE `tweet2qq`  (
  `tweet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `qq_group_id` int(20) NOT NULL,
  `follow` int(1) NOT NULL COMMENT 'boolean, 是否关注',
  `tweet_nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '群里给定的推主的昵称',
  `trans` int(1) NOT NULL COMMENT 'boolean, 是否翻译',
  `send_rt` int(1) NOT NULL COMMENT 'boolean, 是否转发rt的推文',
  `media_only` int(1) NOT NULL COMMENT 'boolean, 是否只发送有图片的推文',
  `order_` int(20) NOT NULL COMMENT '排序',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '{tweet_nick_name}新推特:{content}' COMMENT '转推消息格式',
  PRIMARY KEY (`tweet_id`, `qq_group_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tweet_info
-- ----------------------------
DROP TABLE IF EXISTS `tweet_info`;
CREATE TABLE `tweet_info`  (
  `tweet_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tweet_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '推主名, @后的那堆东西',
  `back_up` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注名, 用于辨识出时哪个推主',
  PRIMARY KEY (`tweet_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
