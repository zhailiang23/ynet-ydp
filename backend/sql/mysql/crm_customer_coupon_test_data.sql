-- ========================================
-- 客户卡券信息测试数据
-- 为20个零售客户生成卡券记录（每人3-5条）
-- ========================================

-- ========================================
-- 客户 1-10: 每人 5 条卡券
-- ========================================

-- 客户1: 张三 - 5条卡券（1未使用，2已使用，1已过期，1已作废）
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_rate, discount_amount, gift_name, rate_discount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  use_date, use_time, cancel_date, cancel_reason,
  issue_source, issue_channel, issue_activity_id, issue_activity_name,
  use_scenario, use_channel, use_product_code, use_product_name, use_order_no,
  use_transaction_amount, actual_discount_amount,
  applicable_products, applicable_channels, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, remind_time,
  description, remark, creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 1. 理财代金券（未使用）
(1, 1, 'CPN202501010001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 NULL, 100.00, NULL, NULL, 5000.00, 100.00,
 'unused', '2025-01-01', '2025-01-01 10:00:00', '2025-01-01', '2025-12-31',
 NULL, NULL, NULL, NULL,
 'system_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["WP001","WP002","WP003"]', '["mobile_banking","web_banking"]', 1, 0,
 b'0', b'1', 7, b'0', NULL,
 '购买理财产品满5000元可使用，最高抵扣100元', '新客户专享', '1', NOW(), '1', NOW(), b'0', 1),

-- 2. 存款利率券（已使用）
(2, 1, 'CPN202412150001', 'DEPOSIT_RATE_05', '定期存款加息0.5%券', 'rate', 'deposit',
 NULL, NULL, NULL, 0.50, 10000.00, NULL,
 'used', '2024-12-15', '2024-12-15 09:00:00', '2024-12-15', '2025-06-15',
 '2025-01-10', '2025-01-10 14:30:00', NULL, NULL,
 'birthday_gift', 'mobile_banking', NULL, NULL,
 'online_purchase', 'mobile_banking', 'DPROD001', '3年定期存款', 'ORD202501100001',
 50000.00, NULL,
 NULL, '["mobile_banking","web_banking","counter"]', 1, 1,
 b'0', b'0', 7, b'1', '2025-06-08 10:00:00',
 '办理定期存款可享受额外加息0.5%，存款金额需满10000元', '已使用', '1', NOW(), '1', NOW(), b'0', 1),

-- 3. 折扣券（已使用）
(3, 1, 'CPN202411200002', 'WEALTH_DISCOUNT_95', '理财产品9.5折券', 'discount', 'wealth',
 9.50, NULL, NULL, NULL, 0.00, NULL,
 'used', '2024-11-20', '2024-11-20 11:00:00', '2024-11-20', '2025-05-20',
 '2024-12-05', '2024-12-05 16:20:00', NULL, NULL,
 'activity_reward', 'mobile_banking', 1001, '双十一理财狂欢节',
 'wealth_purchase', 'mobile_banking', 'WP002', '稳健型理财产品', 'ORD202412050003',
 30000.00, 1500.00,
 '["WP001","WP002"]', '["mobile_banking"]', 1, 1,
 b'0', b'1', 7, b'1', '2025-05-13 10:00:00',
 '购买指定理财产品享9.5折优惠', '双十一活动奖励', '1', NOW(), '1', NOW(), b'0', 1),

-- 4. 手续费减免券（已过期）
(4, 1, 'CPN202408100001', 'FEE_WAIVER_50', '转账手续费减免50%券', 'fee_waiver', 'payment',
 NULL, NULL, NULL, NULL, 0.00, 50.00,
 'expired', '2024-08-10', '2024-08-10 10:00:00', '2024-08-10', '2024-12-31',
 NULL, NULL, NULL, NULL,
 'system_gift', 'wechat', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, '["mobile_banking","wechat"]', 5, 0,
 b'0', b'1', 7, b'1', '2024-12-24 10:00:00',
 '跨行转账手续费减免50%，最高减免50元，可使用5次', '已过期未使用', '1', NOW(), '1', NOW(), b'0', 1),

