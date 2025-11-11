-- =============================================
-- 修改销售技巧表字段类型
-- =============================================

-- 先清空这两个字段的值（因为要改为 BIGINT 类型）
UPDATE crm_practice_skill SET compliance_rules = NULL, related_products = NULL;

-- 修改 compliance_rules 字段：从 VARCHAR 改为 BIGINT
ALTER TABLE crm_practice_skill MODIFY COLUMN compliance_rules BIGINT COMMENT '合规规则（培训文件ID）';

-- 修改 related_products 字段：从 VARCHAR 改为 BIGINT
ALTER TABLE crm_practice_skill MODIFY COLUMN related_products BIGINT COMMENT '产品知识（培训文件ID）';
