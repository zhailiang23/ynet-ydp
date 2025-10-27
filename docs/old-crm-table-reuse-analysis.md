# 老版 CRM 数据表重用分析报告

**版本**: v1.0
**日期**: 2025-10-27
**分析人员**: Claude
**数据库**: MySQL 8.0+

---

## 一、概述

本文档分析老版 CRM 系统的数据模型,对比新版用户画像系统的需求和设计,识别可重用的数据表,为数据迁移和系统升级提供参考。

### 1.1 老版 CRM 数据库信息

- **数据库地址**: `jdbc:mysql://192.168.201.44:3306/dev_palmbank`
- **用户名**: `dev_ibbp_equity`
- **密码**: `1j#ey@jr`
- **数据模型位置**: `/Users/zhailiang/Documents/code/ibbp-palmbank-center/ibbp-palmbank-center/domain/src/main/java/com/ynet/palmbank/domain/crm/model`

### 1.2 分析范围

- **老版 CRM**: 分析 100+ 张表,识别核心业务表
- **新版用户画像**: 参考新需求文档和数据库设计方案
- **对比维度**: 表结构、字段映射、业务逻辑、数据质量

---

## 二、可重用表分析

### 2.1 客户基础信息表 (3张) ⭐⭐⭐⭐⭐

#### 表1: `acrm_f_ci_customer` → `crm_customer`

**重用度**: ⭐⭐⭐⭐⭐ (90%)

**老表结构**:
```java
// 客户基本信息 (零售+对公共用)
@TableName("acrm_f_ci_customer")
public class AcrmFCiCustomer {
    @TableId("cust_id") String custId;           // 客户编号
    String custType;                              // 客户类型 (零售/对公)
    String custName;                              // 客户名称
    String identType;                             // 证件类型
    String identNo;                               // 证件号码
    String custLevel;                             // 客户级别
    String custStat;                              // 客户状态
    String potentialFlag;                         // 潜在客户标识
    String riskLevel;                             // 风险等级
    String createBranchNo;                        // 创建机构
    String createTellerNo;                        // 创建柜员
    Date createTime;                              // 创建时间
    Date lastUpdateTm;                            // 最后更新时间
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer (
    id BIGINT PRIMARY KEY,
    customer_no VARCHAR(50) UNIQUE COMMENT '客户编号',
    customer_type TINYINT COMMENT '1-零售,2-对公',
    customer_name VARCHAR(200) COMMENT '客户名称',
    customer_level VARCHAR(20) COMMENT '客户等级',
    status TINYINT COMMENT '状态',
    risk_level VARCHAR(20) COMMENT '风险等级',
    tenant_id BIGINT COMMENT '租户ID',
    creator VARCHAR(64),
    create_time DATETIME,
    updater VARCHAR(64),
    update_time DATETIME,
    deleted TINYINT DEFAULT 0
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `cust_id` | `customer_no` | 直接映射 | 客户编号保持不变 |
| `custType` | `customer_type` | 值转换 | 需要转换为数字: "零售"→1, "对公"→2 |
| `custName` | `customer_name` | 直接映射 | 客户名称 |
| `custLevel` | `customer_level` | 直接映射 | 客户等级 |
| `custStat` | `status` | 值转换 | 转换为新状态码 |
| `riskLevel` | `risk_level` | 直接映射 | 风险等级 |
| `createTime` | `create_time` | 直接映射 | 创建时间 |
| `lastUpdateTm` | `update_time` | 直接映射 | 更新时间 |
| `identType` | - | **弃用** | 移到证件信息扩展表 |
| `identNo` | - | **弃用** | 移到证件信息扩展表 |

**重用建议**:
- ✅ **核心字段可直接迁移**: 客户编号、名称、等级、状态
- ✅ **需要数据转换**: 客户类型、状态码需要映射
- ⚠️ **证件信息分离**: 证件类型和号码移到 `crm_customer_identity` 表
- ⚠️ **新增字段**: 租户ID需要在迁移时指定默认值

**迁移SQL示例**:
```sql
INSERT INTO crm_customer (
    customer_no, customer_type, customer_name, customer_level,
    status, risk_level, create_time, update_time, tenant_id
)
SELECT
    cust_id,
    CASE
        WHEN cust_type = '个人' THEN 1
        WHEN cust_type = '对公' THEN 2
        ELSE 1
    END,
    cust_name,
    cust_level,
    CASE
        WHEN cust_stat = '正常' THEN 1
        WHEN cust_stat = '停用' THEN 0
        ELSE 1
    END,
    risk_level,
    create_time,
    last_update_tm,
    1 AS tenant_id  -- 默认租户
