-- ----------------------------
-- 客户提醒信息表测试数据
-- 为所有20个客户（10个零售 + 10个对公）生成3-5条提醒记录
-- 涵盖各种提醒类型、提醒级别、提醒状态
-- ----------------------------

-- 清空表数据
TRUNCATE TABLE `crm_customer_reminder`;

-- ==================== 零售客户提醒信息 ====================

-- 客户1: 张三 (customer_id=1) - 5条提醒
INSERT INTO `crm_customer_reminder` (
  `customer_id`, `sequence_no`, `reminder_category_name`, `reminder_generate_date`, `reminder_due_date`,
  `reminder_content`, `reminder_no`, `reminder_type`, `reminder_level`, `reminder_status`,
  `reminder_source`, `is_sent`, `send_time`, `send_channel`, `recipient_user_id`, `recipient_user_name`,
  `recipient_dept_id`, `recipient_dept_name`, `handler_user_id`, `handler_user_name`,
  `handle_time`, `handle_result`, `is_read`, `read_time`, `priority`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, '账户余额变动（转出）', '2021-06-29', '2021-06-30',
 '贵莉的存款账户（账户：33108219894009）于2021年06月29日发生了变动，转出金额200000元。',
 'REM202106290001', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', '2021-06-29 10:00:00', 'sms', 201, '张三/高新分行', 301, '零售业务部',
 201, '张三/高新分行', '2021-06-29 15:00:00', '已与客户确认，为正常转账',
 b'1', '2021-06-29 10:05:00', 8, '1', NOW(), '1', NOW(), b'0', 1),

(1, 2, '授信到期提醒', '2022-07-31', '2022-08-02',
 '你支行某莉的个人经营性贷款于2021年02月16日到期。请通知客户如需办理续贷的，提前做好续贷手续。',
 'REM202207310001', 'credit_expire', 'urgent', 'completed', 'system_auto',
 b'1', '2022-07-31 09:00:00', 'sms', 202, '李四/高新分行', 302, '贷款部',
 202, '李四/高新分行', '2022-08-01 10:00:00', '客户已申请续贷，正在审批中',
 b'1', '2022-07-31 09:10:00', 10, '1', NOW(), '1', NOW(), b'0', 1),

(1, 3, '客户接触', '2022-08-23', '2022-08-24',
 '客户接触任务', 'REM202208230001', 'customer_contact', 'normal', 'completed', 'manual_create',
 b'1', '2022-08-23 14:00:00', 'system', 201, '张三/高新分行', 301, '零售业务部',
 201, '张三/高新分行', '2022-08-23 16:00:00', '已完成客户拜访，客户满意度较高',
 b'1', '2022-08-23 14:05:00', 5, '1', NOW(), '1', NOW(), b'0', 1),

(1, 4, '客户生日', '2022-01-13', '2022-01-13',
 '客户某莉将于2022年01月13日过生日。', 'REM202201130001', 'customer_birthday', 'info', 'completed', 'system_auto',
 b'1', '2022-01-13 08:00:00', 'sms', 201, '张三/高新分行', 301, '零售业务部',
 201, '张三/高新分行', '2022-01-13 10:00:00', '已发送生日祝福，客户表示感谢',
 b'1', '2022-01-13 08:05:00', 3, '1', NOW(), '1', NOW(), b'0', 1),

