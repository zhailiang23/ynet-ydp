-- ============================================
-- 客户家庭信息和家庭成员测试数据
-- 创建日期: 2025-10-29
-- 说明: 为现有的10条零售客户记录创建家庭信息和家庭成员数据
-- ============================================

-- ============================================
-- 客户家庭信息测试数据（10条）
-- ============================================

-- 客户1：张三 - 富裕家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  1, 4, 2, 2, 1,
  120.00, '5', 80.00, '4',
  30.00, 800.00, '房产2套，车辆2辆，存款500万', '1',
  '1', '2', 1, 1, '张三', '佛山市南海区桂城街道金融中心',
  '广东省佛山市南海区桂城街道金融中心14楼', '0757-86666888', 1, 1, '1', 150.00,
  '2', '2', NULL, '经营餐饮连锁，规模中等', '1', '优质客户，家庭和睦',
  'admin', 'admin', 1
);

-- 客户2：李四 - 中产家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  2, 3, 1, 2, 1,
  60.00, '4', 45.00, '3',
  80.00, 300.00, '房产1套（按揭中），车辆1辆', '1',
  '1', '1', 1, 1, '李四', '佛山市禅城区祖庙街道',
  '广东省佛山市禅城区祖庙街道建新路18号', '0757-82888999', 1, 1, '2', 80.00,
  '3', '2', NULL, NULL, '2', '稳定的工薪家庭',
  'admin', 'admin', 1
);

-- 客户3：王五 - 年轻家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  3, 2, 0, 2, 0,
  40.00, '3', 30.00, '2',
  50.00, 150.00, '房产1套（按揭中）', '1',
  '1', '1', 0, 1, '王五', '佛山市顺德区大良街道',
  '广东省佛山市顺德区大良街道凤山中路88号', '0757-22688888', 1, 1, '2', 50.00,
  '3', '2', NULL, NULL, '3', '年轻夫妻，未来发展潜力大',
  'admin', 'admin', 1
);

-- 客户4：赵六 - 企业主家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  4, 5, 2, 2, 2,
  250.00, '6', 120.00, '5',
  100.00, 1500.00, '房产3套，商铺2间，车辆3辆', '2',
  '1', '2', 1, 1, '赵六', '佛山市南海区狮山镇',
  '广东省佛山市南海区狮山镇科技工业园A区', '0757-85666777', 1, 1, '1', 300.00,
  '4', '2', NULL, '经营制造业，年产值5000万', '1', '重点优质客户，企业主',
  'admin', 'admin', 1
);

-- 客户5：孙七 - 普通家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  5, 4, 2, 2, 1,
  35.00, '3', 28.00, '2',
  15.00, 120.00, '房产1套，车辆1辆', '1',
  '1', '2', 1, 1, '孙七', '佛山市三水区西南街道',
  '广东省佛山市三水区西南街道河口大道56号', '0757-87555666', 1, 0, '3', 30.00,
  '2', '2', NULL, NULL, '3', '普通工薪家庭',
  'admin', 'admin', 1
);

-- 客户6：周八 - 退休家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  6, 2, 0, 0, 0,
  18.00, '2', 15.00, '2',
  0.00, 200.00, '房产1套（已还清），存款150万', '5',
  '1', '2', 0, 1, '周八', '佛山市禅城区石湾镇',
  '广东省佛山市禅城区石湾镇和平路123号', '0757-82333444', 1, 0, '2', 0.00,
  '1', '1', NULL, NULL, '3', '退休老年夫妻，子女已独立',
  'admin', 'admin', 1
);

-- 客户7：吴九 - 单身白领
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  7, 1, 0, 1, 0,
  25.00, '2', 20.00, '2',
  0.00, 80.00, '租房，车辆1辆', '1',
  '2', '4', 1, 1, '吴九', '佛山市南海区桂城街道千灯湖',
  '广东省佛山市南海区桂城街道千灯湖花园5栋', '0757-86777888', 1, 1, '2', 20.00,
  '1', '1', NULL, NULL, '3', '单身白领，有发展潜力',
  'admin', 'admin', 1
);

-- 客户8：郑十 - 投资客家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  8, 3, 1, 2, 1,
  180.00, '5', 90.00, '4',
  200.00, 1200.00, '房产5套（部分出租），车辆2辆，股票基金', '3',
  '1', '2', 1, 1, '郑十', '佛山市顺德区北滘镇',
  '广东省佛山市顺德区北滘镇碧桂园别墅区', '0757-26888999', 1, 1, '1', 200.00,
  '5', '3', NULL, '主要靠投资收益，房产投资为主', '1', '高净值客户',
  'admin', 'admin', 1
);

