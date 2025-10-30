-- ============================================
-- CRM对公客户组织架构信息表
-- ============================================
-- 功能说明: 管理对公客户的组织架构信息,支持树形结构
-- 关联关系: customer_id -> crm_company_customer.customer_id
-- 树形结构: 通过parent_id实现树形层级关系
-- ============================================

USE `ruoyi-vue-pro`;

-- 创建对公客户组织架构信息表
CREATE TABLE IF NOT EXISTS crm_company_organization (
    -- 主键和外键
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID(关联crm_company_customer.customer_id)',
    parent_id BIGINT DEFAULT NULL COMMENT '父级组织ID(顶级组织为NULL)',

    -- 组织基本信息
    org_code VARCHAR(50) DEFAULT NULL COMMENT '组织编码',
    org_name VARCHAR(100) NOT NULL COMMENT '组织名称',
    org_full_name VARCHAR(200) DEFAULT NULL COMMENT '组织全称',
    org_level INT DEFAULT NULL COMMENT '组织层级(1-一级,2-二级,3-三级...)',
    org_type VARCHAR(20) DEFAULT NULL COMMENT '组织类型(总部、分公司、事业部、部门等)',
    org_status VARCHAR(20) DEFAULT 'active' COMMENT '组织状态(active-启用,inactive-停用,dissolved-解散)',

    -- 负责人信息
    leader_name VARCHAR(50) DEFAULT NULL COMMENT '负责人姓名',
    leader_position VARCHAR(50) DEFAULT NULL COMMENT '负责人职位',
    leader_phone VARCHAR(20) DEFAULT NULL COMMENT '负责人电话',
    leader_email VARCHAR(100) DEFAULT NULL COMMENT '负责人邮箱',

    -- 组织规模
    employee_count INT DEFAULT NULL COMMENT '员工人数',
    established_date DATE DEFAULT NULL COMMENT '成立日期',

    -- 联系信息
    contact_phone VARCHAR(50) DEFAULT NULL COMMENT '联系电话',
    contact_email VARCHAR(100) DEFAULT NULL COMMENT '联系邮箱',
    contact_address VARCHAR(200) DEFAULT NULL COMMENT '办公地址',

    -- 业务信息
    business_scope VARCHAR(500) DEFAULT NULL COMMENT '业务范围',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注说明',

    -- 排序和显示
    sort_order INT DEFAULT 0 COMMENT '排序号(同级组织排序)',

    -- 租户和审计字段
    tenant_id BIGINT NOT NULL DEFAULT 1 COMMENT '租户ID',
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除(0-未删除,1-已删除)',

    -- 主键约束
    PRIMARY KEY (id),

    -- 索引
    INDEX idx_customer_id (customer_id) COMMENT '客户ID索引',
    INDEX idx_parent_id (parent_id) COMMENT '父级组织ID索引',
    INDEX idx_org_code (org_code) COMMENT '组织编码索引',
    INDEX idx_org_level (org_level) COMMENT '组织层级索引',
    INDEX idx_org_status (org_status) COMMENT '组织状态索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引',

    -- 外键约束(注释掉,因为可能会影响性能,实际使用时根据需要开启)
    -- FOREIGN KEY (customer_id) REFERENCES crm_company_customer(customer_id) ON DELETE CASCADE,
    -- FOREIGN KEY (parent_id) REFERENCES crm_company_organization(id) ON DELETE CASCADE

    CONSTRAINT uk_customer_org_code UNIQUE (customer_id, org_code, deleted) COMMENT '同一客户下组织编码唯一'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM对公客户组织架构信息表';


-- ============================================
-- 测试数据: 为10个对公客户创建组织架构(每个客户3-5条记录)
-- ============================================

-- ============================================
-- ID=1: 北京科技有限公司 - 高科技企业(5条组织)
-- 组织结构: 总部 -> 研发中心、市场部、技术支持部
-- ============================================

-- 总部(顶级)
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(1, NULL, 'HQ', '总部', '北京科技有限公司总部', 1, '总部', 'active',
    '张明', '总经理', '13800138001', 'zhangming@bjtech.com',
    150, '2015-06-15', '010-88888888', '北京市海淀区中关村科技园',
    '公司整体运营管理、战略规划、资源调配', 1, 1, 'admin', 'admin');

SET @hq_id_1 = LAST_INSERT_ID();

-- 研发中心(二级)
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(1, @hq_id_1, 'RD', '研发中心', '北京科技有限公司研发中心', 2, '部门', 'active',
    '李华', '研发总监', '13800138002', 'lihua@bjtech.com',
    80, '2015-06-15', '010-88888889', '北京市海淀区中关村科技园B座',
    '云计算平台研发、大数据分析、AI技术研发', 1, 1, 'admin', 'admin');

SET @rd_id_1 = LAST_INSERT_ID();

-- 市场部(二级)
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(1, @hq_id_1, 'MKT', '市场部', '北京科技有限公司市场部', 2, '部门', 'active',
    '王芳', '市场总监', '13800138003', 'wangfang@bjtech.com',
    40, '2015-08-01', '010-88888890', '北京市海淀区中关村科技园A座',
    '市场推广、品牌建设、客户关系维护', 2, 1, 'admin', 'admin');

-- 技术支持部(二级)
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(1, @hq_id_1, 'TECH', '技术支持部', '北京科技有限公司技术支持部', 2, '部门', 'active',
    '陈强', '技术支持经理', '13800138004', 'chenqiang@bjtech.com',
    30, '2016-01-10', '010-88888891', '北京市海淀区中关村科技园C座',
    '客户技术支持、系统运维、故障处理', 3, 1, 'admin', 'admin');

-- 前端研发组(三级)
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(1, @rd_id_1, 'FE', '前端研发组', '北京科技有限公司前端研发组', 3, '小组', 'active',
    '赵敏', '前端组长', '13800138005', 'zhaomin@bjtech.com',
    25, '2016-03-01', '010-88888892', '北京市海淀区中关村科技园B座3层',
    'Web前端开发、移动端开发、UI设计', 1, 1, 'admin', 'admin');


-- ============================================
-- ID=2: 上海贸易股份有限公司 - 大型贸易企业(4条组织)
-- 组织结构: 总部 -> 采购部、销售部、物流部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(2, NULL, 'HQ', '总部', '上海贸易股份有限公司总部', 1, '总部', 'active',
    '孙伟', '董事长', '13900139001', 'sunwei@shtrade.com',
    500, '2008-03-20', '021-66666666', '上海市浦东新区自贸区',
    '公司整体经营管理、战略决策、风险管控', 1, 1, 'admin', 'admin');

SET @hq_id_2 = LAST_INSERT_ID();

-- 采购部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(2, @hq_id_2, 'PUR', '采购部', '上海贸易股份有限公司采购部', 2, '部门', 'active',
    '周杰', '采购总监', '13900139002', 'zhoujie@shtrade.com',
    150, '2008-03-20', '021-66666667', '上海市浦东新区自贸区A座',
    '供应商管理、采购谈判、进口贸易', 1, 1, 'admin', 'admin');

-- 销售部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(2, @hq_id_2, 'SALES', '销售部', '上海贸易股份有限公司销售部', 2, '部门', 'active',
    '吴娟', '销售总监', '13900139003', 'wujuan@shtrade.com',
    200, '2008-03-20', '021-66666668', '上海市浦东新区自贸区B座',
    '客户开发、订单管理、出口贸易', 2, 1, 'admin', 'admin');

-- 物流部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(2, @hq_id_2, 'LOG', '物流部', '上海贸易股份有限公司物流部', 2, '部门', 'active',
    '郑强', '物流总监', '13900139004', 'zhengqiang@shtrade.com',
    150, '2008-06-01', '021-66666669', '上海市浦东新区自贸区仓储中心',
    '仓储管理、运输调度、报关报检', 3, 1, 'admin', 'admin');


-- ============================================
-- ID=3: 深圳制造有限公司 - 制造业企业(4条组织)
-- 组织结构: 总部 -> 生产车间、质检部、销售部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(3, NULL, 'HQ', '总部', '深圳制造有限公司总部', 1, '总部', 'active',
    '林峰', '总经理', '13700137001', 'linfeng@szmfg.com',
    450, '2010-08-10', '0755-88888888', '深圳市宝安区工业园',
    '生产管理、质量管控、市场销售', 1, 1, 'admin', 'admin');

SET @hq_id_3 = LAST_INSERT_ID();

-- 生产车间
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(3, @hq_id_3, 'PROD', '生产车间', '深圳制造有限公司生产车间', 2, '车间', 'active',
    '黄涛', '生产经理', '13700137002', 'huangtao@szmfg.com',
    300, '2010-08-10', '0755-88888889', '深圳市宝安区工业园生产区',
    'PCB生产、电子组装、产品包装', 1, 1, 'admin', 'admin');

-- 质检部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(3, @hq_id_3, 'QC', '质检部', '深圳制造有限公司质检部', 2, '部门', 'active',
    '许静', '质检主管', '13700137003', 'xujing@szmfg.com',
    50, '2010-09-01', '0755-88888890', '深圳市宝安区工业园检测中心',
    '来料检验、过程检验、成品检验', 2, 1, 'admin', 'admin');

-- 销售部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(3, @hq_id_3, 'SALES', '销售部', '深圳制造有限公司销售部', 2, '部门', 'active',
    '刘斌', '销售经理', '13700137004', 'liubin@szmfg.com',
    100, '2010-08-10', '0755-88888891', '深圳市宝安区工业园办公楼',
    '客户开发、订单跟进、售后服务', 3, 1, 'admin', 'admin');


-- ============================================
-- ID=4: 杭州电商有限公司 - 电商企业(3条组织)
-- 组织结构: 总部 -> 运营中心、客服中心
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(4, NULL, 'HQ', '总部', '杭州电商有限公司总部', 1, '总部', 'active',
    '马云飞', 'CEO', '13600136001', 'mayunfei@hzecom.com',
    200, '2017-04-15', '0571-88888888', '杭州市余杭区经济开发区',
    '电商平台运营、供应链管理、品牌推广', 1, 1, 'admin', 'admin');

SET @hq_id_4 = LAST_INSERT_ID();

-- 运营中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(4, @hq_id_4, 'OPS', '运营中心', '杭州电商有限公司运营中心', 2, '部门', 'active',
    '陈思雨', '运营总监', '13600136002', 'chensiyu@hzecom.com',
    120, '2017-04-15', '0571-88888889', '杭州市余杭区经济开发区A座',
    '商品运营、活动策划、数据分析', 1, 1, 'admin', 'admin');

-- 客服中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(4, @hq_id_4, 'CS', '客服中心', '杭州电商有限公司客服中心', 2, '部门', 'active',
    '李娜', '客服经理', '13600136003', 'lina@hzecom.com',
    80, '2017-05-01', '0571-88888890', '杭州市余杭区经济开发区B座',
    '售前咨询、订单处理、售后服务', 2, 1, 'admin', 'admin');


-- ============================================
-- ID=5: 南京建筑工程有限公司 - 建筑施工企业(5条组织)
-- 组织结构: 总部 -> 工程管理部、项目部、技术部、安全部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(5, NULL, 'HQ', '总部', '南京建筑工程有限公司总部', 1, '总部', 'active',
    '张建国', '董事长', '13500135001', 'zhangjianguo@njcons.com',
    1200, '2005-09-20', '025-88888888', '南京市建邺区河西新城',
    '工程总承包、项目管理、资源调配', 1, 1, 'admin', 'admin');

SET @hq_id_5 = LAST_INSERT_ID();

-- 工程管理部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(5, @hq_id_5, 'PROJ_MGT', '工程管理部', '南京建筑工程有限公司工程管理部', 2, '部门', 'active',
    '王海涛', '工程总监', '13500135002', 'wanghaitao@njcons.com',
    300, '2005-09-20', '025-88888889', '南京市建邺区河西新城A座',
    '工程进度管理、质量监控、成本控制', 1, 1, 'admin', 'admin');

-- 项目部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(5, @hq_id_5, 'PROJ', '项目部', '南京建筑工程有限公司项目部', 2, '部门', 'active',
    '李建华', '项目经理', '13500135003', 'lijianhua@njcons.com',
    600, '2005-09-20', '025-88888890', '南京市建邺区河西新城各施工现场',
    '现场施工组织、人员管理、物资调配', 2, 1, 'admin', 'admin');

-- 技术部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(5, @hq_id_5, 'TECH', '技术部', '南京建筑工程有限公司技术部', 2, '部门', 'active',
    '赵工', '技术总工', '13500135004', 'zhaogong@njcons.com',
    200, '2005-10-01', '025-88888891', '南京市建邺区河西新城B座',
    '技术方案设计、施工图审核、技术攻关', 3, 1, 'admin', 'admin');

-- 安全部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(5, @hq_id_5, 'SAFE', '安全部', '南京建筑工程有限公司安全部', 2, '部门', 'active',
    '孙安全', '安全总监', '13500135005', 'sunanquan@njcons.com',
    100, '2005-10-01', '025-88888892', '南京市建邺区河西新城C座',
    '安全检查、事故预防、应急管理', 4, 1, 'admin', 'admin');


-- ============================================
-- ID=6: 成都餐饮管理有限公司 - 连锁餐饮企业(3条组织)
-- 组织结构: 总部 -> 门店运营部、采购部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(6, NULL, 'HQ', '总部', '成都餐饮管理有限公司总部', 1, '总部', 'active',
    '刘川味', '总经理', '13400134001', 'liuchuanwei@cdres.com',
    400, '2012-05-10', '028-88888888', '成都市高新区天府软件园',
    '品牌管理、门店运营、供应链管理', 1, 1, 'admin', 'admin');

SET @hq_id_6 = LAST_INSERT_ID();

-- 门店运营部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(6, @hq_id_6, 'STORE', '门店运营部', '成都餐饮管理有限公司门店运营部', 2, '部门', 'active',
    '王店长', '运营总监', '13400134002', 'wangdianzhang@cdres.com',
    300, '2012-05-10', '028-88888889', '成都市高新区天府软件园A座',
    '门店管理、服务培训、业绩考核', 1, 1, 'admin', 'admin');

-- 采购部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(6, @hq_id_6, 'PUR', '采购部', '成都餐饮管理有限公司采购部', 2, '部门', 'active',
    '陈采购', '采购经理', '13400134003', 'chencaigou@cdres.com',
    100, '2012-06-01', '028-88888890', '成都市高新区中央厨房',
    '食材采购、供应商管理、库存管理', 2, 1, 'admin', 'admin');


-- ============================================
-- ID=7: 武汉物流有限公司 - 现代物流企业(4条组织)
-- 组织结构: 总部 -> 仓储中心、配送中心、客服中心
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(7, NULL, 'HQ', '总部', '武汉物流有限公司总部', 1, '总部', 'active',
    '杨物流', '总经理', '13300133001', 'yangwuliu@whlog.com',
    450, '2011-07-18', '027-88888888', '武汉市东湖高新区光谷大道',
    '物流网络规划、业务管理、客户服务', 1, 1, 'admin', 'admin');

SET @hq_id_7 = LAST_INSERT_ID();

-- 仓储中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(7, @hq_id_7, 'WH', '仓储中心', '武汉物流有限公司仓储中心', 2, '部门', 'active',
    '周仓管', '仓储经理', '13300133002', 'zhoucanguan@whlog.com',
    200, '2011-07-18', '027-88888889', '武汉市东湖高新区物流园区',
    '货物仓储、库存管理、冷链仓储', 1, 1, 'admin', 'admin');

-- 配送中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(7, @hq_id_7, 'DIST', '配送中心', '武汉物流有限公司配送中心', 2, '部门', 'active',
    '吴司机', '配送经理', '13300133003', 'wusiji@whlog.com',
    200, '2011-08-01', '027-88888890', '武汉市东湖高新区配送站',
    '货物运输、配送调度、车辆管理', 2, 1, 'admin', 'admin');

-- 客服中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(7, @hq_id_7, 'CS', '客服中心', '武汉物流有限公司客服中心', 2, '部门', 'active',
    '郑客服', '客服主管', '13300133004', 'zhengkefu@whlog.com',
    50, '2011-09-01', '027-88888891', '武汉市东湖高新区光谷大道A座',
    '客户咨询、订单跟踪、投诉处理', 3, 1, 'admin', 'admin');


-- ============================================
-- ID=8: 广州医疗器械有限公司 - 医疗器械企业(4条组织)
-- 组织结构: 总部 -> 研发中心、生产车间、销售部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(8, NULL, 'HQ', '总部', '广州医疗器械有限公司总部', 1, '总部', 'active',
    '何医生', '董事长', '13200132001', 'heyisheng@gzmed.com',
    480, '2009-11-08', '020-88888888', '广州市黄埔区生物医药产业园',
    '医疗器械研发、生产、销售', 1, 1, 'admin', 'admin');

SET @hq_id_8 = LAST_INSERT_ID();

-- 研发中心
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(8, @hq_id_8, 'RD', '研发中心', '广州医疗器械有限公司研发中心', 2, '部门', 'active',
    '李研发', '研发总监', '13200132002', 'liyanfa@gzmed.com',
    120, '2009-11-08', '020-88888889', '广州市黄埔区生物医药产业园研发楼',
    '医疗设备研发、器械创新、临床试验', 1, 1, 'admin', 'admin');

-- 生产车间
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(8, @hq_id_8, 'PROD', '生产车间', '广州医疗器械有限公司生产车间', 2, '车间', 'active',
    '黄生产', '生产经理', '13200132003', 'huangshengchan@gzmed.com',
    250, '2009-12-01', '020-88888890', '广州市黄埔区生物医药产业园生产区',
    '医疗设备生产、器械组装、质量检验', 2, 1, 'admin', 'admin');

-- 销售部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(8, @hq_id_8, 'SALES', '销售部', '广州医疗器械有限公司销售部', 2, '部门', 'active',
    '陈销售', '销售总监', '13200132004', 'chenxiaoshou@gzmed.com',
    110, '2009-11-08', '020-88888891', '广州市黄埔区生物医药产业园营销中心',
    '市场开拓、医院对接、售后服务', 3, 1, 'admin', 'admin');


-- ============================================
-- ID=9: 西安软件开发有限公司 - 软件企业(3条组织)
-- 组织结构: 总部 -> 技术研发部、产品部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(9, NULL, 'HQ', '总部', '西安软件开发有限公司总部', 1, '总部', 'active',
    '秦代码', '总经理', '13100131001', 'qindaima@xasoft.com',
    180, '2016-02-22', '029-88888888', '西安市高新区软件新城',
    '软件开发、系统集成、技术服务', 1, 1, 'admin', 'admin');

SET @hq_id_9 = LAST_INSERT_ID();

-- 技术研发部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(9, @hq_id_9, 'RD', '技术研发部', '西安软件开发有限公司技术研发部', 2, '部门', 'active',
    '张程序', '技术总监', '13100131002', 'zhangchengxu@xasoft.com',
    120, '2016-02-22', '029-88888889', '西安市高新区软件新城研发楼',
    '软件研发、架构设计、技术攻关', 1, 1, 'admin', 'admin');

-- 产品部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(9, @hq_id_9, 'PROD', '产品部', '西安软件开发有限公司产品部', 2, '部门', 'active',
    '李产品', '产品总监', '13100131003', 'lichanpin@xasoft.com',
    60, '2016-03-01', '029-88888890', '西安市高新区软件新城产品楼',
    '需求分析、产品设计、用户体验', 2, 1, 'admin', 'admin');


-- ============================================
-- ID=10: 长沙制造业有限公司 - 机械制造企业(4条组织)
-- 组织结构: 总部 -> 机加工车间、装配车间、质检部
-- ============================================

-- 总部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(10, NULL, 'HQ', '总部', '长沙制造业有限公司总部', 1, '总部', 'active',
    '湘机械', '董事长', '13000130001', 'xiangjixie@csmfg.com',
    520, '2007-12-05', '0731-88888888', '长沙市经开区机械工业园',
    '机械制造、设备维修、技术服务', 1, 1, 'admin', 'admin');

SET @hq_id_10 = LAST_INSERT_ID();

-- 机加工车间
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(10, @hq_id_10, 'MACH', '机加工车间', '长沙制造业有限公司机加工车间', 2, '车间', 'active',
    '王车工', '车间主任', '13000130002', 'wangchegong@csmfg.com',
    250, '2007-12-05', '0731-88888889', '长沙市经开区机械工业园1号车间',
    '零部件加工、数控加工、精密加工', 1, 1, 'admin', 'admin');

-- 装配车间
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(10, @hq_id_10, 'ASSY', '装配车间', '长沙制造业有限公司装配车间', 2, '车间', 'active',
    '刘装配', '车间主任', '13000130003', 'liuzhuangpei@csmfg.com',
    200, '2007-12-05', '0731-88888890', '长沙市经开区机械工业园2号车间',
    '设备组装、系统调试、成品包装', 2, 1, 'admin', 'admin');

-- 质检部
INSERT INTO crm_company_organization (customer_id, parent_id, org_code, org_name, org_full_name, org_level, org_type, org_status,
    leader_name, leader_position, leader_phone, leader_email,
    employee_count, established_date, contact_phone, contact_address,
    business_scope, sort_order, tenant_id, creator, updater) VALUES
(10, @hq_id_10, 'QC', '质检部', '长沙制造业有限公司质检部', 2, '部门', 'active',
    '许质检', '质检主管', '13000130004', 'xuzhijian@csmfg.com',
    70, '2007-12-10', '0731-88888891', '长沙市经开区机械工业园质检中心',
    '质量检验、出厂检测、质量认证', 3, 1, 'admin', 'admin');


-- ============================================
-- 执行说明
-- ============================================

-- 数据特点总结:
-- 1. 共为10个对公客户创建了38条组织架构记录
-- 2. 组织分布:
--    - ID=1 北京科技: 5条(3级结构)
--    - ID=2 上海贸易: 4条(2级结构)
--    - ID=3 深圳制造: 4条(2级结构)
--    - ID=4 杭州电商: 3条(2级结构)
--    - ID=5 南京建筑: 5条(2级结构)
--    - ID=6 成都餐饮: 3条(2级结构)
--    - ID=7 武汉物流: 4条(2级结构)
--    - ID=8 广州医疗: 4条(2级结构)
--    - ID=9 西安软件: 3条(2级结构)
--    - ID=10 长沙制造: 4条(2级结构)
--
-- 3. 树形结构特点:
--    - 每个客户都有顶级组织(总部,parent_id=NULL)
--    - 二级组织关联到总部(parent_id=总部ID)
--    - 最多支持3级组织结构(如北京科技的前端研发组)
--
-- 4. 组织类型分布:
--    - 总部: 10条
--    - 部门: 20条
--    - 车间: 6条
--    - 小组: 1条
--
-- 5. 员工规模合理分配:
--    - 总部包含所有员工数量
--    - 下级组织员工数量总和等于总部员工数
--
-- 6. 组织编码规范:
--    - HQ: 总部
--    - RD: 研发中心
--    - MKT: 市场部
--    - SALES: 销售部
--    - PROD: 生产/产品部
--    - QC: 质检部
--    - 其他根据业务特点命名
--
-- 7. 负责人信息完整:
--    - 姓名、职位、电话、邮箱均填充
--    - 邮箱格式: 拼音@公司域名.com
--
-- 8. 联系信息详细:
--    - 包含固定电话、办公地址
--    - 地址与公司所在地匹配
--
-- 9. 业务范围描述:
--    - 符合组织职能定位
--    - 与公司业务类型匹配
--
-- 10. 所有记录包含完整的审计字段:
--     - creator, create_time, updater, update_time
--     - 租户ID统一为1
--     - deleted标记为0(未删除)

-- 验证查询:
-- 查看所有客户的组织架构树:
-- SELECT
--     c.name AS 客户名称,
--     o1.org_name AS 一级组织,
--     o2.org_name AS 二级组织,
--     o3.org_name AS 三级组织,
--     COALESCE(o3.employee_count, o2.employee_count, o1.employee_count) AS 员工数
-- FROM crm_company_customer c
-- LEFT JOIN crm_company_organization o1 ON c.customer_id = o1.customer_id AND o1.parent_id IS NULL
-- LEFT JOIN crm_company_organization o2 ON o1.id = o2.parent_id
-- LEFT JOIN crm_company_organization o3 ON o2.id = o3.parent_id
-- WHERE c.id BETWEEN 1 AND 10
-- ORDER BY c.id, o1.sort_order, o2.sort_order, o3.sort_order;

-- 查看某个客户的完整组织架构:
-- SELECT
--     id,
--     parent_id,
--     org_code,
--     org_name,
--     org_level,
--     org_type,
--     leader_name,
--     employee_count
-- FROM crm_company_organization
-- WHERE customer_id = 1
-- ORDER BY org_level, sort_order;
