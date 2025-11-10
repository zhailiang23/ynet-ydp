-- =====================================================
-- 智能陪练系统 - 字典数据
-- =====================================================
-- 模块：智能陪练 (Intelligent Practice)
-- 版本：2.0
-- 创建日期：2025-11-06
-- 说明：包含智能陪练模块所需的所有字典类型和字典项
-- =====================================================

SET NAMES utf8mb4;

-- =====================================================
-- 字典类型定义
-- =====================================================

-- 1. 难度等级
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM难度等级', 'aicrm_difficulty_level', 0, '陪练内容的难度等级分类', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 2. 营销环节
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM营销环节', 'aicrm_marketing_step', 0, '销售过程中的营销环节', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 3. 技巧分类
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM技巧分类', 'aicrm_skill_category', 0, '销售技巧的分类：套路和技巧', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 4. 性别
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM性别', 'aicrm_gender', 0, '虚拟客户的性别', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 5. 性格类型
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM性格类型', 'aicrm_personality_type', 0, '虚拟客户的性格类型', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 6. 剧本状态
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM剧本状态', 'aicrm_script_status', 0, '陪练剧本的状态', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 7. 课程状态
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM课程状态', 'aicrm_course_status', 0, '陪练课程的状态', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 8. 记录状态
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM记录状态', 'aicrm_record_status', 0, '用户陪练记录的状态', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 9. 对话角色
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM对话角色', 'aicrm_conversation_role', 0, '对话中的角色类型', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 10. 情绪标签
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM情绪标签', 'aicrm_emotion_tag', 0, '对话中的情绪标签', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 11. 销售意图
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM销售意图', 'aicrm_sales_intent', 0, '销售人员的意图识别', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();

-- 12. 客户反应
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户反应', 'aicrm_customer_reaction', 0, '虚拟客户的反应类型', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = VALUES(name), remark = VALUES(remark), update_time = NOW();


-- =====================================================
-- 字典数据定义
-- =====================================================

