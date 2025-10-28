-- CRM对公客户字典数据
-- 为对公客户补充字段创建字典类型和数据
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 字典类型 (11个)
-- ============================================

-- 财务报表类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (210, 'CRM财务报表类型', 'crm_fin_report_type', 0, '企业财务报表类型', 1, NOW(), 1, NOW(), 0);

-- 投资类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (211, 'CRM投资类型', 'crm_investment_type', 0, '企业投资性质类型', 1, NOW(), 1, NOW(), 0);

-- 组织形式
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (212, 'CRM组织形式', 'crm_org_form', 0, '企业组织形式', 1, NOW(), 1, NOW(), 0);

-- 治理结构
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (213, 'CRM治理结构', 'crm_governance_structure', 0, '公司治理结构类型', 1, NOW(), 1, NOW(), 0);

-- 控股类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (214, 'CRM控股类型', 'crm_holding_type', 0, '企业控股类型', 1, NOW(), 1, NOW(), 0);

-- 经营模式
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (215, 'CRM经营模式', 'crm_business_mode', 0, '企业经营模式类型', 1, NOW(), 1, NOW(), 0);

-- 行业特征
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (216, 'CRM行业特征', 'crm_industry_char', 0, '行业特征分类', 1, NOW(), 1, NOW(), 0);

-- 行业发展前景
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (217, 'CRM行业发展前景', 'crm_industry_prospect', 0, '行业发展前景评估', 1, NOW(), 1, NOW(), 0);

-- 员工规模
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (218, 'CRM员工规模', 'crm_employee_scale', 0, '企业员工人数规模', 1, NOW(), 1, NOW(), 0);

-- 生产能力
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (219, 'CRM生产能力', 'crm_production_capacity', 0, '企业生产能力利用率', 1, NOW(), 1, NOW(), 0);