(1, 5, '理财到期', '2023-03-15', '2023-03-20',
 '客户张三购买的理财产品将于2023年03月20日到期，到期金额150000元。',
 'REM202303150001', 'wealth_expire', 'important', 'pending', 'system_auto',
 b'1', '2023-03-15 09:00:00', 'sms', 201, '张三/高新分行', 301, '零售业务部',
 NULL, NULL, NULL, NULL, b'1', '2023-03-15 09:05:00', 7, '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2-10的提醒数据（简化版，每人3-4条）
INSERT INTO `crm_customer_reminder` (
  `customer_id`, `sequence_no`, `reminder_category_name`, `reminder_generate_date`, `reminder_due_date`,
  `reminder_content`, `reminder_no`, `reminder_type`, `reminder_level`, `reminder_status`,
  `reminder_source`, `is_sent`, `send_channel`, `recipient_user_id`, `recipient_user_name`,
  `is_read`, `priority`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户2: 李四 - 4条
(2, 1, '贷款到期', '2023-01-10', '2023-01-15',
 '客户李四的个人消费贷款将于2023年01月15日到期，贷款金额300000元。',
 'REM202301100001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(2, 2, '客户生日', '2023-05-20', '2023-05-20',
 '客户李四将于2023年05月20日过生日。',
 'REM202305200001', 'customer_birthday', 'info', 'completed', 'system_auto',
 b'1', 'sms', 202, '李四/高新分行', b'1', 3, '1', NOW(), '1', NOW(), b'0', 1),
(2, 3, '客户接触', '2023-06-01', '2023-06-02',
 '客户接触任务',
 'REM202306010001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 201, '张三/高新分行', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(2, 4, '理财到期', '2023-07-10', '2023-07-15',
 '客户李四购买的理财产品将于2023年07月15日到期。',
 'REM202307100001', 'wealth_expire', 'important', 'pending', 'system_auto',
 b'1', 'sms', 202, '李四/高新分行', b'0', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户3: 王五 - 3条
(3, 1, '账户余额变动（转出）', '2023-02-10', '2023-02-11',
 '客户账户发生大额转出，金额500000元。',
 'REM202302100001', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'sms', 203, '王五/高新分行', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(3, 2, '授信到期提醒', '2023-04-20', '2023-04-25',
 '客户王五的授信额度将于2023年04月25日到期。',
 'REM202304200001', 'credit_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(3, 3, '客户生日', '2023-09-08', '2023-09-08',
 '客户王五将于2023年09月08日过生日。',
 'REM202309080001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 203, '王五/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户4: 赵六 - 5条
(4, 1, '还款提醒', '2023-01-05', '2023-01-10',
 '客户赵六的贷款将于2023年01月10日需要还款，金额50000元。',
 'REM202301050001', 'payment_remind', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 204, '赵六/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(4, 2, '产品到期', '2023-03-10', '2023-03-15',
 '客户赵六的定期存款将于2023年03月15日到期。',
 'REM202303100001', 'product_expire', 'important', 'completed', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),
(4, 3, '客户接触', '2023-05-15', '2023-05-16',
 '客户接触任务',
 'REM202305150001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 204, '赵六/高新分行', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(4, 4, '客户生日', '2023-11-22', '2023-11-22',
 '客户赵六将于2023年11月22日过生日。',
 'REM202311220001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 204, '赵六/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),
(4, 5, '重要事件', '2023-06-01', '2023-06-05',
 '客户赵六VIP等级即将到期，需及时跟进续约。',
 'REM202306010002', 'important_event', 'important', 'pending', 'rule_trigger',
 b'1', 'system', 201, '张三/高新分行', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户5: 孙七 - 4条
(5, 1, '贷款到期', '2023-02-20', '2023-02-25',
 '客户孙七的个人贷款将于2023年02月25日到期。',
 'REM202302200001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 205, '孙七/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(5, 2, '理财到期', '2023-04-10', '2023-04-15',
 '客户孙七购买的理财产品将于2023年04月15日到期。',
 'REM202304100001', 'wealth_expire', 'important', 'pending', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),
(5, 3, '客户生日', '2023-07-15', '2023-07-15',
 '客户孙七将于2023年07月15日过生日。',
 'REM202307150001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 205, '孙七/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),
(5, 4, '客户接触', '2023-08-01', '2023-08-02',
 '客户接触任务',
 'REM202308010001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 202, '李四/高新分行', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户6: 周八 - 3条
(6, 1, '账户余额变动（转出）', '2023-03-05', '2023-03-06',
 '客户账户发生大额转出，金额800000元。',
 'REM202303050001', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'sms', 206, '周八/高新分行', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(6, 2, '客户生日', '2023-10-10', '2023-10-10',
 '客户周八将于2023年10月10日过生日。',
 'REM202310100001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 206, '周八/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),
(6, 3, '产品到期', '2023-05-20', '2023-05-25',
 '客户周八的定期存款将于2023年05月25日到期。',
 'REM202305200002', 'product_expire', 'important', 'pending', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户7: 吴九 - 3条
(7, 1, '授信到期提醒', '2023-02-15', '2023-02-20',
 '客户吴九的授信额度将于2023年02月20日到期。',
 'REM202302150001', 'credit_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 207, '吴九/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(7, 2, '客户接触', '2023-06-10', '2023-06-11',
 '客户接触任务',
 'REM202306100001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 207, '吴九/高新分行', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(7, 3, '客户生日', '2023-12-05', '2023-12-05',
 '客户吴九将于2023年12月05日过生日。',
 'REM202312050001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 207, '吴九/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户8: 郑十 - 3条
(8, 1, '贷款到期', '2023-03-20', '2023-03-25',
 '客户郑十的个人贷款将于2023年03月25日到期。',
 'REM202303200001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 208, '郑十/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(8, 2, '客户生日', '2023-08-18', '2023-08-18',
 '客户郑十将于2023年08月18日过生日。',
 'REM202308180001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 208, '郑十/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),
(8, 3, '理财到期', '2023-05-10', '2023-05-15',
 '客户郑十购买的理财产品将于2023年05月15日到期。',
 'REM202305100001', 'wealth_expire', 'important', 'pending', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户9: 陈十一 - 3条
(9, 1, '账户余额变动（转出）', '2023-04-05', '2023-04-06',
 '客户账户发生大额转出，金额600000元。',
 'REM202304050001', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'sms', 209, '陈十一/高新分行', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(9, 2, '客户接触', '2023-07-01', '2023-07-02',
 '客户接触任务',
 'REM202307010001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 209, '陈十一/高新分行', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(9, 3, '客户生日', '2023-11-30', '2023-11-30',
 '客户陈十一将于2023年11月30日过生日。',
 'REM202311300001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 209, '陈十一/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户10: 黄十二 - 3条
(10, 1, '还款提醒', '2023-01-20', '2023-01-25',
 '客户黄十二的贷款将于2023年01月25日需要还款。',
 'REM202301200001', 'payment_remind', 'urgent', 'completed', 'system_auto',
 b'1', 'sms', 210, '黄十二/高新分行', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(10, 2, '客户生日', '2023-06-25', '2023-06-25',
 '客户黄十二将于2023年06月25日过生日。',
 'REM202306250001', 'customer_birthday', 'info', 'pending', 'system_auto',
 b'0', 'sms', 210, '黄十二/高新分行', b'0', 3, '1', NOW(), '1', NOW(), b'0', 1),
(10, 3, '产品到期', '2023-08-10', '2023-08-15',
 '客户黄十二的定期存款将于2023年08月15日到期。',
 'REM202308100001', 'product_expire', 'important', 'pending', 'system_auto',
 b'1', 'sms', 201, '张三/高新分行', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 对公客户提醒信息 ====================

-- 客户11-20的提醒数据（对公客户，各3-4条）
INSERT INTO `crm_customer_reminder` (
  `customer_id`, `sequence_no`, `reminder_category_name`, `reminder_generate_date`, `reminder_due_date`,
  `reminder_content`, `reminder_no`, `reminder_type`, `reminder_level`, `reminder_status`,
  `reminder_source`, `is_sent`, `send_channel`, `recipient_user_id`, `recipient_user_name`,
  `is_read`, `priority`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 客户11: 杭州科技有限公司 - 4条
(11, 1, '授信到期提醒', '2023-02-10', '2023-02-15',
 '企业授信额度将于2023年02月15日到期，授信金额5000000元。',
 'REM202302100002', 'credit_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 211, '卫经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(11, 2, '贷款到期', '2023-04-10', '2023-04-15',
 '企业流动资金贷款将于2023年04月15日到期。',
 'REM202304100002', 'loan_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 212, '雷工程师', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(11, 3, '客户接触', '2023-06-05', '2023-06-06',
 '企业客户接触任务',
 'REM202306050001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 211, '卫经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(11, 4, '重要事件', '2023-07-01', '2023-07-05',
 '企业营业执照即将到期，需提醒更新。',
 'REM202307010002', 'important_event', 'important', 'pending', 'rule_trigger',
 b'1', 'email', 213, '项经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户12: 浙江贸易股份有限公司 - 4条
(12, 1, '账户余额变动（转出）', '2023-03-10', '2023-03-11',
 '企业账户发生大额转出，金额10000000元。',
 'REM202303100002', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'email', 214, '印经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(12, 2, '授信到期提醒', '2023-05-15', '2023-05-20',
 '企业授信额度将于2023年05月20日到期。',
 'REM202305150002', 'credit_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 215, '齐经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(12, 3, '客户接触', '2023-07-10', '2023-07-11',
 '企业客户接触任务',
 'REM202307100002', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 214, '印经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(12, 4, '还款提醒', '2023-08-05', '2023-08-10',
 '企业贷款将于2023年08月10日需要还款。',
 'REM202308050001', 'payment_remind', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 216, '佟经理', b'0', 10, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户13: 宁波制造业集团 - 3条
(13, 1, '贷款到期', '2023-03-25', '2023-03-30',
 '企业流动资金贷款将于2023年03月30日到期。',
 'REM202303250001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 217, '包经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(13, 2, '客户接触', '2023-06-15', '2023-06-16',
 '企业客户接触任务',
 'REM202306150001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 218, '储经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(13, 3, '产品到期', '2023-08-20', '2023-08-25',
 '企业定期存款将于2023年08月25日到期。',
 'REM202308200001', 'product_expire', 'important', 'pending', 'system_auto',
 b'1', 'email', 217, '包经理', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户14: 温州建设工程公司 - 4条
(14, 1, '授信到期提醒', '2023-02-25', '2023-03-01',
 '企业授信额度将于2023年03月01日到期。',
 'REM202302250001', 'credit_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 219, '顾经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(14, 2, '贷款到期', '2023-05-10', '2023-05-15',
 '企业项目贷款将于2023年05月15日到期。',
 'REM202305100002', 'loan_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 220, '熊经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(14, 3, '客户接触', '2023-07-20', '2023-07-21',
 '企业客户接触任务',
 'REM202307200001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 219, '顾经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(14, 4, '重要事件', '2023-09-01', '2023-09-05',
 '企业年审即将到期，需提醒及时办理。',
 'REM202309010001', 'important_event', 'important', 'pending', 'rule_trigger',
 b'1', 'email', 221, '谈经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户15: 绍兴纺织实业公司 - 3条
(15, 1, '账户余额变动（转出）', '2023-04-10', '2023-04-11',
 '企业账户发生大额转出，金额8000000元。',
 'REM202304100003', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'email', 222, '边经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(15, 2, '授信到期提醒', '2023-06-20', '2023-06-25',
 '企业授信额度将于2023年06月25日到期。',
 'REM202306200001', 'credit_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 223, '殷经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(15, 3, '客户接触', '2023-08-15', '2023-08-16',
 '企业客户接触任务',
 'REM202308150001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 222, '边经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户16: 金华物流运输公司 - 3条
(16, 1, '贷款到期', '2023-03-15', '2023-03-20',
 '企业流动资金贷款将于2023年03月20日到期。',
 'REM202303150002', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 224, '华经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(16, 2, '客户接触', '2023-07-05', '2023-07-06',
 '企业客户接触任务',
 'REM202307050001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 225, '彭经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(16, 3, '产品到期', '2023-09-10', '2023-09-15',
 '企业定期存款将于2023年09月15日到期。',
 'REM202309100001', 'product_expire', 'important', 'pending', 'system_auto',
 b'1', 'email', 224, '华经理', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户17: 台州电子科技公司 - 3条
(17, 1, '授信到期提醒', '2023-04-20', '2023-04-25',
 '企业授信额度将于2023年04月25日到期。',
 'REM202304200002', 'credit_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 226, '岳经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(17, 2, '客户接触', '2023-08-01', '2023-08-02',
 '企业客户接触任务',
 'REM202308010002', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 227, '关经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(17, 3, '还款提醒', '2023-09-20', '2023-09-25',
 '企业贷款将于2023年09月25日需要还款。',
 'REM202309200001', 'payment_remind', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 226, '岳经理', b'0', 10, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户18: 嘉兴食品加工厂 - 3条
(18, 1, '贷款到期', '2023-05-05', '2023-05-10',
 '企业流动资金贷款将于2023年05月10日到期。',
 'REM202305050001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 228, '蔡经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(18, 2, '客户接触', '2023-07-25', '2023-07-26',
 '企业客户接触任务',
 'REM202307250001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 229, '武经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(18, 3, '产品到期', '2023-10-05', '2023-10-10',
 '企业定期存款将于2023年10月10日到期。',
 'REM202310050001', 'product_expire', 'important', 'pending', 'system_auto',
 b'1', 'email', 228, '蔡经理', b'1', 7, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户19: 湖州化工材料公司 - 3条
(19, 1, '账户余额变动（转出）', '2023-05-15', '2023-05-16',
 '企业账户发生大额转出，金额12000000元。',
 'REM202305150003', 'account_balance', 'important', 'completed', 'system_auto',
 b'1', 'email', 230, '沈经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1),
(19, 2, '授信到期提醒', '2023-08-10', '2023-08-15',
 '企业授信额度将于2023年08月15日到期。',
 'REM202308100002', 'credit_expire', 'urgent', 'pending', 'system_auto',
 b'1', 'email', 231, '樊经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(19, 3, '客户接触', '2023-10-01', '2023-10-02',
 '企业客户接触任务',
 'REM202310010001', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 230, '沈经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),

-- 客户20: 衢州矿业开发公司 - 3条
(20, 1, '贷款到期', '2023-04-15', '2023-04-20',
 '企业流动资金贷款将于2023年04月20日到期。',
 'REM202304150001', 'loan_expire', 'urgent', 'completed', 'system_auto',
 b'1', 'email', 232, '於经理', b'1', 10, '1', NOW(), '1', NOW(), b'0', 1),
(20, 2, '客户接触', '2023-07-15', '2023-07-16',
 '企业客户接触任务',
 'REM202307150002', 'customer_contact', 'normal', 'pending', 'manual_create',
 b'1', 'system', 233, '申经理', b'1', 5, '1', NOW(), '1', NOW(), b'0', 1),
(20, 3, '重要事件', '2023-10-10', '2023-10-15',
 '企业环保证书即将到期，需提醒更新。',
 'REM202310100002', 'important_event', 'important', 'pending', 'rule_trigger',
 b'1', 'email', 232, '於经理', b'1', 8, '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 验证查询 ====================

-- 1. 统计每个客户的提醒数量
SELECT
  cr.customer_id,
  cc.customer_name,
  COUNT(*) AS reminder_count,
  SUM(CASE WHEN cr.reminder_status = 'completed' THEN 1 ELSE 0 END) AS completed_count,
  SUM(CASE WHEN cr.reminder_status = 'pending' THEN 1 ELSE 0 END) AS pending_count,
  SUM(CASE WHEN cr.is_sent = b'1' THEN 1 ELSE 0 END) AS sent_count,
  SUM(CASE WHEN cr.is_read = b'1' THEN 1 ELSE 0 END) AS read_count
FROM crm_customer_reminder cr
LEFT JOIN crm_customer cc ON cr.customer_id = cc.id
GROUP BY cr.customer_id, cc.customer_name
ORDER BY cr.customer_id;

-- 2. 按提醒类型统计
SELECT
  reminder_type,
  COUNT(*) AS count
FROM crm_customer_reminder
GROUP BY reminder_type
ORDER BY count DESC;

-- 3. 按提醒级别统计
SELECT
  reminder_level,
  COUNT(*) AS count
FROM crm_customer_reminder
GROUP BY reminder_level
ORDER BY count DESC;

-- 4. 按提醒状态统计
SELECT
  reminder_status,
  COUNT(*) AS count
FROM crm_customer_reminder
GROUP BY reminder_status
ORDER BY count DESC;

-- 5. 按提醒来源统计
SELECT
  reminder_source,
  COUNT(*) AS count
FROM crm_customer_reminder
GROUP BY reminder_source
ORDER BY count DESC;

-- 6. 按发送渠道统计
SELECT
  send_channel,
  COUNT(*) AS count
FROM crm_customer_reminder
WHERE send_channel IS NOT NULL
GROUP BY send_channel
ORDER BY count DESC;

-- 7. 最近的提醒列表（前20条）
SELECT
  cr.reminder_no,
  cc.customer_name,
  cr.reminder_category_name,
  cr.reminder_type,
  cr.reminder_level,
  cr.reminder_status,
  cr.reminder_generate_date,
  cr.reminder_due_date,
  cr.recipient_user_name
FROM crm_customer_reminder cr
LEFT JOIN crm_customer cc ON cr.customer_id = cc.id
ORDER BY cr.reminder_generate_date DESC
LIMIT 20;
