/*
 Navicat Premium Data Transfer

 Source Server         : mp
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 112.74.111.23:3306
 Source Schema         : api-gateway

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 13/10/2023 22:26:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application_interface
-- ----------------------------
DROP TABLE IF EXISTS `application_interface`;
CREATE TABLE `application_interface`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口标识',
  `interface_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口名称',
  `interface_version` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口版本',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx`(`system_id`, `interface_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_interface
-- ----------------------------
INSERT INTO `application_interface` VALUES (28, 'api-gateway-test-provider', 'cn.pacman.gateway.rpc.IActivityBooth', '活动服务', '1.0.0', '2023-03-27 06:15:15', '2023-03-27 06:15:15');

-- ----------------------------
-- Table structure for application_interface_method
-- ----------------------------
DROP TABLE IF EXISTS `application_interface_method`;
CREATE TABLE `application_interface_method`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `interface_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口标识',
  `method_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法标识',
  `method_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方法名称',
  `parameter_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '参数类型；(RPC 限定单参数注册)；new String[]{\"java.lang.String\"}、new String[]{\"cn.bugstack.gateway.rpc.dto.XReq\"}',
  `uri` varchar(126) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int NULL DEFAULT NULL COMMENT 'true = 1是、false = 0否',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx`(`system_id`, `interface_id`, `method_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_interface_method
-- ----------------------------
INSERT INTO `application_interface_method` VALUES (28, 'api-gateway-test-provider', 'cn.pacman.gateway.rpc.IActivityBooth', 'test', '测试方法', 'java.lang.String,cn.pacman.gateway.rpc.dto.XReq', '/wg/activity/test', 'POST', 0, '2023-03-27 06:15:16', '2023-03-27 06:15:16');
INSERT INTO `application_interface_method` VALUES (29, 'api-gateway-test-provider', 'cn.pacman.gateway.rpc.IActivityBooth', 'insert', '插入方法', 'cn.pacman.gateway.rpc.dto.XReq', '/wg/activity/insert', 'POST', 1, '2023-03-27 06:15:16', '2023-03-27 06:15:16');
INSERT INTO `application_interface_method` VALUES (30, 'api-gateway-test-provider', 'cn.pacman.gateway.rpc.IActivityBooth', 'sayHi', '探活方法', 'java.lang.String', '/wg/activity/sayHi', 'GET', 0, '2023-03-27 06:15:16', '2023-03-27 06:15:16');

-- ----------------------------
-- Table structure for application_system
-- ----------------------------
DROP TABLE IF EXISTS `application_system`;
CREATE TABLE `application_system`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统名称',
  `system_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统类型；RPC、HTTP',
  `system_registry` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '注册中心',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_systemId`(`system_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_system
-- ----------------------------
INSERT INTO `application_system` VALUES (44, 'api-gateway-test-provider', '网关sdk测试工程', 'RPC', 'zookeeper://112.74.111.23:2181', '2023-03-27 06:15:15', '2023-03-27 06:15:15');

-- ----------------------------
-- Table structure for gateway_distribution
-- ----------------------------
DROP TABLE IF EXISTS `gateway_distribution`;
CREATE TABLE `gateway_distribution`  (
  `id` int NOT NULL COMMENT '自增主键',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关标识',
  `system_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统标识',
  `system_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '系统名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_distribution
-- ----------------------------

-- ----------------------------
-- Table structure for gateway_server
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server`;
CREATE TABLE `gateway_server`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `group_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_server
-- ----------------------------

-- ----------------------------
-- Table structure for gateway_server_detail
-- ----------------------------
DROP TABLE IF EXISTS `gateway_server_detail`;
CREATE TABLE `gateway_server_detail`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `group_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分组标识',
  `gateway_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关标识',
  `gateway_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关名称',
  `gateway_address` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '网关地址：127.0.0.1',
  `status` varchar(4) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '服务状态：0不可用、1可使用',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_gateway`(`gateway_id`, `gateway_address`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gateway_server_detail
-- ----------------------------
INSERT INTO `gateway_server_detail` VALUES (23, '10005', 'api-gateway-g6', '电商配送网关', '172.0.0.1:7398', '1', '2023-03-24 14:00:57', '2023-03-24 14:00:57');

-- ----------------------------
-- Table structure for http_statement
-- ----------------------------
DROP TABLE IF EXISTS `http_statement`;
CREATE TABLE `http_statement`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `application` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '应用名称',
  `interface_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '服务接口；RPC、其他',
  `method_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT ' 服务方法；RPC#method',
  `parameter_type` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '参数类型(RPC 限定单参数注册)；new String[]{\"java.lang.String\"}、new String[]{\"cn.bugstack.gateway.rpc.dto.XReq\"}',
  `uri` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '网关接口',
  `http_command_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '接口类型；GET、POST、PUT、DELETE',
  `auth` int NOT NULL DEFAULT 0 COMMENT 'true = 1是、false = 0否',
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of http_statement
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
