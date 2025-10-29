-- ----------------------------
-- 客户担保信息测试数据
-- 创建至少15条抵押物信息、15条质押物信息、15条担保人信息
-- 10个零售客户（R10001-R10010）
-- 10个对公客户（C20001-C20010）
-- ----------------------------

-- ==================== 客户抵押物信息测试数据（15条） ====================

INSERT INTO crm_customer_guarantee_mortgage (
  customer_id, credit_id, collateral_no, collateral_name, collateral_type, certificate_no,
  guarantee_type, mortgagor_name, mortgagor_type, relation_with_borrower, guarantee_amount,
  management_branch_id, management_branch_name, mortgagor_id_type, mortgagor_id_no,
  collateral_address, collateral_area, evaluation_value, evaluation_date, evaluation_agency,
  mortgage_rate, mortgage_status, mortgage_date, creator, updater, tenant_id
) VALUES
-- 零售客户抵押物（8条）
(2, 2, 'MG001-2024-001', '北京朝阳区住宅', 'residence', '京（2024）朝阳不动产权第0001号',
 'mortgage', '李四', 'person', 'self', 200.00,
 102, '泰布', 'id_card', '230102198505****',
 '北京市朝阳区建国路88号', 120.50, 250.00, '2024-06-01', '北京房地产评估有限公司',
 80.00, 'effective', '2024-06-15', '1', '1', 0),

(3, 3, 'MG002-2025-001', '上海浦东新区商铺', 'shop', '沪（2025）浦东不动产权第0002号',
 'mortgage', '王五', 'person', 'self', 50.00,
 102, '泰布', 'id_card', '370283199203****',
 '上海市浦东新区陆家嘴环路100号', 80.00, 60.00, '2025-03-10', '上海房产评估中心',
 83.33, 'effective', '2025-03-20', '1', '1', 0),

(6, 7, 'MG003-2023-001', '杭州西湖区住宅', 'residence', '浙（2023）杭州不动产权第0003号',
 'mortgage', '周八', 'person', 'self', 150.00,
 102, '泰布', 'id_card', '330108199605****',
 '浙江省杭州市西湖区文三路150号', 95.00, 180.00, '2023-05-10', '浙江评估事务所',
 83.33, 'effective', '2023-05-20', '1', '1', 0),

(7, 8, 'MG004-2024-001', '成都高新区写字楼', 'office', '川（2024）成都不动产权第0004号',
 'mortgage', '吴九', 'person', 'self', 80.00,
 102, '泰布', 'id_card', '510107199703****',
 '四川省成都市高新区天府大道200号', 150.00, 100.00, '2024-12-01', '四川房地产评估公司',
 80.00, 'effective', '2024-12-10', '1', '1', 0),

(8, 9, 'MG005-2025-001', '广州天河区住宅', 'residence', '粤（2025）广州不动产权第0005号',
 'mortgage', '郑十', 'person', 'self', 100.00,
 102, '泰布', 'id_card', '440104199801****',
 '广东省广州市天河区珠江新城花城大道88号', 110.00, 120.00, '2025-04-10', '广州资产评估中心',
 83.33, 'effective', '2025-04-15', '1', '1', 0),

(1, 1, 'MG006-2025-001', '北京海淀区住宅', 'residence', '京（2025）海淀不动产权第0006号',
 'mortgage', '张三', 'person', 'self', 50.00,
 102, '泰布', 'id_card', '150105131119****',
 '北京市海淀区中关村大街1号', 85.00, 60.00, '2025-01-05', '北京房地产评估有限公司',
 83.33, 'effective', '2025-01-10', '1', '1', 0),

(10, 11, 'MG007-2024-001', '重庆渝中区商铺', 'shop', '渝（2024）渝中不动产权第0007号',
 'mortgage', '冯十二', 'person', 'self', 250.00,
 102, '泰布', 'id_card', '500104200002****',
 '重庆市渝中区解放碑商圈步行街50号', 120.00, 300.00, '2024-02-25', '重庆评估公司',
 83.33, 'effective', '2024-03-10', '1', '1', 0),

(4, 4, 'MG008-2020-001', '西安雁塔区住宅', 'residence', '陕（2020）西安不动产权第0008号',
 'mortgage', '赵六', 'person', 'self', 50.00,
 102, '泰布', 'id_card', '610103199408****',
 '陕西省西安市雁塔区高新路88号', 90.00, 60.00, '2020-08-10', '陕西房产评估所',
 83.33, 'effective', '2020-08-20', '1', '1', 0),

