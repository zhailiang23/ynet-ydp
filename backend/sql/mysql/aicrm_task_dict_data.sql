-- ============================================================
-- AICRM任务相关字典数据
-- 创建时间: 2026-01-08
-- 说明: 为任务管理模块添加任务类型和业务价值字典
-- ============================================================

-- 1. 创建任务类型字典类型
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, 'AICRM任务类型', 'aicrm_task_type', 0, 'AI CRM系统任务类型字典', '1', NOW(), '1', NOW(), b'0', NULL)
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`), `remark` = VALUES(`remark`), `updater` = '1', `update_time` = NOW();

-- 2. 创建任务类型字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '资产提升', 'ASSET_UPGRADE', 'aicrm_task_type', 0, 'success', '资产提升类任务', '1', NOW(), '1', NOW(), b'0'),
(2, '到期提醒', 'EXPIRE_REMINDER', 'aicrm_task_type', 0, 'warning', '到期提醒类任务', '1', NOW(), '1', NOW(), b'0'),
(3, '合规', 'COMPLIANCE', 'aicrm_task_type', 0, 'danger', '合规类任务', '1', NOW(), '1', NOW(), b'0'),
(4, '会议', 'MEETING', 'aicrm_task_type', 0, 'primary', '会议类任务', '1', NOW(), '1', NOW(), b'0'),
(5, '行政', 'ADMINISTRATIVE', 'aicrm_task_type', 0, 'default', '行政类任务', '1', NOW(), '1', NOW(), b'0'),
(6, '营销推广', 'MARKETING', 'aicrm_task_type', 0, 'warning', '营销推广类任务（由营销活动任务下发模块创建）', '1', NOW(), '1', NOW(), b'0'),
(7, '批量任务', 'BATCH', 'aicrm_task_type', 0, 'info', '批量创建的任务', '1', NOW(), '1', NOW(), b'0'),
(8, '客户跟进', 'FOLLOW_UP', 'aicrm_task_type', 0, 'primary', '客户跟进类任务', '1', NOW(), '1', NOW(), b'0'),
(99, '其他', 'OTHER', 'aicrm_task_type', 0, 'default', '其他类型任务', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
    `label` = VALUES(`label`),
    `color_type` = VALUES(`color_type`),
    `remark` = VALUES(`remark`),
    `updater` = '1',
    `update_time` = NOW();

-- 3. 创建业务价值字典类型
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, 'AICRM任务业务价值', 'aicrm_task_business_value', 0, 'AI CRM系统任务业务价值等级', '1', NOW(), '1', NOW(), b'0', NULL)
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`), `remark` = VALUES(`remark`), `updater` = '1', `update_time` = NOW();

-- 4. 创建业务价值字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '高', 'HIGH', 'aicrm_task_business_value', 0, 'danger', '高业务价值', '1', NOW(), '1', NOW(), b'0'),
(2, '中', 'MEDIUM', 'aicrm_task_business_value', 0, 'warning', '中等业务价值', '1', NOW(), '1', NOW(), b'0'),
(3, '低', 'LOW', 'aicrm_task_business_value', 0, 'default', '低业务价值', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
    `label` = VALUES(`label`),
    `color_type` = VALUES(`color_type`),
    `remark` = VALUES(`remark`),
    `updater` = '1',
    `update_time` = NOW();

-- 5. 创建任务优先级字典类型（如果不存在）
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, 'AICRM任务优先级', 'aicrm_task_priority', 0, 'AI CRM系统任务优先级', '1', NOW(), '1', NOW(), b'0', NULL)
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`), `remark` = VALUES(`remark`), `updater` = '1', `update_time` = NOW();

-- 6. 创建任务优先级字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, 'P0-紧急', 'P0', 'aicrm_task_priority', 0, 'danger', '紧急优先级（最高）', '1', NOW(), '1', NOW(), b'0'),
(2, 'P1-重要', 'P1', 'aicrm_task_priority', 0, 'warning', '重要优先级', '1', NOW(), '1', NOW(), b'0'),
(3, 'P2-普通', 'P2', 'aicrm_task_priority', 0, 'primary', '普通优先级', '1', NOW(), '1', NOW(), b'0'),
(4, 'P3-低', 'P3', 'aicrm_task_priority', 0, 'default', '低优先级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
    `label` = VALUES(`label`),
    `color_type` = VALUES(`color_type`),
    `remark` = VALUES(`remark`),
    `updater` = '1',
    `update_time` = NOW();

-- 7. 创建任务状态字典类型（如果不存在）
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, 'AICRM任务状态', 'aicrm_task_status', 0, 'AI CRM系统任务状态', '1', NOW(), '1', NOW(), b'0', NULL)
ON DUPLICATE KEY UPDATE `name` = VALUES(`name`), `remark` = VALUES(`remark`), `updater` = '1', `update_time` = NOW();

-- 8. 创建任务状态字典数据
INSERT INTO `system_dict_data` (`sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
(1, '待办', '0', 'aicrm_task_status', 0, 'warning', '待办状态', '1', NOW(), '1', NOW(), b'0'),
(2, '进行中', '1', 'aicrm_task_status', 0, 'primary', '进行中状态', '1', NOW(), '1', NOW(), b'0'),
(3, '已完成', '2', 'aicrm_task_status', 0, 'success', '已完成状态', '1', NOW(), '1', NOW(), b'0'),
(4, '已取消', '3', 'aicrm_task_status', 0, 'default', '已取消状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE
    `label` = VALUES(`label`),
    `color_type` = VALUES(`color_type`),
    `remark` = VALUES(`remark`),
    `updater` = '1',
    `update_time` = NOW();
