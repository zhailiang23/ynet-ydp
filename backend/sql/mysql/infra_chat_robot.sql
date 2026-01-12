-- ----------------------------
-- 对话机器人管理表
-- ----------------------------
DROP TABLE IF EXISTS `infra_chat_robot`;
CREATE TABLE `infra_chat_robot` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '机器人ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '机器人名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机器人描述',
  `platform_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '对接平台类型（dingtalk=钉钉，wework=企业微信）',
  `platform_config` json NULL COMMENT '平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）',
  `agent_id` bigint NULL DEFAULT NULL COMMENT '绑定的智能体ID（关联infra_external_agent表）',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '是否启用（0=禁用，1=启用）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_platform_type`(`platform_type` ASC) USING BTREE COMMENT '平台类型索引',
  INDEX `idx_agent_id`(`agent_id` ASC) USING BTREE COMMENT '智能体ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引',
  CONSTRAINT `fk_chat_robot_agent` FOREIGN KEY (`agent_id`) REFERENCES `infra_external_agent` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '对话机器人管理表';

-- ----------------------------
-- 示例数据（可选）
-- ----------------------------
-- 钉钉机器人示例
-- INSERT INTO `infra_chat_robot` (`name`, `description`, `platform_type`, `platform_config`, `agent_id`, `status`, `sort`)
-- VALUES ('客服钉钉机器人', '用于处理客户咨询的钉钉机器人', 'dingtalk',
--   JSON_OBJECT(
--     'webhook', 'https://oapi.dingtalk.com/robot/send?access_token=xxx',
--     'secret', 'SECxxx',
--     'msgtype', 'text'
--   ),
--   1, 1, 1);

-- 企业微信机器人示例
-- INSERT INTO `infra_chat_robot` (`name`, `description`, `platform_type`, `platform_config`, `agent_id`, `status`, `sort`)
-- VALUES ('内部企业微信机器人', '用于团队内部沟通的企业微信机器人', 'wework',
--   JSON_OBJECT(
--     'webhook', 'https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx',
--     'msgtype', 'markdown'
--   ),
--   2, 1, 2);
