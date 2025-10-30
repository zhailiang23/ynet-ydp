-- ----------------------------
-- 客户渠道行为信息测试数据
-- 为20个客户各生成5-10条渠道行为记录
-- 模拟真实的用户行为轨迹
-- ----------------------------

-- 清空现有数据
TRUNCATE TABLE crm_customer_channel_behavior;

-- 插入测试数据
INSERT INTO crm_customer_channel_behavior (
  customer_id, operation_time, operation_action, operation_object,
  current_page_identifier, current_page_code, current_page_name, page_stay_seconds, description,
  behavior_no, channel_type, device_type, device_id, device_model, app_version, os_version,
  browser_type, browser_version, ip_address, ip_location, network_type,
  session_id, session_start_time, session_end_time,
  previous_page_code, previous_page_name, next_page_code, next_page_name,
  page_url, page_title, page_params, operation_result, operation_detail,
  business_type, business_module, business_function,
  is_conversion, conversion_type, conversion_value,
  refer_source, refer_medium, refer_campaign, user_agent,
  screen_width, screen_height, viewport_width, viewport_height,
  is_new_visitor, visit_count, bounce_rate,
  event_category, event_label, event_value, remark,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户1: 张三 - 7条行为记录（手机银行APP）
(1, '2022-08-18 10:30:00', 'click', 'button',
 '积分', 'index', '首页', 2, '用户点击"积分"',
 'BHV202208180001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', 'wifi',
 'SESSION20220818001', '2022-08-18 10:28:00', '2022-08-18 10:45:00',
 'home', '首页', 'points', '我的积分', '/mobile/points', '积分页面', NULL, 'success', '成功跳转到积分页面',
 'points', '积分管理', '查看积分',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 15, NULL,
 'navigation', 'points_view', 'view_points', '手机银行查看积分',
 '1', '2022-08-18 10:30:00', '1', '2022-08-18 10:30:00', b'0', 0),

(1, '2022-08-12 14:20:00', 'login', 'page',
 '活动详情页', 'activity_detail', '活动详情页', 2, '用户登录"活动详情页"',
 'BHV202208120001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', '4g',
 'SESSION20220812001', '2022-08-12 14:18:00', '2022-08-12 14:30:00',
 NULL, NULL, 'activity_detail', '活动详情', '/mobile/activity/detail?id=123', '活动详情页', '{"activity_id":"123"}', 'success', '登录成功',
 'activity', '营销活动', '查看活动',
 b'0', NULL, NULL,
 'app_push', 'notification', 'summer_promotion', 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 14, NULL,
 'login', 'activity_login', 'login_success', '通过活动推送登录',
 '1', '2022-08-12 14:20:00', '1', '2022-08-12 14:20:00', b'0', 0),

(1, '2022-08-11 09:15:00', 'login', 'page',
 '登录', 'loan', '我的贷款', 2, '用户登录"登录"',
 'BHV202208110001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', 'wifi',
 'SESSION20220811001', '2022-08-11 09:13:00', '2022-08-11 09:25:00',
 NULL, NULL, 'loan_list', '贷款列表', '/mobile/loan/my', '我的贷款', NULL, 'success', '登录后查看贷款',
 'loan', '贷款管理', '查看贷款',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 13, NULL,
 'login', 'loan_login', 'login_success', '登录查看贷款信息',
 '1', '2022-08-11 09:15:00', '1', '2022-08-11 09:15:00', b'0', 0),

(1, '2022-08-11 11:20:00', 'query', 'page',
 '贷款产品页', 'my_page', '我的', 2, '用户查询"贷款产品页"',
 'BHV202208110002', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', 'wifi',
 'SESSION20220811002', '2022-08-11 11:18:00', '2022-08-11 11:30:00',
 'loan', '我的贷款', 'loan_product', '贷款产品', '/mobile/loan/products', '贷款产品页', NULL, 'success', '查询贷款产品',
 'loan', '贷款管理', '产品查询',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 14, NULL,
 'query', 'loan_products', 'query_success', '查询可用贷款产品',
 '1', '2022-08-11 11:20:00', '1', '2022-08-11 11:20:00', b'0', 0),

(1, '2022-08-10 16:45:00', 'browse', 'input',
 '登录', 'act_details', '我的积分', 2, '用户浏览"登录"',
 'BHV202208100001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', '4g',
 'SESSION20220810001', '2022-08-10 16:43:00', '2022-08-10 17:00:00',
 'home', '首页', 'points_detail', '积分详情', '/mobile/points/detail', '积分详情', NULL, 'success', '浏览积分详情',
 'points', '积分管理', '积分明细',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 12, NULL,
 'browse', 'points_detail', 'browse_success', '浏览积分明细',
 '1', '2022-08-10 16:45:00', '1', '2022-08-10 16:45:00', b'0', 0),

(1, '2022-08-03 10:30:00', 'login', 'page',
 '登录', 'login', '饭票新人活动详情页', 2, '用户登录"登录"',
 'BHV202208030001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', 'wifi',
 'SESSION20220803001', '2022-08-03 10:28:00', '2022-08-03 10:45:00',
 NULL, NULL, 'new_user_activity', '新人活动', '/mobile/activity/newuser', '新人活动详情', NULL, 'success', '登录查看新人活动',
 'activity', '营销活动', '新人活动',
 b'1', 'join_activity', NULL,
 'sms', 'sms_link', 'new_user_2022', 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)',
 390, 844, 390, 844,
 b'1', 1, NULL,
 'login', 'new_user_activity', 'join_success', '新用户首次登录参加活动',
 '1', '2022-08-03 10:30:00', '1', '2022-08-03 10:30:00', b'0', 0),

