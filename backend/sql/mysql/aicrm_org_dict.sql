-- ============================================
-- AICRM 组织架构字典数据
-- ============================================
-- 说明: 为组织架构模块创建字典类型和字典数据
-- ============================================

USE `ruoyi-vue-pro`;

-- ============================================
-- 1. 创建字典类型: AICRM组织类型
-- ============================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES ('AICRM组织类型', 'aicrm_org_type', 0, '对公客户组织架构类型字典', 'admin', NOW(), 'admin', NOW(), b'0');

-- ============================================
-- 2. 创建字典数据: AICRM组织类型
-- ============================================
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '总部', '总部', 'aicrm_org_type', 0, 'primary', '集团或公司总部', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '分公司', '分公司', 'aicrm_org_type', 0, 'success', '分公司', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '子公司', '子公司', 'aicrm_org_type', 0, 'success', '子公司', 'admin', NOW(), 'admin', NOW(), b'0'),
(4, '事业部', '事业部', 'aicrm_org_type', 0, 'info', '事业部', 'admin', NOW(), 'admin', NOW(), b'0'),
(5, '部门', '部门', 'aicrm_org_type', 0, 'default', '职能部门', 'admin', NOW(), 'admin', NOW(), b'0'),
(6, '车间', '车间', 'aicrm_org_type', 0, 'default', '生产车间', 'admin', NOW(), 'admin', NOW(), b'0'),
(7, '小组', '小组', 'aicrm_org_type', 0, 'default', '工作小组', 'admin', NOW(), 'admin', NOW(), b'0'),
(8, '中心', '中心', 'aicrm_org_type', 0, 'info', '业务中心', 'admin', NOW(), 'admin', NOW(), b'0'),
(9, '办事处', '办事处', 'aicrm_org_type', 0, 'default', '办事处', 'admin', NOW(), 'admin', NOW(), b'0'),
(10, '其他', '其他', 'aicrm_org_type', 0, 'default', '其他组织类型', 'admin', NOW(), 'admin', NOW(), b'0');

-- ============================================
-- 3. 创建字典类型: AICRM组织状态
-- ============================================
INSERT INTO system_dict_type (name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES ('AICRM组织状态', 'aicrm_org_status', 0, '对公客户组织架构状态字典', 'admin', NOW(), 'admin', NOW(), b'0');

-- ============================================
-- 4. 创建字典数据: AICRM组织状态
-- ============================================
INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted)
VALUES
(1, '启用', 'active', 'aicrm_org_status', 0, 'success', '组织正常运营', 'admin', NOW(), 'admin', NOW(), b'0'),
(2, '停用', 'inactive', 'aicrm_org_status', 0, 'warning', '组织暂停运营', 'admin', NOW(), 'admin', NOW(), b'0'),
(3, '解散', 'dissolved', 'aicrm_org_status', 0, 'danger', '组织已解散', 'admin', NOW(), 'admin', NOW(), b'0');

-- ============================================
-- 验证查询
-- ============================================
-- 查看组织类型字典
-- SELECT dict_type, label, value, sort FROM system_dict_data WHERE dict_type = 'aicrm_org_type' AND deleted = 0 ORDER BY sort;

-- 查看组织状态字典
-- SELECT dict_type, label, value, sort FROM system_dict_data WHERE dict_type = 'aicrm_org_status' AND deleted = 0 ORDER BY sort;
