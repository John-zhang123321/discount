/*
Navicat MySQL Data Transfer

Source Server         : my122.51.126.115
Source Server Version : 50726
Source Host           : 122.51.126.115:3306
Source Database       : discount-power-dev

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-07-22 09:40:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` bigint(64) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `pid` varchar(50) NOT NULL COMMENT '上级部门',
  `level` int(11) DEFAULT NULL,
  `create_user` bigint(64) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_user` bigint(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', 'eladmin', '0', '1', null, '2019-03-25 09:14:05', null, '2019-08-08 11:22:04');
INSERT INTO `dept` VALUES ('2', '研发部', '7', '3', null, '2019-03-25 09:15:32', null, '2019-08-08 11:22:13');
INSERT INTO `dept` VALUES ('7', '华南分部', '1', '333', null, '2019-03-25 11:04:50', null, '2019-11-02 21:28:01');
INSERT INTO `dept` VALUES ('9', '财务部', '7', '3', null, '2019-03-25 11:05:34', null, '2019-08-08 11:22:10');
INSERT INTO `dept` VALUES ('10', '行政部', '8', '3', null, '2019-03-25 11:05:58', null, '2019-08-08 11:22:15');
INSERT INTO `dept` VALUES ('11', '人事部3', '8', '3', null, '2019-03-25 11:07:58', null, '2019-11-02 21:27:40');
INSERT INTO `dept` VALUES ('1190626683653328896', '23', '23', '23', null, '2019-11-02 21:48:17', null, null);

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint(64) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `sort` int(20) DEFAULT NULL,
  `dept_id` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` bigint(64) DEFAULT NULL,
  `update_user` bigint(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKmvhj0rogastlctflsxf1d6k3i` (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job
-- ----------------------------
INSERT INTO `job` VALUES ('2', '董事长秘书', '2', '1', '2019-03-29 14:01:30', null, null, null);
INSERT INTO `job` VALUES ('8', '人事专员', '3', '11', '2019-03-29 14:52:28', null, null, null);
INSERT INTO `job` VALUES ('10', '产品经理', '4', '2', '2019-03-29 14:55:51', null, null, null);
INSERT INTO `job` VALUES ('11', '全栈开发', '6', '2', '2019-03-31 13:39:30', null, null, null);
INSERT INTO `job` VALUES ('12', '软件测试', '5', '2', '2019-03-31 13:39:43', null, null, null);
INSERT INTO `job` VALUES ('19', '董事长', '1', '1', '2019-03-31 14:58:15', null, null, null);

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `pid` varchar(50) DEFAULT NULL COMMENT '所属上级',
  `name` varchar(20) DEFAULT NULL COMMENT '组织名称',
  `description` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `address` varchar(50) DEFAULT NULL COMMENT '机构所在地',
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Records of organization
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `pid` bigint(50) NOT NULL COMMENT '上级权限',
  `component` varchar(50) DEFAULT NULL COMMENT '组件',
  `type` tinyint(1) unsigned DEFAULT NULL COMMENT '类型0.目录 1.菜单2.按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `path` varchar(50) DEFAULT NULL COMMENT '链接地址',
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', '系统管理', 'SYSTEM', '0', 'Layout', '0', '1', 'system', '/system', null, '2019-08-06 17:10:07', null, null);
INSERT INTO `permission` VALUES ('2', '管理员管理', 'USER_ALL', '1', 'system/user/manager/index', '1', '20', 'peoples', 'manager', null, '2019-08-06 17:10:33', '1', '2019-08-08 10:35:09');
INSERT INTO `permission` VALUES ('3', '用户查询', 'USER_SELECT', '2', null, '2', '200', null, null, null, '2019-08-06 17:10:36', '1', '2019-08-08 10:39:41');
INSERT INTO `permission` VALUES ('4', '用户创建', 'USER_CREATE', '2', null, '2', '201', null, null, null, '2019-08-06 17:10:40', '1', '2019-08-08 10:39:47');
INSERT INTO `permission` VALUES ('5', '用户编辑', 'USER_EDIT', '2', null, '2', '202', null, null, null, '2019-08-06 17:10:43', '1', '2019-08-08 10:39:54');
INSERT INTO `permission` VALUES ('6', '用户删除', 'USER_DELETE', '2', null, '2', '203', null, null, null, '2019-08-06 17:10:46', '1', '2019-08-08 10:40:00');
INSERT INTO `permission` VALUES ('7', '角色管理', 'ROLE_ALL', '1', 'system/role/index', '1', '30', 'role', 'role', null, '2019-08-06 17:10:49', '1', '2019-08-08 10:35:21');
INSERT INTO `permission` VALUES ('8', '角色查询', 'ROLE_SELECT', '7', null, '2', '302', null, null, null, '2019-08-06 17:10:53', '1', '2019-08-08 10:40:40');
INSERT INTO `permission` VALUES ('9', '角色创建', 'ROLE_CREATE', '7', null, '2', '303', null, null, null, '2019-08-06 17:10:56', '1', '2019-08-08 10:40:48');
INSERT INTO `permission` VALUES ('10', '角色编辑', 'ROLE_EDIT', '7', null, '2', '300', null, null, null, '2019-08-06 17:10:10', '1', '2019-08-08 10:40:09');
INSERT INTO `permission` VALUES ('11', '角色删除', 'ROLE_DELETE', '7', null, '2', '301', null, null, null, '2019-08-06 17:10:13', '1', '2019-08-08 10:40:33');
INSERT INTO `permission` VALUES ('12', '权限管理', 'PERMISSION_ALL', '1', 'system/permission/index', '1', '10', 'permission', 'permission', null, '2019-08-06 17:10:15', '1', '2019-08-08 10:34:46');
INSERT INTO `permission` VALUES ('13', '权限查询', 'PERMISSION_SELECT', '12', null, '2', '100', null, null, null, '2019-08-06 17:10:20', '1', '2019-08-08 10:37:34');
INSERT INTO `permission` VALUES ('14', '权限创建', 'PERMISSION_CREATE', '12', null, '2', '101', null, null, null, '2019-08-06 17:10:24', '1', '2019-08-08 10:37:43');
INSERT INTO `permission` VALUES ('15', '权限编辑', 'PERMISSION_EDIT', '12', null, '2', '102', null, null, null, '2019-08-06 17:10:27', '1', '2019-08-08 10:37:48');
INSERT INTO `permission` VALUES ('16', '权限删除', 'PERMISSION_DELETE', '12', null, '2', '103', null, null, null, '2019-08-06 17:10:31', '1', '2019-08-08 10:37:55');
INSERT INTO `permission` VALUES ('17', '部门管理', 'DEPT_ALL', '1', 'dept/index', '1', '40', 'dept', 'dept', '1', '2019-08-08 09:29:02', '1', '2019-11-01 21:58:21');
INSERT INTO `permission` VALUES ('20', '用户管理', 'CONTENT', '0', 'Layout', '0', '3', 'peoples', '/content', '1', '2019-08-21 16:18:38', '1', '2019-08-21 16:25:03');
INSERT INTO `permission` VALUES ('21', '分类管理', 'CATEGORY', '20', 'category/index', '1', '44', 'category', '/category', '1', '2019-08-21 16:20:33', null, '2019-11-21 10:31:49');
INSERT INTO `permission` VALUES ('22', '操作日志', 'OPERATOR_LOG', '19', 'monitor/log/index', '1', '50', 'log', 'logs', '1', '2019-08-10 19:55:24', null, null);
INSERT INTO `permission` VALUES ('23', '系统缓存', 'SYSTEM_CACHE', '19', 'monitor/redis/index', '1', '500', 'redis', 'redis', '1', '2019-08-10 20:21:50', null, null);
INSERT INTO `permission` VALUES ('24', '部门查询', 'DEPT_SELECT', '17', null, '2', '400', null, null, '1', '2019-08-08 09:30:31', '1', '2019-08-08 10:40:55');
INSERT INTO `permission` VALUES ('25', '部门创建', 'DEPT_CREATE', '17', null, '2', '401', null, null, '1', '2019-08-08 09:31:15', '1', '2019-08-08 10:41:01');
INSERT INTO `permission` VALUES ('26', '部门编辑', 'DEPT_EDIT', '17', null, '2', '402', null, null, '1', '2019-08-08 09:31:50', '1', '2019-08-08 10:41:06');
INSERT INTO `permission` VALUES ('27', '部门删除', 'DEPT_DELETE', '17', null, '2', '403', null, null, '1', '2019-08-08 09:32:13', '1', '2019-08-08 10:41:13');
INSERT INTO `permission` VALUES ('28', '岗位管理', 'JOB_ALL', '1', 'system/job/index', '1', '50', 'add', 'job', '1', '2019-08-08 17:24:51', null, null);
INSERT INTO `permission` VALUES ('29', '岗位创建', 'JOB_CREATE', '18', null, '2', '500', null, null, '1', '2019-08-08 17:25:41', null, null);
INSERT INTO `permission` VALUES ('30', '岗位删除', 'JOB_DELETE', '18', null, '2', '501', null, null, '1', '2019-08-08 17:26:16', null, null);
INSERT INTO `permission` VALUES ('31', '岗位编辑', 'JOB_EDIT', '18', null, '2', '502', null, null, '1', '2019-08-08 17:26:47', null, null);
INSERT INTO `permission` VALUES ('32', '岗位查询', 'JOB_SELECT', '18', null, '2', '503', null, null, '1', '2019-08-08 17:27:10', null, null);
INSERT INTO `permission` VALUES ('33', '系统监控', 'MONITOR', '0', 'Layout', '0', '2', 'monitor', '/monitor', '1', '2019-08-10 19:41:41', '1', '2019-08-10 20:22:26');
INSERT INTO `permission` VALUES ('34', '分类创建', 'CATEGORY_CREATE', '21', '', '2', '33', '', '', '1', '2019-08-21 16:23:54', null, null);
INSERT INTO `permission` VALUES ('35', '分类查询', 'CATEGORY_SELECT', '21', '', '2', '0', '', '', '1', '2019-08-21 16:41:04', null, null);
INSERT INTO `permission` VALUES ('36', '分类编辑', 'CATEGORY_EDIT', '21', '', '2', '0', '', '', '1', '2019-08-21 16:41:46', null, null);
INSERT INTO `permission` VALUES ('37', '分类删除', 'CATEGORY_DELETE', '21', '', '2', '0', '', '', '1', '2019-08-21 16:42:05', '1', '2019-08-21 16:42:19');
INSERT INTO `permission` VALUES ('38', '日志查看', 'OPERATOR_SELECT', '22', '', '2', '0', '', '', '1', '2019-08-22 13:01:39', null, null);
INSERT INTO `permission` VALUES ('39', '日志删除', 'OPERATOR_DELETE', '22', '', '2', '0', '', '', '1', '2019-08-22 13:02:03', null, null);
INSERT INTO `permission` VALUES ('40', '缓存查看', 'CACHE_SELECT', '23', '', '2', '0', '', '', '1', '2019-08-22 13:03:00', null, null);
INSERT INTO `permission` VALUES ('41', '缓存删除', 'CACHE_DELETE', '23', '', '2', '0', '', '', '1', '2019-08-22 13:03:22', null, null);
INSERT INTO `permission` VALUES ('42', '人员管理', 'MERCHANT_ALL', '1', 'system/user/merchant/index', '1', '20', 'peoples', 'merchant', null, '2019-08-06 17:10:33', '1', '2019-08-08 10:35:09');
INSERT INTO `permission` VALUES ('1184401004800643072', '系统工具', 'TOOLS', '0', 'Layout', '0', '0', 'sys-tools', '/tools', null, '2019-10-16 17:29:40', null, null);
INSERT INTO `permission` VALUES ('1188743720502169600', '定时任务', 'CRON', '1184401004800643072', 'system/timing/index', '1', '0', 'timing', 'CRON', null, '2019-10-28 17:06:03', null, null);
INSERT INTO `permission` VALUES ('1188744612592881664', '代码生成', 'CODE_ALL', '1184401004800643072', 'generator/index', '1', '0', 'dev', 'create', null, '2019-10-28 17:09:36', null, '2019-11-02 22:00:48');
INSERT INTO `permission` VALUES ('1188745433351065600', '查询', 'CODE_QUERY', '1188744612592881664', '', '2', '0', '', '', null, '2019-10-28 17:12:52', null, '2019-11-02 22:06:07');
INSERT INTO `permission` VALUES ('1188745538951057408', '新增', 'CRON_CREATE', '1188743720502169600', '', '2', '0', '', '', null, '2019-10-28 17:13:17', null, null);
INSERT INTO `permission` VALUES ('1189735218911776768', '代码配置器', 'GENCONFIG_ALL', '1184401004800643072', 'generator/config/index', '1', '0', 'develop', 'config', null, '2019-10-31 10:45:55', null, '2019-11-02 22:01:18');
INSERT INTO `permission` VALUES ('1189737256424968192', '新增', 'GENCONFIG_CREATE', '1189735218911776768', '', '2', '0', '', '', null, '2019-10-31 10:54:01', null, null);
INSERT INTO `permission` VALUES ('1190630467708260352', '修改', 'GENCONFIG_UPDATE', '1189735218911776768', '', '2', '0', '', '', null, '2019-11-02 22:03:19', null, null);
INSERT INTO `permission` VALUES ('1190630921611644928', '生成代码', 'CODE_UPDATE', '1188744612592881664', '', '2', '0', '', '', null, '2019-11-02 22:05:07', null, null);
INSERT INTO `permission` VALUES ('1190810362245681152', '邮件工具', 'MAIL', '1184401004800643072', 'tools/email/index', '1', '0', 'Mail', 'mail', null, '2019-11-03 09:58:09', null, '2019-11-03 10:12:21');
INSERT INTO `permission` VALUES ('1197334177717030912', '店铺管理', 'SHOP', '20', 'shop/index', '1', '0', 'shop', '/shop', null, '2019-11-21 10:01:28', null, null);
INSERT INTO `permission` VALUES ('1197334177930940416', '店铺创建', 'SHOP_CREATE', '1197334177717030912', null, '2', '0', null, null, null, '2019-11-21 10:01:28', null, null);
INSERT INTO `permission` VALUES ('1197334178069352448', '店铺删除', 'SHOP_DELETE', '1197334177717030912', null, '2', '0', null, null, null, '2019-11-21 10:01:28', null, null);
INSERT INTO `permission` VALUES ('1197334178161627136', '店铺更新', 'SHOP_EDIT', '1197334177717030912', 'shop_edit/index', '2', '0', null, 'shop_edit', null, '2019-11-21 10:01:28', null, '2020-01-27 16:17:25');
INSERT INTO `permission` VALUES ('1197334178266484736', '店铺查询', 'SHOP_QUERY', '1197334177717030912', null, '2', '0', null, null, null, '2019-11-21 10:01:28', null, null);
INSERT INTO `permission` VALUES ('1201012213129809920', '反馈管理', 'FEEDBACK', '1', 'feedback/index', '1', '0', 'feedback', 'feedback', null, '2019-12-01 13:36:40', null, null);
INSERT INTO `permission` VALUES ('1201012213649903616', '反馈创建', 'FEEDBACK_CREATE', '1201012213129809920', null, '2', '0', null, null, null, '2019-12-01 13:36:40', null, null);
INSERT INTO `permission` VALUES ('1201012215428288512', '反馈删除', 'FEEDBACK_DELETE', '1201012213129809920', null, '2', '0', null, null, null, '2019-12-01 13:36:41', null, null);
INSERT INTO `permission` VALUES ('1201012216057434112', '反馈更新', 'FEEDBACK_UPDATE', '1201012213129809920', null, '2', '0', null, null, null, '2019-12-01 13:36:41', null, null);
INSERT INTO `permission` VALUES ('1201012216422338560', '反馈查询', 'FEEDBACK_QUERY', '1201012213129809920', null, '2', '0', null, null, null, '2019-12-01 13:36:41', null, null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `data_scope` varchar(255) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员5', '88888888', '', '0', '1', '2019-07-28 21:28:22', null, '2019-10-28 09:51:20');
INSERT INTO `role` VALUES ('1188731314648518656', '3333', '32323', null, null, null, '2019-10-28 16:16:46', null, null);
INSERT INTO `role` VALUES ('1195241753700077568', '444', '4444', null, null, null, '2019-11-15 15:26:55', null, null);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` bigint(64) NOT NULL,
  `role_id` bigint(50) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(50) DEFAULT NULL COMMENT '权限id',
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `perIndex` (`permission_id`) USING BTREE,
  KEY `roleIndex` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1195272011270721536', '1195241753700077568', '1', null, '2019-11-15 17:27:09', null, null);
INSERT INTO `role_permission` VALUES ('1195272011342024704', '1195241753700077568', '2', null, '2019-11-15 17:27:09', null, null);
INSERT INTO `role_permission` VALUES ('1195272011350413312', '1195241753700077568', '3', null, '2019-11-15 17:27:09', null, null);
INSERT INTO `role_permission` VALUES ('1195272011362996224', '1195241753700077568', '5', null, '2019-11-15 17:27:09', null, null);
INSERT INTO `role_permission` VALUES ('1244534406912282624', '1', '1', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407386238976', '1', '2', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407465930752', '1', '3', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407482707968', '1', '4', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407503679488', '1', '5', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407520456704', '1', '6', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407562399744', '1', '7', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407587565568', '1', '8', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407684034560', '1', '9', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407797280768', '1', '10', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407889555456', '1', '11', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407906332672', '1', '12', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407927304192', '1', '13', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407948275712', '1', '14', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407965052928', '1', '15', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534407990218752', '1', '16', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408023773184', '1', '17', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408044744704', '1', '24', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408082493440', '1', '25', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408103464960', '1', '26', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408149602304', '1', '27', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408178962432', '1', '1201012213129809920', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408304791552', '1', '1201012213649903616', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408439009280', '1', '1201012215428288512', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408464175104', '1', '1201012216057434112', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408476758016', '1', '1201012216422338560', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408497729536', '1', '20', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408527089664', '1', '21', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408543866880', '1', '34', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408560644096', '1', '35', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408577421312', '1', '36', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408606781440', '1', '37', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408623558656', '1', '1197334177717030912', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408661307392', '1', '1197334177930940416', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408690667520', '1', '1197334178069352448', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408699056128', '1', '1197334178161627136', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408799719424', '1', '1197334178266484736', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534408988463104', '1', '33', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409152040960', '1', '1184401004800643072', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409290452992', '1', '1188743720502169600', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409311424512', '1', '1188745538951057408', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409328201728', '1', '1188744612592881664', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409399504896', '1', '1188745433351065600', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409437253632', '1', '1190630921611644928', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409449836544', '1', '1189735218911776768', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409483390976', '1', '1189737256424968192', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409500168192', '1', '1190630467708260352', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1244534409516945408', '1', '1190810362245681152', null, '2020-03-30 15:58:20', null, null);
INSERT INTO `role_permission` VALUES ('1279326596750249984', '1188731314648518656', '1', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596771221504', '1188731314648518656', '7', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596808970240', '1188731314648518656', '12', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596880273408', '1188731314648518656', '17', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596909633536', '1188731314648518656', '8', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596926410752', '1188731314648518656', '13', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326596980936704', '1188731314648518656', '24', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597073211392', '1188731314648518656', '28', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597182263296', '1188731314648518656', '42', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597228400640', '1188731314648518656', '1201012213129809920', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597278732288', '1188731314648518656', '1201012213649903616', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597316481024', '1188731314648518656', '1201012215428288512', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597350035456', '1188731314648518656', '1201012216057434112', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597371006976', '1188731314648518656', '1201012216422338560', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597471670272', '1188731314648518656', '20', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597488447488', '1188731314648518656', '21', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597526196224', '1188731314648518656', '34', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597547167744', '1188731314648518656', '35', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597563944960', '1188731314648518656', '36', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597589110784', '1188731314648518656', '37', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597610082304', '1188731314648518656', '1197334177717030912', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597631053824', '1188731314648518656', '1197334177930940416', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597664608256', '1188731314648518656', '1197334178069352448', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597693968384', '1188731314648518656', '1197334178161627136', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597723328512', '1188731314648518656', '1197334178266484736', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597744300032', '1188731314648518656', '1184401004800643072', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597782048768', '1188731314648518656', '1188743720502169600', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597798825984', '1188731314648518656', '1188745538951057408', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597828186112', '1188731314648518656', '1188744612592881664', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597844963328', '1188731314648518656', '1188745433351065600', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597878517760', '1188731314648518656', '1190630921611644928', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597891100672', '1188731314648518656', '1189735218911776768', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597907877888', '1188731314648518656', '1189737256424968192', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597920460800', '1188731314648518656', '1190630467708260352', null, '2020-07-04 16:10:04', null, null);
INSERT INTO `role_permission` VALUES ('1279326597962403840', '1188731314648518656', '1190810362245681152', null, '2020-07-04 16:10:04', null, null);

-- ----------------------------
-- Table structure for roles_depts
-- ----------------------------
DROP TABLE IF EXISTS `roles_depts`;
CREATE TABLE `roles_depts` (
  `role_id` bigint(64) NOT NULL,
  `dept_id` bigint(64) NOT NULL,
  PRIMARY KEY (`role_id`,`dept_id`),
  KEY `FK7qg6itn5ajdoa9h9o78v9ksur` (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles_depts
-- ----------------------------
INSERT INTO `roles_depts` VALUES ('2', '5');
INSERT INTO `roles_depts` VALUES ('4', '6');
INSERT INTO `roles_depts` VALUES ('4', '7');
INSERT INTO `roles_depts` VALUES ('2', '8');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `user_id` bigint(50) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(50) DEFAULT NULL COMMENT '角色编号',
  `type` varchar(10) DEFAULT '',
  `create_user` bigint(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(64) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `userIdIndex` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('123', '1', '1', '0', null, null, null, null);
INSERT INTO `user_role` VALUES ('1194484594389422080', '1237227140034990080', '1188731314648518656', '', null, '2019-11-13 13:18:14', null, null);
INSERT INTO `user_role` VALUES ('1194489304924164096', '1194489303787507712', '1', '', null, '2019-11-13 13:36:57', null, null);
INSERT INTO `user_role` VALUES ('1194489399308587008', '1194489399241478144', '1195241753700077568', '', null, '2019-11-13 13:37:20', null, null);
INSERT INTO `user_role` VALUES ('1194489512802258944', '1194489512747732992', '1', '', null, '2019-11-13 13:37:47', null, null);
INSERT INTO `user_role` VALUES ('1237227141368778752', null, '1188731314648518656', '', null, '2020-03-10 12:01:52', null, null);
INSERT INTO `user_role` VALUES ('1279323349440401408', null, '1188731314648518656', '', null, '2020-07-04 15:57:10', null, null);