-- 客户9：钱十一 - 创业家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  9, 3, 1, 2, 1,
  85.00, '4', 65.00, '3',
  120.00, 350.00, '房产1套（按揭），车辆2辆，公司股权', '2',
  '1', '1', 1, 1, '钱十一', '佛山市南海区丹灶镇',
  '广东省佛山市南海区丹灶镇金沙工业园', '0757-85888777', 1, 1, '2', 100.00,
  '4', '3', NULL, '创业中，经营互联网科技公司', '2', '创业者，有发展潜力',
  'admin', 'admin', 1
);

-- 客户10：冯十二 - 与父母同住家庭
INSERT INTO `crm_customer_family` (
  `customer_id`, `family_member_count`, `support_member_count`, `labor_member_count`, `children_count`,
  `family_annual_income`, `family_annual_income_scope`, `family_annual_expenditure`, `family_annual_expenditure_scope`,
  `family_debt`, `family_total_assets`, `family_assets_info`, `main_income_source`,
  `residence_status`, `house_status`, `has_home_car`, `is_house_holder`, `house_holder_name`, `residence_location`,
  `family_address`, `home_tel`, `is_harmony`, `is_credit_family`, `credit_status`, `credit_amount`,
  `family_debt_scope`, `debt_status`, `family_adverse_records`, `business_and_scale`, `family_strength`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES (
  10, 6, 2, 3, 2,
  95.00, '4', 70.00, '3',
  40.00, 450.00, '父母房产1套，自有车辆1辆', '1',
  '3', '3', 1, 0, '冯父', '佛山市高明区荷城街道',
  '广东省佛山市高明区荷城街道中山路188号', '0757-88666777', 1, 1, '2', 60.00,
  '2', '2', NULL, NULL, '2', '三代同堂，与父母同住',
  'admin', 'admin', 1
);

-- ============================================
-- 客户家庭成员测试数据（共约40条）
-- ============================================

-- 客户1：张三的家庭成员（4人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `email`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(1, '张三', 'self', 1, '1980-05-15', 44, 'spouse', '440281198005151234', '本科', '某餐饮连锁公司', '总经理', '广东省佛山市南海区桂城街道金融中心14楼', '138-0000-0001', '0757-86666888', 'zhangsan@example.com', 1, '户主本人', 'admin', 'admin', 1),
(1, '李红', 'spouse', 2, '1982-08-20', 42, 'spouse', '440281198208201235', '本科', '某贸易公司', '财务经理', '广东省佛山市南海区桂城街道金融中心14楼', '138-0000-0002', '0757-86666888', 'lihong@example.com', 1, '配偶', 'admin', 'admin', 1),
(1, '张小明', 'son', 1, '2010-03-10', 14, 'son', '440281201003101236', '初中', '佛山某中学', '学生', '广东省佛山市南海区桂城街道金融中心14楼', NULL, '0757-86666888', NULL, 0, '儿子', 'admin', 'admin', 1),
(1, '张父', 'father', 1, '1955-02-05', 69, 'father', '440281195502051237', '高中', '已退休', '退休', '广东省佛山市南海区桂城街道金融中心14楼', '138-0000-0003', '0757-86666888', NULL, 0, '父亲，已退休', 'admin', 'admin', 1);

-- 客户2：李四的家庭成员（3人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(2, '李四', 'self', 1, '1985-06-12', 39, 'spouse', '440281198506121238', '本科', '某科技公司', '项目经理', '广东省佛山市禅城区祖庙街道建新路18号', '138-0000-0004', '0757-82888999', 1, '户主本人', 'admin', 'admin', 1),
(2, '王芳', 'spouse', 2, '1987-09-25', 37, 'spouse', '440281198709251239', '大专', '某银行', '柜员', '广东省佛山市禅城区祖庙街道建新路18号', '138-0000-0005', '0757-82888999', 1, '配偶', 'admin', 'admin', 1),
(2, '李小雨', 'daughter', 2, '2015-11-08', 9, 'daughter', '440281201511081240', '小学', '佛山某小学', '学生', '广东省佛山市禅城区祖庙街道建新路18号', NULL, '0757-82888999', 0, '女儿', 'admin', 'admin', 1);

-- 客户3：王五的家庭成员（2人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(3, '王五', 'self', 1, '1990-07-18', 34, 'spouse', '440281199007181241', '硕士', '某互联网公司', '高级工程师', '广东省佛山市顺德区大良街道凤山中路88号', '138-0000-0006', '0757-22688888', 1, '户主本人', 'admin', 'admin', 1),
(3, '刘婷', 'spouse', 2, '1992-04-22', 32, 'spouse', '440281199204221242', '本科', '某教育机构', '教师', '广东省佛山市顺德区大良街道凤山中路88号', '138-0000-0007', '0757-22688888', 1, '配偶', 'admin', 'admin', 1);

-- 客户4：赵六的家庭成员（5人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(4, '赵六', 'self', 1, '1975-03-08', 49, 'spouse', '440281197503081243', '本科', '某制造公司', '董事长', '广东省佛山市南海区狮山镇科技工业园A区', '138-0000-0008', '0757-85666777', 1, '户主本人，企业主', 'admin', 'admin', 1),
(4, '陈美', 'spouse', 2, '1978-11-15', 46, 'spouse', '440281197811151244', '大专', '家庭主妇', '家庭主妇', '广东省佛山市南海区狮山镇科技工业园A区', '138-0000-0009', '0757-85666777', 1, '配偶', 'admin', 'admin', 1),
(4, '赵大', 'son', 1, '2000-05-20', 24, 'son', '440281200005201245', '本科', '某制造公司', '车间主任', '广东省佛山市南海区狮山镇科技工业园A区', '138-0000-0010', '0757-85666777', 1, '长子，在家族企业工作', 'admin', 'admin', 1),
(4, '赵小', 'daughter', 2, '2005-08-12', 19, 'daughter', '440281200508121246', '大学', '某大学', '学生', '广东省佛山市南海区狮山镇科技工业园A区', '138-0000-0011', '0757-85666777', 0, '女儿，在读大学', 'admin', 'admin', 1),
(4, '赵母', 'mother', 2, '1950-01-10', 74, 'mother', '440281195001101247', '小学', '已退休', '退休', '广东省佛山市南海区狮山镇科技工业园A区', NULL, '0757-85666777', 0, '母亲，已退休', 'admin', 'admin', 1);

-- 客户5：孙七的家庭成员（4人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(5, '孙七', 'self', 1, '1988-12-03', 36, 'spouse', '440281198812031248', '大专', '某物流公司', '配送员', '广东省佛山市三水区西南街道河口大道56号', '138-0000-0012', '0757-87555666', 1, '户主本人', 'admin', 'admin', 1),
(5, '赵兰', 'spouse', 2, '1990-02-14', 34, 'spouse', '440281199002141249', '高中', '某超市', '收银员', '广东省佛山市三水区西南街道河口大道56号', '138-0000-0013', '0757-87555666', 1, '配偶', 'admin', 'admin', 1),
(5, '孙小宝', 'son', 1, '2012-06-18', 12, 'son', '440281201206181250', '小学', '三水某小学', '学生', '广东省佛山市三水区西南街道河口大道56号', NULL, '0757-87555666', 0, '儿子', 'admin', 'admin', 1),
(5, '孙父', 'father', 1, '1960-08-25', 64, 'father', '440281196008251251', '初中', '已退休', '退休', '广东省佛山市三水区西南街道河口大道56号', NULL, '0757-87555666', 0, '父亲，已退休', 'admin', 'admin', 1);

-- 客户6：周八的家庭成员（2人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(6, '周八', 'self', 1, '1958-04-10', 66, 'spouse', '440281195804101252', '高中', '已退休', '退休', '广东省佛山市禅城区石湾镇和平路123号', '138-0000-0014', '0757-82333444', 1, '户主本人，退休', 'admin', 'admin', 1),
(6, '吴梅', 'spouse', 2, '1960-09-05', 64, 'spouse', '440281196009051253', '初中', '已退休', '退休', '广东省佛山市禅城区石湾镇和平路123号', '138-0000-0015', '0757-82333444', 1, '配偶，退休', 'admin', 'admin', 1);

-- 客户7：吴九的家庭成员（1人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(7, '吴九', 'self', 1, '1995-10-28', 29, 'spouse', '440281199510281254', '硕士', '某金融公司', '投资顾问', '广东省佛山市南海区桂城街道千灯湖花园5栋', '138-0000-0016', '0757-86777888', 1, '户主本人，单身', 'admin', 'admin', 1);

-- 客户8：郑十的家庭成员（3人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(8, '郑十', 'self', 1, '1978-07-22', 46, 'spouse', '440281197807221255', '硕士', '某投资公司', '投资总监', '广东省佛山市顺德区北滘镇碧桂园别墅区', '138-0000-0017', '0757-26888999', 1, '户主本人，职业投资者', 'admin', 'admin', 1),
(8, '黄丽', 'spouse', 2, '1980-12-08', 44, 'spouse', '440281198012081256', '本科', '家庭主妇', '家庭主妇', '广东省佛山市顺德区北滘镇碧桂园别墅区', '138-0000-0018', '0757-26888999', 1, '配偶', 'admin', 'admin', 1),
(8, '郑小月', 'daughter', 2, '2008-04-15', 16, 'daughter', '440281200804151257', '高中', '顺德某中学', '学生', '广东省佛山市顺德区北滘镇碧桂园别墅区', '138-0000-0019', '0757-26888999', 0, '女儿', 'admin', 'admin', 1);

-- 客户9：钱十一的家庭成员（3人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(9, '钱十一', 'self', 1, '1986-11-30', 38, 'spouse', '440281198611301258', '本科', '某科技公司', 'CEO', '广东省佛山市南海区丹灶镇金沙工业园', '138-0000-0020', '0757-85888777', 1, '户主本人，创业者', 'admin', 'admin', 1),
(9, '周静', 'spouse', 2, '1988-05-18', 36, 'spouse', '440281198805181259', '本科', '某科技公司', 'CFO', '广东省佛山市南海区丹灶镇金沙工业园', '138-0000-0021', '0757-85888777', 1, '配偶，联合创始人', 'admin', 'admin', 1),
(9, '钱小虎', 'son', 1, '2014-09-10', 10, 'son', '440281201409101260', '小学', '丹灶某小学', '学生', '广东省佛山市南海区丹灶镇金沙工业园', NULL, '0757-85888777', 0, '儿子', 'admin', 'admin', 1);

-- 客户10：冯十二的家庭成员（6人）
INSERT INTO `crm_customer_family_member` (
  `customer_id`, `member_name`, `relation_type`, `gender`, `birthday`, `age`,
  `identity_type`, `identity_no`, `education_level`, `company`, `position`,
  `address`, `mobile`, `tel`, `is_main_member`, `remark`,
  `creator`, `updater`, `tenant_id`
) VALUES
(10, '冯十二', 'self', 1, '1989-01-25', 35, 'spouse', '440281198901251261', '大专', '某制造厂', '车间主任', '广东省佛山市高明区荷城街道中山路188号', '138-0000-0022', '0757-88666777', 1, '户主本人', 'admin', 'admin', 1),
(10, '杨雪', 'spouse', 2, '1991-06-12', 33, 'spouse', '440281199106121262', '大专', '某服装厂', '质检员', '广东省佛山市高明区荷城街道中山路188号', '138-0000-0023', '0757-88666777', 1, '配偶', 'admin', 'admin', 1),
(10, '冯小龙', 'son', 1, '2013-03-08', 11, 'son', '440281201303081263', '小学', '高明某小学', '学生', '广东省佛山市高明区荷城街道中山路188号', NULL, '0757-88666777', 0, '儿子', 'admin', 'admin', 1),
(10, '冯小凤', 'daughter', 2, '2017-07-20', 7, 'daughter', '440281201707201264', '幼儿园', '高明某幼儿园', '学生', '广东省佛山市高明区荷城街道中山路188号', NULL, '0757-88666777', 0, '女儿', 'admin', 'admin', 1),
(10, '冯父', 'father', 1, '1962-11-05', 62, 'father', '440281196211051265', '初中', '已退休', '退休', '广东省佛山市高明区荷城街道中山路188号', NULL, '0757-88666777', 0, '父亲，已退休', 'admin', 'admin', 1),
(10, '冯母', 'mother', 2, '1965-02-18', 59, 'mother', '440281196502181266', '小学', '已退休', '退休', '广东省佛山市高明区荷城街道中山路188号', NULL, '0757-88666777', 0, '母亲，已退休', 'admin', 'admin', 1);
