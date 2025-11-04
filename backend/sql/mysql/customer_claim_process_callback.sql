-- 客户认领流程后置通知配置
-- 配置流程后置通知URL,用于在审批通过后自动分配客户

UPDATE bpm_process_definition_info
SET process_after_trigger_setting = JSON_OBJECT(
    'url', 'http://localhost:48080/admin-api/aicrm/customer-claim/callback/process-end',
    'header', JSON_ARRAY(),
    'body', JSON_ARRAY(),
    'response', JSON_ARRAY()
)
WHERE process_definition_id LIKE 'customer_claim%';

-- 查询验证
SELECT
    id,
    process_definition_id,
    process_after_trigger_setting
FROM bpm_process_definition_info
WHERE process_definition_id LIKE 'customer_claim%';
