/*
Navicat MySQL Data Transfer

Source Server         : my122.51.126.115
Source Server Version : 50726
Source Host           : 122.51.126.115:3306
Source Database       : discount-setup-dev

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-07-22 09:40:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `url` varchar(500) DEFAULT NULL COMMENT '图片的url',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_user` bigint(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1197342622679699456', '服饰', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/817d8e58fb5d4bd686cfbcaa05c5f65a.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937089%3B1895556289%26q-key-time%3D1579937089%3B1895556289%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D6bfe5216c5986aba540880213e49ba88a255de66', '服饰', null, '2019-11-21 10:35:01', null, '2020-01-25 15:24:50');
INSERT INTO `category` VALUES ('1197354788195536896', '裤子', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/0debeb8d1da1488cb48158e57fcf465a.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937111%3B1895556311%26q-key-time%3D1579937111%3B1895556311%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D819759003876cba1c57161db04ea1b68f47946a7', '裤子', null, '2019-11-21 11:23:22', null, '2020-01-25 15:25:11');
INSERT INTO `category` VALUES ('1197354994601431040', '鞋类', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/a761a5ac82884242bba0ee7ad0cc8cb4.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579936857%3B1895556057%26q-key-time%3D1579936857%3B1895556057%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D77aa1e843d14b24067080c2bd84e9065e3e5bea2', '鞋类', null, '2019-11-21 11:24:11', null, '2020-01-25 15:20:57');
INSERT INTO `category` VALUES ('1220972349902426112', '水果', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/2ed1897f8adb4b6dad54f45f71cb4ee1.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937467%3B1895556667%26q-key-time%3D1579937467%3B1895556667%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D6ad580110f52c30e0eccb5567566fdf7b07d9337', '水果', null, '2020-01-25 15:31:07', null, null);
INSERT INTO `category` VALUES ('1220973285244801024', '美食', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/e3e73a51abae4376aac28fb36a77753a.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937690%3B1895556890%26q-key-time%3D1579937690%3B1895556890%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Da092df5f63dff124cc53fa058255343d6669c680', '美食', null, '2020-01-25 15:34:50', null, null);
INSERT INTO `category` VALUES ('1220973769821130752', '箱包', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/628ec54e7b3547f7a830f837c213de98.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937805%3B1895557005%26q-key-time%3D1579937805%3B1895557005%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Db2089a09103b1b752df1a532fb54dbc4cd5e0533', '箱包', null, '2020-01-25 15:36:46', null, null);
INSERT INTO `category` VALUES ('1220974501731373056', '游玩', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/a999d0e40b814fc4ab253f26406df6f3.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579937980%3B1895557180%26q-key-time%3D1579937980%3B1895557180%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Dcb4fde3e4ab46775a7eaf51c979a15bd4421e5f3', '游玩', null, '2020-01-25 15:39:40', null, null);
INSERT INTO `category` VALUES ('1220975501733138432', '住宿', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-01-25/2f0d8d895f5e4dc6874adfeeb97a48d3.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1579938218%3B1895557418%26q-key-time%3D1579938218%3B1895557418%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3Dd40e199769cad5e46107a956ad25bcd0c3bae71a', '住宿', null, '2020-01-25 15:43:39', null, '2020-03-10 12:14:30');

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `user_id` bigint(50) DEFAULT NULL COMMENT '收藏人',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '店铺id',
  `create_user` bigint(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of collection
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `user_id` bigint(10) DEFAULT NULL COMMENT '所属用户id',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '店铺id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '描述',
  `original_price_max` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '原价最高值',
  `original_price_min` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '原价最低值',
  `present_price_max` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '现价最高值',
  `present_price_min` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '现价最低值',
  `start_time` datetime DEFAULT NULL COMMENT '折扣开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '折扣结束时间',
  `sale` int(11) DEFAULT '0' COMMENT '出售数',
  `love` int(11) DEFAULT '0' COMMENT '点赞',
  `proportion` int(11) unsigned DEFAULT '0' COMMENT '最高随机优惠比例',
  `create_user` bigint(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for goods_img
-- ----------------------------
DROP TABLE IF EXISTS `goods_img`;
CREATE TABLE `goods_img` (
  `id` bigint(11) NOT NULL COMMENT '编号',
  `goods_id` bigint(50) DEFAULT NULL COMMENT '商品id',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of goods_img
-- ----------------------------

-- ----------------------------
-- Table structure for hot_search
-- ----------------------------
DROP TABLE IF EXISTS `hot_search`;
CREATE TABLE `hot_search` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '店铺id',
  `count` int(11) DEFAULT NULL COMMENT '次数',
  `create_user` bigint(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热门搜索表';

-- ----------------------------
-- Records of hot_search
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `category_id` bigint(100) DEFAULT NULL COMMENT '分类id',
  `user_id` bigint(50) DEFAULT NULL COMMENT '发帖人id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `url` varchar(500) DEFAULT NULL COMMENT '图片或视频的url',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `address` varchar(100) DEFAULT NULL COMMENT '地点',
  `like` int(11) DEFAULT '0' COMMENT '点赞数',
  `discuss` int(11) DEFAULT '0' COMMENT '评论数',
  `look` int(11) DEFAULT '0' COMMENT '查看数',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='帖子表';

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `user_id` bigint(50) DEFAULT NULL COMMENT '举报人id',
  `be_user_id` bigint(50) DEFAULT NULL COMMENT '被举报人id',
  `report_type_id` bigint(50) DEFAULT NULL COMMENT '举报类型',
  `status` int(11) DEFAULT '0' COMMENT '状态 0.受理中 1.已反馈',
  `result` varchar(50) DEFAULT NULL COMMENT '举报结果',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报表';

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for report_type
-- ----------------------------
DROP TABLE IF EXISTS `report_type`;
CREATE TABLE `report_type` (
  `id` bigint(50) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报类型表';

-- ----------------------------
-- Records of report_type
-- ----------------------------

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` bigint(50) NOT NULL COMMENT '编号',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '店铺id',
  `user_id` bigint(50) DEFAULT NULL COMMENT '用户id',
  `status` tinyint(2) DEFAULT '0' COMMENT '0.审批中1.已审批 2.已拒绝',
  `reason` varchar(100) DEFAULT NULL COMMENT '原因',
  `create_user` bigint(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(50) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='审核表';

-- ----------------------------
-- Records of review
-- ----------------------------

-- ----------------------------
-- Table structure for shop
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` bigint(50) NOT NULL COMMENT '编号',
  `user_id` bigint(50) DEFAULT NULL COMMENT '所属用户id',
  `category_id` bigint(10) DEFAULT NULL,
  `shop_name` varchar(50) DEFAULT NULL COMMENT '店铺名称',
  `city_code` varchar(10) DEFAULT NULL COMMENT '城市编码',
  `province_name` varchar(50) DEFAULT NULL COMMENT '省份',
  `city_name` varchar(50) DEFAULT NULL COMMENT '城市',
  `area_name` varchar(50) DEFAULT NULL COMMENT '地区',
  `detail` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `contact` varchar(10) DEFAULT NULL COMMENT '联系人',
  `lon` double(10,6) DEFAULT NULL COMMENT '经度',
  `lat` double(10,6) DEFAULT NULL COMMENT '纬度',
  `status` tinyint(2) DEFAULT '0' COMMENT '0审批中.1通过2拒绝',
  `rate` double(3,1) DEFAULT '0.0' COMMENT '评分',
  `reason` varchar(255) DEFAULT NULL COMMENT '原因',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户店铺表';

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1251516233233338368', '1251515986981556224', '1197342622679699456', '啊啊啊', '110100', '北京市', '北京市', '东城区', '谢河浜(嘉兴市秀洲区)', '13626585858', '额呃呃呃', '120.631775', '30.807185', '0', '0.0', '正在审核中', '1251515986981556224', '2020-04-18 22:21:37', '1251515986981556224', '2020-04-18 22:42:55');
INSERT INTO `shop` VALUES ('1251690615398338560', '1251660144606056448', '1197342622679699456', '呃呃呃', '110100', '北京市', '北京市', '东城区', '谢河浜(嘉兴市秀洲区)', '13626532585', '粑粑', '120.631909', '30.807202', '0', '0.0', '正在审核中', '1251660144606056448', '2020-04-19 09:54:33', null, null);

-- ----------------------------
-- Table structure for shop_img
-- ----------------------------
DROP TABLE IF EXISTS `shop_img`;
CREATE TABLE `shop_img` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `shop_id` bigint(50) DEFAULT NULL COMMENT '店铺id',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` tinyint(2) DEFAULT NULL COMMENT '0.店铺图片 1.营业执照',
  `create_user` bigint(36) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` bigint(36) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1251690614295236609 DEFAULT CHARSET=utf8 COMMENT='店铺图片表';

-- ----------------------------
-- Records of shop_img
-- ----------------------------
INSERT INTO `shop_img` VALUES ('1251516229307469824', '1251516233233338368', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-04-18/2d68f19fe08544b0a645912baec36e31.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1587219695%3B1902752495%26q-key-time%3D1587219695%3B1902752495%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D45b9fb9ddcbf753c75a49da5e5009d2db1455594', '0', '1251515986981556224', '2020-04-18 22:21:36', '1251515986981556224', '2020-04-18 22:21:37');
INSERT INTO `shop_img` VALUES ('1251516231949881344', '1251516233233338368', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-04-18/58a1d9083529447f85a60cee1d4ef4aa.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1587219696%3B1902752496%26q-key-time%3D1587219696%3B1902752496%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D019e1fbfad8e9e50f1480464920da6b34edd280b', '1', '1251515986981556224', '2020-04-18 22:21:36', '1251515986981556224', '2020-04-18 22:21:37');
INSERT INTO `shop_img` VALUES ('1251690610788798464', '1251690615398338560', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-04-19/47a11ca81e9b44dab649e9128aa9b0e3.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1587261271%3B1902794071%26q-key-time%3D1587261271%3B1902794071%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D4359d711e12199b8868c0c3bc3eeef7e4367a2d4', '0', '1251660144606056448', '2020-04-19 09:54:32', '1251660144606056448', '2020-04-19 09:54:33');
INSERT INTO `shop_img` VALUES ('1251690614295236608', '1251690615398338560', 'http://may-1259221767.cos.ap-shanghai.myqcloud.com/img/2020-04-19/c3f51652e40041a1b2d64ab5cd60bb9e.jpg?sign=q-sign-algorithm%3Dsha1%26q-ak%3DAKIDSiuvlQUFNPzSjpqU4TpkZGqHbjMpnXgd%26q-sign-time%3D1587261272%3B1902794072%26q-key-time%3D1587261272%3B1902794072%26q-header-list%3D%26q-url-param-list%3D%26q-signature%3D7fced7acde34c1e336ebbfa3a3ec23cc750b60e6', '1', '1251660144606056448', '2020-04-19 09:54:32', '1251660144606056448', '2020-04-19 09:54:33');