-- 对公客户抵押物（7条）
(11, 12, 'MG101-2025-001', '北京朝阳区写字楼', 'office', '京（2025）朝阳不动产权第1001号',
 'mortgage', '北京科技有限公司', 'company', 'self', 1000.00,
 103, '中心', 'unified_credit_code', '91110108MA01****',
 '北京市朝阳区建外大街88号', 2000.00, 1200.00, '2025-01-10', '北京房地产评估有限公司',
 83.33, 'effective', '2025-01-15', '1', '1', 0),

(13, 14, 'MG102-2024-001', '深圳南山区厂房', 'factory', '粤（2024）深圳不动产权第1002号',
 'mortgage', '深圳制造有限公司', 'company', 'self', 2000.00,
 103, '中心', 'unified_credit_code', '91440300MA5E****',
 '广东省深圳市南山区科技园南区', 5000.00, 2400.00, '2024-07-10', '深圳资产评估公司',
 83.33, 'effective', '2024-07-20', '1', '1', 0),

(15, 16, 'MG103-2024-001', '南京建邺区土地使用权', 'land', '苏（2024）南京不动产权第1003号',
 'mortgage', '南京建筑工程有限公司', 'company', 'self', 3000.00,
 103, '中心', 'unified_credit_code', '91320104MA1U****',
 '江苏省南京市建邺区河西新城', 10000.00, 3600.00, '2024-09-05', '江苏评估事务所',
 83.33, 'effective', '2024-09-15', '1', '1', 0),

(12, 13, 'MG104-2024-001', '上海浦东新区写字楼', 'office', '沪（2024）浦东不动产权第1004号',
 'mortgage', '上海贸易股份有限公司', 'company', 'self', 1500.00,
 103, '中心', 'unified_credit_code', '91310115MA1H****',
 '上海市浦东新区陆家嘴金融贸易区', 3000.00, 1800.00, '2024-10-20', '上海房产评估中心',
 83.33, 'effective', '2024-11-01', '1', '1', 0),

(14, 15, 'MG105-2025-001', '杭州滨江区在建工程', 'construction', '浙（2025）杭州不动产权第1005号',
 'mortgage', '杭州电商有限公司', 'company', 'self', 500.00,
 103, '中心', 'unified_credit_code', '91330108MA2C****',
 '浙江省杭州市滨江区网商路', 1500.00, 600.00, '2025-02-05', '浙江评估事务所',
 83.33, 'effective', '2025-02-10', '1', '1', 0),

(18, 20, 'MG106-2024-001', '广州天河区机器设备', 'equipment', '粤（2024）广州动产第1006号',
 'mortgage', '广州医疗器械有限公司', 'company', 'self', 1200.00,
 103, '中心', 'unified_credit_code', '91440104MA5A****',
 '广东省广州市天河区科学城', NULL, 1440.00, '2024-07-20', '广州资产评估中心',
 83.33, 'effective', '2024-08-01', '1', '1', 0),

(20, 22, 'MG107-2024-001', '长沙雨花区厂房', 'factory', '湘（2024）长沙不动产权第1007号',
 'mortgage', '长沙制造业有限公司', 'company', 'self', 1800.00,
 103, '中心', 'unified_credit_code', '91430104MA4L****',
 '湖南省长沙市雨花区经济开发区', 4000.00, 2160.00, '2024-10-05', '湖南评估公司',
 83.33, 'effective', '2024-10-15', '1', '1', 0);

-- ==================== 客户质押物信息测试数据（15条） ====================

INSERT INTO crm_customer_guarantee_pledge (
  customer_id, credit_id, collateral_no, collateral_type, guarantee_type,
  pledgor_id_type, pledgor_id_no, pledgor_name, pledgor_type, relation_with_borrower,
  guarantee_amount, pledge_rate, collateral_name, collateral_value, pledge_status,
  pledge_date, management_branch_id, management_branch_name, creator, updater, tenant_id
) VALUES
-- 零售客户质押物（8条）
(1, 1, 'PL001-2025-001', 'deposit_receipt', 'pledge',
 'id_card', '150105131119****', '张三', 'person', 'self',
 50.00, 90.00, '大额存单001', 55.56, 'effective',
 '2025-01-10', 102, '泰布', '1', '1', 0),

