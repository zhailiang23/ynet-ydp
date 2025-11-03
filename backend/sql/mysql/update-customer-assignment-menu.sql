-- 更新客户归属管理菜单,指向新的归属管理页面

UPDATE system_menu
SET component = 'aicrm/customerassignment/assignment-manage/index',
    updater = '1',
    update_time = NOW()
WHERE id = 5068 AND name = '客户归属管理';

-- 验证更新
SELECT id, name, path, component
FROM system_menu
WHERE id = 5068;
