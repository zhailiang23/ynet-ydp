-- ----------------------------
-- 同时拥有工作信息和经营信息的客户测试数据
-- 模拟现实中一些客户既有正式工作又有副业经营的情况
-- ----------------------------

-- 客户 11: 公司职员 + 个体工商户(网店)
-- 工作信息: 在民企担任市场主管
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  11, 'employee', '云南某电商公司', 'private', '电子商务',
  '市场主管', 'supervisor', '市场部',
  '2020-03-01', 4, b'1',
  '云南省', '昆明市', '盘龙区', '白云路66号',
  180000.00, 15000.00, '基本工资+提成',
  b'1', b'1',
  '13200132000', 'user11@ecommerce.com',
  1, '白天上班，晚上经营网店',
  '1', NOW(), '1', NOW(), 1
);

-- 经营信息: 经营个体网店
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  11, '某某网店', 'individual', '530100600005678',
  '2027-12-31', '2020-06-01',
  '网络销售、电子商务', '电子商务', 'small', 'normal',
  10000.00, 2,
  '云南省', '昆明市', '盘龙区', '白云路66号(家庭地址)',
  480000.00, 40000.00, 120000.00,
  '2020-06-01', b'1',
  '本人', '13200132000',
  '530100600005678', b'0',
  1, '业余时间经营，年利润12万',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 12: 事业单位职员 + 咨询服务(兼职)
-- 工作信息: 在高校担任讲师
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  12, 'employee', '云南某职业学院', 'institution', '教育',
  '讲师', 'staff', '经济管理系',
  '2017-09-01', 7, b'1',
  '云南省', '昆明市', '呈贡区', '大学城',
  120000.00, 10000.00, '基本工资+课酬',
  b'1', b'1',
  '0871-77777777', 'user12@college.edu',
  1, '利用专业知识提供企业咨询服务',
  '1', NOW(), '1', NOW(), 1
);

-- 经营信息: 注册个体工商户提供咨询服务
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`, `contact_email`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  12, '某某企业管理咨询工作室', 'individual', '530100600006789',
  '2029-12-31', '2019-01-15',
  '企业管理咨询、培训服务', '服务', 'small', 'normal',
  30000.00, 1,
  '云南省', '昆明市', '呈贡区', '大学城教师公寓',
  360000.00, 30000.00, 200000.00,
  '2019-01-15', b'1',
  '本人', '13100131000', 'user12@consulting.com',
  '530100600006789', b'0',
  1, '周末和假期提供咨询培训服务',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 13: 国企职员 + 家族企业股东
-- 工作信息: 在国企担任部门经理
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  13, 'employee', '云南某能源集团', 'state_owned', '能源',
  '财务经理', 'manager', '财务部',
  '2013-07-01', 11, b'1',
  '云南省', '昆明市', '西山区', '滇池路888号',
  320000.00, 26666.67, '基本工资+绩效',
  b'1', b'1',
  '0871-99999999', 'user13@energy.com',
  1, '在国企工作稳定，同时参与家族企业',
  '1', NOW(), '1', NOW(), 1
);

-- 经营信息: 家族企业持股30%
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `production_capacity`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  13, '云南某建材有限公司', 'company', '530100000056789',
  '2033-06-30', '2008-07-01',
  '建筑材料生产、销售', '制造', 'medium', 'normal',
  8000000.00, 85,
  '云南省', '昆明市', '安宁市', '工业园区18号',
  18000000.00, 1500000.00, 2400000.00,
  '年产建材5万吨',
  '2008-07-01', b'1',
  '其父亲(法人代表)', '13000130000',
  '530100000056789', b'1',
  1, '家族企业，本人持股30%，父亲经营管理',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 14: 外企员工 + 投资入股朋友公司
-- 工作信息: 在外企担任技术专家
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  14, 'employee', '某外资软件公司', 'foreign', '信息技术',
  '高级技术专家', 'director', '研发中心',
  '2016-04-01', 8, b'1',
  '云南省', '昆明市', '高新区', '海源北路100号',
  480000.00, 40000.00, '基本工资+股票期权',
  b'1', b'1',
  '0871-55555555', 'user14@foreign-tech.com',
  1, '技术入股朋友创业公司',
  '1', NOW(), '1', NOW(), 1
);

-- 经营信息: 技术入股创业公司(持股15%)
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `production_capacity`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  14, '云南某智能科技有限公司', 'company', '530100000067890',
  '2034-12-31', '2021-01-10',
  '人工智能、物联网技术研发', '信息技术', 'small', 'normal',
  2000000.00, 18,
  '云南省', '昆明市', '高新区', '科技创新园A座',
  5000000.00, 416666.67, 800000.00,
  '年交付AI项目10+个',
  '2021-01-10', b'1',
  '合伙人(CEO)', '12900129000',
  '530100000067890', b'1',
  1, '技术入股15%，业余时间提供技术指导',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 15: 民企职员 + 退休后创业
-- 工作信息: 即将退休的老员工
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `end_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  15, 'employee', '云南某食品公司', 'private', '食品',
  '生产总监', 'director', '生产部',
  '1995-03-01', '2024-12-31', 29, b'0',
  '云南省', '昆明市', '官渡区', '新螺蛳湾国际商贸城',
  280000.00, 23333.33, '基本工资+退休金',
  b'1', b'1',
  '0871-44444444',
  1, '2024年底退休，已提前开始创业准备',
  '1', NOW(), '1', NOW(), 1
);

