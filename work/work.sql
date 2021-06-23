/*
Navicat MySQL Data Transfer

Source Server         : 8.131.54.33_3306
Source Server Version : 50730
Source Host           : 8.131.54.33:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-05-13 17:30:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for eqpt
-- ----------------------------
DROP TABLE IF EXISTS `eqpt`;
CREATE TABLE `eqpt` (
  `eqpt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `eqpt_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备名称',
  `eqpt_price` double(255,0) DEFAULT NULL COMMENT '设备价格',
  PRIMARY KEY (`eqpt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of eqpt
-- ----------------------------
INSERT INTO `eqpt` VALUES ('1', '测试设备1', '999');
INSERT INTO `eqpt` VALUES ('2', '测试设备2', '888');
INSERT INTO `eqpt` VALUES ('3', '测试设备3', '777');
INSERT INTO `eqpt` VALUES ('4', '测试设备4', '6666');

-- ----------------------------
-- Table structure for eqpt_data
-- ----------------------------
DROP TABLE IF EXISTS `eqpt_data`;
CREATE TABLE `eqpt_data` (
  `eqpt_id` int(11) DEFAULT NULL COMMENT '设备id',
  `eqpt_temperature` double(255,0) DEFAULT NULL COMMENT '温度',
  `eqpt_humidity` double(255,0) DEFAULT NULL COMMENT '湿度',
  `create_date` datetime DEFAULT NULL COMMENT '数据产生时间',
  KEY `fk` (`eqpt_id`) USING BTREE,
  CONSTRAINT `fk` FOREIGN KEY (`eqpt_id`) REFERENCES `eqpt` (`eqpt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of eqpt_data
-- ----------------------------
INSERT INTO `eqpt_data` VALUES ('1', '10', '20', '2021-05-01 16:06:04');
INSERT INTO `eqpt_data` VALUES ('1', '20', '30', '2021-05-02 16:06:47');
INSERT INTO `eqpt_data` VALUES ('1', '30', '20', '2021-05-03 16:06:04');
INSERT INTO `eqpt_data` VALUES ('1', '40', '30', '2021-05-04 16:07:45');
INSERT INTO `eqpt_data` VALUES ('1', '30', '22', '2021-05-05 16:08:32');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `user_pwd` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_role` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('6aa56f4e43f4b3916a5a129ea7fd805d', 'admin', '81dc9bdb52d04dc20036dbd8313ed055', 'admin', 'text@qq.com', null);
INSERT INTO `user` VALUES ('9057e27952a05a81362fe043b77bdf94', 'jiantengfei', '81dc9bdb52d04dc20036dbd8313ed055', '18810598119', '1308659229@qq.com', null);
