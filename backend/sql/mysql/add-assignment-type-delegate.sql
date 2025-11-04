-- ========================================
-- 添加客户归属类型 - 托管
-- 将归属类型从 主办/协办 扩展为 主办/协办/托管
-- ========================================

-- ----------------------------
-- 1. 创建归属类型字典
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
SELECT NULL, 'AICRM客户归属类型', 'aicrm_assignment_type', 0, '客户归属关系类型', '1', NOW(), '1', NOW(), b'0'
WHERE NOT EXISTS (SELECT 1 FROM system_dict_type WHERE type = 'aicrm_assignment_type');

-- ----------------------------
-- 2. 添加字典数据项
-- ----------------------------
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted)
SELECT * FROM (
    SELECT 1 as sort, '主办' as label, '1' as value, 'aicrm_assignment_type' as dict_type, 0 as status, 'primary' as color_type, '主要负责的客户经理' as remark, '1' as creator, NOW() as create_time, '1' as updater, NOW() as update_time, b'0' as deleted
    UNION ALL
    SELECT 2, '协办', '2', 'aicrm_assignment_type', 0, 'success', '协助服务的客户经理', '1', NOW(), '1', NOW(), b'0'
    UNION ALL
    SELECT 3, '托管', '3', 'aicrm_assignment_type', 0, 'warning', '临时托管的客户经理', '1', NOW(), '1', NOW(), b'0'
) as tmp
WHERE NOT EXISTS (SELECT 1 FROM system_dict_data WHERE dict_type = 'aicrm_assignment_type' AND value = tmp.value);
