-- ----------------------------
-- 客户营销活动信息表测试数据
-- 为所有20个客户（10个零售 + 10个对公）生成3-5条营销活动记录
-- 涵盖各种活动形式、活动状态、审批状态
-- ----------------------------

-- 清空表数据
TRUNCATE TABLE `crm_customer_marketing_activity`;

-- ==================== 零售客户营销活动信息 ====================

-- 客户1: 张三 (customer_id=1) - 5条活动
INSERT INTO `crm_customer_marketing_activity` (
  `customer_id`, `sequence_no`, `activity_name`, `activity_no`, `activity_form`,
  `activity_status`, `approval_status`, `activity_order_time`, `activity_end_time`,
  `is_send_sms`, `start_dt`, `end_dt`, `activity_type`, `activity_location`,
  `activity_budget`, `activity_cost`, `target_customer_count`, `actual_customer_count`,
  `expected_effect`, `actual_effect`, `handler_user_id`, `handler_user_name`,
  `handler_dept_id`, `handler_dept_name`, `approver_user_id`, `approver_user_name`,
  `approval_time`, `approval_comment`, `sms_send_time`, `sms_content`,
  `activity_score`, `customer_feedback`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, '亲子沙龙', '1082170869', 'parent_child', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', '2023-06-07', '2023-12-07', 'customer_maintain',
 '杭州市西湖区银行VIP厅', 50000.00, 45000.00, 30, 28,
 '提升客户满意度，增强客户粘性', '活动效果良好，客户反馈积极', 201, '张三/高新分行',
 301, '零售业务部', 401, '李经理', '2023-06-05 10:00:00', '活动方案完善，批准执行',
 '2023-06-06 09:00:00', '尊敬的客户，邀请您参加亲子沙龙活动...', 5,
 '活动很棒，孩子很喜欢', '1', NOW(), '1', NOW(), b'0', 1),

(1, 2, '客户关怀活动', '1082170870', 'visit', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', '2023-05-01', '2023-05-01', 'customer_maintain',
 '客户家中', 2000.00, 1800.00, 1, 1,
 '加强客户联系，了解客户需求', '客户满意，表示会继续使用我行服务', 201, '张三/高新分行',
 301, '零售业务部', 401, '李经理', '2023-04-28 15:00:00', '同意拜访',
 NULL, NULL, 5, '很感谢银行的关心', '1', NOW(), '1', NOW(), b'0', 1),

(1, 3, '线下沙龙', '1082170871', 'salon', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', '2023-07-15', '2023-07-15', 'product_promotion',
 '杭州西湖国际会议中心', 80000.00, 75000.00, 50, 45,
 '推广理财新产品，挖掘高端客户', '成功销售产品10份', 202, '李四/高新分行',
 301, '零售业务部', 401, '李经理', '2023-07-10 10:00:00', '活动计划详细，批准',
 '2023-07-13 09:00:00', '诚邀您参加理财沙龙活动...', 4,
 '产品介绍专业，服务周到', '1', NOW(), '1', NOW(), b'0', 1),

(1, 4, '客户拜访', '1082170872', 'visit', 'draft', 'pending_submit', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', '2023-08-20', '2023-08-20', 'customer_callback',
 '待定', 3000.00, NULL, 1, NULL,
 '回访客户，收集意见', NULL, 203, '王五/高新分行',
 301, '零售业务部', NULL, NULL, NULL, NULL,
 NULL, NULL, NULL, NULL, '1', NOW(), '1', NOW(), b'0', 1),