(5, 5, 'PL002-2025-002', 'deposit_receipt', 'pledge',
 'id_card', '420106199501****', '孙七', 'person', 'self',
 80.00, 90.00, '定期存单002', 88.89, 'effective',
 '2025-02-01', 102, '泰布', '1', '1', 0),

(5, 6, 'PL003-2025-003', 'policy', 'pledge',
 'id_card', '420106199501****', '孙七', 'person', 'self',
 30.00, 80.00, '人寿保单003', 37.50, 'effective',
 '2025-02-01', 102, '泰布', '1', '1', 0),

(9, 9, 'PL004-2023-001', 'treasury_bond', 'pledge',
 'id_card', '320106199908****', '钱十一', 'person', 'self',
 50.00, 95.00, '国债004', 52.63, 'effective',
 '2023-08-15', 102, '泰布', '1', '1', 0),

(2, 2, 'PL005-2019-001', 'deposit_receipt', 'pledge',
 'id_card', '230102198505****', '李四', 'person', 'self',
 150.00, 90.00, '大额存单005', 166.67, 'effective',
 '2019-01-15', 102, '泰布', '1', '1', 0),

(3, 3, 'PL006-2020-001', 'policy', 'pledge',
 'id_card', '370283199203****', '王五', 'person', 'self',
 20.00, 80.00, '财产保险单006', 25.00, 'effective',
 '2020-03-10', 102, '泰布', '1', '1', 0),

(6, 7, 'PL007-2021-001', 'deposit_receipt', 'pledge',
 'id_card', '330108199605****', '周八', 'person', 'self',
 50.00, 90.00, '定期存单007', 55.56, 'effective',
 '2021-05-10', 102, '泰布', '1', '1', 0),

(10, 11, 'PL008-2024-001', 'treasury_bond', 'pledge',
 'id_card', '500104200002****', '冯十二', 'person', 'self',
 250.00, 95.00, '国债008', 263.16, 'effective',
 '2024-02-20', 102, '泰布', '1', '1', 0),

-- 对公客户质押物（7条）
(11, 12, 'PL101-2025-001', 'receivable', 'pledge',
 'unified_credit_code', '91110108MA01****', '北京科技有限公司', 'company', 'self',
 600.00, 70.00, '应收账款101', 857.14, 'effective',
 '2025-01-15', 103, '中心', '1', '1', 0),

(12, 13, 'PL102-2024-001', 'equity', 'pledge',
 'unified_credit_code', '91310115MA1H****', '上海贸易股份有限公司', 'company', 'self',
 1000.00, 60.00, '子公司股权102', 1666.67, 'effective',
 '2024-11-01', 103, '中心', '1', '1', 0),

(13, 14, 'PL103-2024-001', 'warehouse_receipt', 'pledge',
 'unified_credit_code', '91440300MA5E****', '深圳制造有限公司', 'company', 'self',
 1500.00, 75.00, '产成品仓单103', 2000.00, 'effective',
 '2024-07-20', 103, '中心', '1', '1', 0),

(14, 15, 'PL104-2025-001', 'receivable', 'pledge',
 'unified_credit_code', '91330108MA2C****', '杭州电商有限公司', 'company', 'self',
 500.00, 70.00, '应收账款104', 714.29, 'effective',
 '2025-02-10', 103, '中心', '1', '1', 0),

(17, 18, 'PL105-2024-001', 'warehouse_receipt', 'pledge',
 'unified_credit_code', '91420106MA4K****', '武汉物流有限公司', 'company', 'self',
 800.00, 75.00, '货物仓单105', 1066.67, 'effective',
 '2024-12-20', 103, '中心', '1', '1', 0),

(19, 21, 'PL106-2025-001', 'intellectual_property', 'pledge',
 'unified_credit_code', '91610113MA6U****', '西安软件开发有限公司', 'company', 'self',
 400.00, 50.00, '软件著作权106', 800.00, 'effective',
 '2025-04-01', 103, '中心', '1', '1', 0),

(20, 22, 'PL107-2024-001', 'receivable', 'pledge',
 'unified_credit_code', '91430104MA4L****', '长沙制造业有限公司', 'company', 'self',
 1800.00, 70.00, '应收账款107', 2571.43, 'effective',
 '2024-10-15', 103, '中心', '1', '1', 0);

-- ==================== 客户担保人信息测试数据（15条） ====================

