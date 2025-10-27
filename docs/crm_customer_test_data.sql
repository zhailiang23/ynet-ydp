-- ============================================
-- 客户画像系统 - 测试数据插入脚本
-- 版本: v1.0
-- 日期: 2025-10-27
-- 说明: 插入10条零售客户和10条对公客户测试数据
-- 结果: crm_customer 20条, crm_retail_customer 10条, crm_company_customer 10条
-- ============================================

-- ============================================
-- 1. 零售客户测试数据 (10条)
-- ============================================

-- 零售客户1: 张三 (普通客户)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10001', 1, '张三', '普通客户', 1,
  0, 0, '良好', 'A', 750.00,
  '网点开发', '潜力客户,工薪族', '普通工薪阶层客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '小张', 1, '1990-05-15', 35, '中国', '汉族', '北京市海淀区',
  '居民', '北京市海淀区中关村大街1号', '专业技术人员', '软件工程师', 2, NULL, '本科', '学士',
  0, 0, NULL, 0, '工薪阶层', '中', '中',
  '良好', 'B', 75.00, '正式客户', 0,
  0, 'GRID001', '低', '低', 'A',
  1, '北京科技有限公司', '2020-03-15', '2020-06-01', 0, 1, 'admin');

-- 零售客户2: 李四 (VIP客户)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10002', 1, '李四', 'VIP客户', 1,
  1, 1, '优秀', 'AAA', 850.00,
  '电话营销', 'VIP,高净值,理财达人', 'VIP级别客户,理财产品持有量大', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, 'Leo', 1, '1985-08-20', 40, '中国', '汉族', '上海市浦东新区',
  '居民', '上海市浦东新区陆家嘴环路1000号', '企业高管', '总经理', 2, NULL, '硕士', '硕士',
  1, 1, '金卡', 1, '企业高管', '高', '高',
  '优秀', 'AAA', 95.00, '正式客户', 1,
  1, 'GRID002', '低', '低', 'AAA',
  0, NULL, '2018-01-10', '2018-02-01',  0, 1, 'admin');

-- 零售客户3: 王五 (普通客户,女性)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10003', 1, '王五', '普通客户', 1,
  0, 0, '良好', 'B', 720.00,
  '网络营销', '年轻客户,互联网活跃', '互联网渠道获客', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '小王', 2, '1995-03-10', 30, '中国', '汉族', '广东省深圳市',
  '居民', '广东省深圳市南山区科技园', '专业技术人员', '设计师', 1, NULL, '本科', '学士',
  0, 0, NULL, 0, '工薪阶层', '中', '中',
  '良好', 'B', 78.00, '正式客户', 0,
  0, 'GRID003', '低', '低', 'B',
  1, '深圳设计有限公司', '2021-05-20', '2021-07-01',  0, 1, 'admin');

-- 零售客户4: 赵六 (高净值客户)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10004', 1, '赵六', '钻石卡客户', 1,
  1, 1, '优秀', 'AAA', 900.00,
  '客户推荐', '高净值,企业主,投资达人', '企业主,资产规模大', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '赵总', 1, '1978-11-25', 47, '中国', '汉族', '江苏省南京市',
  '居民', '江苏省南京市鼓楼区中央路100号', '个体经营户', '企业主', 2, NULL, '本科', '学士',
  1, 1, '钻石卡', 1, '个体经营户', '高', '高',
  '优秀', 'AAA', 98.00, '正式客户', 1,
  1, 'GRID004', '低', '低', 'AAA',
  0, NULL, '2015-03-01', '2015-04-01',  0, 1, 'admin');

-- 零售客户5: 孙七 (普通客户,代发工资)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10005', 1, '孙七', '普通客户', 1,
  0, 0, '良好', 'A', 760.00,
  '代发工资', '代发客户,稳定收入', '通过企业代发工资渠道获客', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '小孙', 2, '1992-07-08', 33, '中国', '汉族', '浙江省杭州市',
  '居民', '浙江省杭州市西湖区文三路200号', '专业技术人员', '会计师', 2, NULL, '本科', '学士',
  0, 0, NULL, 0, '工薪阶层', '中', '中',
  '良好', 'B', 80.00, '正式客户', 0,
  0, 'GRID005', '低', '低', 'A',
  1, '杭州科技集团有限公司', '2019-08-15', '2019-09-01',  0, 1, 'admin');

