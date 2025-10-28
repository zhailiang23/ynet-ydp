-- CRM对公客户表字段补充SQL
-- 根据老系统acrm_f_ci_org表补充crm_company_customer表缺失的36个字段
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 财务信息字段 (5个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN total_assets DECIMAL(18,2) DEFAULT NULL COMMENT '资产总额' AFTER ext_field3;
ALTER TABLE crm_company_customer ADD COLUMN total_debt DECIMAL(18,2) DEFAULT NULL COMMENT '负债总额' AFTER total_assets;
ALTER TABLE crm_company_customer ADD COLUMN annual_income DECIMAL(18,2) DEFAULT NULL COMMENT '年收入' AFTER total_debt;
ALTER TABLE crm_company_customer ADD COLUMN annual_profit DECIMAL(18,2) DEFAULT NULL COMMENT '年利润' AFTER annual_income;
ALTER TABLE crm_company_customer ADD COLUMN fin_report_type VARCHAR(20) DEFAULT NULL COMMENT '财务报表类型' AFTER annual_profit;

-- ============================================
-- 2. 股权与投资信息 (3个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN is_stock_holder BIT(1) DEFAULT b'0' COMMENT '是否股东' AFTER fin_report_type;
ALTER TABLE crm_company_customer ADD COLUMN hold_stock_amt DECIMAL(18,2) DEFAULT NULL COMMENT '持股金额' AFTER is_stock_holder;
ALTER TABLE crm_company_customer ADD COLUMN investment_type VARCHAR(20) DEFAULT NULL COMMENT '投资类型' AFTER hold_stock_amt;

-- ============================================
-- 3. 企业组织信息 (7个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN org_form VARCHAR(20) DEFAULT NULL COMMENT '组织形式' AFTER investment_type;
ALTER TABLE crm_company_customer ADD COLUMN governance_structure VARCHAR(20) DEFAULT NULL COMMENT '治理结构' AFTER org_form;
ALTER TABLE crm_company_customer ADD COLUMN holding_type VARCHAR(20) DEFAULT NULL COMMENT '控股类型' AFTER governance_structure;
ALTER TABLE crm_company_customer ADD COLUMN enterprise_belong VARCHAR(100) DEFAULT NULL COMMENT '企业归属' AFTER holding_type;
ALTER TABLE crm_company_customer ADD COLUMN superior_dept VARCHAR(60) DEFAULT NULL COMMENT '上级部门' AFTER enterprise_belong;
ALTER TABLE crm_company_customer ADD COLUMN company_organization VARCHAR(80) DEFAULT NULL COMMENT '公司机构' AFTER superior_dept;
ALTER TABLE crm_company_customer ADD COLUMN nation_code VARCHAR(20) DEFAULT NULL COMMENT '国家代码' AFTER company_organization;

-- ============================================
-- 4. 业务经营信息 (8个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN main_business VARCHAR(500) DEFAULT NULL COMMENT '主营业务' AFTER nation_code;
ALTER TABLE crm_company_customer ADD COLUMN minor_business VARCHAR(500) DEFAULT NULL COMMENT '辅营业务' AFTER main_business;
ALTER TABLE crm_company_customer ADD COLUMN business_mode VARCHAR(20) DEFAULT NULL COMMENT '经营模式' AFTER minor_business;
ALTER TABLE crm_company_customer ADD COLUMN business_start_date DATE DEFAULT NULL COMMENT '营业开始日期' AFTER business_mode;
ALTER TABLE crm_company_customer ADD COLUMN industry_character VARCHAR(20) DEFAULT NULL COMMENT '行业特征' AFTER business_start_date;
ALTER TABLE crm_company_customer ADD COLUMN industry_development_prospect VARCHAR(20) DEFAULT NULL COMMENT '行业发展前景' AFTER industry_character;
ALTER TABLE crm_company_customer ADD COLUMN area_code VARCHAR(20) DEFAULT NULL COMMENT '地区代码' AFTER industry_development_prospect;
ALTER TABLE crm_company_customer ADD COLUMN industry_position VARCHAR(80) DEFAULT NULL COMMENT '行业地位' AFTER area_code;

-- ============================================
-- 5. 企业规模信息 (4个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN employee_scale VARCHAR(20) DEFAULT NULL COMMENT '员工规模' AFTER industry_position;
ALTER TABLE crm_company_customer ADD COLUMN assets_scale VARCHAR(20) DEFAULT NULL COMMENT '资产规模' AFTER employee_scale;
ALTER TABLE crm_company_customer ADD COLUMN production_capacity VARCHAR(20) DEFAULT NULL COMMENT '生产能力' AFTER assets_scale;
ALTER TABLE crm_company_customer ADD COLUMN enterprise_property VARCHAR(20) DEFAULT NULL COMMENT '企业性质' AFTER production_capacity;

-- ============================================
-- 6. 监管与评级信息 (3个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN loan_card_flag BIT(1) DEFAULT b'0' COMMENT '贷款卡标志' AFTER enterprise_property;
ALTER TABLE crm_company_customer ADD COLUMN loan_card_status VARCHAR(20) DEFAULT NULL COMMENT '贷款卡状态' AFTER loan_card_flag;
ALTER TABLE crm_company_customer ADD COLUMN loan_card_audit_date DATE DEFAULT NULL COMMENT '贷款卡审核日期' AFTER loan_card_status;

-- ============================================
-- 7. 其他特殊字段 (2个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN enterprise_scale_pboc VARCHAR(20) DEFAULT NULL COMMENT '人行企业规模' AFTER loan_card_audit_date;
ALTER TABLE crm_company_customer ADD COLUMN enterprise_scale_deposit VARCHAR(20) DEFAULT NULL COMMENT '存款企业规模' AFTER enterprise_scale_pboc;

-- ============================================
-- 8. 系统管理与追溯字段 (4个)
-- ============================================

ALTER TABLE crm_company_customer ADD COLUMN etl_date DATE DEFAULT NULL COMMENT 'ETL导入日期' AFTER enterprise_scale_deposit;
ALTER TABLE crm_company_customer ADD COLUMN old_tx_seq_no VARCHAR(32) DEFAULT NULL COMMENT '老系统交易序列号' AFTER etl_date;
ALTER TABLE crm_company_customer ADD COLUMN old_last_update_sys VARCHAR(20) DEFAULT NULL COMMENT '老系统最后更新系统' AFTER old_tx_seq_no;
ALTER TABLE crm_company_customer ADD COLUMN old_cust_id VARCHAR(50) DEFAULT NULL COMMENT '老系统客户ID' AFTER old_last_update_sys;

-- ============================================
-- 9. 创建索引以提升查询性能
-- ============================================

-- 财务信息索引
CREATE INDEX idx_total_assets ON crm_company_customer(total_assets);
CREATE INDEX idx_annual_income ON crm_company_customer(annual_income);

-- 企业分类索引
CREATE INDEX idx_holding_type ON crm_company_customer(holding_type);
CREATE INDEX idx_business_mode ON crm_company_customer(business_mode);
CREATE INDEX idx_employee_scale ON crm_company_customer(employee_scale);

-- 地区和行业索引
CREATE INDEX idx_area_code ON crm_company_customer(area_code);
CREATE INDEX idx_nation_code ON crm_company_customer(nation_code);

-- 系统追溯索引
CREATE INDEX idx_old_cust_id ON crm_company_customer(old_cust_id);
CREATE INDEX idx_etl_date ON crm_company_customer(etl_date);

-- ============================================
-- 说明
-- ============================================

-- 1. 共添加36个字段,覆盖老系统acrm_f_ci_org表的所有缺失字段
-- 2. 数值字段统一使用DECIMAL(18,2)保证精度
-- 3. 布尔字段使用BIT(1)类型
-- 4. 文本字段根据业务需求设置合适长度
-- 5. 添加了10个索引以提升查询性能
-- 6. 字段顺序按业务逻辑组织,便于理解和维护

-- 字段分类统计:
-- - 财务信息: 5个字段
-- - 股权投资: 3个字段
-- - 企业组织: 7个字段
-- - 业务经营: 8个字段
-- - 企业规模: 4个字段
-- - 监管评级: 3个字段
-- - 其他特殊: 2个字段
-- - 系统追溯: 4个字段

-- 执行完成后,crm_company_customer表将从52个字段扩展到88个字段
