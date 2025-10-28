-- ----------------------------
-- 客户证件信息表 (零售客户特有)
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表关联方式
-- 关系: crm_retail_customer 1对多 crm_customer_identity
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_identity`;
CREATE TABLE `crm_customer_identity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '证件信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `identity_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件类型（字典: customer_identity_type）',
  `identity_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '证件号码（敏感信息，需加密存储）',
  `identity_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件上的姓名',
  `issue_authority` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发证机关',
  `issue_date` date NULL DEFAULT NULL COMMENT '发证日期',
  `expiry_date` date NULL DEFAULT NULL COMMENT '有效期至（长期有效填 9999-12-31）',
  `is_primary` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否主证件（默认身份证为主证件）',
  `identity_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件地址',
  `identity_front_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件正面照片URL',
  `identity_back_url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件反面照片URL',
  `verification_status` tinyint NOT NULL DEFAULT 0 COMMENT '核验状态（0=未核验 1=核验中 2=核验通过 3=核验失败）',
  `verification_time` datetime NULL DEFAULT NULL COMMENT '核验时间',
  `verification_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '核验备注',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_identity_no`(`identity_no` ASC) USING BTREE COMMENT '证件号码索引（用于查重和检索）',
  INDEX `idx_identity_type`(`identity_type` ASC) USING BTREE COMMENT '证件类型索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户证件信息表（零售客户特有，1对多关系，支持多证件）';

-- ----------------------------
-- 字典数据：证件类型 (customer_identity_type)
-- 说明：应通过系统管理 -> 字典管理模块维护，此处提供参考数据
-- ----------------------------
-- INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted_time, deleted)
-- VALUES (NULL, '客户证件类型', 'customer_identity_type', 0, '零售客户证件类型字典', '1', NOW(), '1', NOW(), NULL, b'0');

-- INSERT INTO system_dict_data (id, sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted) VALUES
-- (NULL, 1, '居民身份证', 'id_card', 'customer_identity_type', 0, 'primary', '', '中华人民共和国居民身份证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 2, '护照', 'passport', 'customer_identity_type', 0, 'success', '', '中华人民共和国护照', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 3, '港澳通行证', 'hk_macao_pass', 'customer_identity_type', 0, 'info', '', '往来港澳通行证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 4, '台湾通行证', 'taiwan_pass', 'customer_identity_type', 0, 'info', '', '大陆居民往来台湾通行证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 5, '军官证', 'military_officer', 'customer_identity_type', 0, 'warning', '', '中国人民解放军军官证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 6, '士兵证', 'military_soldier', 'customer_identity_type', 0, 'warning', '', '中国人民解放军士兵证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 7, '武警证', 'armed_police', 'customer_identity_type', 0, 'warning', '', '中国人民武装警察证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 8, '港澳居民居住证', 'hk_macao_residence', 'customer_identity_type', 0, 'success', '', '港澳居民居住证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 9, '台湾居民居住证', 'taiwan_residence', 'customer_identity_type', 0, 'success', '', '台湾居民居住证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 10, '外国人永久居留证', 'foreign_permanent', 'customer_identity_type', 0, 'danger', '', '中华人民共和国外国人永久居留身份证', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 11, '户口簿', 'household_register', 'customer_identity_type', 0, 'default', '', '居民户口簿', '1', NOW(), '1', NOW(), b'0'),
-- (NULL, 99, '其他证件', 'other', 'customer_identity_type', 0, 'default', '', '其他类型证件', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 示例数据（可选）
-- ----------------------------
-- BEGIN;
-- -- 示例：客户ID为1的客户有身份证和护照两个证件
-- INSERT INTO `crm_customer_identity` (`id`, `customer_id`, `identity_type`, `identity_no`, `identity_name`, `issue_authority`, `issue_date`, `expiry_date`, `is_primary`, `identity_address`, `identity_front_url`, `identity_back_url`, `verification_status`, `verification_time`, `verification_remark`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
-- VALUES
-- (1, 1, 'id_card', '110101199001011234', '张三', '北京市公安局朝阳分局', '2010-01-01', '2030-01-01', b'1', '北京市朝阳区某某街道', '/upload/identity/1_front.jpg', '/upload/identity/1_back.jpg', 2, '2025-01-15 10:00:00', '核验通过', '主证件', '1', '2025-01-15 09:00:00', '1', '2025-01-15 10:00:00', b'0', 1),
-- (2, 1, 'passport', 'E12345678', '张三', '北京市出入境管理局', '2020-06-01', '2030-06-01', b'0', NULL, '/upload/identity/2_front.jpg', NULL, 2, '2025-01-15 10:05:00', '核验通过', '因私普通护照', '1', '2025-01-15 09:10:00', '1', '2025-01-15 10:05:00', b'0', 1);
-- COMMIT;

-- ----------------------------
-- 说明和注意事项
-- ----------------------------
-- 1. 证件号码字段 (identity_no) 存储敏感信息，建议在应用层进行加密存储
-- 2. 证件类型 (identity_type) 通过字典管理，字典类型为 'customer_identity_type'
-- 3. 有效期至 (expiry_date) 对于长期有效的证件，填写 '9999-12-31'
-- 4. 主证件 (is_primary) 标识该证件是否为客户的主要身份证件（通常身份证为主证件）
-- 5. 核验状态 (verification_status) 用于跟踪证件的核验流程
-- 6. 证件照片 (identity_front_url/identity_back_url) 存储文件服务器的URL路径
-- 7. 支持多证件管理，一个客户可以有多条证件记录（身份证、护照、军官证等）
-- 8. 外键关联：customer_id 关联 crm_customer 表的 id 字段
-- 9. 索引设计：
--    - idx_customer_id: 根据客户ID快速查询该客户的所有证件
--    - idx_identity_no: 根据证件号码查询（用于查重和实名核验）
--    - idx_identity_type: 根据证件类型统计和查询
--    - idx_tenant_id: 多租户数据隔离
-- 10. 数据权限：通过 tenant_id 实现多租户隔离
-- 11. 软删除：使用 deleted 字段实现逻辑删除，不物理删除历史数据
