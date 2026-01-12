-- 为 CRM 零售客户表添加手机号字段
-- 用于支持客户识别功能：通过手机号或证件号查询客户

-- 添加手机号字段到零售客户表
ALTER TABLE crm_retail_customer
ADD COLUMN `mobile` varchar(20) DEFAULT NULL COMMENT '手机号码' AFTER `id_card_no`;

-- 为手机号字段创建索引，提高查询性能
CREATE INDEX idx_mobile ON crm_retail_customer(`mobile`);

-- 为证件号字段创建索引（如果还没有的话）
CREATE INDEX idx_id_card_no ON crm_retail_customer(`id_card_no`);
