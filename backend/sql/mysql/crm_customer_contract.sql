-- ----------------------------
-- 客户签约信息表
-- 设计原则:
-- 1. 签约类型表记录所有签约类型的分类和配置
-- 2. 客户签约信息表记录客户的签约情况
-- 3. 支持零售客户和对公客户
-- 4. 签约类型分为：电子渠道、产品签约、三方及其他 3大类
-- ----------------------------

-- ==================== 签约类型表 ====================
DROP TABLE IF EXISTS `crm_contract_type`;
CREATE TABLE `crm_contract_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '签约类型主键',

  -- ==================== 类型基本信息 ====================
  `type_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型编码',
  `type_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型名称',
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '签约分类（字典: aicrm_contract_category，e_channel=电子渠道，product=产品签约，third_party=三方及其他）',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父类型ID（0表示顶级分类）',

  -- ==================== 类型配置 ====================
  `customer_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'all' COMMENT '适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
  `require_identity` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否需要证件信息（0=不需要，1=需要）',
  `require_account` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否需要账号信息（0=不需要，1=需要）',
  `auto_renew` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否支持自动续签（0=不支持，1=支持）',
  `max_term_days` int NULL DEFAULT NULL COMMENT '最长签约期限（天）',

  -- ==================== 状态信息 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0=停用，1=启用）',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',

  -- ==================== 备注信息 ====================
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签约类型描述',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_type_code`(`type_code` ASC, `deleted` ASC) USING BTREE COMMENT '类型编码唯一索引',
  INDEX `idx_category`(`category` ASC) USING BTREE COMMENT '签约分类索引',
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE COMMENT '父类型ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '签约类型表';

