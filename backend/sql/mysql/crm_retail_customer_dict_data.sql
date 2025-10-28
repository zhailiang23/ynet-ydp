-- CRM零售客户新增字段的字典数据
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 字典类型
-- ============================================

-- 人员称谓
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (200, 'CRM人员称谓', 'crm_person_title', 0, 'CRM零售客户人员称谓', 1, NOW(), 1, NOW(), 0);

-- 户口类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (201, 'CRM户口类型', 'crm_household_type', 0, 'CRM零售客户户口类型', 1, NOW(), 1, NOW(), 0);

-- 政治面貌
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (202, 'CRM政治面貌', 'crm_political_status', 0, 'CRM零售客户政治面貌', 1, NOW(), 1, NOW(), 0);

-- 健康状况
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (203, 'CRM健康状况', 'crm_health_status', 0, 'CRM零售客户健康状况', 1, NOW(), 1, NOW(), 0);

-- 职业状态
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (204, 'CRM职业状态', 'crm_career_status', 0, 'CRM零售客户职业状态', 1, NOW(), 1, NOW(), 0);

-- 单位性质
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (205, 'CRM单位性质', 'crm_employer_type', 0, 'CRM零售客户单位性质', 1, NOW(), 1, NOW(), 0);

-- 投资性质
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (206, 'CRM投资性质', 'crm_investment_nature', 0, 'CRM零售客户投资性质', 1, NOW(), 1, NOW(), 0);

-- 登记类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (207, 'CRM登记类型', 'crm_registration_type', 0, 'CRM零售客户登记类型', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 2. 字典数据
-- ============================================

-- 人员称谓数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_person_title', '先生', 'mr', 1, '男性称谓', 1, NOW(), 1, NOW(), 0),
('crm_person_title', '女士', 'ms', 2, '已婚或未知女性称谓', 1, NOW(), 1, NOW(), 0),
('crm_person_title', '小姐', 'miss', 3, '未婚女性称谓', 1, NOW(), 1, NOW(), 0),
('crm_person_title', '博士', 'dr', 4, '拥有博士学位者', 1, NOW(), 1, NOW(), 0);

-- 户口类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_household_type', '农业户口', 'agricultural', 1, '农业户籍', 1, NOW(), 1, NOW(), 0),
('crm_household_type', '非农业户口', 'non_agricultural', 2, '非农业户籍/城镇户籍', 1, NOW(), 1, NOW(), 0);

-- 政治面貌数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_political_status', '中共党员', 'party_member', 1, '中国共产党党员', 1, NOW(), 1, NOW(), 0),
('crm_political_status', '共青团员', 'league_member', 2, '中国共产主义青年团团员', 1, NOW(), 1, NOW(), 0),
('crm_political_status', '民主党派', 'democratic_party', 3, '民主党派成员', 1, NOW(), 1, NOW(), 0),
('crm_political_status', '群众', 'mass', 4, '无党派群众', 1, NOW(), 1, NOW(), 0);

-- 健康状况数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_health_status', '健康', 'healthy', 1, '身体健康', 1, NOW(), 1, NOW(), 0),
('crm_health_status', '良好', 'good', 2, '身体良好', 1, NOW(), 1, NOW(), 0),
('crm_health_status', '一般', 'general', 3, '身体一般', 1, NOW(), 1, NOW(), 0),
('crm_health_status', '较差', 'poor', 4, '身体较差', 1, NOW(), 1, NOW(), 0);

-- 职业状态数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_career_status', '在职', 'employed', 1, '在职工作中', 1, NOW(), 1, NOW(), 0),
('crm_career_status', '离职', 'unemployed', 2, '已离职', 1, NOW(), 1, NOW(), 0),
('crm_career_status', '退休', 'retired', 3, '已退休', 1, NOW(), 1, NOW(), 0),
('crm_career_status', '自由职业', 'freelance', 4, '自由职业者', 1, NOW(), 1, NOW(), 0);

-- 单位性质数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_employer_type', '国有企业', 'state_owned', 1, '国有企业', 1, NOW(), 1, NOW(), 0),
('crm_employer_type', '私营企业', 'private', 2, '私营/民营企业', 1, NOW(), 1, NOW(), 0),
('crm_employer_type', '外资企业', 'foreign', 3, '外商独资企业', 1, NOW(), 1, NOW(), 0),
('crm_employer_type', '合资企业', 'joint_venture', 4, '中外合资企业', 1, NOW(), 1, NOW(), 0),
('crm_employer_type', '政府机关', 'government', 5, '政府机关单位', 1, NOW(), 1, NOW(), 0),
('crm_employer_type', '事业单位', 'public_institution', 6, '事业单位', 1, NOW(), 1, NOW(), 0);

-- 投资性质数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_investment_nature', '保守型', 'conservative', 1, '风险承受能力低', 1, NOW(), 1, NOW(), 0),
('crm_investment_nature', '稳健型', 'moderate', 2, '风险承受能力中等', 1, NOW(), 1, NOW(), 0),
('crm_investment_nature', '进取型', 'aggressive', 3, '风险承受能力较高', 1, NOW(), 1, NOW(), 0),
('crm_investment_nature', '激进型', 'radical', 4, '风险承受能力很高', 1, NOW(), 1, NOW(), 0);

-- 登记类型数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('crm_registration_type', '初次登记', 'initial', 1, '初次登记注册', 1, NOW(), 1, NOW(), 0),
('crm_registration_type', '变更登记', 'change', 2, '信息变更登记', 1, NOW(), 1, NOW(), 0),
('crm_registration_type', '注销登记', 'cancellation', 3, '注销登记', 1, NOW(), 1, NOW(), 0);

-- ============================================
-- 说明
-- ============================================

-- 1. 字典类型ID从200开始,避免与系统已有字典冲突
-- 2. 所有字典状态为启用 (status=0)
-- 3. 字典数据按照业务逻辑排序
-- 4. creator和updater都设为1 (系统管理员)
-- 5. deleted设为0 (未删除)

-- 执行完成后,可以在系统管理->字典管理中查看和管理这些字典
