-- =============================================
-- 插入示例培训文件数据
-- =============================================

-- 培训手册类型（training_manual）- 10条
INSERT INTO crm_practice_material (name, file_type, file_url, file_size, content, content_rich, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
('新员工入职培训手册', 'training_manual', 'https://example.com/files/training/new_employee_guide.pdf', 2048576, '新员工入职培训手册内容...', '<p>新员工入职培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('银行产品销售培训手册', 'training_manual', 'https://example.com/files/training/product_sales_guide.pdf', 3145728, '银行产品销售培训手册内容...', '<p>银行产品销售培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('客户服务标准培训手册', 'training_manual', 'https://example.com/files/training/customer_service_guide.pdf', 1572864, '客户服务标准培训手册内容...', '<p>客户服务标准培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('风险管理培训手册', 'training_manual', 'https://example.com/files/training/risk_management_guide.pdf', 2621440, '风险管理培训手册内容...', '<p>风险管理培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('电话销售技巧培训手册', 'training_manual', 'https://example.com/files/training/phone_sales_guide.pdf', 1835008, '电话销售技巧培训手册内容...', '<p>电话销售技巧培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('客户关系管理培训手册', 'training_manual', 'https://example.com/files/training/crm_training_guide.pdf', 2097152, '客户关系管理培训手册内容...', '<p>客户关系管理培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('金融法律法规培训手册', 'training_manual', 'https://example.com/files/training/financial_law_guide.pdf', 3670016, '金融法律法规培训手册内容...', '<p>金融法律法规培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('投资理财顾问培训手册', 'training_manual', 'https://example.com/files/training/investment_advisor_guide.pdf', 2359296, '投资理财顾问培训手册内容...', '<p>投资理财顾问培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('反洗钱业务培训手册', 'training_manual', 'https://example.com/files/training/anti_money_laundering_guide.pdf', 1966080, '反洗钱业务培训手册内容...', '<p>反洗钱业务培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('数字化营销培训手册', 'training_manual', 'https://example.com/files/training/digital_marketing_guide.pdf', 2752512, '数字化营销培训手册内容...', '<p>数字化营销培训手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1);

-- 规则规范类型（rules_and_regulations）- 10条
INSERT INTO crm_practice_material (name, file_type, file_url, file_size, content, content_rich, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
('银行业务操作规范', 'rules_and_regulations', 'https://example.com/files/rules/banking_operation_rules.pdf', 1048576, '银行业务操作规范内容...', '<p>银行业务操作规范内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('客户信息保密规定', 'rules_and_regulations', 'https://example.com/files/rules/customer_confidentiality_rules.pdf', 786432, '客户信息保密规定内容...', '<p>客户信息保密规定内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('金融产品销售合规指引', 'rules_and_regulations', 'https://example.com/files/rules/product_sales_compliance.pdf', 1310720, '金融产品销售合规指引内容...', '<p>金融产品销售合规指引内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('员工行为准则规范', 'rules_and_regulations', 'https://example.com/files/rules/employee_code_of_conduct.pdf', 655360, '员工行为准则规范内容...', '<p>员工行为准则规范内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('反洗钱合规操作指引', 'rules_and_regulations', 'https://example.com/files/rules/aml_compliance_guide.pdf', 1572864, '反洗钱合规操作指引内容...', '<p>反洗钱合规操作指引内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('客户适当性管理规定', 'rules_and_regulations', 'https://example.com/files/rules/customer_suitability_rules.pdf', 917504, '客户适当性管理规定内容...', '<p>客户适当性管理规定内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('金融消费者权益保护规定', 'rules_and_regulations', 'https://example.com/files/rules/consumer_protection_rules.pdf', 1179648, '金融消费者权益保护规定内容...', '<p>金融消费者权益保护规定内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('贷款业务风险管理规定', 'rules_and_regulations', 'https://example.com/files/rules/loan_risk_management_rules.pdf', 1441792, '贷款业务风险管理规定内容...', '<p>贷款业务风险管理规定内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('理财产品销售录音录像规范', 'rules_and_regulations', 'https://example.com/files/rules/wealth_sales_recording_rules.pdf', 524288, '理财产品销售录音录像规范内容...', '<p>理财产品销售录音录像规范内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('客户投诉处理管理办法', 'rules_and_regulations', 'https://example.com/files/rules/complaint_handling_rules.pdf', 851968, '客户投诉处理管理办法内容...', '<p>客户投诉处理管理办法内容...</p>', '1', NOW(), '1', NOW(), 0, 1);

-- 产品信息类型（product_information）- 10条
INSERT INTO crm_practice_material (name, file_type, file_url, file_size, content, content_rich, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
('个人储蓄产品介绍', 'product_information', 'https://example.com/files/products/savings_product_info.pdf', 1048576, '个人储蓄产品介绍内容...', '<p>个人储蓄产品介绍内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('理财产品详细说明书', 'product_information', 'https://example.com/files/products/wealth_product_info.pdf', 2097152, '理财产品详细说明书内容...', '<p>理财产品详细说明书内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('信用卡产品手册', 'product_information', 'https://example.com/files/products/credit_card_info.pdf', 1572864, '信用卡产品手册内容...', '<p>信用卡产品手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('个人贷款产品介绍', 'product_information', 'https://example.com/files/products/personal_loan_info.pdf', 1835008, '个人贷款产品介绍内容...', '<p>个人贷款产品介绍内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('基金投资产品说明', 'product_information', 'https://example.com/files/products/fund_product_info.pdf', 2359296, '基金投资产品说明内容...', '<p>基金投资产品说明内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('保险理财产品指南', 'product_information', 'https://example.com/files/products/insurance_product_info.pdf', 2621440, '保险理财产品指南内容...', '<p>保险理财产品指南内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('黄金投资产品介绍', 'product_information', 'https://example.com/files/products/gold_product_info.pdf', 1310720, '黄金投资产品介绍内容...', '<p>黄金投资产品介绍内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('外汇理财产品说明', 'product_information', 'https://example.com/files/products/forex_product_info.pdf', 1703936, '外汇理财产品说明内容...', '<p>外汇理财产品说明内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('企业金融服务产品手册', 'product_information', 'https://example.com/files/products/corporate_finance_info.pdf', 2883584, '企业金融服务产品手册内容...', '<p>企业金融服务产品手册内容...</p>', '1', NOW(), '1', NOW(), 0, 1),
('私人银行高端产品介绍', 'product_information', 'https://example.com/files/products/private_banking_info.pdf', 3145728, '私人银行高端产品介绍内容...', '<p>私人银行高端产品介绍内容...</p>', '1', NOW(), '1', NOW(), 0, 1);
