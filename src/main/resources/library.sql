/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 30/10/2021 16:21:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` varchar(17) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '管理员id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '男' COMMENT '性',
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('m', 'm1', 'm', '男', NULL, NULL);
INSERT INTO `admin` VALUES ('m001', '刘鹏', 'm001', '女', '15182764622', '111@gmail.com');
INSERT INTO `admin` VALUES ('mm', 'mm', 'mm', '男', NULL, NULL);
INSERT INTO `admin` VALUES ('mmm', 'mmm', 'mmm', '男', NULL, NULL);

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `bookID` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍ID',
  `bookName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '书名',
  `bookAuthor` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '书籍作者',
  `bookPublisher` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '出版社',
  `publishTime` datetime(0) NULL DEFAULT NULL COMMENT '出版日期',
  `bookPrice` float(5, 2) NULL DEFAULT NULL COMMENT '单价',
  `bookSum` int(5) NULL DEFAULT NULL COMMENT '总数',
  `bookLend` int(255) NULL DEFAULT NULL COMMENT '借出数量',
  `tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '书籍类型',
  `isbn` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'isbn编号',
  PRIMARY KEY (`bookID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('b001', '冬与狮', '兰晓龙', '人民文学出版社', '2021-10-01 10:11:01', 39.00, 4, 1, '小说', '9787020142422');
INSERT INTO `book` VALUES ('b002', '技术与文明：我们的时代和未来', '张笑宇', '广西师范大学出版社', '2021-03-10 10:12:23', 78.00, 5, 2, '历史', '9787559832870');
INSERT INTO `book` VALUES ('b003', '细胞生物学（第4版）', '翟中和', '高等教育出版社', '2011-06-01 10:14:38', 78.00, 2, 1, '生物', '9787040321753');
INSERT INTO `book` VALUES ('b004', '计算机网络（第7版）', '谢希仁', '电子工业出版社', '2017-01-01 10:15:39', 45.00, 1, 1, '计算机', '9787121302954');
INSERT INTO `book` VALUES ('b005', '左宗棠全传', '秦翰才', '中华书局', '2016-06-01 10:17:50', 68.00, 3, 1, '历史', '9787101115079');
INSERT INTO `book` VALUES ('b006', '活着', '余华', '作家出版社', '2012-08-01 10:19:35', 20.00, 2, 1, '文学', '9787506365437');
INSERT INTO `book` VALUES ('b007', '鲁迅', '鲁迅', '北京日报出版社（原同心出版社）', '2014-05-25 10:21:10', 380.00, 1, 1, '文学', '9787547711101');
INSERT INTO `book` VALUES ('b008', '杭州古旧地图集', '杭州市档案馆', '浙江古籍出版社', '2007-01-25 10:23:17', 680.00, 1, 1, '地理', '9787807151753');
INSERT INTO `book` VALUES ('b009', '孙子兵法', '[春秋]孙武', '蓝天出版社', '2006-04-25 10:24:41', 29.80, 1, 0, '军事', '9787801587466');
INSERT INTO `book` VALUES ('b010', '福尔摩斯探案全集（套装共4册）', '[英]阿瑟·柯南·道尔', '译林出版社', '2017-04-01 10:27:14', 168.00, 1, 0, '小说', '9787544766432');
INSERT INTO `book` VALUES ('b111', '总量为0', '0', '0', '2021-10-29 22:07:56', 0.00, 0, 0, '0', '0');
INSERT INTO `book` VALUES ('b112', '借出大于总量', '1', '1', '2021-10-29 22:08:46', 0.00, 1, 2, '0', '0');
INSERT INTO `book` VALUES ('b113', '借出等于总量', '2', '2', '2021-10-29 22:09:27', 0.00, 1, 1, '0', '0');
INSERT INTO `book` VALUES ('b114', '总量小于0', '3', '3', '2021-10-29 22:10:00', 0.00, -1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow`  (
  `readerID` varchar(17) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者id',
  `bookID` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '书籍id',
  `borrowTime` datetime(0) NOT NULL COMMENT '借出时间',
  `returnTime` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`readerID`, `bookID`, `borrowTime`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('r002', 'b003', '2021-10-11 10:30:24', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b001', '2021-10-03 10:30:07', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b002', '2021-10-06 10:30:37', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b003', '2021-10-17 10:30:52', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b004', '2021-10-03 10:31:06', '2021-10-03 10:32:13');
INSERT INTO `borrow` VALUES ('r110', 'b004', '2021-10-25 10:31:49', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b005', '2021-09-26 10:31:25', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b006', '2021-10-10 10:31:35', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b007', '2021-10-12 10:32:31', NULL);
INSERT INTO `borrow` VALUES ('r110', 'b008', '2021-10-06 10:32:08', NULL);
INSERT INTO `borrow` VALUES ('r111', 'b010', '2021-10-29 22:12:27', '2021-10-29 22:23:02');
INSERT INTO `borrow` VALUES ('r111', 'b111', '2021-10-29 11:27:33', NULL);

-- ----------------------------
-- Table structure for reader
-- ----------------------------
DROP TABLE IF EXISTS `reader`;
CREATE TABLE `reader`  (
  `id` varchar(17) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '读者id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '男' COMMENT '性别',
  `telephone` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader
-- ----------------------------
INSERT INTO `reader` VALUES ('r001', '张刚', 'r001', '男', '13615930165 ', 'jiaping@hotmail.com');
INSERT INTO `reader` VALUES ('r002', '黄金凤', 'r002', '女', '15630925514 ', 'guiying31@gmail.com');
INSERT INTO `reader` VALUES ('r003', '宋淑兰', 'r003', '女', '15144529242 ', 'zhuming@yahoo.com');
INSERT INTO `reader` VALUES ('r004', '陈凯', 'r004', '男', '18659586449 ', 'yfan@yahoo.com');
INSERT INTO `reader` VALUES ('r00511', 'r00511', 'r00511', '男', '17330000000', '1@qq.co');
INSERT INTO `reader` VALUES ('r110', '借了8本书', 'r110', '男', '17885544754', '1@qq.co');
INSERT INTO `reader` VALUES ('r111', 'r111r111', 'r111r111', '女', '17885544754', '1@qq.co');
INSERT INTO `reader` VALUES ('r114', 'r110', 'r11444', '男', '17338453201', '1@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
