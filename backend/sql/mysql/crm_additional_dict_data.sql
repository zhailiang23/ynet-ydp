-- CRM零售客户补充字典数据
-- 为居住时长类型和宗教信仰字段创建字典
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 字典类型
-- ============================================

-- 居住时长类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (208, 'CRM居住时长类型', 'crm_residence_duration_type', 0, 'CRM零售客户在当前居住地的居住时长分类', 1, NOW(), 1, NOW(), 0);

-- 宗教信仰
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (209, 'CRM宗教信仰', 'crm_religion', 0, 'CRM零售客户宗教信仰类型', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 2. 字典数据
-- ============================================

-- 居住时长类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_residence_duration_type', '1年以内', '1', 1, '在当前居住地居住不足1年', 1, NOW(), 1, NOW(), 0),
('crm_residence_duration_type', '1-3年', '2', 2, '在当前居住地居住1-3年', 1, NOW(), 1, NOW(), 0),
('crm_residence_duration_type', '3-5年', '3', 3, '在当前居住地居住3-5年', 1, NOW(), 1, NOW(), 0),
('crm_residence_duration_type', '5-10年', '4', 4, '在当前居住地居住5-10年', 1, NOW(), 1, NOW(), 0),
('crm_residence_duration_type', '10年以上', '5', 5, '在当前居住地居住10年以上', 1, NOW(), 1, NOW(), 0);

-- 宗教信仰数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_religion', '无宗教信仰', 'none', 1, '无宗教信仰/无神论', 1, NOW(), 1, NOW(), 0),
('crm_religion', '佛教', 'buddhism', 2, '佛教信仰', 1, NOW(), 1, NOW(), 0),
('crm_religion', '道教', 'taoism', 3, '道教信仰', 1, NOW(), 1, NOW(), 0),
('crm_religion', '伊斯兰教', 'islam', 4, '伊斯兰教信仰', 1, NOW(), 1, NOW(), 0),
('crm_religion', '基督教', 'christianity', 5, '基督教信仰(新教)', 1, NOW(), 1, NOW(), 0),
('crm_religion', '天主教', 'catholicism', 6, '天主教信仰', 1, NOW(), 1, NOW(), 0),
('crm_religion', '其他', 'other', 7, '其他宗教信仰', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 说明
-- ============================================

-- 1. 字典类型ID使用208-209,避免与已有字典冲突
-- 2. 居住时长类型: 用于评估客户居住稳定性,是信贷风险评估的参考指标之一
-- 3. 宗教信仰: 用于完善客户画像,尊重客户宗教信仰,在特定业务场景可能需要考虑
-- 4. 所有字典数据按业务逻辑排序
-- 5. status=0表示启用状态

-- 执行完成后,可以在系统管理->字典管理中查看和管理这些字典