(1, 5, '线下沙龙', '1082170873', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', '2023-09-10', '2023-09-10', 'holiday_marketing',
 '杭州市中心营业厅', 60000.00, NULL, 40, NULL,
 '中秋节营销活动，提升品牌形象', NULL, 201, '张三/高新分行',
 301, '零售业务部', 401, '李经理', '2023-09-01 14:00:00', '活动方案可行，批准',
 '2023-09-05 10:00:00', '中秋佳节，诚邀您参加...', NULL,
 NULL, '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2-10的活动数据（简化版，每人3-4条）
INSERT INTO `crm_customer_marketing_activity` (
  `customer_id`, `sequence_no`, `activity_name`, `activity_no`, `activity_form`,
  `activity_status`, `approval_status`, `activity_order_time`, `activity_end_time`,
  `is_send_sms`, `activity_type`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户2: 李四 - 4条
(2, 1, '客户关怀活动', '1082170874', 'visit', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(2, 2, '电话营销', '1082170875', 'phone', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'product_promotion', 202, '李四/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(2, 3, '线下沙龙', '1082170876', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(2, 4, '短信营销', '1082170877', 'sms', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 203, '王五/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户3: 王五 - 3条
(3, 1, '亲子沙龙', '1082170878', 'parent_child', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(3, 2, '线下沙龙', '1082170879', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 202, '李四/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(3, 3, '客户拜访', '1082170880', 'visit', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户4: 赵六 - 5条
(4, 1, '客户拜访', '1082170881', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 204, '赵六/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(4, 2, '线下沙龙', '1082170882', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(4, 3, '电话营销', '1082170883', 'phone', 'draft', 'pending_submit', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'cross_selling', 202, '李四/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(4, 4, '亲子沙龙', '1082170884', 'parent_child', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 204, '赵六/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(4, 5, '微信营销', '1082170885', 'wechat', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户5: 孙七 - 4条
(5, 1, '线下沙龙', '1082170886', 'salon', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 205, '孙七/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(5, 2, '客户拜访', '1082170887', 'visit', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(5, 3, '线上活动', '1082170888', 'online', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'brand_publicity', 202, '李四/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(5, 4, '电话营销', '1082170889', 'phone', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'product_promotion', 205, '孙七/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户6: 周八 - 3条
(6, 1, '客户拜访', '1082170890', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 206, '周八/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(6, 2, '线下沙龙', '1082170891', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(6, 3, '亲子沙龙', '1082170892', 'parent_child', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 206, '周八/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7: 吴九 - 3条
(7, 1, '线下沙龙', '1082170893', 'salon', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 207, '吴九/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(7, 2, '电话营销', '1082170894', 'phone', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'cross_selling', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(7, 3, '客户拜访', '1082170895', 'visit', 'draft', 'pending_submit', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 207, '吴九/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8: 郑十 - 3条
(8, 1, '客户拜访', '1082170896', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 208, '郑十/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(8, 2, '线下沙龙', '1082170897', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'holiday_marketing', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(8, 3, '短信营销', '1082170898', 'sms', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 208, '郑十/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9: 陈十一 - 3条
(9, 1, '线下沙龙', '1082170899', 'salon', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'brand_publicity', 209, '陈十一/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(9, 2, '客户拜访', '1082170900', 'visit', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(9, 3, '亲子沙龙', '1082170901', 'parent_child', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'customer_maintain', 209, '陈十一/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10: 黄十二 - 3条
(10, 1, '客户拜访', '1082170902', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 210, '黄十二/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(10, 2, '线下沙龙', '1082170903', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 201, '张三/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1),
(10, 3, '会议营销', '1082170904', 'conference', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'value_added_service', 210, '黄十二/高新分行',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 对公客户营销活动信息 ====================

-- 客户11-20的活动数据（对公客户，各3-4条）
INSERT INTO `crm_customer_marketing_activity` (
  `customer_id`, `sequence_no`, `activity_name`, `activity_no`, `activity_form`,
  `activity_status`, `approval_status`, `activity_order_time`, `activity_end_time`,
  `is_send_sms`, `activity_type`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户11: 杭州科技有限公司 - 4条
(11, 1, '客户拜访', '1082170905', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 211, '卫经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(11, 2, '线下沙龙', '1082170906', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 212, '雷工程师',
 '1', NOW(), '1', NOW(), b'0', 1),
(11, 3, '会议营销', '1082170907', 'conference', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'cross_selling', 211, '卫经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(11, 4, '电话营销', '1082170908', 'phone', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 213, '项经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12: 浙江贸易股份有限公司 - 4条
(12, 1, '客户拜访', '1082170909', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 214, '印经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(12, 2, '会议营销', '1082170910', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 215, '齐经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(12, 3, '线下沙龙', '1082170911', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'brand_publicity', 214, '印经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(12, 4, '客户拜访', '1082170912', 'visit', 'draft', 'pending_submit', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 216, '佟经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13: 宁波制造业集团 - 3条
(13, 1, '客户拜访', '1082170913', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 217, '包经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(13, 2, '会议营销', '1082170914', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 218, '储经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(13, 3, '线下沙龙', '1082170915', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'cross_selling', 217, '包经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14: 温州建设工程公司 - 4条
(14, 1, '客户拜访', '1082170916', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 219, '顾经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(14, 2, '会议营销', '1082170917', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 220, '熊经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(14, 3, '电话营销', '1082170918', 'phone', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 219, '顾经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(14, 4, '线下沙龙', '1082170919', 'salon', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'brand_publicity', 221, '谈经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15: 绍兴纺织实业公司 - 3条
(15, 1, '客户拜访', '1082170920', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 222, '边经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(15, 2, '会议营销', '1082170921', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 223, '殷经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(15, 3, '线下沙龙', '1082170922', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'holiday_marketing', 222, '边经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16: 金华物流运输公司 - 3条
(16, 1, '客户拜访', '1082170923', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 224, '华经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(16, 2, '会议营销', '1082170924', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 225, '彭经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(16, 3, '电话营销', '1082170925', 'phone', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'cross_selling', 224, '华经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17: 台州电子科技公司 - 3条
(17, 1, '客户拜访', '1082170926', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 226, '岳经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(17, 2, '会议营销', '1082170927', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 227, '关经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(17, 3, '线下沙龙', '1082170928', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'value_added_service', 226, '岳经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18: 嘉兴食品加工厂 - 3条
(18, 1, '客户拜访', '1082170929', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 228, '蔡经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(18, 2, '会议营销', '1082170930', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 229, '武经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(18, 3, '电话营销', '1082170931', 'phone', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_callback', 228, '蔡经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19: 湖州化工材料公司 - 3条
(19, 1, '客户拜访', '1082170932', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 230, '沈经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(19, 2, '会议营销', '1082170933', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 231, '樊经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(19, 3, '线下沙龙', '1082170934', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'brand_publicity', 230, '沈经理',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20: 衢州矿业开发公司 - 3条
(20, 1, '客户拜访', '1082170935', 'visit', 'executing', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'0', 'customer_maintain', 232, '於经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(20, 2, '会议营销', '1082170936', 'conference', 'completed', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'product_promotion', 233, '申经理',
 '1', NOW(), '1', NOW(), b'0', 1),
(20, 3, '线下沙龙', '1082170937', 'salon', 'pending', 'approved', '2023-06-07 12:00:00',
 '2023-12-07 12:00:00', b'1', 'cross_selling', 232, '於经理',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 验证查询 ====================

-- 1. 统计每个客户的营销活动数量
SELECT
  cma.customer_id,
  cc.customer_name,
  COUNT(*) AS activity_count,
  SUM(CASE WHEN cma.activity_status = 'completed' THEN 1 ELSE 0 END) AS completed_count,
  SUM(CASE WHEN cma.activity_status = 'executing' THEN 1 ELSE 0 END) AS executing_count,
  SUM(CASE WHEN cma.activity_status = 'pending' THEN 1 ELSE 0 END) AS pending_count,
  SUM(CASE WHEN cma.activity_status = 'draft' THEN 1 ELSE 0 END) AS draft_count
FROM crm_customer_marketing_activity cma
LEFT JOIN crm_customer cc ON cma.customer_id = cc.id
GROUP BY cma.customer_id, cc.customer_name
ORDER BY cma.customer_id;

-- 2. 按活动形式统计
SELECT
  activity_form,
  COUNT(*) AS count
FROM crm_customer_marketing_activity
GROUP BY activity_form
ORDER BY count DESC;

-- 3. 按活动状态统计
SELECT
  activity_status,
  COUNT(*) AS count
FROM crm_customer_marketing_activity
GROUP BY activity_status
ORDER BY count DESC;

-- 4. 按审批状态统计
SELECT
  approval_status,
  COUNT(*) AS count
FROM crm_customer_marketing_activity
GROUP BY approval_status
ORDER BY count DESC;

-- 5. 按活动类型统计
SELECT
  activity_type,
  COUNT(*) AS count
FROM crm_customer_marketing_activity
GROUP BY activity_type
ORDER BY count DESC;

-- 6. 统计是否发送短信
SELECT
  is_send_sms,
  COUNT(*) AS count
FROM crm_customer_marketing_activity
GROUP BY is_send_sms;

-- 7. 最近的营销活动列表（前20条）
SELECT
  cma.activity_no,
  cc.customer_name,
  cma.activity_name,
  cma.activity_form,
  cma.activity_status,
  cma.approval_status,
  cma.activity_order_time,
  cma.handler_user_name
FROM crm_customer_marketing_activity cma
LEFT JOIN crm_customer cc ON cma.customer_id = cc.id
ORDER BY cma.create_time DESC
LIMIT 20;
