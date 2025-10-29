-- ----------------------------
-- 客户工作信息和经营信息测试数据
-- 为前 10 个客户创建不同类型的工作/经营信息
-- ----------------------------

-- ==================== 工作信息测试数据 ====================

-- 客户 1: 国企高管
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
  1, 'employee', '云南某国有银行', 'state_owned', '金融',
  '副总裁', 'executive', '零售业务部',
  '2015-01-01', 9, b'1',
  '云南省', '昆明市', '五华区', '人民中路123号',
  800000.00, 66666.67, '基本工资+绩效+年终奖',
  b'1', b'1',
  '0871-12345678', 'zhangsan@bank.com',
  1, '重要客户，收入稳定',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 2: 民企中层管理
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  2, 'employee', '云南某科技有限公司', 'private', '互联网',
  '技术经理', 'manager', '研发部',
  '2018-06-01', 6, b'1',
  '云南省', '昆明市', '盘龙区', '北京路88号创业大厦',
  360000.00, 30000.00, '基本工资+项目奖金',
  b'1', b'1',
  '13800138000', 'lisi@tech.com',
  1,
  '1', NOW(), '1', NOW(), 1
);

-- 客户 3: 外企普通员工
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`,
  `verification_status`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  3, 'employee', '某跨国制造企业', 'foreign', '制造',
  '生产主管', 'supervisor', '生产部',
  '2019-03-15', 5, b'1',
  '云南省', '昆明市', '官渡区', '经开区工业园',
  240000.00, 20000.00, '基本工资+加班费',
  b'1', b'1',
  '13900139000',
  1,
  '1', NOW(), '1', NOW(), 1
);

-- 客户 4: 事业单位职员
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`, `work_email`,
  `verification_status`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  4, 'employee', '云南某高校', 'institution', '教育',
  '副教授', 'director', '计算机学院',
  '2012-09-01', 12, b'1',
  '云南省', '昆明市', '五华区', '学府路1号',
  180000.00, 15000.00, '基本工资+课酬',
  b'1', b'1',
  '0871-88888888', 'wangwu@university.edu',
  1,
  '1', NOW(), '1', NOW(), 1
);

