-- ----------------------------
-- 客户产品推荐表
-- 设计原则:
-- 1. 记录系统根据客户画像和偏好生成的产品推荐信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、产品编号、产品名称
-- 4. 关联产品分类表 crm_product_category
-- 5. 支持智能推荐和人工推荐
-- 6. 支持推荐效果跟踪
-- 7. 展示匹配客户的行内推荐产品信息，以及行内近期的热销产品，方便各个渠道用户快速对客户进行营销
-- ----------------------------

-- ==================== 客户产品推荐表 ====================
DROP TABLE IF EXISTS `crm_customer_product_recommendation`;
CREATE TABLE `crm_customer_product_recommendation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '推荐主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品编号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',

  -- ==================== 产品关联信息 ====================
  `product_category_id` bigint NOT NULL COMMENT '产品类目ID（关联 crm_product_category 表）',
  `product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品类型（字典: aicrm_product_type）',

  -- ==================== 推荐信息 ====================
  `recommendation_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推荐编号',
  `recommendation_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
  `recommendation_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
  `recommendation_reason` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推荐理由',
  `recommendation_score` decimal(10,2) NULL DEFAULT NULL COMMENT '推荐评分（0-100分）',
  `recommendation_date` date NOT NULL COMMENT '推荐日期',
  `recommendation_time` datetime NOT NULL COMMENT '推荐时间',
  `valid_start_date` date NOT NULL COMMENT '有效开始日期',
  `valid_end_date` date NOT NULL COMMENT '有效结束日期',
  `is_hot_sale` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否热销产品',
  `is_match_customer` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否匹配客户',
  `match_degree` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',

  -- ==================== 推荐状态 ====================
  `recommendation_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）',
  `push_time` datetime NULL DEFAULT NULL COMMENT '推送时间',
  `push_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
  `view_time` datetime NULL DEFAULT NULL COMMENT '查看时间',
  `view_count` int NOT NULL DEFAULT 0 COMMENT '查看次数',
  `click_time` datetime NULL DEFAULT NULL COMMENT '点击时间',
  `click_count` int NOT NULL DEFAULT 0 COMMENT '点击次数',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '申请时间',
  `purchase_time` datetime NULL DEFAULT NULL COMMENT '购买时间',
  `purchase_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '购买金额',
  `reject_time` datetime NULL DEFAULT NULL COMMENT '拒绝时间',
  `reject_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '拒绝原因',

  -- ==================== 推荐人员信息 ====================
  `recommender_user_id` bigint NULL DEFAULT NULL COMMENT '推荐人ID',
  `recommender_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推荐人姓名',
  `recommender_dept_id` bigint NULL DEFAULT NULL COMMENT '推荐部门ID',
  `recommender_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '推荐部门名称',

  -- ==================== 产品详情信息 ====================
  `interest_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '利率/收益率（%）',
  `term_days` int NULL DEFAULT NULL COMMENT '期限（天）',
  `min_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '起购金额',
  `max_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '最大金额',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level）',
  `product_features` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品特点',
  `product_advantage` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品优势',

  -- ==================== 营销信息 ====================
  `marketing_theme` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营销主题',
  `marketing_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营销内容',
  `promotion_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '促销类型（字典: aicrm_promotion_type）',
  `promotion_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '促销内容',

  -- ==================== 效果跟踪 ====================
  `conversion_rate` decimal(10,2) NULL DEFAULT NULL COMMENT '转化率（%）',
  `is_effective` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有效',
  `effectiveness_score` decimal(10,2) NULL DEFAULT NULL COMMENT '有效性评分（0-100分）',
  `feedback_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `feedback_time` datetime NULL DEFAULT NULL COMMENT '反馈时间',

  -- ==================== 优先级和排序 ====================
  `priority` int NOT NULL DEFAULT 0 COMMENT '优先级（数字越大优先级越高）',
  `display_order` int NOT NULL DEFAULT 0 COMMENT '显示顺序',

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
  UNIQUE INDEX `uk_recommendation_no`(`recommendation_no` ASC) USING BTREE COMMENT '推荐编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_product_category_id`(`product_category_id` ASC) USING BTREE COMMENT '产品类目ID索引',
  INDEX `idx_product_code`(`product_code` ASC) USING BTREE COMMENT '产品编号索引',
  INDEX `idx_product_type`(`product_type` ASC) USING BTREE COMMENT '产品类型索引',
  INDEX `idx_recommendation_type`(`recommendation_type` ASC) USING BTREE COMMENT '推荐类型索引',
  INDEX `idx_recommendation_status`(`recommendation_status` ASC) USING BTREE COMMENT '推荐状态索引',
  INDEX `idx_recommendation_date`(`recommendation_date` ASC) USING BTREE COMMENT '推荐日期索引',
  INDEX `idx_is_hot_sale`(`is_hot_sale` ASC) USING BTREE COMMENT '是否热销产品索引',
  INDEX `idx_is_match_customer`(`is_match_customer` ASC) USING BTREE COMMENT '是否匹配客户索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户产品推荐表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 推荐类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM产品推荐类型', 'aicrm_recommendation_type', 0, '产品推荐的类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM产品推荐类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '智能推荐', 'intelligent', 'aicrm_recommendation_type', 0, 'primary', 'AI智能推荐', '1', NOW(), '1', NOW(), b'0'),
