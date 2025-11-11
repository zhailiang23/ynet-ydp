-- =============================================
-- 智能练习模块 - 虚拟客户字典项补充
-- =============================================

-- =============================================
-- 1. 补充职业类型字典项 (aicrm_occupation)
-- =============================================

-- manager - 部门经理
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (8, '部门经理', 'manager', 'aicrm_occupation', 0, 'default', '', '企业部门经理', '1', NOW(), '1', NOW(), 0);

-- entrepreneur - 创业者
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (9, '创业者', 'entrepreneur', 'aicrm_occupation', 0, 'default', '', '创业者/企业创始人', '1', NOW(), '1', NOW(), 0);

-- engineer - 工程师
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (10, '工程师', 'engineer', 'aicrm_occupation', 0, 'default', '', '技术工程师', '1', NOW(), '1', NOW(), 0);

-- teacher - 教师
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (11, '教师', 'teacher', 'aicrm_occupation', 0, 'default', '', '教师/教育工作者', '1', NOW(), '1', NOW(), 0);

-- consultant - 咨询顾问
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (12, '咨询顾问', 'consultant', 'aicrm_occupation', 0, 'default', '', '专业咨询顾问', '1', NOW(), '1', NOW(), 0);

-- doctor - 医生
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (13, '医生', 'doctor', 'aicrm_occupation', 0, 'default', '', '医生/医疗工作者', '1', NOW(), '1', NOW(), 0);

-- lawyer - 律师
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (14, '律师', 'lawyer', 'aicrm_occupation', 0, 'default', '', '律师/法律工作者', '1', NOW(), '1', NOW(), 0);

-- sales - 销售
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (15, '销售', 'sales', 'aicrm_occupation', 0, 'default', '', '销售人员', '1', NOW(), '1', NOW(), 0);

-- designer - 设计师
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (16, '设计师', 'designer', 'aicrm_occupation', 0, 'default', '', '设计师', '1', NOW(), '1', NOW(), 0);


-- =============================================
-- 2. 补充行业类型字典项 (aicrm_industry)
-- =============================================

-- creative - 创意/文化
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (11, '创意/文化', 'creative', 'aicrm_industry', 0, 'default', '', '创意、文化、艺术行业', '1', NOW(), '1', NOW(), 0);

-- technology - 科技
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (12, '科技', 'technology', 'aicrm_industry', 0, 'default', '', '科技行业', '1', NOW(), '1', NOW(), 0);

-- legal - 法律
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (13, '法律', 'legal', 'aicrm_industry', 0, 'default', '', '法律服务行业', '1', NOW(), '1', NOW(), 0);


-- =============================================
-- 3. 补充性格类型字典项 (aicrm_personality_type)
-- =============================================

-- analytical - 分析型
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (6, '分析型', 'analytical', 'aicrm_personality_type', 0, 'default', '', '善于分析、注重数据和逻辑', '1', NOW(), '1', NOW(), 0);

-- conservative - 保守型
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (7, '保守型', 'conservative', 'aicrm_personality_type', 0, 'default', '', '谨慎、保守、稳健', '1', NOW(), '1', NOW(), 0);

-- decisive - 果断型
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (8, '果断型', 'decisive', 'aicrm_personality_type', 0, 'default', '', '决策果断、行动迅速', '1', NOW(), '1', NOW(), 0);

-- expressive - 表达型
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (9, '表达型', 'expressive', 'aicrm_personality_type', 0, 'default', '', '善于表达、热情外向', '1', NOW(), '1', NOW(), 0);

-- persuasive - 说服型
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES (10, '说服型', 'persuasive', 'aicrm_personality_type', 0, 'default', '', '善于说服、影响他人', '1', NOW(), '1', NOW(), 0);


-- =============================================
-- 验证查询
-- =============================================

-- 验证职业类型
-- SELECT dict_type, label, value, sort FROM system_dict_data WHERE dict_type = 'aicrm_occupation' ORDER BY sort;

-- 验证行业类型
-- SELECT dict_type, label, value, sort FROM system_dict_data WHERE dict_type = 'aicrm_industry' ORDER BY sort;

-- 验证性格类型
-- SELECT dict_type, label, value, sort FROM system_dict_data WHERE dict_type = 'aicrm_personality_type' ORDER BY sort;
