-- ----------------------------
-- 客户需求信息表测试数据
-- 为所有20个客户（10个零售 + 10个对公）生成3-5条需求记录
-- 涵盖各种需求渠道、需求类型、需求状态
-- ----------------------------

-- 清空表数据
TRUNCATE TABLE `crm_customer_demand`;

-- ==================== 零售客户需求信息 ====================

-- 客户1: 张三 (customer_id=1) - 5条需求
INSERT INTO `crm_customer_demand` (
  `customer_id`, `sequence_no`, `demand_channel`, `demand_content`, `submit_time`,
  `is_processed`, `demand_no`, `demand_type`, `demand_status`, `demand_priority`,
  `demand_source`, `handler_user_id`, `handler_user_name`, `handler_dept_id`, `handler_dept_name`,
  `process_start_time`, `process_end_time`, `process_duration`, `process_result`,
  `customer_satisfaction`, `satisfaction_feedback`, `follow_up_required`, `related_product`,
  `expected_amount`, `actual_amount`, `is_converted`, `convert_time`, `is_closed`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:00:00',
 b'1', 'DEM202203230001', 'product_consult', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', 301, '零售业务部',
 '2022-03-23 10:30:00', '2022-03-23 11:00:00', 30, '已向客户详细介绍理财产品特点和收益情况',
 5, '服务很专业，解答很详细', b'0', '理财产品A', 100000.00, 100000.00,
 b'1', '2022-03-25 14:00:00', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(1, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 14:00:00',
 b'0', 'DEM202203230002', 'account_query', 'pending', 'normal',
 'customer_active', NULL, NULL, NULL, NULL,
 NULL, NULL, NULL, NULL,
 NULL, NULL, b'0', NULL, NULL, NULL,
 b'0', NULL, b'0', '1', NOW(), '1', NOW(), b'0', 1),

(1, 3, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 15:30:00',
 b'1', 'DEM202203230003', 'wealth_manage', 'processed', 'high',
 'marketing_activity', 202, '李四/高新分行', 301, '零售业务部',
 '2022-03-23 16:00:00', '2022-03-23 17:30:00', 90, '为客户制定了个性化理财方案',
 4, '方案不错，考虑中', b'1', '基金组合', 200000.00, NULL,
 b'0', NULL, b'0', '1', NOW(), '1', NOW(), b'0', 1),

(1, 4, 'enterprise_banking', '需求内容需求内容需求内容', '2022-03-23 16:00:00',
 b'0', 'DEM202203230004', 'business_handle', 'pending', 'normal',
 'customer_active', NULL, NULL, NULL, NULL,
 NULL, NULL, NULL, NULL,
 NULL, NULL, b'0', NULL, NULL, NULL,
 b'0', NULL, b'0', '1', NOW(), '1', NOW(), b'0', 1),

(1, 5, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 17:00:00',
 b'1', 'DEM202203230005', 'loan_apply', 'processed', 'urgent',
 'customer_active', 203, '王五/高新分行', 302, '贷款部',
 '2022-03-23 17:15:00', '2022-03-24 10:00:00', 1005, '贷款申请已批准，额度50万',
 5, '办理很快，非常满意', b'0', '个人消费贷款', 500000.00, 500000.00,
 b'1', '2022-03-24 10:00:00', b'1', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2-10的需求数据（简化版，每人3-4条）
INSERT INTO `crm_customer_demand` (
  `customer_id`, `sequence_no`, `demand_channel`, `demand_content`, `submit_time`,
  `is_processed`, `demand_no`, `demand_type`, `demand_status`, `demand_priority`,
  `demand_source`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户2: 李四 - 4条
(2, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:00:00',
 b'1', 'DEM202203230006', 'product_consult', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(2, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:00:00',
 b'0', 'DEM202203230007', 'wealth_manage', 'pending', 'high',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(2, 3, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 13:00:00',
 b'1', 'DEM202203230008', 'credit_card', 'processed', 'normal',
 'customer_active', 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(2, 4, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 15:00:00',
 b'1', 'DEM202203230009', 'account_query', 'processed', 'low',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户3: 王五 - 3条
(3, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 08:30:00',
 b'1', 'DEM202203230010', 'loan_apply', 'processed', 'urgent',
 'customer_active', 203, '王五/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(3, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:30:00',
 b'1', 'DEM202203230011', 'business_handle', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(3, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 14:00:00',
 b'0', 'DEM202203230012', 'product_consult', 'pending', 'normal',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户4: 赵六 - 5条
(4, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:15:00',
 b'1', 'DEM202203230013', 'wealth_manage', 'processed', 'high',
 'data_analysis', 204, '赵六/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:30:00',
 b'1', 'DEM202203230014', 'product_consult', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, 3, 'enterprise_banking', '需求内容需求内容需求内容', '2022-03-23 13:30:00',
 b'0', 'DEM202203230015', 'account_open', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(4, 4, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 15:30:00',
 b'1', 'DEM202203230016', 'business_handle', 'processed', 'normal',
 'customer_active', 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, 5, 'mobile_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 17:00:00',
 b'1', 'DEM202203230017', 'complaint_suggest', 'processed', 'high',
 'customer_active', 204, '赵六/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户5: 孙七 - 4条
(5, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 08:00:00',
 b'1', 'DEM202203230018', 'loan_apply', 'processed', 'urgent',
 'customer_active', 205, '孙七/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(5, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:00:00',
 b'1', 'DEM202203230019', 'wealth_manage', 'processed', 'normal',
 'marketing_activity', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(5, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 14:30:00',
 b'0', 'DEM202203230020', 'product_consult', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(5, 4, 'mobile_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 16:30:00',
 b'1', 'DEM202203230021', 'account_query', 'processed', 'low',
 'customer_active', 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户6: 周八 - 3条
(6, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:30:00',
 b'1', 'DEM202203230022', 'credit_card', 'processed', 'normal',
 'customer_active', 206, '周八/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(6, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 12:00:00',
 b'1', 'DEM202203230023', 'business_handle', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(6, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 15:00:00',
 b'0', 'DEM202203230024', 'wealth_manage', 'pending', 'high',
 'data_analysis', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7: 吴九 - 3条
(7, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 08:45:00',
 b'1', 'DEM202203230025', 'product_consult', 'processed', 'normal',
 'customer_active', 207, '吴九/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(7, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:45:00',
 b'1', 'DEM202203230026', 'loan_apply', 'processed', 'high',
 'customer_active', 203, '王五/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(7, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 16:00:00',
 b'0', 'DEM202203230027', 'account_query', 'pending', 'low',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8: 郑十 - 3条
(8, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:45:00',
 b'1', 'DEM202203230028', 'wealth_manage', 'processed', 'normal',
 'marketing_activity', 208, '郑十/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(8, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 12:30:00',
 b'1', 'DEM202203230029', 'business_handle', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(8, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 15:30:00',
 b'0', 'DEM202203230030', 'product_consult', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9: 陈十一 - 3条
(9, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:15:00',
 b'1', 'DEM202203230031', 'credit_card', 'processed', 'normal',
 'customer_active', 209, '陈十一/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(9, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 13:15:00',
 b'1', 'DEM202203230032', 'account_open', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(9, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 16:15:00',
 b'0', 'DEM202203230033', 'wealth_manage', 'pending', 'high',
 'data_analysis', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10: 黄十二 - 3条
(10, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:45:00',
 b'1', 'DEM202203230034', 'loan_apply', 'processed', 'urgent',
 'customer_active', 210, '黄十二/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(10, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 13:45:00',
 b'1', 'DEM202203230035', 'business_handle', 'processed', 'normal',
 'customer_active', 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(10, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 16:45:00',
 b'0', 'DEM202203230036', 'product_consult', 'pending', 'normal',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 对公客户需求信息 ====================

-- 客户11-20的需求数据（对公客户，各3-4条）
INSERT INTO `crm_customer_demand` (
  `customer_id`, `sequence_no`, `demand_channel`, `demand_content`, `submit_time`,
  `is_processed`, `demand_no`, `demand_type`, `demand_status`, `demand_priority`,
  `demand_source`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户11: 杭州科技有限公司 - 4条
(11, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 08:15:00',
 b'1', 'DEM202203230037', 'loan_apply', 'processed', 'urgent',
 'customer_active', 211, '卫经理', '1', NOW(), '1', NOW(), b'0', 1),
(11, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:15:00',
 b'1', 'DEM202203230038', 'account_open', 'processed', 'high',
 'customer_active', 212, '雷工程师', '1', NOW(), '1', NOW(), b'0', 1),
(11, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 14:15:00',
 b'0', 'DEM202203230039', 'product_consult', 'pending', 'normal',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(11, 4, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 16:15:00',
 b'1', 'DEM202203230040', 'business_handle', 'processed', 'normal',
 'customer_active', 213, '项经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12: 浙江贸易股份有限公司 - 4条
(12, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 08:30:00',
 b'1', 'DEM202203230041', 'wealth_manage', 'processed', 'high',
 'data_analysis', 214, '印经理', '1', NOW(), '1', NOW(), b'0', 1),
(12, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:30:00',
 b'1', 'DEM202203230042', 'loan_apply', 'processed', 'urgent',
 'customer_active', 215, '齐经理', '1', NOW(), '1', NOW(), b'0', 1),
(12, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 14:30:00',
 b'0', 'DEM202203230043', 'product_consult', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(12, 4, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 17:00:00',
 b'1', 'DEM202203230044', 'account_query', 'processed', 'low',
 'customer_active', 216, '佟经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13: 宁波制造业集团 - 3条
(13, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:00:00',
 b'1', 'DEM202203230045', 'loan_apply', 'processed', 'urgent',
 'customer_active', 217, '包经理', '1', NOW(), '1', NOW(), b'0', 1),
(13, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 12:00:00',
 b'1', 'DEM202203230046', 'business_handle', 'processed', 'normal',
 'customer_active', 218, '储经理', '1', NOW(), '1', NOW(), b'0', 1),
(13, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 15:00:00',
 b'0', 'DEM202203230047', 'product_consult', 'pending', 'high',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14: 温州建设工程公司 - 4条
(14, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:15:00',
 b'1', 'DEM202203230048', 'loan_apply', 'processed', 'urgent',
 'customer_active', 219, '顾经理', '1', NOW(), '1', NOW(), b'0', 1),
(14, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:15:00',
 b'1', 'DEM202203230049', 'wealth_manage', 'processed', 'normal',
 'data_analysis', 220, '熊经理', '1', NOW(), '1', NOW(), b'0', 1),
(14, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 14:45:00',
 b'0', 'DEM202203230050', 'account_open', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),
(14, 4, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 17:15:00',
 b'1', 'DEM202203230051', 'business_handle', 'processed', 'normal',
 'customer_active', 221, '谈经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15: 绍兴纺织实业公司 - 3条
(15, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:30:00',
 b'1', 'DEM202203230052', 'loan_apply', 'processed', 'high',
 'customer_active', 222, '边经理', '1', NOW(), '1', NOW(), b'0', 1),
(15, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 12:30:00',
 b'1', 'DEM202203230053', 'product_consult', 'processed', 'normal',
 'customer_active', 223, '殷经理', '1', NOW(), '1', NOW(), b'0', 1),
(15, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 16:30:00',
 b'0', 'DEM202203230054', 'wealth_manage', 'pending', 'high',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16: 金华物流运输公司 - 3条
(16, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 09:45:00',
 b'1', 'DEM202203230055', 'loan_apply', 'processed', 'urgent',
 'customer_active', 224, '华经理', '1', NOW(), '1', NOW(), b'0', 1),
(16, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 13:00:00',
 b'1', 'DEM202203230056', 'business_handle', 'processed', 'normal',
 'customer_active', 225, '彭经理', '1', NOW(), '1', NOW(), b'0', 1),
(16, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 16:00:00',
 b'0', 'DEM202203230057', 'product_consult', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17: 台州电子科技公司 - 3条
(17, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:00:00',
 b'1', 'DEM202203230058', 'account_open', 'processed', 'high',
 'customer_active', 226, '岳经理', '1', NOW(), '1', NOW(), b'0', 1),
(17, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 13:30:00',
 b'1', 'DEM202203230059', 'loan_apply', 'processed', 'urgent',
 'customer_active', 227, '关经理', '1', NOW(), '1', NOW(), b'0', 1),
(17, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 17:30:00',
 b'0', 'DEM202203230060', 'wealth_manage', 'pending', 'normal',
 'data_analysis', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18: 嘉兴食品加工厂 - 3条
(18, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 10:30:00',
 b'1', 'DEM202203230061', 'loan_apply', 'processed', 'urgent',
 'customer_active', 228, '蔡经理', '1', NOW(), '1', NOW(), b'0', 1),
(18, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 14:00:00',
 b'1', 'DEM202203230062', 'business_handle', 'processed', 'normal',
 'customer_active', 229, '武经理', '1', NOW(), '1', NOW(), b'0', 1),
(18, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 17:00:00',
 b'0', 'DEM202203230063', 'product_consult', 'pending', 'normal',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19: 湖州化工材料公司 - 3条
(19, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:00:00',
 b'1', 'DEM202203230064', 'wealth_manage', 'processed', 'high',
 'data_analysis', 230, '沈经理', '1', NOW(), '1', NOW(), b'0', 1),
(19, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 14:30:00',
 b'1', 'DEM202203230065', 'loan_apply', 'processed', 'urgent',
 'customer_active', 231, '樊经理', '1', NOW(), '1', NOW(), b'0', 1),
(19, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 17:30:00',
 b'0', 'DEM202203230066', 'account_query', 'pending', 'low',
 'customer_active', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20: 衢州矿业开发公司 - 3条
(20, 1, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 11:30:00',
 b'1', 'DEM202203230067', 'loan_apply', 'processed', 'urgent',
 'customer_active', 232, '於经理', '1', NOW(), '1', NOW(), b'0', 1),
(20, 2, 'enterprise_banking', '需求内容需求内容需求内容需求内容', '2022-03-23 15:00:00',
 b'1', 'DEM202203230068', 'business_handle', 'processed', 'normal',
 'customer_active', 233, '申经理', '1', NOW(), '1', NOW(), b'0', 1),
(20, 3, 'crm', '需求内容需求内容需求内容需求内容', '2022-03-23 18:00:00',
 b'0', 'DEM202203230069', 'product_consult', 'pending', 'normal',
 'marketing_activity', NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 验证查询 ====================

-- 1. 统计每个客户的需求数量
SELECT
  cd.customer_id,
  cc.customer_name,
  COUNT(*) AS demand_count,
  SUM(CASE WHEN cd.is_processed = b'1' THEN 1 ELSE 0 END) AS processed_count,
  SUM(CASE WHEN cd.is_processed = b'0' THEN 1 ELSE 0 END) AS pending_count
FROM crm_customer_demand cd
LEFT JOIN crm_customer cc ON cd.customer_id = cc.id
GROUP BY cd.customer_id, cc.customer_name
ORDER BY cd.customer_id;

-- 2. 按需求渠道统计
SELECT
  demand_channel,
  COUNT(*) AS count
FROM crm_customer_demand
GROUP BY demand_channel
ORDER BY count DESC;

-- 3. 按需求类型统计
SELECT
  demand_type,
  COUNT(*) AS count
FROM crm_customer_demand
GROUP BY demand_type
ORDER BY count DESC;

-- 4. 按需求状态统计
SELECT
  demand_status,
  COUNT(*) AS count
FROM crm_customer_demand
GROUP BY demand_status
ORDER BY count DESC;

-- 5. 按需求优先级统计
SELECT
  demand_priority,
  COUNT(*) AS count
FROM crm_customer_demand
GROUP BY demand_priority
ORDER BY count DESC;

-- 6. 按是否处理统计
SELECT
  is_processed,
  COUNT(*) AS count
FROM crm_customer_demand
GROUP BY is_processed;

-- 7. 最近的需求列表（前20条）
SELECT
  cd.demand_no,
  cc.customer_name,
  cd.demand_channel,
  cd.demand_type,
  cd.demand_status,
  cd.is_processed,
  cd.submit_time,
  cd.handler_user_name
FROM crm_customer_demand cd
LEFT JOIN crm_customer cc ON cd.customer_id = cc.id
ORDER BY cd.submit_time DESC
LIMIT 20;
