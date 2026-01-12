-- =============================================
-- 金融产品管理系统数据库初始化脚本
-- =============================================

-- =============================================
-- 1. 创建金融产品表
-- =============================================
CREATE TABLE IF NOT EXISTS `fin_product` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '产品编号',
  `product_code` VARCHAR(50) NOT NULL COMMENT '产品代码',
  `product_name` VARCHAR(100) NOT NULL COMMENT '产品名称',
  `catalog_id` BIGINT NOT NULL COMMENT '产品目录ID（关联 crm_product_catalog.id）',
  `category` VARCHAR(20) NOT NULL COMMENT '产品分类（理财、基金、保险、债券、贵金属）',
  `risk_level` VARCHAR(10) NOT NULL COMMENT '风险等级（R1-R5 或 PR1-PR5）',

  -- 收益信息
  `expected_return` DECIMAL(5,2) COMMENT '预期收益率（%）',
  `return_type` VARCHAR(20) COMMENT '收益类型（业绩比较基准、近一年收益、预定利率）',

  -- 期限信息
  `duration` VARCHAR(50) COMMENT '产品期限（如：360天、10年）',
  `duration_days` INT COMMENT '期限天数（用于排序）',

  -- 投资金额
  `minimum_investment` DECIMAL(12,2) NOT NULL COMMENT '起购金额',
  `investment_unit` VARCHAR(10) DEFAULT '元' COMMENT '金额单位',

  -- 产品状态
  `status` TINYINT NOT NULL DEFAULT 1 COMMENT '产品状态（0=停售 1=在售）',
  `is_hot` TINYINT DEFAULT 0 COMMENT '是否热销（0=否 1=是）',
  `is_new` TINYINT DEFAULT 0 COMMENT '是否新品（0=否 1=是）',

  -- 产品详情
  `description` TEXT COMMENT '产品描述',
  `features` TEXT COMMENT '产品特色（JSON 格式）',
  `sale_channel` VARCHAR(20) COMMENT '销售渠道（自营、代销）',

  -- 标签（JSON 格式存储）
  `tags` JSON COMMENT '产品标签（JSON 数组，如：["高收益", "低风险", "AI推荐"]）',

  -- AI 相关
  `ai_match_score` INT COMMENT 'AI 匹配度（0-100）',
  `ai_keywords` VARCHAR(500) COMMENT 'AI 关键词（逗号分隔）',

  -- 展示信息
  `sort` INT DEFAULT 0 COMMENT '显示顺序',
  `banner_image` VARCHAR(255) COMMENT '轮播图片 URL',

  -- 多租户和审计字段
  `tenant_id` BIGINT NOT NULL COMMENT '租户编号',
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_code` (`product_code`, `deleted`),
  KEY `idx_catalog_id` (`catalog_id`),
  KEY `idx_category` (`category`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='金融产品表';

-- =============================================
-- 2. 创建产品轮播推荐表
-- =============================================
CREATE TABLE IF NOT EXISTS `fin_product_carousel` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL COMMENT '轮播标题',
  `subtitle` VARCHAR(200) COMMENT '轮播副标题',
  `image_url` VARCHAR(255) NOT NULL COMMENT '轮播图片URL',
  `link_type` VARCHAR(20) COMMENT 'product=产品详情、url=外部链接',
  `link_id` BIGINT COMMENT '关联产品ID',
  `link_url` VARCHAR(500) COMMENT '外部链接URL',
  `badge_text` VARCHAR(20) COMMENT '角标文字（如：早报精选、AI 洞察）',
  `badge_color` VARCHAR(20) COMMENT '角标颜色',
  `sort` INT DEFAULT 0,
  `status` TINYINT NOT NULL DEFAULT 1,

  `tenant_id` BIGINT NOT NULL,
  `creator` VARCHAR(64) DEFAULT '',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updater` VARCHAR(64) DEFAULT '',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted` BIT(1) NOT NULL DEFAULT b'0',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品轮播推荐表';

-- =============================================
-- 3. 插入字典类型
-- =============================================
-- 产品分类字典类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('AICRM金融产品分类', 'aicrm_fin_product_category', 0, '金融产品分类（理财、基金、保险、债券、贵金属）', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

-- 风险等级字典类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('AICRM金融产品风险等级', 'aicrm_fin_risk_level', 0, '金融产品风险等级（R1-R5）', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

-- 产品状态字典类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('AICRM金融产品状态', 'aicrm_fin_product_status', 0, '金融产品状态（在售、停售）', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`);