-- 零售客户6: 周八 (VIP客户,女性)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10006', 1, '周八', '金卡客户', 1,
  1, 1, '优秀', 'AA', 820.00,
  '客户推荐', 'VIP,理财偏好,消费活跃', '理财产品活跃客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, 'Linda', 2, '1988-04-12', 37, '中国', '汉族', '四川省成都市',
  '居民', '四川省成都市武侯区天府大道1000号', '专业技术人员', '医生', 2, NULL, '硕士', '硕士',
  1, 0, '金卡', 0, '工薪阶层', '高', '中',
  '优秀', 'AA', 88.00, '正式客户', 0,
  1, 'GRID006', '低', '低', 'AA',
  0, NULL, '2017-06-20', '2017-08-01',  0, 1, 'admin');

-- 零售客户7: 吴九 (普通客户)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10007', 1, '吴九', '普通客户', 1,
  0, 0, '一般', 'C', 680.00,
  '网点开发', '年轻客户', '刚毕业大学生', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '小吴', 1, '1998-09-18', 27, '中国', '汉族', '湖北省武汉市',
  '居民', '湖北省武汉市洪山区光谷大道100号', '专业技术人员', '程序员', 1, NULL, '本科', '学士',
  0, 0, NULL, 0, '工薪阶层', '低', '低',
  '一般', 'C', 68.00, '正式客户', 0,
  0, 'GRID007', '中', '中', 'B',
  1, '武汉互联网科技公司', '2023-07-01', '2023-08-01',  0, 1, 'admin');

-- 零售客户8: 郑十 (高端客户,男性)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10008', 1, '郑十', '白金卡客户', 1,
  1, 1, '优秀', 'AAA', 880.00,
  '网点开发', '高端客户,投资者,资产丰富', '私人银行客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '郑总', 1, '1975-12-30', 49, '中国', '汉族', '广东省广州市',
  '居民', '广东省广州市天河区珠江新城', '企业高管', '董事长', 2, NULL, 'MBA', '硕士',
  1, 1, '白金卡', 1, '企业高管', '高', '高',
  '优秀', 'AAA', 96.00, '正式客户', 1,
  1, 'GRID008', '低', '低', 'AAA',
  0, NULL, '2012-05-10', '2012-06-01',  0, 1, 'admin');

-- 零售客户9: 钱十一 (普通客户,女性)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10009', 1, '钱十一', '普通客户', 1,
  0, 0, '良好', 'B', 730.00,
  '电话营销', '年轻客户,消费活跃', '消费贷款客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, 'Amy', 2, '1993-06-22', 32, '中国', '汉族', '陕西省西安市',
  '居民', '陕西省西安市雁塔区科技路50号', '专业技术人员', '教师', 2, NULL, '硕士', '硕士',
  0, 0, NULL, 0, '工薪阶层', '中', '中',
  '良好', 'B', 76.00, '正式客户', 0,
  0, 'GRID009', '低', '低', 'B',
  1, '西安大学', '2020-09-01', '2020-10-01',  0, 1, 'admin');

-- 零售客户10: 冯十二 (银卡客户,男性)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('R10010', 1, '冯十二', '银卡客户', 1,
  0, 0, '良好', 'A', 780.00,
  '网络营销', 'VIP潜力,年轻有为', '有潜力成长为VIP客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, age, nationality, nation, native_place,
  residence_type, domicile_place, occupation, occupation_type, marital_status, religion, education, degree,
  is_vip, is_core_vip, vip_level, is_high_net_worth, net_worth_type, income_level, asset_level,
  reputation_status, reputation_level, reputation_score, retail_customer_type, is_high_end_customer,
  is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level, credit_risk_rating,
  is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified, tenant_id, creator)
VALUES (@customer_id, '小冯', 1, '1991-02-14', 34, '中国', '汉族', '湖南省长沙市',
  '居民', '湖南省长沙市岳麓区麓山南路100号', '专业技术人员', '工程师', 2, NULL, '硕士', '硕士',
  0, 0, '银卡', 0, '工薪阶层', '中', '中',
  '良好', 'A', 82.00, '正式客户', 0,
  1, 'GRID010', '低', '低', 'A',
  1, '长沙制造业有限公司', '2019-11-05', '2019-12-01',  0, 1, 'admin');


