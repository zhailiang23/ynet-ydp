-- ========================================
-- 客户管理关系模块 - 字典数据
-- 参考: docs/customer-management-relationship-technical-design.md
-- ========================================

-- ----------------------------
-- 1. aicrm_assignment_operation_type (归属操作类型)
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM归属操作类型', 'aicrm_assignment_operation_type', 0, '客户归属关系操作类型', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '手动分配', 'manual_assign', 'aicrm_assignment_operation_type', 0, 'primary', '管理员手动分配客户', '1', NOW(), '1', NOW(), b'0'),
(2, '客户移交', 'transfer', 'aicrm_assignment_operation_type', 0, 'success', '客户经理间移交客户', '1', NOW(), '1', NOW(), b'0'),
(3, '客户托管', 'delegate', 'aicrm_assignment_operation_type', 0, 'warning', '托管客户给其他经理', '1', NOW(), '1', NOW(), b'0'),
(4, '托管结束', 'delegate_end', 'aicrm_assignment_operation_type', 0, 'info', '结束托管关系', '1', NOW(), '1', NOW(), b'0'),
(5, '客户认领', 'claim', 'aicrm_assignment_operation_type', 0, 'success', '客户经理认领客户', '1', NOW(), '1', NOW(), b'0'),
(6, '客户退回', 'return', 'aicrm_assignment_operation_type', 0, 'warning', '退回客户给主管', '1', NOW(), '1', NOW(), b'0'),
(7, '客户回收', 'reclaim', 'aicrm_assignment_operation_type', 0, 'danger', '主管回收客户', '1', NOW(), '1', NOW(), b'0'),
(8, '主办变更', 'dept_transfer', 'aicrm_assignment_operation_type', 0, 'primary', '变更主办机构', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 2. aicrm_process_status (流程状态)
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM流程状态', 'aicrm_process_status', 0, '审批流程状态', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '审批中', '1', 'aicrm_process_status', 0, 'primary', '流程审批中', '1', NOW(), '1', NOW(), b'0'),
(2, '已通过', '2', 'aicrm_process_status', 0, 'success', '审批通过', '1', NOW(), '1', NOW(), b'0'),
(3, '已拒绝', '3', 'aicrm_process_status', 0, 'danger', '审批拒绝', '1', NOW(), '1', NOW(), b'0'),
(4, '已取消', '4', 'aicrm_process_status', 0, 'info', '申请人取消', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 3. aicrm_delegation_status (托管状态)
-- ----------------------------
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM托管状态', 'aicrm_delegation_status', 0, '客户托管状态', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '托管中', '1', 'aicrm_delegation_status', 0, 'primary', '正在托管', '1', NOW(), '1', NOW(), b'0'),
(2, '已结束', '0', 'aicrm_delegation_status', 0, 'success', '托管已结束', '1', NOW(), '1', NOW(), b'0'),
(3, '已取消', '2', 'aicrm_delegation_status', 0, 'info', '托管已取消', '1', NOW(), '1', NOW(), b'0');

-- ----------------------------
-- 4. aicrm_assignment_status (归属分配状态) - 补充现有字典
-- ----------------------------
-- 检查是否已存在,如果不存在则创建
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
SELECT NULL, 'AICRM客户归属状态', 'aicrm_assignment_status', 0, '客户归属分配状态', '1', NOW(), '1', NOW(), b'0'
WHERE NOT EXISTS (SELECT 1 FROM system_dict_type WHERE type = 'aicrm_assignment_status');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted)
SELECT * FROM (
    SELECT 1 as sort, '未分配' as label, '0' as value, 'aicrm_assignment_status' as dict_type, 0 as status, 'info' as color_type, '客户未分配给任何客户经理' as remark, '1' as creator, NOW() as create_time, '1' as updater, NOW() as update_time, b'0' as deleted
    UNION ALL
    SELECT 2, '已分配', '1', 'aicrm_assignment_status', 0, 'success', '客户已分配给客户经理', '1', NOW(), '1', NOW(), b'0'
    UNION ALL
    SELECT 3, '托管中', '2', 'aicrm_assignment_status', 0, 'warning', '客户正在托管给其他客户经理', '1', NOW(), '1', NOW(), b'0'
) as tmp
WHERE NOT EXISTS (SELECT 1 FROM system_dict_data WHERE dict_type = 'aicrm_assignment_status' AND value = tmp.value);
