-- 培训文件类型字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
VALUES ('AICRM培训文件类型', 'aicrm_practice_material_file_type', 0, '智能陪练培训文件的类型分类', '1', NOW(), '1', NOW(), b'0', NULL);

-- 获取刚插入的字典类型ID
SET @dict_type_id = LAST_INSERT_ID();

-- 培训文件类型字典项
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES 
(1, '培训手册', 'training_manual', 'aicrm_practice_material_file_type', 0, 'primary', '', '培训手册文件', '1', NOW(), '1', NOW(), b'0'),
(2, '规则规范', 'rules_and_regulations', 'aicrm_practice_material_file_type', 0, 'success', '', '规则规范文件', '1', NOW(), '1', NOW(), b'0'),
(3, '产品信息', 'product_information', 'aicrm_practice_material_file_type', 0, 'warning', '', '产品信息文件', '1', NOW(), '1', NOW(), b'0');
