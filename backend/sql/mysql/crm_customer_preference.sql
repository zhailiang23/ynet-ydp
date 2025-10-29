-- ----------------------------
-- 客户投资偏好表 (零售客户特有)
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表关联方式
-- 关系: crm_retail_customer 1对1 crm_customer_preference
-- 参考老系统: acrm_f_ci_per_preference
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_preference`;
CREATE TABLE `crm_customer_preference` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '投资偏好主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表，1对1关系）',

  -- ==================== 喜好的电子渠道 ====================
  `prefer_mobile_banking` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好手机银行',
  `prefer_phone_banking` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好电话银行',
  `prefer_online_banking` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好网上银行',
  `prefer_wechat_banking` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好微信银行',
  `prefer_sms_service` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好短信服务',
  `prefer_atm_banking` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好自助银行',
  `prefer_channel_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他电子渠道',

  -- ==================== 喜好投资类型 ====================
  `prefer_invest_stocks` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好股票投资',
  `prefer_invest_funds` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好基金投资',
  `prefer_invest_bonds` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好债券投资',
  `prefer_invest_gold` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好黄金投资',
  `prefer_invest_forex` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好外汇投资',
  `prefer_invest_wealth_products` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好理财产品',
  `prefer_invest_trust` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好信托产品',
  `prefer_invest_insurance` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好保险理财',
  `prefer_invest_private_equity` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好私募基金',
  `prefer_invest_structured_products` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好结构性产品',
  `prefer_invest_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他投资类型',

  -- ==================== 喜好品牌类型 ====================
  `prefer_brand_luxury` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好奢侈品牌',
  `prefer_brand_light_luxury` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好轻奢品牌',
  `prefer_brand_international` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好国际品牌',
  `prefer_brand_local` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好本土品牌',
  `prefer_brand_designer` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好设计师品牌',
  `prefer_brand_fast_fashion` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好快时尚品牌',
  `prefer_brand_niche` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好小众品牌',
  `prefer_brand_eco_friendly` bit(1) NOT NULL DEFAULT b'0' COMMENT '喜好环保品牌',
  `prefer_brand_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他品牌类型',

  -- ==================== 希望得到的理财服务 ====================
  `prefer_service_asset_allocation` bit(1) NOT NULL DEFAULT b'0' COMMENT '资产配置建议',
  `prefer_service_market_analysis` bit(1) NOT NULL DEFAULT b'0' COMMENT '市场分析',
  `prefer_service_product_recommendation` bit(1) NOT NULL DEFAULT b'0' COMMENT '产品推荐',
  `prefer_service_risk_assessment` bit(1) NOT NULL DEFAULT b'0' COMMENT '风险评估',
  `prefer_service_tax_planning` bit(1) NOT NULL DEFAULT b'0' COMMENT '税务筹划',
  `prefer_service_wealth_inheritance` bit(1) NOT NULL DEFAULT b'0' COMMENT '财富传承',
  `prefer_service_insurance_planning` bit(1) NOT NULL DEFAULT b'0' COMMENT '保险规划',
  `prefer_service_retirement_planning` bit(1) NOT NULL DEFAULT b'0' COMMENT '退休规划',
  `prefer_service_education_planning` bit(1) NOT NULL DEFAULT b'0' COMMENT '教育金规划',
  `prefer_service_real_estate_consulting` bit(1) NOT NULL DEFAULT b'0' COMMENT '房产咨询',
  `prefer_service_overseas_investment` bit(1) NOT NULL DEFAULT b'0' COMMENT '海外投资',
  `prefer_service_family_trust` bit(1) NOT NULL DEFAULT b'0' COMMENT '家族信托',
  `prefer_service_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他理财服务',

  -- ==================== 希望理财经理的联系方式 ====================
  `prefer_contact_phone` bit(1) NOT NULL DEFAULT b'0' COMMENT '电话联系',
  `prefer_contact_wechat` bit(1) NOT NULL DEFAULT b'0' COMMENT '微信联系',
  `prefer_contact_email` bit(1) NOT NULL DEFAULT b'0' COMMENT '邮件联系',
  `prefer_contact_sms` bit(1) NOT NULL DEFAULT b'0' COMMENT '短信联系',
  `prefer_contact_visit` bit(1) NOT NULL DEFAULT b'0' COMMENT '上门拜访',
  `prefer_contact_video` bit(1) NOT NULL DEFAULT b'0' COMMENT '视频通话',
  `prefer_contact_app` bit(1) NOT NULL DEFAULT b'0' COMMENT 'APP消息',
  `prefer_contact_letter` bit(1) NOT NULL DEFAULT b'0' COMMENT '纸质信件',
  `prefer_contact_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他联系方式',

  -- ==================== 希望参加的沙龙活动 ====================
  `prefer_salon_investment_seminar` bit(1) NOT NULL DEFAULT b'0' COMMENT '投资理财讲座',
  `prefer_salon_wine_tasting` bit(1) NOT NULL DEFAULT b'0' COMMENT '红酒品鉴',
  `prefer_salon_golf` bit(1) NOT NULL DEFAULT b'0' COMMENT '高尔夫活动',
  `prefer_salon_art_appreciation` bit(1) NOT NULL DEFAULT b'0' COMMENT '艺术鉴赏',
  `prefer_salon_health_wellness` bit(1) NOT NULL DEFAULT b'0' COMMENT '健康养生',
  `prefer_salon_parent_child` bit(1) NOT NULL DEFAULT b'0' COMMENT '亲子教育',
  `prefer_salon_business_networking` bit(1) NOT NULL DEFAULT b'0' COMMENT '商务交流',
  `prefer_salon_travel` bit(1) NOT NULL DEFAULT b'0' COMMENT '旅游考察',
  `prefer_salon_tea_ceremony` bit(1) NOT NULL DEFAULT b'0' COMMENT '茶艺文化',
  `prefer_salon_cuisine` bit(1) NOT NULL DEFAULT b'0' COMMENT '美食品鉴',
  `prefer_salon_reading` bit(1) NOT NULL DEFAULT b'0' COMMENT '读书会',
  `prefer_salon_photography` bit(1) NOT NULL DEFAULT b'0' COMMENT '摄影交流',
  `prefer_salon_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他沙龙活动',

  -- ==================== 个人兴趣爱好 ====================
  `hobby_sports` bit(1) NOT NULL DEFAULT b'0' COMMENT '运动健身',
  `hobby_reading` bit(1) NOT NULL DEFAULT b'0' COMMENT '阅读',
  `hobby_travel` bit(1) NOT NULL DEFAULT b'0' COMMENT '旅游',
  `hobby_music` bit(1) NOT NULL DEFAULT b'0' COMMENT '音乐',
  `hobby_food` bit(1) NOT NULL DEFAULT b'0' COMMENT '美食',
  `hobby_photography` bit(1) NOT NULL DEFAULT b'0' COMMENT '摄影',
  `hobby_painting` bit(1) NOT NULL DEFAULT b'0' COMMENT '绘画',
  `hobby_collecting` bit(1) NOT NULL DEFAULT b'0' COMMENT '收藏',
  `hobby_gardening` bit(1) NOT NULL DEFAULT b'0' COMMENT '园艺',
  `hobby_tea` bit(1) NOT NULL DEFAULT b'0' COMMENT '茶艺',
  `hobby_calligraphy` bit(1) NOT NULL DEFAULT b'0' COMMENT '书法',
  `hobby_chess` bit(1) NOT NULL DEFAULT b'0' COMMENT '棋牌',
  `hobby_fishing` bit(1) NOT NULL DEFAULT b'0' COMMENT '钓鱼',
  `hobby_pets` bit(1) NOT NULL DEFAULT b'0' COMMENT '宠物',
  `hobby_movies` bit(1) NOT NULL DEFAULT b'0' COMMENT '电影',
  `hobby_gaming` bit(1) NOT NULL DEFAULT b'0' COMMENT '游戏',
  `hobby_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他兴趣爱好',

  -- ==================== 希望联系的时间 ====================
  `prefer_time_workday_morning` bit(1) NOT NULL DEFAULT b'0' COMMENT '工作日上午(9:00-12:00)',
  `prefer_time_workday_afternoon` bit(1) NOT NULL DEFAULT b'0' COMMENT '工作日下午(14:00-18:00)',
  `prefer_time_workday_evening` bit(1) NOT NULL DEFAULT b'0' COMMENT '工作日晚上(18:00-21:00)',
  `prefer_time_weekend_morning` bit(1) NOT NULL DEFAULT b'0' COMMENT '周末上午(9:00-12:00)',
  `prefer_time_weekend_afternoon` bit(1) NOT NULL DEFAULT b'0' COMMENT '周末下午(14:00-18:00)',
  `prefer_time_weekend_evening` bit(1) NOT NULL DEFAULT b'0' COMMENT '周末晚上(18:00-21:00)',
  `prefer_time_anytime` bit(1) NOT NULL DEFAULT b'0' COMMENT '任何时间',
  `prefer_time_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他时间说明',

  -- ==================== 投资周期偏好 ====================
  `prefer_period_short_term` bit(1) NOT NULL DEFAULT b'0' COMMENT '短期(1年以内)',
  `prefer_period_medium_term` bit(1) NOT NULL DEFAULT b'0' COMMENT '中期(1-3年)',
  `prefer_period_long_term` bit(1) NOT NULL DEFAULT b'0' COMMENT '长期(3-5年)',
  `prefer_period_ultra_long_term` bit(1) NOT NULL DEFAULT b'0' COMMENT '超长期(5年以上)',
  `prefer_period_flexible` bit(1) NOT NULL DEFAULT b'0' COMMENT '灵活周期',
  `prefer_period_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他周期说明',

  -- ==================== 风险偏好（单选，使用字典）====================
  `risk_preference` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险偏好（字典: aicrm_risk_preference，conservative=保守型，prudent=稳健型，balanced=平衡型，aggressive=进取型，radical=激进型）',

  -- ==================== 偏好产品类型 ====================
  `prefer_product_deposit` bit(1) NOT NULL DEFAULT b'0' COMMENT '存款产品',
  `prefer_product_wealth_management` bit(1) NOT NULL DEFAULT b'0' COMMENT '理财产品',
  `prefer_product_insurance` bit(1) NOT NULL DEFAULT b'0' COMMENT '保险产品',
  `prefer_product_fund` bit(1) NOT NULL DEFAULT b'0' COMMENT '基金产品',
  `prefer_product_precious_metal` bit(1) NOT NULL DEFAULT b'0' COMMENT '贵金属',
  `prefer_product_forex` bit(1) NOT NULL DEFAULT b'0' COMMENT '外汇产品',
  `prefer_product_credit_card` bit(1) NOT NULL DEFAULT b'0' COMMENT '信用卡',
  `prefer_product_loan` bit(1) NOT NULL DEFAULT b'0' COMMENT '贷款产品',
  `prefer_product_structured` bit(1) NOT NULL DEFAULT b'0' COMMENT '结构性产品',
  `prefer_product_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他产品类型',

  -- ==================== 偏好资讯类型 ====================
  `prefer_info_market_analysis` bit(1) NOT NULL DEFAULT b'0' COMMENT '市场分析',
  `prefer_info_product_news` bit(1) NOT NULL DEFAULT b'0' COMMENT '产品资讯',
  `prefer_info_policy_update` bit(1) NOT NULL DEFAULT b'0' COMMENT '政策更新',
  `prefer_info_finance_knowledge` bit(1) NOT NULL DEFAULT b'0' COMMENT '理财知识',
  `prefer_info_activity_notice` bit(1) NOT NULL DEFAULT b'0' COMMENT '活动通知',
  `prefer_info_industry_report` bit(1) NOT NULL DEFAULT b'0' COMMENT '行业报告',
  `prefer_info_economic_data` bit(1) NOT NULL DEFAULT b'0' COMMENT '经济数据',
  `prefer_info_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他资讯类型',

  -- ==================== 语言偏好（单选，使用字典）====================
  `prefer_language` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'zh_CN' COMMENT '语言偏好（字典: aicrm_language，zh_CN=简体中文，zh_TW=繁体中文，en_US=英语，zh_HK=粤语）',

  -- ==================== 通知偏好 ====================
  `notification_email` bit(1) NOT NULL DEFAULT b'1' COMMENT '接收邮件通知',
  `notification_sms` bit(1) NOT NULL DEFAULT b'1' COMMENT '接收短信通知',
  `notification_wechat` bit(1) NOT NULL DEFAULT b'1' COMMENT '接收微信通知',
  `notification_app_push` bit(1) NOT NULL DEFAULT b'1' COMMENT '接收APP推送通知',
  `notification_phone` bit(1) NOT NULL DEFAULT b'0' COMMENT '接收电话通知',
  `notification_letter` bit(1) NOT NULL DEFAULT b'0' COMMENT '接收纸质信件',

  -- ==================== 投资金额偏好 ====================
  `prefer_amount_below_10w` bit(1) NOT NULL DEFAULT b'0' COMMENT '10万以下',
  `prefer_amount_10w_to_50w` bit(1) NOT NULL DEFAULT b'0' COMMENT '10-50万',
  `prefer_amount_50w_to_100w` bit(1) NOT NULL DEFAULT b'0' COMMENT '50-100万',
  `prefer_amount_100w_to_300w` bit(1) NOT NULL DEFAULT b'0' COMMENT '100-300万',
  `prefer_amount_above_300w` bit(1) NOT NULL DEFAULT b'0' COMMENT '300万以上',
  `prefer_amount_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他金额说明',

  -- ==================== 收益预期 ====================
  `expect_return_below_3` bit(1) NOT NULL DEFAULT b'0' COMMENT '预期收益3%以下',
  `expect_return_3_to_5` bit(1) NOT NULL DEFAULT b'0' COMMENT '预期收益3%-5%',
  `expect_return_5_to_8` bit(1) NOT NULL DEFAULT b'0' COMMENT '预期收益5%-8%',
  `expect_return_8_to_12` bit(1) NOT NULL DEFAULT b'0' COMMENT '预期收益8%-12%',
  `expect_return_above_12` bit(1) NOT NULL DEFAULT b'0' COMMENT '预期收益12%以上',
  `expect_return_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他收益预期',

  -- ==================== 服务频率偏好 ====================
  `prefer_frequency_daily` bit(1) NOT NULL DEFAULT b'0' COMMENT '每天联系',
  `prefer_frequency_weekly` bit(1) NOT NULL DEFAULT b'0' COMMENT '每周联系',
  `prefer_frequency_biweekly` bit(1) NOT NULL DEFAULT b'0' COMMENT '每两周联系',
  `prefer_frequency_monthly` bit(1) NOT NULL DEFAULT b'0' COMMENT '每月联系',
  `prefer_frequency_quarterly` bit(1) NOT NULL DEFAULT b'0' COMMENT '每季度联系',
  `prefer_frequency_on_demand` bit(1) NOT NULL DEFAULT b'0' COMMENT '有需要时联系',
  `prefer_frequency_other` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他频率说明',

  -- ==================== 营销偏好 ====================
  `allow_marketing` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否接受营销推广',
  `allow_third_party_sharing` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否允许第三方信息共享',
  `allow_profiling` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否允许用户画像分析',

  -- ==================== 其他备注 ====================
  `preference_notes` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '偏好备注（自由文本）',

  -- ==================== 最后更新信息 ====================
  `last_survey_time` datetime NULL DEFAULT NULL COMMENT '最后问卷调查时间',
  `last_update_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '最后更新来源（survey=问卷，manual=手动，system=系统推断）',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_customer_id`(`customer_id` ASC, `deleted` ASC) USING BTREE COMMENT '客户ID唯一索引（1对1关系）',
  INDEX `idx_risk_preference`(`risk_preference` ASC) USING BTREE COMMENT '风险偏好索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户投资偏好表（零售客户特有，1对1关系）';

