-- ----------------------------
-- 网格信息和客户归属网格测试数据
-- 创建10条网格记录（物理网格和虚拟网格）
-- 为所有现有客户创建网格归属关系和调整历史
-- ----------------------------

-- ==================== 第一步：创建网格信息 (10条) ====================

-- 物理网格 (6条)
INSERT INTO `crm_grid_info` (
  `grid_code`, `grid_name`, `grid_type`,
  `dept_id`, `manager_user_id`,
  `province`, `city`, `district`, `address_detail`,
  `status`, `sort_order`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES
-- 物理网格1: 五华区网格
('PG001', '五华区网格', 'physical',
 103, 1,
 '云南省', '昆明市', '五华区', '人民中路、华山南路区域',
 1, 1, '五华区核心商圈网格',
 '1', NOW(), '1', NOW(), 1),

-- 物理网格2: 盘龙区网格
('PG002', '盘龙区网格', 'physical',
 104, 100,
 '云南省', '昆明市', '盘龙区', '北京路、白云路区域',
 1, 2, '盘龙区主要区域网格',
 '1', NOW(), '1', NOW(), 1),

-- 物理网格3: 西山区网格
('PG003', '西山区网格', 'physical',
 106, 103,
 '云南省', '昆明市', '西山区', '滇池路、前卫西路区域',
 1, 3, '西山区主要区域网格',
 '1', NOW(), '1', NOW(), 1),

-- 物理网格4: 官渡区网格
('PG004', '官渡区网格', 'physical',
 107, 104,
 '云南省', '昆明市', '官渡区', '关上中路、经开区域',
 1, 4, '官渡区主要区域网格',
 '1', NOW(), '1', NOW(), 1),

-- 物理网格5: 呈贡区网格
('PG005', '呈贡区网格', 'physical',
 108, 112,
 '云南省', '昆明市', '呈贡区', '大学城、新区区域',
 1, 5, '呈贡新区网格',
 '1', NOW(), '1', NOW(), 1),

-- 物理网格6: 高新区网格
('PG006', '高新区网格', 'physical',
 100, 100,
 '云南省', '昆明市', '高新区', '科技路、海源北路区域',
 1, 6, '高新技术产业区网格',
 '1', NOW(), '1', NOW(), 1),

-- 虚拟网格 (4条)
-- 虚拟网格1: 高净值客户网格
('VG001', '高净值客户网格', 'virtual',
 103, 1,
 NULL, NULL, NULL, '年收入100万以上或资产500万以上',
 1, 11, '高净值客户专属虚拟网格',
 '1', NOW(), '1', NOW(), 1),

-- 虚拟网格2: 企业主网格
('VG002', '企业主网格', 'virtual',
 104, 100,
 NULL, NULL, NULL, '拥有经营实体的客户',
 1, 12, '企业主客户虚拟网格',
 '1', NOW(), '1', NOW(), 1),

-- 虚拟网格3: 公职人员网格
('VG003', '公职人员网格', 'virtual',
 106, 103,
 NULL, NULL, NULL, '政府机关、事业单位工作人员',
 1, 13, '公职人员虚拟网格',
 '1', NOW(), '1', NOW(), 1),

-- 虚拟网格4: 科技行业网格
('VG004', '科技行业网格', 'virtual',
 107, 104,
 NULL, NULL, NULL, '科技、互联网行业从业者',
 1, 14, '科技行业客户虚拟网格',
 '1', NOW(), '1', NOW(), 1);

-- ==================== 第二步：为所有客户创建网格归属关系 ====================

-- 客户1-3: 五华区网格 + 高净值客户网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  1 AS physical_grid_id, 'PG001' AS physical_grid_code, '五华区网格' AS physical_grid_name, 1 AS physical_grid_manager_user_id,
  7 AS virtual_grid_id, 'VG001' AS virtual_grid_code, '高净值客户网格' AS virtual_grid_name, 1 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '系统初始化分配' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id IN (1, 2, 3) AND c.deleted = b'0';

-- 客户4-5: 盘龙区网格 + 公职人员网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  2 AS physical_grid_id, 'PG002' AS physical_grid_code, '盘龙区网格' AS physical_grid_name, 100 AS physical_grid_manager_user_id,
  9 AS virtual_grid_id, 'VG003' AS virtual_grid_code, '公职人员网格' AS virtual_grid_name, 103 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '系统初始化分配' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id IN (4, 5) AND c.deleted = b'0';

-- 客户6-7: 西山区网格 + 企业主网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  3 AS physical_grid_id, 'PG003' AS physical_grid_code, '西山区网格' AS physical_grid_name, 103 AS physical_grid_manager_user_id,
  8 AS virtual_grid_id, 'VG002' AS virtual_grid_code, '企业主网格' AS virtual_grid_name, 100 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '系统初始化分配' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id IN (6, 7) AND c.deleted = b'0';

-- 客户8-10: 官渡区网格 + 企业主网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  4 AS physical_grid_id, 'PG004' AS physical_grid_code, '官渡区网格' AS physical_grid_name, 104 AS physical_grid_manager_user_id,
  8 AS virtual_grid_id, 'VG002' AS virtual_grid_code, '企业主网格' AS virtual_grid_name, 100 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '系统初始化分配' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id IN (8, 9, 10) AND c.deleted = b'0';

-- 客户11-12: 盘龙区网格 + 科技行业网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  2 AS physical_grid_id, 'PG002' AS physical_grid_code, '盘龙区网格' AS physical_grid_name, 100 AS physical_grid_manager_user_id,
  10 AS virtual_grid_id, 'VG004' AS virtual_grid_code, '科技行业网格' AS virtual_grid_name, 104 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '系统初始化分配' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id IN (11, 12) AND c.deleted = b'0';

-- 客户13: 五华区网格 + 高净值客户网格 + 企业主网格（高净值）
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  1 AS physical_grid_id, 'PG001' AS physical_grid_code, '五华区网格' AS physical_grid_name, 1 AS physical_grid_manager_user_id,
  7 AS virtual_grid_id, 'VG001' AS virtual_grid_code, '高净值客户网格' AS virtual_grid_name, 1 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '高净值企业主' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id = 13 AND c.deleted = b'0';

-- 客户14: 高新区网格 + 科技行业网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  6 AS physical_grid_id, 'PG006' AS physical_grid_code, '高新区网格' AS physical_grid_name, 100 AS physical_grid_manager_user_id,
  10 AS virtual_grid_id, 'VG004' AS virtual_grid_code, '科技行业网格' AS virtual_grid_name, 104 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '科技行业高管' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id = 14 AND c.deleted = b'0';

-- 客户15: 官渡区网格 + 企业主网格
INSERT INTO `crm_customer_grid_assignment` (
  `customer_id`,
  `physical_grid_id`, `physical_grid_code`, `physical_grid_name`, `physical_grid_manager_user_id`,
  `virtual_grid_id`, `virtual_grid_code`, `virtual_grid_name`, `virtual_grid_manager_user_id`,
  `assign_date`, `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
)
SELECT
  c.id AS customer_id,
  4 AS physical_grid_id, 'PG004' AS physical_grid_code, '官渡区网格' AS physical_grid_name, 104 AS physical_grid_manager_user_id,
  8 AS virtual_grid_id, 'VG002' AS virtual_grid_code, '企业主网格' AS virtual_grid_name, 100 AS virtual_grid_manager_user_id,
  CURDATE() AS assign_date, 1 AS assign_operator_id, 1 AS status, '餐饮行业创业者' AS remark,
  '1' AS creator, NOW() AS create_time, '1' AS updater, NOW() AS update_time, 1 AS tenant_id
FROM crm_customer c
WHERE c.id = 15 AND c.deleted = b'0';

-- ==================== 第三步：创建网格归属调整历史记录 ====================

-- 客户3的网格调整历史：从盘龙区调整到五华区
INSERT INTO `crm_customer_grid_history` (
  `customer_id`, `adjust_date`, `adjust_reason`,
  `before_physical_grid_id`, `before_physical_grid_code`, `before_physical_grid_name`, `before_physical_grid_manager_user_id`,
  `before_virtual_grid_id`, `before_virtual_grid_code`, `before_virtual_grid_name`, `before_virtual_grid_manager_user_id`,
  `after_physical_grid_id`, `after_physical_grid_code`, `after_physical_grid_name`, `after_physical_grid_manager_user_id`,
  `after_virtual_grid_id`, `after_virtual_grid_code`, `after_virtual_grid_name`, `after_virtual_grid_manager_user_id`,
  `adjust_operator_id`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  3, DATE_SUB(CURDATE(), INTERVAL 30 DAY), '客户搬迁至五华区',
  2, 'PG002', '盘龙区网格', 100,
  7, 'VG001', '高净值客户网格', 1,
  1, 'PG001', '五华区网格', 1,
  7, 'VG001', '高净值客户网格', 1,
  1, '客户居住地变更',
  '1', DATE_SUB(NOW(), INTERVAL 30 DAY), '1', DATE_SUB(NOW(), INTERVAL 30 DAY), 1
);

-- 客户7的网格调整历史：虚拟网格从企业主调整到高净值
INSERT INTO `crm_customer_grid_history` (
  `customer_id`, `adjust_date`, `adjust_reason`,
  `before_physical_grid_id`, `before_physical_grid_code`, `before_physical_grid_name`, `before_physical_grid_manager_user_id`,
  `before_virtual_grid_id`, `before_virtual_grid_code`, `before_virtual_grid_name`, `before_virtual_grid_manager_user_id`,
  `after_physical_grid_id`, `after_physical_grid_code`, `after_physical_grid_name`, `after_physical_grid_manager_user_id`,
  `after_virtual_grid_id`, `after_virtual_grid_code`, `after_virtual_grid_name`, `after_virtual_grid_manager_user_id`,
  `adjust_operator_id`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  7, DATE_SUB(CURDATE(), INTERVAL 15 DAY), '企业发展达到高净值标准',
  3, 'PG003', '西山区网格', 103,
  8, 'VG002', '企业主网格', 100,
  3, 'PG003', '西山区网格', 103,
  7, 'VG001', '高净值客户网格', 1,
  1, '客户资产规模增长',
  '1', DATE_SUB(NOW(), INTERVAL 15 DAY), '1', DATE_SUB(NOW(), INTERVAL 15 DAY), 1
);

-- 客户10的网格调整历史：跨区域调整
INSERT INTO `crm_customer_grid_history` (
  `customer_id`, `adjust_date`, `adjust_reason`,
  `before_physical_grid_id`, `before_physical_grid_code`, `before_physical_grid_name`, `before_physical_grid_manager_user_id`,
  `before_virtual_grid_id`, `before_virtual_grid_code`, `before_virtual_grid_name`, `before_virtual_grid_manager_user_id`,
  `after_physical_grid_id`, `after_physical_grid_code`, `after_physical_grid_name`, `after_physical_grid_manager_user_id`,
  `after_virtual_grid_id`, `after_virtual_grid_code`, `after_virtual_grid_name`, `after_virtual_grid_manager_user_id`,
  `adjust_operator_id`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, DATE_SUB(CURDATE(), INTERVAL 60 DAY), '工厂从呈贡区迁至官渡区',
  5, 'PG005', '呈贡区网格', 112,
  8, 'VG002', '企业主网格', 100,
  4, 'PG004', '官渡区网格', 104,
  8, 'VG002', '企业主网格', 100,
  1, '企业地址变更',
  '1', DATE_SUB(NOW(), INTERVAL 60 DAY), '1', DATE_SUB(NOW(), INTERVAL 60 DAY), 1
);

-- 客户12的网格调整历史：新增虚拟网格归属
INSERT INTO `crm_customer_grid_history` (
  `customer_id`, `adjust_date`, `adjust_reason`,
  `before_physical_grid_id`, `before_physical_grid_code`, `before_physical_grid_name`, `before_physical_grid_manager_user_id`,
  `before_virtual_grid_id`, `before_virtual_grid_code`, `before_virtual_grid_name`, `before_virtual_grid_manager_user_id`,
  `after_physical_grid_id`, `after_physical_grid_code`, `after_physical_grid_name`, `after_physical_grid_manager_user_id`,
  `after_virtual_grid_id`, `after_virtual_grid_code`, `after_virtual_grid_name`, `after_virtual_grid_manager_user_id`,
  `adjust_operator_id`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  12, DATE_SUB(CURDATE(), INTERVAL 45 DAY), '客户注册个体工商户',
  2, 'PG002', '盘龙区网格', 100,
  9, 'VG003', '公职人员网格', 103,
  2, 'PG002', '盘龙区网格', 100,
  10, 'VG004', '科技行业网格', 104,
  1, '客户开始从事咨询业务',
  '1', DATE_SUB(NOW(), INTERVAL 45 DAY), '1', DATE_SUB(NOW(), INTERVAL 45 DAY), 1
);

-- 客户14的网格调整历史：物理网格从盘龙区调整到高新区
INSERT INTO `crm_customer_grid_history` (
  `customer_id`, `adjust_date`, `adjust_reason`,
  `before_physical_grid_id`, `before_physical_grid_code`, `before_physical_grid_name`, `before_physical_grid_manager_user_id`,
  `before_virtual_grid_id`, `before_virtual_grid_code`, `before_virtual_grid_name`, `before_virtual_grid_manager_user_id`,
  `after_physical_grid_id`, `after_physical_grid_code`, `after_physical_grid_name`, `after_physical_grid_manager_user_id`,
  `after_virtual_grid_id`, `after_virtual_grid_code`, `after_virtual_grid_name`, `after_virtual_grid_manager_user_id`,
  `adjust_operator_id`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  14, DATE_SUB(CURDATE(), INTERVAL 20 DAY), '工作地点变更至高新区',
  2, 'PG002', '盘龙区网格', 100,
  10, 'VG004', '科技行业网格', 104,
  6, 'PG006', '高新区网格', 100,
  10, 'VG004', '科技行业网格', 104,
  1, '客户跳槽至高新区企业',
  '1', DATE_SUB(NOW(), INTERVAL 20 DAY), '1', DATE_SUB(NOW(), INTERVAL 20 DAY), 1
);

-- ==================== 统计查询 ====================

-- 1. 网格信息统计
SELECT
  '网格信息统计' AS category,
  COUNT(*) AS total_grid_count,
  SUM(CASE WHEN grid_type = 'physical' THEN 1 ELSE 0 END) AS physical_grid_count,
  SUM(CASE WHEN grid_type = 'virtual' THEN 1 ELSE 0 END) AS virtual_grid_count,
  SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) AS active_grid_count
FROM crm_grid_info
WHERE deleted = b'0';

-- 2. 客户网格归属统计
SELECT
  '客户网格归属统计' AS category,
  COUNT(*) AS total_assignment_count,
  COUNT(DISTINCT customer_id) AS customers_with_grid,
  COUNT(DISTINCT physical_grid_id) AS used_physical_grids,
  COUNT(DISTINCT virtual_grid_id) AS used_virtual_grids
FROM crm_customer_grid_assignment
WHERE deleted = b'0' AND status = 1;

-- 3. 网格调整历史统计
SELECT
  '网格调整历史统计' AS category,
  COUNT(*) AS total_history_count,
  COUNT(DISTINCT customer_id) AS customers_with_history
FROM crm_customer_grid_history
WHERE deleted = b'0';

-- 4. 各网格客户分布
SELECT
  g.grid_code AS 网格编号,
  g.grid_name AS 网格名称,
  g.grid_type AS 网格类型,
  u.nickname AS 网格管理员,
  COUNT(CASE WHEN g.grid_type = 'physical' THEN a.physical_grid_id ELSE a.virtual_grid_id END) AS 客户数量
FROM crm_grid_info g
LEFT JOIN crm_customer_grid_assignment a ON
  (g.grid_type = 'physical' AND g.id = a.physical_grid_id) OR
  (g.grid_type = 'virtual' AND g.id = a.virtual_grid_id)
LEFT JOIN system_users u ON g.manager_user_id = u.id
WHERE g.deleted = b'0' AND (a.deleted = b'0' OR a.id IS NULL)
GROUP BY g.id, g.grid_code, g.grid_name, g.grid_type, u.nickname
ORDER BY g.grid_type, g.sort_order;

-- 5. 客户网格归属详情（前10个客户）
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  a.physical_grid_code AS 物理网格编号,
  a.physical_grid_name AS 物理网格名称,
  u1.nickname AS 物理网格管理员,
  a.virtual_grid_code AS 虚拟网格编号,
  a.virtual_grid_name AS 虚拟网格名称,
  u2.nickname AS 虚拟网格管理员
FROM crm_customer c
LEFT JOIN crm_customer_grid_assignment a ON c.id = a.customer_id AND a.deleted = b'0'
LEFT JOIN system_users u1 ON a.physical_grid_manager_user_id = u1.id
LEFT JOIN system_users u2 ON a.virtual_grid_manager_user_id = u2.id
WHERE c.id <= 10 AND c.deleted = b'0'
ORDER BY c.id;
