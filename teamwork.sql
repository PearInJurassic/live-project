/*
Navicat MySQL Data Transfer

Source Server         : massizhi
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : teamwork

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-03-15 20:37:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dispatch1`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch1`;
CREATE TABLE `dispatch1` (
  `dispatchid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `uname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `utel` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `masknum` int(11) NOT NULL,
  PRIMARY KEY (`dispatchid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch1
-- ----------------------------
INSERT INTO `dispatch1` VALUES ('1', '110101199003074215', '张三', '13859631231', '2');
INSERT INTO `dispatch1` VALUES ('2', '110101199003078072', '李四', '18977777777', '3');
INSERT INTO `dispatch1` VALUES ('3', '110101199003070054', '王五', '18266666666', '3');
INSERT INTO `dispatch1` VALUES ('4', '110101199003073714', '李留', '13911111111', '1');
INSERT INTO `dispatch1` VALUES ('5', '110101199003077037', '马奇', '15911111111', '1');

-- ----------------------------
-- Table structure for `dispatch2`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch2`;
CREATE TABLE `dispatch2` (
  `dispatchid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(18) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `utel` varchar(45) NOT NULL,
  `masknum` int(11) NOT NULL,
  PRIMARY KEY (`dispatchid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch2
-- ----------------------------
INSERT INTO `dispatch2` VALUES ('1', '230102199003076310', '马果', '13899999999', '1');
INSERT INTO `dispatch2` VALUES ('2', '230102199003074411', '马想法', '13788888888', '2');
INSERT INTO `dispatch2` VALUES ('3', '23010219900307223X', '马是', '13566666666', '1');
INSERT INTO `dispatch2` VALUES ('4', '23010219900307063X', '马饭锅', '13244444444', '1');
INSERT INTO `dispatch2` VALUES ('5', '230102199003074315', '马是', '18255555555', '3');

-- ----------------------------
-- Table structure for `dispatch3`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch3`;
CREATE TABLE `dispatch3` (
  `dispatchid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(18) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `utel` varchar(45) NOT NULL,
  `masknum` int(11) NOT NULL,
  PRIMARY KEY (`dispatchid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch3
-- ----------------------------
INSERT INTO `dispatch3` VALUES ('1', '320102199009047280', '李去', '15855555555', '1');
INSERT INTO `dispatch3` VALUES ('2', '320102199009044901', '李发', '15455555555', '2');
INSERT INTO `dispatch3` VALUES ('3', '320102199009049024', '李如果', '18966666666', '3');
INSERT INTO `dispatch3` VALUES ('4', '320102199009043423', '李人', '18255555555', '2');
INSERT INTO `dispatch3` VALUES ('5', '320102199009047360', '李就', '18466666666', '1');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(18) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `utel` varchar(45) NOT NULL,
  `masknum` int(11) NOT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '350102200204057549', '张武', '13599999999', '2');
INSERT INTO `orders` VALUES ('2', '350102200204056546', '李想', '15644444444', '2');
INSERT INTO `orders` VALUES ('3', '350102200204052940', '赵东方', '15888888888', '3');
