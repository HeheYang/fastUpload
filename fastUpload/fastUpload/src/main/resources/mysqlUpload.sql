/*
Navicat MySQL Data Transfer

Source Server         : fastpan
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : fastpan

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-20 15:18:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` bigint(32) unsigned NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) NOT NULL,
  `file_url` varchar(110) NOT NULL,
  `file_type` tinyint(4) NOT NULL COMMENT '文件类型:图片..',
  `file_state` tinyint(4) NOT NULL COMMENT '文件状态:已分享，未分享，已和谐',
  `file_md5` varchar(225) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL,
  `fistfile_md5` varchar(225) DEFAULT NULL,
  `file_size` varchar(21) NOT NULL DEFAULT '',
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='文件表';






DROP TABLE IF EXISTS `perfile`;
CREATE TABLE `perfile` (
  `perfile_id` bigint(32) NOT NULL AUTO_INCREMENT,
  `perfile_size` bigint(21) DEFAULT NULL,
  `perfile_name` varchar(225) DEFAULT NULL,
  `perfile_chuck` int(5) DEFAULT NULL,
  `perfile_url` varchar(110) DEFAULT NULL,
  `perfile_md5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`perfile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=693 DEFAULT CHARSET=utf8;