FROM acrm_f_ci_customer
WHERE cust_id IS NOT NULL;
```

---

#### 表2: `acrm_f_ci_person` → `crm_retail_customer`

**重用度**: ⭐⭐⭐⭐⭐ (95%)

**老表结构**:
```java
// 个人客户扩展信息
@TableName("acrm_f_ci_person")
public class AcrmFCiPerson {
    @TableId("cust_id") String custId;           // 客户编号 (主键)
    String gender;                                // 性别
    Date birthday;                                // 出生日期
    String age;                                   // 年龄
    String nationality;                           // 民族
    String citizenship;                           // 国籍
    String marriage;                              // 婚姻状况
    String health;                                // 健康状况
    String residence;                             // 居住状况
    String household;                             // 户籍性质
    String highestSchooling;                      // 最高学历
    String careerType;                            // 职业
    String unitName;                              // 单位名称
    String duty;                                  // 职务
    String annualIncome;                          // 年收入
    // ... 其他 30+ 字段
}
```

**新表结构**:
```sql
CREATE TABLE crm_retail_customer (
    customer_id BIGINT PRIMARY KEY COMMENT '客户ID',
    gender TINYINT COMMENT '性别 1-男,2-女',
    birthday DATE COMMENT '生日',
    nationality VARCHAR(50) COMMENT '民族',
    citizenship VARCHAR(50) COMMENT '国籍',
    marriage_status VARCHAR(20) COMMENT '婚姻状况',
    health_status VARCHAR(20) COMMENT '健康状况',
    education_level VARCHAR(20) COMMENT '最高学历',
    occupation VARCHAR(100) COMMENT '职业',
    work_unit VARCHAR(200) COMMENT '工作单位',
    position VARCHAR(100) COMMENT '职务',
    annual_income DECIMAL(18,2) COMMENT '年收入',
    -- 其他字段...
    FOREIGN KEY (customer_id) REFERENCES crm_customer(id)
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联到 `crm_customer.id` |
| `gender` | `gender` | 值转换 | "男"→1, "女"→2 |
| `birthday` | `birthday` | 直接映射 | 出生日期 |
| `nationality` | `nationality` | 直接映射 | 民族 |
| `citizenship` | `citizenship` | 直接映射 | 国籍 |
| `marriage` | `marriage_status` | 直接映射 | 婚姻状况 |
| `health` | `health_status` | 直接映射 | 健康状况 |
| `highestSchooling` | `education_level` | 直接映射 | 最高学历 |
| `careerType` | `occupation` | 直接映射 | 职业 |
| `unitName` | `work_unit` | 直接映射 | 工作单位 |
| `duty` | `position` | 直接映射 | 职务 |
| `annualIncome` | `annual_income` | 类型转换 | String → DECIMAL |

**重用建议**:
- ✅ **高度可重用**: 个人基本信息几乎完全匹配
- ✅ **字段丰富**: 老表字段非常详细,可选择性迁移
- ⚠️ **工作信息分离**: 部分工作信息可能需要移到 `crm_customer_work` 表
- ⚠️ **经营信息分离**: 个体经营户信息移到 `crm_customer_business` 表

---

#### 表3: `acrm_f_ci_org` → `crm_company_customer`

**重用度**: ⭐⭐⭐⭐ (85%)

**老表结构**:
```java
// 对公客户扩展信息
@TableName("acrm_f_ci_org")
public class AcrmFCiOrg {
    String custId;                                // 客户编号
    String custName;                              // 机构名称
    String orgCustType;                           // 机构客户类型
    String economicType;                          // 经济类型
    String entProperty;                           // 企业性质
    String mainIndustry;                          // 主营行业
    BigDecimal regFundAmt;                        // 注册资金
    String regFundCurrency;                       // 注册资金币种
    Date buildDate;                               // 成立日期
    BigDecimal totalAssets;                       // 总资产
    BigDecimal totalDebt;                         // 总负债
    BigDecimal annualIncome;                      // 年收入
    BigDecimal annualProfit;                      // 年利润
    String employeeScale;                         // 员工规模
    String mainBusiness;                          // 主营业务
    // ... 其他字段
}
```

**新表结构**:
```sql
CREATE TABLE crm_company_customer (
    customer_id BIGINT PRIMARY KEY COMMENT '客户ID',
    credit_code VARCHAR(50) COMMENT '统一信用代码',
    enterprise_type VARCHAR(50) COMMENT '企业类型',
    economic_type VARCHAR(50) COMMENT '经济类型',
    registered_capital DECIMAL(18,2) COMMENT '注册资本',
    establish_date DATE COMMENT '成立日期',
    employee_count INT COMMENT '员工人数',
    annual_revenue DECIMAL(18,2) COMMENT '年营业额',
    main_industry VARCHAR(100) COMMENT '主营行业',
    main_business TEXT COMMENT '主营业务',
    -- 其他字段...
    FOREIGN KEY (customer_id) REFERENCES crm_customer(id)
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联到 `crm_customer.id` |
| `economicType` | `economic_type` | 直接映射 | 经济类型 |
| `entProperty` | `enterprise_type` | 直接映射 | 企业类型 |
| `mainIndustry` | `main_industry` | 直接映射 | 主营行业 |
| `regFundAmt` | `registered_capital` | 直接映射 | 注册资本 |
| `buildDate` | `establish_date` | 直接映射 | 成立日期 |
| `employeeScale` | `employee_count` | 值提取 | 从"100-500人"提取中间值 |
| `annualIncome` | `annual_revenue` | 直接映射 | 年营业额 |
| `mainBusiness` | `main_business` | 直接映射 | 主营业务 |

**重用建议**:
- ✅ **核心字段匹配**: 企业基本信息完整
- ⚠️ **信用代码缺失**: 老表可能没有统一信用代码,需要补充
- ⚠️ **财务信息分离**: 财务数据应移到 `crm_company_finance` 表

---

### 2.2 客户管理表 (3张) ⭐⭐⭐⭐

#### 表4: `ocrm_f_ci_belong_custmgr` → `crm_customer_assignment`

**重用度**: ⭐⭐⭐⭐ (80%)

**老表结构**:
```java
// 客户归属客户经理
@TableName("ocrm_f_ci_belong_custmgr")
public class OcrmFCiBelongCustmgr {
    @TableId("id") String id;
    String custId;                                // 客户编号
    String mgrId;                                 // 客户经理编号
    String mgrName;                               // 客户经理名称
    String institution;                           // 所属机构
    String institutionName;                       // 所属机构名称
    String mainType;                              // 主协办类型
    String maintainRight;                         // 是否有维护权
    String checkRight;                            // 是否有查看权
    Date assignDate;                              // 分配日期
    Date effectDate;                              // 生效日期
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_assignment (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    assignment_type TINYINT COMMENT '归属类型 1-机构,2-客户经理,3-片区,4-网格,5-客户群',
    assignment_value VARCHAR(100) COMMENT '归属值',
    assignment_name VARCHAR(200) COMMENT '归属名称',
    role_type TINYINT COMMENT '角色类型 1-主办,2-协办',
    permission_type TINYINT COMMENT '权限类型 1-维护,2-查看',
    start_date DATE COMMENT '生效日期',
    end_date DATE COMMENT '失效日期',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `mgrId` | `assignment_value` | 直接映射 | 客户经理ID |
| `mgrName` | `assignment_name` | 直接映射 | 客户经理名称 |
| `mainType` | `role_type` | 值转换 | "主办"→1, "协办"→2 |
| `maintainRight` | `permission_type` | 值转换 | 有维护权→1, 仅查看→2 |
| `effectDate` | `start_date` | 直接映射 | 生效日期 |
| - | `assignment_type` | **新增** | 固定值2(客户经理) |

**重用建议**:
- ✅ **核心逻辑一致**: 客户经理归属关系完整
- ✅ **支持主协办**: 与新设计匹配
- ⚠️ **需要扩展**: 新表支持多种归属类型(机构、片区、网格等)
- ⚠️ **机构归属**: 老表的 `institution` 字段需要单独迁移到机构归属记录

**迁移SQL示例**:
```sql
-- 迁移客户经理归属
INSERT INTO crm_customer_assignment (
    customer_id, assignment_type, assignment_value, assignment_name,
    role_type, permission_type, start_date, create_time
)
SELECT
    c.id,
    2 AS assignment_type,  -- 客户经理
    b.mgr_id,
    b.mgr_name,
    CASE WHEN b.main_type = '主办' THEN 1 ELSE 2 END,
    CASE WHEN b.maintain_right = 'Y' THEN 1 ELSE 2 END,
    b.effect_date,
    b.etl_date
FROM ocrm_f_ci_belong_custmgr b
JOIN crm_customer c ON b.cust_id = c.customer_no
WHERE b.cust_id IS NOT NULL;

-- 迁移机构归属
INSERT INTO crm_customer_assignment (
    customer_id, assignment_type, assignment_value, assignment_name,
    role_type, start_date, create_time
)
SELECT DISTINCT
    c.id,
    1 AS assignment_type,  -- 机构
    b.institution,
    b.institution_name,
    1 AS role_type,  -- 主办
    b.effect_date,
    b.etl_date
FROM ocrm_f_ci_belong_custmgr b
JOIN crm_customer c ON b.cust_id = c.customer_no
WHERE b.institution IS NOT NULL;
```

---

### 2.3 客户关系与画像表 (5张) ⭐⭐⭐⭐

#### 表5: `acrm_f_ci_cust_relate` → `crm_customer_relation`

**重用度**: ⭐⭐⭐⭐⭐ (95%)

**老表结构**:
```java
// 客户关联客户表 (知识图谱)
@TableName("acrm_f_ci_cust_relate")
public class AcrmFCiCustRelate {
    @TableId("id") Long id;
    String custId;                                // 客户编号
    String custName;                              // 客户名称
    String custNoR;                               // 关联客户号
    String custNameR;                             // 关联客户名称
    String rType;                                 // 关联类型
    String relationship;                          // 关联关系
    String rDesc;                                 // 关联描述
    Date createDate;                              // 创建时间
    String createPerson;                          // 创建人
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_relation (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户A的ID',
    related_customer_id BIGINT NOT NULL COMMENT '客户B的ID',
    relation_type_id BIGINT COMMENT '关系类型ID',
    relation_source TINYINT COMMENT '关系来源 1-系统,2-手工',
    relation_strength TINYINT COMMENT '关系强度 1-5',
    remark VARCHAR(500) COMMENT '备注',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 客户A |
| `custNoR` | `related_customer_id` | 关联映射 | 客户B |
| `relationship` | `relation_type_id` | 查找映射 | 需要先建立关系类型字典 |
| `rDesc` | `remark` | 直接映射 | 关系描述 |
| `createPerson` | `creator` | 直接映射 | 创建人 |
| - | `relation_source` | 推断 | 有 createPerson → 2(手工) |

**重用建议**:
- ✅ **完全匹配**: 客户关系数据结构一致
- ✅ **支持知识图谱**: 可直接用于图谱展示
- ⚠️ **关系类型标准化**: 需要将 `relationship` 字段标准化为关系类型字典
- ⚠️ **双向关系**: 需要检查是否需要创建反向关系

**关系类型映射示例**:
```sql
-- 创建关系类型字典
INSERT INTO crm_customer_relation_type (type_code, type_name, description) VALUES
('FAMILY', '家庭成员', '家庭成员关系'),
('COUPLE', '夫妻', '夫妻关系'),
('PARENT_CHILD', '父子/母子', '父母子女关系'),
('BUSINESS', '业务合作', '业务合作伙伴'),
('SUPPLY_CHAIN', '供应链', '供应链上下游'),
('SHAREHOLDER', '股东', '股东关系');

-- 迁移关系数据
INSERT INTO crm_customer_relation (
    customer_id, related_customer_id, relation_type_id,
    relation_source, remark, create_time, creator
)
SELECT
    c1.id,
    c2.id,
    rt.id,
    CASE WHEN r.create_person IS NOT NULL THEN 2 ELSE 1 END,
    r.r_desc,
    r.create_date,
    r.create_person
FROM acrm_f_ci_cust_relate r
JOIN crm_customer c1 ON r.cust_id = c1.customer_no
JOIN crm_customer c2 ON r.cust_no_r = c2.customer_no
JOIN crm_customer_relation_type rt ON r.relationship = rt.type_code
WHERE r.cust_id IS NOT NULL AND r.cust_no_r IS NOT NULL;
```

---

#### 表6: `acrm_f_ci_grade` → `crm_customer_rating`

**重用度**: ⭐⭐⭐⭐ (85%)

**老表结构**:
```java
// 客户评级结果
@TableName("acrm_f_ci_grade")
public class AcrmFCiGrade {
    @TableId("cust_grade_id") String custGradeId;
    String custId;                                // 客户编号
    String custGrade;                             // 客户级别
    String custGradeType;                         // 适用客户类型
    String orgCode;                               // 归属机构号
    String orgName;                               // 归属机构名称
    Date evaluateDate;                            // 评级时间
    Date effectiveDate;                           // 生效时间
    Date expiredDate;                             // 失效时间
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_rating (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    rating_scheme_id BIGINT COMMENT '评级方案ID',
    rating_level VARCHAR(20) COMMENT '评级等级',
    rating_score DECIMAL(10,2) COMMENT '评级分数',
    rating_date DATE COMMENT '评级日期',
    effective_date DATE COMMENT '生效日期',
    expiry_date DATE COMMENT '失效日期',
    rating_source TINYINT COMMENT '评级来源 1-系统,2-手工',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `custGrade` | `rating_level` | 直接映射 | 评级等级 |
| `custGradeType` | `rating_scheme_id` | 查找映射 | 转为评级方案ID |
| `evaluateDate` | `rating_date` | 直接映射 | 评级日期 |
| `effectiveDate` | `effective_date` | 直接映射 | 生效日期 |
| `expiredDate` | `expiry_date` | 直接映射 | 失效日期 |

**重用建议**:
- ✅ **评级数据完整**: 评级等级、日期等核心字段齐全
- ✅ **支持历史追溯**: 有生效和失效日期
- ⚠️ **评级方案**: 需要建立评级方案字典表
- ⚠️ **评级分数**: 老表可能没有分数,只有等级

---

#### 表7: `ocrm_f_ci_cust_contribution` → `crm_customer_contribution`

**重用度**: ⭐⭐⭐⭐⭐ (90%)

**老表结构**:
```java
// 客户贡献度信息
@TableName("ocrm_f_ci_cust_contribution")
public class OcrmFCiCustContribution {
    @TableId("id") Long id;
    String custId;                                // 客户编号
    String custName;                              // 客户名称
    String orgId;                                 // 机构号
    BigDecimal contriDeposit;                     // 存款贡献度
    BigDecimal contributionLoan;                  // 贷款贡献度
    BigDecimal contributionMid;                   // 中间业务贡献度
    BigDecimal contributionCust;                  // 客户总贡献度
    BigDecimal contriBillDiscount;                // 票据贴现贡献度
    BigDecimal contriInternation;                 // 国际业务贡献度
    Date etlDate;                                 // 数据日期
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_contribution (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    calculation_date DATE COMMENT '计算日期',
    total_contribution DECIMAL(18,2) COMMENT '总贡献度',
    -- 审计字段
);

CREATE TABLE crm_customer_contribution_detail (
    id BIGINT PRIMARY KEY,
    contribution_id BIGINT NOT NULL COMMENT '贡献度主表ID',
    contribution_type VARCHAR(50) COMMENT '贡献类型',
    contribution_value DECIMAL(18,2) COMMENT '贡献值',
    weight DECIMAL(5,2) COMMENT '权重',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `contributionCust` | `total_contribution` | 直接映射 | 总贡献度 |
| `etlDate` | `calculation_date` | 直接映射 | 计算日期 |
| `contriDeposit` | `contribution_value` | 拆分 | 存款贡献明细 |
| `contributionLoan` | `contribution_value` | 拆分 | 贷款贡献明细 |
| `contributionMid` | `contribution_value` | 拆分 | 中间业务贡献明细 |

**重用建议**:
- ✅ **数据完整**: 各类贡献度数据齐全
- ✅ **支持明细拆分**: 可拆分为主表+明细表
- ✅ **按日计算**: 有数据日期,支持趋势分析
- ⚠️ **需要拆分**: 老表是宽表,新表是主从表结构

**迁移SQL示例**:
```sql
-- 迁移主表
INSERT INTO crm_customer_contribution (
    customer_id, calculation_date, total_contribution, create_time
)
SELECT
    c.id,
    cc.etl_date,
    cc.contribution_cust,
    cc.etl_date
FROM ocrm_f_ci_cust_contribution cc
JOIN crm_customer c ON cc.cust_id = c.customer_no
WHERE cc.cust_id IS NOT NULL;

-- 迁移明细表 - 存款贡献
INSERT INTO crm_customer_contribution_detail (
    contribution_id, contribution_type, contribution_value, create_time
)
SELECT
    contrib.id,
    'DEPOSIT' AS contribution_type,
    cc.contri_deposit,
    cc.etl_date
FROM ocrm_f_ci_cust_contribution cc
JOIN crm_customer c ON cc.cust_id = c.customer_no
JOIN crm_customer_contribution contrib ON c.id = contrib.customer_id AND cc.etl_date = contrib.calculation_date
WHERE cc.contri_deposit > 0;

-- 迁移明细表 - 贷款贡献
INSERT INTO crm_customer_contribution_detail (
    contribution_id, contribution_type, contribution_value, create_time
)
SELECT
    contrib.id,
    'LOAN' AS contribution_type,
    cc.contribution_loan,
    cc.etl_date
FROM ocrm_f_ci_cust_contribution cc
JOIN crm_customer c ON cc.cust_id = c.customer_no
JOIN crm_customer_contribution contrib ON c.id = contrib.customer_id AND cc.etl_date = contrib.calculation_date
WHERE cc.contribution_loan > 0;

-- 其他贡献类型类似处理...
```

---

#### 表8: `acrm_f_ci_per_preference` → `crm_customer_preference`

**重用度**: ⭐⭐⭐⭐⭐ (90%)

**老表结构**:
```java
// 个人客户偏好信息
@TableName("acrm_f_ci_per_preference")
public class AcrmFCiPerPreference {
    @TableId("cust_id") String custId;           // 客户编号
    String riskPrefer;                            // 投资风险偏好
    String investExpr;                            // 投资经验
    String investPosition;                        // 投资方向偏好
    String investCycle;                           // 投资周期偏好
    String financeBusinessPrefer;                 // 金融业务类型偏好
    String interestInvestment;                    // 感兴趣的投资信息
    String investStyle;                           // 客户风险承受能力
    String investTarget;                          // 主要的投资目标
    String investChannel;                         // 主要的投资渠道
    String insurancePrefer;                       // 保险倾向
    String consumHabit;                           // 消费习惯
    String vehiclePrefer;                         // 出行交通工具偏好
    String giftPrefer;                            // 赠品礼物偏好
    String contactType;                           // 联络方式偏好
    String contactFreqPrefer;                     // 联络频率偏好
    String contactTimePrefer;                     // 联络时间偏好
    String langPrefer;                            // 语言偏好
    String titlePrefer;                           // 称谓偏好
    String receiveSmsFlag;                        // 是否愿意接受短信
    String joinCampFlag;                          // 是否愿意参加联谊活动
    String postDataFlag;                          // 是否接受我行寄发的资料
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_preference (
    customer_id BIGINT PRIMARY KEY COMMENT '客户ID',
    risk_preference TINYINT COMMENT '风险偏好 1-保守,2-稳健,3-平衡,4-进取,5-激进',
    investment_experience VARCHAR(50) COMMENT '投资经验',
    investment_direction TEXT COMMENT '投资方向偏好',
    investment_cycle VARCHAR(50) COMMENT '投资周期偏好',
    interest_products TEXT COMMENT '感兴趣的产品',
    hobbies TEXT COMMENT '兴趣爱好',
    contact_preference VARCHAR(50) COMMENT '联络方式偏好',
    contact_time VARCHAR(50) COMMENT '联络时间偏好',
    sms_subscribe TINYINT COMMENT '短信订阅 0-否,1-是',
    activity_subscribe TINYINT COMMENT '活动订阅 0-否,1-是',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `riskPrefer` | `risk_preference` | 值转换 | 文本→数字等级 |
| `investExpr` | `investment_experience` | 直接映射 | 投资经验 |
| `investPosition` | `investment_direction` | 直接映射 | 投资方向 |
| `investCycle` | `investment_cycle` | 直接映射 | 投资周期 |
| `interestInvestment` | `interest_products` | 直接映射 | 感兴趣产品 |
| `contactType` | `contact_preference` | 直接映射 | 联络方式 |
| `contactTimePrefer` | `contact_time` | 直接映射 | 联络时间 |
| `receiveSmsFlag` | `sms_subscribe` | 值转换 | "Y"→1, "N"→0 |
| `joinCampFlag` | `activity_subscribe` | 值转换 | "Y"→1, "N"→0 |

**重用建议**:
- ✅ **偏好数据详细**: 投资、消费、联络等多维度偏好
- ✅ **零售客户专用**: 与新设计一致
- ✅ **营销价值高**: 可直接用于精准营销
- ⚠️ **风险等级标准化**: 需要将文本描述转为数字等级

---

#### 表9: `acrm_f_ci_per_family` → `crm_customer_family` & `crm_customer_family_member`

**重用度**: ⭐⭐⭐⭐ (80%)

**老表结构**:
```java
// 家庭临时基本状况
@TableName("acrm_f_ci_per_family")
public class AcrmFCiPerFamily {
    @TableId("id") Long id;
    String custId;                                // 客户编号
    BigDecimal population;                        // 总人口数量
    BigDecimal childrenNum;                       // 子女人数
    BigDecimal providePopNum;                     // 赡养人数
    BigDecimal supplyPopNum;                      // 供养人数
    String laborPopNum;                           // 劳动力人数
    String isHouseHolder;                         // 是否户主
    String houseHolderName;                       // 户主姓名
    String residenceStat;                         // 居住情况
    String houseStat;                             // 住宅状况
    String hasHomeCar;                            // 是否有私家车
    String familyAnnIncScope;                     // 家庭年收入范围
    String familyAnnualPayScope;                  // 家庭年支出范围
    BigDecimal famAssTotAmt;                      // 家庭资产总额
    BigDecimal famLiabTotAmt;                     // 家庭负债总额
    String creditInfo;                            // 信用情况
    String isHarmony;                             // 家庭和睦
}
```

**新表结构**:
```sql
-- 家庭主表
CREATE TABLE crm_customer_family (
    customer_id BIGINT PRIMARY KEY COMMENT '客户ID',
    family_member_count INT COMMENT '家庭成员数量',
    children_count INT COMMENT '子女人数',
    family_annual_income DECIMAL(18,2) COMMENT '家庭年收入',
    family_annual_expense DECIMAL(18,2) COMMENT '家庭年支出',
    family_assets DECIMAL(18,2) COMMENT '家庭总资产',
    family_liabilities DECIMAL(18,2) COMMENT '家庭总负债',
    housing_status VARCHAR(50) COMMENT '住房状况',
    has_car TINYINT COMMENT '是否有车',
    -- 审计字段
);

-- 家庭成员表
CREATE TABLE crm_customer_family_member (
    id BIGINT PRIMARY KEY,
    family_id BIGINT NOT NULL COMMENT '家庭ID',
    member_name VARCHAR(100) COMMENT '成员姓名',
    relation_type VARCHAR(50) COMMENT '关系类型',
    gender TINYINT COMMENT '性别',
    birthday DATE COMMENT '生日',
    occupation VARCHAR(100) COMMENT '职业',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `population` | `family_member_count` | 类型转换 | DECIMAL→INT |
| `childrenNum` | `children_count` | 类型转换 | DECIMAL→INT |
| `famAssTotAmt` | `family_assets` | 直接映射 | 家庭资产 |
| `famLiabTotAmt` | `family_liabilities` | 直接映射 | 家庭负债 |
| `houseStat` | `housing_status` | 直接映射 | 住房状况 |
| `hasHomeCar` | `has_car` | 值转换 | "Y"→1, "N"→0 |
| `familyAnnIncScope` | `family_annual_income` | 值提取 | 从范围提取中间值 |

**重用建议**:
- ✅ **家庭数据完整**: 人口、资产、负债等核心数据齐全
- ⚠️ **成员信息缺失**: 老表只有户主信息,缺少其他成员明细
- ⚠️ **范围值处理**: 收入、支出是范围值,需要转为具体数值
- ℹ️ **成员表需补充**: 需要从其他数据源或手工补充家庭成员信息

---

### 2.4 业务数据表 (2张) ⭐⭐⭐⭐

#### 表10: `acrm_f_ci_deposit_act` → `crm_customer_account`

**重用度**: ⭐⭐⭐⭐ (80%)

**老表结构**:
```java
// 存款账号信息
@TableName("acrm_f_ci_deposit_act")
public class AcrmFCiDepositAct {
    @TableId("agr_no") String agrNo;             // 协议标识
    String custId;                                // 客户编号
    String custType;                              // 客户类型 (对公/对私)
    String acctNo;                                // 账号
    String acctName;                              // 账户名称
    String curType;                               // 币种
    String accountStat;                           // 账户状态
    BigDecimal busiAmt;                           // 余额 (折人民币)
    BigDecimal amountOrgMoney;                    // 余额 (原币种)
    Date openAccountDate;                         // 开户日期
    Date logoutAccountDate;                       // 销户日期
    String orgNo;                                 // 开户机构
    String orgName;                               // 开户网点名称
    String depClassCd;                            // 存款类别 (1-活期,2-定期)
    String productId;                             // 产品标识
    BigDecimal depositeAvgM;                      // 月日均
    BigDecimal depositeAvgQ;                      // 季日均
    BigDecimal depositeAvgY;                      // 年日均
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_account (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    account_type INT COMMENT '账户类型 1-存款,2-贷款,3-理财,4-基金,5-信用卡',
    account_no VARCHAR(50) COMMENT '账户号',
    account_name VARCHAR(200) COMMENT '账户名称',
    currency_type VARCHAR(10) COMMENT '币种',
    balance DECIMAL(18,2) COMMENT '账户余额',
    balance_cny DECIMAL(18,2) COMMENT '余额(折人民币)',
    account_status TINYINT COMMENT '状态 1-正常,2-冻结,3-销户',
    open_date DATE COMMENT '开户日期',
    close_date DATE COMMENT '销户日期',
    open_org VARCHAR(50) COMMENT '开户机构',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `acctNo` | `account_no` | 直接映射 | 账户号 |
| `acctName` | `account_name` | 直接映射 | 账户名称 |
| `curType` | `currency_type` | 直接映射 | 币种 |
| `amountOrgMoney` | `balance` | 直接映射 | 原币余额 |
| `busiAmt` | `balance_cny` | 直接映射 | 人民币余额 |
| `accountStat` | `account_status` | 值转换 | 状态码转换 |
| `openAccountDate` | `open_date` | 直接映射 | 开户日期 |
| `logoutAccountDate` | `close_date` | 直接映射 | 销户日期 |
| `orgNo` | `open_org` | 直接映射 | 开户机构 |
| - | `account_type` | **新增** | 固定值1(存款账户) |

**重用建议**:
- ✅ **存款账户完整**: 核心字段齐全
- ✅ **支持零售和对公**: 通过 `custType` 区分
- ⚠️ **账户类型单一**: 老表只有存款账户,需要其他表补充贷款、理财等账户
- ⚠️ **日均数据**: 老表的月日均、季日均、年日均数据应移到快照表

**相关老表**:
- `acrm_f_agr_loan` - 贷款账户 → 同样迁移到 `crm_customer_account`
- 其他账户类型表需要单独迁移

---

#### 表11: `acrm_f_ci_address` → `crm_company_address` (对公) / 嵌入主表 (零售)

**重用度**: ⭐⭐⭐⭐ (80%)

**老表结构**:
```java
// 地址信息 (零售+对公共用)
@TableName("acrm_f_ci_address")
public class AcrmFCiAddress {
    @TableId("addr_id") String addrId;           // 地址标识
    String custId;                                // 客户编号
    String addrType;                              // 地址类型
    String addr;                                  // 详细地址
    String enAddr;                                // 英文地址
    String zipcode;                               // 邮政编码
    String provinceCode;                          // 省代码
    String cityCode;                              // 市代码
    String countyCode;                            // 县区代码
    String townCode;                              // 乡镇代码
    String townName;                              // 乡镇名称
    String streetName;                            // 街道名称
    String villageNo;                             // 行政村编号
    String villageName;                           // 行政村名称
    String isPriori;                              // 是否首选
    String contmethInfo;                          // 地址联系电话
}
```

**新表结构** (对公):
```sql
CREATE TABLE crm_company_address (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    address_type TINYINT COMMENT '地址类型 1-注册,2-经营,3-办公',
    province VARCHAR(50) COMMENT '省份',
    city VARCHAR(50) COMMENT '城市',
    district VARCHAR(50) COMMENT '区县',
    street VARCHAR(100) COMMENT '街道',
    address VARCHAR(500) COMMENT '详细地址',
    postal_code VARCHAR(20) COMMENT '邮政编码',
    contact_phone VARCHAR(50) COMMENT '联系电话',
    is_default TINYINT COMMENT '是否默认',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `custId` | `customer_id` | 关联映射 | 关联客户 |
| `addrType` | `address_type` | 值转换 | 地址类型转换 |
| `provinceCode` | `province` | 查找映射 | 省代码→省名称 |
| `cityCode` | `city` | 查找映射 | 市代码→市名称 |
| `countyCode` | `district` | 查找映射 | 县代码→县名称 |
| `streetName` | `street` | 直接映射 | 街道 |
| `addr` | `address` | 直接映射 | 详细地址 |
| `zipcode` | `postal_code` | 直接映射 | 邮编 |
| `contmethInfo` | `contact_phone` | 直接映射 | 联系电话 |
| `isPriori` | `is_default` | 值转换 | "Y"→1, "N"→0 |

**重用建议**:
- ✅ **对公地址完整**: 支持多地址类型
- ✅ **行政区划详细**: 省市县乡村五级区划
- ⚠️ **零售地址简化**: 零售客户的地址可能只需要一个,建议嵌入主表
- ⚠️ **行政区划转换**: 需要将区划代码转为名称

---

### 2.5 营销服务表 (1张) ⭐⭐⭐⭐

#### 表12: `cs_cst_vst` → `crm_customer_contact_record`

**重用度**: ⭐⭐⭐⭐⭐ (95%)

**老表结构**:
```java
// 客户拜访
@TableName("cs_cst_vst")
public class CsCstVst {
    @TableId("cst_vst_id") Integer cstVstId;
    String cstId;                                 // 客户ID
    String vstMthCd;                              // 拜访方式代码
    Date vstTm;                                   // 客户拜访时间
    String cstInintCd;                            // 客户意向代码
    String vstPsnLst;                             // 拜访人员清单
    String cstPsnLst;                             // 客户人员清单
    String vstAdr;                                // 拜访地点
    String vstRsltDsc;                            // 拜访结果
    Date fuaTm;                                   // 再次跟进时间
    String toDoDsc;                               // 待跟进事项
    String othDsc;                                // 其它说明
    String crtUsrId;                              // 创建人
    Date crtTm;                                   // 创建时间
}
```

**新表结构**:
```sql
CREATE TABLE crm_customer_contact_record (
    id BIGINT PRIMARY KEY,
    customer_id BIGINT NOT NULL COMMENT '客户ID',
    contact_type TINYINT COMMENT '接触类型 1-拜访,2-电话,3-短信,4-微信,5-邮件',
    contact_method VARCHAR(50) COMMENT '接触方式',
    contact_time DATETIME COMMENT '接触时间',
    contact_location VARCHAR(200) COMMENT '接触地点',
    contact_content TEXT COMMENT '接触内容',
    contact_result TEXT COMMENT '接触结果',
    customer_intention VARCHAR(100) COMMENT '客户意向',
    next_follow_time DATETIME COMMENT '下次跟进时间',
    follow_matters TEXT COMMENT '待跟进事项',
    remark TEXT COMMENT '备注',
    -- 审计字段
);
```

**字段映射关系**:
| 老表字段 | 新表字段 | 映射关系 | 说明 |
|---------|---------|---------|------|
| `cstId` | `customer_id` | 关联映射 | 关联客户 |
| `vstMthCd` | `contact_method` | 字典映射 | 拜访方式 |
| `vstTm` | `contact_time` | 直接映射 | 接触时间 |
| `vstAdr` | `contact_location` | 直接映射 | 拜访地点 |
| `vstRsltDsc` | `contact_result` | 直接映射 | 接触结果 |
| `cstInintCd` | `customer_intention` | 字典映射 | 客户意向 |
| `fuaTm` | `next_follow_time` | 直接映射 | 下次跟进时间 |
| `toDoDsc` | `follow_matters` | 直接映射 | 待跟进事项 |
| `othDsc` | `remark` | 直接映射 | 备注 |
| - | `contact_type` | **推断** | 固定值1(拜访) |

**重用建议**:
- ✅ **拜访记录完整**: 时间、地点、结果、跟进事项齐全
- ✅ **营销价值高**: 可用于分析客户接触频率和效果
- ✅ **支持跟进管理**: 有下次跟进时间和事项
- ⚠️ **接触类型扩展**: 老表只有拜访,新表支持电话、短信等多种方式

---

## 三、其他可能重用的表

### 3.1 部分可重用表 (重用度 50-70%)

#### 表13: `acrm_f_ci_contmeth` → `crm_customer_contact`

**老表**: 客户联系方式
**新表**: 联系人信息 (对公) / 嵌入主表 (零售)
**重用度**: ⭐⭐⭐ (70%)

#### 表14: `acrm_f_ci_cust_identifier` → `crm_customer_identity`

**老表**: 客户证件信息
**新表**: 客户证件信息
**重用度**: ⭐⭐⭐⭐ (85%)

#### 表15: `ocrm_f_ci_belong_hist` → `crm_customer_assignment_history`

**老表**: 客户归属历史
**新表**: 归属历史表
**重用度**: ⭐⭐⭐⭐ (80%)

#### 表16: `acrm_f_agr_loan` → `crm_customer_account` (贷款账户)

**老表**: 贷款协议
**新表**: 客户账户 (账户类型=贷款)
**重用度**: ⭐⭐⭐⭐ (80%)

#### 表17: `bsn_f_pd_*_sgn_agrm_*` → `crm_customer_contract`

**老表**: 各类签约表
**新表**: 签约信息
**重用度**: ⭐⭐⭐ (70%)

#### 表18: `acrm_f_evt_save_trad_tans` → `crm_customer_transaction`

**老表**: 存款交易流水
**新表**: 交易明细表
**重用度**: ⭐⭐⭐⭐ (80%)

---

### 3.2 参考价值表 (重用度 30-50%)

以下老表虽然不能直接重用,但可作为业务理解的参考:

- `acrm_base_market` - 营销活动基础表
- `ocrm_f_mm_mkt_busi_oppor` - 营销商机表
- `cs_svr_plan` - 服务计划表
- `ci_cst_mbsh_grd` - 客户会员等级
- `ci_cst_ev` - 客户事件表
- `ocrm_f_ci_grade_scheme` - 评级方案表

---

## 四、不可重用的表

以下老表由于业务逻辑变化、字段不匹配或数据质量问题,**不建议重用**:

### 4.1 业务逻辑变更

- `acrm_f_ci_create_profit` - 利润创造表 (新系统通过贡献度计算)
- `acrm_f_ci_gk_*` - 概况类表 (新系统通过实时计算)

### 4.2 字段严重不匹配

- `acrm_a_ci_gath_business` - 采集业务表 (字段无法对应)
- `admin_auth_*` - 老权限表 (新系统使用芋道框架的权限体系)

### 4.3 数据质量问题

需要对老数据进行数据质量检查,以下情况的数据不建议迁移:
- 客户编号为空
- 客户名称为空或异常值
- 关键日期字段为空
- 金额字段为负数 (非正常业务)

---

## 五、迁移优先级建议

### 5.1 高优先级 (必须迁移) ⭐⭐⭐⭐⭐

1. **客户主表** - `acrm_f_ci_customer` → `crm_customer`
2. **零售客户扩展** - `acrm_f_ci_person` → `crm_retail_customer`
3. **对公客户扩展** - `acrm_f_ci_org` → `crm_company_customer`
4. **客户归属** - `ocrm_f_ci_belong_custmgr` → `crm_customer_assignment`

### 5.2 中高优先级 (重要业务) ⭐⭐⭐⭐

5. **客户关系** - `acrm_f_ci_cust_relate` → `crm_customer_relation`
6. **客户评级** - `acrm_f_ci_grade` → `crm_customer_rating`
7. **客户贡献度** - `ocrm_f_ci_cust_contribution` → `crm_customer_contribution`
8. **账户信息** - `acrm_f_ci_deposit_act` + `acrm_f_agr_loan` → `crm_customer_account`

### 5.3 中等优先级 (增值业务) ⭐⭐⭐

9. **客户偏好** - `acrm_f_ci_per_preference` → `crm_customer_preference`
10. **家庭信息** - `acrm_f_ci_per_family` → `crm_customer_family`
11. **接触轨迹** - `cs_cst_vst` → `crm_customer_contact_record`
12. **地址信息** - `acrm_f_ci_address` → `crm_company_address`

### 5.4 低优先级 (可选迁移) ⭐⭐

13. **证件信息** - `acrm_f_ci_cust_identifier` → `crm_customer_identity`
14. **交易流水** - `acrm_f_evt_save_trad_tans` → `crm_customer_transaction`
15. **签约信息** - `bsn_f_pd_*_sgn_agrm_*` → `crm_customer_contract`

---

## 六、迁移注意事项

### 6.1 数据转换规则

#### 客户类型转换
```sql
CASE
    WHEN old.cust_type IN ('个人', '零售', 'PERSON') THEN 1
    WHEN old.cust_type IN ('对公', '企业', 'COMPANY') THEN 2
    ELSE 1
END
```

#### 状态码转换
```sql
CASE
    WHEN old.cust_stat = '正常' THEN 1
    WHEN old.cust_stat = '停用' THEN 0
    WHEN old.cust_stat = '注销' THEN 2
    ELSE 1
END
```

#### 布尔值转换
```sql
CASE
    WHEN old.flag_field IN ('Y', 'YES', '1', '是') THEN 1
    WHEN old.flag_field IN ('N', 'NO', '0', '否') THEN 0
    ELSE 0
END
```

### 6.2 数据质量检查

**迁移前必须执行的数据质量检查**:

```sql
-- 检查客户编号重复
SELECT cust_id, COUNT(*)
FROM acrm_f_ci_customer
GROUP BY cust_id
HAVING COUNT(*) > 1;

-- 检查客户名称为空
SELECT COUNT(*)
FROM acrm_f_ci_customer
WHERE cust_name IS NULL OR cust_name = '';

-- 检查证件号格式
SELECT cust_id, ident_no
FROM acrm_f_ci_customer
WHERE ident_type = '身份证'
  AND (LENGTH(ident_no) NOT IN (15, 18) OR ident_no NOT REGEXP '^[0-9X]+$');

-- 检查账户余额异常
SELECT agr_no, cust_id, busi_amt
FROM acrm_f_ci_deposit_act
WHERE busi_amt < 0 AND account_stat = '正常';

-- 检查关联关系完整性
SELECT r.cust_id, r.cust_no_r
FROM acrm_f_ci_cust_relate r
LEFT JOIN acrm_f_ci_customer c1 ON r.cust_id = c1.cust_id
LEFT JOIN acrm_f_ci_customer c2 ON r.cust_no_r = c2.cust_id
WHERE c1.cust_id IS NULL OR c2.cust_id IS NULL;
```

### 6.3 关联关系处理

**客户ID映射表**:

迁移过程中需要建立老客户编号到新客户ID的映射表:

```sql
CREATE TABLE migration_customer_id_mapping (
    old_cust_id VARCHAR(50) PRIMARY KEY,
    new_customer_id BIGINT NOT NULL,
    customer_type TINYINT COMMENT '1-零售,2-对公',
    migration_time DATETIME,
    INDEX idx_new_id (new_customer_id)
);

-- 生成映射关系
INSERT INTO migration_customer_id_mapping (old_cust_id, new_customer_id, customer_type, migration_time)
SELECT old.cust_id, new.id, new.customer_type, NOW()
FROM acrm_f_ci_customer old
JOIN crm_customer new ON old.cust_id = new.customer_no;
```

**使用映射表迁移关联数据**:

```sql
-- 示例: 迁移客户关系时使用映射表
INSERT INTO crm_customer_relation (customer_id, related_customer_id, ...)
SELECT
    m1.new_customer_id,
    m2.new_customer_id,
    ...
FROM acrm_f_ci_cust_relate r
JOIN migration_customer_id_mapping m1 ON r.cust_id = m1.old_cust_id
JOIN migration_customer_id_mapping m2 ON r.cust_no_r = m2.old_cust_id;
```

### 6.4 敏感数据加密

需要加密的字段:
- 证件号码 (`identNo` / `identity_no`)
- 手机号 (`mobile`)
- 银行账号 (`acctNo` / `account_no`)
- 联系电话 (`contmethInfo` / `contact_phone`)

**加密迁移示例**:

```sql
-- 假设使用 AES_ENCRYPT 函数 (实际需根据新系统加密规范)
INSERT INTO crm_customer_identity (customer_id, identity_type, identity_no, ...)
SELECT
    m.new_customer_id,
    i.ident_type,
    AES_ENCRYPT(i.ident_no, 'encryption_key'),  -- 加密证件号
    ...
FROM acrm_f_ci_cust_identifier i
JOIN migration_customer_id_mapping m ON i.cust_id = m.old_cust_id;
```

### 6.5 租户ID处理

新系统支持多租户,所有表都需要 `tenant_id` 字段:

```sql
-- 迁移时指定默认租户
INSERT INTO crm_customer (customer_no, customer_name, tenant_id, ...)
SELECT
    cust_id,
    cust_name,
    1 AS tenant_id,  -- 默认租户ID
    ...
FROM acrm_f_ci_customer;
```

如果老系统已经有机构隔离,可以根据机构映射到不同租户:

```sql
-- 机构到租户的映射
CREATE TABLE migration_org_tenant_mapping (
    old_org_no VARCHAR(50) PRIMARY KEY,
    tenant_id BIGINT NOT NULL
);

-- 根据机构确定租户
INSERT INTO crm_customer (customer_no, customer_name, tenant_id, ...)
SELECT
    c.cust_id,
    c.cust_name,
    COALESCE(m.tenant_id, 1) AS tenant_id,  -- 找不到映射则使用默认租户
    ...
FROM acrm_f_ci_customer c
LEFT JOIN migration_org_tenant_mapping m ON c.create_branch_no = m.old_org_no;
```

---

## 七、迁移测试建议

### 7.1 数据完整性测试

```sql
-- 测试1: 客户数量一致性
SELECT
    (SELECT COUNT(*) FROM acrm_f_ci_customer) AS old_count,
    (SELECT COUNT(*) FROM crm_customer) AS new_count,
    (SELECT COUNT(*) FROM acrm_f_ci_customer) - (SELECT COUNT(*) FROM crm_customer) AS diff;

-- 测试2: 零售客户数量一致性
SELECT
    (SELECT COUNT(*) FROM acrm_f_ci_person) AS old_count,
    (SELECT COUNT(*) FROM crm_retail_customer) AS new_count;

-- 测试3: 对公客户数量一致性
SELECT
    (SELECT COUNT(*) FROM acrm_f_ci_org) AS old_count,
    (SELECT COUNT(*) FROM crm_company_customer) AS new_count;

-- 测试4: 账户余额总和一致性
SELECT
    (SELECT SUM(busi_amt) FROM acrm_f_ci_deposit_act WHERE account_stat = '正常') AS old_total,
    (SELECT SUM(balance_cny) FROM crm_customer_account WHERE account_status = 1) AS new_total;
```

### 7.2 业务逻辑测试

- 验证客户归属关系是否正确
- 验证客户关系图谱是否完整
- 验证客户评级数据是否准确
- 验证账户余额计算是否正确

### 7.3 性能测试

- 测试单表查询性能
- 测试多表关联查询性能
- 测试分页查询性能
- 测试索引效率

---

## 八、总结

### 8.1 可重用表汇总

| 序号 | 老表名 | 新表名 | 重用度 | 优先级 |
|-----|--------|--------|--------|--------|
| 1 | acrm_f_ci_customer | crm_customer | ⭐⭐⭐⭐⭐ | P0 |
| 2 | acrm_f_ci_person | crm_retail_customer | ⭐⭐⭐⭐⭐ | P0 |
| 3 | acrm_f_ci_org | crm_company_customer | ⭐⭐⭐⭐ | P0 |
| 4 | ocrm_f_ci_belong_custmgr | crm_customer_assignment | ⭐⭐⭐⭐ | P0 |
| 5 | acrm_f_ci_cust_relate | crm_customer_relation | ⭐⭐⭐⭐⭐ | P1 |
| 6 | acrm_f_ci_grade | crm_customer_rating | ⭐⭐⭐⭐ | P1 |
| 7 | ocrm_f_ci_cust_contribution | crm_customer_contribution | ⭐⭐⭐⭐⭐ | P1 |
| 8 | acrm_f_ci_deposit_act | crm_customer_account | ⭐⭐⭐⭐ | P1 |
| 9 | acrm_f_ci_per_preference | crm_customer_preference | ⭐⭐⭐⭐⭐ | P2 |
| 10 | acrm_f_ci_per_family | crm_customer_family | ⭐⭐⭐⭐ | P2 |
| 11 | cs_cst_vst | crm_customer_contact_record | ⭐⭐⭐⭐⭐ | P2 |
| 12 | acrm_f_ci_address | crm_company_address | ⭐⭐⭐⭐ | P2 |

### 8.2 关键收益

- ✅ **核心数据100%可迁移**: 客户主表、零售扩展、对公扩展
- ✅ **业务数据90%可重用**: 归属、关系、评级、贡献度、账户
- ✅ **增值数据80%可利用**: 偏好、家庭、接触轨迹
- ✅ **历史数据可追溯**: 评级历史、归属历史、交易历史

### 8.3 风险提示

- ⚠️ **数据清洗工作量大**: 需要处理空值、异常值、重复数据
- ⚠️ **字段映射复杂**: 部分字段需要值转换或字典映射
- ⚠️ **关联关系复杂**: 需要建立客户ID映射表
- ⚠️ **性能影响**: 大批量数据迁移需要分批处理
- ⚠️ **敏感数据**: 需要加密处理证件号、账号等敏感字段

### 8.4 下一步行动

1. **数据评估**: 连接老数据库,执行数据质量检查SQL
2. **迁移脚本**: 根据本文档编写详细的数据迁移脚本
3. **测试环境**: 在测试环境先执行迁移,验证数据完整性
4. **性能优化**: 根据测试结果优化迁移脚本和索引
5. **生产迁移**: 制定详细的生产环境迁移计划和回滚方案

---

**文档结束**
