-- ----------------------------
-- 重命名 AICRM 相关字典类型
-- 将字典类型前缀统一改为 aicrm_，字典名称前加 AICRM 前缀
-- ----------------------------

-- 1. 客户证件类型: customer_identity_type -> aicrm_customer_identity_type
UPDATE system_dict_type
SET type = 'aicrm_customer_identity_type',
    name = 'AICRM 客户证件类型',
    remark = 'AICRM 零售客户证件类型字典',
    updater = '1',
    update_time = NOW()
WHERE type = 'customer_identity_type';

UPDATE system_dict_data
SET dict_type = 'aicrm_customer_identity_type',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'customer_identity_type';

-- 2. 信用等级: crm_credit_level -> aicrm_credit_level
UPDATE system_dict_type
SET type = 'aicrm_credit_level',
    name = 'AICRM 信用等级',
    updater = '1',
    update_time = NOW()
WHERE type = 'crm_credit_level';

UPDATE system_dict_data
SET dict_type = 'aicrm_credit_level',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'crm_credit_level';

-- 3. 客户来源: crm_customer_source -> aicrm_customer_source
UPDATE system_dict_type
SET type = 'aicrm_customer_source',
    name = 'AICRM 客户来源',
    updater = '1',
    update_time = NOW()
WHERE type = 'crm_customer_source';

UPDATE system_dict_data
SET dict_type = 'aicrm_customer_source',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'crm_customer_source';

-- 4. 客户等级: crm_customer_level -> aicrm_customer_level
UPDATE system_dict_type
SET type = 'aicrm_customer_level',
    name = 'AICRM 客户等级',
    updater = '1',
    update_time = NOW()
WHERE type = 'crm_customer_level';

UPDATE system_dict_data
SET dict_type = 'aicrm_customer_level',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'crm_customer_level';

-- 5. 客户状态: crm_customer_status -> aicrm_customer_status
UPDATE system_dict_type
SET type = 'aicrm_customer_status',
    name = 'AICRM 客户状态',
    updater = '1',
    update_time = NOW()
WHERE type = 'crm_customer_status';

UPDATE system_dict_data
SET dict_type = 'aicrm_customer_status',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'crm_customer_status';

-- 6. 客户类型: crm_customer_type -> aicrm_customer_type
UPDATE system_dict_type
SET type = 'aicrm_customer_type',
    name = 'AICRM 客户类型',
    updater = '1',
    update_time = NOW()
WHERE type = 'crm_customer_type';

UPDATE system_dict_data
SET dict_type = 'aicrm_customer_type',
    updater = '1',
    update_time = NOW()
WHERE dict_type = 'crm_customer_type';

-- 验证修改结果
SELECT id, name, type FROM system_dict_type
WHERE type IN (
    'aicrm_customer_identity_type',
    'aicrm_credit_level',
    'aicrm_customer_source',
    'aicrm_customer_level',
    'aicrm_customer_status',
    'aicrm_customer_type'
)
ORDER BY id;