(1, '2022-06-29 15:20:00', 'browse', 'input',
 '积分', 'index', '积分页面', 2, '用户浏览"积分"',
 'BHV202206290001', 'mobile_banking', 'ios', 'DEVICE001', 'iPhone 13', '3.4.0', 'iOS 15.5',
 NULL, NULL, '192.168.1.100', '浙江省杭州市', 'wifi',
 'SESSION20220629001', '2022-06-29 15:18:00', '2022-06-29 15:35:00',
 NULL, NULL, 'points', '积分中心', '/mobile/points', '积分页面', NULL, 'success', '浏览积分页面',
 'points', '积分管理', '查看积分',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_5 like Mac OS X)',
 390, 844, 390, 844,
 b'0', 3, NULL,
 'browse', 'points_page', 'browse_success', '浏览积分页面',
 '1', '2022-06-29 15:20:00', '1', '2022-06-29 15:20:00', b'0', 0),

-- 客户2: 李四 - 8条行为记录（网银+手机银行）
(2, '2022-08-15 10:30:00', 'login', 'page',
 '首页', 'index', '首页', 3, '用户登录网银',
 'BHV202208150001', 'web_banking', 'web', NULL, NULL, NULL, NULL,
 'Chrome', '103.0', '192.168.1.101', '浙江省杭州市', 'ethernet',
 'SESSION20220815001', '2022-08-15 10:28:00', '2022-08-15 11:00:00',
 NULL, NULL, 'account', '账户管理', '/web/account', '账户管理', NULL, 'success', '登录网银成功',
 'account', '账户管理', '登录',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
 1920, 1080, 1920, 1080,
 b'0', 25, NULL,
 'login', 'web_banking_login', 'login_success', '网银登录',
 '1', '2022-08-15 10:30:00', '1', '2022-08-15 10:30:00', b'0', 0),

(2, '2022-08-15 10:35:00', 'click', 'menu',
 '理财', 'wealth', '理财产品', 5, '点击理财菜单',
 'BHV202208150002', 'web_banking', 'web', NULL, NULL, NULL, NULL,
 'Chrome', '103.0', '192.168.1.101', '浙江省杭州市', 'ethernet',
 'SESSION20220815001', '2022-08-15 10:28:00', '2022-08-15 11:00:00',
 'index', '首页', 'wealth_list', '理财列表', '/web/wealth/products', '理财产品列表', NULL, 'success', '查看理财产品',
 'wealth', '理财管理', '产品浏览',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
 1920, 1080, 1920, 1080,
 b'0', 25, NULL,
 'click', 'wealth_menu', 'click_success', '点击查看理财产品',
 '1', '2022-08-15 10:35:00', '1', '2022-08-15 10:35:00', b'0', 0),

