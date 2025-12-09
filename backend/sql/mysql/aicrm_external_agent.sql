-- =============================
-- 外部智能体管理表
-- =============================

-- 创建外部智能体管理表
CREATE TABLE IF NOT EXISTS `aicrm_external_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '智能体ID',
  `name` varchar(100) NOT NULL COMMENT '智能体名称',
  `description` varchar(500) NULL COMMENT '智能体描述',
  `platform_type` varchar(20) NOT NULL COMMENT '平台类型（dify, hiagent, coze等）',
  `access_url` varchar(255) NOT NULL COMMENT '访问URL',
  `api_key` varchar(255) NOT NULL COMMENT 'API密钥',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0=禁用，1=启用）',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序',
  -- 标准审计字段
  `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '外部智能体管理表';

-- 创建索引
CREATE INDEX idx_platform_type ON aicrm_external_agent(platform_type);
CREATE INDEX idx_status ON aicrm_external_agent(status);
CREATE INDEX idx_tenant_id ON aicrm_external_agent(tenant_id);

-- =============================
-- 数据字典配置
-- =============================

-- 1. 插入字典类型
INSERT INTO system_dict_type (
  `id`, `name`, `type`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`
) VALUES (
  217, '外部智能体平台类型', 'aicrm_external_agent_platform_type', 0,
  '外部智能体平台类型：dify、hiagent、coze 等',
  '1', NOW(), '1', NOW(), b'0', NULL
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  remark = VALUES(remark),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 2. 插入字典数据
INSERT INTO system_dict_data (
  `id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES
  (1401, 1, 'Dify', 'dify', 'aicrm_external_agent_platform_type', 0, 'primary', '', 'Dify 平台', '1', NOW(), '1', NOW(), b'0'),
  (1402, 2, 'HiAgent', 'hiagent', 'aicrm_external_agent_platform_type', 0, 'success', '', 'HiAgent 平台', '1', NOW(), '1', NOW(), b'0'),
  (1403, 3, 'Coze', 'coze', 'aicrm_external_agent_platform_type', 0, 'warning', '', 'Coze 平台', '1', NOW(), '1', NOW(), b'0'),
  (1404, 4, '其他', 'other', 'aicrm_external_agent_platform_type', 0, 'info', '', '其他平台', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
  sort = VALUES(sort),
  label = VALUES(label),
  value = VALUES(value),
  status = VALUES(status),
  color_type = VALUES(color_type),
  remark = VALUES(remark),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- =============================
-- 菜单权限配置
-- =============================

-- 1. 外部智能体管理菜单（主菜单）
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2700, '外部智能体', '', 2, 10, 2397, 'external-agent', 'ant-design:robot-outlined', 'infra/externalagent/index', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  sort = VALUES(sort),
  path = VALUES(path),
  icon = VALUES(icon),
  component = VALUES(component),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 2. 外部智能体管理 - 查询权限
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2701, '外部智能体查询', 'infra:external-agent:query', 3, 1, 2700, '', '', '', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 3. 外部智能体管理 - 新增权限
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2702, '外部智能体新增', 'infra:external-agent:create', 3, 2, 2700, '', '', '', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 4. 外部智能体管理 - 修改权限
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2703, '外部智能体修改', 'infra:external-agent:update', 3, 3, 2700, '', '', '', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 5. 外部智能体管理 - 删除权限
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2704, '外部智能体删除', 'infra:external-agent:delete', 3, 4, 2700, '', '', '', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- 6. 外部智能体管理 - 导出权限
INSERT INTO system_menu (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`, `path`, `icon`, `component`, `component_name`,
  `status`, `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  2705, '外部智能体导出', 'infra:external-agent:export', 3, 5, 2700, '', '', '', NULL,
  0, b'1', b'1', b'1', '1', NOW(), '1', NOW(), b'0'
) ON DUPLICATE KEY UPDATE
  name = VALUES(name),
  permission = VALUES(permission),
  updater = VALUES(updater),
  update_time = VALUES(update_time);

-- =============================
-- 示例数据
-- =============================

-- 插入示例外部智能体
INSERT INTO aicrm_external_agent (
  `name`, `description`, `platform_type`, `access_url`, `api_key`, `status`, `sort`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
  ('客服助手', 'Dify 平台的智能客服助手，用于处理客户咨询', 'dify', 'https://api.dify.ai/v1', 'app-xxxxxxxxxxxxxx', 1, 1, '1', NOW(), '1', NOW(), b'0', 1),
  ('销售助理', 'HiAgent 平台的销售辅助智能体，帮助生成销售话术', 'hiagent', 'https://hiagent.example.com/api', 'sk-xxxxxxxxxxxxxx', 1, 2, '1', NOW(), '1', NOW(), b'0', 1),
  ('知识问答', 'Coze 平台的知识库问答机器人', 'coze', 'https://api.coze.com/v1', 'coze-xxxxxxxxxxxxxx', 1, 3, '1', NOW(), '1', NOW(), b'0', 1)
ON DUPLICATE KEY UPDATE
  description = VALUES(description),
  access_url = VALUES(access_url),
  updater = VALUES(updater),
  update_time = VALUES(update_time);
