-- ----------------------------
-- Table structure for infra_external_agent
-- ----------------------------
DROP TABLE IF EXISTS `infra_external_agent`;
CREATE TABLE `infra_external_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '智能体ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '智能体名称',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '智能体描述',
  `platform_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '平台类型（dify, hiagent, coze等）',
  `access_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '访问URL',
  `api_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API密钥',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0=禁用，1=启用）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_platform_type`(`platform_type` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外部智能体管理表';

-- ----------------------------
-- Records of infra_external_agent
-- ----------------------------
BEGIN;
-- 示例数据
INSERT INTO `infra_external_agent` (`id`, `name`, `description`, `platform_type`, `access_url`, `api_key`, `status`, `sort`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`) VALUES
(1, 'Dify智能客服', '基于Dify平台的智能客服助手', 'dify', 'https://api.dify.ai/v1', 'app-xxxxxxxxxxxxxxxxxxxx', 1, 1, 'admin', NOW(), 'admin', NOW(), b'0', 1),
(2, 'HiAgent销售助手', '基于HiAgent平台的销售助手', 'hiagent', 'https://api.hiagent.ai/v1', 'sk-xxxxxxxxxxxxxxxxxxxx', 1, 2, 'admin', NOW(), 'admin', NOW(), b'0', 1),
(3, 'Coze文档问答', '基于Coze平台的文档问答机器人', 'coze', 'https://api.coze.com/v1', 'pat_xxxxxxxxxxxxxxxxxxxx', 0, 3, 'admin', NOW(), 'admin', NOW(), b'0', 1);
COMMIT;

-- ----------------------------
-- 字典类型：智能体平台类型
-- ----------------------------
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, 'ai智能体平台类型', 'ai_agent_platform_type', 0, '外部智能体平台类型字典', 'admin', NOW(), 'admin', NOW(), b'0', NULL);

-- 获取刚插入的字典类型ID
SET @dict_type_id = LAST_INSERT_ID();

-- ----------------------------
-- 字典数据：智能体平台类型
-- ----------------------------
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 'Dify', 'dify', 'ai_agent_platform_type', 0, 'primary', '', 'Dify AI平台', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, 'HiAgent', 'hiagent', 'ai_agent_platform_type', 0, 'success', '', 'HiAgent AI平台', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, 'Coze', 'coze', 'ai_agent_platform_type', 0, 'info', '', '扣子AI平台', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, 'Custom', 'custom', 'ai_agent_platform_type', 0, 'warning', '', '自定义平台', 'admin', NOW(), 'admin', NOW(), b'0');