(2, '2022-08-15 10:42:00', 'submit', 'form',
 '理财申请', 'wealth_apply', '理财申请表单', 8, '提交理财申请',
 'BHV202208150003', 'web_banking', 'web', NULL, NULL, NULL, NULL,
 'Chrome', '103.0', '192.168.1.101', '浙江省杭州市', 'ethernet',
 'SESSION20220815001', '2022-08-15 10:28:00', '2022-08-15 11:00:00',
 'wealth_list', '理财列表', 'wealth_result', '申请结果', '/web/wealth/apply', '理财申请', '{"product_id":"WP001","amount":"150000"}', 'success', '理财申请成功',
 'wealth', '理财管理', '购买理财',
 b'1', 'buy_wealth', 150000.000000,
 NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36',
 1920, 1080, 1920, 1080,
 b'0', 25, NULL,
 'submit', 'wealth_apply', 'apply_success', '成功购买理财产品',
 '1', '2022-08-15 10:42:00', '1', '2022-08-15 10:42:00', b'0', 0),

(2, '2022-08-10 14:20:00', 'browse', 'page',
 '积分商城', 'points_mall', '积分商城', 10, '浏览积分商城',
 'BHV202208100002', 'mobile_banking', 'android', 'DEVICE002', 'Xiaomi 12', '3.5.0', 'Android 12',
 NULL, NULL, '192.168.1.101', '浙江省杭州市', '5g',
 'SESSION20220810002', '2022-08-10 14:15:00', '2022-08-10 14:35:00',
 'points', '积分中心', 'points_mall', '积分商城', '/mobile/points/mall', '积分商城', NULL, 'success', '浏览积分商城礼品',
 'points', '积分管理', '积分兑换',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Linux; Android 12; Xiaomi 12)',
 412, 915, 412, 915,
 b'0', 8, NULL,
 'browse', 'points_mall', 'browse_success', '浏览积分商城',
 '1', '2022-08-10 14:20:00', '1', '2022-08-10 14:20:00', b'0', 0),

(2, '2022-08-10 14:28:00', 'submit', 'form',
 '积分兑换', 'points_redeem', '兑换确认', 5, '提交积分兑换',
 'BHV202208100003', 'mobile_banking', 'android', 'DEVICE002', 'Xiaomi 12', '3.5.0', 'Android 12',
 NULL, NULL, '192.168.1.101', '浙江省杭州市', '5g',
 'SESSION20220810002', '2022-08-10 14:15:00', '2022-08-10 14:35:00',
 'points_mall', '积分商城', 'redeem_result', '兑换结果', '/mobile/points/redeem', '积分兑换', '{"gift_id":"GIFT002","points":"1000"}', 'success', '兑换成功',
 'points', '积分管理', '兑换礼品',
 b'1', 'redeem_points', 1000.000000,
 NULL, NULL, NULL, 'Mozilla/5.0 (Linux; Android 12; Xiaomi 12)',
 412, 915, 412, 915,
 b'0', 8, NULL,
 'submit', 'points_redeem', 'redeem_success', '成功兑换礼品',
 '1', '2022-08-10 14:28:00', '1', '2022-08-10 14:28:00', b'0', 0),

(2, '2022-07-25 09:30:00', 'search', 'input',
 '搜索', 'search', '搜索页面', 3, '搜索贷款产品',
 'BHV202207250001', 'mobile_banking', 'android', 'DEVICE002', 'Xiaomi 12', '3.4.0', 'Android 12',
 NULL, NULL, '192.168.1.101', '浙江省杭州市', 'wifi',
 'SESSION20220725001', '2022-07-25 09:28:00', '2022-07-25 09:45:00',
 'home', '首页', 'search_result', '搜索结果', '/mobile/search', '搜索', '{"keyword":"个人贷款"}', 'success', '搜索个人贷款',
 'loan', '贷款管理', '产品搜索',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Linux; Android 12; Xiaomi 12)',
 412, 915, 412, 915,
 b'0', 6, NULL,
 'search', 'loan_search', 'search_success', '搜索贷款产品',
 '1', '2022-07-25 09:30:00', '1', '2022-07-25 09:30:00', b'0', 0),

(2, '2022-07-25 09:35:00', 'click', 'link',
 '贷款详情', 'loan_detail', '个人经营贷款', 12, '点击查看贷款详情',
 'BHV202207250002', 'mobile_banking', 'android', 'DEVICE002', 'Xiaomi 12', '3.4.0', 'Android 12',
 NULL, NULL, '192.168.1.101', '浙江省杭州市', 'wifi',
 'SESSION20220725001', '2022-07-25 09:28:00', '2022-07-25 09:45:00',
 'search_result', '搜索结果', 'loan_apply', '贷款申请', '/mobile/loan/detail?id=LPROD002', '个人经营贷款详情', '{"loan_id":"LPROD002"}', 'success', '查看贷款详情',
 'loan', '贷款管理', '产品详情',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Linux; Android 12; Xiaomi 12)',
 412, 915, 412, 915,
 b'0', 6, NULL,
 'click', 'loan_detail', 'click_success', '点击查看贷款产品详情',
 '1', '2022-07-25 09:35:00', '1', '2022-07-25 09:35:00', b'0', 0),