-- 客户 5: 政府公务员
INSERT INTO `crm_customer_work_info` (
  `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`,
  `position`, `position_level`, `department`,
  `start_date`, `work_years`, `is_current`,
  `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`,
  `annual_income`, `monthly_income`, `income_source`,
  `has_social_insurance`, `has_housing_fund`,
  `work_phone`,
  `verification_status`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  5, 'employee', '云南省某厅', 'government', '政府机关',
  '处长', 'director', '办公室',
  '2010-07-01', 14, b'1',
  '云南省', '昆明市', '五华区', '华山南路56号',
  150000.00, 12500.00, '基本工资+津贴',
  b'1', b'1',
  '0871-66666666',
  1,
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 经营信息测试数据 ====================

-- 客户 6: 个体工商户（餐饮）
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
  6, '昆明某餐厅', 'individual', '530100600001234',
  '2028-12-31', '2018-01-15',
  '中餐、快餐服务', '餐饮', 'small', 'normal',
  50000.00, 8,
  '云南省', '昆明市', '西山区', '滇池路188号',
  1200000.00, 100000.00, 300000.00,
  '2018-01-15', b'1',
  '赵六', '13700137000', 'zhaoliu@restaurant.com',
  '530100600001234', b'0',
  1, '经营稳定，现金流良好',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 7: 有限公司（贸易）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `production_capacity`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`, `contact_email`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  7, '云南某贸易有限公司', 'company', '530100000012345',
  '2030-06-30', '2015-07-01',
  '进出口贸易、批发零售', '贸易', 'medium', 'normal',
  5000000.00, 35,
  '云南省', '昆明市', '官渡区', '新亚洲体育城商贸大厦',
  15000000.00, 1250000.00, 2000000.00,
  '年进出口额约1500万美元',
  '2015-07-01', b'1',
  '孙七', '13600136000', 'sunqi@trade.com',
  '530100000012345', b'1',
  1, '重点客户，业务往来频繁',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 8: 合伙企业（咨询服务）
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
  `verification_status`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, '云南某管理咨询合伙企业', 'partnership', '530100100023456',
  '2029-03-31', '2019-04-01',
  '企业管理咨询、财务咨询', '服务', 'small', 'normal',
  1000000.00, 12,
  '云南省', '昆明市', '五华区', '护国路69号汇都国际',
  3600000.00, 300000.00, 800000.00,
  '2019-04-01', b'1',
  '周八', '13500135000', 'zhouba@consulting.com',
  '530100100023456', b'0',
  1,
  '1', NOW(), '1', NOW(), 1
);

-- 客户 9: 科技公司（软件开发）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `production_capacity`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`, `contact_email`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  9, '云南某软件科技有限公司', 'company', '530100000034567',
  '2032-12-31', '2017-01-10',
  '软件开发、技术服务、系统集成', '信息技术', 'medium', 'normal',
  3000000.00, 58,
  '云南省', '昆明市', '高新区', '科技路99号创新大厦',
  8000000.00, 666666.67, 1500000.00,
  '年交付软件项目20+个',
  '2017-01-10', b'1',
  '吴九', '13400134000', 'wujiu@software.com',
  '530100000034567', b'1',
  1, '高成长型企业，潜力客户',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 10: 制造企业（中小型）
INSERT INTO `crm_customer_business_info` (
  `customer_id`, `business_name`, `business_type`, `business_license_no`,
  `business_license_expire_date`, `registration_date`,
  `business_scope`, `industry`, `business_scale`, `business_status`,
  `registered_capital`, `employee_count`,
  `business_address_province`, `business_address_city`, `business_address_district`, `business_address_detail`,
  `annual_revenue`, `monthly_revenue`, `annual_profit`,
  `production_capacity`,
  `start_date`, `is_current`,
  `contact_person`, `contact_phone`, `contact_email`,
  `tax_registration_no`, `is_general_taxpayer`,
  `verification_status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, '云南某机械制造有限公司', 'company', '530100000045678',
  '2031-08-31', '2016-09-01',
  '机械设备制造、加工', '制造', 'medium', 'normal',
  10000000.00, 120,
  '云南省', '昆明市', '经开区', '经开路20号工业园',
  25000000.00, 2083333.33, 3000000.00,
  '年产各类机械设备500台套',
  '2016-09-01', b'1',
  '郑十', '13300133000', 'zhengshi@machinery.com',
  '530100000045678', b'1',
  1, '固定资产较多，融资需求大',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 统计查询 ====================

-- 1. 统计工作信息数量
SELECT
  '工作信息统计' AS category,
  COUNT(*) AS total_count,
  SUM(CASE WHEN employer_type = 'government' THEN 1 ELSE 0 END) AS government_count,
  SUM(CASE WHEN employer_type = 'institution' THEN 1 ELSE 0 END) AS institution_count,
  SUM(CASE WHEN employer_type = 'state_owned' THEN 1 ELSE 0 END) AS state_owned_count,
  SUM(CASE WHEN employer_type = 'private' THEN 1 ELSE 0 END) AS private_count,
  SUM(CASE WHEN employer_type = 'foreign' THEN 1 ELSE 0 END) AS foreign_count,
  AVG(annual_income) AS avg_annual_income,
  SUM(CASE WHEN position_level = 'executive' THEN 1 ELSE 0 END) AS executive_count,
  SUM(CASE WHEN position_level = 'director' THEN 1 ELSE 0 END) AS director_count,
  SUM(CASE WHEN position_level = 'manager' THEN 1 ELSE 0 END) AS manager_count
FROM crm_customer_work_info
WHERE deleted = b'0';

-- 2. 统计经营信息数量
SELECT
  '经营信息统计' AS category,
  COUNT(*) AS total_count,
  SUM(CASE WHEN business_type = 'individual' THEN 1 ELSE 0 END) AS individual_count,
  SUM(CASE WHEN business_type = 'company' THEN 1 ELSE 0 END) AS company_count,
  SUM(CASE WHEN business_type = 'partnership' THEN 1 ELSE 0 END) AS partnership_count,
  SUM(CASE WHEN business_scale = 'small' THEN 1 ELSE 0 END) AS small_scale_count,
  SUM(CASE WHEN business_scale = 'medium' THEN 1 ELSE 0 END) AS medium_scale_count,
  AVG(annual_revenue) AS avg_annual_revenue,
  AVG(annual_profit) AS avg_annual_profit,
  SUM(CASE WHEN is_general_taxpayer = b'1' THEN 1 ELSE 0 END) AS general_taxpayer_count,
  SUM(employee_count) AS total_employees
FROM crm_customer_business_info
WHERE deleted = b'0';

-- 3. 查看前10个客户的工作/经营情况汇总
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  CASE
    WHEN w.id IS NOT NULL THEN '职员'
    WHEN b.id IS NOT NULL THEN '个体/企业主'
    ELSE '未知'
  END AS 客户类型,
  COALESCE(w.employer_name, b.business_name) AS 单位或企业名称,
  COALESCE(w.position, '法人/经营者') AS 职位,
  COALESCE(w.annual_income, b.annual_profit) AS 年收入或年利润
FROM crm_customer c
LEFT JOIN crm_customer_work_info w ON c.id = w.customer_id AND w.deleted = b'0' AND w.is_current = b'1'
LEFT JOIN crm_customer_business_info b ON c.id = b.customer_id AND b.deleted = b'0' AND b.is_current = b'1'
WHERE c.id <= 10 AND c.deleted = b'0'
ORDER BY c.id;
