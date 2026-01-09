-- ----------------------------
-- 任务评分因子表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `aicrm_task_scoring_factor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `factor_name` varchar(64) NOT NULL COMMENT '因子名称（中文）',
  `factor_name_en` varchar(64) NOT NULL COMMENT '因子英文名',
  `icon` varchar(64) DEFAULT NULL COMMENT 'Material Symbols图标名',
  `weight` int NOT NULL DEFAULT 0 COMMENT '权重(%)',
  `description` varchar(500) DEFAULT NULL COMMENT '因子说明',
  `enabled` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否启用',
  `sort` int NOT NULL DEFAULT 0 COMMENT '排序（升序）',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户ID',
  PRIMARY KEY (`id`),
  KEY `idx_sort` (`sort`),
  KEY `idx_enabled` (`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='任务评分因子表';

-- ----------------------------
-- 初始化数据
-- ----------------------------
-- 业务价值
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    '业务价值', 'Business Value', 'monetization_on', 40,
    '基于客户AUM、潜在营收价值及产品利润率计算。', b'1', 1,
    '1', NOW(), '1', NOW(), b'0', 1
);

-- 紧急程度
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    '紧急程度', 'Urgency', 'timer', 30,
    '基于任务截止时间(SLA)倒计时，越接近截止时间分值越高。', b'1', 2,
    '1', NOW(), '1', NOW(), b'0', 1
);

-- 合规强制
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    '合规强制', 'Compliance', 'gavel', 20,
    '监管要求的必须执行项（如反洗钱尽调、风险测评过期）。', b'1', 3,
    '1', NOW(), '1', NOW(), b'0', 1
);

-- 客户意向
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    '客户意向', 'Customer Intent', 'sentiment_satisfied', 10,
    '基于客户近期在App/Web端的浏览、点击、搜索行为评分。', b'1', 4,
    '1', NOW(), '1', NOW(), b'0', 1
);
