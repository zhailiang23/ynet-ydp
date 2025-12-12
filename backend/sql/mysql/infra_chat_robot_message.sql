-- ----------------------------
-- Table structure for infra_chat_robot_message
-- 钉钉机器人消息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `infra_chat_robot_message` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '消息ID',
  `robot_id` BIGINT NOT NULL COMMENT '机器人ID（冗余字段，便于查询）',
  `conversation_id` VARCHAR(255) NOT NULL COMMENT '钉钉对话ID',
  `message_id` VARCHAR(255) DEFAULT NULL COMMENT '钉钉消息ID（接收的消息有此字段）',
  `sender_id` VARCHAR(255) DEFAULT NULL COMMENT '发送者ID',
  `sender_name` VARCHAR(255) DEFAULT NULL COMMENT '发送者昵称',
  `content` LONGTEXT NOT NULL COMMENT '消息内容',
  `message_type` VARCHAR(32) NOT NULL DEFAULT 'text' COMMENT '消息类型：text, image, file, audio, video',
  `source` TINYINT NOT NULL DEFAULT 0 COMMENT '消息来源：0=钉钉用户，1=管理员（以机器人身份发送），2=系统',
  `is_read` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否已读',
  `send_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',

  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户编号',

  KEY `idx_conversation_time` (`conversation_id`, `send_time`),
  KEY `idx_robot_conversation` (`robot_id`, `conversation_id`),
  KEY `idx_message_id` (`message_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钉钉机器人消息表';
