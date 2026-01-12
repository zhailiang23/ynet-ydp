-- =============================================
-- 任务动态评分功能测试数据
-- 用途: 验证任务评分因子、条件配置和动态评分功能
-- =============================================

-- 1. 清理旧测试数据
DELETE FROM aicrm_task_scoring_condition WHERE factor_id IN (
    SELECT id FROM aicrm_task_scoring_factor WHERE factor_name LIKE 'TEST_%'
);
DELETE FROM aicrm_task_scoring_factor WHERE factor_name LIKE 'TEST_%';
DELETE FROM aicrm_task WHERE title LIKE 'TEST_%';
DELETE FROM crm_customer WHERE customer_name LIKE 'TEST_%';

-- 2. 创建测试客户（不同等级和信用评分）
INSERT INTO crm_customer (
    customer_no, customer_name, customer_type, customer_level, credit_score, credit_level,
    customer_status, assignment_status, is_valid,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 高价值 VIP 客户（开户400天）
('TEST001', 'TEST_VIP客户-张三', 1, 'VIP', 150000.00, 'AAA',
 1, 0, 1,
 '1', DATE_SUB(NOW(), INTERVAL 400 DAY), '1', NOW(), 0, 1),

-- 中等价值金卡客户（开户200天）
('TEST002', 'TEST_金卡客户-李四', 1, 'GOLD', 80000.00, 'AA',
 1, 0, 1,
 '1', DATE_SUB(NOW(), INTERVAL 200 DAY), '1', NOW(), 0, 1),

-- 普通银卡客户（开户100天）
('TEST003', 'TEST_银卡客户-王五', 1, 'SILVER', 50000.00, 'A',
 1, 0, 1,
 '1', DATE_SUB(NOW(), INTERVAL 100 DAY), '1', NOW(), 0, 1),

-- 新注册普通客户（开户30天）
('TEST004', 'TEST_普通客户-赵六', 1, 'NORMAL', 20000.00, 'BBB',
 1, 0, 1,
 '1', DATE_SUB(NOW(), INTERVAL 30 DAY), '1', NOW(), 0, 1),

-- 低信用客户（开户10天）
('TEST005', 'TEST_低信用客户-孙七', 1, 'NORMAL', 5000.00, 'C',
 1, 0, 1,
 '1', DATE_SUB(NOW(), INTERVAL 10 DAY), '1', NOW(), 0, 1);

-- 3. 创建评分因子 - VIP客户加分
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    logic_type, impact_type, score_adjustment,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_VIP客户加分', 'VIP Customer Bonus', 'star', 100,
    '客户等级为VIP时，任务评分+20分',
    1, 100,
    'AND', 'score', 20,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 获取刚插入的因子ID
SET @factor_vip_id = LAST_INSERT_ID();

-- 添加条件：客户等级 = VIP
INSERT INTO aicrm_task_scoring_condition (
    factor_id, data_source, field_name, operator, field_value, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    @factor_vip_id, 'customer_profile', 'customer_level', 'EQUAL', 'VIP', 1,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 4. 创建评分因子 - 高信用评分加分
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    logic_type, impact_type, score_adjustment,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_高信用评分加分', 'High Credit Score Bonus', 'trending_up', 100,
    '信用评分>100000时，任务评分+15分',
    1, 101,
    'AND', 'score', 15,
    '1', NOW(), '1', NOW(), 0, 1
);

SET @factor_high_credit_id = LAST_INSERT_ID();

-- 添加条件：信用评分 > 100000
INSERT INTO aicrm_task_scoring_condition (
    factor_id, data_source, field_name, operator, field_value, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    @factor_high_credit_id, 'customer_profile', 'account_balance', 'GREATER_THAN', '100000', 1,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 5. 创建评分因子 - 老客户加分
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    logic_type, impact_type, score_adjustment,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_老客户加分', 'Long-term Customer Bonus', 'schedule', 100,
    '开户时长>365天时，任务评分+10分',
    1, 102,
    'AND', 'score', 10,
    '1', NOW(), '1', NOW(), 0, 1
);

SET @factor_old_customer_id = LAST_INSERT_ID();

-- 添加条件：开户时长 > 365 天
INSERT INTO aicrm_task_scoring_condition (
    factor_id, data_source, field_name, operator, field_value, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    @factor_old_customer_id, 'customer_profile', 'account_age_days', 'GREATER_THAN', '365', 1,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 6. 创建评分因子 - 高优先级任务加分
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    logic_type, impact_type, score_adjustment,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_高优先级加分', 'High Priority Bonus', 'priority_high', 100,
    '任务优先级为HIGH时，任务评分+30分',
    1, 103,
    'AND', 'score', 30,
    '1', NOW(), '1', NOW(), 0, 1
);

SET @factor_high_priority_id = LAST_INSERT_ID();

-- 添加条件：任务优先级 = HIGH
INSERT INTO aicrm_task_scoring_condition (
    factor_id, data_source, field_name, operator, field_value, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    @factor_high_priority_id, 'task_attribute', 'task_priority', 'EQUAL', 'HIGH', 1,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 7. 创建评分因子 - 紧急截止日期加分
INSERT INTO aicrm_task_scoring_factor (
    factor_name, factor_name_en, icon, weight, description, enabled, sort,
    logic_type, impact_type, score_adjustment,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_紧急截止加分', 'Urgent Deadline Bonus', 'alarm', 100,
    '截止日期<=3天时，任务评分+25分',
    1, 104,
    'AND', 'score', 25,
    '1', NOW(), '1', NOW(), 0, 1
);

SET @factor_urgent_deadline_id = LAST_INSERT_ID();

-- 添加条件：截止天数 <= 3
INSERT INTO aicrm_task_scoring_condition (
    factor_id, data_source, field_name, operator, field_value, sort,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    @factor_urgent_deadline_id, 'task_attribute', 'deadline_days', 'LESS_EQUAL', '3', 1,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 8. 创建测试任务
-- 获取测试客户的 ID
SET @customer_vip_id = (SELECT id FROM crm_customer WHERE customer_name = 'TEST_VIP客户-张三' AND deleted = 0 LIMIT 1);
SET @customer_gold_id = (SELECT id FROM crm_customer WHERE customer_name = 'TEST_金卡客户-李四' AND deleted = 0 LIMIT 1);
SET @customer_silver_id = (SELECT id FROM crm_customer WHERE customer_name = 'TEST_银卡客户-王五' AND deleted = 0 LIMIT 1);
SET @customer_normal_id = (SELECT id FROM crm_customer WHERE customer_name = 'TEST_普通客户-赵六' AND deleted = 0 LIMIT 1);
SET @customer_low_credit_id = (SELECT id FROM crm_customer WHERE customer_name = 'TEST_低信用客户-孙七' AND deleted = 0 LIMIT 1);

-- 任务1: VIP客户 + 高信用 + 老客户 + 高优先级（应得最高分：基础50 + 20 + 15 + 10 + 30 = 125）
INSERT INTO aicrm_task (
    title, task_type, status, priority, customer_id,
    responsible_user_id, deadline, comprehensive_score,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_任务1-VIP高优先级', 'CUSTOMER_VISIT', 0, 'HIGH', @customer_vip_id,
    1, DATE_ADD(NOW(), INTERVAL 5 DAY), 50.00,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 任务2: 金卡客户 + 普通优先级 + 紧急截止（应得：基础50 + 15 + 25 = 90）
INSERT INTO aicrm_task (
    title, task_type, status, priority, customer_id,
    responsible_user_id, deadline, comprehensive_score,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_任务2-金卡紧急', 'FOLLOW_UP', 0, 'MEDIUM', @customer_gold_id,
    1, DATE_ADD(NOW(), INTERVAL 2 DAY), 50.00,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 任务3: 银卡客户 + 低优先级（应得：基础50，无加分）
INSERT INTO aicrm_task (
    title, task_type, status, priority, customer_id,
    responsible_user_id, deadline, comprehensive_score,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_任务3-银卡普通', 'PHONE_CALL', 0, 'LOW', @customer_silver_id,
    1, DATE_ADD(NOW(), INTERVAL 10 DAY), 50.00,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 任务4: 普通客户 + 高优先级 + 紧急截止（应得：基础50 + 30 + 25 = 105）
INSERT INTO aicrm_task (
    title, task_type, status, priority, customer_id,
    responsible_user_id, deadline, comprehensive_score,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_任务4-普通紧急高优先级', 'CUSTOMER_VISIT', 0, 'HIGH', @customer_normal_id,
    1, DATE_ADD(NOW(), INTERVAL 1 DAY), 50.00,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 任务5: 低信用客户 + 低优先级（应得：基础50，无加分）
INSERT INTO aicrm_task (
    title, task_type, status, priority, customer_id,
    responsible_user_id, deadline, comprehensive_score,
    creator, create_time, updater, update_time, deleted, tenant_id
) VALUES (
    'TEST_任务5-低信用普通', 'FOLLOW_UP', 0, 'LOW', @customer_low_credit_id,
    1, DATE_ADD(NOW(), INTERVAL 15 DAY), 50.00,
    '1', NOW(), '1', NOW(), 0, 1
);

-- 查询结果验证
SELECT
    '=== 测试数据创建完成 ===' AS message,
    (SELECT COUNT(*) FROM crm_customer WHERE customer_name LIKE 'TEST_%' AND deleted = 0) AS test_customers_count,
    (SELECT COUNT(*) FROM aicrm_task_scoring_factor WHERE factor_name LIKE 'TEST_%' AND deleted = 0) AS test_factors_count,
    (SELECT COUNT(*) FROM aicrm_task_scoring_condition WHERE deleted = 0 AND factor_id IN (
        SELECT id FROM aicrm_task_scoring_factor WHERE factor_name LIKE 'TEST_%'
    )) AS test_conditions_count,
    (SELECT COUNT(*) FROM aicrm_task WHERE title LIKE 'TEST_%' AND deleted = 0) AS test_tasks_count;

-- 显示测试任务及其关联的客户信息
SELECT
    t.id AS task_id,
    t.title AS task_name,
    t.priority,
    DATEDIFF(t.deadline, NOW()) AS deadline_days,
    t.comprehensive_score AS current_score,
    c.customer_name,
    c.customer_level,
    c.credit_score,
    DATEDIFF(NOW(), c.create_time) AS account_age_days
FROM aicrm_task t
LEFT JOIN crm_customer c ON t.customer_id = c.id
WHERE t.title LIKE 'TEST_%' AND t.deleted = 0
ORDER BY t.id;

-- =============================================
-- 预期评分结果:
-- 任务1: 125分 (VIP+20, 高信用+15, 老客户+10, 高优先级+30)
-- 任务2: 90分  (高信用+15, 紧急截止+25)
-- 任务3: 50分  (无加分)
-- 任务4: 105分 (高优先级+30, 紧急截止+25)
-- 任务5: 50分  (无加分)
--
-- 排序后顺序应为: 任务1 > 任务4 > 任务2 > 任务3 = 任务5
-- =============================================