INSERT INTO crm_customer_guarantor (
  customer_id, credit_id, contract_no, contract_type, contract_status, sign_date,
  guarantee_type, guarantor_no, guarantor_name, currency_code, guarantee_total_amount,
  business_start_date, business_end_date, guarantor_type, guarantor_id_type, guarantor_id_no,
  relation_with_borrower, guarantee_method, used_amount, available_amount,
  manager_user_id, branch_id, creator, updater, tenant_id
) VALUES
-- 零售客户担保人（8条）
(1, 1, 'GC001-2025-001', 'joint_guarantee', 'effective', '2025-01-10',
 'guarantee', 'GT001', '张伟（张三父亲）', 'CNY', 50.00,
 '2025-01-10', '2026-01-10', 'person', 'id_card', '150105195805****',
 'parent', 'joint', 50.00, 0.00,
 104, 102, '1', '1', 0),

(3, 3, 'GC002-2025-001', 'joint_guarantee', 'effective', '2025-03-20',
 'guarantee', 'GT002', '王芳（王五配偶）', 'CNY', 50.00,
 '2025-03-20', '2026-03-20', 'person', 'id_card', '370283199305****',
 'spouse', 'joint', 30.00, 20.00,
 105, 102, '1', '1', 0),

(4, 4, 'GC003-2020-001', 'joint_guarantee', 'effective', '2020-08-20',
 'guarantee', 'GT003', '赵刚（赵六兄弟）', 'CNY', 50.00,
 '2020-08-20', '2027-08-20', 'person', 'id_card', '610103199210****',
 'sibling', 'joint', 20.00, 30.00,
 104, 102, '1', '1', 0),

(7, 8, 'GC004-2024-001', 'maximum_guarantee', 'effective', '2024-12-10',
 'guarantee', 'GT004', '吴强（吴九父亲）', 'CNY', 100.00,
 '2024-12-10', '2025-12-10', 'person', 'id_card', '510107196503****',
 'parent', 'joint', 60.00, 40.00,
 105, 102, '1', '1', 0),

(8, 9, 'GC005-2025-001', 'joint_guarantee', 'effective', '2025-04-15',
 'guarantee', 'GT005', '郑丽（郑十配偶）', 'CNY', 100.00,
 '2025-04-15', '2026-04-15', 'person', 'id_card', '440104199903****',
 'spouse', 'joint', 100.00, 0.00,
 104, 102, '1', '1', 0),

(2, 2, 'GC006-2024-001', 'joint_guarantee', 'effective', '2024-06-15',
 'guarantee', 'GT006', '李明（李四父亲）', 'CNY', 200.00,
 '2024-06-15', '2044-06-15', 'person', 'id_card', '230102195805****',
 'parent', 'joint', 150.00, 50.00,
 104, 102, '1', '1', 0),

(6, 7, 'GC007-2023-001', 'maximum_guarantee', 'effective', '2023-05-20',
 'guarantee', 'GT007', '周华（周八配偶）', 'CNY', 150.00,
 '2023-05-20', '2043-05-20', 'person', 'id_card', '330108199708****',
 'spouse', 'joint', 120.00, 30.00,
 104, 102, '1', '1', 0),

(10, 11, 'GC008-2024-001', 'joint_guarantee', 'effective', '2024-03-10',
 'guarantee', 'GT008', '冯军（冯十二父亲）', 'CNY', 300.00,
 '2024-03-10', '2044-03-10', 'person', 'id_card', '500104197002****',
 'parent', 'joint', 250.00, 50.00,
 104, 102, '1', '1', 0),

-- 对公客户担保人（7条）
(11, 12, 'GC101-2025-001', 'maximum_guarantee', 'effective', '2025-01-15',
 'guarantee', 'GT101', '北京投资集团有限公司', 'CNY', 1000.00,
 '2025-01-15', '2026-01-15', 'company', 'unified_credit_code', '91110000MA00****',
 'shareholder', 'joint', 600.00, 400.00,
 106, 103, '1', '1', 0),

(12, 13, 'GC102-2024-001', 'maximum_guarantee', 'effective', '2024-11-01',
 'guarantee', 'GT102', '上海实业控股有限公司', 'CNY', 1500.00,
 '2024-11-01', '2025-11-01', 'company', 'unified_credit_code', '91310000MA01****',
 'related_company', 'joint', 1000.00, 500.00,
 107, 103, '1', '1', 0),

(13, 14, 'GC103-2024-001', 'maximum_guarantee', 'effective', '2024-07-20',
 'guarantee', 'GT103', '深圳产业集团', 'CNY', 2000.00,
 '2024-07-20', '2029-07-20', 'company', 'unified_credit_code', '91440300MA02****',
 'shareholder', 'joint', 1500.00, 500.00,
 106, 103, '1', '1', 0),