-- ==================== 客户签约信息表 ====================
DROP TABLE IF EXISTS `crm_customer_contract`;
CREATE TABLE `crm_customer_contract` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '签约主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `contract_type_id` bigint NOT NULL COMMENT '签约类型ID（关联 crm_contract_type.id）',

  -- ==================== 图片字段 ====================
  `contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '签约账号/签约编号',
  `contract_date` date NOT NULL COMMENT '签约日期',
  `branch_id` bigint NULL DEFAULT NULL COMMENT '签约机构ID（关联 system_dept.id）',
  `branch_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签约机构名称',
  `contract_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）',
  `contract_situation` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签约情况（具体的签约渠道或产品名称）',
  `identity_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件类型（字典: aicrm_identity_type）',
  `identity_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '证件号码（加密存储）',

  -- ==================== 扩展字段 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联账号',
  `start_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `end_date` date NULL DEFAULT NULL COMMENT '失效日期',
  `sign_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
  `cancel_date` date NULL DEFAULT NULL COMMENT '解约日期',
  `cancel_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '解约原因',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理ID（关联 system_users.id）',

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
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_contract_type_id`(`contract_type_id` ASC) USING BTREE COMMENT '签约类型ID索引',
  INDEX `idx_contract_no`(`contract_no` ASC) USING BTREE COMMENT '签约账号索引',
  INDEX `idx_contract_status`(`contract_status` ASC) USING BTREE COMMENT '签约状态索引',
  INDEX `idx_contract_date`(`contract_date` ASC) USING BTREE COMMENT '签约日期索引',
  INDEX `idx_branch_id`(`branch_id` ASC) USING BTREE COMMENT '签约机构索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户签约信息表（零售+对公共用）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 签约分类字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM签约分类', 'aicrm_contract_category', 0, '签约分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM签约分类';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '电子渠道', 'e_channel', 'aicrm_contract_category', 0, 'primary', '电子渠道类签约', '1', NOW(), '1', NOW(), b'0'),
(2, '产品签约', 'product', 'aicrm_contract_category', 0, 'success', '产品类签约', '1', NOW(), '1', NOW(), b'0'),
(3, '三方及其他', 'third_party', 'aicrm_contract_category', 0, 'info', '三方及其他签约', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 签约状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM签约状态', 'aicrm_contract_status', 0, '签约状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM签约状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '已签约', 'signed', 'aicrm_contract_status', 0, 'success', '签约生效中', '1', NOW(), '1', NOW(), b'0'),
(2, '已解约', 'cancelled', 'aicrm_contract_status', 0, 'warning', '已解除签约', '1', NOW(), '1', NOW(), b'0'),
(3, '已过期', 'expired', 'aicrm_contract_status', 0, 'info', '签约已过期', '1', NOW(), '1', NOW(), b'0'),
(4, '已冻结', 'frozen', 'aicrm_contract_status', 0, 'danger', '签约已冻结', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 签约渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM签约渠道', 'aicrm_sign_channel', 0, '签约渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM签约渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '柜面', 'counter', 'aicrm_sign_channel', 0, 'primary', '柜面签约', '1', NOW(), '1', NOW(), b'0'),
(2, '手机银行', 'mobile', 'aicrm_sign_channel', 0, 'success', '手机银行签约', '1', NOW(), '1', NOW(), b'0'),
(3, '网上银行', 'online', 'aicrm_sign_channel', 0, 'info', '网上银行签约', '1', NOW(), '1', NOW(), b'0'),
(4, '微信', 'wechat', 'aicrm_sign_channel', 0, 'warning', '微信渠道签约', '1', NOW(), '1', NOW(), b'0'),
(5, '其他', 'other', 'aicrm_sign_channel', 0, 'default', '其他渠道签约', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- ==================== 签约类型数据（按图片分类） ====================

-- 一级分类：电子渠道
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('EC', '电子渠道', 'e_channel', 0, 'all', b'0', b'0', 1, 1, '电子渠道类签约', '1', '1', 0);

-- 电子渠道二级类型
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('EC001', '手机银行', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'1', 1, 1, '手机银行签约', '1', '1', 0),
('EC002', '微信银行', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'1', 1, 2, '微信银行签约', '1', '1', 0),
('EC003', '网上银行', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'1', 1, 3, '网上银行签约', '1', '1', 0),
('EC004', '短信签约', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'0', 1, 4, '短信服务签约', '1', '1', 0),
('EC005', '电话银行', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'0', 1, 5, '电话银行签约', '1', '1', 0),
('EC006', '手机号码支付', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'retail', b'1', b'1', 1, 6, '手机号码支付签约', '1', '1', 0),
('EC007', '小额免密支付', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'retail', b'1', b'1', 1, 7, '小额免密支付签约', '1', '1', 0),
('EC008', 'ATM转账', 'e_channel', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'EC') AS tmp), 'all', b'1', b'1', 1, 8, 'ATM转账签约', '1', '1', 0);

-- 一级分类：产品签约
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('PS', '产品签约', 'product', 0, 'all', b'0', b'0', 1, 2, '产品类签约', '1', '1', 0);

-- 产品签约二级类型
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('PS001', '信用卡自动还款签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'retail', b'1', b'1', 1, 1, '信用卡自动还款签约', '1', '1', 0),
('PS002', '基金签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 2, '基金交易签约', '1', '1', 0),
('PS003', '理财签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 3, '理财产品签约', '1', '1', 0),
('PS004', '活期自动定期签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 4, '活期自动转定期签约', '1', '1', 0),
('PS005', '活期自动投资签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 5, '活期自动投资签约', '1', '1', 0),
('PS006', '乐享存产品签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 6, '乐享存产品签约', '1', '1', 0),
('PS007', '网银互联支付签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 7, '网银互联支付签约', '1', '1', 0),
('PS008', '信用卡短信功能签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'retail', b'1', b'0', 1, 8, '信用卡短信功能签约', '1', '1', 0),
('PS009', '信用卡小额免密功能签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'retail', b'1', b'1', 1, 9, '信用卡小额免密功能签约', '1', '1', 0),
('PS010', '信用卡网上支付功能签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'retail', b'1', b'1', 1, 10, '信用卡网上支付功能签约', '1', '1', 0),
('PS011', '基金定投签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 11, '基金定投签约', '1', '1', 0),
('PS012', '无卡支付签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'retail', b'1', b'1', 1, 12, '无卡支付签约', '1', '1', 0),
('PS013', '理宝产品签约', 'product', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'PS') AS tmp), 'all', b'1', b'1', 1, 13, '理宝产品签约', '1', '1', 0);

-- 一级分类：三方及其他
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('TP', '三方及其他', 'third_party', 0, 'all', b'0', b'0', 1, 3, '三方及其他类签约', '1', '1', 0);

-- 三方及其他二级类型
INSERT INTO crm_contract_type (type_code, type_name, category, parent_id, customer_type, require_identity, require_account, status, sort_order, description, creator, updater, tenant_id) VALUES
('TP001', '易付宝签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 1, '易付宝签约', '1', '1', 0),
('TP002', '电力交易代扣签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 2, '电力交易代扣签约', '1', '1', 0),
('TP003', '财付通签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 3, '财付通签约', '1', '1', 0),
('TP004', '基金个人客产签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'retail', b'1', b'1', 1, 4, '基金个人客户产品签约', '1', '1', 0),
('TP005', '翼支付签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 5, '翼支付签约', '1', '1', 0),
('TP006', '三方存管签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 6, '证券三方存管签约', '1', '1', 0),
('TP007', '三方协议', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 7, '三方协议签约', '1', '1', 0),
('TP008', '支付宝签约', 'third_party', (SELECT id FROM (SELECT id FROM crm_contract_type WHERE type_code = 'TP') AS tmp), 'all', b'1', b'1', 1, 8, '支付宝签约', '1', '1', 0);
