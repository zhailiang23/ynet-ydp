-- 为 infra_external_agent 表添加 code 字段

-- 1. 先添加字段（允许NULL）
ALTER TABLE `infra_external_agent`
ADD COLUMN `code` varchar(50) NULL COMMENT '智能体编码' AFTER `id`;

-- 2. 为现有数据生成唯一的code（使用平台类型_id作为编码）
UPDATE `infra_external_agent`
SET `code` = CONCAT(platform_type, '_', id)
WHERE `code` IS NULL OR `code` = '';

-- 3. 设置字段为 NOT NULL
ALTER TABLE `infra_external_agent`
MODIFY COLUMN `code` varchar(50) NOT NULL COMMENT '智能体编码';

-- 4. 添加唯一索引
ALTER TABLE `infra_external_agent`
ADD UNIQUE KEY `uk_code` (`code`) USING BTREE COMMENT '智能体编码唯一索引';
