-- JDBC 认证方式 创建数据库 cas 和表 user
CREATE DATABASE IF NOT EXISTS cas DEFAULT CHARACTER SET utf8;

CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `expired` tinyint(4) NULL DEFAULT NULL,
  `disabled` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `user` VALUES (1, 'pitt1997', '123456', 0, 0);
INSERT INTO `user` VALUES (2, 'valieva', '12345678', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;