(15, 16, 'GC104-2024-001', 'maximum_guarantee', 'effective', '2024-09-15',
 'guarantee', 'GT104', '南京建设投资集团', 'CNY', 3000.00,
 '2024-09-15', '2027-09-15', 'company', 'unified_credit_code', '91320100MA03****',
 'related_company', 'joint', 2000.00, 1000.00,
 106, 103, '1', '1', 0),

(14, 15, 'GC105-2025-001', 'joint_guarantee', 'effective', '2025-02-10',
 'guarantee', 'GT105', '浙江电商控股有限公司', 'CNY', 800.00,
 '2025-02-10', '2026-02-10', 'company', 'unified_credit_code', '91330000MA04****',
 'shareholder', 'joint', 500.00, 300.00,
 107, 103, '1', '1', 0),

(18, 20, 'GC106-2024-001', 'maximum_guarantee', 'effective', '2024-08-01',
 'guarantee', 'GT106', '广州医药集团', 'CNY', 1800.00,
 '2024-08-01', '2029-08-01', 'company', 'unified_credit_code', '91440000MA05****',
 'related_company', 'joint', 1200.00, 600.00,
 107, 103, '1', '1', 0),

(20, 22, 'GC107-2024-001', 'maximum_guarantee', 'effective', '2024-10-15',
 'guarantee', 'GT107', '湖南制造业投资集团', 'CNY', 2500.00,
 '2024-10-15', '2025-10-15', 'company', 'unified_credit_code', '91430000MA06****',
 'shareholder', 'joint', 1800.00, 700.00,
 107, 103, '1', '1', 0);

-- ==================== 验证数据 ====================

-- 1. 统计各客户类型的担保信息数
SELECT
  c.customer_type,
  CASE c.customer_type WHEN 1 THEN '零售客户' WHEN 2 THEN '对公客户' END AS 客户类型,
  COUNT(DISTINCT m.customer_id) AS 抵押客户数,
  COUNT(m.id) AS 抵押物记录数,
  SUM(m.guarantee_amount) AS 抵押担保总额,
  COUNT(DISTINCT p.customer_id) AS 质押客户数,
  COUNT(p.id) AS 质押物记录数,
  SUM(p.guarantee_amount) AS 质押担保总额,
  COUNT(DISTINCT g.customer_id) AS 担保人客户数,
  COUNT(g.id) AS 担保人记录数,
  SUM(g.guarantee_total_amount) AS 保证担保总额
FROM crm_customer c
LEFT JOIN crm_customer_guarantee_mortgage m ON c.id = m.customer_id AND m.deleted = 0
LEFT JOIN crm_customer_guarantee_pledge p ON c.id = p.customer_id AND p.deleted = 0
LEFT JOIN crm_customer_guarantor g ON c.id = g.customer_id AND g.deleted = 0
WHERE c.deleted = 0
GROUP BY c.customer_type;

-- 2. 统计抵押物类型分布
SELECT
  d.label AS 抵押物类型,
  COUNT(*) AS 记录数,
  SUM(m.guarantee_amount) AS 担保金额,
  AVG(m.mortgage_rate) AS 平均抵押率
FROM crm_customer_guarantee_mortgage m
JOIN system_dict_data d ON m.collateral_type = d.value AND d.dict_type = 'aicrm_mortgage_type'
WHERE m.deleted = 0
GROUP BY m.collateral_type, d.label
ORDER BY 记录数 DESC;

-- 3. 统计质押物类型分布
SELECT
  d.label AS 质押物类型,
  COUNT(*) AS 记录数,
  SUM(p.guarantee_amount) AS 担保金额,
  AVG(p.pledge_rate) AS 平均质押率
FROM crm_customer_guarantee_pledge p
JOIN system_dict_data d ON p.collateral_type = d.value AND d.dict_type = 'aicrm_pledge_type'
WHERE p.deleted = 0
GROUP BY p.collateral_type, d.label
ORDER BY 记录数 DESC;

-- 4. 统计担保人类型分布
SELECT
  d.label AS 担保人类型,
  COUNT(*) AS 记录数,
  SUM(g.guarantee_total_amount) AS 担保总额,
  SUM(g.used_amount) AS 已用金额,
  SUM(g.available_amount) AS 可用金额