-- ----------------------------
-- 为现有所有客户创建默认投资偏好记录
-- ----------------------------
INSERT INTO `crm_customer_preference` (
  `customer_id`,
  `prefer_mobile_banking`,
  `prefer_online_banking`,
  `prefer_wechat_banking`,
  `risk_preference`,
  `prefer_language`,
  `last_update_source`,
  `creator`,
  `create_time`,
  `updater`,
  `update_time`,
  `tenant_id`
)
SELECT
  c.id AS customer_id,
  b'1' AS prefer_mobile_banking,
  b'1' AS prefer_online_banking,
  b'1' AS prefer_wechat_banking,
  'balanced' AS risk_preference,
  'zh_CN' AS prefer_language,
  'system' AS last_update_source,
  '1' AS creator,
  NOW() AS create_time,
  '1' AS updater,
  NOW() AS update_time,
  c.tenant_id
FROM crm_customer c
LEFT JOIN crm_customer_preference p ON c.id = p.customer_id AND p.deleted = b'0'
WHERE p.id IS NULL
  AND c.deleted = b'0';

-- ----------------------------
-- 字典类型和字典数据（用于单选字段）
-- ----------------------------

-- 1. 风险偏好字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM风险偏好', 'aicrm_risk_preference', 0, '客户风险偏好类型', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '保守型', 'conservative', 'aicrm_risk_preference', 0, 'success', '追求本金安全，不愿承担风险', '1', NOW(), '1', NOW(), b'0'),
(2, '稳健型', 'prudent', 'aicrm_risk_preference', 0, 'primary', '追求稳定收益，承担较小风险', '1', NOW(), '1', NOW(), b'0'),
(3, '平衡型', 'balanced', 'aicrm_risk_preference', 0, 'info', '收益与风险平衡', '1', NOW(), '1', NOW(), b'0'),
(4, '进取型', 'aggressive', 'aicrm_risk_preference', 0, 'warning', '追求较高收益，承担较大风险', '1', NOW(), '1', NOW(), b'0'),
(5, '激进型', 'radical', 'aicrm_risk_preference', 0, 'danger', '追求高收益，愿承担高风险', '1', NOW(), '1', NOW(), b'0');

-- 2. 语言偏好字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM语言偏好', 'aicrm_language', 0, '客户语言偏好', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '简体中文', 'zh_CN', 'aicrm_language', 0, 'primary', '简体中文', '1', NOW(), '1', NOW(), b'0'),
(2, '繁体中文', 'zh_TW', 'aicrm_language', 0, 'success', '繁体中文', '1', NOW(), '1', NOW(), b'0'),
(3, '英语', 'en_US', 'aicrm_language', 0, 'info', '英语', '1', NOW(), '1', NOW(), b'0'),
(4, '粤语', 'zh_HK', 'aicrm_language', 0, 'warning', '粤语', '1', NOW(), '1', NOW(), b'0');