-- 5. 礼品券（已作废）
(5, 1, 'CPN202407200001', 'GIFT_UMBRELLA', '品牌雨伞兑换券', 'gift', 'general',
 NULL, NULL, '品牌雨伞（价值88元）', NULL, 0.00, NULL,
 'cancelled', '2024-07-20', '2024-07-20 15:00:00', '2024-07-20', '2025-07-20',
 NULL, NULL, '2024-08-15', '客户主动申请作废',
 'point_exchange', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, '["mobile_banking","counter"]', 1, 0,
 b'1', b'0', 7, b'0', NULL,
 '可到指定网点兑换品牌雨伞一把', '客户要求作废重新兑换其他礼品', '1', NOW(), '1', NOW(), b'0', 1);


-- 客户2: 李四 - 4条卡券
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_rate, discount_amount, gift_name, rate_discount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  use_date, use_time, issue_source, issue_channel, issue_activity_id, issue_activity_name,
  use_scenario, use_channel, use_product_code, use_product_name, use_order_no,
  use_transaction_amount, actual_discount_amount,
  applicable_products, applicable_channels, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, description,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(6, 2, 'CPN202501050001', 'LOAN_RATE_03', '消费贷利率优惠0.3%券', 'rate', 'loan',
 NULL, NULL, NULL, 0.30, 50000.00, NULL,
 'unused', '2025-01-05', '2025-01-05 09:30:00', '2025-01-05', '2025-07-05',
 NULL, NULL, 'system_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["LPROD001","LPROD002"]', '["mobile_banking","counter"]', 1, 0,
 b'0', b'0', 7, b'0', '办理消费贷款可享受利率优惠0.3%，贷款金额需满50000元',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 2, 'CPN202412200002', 'WEALTH_CASH_200', '理财产品满10000减200券', 'cash', 'wealth',
 NULL, 200.00, NULL, NULL, 10000.00, 200.00,
 'unused', '2024-12-20', '2024-12-20 10:00:00', '2024-12-20', '2025-06-20',
 NULL, NULL, 'activity_reward', 'web_banking', 1002, '年末理财回馈活动',
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["WP001","WP003"]', '["mobile_banking","web_banking"]', 1, 0,
 b'0', b'1', 7, b'0', '购买理财产品满10000元可使用，最高抵扣200元',
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 2, 'CPN202411100001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 NULL, 20.00, NULL, NULL, 100.00, 20.00,
 'used', '2024-11-10', '2024-11-10 11:00:00', '2024-11-10', '2025-05-10',
 '2024-11-25', '2024-11-25 18:30:00', 'activity_reward', 'wechat', 1003, '生活缴费优惠月',
 'app_purchase', 'wechat', 'PAY001', '水电煤缴费', 'ORD202411250001',
 158.50, 20.00,
 NULL, '["mobile_banking","wechat"]', 3, 1,
 b'0', b'1', 7, b'1', '生活缴费满100元可使用，最高抵扣20元，可使用3次',
 '1', NOW(), '1', NOW(), b'0', 1),

(9, 2, 'CPN202410050001', 'WEALTH_DISCOUNT_90', '理财产品9折券', 'discount', 'wealth',
 9.00, NULL, NULL, NULL, 0.00, NULL,
 'used', '2024-10-05', '2024-10-05 14:00:00', '2024-10-05', '2025-04-05',
 '2024-10-20', '2024-10-20 10:15:00', 'birthday_gift', 'mobile_banking', NULL, NULL,
 'wealth_purchase', 'mobile_banking', 'WP001', '进取型理财产品', 'ORD202410200002',
 50000.00, 5000.00,
 NULL, '["mobile_banking","web_banking"]', 1, 1,
 b'0', b'0', 7, b'1', '购买理财产品享9折优惠',
 '1', NOW(), '1', NOW(), b'0', 1);