FROM crm_customer_guarantor g
JOIN system_dict_data d ON g.guarantor_type = d.value AND d.dict_type = 'aicrm_guarantor_type'
WHERE g.deleted = 0
GROUP BY g.guarantor_type, d.label;

-- 5. 统计担保状态分布
SELECT
  '抵押物' AS 担保方式,
  d.label AS 状态,
  COUNT(*) AS 记录数
FROM crm_customer_guarantee_mortgage m
JOIN system_dict_data d ON m.mortgage_status = d.value AND d.dict_type = 'aicrm_mortgage_status'
WHERE m.deleted = 0
GROUP BY m.mortgage_status, d.label
UNION ALL
SELECT
  '质押物' AS 担保方式,
  d.label AS 状态,
  COUNT(*) AS 记录数
FROM crm_customer_guarantee_pledge p
JOIN system_dict_data d ON p.pledge_status = d.value AND d.dict_type = 'aicrm_pledge_status'
WHERE p.deleted = 0
GROUP BY p.pledge_status, d.label
UNION ALL
SELECT
  '保证人' AS 担保方式,
  d.label AS 状态,
  COUNT(*) AS 记录数
FROM crm_customer_guarantor g
JOIN system_dict_data d ON g.contract_status = d.value AND d.dict_type = 'aicrm_guarantor_contract_status'
WHERE g.deleted = 0
GROUP BY g.contract_status, d.label;

-- 6. 查询每个客户的担保信息汇总
SELECT
  c.customer_no AS 客户编号,
  c.customer_name AS 客户名称,
  CASE c.customer_type WHEN 1 THEN '零售' WHEN 2 THEN '对公' END AS 客户类型,
  COUNT(DISTINCT m.id) AS 抵押物数量,
  COALESCE(SUM(m.guarantee_amount), 0) AS 抵押担保总额,
  COUNT(DISTINCT p.id) AS 质押物数量,
  COALESCE(SUM(p.guarantee_amount), 0) AS 质押担保总额,
  COUNT(DISTINCT g.id) AS 担保人数量,
  COALESCE(SUM(g.guarantee_total_amount), 0) AS 保证担保总额,
  COALESCE(SUM(m.guarantee_amount), 0) + COALESCE(SUM(p.guarantee_amount), 0) + COALESCE(SUM(g.guarantee_total_amount), 0) AS 担保总额
FROM crm_customer c
LEFT JOIN crm_customer_guarantee_mortgage m ON c.id = m.customer_id AND m.deleted = 0
LEFT JOIN crm_customer_guarantee_pledge p ON c.id = p.customer_id AND p.deleted = 0
LEFT JOIN crm_customer_guarantor g ON c.id = g.customer_id AND g.deleted = 0
WHERE c.deleted = 0
GROUP BY c.id, c.customer_no, c.customer_name, c.customer_type
HAVING COUNT(DISTINCT m.id) > 0 OR COUNT(DISTINCT p.id) > 0 OR COUNT(DISTINCT g.id) > 0
ORDER BY c.id;

-- 7. 查询担保覆盖率最高的前10个客户
SELECT
  c.customer_no AS 客户编号,
  c.customer_name AS 客户名称,
  cr.credit_limit AS 授信额度,
  COALESCE(SUM(m.guarantee_amount), 0) + COALESCE(SUM(p.guarantee_amount), 0) + COALESCE(SUM(g.guarantee_total_amount), 0) AS 担保总额,
  ROUND((COALESCE(SUM(m.guarantee_amount), 0) + COALESCE(SUM(p.guarantee_amount), 0) + COALESCE(SUM(g.guarantee_total_amount), 0)) / cr.credit_limit * 100, 2) AS 担保覆盖率
FROM crm_customer c
JOIN crm_customer_credit cr ON c.id = cr.customer_id AND cr.deleted = 0
LEFT JOIN crm_customer_guarantee_mortgage m ON c.id = m.customer_id AND m.deleted = 0
LEFT JOIN crm_customer_guarantee_pledge p ON c.id = p.customer_id AND p.deleted = 0
LEFT JOIN crm_customer_guarantor g ON c.id = g.customer_id AND g.deleted = 0
WHERE c.deleted = 0
GROUP BY c.id, c.customer_no, c.customer_name, cr.credit_limit
HAVING COUNT(DISTINCT m.id) > 0 OR COUNT(DISTINCT p.id) > 0 OR COUNT(DISTINCT g.id) > 0
ORDER BY 担保覆盖率 DESC
LIMIT 10;
