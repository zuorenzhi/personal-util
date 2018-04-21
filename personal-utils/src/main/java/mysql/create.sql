CREATE TABLE `a_b_c` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `r_id` varchar(32) DEFAULT NULL COMMENT '机器人ID',
  `name` varchar(255) NOT NULL COMMENT '字符串',
  `standard_ask` varchar(255) DEFAULT NULL COMMENT '标准问',
  `default_answer` varchar(1000) DEFAULT NULL COMMENT '默认答案',
  `type` tinyint(2) NOT NULL COMMENT '知识类型（0:通用行业知识，1:新分类发现）',
  `status_1` tinyint(2) NOT NULL COMMENT '知识状态（0：未使用，1:已使用，2:已忽略）',
  `status_2` smallint(6) NOT NULL DEFAULT '0' COMMENT '状态(0未处理、1已标注、2 已忽略、3已训练)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '删除状态(0:未删除;1:已删除)',
  `delete_time` datetime DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_classification_name` (`robot_id`,`name`,`delete_time`) USING BTREE,
  KEY `idx_standard_ask` (`standard_ask`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='知识分类表';