(2, '人工推荐', 'manual', 'aicrm_recommendation_type', 0, 'success', '客户经理人工推荐', '1', NOW(), '1', NOW(), b'0'),
(3, '热销产品', 'hot_sale', 'aicrm_recommendation_type', 0, 'danger', '近期热销产品', '1', NOW(), '1', NOW(), b'0'),
(4, '匹配客户', 'match_customer', 'aicrm_recommendation_type', 0, 'warning', '根据客户画像匹配', '1', NOW(), '1', NOW(), b'0'),
(5, '交叉销售', 'cross_sell', 'aicrm_recommendation_type', 0, 'info', '交叉销售推荐', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 推荐来源字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM推荐来源', 'aicrm_recommendation_source', 0, '产品推荐的来源渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM推荐来源';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '系统自动', 'system_auto', 'aicrm_recommendation_source', 0, 'primary', '系统自动生成推荐', '1', NOW(), '1', NOW(), b'0'),
(2, 'AI分析', 'ai_analysis', 'aicrm_recommendation_source', 0, 'success', 'AI大数据分析', '1', NOW(), '1', NOW(), b'0'),
(3, '客户经理推荐', 'manager_recommend', 'aicrm_recommendation_source', 0, 'info', '客户经理主动推荐', '1', NOW(), '1', NOW(), b'0'),
(4, '数据挖掘', 'data_mining', 'aicrm_recommendation_source', 0, 'warning', '数据挖掘推荐', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 匹配度字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM匹配度', 'aicrm_match_degree', 0, '客户与产品的匹配程度', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM匹配度';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '高度匹配', 'high', 'aicrm_match_degree', 0, 'success', '匹配度80%以上', '1', NOW(), '1', NOW(), b'0'),
(2, '中等匹配', 'medium', 'aicrm_match_degree', 0, 'warning', '匹配度50%-80%', '1', NOW(), '1', NOW(), b'0'),
(3, '低度匹配', 'low', 'aicrm_match_degree', 0, 'info', '匹配度50%以下', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 推荐状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM推荐状态', 'aicrm_recommendation_status', 0, '产品推荐的处理状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM推荐状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待推送', 'pending', 'aicrm_recommendation_status', 0, 'info', '推荐待推送', '1', NOW(), '1', NOW(), b'0'),
(2, '已推送', 'pushed', 'aicrm_recommendation_status', 0, 'primary', '已推送给客户', '1', NOW(), '1', NOW(), b'0'),
(3, '已查看', 'viewed', 'aicrm_recommendation_status', 0, 'default', '客户已查看', '1', NOW(), '1', NOW(), b'0'),
(4, '已点击', 'clicked', 'aicrm_recommendation_status', 0, 'warning', '客户已点击', '1', NOW(), '1', NOW(), b'0'),
(5, '已申请', 'applied', 'aicrm_recommendation_status', 0, 'success', '客户已申请', '1', NOW(), '1', NOW(), b'0'),
(6, '已购买', 'purchased', 'aicrm_recommendation_status', 0, 'success', '客户已购买', '1', NOW(), '1', NOW(), b'0'),
(7, '已拒绝', 'rejected', 'aicrm_recommendation_status', 0, 'danger', '客户已拒绝', '1', NOW(), '1', NOW(), b'0'),
(8, '已过期', 'expired', 'aicrm_recommendation_status', 0, 'default', '推荐已过期', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 推送渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM推送渠道', 'aicrm_push_channel', 0, '产品推荐的推送渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM推送渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '手机银行', 'mobile_banking', 'aicrm_push_channel', 0, 'primary', '手机银行APP推送', '1', NOW(), '1', NOW(), b'0'),
(2, '微信银行', 'wechat', 'aicrm_push_channel', 0, 'success', '微信银行推送', '1', NOW(), '1', NOW(), b'0'),
(3, '短信', 'sms', 'aicrm_push_channel', 0, 'warning', '短信推送', '1', NOW(), '1', NOW(), b'0'),
(4, '邮件', 'email', 'aicrm_push_channel', 0, 'info', '邮件推送', '1', NOW(), '1', NOW(), b'0'),
(5, '电话', 'call', 'aicrm_push_channel', 0, 'danger', '电话推荐', '1', NOW(), '1', NOW(), b'0'),
(6, '上门拜访', 'visit', 'aicrm_push_channel', 0, 'default', '上门拜访推荐', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 促销类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM促销类型', 'aicrm_promotion_type', 0, '产品促销的类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM促销类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '利率优惠', 'interest_discount', 'aicrm_promotion_type', 0, 'success', '利率优惠活动', '1', NOW(), '1', NOW(), b'0'),
(2, '费用减免', 'fee_waiver', 'aicrm_promotion_type', 0, 'primary', '手续费减免', '1', NOW(), '1', NOW(), b'0'),
(3, '赠送礼品', 'gift_giving', 'aicrm_promotion_type', 0, 'warning', '购买赠送礼品', '1', NOW(), '1', NOW(), b'0'),
(4, '积分奖励', 'points_reward', 'aicrm_promotion_type', 0, 'info', '积分奖励活动', '1', NOW(), '1', NOW(), b'0'),
(5, '限时优惠', 'time_limited', 'aicrm_promotion_type', 0, 'danger', '限时优惠活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
