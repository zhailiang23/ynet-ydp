-- ----------------------------
-- 客户投诉信息测试数据
-- 说明:
-- 1. 为所有20个客户（10个零售客户 + 10个对公客户）创建投诉信息
-- 2. 每个客户创建3-5条投诉记录
-- 3. 涵盖多种投诉渠道、类型、状态
-- 4. 投诉内容真实合理，符合业务场景
-- ----------------------------

-- ==================== 零售客户投诉信息 ====================

-- 客户1: 张三 (customer_id=1) - 5条投诉
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `handler_dept_id`, `handler_dept_name`, `process_result`,
  `process_start_time`, `process_end_time`, `process_duration`, `customer_satisfaction`,
  `satisfaction_feedback`, `is_closed`, `close_time`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, 'WO202510290001', 'ECIF00001', '张三', 'completed', '2025-10-28 16:30:00', 'mobile_banking',
 'service_attitude', '手机银行客服态度不好，回复速度慢', '2025-10-25 09:15:00', 'normal', 101,
 '李客服', 201, '客户服务部', '已与客户沟通，进行了道歉，并加强了客服培训', '2025-10-25 10:00:00',
 '2025-10-28 16:30:00', 4950, 4, '问题已解决，服务态度有所改善', b'1', '2025-10-28 17:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, 2, 'WO202510150002', 'ECIF00001', '张三', 'closed', '2025-10-16 14:00:00', 'call_center',
 'business_process', '理财产品赎回流程太复杂，时间过长', '2025-10-14 14:20:00', 'important', 102,
 '王经理', 202, '个人金融部', '已优化理财赎回流程，缩短了处理时间', '2025-10-14 15:00:00',
 '2025-10-16 14:00:00', 2840, 5, '非常满意，处理及时高效', b'1', '2025-10-16 15:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, 3, 'WO202509200003', 'ECIF00001', '张三', 'closed', '2025-09-22 10:30:00', 'online_banking',
 'system_failure', '网银转账时系统卡顿，导致重复扣款', '2025-09-20 11:00:00', 'urgent', 103,
 '赵主管', 203, '运营技术部', '已退还重复扣款，系统问题已修复', '2025-09-20 11:30:00',
 '2025-09-22 10:30:00', 2820, 5, '处理速度很快，问题彻底解决', b'1', '2025-09-22 11:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, 4, 'WO202508100004', 'ECIF00001', '张三', 'closed', '2025-08-12 16:00:00', 'account_manager',
 'product_issue', '购买的基金产品收益与宣传不符', '2025-08-10 09:30:00', 'normal', 104,
 '钱经理', 202, '个人金融部', '已详细解释基金市场波动原因，客户表示理解', '2025-08-10 14:00:00',
 '2025-08-12 16:00:00', 3120, 3, '解释合理，但希望产品介绍更清楚', b'1', '2025-08-13 09:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, 5, 'WO202507050005', 'ECIF00001', '张三', 'closed', '2025-07-08 11:30:00', 'counter',
 'waiting_time', '柜台办理业务等待时间过长，排队1小时', '2025-07-05 15:40:00', 'normal', 105,
 '孙主任', 204, '营业部', '已增加柜台窗口数量，优化业务流程', '2025-07-06 09:00:00',
 '2025-07-08 11:30:00', 3150, 4, '等待时间有所缩短', b'1', '2025-07-08 15:00:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2: 李四 (customer_id=2) - 4条投诉
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `handler_dept_id`, `handler_dept_name`, `process_result`,
  `process_start_time`, `process_end_time`, `process_duration`, `customer_satisfaction`,
  `is_closed`, `close_time`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(2, 1, 'WO202510200006', 'ECIF00002', '李四', 'processing', '2025-10-28 09:00:00', 'mobile_banking',
 'fee_dispute', 'ATM跨行取款手续费收取有误', '2025-10-20 10:15:00', 'important', 106,
 '周经理', 205, '运营管理部', NULL, '2025-10-20 14:00:00', NULL, NULL, NULL,
 b'0', NULL, '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, 'WO202509150007', 'ECIF00002', '李四', 'closed', '2025-09-18 14:30:00', 'call_center',
 'business_process', '信用卡申请审批时间过长', '2025-09-15 11:20:00', 'normal', 107,
 '吴主管', 206, '信用卡中心', '已加快审批流程，信用卡已发放', '2025-09-15 15:00:00',
 '2025-09-18 14:30:00', 4890, 4, b'1', '2025-09-18 16:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 3, 'WO202508250008', 'ECIF00002', '李四', 'closed', '2025-08-27 10:00:00', 'wechat',
 'service_attitude', '微信客服回复不及时', '2025-08-25 16:30:00', 'normal', 108,
 '郑客服', 201, '客户服务部', '已加强微信客服响应速度', '2025-08-26 09:00:00',
 '2025-08-27 10:00:00', 1500, 3, b'1', '2025-08-27 14:00:00',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 4, 'WO202507180009', 'ECIF00002', '李四', 'closed', '2025-07-20 15:30:00', 'counter',
 'business_process', '开通网银业务流程复杂', '2025-07-18 09:45:00', 'normal', 109,
 '冯经理', 204, '营业部', '已简化网银开通流程，提供操作指引', '2025-07-18 14:00:00',
 '2025-07-20 15:30:00', 3345, 4, b'1', '2025-07-20 16:00:00',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户3: 王五 (customer_id=3) - 3条投诉
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `process_result`, `process_start_time`, `process_end_time`,
  `process_duration`, `customer_satisfaction`, `is_closed`, `close_time`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(3, 1, 'WO202510180010', 'ECIF00003', '王五', 'pending_feedback', '2025-10-27 11:00:00',
 'online_banking', 'system_failure', '网银登录频繁提示密码错误', '2025-10-18 14:20:00', 'normal',
 110, '陈工程师', '已重置密码，系统正常', '2025-10-18 15:00:00', '2025-10-27 11:00:00',
 18940, 4, b'0', NULL, '1', NOW(), '1', NOW(), b'0', 1),

(3, 2, 'WO202509080011', 'ECIF00003', '王五', 'closed', '2025-09-10 16:00:00', 'mobile_banking',
 'product_issue', '理财产品到期未自动赎回', '2025-09-08 10:30:00', 'important', 111,
 '刘经理', '已手动处理赎回，资金到账', '2025-09-08 14:00:00', '2025-09-10 16:00:00',
 3930, 5, b'1', '2025-09-10 17:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(3, 3, 'WO202508050012', 'ECIF00003', '王五', 'closed', '2025-08-07 14:30:00', 'call_center',
 'waiting_time', '客服电话等待时间过长', '2025-08-05 09:15:00', 'normal', 112,
 '何主管', '已增加客服人员配置', '2025-08-05 14:00:00', '2025-08-07 14:30:00',
 3075, 3, b'1', '2025-08-07 16:00:00', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户4: 赵六 (customer_id=4) - 5条投诉
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `process_result`, `process_start_time`, `process_end_time`,
  `process_duration`, `customer_satisfaction`, `is_closed`, `close_time`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(4, 1, 'WO202510220013', 'ECIF00004', '赵六', 'processing', '2025-10-28 14:00:00', 'account_manager',
 'product_issue', 'VIP理财产品收益率与承诺不符', '2025-10-22 11:00:00', 'urgent', 113,
 '许经理', NULL, '2025-10-22 14:00:00', NULL, NULL, NULL, b'0', NULL,
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 2, 'WO202509250014', 'ECIF00004', '赵六', 'closed', '2025-09-28 10:00:00', 'mobile_banking',
 'system_failure', '大额转账交易失败但扣款成功', '2025-09-25 15:30:00', 'urgent', 114,
 '曾主管', '已退还扣款，系统问题已修复', '2025-09-25 16:00:00', '2025-09-28 10:00:00',
 4050, 4, b'1', '2025-09-28 11:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(4, 3, 'WO202508200015', 'ECIF00004', '赵六', 'closed', '2025-08-22 16:30:00', 'counter',
 'service_attitude', '柜员办理业务态度冷淡', '2025-08-20 10:45:00', 'normal', 115,
 '彭主任', '已对柜员进行培训和教育', '2025-08-20 14:00:00', '2025-08-22 16:30:00',
 3705, 4, b'1', '2025-08-23 09:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, 'WO202507120016', 'ECIF00004', '赵六', 'closed', '2025-07-15 11:00:00', 'online_banking',
 'fee_dispute', '网银转账手续费计算错误', '2025-07-12 14:20:00', 'important', 116,
 '贾经理', '已退还多收费用，系统已修正', '2025-07-12 15:00:00', '2025-07-15 11:00:00',
 4220, 5, b'1', '2025-07-15 14:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(4, 5, 'WO202506080017', 'ECIF00004', '赵六', 'closed', '2025-06-10 15:00:00', 'wechat',
 'business_process', '贷款提前还款流程不清晰', '2025-06-08 09:30:00', 'normal', 117,
 '方经理', '已提供详细操作指引', '2025-06-08 14:00:00', '2025-06-10 15:00:00',
 3270, 4, b'1', '2025-06-10 16:00:00', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户5: 孙七 (customer_id=5) - 4条投诉
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `process_result`, `process_start_time`, `process_end_time`,
  `process_duration`, `customer_satisfaction`, `is_closed`, `close_time`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(5, 1, 'WO202510120018', 'ECIF00005', '孙七', 'completed', '2025-10-26 14:00:00', 'mobile_banking',
 'business_process', '手机银行转账限额设置不合理', '2025-10-12 10:15:00', 'normal', 118,
 '邵经理', '已调整客户转账限额', '2025-10-12 14:00:00', '2025-10-26 14:00:00',
 20220, 4, b'1', '2025-10-26 16:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(5, 2, 'WO202509050019', 'ECIF00005', '孙七', 'closed', '2025-09-07 11:30:00', 'call_center',
 'product_issue', '基金定投扣款时间不准确', '2025-09-05 09:00:00', 'normal', 119,
 '洪经理', '已调整定投扣款设置', '2025-09-05 14:00:00', '2025-09-07 11:30:00',
 3090, 4, b'1', '2025-09-07 14:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(5, 3, 'WO202508010020', 'ECIF00005', '孙七', 'closed', '2025-08-03 16:00:00', 'counter',
 'waiting_time', '办理银行卡换卡业务等待时间长', '2025-08-01 14:30:00', 'normal', 120,
 '汤主任', '已优化业务办理流程', '2025-08-02 09:00:00', '2025-08-03 16:00:00',
 2790, 3, b'1', '2025-08-03 17:00:00', '1', NOW(), '1', NOW(), b'0', 1),

(5, 4, 'WO202507060021', 'ECIF00005', '孙七', 'closed', '2025-07-09 10:30:00', 'online_banking',
 'system_failure', '网银U盾驱动安装失败', '2025-07-06 15:20:00', 'important', 121,
 '滕工程师', '已协助客户重新安装驱动', '2025-07-07 09:00:00', '2025-07-09 10:30:00',
 2970, 4, b'1', '2025-07-09 14:00:00', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户6-10的投诉数据（简化版，各3条）
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `is_closed`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户6: 周八
(6, 1, 'WO202510080022', 'ECIF00006', '周八', 'pending', '2025-10-28 09:00:00', 'mobile_banking',
 'system_failure', '手机银行无法登录', '2025-10-08 11:20:00', 'normal', 122, '姚工程师',
 b'0', '1', NOW(), '1', NOW(), b'0', 1),

(6, 2, 'WO202509010023', 'ECIF00006', '周八', 'closed', '2025-09-03 14:00:00', 'call_center',
 'service_attitude', '客服人员态度不好', '2025-09-01 10:30:00', 'normal', 123, '邹主管',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(6, 3, 'WO202508100024', 'ECIF00006', '周八', 'closed', '2025-08-12 11:00:00', 'counter',
 'business_process', '开通短信通知业务办理慢', '2025-08-10 15:00:00', 'normal', 124, '熊柜员',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7: 吴九
(7, 1, 'WO202510190025', 'ECIF00007', '吴九', 'processing', '2025-10-28 10:00:00', 'account_manager',
 'product_issue', 'VIP专属理财产品收益不达预期', '2025-10-19 14:30:00', 'important', 125, '屈经理',
 b'0', '1', NOW(), '1', NOW(), b'0', 1),

(7, 2, 'WO202509180026', 'ECIF00007', '吴九', 'closed', '2025-09-20 15:30:00', 'mobile_banking',
 'fee_dispute', '账户管理费收取有疑问', '2025-09-18 09:45:00', 'normal', 126, '舒经理',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(7, 3, 'WO202508150027', 'ECIF00007', '吴九', 'closed', '2025-08-17 14:00:00', 'online_banking',
 'system_failure', '大额转账页面卡顿', '2025-08-15 16:20:00', 'important', 127, '祝工程师',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8: 郑十
(8, 1, 'WO202510050028', 'ECIF00008', '郑十', 'completed', '2025-10-25 11:00:00', 'call_center',
 'business_process', '信用卡还款到账时间延迟', '2025-10-05 10:15:00', 'normal', 128, '毛经理',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(8, 2, 'WO202509120029', 'ECIF00008', '郑十', 'closed', '2025-09-14 16:00:00', 'wechat',
 'service_attitude', '微信客服回复态度敷衍', '2025-09-12 14:30:00', 'normal', 129, '游客服',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(8, 3, 'WO202508200030', 'ECIF00008', '郑十', 'closed', '2025-08-22 10:30:00', 'counter',
 'waiting_time', '取号后等待时间超过30分钟', '2025-08-20 11:00:00', 'normal', 130, '郁主任',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9: 陈十一
(9, 1, 'WO202510020031', 'ECIF00009', '陈十一', 'pending', '2025-10-28 08:00:00', 'mobile_banking',
 'business_process', '小额贷款申请审批慢', '2025-10-02 09:20:00', 'normal', 131, '尹经理',
 b'0', '1', NOW(), '1', NOW(), b'0', 1),

(9, 2, 'WO202509080032', 'ECIF00009', '陈十一', 'closed', '2025-09-10 14:30:00', 'call_center',
 'product_issue', '存款利率与宣传不一致', '2025-09-08 15:40:00', 'normal', 132, '柏经理',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(9, 3, 'WO202508050033', 'ECIF00009', '陈十一', 'closed', '2025-08-07 11:00:00', 'counter',
 'service_attitude', '柜员服务不耐烦', '2025-08-05 14:15:00', 'normal', 133, '水主任',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10: 黄十二
(10, 1, 'WO202510240034', 'ECIF00010', '黄十二', 'processing', '2025-10-28 15:00:00', 'account_manager',
 'product_issue', 'VIP专属产品额度不足', '2025-10-24 10:00:00', 'important', 134, '席经理',
 b'0', '1', NOW(), '1', NOW(), b'0', 1),

(10, 2, 'WO202509220035', 'ECIF00010', '黄十二', 'closed', '2025-09-25 16:00:00', 'online_banking',
 'system_failure', '网银支付功能异常', '2025-09-22 11:30:00', 'urgent', 135, '习工程师',
 b'1', '1', NOW(), '1', NOW(), b'0', 1),

(10, 3, 'WO202508180036', 'ECIF00010', '黄十二', 'closed', '2025-08-20 14:00:00', 'mobile_banking',
 'fee_dispute', 'VIP客户仍被收取手续费', '2025-08-18 09:00:00', 'important', 136, '包经理',
 b'1', '1', NOW(), '1', NOW(), b'0', 1);

-- ==================== 对公客户投诉信息 ====================

-- 客户11-20的投诉数据（对公客户，各3-4条）
INSERT INTO `crm_customer_complaint` (
  `customer_id`, `sequence_no`, `work_order_no`, `ecif_customer_no`, `customer_name`,
  `work_order_status`, `last_process_time`, `complaint_channel`, `complaint_type`,
  `complaint_content`, `complaint_time`, `complaint_level`, `handler_user_id`,
  `handler_user_name`, `is_closed`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户11: 杭州科技有限公司 - 4条
(11, 1, 'WO202510210037', 'ECIF00011', '杭州科技有限公司', 'processing', '2025-10-28 11:00:00',
 'account_manager', 'business_process', '对公账户开户流程复杂，资料要求过多', '2025-10-21 14:00:00',
 'important', 137, '卫经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(11, 2, 'WO202509160038', 'ECIF00011', '杭州科技有限公司', 'closed', '2025-09-19 15:00:00',
 'online_banking', 'system_failure', '企业网银批量转账功能故障', '2025-09-16 10:30:00', 'urgent',
 138, '雷工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(11, 3, 'WO202508220039', 'ECIF00011', '杭州科技有限公司', 'closed', '2025-08-25 14:00:00',
 'counter', 'fee_dispute', '电子回单打印费用过高', '2025-08-22 11:00:00', 'normal', 139,
 '项经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(11, 4, 'WO202507150040', 'ECIF00011', '杭州科技有限公司', 'closed', '2025-07-18 16:00:00',
 'call_center', 'service_attitude', '对公客户经理响应速度慢', '2025-07-15 09:30:00', 'normal',
 140, '齐经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12: 浙江贸易股份有限公司 - 4条
(12, 1, 'WO202510170041', 'ECIF00012', '浙江贸易股份有限公司', 'pending_feedback', '2025-10-28 10:00:00',
 'account_manager', 'product_issue', '贸易融资产品审批时间长', '2025-10-17 15:20:00', 'important',
 141, '印经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(12, 2, 'WO202509140042', 'ECIF00012', '浙江贸易股份有限公司', 'closed', '2025-09-17 14:30:00',
 'online_banking', 'system_failure', '国际结算系统操作不便', '2025-09-14 09:00:00', 'normal',
 142, '符工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(12, 3, 'WO202508120043', 'ECIF00012', '浙江贸易股份有限公司', 'closed', '2025-08-15 11:00:00',
 'counter', 'business_process', '信用证办理流程繁琐', '2025-08-12 14:00:00', 'normal', 143,
 '喻经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(12, 4, 'WO202507080044', 'ECIF00012', '浙江贸易股份有限公司', 'closed', '2025-07-11 16:00:00',
 'account_manager', 'fee_dispute', '国际汇款手续费计算错误', '2025-07-08 10:30:00', 'important',
 144, '景经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13: 宁波制造业集团 - 3条
(13, 1, 'WO202510130045', 'ECIF00013', '宁波制造业集团', 'processing', '2025-10-28 14:00:00',
 'account_manager', 'product_issue', '供应链金融产品额度不够', '2025-10-13 11:00:00', 'urgent',
 145, '束经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(13, 2, 'WO202509100046', 'ECIF00013', '宁波制造业集团', 'closed', '2025-09-13 15:00:00',
 'online_banking', 'system_failure', '企业网银大额支付超时', '2025-09-10 14:30:00', 'urgent',
 146, '龙工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(13, 3, 'WO202508080047', 'ECIF00013', '宁波制造业集团', 'closed', '2025-08-11 14:00:00',
 'account_manager', 'service_attitude', '客户经理服务不够主动', '2025-08-08 10:00:00', 'normal',
 147, '左经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14: 温州建设工程公司 - 4条
(14, 1, 'WO202510110048', 'ECIF00014', '温州建设工程公司', 'completed', '2025-10-27 11:00:00',
 'account_manager', 'business_process', '工程款支付审批流程慢', '2025-10-11 09:30:00', 'important',
 148, '廉经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(14, 2, 'WO202509060049', 'ECIF00014', '温州建设工程公司', 'closed', '2025-09-09 16:00:00',
 'online_banking', 'fee_dispute', '网银转账手续费不合理', '2025-09-06 14:00:00', 'normal',
 149, '宫经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(14, 3, 'WO202508040050', 'ECIF00014', '温州建设工程公司', 'closed', '2025-08-07 15:00:00',
 'counter', 'waiting_time', '对公业务办理窗口等待时间长', '2025-08-04 10:30:00', 'normal',
 150, '单主任', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(14, 4, 'WO202507020051', 'ECIF00014', '温州建设工程公司', 'closed', '2025-07-05 14:00:00',
 'account_manager', 'product_issue', '项目贷款利率调整不及时', '2025-07-02 11:00:00', 'normal',
 151, '佟经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15: 绍兴纺织实业公司 - 3条
(15, 1, 'WO202510090052', 'ECIF00015', '绍兴纺织实业公司', 'pending', '2025-10-28 09:00:00',
 'account_manager', 'business_process', '出口退税业务办理时间长', '2025-10-09 14:20:00', 'normal',
 152, '仲经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(15, 2, 'WO202509030053', 'ECIF00015', '绍兴纺织实业公司', 'closed', '2025-09-06 15:30:00',
 'online_banking', 'system_failure', '企业网银导出流水功能异常', '2025-09-03 10:00:00', 'normal',
 153, '吉工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(15, 3, 'WO202508010054', 'ECIF00015', '绍兴纺织实业公司', 'closed', '2025-08-04 14:00:00',
 'counter', 'service_attitude', '对公柜台服务效率低', '2025-08-01 11:30:00', 'normal', 154,
 '阳主任', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16: 金华物流运输公司 - 3条
(16, 1, 'WO202510070055', 'ECIF00016', '金华物流运输公司', 'completed', '2025-10-26 14:00:00',
 'account_manager', 'product_issue', '流动资金贷款额度不足', '2025-10-07 10:00:00', 'important',
 155, '羿经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(16, 2, 'WO202509020056', 'ECIF00016', '金华物流运输公司', 'closed', '2025-09-05 11:00:00',
 'online_banking', 'fee_dispute', '账户管理费收取过高', '2025-09-02 14:30:00', 'normal', 156,
 '令经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(16, 3, 'WO202507280057', 'ECIF00016', '金华物流运输公司', 'closed', '2025-07-31 16:00:00',
 'call_center', 'service_attitude', '客服对业务不熟悉', '2025-07-28 09:00:00', 'normal', 157,
 '通主管', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17: 台州电子科技公司 - 3条
(17, 1, 'WO202510060058', 'ECIF00017', '台州电子科技公司', 'processing', '2025-10-28 10:00:00',
 'account_manager', 'business_process', '科技贷款审批材料要求多', '2025-10-06 11:20:00', 'normal',
 158, '古经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(17, 2, 'WO202508300059', 'ECIF00017', '台州电子科技公司', 'closed', '2025-09-02 14:00:00',
 'online_banking', 'system_failure', '网银数字证书更新失败', '2025-08-30 15:00:00', 'important',
 159, '易工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(17, 3, 'WO202507250060', 'ECIF00017', '台州电子科技公司', 'closed', '2025-07-28 11:00:00',
 'counter', 'waiting_time', '开立信用证等待审批时间长', '2025-07-25 10:00:00', 'normal', 160,
 '明主任', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18: 嘉兴食品加工厂 - 3条
(18, 1, 'WO202510040061', 'ECIF00018', '嘉兴食品加工厂', 'pending', '2025-10-28 08:00:00',
 'account_manager', 'product_issue', '流动资金贷款利率高', '2025-10-04 14:00:00', 'normal', 161,
 '盛经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(18, 2, 'WO202508280062', 'ECIF00018', '嘉兴食品加工厂', 'closed', '2025-08-31 15:00:00',
 'call_center', 'service_attitude', '客户经理更换频繁', '2025-08-28 11:00:00', 'normal', 162,
 '后经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(18, 3, 'WO202507220063', 'ECIF00018', '嘉兴食品加工厂', 'closed', '2025-07-25 14:00:00',
 'online_banking', 'business_process', '代发工资功能操作复杂', '2025-07-22 09:30:00', 'normal',
 163, '农经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19: 湖州化工材料公司 - 3条
(19, 1, 'WO202510030064', 'ECIF00019', '湖州化工材料公司', 'completed', '2025-10-25 11:00:00',
 'account_manager', 'business_process', '环保贷款申请流程不清晰', '2025-10-03 10:00:00', 'normal',
 164, '索经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(19, 2, 'WO202508250065', 'ECIF00019', '湖州化工材料公司', 'closed', '2025-08-28 16:00:00',
 'online_banking', 'fee_dispute', '国内信用证手续费过高', '2025-08-25 14:30:00', 'normal', 165,
 '葛经理', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(19, 3, 'WO202507200066', 'ECIF00019', '湖州化工材料公司', 'closed', '2025-07-23 15:00:00',
 'counter', 'waiting_time', '银行承兑汇票办理速度慢', '2025-07-20 11:00:00', 'normal', 166,
 '籍主任', b'1', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20: 衢州矿业开发公司 - 3条
(20, 1, 'WO202510010067', 'ECIF00020', '衢州矿业开发公司', 'processing', '2025-10-28 13:00:00',
 'account_manager', 'product_issue', '矿业贷款额度审批不及时', '2025-10-01 15:00:00', 'important',
 167, '牛经理', b'0', '1', NOW(), '1', NOW(), b'0', 1),

(20, 2, 'WO202508230068', 'ECIF00020', '衢州矿业开发公司', 'closed', '2025-08-26 14:00:00',
 'online_banking', 'system_failure', '大额支付系统经常维护', '2025-08-23 10:30:00', 'urgent', 168,
 '寿工程师', b'1', '1', NOW(), '1', NOW(), b'0', 1),

(20, 3, 'WO202507180069', 'ECIF00020', '衢州矿业开发公司', 'closed', '2025-07-21 11:00:00',
 'account_manager', 'service_attitude', '客户经理专业度不够', '2025-07-18 14:00:00', 'normal', 169,
 '通经理', b'1', '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 数据验证查询 ====================

-- 1. 统计每个客户的投诉数量
SELECT
  customer_id,
  customer_name,
  COUNT(*) as complaint_count,
  SUM(CASE WHEN work_order_status = 'closed' THEN 1 ELSE 0 END) as closed_count,
  SUM(CASE WHEN work_order_status = 'processing' THEN 1 ELSE 0 END) as processing_count,
  SUM(CASE WHEN work_order_status = 'pending' THEN 1 ELSE 0 END) as pending_count
FROM crm_customer_complaint
GROUP BY customer_id, customer_name
ORDER BY customer_id;

-- 2. 统计各投诉渠道的数量
SELECT
  complaint_channel,
  COUNT(*) as count
FROM crm_customer_complaint
GROUP BY complaint_channel
ORDER BY count DESC;

-- 3. 统计各投诉类型的数量
SELECT
  complaint_type,
  COUNT(*) as count
FROM crm_customer_complaint
GROUP BY complaint_type
ORDER BY count DESC;

-- 4. 统计各工单状态的数量
SELECT
  work_order_status,
  COUNT(*) as count
FROM crm_customer_complaint
GROUP BY work_order_status
ORDER BY count DESC;

-- 5. 统计投诉级别分布
SELECT
  complaint_level,
  COUNT(*) as count
FROM crm_customer_complaint
GROUP BY complaint_level
ORDER BY
  CASE complaint_level
    WHEN 'urgent' THEN 1
    WHEN 'important' THEN 2
    WHEN 'normal' THEN 3
  END;

-- 6. 查询最近的投诉记录
SELECT
  customer_id,
  customer_name,
  work_order_no,
  complaint_type,
  complaint_content,
  complaint_time,
  work_order_status
FROM crm_customer_complaint
ORDER BY complaint_time DESC
LIMIT 20;

-- 7. 统计客户满意度
SELECT
  customer_satisfaction,
  COUNT(*) as count
FROM crm_customer_complaint
WHERE customer_satisfaction IS NOT NULL
GROUP BY customer_satisfaction
ORDER BY customer_satisfaction DESC;
