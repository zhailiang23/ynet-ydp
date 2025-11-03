-- 为客户表添加归属管理相关字段

-- 1. 分配状态字段 (0=未分配, 1=已分配)
ALTER TABLE crm_customer
ADD COLUMN assignment_status TINYINT NOT NULL DEFAULT 0 COMMENT '分配状态(0=未分配,1=已分配)' AFTER customer_status;

-- 2. 是否有效客户字段 (0=否, 1=是)
ALTER TABLE crm_customer
ADD COLUMN is_valid BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否有效客户(0=否,1=是)' AFTER assignment_status;

-- 为分配状态添加索引以提升查询性能
CREATE INDEX idx_assignment_status ON crm_customer(assignment_status);