-- ============================================
-- 2. 对公客户测试数据 (10条)
-- ============================================

-- 对公客户1: 北京科技有限公司 (小微企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20001', 2, '北京科技有限公司', '普通客户', 1,
  0, 0, '良好', 'BBB', 700.00,
  '网点开发', '小微企业,科技行业', '软件开发企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '110108123456789', '91110108MA01ABCD12', '12345678-9',
  '91110108MA01ABCD12', '11010812345678', '有限责任公司', '民营企业', '私人控股', '其他企业', '小',
  500.00, 'CNY', '2018-03-15', '2018-03-15至2048-03-14',
  '信息传输、软件和信息技术服务业', '软件和信息技术服务业', '软件开发', '应用软件开发', 'I65',
  0, 1, 0, 0, 0, 1,
  0, '中国工商银行北京海淀支行', '0200001234567890',
  '张三', '身份证', '110101198905151234', '13800138001',
  '软件企业认定', '海淀区科技局', '北京市经信委',
  'BBB', '中诚信', '2024-01-15', 1, 'admin');

-- 对公客户2: 上海贸易股份有限公司 (中型企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20002', 2, '上海贸易股份有限公司', '重点客户', 1,
  1, 1, '优秀', 'AA', 820.00,
  '客户推荐', '中型企业,进出口,贸易行业', '重要贸易客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '310115987654321', '91310115MA01EFGH34', '98765432-1',
  '91310115MA01EFGH34', '31011598765432', '股份有限公司', '民营企业', '私人控股', '其他企业', '中',
  5000.00, 'CNY', '2015-06-20', '2015-06-20至长期',
  '批发和零售业', '批发业', '贸易代理业', '进出口代理', 'F51',
  0, 0, 0, 1, 0, 1,
  0, '中国建设银行上海浦东支行', '3100001234567890',
  '李四', '身份证', '310101198508201234', '13900139001',
  '进出口经营许可证', '浦东新区商务委', '上海市商务委',
  'AA', '联合信用', '2024-03-20', 1, 'admin');

-- 对公客户3: 深圳制造有限公司 (大型企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20003', 2, '深圳制造有限公司', '战略客户', 1,
  1, 1, '优秀', 'AAA', 900.00,
  '网点开发', '大型企业,制造业,高科技', '核心制造业客户', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '440300111222333', '91440300MA01IJKL56', '11122233-3',
  '91440300MA01IJKL56', '44030011122233', '有限责任公司', '民营企业', '私人控股', '其他企业', '大',
  50000.00, 'CNY', '2010-08-10', '2010-08-10至长期',
  '制造业', '计算机、通信和其他电子设备制造业', '电子器件制造', '半导体分立器件制造', 'C39',
  1, 0, 1, 1, 0, 1,
  0, '中国银行深圳南山支行', '4403001234567890',
  '王五', '身份证', '440301199503101234', '13700137001',
  '高新技术企业认定,ISO9001质量认证', '南山区科技创新局', '深圳市工信局',
  'AAA', '中证鹏元', '2024-02-10', 1, 'admin');

-- 对公客户4: 杭州电商有限公司 (小微企业,电商)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20004', 2, '杭州电商有限公司', '普通客户', 1,
  0, 0, '良好', 'A', 750.00,
  '网络营销', '小微企业,电商平台', '互联网电商企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '330106444555666', '91330106MA01MNOP78', '44455566-6',
  '91330106MA01MNOP78', '33010644455566', '有限责任公司', '民营企业', '私人控股', '其他企业', '小',
  300.00, 'CNY', '2020-05-18', '2020-05-18至2040-05-17',
  '批发和零售业', '零售业', '互联网零售', '电子商务平台', 'F52',
  0, 1, 0, 0, 0, 1,
  0, '中国农业银行杭州西湖支行', '3301001234567890',
  '孙七', '身份证', '330106199207081234', '13600136001',
  '互联网经营许可证', '西湖区市场监管局', '杭州市商务局',
  'A', '东方金诚', '2024-05-01', 1, 'admin');

