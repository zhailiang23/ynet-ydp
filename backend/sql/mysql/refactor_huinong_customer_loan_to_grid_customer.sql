-- ========================================================
-- 重构：将 grid_huinong_customer_loan 表合并到 grid_customer 表
-- 执行日期：2025-12-24
-- 说明：将惠农贷款客户管理功能迁移到统一的客户管理表
-- ========================================================

-- Phase 1: 添加贷款专属字段到 grid_customer 表
-- ========================================================

ALTER TABLE grid_customer
-- 客户信息字段
ADD COLUMN customer_category VARCHAR(50) COMMENT '客户大类 (字典: aicrm_customer_category, 仅 HUINONG_LOAN 使用)' AFTER status,
ADD COLUMN subdivision_type VARCHAR(50) COMMENT '细分类型 (仅 HUINONG_LOAN 使用)' AFTER customer_category,
ADD COLUMN business_address VARCHAR(255) COMMENT '经营地址 (仅 HUINONG_LOAN 使用)' AFTER subdivision_type,
ADD COLUMN customer_situation TEXT COMMENT '客户情况 (仅 HUINONG_LOAN 使用)' AFTER business_address,
ADD COLUMN business_years INT COMMENT '经营年限 (仅 HUINONG_LOAN 使用)' AFTER customer_situation,

-- 融资需求字段
ADD COLUMN current_financing VARCHAR(255) COMMENT '当前融资情况 (仅 HUINONG_LOAN 使用)' AFTER business_years,
ADD COLUMN credit_demand VARCHAR(255) COMMENT '信贷产品需求 (仅 HUINONG_LOAN 使用)' AFTER current_financing,
ADD COLUMN demand_month VARCHAR(10) COMMENT '需求月份 1-12 (仅 HUINONG_LOAN 使用)' AFTER credit_demand,
ADD COLUMN demand_period VARCHAR(20) COMMENT '需求旬: 上旬/中旬/下旬 (字典: aicrm_demand_period, 仅 HUINONG_LOAN 使用)' AFTER demand_month,
ADD COLUMN business_progress VARCHAR(100) COMMENT '业务进展 (仅 HUINONG_LOAN 使用)' AFTER demand_period,
ADD COLUMN customer_source VARCHAR(50) COMMENT '客户来源: 行内客户/外拓客户 (字典: aicrm_customer_source, 仅 HUINONG_LOAN 使用)' AFTER business_progress,

-- 贷款申请字段
ADD COLUMN is_applied TINYINT(1) DEFAULT 0 COMMENT '是否进件 (仅 HUINONG_LOAN 使用)' AFTER customer_source,
ADD COLUMN apply_time DATETIME COMMENT '进件时间 (仅 HUINONG_LOAN 使用)' AFTER is_applied,
ADD COLUMN is_approved TINYINT(1) DEFAULT 0 COMMENT '是否审批通过 (仅 HUINONG_LOAN 使用)' AFTER apply_time,
ADD COLUMN approve_time DATETIME COMMENT '审批通过时间 (仅 HUINONG_LOAN 使用)' AFTER is_approved,

-- 贷款金额字段
ADD COLUMN loan_product_name VARCHAR(100) COMMENT '贷款产品名称 (仅 HUINONG_LOAN 使用)' AFTER approve_time,
ADD COLUMN loan_amount DECIMAL(15,2) COMMENT '贷款金额 (元, 仅 HUINONG_LOAN 使用)' AFTER loan_product_name,
ADD COLUMN credit_limit DECIMAL(15,2) COMMENT '授信额度 (元, 仅 HUINONG_LOAN 使用)' AFTER loan_amount,
ADD COLUMN loan_balance DECIMAL(15,2) COMMENT '贷款余额 (元, 仅 HUINONG_LOAN 使用)' AFTER credit_limit,
ADD COLUMN overdue_status VARCHAR(50) COMMENT '逾期状态 (仅 HUINONG_LOAN 使用)' AFTER loan_balance;

-- Phase 2: 添加性能优化索引
-- ========================================================

CREATE INDEX idx_customer_type ON grid_customer(customer_type);
CREATE INDEX idx_grid_customer_type ON grid_customer(grid_id, customer_type);
CREATE INDEX idx_loan_balance ON grid_customer(loan_balance);
CREATE INDEX idx_loan_status ON grid_customer(is_applied, is_approved);

-- Phase 3: 迁移数据从 grid_huinong_customer_loan 到 grid_customer
-- ========================================================

-- 注意：这个迁移脚本更新已存在于 grid_customer 中的记录
-- grid_huinong_customer_loan.customer_id 指向 grid_customer.id（一对一关系）

