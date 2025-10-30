-- 客户偏好表
CREATE TABLE IF NOT EXISTS `crm_customer_preference` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联客户主表）',

  -- 喜好的电子渠道
  `electronic_channels` varchar(500) DEFAULT NULL COMMENT '电子渠道（多选，逗号分隔：mobile_bank,phone_bank,online_bank,wechat_bank,sms_service,atm）',
  `other_channel` varchar(200) DEFAULT NULL COMMENT '其他电子渠道',

  -- 喜好投资类型
  `investment_types` varchar(500) DEFAULT NULL COMMENT '投资类型（多选，逗号分隔）',
  `other_investment_type` varchar(200) DEFAULT NULL COMMENT '其他投资类型',

  -- 喜好品牌类型
  `brand_types` varchar(500) DEFAULT NULL COMMENT '品牌类型（多选，逗号分隔）',
  `other_brand_type` varchar(200) DEFAULT NULL COMMENT '其他品牌类型',

  -- 希望得到的理财服务
  `financial_services` varchar(500) DEFAULT NULL COMMENT '理财服务（多选，逗号分隔）',
  `other_financial_service` varchar(200) DEFAULT NULL COMMENT '其他理财服务',

  -- 希望理财经理的联系方式
  `contact_methods` varchar(500) DEFAULT NULL COMMENT '联系方式（多选，逗号分隔）',
  `other_contact_method` varchar(200) DEFAULT NULL COMMENT '其他联系方式',

  -- 希望参加的沙龙活动
  `salon_activities` varchar(500) DEFAULT NULL COMMENT '沙龙活动（多选，逗号分隔）',
  `other_salon_activity` varchar(200) DEFAULT NULL COMMENT '其他沙龙活动',

  -- 个人兴趣爱好
  `interest_hobbies` varchar(500) DEFAULT NULL COMMENT '兴趣爱好（多选，逗号分隔）',
  `other_interest_hobby` varchar(200) DEFAULT NULL COMMENT '其他兴趣爱好',

  -- 希望联系的时间
  `contact_times` varchar(500) DEFAULT NULL COMMENT '联系时间（多选，逗号分隔）',
  `other_contact_time` varchar(200) DEFAULT NULL COMMENT '其他联系时间',

  -- 投资周期偏好
  `investment_periods` varchar(500) DEFAULT NULL COMMENT '投资周期（多选，逗号分隔）',
  `other_investment_period` varchar(200) DEFAULT NULL COMMENT '其他投资周期',

  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`) COMMENT '每个客户只有一条偏好记录'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户偏好表';
