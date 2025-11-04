-- 客户认领流程配置脚本
-- 用途: 配置客户认领流程的摘要显示和业务表单路径
-- 场景: 每次重新部署客户认领流程定义后，都需要执行此脚本重新配置

-- 1. 更新所有客户认领流程的摘要配置
-- 用于在"我的流程"页面显示客户名称和申请理由
UPDATE bpm_process_definition_info
SET summary_setting = JSON_OBJECT(
    'enable', true,
    'summary', JSON_ARRAY('customerName:客户名称', 'applyReason:申请理由')
)
WHERE process_definition_id LIKE 'customer_claim%';

-- 2. 更新业务表单路径配置
-- 用于在流程详情页加载业务表单组件,显示客户认领申请详情
UPDATE bpm_process_definition_info
SET form_custom_view_path = '/aicrm/customerclaim/detail'
WHERE process_definition_id LIKE 'customer_claim%';

-- 验证配置是否生效
SELECT
    id,
    process_definition_id,
    form_type,
    form_custom_view_path,
    summary_setting
FROM bpm_process_definition_info
WHERE process_definition_id LIKE 'customer_claim%'
ORDER BY id DESC;