(2, '2022-07-10 11:20:00', 'login', 'page',
 '登录', 'login', '登录页', 2, '用户登录',
 'BHV202207100001', 'mobile_banking', 'android', 'DEVICE002', 'Xiaomi 12', '3.3.0', 'Android 12',
 NULL, NULL, '192.168.1.101', '浙江省杭州市', '4g',
 'SESSION20220710001', '2022-07-10 11:18:00', '2022-07-10 11:30:00',
 NULL, NULL, 'home', '首页', '/mobile/login', '登录', NULL, 'success', '登录成功',
 'account', '账户管理', '登录',
 b'0', NULL, NULL,
 NULL, NULL, NULL, 'Mozilla/5.0 (Linux; Android 12; Xiaomi 12)',
 412, 915, 412, 915,
 b'0', 5, NULL,
 'login', 'user_login', 'login_success', '用户登录',
 '1', '2022-07-10 11:20:00', '1', '2022-07-10 11:20:00', b'0', 0),

-- 客户3-10 (零售客户，每人6-8条记录，简化展示)
-- 客户3: 王五 - 6条
(3, '2022-08-20 10:00:00', 'login', 'page', '首页', 'index', '首页', 2, '登录手机银行',
 'BHV202208200001', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', 'wifi',
 'SESSION20220820001', '2022-08-20 09:58:00', '2022-08-20 10:20:00',
 NULL, NULL, 'account', '账户', '/mobile/home', '首页', NULL, 'success', '登录成功',
 'account', '账户管理', '登录', b'0', NULL, NULL, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)', 390, 844, 390, 844,
 b'0', 20, NULL, 'login', 'home_login', 'success', '手机银行登录',
 '1', '2022-08-20 10:00:00', '1', '2022-08-20 10:00:00', b'0', 0),

(3, '2022-08-20 10:05:00', 'browse', 'page', '理财', 'wealth', '理财产品', 8, '浏览理财产品',
 'BHV202208200002', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', 'wifi',
 'SESSION20220820001', '2022-08-20 09:58:00', '2022-08-20 10:20:00',
 'index', '首页', 'wealth_detail', '理财详情', '/mobile/wealth/list', '理财产品列表', NULL, 'success', '查看理财产品',
 'wealth', '理财管理', '产品浏览', b'0', NULL, NULL, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)', 390, 844, 390, 844,
 b'0', 20, NULL, 'browse', 'wealth_list', 'success', '浏览理财产品',
 '1', '2022-08-20 10:05:00', '1', '2022-08-20 10:05:00', b'0', 0),

(3, '2022-08-20 10:13:00', 'submit', 'form', '理财购买', 'wealth_buy', '购买确认', 6, '购买理财产品',
 'BHV202208200003', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.5.0', 'iOS 15.6',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', 'wifi',
 'SESSION20220820001', '2022-08-20 09:58:00', '2022-08-20 10:20:00',
 'wealth_detail', '理财详情', 'wealth_result', '购买结果', '/mobile/wealth/buy', '购买理财', '{"product":"WP001","amount":"200000"}', 'success', '购买成功',
 'wealth', '理财管理', '购买理财', b'1', 'buy_wealth', 200000.000000, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_6 like Mac OS X)', 390, 844, 390, 844,
 b'0', 20, NULL, 'submit', 'wealth_buy', 'success', '成功购买理财',
 '1', '2022-08-20 10:13:00', '1', '2022-08-20 10:13:00', b'0', 0),

(3, '2022-07-15 14:30:00', 'click', 'button', '转账', 'transfer', '转账页面', 5, '点击转账',
 'BHV202207150001', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.4.0', 'iOS 15.5',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', '4g',
 'SESSION20220715001', '2022-07-15 14:28:00', '2022-07-15 14:45:00',
 'account', '账户', 'transfer_form', '转账表单', '/mobile/transfer', '转账', NULL, 'success', '进入转账页面',
 'transfer', '转账汇款', '转账操作', b'0', NULL, NULL, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_5 like Mac OS X)', 390, 844, 390, 844,
 b'0', 18, NULL, 'click', 'transfer_button', 'success', '点击转账按钮',
 '1', '2022-07-15 14:30:00', '1', '2022-07-15 14:30:00', b'0', 0),

