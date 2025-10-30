-- ----------------------------
-- 客户评级信息测试数据
-- 说明:
-- 1. 为所有20个客户（10个零售客户 + 10个对公客户）创建评级信息
-- 2. 每个客户创建1条当前评级信息（1对1关系）
-- 3. 每个客户创建3-5条评级调整历史记录
-- 4. 涵盖不同价值等级、服务等级、评级方式等场景
-- 5. 符合业务逻辑的评级变化趋势
-- ----------------------------

-- ==================== 零售客户评级信息（当前评级）====================

-- 客户1: 张三 (customer_id=1) - 财富客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `cust_grade_id`, `effective_date`,
  `expired_date`, `evaluate_date`, `cust_grade`, `cust_grade_type`, `org_code`, `org_name`,
  `rating_score`, `rating_model_version`, `risk_level`, `next_rating_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, 'approved', '2025-10-29', 'wealth', 'five_star', 'five_star', NULL, 'system',
 'RATING20251029001', '2025-10-29', '2026-10-29', '2025-10-28', 'AAA', 'retail', '100001', '西湖支行',
 95.50, 'v2.5.0', 'low', '2026-04-29', '客户资产优质，信用良好',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2: 李四 (customer_id=2) - 贵宾客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(2, 1, 'approved', '2025-10-28', 'vip', 'four_star', 'four_star', NULL, 'system',
 '2025-10-28', 88.30, 'v2.5.0', 'low', '2026-04-28',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户3: 王五 (customer_id=3) - 优质客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(3, 1, 'approved', '2025-10-27', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2025-10-27', 75.80, 'v2.5.0', 'medium_low', '2026-04-27',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户4: 赵六 (customer_id=4) - 财富客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(4, 1, 'approved', '2025-10-26', 'wealth', 'five_star', 'five_star', NULL, 'hybrid',
 '2025-10-26', 92.00, 'v2.5.0', 'low', '2026-04-26',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户5: 孙七 (customer_id=5) - 优质客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(5, 1, 'approved', '2025-10-25', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2025-10-25', 76.50, 'v2.5.0', 'medium', '2026-04-25',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户6: 周八 (customer_id=6) - 普通客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(6, 1, 'approved', '2025-10-24', 'normal', 'two_star', 'two_star', NULL, 'system',
 '2025-10-24', 65.20, 'v2.5.0', 'medium', '2026-04-24',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户7: 吴九 (customer_id=7) - 贵宾客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(7, 1, 'approved', '2025-10-23', 'vip', 'four_star', 'four_star', NULL, 'system',
 '2025-10-23', 86.70, 'v2.5.0', 'low', '2026-04-23',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户8: 郑十 (customer_id=8) - 普通客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(8, 1, 'approved', '2025-10-22', 'normal', 'two_star', 'three_star', '存在逾期记录', 'system',
 '2025-10-22', 62.50, 'v2.5.0', 'medium_high', '2026-04-22',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户9: 陈十一 (customer_id=9) - 基础客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(9, 1, 'approved', '2025-10-21', 'basic', 'one_star', 'one_star', NULL, 'system',
 '2025-10-21', 55.80, 'v2.5.0', 'medium', '2026-04-21',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户10: 黄十二 (customer_id=10) - 财富客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(10, 1, 'approved', '2025-10-20', 'wealth', 'five_star', 'five_star', NULL, 'hybrid',
 '2025-10-20', 94.20, 'v2.5.0', 'low', '2026-04-20',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ==================== 对公客户评级信息（当前评级）====================

-- 客户11: 杭州科技有限公司 (customer_id=11) - 财富客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(11, 1, 'approved', '2025-10-19', 'wealth', 'five_star', 'five_star', NULL, 'system',
 '2025-10-19', 93.50, 'v2.5.0', 'low', '2026-04-19', '科技企业，业务稳健',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户12: 浙江贸易股份有限公司 (customer_id=12) - 贵宾客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(12, 1, 'approved', '2025-10-18', 'vip', 'four_star', 'four_star', NULL, 'hybrid',
 '2025-10-18', 89.80, 'v2.5.0', 'low', '2026-04-18',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户13: 宁波制造业集团 (customer_id=13) - 财富客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(13, 1, 'approved', '2025-10-17', 'wealth', 'five_star', 'five_star', NULL, 'system',
 '2025-10-17', 96.00, 'v2.5.0', 'low', '2026-04-17',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户14: 温州建设工程公司 (customer_id=14) - 优质客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(14, 1, 'approved', '2025-10-16', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2025-10-16', 78.50, 'v2.5.0', 'medium', '2026-04-16',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户15: 绍兴纺织实业公司 (customer_id=15) - 贵宾客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(15, 1, 'approved', '2025-10-15', 'vip', 'four_star', 'four_star', NULL, 'system',
 '2025-10-15', 87.20, 'v2.5.0', 'medium_low', '2026-04-15',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户16: 金华物流运输公司 (customer_id=16) - 优质客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(16, 1, 'approved', '2025-10-14', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2025-10-14', 74.60, 'v2.5.0', 'medium', '2026-04-14',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户17: 台州电子科技公司 (customer_id=17) - 普通客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(17, 1, 'approved', '2025-10-13', 'normal', 'two_star', 'two_star', NULL, 'system',
 '2025-10-13', 68.90, 'v2.5.0', 'medium', '2026-04-13',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户18: 嘉兴食品加工厂 (customer_id=18) - 普通客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(18, 1, 'approved', '2025-10-12', 'normal', 'two_star', 'three_star', '行业风险较高', 'system',
 '2025-10-12', 66.30, 'v2.5.0', 'medium_high', '2026-04-12',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户19: 湖州化工材料公司 (customer_id=19) - 优质客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(19, 1, 'approved', '2025-10-11', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2025-10-11', 77.40, 'v2.5.0', 'medium', '2026-04-11',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户20: 衢州矿业开发公司 (customer_id=20) - 贵宾客户
INSERT INTO `crm_customer_rating` (
  `customer_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`, `service_level`,
  `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `next_rating_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(20, 1, 'approved', '2025-10-10', 'vip', 'four_star', 'four_star', NULL, 'hybrid',
 '2025-10-10', 90.50, 'v2.5.0', 'low', '2026-04-10',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 客户评级调整历史数据 ====================

-- 客户1: 张三 - 5条历史记录（从基础客户逐步升级到财富客户）
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`,
  `expired_date`, `rating_score`, `rating_model_version`, `risk_level`, `change_reason`,
  `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 初始评级
(1, 1, 1, 'approved', '2023-01-15', 'basic', 'one_star', 'one_star', NULL, 'system',
 '2023-01-15', '2023-07-15', 58.20, 'v2.0.0', 'medium', '首次开户评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

-- 第一次升级
(1, 1, 2, 'approved', '2023-07-15', 'normal', 'two_star', 'two_star', NULL, 'system',
 '2023-07-15', '2024-01-15', 65.50, 'v2.1.0', 'medium', '资产增长，交易活跃', 'upgrade', 'basic', 'one_star',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 第二次升级
(1, 1, 3, 'approved', '2024-01-15', 'quality', 'three_star', 'three_star', NULL, 'system',
 '2024-01-15', '2024-07-15', 76.80, 'v2.2.0', 'medium_low', '持续增长，无风险事件', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 第三次升级
(1, 1, 4, 'approved', '2024-07-15', 'vip', 'four_star', 'four_star', NULL, 'hybrid',
 '2024-07-15', '2025-01-15', 88.50, 'v2.3.0', 'low', '资产达到贵宾标准', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 第四次升级（当前）
(1, 1, 5, 'approved', '2025-10-29', 'wealth', 'five_star', 'five_star', NULL, 'system',
 '2025-10-29', '2026-10-29', 95.50, 'v2.5.0', 'low', '资产突破财富客户门槛', 'upgrade', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2: 李四 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `expired_date`, `rating_score`,
  `rating_model_version`, `risk_level`, `change_reason`, `change_type`, `previous_value_level`,
  `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(2, 2, 1, 'approved', '2023-03-10', 'normal', 'two_star', 'system', '2023-03-10', '2023-09-10',
 64.50, 'v2.0.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, 2, 'approved', '2023-09-10', 'quality', 'three_star', 'system', '2023-09-10', '2024-03-10',
 75.20, 'v2.1.0', 'medium_low', '业务增长良好', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, 3, 'approved', '2024-03-10', 'quality', 'three_star', 'system', '2024-03-10', '2024-09-10',
 76.80, 'v2.2.0', 'medium_low', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, 4, 'approved', '2025-10-28', 'vip', 'four_star', 'system', '2025-10-28', '2026-04-28',
 88.30, 'v2.5.0', 'low', '资产快速增长', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户3: 王五 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(3, 3, 1, 'approved', '2024-01-20', 'normal', 'two_star', 'system', '2024-01-20',
 66.00, 'v2.2.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(3, 3, 2, 'approved', '2024-07-20', 'normal', 'two_star', 'system', '2024-07-20',
 67.50, 'v2.3.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(3, 3, 3, 'approved', '2025-10-27', 'quality', 'three_star', 'system', '2025-10-27',
 75.80, 'v2.5.0', 'medium_low', '资产增长超预期', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户4: 赵六 - 5条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(4, 4, 1, 'approved', '2022-06-01', 'quality', 'three_star', 'system', '2022-06-01',
 74.00, 'v1.8.0', 'medium_low', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, 2, 'approved', '2023-01-01', 'vip', 'four_star', 'hybrid', '2023-01-01',
 85.50, 'v2.0.0', 'low', '理财产品投资增加', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, 3, 'approved', '2023-07-01', 'vip', 'four_star', 'system', '2023-07-01',
 86.80, 'v2.1.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, 4, 'approved', '2024-01-01', 'vip', 'four_star', 'system', '2024-01-01',
 88.20, 'v2.2.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, 5, 'approved', '2025-10-26', 'wealth', 'five_star', 'hybrid', '2025-10-26',
 92.00, 'v2.5.0', 'low', '资产规模持续扩大', 'upgrade', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户5: 孙七 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(5, 5, 1, 'approved', '2023-05-15', 'normal', 'two_star', 'system', '2023-05-15',
 63.50, 'v2.0.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, 2, 'approved', '2023-11-15', 'normal', 'two_star', 'system', '2023-11-15',
 65.20, 'v2.1.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, 3, 'approved', '2024-05-15', 'quality', 'three_star', 'system', '2024-05-15',
 72.80, 'v2.2.0', 'medium', '稳定增长', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, 4, 'approved', '2025-10-25', 'quality', 'three_star', 'system', '2025-10-25',
 76.50, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户6: 周八 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(6, 6, 1, 'approved', '2024-02-10', 'basic', 'one_star', 'system', '2024-02-10',
 56.00, 'v2.2.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(6, 6, 2, 'approved', '2024-08-10', 'normal', 'two_star', 'system', '2024-08-10',
 62.50, 'v2.3.0', 'medium', '交易频率提升', 'upgrade', 'basic', 'one_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(6, 6, 3, 'approved', '2025-10-24', 'normal', 'two_star', 'system', '2025-10-24',
 65.20, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户7: 吴九 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(7, 7, 1, 'approved', '2023-02-20', 'normal', 'two_star', 'system', '2023-02-20',
 66.80, 'v2.0.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, 2, 'approved', '2023-08-20', 'quality', 'three_star', 'system', '2023-08-20',
 74.50, 'v2.1.0', 'medium_low', '资产稳步增长', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, 3, 'approved', '2024-02-20', 'quality', 'three_star', 'system', '2024-02-20',
 77.20, 'v2.2.0', 'medium_low', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, 4, 'approved', '2025-10-23', 'vip', 'four_star', 'system', '2025-10-23',
 86.70, 'v2.5.0', 'low', '大额理财购买', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户8: 郑十 - 3条历史记录（有降级）
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`,
  `rating_score`, `rating_model_version`, `risk_level`, `change_reason`, `change_type`,
  `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(8, 8, 1, 'approved', '2023-10-01', 'quality', 'three_star', 'three_star', NULL, 'system', '2023-10-01',
 73.00, 'v2.1.0', 'medium_low', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 8, 2, 'approved', '2024-04-01', 'quality', 'three_star', 'three_star', NULL, 'system', '2024-04-01',
 74.50, 'v2.2.0', 'medium_low', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 8, 3, 'approved', '2025-10-22', 'normal', 'two_star', 'three_star', '存在逾期记录', 'system', '2025-10-22',
 62.50, 'v2.5.0', 'medium_high', '出现信用逾期', 'downgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户9: 陈十一 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(9, 9, 1, 'approved', '2024-06-01', 'basic', 'one_star', 'system', '2024-06-01',
 52.00, 'v2.2.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(9, 9, 2, 'approved', '2024-12-01', 'basic', 'one_star', 'system', '2024-12-01',
 54.50, 'v2.4.0', 'medium', '定期复评，维持等级', 'maintain', 'basic', 'one_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(9, 9, 3, 'approved', '2025-10-21', 'basic', 'one_star', 'system', '2025-10-21',
 55.80, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'basic', 'one_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户10: 黄十二 - 5条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(10, 10, 1, 'approved', '2022-03-15', 'normal', 'two_star', 'system', '2022-03-15',
 68.00, 'v1.8.0', 'medium', '首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, 2, 'approved', '2022-09-15', 'quality', 'three_star', 'system', '2022-09-15',
 78.50, 'v1.9.0', 'medium_low', '资产增长迅速', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, 3, 'approved', '2023-03-15', 'vip', 'four_star', 'hybrid', '2023-03-15',
 87.20, 'v2.0.0', 'low', '达到贵宾门槛', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, 4, 'approved', '2024-03-15', 'vip', 'four_star', 'system', '2024-03-15',
 89.50, 'v2.2.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, 5, 'approved', '2025-10-20', 'wealth', 'five_star', 'hybrid', '2025-10-20',
 94.20, 'v2.5.0', 'low', '资产突破财富门槛', 'upgrade', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ==================== 对公客户评级调整历史 ====================

-- 客户11: 杭州科技有限公司 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(11, 11, 1, 'approved', '2023-01-10', 'quality', 'three_star', 'system', '2023-01-10',
 76.00, 'v2.0.0', 'medium_low', '企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, 2, 'approved', '2023-07-10', 'vip', 'four_star', 'hybrid', '2023-07-10',
 85.50, 'v2.1.0', 'low', '业务规模扩大', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, 3, 'approved', '2024-01-10', 'vip', 'four_star', 'system', '2024-01-10',
 88.20, 'v2.2.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, 4, 'approved', '2025-10-19', 'wealth', 'five_star', 'system', '2025-10-19',
 93.50, 'v2.5.0', 'low', '科技企业快速发展', 'upgrade', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户12: 浙江贸易股份有限公司 - 5条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(12, 12, 1, 'approved', '2022-05-01', 'normal', 'two_star', 'system', '2022-05-01',
 70.00, 'v1.8.0', 'medium', '企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, 2, 'approved', '2022-11-01', 'quality', 'three_star', 'system', '2022-11-01',
 78.50, 'v1.9.0', 'medium', '贸易额增长', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, 3, 'approved', '2023-05-01', 'quality', 'three_star', 'system', '2023-05-01',
 79.80, 'v2.0.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, 4, 'approved', '2024-05-01', 'vip', 'four_star', 'hybrid', '2024-05-01',
 86.50, 'v2.2.0', 'low', '出口业务突破', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, 5, 'approved', '2025-10-18', 'vip', 'four_star', 'hybrid', '2025-10-18',
 89.80, 'v2.5.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户13: 宁波制造业集团 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(13, 13, 1, 'approved', '2022-08-01', 'vip', 'four_star', 'system', '2022-08-01',
 88.00, 'v1.9.0', 'low', '大型企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 13, 2, 'approved', '2023-02-01', 'vip', 'four_star', 'system', '2023-02-01',
 90.50, 'v2.0.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 13, 3, 'approved', '2024-02-01', 'wealth', 'five_star', 'system', '2024-02-01',
 94.80, 'v2.2.0', 'low', '制造业龙头企业', 'upgrade', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 13, 4, 'approved', '2025-10-17', 'wealth', 'five_star', 'system', '2025-10-17',
 96.00, 'v2.5.0', 'low', '定期复评，维持等级', 'maintain', 'wealth', 'five_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户14: 温州建设工程公司 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(14, 14, 1, 'approved', '2023-06-15', 'normal', 'two_star', 'system', '2023-06-15',
 69.50, 'v2.0.0', 'medium', '建筑企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 14, 2, 'approved', '2024-06-15', 'quality', 'three_star', 'system', '2024-06-15',
 75.80, 'v2.2.0', 'medium', '工程款回笼良好', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 14, 3, 'approved', '2025-10-16', 'quality', 'three_star', 'system', '2025-10-16',
 78.50, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户15: 绍兴纺织实业公司 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(15, 15, 1, 'approved', '2023-03-20', 'quality', 'three_star', 'system', '2023-03-20',
 74.00, 'v2.0.0', 'medium_low', '纺织企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, 2, 'approved', '2023-09-20', 'quality', 'three_star', 'system', '2023-09-20',
 76.50, 'v2.1.0', 'medium_low', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, 3, 'approved', '2024-03-20', 'vip', 'four_star', 'system', '2024-03-20',
 84.80, 'v2.2.0', 'low', '出口业务扩展', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, 4, 'approved', '2025-10-15', 'vip', 'four_star', 'system', '2025-10-15',
 87.20, 'v2.5.0', 'medium_low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户16: 金华物流运输公司 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(16, 16, 1, 'approved', '2024-01-05', 'normal', 'two_star', 'system', '2024-01-05',
 67.00, 'v2.2.0', 'medium', '物流企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(16, 16, 2, 'approved', '2024-07-05', 'quality', 'three_star', 'system', '2024-07-05',
 72.50, 'v2.3.0', 'medium', '业务量增长', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(16, 16, 3, 'approved', '2025-10-14', 'quality', 'three_star', 'system', '2025-10-14',
 74.60, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户17: 台州电子科技公司 - 3条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(17, 17, 1, 'approved', '2024-03-10', 'normal', 'two_star', 'system', '2024-03-10',
 66.50, 'v2.2.0', 'medium', '中小企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(17, 17, 2, 'approved', '2024-09-10', 'normal', 'two_star', 'system', '2024-09-10',
 67.80, 'v2.3.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(17, 17, 3, 'approved', '2025-10-13', 'normal', 'two_star', 'system', '2025-10-13',
 68.90, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户18: 嘉兴食品加工厂 - 3条历史记录（有风险因子）
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `service_level_before_risk`, `risk_factors`, `rating_method`, `effective_date`,
  `rating_score`, `rating_model_version`, `risk_level`, `change_reason`, `change_type`,
  `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(18, 18, 1, 'approved', '2023-11-01', 'quality', 'three_star', 'three_star', NULL, 'system', '2023-11-01',
 72.50, 'v2.1.0', 'medium', '食品企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(18, 18, 2, 'approved', '2024-05-01', 'quality', 'three_star', 'three_star', NULL, 'system', '2024-05-01',
 73.80, 'v2.2.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(18, 18, 3, 'approved', '2025-10-12', 'normal', 'two_star', 'three_star', '行业风险较高', 'system', '2025-10-12',
 66.30, 'v2.5.0', 'medium_high', '行业监管趋严', 'downgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户19: 湖州化工材料公司 - 4条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(19, 19, 1, 'approved', '2023-04-15', 'normal', 'two_star', 'system', '2023-04-15',
 68.50, 'v2.0.0', 'medium', '化工企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(19, 19, 2, 'approved', '2023-10-15', 'normal', 'two_star', 'system', '2023-10-15',
 70.20, 'v2.1.0', 'medium', '定期复评，维持等级', 'maintain', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(19, 19, 3, 'approved', '2024-04-15', 'quality', 'three_star', 'system', '2024-04-15',
 76.50, 'v2.2.0', 'medium', '产品销售增长', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(19, 19, 4, 'approved', '2025-10-11', 'quality', 'three_star', 'system', '2025-10-11',
 77.40, 'v2.5.0', 'medium', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户20: 衢州矿业开发公司 - 5条历史记录
INSERT INTO `crm_customer_rating_history` (
  `customer_id`, `rating_id`, `sequence_no`, `approval_status`, `rating_date`, `value_level`,
  `service_level`, `rating_method`, `effective_date`, `rating_score`, `rating_model_version`,
  `risk_level`, `change_reason`, `change_type`, `previous_value_level`, `previous_service_level`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(20, 20, 1, 'approved', '2022-07-01', 'normal', 'two_star', 'system', '2022-07-01',
 71.00, 'v1.9.0', 'medium', '矿业企业首次评级', 'initial', NULL, NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(20, 20, 2, 'approved', '2023-01-01', 'quality', 'three_star', 'system', '2023-01-01',
 79.50, 'v2.0.0', 'medium_low', '矿产价格上涨', 'upgrade', 'normal', 'two_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(20, 20, 3, 'approved', '2023-07-01', 'quality', 'three_star', 'system', '2023-07-01',
 81.20, 'v2.1.0', 'medium_low', '定期复评，维持等级', 'maintain', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(20, 20, 4, 'approved', '2024-07-01', 'vip', 'four_star', 'hybrid', '2024-07-01',
 88.00, 'v2.3.0', 'low', '资源储量丰富', 'upgrade', 'quality', 'three_star',
 '1', NOW(), '1', NOW(), b'0', 1),

(20, 20, 5, 'approved', '2025-10-10', 'vip', 'four_star', 'hybrid', '2025-10-10',
 90.50, 'v2.5.0', 'low', '定期复评，维持等级', 'maintain', 'vip', 'four_star',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 数据验证查询 ====================

-- 1. 统计每个客户的评级信息
SELECT
  customer_id,
  value_level,
  service_level,
  rating_date,
  rating_score,
  risk_level
FROM crm_customer_rating
ORDER BY customer_id;

-- 2. 统计各价值等级的客户数量
SELECT
  value_level,
  COUNT(*) as customer_count
FROM crm_customer_rating
GROUP BY value_level
ORDER BY customer_count DESC;

-- 3. 统计各服务等级的客户数量
SELECT
  service_level,
  COUNT(*) as customer_count
FROM crm_customer_rating
GROUP BY service_level
ORDER BY customer_count DESC;

-- 4. 统计每个客户的历史评级记录数
SELECT
  customer_id,
  COUNT(*) as history_count,
  MIN(rating_date) as first_rating_date,
  MAX(rating_date) as last_rating_date
FROM crm_customer_rating_history
GROUP BY customer_id
ORDER BY customer_id;

-- 5. 统计各调整类型的数量
SELECT
  change_type,
  COUNT(*) as count
FROM crm_customer_rating_history
GROUP BY change_type
ORDER BY count DESC;

-- 6. 查询有降级记录的客户
SELECT
  customer_id,
  rating_date,
  value_level,
  previous_value_level,
  change_reason
FROM crm_customer_rating_history
WHERE change_type = 'downgrade'
ORDER BY rating_date DESC;

-- 7. 查询评级分数最高的前10名客户
SELECT
  customer_id,
  value_level,
  service_level,
  rating_score,
  rating_date
FROM crm_customer_rating
ORDER BY rating_score DESC
LIMIT 10;