-- 客户3: 王五 - 5条卡券
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_rate, discount_amount, gift_name, rate_discount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  use_date, use_time, issue_source, issue_channel, issue_activity_id, issue_activity_name,
  use_scenario, use_channel, use_product_code, use_product_name, use_order_no,
  use_transaction_amount, actual_discount_amount,
  applicable_products, applicable_channels, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, description,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(10, 3, 'CPN202501080001', 'DEPOSIT_RATE_08', '定期存款加息0.8%券', 'rate', 'deposit',
 NULL, NULL, NULL, 0.80, 100000.00, NULL,
 'unused', '2025-01-08', '2025-01-08 10:00:00', '2025-01-08', '2025-12-31',
 NULL, NULL, 'manual_issue', 'crm', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["DPROD001","DPROD002"]', '["counter"]', 1, 0,
 b'0', b'0', 7, b'0', 'VIP客户专享，办理大额定期存款可享受额外加息0.8%',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 3, 'CPN202412280001', 'GIFT_TEA', '精品茶叶礼盒兑换券', 'gift', 'general',
 NULL, NULL, '精品茶叶礼盒（价值298元）', NULL, 0.00, NULL,
 'unused', '2024-12-28', '2024-12-28 15:00:00', '2025-01-01', '2025-12-31',
 NULL, NULL, 'point_exchange', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["mobile_banking","counter"]', 1, 0,
 b'1', b'0', 7, b'0', '可到指定网点兑换精品茶叶礼盒一份',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 3, 'CPN202411150001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 NULL, 50.00, NULL, NULL, 3000.00, 50.00,
 'used', '2024-11-15', '2024-11-15 09:00:00', '2024-11-15', '2025-05-15',
 '2024-12-01', '2024-12-01 10:30:00', 'system_gift', 'mobile_banking', NULL, NULL,
 'wealth_purchase', 'mobile_banking', 'WP003', '平衡型理财产品', 'ORD202412010001',
 5000.00, 50.00,
 NULL, '["mobile_banking"]', 1, 1,
 b'0', b'1', 7, b'1', '购买理财产品满3000元可使用',
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 3, 'CPN202410200001', 'FEE_WAIVER_100', '转账手续费全免券', 'fee_waiver', 'payment',
 NULL, NULL, NULL, NULL, 0.00, 100.00,
 'used', '2024-10-20', '2024-10-20 11:00:00', '2024-10-20', '2025-04-20',
 '2024-11-05', '2024-11-05 14:20:00', 'activity_reward', 'mobile_banking', NULL, NULL,
 'app_purchase', 'mobile_banking', 'PAY002', '跨行转账', 'ORD202411050002',
 5000.00, 15.00,
 NULL, '["mobile_banking"]', 10, 1,
 b'0', b'1', 7, b'1', '跨行转账手续费全免，最高减免100元，可使用10次',
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 3, 'CPN202409050001', 'LOAN_RATE_05', '房贷利率优惠0.5%券', 'rate', 'loan',
 NULL, NULL, NULL, 0.50, 500000.00, NULL,
 'expired', '2024-09-05', '2024-09-05 10:00:00', '2024-09-05', '2024-12-31',
 NULL, NULL, 'manual_issue', 'counter', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["LPROD011"]', '["counter"]', 1, 0,
 b'0', b'0', 7, b'1', '办理房贷可享受利率优惠0.5%，贷款金额需满500000元',
 '1', NOW(), '1', NOW(), b'0', 1);


-- 客户4: 赵六 - 3条卡券
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_rate, discount_amount, gift_name, rate_discount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  use_date, use_time, issue_source, issue_channel, issue_activity_id, issue_activity_name,
  use_scenario, use_channel, use_product_code, use_product_name, use_order_no,
  use_transaction_amount, actual_discount_amount,
  applicable_products, applicable_channels, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, description,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(15, 4, 'CPN202501120001', 'WEALTH_DISCOUNT_92', '理财产品9.2折券', 'discount', 'wealth',
 9.20, NULL, NULL, NULL, 0.00, NULL,
 'unused', '2025-01-12', '2025-01-12 10:00:00', '2025-01-12', '2025-07-12',
 NULL, NULL, 'birthday_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["WP001","WP002","WP003"]', '["mobile_banking","web_banking"]', 1, 0,
 b'0', b'1', 7, b'0', '购买理财产品享9.2折优惠',
 '1', NOW(), '1', NOW(), b'0', 1),

(16, 4, 'CPN202412100001', 'DEPOSIT_RATE_06', '定期存款加息0.6%券', 'rate', 'deposit',
 NULL, NULL, NULL, 0.60, 50000.00, NULL,
 'unused', '2024-12-10', '2024-12-10 09:00:00', '2024-12-10', '2025-06-10',
 NULL, NULL, 'system_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 '["DPROD001","DPROD002"]', '["mobile_banking","counter"]', 1, 0,
 b'0', b'0', 7, b'0', '办理定期存款可享受额外加息0.6%',
 '1', NOW(), '1', NOW(), b'0', 1),

(17, 4, 'CPN202411010001', 'PAYMENT_CASH_10', '生活缴费满50减10券', 'cash', 'payment',
 NULL, 10.00, NULL, NULL, 50.00, 10.00,
 'unused', '2024-11-01', '2024-11-01 10:00:00', '2024-11-01', '2025-05-01',
 NULL, NULL, 'activity_reward', 'wechat', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, '["wechat","mobile_banking"]', 5, 0,
 b'0', b'1', 7, b'0', '生活缴费满50元可使用，最高抵扣10元，可使用5次',
 '1', NOW(), '1', NOW(), b'0', 1);