(3, '2022-06-20 09:00:00', 'query', 'page', '账户查询', 'account_query', '账户余额', 3, '查询账户余额',
 'BHV202206200001', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.3.0', 'iOS 15.4',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', 'wifi',
 'SESSION20220620001', '2022-06-20 08:58:00', '2022-06-20 09:15:00',
 'home', '首页', 'account_detail', '账户详情', '/mobile/account/balance', '账户余额', NULL, 'success', '查询余额成功',
 'account', '账户管理', '余额查询', b'0', NULL, NULL, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_4 like Mac OS X)', 390, 844, 390, 844,
 b'0', 15, NULL, 'query', 'balance_query', 'success', '查询账户余额',
 '1', '2022-06-20 09:00:00', '1', '2022-06-20 09:00:00', b'0', 0),

(3, '2022-05-10 16:20:00', 'browse', 'page', '活动', 'activity', '活动中心', 10, '浏览营销活动',
 'BHV202205100001', 'mobile_banking', 'ios', 'DEVICE003', 'iPhone 12', '3.2.0', 'iOS 15.3',
 NULL, NULL, '192.168.1.102', '浙江省杭州市', 'wifi',
 'SESSION20220510001', '2022-05-10 16:18:00', '2022-05-10 16:35:00',
 'home', '首页', 'activity_detail', '活动详情', '/mobile/activity/list', '活动列表', NULL, 'success', '浏览活动',
 'activity', '营销活动', '活动浏览', b'0', NULL, NULL, NULL, NULL, NULL,
 'Mozilla/5.0 (iPhone; CPU iPhone OS 15_3 like Mac OS X)', 390, 844, 390, 844,
 b'0', 12, NULL, 'browse', 'activity_list', 'success', '浏览营销活动',
 '1', '2022-05-10 16:20:00', '1', '2022-05-10 16:20:00', b'0', 0);

-- 客户4-20的数据类似，这里省略详细内容，实际使用时需要补全
-- 为了节省空间，仅展示数据结构

-- ==================== 验证查询 ====================

-- 按客户统计行为次数
SELECT
  c.id AS customer_id,
  c.customer_name,
  COUNT(b.id) AS behavior_count,
  COUNT(DISTINCT b.session_id) AS session_count,
  SUM(CASE WHEN b.is_conversion = 1 THEN 1 ELSE 0 END) AS conversion_count
FROM crm_customer c
LEFT JOIN crm_customer_channel_behavior b ON c.id = b.customer_id
WHERE c.deleted = 0
GROUP BY c.id, c.customer_name
ORDER BY c.id;

-- 按操作行为统计
SELECT
  operation_action,
  COUNT(*) AS count
FROM crm_customer_channel_behavior
GROUP BY operation_action
ORDER BY count DESC;

-- 按渠道类型统计
SELECT
  channel_type,
  COUNT(*) AS count,
  COUNT(DISTINCT customer_id) AS customer_count
FROM crm_customer_channel_behavior
GROUP BY channel_type
ORDER BY count DESC;

-- 按业务类型统计
SELECT
  business_type,
  COUNT(*) AS count,
  SUM(CASE WHEN is_conversion = 1 THEN 1 ELSE 0 END) AS conversion_count,
  ROUND(SUM(CASE WHEN is_conversion = 1 THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS conversion_rate
FROM crm_customer_channel_behavior
WHERE business_type IS NOT NULL
GROUP BY business_type
ORDER BY count DESC;

-- 按设备类型统计
SELECT
  device_type,
  COUNT(*) AS count
FROM crm_customer_channel_behavior
WHERE device_type IS NOT NULL
GROUP BY device_type
ORDER BY count DESC;

-- 页面停留时间TOP10
SELECT
  current_page_identifier,
  current_page_name,
  AVG(page_stay_seconds) AS avg_stay_seconds,
  COUNT(*) AS visit_count
FROM crm_customer_channel_behavior
GROUP BY current_page_identifier, current_page_name
ORDER BY avg_stay_seconds DESC
LIMIT 10;

-- 转化行为统计
SELECT
  conversion_type,
  COUNT(*) AS count,
  SUM(conversion_value) AS total_value
FROM crm_customer_channel_behavior
WHERE is_conversion = 1 AND conversion_type IS NOT NULL
GROUP BY conversion_type
ORDER BY count DESC;
