-- 为零售客户表添加缺失的字段

-- 添加职业相关字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `employer_name` VARCHAR(200) NULL COMMENT '工作单位名称' AFTER `occupation`,
ADD COLUMN `position` VARCHAR(100) NULL COMMENT '职位' AFTER `employer_name`;

-- 添加 VIP 积分字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `vip_points` INT NULL COMMENT 'VIP积分' AFTER `vip_level`,
ADD COLUMN `vip_start_date` DATE NULL COMMENT 'VIP开始日期' AFTER `vip_points`,
ADD COLUMN `vip_end_date` DATE NULL COMMENT 'VIP到期日期' AFTER `vip_start_date`;

-- 添加收入信息字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `annual_income` DECIMAL(15,2) NULL COMMENT '年收入' AFTER `vip_end_date`,
ADD COLUMN `monthly_income` DECIMAL(15,2) NULL COMMENT '月收入' AFTER `annual_income`,
ADD COLUMN `source_of_income` VARCHAR(200) NULL COMMENT '收入来源' AFTER `monthly_income`;

-- 添加资产信息字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `assets` DECIMAL(15,2) NULL COMMENT '资产总额' AFTER `source_of_income`,
ADD COLUMN `liabilities` DECIMAL(15,2) NULL COMMENT '负债总额' AFTER `assets`,
ADD COLUMN `has_house` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有房产' AFTER `liabilities`,
ADD COLUMN `has_car` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有车' AFTER `has_house`,
ADD COLUMN `has_insurance` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有保险' AFTER `has_car`;

-- 添加信誉信息字段
ALTER TABLE `crm_retail_customer`
ADD COLUMN `has_loan_record` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有贷款记录' AFTER `has_insurance`,
ADD COLUMN `has_overdue_record` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有逾期记录' AFTER `has_loan_record`,
ADD COLUMN `blacklist_flag` BIT(1) NOT NULL DEFAULT b'0' COMMENT '黑名单标志' AFTER `has_overdue_record`;

-- 为现有测试数据填充示例值
UPDATE `crm_retail_customer` SET
  employer_name = CASE
    WHEN occupation = '专业技术人员' THEN '某某科技有限公司'
    WHEN occupation = '公务员' THEN '某某政府机关'
    WHEN occupation = '企业管理人员' THEN '某某集团公司'
    ELSE '某某公司'
  END,
  position = CASE
    WHEN occupation = '专业技术人员' THEN '高级工程师'
    WHEN occupation = '公务员' THEN '科长'
    WHEN occupation = '企业管理人员' THEN '部门经理'
    ELSE '职员'
  END,
  vip_points = CASE
    WHEN is_vip = 1 THEN FLOOR(1000 + RAND() * 9000)
    ELSE 0
  END,
  vip_start_date = CASE
    WHEN is_vip = 1 THEN DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 365) DAY)
    ELSE NULL
  END,
  vip_end_date = CASE
    WHEN is_vip = 1 THEN DATE_ADD(CURDATE(), INTERVAL FLOOR(365 + RAND() * 365) DAY)
    ELSE NULL
  END,
  annual_income = FLOOR(100000 + RAND() * 900000),
  monthly_income = FLOOR(8000 + RAND() * 75000),
  source_of_income = CASE
    WHEN occupation = '专业技术人员' THEN '工资收入+项目奖金'
    WHEN occupation = '公务员' THEN '工资收入'
    WHEN occupation = '企业管理人员' THEN '工资收入+股权分红'
    ELSE '工资收入'
  END,
  assets = FLOOR(500000 + RAND() * 9500000),
  liabilities = FLOOR(100000 + RAND() * 2000000),
  has_house = b'1',
  has_car = CASE WHEN RAND() > 0.3 THEN b'1' ELSE b'0' END,
  has_insurance = b'1',
  has_loan_record = CASE WHEN RAND() > 0.5 THEN b'1' ELSE b'0' END,
  has_overdue_record = b'0',
  blacklist_flag = b'0'
WHERE occupation IS NOT NULL;
