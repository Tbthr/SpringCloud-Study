/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : db_01

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 07/10/2020 19:22:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `deptno` int(11) NOT NULL AUTO_INCREMENT,
  `dname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `db_source` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`deptno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (1, '开发部', 'db_01');
INSERT INTO `dept` VALUES (2, '管理部', 'db_01');
INSERT INTO `dept` VALUES (3, '运维部', 'db_01');
INSERT INTO `dept` VALUES (4, '研发部', 'db_01');
INSERT INTO `dept` VALUES (5, '项目部', 'db_01');
INSERT INTO `dept` VALUES (8, '测试', 'db_01');

SET FOREIGN_KEY_CHECKS = 1;
