/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : questms

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 04/09/2022 21:07:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acl_menu
-- ----------------------------
DROP TABLE IF EXISTS `acl_menu`;
CREATE TABLE `acl_menu`  (
  `menu_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` tinyint NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` tinyint NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` tinyint NULL DEFAULT 0 COMMENT '菜单状态（0显示 1隐藏）',
  `status` tinyint NULL DEFAULT 0 COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of acl_menu
-- ----------------------------
INSERT INTO `acl_menu` VALUES ('1566053980830437378', '菜单1', '0', 0, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:22:20', '2022-09-03 21:22:20');
INSERT INTO `acl_menu` VALUES ('1566054017379602433', '菜单2', '0', 2, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:22:28', '2022-09-03 21:22:28');
INSERT INTO `acl_menu` VALUES ('1566054041735925762', '菜单3', '0', 3, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:22:34', '2022-09-03 21:22:34');
INSERT INTO `acl_menu` VALUES ('1566054110480568321', '菜单4', '0', 4, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:22:50', '2022-09-03 21:22:50');
INSERT INTO `acl_menu` VALUES ('1566054262561837058', '菜单1-1', '1566053980830437378', 1, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:23:27', '2022-09-03 21:23:27');
INSERT INTO `acl_menu` VALUES ('1566054293448691713', '菜单1-2', '1566053980830437378', 2, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:23:34', '2022-09-03 21:23:34');
INSERT INTO `acl_menu` VALUES ('1566054414898958337', '菜单1-1-1', '1566054262561837058', 1, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:24:03', '2022-09-03 21:24:03');
INSERT INTO `acl_menu` VALUES ('1566054439049760770', '菜单1-1-2', '1566054262561837058', 2, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:24:09', '2022-09-03 21:24:09');
INSERT INTO `acl_menu` VALUES ('1566054512861122562', '菜单2-1', '1566054017379602433', 1, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:24:26', '2022-09-03 21:24:26');
INSERT INTO `acl_menu` VALUES ('1566054537745928194', '菜单2-2', '1566054017379602433', 2, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:24:32', '2022-09-03 21:24:32');
INSERT INTO `acl_menu` VALUES ('1566059083146797058', '菜单3-1', '1566054041735925762', 1, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:42:36', '2022-09-03 21:42:36');
INSERT INTO `acl_menu` VALUES ('1566059124821401602', '菜单3-2', '1566054041735925762', 2, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:42:46', '2022-09-03 21:42:46');
INSERT INTO `acl_menu` VALUES ('1566059143666409474', '菜单3-3', '1566054041735925762', 3, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-03 21:42:50', '2022-09-03 21:42:50');
INSERT INTO `acl_menu` VALUES ('1566306276059500545', 'string', 'string', 0, 'string', 'string', 'string', 0, 0, 'M', 0, 0, 'string', 'string', 'string', '2022-09-04 14:04:51', '2022-09-04 14:04:51');

-- ----------------------------
-- Table structure for acl_role
-- ----------------------------
DROP TABLE IF EXISTS `acl_role`;
CREATE TABLE `acl_role`  (
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '角色状态（0正常 1停用）',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除标志（0代表存在 2代表删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of acl_role
-- ----------------------------
INSERT INTO `acl_role` VALUES ('1', '超级管理员', 'admin', 1, 0, 0, '超级管理员', '2022-08-15 20:40:11', NULL);
INSERT INTO `acl_role` VALUES ('100', '学生', 'student', 3, 0, 0, '学生的角色', '2022-08-19 15:17:13', '2022-08-26 13:04:05');
INSERT INTO `acl_role` VALUES ('101', '家长', 'parents', 4, 0, 0, NULL, '2022-08-19 15:17:42', '2022-08-26 16:17:20');
INSERT INTO `acl_role` VALUES ('2', '学校管理员', 'common', 2, 0, 0, '学校管理员', '2022-08-15 20:40:11', '2022-08-26 16:17:09');

-- ----------------------------
-- Table structure for acl_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `acl_role_menu`;
CREATE TABLE `acl_role_menu`  (
  `role_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `menu_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_permission_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of acl_role_menu
-- ----------------------------
INSERT INTO `acl_role_menu` VALUES ('100', '1566053980830437378', '2022-09-04 11:24:24', '2022-09-04 11:24:24');
INSERT INTO `acl_role_menu` VALUES ('100', '1566054017379602433', '2022-09-04 11:24:24', '2022-09-04 11:24:24');

-- ----------------------------
-- Table structure for acl_user_role
-- ----------------------------
DROP TABLE IF EXISTS `acl_user_role`;
CREATE TABLE `acl_user_role`  (
  `user_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '用户id',
  `role_id` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '角色id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`, `user_id`) USING BTREE,
  INDEX `idx_role_id`(`role_id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of acl_user_role
-- ----------------------------
INSERT INTO `acl_user_role` VALUES ('1566407113020637185', '100', '2022-09-04 20:53:00', '2022-09-04 20:53:00');
INSERT INTO `acl_user_role` VALUES ('1566407176551759874', '100', '2022-09-04 20:53:00', '2022-09-04 20:53:00');
INSERT INTO `acl_user_role` VALUES ('1566407218285084673', '100', '2022-09-04 20:53:00', '2022-09-04 20:53:00');
INSERT INTO `acl_user_role` VALUES ('1566407258332299265', '100', '2022-09-04 20:53:00', '2022-09-04 20:53:00');
INSERT INTO `acl_user_role` VALUES ('1566407113020637185', '101', '2022-09-04 20:53:18', '2022-09-04 20:53:18');
INSERT INTO `acl_user_role` VALUES ('1566407176551759874', '101', '2022-09-04 20:53:18', '2022-09-04 20:53:18');
INSERT INTO `acl_user_role` VALUES ('1566407218285084673', '101', '2022-09-04 20:53:18', '2022-09-04 20:53:18');
INSERT INTO `acl_user_role` VALUES ('1566407258332299265', '101', '2022-09-04 20:53:18', '2022-09-04 20:53:18');

-- ----------------------------
-- Table structure for device_info
-- ----------------------------
DROP TABLE IF EXISTS `device_info`;
CREATE TABLE `device_info`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备状态，0：可以借用，1：正在借用，2：正在修理',
  `qr_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '二维码字段',
  `label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '设备标签',
  `type` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备种类',
  `disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0:可借 1:不可借',
  `state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '设备状态，0：空闲，1：借用中，2：维修中',
  `max_use_time` int NOT NULL COMMENT '最大借用时间（小时）',
  `current_user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前使用者id',
  `batch` int NULL DEFAULT 0 COMMENT '批次',
  `lab_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属实验室',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_info
-- ----------------------------
INSERT INTO `device_info` VALUES ('1438420236616200193', 'c56b600459be4aaa8a97f6d182800aa8', '投影仪1', '1403977992357818370', 0, 0, 30, '', 1, '1416770497566511105', 0, '2021-09-16 16:31:24', '2021-10-06 20:17:09');
INSERT INTO `device_info` VALUES ('1438859692305362945', '01b36f4bccfd44069580a8bb4cbea5b9', '苹果电脑1', '1408658936483110913', 0, 0, 11, '', 1, '1417049429796724738', 0, '2021-09-17 21:37:38', '2021-10-06 22:17:13');
INSERT INTO `device_info` VALUES ('1438859692305362946', '2161b0c671954a94aadb348ad2515fac', '苹果电脑2', '1408658936483110913', 0, 0, 11, '', 1, '1417049429796724738', 0, '2021-09-17 21:37:38', '2021-10-06 20:07:34');
INSERT INTO `device_info` VALUES ('1440909020133896194', '8d5dbaeefcdb4ebcb6029a5ea887a6a9', '智能机器人1', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020133896195', '7fd6ef83921d4edd8bbb9a4067749658', '智能机器人2', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020133896196', 'e18b2ed6878c4658966341ceee0888fb', '智能机器人3', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020133896197', '94802a0f7726407f9130a19dbcff399f', '智能机器人4', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020133896198', 'cd8c5b68e771498b9cfc6471f165a9dc', '智能机器人5', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020133896199', '53de358e890a4e6fadbbb63bc1b00442', '智能机器人6', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020201005057', 'efab09f90a7243e2afe36c7229567c8d', '智能机器人7', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909020201005058', '9b615b95adb44c10983f5e81b4c32f8c', '智能机器人8', '1440908918543659009', 0, 0, 2, NULL, 1, '1417049429796724738', 0, '2021-09-23 13:20:56', '2021-09-23 13:20:56');
INSERT INTO `device_info` VALUES ('1440909127558410241', '34413cd24bf84f829f5c714174d7fa2f', '苹果电脑3', '1408658936483110913', 0, 0, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-04 20:23:46');
INSERT INTO `device_info` VALUES ('1440909127558410242', '8fc7237f87894f52b01663d70bb881c0', '苹果电脑4', '1408658936483110913', 0, 2, 10, NULL, 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-04 20:23:21');
INSERT INTO `device_info` VALUES ('1440909127558410243', '8312a82f5b714dd5a6daa574b2c63bdd', '苹果电脑5', '1408658936483110913', 0, 0, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-08 10:41:26');
INSERT INTO `device_info` VALUES ('1440909127558410244', '587f01485b6b4973855001011772ad85', '苹果电脑6', '1408658936483110913', 0, 0, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-04 20:22:41');
INSERT INTO `device_info` VALUES ('1440909127558410245', '8b85ee35866644b78d0785b573ca8eb7', '苹果电脑7', '1408658936483110913', 0, 0, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-07 19:09:50');
INSERT INTO `device_info` VALUES ('1440909127558410246', 'b9a3c04c4e3c4fd1b31337f663d90a6e', '苹果电脑8', '1408658936483110913', 0, 0, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-04 19:30:38');
INSERT INTO `device_info` VALUES ('1440909127558410247', 'e46d76617e594d5b9d958ceb57aeaf5b', '苹果电脑9', '1408658936483110913', 0, 1, 10, '', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-04 20:32:42');
INSERT INTO `device_info` VALUES ('1440909127558410248', 'f9146684889641b8876aadd1a1e7158b', '苹果电脑10', '1408658936483110913', 0, 0, 10, NULL, 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-09-23 13:21:21');
INSERT INTO `device_info` VALUES ('1440909127558410249', '5fb1bccd196a443895d4b59f5cd4184b', '苹果电脑11', '1408658936483110913', 0, 1, 10, '1438415603348910081', 2, '1416770497566511105', 0, '2021-09-23 13:21:21', '2021-10-08 20:51:58');
INSERT INTO `device_info` VALUES ('1441311344488677377', 'ec87cc1eb98a4dfca511c0d82cb3644d', 'imac1', '1404010904146984962', 0, 0, 2, NULL, 0, '1438762279641083905', 0, '2021-09-24 15:59:37', '2021-09-24 15:59:37');
INSERT INTO `device_info` VALUES ('1444991194273509377', '3b78dc8ae2f24bb3b8d499162eeaf2fc', '苹果电脑12', '1408658936483110913', 0, 2, 10, '', 1, '1416770497566511105', 0, '2021-10-04 19:42:02', '2021-10-04 20:47:25');
INSERT INTO `device_info` VALUES ('1445005792594718722', 'f53ad99ba2b64bc9b91fe61e7207d6cf', '投影仪3', '1403977992357818370', 0, 0, 10, NULL, 1, '1417049429796724738', 0, '2021-10-04 20:40:03', '2021-10-04 20:40:03');
INSERT INTO `device_info` VALUES ('1445006232237469697', 'c07cfabc6fda4e379a8cccf819fb0f87', '键盘1', '1405705801937805313', 0, 0, 20, NULL, 1, '1417049429796724738', 0, '2021-10-04 20:41:47', '2021-10-04 20:41:47');
INSERT INTO `device_info` VALUES ('1445006232237469699', '418fbaec234f40859b22b470d4dde633', '键盘3', '1405705801937805313', 0, 0, 20, NULL, 1, '1417049429796724738', 0, '2021-10-04 20:41:47', '2021-10-04 20:41:47');
INSERT INTO `device_info` VALUES ('1445006837395845121', '742ed8b84da64f30925b1c2b77c38130', 'imac2', '1404010904146984962', 0, 0, 5, NULL, 1, '1416770497566511105', 0, '2021-10-04 20:44:12', '2021-10-04 20:44:12');
INSERT INTO `device_info` VALUES ('1445006837395845122', 'bf97be6b32764fcebcb28ad7498c68d6', 'imac3', '1404010904146984962', 0, 0, 5, NULL, 1, '1416770497566511105', 0, '2021-10-04 20:44:12', '2021-10-04 20:44:12');

-- ----------------------------
-- Table structure for device_record
-- ----------------------------
DROP TABLE IF EXISTS `device_record`;
CREATE TABLE `device_record`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `device_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备ID',
  `lab_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属实验室',
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借用者ID',
  `state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0: 预约中 1：借用中 2：已归还',
  `start_time` datetime NOT NULL COMMENT '借用时间',
  `end_time` datetime NOT NULL COMMENT '理论归还时间',
  `real_end_time` datetime NULL DEFAULT NULL COMMENT '真实归还时间',
  `start_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '借用时照片',
  `end_img` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '归还时照片',
  `purpose` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
  `sign` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否签到，0：未签到，1：签到，2：超时',
  `disabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否通过预约',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_record
-- ----------------------------
INSERT INTO `device_record` VALUES ('1444988327282176002', '1440909127558410246', '1416770497566511105', '143212312142141', 2, '2021-10-04 19:30:14', '2021-10-05 05:30:14', '2021-10-08 19:47:10', 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/borrowAndReturn/2021/10/04/a4975059883d45c79ce5e8e8a8351d2ctmp_d2a88b9871331f880d4fc34dfb1d430a494eb360354a0aea.jpg', NULL, '研究', '', 0, 1, 0, '2021-10-04 19:30:38', '2021-10-04 19:30:38');
INSERT INTO `device_record` VALUES ('1444988457196548098', '1440909127558410243', '1416770497566511105', '1438415603348910081', 2, '2021-10-04 19:30:49', '2021-10-05 05:30:49', '2021-10-08 20:50:35', 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/borrowAndReturn/2021/10/04/18c6857331ed47d8bcb4de27d75c9f9ftmp_ae52aff48f4ef469522bd66092839f6164cc3b4657e0ab2a.jpg', NULL, '研究', '使用完毕', 0, 1, 0, '2021-10-04 19:31:09', '2021-10-04 19:36:54');
INSERT INTO `device_record` VALUES ('1445000866971873281', '1440909127558410245', '1416770497566511105', '1438415603348910081', 0, '2021-10-08 15:19:56', '2021-10-08 19:47:28', '2021-10-04 20:22:00', 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/borrowAndReturn/2021/10/04/b754b2b5a7d9405eae4054d9e8517d34tmp_e275ea77eaa232f6320d08ce32aaaa3c80419dd91e66cd8f.jpg', NULL, '学习使用', '学习完毕', 0, 1, 0, '2021-10-04 20:20:28', '2021-10-04 20:22:00');
INSERT INTO `device_record` VALUES ('1445001695061700609', '1440909127558410245', '1416770497566511105', '1438415603348910081', 0, '2021-10-09 08:23:41', '2021-10-09 14:23:41', '2021-10-06 20:24:34', '', NULL, '研究', NULL, 0, 1, 0, '2021-10-04 20:23:46', '2021-10-04 20:23:46');
INSERT INTO `device_record` VALUES ('1445007449420296193', '1444991194273509377', '1416770497566511105', '1438415603348910081', 2, '2021-10-04 20:46:23', '2021-10-05 06:46:23', '2021-10-04 20:46:58', 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/borrowAndReturn/2021/10/04/78e8ae8991244dde8f140a44d00c1883tmp_9d0a5539a634ae5d52109ece4000eb45b835e66f049f774a.jpg', 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/borrowAndReturn/2021/10/04/455b2124d3e540f6a0504d574b1001d6tmp_3a172a567bf2a77e43bb1f3c66bcedbdbca3f6081045e8aa.jpg', '研究', '无', 0, 1, 0, '2021-10-04 20:46:38', '2021-10-04 20:46:58');
INSERT INTO `device_record` VALUES ('1445414753198243842', '1440909127558410243', 'string', '1438415603348910081', 2, '2021-10-05 15:43:33', '2021-10-05 15:43:33', '2021-10-08 10:41:26', NULL, 'string', 'string', '无', 1, 1, 0, '2021-10-05 23:45:06', '2021-10-08 10:41:26');
INSERT INTO `device_record` VALUES ('1445722398257926146', '1438859692305362946', '1417049429796724738', '1438415603348910081', 1, '2021-10-06 20:07:28', '2021-10-07 07:07:28', '2021-10-08 10:23:21', '', NULL, '胜多负少大', NULL, 1, 1, 0, '2021-10-06 20:07:35', '2021-10-06 20:07:35');
INSERT INTO `device_record` VALUES ('1445724807013158914', '1438420236616200193', '1416770497566511105', '1438415603348910081', 1, '2021-10-06 20:17:03', '2021-10-08 02:17:03', '2021-10-08 10:23:24', '', NULL, '研究', NULL, 1, 1, 0, '2021-10-06 20:17:09', '2021-10-06 20:17:09');
INSERT INTO `device_record` VALUES ('1445754981549170690', '1438859692305362945', '1417049429796724738', '1438415603348910081', 2, '2021-10-06 22:17:00', '2021-10-07 09:17:00', '2021-10-06 22:17:13', '', NULL, '研究', '无', 1, 1, 0, '2021-10-06 22:17:03', '2021-10-06 22:17:13');
INSERT INTO `device_record` VALUES ('1446458346402807809', '1440909127558410249', '1416770497566511105', '1438415603348910081', 1, '2021-10-08 20:51:54', '2021-10-09 06:51:54', NULL, '', NULL, '研究', NULL, 1, 1, 0, '2021-10-08 20:51:58', '2021-10-08 20:51:58');

-- ----------------------------
-- Table structure for device_repair_record
-- ----------------------------
DROP TABLE IF EXISTS `device_repair_record`;
CREATE TABLE `device_repair_record`  (
  `id` varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '报修人id',
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '故障描述',
  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备id',
  `repair_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备故障类型',
  `state` int NULL DEFAULT 0 COMMENT '状态 0：修理中，1：完成修理',
  `device_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备图片',
  `create_time` datetime(6) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) NULL DEFAULT NULL COMMENT '更新时间',
  `device_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `repair` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_repair_record
-- ----------------------------
INSERT INTO `device_repair_record` VALUES ('1445001590032134145', '1438415603348910081', '无法开机', '1440909127558410242', '无法开机', 0, '', '2021-10-04 20:23:20.566000', '2021-10-04 20:23:20.566000', '苹果电脑4', NULL);
INSERT INTO `device_repair_record` VALUES ('1445007649476014082', '1438415603348910081', '无法登录', '1444991194273509377', '无法开机', 0, 'https://labms-sdnu.oss-cn-beijing.aliyuncs.com/repair/2021/10/04/4788dbc937b34ed087e1d2293733ff67tmp_f6d99865e308ead8db2c5bd0d57244b8b0419d536484f4c5.jpg', '2021-10-04 20:47:25.236000', '2021-10-04 20:47:25.236000', '苹果电脑12', NULL);

-- ----------------------------
-- Table structure for device_type
-- ----------------------------
DROP TABLE IF EXISTS `device_type`;
CREATE TABLE `device_type`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名字',
  `lab_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属实验室',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of device_type
-- ----------------------------
INSERT INTO `device_type` VALUES ('1403977992357818370', '投影仪', NULL, 1, '2021-06-13 15:30:12', '2021-06-13 15:30:12');
INSERT INTO `device_type` VALUES ('1404010904146984962', 'imac', NULL, 1, '2021-06-13 17:40:59', '2021-06-13 17:40:59');
INSERT INTO `device_type` VALUES ('1405454638541709314', '软工', NULL, 1, '2021-06-17 17:17:52', '2021-06-17 17:17:52');
INSERT INTO `device_type` VALUES ('1405705801937805313', '键盘', NULL, 1, '2021-06-18 09:55:54', '2021-06-18 09:55:54');
INSERT INTO `device_type` VALUES ('1408658936483110913', '苹果电脑', NULL, 0, '2021-06-26 13:30:36', '2021-06-26 13:30:36');
INSERT INTO `device_type` VALUES ('1440908918543659009', '智能机器人', NULL, 0, '2021-09-23 13:20:32', '2021-09-23 13:20:32');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for ucenter_academy
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_academy`;
CREATE TABLE `ucenter_academy`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院名字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_academy
-- ----------------------------
INSERT INTO `ucenter_academy` VALUES ('1415963233737793538', '信息科学与工程学院', '2021-07-16 17:15:16', '2021-07-16 17:15:16');
INSERT INTO `ucenter_academy` VALUES ('1437049639269105666', '马克思主义学院', '2021-09-12 21:45:08', '2021-09-12 21:45:08');

-- ----------------------------
-- Table structure for ucenter_clazz
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_clazz`;
CREATE TABLE `ucenter_clazz`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `academy_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院ID',
  `major_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专业ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_clazz
-- ----------------------------
INSERT INTO `ucenter_clazz` VALUES ('1415963577561669634', '1415963233737793538', '1415963287340998657', '计工本1902', '2021-07-16 17:16:38', '2021-07-16 17:16:38');
INSERT INTO `ucenter_clazz` VALUES ('1415963606649167873', '1415963233737793538', '1415963287340998657', '计工本1901', '2021-07-16 17:16:45', '2021-07-16 17:16:45');
INSERT INTO `ucenter_clazz` VALUES ('1433310332570107906', '1415963233737793538', '1415963287340998657', '计工本2001', '2021-09-02 14:06:28', '2021-09-02 14:06:28');
INSERT INTO `ucenter_clazz` VALUES ('1440909554068836354', '1415963233737793538', '1440909418601205761', '计本2001', '2021-09-23 13:23:03', '2021-09-23 13:23:03');
INSERT INTO `ucenter_clazz` VALUES ('1440909591691743234', '1415963233737793538', '1440909459495669762', '网安本1901', '2021-09-23 13:23:12', '2021-09-23 13:23:12');
INSERT INTO `ucenter_clazz` VALUES ('1440909619445452802', '1415963233737793538', '1440909459495669762', '网安本2001', '2021-09-23 13:23:19', '2021-09-23 13:23:19');
INSERT INTO `ucenter_clazz` VALUES ('1440909662151856130', '1415963233737793538', '1440909491686952961', '物联本1901', '2021-09-23 13:23:29', '2021-09-23 13:23:29');
INSERT INTO `ucenter_clazz` VALUES ('1440909693068070913', '1415963233737793538', '1440909491686952961', '物联本2001', '2021-09-23 13:23:36', '2021-09-23 13:23:36');

-- ----------------------------
-- Table structure for ucenter_control
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_control`;
CREATE TABLE `ucenter_control`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '功能名字',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标',
  `src` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由',
  `disabled` tinyint NOT NULL DEFAULT 0 COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `sort` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_control
-- ----------------------------
INSERT INTO `ucenter_control` VALUES ('1', '设备借用', '/static/home/borrow.png', '/pages/device/borrow/borrow', 0, '2021-09-16 16:15:10', '2021-09-16 16:15:12', '1');
INSERT INTO `ucenter_control` VALUES ('2', '设备报修', '/static/home/repair.png', '/pages/device/repair/repair', 0, '2021-09-16 16:17:16', '2021-09-16 16:17:17', '3');
INSERT INTO `ucenter_control` VALUES ('3', '设备预约', '/static/home/signIn.png', '/pages/device/order/order', 1, '2021-09-16 16:19:37', '2021-09-16 16:19:39', '2');
INSERT INTO `ucenter_control` VALUES ('4', '设备归还', '/static/home/borrow.png', '/pages/device/back/back', 0, '2021-09-16 16:35:17', '2021-09-16 16:35:19', '4');

-- ----------------------------
-- Table structure for ucenter_department
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_department`;
CREATE TABLE `ucenter_department`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `name` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '父级部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_department
-- ----------------------------
INSERT INTO `ucenter_department` VALUES ('1416321965646725121', '计算机系', '1415963233737793538', '2021-07-17 17:00:45', '2021-07-17 17:00:45');
INSERT INTO `ucenter_department` VALUES ('1416322586521165825', '通信系', '1415963233737793538', '2021-07-17 17:03:13', '2021-07-17 17:03:13');
INSERT INTO `ucenter_department` VALUES ('1416322716632670209', '网安系', '1415963233737793538', '2021-07-17 17:03:44', '2021-07-17 17:03:44');
INSERT INTO `ucenter_department` VALUES ('1417105789180268545', '马克思主义学院', '0', '2021-07-19 20:55:23', '2021-07-19 20:55:23');

-- ----------------------------
-- Table structure for ucenter_lab_teacher
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_lab_teacher`;
CREATE TABLE `ucenter_lab_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `lab_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室id',
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教师id',
  `attend_time` date NOT NULL COMMENT '加入实验室时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_lab_teacher
-- ----------------------------
INSERT INTO `ucenter_lab_teacher` VALUES ('1', '1416770497566511105', '1416328503052595201', '2021-07-01', '2021-07-19 17:14:51', '2021-07-19 17:14:52');
INSERT INTO `ucenter_lab_teacher` VALUES ('1440216338814267394', 'string', 'string', '2021-09-21', '2021-09-21 15:28:28', '2021-09-21 15:28:28');
INSERT INTO `ucenter_lab_teacher` VALUES ('1440217219118985218', '1438762279641083905', '1417362102971879425', '2021-09-21', '2021-09-21 15:31:58', '2021-09-21 15:31:58');
INSERT INTO `ucenter_lab_teacher` VALUES ('1440254163127435265', '1438762279641083905', '1417304876563304449', '2021-09-21', '2021-09-21 17:58:46', '2021-09-21 17:58:46');
INSERT INTO `ucenter_lab_teacher` VALUES ('1440254444330352642', '1438762279641083905', '1417304876563304449', '2021-09-22', '2021-09-21 17:59:53', '2021-09-21 17:59:53');
INSERT INTO `ucenter_lab_teacher` VALUES ('213', '1416770497566511105', '1417304876563304449', '2021-07-20', '2021-07-20 10:07:13', '2021-07-20 10:07:18');
INSERT INTO `ucenter_lab_teacher` VALUES ('222', '1416770497566511105', '1417362102971879425', '2021-07-20', '2021-07-20 13:54:23', '2021-07-20 13:54:25');
INSERT INTO `ucenter_lab_teacher` VALUES ('3453', '1416770497566511105', '1417362917061120001', '2021-07-20', '2021-07-20 13:57:33', '2021-07-20 13:57:35');

-- ----------------------------
-- Table structure for ucenter_lab_teacher_details
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_lab_teacher_details`;
CREATE TABLE `ucenter_lab_teacher_details`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `lab_teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'lab-teacher表中的id',
  `achievement` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '成就',
  `sort` tinyint NOT NULL COMMENT '排序',
  `award_time` date NOT NULL COMMENT '成就时间',
  `is_deleted` tinyint NOT NULL COMMENT '逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_lab_teacher_details
-- ----------------------------
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417395333104484354', '1', '123312312', 2, '2021-07-20', 0, '2021-07-20 16:05:56', '2021-07-20 16:05:56');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417395645911482369', '1', '123123\n121341\nwerwe', 1, '2021-07-20', 0, '2021-07-20 16:07:10', '2021-07-20 16:38:15');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417404347343265793', '1', '1234lkdfjsdaofi\nsdfawe', 1, '2021-07-20', 0, '2021-07-20 16:41:45', '2021-07-20 16:41:45');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417407414591680513', '1', '123\n2314k\n23kljl\n123', 1, '2021-07-20', 0, '2021-07-20 16:53:56', '2021-07-20 16:53:56');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417408446159777793', '213', 'wumen \nsdkljfal\nwejklj', 1, '2021-07-20', 0, '2021-07-20 16:58:02', '2021-07-20 16:58:02');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417414983494254593', '1', '235日3', 2, '2021-07-21', 1, '2021-07-20 17:24:01', '2021-07-20 17:24:01');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417416410312531969', '1', '21234233', 1, '2021-07-24', 1, '2021-07-20 17:29:41', '2021-09-02 15:03:39');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417416457741721602', '1', '1234fsdgsd', 1, '2021-07-22', 0, '2021-07-20 17:29:52', '2021-07-20 17:29:52');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417418072758476801', '3453', '沃尔夫就', 1, '2021-07-19', 0, '2021-07-20 17:36:17', '2021-07-20 17:36:17');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417418178081644546', '3453', '发给沃尔夫问问是否是的是的是', 1, '2021-07-22', 0, '2021-07-20 17:36:42', '2021-07-20 17:36:42');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1417418223329796097', '3453', ' 闻风丧胆 违法未', 1, '2021-07-20', 0, '2021-07-20 17:36:53', '2021-07-20 17:36:53');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1440238489621606401', '1440217219118985218', '111', 1, '2021-09-21', 0, '2021-09-21 16:56:29', '2021-09-21 16:56:29');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1440249868806107137', '1440217219118985218', '11', 1, '2021-09-21', 0, '2021-09-21 17:41:42', '2021-09-21 17:41:42');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('1440254215422017537', '1440254163127435265', '121', 1, '2021-09-22', 1, '2021-09-21 17:58:58', '2021-09-21 17:58:58');
INSERT INTO `ucenter_lab_teacher_details` VALUES ('324132', '222', '3412fwsdfasdf', 1, '2021-07-20', 0, '2021-07-20 13:55:16', '2021-07-20 13:55:18');

-- ----------------------------
-- Table structure for ucenter_laboratory
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_laboratory`;
CREATE TABLE `ucenter_laboratory`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室',
  `parent_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '所处于学院',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室头像',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室位置',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室名字',
  `teacher_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责实验室的老师',
  `teacher_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '负责老师的电话',
  `details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室介绍',
  `rules` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实验室规章',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_laboratory
-- ----------------------------
INSERT INTO `ucenter_laboratory` VALUES ('1416770497566511105', '1415963233737793538', 'https://guli-edu-100701.oss-cn-qingdao.aliyuncs.com/default/defaultAvatar.png', '信工楼201', '201实验室', '高保忠', '12345678901', '<p>iOS移动开发实验室，成立于2017年，面积63. 44平方米，含有19个移动开发工作位，配备2套移动开发教师端平台、11套移动开发学生端平台、3台膝上移动开发测试平台、4套掌上移动开发测试平台和1套展示系统。该实验室主要承担计算机科学与技术、通信工程和物联网工程三个专业中对i0S移动开发有浓厚兴趣的本专科生创新创业训练及实训开发类实验实践训练任务。</p>', '<ol>\n<li>实验室规章</li>\n<li>实验室规章</li>\n<li>实验室规章</li>\n<li>实验室规章</li>\n<li>实验室规章</li>\n<li>实验室规章</li>\n<li>实验室规章</li>\n</ol>', '2021-07-18 22:43:03', '2021-07-18 22:43:03');
INSERT INTO `ucenter_laboratory` VALUES ('1417049429796724738', '1415963233737793538', 'https://guli-edu-100701.oss-cn-qingdao.aliyuncs.com/default/defaultAvatar.png', '信工楼217', '217实验室', '张鸿文', '13280994621', '<p>人工智能实验室</p>', '<ol>\n<li>不允许在实验室内吃饭</li>\n<li>不允许闲聊</li>\n</ol>', '2021-07-19 17:11:26', '2021-09-23 13:25:42');
INSERT INTO `ucenter_laboratory` VALUES ('1438762279641083905', '1437049639269105666', 'https://guli-edu-100701.oss-cn-qingdao.aliyuncs.com/default/defaultAvatar.png', '马克思主义学院楼101', '101实验室', '晏祖强', '17513579866', '<p>测试</p>', '<p>测试</p>', '2021-09-17 15:10:33', '2021-09-23 13:25:29');
INSERT INTO `ucenter_laboratory` VALUES ('1445007094330523649', '1415963233737793538', 'https://guli-edu-100701.oss-cn-qingdao.aliyuncs.com/default/defaultAvatar.png', '信工楼207', '207实验室', '魏晓超', '19854198767', '<p>1</p>', '<p>1</p>', '2021-10-04 20:45:13', '2021-10-04 20:45:13');

-- ----------------------------
-- Table structure for ucenter_major
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_major`;
CREATE TABLE `ucenter_major`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `academy_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学院ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '专业名字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_major
-- ----------------------------
INSERT INTO `ucenter_major` VALUES ('1415963287340998657', '1415963233737793538', '计算机科学与技术(非师范类)', '2021-07-16 17:15:29', '2021-07-16 17:15:29');
INSERT INTO `ucenter_major` VALUES ('1440909418601205761', '1415963233737793538', '计算机科学与技术', '2021-09-23 13:22:31', '2021-09-23 13:22:31');
INSERT INTO `ucenter_major` VALUES ('1440909459495669762', '1415963233737793538', '网络空间安全', '2021-09-23 13:22:41', '2021-09-23 13:22:41');
INSERT INTO `ucenter_major` VALUES ('1440909491686952961', '1415963233737793538', '物联网工程', '2021-09-23 13:22:48', '2021-09-23 13:22:48');
INSERT INTO `ucenter_major` VALUES ('1440946682395971585', '1415963233737793538', '通信工程', '2021-09-23 15:50:35', '2021-09-23 15:50:35');

-- ----------------------------
-- Table structure for ucenter_student
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_student`;
CREATE TABLE `ucenter_student`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `wx_openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '微信openid',
  `number` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
  `academy_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学院ID',
  `major_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '专业ID',
  `clazz_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '班级ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '电话',
  `qq` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'qq',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '邮箱',
  `disabled` tinyint(1) NULL DEFAULT 0 COMMENT '是否禁用，0：不禁用，1：禁用',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gender` tinyint NULL DEFAULT 2 COMMENT '0 女 1男 2保密',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE COMMENT '学号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_student
-- ----------------------------
INSERT INTO `ucenter_student` VALUES ('1416319900614688769', 'o5VIX5WPibdHPnKbxS79gbstIAYE', '201911010223', '123123', '王晨晨', '1415963233737793538', '1415963287340998657', '1415963577561669634', '13280994621', '1451469248', '', 0, 0, '2021-10-04 20:44:21', '2021-07-17 16:52:32', 1);
INSERT INTO `ucenter_student` VALUES ('143212312142141', '', '11', 'c4ca4238a0b923820dcc509a6f75849b', '晏祖强', '1415963233737793538', '1415963287340998657', '1415963577561669634', '1', '1', '1', 0, 0, '2021-10-04 20:29:55', '2021-10-04 17:27:01', 2);
INSERT INTO `ucenter_student` VALUES ('1438415603348910081', 'o5VIX5aY3R4435T46Rm4BeM_sRpQ', '1', 'c4ca4238a0b923820dcc509a6f75849b', '赵运来', '1415963233737793538', '1415963287340998657', '1415963577561669634', '1111', '1111', '1111', 0, 0, '2021-10-14 14:04:49', '2021-09-16 16:12:59', 1);
INSERT INTO `ucenter_student` VALUES ('1445002759685111809', '', '202011000103', 'c4ca4238a0b923820dcc509a6f75849b', '吕熙', '1415963233737793538', '1440909459495669762', '1440909591691743234', '17513579867', '2751962232', '2751962232@qq.com', 0, 1, '2021-10-04 20:27:59', '2021-10-04 20:27:59', 2);
INSERT INTO `ucenter_student` VALUES ('1445003411295404033', '', '202011000121', 'c4ca4238a0b923820dcc509a6f75849b', '宋浩洋', '1415963233737793538', '1440909418601205761', '1440909554068836354', '175135798', '', '2751962232@qq.com', 0, 1, '2021-10-04 20:30:35', '2021-10-04 20:30:35', 2);
INSERT INTO `ucenter_student` VALUES ('1445003596163547137', '', '202011000105', 'c4ca4238a0b923820dcc509a6f75849b', '盛宏超', '1415963233737793538', '1415963287340998657', '1433310332570107906', '19854196767', '', '2751962232@qq.com', 0, 0, '2021-10-04 20:31:19', '2021-10-04 20:31:19', 2);
INSERT INTO `ucenter_student` VALUES ('1566407113020637185', 'string', 'num1', 'string', '测试1', 'string', 'string', 'string', 'string', 'string', 'string', 0, 0, '2022-09-04 20:45:33', '2022-09-04 20:45:33', 0);
INSERT INTO `ucenter_student` VALUES ('1566407176551759874', 'string', 'num2', 'string', '测试2', 'string', 'string', 'string', 'string', 'string', 'string', 0, 0, '2022-09-04 20:45:48', '2022-09-04 20:45:48', 0);
INSERT INTO `ucenter_student` VALUES ('1566407218285084673', 'string', 'num3', 'string', '测试3', 'string', 'string', 'string', 'string', 'string', 'string', 0, 0, '2022-09-04 20:45:58', '2022-09-04 20:45:58', 0);
INSERT INTO `ucenter_student` VALUES ('1566407258332299265', 'string', 'num4', 'string', '测试4', 'string', 'string', 'string', 'string', 'string', 'string', 0, 0, '2022-09-04 20:46:07', '2022-09-04 20:46:07', 0);
INSERT INTO `ucenter_student` VALUES ('21112', '', '12', 'c4ca4238a0b923820dcc509a6f75849b', '李筱然', '1415963233737793538', '1415963287340998657', '1415963577561669634', '111', '111', '11', 0, 0, '2021-10-04 20:28:53', '2021-10-03 21:38:51', 2);

-- ----------------------------
-- Table structure for ucenter_teacher
-- ----------------------------
DROP TABLE IF EXISTS `ucenter_teacher`;
CREATE TABLE `ucenter_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ID',
  `wx_openid` char(28) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信openid',
  `number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教工号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名字',
  `department_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '电话',
  `qq` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `disabled` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用，0：不禁用，1：禁用',
  `is_deleted` tinyint NOT NULL DEFAULT 0 COMMENT '是否逻辑删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` date NOT NULL COMMENT '修改时间',
  `gender` tinyint NOT NULL DEFAULT 2 COMMENT '0女 1男 2保密',
  `intro` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '教师介绍',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `number`(`number`) USING BTREE COMMENT '工号唯一'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ucenter_teacher
-- ----------------------------
INSERT INTO `ucenter_teacher` VALUES ('1416328503052595201', 'o5VIX5WPibdHPnKbxS79gbstIAYA', '123', '123123', '张三', '1416321965646725121', '123', '123', '123', 0, 0, '2021-07-17 17:26:43', '2021-07-17', 1, '');
INSERT INTO `ucenter_teacher` VALUES ('1417304876563304449', '', '2020110001', '123456', '李四', '1416321965646725121', '17513652762', '3242342525', '2751962232@qq.com', 0, 0, '2021-07-20 10:06:29', '2021-07-20', 2, NULL);
INSERT INTO `ucenter_teacher` VALUES ('1417362102971879425', '', '12321312312', '123456', '王五', '1416322586521165825', '12313123213', NULL, NULL, 1, 0, '2021-07-20 13:53:53', '2021-07-20', 2, NULL);
INSERT INTO `ucenter_teacher` VALUES ('1417362917061120001', '', '23423423423', '123456', 'test', '1416322716632670209', '123123123', '12313123131', '1231212321', 1, 0, '2021-07-20 13:57:07', '2021-07-20', 2, NULL);

-- ----------------------------
-- View structure for ucenter_user
-- ----------------------------
DROP VIEW IF EXISTS `ucenter_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `ucenter_user` AS select `s`.`id` AS `id`,`s`.`number` AS `number`,`s`.`name` AS `name`,`s`.`gender` AS `gender`,`s`.`phone` AS `phone`,`s`.`email` AS `email`,`s`.`is_deleted` AS `is_deleted`,`s`.`disabled` AS `disabled`,`s`.`create_time` AS `create_time`,`s`.`update_time` AS `update_time` from `ucenter_student` `s` union select `t`.`id` AS `id`,`t`.`number` AS `number`,`t`.`name` AS `name`,`t`.`gender` AS `gender`,`t`.`phone` AS `phone`,`t`.`email` AS `email`,`t`.`is_deleted` AS `is_deleted`,`t`.`disabled` AS `disabled`,`t`.`create_time` AS `create_time`,`t`.`update_time` AS `update_time` from `ucenter_teacher` `t`;

SET FOREIGN_KEY_CHECKS = 1;
