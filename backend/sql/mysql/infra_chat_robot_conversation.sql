-- ----------------------------
-- Table structure for infra_chat_robot_conversation
-- 钉钉机器人对话表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `infra_chat_robot_conversation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '对话ID',
  `robot_id` BIGINT NOT NULL COMMENT '机器人ID',
  `conversation_id` VARCHAR(255) NOT NULL COMMENT '钉钉对话ID（群聊或单聊的唯一标识）',
  `conversation_type` TINYINT NOT NULL DEFAULT 1 COMMENT '对话类型：1=单聊，2=群聊',
  `conversation_title` VARCHAR(255) DEFAULT NULL COMMENT '对话标题（群聊名称或用户昵称）',
  `sender_id` VARCHAR(255) DEFAULT NULL COMMENT '发送者ID（单聊时记录用户ID）',
  `sender_name` VARCHAR(255) DEFAULT NULL COMMENT '发送者昵称',
  `last_message_time` DATETIME DEFAULT NULL COMMENT '最后消息时间',
  `last_message_content` VARCHAR(500) DEFAULT NULL COMMENT '最后消息内容',
  `total_message_count` INT NOT NULL DEFAULT 0 COMMENT '消息总数',
  `unread_count` INT NOT NULL DEFAULT 0 COMMENT '未读消息数',
  `status` TINYINT NOT NULL DEFAULT 0 COMMENT '对话状态：0=进行中，1=已关闭',

  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',

  UNIQUE KEY `uk_robot_conversation` (`robot_id`, `conversation_id`, `deleted`),
  KEY `idx_robot_status_time` (`robot_id`, `status`, `last_message_time`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钉钉机器人对话表';
