-- CRM 模块字典数据初始化脚本
-- 用于初始化客户相关的字典类型和数据项

-- ========== 1. 插入字典类型 (system_dict_type 表) ==========

-- 客户类型
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('客户类型', 'crm_customer_type', 0, 'CRM 客户类型：零售客户、对公客户', '1', NOW(), '1', NOW(), 0);

-- 客户状态
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('客户状态', 'crm_customer_status', 0, 'CRM 客户状态：正常、冻结、注销', '1', NOW(), '1', NOW(), 0);

-- 客户等级
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('客户等级', 'crm_customer_level', 0, 'CRM 客户等级：普通、VIP、金卡、钻石卡等', '1', NOW(), '1', NOW(), 0);

-- 客户来源
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('客户来源', 'crm_customer_source', 0, 'CRM 客户来源渠道', '1', NOW(), '1', NOW(), 0);

-- 信用等级
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES ('信用等级', 'crm_credit_level', 0, 'CRM 客户信用等级：AAA、AA、A、BBB、BB、B、C', '1', NOW(), '1', NOW(), 0);

-- ========== 2. 插入字典数据项 (system_dict_data 表) ==========

-- 客户类型数据项
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '零售客户', '1', 'crm_customer_type', 0, 'primary', '个人客户', '1', NOW(), '1', NOW(), 0),
(2, '对公客户', '2', 'crm_customer_type', 0, 'success', '企业客户', '1', NOW(), '1', NOW(), 0);

-- 客户状态数据项
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '正常', '1', 'crm_customer_status', 0, 'success', '客户状态正常', '1', NOW(), '1', NOW(), 0),
(2, '冻结', '2', 'crm_customer_status', 0, 'warning', '客户已冻结', '1', NOW(), '1', NOW(), 0),
(3, '注销', '3', 'crm_customer_status', 0, 'danger', '客户已注销', '1', NOW(), '1', NOW(), 0);

-- 客户等级数据项（零售客户等级）
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '普通客户', 'normal', 'crm_customer_level', 0, 'default', '普通客户', '1', NOW(), '1', NOW(), 0),
(2, 'VIP客户', 'vip', 'crm_customer_level', 0, 'primary', 'VIP客户', '1', NOW(), '1', NOW(), 0),
(3, '金卡客户', 'gold', 'crm_customer_level', 0, 'warning', '金卡客户', '1', NOW(), '1', NOW(), 0),
(4, '钻石卡客户', 'diamond', 'crm_customer_level', 0, 'danger', '钻石卡客户', '1', NOW(), '1', NOW(), 0),
(5, '战略客户', 'strategic', 'crm_customer_level', 0, 'success', '战略客户（对公）', '1', NOW(), '1', NOW(), 0),
(6, '重点客户', 'key', 'crm_customer_level', 0, 'primary', '重点客户（对公）', '1', NOW(), '1', NOW(), 0);

-- 客户来源数据项
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, '网点开发', 'branch', 'crm_customer_source', 0, 'primary', '通过网点开发获取', '1', NOW(), '1', NOW(), 0),
(2, '电话营销', 'telemarketing', 'crm_customer_source', 0, 'success', '通过电话营销获取', '1', NOW(), '1', NOW(), 0),
(3, '网络营销', 'online', 'crm_customer_source', 0, 'warning', '通过网络营销获取', '1', NOW(), '1', NOW(), 0),
(4, '老客介绍', 'referral', 'crm_customer_source', 0, 'danger', '老客户介绍', '1', NOW(), '1', NOW(), 0),
(5, '广告推广', 'advertising', 'crm_customer_source', 0, 'info', '广告推广获取', '1', NOW(), '1', NOW(), 0),
(6, '展会活动', 'exhibition', 'crm_customer_source', 0, 'default', '展会活动获取', '1', NOW(), '1', NOW(), 0),
(7, '合作伙伴', 'partner', 'crm_customer_source', 0, 'primary', '合作伙伴推荐', '1', NOW(), '1', NOW(), 0),
(8, '其他渠道', 'other', 'crm_customer_source', 0, 'default', '其他渠道', '1', NOW(), '1', NOW(), 0);

-- 信用等级数据项
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(1, 'AAA级', 'AAA', 'crm_credit_level', 0, 'success', '信用极好', '1', NOW(), '1', NOW(), 0),
(2, 'AA级', 'AA', 'crm_credit_level', 0, 'success', '信用优良', '1', NOW(), '1', NOW(), 0),
(3, 'A级', 'A', 'crm_credit_level', 0, 'primary', '信用良好', '1', NOW(), '1', NOW(), 0),
(4, 'BBB级', 'BBB', 'crm_credit_level', 0, 'primary', '信用较好', '1', NOW(), '1', NOW(), 0),
(5, 'BB级', 'BB', 'crm_credit_level', 0, 'warning', '信用一般', '1', NOW(), '1', NOW(), 0),
(6, 'B级', 'B', 'crm_credit_level', 0, 'warning', '信用较差', '1', NOW(), '1', NOW(), 0),
(7, 'C级', 'C', 'crm_credit_level', 0, 'danger', '信用差', '1', NOW(), '1', NOW(), 0);
