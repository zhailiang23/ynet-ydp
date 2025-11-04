-- 更新客户归属类型字典,增加托管类型
-- 注意:如果字典已存在则跳过

-- 1. 检查并插入归属类型字典
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted, deleted_time)
SELECT 'AICRM客户归属类型', 'aicrm_customer_assignment_type', 0, '零售和对公客户的归属类型', 'system', NOW(), 'system', NOW(), 0, NULL
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM system_dict_type WHERE type = 'aicrm_customer_assignment_type'
);

-- 2. 获取字典类型ID (假设自增ID)
SET @dict_type_id = (SELECT id FROM system_dict_type WHERE type = 'aicrm_customer_assignment_type' LIMIT 1);

-- 3. 删除旧的字典数据项(如果存在)
DELETE FROM system_dict_data WHERE dict_type = 'aicrm_customer_assignment_type';

-- 4. 插入新的字典数据项(主办/协办/托管)
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, css_class, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '主办', '1', 'aicrm_customer_assignment_type', 0, 'primary', '', '客户主办关系', 'system', NOW(), 'system', NOW(), 0),
(2, '协办', '2', 'aicrm_customer_assignment_type', 0, 'success', '', '客户协办关系', 'system', NOW(), 'system', NOW(), 0),
(3, '托管', '3', 'aicrm_customer_assignment_type', 0, 'warning', '', '客户托管关系', 'system', NOW(), 'system', NOW(), 0);

-- 5. 更新现有的归属记录,将标记为托管的记录修改为托管类型
-- 注意:这是兼容旧数据的迁移,如果已经有assignment_type=3的记录则跳过
UPDATE crm_customer_assignment
SET assignment_type = 3
WHERE is_delegated = 1 AND assignment_type != 3;

-- 6. 显示结果
SELECT '字典数据更新完成' as message;
SELECT * FROM system_dict_data WHERE dict_type = 'aicrm_customer_assignment_type';
