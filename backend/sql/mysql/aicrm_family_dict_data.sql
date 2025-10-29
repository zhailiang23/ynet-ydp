-- AICRM家庭信息字典数据
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 字典类型
-- ============================================

-- 家庭关系
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (300, 'AICRM家庭关系', 'aicrm_family_relation', 0, 'AICRM零售客户家庭成员关系', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM家庭关系', remark='AICRM零售客户家庭成员关系';

-- 学历
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (301, 'AICRM学历', 'aicrm_education_level', 0, 'AICRM零售客户学历', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM学历', remark='AICRM零售客户学历';

-- 家庭实力
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (302, 'AICRM家庭实力', 'aicrm_family_strength', 0, 'AICRM家庭综合实力评估', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM家庭实力', remark='AICRM家庭综合实力评估';

-- 家庭年收入范围
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (303, 'AICRM家庭年收入范围', 'aicrm_family_income_scope', 0, 'AICRM家庭年收入范围', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM家庭年收入范围', remark='AICRM家庭年收入范围';

-- 家庭年支出范围
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (304, 'AICRM家庭年支出范围', 'aicrm_family_expenditure_scope', 0, 'AICRM家庭年支出范围', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM家庭年支出范围', remark='AICRM家庭年支出范围';

-- 收入来源
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (305, 'AICRM收入来源', 'aicrm_income_source', 0, 'AICRM主要收入来源', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM收入来源', remark='AICRM主要收入来源';

-- 居住状况
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (306, 'AICRM居住状况', 'aicrm_residence_status', 0, 'AICRM居住状况', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM居住状况', remark='AICRM居住状况';

-- 房屋状况
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (307, 'AICRM房屋状况', 'aicrm_house_status', 0, 'AICRM房屋状况', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM房屋状况', remark='AICRM房屋状况';

-- 负债范围
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (308, 'AICRM负债范围', 'aicrm_debt_scope', 0, 'AICRM家庭负债范围', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM负债范围', remark='AICRM家庭负债范围';

-- 负债状况
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (309, 'AICRM负债状况', 'aicrm_debt_status', 0, 'AICRM负债状况评估', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM负债状况', remark='AICRM负债状况评估';

-- 信用状况
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (310, 'AICRM信用状况', 'aicrm_credit_status', 0, 'AICRM信用状况评估', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE name='AICRM信用状况', remark='AICRM信用状况评估';

-- ============================================
-- 2. 字典数据
-- ============================================

-- 家庭关系数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_family_relation', '配偶', 'spouse', 1, '丈夫或妻子', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '父亲', 'father', 2, '父亲', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '母亲', 'mother', 3, '母亲', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '子女', 'child', 4, '儿子或女儿', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '兄弟', 'brother', 5, '兄弟', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '姐妹', 'sister', 6, '姐妹', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '祖父母', 'grandparent', 7, '祖父或祖母', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '孙子女', 'grandchild', 8, '孙子或孙女', 1, NOW(), 1, NOW(), 0),
('aicrm_family_relation', '其他', 'other', 9, '其他关系', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 学历数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_education_level', '小学', 'primary', 1, '小学学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '初中', 'junior_high', 2, '初中学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '高中/中专', 'high_school', 3, '高中或中专学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '大专', 'associate', 4, '大专学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '本科', 'bachelor', 5, '本科学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '硕士', 'master', 6, '硕士研究生学历', 1, NOW(), 1, NOW(), 0),
('aicrm_education_level', '博士', 'phd', 7, '博士研究生学历', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 家庭实力数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_family_strength', '强', 'strong', 1, '家庭实力强', 1, NOW(), 1, NOW(), 0),
('aicrm_family_strength', '中', 'medium', 2, '家庭实力中等', 1, NOW(), 1, NOW(), 0),
('aicrm_family_strength', '弱', 'weak', 3, '家庭实力弱', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 家庭年收入范围数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_family_income_scope', '5万以下', 'below_50k', 1, '年收入5万元以下', 1, NOW(), 1, NOW(), 0),
('aicrm_family_income_scope', '5-10万', '50k_100k', 2, '年收入5-10万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_income_scope', '10-20万', '100k_200k', 3, '年收入10-20万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_income_scope', '20-50万', '200k_500k', 4, '年收入20-50万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_income_scope', '50-100万', '500k_1m', 5, '年收入50-100万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_income_scope', '100万以上', 'above_1m', 6, '年收入100万元以上', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 家庭年支出范围数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_family_expenditure_scope', '5万以下', 'below_50k', 1, '年支出5万元以下', 1, NOW(), 1, NOW(), 0),
('aicrm_family_expenditure_scope', '5-10万', '50k_100k', 2, '年支出5-10万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_expenditure_scope', '10-20万', '100k_200k', 3, '年支出10-20万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_expenditure_scope', '20-50万', '200k_500k', 4, '年支出20-50万元', 1, NOW(), 1, NOW(), 0),
('aicrm_family_expenditure_scope', '50万以上', 'above_500k', 5, '年支出50万元以上', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 收入来源数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_income_source', '工资收入', 'salary', 1, '工资性收入', 1, NOW(), 1, NOW(), 0),
('aicrm_income_source', '经营收入', 'business', 2, '经营性收入', 1, NOW(), 1, NOW(), 0),
('aicrm_income_source', '投资收入', 'investment', 3, '投资性收入', 1, NOW(), 1, NOW(), 0),
('aicrm_income_source', '财产收入', 'property', 4, '财产性收入', 1, NOW(), 1, NOW(), 0),
('aicrm_income_source', '转移收入', 'transfer', 5, '转移性收入', 1, NOW(), 1, NOW(), 0),
('aicrm_income_source', '其他', 'other', 6, '其他收入', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 居住状况数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_residence_status', '自有住房', 'owned', 1, '拥有自己的住房', 1, NOW(), 1, NOW(), 0),
('aicrm_residence_status', '租赁住房', 'rented', 2, '租赁他人住房', 1, NOW(), 1, NOW(), 0),
('aicrm_residence_status', '与父母同住', 'with_parents', 3, '与父母同住', 1, NOW(), 1, NOW(), 0),
('aicrm_residence_status', '宿舍', 'dormitory', 4, '单位或学校宿舍', 1, NOW(), 1, NOW(), 0),
('aicrm_residence_status', '其他', 'other', 5, '其他居住状况', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 房屋状况数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_house_status', '商品房', 'commercial', 1, '商品住宅', 1, NOW(), 1, NOW(), 0),
('aicrm_house_status', '经济适用房', 'affordable', 2, '经济适用住房', 1, NOW(), 1, NOW(), 0),
('aicrm_house_status', '公租房', 'public_rental', 3, '公共租赁住房', 1, NOW(), 1, NOW(), 0),
('aicrm_house_status', '自建房', 'self_built', 4, '自建住房', 1, NOW(), 1, NOW(), 0),
('aicrm_house_status', '福利房', 'welfare', 5, '单位福利分房', 1, NOW(), 1, NOW(), 0),
('aicrm_house_status', '其他', 'other', 6, '其他房屋类型', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 负债范围数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_debt_scope', '无负债', 'none', 1, '无负债', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_scope', '10万以下', 'below_100k', 2, '负债10万元以下', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_scope', '10-50万', '100k_500k', 3, '负债10-50万元', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_scope', '50-100万', '500k_1m', 4, '负债50-100万元', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_scope', '100万以上', 'above_1m', 5, '负债100万元以上', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 负债状况数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_debt_status', '无负债', 'none', 1, '无负债', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_status', '负债较低', 'low', 2, '负债率较低,在可控范围', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_status', '负债适中', 'medium', 3, '负债率适中', 1, NOW(), 1, NOW(), 0),
('aicrm_debt_status', '负债较高', 'high', 4, '负债率较高,需注意', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);

-- 信用状况数据
INSERT INTO system_dict_data (dict_type, label, value, sort, remark, creator, create_time, updater, update_time, deleted) VALUES
('aicrm_credit_status', '优秀', 'excellent', 1, '信用记录优秀', 1, NOW(), 1, NOW(), 0),
('aicrm_credit_status', '良好', 'good', 2, '信用记录良好', 1, NOW(), 1, NOW(), 0),
('aicrm_credit_status', '一般', 'fair', 3, '信用记录一般', 1, NOW(), 1, NOW(), 0),
('aicrm_credit_status', '较差', 'poor', 4, '信用记录较差', 1, NOW(), 1, NOW(), 0),
('aicrm_credit_status', '无记录', 'no_record', 5, '无信用记录', 1, NOW(), 1, NOW(), 0)
ON DUPLICATE KEY UPDATE label=VALUES(label), sort=VALUES(sort);