-- ==================== 1. 难度等级 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_difficulty_level', 'beginner', '初级', 1, 0, '适合新手学员', '', NOW(), '', NOW(), b'0'),
('aicrm_difficulty_level', 'intermediate', '中级', 2, 0, '需要一定经验', '', NOW(), '', NOW(), b'0'),
('aicrm_difficulty_level', 'advanced', '高级', 3, 0, '适合资深销售', '', NOW(), '', NOW(), b'0'),
('aicrm_difficulty_level', 'expert', '专家', 4, 0, '极具挑战性', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 2. 营销环节 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_marketing_step', 'contact', '初次接触', 1, 0, '与客户建立首次联系', '', NOW(), '', NOW(), b'0'),
('aicrm_marketing_step', 'needs_discovery', '需求挖掘', 2, 0, '了解客户需求和痛点', '', NOW(), '', NOW(), b'0'),
('aicrm_marketing_step', 'product_intro', '产品介绍', 3, 0, '介绍产品功能和价值', '', NOW(), '', NOW(), b'0'),
('aicrm_marketing_step', 'objection_handling', '异议处理', 4, 0, '处理客户的疑虑和异议', '', NOW(), '', NOW(), b'0'),
('aicrm_marketing_step', 'closing', '促成交易', 5, 0, '推动客户做出购买决策', '', NOW(), '', NOW(), b'0'),
('aicrm_marketing_step', 'follow_up', '后续跟进', 6, 0, '交易后的客户维护', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 3. 技巧分类 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_skill_category', 'routine', '套路', 1, 0, '系统化的销售流程和方法', '', NOW(), '', NOW(), b'0'),
('aicrm_skill_category', 'skill', '技巧', 2, 0, '具体的销售技能和话术', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 4. 性别 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_gender', 'male', '男', 1, 0, '男性', '', NOW(), '', NOW(), b'0'),
('aicrm_gender', 'female', '女', 2, 0, '女性', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 5. 性格类型 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_personality_type', 'rational', '理性型', 1, 0, '注重数据和逻辑分析', '', NOW(), '', NOW(), b'0'),
('aicrm_personality_type', 'emotional', '感性型', 2, 0, '注重感受和体验', '', NOW(), '', NOW(), b'0'),
('aicrm_personality_type', 'analytical', '分析型', 3, 0, '追求细节和完美', '', NOW(), '', NOW(), b'0'),
('aicrm_personality_type', 'driver', '支配型', 4, 0, '强势主导决策', '', NOW(), '', NOW(), b'0'),
('aicrm_personality_type', 'expressive', '表达型', 5, 0, '热情外向善于沟通', '', NOW(), '', NOW(), b'0'),
('aicrm_personality_type', 'amiable', '和善型', 6, 0, '友好温和重视关系', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 6. 剧本状态 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_script_status', 'draft', '草稿', 1, 0, '剧本编辑中，未发布', '', NOW(), '', NOW(), b'0'),
('aicrm_script_status', 'published', '已发布', 2, 0, '剧本已发布，可用于陪练', '', NOW(), '', NOW(), b'0'),
('aicrm_script_status', 'archived', '已归档', 3, 0, '剧本已归档，不再使用', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 7. 课程状态 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_course_status', 'active', '进行中', 1, 0, '课程开放可参与', '', NOW(), '', NOW(), b'0'),
('aicrm_course_status', 'completed', '已结束', 2, 0, '课程已完成', '', NOW(), '', NOW(), b'0'),
('aicrm_course_status', 'cancelled', '已取消', 3, 0, '课程已取消', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 8. 记录状态 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_record_status', 'in_progress', '进行中', 1, 0, '陪练正在进行', '', NOW(), '', NOW(), b'0'),
('aicrm_record_status', 'completed', '已完成', 2, 0, '陪练已完成', '', NOW(), '', NOW(), b'0'),
('aicrm_record_status', 'abandoned', '已放弃', 3, 0, '中途退出', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 9. 对话角色 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_conversation_role', 'student', '学员', 1, 0, '参与陪练的学员', '', NOW(), '', NOW(), b'0'),
('aicrm_conversation_role', 'virtual_customer', '虚拟客户', 2, 0, 'AI扮演的虚拟客户', '', NOW(), '', NOW(), b'0'),
('aicrm_conversation_role', 'system', '系统', 3, 0, '系统提示消息', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 10. 情绪标签 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_emotion_tag', 'positive', '积极', 1, 0, '情绪积极乐观', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'neutral', '中性', 2, 0, '情绪平淡', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'negative', '消极', 3, 0, '情绪负面', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'anxious', '焦虑', 4, 0, '表现出焦虑不安', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'excited', '兴奋', 5, 0, '表现出兴奋期待', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'confused', '困惑', 6, 0, '表现出疑惑不解', '', NOW(), '', NOW(), b'0'),
('aicrm_emotion_tag', 'resistant', '抗拒', 7, 0, '表现出抵触抗拒', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 11. 销售意图 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_sales_intent', 'opening', '开场', 1, 0, '初次接触开场', '', NOW(), '', NOW(), b'0'),
('aicrm_sales_intent', 'needs_probing', '需求探询', 2, 0, '挖掘客户需求', '', NOW(), '', NOW(), b'0'),
('aicrm_sales_intent', 'value_presentation', '价值呈现', 3, 0, '展示产品价值', '', NOW(), '', NOW(), b'0'),
('aicrm_sales_intent', 'objection_response', '异议应对', 4, 0, '处理客户异议', '', NOW(), '', NOW(), b'0'),
('aicrm_sales_intent', 'closing_attempt', '成交尝试', 5, 0, '尝试促成交易', '', NOW(), '', NOW(), b'0'),
('aicrm_sales_intent', 'relationship_building', '关系建立', 6, 0, '建立客户关系', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();

-- ==================== 12. 客户反应 ====================
INSERT INTO system_dict_data (dict_type, value, label, sort, status, remark, creator, create_time, updater, update_time, deleted)
VALUES
('aicrm_customer_reaction', 'interested', '感兴趣', 1, 0, '表现出兴趣', '', NOW(), '', NOW(), b'0'),
('aicrm_customer_reaction', 'hesitant', '犹豫', 2, 0, '犹豫不决', '', NOW(), '', NOW(), b'0'),
('aicrm_customer_reaction', 'objecting', '提出异议', 3, 0, '提出质疑或反对', '', NOW(), '', NOW(), b'0'),
('aicrm_customer_reaction', 'agreeing', '认同', 4, 0, '表示同意', '', NOW(), '', NOW(), b'0'),
('aicrm_customer_reaction', 'rejecting', '拒绝', 5, 0, '明确拒绝', '', NOW(), '', NOW(), b'0'),
('aicrm_customer_reaction', 'requesting_info', '请求信息', 6, 0, '要求更多信息', '', NOW(), '', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label), sort = VALUES(sort), remark = VALUES(remark), update_time = NOW();


-- =====================================================
-- 字典数据统计
-- =====================================================
-- 字典类型数量：12个
-- 字典项总数：约65+项
-- =====================================================