-- 贷款卡状态
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (220, 'CRM贷款卡状态', 'crm_loan_card_status', 0, '企业贷款卡状态', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 2. 字典数据
-- ============================================

-- 财务报表类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_fin_report_type', '年报', 'annual', 1, '年度财务报表', 1, NOW(), 1, NOW(), 0),
('crm_fin_report_type', '半年报', 'semi_annual', 2, '半年度财务报表', 1, NOW(), 1, NOW(), 0),
('crm_fin_report_type', '季报', 'quarterly', 3, '季度财务报表', 1, NOW(), 1, NOW(), 0),
('crm_fin_report_type', '月报', 'monthly', 4, '月度财务报表', 1, NOW(), 1, NOW(), 0);

-- 投资类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_investment_type', '风险投资', 'venture', 1, '风险投资/创业投资', 1, NOW(), 1, NOW(), 0),
('crm_investment_type', '战略投资', 'strategic', 2, '战略投资', 1, NOW(), 1, NOW(), 0),
('crm_investment_type', '财务投资', 'financial', 3, '财务投资', 1, NOW(), 1, NOW(), 0),
('crm_investment_type', '产业投资', 'industry', 4, '产业投资', 1, NOW(), 1, NOW(), 0);

-- 组织形式数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_org_form', '有限责任公司', 'limited_liability', 1, '有限责任公司', 1, NOW(), 1, NOW(), 0),
('crm_org_form', '股份有限公司', 'joint_stock', 2, '股份有限公司', 1, NOW(), 1, NOW(), 0),
('crm_org_form', '个人独资企业', 'sole_proprietorship', 3, '个人独资企业', 1, NOW(), 1, NOW(), 0),
('crm_org_form', '合伙企业', 'partnership', 4, '合伙企业', 1, NOW(), 1, NOW(), 0),
('crm_org_form', '外商独资企业', 'wholly_foreign_owned', 5, '外商独资企业', 1, NOW(), 1, NOW(), 0),
('crm_org_form', '中外合资企业', 'sino_foreign_joint', 6, '中外合资企业', 1, NOW(), 1, NOW(), 0);

-- 治理结构数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_governance_structure', '一人有限公司', 'one_person', 1, '一人有限公司', 1, NOW(), 1, NOW(), 0),
('crm_governance_structure', '股东会制', 'shareholders', 2, '股东会制', 1, NOW(), 1, NOW(), 0),
('crm_governance_structure', '董事会制', 'board_directors', 3, '董事会制', 1, NOW(), 1, NOW(), 0),
('crm_governance_structure', '监事会制', 'board_supervisors', 4, '监事会制', 1, NOW(), 1, NOW(), 0);

-- 控股类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_holding_type', '国有控股', 'state_holding', 1, '国有控股企业', 1, NOW(), 1, NOW(), 0),
('crm_holding_type', '民营控股', 'private_holding', 2, '民营控股企业', 1, NOW(), 1, NOW(), 0),
('crm_holding_type', '外资控股', 'foreign_holding', 3, '外资控股企业', 1, NOW(), 1, NOW(), 0),
('crm_holding_type', '中外合资', 'sino_foreign', 4, '中外合资企业', 1, NOW(), 1, NOW(), 0),
('crm_holding_type', '集体控股', 'collective', 5, '集体控股企业', 1, NOW(), 1, NOW(), 0);

-- 经营模式数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_business_mode', '生产型', 'manufacturing', 1, '生产制造型企业', 1, NOW(), 1, NOW(), 0),
('crm_business_mode', '贸易型', 'trading', 2, '贸易型企业', 1, NOW(), 1, NOW(), 0),
('crm_business_mode', '服务型', 'service', 3, '服务型企业', 1, NOW(), 1, NOW(), 0),
('crm_business_mode', '混合型', 'hybrid', 4, '生产+贸易+服务混合', 1, NOW(), 1, NOW(), 0),
('crm_business_mode', '电商型', 'ecommerce', 5, '电子商务型企业', 1, NOW(), 1, NOW(), 0);

-- 行业特征数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_industry_char', '传统行业', 'traditional', 1, '传统行业', 1, NOW(), 1, NOW(), 0),
('crm_industry_char', '新兴行业', 'emerging', 2, '新兴行业', 1, NOW(), 1, NOW(), 0),
('crm_industry_char', '高新技术', 'high_tech', 3, '高新技术行业', 1, NOW(), 1, NOW(), 0),
('crm_industry_char', '现代服务业', 'modern_service', 4, '现代服务业', 1, NOW(), 1, NOW(), 0),
('crm_industry_char', '战略新兴', 'strategic_emerging', 5, '战略性新兴产业', 1, NOW(), 1, NOW(), 0);

-- 行业发展前景数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_industry_prospect', '高速增长', 'high_growth', 1, '行业处于高速增长期', 1, NOW(), 1, NOW(), 0),
('crm_industry_prospect', '稳定增长', 'stable_growth', 2, '行业稳定增长', 1, NOW(), 1, NOW(), 0),
('crm_industry_prospect', '缓慢增长', 'slow_growth', 3, '行业缓慢增长', 1, NOW(), 1, NOW(), 0),
('crm_industry_prospect', '成熟期', 'mature', 4, '行业处于成熟期', 1, NOW(), 1, NOW(), 0),
('crm_industry_prospect', '衰退期', 'decline', 5, '行业处于衰退期', 1, NOW(), 1, NOW(), 0);

-- 员工规模数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_employee_scale', '50人以下', '1', 1, '微型企业', 1, NOW(), 1, NOW(), 0),
('crm_employee_scale', '50-300人', '2', 2, '小型企业', 1, NOW(), 1, NOW(), 0),
('crm_employee_scale', '300-1000人', '3', 3, '中型企业', 1, NOW(), 1, NOW(), 0),
('crm_employee_scale', '1000-5000人', '4', 4, '大型企业', 1, NOW(), 1, NOW(), 0),
('crm_employee_scale', '5000人以上', '5', 5, '超大型企业', 1, NOW(), 1, NOW(), 0);

-- 生产能力数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_production_capacity', '满负荷生产', 'full', 1, '生产能力100%利用', 1, NOW(), 1, NOW(), 0),
('crm_production_capacity', '80%以上', 'high', 2, '生产能力80%-100%', 1, NOW(), 1, NOW(), 0),
('crm_production_capacity', '60%-80%', 'medium', 3, '生产能力60%-80%', 1, NOW(), 1, NOW(), 0),
('crm_production_capacity', '40%-60%', 'low', 4, '生产能力40%-60%', 1, NOW(), 1, NOW(), 0),
('crm_production_capacity', '40%以下', 'very_low', 5, '生产能力40%以下', 1, NOW(), 1, NOW(), 0);

-- 贷款卡状态数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_loan_card_status', '正常', 'normal', 1, '贷款卡状态正常', 1, NOW(), 1, NOW(), 0),
('crm_loan_card_status', '冻结', 'frozen', 2, '贷款卡已冻结', 1, NOW(), 1, NOW(), 0),
('crm_loan_card_status', '注销', 'cancelled', 3, '贷款卡已注销', 1, NOW(), 1, NOW(), 0),
('crm_loan_card_status', '挂失', 'lost', 4, '贷款卡挂失', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 说明
-- ============================================

-- 1. 创建了11个字典类型,ID从210-220
-- 2. 共创建了59条字典数据
-- 3. 所有字典类型状态为启用(status=0)
-- 4. 字典数据按业务逻辑排序
-- 5. creator和updater设为1(系统管理员)

-- 字典类型统计:
-- - 财务报表类型: 4条数据
-- - 投资类型: 4条数据
-- - 组织形式: 6条数据
-- - 治理结构: 4条数据
-- - 控股类型: 5条数据
-- - 经营模式: 5条数据
-- - 行业特征: 5条数据
-- - 行业发展前景: 5条数据
-- - 员工规模: 5条数据
-- - 生产能力: 5条数据
-- - 贷款卡状态: 4条数据

-- 执行完成后,可以在系统管理->字典管理中查看和管理这些字典
