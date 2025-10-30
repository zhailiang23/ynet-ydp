-- ----------------------------
-- 客户接触信息表测试数据
-- 为所有20个客户（10个零售 + 10个对公）生成3-5条接触记录
-- 涵盖各种接触类型、接触状态、接触结果
-- ----------------------------

-- 清空表数据
TRUNCATE TABLE `crm_customer_contact`;

-- ==================== 零售客户接触信息 ====================

-- 客户1: 张三 (customer_id=1) - 4条接触
INSERT INTO `crm_customer_contact` (
  `customer_id`, `evaluation_time`, `contact_type`, `account_manager`, `contact_purpose`,
  `contact_feedback`, `contact_no`, `contact_status`, `contact_channel`, `contact_result`,
  `customer_intention`, `contact_duration`, `contact_location`, `contact_subject`,
  `contact_summary`, `vst_tm`, `vst_adr`, `vst_rslt_dsc`, `is_need_followup`,
  `followup_user_id`, `followup_user_name`, `followup_status`, `related_activity_name`,
  `related_product`, `customer_satisfaction`, `handler_user_id`, `handler_user_name`,
  `handler_dept_id`, `handler_dept_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, '2022-08-18 10:00:00', 'customer_callback', '系统管理员', '演示616 任务',
 '活动名称：演示616 任务\n名称：演示616-胡荣 反\n馈结果：初步接触，继续跟进 备注：123',
 'CON202208180001', 'completed', 'passive', 'success', 'high', 60,
 '杭州市西湖区银行VIP厅', '演示616 任务', '客户对产品感兴趣，初步接触，需要继续跟进',
 '2022-08-18 10:00:00', '杭州市西湖区银行VIP厅', '初步接触，继续跟进', b'1',
 201, '张三/高新分行', 'following', '演示616 任务', '理财产品', 5,
 201, '张三/高新分行', 301, '零售业务部',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, '2022-08-12 14:00:00', 'phone', '系统管理员', '演示616 任务',
 '活动名称：演示616 任务\n名称：演示616-胡荣 反\n馈结果：初步接触，继续跟进 备注：123',
 'CON202208120001', 'completed', 'active', 'partial', 'medium', 30,
 '电话沟通', '演示616 任务', '电话沟通产品详情，客户表示需要考虑',
 '2022-08-12 14:00:00', '电话沟通', '初步接触，继续跟进', b'1',
 201, '张三/高新分行', 'pending', '演示616 任务', '理财产品', 4,
 201, '张三/高新分行', 301, '零售业务部',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, '2022-08-11 15:00:00', 'phone', '系统管理员', '演示616 任务',
 '活动名称：演示616 任务\n名称：演示616-胡荣 反\n馈结果：初步接触，继续跟进 备注：123',
 'CON202208110001', 'completed', 'active', 'success', 'high', 45,
 '电话沟通', '演示616 任务', '电话跟进，客户已决定购买',
 '2022-08-11 15:00:00', '电话沟通', '初步接触，继续跟进', b'0',
 NULL, NULL, 'completed', '演示616 任务', '理财产品', 5,
 201, '张三/高新分行', 301, '零售业务部',
 '1', NOW(), '1', NOW(), b'0', 1),

(1, '2022-08-11 09:00:00', 'customer_callback', '系统管理员', '演示616 任务',
 '活动名称：演示616 任务\n名称：演示616-胡荣 反\n馈结果：初步接触，继续跟进 备注：123',
 'CON202208110002', 'completed', 'passive', 'success', 'high', 50,
 '杭州市西湖区银行VIP厅', '演示616 任务', '客户主动咨询，达成合作意向',
 '2022-08-11 09:00:00', '杭州市西湖区银行VIP厅', '初步接触，继续跟进', b'1',
 202, '李四/高新分行', 'following', '演示616 任务', '理财产品', 5,
 201, '张三/高新分行', 301, '零售业务部',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2-10的接触数据（简化版，每人3-4条）
INSERT INTO `crm_customer_contact` (
  `customer_id`, `evaluation_time`, `contact_type`, `account_manager`, `contact_purpose`,
  `contact_feedback`, `contact_no`, `contact_status`, `contact_channel`, `contact_result`,
  `customer_intention`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户2: 李四 - 4条
(2, '2022-09-01 10:00:00', 'visit', '系统管理员', '产品推介拜访',
 '上门拜访客户，介绍新产品，客户表示感兴趣', 'CON202209010001', 'completed', 'active', 'success', 'high',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(2, '2022-08-20 14:30:00', 'phone', '系统管理员', '贷款咨询',
 '电话沟通贷款产品，客户需要考虑', 'CON202208200001', 'completed', 'active', 'partial', 'medium',
 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(2, '2022-07-15 11:00:00', 'wechat', '系统管理员', '日常维护',
 '微信沟通，关心客户近况', 'CON202207150001', 'completed', 'active', 'success', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(2, '2022-06-10 16:00:00', 'customer_callback', '系统管理员', '投诉处理',
 '客户投诉处理，问题已解决', 'CON202206100001', 'completed', 'passive', 'success', 'low',
 203, '王五/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户3: 王五 - 3条
(3, '2022-09-05 09:00:00', 'visit', '系统管理员', '贷款续签',
 '上门拜访，讨论贷款续签事宜', 'CON202209050001', 'completed', 'active', 'success', 'high',
 203, '王五/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(3, '2022-08-15 10:30:00', 'phone', '系统管理员', '业务咨询',
 '电话沟通业务办理流程', 'CON202208150001', 'completed', 'passive', 'success', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(3, '2022-07-20 14:00:00', 'meeting', '系统管理员', '产品说明会',
 '参加产品说明会，客户表示满意', 'CON202207200001', 'completed', 'active', 'success', 'high',
 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户4: 赵六 - 5条
(4, '2022-09-10 11:00:00', 'customer_callback', '系统管理员', '理财咨询',
 '客户主动咨询理财产品', 'CON202209100001', 'completed', 'passive', 'success', 'high',
 204, '赵六/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, '2022-08-25 15:00:00', 'phone', '系统管理员', '回访',
 '电话回访，了解客户满意度', 'CON202208250001', 'completed', 'active', 'success', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, '2022-08-05 10:00:00', 'visit', '系统管理员', 'VIP客户维护',
 '上门拜访VIP客户，赠送礼品', 'CON202208050001', 'completed', 'active', 'success', 'high',
 204, '赵六/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, '2022-07-10 14:30:00', 'wechat', '系统管理员', '节日祝福',
 '微信发送节日祝福', 'CON202207100001', 'completed', 'active', 'success', 'medium',
 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(4, '2022-06-15 16:00:00', 'email', '系统管理员', '产品推荐',
 '邮件发送新产品介绍', 'CON202206150001', 'completed', 'active', 'partial', 'low',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户5: 孙七 - 4条
(5, '2022-09-12 10:00:00', 'visit', '系统管理员', '贷款申请',
 '上门拜访，协助客户办理贷款', 'CON202209120001', 'completed', 'active', 'success', 'high',
 205, '孙七/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(5, '2022-08-18 14:00:00', 'phone', '系统管理员', '产品咨询',
 '电话沟通产品详情', 'CON202208180002', 'completed', 'passive', 'success', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(5, '2022-07-25 11:00:00', 'wechat', '系统管理员', '日常维护',
 '微信沟通，关心客户近况', 'CON202207250001', 'completed', 'active', 'success', 'medium',
 205, '孙七/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(5, '2022-06-20 15:00:00', 'meeting', '系统管理员', '理财沙龙',
 '邀请客户参加理财沙龙', 'CON202206200001', 'completed', 'active', 'success', 'high',
 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户6: 周八 - 3条
(6, '2022-09-15 09:30:00', 'phone', '系统管理员', '回访',
 '电话回访客户使用体验', 'CON202209150001', 'completed', 'active', 'success', 'medium',
 206, '周八/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(6, '2022-08-10 10:30:00', 'visit', '系统管理员', '账户开通',
 '上门协助客户开通新账户', 'CON202208100001', 'completed', 'active', 'success', 'high',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(6, '2022-07-05 14:00:00', 'customer_callback', '系统管理员', '业务咨询',
 '客户主动咨询业务办理', 'CON202207050001', 'completed', 'passive', 'success', 'high',
 206, '周八/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7: 吴九 - 3条
(7, '2022-09-18 10:00:00', 'visit', '系统管理员', '贷款续签',
 '上门拜访，办理贷款续签', 'CON202209180001', 'completed', 'active', 'success', 'high',
 207, '吴九/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(7, '2022-08-22 15:00:00', 'phone', '系统管理员', '产品推介',
 '电话介绍新产品', 'CON202208220001', 'completed', 'active', 'partial', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(7, '2022-07-12 11:00:00', 'wechat', '系统管理员', '日常维护',
 '微信沟通，维护客户关系', 'CON202207120001', 'completed', 'active', 'success', 'medium',
 207, '吴九/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8: 郑十 - 3条
(8, '2022-09-20 09:00:00', 'phone', '系统管理员', '回访',
 '电话回访客户满意度', 'CON202209200001', 'completed', 'active', 'success', 'high',
 208, '郑十/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(8, '2022-08-28 14:00:00', 'visit', '系统管理员', '理财咨询',
 '上门拜访，介绍理财产品', 'CON202208280001', 'completed', 'active', 'success', 'high',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(8, '2022-07-18 10:00:00', 'customer_callback', '系统管理员', '业务办理',
 '客户主动咨询业务办理', 'CON202207180001', 'completed', 'passive', 'success', 'medium',
 208, '郑十/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9: 陈十一 - 3条
(9, '2022-09-22 10:30:00', 'visit', '系统管理员', '信用卡办理',
 '上门协助办理信用卡', 'CON202209220001', 'completed', 'active', 'success', 'high',
 209, '陈十一/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(9, '2022-08-30 15:30:00', 'phone', '系统管理员', '产品咨询',
 '电话沟通产品详情', 'CON202208300001', 'completed', 'passive', 'success', 'medium',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(9, '2022-07-22 11:00:00', 'meeting', '系统管理员', '客户沙龙',
 '邀请客户参加沙龙活动', 'CON202207220001', 'completed', 'active', 'success', 'high',
 202, '李四/高新分行', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10: 黄十二 - 3条
(10, '2022-09-25 09:00:00', 'phone', '系统管理员', '回访',
 '电话回访贷款使用情况', 'CON202209250001', 'completed', 'active', 'success', 'medium',
 210, '黄十二/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(10, '2022-09-05 14:00:00', 'visit', '系统管理员', '贷款申请',
 '上门拜访，协助贷款申请', 'CON202209050002', 'completed', 'active', 'success', 'high',
 201, '张三/高新分行', '1', NOW(), '1', NOW(), b'0', 1),
(10, '2022-08-08 10:00:00', 'customer_callback', '系统管理员', '业务咨询',
 '客户主动咨询业务', 'CON202208080001', 'completed', 'passive', 'success', 'high',
 210, '黄十二/高新分行', '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 对公客户接触信息 ====================

-- 客户11-20的接触数据（对公客户，各3-4条）
INSERT INTO `crm_customer_contact` (
  `customer_id`, `evaluation_time`, `contact_type`, `account_manager`, `contact_purpose`,
  `contact_feedback`, `contact_no`, `contact_status`, `contact_channel`, `contact_result`,
  `customer_intention`, `handler_user_id`, `handler_user_name`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户11: 杭州科技有限公司 - 4条
(11, '2022-09-28 10:00:00', 'visit', '系统管理员', '企业贷款洽谈',
 '上门拜访，洽谈企业贷款事宜', 'CON202209280001', 'completed', 'active', 'success', 'high',
 211, '卫经理', '1', NOW(), '1', NOW(), b'0', 1),
(11, '2022-09-10 14:00:00', 'meeting', '系统管理员', '授信业务会议',
 '参加授信业务会议，讨论合作方案', 'CON202209100002', 'completed', 'active', 'success', 'high',
 212, '雷工程师', '1', NOW(), '1', NOW(), b'0', 1),
(11, '2022-08-15 11:00:00', 'phone', '系统管理员', '业务跟进',
 '电话跟进业务进展', 'CON202208150002', 'completed', 'active', 'success', 'medium',
 211, '卫经理', '1', NOW(), '1', NOW(), b'0', 1),
(11, '2022-07-20 15:00:00', 'email', '系统管理员', '产品介绍',
 '邮件发送企业金融产品介绍', 'CON202207200002', 'completed', 'active', 'partial', 'medium',
 213, '项经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12: 浙江贸易股份有限公司 - 4条
(12, '2022-09-30 09:30:00', 'visit', '系统管理员', '贸易融资洽谈',
 '上门拜访，洽谈贸易融资业务', 'CON202209300001', 'completed', 'active', 'success', 'high',
 214, '印经理', '1', NOW(), '1', NOW(), b'0', 1),
(12, '2022-09-15 14:30:00', 'meeting', '系统管理员', '合作洽谈会',
 '召开合作洽谈会议', 'CON202209150002', 'completed', 'active', 'success', 'high',
 215, '齐经理', '1', NOW(), '1', NOW(), b'0', 1),
(12, '2022-08-20 10:00:00', 'phone', '系统管理员', '业务咨询',
 '电话沟通业务办理流程', 'CON202208200002', 'completed', 'passive', 'success', 'medium',
 214, '印经理', '1', NOW(), '1', NOW(), b'0', 1),
(12, '2022-07-25 16:00:00', 'customer_callback', '系统管理员', '授信申请',
 '企业主动咨询授信申请', 'CON202207250002', 'completed', 'passive', 'success', 'high',
 216, '佟经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13: 宁波制造业集团 - 3条
(13, '2022-10-05 10:00:00', 'visit', '系统管理员', '企业贷款续签',
 '上门拜访，办理贷款续签', 'CON202210050001', 'completed', 'active', 'success', 'high',
 217, '包经理', '1', NOW(), '1', NOW(), b'0', 1),
(13, '2022-09-12 14:00:00', 'meeting', '系统管理员', '业务洽谈',
 '参加业务洽谈会议', 'CON202209120002', 'completed', 'active', 'success', 'high',
 218, '储经理', '1', NOW(), '1', NOW(), b'0', 1),
(13, '2022-08-18 11:00:00', 'phone', '系统管理员', '回访',
 '电话回访企业满意度', 'CON202208180003', 'completed', 'active', 'success', 'medium',
 217, '包经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14: 温州建设工程公司 - 4条
(14, '2022-10-08 09:00:00', 'visit', '系统管理员', '项目贷款洽谈',
 '上门拜访，洽谈项目贷款', 'CON202210080001', 'completed', 'active', 'success', 'high',
 219, '顾经理', '1', NOW(), '1', NOW(), b'0', 1),
(14, '2022-09-20 15:00:00', 'meeting', '系统管理员', '合作会议',
 '召开合作会议，讨论融资方案', 'CON202209200002', 'completed', 'active', 'success', 'high',
 220, '熊经理', '1', NOW(), '1', NOW(), b'0', 1),
(14, '2022-08-25 10:30:00', 'phone', '系统管理员', '业务跟进',
 '电话跟进业务办理进度', 'CON202208250002', 'completed', 'active', 'success', 'medium',
 219, '顾经理', '1', NOW(), '1', NOW(), b'0', 1),
(14, '2022-07-30 14:00:00', 'email', '系统管理员', '产品推荐',
 '邮件推荐适合的金融产品', 'CON202207300001', 'completed', 'active', 'partial', 'medium',
 221, '谈经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15: 绍兴纺织实业公司 - 3条
(15, '2022-10-10 10:00:00', 'visit', '系统管理员', '流动资金贷款',
 '上门拜访，洽谈流动资金贷款', 'CON202210100001', 'completed', 'active', 'success', 'high',
 222, '边经理', '1', NOW(), '1', NOW(), b'0', 1),
(15, '2022-09-25 14:00:00', 'phone', '系统管理员', '回访',
 '电话回访客户满意度', 'CON202209250002', 'completed', 'active', 'success', 'medium',
 223, '殷经理', '1', NOW(), '1', NOW(), b'0', 1),
(15, '2022-09-05 11:00:00', 'meeting', '系统管理员', '业务洽谈',
 '参加业务洽谈会', 'CON202209050003', 'completed', 'active', 'success', 'high',
 222, '边经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16: 金华物流运输公司 - 3条
(16, '2022-10-12 09:30:00', 'visit', '系统管理员', '企业贷款申请',
 '上门拜访，协助贷款申请', 'CON202210120001', 'completed', 'active', 'success', 'high',
 224, '华经理', '1', NOW(), '1', NOW(), b'0', 1),
(16, '2022-09-28 15:00:00', 'phone', '系统管理员', '业务咨询',
 '电话沟通业务详情', 'CON202209280002', 'completed', 'passive', 'success', 'medium',
 225, '彭经理', '1', NOW(), '1', NOW(), b'0', 1),
(16, '2022-09-08 10:00:00', 'customer_callback', '系统管理员', '授信查询',
 '企业主动查询授信情况', 'CON202209080001', 'completed', 'passive', 'success', 'high',
 224, '华经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17: 台州电子科技公司 - 3条
(17, '2022-10-15 10:00:00', 'visit', '系统管理员', '科技贷款洽谈',
 '上门拜访，洽谈科技贷款', 'CON202210150001', 'completed', 'active', 'success', 'high',
 226, '岳经理', '1', NOW(), '1', NOW(), b'0', 1),
(17, '2022-09-30 14:00:00', 'meeting', '系统管理员', '合作洽谈',
 '召开合作洽谈会议', 'CON202209300002', 'completed', 'active', 'success', 'high',
 227, '关经理', '1', NOW(), '1', NOW(), b'0', 1),
(17, '2022-09-10 11:00:00', 'phone', '系统管理员', '回访',
 '电话回访企业经营情况', 'CON202209100003', 'completed', 'active', 'success', 'medium',
 226, '岳经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18: 嘉兴食品加工厂 - 3条
(18, '2022-10-18 09:00:00', 'visit', '系统管理员', '流动资金贷款',
 '上门拜访，办理流动资金贷款', 'CON202210180001', 'completed', 'active', 'success', 'high',
 228, '蔡经理', '1', NOW(), '1', NOW(), b'0', 1),
(18, '2022-10-05 15:00:00', 'phone', '系统管理员', '业务跟进',
 '电话跟进业务办理进度', 'CON202210050002', 'completed', 'active', 'success', 'medium',
 229, '武经理', '1', NOW(), '1', NOW(), b'0', 1),
(18, '2022-09-15 10:00:00', 'customer_callback', '系统管理员', '业务咨询',
 '企业主动咨询业务', 'CON202209150003', 'completed', 'passive', 'success', 'high',
 228, '蔡经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19: 湖州化工材料公司 - 3条
(19, '2022-10-20 10:00:00', 'visit', '系统管理员', '环保贷款洽谈',
 '上门拜访，洽谈环保贷款', 'CON202210200001', 'completed', 'active', 'success', 'high',
 230, '沈经理', '1', NOW(), '1', NOW(), b'0', 1),
(19, '2022-10-08 14:00:00', 'meeting', '系统管理员', '业务洽谈',
 '参加业务洽谈会', 'CON202210080002', 'completed', 'active', 'success', 'high',
 231, '樊经理', '1', NOW(), '1', NOW(), b'0', 1),
(19, '2022-09-20 11:00:00', 'phone', '系统管理员', '回访',
 '电话回访客户满意度', 'CON202209200003', 'completed', 'active', 'success', 'medium',
 230, '沈经理', '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20: 衢州矿业开发公司 - 3条
(20, '2022-10-22 09:00:00', 'visit', '系统管理员', '项目贷款申请',
 '上门拜访，协助项目贷款申请', 'CON202210220001', 'completed', 'active', 'success', 'high',
 232, '於经理', '1', NOW(), '1', NOW(), b'0', 1),
(20, '2022-10-10 15:00:00', 'phone', '系统管理员', '业务咨询',
 '电话沟通业务详情', 'CON202210100002', 'completed', 'passive', 'success', 'medium',
 233, '申经理', '1', NOW(), '1', NOW(), b'0', 1),
(20, '2022-09-22 10:00:00', 'meeting', '系统管理员', '合作洽谈',
 '召开合作洽谈会议', 'CON202209220002', 'completed', 'active', 'success', 'high',
 232, '於经理', '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 验证查询 ====================

-- 1. 统计每个客户的接触数量
SELECT
  cc.customer_id,
  c.customer_name,
  COUNT(*) AS contact_count,
  SUM(CASE WHEN cc.contact_status = 'completed' THEN 1 ELSE 0 END) AS completed_count,
  SUM(CASE WHEN cc.contact_result = 'success' THEN 1 ELSE 0 END) AS success_count
FROM crm_customer_contact cc
LEFT JOIN crm_customer c ON cc.customer_id = c.id
GROUP BY cc.customer_id, c.customer_name
ORDER BY cc.customer_id;

-- 2. 按接触类型统计
SELECT
  contact_type,
  COUNT(*) AS count
FROM crm_customer_contact
GROUP BY contact_type
ORDER BY count DESC;

-- 3. 按接触状态统计
SELECT
  contact_status,
  COUNT(*) AS count
FROM crm_customer_contact
GROUP BY contact_status
ORDER BY count DESC;

-- 4. 按接触渠道统计
SELECT
  contact_channel,
  COUNT(*) AS count
FROM crm_customer_contact
GROUP BY contact_channel
ORDER BY count DESC;

-- 5. 按接触结果统计
SELECT
  contact_result,
  COUNT(*) AS count
FROM crm_customer_contact
GROUP BY contact_result
ORDER BY count DESC;

-- 6. 按客户意向统计
SELECT
  customer_intention,
  COUNT(*) AS count
FROM crm_customer_contact
GROUP BY customer_intention
ORDER BY count DESC;

-- 7. 最近的接触记录（前20条）
SELECT
  cc.contact_no,
  c.customer_name,
  cc.contact_type,
  cc.account_manager,
  cc.contact_purpose,
  cc.contact_status,
  cc.contact_result,
  cc.customer_intention,
  cc.evaluation_time
FROM crm_customer_contact cc
LEFT JOIN crm_customer c ON cc.customer_id = c.id
ORDER BY cc.evaluation_time DESC
LIMIT 20;
