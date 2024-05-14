/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : demo008

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 14/05/2024 13:01:23
*/
CREATE DATABASE `demo008` CHARACTER SET 'utf8mb4';

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cat
-- ----------------------------
DROP TABLE IF EXISTS `cat`;
CREATE TABLE `cat`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, 'BOSS', '总经办', 0);
INSERT INTO `department` VALUES (2, 'PERSONAL', '人事部', 0);
INSERT INTO `department` VALUES (3, 'MARKET', '市场部', 0);
INSERT INTO `department` VALUES (4, 'SALE', '营销部', 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `realname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inputtime` date NULL DEFAULT NULL,
  `state` tinyint(1) NULL DEFAULT NULL,
  `admin` tinyint(1) NULL DEFAULT NULL,
  `dept_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_emp_dept`(`dept_id`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'admin', '超级管理员', 'admin1', NULL, NULL, '2024-05-14', 0, 1, 1);
INSERT INTO `employee` VALUES (2, 'zhangsan', '张三', 'zhangsan1', '13088888888', 'zhangsan@qq.com', '2024-05-14', 0, 0, 2);

-- ----------------------------
-- Table structure for employee_role
-- ----------------------------
DROP TABLE IF EXISTS `employee_role`;
CREATE TABLE `employee_role`  (
  `employee_id` bigint(20) NULL DEFAULT NULL,
  `role_id` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (5, '员工数据', 'employee:list');
INSERT INTO `permission` VALUES (6, '员工新增', 'employee:save');
INSERT INTO `permission` VALUES (7, '员工列表', 'employee:index');
INSERT INTO `permission` VALUES (8, '员工更新', 'employee:update');
INSERT INTO `permission` VALUES (9, '员工离职', 'employee:quit');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, 'HR', '人事专员');
INSERT INTO `role` VALUES (3, 'MARKETMAN', '市场专员');
INSERT INTO `role` VALUES (4, 'SALEMAN', '销售专员');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` bigint(20) NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_id` bigint(20) NULL DEFAULT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for systemlog
-- ----------------------------
DROP TABLE IF EXISTS `systemlog`;
CREATE TABLE `systemlog`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opUser_id` bigint(20) NULL DEFAULT NULL,
  `opTime` date NULL DEFAULT NULL,
  `opIP` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `functio` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of systemlog
-- ----------------------------
INSERT INTO `systemlog` VALUES (12, 1, '2024-05-14', '0:0:0:0:0:0:0:1', '[{\"page\":1,\"rows\":10,\"start\":0}]', 'com.awaion.demo008.service.impl.EmployeeServiceImpl:queryPage');
INSERT INTO `systemlog` VALUES (13, 1, '2024-05-14', '0:0:0:0:0:0:0:1', '[{\"page\":1,\"rows\":10,\"keyword\":\"\",\"start\":0}]', 'com.awaion.demo008.service.impl.EmployeeServiceImpl:queryPage');
INSERT INTO `systemlog` VALUES (14, NULL, '2024-05-14', '0:0:0:0:0:0:0:1', '[\"admin\"]', 'com.awaion.demo008.service.impl.EmployeeServiceImpl:queryEmpByUsername');
INSERT INTO `systemlog` VALUES (15, NULL, '2024-05-14', '0:0:0:0:0:0:0:1', '[\"admin\"]', 'com.awaion.demo008.service.impl.EmployeeServiceImpl:queryEmpByUsername');

-- ----------------------------
-- Table structure for systemmenu
-- ----------------------------
DROP TABLE IF EXISTS `systemmenu`;
CREATE TABLE `systemmenu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `iconCls` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_parent`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of systemmenu
-- ----------------------------
INSERT INTO `systemmenu` VALUES (1, '营销管理', 'icon-add', '', NULL);
INSERT INTO `systemmenu` VALUES (2, '客户管理', NULL, '', NULL);
INSERT INTO `systemmenu` VALUES (3, '系统管理', '', '', NULL);
INSERT INTO `systemmenu` VALUES (4, '订单合同管理', NULL, '', NULL);
INSERT INTO `systemmenu` VALUES (6, '潜在客户开发计划', 'icon-remove', '', 1);
INSERT INTO `systemmenu` VALUES (7, '客户信息管理', NULL, '', 2);
INSERT INTO `systemmenu` VALUES (25, '潜在客户管理', 'icon-add', '', 1);
INSERT INTO `systemmenu` VALUES (26, '客户跟进历史', '', '', 2);
INSERT INTO `systemmenu` VALUES (27, '客户移交记录', '', '', 2);
INSERT INTO `systemmenu` VALUES (28, '客户资源池管理', '', '', 2);
INSERT INTO `systemmenu` VALUES (29, '员工管理', '', '/employee', 3);
INSERT INTO `systemmenu` VALUES (30, '部门管理', '', '/department', 3);
INSERT INTO `systemmenu` VALUES (31, '角色管理', '', '/role', 3);
INSERT INTO `systemmenu` VALUES (32, '数据字典', '', '', 3);
INSERT INTO `systemmenu` VALUES (33, '合同管理', '', '', 4);
INSERT INTO `systemmenu` VALUES (34, '订单管理', '', '', 4);
INSERT INTO `systemmenu` VALUES (35, '售后管理', '', '', NULL);
INSERT INTO `systemmenu` VALUES (36, '权限管理', '', '/permission', 3);
INSERT INTO `systemmenu` VALUES (37, '菜单管理', '', '/systemMenu', 3);

SET FOREIGN_KEY_CHECKS = 1;
