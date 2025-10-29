-- ============================================
-- 客户重要事件表
-- 创建日期: 2025-10-29
-- 说明:
--   1. 记录客户的重要事件信息
--   2. 支持零售客户和对公客户（通过customer_id关联crm_customer主表）
--   3. 包含完整的字典数据初始化
--   4. 新系统表，无老系统对应表
-- ============================================

-- ============================================
-- 客户重要事件表
-- ============================================
CREATE TABLE `crm_customer_important_event` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID（关联客户主表）',

  -- 事件基本信息（图片中的字段）
  `event_name` VARCHAR(200) NOT NULL COMMENT '事件名称',
  `event_status` VARCHAR(50) NOT NULL COMMENT '事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）',
  `event_date` DATE NOT NULL COMMENT '事件发生日期',
  `event_content` VARCHAR(2000) DEFAULT NULL COMMENT '事件内容',

  -- 维护人信息
  `maintainer_id` BIGINT DEFAULT NULL COMMENT '维护人ID（关联用户表）',
  `maintainer_name` VARCHAR(100) DEFAULT NULL COMMENT '维护人姓名',
  `maintain_time` DATETIME DEFAULT NULL COMMENT '最近维护日期',

  -- 扩展字段
  `event_type` VARCHAR(50) DEFAULT NULL COMMENT '事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
  `event_level` VARCHAR(50) DEFAULT NULL COMMENT '事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
  `event_source` VARCHAR(50) DEFAULT NULL COMMENT '事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
  `remind_flag` TINYINT(1) DEFAULT 0 COMMENT '是否提醒（0-否，1-是）',
  `remind_time` DATETIME DEFAULT NULL COMMENT '提醒时间',
  `attachment_url` VARCHAR(500) DEFAULT NULL COMMENT '附件地址',
  `remark` VARCHAR(1000) DEFAULT NULL COMMENT '备注',

  -- 审计字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_event_date` (`event_date`),
  KEY `idx_event_status` (`event_status`),
  KEY `idx_maintainer_id` (`maintainer_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户重要事件表（零售+对公共用）';

-- ============================================
-- 字典数据初始化
-- ============================================

-- 事件状态字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM事件状态', 'aicrm_event_status', 0, '客户重要事件状态', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_event_status', 'not_occurred', '未发生', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_status', 'in_progress', '进行中', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_status', 'completed', '已完成', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_status', 'cancelled', '已取消', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 事件类型字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM事件类型', 'aicrm_event_type', 0, '客户重要事件类型', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_event_type', 'birthday', '生日', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'marriage', '结婚', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'childbirth', '生子', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'promotion', '升职', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'job_change', '跳槽', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'house_purchase', '购房', 6, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'car_purchase', '购车', 7, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'retirement', '退休', 8, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'education', '子女教育', 9, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'illness', '重大疾病', 10, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'travel', '出国旅游', 11, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'investment', '重大投资', 12, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'loan', '贷款', 13, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'company_established', '公司成立', 14, 0, '对公客户', 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'company_expansion', '企业扩张', 15, 0, '对公客户', 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'company_merger', '企业并购', 16, 0, '对公客户', 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'company_ipo', '企业上市', 17, 0, '对公客户', 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_type', 'other', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 事件级别字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM事件级别', 'aicrm_event_level', 0, '客户重要事件级别', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_event_level', 'critical', '重要', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_level', 'important', '一般', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_level', 'normal', '普通', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 事件来源字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM事件来源', 'aicrm_event_source', 0, '客户重要事件来源', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_event_source', 'customer_inform', '客户告知', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_source', 'system_identify', '系统识别', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_source', 'manager_input', '客户经理录入', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_source', 'data_import', '数据导入', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_event_source', 'other', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');
