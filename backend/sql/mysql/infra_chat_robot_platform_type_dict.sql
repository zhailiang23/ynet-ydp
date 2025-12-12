-- 对话机器人平台类型字典
-- 字典类型
INSERT INTO `system_dict_type` (`id`, `name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES (NULL, '对话机器人平台类型', 'infra_chat_robot_platform_type', 0, '对话机器人对接的平台类型', '1', NOW(), '1', NOW(), b'0', NULL);

-- 字典数据
INSERT INTO `system_dict_data` (`id`, `sort`, `label`, `value`, `dict_type`, `status`, `color_type`, `css_class`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`)
VALUES
(NULL, 1, '钉钉', 'dingtalk', 'infra_chat_robot_platform_type', 0, 'primary', '', '钉钉平台', '1', NOW(), '1', NOW(), b'0'),
(NULL, 2, '企业微信', 'wework', 'infra_chat_robot_platform_type', 0, 'success', '', '企业微信平台', '1', NOW(), '1', NOW(), b'0');