-- 对公客户5: 南京建筑工程有限公司 (中型企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20005', 2, '南京建筑工程有限公司', '重点客户', 1,
  1, 0, '良好', 'A', 780.00,
  '网点开发', '中型企业,建筑业,资质齐全', '建筑施工企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '320102777888999', '91320102MA01QRST90', '77788899-9',
  '91320102MA01QRST90', '32010277788899', '有限责任公司', '民营企业', '私人控股', '其他企业', '中',
  8000.00, 'CNY', '2012-11-25', '2012-11-25至长期',
  '建筑业', '房屋建筑业', '住宅房屋建筑', '住宅建筑施工', 'E47',
  0, 0, 0, 0, 0, 1,
  0, '中国工商银行南京鼓楼支行', '3201001234567890',
  '赵六', '身份证', '320102197811251234', '13500135001',
  '建筑施工总承包资质,安全生产许可证', '鼓楼区住建局', '南京市住建委',
  'A', '中诚信', '2024-04-15', 1, 'admin');

-- 对公客户6: 成都餐饮管理有限公司 (小微企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20006', 2, '成都餐饮管理有限公司', '普通客户', 1,
  0, 0, '良好', 'BBB', 690.00,
  '客户推荐', '小微企业,餐饮业,连锁经营', '连锁餐饮企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '510107333444555', '91510107MA01UVWX12', '33344455-5',
  '91510107MA01UVWX12', '51010733344455', '有限责任公司', '民营企业', '私人控股', '其他企业', '小',
  200.00, 'CNY', '2019-04-12', '2019-04-12至2039-04-11',
  '住宿和餐饮业', '餐饮业', '正餐服务', '中餐服务', 'H62',
  0, 1, 0, 0, 0, 1,
  0, '中国建设银行成都武侯支行', '5101001234567890',
  '周八', '身份证', '510107198804121234', '13400134001',
  '食品经营许可证,餐饮服务许可证', '武侯区市场监管局', '成都市商务局',
  'BBB', '联合信用', '2024-06-01', 1, 'admin');

-- 对公客户7: 武汉物流有限公司 (中型企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20007', 2, '武汉物流有限公司', '重点客户', 1,
  1, 0, '良好', 'A', 770.00,
  '网点开发', '中型企业,物流运输,网络覆盖广', '区域物流龙头企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '420111666777888', '91420111MA01YZ0123', '66677788-8',
  '91420111MA01YZ0123', '42011166677788', '有限责任公司', '民营企业', '私人控股', '其他企业', '中',
  3000.00, 'CNY', '2016-09-18', '2016-09-18至长期',
  '交通运输、仓储和邮政业', '道路运输业', '道路货物运输', '普通货物道路运输', 'G54',
  0, 0, 0, 0, 0, 1,
  0, '中国邮政储蓄银行武汉洪山支行', '4201001234567890',
  '吴九', '身份证', '420111199809181234', '13300133001',
  '道路运输经营许可证', '洪山区交通运输局', '武汉市交通运输局',
  'A', '东方金诚', '2024-07-10', 1, 'admin');

-- 对公客户8: 广州医疗器械有限公司 (中型企业,上市公司)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20008', 2, '广州医疗器械有限公司', '战略客户', 1,
  1, 1, '优秀', 'AAA', 880.00,
  '客户推荐', '中型企业,上市公司,医疗行业', '医疗器械龙头企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '440104999000111', '91440104MA01ABC456', '99900011-1',
  '91440104MA01ABC456', '44010499900011', '股份有限公司', '民营企业', '私人控股', '其他企业', '中',
  15000.00, 'CNY', '2008-12-30', '2008-12-30至长期',
  '制造业', '专用设备制造业', '医疗仪器设备及器械制造', '医疗诊断监护设备制造', 'C35',
  1, 0, 1, 1, 0, 1,
  0, '中国银行广州天河支行', '4401001234567890',
  '郑十', '身份证', '440104197512301234', '13200132001',
  '医疗器械生产许可证,ISO13485认证,FDA认证', '天河区卫健局', '广州市药监局',
  'AAA', '中证鹏元', '2024-08-20', 1, 'admin');

