-- ----------------------------
-- 客户渠道行为信息表
-- 设计原则:
-- 1. 记录客户在各个渠道的行为轨迹和操作日志
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：操作时间、操作行为、操作对象、当前页面标识、当前页面名称、当前页面名称、页面停留时间(S)、说明
-- 4. 支持多渠道行为追踪（手机银行、网银、微信、CRM系统等）
-- 5. 用于客户行为分析和画像构建
-- ----------------------------

-- ==================== 客户渠道行为信息表 ====================
DROP TABLE IF EXISTS `crm_customer_channel_behavior`;
CREATE TABLE `crm_customer_channel_behavior` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '行为主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `operation_time` datetime NOT NULL COMMENT '操作时间',
  `operation_action` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
  `operation_object` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
  `current_page_identifier` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
  `current_page_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '当前页面名称（页面code，如：index、loan、my_page、act_details）',
  `current_page_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
  `page_stay_seconds` int NOT NULL DEFAULT 0 COMMENT '页面停留时间(S)',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '说明',

  -- ==================== 扩展字段 ====================
  `behavior_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '行为编号',
  `channel_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
  `device_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
  `device_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备ID',
  `device_model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设备型号',
  `app_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'APP版本号',
  `os_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作系统版本',
  `browser_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器类型',
  `browser_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器版本',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `ip_location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'IP归属地',
  `network_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
  `session_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会话ID',
  `session_start_time` datetime NULL DEFAULT NULL COMMENT '会话开始时间',
  `session_end_time` datetime NULL DEFAULT NULL COMMENT '会话结束时间',
  `previous_page_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上一页面标识',
  `previous_page_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上一页面名称',
  `next_page_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下一页面标识',
  `next_page_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下一页面名称',
  `page_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面URL',
  `page_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面标题',
  `page_params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '页面参数（JSON格式）',
  `operation_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
  `operation_detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作详情',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
  `business_module` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务模块',
  `business_function` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务功能',
  `is_conversion` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否转化（是否产生业务）',
  `conversion_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '转化类型（字典: aicrm_conversion_type）',
  `conversion_value` decimal(24,6) NULL DEFAULT NULL COMMENT '转化价值',
  `refer_source` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '来源（外部链接、推广活动等）',
  `refer_medium` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '媒介',
  `refer_campaign` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推广活动',
  `user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'User Agent',
  `screen_width` int NULL DEFAULT NULL COMMENT '屏幕宽度',
  `screen_height` int NULL DEFAULT NULL COMMENT '屏幕高度',
  `viewport_width` int NULL DEFAULT NULL COMMENT '视口宽度',
  `viewport_height` int NULL DEFAULT NULL COMMENT '视口高度',
  `is_new_visitor` bit(1) NULL DEFAULT NULL COMMENT '是否新访客',
  `visit_count` int NOT NULL DEFAULT 0 COMMENT '访问次数',
  `bounce_rate` decimal(10,2) NULL DEFAULT NULL COMMENT '跳出率（%）',
  `event_category` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '事件分类',
  `event_label` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '事件标签',
  `event_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '事件值',

  -- ==================== 备注信息 ====================
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_behavior_no`(`behavior_no` ASC) USING BTREE COMMENT '行为编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_operation_time`(`operation_time` ASC) USING BTREE COMMENT '操作时间索引',
  INDEX `idx_operation_action`(`operation_action` ASC) USING BTREE COMMENT '操作行为索引',
  INDEX `idx_channel_type`(`channel_type` ASC) USING BTREE COMMENT '渠道类型索引',
  INDEX `idx_current_page_code`(`current_page_code` ASC) USING BTREE COMMENT '当前页面标识索引',
  INDEX `idx_session_id`(`session_id` ASC) USING BTREE COMMENT '会话ID索引',
  INDEX `idx_business_type`(`business_type` ASC) USING BTREE COMMENT '业务类型索引',
  INDEX `idx_is_conversion`(`is_conversion` ASC) USING BTREE COMMENT '是否转化索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户渠道行为信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 操作行为字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM操作行为', 'aicrm_operation_action', 0, '客户的操作行为类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM操作行为';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '点击', 'click', 'aicrm_operation_action', 0, 'primary', '点击操作', '1', NOW(), '1', NOW(), b'0'),