-- =============================================
-- 4. 插入字典数据
-- =============================================
-- 产品分类字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
  (1, '理财', 'WEALTH', 'aicrm_fin_product_category', 0, 'primary', '', '理财产品', '1', NOW(), '1', NOW(), b'0'),
  (2, '基金', 'FUND', 'aicrm_fin_product_category', 0, 'success', '', '基金产品', '1', NOW(), '1', NOW(), b'0'),
  (3, '保险', 'INSURANCE', 'aicrm_fin_product_category', 0, 'info', '', '保险产品', '1', NOW(), '1', NOW(), b'0'),
  (4, '债券', 'BOND', 'aicrm_fin_product_category', 0, 'warning', '', '债券产品', '1', NOW(), '1', NOW(), b'0'),
  (5, '贵金属', 'PRECIOUS_METAL', 'aicrm_fin_product_category', 0, 'danger', '', '贵金属产品', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`);

-- 风险等级字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
  (1, 'R1-极低风险', 'R1', 'aicrm_fin_risk_level', 0, 'success', '', '极低风险', '1', NOW(), '1', NOW(), b'0'),
  (2, 'R2-低风险', 'R2', 'aicrm_fin_risk_level', 0, 'primary', '', '低风险', '1', NOW(), '1', NOW(), b'0'),
  (3, 'R3-中等风险', 'R3', 'aicrm_fin_risk_level', 0, 'info', '', '中等风险', '1', NOW(), '1', NOW(), b'0'),
  (4, 'R4-中高风险', 'R4', 'aicrm_fin_risk_level', 0, 'warning', '', '中高风险', '1', NOW(), '1', NOW(), b'0'),
  (5, 'R5-高风险', 'R5', 'aicrm_fin_risk_level', 0, 'danger', '', '高风险', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`);

-- 产品状态字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
  (1, '在售', 'SELLING', 'aicrm_fin_product_status', 0, 'success', '', '产品在售', '1', NOW(), '1', NOW(), b'0'),
  (2, '停售', 'STOPPED', 'aicrm_fin_product_status', 0, 'danger', '', '产品停售', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE `label` = VALUES(`label`);

-- =============================================
-- 5. 插入测试数据
-- =============================================
-- 注意：需要先获取有效的 catalog_id，这里使用 1 作为示例
-- 实际使用时应该查询 crm_product_catalog 表获取真实的 catalog_id

-- 插入理财产品
INSERT INTO `fin_product` (
  `product_code`, `product_name`, `catalog_id`, `category`, `risk_level`,
  `expected_return`, `return_type`, `duration`, `duration_days`,
  `minimum_investment`, `investment_unit`, `status`, `is_hot`, `is_new`,
  `description`, `sale_channel`, `tags`, `ai_match_score`, `sort`, `tenant_id`
)
VALUES
  ('WM001', '富盈封闭式360天', 1, 'WEALTH', 'R2',
   4.25, '业绩比较基准', '360天', 360,
   10000.00, '元', 1, 1, 0,
   '稳健理财产品，适合保守型投资者', '代销',
   JSON_ARRAY('高收益', '低风险', '热销'), 85, 1, 1),

  ('WM002', '智盈宝180天', 1, 'WEALTH', 'R2',
   3.95, '业绩比较基准', '180天', 180,
   50000.00, '元', 1, 0, 1,
   '短期灵活理财，随时可赎回', '自营',
   JSON_ARRAY('短期灵活', '新品首发'), 78, 2, 1),

  ('FUND001', '沪深300指数基金', 1, 'FUND', 'R3',
   8.50, '近一年收益', '长期持有', 99999,
   1000.00, '元', 1, 1, 0,
   '跟踪沪深300指数，分散投资风险', '代销',
   JSON_ARRAY('高收益', 'AI推荐'), 92, 3, 1),

  ('INS001', '安康重疾险', 1, 'INSURANCE', 'R1',
   NULL, NULL, '20年', 7300,
   3000.00, '元/年', 1, 0, 0,
   '保障100种重大疾病，保额最高50万', '代销',
   JSON_ARRAY('低风险', '长期保障'), 70, 4, 1),

  ('BOND001', '国债逆回购7天', 1, 'BOND', 'R1',
   2.80, '固定收益', '7天', 7,
   1000.00, '元', 1, 0, 0,
   '国债逆回购，安全性高', '自营',
   JSON_ARRAY('低风险', '短期灵活'), 65, 5, 1),

  ('GOLD001', '黄金定投', 1, 'PRECIOUS_METAL', 'R3',
   6.20, '近一年收益', '长期持有', 99999,
   100.00, '元', 1, 1, 1,
   '按月定投黄金，对冲通胀风险', '自营',
   JSON_ARRAY('新品首发', 'AI推荐', '抗通胀'), 88, 6, 1);

-- 插入轮播推荐数据
INSERT INTO `fin_product_carousel` (
  `title`, `subtitle`, `image_url`, `link_type`, `link_id`,
  `badge_text`, `badge_color`, `sort`, `status`, `tenant_id`
)
VALUES
  ('早报精选', '今日推荐高收益理财产品', 'https://via.placeholder.com/400x200', 'product', 1,
   '早报精选', 'primary', 1, 1, 1),

  ('AI 洞察', 'AI推荐：适合您的投资组合', 'https://via.placeholder.com/400x200', 'product', 3,
   'AI 洞察', 'success', 2, 1, 1);

-- =============================================
-- 执行完成提示
-- =============================================
SELECT '金融产品管理系统数据库初始化完成！' AS message;
SELECT COUNT(*) AS product_count FROM fin_product WHERE deleted = b'0';
SELECT COUNT(*) AS carousel_count FROM fin_product_carousel WHERE deleted = b'0';
SELECT COUNT(*) AS dict_type_count FROM system_dict_type WHERE type LIKE 'aicrm_fin%' AND deleted = b'0';
SELECT COUNT(*) AS dict_data_count FROM system_dict_data WHERE dict_type LIKE 'aicrm_fin%' AND deleted = b'0';
