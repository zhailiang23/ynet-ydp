-- =============================================
-- 虚拟客户相关字典数据
-- =============================================

-- 1. 性别字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM性别', 'aicrm_gender', 0, '客户性别分类', '1', NOW(), '1', NOW(), b'0', NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '男', 'male', 'aicrm_gender', 0, 'primary', '', '男性', '1', NOW(), '1', NOW(), b'0'),
(2, '女', 'female', 'aicrm_gender', 0, 'danger', '', '女性', '1', NOW(), '1', NOW(), b'0');

-- 2. 职业字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM职业类型', 'aicrm_occupation', 0, '客户职业分类', '1', NOW(), '1', NOW(), b'0', NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '企业高管', 'executive', 'aicrm_occupation', 0, 'primary', '', '企业高管', '1', NOW(), '1', NOW(), b'0'),
(2, '企业主', 'business_owner', 'aicrm_occupation', 0, 'success', '', '企业主', '1', NOW(), '1', NOW(), b'0'),
(3, '专业人士', 'professional', 'aicrm_occupation', 0, 'warning', '', '医生、律师、会计师等', '1', NOW(), '1', NOW(), b'0'),
(4, '公务员', 'civil_servant', 'aicrm_occupation', 0, 'info', '', '公务员', '1', NOW(), '1', NOW(), b'0'),
(5, '自由职业者', 'freelancer', 'aicrm_occupation', 0, 'default', '', '自由职业者', '1', NOW(), '1', NOW(), b'0'),
(6, '退休人员', 'retiree', 'aicrm_occupation', 0, 'default', '', '退休人员', '1', NOW(), '1', NOW(), b'0'),
(7, '其他', 'other', 'aicrm_occupation', 0, 'default', '', '其他职业', '1', NOW(), '1', NOW(), b'0');

-- 3. 所属行业字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM所属行业', 'aicrm_industry', 0, '客户所属行业分类', '1', NOW(), '1', NOW(), b'0', NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '金融', 'finance', 'aicrm_industry', 0, 'primary', '', '金融行业', '1', NOW(), '1', NOW(), b'0'),
(2, '制造业', 'manufacturing', 'aicrm_industry', 0, 'success', '', '制造业', '1', NOW(), '1', NOW(), b'0'),
(3, '房地产', 'real_estate', 'aicrm_industry', 0, 'warning', '', '房地产', '1', NOW(), '1', NOW(), b'0'),
(4, '互联网/科技', 'it_tech', 'aicrm_industry', 0, 'info', '', '互联网科技', '1', NOW(), '1', NOW(), b'0'),
(5, '医疗健康', 'healthcare', 'aicrm_industry', 0, 'danger', '', '医疗健康', '1', NOW(), '1', NOW(), b'0'),
(6, '教育培训', 'education', 'aicrm_industry', 0, 'primary', '', '教育培训', '1', NOW(), '1', NOW(), b'0'),
(7, '批发零售', 'retail', 'aicrm_industry', 0, 'success', '', '批发零售', '1', NOW(), '1', NOW(), b'0'),
(8, '服务业', 'service', 'aicrm_industry', 0, 'warning', '', '服务业', '1', NOW(), '1', NOW(), b'0'),
(9, '政府机关', 'government', 'aicrm_industry', 0, 'info', '', '政府机关', '1', NOW(), '1', NOW(), b'0'),
(10, '其他', 'other', 'aicrm_industry', 0, 'default', '', '其他行业', '1', NOW(), '1', NOW(), b'0');

-- 4. 性格类型字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM性格类型', 'aicrm_personality_type', 0, '客户性格类型分类', '1', NOW(), '1', NOW(), b'0', NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '理性型', 'rational', 'aicrm_personality_type', 0, 'primary', '', '理性分析型', '1', NOW(), '1', NOW(), b'0'),
(2, '感性型', 'emotional', 'aicrm_personality_type', 0, 'danger', '', '感性情绪型', '1', NOW(), '1', NOW(), b'0'),
(3, '社交型', 'social', 'aicrm_personality_type', 0, 'success', '', '社交活跃型', '1', NOW(), '1', NOW(), b'0'),
(4, '务实型', 'pragmatic', 'aicrm_personality_type', 0, 'warning', '', '务实稳重型', '1', NOW(), '1', NOW(), b'0'),
(5, '创新型', 'innovative', 'aicrm_personality_type', 0, 'info', '', '创新开放型', '1', NOW(), '1', NOW(), b'0');

-- 5. 风险偏好字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM风险偏好', 'aicrm_risk_preference', 0, '客户风险偏好分类', '1', NOW(), '1', NOW(), b'0', NULL);

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '保守型', 'conservative', 'aicrm_risk_preference', 0, 'success', '', '风险厌恶，偏好稳健投资', '1', NOW(), '1', NOW(), b'0'),
(2, '稳健型', 'moderate', 'aicrm_risk_preference', 0, 'primary', '', '平衡风险与收益', '1', NOW(), '1', NOW(), b'0'),
(3, '平衡型', 'balanced', 'aicrm_risk_preference', 0, 'warning', '', '可接受适度风险', '1', NOW(), '1', NOW(), b'0'),
(4, '成长型', 'growth', 'aicrm_risk_preference', 0, 'info', '', '追求较高收益，可承受较高风险', '1', NOW(), '1', NOW(), b'0'),
(5, '激进型', 'aggressive', 'aicrm_risk_preference', 0, 'danger', '', '追求高收益，可承受高风险', '1', NOW(), '1', NOW(), b'0');
