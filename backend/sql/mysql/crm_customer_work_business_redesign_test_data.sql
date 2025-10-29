-- ----------------------------
-- 精简版工作信息和经营信息测试数据
-- 只包含核心业务字段
-- ----------------------------

-- ==================== 工作信息测试数据 (5条) ====================

-- 客户 1: 国企高管
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  1, '云南某国有银行', 'state_owned', '金融',
  '副总裁', 'executive', '零售业务部', 9,
  800000.00, 66666.67,
  b'1', b'1',
  '0871-12345678', 'zhangsan@bank.com', '重要客户，收入稳定',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 2: 民企中层管理
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  2, '云南某科技有限公司', 'private', '互联网',
  '技术经理', 'manager', '研发部', 6,
  360000.00, 30000.00,
  b'1', b'1',
  '13800138000', 'lisi@tech.com',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 3: 外企普通员工
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  3, '某跨国制造企业', 'foreign', '制造',
  '生产主管', 'supervisor', '生产部', 5,
  240000.00, 20000.00,
  b'1', b'1',
  '13900139000',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 4: 事业单位职员
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  4, '云南某高校', 'institution', '教育',
  '副教授', 'director', '计算机学院', 12,
  180000.00, 15000.00,
  b'1', b'1',
  '0871-88888888', 'wangwu@university.edu',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 5: 政府公务员
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  5, '云南省某厅', 'government', '政府机关',
  '处长', 'director', '办公室', 14,
  150000.00, 12500.00,
  b'1', b'1',
  '0871-66666666',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 经营信息测试数据 (5条) ====================

-- 客户 6: 个体工商户（餐饮）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  6, '昆明某餐厅', 'individual', '530100600001234',
  '中餐、快餐服务', '餐饮', 'small', 'normal',
  50000.00, 8,
  1200000.00, 100000.00, 300000.00,
  '530100600001234', b'0',
  '经营稳定，现金流良好',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 7: 有限公司（贸易）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  7, '云南某贸易有限公司', 'company', '530100000012345',
  '进出口贸易、批发零售', '贸易', 'medium', 'normal',
  5000000.00, 35,
  15000000.00, 1250000.00, 2000000.00,
  '530100000012345', b'1',
  '重点客户，业务往来频繁',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 8: 合伙企业（咨询服务）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, '云南某管理咨询合伙企业', 'partnership', '530100100023456',
  '企业管理咨询、财务咨询', '服务', 'small', 'normal',
  1000000.00, 12,
  3600000.00, 300000.00, 800000.00,
  '530100100023456', b'0',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 9: 科技公司（软件开发）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  9, '云南某软件科技有限公司', 'company', '530100000034567',
  '软件开发、技术服务、系统集成', '信息技术', 'medium', 'normal',
  3000000.00, 58,
  8000000.00, 666666.67, 1500000.00,
  '530100000034567', b'1',
  '高成长型企业，潜力客户',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 10: 制造企业（中小型）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, '云南某机械制造有限公司', 'company', '530100000045678',
  '机械设备制造、加工', '制造', 'medium', 'normal',
  10000000.00, 120,
  25000000.00, 2083333.33, 3000000.00,
  '530100000045678', b'1',
  '固定资产较多，融资需求大',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 两者兼有的测试数据 (5条) ====================

-- 客户 11: 电商公司职员 + 网店
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  11, '云南某电商公司', 'private', '电子商务',
  '市场主管', 'supervisor', '市场部', 4,
  180000.00, 15000.00,
  b'1', b'1',
  '13200132000', 'user11@ecommerce.com', '白天上班，晚上经营网店',
  '1', NOW(), '1', NOW(), 1
);

INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  11, '某某网店', 'individual', '530100600005678',
  '网络销售、电子商务', '电子商务', 'small', 'normal',
  10000.00, 2,
  480000.00, 40000.00, 120000.00,
  '530100600005678', b'0',
  '业余时间经营，年利润12万',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 12: 高校讲师 + 咨询工作室
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  12, '云南某职业学院', 'institution', '教育',
  '讲师', 'staff', '经济管理系', 7,
  120000.00, 10000.00,
  b'1', b'1',
  '0871-77777777', 'user12@college.edu', '利用专业知识提供企业咨询服务',
  '1', NOW(), '1', NOW(), 1
);

INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  12, '某某企业管理咨询工作室', 'individual', '530100600006789',
  '企业管理咨询、培训服务', '服务', 'small', 'normal',
  30000.00, 1,
  360000.00, 30000.00, 200000.00,
  '530100600006789', b'0',
  '周末和假期提供咨询培训服务',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 13: 国企财务经理 + 家族企业股东
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  13, '云南某能源集团', 'state_owned', '能源',
  '财务经理', 'manager', '财务部', 11,
  320000.00, 26666.67,
  b'1', b'1',
  '0871-99999999', 'user13@energy.com', '在国企工作稳定，同时参与家族企业',
  '1', NOW(), '1', NOW(), 1
);

INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  13, '云南某建材有限公司', 'company', '530100000056789',
  '建筑材料生产、销售', '制造', 'medium', 'normal',
  8000000.00, 85,
  18000000.00, 1500000.00, 2400000.00,
  '530100000056789', b'1',
  '家族企业，本人持股30%，父亲经营管理',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 14: 外企技术专家 + 创业公司股东
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  14, '某外资软件公司', 'foreign', '信息技术',
  '高级技术专家', 'director', '研发中心', 8,
  480000.00, 40000.00,
  b'1', b'1',
  '0871-55555555', 'user14@foreign-tech.com', '技术入股朋友创业公司',
  '1', NOW(), '1', NOW(), 1
);

INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  14, '云南某智能科技有限公司', 'company', '530100000067890',
  '人工智能、物联网技术研发', '信息技术', 'small', 'normal',
  2000000.00, 18,
  5000000.00, 416666.67, 800000.00,
  '530100000067890', b'1',
  '技术入股15%，业余时间提供技术指导',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 15: 食品公司总监 + 餐饮创业
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`, `work_years`,
  `annual_income`, `monthly_income`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  15, '云南某食品公司', 'private', '食品',
  '生产总监', 'director', '生产部', 29,
  280000.00, 23333.33,
  b'1', b'1',
  '0871-44444444', '即将退休，已开始创业准备',
  '1', NOW(), '1', NOW(), 1
);

INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `tax_registration_no`, `is_general_taxpayer`,
  `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  15, '某某特色餐饮店', 'individual', '530100600007890',
  '餐饮服务', '餐饮', 'small', 'normal',
  100000.00, 6,
  720000.00, 60000.00, 180000.00,
  '530100600007890', b'0',
  '利用多年食品行业经验，退休后创业',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 统计查询 ====================

-- 1. 统计工作信息
SELECT
  '工作信息统计' AS category,
  COUNT(*) AS total_count,
  SUM(CASE WHEN employer_type = 'government' THEN 1 ELSE 0 END) AS government_count,
  SUM(CASE WHEN employer_type = 'institution' THEN 1 ELSE 0 END) AS institution_count,
  SUM(CASE WHEN employer_type = 'state_owned' THEN 1 ELSE 0 END) AS state_owned_count,
  SUM(CASE WHEN employer_type = 'private' THEN 1 ELSE 0 END) AS private_count,
  SUM(CASE WHEN employer_type = 'foreign' THEN 1 ELSE 0 END) AS foreign_count,
  ROUND(AVG(annual_income), 2) AS avg_annual_income,
  SUM(CASE WHEN position_level = 'executive' THEN 1 ELSE 0 END) AS executive_count,
  SUM(CASE WHEN position_level = 'director' THEN 1 ELSE 0 END) AS director_count,
  SUM(CASE WHEN position_level = 'manager' THEN 1 ELSE 0 END) AS manager_count
FROM crm_customer_work_info
WHERE deleted = b'0';

-- 2. 统计经营信息
SELECT
  '经营信息统计' AS category,
  COUNT(*) AS total_count,
  SUM(CASE WHEN business_type = 'individual' THEN 1 ELSE 0 END) AS individual_count,
  SUM(CASE WHEN business_type = 'company' THEN 1 ELSE 0 END) AS company_count,
  SUM(CASE WHEN business_type = 'partnership' THEN 1 ELSE 0 END) AS partnership_count,
  SUM(CASE WHEN business_scale = 'small' THEN 1 ELSE 0 END) AS small_scale_count,
  SUM(CASE WHEN business_scale = 'medium' THEN 1 ELSE 0 END) AS medium_scale_count,
  ROUND(AVG(annual_revenue), 2) AS avg_annual_revenue,
  ROUND(AVG(annual_profit), 2) AS avg_annual_profit,
  SUM(CASE WHEN is_general_taxpayer = b'1' THEN 1 ELSE 0 END) AS general_taxpayer_count,
  SUM(employee_count) AS total_employees
FROM crm_customer_business_info
WHERE deleted = b'0';

-- 3. 客户类型分布
SELECT
  '客户类型分布' AS category,
  SUM(CASE WHEN 客户类型 = '仅职员' THEN 1 ELSE 0 END) AS 仅职员数,
  SUM(CASE WHEN 客户类型 = '仅个体/企业主' THEN 1 ELSE 0 END) AS 仅个体企业主数,
  SUM(CASE WHEN 客户类型 = '职员+个体/企业主' THEN 1 ELSE 0 END) AS 两者兼有数,
  COUNT(*) AS 总客户数
FROM (
  SELECT
    c.id,
    CASE
      WHEN MAX(w.id) IS NOT NULL AND MAX(b.id) IS NULL THEN '仅职员'
      WHEN MAX(w.id) IS NULL AND MAX(b.id) IS NOT NULL THEN '仅个体/企业主'
      WHEN MAX(w.id) IS NOT NULL AND MAX(b.id) IS NOT NULL THEN '职员+个体/企业主'
      ELSE '未知'
    END AS 客户类型
  FROM crm_customer c
  LEFT JOIN crm_customer_work_info w ON c.id = w.customer_id AND w.deleted = b'0'
  LEFT JOIN crm_customer_business_info b ON c.id = b.customer_id AND b.deleted = b'0'
  WHERE c.id <= 15 AND c.deleted = b'0'
  GROUP BY c.id
) AS stats;
