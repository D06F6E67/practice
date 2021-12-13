SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for country
-- ----------------------------
DROP TABLE IF EXISTS `country`;
CREATE TABLE `country`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `countryname` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `countrycode` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf16 COLLATE = utf16_general_ci COMMENT = '国家' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of country
-- ----------------------------
INSERT INTO `country` VALUES (1, '中国', 'CN');
INSERT INTO `country` VALUES (2, '美国', 'US');
INSERT INTO `country` VALUES (3, '俄罗斯', 'RU');
INSERT INTO `country` VALUES (4, '英国', 'GB');
INSERT INTO `country` VALUES (5, '法国', 'FR');

SET FOREIGN_KEY_CHECKS = 1;
