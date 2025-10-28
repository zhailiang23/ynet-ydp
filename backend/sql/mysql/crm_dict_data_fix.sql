-- 清理和修复 CRM 字典数据
-- 问题: crm_customer_level 和 crm_customer_source 有重复数据

-- 1. 删除旧的重复数据
DELETE FROM system_dict_data WHERE dict_type = 'crm_customer_level';
DELETE FROM system_dict_data WHERE dict_type = 'crm_customer_source';

-- 2. 重新插入客户等级字典数据(使用字符串值)
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
  (1, '普通客户', 'normal', 'crm_customer_level', 0, 'default', '普通客户', '1', NOW(), '1', NOW(), 0),
  (2, 'VIP客户', 'vip', 'crm_customer_level', 0, 'success', 'VIP客户', '1', NOW(), '1', NOW(), 0),
  (3, '金卡客户', 'gold', 'crm_customer_level', 0, 'warning', '金卡客户', '1', NOW(), '1', NOW(), 0),
  (4, '钻石卡客户', 'diamond', 'crm_customer_level', 0, 'primary', '钻石卡客户', '1', NOW(), '1', NOW(), 0),
  (5, '战略客户', 'strategic', 'crm_customer_level', 0, 'danger', '战略客户', '1', NOW(), '1', NOW(), 0),
  (6, '重点客户', 'key', 'crm_customer_level', 0, 'info', '重点客户', '1', NOW(), '1', NOW(), 0);

-- 3. 重新插入客户来源字典数据(使用字符串值)
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
  (1, '网点开发', 'branch', 'crm_customer_source', 0, 'default', '网点开发', '1', NOW(), '1', NOW(), 0),
  (2, '电话营销', 'telemarketing', 'crm_customer_source', 0, 'primary', '电话营销', '1', NOW(), '1', NOW(), 0),
  (3, '网络营销', 'online', 'crm_customer_source', 0, 'success', '网络营销', '1', NOW(), '1', NOW(), 0),
  (4, '老客介绍', 'referral', 'crm_customer_source', 0, 'info', '老客介绍', '1', NOW(), '1', NOW(), 0),
  (5, '广告推广', 'advertising', 'crm_customer_source', 0, 'warning', '广告推广', '1', NOW(), '1', NOW(), 0),
  (6, '展会活动', 'exhibition', 'crm_customer_source', 0, 'danger', '展会活动', '1', NOW(), '1', NOW(), 0),
  (7, '合作伙伴', 'partner', 'crm_customer_source', 0, 'default', '合作伙伴', '1', NOW(), '1', NOW(), 0),
  (8, '其他渠道', 'other', 'crm_customer_source', 0, 'default', '其他渠道', '1', NOW(), '1', NOW(), 0);