-- 经营信息: 退休后创业开餐厅
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  15, '某某特色餐饮店', 'individual', '530100600007890',
  '2034-06-30', '2024-07-01',
  '餐饮服务', '餐饮', 'small', 'normal',
  100000.00, 6,
  '云南省', '昆明市', '官渡区', '关上中路128号',
  720000.00, 60000.00, 180000.00,
  '2024-07-01', b'1',
  '本人', '12800128000',
  '530100600007890', b'0',
  1, '利用多年食品行业经验，退休后创业',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 统计查询 ====================

-- 1. 统计同时拥有工作和经营信息的客户
SELECT
  '同时拥有工作和经营信息的客户统计' AS category,
  (SELECT COUNT(DISTINCT customer_id) FROM crm_customer_work_info WHERE deleted = b'0') AS 有工作信息客户数,
  (SELECT COUNT(DISTINCT customer_id) FROM crm_customer_business_info WHERE deleted = b'0') AS 有经营信息客户数,
  COUNT(DISTINCT w.customer_id) AS 两者都有的客户数
FROM crm_customer_work_info w
INNER JOIN crm_customer_business_info b ON w.customer_id = b.customer_id
WHERE w.deleted = b'0' AND b.deleted = b'0';

-- 2. 列出所有同时拥有工作和经营信息的客户
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  w.employer_name AS 工作单位,
  w.position AS 职位,
  w.annual_income AS 工作年收入,
  b.business_name AS 经营企业,
  b.business_type AS 企业类型,
  b.annual_profit AS 年利润,
  (COALESCE(w.annual_income, 0) + COALESCE(b.annual_profit, 0)) AS 总年收入
FROM crm_customer c
INNER JOIN crm_customer_work_info w ON c.id = w.customer_id AND w.deleted = b'0'
INNER JOIN crm_customer_business_info b ON c.id = b.customer_id AND b.deleted = b'0'
WHERE c.deleted = b'0'
ORDER BY 总年收入 DESC;

-- 3. 更新后的客户类型分布统计
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  CASE
    WHEN w.id IS NOT NULL AND b.id IS NULL THEN '仅职员'
    WHEN w.id IS NULL AND b.id IS NOT NULL THEN '仅个体/企业主'
    WHEN w.id IS NOT NULL AND b.id IS NOT NULL THEN '职员+个体/企业主'
    ELSE '未知'
  END AS 客户类型,
  w.employer_name AS 雇主单位,
  b.business_name AS 经营企业
FROM crm_customer c
LEFT JOIN crm_customer_work_info w ON c.id = w.customer_id AND w.deleted = b'0'
LEFT JOIN crm_customer_business_info b ON c.id = b.customer_id AND b.deleted = b'0'
WHERE c.id <= 15 AND c.deleted = b'0'
ORDER BY c.id;
