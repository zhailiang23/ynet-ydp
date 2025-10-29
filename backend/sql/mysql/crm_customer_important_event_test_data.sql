-- ============================================
-- 客户重要事件表测试数据
-- 创建日期: 2025-10-29
-- 说明:
--   为现有的所有客户创建重要事件测试数据
--   每个客户创建3-5条随机的重要事件记录
-- ============================================

-- 为所有零售客户创建测试事件数据
INSERT INTO `crm_customer_important_event` (
  `customer_id`,
  `event_name`,
  `event_status`,
  `event_date`,
  `event_content`,
  `maintainer_id`,
  `maintainer_name`,
  `maintain_time`,
  `event_type`,
  `event_level`,
  `event_source`,
  `remind_flag`,
  `creator`,
  `create_time`,
  `updater`,
  `update_time`,
  `tenant_id`
)
SELECT
  c.id AS customer_id,
  CASE
    WHEN seq.n = 1 THEN '客户生日'
    WHEN seq.n = 2 THEN '购买理财产品'
    WHEN seq.n = 3 THEN '子女教育规划咨询'
    WHEN seq.n = 4 THEN '办理房贷业务'
    WHEN seq.n = 5 THEN '开通网银服务'
  END AS event_name,
  CASE
    WHEN RAND() > 0.7 THEN 'not_occurred'
    WHEN RAND() > 0.5 THEN 'in_progress'
    ELSE 'completed'
  END AS event_status,
  DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 365) DAY) AS event_date,
  CASE
    WHEN seq.n = 1 THEN '客户生日，建议发送生日祝福短信'
    WHEN seq.n = 2 THEN '客户购买了50万理财产品，期限6个月'
    WHEN seq.n = 3 THEN '客户咨询子女留学教育金规划方案'
    WHEN seq.n = 4 THEN '客户办理房贷200万，期限20年'
    WHEN seq.n = 5 THEN '客户开通网银服务，需要跟进使用情况'
  END AS event_content,
  u.id AS maintainer_id,
  u.nickname AS maintainer_name,
  NOW() AS maintain_time,
  CASE
    WHEN seq.n = 1 THEN 'birthday'
    WHEN seq.n = 2 THEN 'investment'
    WHEN seq.n = 3 THEN 'education'
    WHEN seq.n = 4 THEN 'loan'
    WHEN seq.n = 5 THEN 'other'
  END AS event_type,
  CASE
    WHEN RAND() > 0.7 THEN 'critical'
    WHEN RAND() > 0.3 THEN 'important'
    ELSE 'normal'
  END AS event_level,
  CASE
    WHEN RAND() > 0.5 THEN 'manager_input'
    ELSE 'customer_inform'
  END AS event_source,
  CASE WHEN seq.n = 1 THEN 1 ELSE 0 END AS remind_flag,
  'system' AS creator,
  NOW() AS create_time,
  'system' AS updater,
  NOW() AS update_time,
  c.tenant_id
FROM
  crm_customer c
CROSS JOIN (
  SELECT 1 AS n UNION ALL
  SELECT 2 UNION ALL
  SELECT 3 UNION ALL
  SELECT 4 UNION ALL
  SELECT 5
) seq
LEFT JOIN system_users u ON u.id = (
  SELECT id FROM system_users
  WHERE deleted = 0
  ORDER BY RAND()
  LIMIT 1
)
WHERE
  c.deleted = 0
  AND c.customer_type = 1  -- 只为零售客户创建
  AND seq.n <= FLOOR(3 + RAND() * 3);  -- 每个客户随机3-5条记录

-- 为对公客户创建测试事件数据
INSERT INTO `crm_customer_important_event` (
  `customer_id`,
  `event_name`,
  `event_status`,
  `event_date`,
  `event_content`,
  `maintainer_id`,
  `maintainer_name`,
  `maintain_time`,
  `event_type`,
  `event_level`,
  `event_source`,
  `remind_flag`,
  `creator`,
  `create_time`,
  `updater`,
  `update_time`,
  `tenant_id`
)
SELECT
  c.id AS customer_id,
  CASE
    WHEN seq.n = 1 THEN '企业成立周年纪念'
    WHEN seq.n = 2 THEN '申请授信业务'
    WHEN seq.n = 3 THEN '企业扩张新建厂房'
    WHEN seq.n = 4 THEN '财务审计报告提交'
  END AS event_name,
  CASE
    WHEN RAND() > 0.6 THEN 'not_occurred'
    WHEN RAND() > 0.4 THEN 'in_progress'
    ELSE 'completed'
  END AS event_status,
  DATE_SUB(CURDATE(), INTERVAL FLOOR(RAND() * 365) DAY) AS event_date,
  CASE
    WHEN seq.n = 1 THEN '企业成立5周年，建议组织庆祝活动'
    WHEN seq.n = 2 THEN '企业申请授信额度500万，用于采购原材料'
    WHEN seq.n = 3 THEN '企业计划扩张，新建厂房投资2000万'
    WHEN seq.n = 4 THEN '企业提交年度财务审计报告'
  END AS event_content,
  u.id AS maintainer_id,
  u.nickname AS maintainer_name,
  NOW() AS maintain_time,
  CASE
    WHEN seq.n = 1 THEN 'company_established'
    WHEN seq.n = 2 THEN 'loan'
    WHEN seq.n = 3 THEN 'company_expansion'
    WHEN seq.n = 4 THEN 'other'
  END AS event_type,
  CASE
    WHEN RAND() > 0.5 THEN 'critical'
    ELSE 'important'
  END AS event_level,
  'manager_input' AS event_source,
  CASE WHEN seq.n = 1 THEN 1 ELSE 0 END AS remind_flag,
  'system' AS creator,
  NOW() AS create_time,
  'system' AS updater,
  NOW() AS update_time,
  c.tenant_id
FROM
  crm_customer c
CROSS JOIN (
  SELECT 1 AS n UNION ALL
  SELECT 2 UNION ALL
  SELECT 3 UNION ALL
  SELECT 4
) seq
LEFT JOIN system_users u ON u.id = (
  SELECT id FROM system_users
  WHERE deleted = 0
  ORDER BY RAND()
  LIMIT 1
)
WHERE
  c.deleted = 0
  AND c.customer_type = 2  -- 只为对公客户创建
  AND seq.n <= FLOOR(3 + RAND() * 2);  -- 每个客户随机3-4条记录

-- 查询统计
SELECT
  '客户重要事件统计' AS description,
  COUNT(*) AS total_events,
  COUNT(DISTINCT customer_id) AS customers_with_events,
  COUNT(CASE WHEN event_status = 'not_occurred' THEN 1 END) AS not_occurred,
  COUNT(CASE WHEN event_status = 'in_progress' THEN 1 END) AS in_progress,
  COUNT(CASE WHEN event_status = 'completed' THEN 1 END) AS completed
FROM
  crm_customer_important_event
WHERE
  deleted = 0;