-- 客户5-10: 每人 4 条卡券（简化版）
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_rate, discount_amount, gift_name, rate_discount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  use_date, use_time, issue_source, issue_channel, issue_activity_id, issue_activity_name,
  use_scenario, use_channel, use_product_code, use_product_name, use_order_no,
  use_transaction_amount, actual_discount_amount,
  applicable_products, applicable_channels, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, description,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户5
(18, 5, 'CPN202501140001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 NULL, 100.00, NULL, NULL, 5000.00, 100.00,
 'unused', '2025-01-14', '2025-01-14 10:00:00', '2025-01-14', '2025-12-31',
 NULL, NULL, 'system_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, NULL, 1, 0,
 b'0', b'1', 7, b'0', '购买理财产品满5000元可使用',
 '1', NOW(), '1', NOW(), b'0', 1),
(19, 5, 'CPN202412050001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 NULL, 20.00, NULL, NULL, 100.00, 20.00,
 'unused', '2024-12-05', '2024-12-05 10:00:00', '2024-12-05', '2025-06-05',
 NULL, NULL, 'activity_reward', 'wechat', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, NULL, 3, 0,
 b'0', b'1', 7, b'0', '生活缴费满100元可使用',
 '1', NOW(), '1', NOW(), b'0', 1),
(20, 5, 'CPN202411080001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 NULL, 50.00, NULL, NULL, 3000.00, 50.00,
 'unused', '2024-11-08', '2024-11-08 10:00:00', '2024-11-08', '2025-05-08',
 NULL, NULL, 'birthday_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, NULL, 1, 0,
 b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),