(2, '登录', 'login', 'aicrm_operation_action', 0, 'success', '登录系统', '1', NOW(), '1', NOW(), b'0'),
(3, '浏览', 'browse', 'aicrm_operation_action', 0, 'info', '浏览页面', '1', NOW(), '1', NOW(), b'0'),
(4, '查询', 'query', 'aicrm_operation_action', 0, 'warning', '查询信息', '1', NOW(), '1', NOW(), b'0'),
(5, '提交', 'submit', 'aicrm_operation_action', 0, 'danger', '提交表单', '1', NOW(), '1', NOW(), b'0'),
(6, '下载', 'download', 'aicrm_operation_action', 0, 'default', '下载文件', '1', NOW(), '1', NOW(), b'0'),
(7, '上传', 'upload', 'aicrm_operation_action', 0, 'primary', '上传文件', '1', NOW(), '1', NOW(), b'0'),
(8, '搜索', 'search', 'aicrm_operation_action', 0, 'info', '搜索内容', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 操作对象字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM操作对象', 'aicrm_operation_object', 0, '操作的对象类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM操作对象';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '页面', 'page', 'aicrm_operation_object', 0, 'primary', '页面', '1', NOW(), '1', NOW(), b'0'),
(2, '按钮', 'button', 'aicrm_operation_object', 0, 'success', '按钮', '1', NOW(), '1', NOW(), b'0'),
(3, '输入框', 'input', 'aicrm_operation_object', 0, 'info', '输入框', '1', NOW(), '1', NOW(), b'0'),
(4, '链接', 'link', 'aicrm_operation_object', 0, 'warning', '链接', '1', NOW(), '1', NOW(), b'0'),
(5, '菜单', 'menu', 'aicrm_operation_object', 0, 'danger', '菜单', '1', NOW(), '1', NOW(), b'0'),
(6, '表单', 'form', 'aicrm_operation_object', 0, 'default', '表单', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 渠道类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM渠道类型', 'aicrm_channel_type', 0, '客户使用的渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM渠道类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '手机银行', 'mobile_banking', 'aicrm_channel_type', 0, 'primary', '手机银行APP', '1', NOW(), '1', NOW(), b'0'),
(2, '网上银行', 'web_banking', 'aicrm_channel_type', 0, 'success', '网上银行', '1', NOW(), '1', NOW(), b'0'),
(3, '微信银行', 'wechat', 'aicrm_channel_type', 0, 'success', '微信银行', '1', NOW(), '1', NOW(), b'0'),
(4, 'CRM系统', 'crm', 'aicrm_channel_type', 0, 'info', 'CRM系统', '1', NOW(), '1', NOW(), b'0'),
(5, '柜台', 'counter', 'aicrm_channel_type', 0, 'warning', '线下柜台', '1', NOW(), '1', NOW(), b'0'),
(6, 'ATM', 'atm', 'aicrm_channel_type', 0, 'default', 'ATM机', '1', NOW(), '1', NOW(), b'0'),
(7, '电话银行', 'call_center', 'aicrm_channel_type', 0, 'danger', '电话银行', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 设备类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM设备类型', 'aicrm_device_type', 0, '客户使用的设备类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM设备类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, 'iOS', 'ios', 'aicrm_device_type', 0, 'primary', 'iOS设备', '1', NOW(), '1', NOW(), b'0'),
(2, 'Android', 'android', 'aicrm_device_type', 0, 'success', 'Android设备', '1', NOW(), '1', NOW(), b'0'),
(3, '网页', 'web', 'aicrm_device_type', 0, 'info', '网页浏览器', '1', NOW(), '1', NOW(), b'0'),
(4, 'H5', 'h5', 'aicrm_device_type', 0, 'warning', 'H5页面', '1', NOW(), '1', NOW(), b'0'),
(5, 'PC', 'pc', 'aicrm_device_type', 0, 'default', 'PC电脑', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 网络类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM网络类型', 'aicrm_network_type', 0, '网络连接类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM网络类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, 'WiFi', 'wifi', 'aicrm_network_type', 0, 'success', 'WiFi网络', '1', NOW(), '1', NOW(), b'0'),
(2, '4G', '4g', 'aicrm_network_type', 0, 'primary', '4G网络', '1', NOW(), '1', NOW(), b'0'),
(3, '5G', '5g', 'aicrm_network_type', 0, 'danger', '5G网络', '1', NOW(), '1', NOW(), b'0'),
(4, '有线网络', 'ethernet', 'aicrm_network_type', 0, 'info', '有线网络', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 操作结果字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM操作结果', 'aicrm_operation_result', 0, '操作的执行结果', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM操作结果';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '成功', 'success', 'aicrm_operation_result', 0, 'success', '操作成功', '1', NOW(), '1', NOW(), b'0'),
(2, '失败', 'failed', 'aicrm_operation_result', 0, 'danger', '操作失败', '1', NOW(), '1', NOW(), b'0'),
(3, '取消', 'cancelled', 'aicrm_operation_result', 0, 'warning', '操作取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 7. 行为业务类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM行为业务类型', 'aicrm_behavior_business_type', 0, '行为关联的业务类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM行为业务类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '账户', 'account', 'aicrm_behavior_business_type', 0, 'primary', '账户业务', '1', NOW(), '1', NOW(), b'0'),
(2, '贷款', 'loan', 'aicrm_behavior_business_type', 0, 'warning', '贷款业务', '1', NOW(), '1', NOW(), b'0'),
(3, '理财', 'wealth', 'aicrm_behavior_business_type', 0, 'success', '理财业务', '1', NOW(), '1', NOW(), b'0'),
(4, '转账', 'transfer', 'aicrm_behavior_business_type', 0, 'info', '转账业务', '1', NOW(), '1', NOW(), b'0'),
(5, '支付', 'payment', 'aicrm_behavior_business_type', 0, 'danger', '支付业务', '1', NOW(), '1', NOW(), b'0'),
(6, '积分', 'points', 'aicrm_behavior_business_type', 0, 'default', '积分业务', '1', NOW(), '1', NOW(), b'0'),
(7, '活动', 'activity', 'aicrm_behavior_business_type', 0, 'primary', '营销活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 8. 转化类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM转化类型', 'aicrm_conversion_type', 0, '行为转化的类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM转化类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '注册', 'register', 'aicrm_conversion_type', 0, 'success', '用户注册', '1', NOW(), '1', NOW(), b'0'),
(2, '开户', 'open_account', 'aicrm_conversion_type', 0, 'primary', '开户', '1', NOW(), '1', NOW(), b'0'),
(3, '申请贷款', 'apply_loan', 'aicrm_conversion_type', 0, 'warning', '申请贷款', '1', NOW(), '1', NOW(), b'0'),
(4, '购买理财', 'buy_wealth', 'aicrm_conversion_type', 0, 'success', '购买理财产品', '1', NOW(), '1', NOW(), b'0'),
(5, '兑换积分', 'redeem_points', 'aicrm_conversion_type', 0, 'info', '兑换积分', '1', NOW(), '1', NOW(), b'0'),
(6, '参加活动', 'join_activity', 'aicrm_conversion_type', 0, 'danger', '参加营销活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
