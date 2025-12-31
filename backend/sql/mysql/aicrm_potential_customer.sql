-- AI CRM 潜客管理表
CREATE TABLE IF NOT EXISTS `aicrm_potential_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',

  -- 基本信息
  `customer_id` bigint DEFAULT NULL COMMENT '关联客户ID（如果已是正式客户）',
  `customer_name` varchar(100) NOT NULL COMMENT '客户姓名',
  `avatar` varchar(500) DEFAULT NULL COMMENT '客户头像URL',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `id_card` varchar(50) DEFAULT NULL COMMENT '身份证号',

  -- 客户属性
  `risk_level` varchar(20) NOT NULL DEFAULT 'BALANCED' COMMENT '风险等级（AGGRESSIVE=激进型, BALANCED=稳健型, CONSERVATIVE=保守型）',
  `customer_level` varchar(10) DEFAULT NULL COMMENT '客户等级（A/B/C/D）',
  `aum` decimal(18,2) DEFAULT 0.00 COMMENT '资产管理规模（Asset Under Management）',
  `potential_value` decimal(18,2) DEFAULT 0.00 COMMENT '潜在价值（预计可撬动资产）',

  -- AI 分析
  `ai_match_score` int DEFAULT 0 COMMENT 'AI 匹配度（0-100）',
  `analysis_conclusion` text DEFAULT NULL COMMENT 'AI 分析结论',
  `insight_id` bigint DEFAULT NULL COMMENT '关联的洞察ID',
  `insight_title` varchar(200) DEFAULT NULL COMMENT '洞察标题（冗余字段，便于查询）',

  -- 状态管理
  `status` varchar(20) NOT NULL DEFAULT 'NEW' COMMENT '潜客状态（NEW=新建, FOLLOWING=跟进中, CONVERTED=已转化, LOST=已流失）',
  `source` varchar(50) DEFAULT 'AI_RECOMMENDATION' COMMENT '来源（AI_RECOMMENDATION=AI推荐, MANUAL=手动添加, IMPORT=导入）',
  `assigned_user_id` bigint DEFAULT NULL COMMENT '分配给的客户经理ID',
  `assigned_user_name` varchar(100) DEFAULT NULL COMMENT '客户经理姓名（冗余字段）',

  -- 跟进信息
  `last_contact_time` datetime DEFAULT NULL COMMENT '最后联系时间',
  `next_followup_time` datetime DEFAULT NULL COMMENT '下次跟进时间',
  `followup_count` int DEFAULT 0 COMMENT '跟进次数',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',

  -- 标准字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`) COMMENT '客户ID索引',
  KEY `idx_insight_id` (`insight_id`) COMMENT '洞察ID索引',
  KEY `idx_status` (`status`) COMMENT '状态索引',
  KEY `idx_ai_match_score` (`ai_match_score` DESC) COMMENT 'AI匹配度索引（降序）',
  KEY `idx_assigned_user` (`assigned_user_id`) COMMENT '分配用户索引',
  KEY `idx_tenant_deleted` (`tenant_id`, `deleted`) COMMENT '租户删除联合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='AI CRM 潜客管理表';

-- 插入测试数据（对应每日洞察详情页的三位高潜力客户）
INSERT INTO `aicrm_potential_customer`
(`customer_name`, `avatar`, `risk_level`, `customer_level`, `aum`, `potential_value`, `ai_match_score`, `analysis_conclusion`, `insight_title`, `status`, `source`)
VALUES
('张三', 'https://lh3.googleusercontent.com/aida-public/AB6AXuDsLxr0vrwIFVD8ebu9F3QmpC1Tuo9ikmmVdWLHp87g2KdCplZi0c1KDZgX3pN_ZGKWZ8JsncR_7Jo6PN8uj7fd9aXWMnNyD0ggqDw9UXNQT9vtPFYFwKOzefRffjYjahqLxS6-APQgZdFfxGfaGVXivy_iW-kecekpYDO17KSVIqdZtZuOszR3a3fOuAGUje5kKYolDAHH-xP7SrtXhqS43u781DI6l3Fw7SOqvRFyCWvI9ukDDLbTYLY4WPSVc9_kEKS2wyX36mWi', 'AGGRESSIVE', 'A', 1250000.00, 300000.00, 98, '客户持有"科技精选A"目前浮亏5%，账户闲置资金充足，历史上三次低位补仓行为。', '科技类基金回调机会', 'NEW', 'AI_RECOMMENDATION'),
('李四', 'https://lh3.googleusercontent.com/aida-public/AB6AXuCiL5GnO39V652zjP4nXhHbYJw0I3n8iPTgYkPJcckxlMcREAwyaSvHGDnfN4v1ymAkMWPZIqu3cS-vH6Ilu_beD_kEO70avdyaZ_At8DpFWGNTblmO2ZMvfWUWwIvYbmUNe8BwdSWbJug_MjBl3mPToEs6N8UqnvwVlPA5RHhvzO9nbjavRlxcyKmbyQaM7o0TYPklm5QNkzqdqP-vV_YpzgONFPcQiPpwG1WE-2gmrudOWr3so0FVfEnmE9hSO1fBbQ5_ut2pw4e8', 'BALANCED', 'A', 5800000.00, 500000.00, 92, '关注科技板块但尚未持仓，近期浏览相关资讯频次高，适合推荐定投方案作为底仓配置。', '科技类基金回调机会', 'NEW', 'AI_RECOMMENDATION'),
('王五', 'https://lh3.googleusercontent.com/aida-public/AB6AXuD87PAaV9rjUDczzzpKCBkWg2HK9u4IMaRrqzzZMQTo5CHbHrIoWB1Ulu4AW9yFI1GaHjFiVjneJcgewah0pxiT41qDNURaJB3Bcm-efu997LQ5LmRa6LGgx6LajRPbNwjJBR6INszR5Z4yyD6m9QpEkInRzuyW4ffeAUZ-vmpw5JewICeorQ_m3HL2FNDoLggCXGFauc_ndmO4VqCh-Lbaf0NnlgnhL_CX2-HCFYXymYbrklLZ6n2xaKGvcI4sFRy9OR1J5IzcVh_b', 'AGGRESSIVE', 'B', 890000.00, 150000.00, 85, '持有同类竞品基金，近期表现不佳，存在换仓可能，建议进行产品对比分析。', '科技类基金回调机会', 'NEW', 'AI_RECOMMENDATION'),
('赵六', NULL, 'BALANCED', 'B', 2300000.00, 200000.00, 78, '近期多次咨询定投产品，家庭资产配置较为集中，适合分散投资建议。', '科技类基金回调机会', 'NEW', 'AI_RECOMMENDATION'),
('孙七', NULL, 'CONSERVATIVE', 'C', 650000.00, 80000.00, 65, '风险偏好较低，但关注收益率，可推荐稳健型产品作为过渡。', '科技类基金回调机会', 'NEW', 'AI_RECOMMENDATION');