-- 对公客户9: 西安软件开发有限公司 (小微企业)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20009', 2, '西安软件开发有限公司', '普通客户', 1,
  0, 0, '良好', 'BBB', 710.00,
  '网络营销', '小微企业,软件行业,创新型', '科技创新企业', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '610113222333444', '91610113MA01DEF789', '22233344-4',
  '91610113MA01DEF789', '61011322233344', '有限责任公司', '民营企业', '私人控股', '其他企业', '小',
  600.00, 'CNY', '2021-06-22', '2021-06-22至2041-06-21',
  '信息传输、软件和信息技术服务业', '软件和信息技术服务业', '软件开发', '行业应用软件开发', 'I65',
  0, 1, 0, 0, 0, 1,
  0, '中国农业银行西安雁塔支行', '6101001234567890',
  '钱十一', '身份证', '610113199306221234', '13100131001',
  '软件企业认定,高新技术企业', '雁塔区科技局', '西安市科技局',
  'BBB', '中诚信', '2024-09-05', 1, 'admin');

-- 对公客户10: 长沙制造业有限公司 (大型企业,集团客户)
INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status,
  is_high_quality, is_important, credit_status, credit_level, credit_score,
  customer_source, customer_tag, remark, tenant_id, dept_id, creator)
VALUES ('C20010', 2, '长沙制造业有限公司', '战略客户', 1,
  1, 1, '优秀', 'AAA', 920.00,
  '网点开发', '大型企业,集团客户,制造业龙头', '省级重点企业,产业链核心', 1, 100, 'admin');

SET @customer_id = LAST_INSERT_ID();
INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
  tax_no, loan_card_no, enterprise_type, enterprise_nature, ownership_type, economic_type, enterprise_scale,
  registered_capital, registered_capital_currency, establish_date, business_term,
  industry_category_l1, industry_category_l2, industry_category_l3, industry_category_l4, industry_code,
  is_listed, is_small_enterprise, is_group_customer, is_import_export, is_related_party, is_ebank_signed,
  is_agriculture_related, basic_account_bank, basic_account_no,
  legal_person_name, legal_person_id_type, legal_person_id_no, legal_person_phone,
  enterprise_qualification, management_dept, supervise_dept,
  company_rating, rating_agency, rating_date, tenant_id, creator)
VALUES (@customer_id, '营业执照', '430104555666777', '91430104MA01GHI012', '55566677-7',
  '91430104MA01GHI012', '43010455566677', '有限责任公司', '国有企业', '国有控股', '国有企业', '大',
  100000.00, 'CNY', '2005-02-14', '2005-02-14至长期',
  '制造业', '通用设备制造业', '金属加工机械制造', '机床制造', 'C34',
  1, 0, 1, 1, 0, 1,
  0, '中国工商银行长沙岳麓支行', '4301001234567890',
  '冯十二', '身份证', '430104199102141234', '13000130001',
  '高新技术企业,国家级企业技术中心,省级制造业单项冠军', '岳麓区工信局', '湖南省工信厅',
  'AAA', '联合信用', '2024-10-01', 1, 'admin');


-- ============================================
-- 3. 验证数据插入结果
-- ============================================

-- 统计客户类型分布
SELECT
  customer_type,
  CASE customer_type
    WHEN 1 THEN '零售客户'
    WHEN 2 THEN '对公客户'
  END AS customer_type_name,
  COUNT(*) AS customer_count
FROM crm_customer
WHERE deleted = 0
GROUP BY customer_type;

-- 验证零售客户扩展表
SELECT COUNT(*) AS retail_customer_count FROM crm_retail_customer WHERE deleted = 0;

-- 验证对公客户扩展表
SELECT COUNT(*) AS company_customer_count FROM crm_company_customer WHERE deleted = 0;

-- 查看零售客户列表(简要信息)
SELECT
  c.customer_no,
  c.customer_name,
  c.customer_level,
  r.gender,
  r.age,
  r.occupation,
  r.is_vip,
  r.vip_level
FROM crm_customer c
JOIN crm_retail_customer r ON c.id = r.customer_id
WHERE c.customer_type = 1 AND c.deleted = 0
ORDER BY c.customer_no;

-- 查看对公客户列表(简要信息)
SELECT
  c.customer_no,
  c.customer_name,
  c.customer_level,
  cc.enterprise_type,
  cc.enterprise_scale,
  cc.registered_capital,
  cc.is_listed,
  cc.is_group_customer
FROM crm_customer c
JOIN crm_company_customer cc ON c.id = cc.customer_id
WHERE c.customer_type = 2 AND c.deleted = 0
ORDER BY c.customer_no;

-- ============================================
-- 脚本结束
-- ============================================
