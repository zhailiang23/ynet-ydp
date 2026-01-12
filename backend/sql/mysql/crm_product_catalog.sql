-- ----------------------------
-- CRM 产品目录表
-- 用于管理银行产品的树形分类结构
-- ----------------------------

-- 创建产品目录表
CREATE TABLE IF NOT EXISTS `crm_product_catalog` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '目录编号',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '目录名称',
  `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '父目录编号，0表示顶级目录',
  `sort` int NOT NULL DEFAULT 0 COMMENT '显示顺序',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态（0启用 1禁用）',
  `category_level` int NOT NULL DEFAULT 1 COMMENT '分类级别（1表示一级，2表示二级，以此类推）',
  `category_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '分类路径（如 /1/2/3/）',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '目录描述',

  -- 通用审计字段
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_parent_id` (`parent_id`) COMMENT '父目录索引',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户索引',
  KEY `idx_category_path` (`category_path`(255)) COMMENT '目录路径索引',
  KEY `idx_status` (`status`) COMMENT '状态索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM - 产品目录表';

-- 插入初始化数据
INSERT INTO `crm_product_catalog`
  (`id`, `name`, `parent_id`, `sort`, `status`, `category_level`, `category_path`, `description`, `creator`, `tenant_id`)
VALUES
  (1, '金融产品', 0, 1, 0, 1, '/1/', '银行金融产品目录', 'admin', 1),
  (2, '理财产品', 1, 1, 0, 2, '/1/2/', '各类理财产品', 'admin', 1),
  (3, '贷款产品', 1, 2, 0, 2, '/1/3/', '各类贷款产品', 'admin', 1),
  (4, '稳健型理财', 2, 1, 0, 3, '/1/2/4/', '低风险理财产品', 'admin', 1),
  (5, '进取型理财', 2, 2, 0, 3, '/1/2/5/', '高收益理财产品', 'admin', 1),
  (6, '个人贷款', 3, 1, 0, 3, '/1/3/6/', '个人信用贷款', 'admin', 1),
  (7, '企业贷款', 3, 2, 0, 3, '/1/3/7/', '企业经营贷款', 'admin', 1);
