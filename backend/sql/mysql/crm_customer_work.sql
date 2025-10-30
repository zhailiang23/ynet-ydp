-- ----------------------------
-- 客户工作信息表 (零售客户特有)
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表关联方式
-- 关系: crm_retail_customer 1对多 crm_customer_work
-- 说明: 记录零售客户的工作或经营信息,支持多个工作记录(如兼职、经营等)
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_work`;
CREATE TABLE `crm_customer_work` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- 工作基本信息
  `work_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工作类型（字典: aicrm_work_type, 如:水稻、家庭经营、非主营业项目等）',
  `employer_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作单位名称/经营主体名称',
  `employer_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单位性质（字典: crm_employer_type）',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位/职务',

  -- 工作时间
  `work_years` int NULL DEFAULT NULL COMMENT '工作年限/经营年限（单位:年）',
  `start_date` date NULL DEFAULT NULL COMMENT '入职日期/开始经营日期',
  `end_date` date NULL DEFAULT NULL COMMENT '离职日期/结束经营日期（NULL表示在职）',
  `is_current` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否当前工作（1=是 0=否）',

  -- 工作地点
  `work_address_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-省',
  `work_address_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-市',
  `work_address_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-区',
  `work_address_detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-详细地址',

  -- 收入信息
  `annual_income` decimal(15, 2) NULL DEFAULT NULL COMMENT '年收入（单位:元）',
  `monthly_income` decimal(15, 2) NULL DEFAULT NULL COMMENT '月收入（单位:元）',
  `income_source` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收入来源说明',

  -- 经营信息（适用于个体经营、家庭经营等）
  `business_scale` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营规模（字典: aicrm_business_scale, 如:500亩、20万元等）',
  `business_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营状态（字典: aicrm_business_status, 如:正常生产、在建/扩建等）',
  `production_capacity` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生产能力（如:年产量500吨）',
  `business_license_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业执照号/经营许可证号',

  -- 联系信息
  `work_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作电话/单位电话',
  `contact_person` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单位联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人电话',

  -- 核验信息
  `verification_status` tinyint NOT NULL DEFAULT 0 COMMENT '核验状态（0=未核验 1=核验中 2=核验通过 3=核验失败）',
  `verification_time` datetime NULL DEFAULT NULL COMMENT '核验时间',
  `verification_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '核验备注',

  -- 附件信息
  `attachment_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '附件URL列表（JSON格式，如营业执照、工作证明等）',

  -- 备注和扩展字段
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注信息',
  `extra_data` json NULL COMMENT '扩展数据（JSON格式，存储其他业务相关信息）',

  -- 系统字段
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_work_type`(`work_type` ASC) USING BTREE COMMENT '工作类型索引',
  INDEX `idx_employer_name`(`employer_name` ASC) USING BTREE COMMENT '单位名称索引',
  INDEX `idx_is_current`(`is_current` ASC) USING BTREE COMMENT '当前工作索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户工作或经营信息表（零售客户特有，1对多关系，支持多个工作记录）';

-- ----------------------------
-- 字典数据：工作类型 (aicrm_work_type)
-- 说明：应通过系统管理 -> 字典管理模块维护，此处提供参考数据
-- ----------------------------
-- INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted_time, deleted)
-- VALUES (NULL, 'AICRM 工作类型', 'aicrm_work_type', 0, '零售客户工作或经营类型字典', '1', NOW(), '1', NOW(), NULL, b'0');

-- INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
-- (NULL, 1, '全职在职', 'full_time', 'aicrm_work_type', 0, 'primary', '', '全职工作人员', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 2, '兼职在职', 'part_time', 'aicrm_work_type', 0, 'info', '', '兼职工作人员', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 3, '个体经营', 'self_employed', 'aicrm_work_type', 0, 'success', '', '个体工商户经营', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 4, '家庭经营', 'family_business', 'aicrm_work_type', 0, 'success', '', '家庭经营（如种植、养殖）', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 5, '合伙经营', 'partnership', 'aicrm_work_type', 0, 'warning', '', '合伙经营企业', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 6, '自由职业', 'freelance', 'aicrm_work_type', 0, 'info', '', '自由职业者', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 7, '退休', 'retired', 'aicrm_work_type', 0, 'default', '', '已退休人员', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 8, '无业', 'unemployed', 'aicrm_work_type', 0, 'default', '', '暂无工作', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 99, '其他', 'other', 'aicrm_work_type', 0, 'default', '', '其他类型工作', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 字典数据：经营规模 (aicrm_business_scale)
-- 说明：应通过系统管理 -> 字典管理模块维护，此处提供参考数据
-- ----------------------------
-- INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted_time, deleted)
-- VALUES (NULL, 'AICRM 经营规模', 'aicrm_business_scale', 0, '零售客户经营规模字典', '1', NOW(), '1', NOW(), NULL, b'0');

-- INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
-- (NULL, 1, '小规模（<10万）', 'small', 'aicrm_business_scale', 0, 'info', '', '年营业额小于10万元', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 2, '中小规模（10-50万）', 'medium_small', 'aicrm_business_scale', 0, 'primary', '', '年营业额10-50万元', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 3, '中等规模（50-100万）', 'medium', 'aicrm_business_scale', 0, 'success', '', '年营业额50-100万元', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 4, '较大规模（100-500万）', 'large', 'aicrm_business_scale', 0, 'warning', '', '年营业额100-500万元', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 5, '大规模（>500万）', 'extra_large', 'aicrm_business_scale', 0, 'danger', '', '年营业额大于500万元', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 字典数据：经营状态 (aicrm_business_status)
-- 说明：应通过系统管理 -> 字典管理模块维护，此处提供参考数据
-- ----------------------------
-- INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted_time, deleted)
-- VALUES (NULL, 'AICRM 经营状态', 'aicrm_business_status', 0, '零售客户经营状态字典', '1', NOW(), '1', NOW(), NULL, b'0');

-- INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
-- (NULL, 1, '正常生产', 'normal', 'aicrm_business_status', 0, 'success', '', '正常生产经营中', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 2, '在建/扩建', 'under_construction', 'aicrm_business_status', 0, 'warning', '', '正在建设或扩建中', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 3, '季节性停产', 'seasonal_shutdown', 'aicrm_business_status', 0, 'info', '', '季节性停产（如农业季节性）', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 4, '临时停产', 'temporary_shutdown', 'aicrm_business_status', 0, 'warning', '', '临时停产维护', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 5, '已停产', 'shutdown', 'aicrm_business_status', 0, 'danger', '', '已经停止生产经营', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 6, '已注销', 'cancelled', 'aicrm_business_status', 0, 'default', '', '已注销营业执照', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 示例数据（可选）
-- ----------------------------
-- BEGIN;
-- -- 示例1：客户ID为1的全职工作
-- INSERT INTO `crm_customer_work` (`id`, `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`, `position`, `work_years`, `start_date`, `end_date`, `is_current`, `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`, `annual_income`, `monthly_income`, `income_source`, `work_phone`, `contact_person`, `contact_phone`, `verification_status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
-- VALUES
-- (1, 1, 'full_time', '某某科技有限公司', 'private_enterprise', '互联网和相关服务', '高级工程师', 5, '2019-01-01', NULL, b'1', '广东省', '深圳市', '南山区', '科技园南区某大厦10楼', 200000.00, 16666.67, '工资收入', '0755-12345678', '人事部张经理', '13800138000', 2, '当前工作单位', '1', '2025-01-15 10:00:00', '1', '2025-01-15 10:00:00', b'0', 1);
--
-- -- 示例2：客户ID为2的家庭经营（种植业）
-- INSERT INTO `crm_customer_work` (`id`, `customer_id`, `work_type`, `employer_name`, `employer_type`, `industry`, `position`, `work_years`, `start_date`, `end_date`, `is_current`, `work_address_province`, `work_address_city`, `work_address_district`, `work_address_detail`, `annual_income`, `monthly_income`, `income_source`, `business_scale`, `business_status`, `production_capacity`, `work_phone`, `verification_status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
-- VALUES
-- (2, 2, 'family_business', '张家水稻种植', NULL, '农业', '种植户', 3, '2022-03-01', NULL, b'1', '广东省', '佛山市', '三水区', '西南街道某村', 150000.00, 12500.00, '农产品销售收入', '500亩', 'normal', '年产水稻500吨', '13900139000', 2, '家庭水稻种植业务', '1', '2025-01-15 10:05:00', '1', '2025-01-15 10:05:00', b'0', 1);
-- COMMIT;

-- ----------------------------
-- 说明和注意事项
-- ----------------------------
-- 1. 工作类型 (work_type) 通过字典管理，支持全职、兼职、经营等多种类型
-- 2. 支持多个工作记录，通过 is_current 标识当前工作
-- 3. 工作年限 (work_years) 可以通过 start_date 自动计算
-- 4. 经营信息字段（business_scale、business_status等）主要用于个体经营和家庭经营类型
-- 5. 收入信息需要定期更新，可用于客户画像和授信评估
-- 6. 核验状态 (verification_status) 用于跟踪工作信息的真实性核验
-- 7. 附件 (attachment_urls) 存储工作证明、营业执照等扫描件
-- 8. 支持多租户数据隔离（tenant_id）
-- 9. 索引设计：
--    - idx_customer_id: 根据客户ID快速查询该客户的所有工作记录
--    - idx_work_type: 根据工作类型统计和查询
--    - idx_employer_name: 根据单位名称查询（用于批量客户分析）
--    - idx_is_current: 快速查询当前在职记录
--    - idx_tenant_id: 多租户数据隔离
-- 10. 数据权限：通过 tenant_id 实现多租户隔离
-- 11. 软删除：使用 deleted 字段实现逻辑删除，不物理删除历史数据
-- 12. extra_data 字段可存储特殊行业的扩展信息（如农业的种植品种、养殖规模等）
