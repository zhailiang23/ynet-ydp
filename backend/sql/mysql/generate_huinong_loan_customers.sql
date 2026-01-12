-- =====================================================
-- 生成惠农贷款目标客户测试数据
-- 说明：支持多对多关系，部分客户会关联多个惠农站点
-- =====================================================

-- 清理旧的测试数据（仅删除测试客户）
DELETE FROM grid_customer_grid_relation WHERE customer_id IN (
    SELECT id FROM grid_customer WHERE customer_type = 'HUINONG_LOAN' AND phone LIKE '139%'
);
DELETE FROM grid_customer WHERE customer_type = 'HUINONG_LOAN' AND phone LIKE '139%';

-- =====================================================
-- 为郑州中原惠农站（ID=53）生成客户
-- =====================================================
INSERT INTO grid_customer (
    grid_id, customer_type, customer_name, gender, phone, address, location,
    customer_category, subdivision_type, business_address, customer_situation,
    business_years, current_financing, credit_demand, demand_month, demand_period,
    business_progress, customer_source, is_applied, is_approved, is_formal_customer,
    source, status, creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 传统种植户（5个）
(53, 'HUINONG_LOAN', '张大伟', '男', '13900001001', '郑州市中原区建设路100号', ST_GeomFromText('POINT(34.7466 113.625)', 4326),
 '种植养殖户', '传统种植户', '郑州市中原区建设路100号', '老客户', 8, '80万', '150万', '2025-03', '上旬', '意向阶段', '外拓获客', 0, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '李秀芳', '女', '13900001002', '郑州市中原区桐柏路200号', ST_GeomFromText('POINT(34.7480 113.620)', 4326),
 '种植养殖户', '传统种植户', '郑州市中原区桐柏路200号', '新客户', 5, '50万', '100万', '2025-04', '中旬', '洽谈阶段', '行内客户', 1, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '王建国', '男', '13900001003', '郑州市中原区工人路300号', ST_GeomFromText('POINT(34.7450 113.630)', 4326),
 '种植养殖户', '传统种植户', '郑州市中原区工人路300号', '老客户', 12, '120万', '200万', '2025-03', '下旬', '审批阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '赵明华', '男', '13900001004', '郑州市中原区伊河路400号', ST_GeomFromText('POINT(34.7440 113.615)', 4326),
 '种植养殖户', '传统种植户', '郑州市中原区伊河路400号', '新客户', 3, '30万', '80万', '2025-05', '上旬', '意向阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '孙丽娟', '女', '13900001005', '郑州市中原区棉纺路500号', ST_GeomFromText('POINT(34.7490 113.610)', 4326),
 '种植养殖户', '传统种植户', '郑州市中原区棉纺路500号', '老客户', 10, '100万', '180万', '2025-04', '中旬', '放款阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 传统养殖户（3个）
(53, 'HUINONG_LOAN', '周大山', '男', '13900001006', '郑州市中原区西三环600号', ST_GeomFromText('POINT(34.7420 113.605)', 4326),
 '种植养殖户', '传统养殖户', '郑州市中原区西三环600号', '老客户', 15, '200万', '300万', '2025-03', '上旬', '审批阶段', '行内客户', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '吴小云', '女', '13900001007', '郑州市中原区秦岭路700号', ST_GeomFromText('POINT(34.7510 113.600)', 4326),
 '种植养殖户', '传统养殖户', '郑州市中原区秦岭路700号', '新客户', 6, '60万', '120万', '2025-05', '中旬', '洽谈阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '郑国强', '男', '13900001008', '郑州市中原区中原路800号', ST_GeomFromText('POINT(34.7470 113.595)', 4326),
 '种植养殖户', '传统养殖户', '郑州市中原区中原路800号', '老客户', 9, '90万', '160万', '2025-04', '下旬', '意向阶段', '行内客户', 0, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 个体工商户（2个）
(53, 'HUINONG_LOAN', '刘德华', '男', '13900001009', '郑州市中原区航海路900号', ST_GeomFromText('POINT(34.7430 113.590)', 4326),
 '个体工商户', '零售商户', '郑州市中原区航海路900号', '新客户', 4, '40万', '90万', '2025-06', '上旬', '洽谈阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(53, 'HUINONG_LOAN', '马晓梅', '女', '13900001010', '郑州市中原区淮河路1000号', ST_GeomFromText('POINT(34.7500 113.585)', 4326),
 '个体工商户', '服务商户', '郑州市中原区淮河路1000号', '老客户', 7, '70万', '140万', '2025-05', '中旬', '审批阶段', '行内客户', 1, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1);

-- =====================================================
-- 为郑州金水惠农站（ID=54）生成客户
-- =====================================================
INSERT INTO grid_customer (
    grid_id, customer_type, customer_name, gender, phone, address, location,
    customer_category, subdivision_type, business_address, customer_situation,
    business_years, current_financing, credit_demand, demand_month, demand_period,
    business_progress, customer_source, is_applied, is_approved, is_formal_customer,
    source, status, creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 传统种植户（4个）
(54, 'HUINONG_LOAN', '陈建平', '男', '13900002001', '郑州市金水区花园路100号', ST_GeomFromText('POINT(34.7680 113.665)', 4326),
 '种植养殖户', '传统种植户', '郑州市金水区花园路100号', '新客户', 6, '60万', '110万', '2025-04', '上旬', '意向阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '林雪梅', '女', '13900002002', '郑州市金水区农业路200号', ST_GeomFromText('POINT(34.7690 113.670)', 4326),
 '种植养殖户', '传统种植户', '郑州市金水区农业路200号', '老客户', 11, '110万', '190万', '2025-03', '中旬', '放款阶段', '行内客户', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '黄国庆', '男', '13900002003', '郑州市金水区东风路300号', ST_GeomFromText('POINT(34.7670 113.675)', 4326),
 '种植养殖户', '传统种植户', '郑州市金水区东风路300号', '新客户', 5, '50万', '100万', '2025-05', '下旬', '洽谈阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '杨秀英', '女', '13900002004', '郑州市金水区经三路400号', ST_GeomFromText('POINT(34.7700 113.660)', 4326),
 '种植养殖户', '传统种植户', '郑州市金水区经三路400号', '老客户', 14, '140万', '220万', '2025-04', '上旬', '审批阶段', '行内客户', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 传统养殖户（3个）
(54, 'HUINONG_LOAN', '徐大龙', '男', '13900002005', '郑州市金水区北环路500号', ST_GeomFromText('POINT(34.7710 113.655)', 4326),
 '种植养殖户', '传统养殖户', '郑州市金水区北环路500号', '新客户', 4, '40万', '85万', '2025-06', '中旬', '意向阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '许春燕', '女', '13900002006', '郑州市金水区文化路600号', ST_GeomFromText('POINT(34.7650 113.680)', 4326),
 '种植养殖户', '传统养殖户', '郑州市金水区文化路600号', '老客户', 13, '130万', '210万', '2025-03', '下旬', '放款阶段', '行内客户', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '何志强', '男', '13900002007', '郑州市金水区丰产路700号', ST_GeomFromText('POINT(34.7720 113.650)', 4326),
 '种植养殖户', '传统养殖户', '郑州市金水区丰产路700号', '新客户', 7, '70万', '130万', '2025-05', '上旬', '洽谈阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 个体工商户（3个）
(54, 'HUINONG_LOAN', '曹丽华', '女', '13900002008', '郑州市金水区纬五路800号', ST_GeomFromText('POINT(34.7660 113.685)', 4326),
 '个体工商户', '零售商户', '郑州市金水区纬五路800号', '老客户', 8, '80万', '150万', '2025-04', '中旬', '审批阶段', '行内客户', 1, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '邓伟民', '男', '13900002009', '郑州市金水区黄河路900号', ST_GeomFromText('POINT(34.7730 113.645)', 4326),
 '个体工商户', '服务商户', '郑州市金水区黄河路900号', '新客户', 5, '50万', '95万', '2025-06', '下旬', '意向阶段', '外拓获客', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(54, 'HUINONG_LOAN', '范小红', '女', '13900002010', '郑州市金水区红专路1000号', ST_GeomFromText('POINT(34.7640 113.690)', 4326),
 '个体工商户', '零售商户', '郑州市金水区红专路1000号', '老客户', 10, '100万', '175万', '2025-05', '上旬', '放款阶段', '行内客户', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1);

-- =====================================================
-- 为郑州二七惠农站（ID=55）生成客户
-- =====================================================
INSERT INTO grid_customer (
    grid_id, customer_type, customer_name, gender, phone, address, location,
    customer_category, subdivision_type, business_address, customer_situation,
    business_years, current_financing, credit_demand, demand_month, demand_period,
    business_progress, customer_source, is_applied, is_approved, is_formal_customer,
    source, status, creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 传统种植户（5个）
(55, 'HUINONG_LOAN', '冯大海', '男', '13900003001', '郑州市二七区大学路100号', ST_GeomFromText('POINT(34.7260 113.640)', 4326),
 '种植养殖户', '传统种植户', '郑州市二七区大学路100号', '老客户', 9, '90万', '165万', '2025-03', '上旬', '审批阶段', '外拓获客', 1, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '高秀兰', '女', '13900003002', '郑州市二七区嵩山路200号', ST_GeomFromText('POINT(34.7250 113.635)', 4326),
 '种植养殖户', '传统种植户', '郑州市二七区嵩山路200号', '新客户', 4, '40万', '75万', '2025-04', '中旬', '意向阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '韩建军', '男', '13900003003', '郑州市二七区陇海路300号', ST_GeomFromText('POINT(34.7240 113.645)', 4326),
 '种植养殖户', '传统种植户', '郑州市二七区陇海路300号', '老客户', 16, '160万', '250万', '2025-03', '下旬', '放款阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '江秀芳', '女', '13900003004', '郑州市二七区人民路400号', ST_GeomFromText('POINT(34.7270 113.630)', 4326),
 '种植养殖户', '传统种植户', '郑州市二七区人民路400号', '新客户', 6, '60万', '115万', '2025-05', '上旬', '洽谈阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '孔德强', '男', '13900003005', '郑州市二七区解放路500号', ST_GeomFromText('POINT(34.7230 113.650)', 4326),
 '种植养殖户', '传统种植户', '郑州市二七区解放路500号', '老客户', 12, '120万', '200万', '2025-04', '中旬', '审批阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 传统养殖户（2个）
(55, 'HUINONG_LOAN', '雷小军', '男', '13900003006', '郑州市二七区长江路600号', ST_GeomFromText('POINT(34.7280 113.625)', 4326),
 '种植养殖户', '传统养殖户', '郑州市二七区长江路600号', '新客户', 5, '50万', '90万', '2025-06', '下旬', '意向阾段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '罗春梅', '女', '13900003007', '郑州市二七区淮河路700号', ST_GeomFromText('POINT(34.7220 113.655)', 4326),
 '种植养殖户', '传统养殖户', '郑州市二七区淮河路700号', '老客户', 11, '110万', '185万', '2025-05', '上旬', '放款阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

-- 个体工商户（3个）
(55, 'HUINONG_LOAN', '梅志刚', '男', '13900003008', '郑州市二七区京广路800号', ST_GeomFromText('POINT(34.7210 113.660)', 4326),
 '个体工商户', '零售商户', '郑州市二七区京广路800号', '新客户', 3, '30万', '65万', '2025-07', '中旬', '意向阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '倪丽娟', '女', '13900003009', '郑州市二七区铭功路900号', ST_GeomFromText('POINT(34.7290 113.620)', 4326),
 '个体工商户', '服务商户', '郑州市二七区铭功路900号', '老客户', 8, '80万', '145万', '2025-06', '上旬', '审批阶段', '外拓获客', 1, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(55, 'HUINONG_LOAN', '欧阳峰', '男', '13900003010', '郑州市二七区郑密路1000号', ST_GeomFromText('POINT(34.7200 113.665)', 4326),
 '个体工商户', '零售商户', '郑州市二七区郑密路1000号', '新客户', 4, '40万', '78万', '2025-07', '下旬', '洽谈阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1);

-- =====================================================
-- 为郑州管城惠农站（ID=56）生成客户（较少，5个）
-- =====================================================
INSERT INTO grid_customer (
    grid_id, customer_type, customer_name, gender, phone, address, location,
    customer_category, subdivision_type, business_address, customer_situation,
    business_years, current_financing, credit_demand, demand_month, demand_period,
    business_progress, customer_source, is_applied, is_approved, is_formal_customer,
    source, status, creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(56, 'HUINONG_LOAN', '潘大伟', '男', '13900004001', '郑州市管城区商城路100号', ST_GeomFromText('POINT(34.7500 113.695)', 4326),
 '种植养殖户', '传统种植户', '郑州市管城区商城路100号', '老客户', 10, '100万', '170万', '2025-04', '上旬', '审批阶段', '外拓获客', 1, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(56, 'HUINONG_LOAN', '钱秀英', '女', '13900004002', '郑州市管城区城东路200号', ST_GeomFromText('POINT(34.7510 113.700)', 4326),
 '种植养殖户', '传统养殖户', '郑州市管城区城东路200号', '新客户', 5, '50万', '95万', '2025-05', '中旬', '意向阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(56, 'HUINONG_LOAN', '全国华', '男', '13900004003', '郑州市管城区紫荆山路300号', ST_GeomFromText('POINT(34.7490 113.705)', 4326),
 '个体工商户', '零售商户', '郑州市管城区紫荆山路300号', '老客户', 7, '70万', '135万', '2025-06', '下旬', '放款阶段', '外拓获客', 1, 1, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(56, 'HUINONG_LOAN', '任小红', '女', '13900004004', '郑州市管城区城南路400号', ST_GeomFromText('POINT(34.7520 113.690)', 4326),
 '种植养殖户', '传统种植户', '郑州市管城区城南路400号', '新客户', 6, '60万', '105万', '2025-07', '上旬', '洽谈阶段', '行内客户', 0, 0, 0, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1),

(56, 'HUINONG_LOAN', '沈志强', '男', '13900004005', '郑州市管城区未来路500号', ST_GeomFromText('POINT(34.7480 113.710)', 4326),
 '个体工商户', '服务商户', '郑州市管城区未来路500号', '老客户', 9, '90万', '155万', '2025-06', '中旬', '审批阶段', '外拓获客', 1, 0, 1, 'MANUAL', 'NORMAL', '1', NOW(), '1', NOW(), 0, 1);

-- =====================================================
-- 创建客户-网格关系记录（自动为所有新客户创建关系）
-- =====================================================
INSERT INTO grid_customer_grid_relation (
    customer_id, grid_id, grid_code, grid_name, grid_type,
    assign_date, assign_operator_id,
    creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
    c.id AS customer_id,
    c.grid_id AS grid_id,
    g.grid_code,
    g.grid_name,
    g.grid_type,
    CURDATE() AS assign_date,
    1 AS assign_operator_id,
    '1' AS creator,
    c.create_time,
    '1' AS updater,
    c.update_time,
    0 AS deleted,
    1 AS tenant_id
FROM grid_customer c
INNER JOIN grid_info g ON c.grid_id = g.id AND g.deleted = 0
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.phone LIKE '139%'
  AND c.deleted = 0
  AND NOT EXISTS (
      SELECT 1 FROM grid_customer_grid_relation r
      WHERE r.customer_id = c.id AND r.grid_id = c.grid_id AND r.deleted = 0
  );

-- =====================================================
-- 添加多站点关系（部分客户关联多个站点）
-- 模拟实际场景：某些客户在多个站点有业务
-- =====================================================

-- 为郑州中原惠农站的部分客户添加与郑州金水惠农站的关联
INSERT INTO grid_customer_grid_relation (
    customer_id, grid_id, grid_code, grid_name, grid_type,
    assign_date, assign_operator_id,
    creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
    c.id AS customer_id,
    54 AS grid_id,  -- 郑州金水惠农站
    'HN002' AS grid_code,
    '郑州金水惠农站' AS grid_name,
    'HUINONG' AS grid_type,
    CURDATE() AS assign_date,
    1 AS assign_operator_id,
    '1' AS creator,
    NOW() AS create_time,
    '1' AS updater,
    NOW() AS update_time,
    0 AS deleted,
    1 AS tenant_id
FROM grid_customer c
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.grid_id = 53  -- 主站点是郑州中原惠农站
  AND c.phone IN ('13900001001', '13900001003', '13900001006')  -- 选择3个客户
  AND c.deleted = 0;

-- 为郑州金水惠农站的部分客户添加与郑州二七惠农站的关联
INSERT INTO grid_customer_grid_relation (
    customer_id, grid_id, grid_code, grid_name, grid_type,
    assign_date, assign_operator_id,
    creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
    c.id AS customer_id,
    55 AS grid_id,  -- 郑州二七惠农站
    'HN003' AS grid_code,
    '郑州二七惠农站' AS grid_name,
    'HUINONG' AS grid_type,
    CURDATE() AS assign_date,
    1 AS assign_operator_id,
    '1' AS creator,
    NOW() AS create_time,
    '1' AS updater,
    NOW() AS update_time,
    0 AS deleted,
    1 AS tenant_id
FROM grid_customer c
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.grid_id = 54  -- 主站点是郑州金水惠农站
  AND c.phone IN ('13900002002', '13900002006')  -- 选择2个客户
  AND c.deleted = 0;

-- 为郑州二七惠农站的部分客户添加与郑州中原惠农站的关联
INSERT INTO grid_customer_grid_relation (
    customer_id, grid_id, grid_code, grid_name, grid_type,
    assign_date, assign_operator_id,
    creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
    c.id AS customer_id,
    53 AS grid_id,  -- 郑州中原惠农站
    'HN001' AS grid_code,
    '郑州中原惠农站' AS grid_name,
    'HUINONG' AS grid_type,
    CURDATE() AS assign_date,
    1 AS assign_operator_id,
    '1' AS creator,
    NOW() AS create_time,
    '1' AS updater,
    NOW() AS update_time,
    0 AS deleted,
    1 AS tenant_id
FROM grid_customer c
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.grid_id = 55  -- 主站点是郑州二七惠农站
  AND c.phone IN ('13900003003', '13900003007')  -- 选择2个客户
  AND c.deleted = 0;

-- =====================================================
-- 验证数据生成结果
-- =====================================================
SELECT '=== 按站点统计客户数（基于主站点 grid_id） ===' AS summary;
SELECT
    gi.id AS grid_id,
    gi.grid_name,
    COUNT(*) AS customer_count
FROM grid_customer c
INNER JOIN grid_info gi ON c.grid_id = gi.id AND gi.deleted = 0
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.phone LIKE '139%'
  AND c.deleted = 0
GROUP BY gi.id, gi.grid_name
ORDER BY customer_count DESC;

SELECT '=== 按站点统计客户数（基于关系表，支持多对多） ===' AS summary;
SELECT
    r.grid_id,
    r.grid_name,
    COUNT(DISTINCT r.customer_id) AS customer_count
FROM grid_customer_grid_relation r
INNER JOIN grid_customer c ON r.customer_id = c.id AND c.deleted = 0
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.phone LIKE '139%'
  AND r.deleted = 0
GROUP BY r.grid_id, r.grid_name
ORDER BY customer_count DESC;

SELECT '=== 关联多个站点的客户 ===' AS summary;
SELECT
    c.id AS customer_id,
    c.customer_name,
    c.phone,
    COUNT(*) AS station_count,
    GROUP_CONCAT(r.grid_name ORDER BY r.grid_id) AS stations
FROM grid_customer c
INNER JOIN grid_customer_grid_relation r ON c.id = r.customer_id AND r.deleted = 0
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.phone LIKE '139%'
  AND c.deleted = 0
GROUP BY c.id, c.customer_name, c.phone
HAVING COUNT(*) > 1
ORDER BY station_count DESC, c.id;

SELECT '=== 客户总数统计 ===' AS summary;
SELECT
    COUNT(*) AS total_customers,
    SUM(CASE WHEN c.is_formal_customer = 1 THEN 1 ELSE 0 END) AS formal_customers,
    SUM(CASE WHEN c.is_applied = 1 THEN 1 ELSE 0 END) AS applied_customers,
    SUM(CASE WHEN c.is_approved = 1 THEN 1 ELSE 0 END) AS approved_customers
FROM grid_customer c
WHERE c.customer_type = 'HUINONG_LOAN'
  AND c.phone LIKE '139%'
  AND c.deleted = 0;