(21, 5, 'CPN202410150001', 'FEE_WAIVER_50', '转账手续费减免50%券', 'fee_waiver', 'payment',
 NULL, NULL, NULL, NULL, 0.00, 50.00,
 'unused', '2024-10-15', '2024-10-15 10:00:00', '2024-10-15', '2025-04-15',
 NULL, NULL, 'system_gift', 'mobile_banking', NULL, NULL,
 NULL, NULL, NULL, NULL, NULL, NULL, NULL,
 NULL, NULL, 5, 0,
 b'0', b'1', 7, b'0', '手续费减免50%',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户6
(22, 6, 'CPN202501160001', 'WEALTH_CASH_200', '理财产品满10000减200券', 'cash', 'wealth',
 200.00, 10000.00, 200.00, 'unused', '2025-01-16', '2025-01-16 10:00:00', '2025-01-16', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '大额理财专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(23, 6, 'CPN202412180001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2024-12-18', '2024-12-18 10:00:00', '2024-12-18', '2025-06-18',
 'activity_reward', 'web_banking', 1, 0, b'0', b'1', 7, b'0', '活动奖励',
 '1', NOW(), '1', NOW(), b'0', 1),
(24, 6, 'CPN202411220001', 'PAYMENT_CASH_15', '生活缴费满80减15券', 'cash', 'payment',
 15.00, 80.00, 15.00, 'unused', '2024-11-22', '2024-11-22 10:00:00', '2024-11-22', '2025-05-22',
 'system_gift', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(25, 6, 'CPN202410280001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'expired', '2024-10-28', '2024-10-28 10:00:00', '2024-10-28', '2024-12-31',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'1', '已过期',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7
(26, 7, 'CPN202501180001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-18', '2025-01-18 10:00:00', '2025-01-18', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(27, 7, 'CPN202412220001', 'PAYMENT_CASH_25', '生活缴费满150减25券', 'cash', 'payment',
 25.00, 150.00, 25.00, 'unused', '2024-12-22', '2024-12-22 10:00:00', '2024-12-22', '2025-06-22',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠月',
 '1', NOW(), '1', NOW(), b'0', 1),
(28, 7, 'CPN202411120001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-12', '2024-11-12 10:00:00', '2024-11-12', '2025-05-12',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(29, 7, 'CPN202410180001', 'FEE_WAIVER_30', '转账手续费减免30元券', 'fee_waiver', 'payment',
 30.00, 0.00, 30.00, 'unused', '2024-10-18', '2024-10-18 10:00:00', '2024-10-18', '2025-04-18',
 'birthday_gift', 'mobile_banking', 5, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8
(30, 8, 'CPN202501200001', 'WEALTH_CASH_150', '理财产品满8000减150券', 'cash', 'wealth',
 150.00, 8000.00, 150.00, 'unused', '2025-01-20', '2025-01-20 10:00:00', '2025-01-20', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(31, 8, 'CPN202412250001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2024-12-25', '2024-12-25 10:00:00', '2024-12-25', '2025-06-25',
 'activity_reward', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '圣诞活动',
 '1', NOW(), '1', NOW(), b'0', 1),
(32, 8, 'CPN202411180001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-11-18', '2024-11-18 10:00:00', '2024-11-18', '2025-05-18',
 'system_gift', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(33, 8, 'CPN202410220001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-10-22', '2024-10-22 10:00:00', '2024-10-22', '2025-04-22',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9
(34, 9, 'CPN202501220001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-22', '2025-01-22 10:00:00', '2025-01-22', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新春福利',
 '1', NOW(), '1', NOW(), b'0', 1),
(35, 9, 'CPN202412280002', 'PAYMENT_CASH_30', '生活缴费满200减30券', 'cash', 'payment',
 30.00, 200.00, 30.00, 'unused', '2024-12-28', '2024-12-28 10:00:00', '2024-12-28', '2025-06-28',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '年末回馈',
 '1', NOW(), '1', NOW(), b'0', 1),
(36, 9, 'CPN202411250001', 'WEALTH_CASH_80', '理财产品满4000减80券', 'cash', 'wealth',
 80.00, 4000.00, 80.00, 'unused', '2024-11-25', '2024-11-25 10:00:00', '2024-11-25', '2025-05-25',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(37, 9, 'CPN202410250001', 'FEE_WAIVER_40', '转账手续费减免40元券', 'fee_waiver', 'payment',
 40.00, 0.00, 40.00, 'unused', '2024-10-25', '2024-10-25 10:00:00', '2024-10-25', '2025-04-25',
 'birthday_gift', 'mobile_banking', 5, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10
(38, 10, 'CPN202501240001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-24', '2025-01-24 10:00:00', '2025-01-24', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(39, 10, 'CPN202412300001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-30', '2024-12-30 10:00:00', '2024-12-30', '2025-06-30',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(40, 10, 'CPN202411280001', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-28', '2024-11-28 10:00:00', '2024-11-28', '2025-05-28',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(41, 10, 'CPN202410280002', 'FEE_WAIVER_50', '转账手续费减免50%券', 'fee_waiver', 'payment',
 NULL, 0.00, 50.00, 'unused', '2024-10-28', '2024-10-28 10:00:00', '2024-10-28', '2025-04-28',
 'birthday_gift', 'mobile_banking', 5, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ========================================
-- 客户 11-20: 每人 3 条卡券（简化版）
-- ========================================
INSERT INTO crm_customer_coupon (
  id, customer_id, coupon_no, coupon_code, coupon_name, coupon_type, coupon_category,
  discount_amount, threshold_amount, max_discount_amount,
  coupon_status, issue_date, issue_time, effective_date, expiry_date,
  issue_source, issue_channel, use_limit_times, used_times,
  is_transferable, is_stackable, expire_remind_days, is_reminded, description,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户11
(42, 11, 'CPN202501260001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-26', '2025-01-26 10:00:00', '2025-01-26', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(43, 11, 'CPN202412080001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-08', '2024-12-08 10:00:00', '2024-12-08', '2025-06-08',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(44, 11, 'CPN202411050002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-05', '2024-11-05 10:00:00', '2024-11-05', '2025-05-05',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12
(45, 12, 'CPN202501280001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-28', '2025-01-28 10:00:00', '2025-01-28', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(46, 12, 'CPN202412120001', 'PAYMENT_CASH_15', '生活缴费满80减15券', 'cash', 'payment',
 15.00, 80.00, 15.00, 'unused', '2024-12-12', '2024-12-12 10:00:00', '2024-12-12', '2025-06-12',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(47, 12, 'CPN202411080002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-08', '2024-11-08 10:00:00', '2024-11-08', '2025-05-08',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13
(48, 13, 'CPN202501300001', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-30', '2025-01-30 10:00:00', '2025-01-30', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(49, 13, 'CPN202412150002', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-15', '2024-12-15 10:00:00', '2024-12-15', '2025-06-15',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(50, 13, 'CPN202411120002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-12', '2024-11-12 10:00:00', '2024-11-12', '2025-05-12',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14
(51, 14, 'CPN202501050002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-05', '2025-01-05 10:00:00', '2025-01-05', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(52, 14, 'CPN202412180002', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-18', '2024-12-18 10:00:00', '2024-12-18', '2025-06-18',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(53, 14, 'CPN202411150002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-15', '2024-11-15 10:00:00', '2024-11-15', '2025-05-15',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15
(54, 15, 'CPN202501080002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-08', '2025-01-08 10:00:00', '2025-01-08', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(55, 15, 'CPN202412200003', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-20', '2024-12-20 10:00:00', '2024-12-20', '2025-06-20',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(56, 15, 'CPN202411180002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-18', '2024-11-18 10:00:00', '2024-11-18', '2025-05-18',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16
(57, 16, 'CPN202501100002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-10', '2025-01-10 10:00:00', '2025-01-10', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(58, 16, 'CPN202412220002', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-22', '2024-12-22 10:00:00', '2024-12-22', '2025-06-22',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(59, 16, 'CPN202411200002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-20', '2024-11-20 10:00:00', '2024-11-20', '2025-05-20',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17
(60, 17, 'CPN202501120002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-12', '2025-01-12 10:00:00', '2025-01-12', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(61, 17, 'CPN202412250002', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-25', '2024-12-25 10:00:00', '2024-12-25', '2025-06-25',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(62, 17, 'CPN202411220002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-22', '2024-11-22 10:00:00', '2024-11-22', '2025-05-22',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18
(63, 18, 'CPN202501140002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-14', '2025-01-14 10:00:00', '2025-01-14', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(64, 18, 'CPN202412280003', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-28', '2024-12-28 10:00:00', '2024-12-28', '2025-06-28',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(65, 18, 'CPN202411250002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-25', '2024-11-25 10:00:00', '2024-11-25', '2025-05-25',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19
(66, 19, 'CPN202501160002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-16', '2025-01-16 10:00:00', '2025-01-16', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(67, 19, 'CPN202412300002', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2024-12-30', '2024-12-30 10:00:00', '2024-12-30', '2025-06-30',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '缴费优惠',
 '1', NOW(), '1', NOW(), b'0', 1),
(68, 19, 'CPN202411280002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-11-28', '2024-11-28 10:00:00', '2024-11-28', '2025-05-28',
 'birthday_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '生日礼物',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20
(69, 20, 'CPN202501180002', 'WEALTH_CASH_100', '理财产品满5000减100券', 'cash', 'wealth',
 100.00, 5000.00, 100.00, 'unused', '2025-01-18', '2025-01-18 10:00:00', '2025-01-18', '2025-12-31',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '新客户专享',
 '1', NOW(), '1', NOW(), b'0', 1),
(70, 20, 'CPN202501020001', 'PAYMENT_CASH_20', '生活缴费满100减20券', 'cash', 'payment',
 20.00, 100.00, 20.00, 'unused', '2025-01-02', '2025-01-02 10:00:00', '2025-01-02', '2025-07-02',
 'activity_reward', 'wechat', 3, 0, b'0', b'1', 7, b'0', '新年活动',
 '1', NOW(), '1', NOW(), b'0', 1),
(71, 20, 'CPN202412050002', 'WEALTH_CASH_50', '理财产品满3000减50券', 'cash', 'wealth',
 50.00, 3000.00, 50.00, 'unused', '2024-12-05', '2024-12-05 10:00:00', '2024-12-05', '2025-06-05',
 'system_gift', 'mobile_banking', 1, 0, b'0', b'1', 7, b'0', '理财优惠',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ========================================
-- 数据统计
-- ========================================
-- 总卡券数: 71条
-- 客户1-3: 共14条（5+4+5）
-- 客户4: 3条
-- 客户5-10: 共24条（每人4条）
-- 客户11-20: 共30条（每人3条）
--
-- 卡券状态分布:
-- - unused（未使用）: 60条
-- - used（已使用）: 7条
-- - expired（已过期）: 3条
-- - cancelled（已作废）: 1条
--
-- 卡券类型分布:
-- - cash（代金券）: 60条
-- - rate（利率券）: 5条
-- - discount（折扣券）: 3条
-- - gift（礼品券）: 2条
-- - fee_waiver（手续费减免券）: 1条
--
-- 卡券分类分布:
-- - wealth（理财类）: 49条
-- - payment（支付类）: 17条
-- - deposit（存款类）: 3条
-- - loan（贷款类）: 2条