UPDATE grid_customer gc
INNER JOIN grid_huinong_customer_loan hcl ON gc.id = hcl.customer_id
SET
    gc.customer_type = 'HUINONG_LOAN',
    gc.gender = COALESCE(hcl.gender, gc.gender),
    gc.customer_category = hcl.customer_category,
    gc.subdivision_type = hcl.subdivision_type,
    gc.business_address = hcl.business_address,
    gc.customer_situation = hcl.customer_situation,
    gc.business_years = hcl.business_years,
    gc.current_financing = hcl.current_financing,
    gc.credit_demand = hcl.credit_demand,
    gc.demand_month = hcl.demand_month,
    gc.demand_period = hcl.demand_period,
    gc.business_progress = hcl.business_progress,
    gc.customer_source = hcl.customer_source,
    gc.is_applied = hcl.is_applied,
    gc.apply_time = hcl.apply_time,
    gc.is_approved = hcl.is_approved,
    gc.approve_time = hcl.approve_time,
    gc.loan_product_name = hcl.loan_product_name,
    gc.loan_amount = hcl.loan_amount,
    gc.credit_limit = hcl.credit_limit,
    gc.loan_balance = hcl.loan_balance,
    gc.overdue_status = hcl.overdue_status,
    gc.update_time = NOW(),
    gc.updater = hcl.updater
WHERE hcl.deleted = 0;

-- Phase 4: 验证数据迁移
-- ========================================================

-- 检查迁移的记录数
SELECT
    COUNT(*) AS migrated_count,
    '迁移到 grid_customer 的记录数（应与源表中未删除的记录数相等）' AS note
FROM grid_customer
WHERE customer_type = 'HUINONG_LOAN';

SELECT
    COUNT(*) AS source_count,
    '源表 grid_huinong_customer_loan 中未删除的记录数' AS note
FROM grid_huinong_customer_loan
WHERE deleted = 0;

-- 对比关键字段（抽样检查）
SELECT
    gc.id AS customer_id,
    gc.customer_name,
    gc.customer_category,
    hcl.customer_category AS original_customer_category,
    gc.loan_amount,
    hcl.loan_amount AS original_loan_amount,
    gc.loan_balance,
    hcl.loan_balance AS original_loan_balance,
    '检查字段映射是否正确' AS note
FROM grid_customer gc
INNER JOIN grid_huinong_customer_loan hcl ON gc.id = hcl.customer_id
WHERE gc.customer_type = 'HUINONG_LOAN' AND hcl.deleted = 0
LIMIT 10;

-- 检查是否有孤立记录（grid_huinong_customer_loan 中的 customer_id 不存在于 grid_customer）
SELECT
    COUNT(*) AS orphan_count,
    '孤立记录数（customer_id 不存在于 grid_customer，需手动处理）' AS note
FROM grid_huinong_customer_loan hcl
WHERE hcl.deleted = 0
  AND NOT EXISTS (
      SELECT 1 FROM grid_customer gc WHERE gc.id = hcl.customer_id AND gc.deleted = 0
  );

-- 如果有孤立记录，查看详情
SELECT
    hcl.id,
    hcl.customer_id,
    '孤立记录详情（需手动处理）' AS note
FROM grid_huinong_customer_loan hcl
WHERE hcl.deleted = 0
  AND NOT EXISTS (
      SELECT 1 FROM grid_customer gc WHERE gc.id = hcl.customer_id AND gc.deleted = 0
  )
LIMIT 20;

-- Phase 5: 备份和删除 grid_huinong_customer_loan 表（谨慎执行）
-- ========================================================

-- 注意：在确认数据迁移无误后，再执行此步骤
-- 建议先备份 grid_huinong_customer_loan 表

-- 备份表（可选）
-- CREATE TABLE grid_huinong_customer_loan_backup AS SELECT * FROM grid_huinong_customer_loan;

-- 删除表（谨慎执行，确认数据迁移无误后再执行）
-- DROP TABLE IF EXISTS grid_huinong_customer_loan;

-- ========================================================
-- 重构完成说明
-- ========================================================
-- 1. grid_customer 表新增了 19 个贷款专属字段
-- 2. 原有的 grid_huinong_customer_loan 数据已迁移到 grid_customer
-- 3. customerType = 'HUINONG_LOAN' 标识这是一个惠农贷款客户
-- 4. 字段映射关系：
--    - grid_huinong_customer_loan 所有字段 → grid_customer 对应字段
--    - customer_id（外键）→ 合并到同一条 grid_customer 记录
-- 5. 删除表前请确保：
--    - 后端代码已更新并测试通过
--    - 前端功能验证正常
--    - 已做好数据备份
-- 6. 性能优化：
--    - 已添加 4 个索引优化查询性能
--    - 建议定期 ANALYZE TABLE grid_customer 更新统计信息
