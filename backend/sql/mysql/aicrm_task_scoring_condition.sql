-- ================================================================
-- 任务评分条件配置功能 - 数据库迁移脚本
-- ================================================================

-- 1. 扩展评分因子表，添加条件逻辑字段
-- ================================================================
ALTER TABLE aicrm_task_scoring_factor
ADD COLUMN logic_type VARCHAR(10) NOT NULL DEFAULT 'AND' COMMENT '条件逻辑关系: AND/OR' AFTER enabled;

-- 2. 创建条件表
-- ================================================================
CREATE TABLE IF NOT EXISTS `aicrm_task_scoring_condition` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `factor_id` bigint NOT NULL COMMENT '所属评分因子ID',
  `data_source` varchar(64) NOT NULL COMMENT '数据来源',
  `field_name` varchar(64) NOT NULL COMMENT '字段/属性名称',
  `operator` varchar(20) NOT NULL COMMENT '操作符',
  `field_value` varchar(500) NOT NULL COMMENT '比较值',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
  PRIMARY KEY (`id`),
  KEY `idx_factor_id` (`factor_id`),
  KEY `idx_sort` (`sort`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务评分条件表';

-- 3. 插入数据字典 - 数据来源
-- ================================================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted_time)
VALUES ('评分条件-数据来源', 'aicrm_scoring_data_source', 0, '任务评分条件的数据来源配置', '1', NOW(), '1', NOW(), NULL)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (dict_type, value, label, sort, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_scoring_data_source', 'customer_profile', '客户画像', 1, 0, 'default', '', '客户的基本信息和标签', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_data_source', 'task_attribute', '任务属性', 2, 0, 'default', '', '任务本身的属性信息', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_data_source', 'transaction_history', '交易历史', 3, 0, 'default', '', '客户的历史交易数据', '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), remark=VALUES(remark);

-- 4. 插入数据字典 - 操作符
-- ================================================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted_time)
VALUES ('评分条件-操作符', 'aicrm_scoring_operator', 0, '任务评分条件的比较操作符', '1', NOW(), '1', NOW(), NULL)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (dict_type, value, label, sort, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_scoring_operator', 'EQUAL', '等于 (=)', 1, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'NOT_EQUAL', '不等于 (!=)', 2, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'GREATER_THAN', '大于 (>)', 3, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'LESS_THAN', '小于 (<)', 4, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'GREATER_EQUAL', '大于等于 (>=)', 5, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'LESS_EQUAL', '小于等于 (<=)', 6, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'IN', '包含 (IN)', 7, 0, 'default', '', '值在给定集合中', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_operator', 'LIKE', '模糊匹配 (LIKE)', 8, 0, 'default', '', '字符串模糊匹配', '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), remark=VALUES(remark);

-- 5. 插入数据字典 - 客户画像字段
-- ================================================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted_time)
VALUES ('评分条件-客户画像字段', 'aicrm_scoring_field_customer_profile', 0, '客户画像可用字段', '1', NOW(), '1', NOW(), NULL)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (dict_type, value, label, sort, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_scoring_field_customer_profile', 'account_balance', '账户余额', 1, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_field_customer_profile', 'customer_level', '客户等级', 2, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_field_customer_profile', 'risk_rating', '风险评级', 3, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_field_customer_profile', 'account_age_days', '开户时长(天)', 4, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label);

-- 6. 插入数据字典 - 任务属性字段
-- ================================================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted_time)
VALUES ('评分条件-任务属性字段', 'aicrm_scoring_field_task_attribute', 0, '任务属性可用字段', '1', NOW(), '1', NOW(), NULL)
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (dict_type, value, label, sort, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_scoring_field_task_attribute', 'task_type', '任务类型', 1, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_field_task_attribute', 'task_priority', '任务优先级', 2, 0, 'default', '', '', '1', NOW(), '1', NOW(), 0),
('aicrm_scoring_field_task_attribute', 'deadline_days', '截止天数', 3, 0, 'default', '', '距离截止日期的天数', '1', NOW(), '1', NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), remark=VALUES(remark);

-- 7. 插入示例条件数据（可选）
-- ================================================================
-- 为业务价值因子（factor_id=1）添加一个示例条件：账户余额大于100万
INSERT INTO aicrm_task_scoring_condition (factor_id, data_source, field_name, operator, field_value, sort, creator, tenant_id)
SELECT 1, 'customer_profile', 'account_balance', 'GREATER_THAN', '1000000', 1, '1', 1
WHERE EXISTS (SELECT 1 FROM aicrm_task_scoring_factor WHERE id = 1)
AND NOT EXISTS (SELECT 1 FROM aicrm_task_scoring_condition WHERE factor_id = 1 AND field_name = 'account_balance');
