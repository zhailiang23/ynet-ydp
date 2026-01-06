-- ----------------------------
-- 任务行动表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `aicrm_task_action` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '行动ID',
  `task_id` bigint NOT NULL COMMENT '任务ID',
  `action_type` varchar(50) NOT NULL COMMENT '行动类型（CALL-打电话、SMS-发短信、EMAIL-发邮件、VISIT-拜访、MEETING-会议、OTHER-其他）',
  `action_time` datetime NOT NULL COMMENT '行动时间',
  `action_user_id` bigint NOT NULL COMMENT '行动人ID',
  `action_user_name` varchar(100) DEFAULT NULL COMMENT '行动人姓名',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_task_id` (`task_id`) USING BTREE COMMENT '任务ID索引',
  KEY `idx_action_user_id` (`action_user_id`) USING BTREE COMMENT '行动人ID索引',
  KEY `idx_action_time` (`action_time`) USING BTREE COMMENT '行动时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务行动表';
