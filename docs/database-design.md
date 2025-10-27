# 客户画像系统数据库设计文档

## 1. 文档概述

### 1.1 设计说明

本文档定义了客户画像系统（CRM 360视图）的完整数据库表结构设计，支持零售客户和对公客户的全方位信息管理。

**版本信息**：
- 文档版本：v1.0
- 数据库：MySQL 8.0+
- 字符集：utf8mb4
- 排序规则：utf8mb4_unicode_ci
- 存储引擎：InnoDB

**技术框架**：
- 基于芋道快速开发平台
- 继承 BaseDO 基础字段
- 支持多租户隔离
- 支持数据权限控制

### 1.2 核心设计特点

1. **数据同步策略**：实时/准实时同步，支持多源数据接入
2. **大数据量处理**：分区表设计（交易明细、行为数据）
3. **外部数据管理**：混合缓存模式，支持本地缓存+实时刷新
4. **统计数据优化**：定时快照策略，提升查询性能
5. **扩展性设计**：预留扩展字段，支持业务灵活扩展

### 1.3 表结构总览

数据库共 **65张核心表**，分为 **12个功能模块**：

| 模块 | 表数量 | 说明 |
|------|--------|------|
| 一、客户主体层 | 2 | 客户主表、配置表 |
| 二、零售客户层 | 8 | 个人客户扩展信息 |
| 三、对公客户层 | 15 | 企业客户扩展信息 |
| 四、客户管理层 | 3 | 归属管理、转移记录 |
| 五、业务数据层 | 10 | 账户、产品、授信、交易等 |
| 六、客户评价层 | 5 | 评级、贡献度、信用 |
| 七、营销服务层 | 8 | 营销、投诉、需求、接触 |
| 八、权益积分层 | 3 | 积分、卡券、礼品 |
| 九、行为数据层 | 3 | 线上行为、重要事件、统计 |
| 十、关系网络层 | 2 | 知识图谱、客户关系 |
| 十一、标签体系层 | 3 | 标签分类、标签、关联 |
| 十二、外部数据层 | 3 | 工商、风险、供应链 |
| **总计** | **65** | - |

### 1.4 通用字段说明

所有业务表均继承 BaseDO 基础字段：

```sql
-- 通用字段（所有表都包含）
id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',
PRIMARY KEY (id)
```

### 1.5 数据字典

#### 1.5.1 客户类型（customer_type）

| 值 | 说明 |
|----|------|
| 1 | 零售客户（个人） |
| 2 | 对公客户（企业） |

#### 1.5.2 客户状态（status）

| 值 | 说明 |
|----|------|
| 0 | 潜在客户 |
| 1 | 正式客户 |
| 2 | 冻结 |
| 3 | 注销 |
| 4 | 睡眠户 |

#### 1.5.3 账户类型（account_type）

| 值 | 说明 |
|----|------|
| 101 | 活期存款 |
| 102 | 定期存款 |
| 103 | 通知存款 |
| 201 | 流动资金贷款 |
| 202 | 固定资产贷款 |
| 301 | 理财产品 |
| 302 | 基金 |
| 303 | 保险 |
| 304 | 贵金属 |
| 401 | 信用卡 |

#### 1.5.4 归属类型（assignment_type）

| 值 | 说明 |
|----|------|
| 1 | 主办 |
| 2 | 协办 |

#### 1.5.5 标签类型（tag_type）

| 值 | 说明 |
|----|------|
| 1 | 系统标签（自动打标） |
| 2 | 自定义标签（手工打标） |

#### 1.5.6 接触方式（contact_type）

| 值 | 说明 |
|----|------|
| 1 | 拜访 |
| 2 | 电话 |
| 3 | 短信 |
| 4 | 邮件 |
| 5 | 微信 |

---

## 2. 客户主体层

### 2.1 客户主表（crm_customer）

**表说明**：存储所有客户的核心信息，区分零售客户和对公客户。

```sql
CREATE TABLE crm_customer (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
    customer_no VARCHAR(32) NOT NULL COMMENT '客户编号（唯一）',
    customer_type TINYINT NOT NULL COMMENT '客户类型：1-零售客户，2-对公客户',
    customer_name VARCHAR(100) NOT NULL COMMENT '客户名称',
    customer_short_name VARCHAR(50) DEFAULT NULL COMMENT '客户简称',
    customer_level VARCHAR(20) DEFAULT NULL COMMENT '客户等级（关联字典）',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '客户状态：0-潜在，1-正式，2-冻结，3-注销，4-睡眠',
    identity_type VARCHAR(20) DEFAULT NULL COMMENT '证件类型（关联字典）',
    identity_no VARCHAR(50) DEFAULT NULL COMMENT '证件号码（加密存储）',
    mobile VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
    email VARCHAR(100) DEFAULT NULL COMMENT '电子邮箱',
    address VARCHAR(200) DEFAULT NULL COMMENT '地址',
    open_date DATE DEFAULT NULL COMMENT '开户日期',
    source_channel VARCHAR(50) DEFAULT NULL COMMENT '来源渠道',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 同步信息
    sync_time DATETIME DEFAULT NULL COMMENT '数据同步时间',
    source_system VARCHAR(50) DEFAULT NULL COMMENT '来源系统',
    source_id VARCHAR(100) DEFAULT NULL COMMENT '来源系统ID',
    version INT NOT NULL DEFAULT 0 COMMENT '数据版本号（乐观锁）',

    -- 扩展字段
    ext_field_1 VARCHAR(100) DEFAULT NULL COMMENT '扩展字段1',
    ext_field_2 VARCHAR(100) DEFAULT NULL COMMENT '扩展字段2',
    ext_field_3 VARCHAR(100) DEFAULT NULL COMMENT '扩展字段3',
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_no (customer_no, deleted) COMMENT '客户编号唯一索引',
    KEY idx_customer_type (customer_type, deleted) COMMENT '客户类型索引',
    KEY idx_customer_level (customer_level, deleted) COMMENT '客户等级索引',
    KEY idx_status (status, deleted) COMMENT '状态索引',
    KEY idx_identity (identity_no, deleted) COMMENT '证件号索引',
    KEY idx_tenant (tenant_id, customer_type, deleted) COMMENT '租户复合索引',
    KEY idx_sync_time (sync_time) COMMENT '同步时间索引',
    KEY idx_create_time (create_time) COMMENT '创建时间索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户主表';
```

### 2.2 客户配置表（crm_customer_config）

**表说明**：存储客户视图配置信息，支持简版、支行版、全行版等不同视图。

```sql
CREATE TABLE crm_customer_config (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '配置ID',
    config_name VARCHAR(100) NOT NULL COMMENT '配置名称',
    config_code VARCHAR(50) NOT NULL COMMENT '配置编码',
    config_type TINYINT NOT NULL COMMENT '配置类型：1-视图配置，2-字段配置，3-权限配置',
    customer_type TINYINT NOT NULL COMMENT '客户类型：1-零售，2-对公',
    config_content JSON NOT NULL COMMENT '配置内容（JSON格式）',
    is_default BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否默认配置',
    sort_order INT NOT NULL DEFAULT 0 COMMENT '排序',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_config_code (config_code, tenant_id, deleted) COMMENT '配置编码唯一索引',
    KEY idx_config_type (config_type, customer_type, deleted) COMMENT '配置类型索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户配置表';
```

---

## 3. 零售客户层

### 3.1 零售客户扩展表（crm_retail_customer）

**表说明**：存储零售客户（个人客户）的扩展信息。

```sql
CREATE TABLE crm_retail_customer (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID（关联crm_customer）',
    gender TINYINT DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    birthday DATE DEFAULT NULL COMMENT '出生日期',
    age INT DEFAULT NULL COMMENT '年龄',
    nationality VARCHAR(50) DEFAULT NULL COMMENT '国籍',
    nation VARCHAR(50) DEFAULT NULL COMMENT '民族',
    marital_status TINYINT DEFAULT NULL COMMENT '婚姻状况：1-未婚，2-已婚，3-离异，4-丧偶',
    education VARCHAR(50) DEFAULT NULL COMMENT '学历（关联字典）',
    political_status VARCHAR(50) DEFAULT NULL COMMENT '政治面貌',
    occupation VARCHAR(100) DEFAULT NULL COMMENT '职业',
    income_level VARCHAR(50) DEFAULT NULL COMMENT '收入水平（关联字典）',

    -- 联系信息
    home_phone VARCHAR(20) DEFAULT NULL COMMENT '家庭电话',
    office_phone VARCHAR(20) DEFAULT NULL COMMENT '办公电话',
    wechat VARCHAR(50) DEFAULT NULL COMMENT '微信号',
    qq VARCHAR(20) DEFAULT NULL COMMENT 'QQ号',

    -- 地址信息
    home_address VARCHAR(200) DEFAULT NULL COMMENT '家庭地址',
    office_address VARCHAR(200) DEFAULT NULL COMMENT '办公地址',
    postal_code VARCHAR(10) DEFAULT NULL COMMENT '邮政编码',

    -- 健康信息
    health_status VARCHAR(50) DEFAULT NULL COMMENT '健康状况',
    is_smoker BIT(1) DEFAULT NULL COMMENT '是否吸烟',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_id (customer_id, deleted) COMMENT '客户ID唯一索引',
    KEY idx_gender (gender, deleted) COMMENT '性别索引',
    KEY idx_age (age, deleted) COMMENT '年龄索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='零售客户扩展表';
```

### 3.2 客户证件信息表（crm_customer_identity）

**表说明**：存储客户的证件信息，支持多证件。

```sql
CREATE TABLE crm_customer_identity (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    identity_type VARCHAR(20) NOT NULL COMMENT '证件类型（关联字典）',
    identity_no VARCHAR(100) NOT NULL COMMENT '证件号码（加密存储）',
    identity_name VARCHAR(100) DEFAULT NULL COMMENT '证件姓名',
    issuing_authority VARCHAR(100) DEFAULT NULL COMMENT '发证机关',
    issue_date DATE DEFAULT NULL COMMENT '发证日期',
    expiry_date DATE DEFAULT NULL COMMENT '到期日期',
    is_long_term BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否长期有效',
    is_primary BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否主证件',
    identity_img_front VARCHAR(200) DEFAULT NULL COMMENT '证件正面图片URL',
    identity_img_back VARCHAR(200) DEFAULT NULL COMMENT '证件背面图片URL',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1-有效，2-过期，3-作废',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_identity_no (identity_no, deleted) COMMENT '证件号索引',
    KEY idx_expiry_date (expiry_date, deleted) COMMENT '到期日期索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户证件信息表';
```

### 3.3 客户工作信息表（crm_customer_work）

**表说明**：存储零售客户的工作信息。

```sql
CREATE TABLE crm_customer_work (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    work_type TINYINT NOT NULL COMMENT '工作类型：1-在职，2-离职，3-退休',
    company_name VARCHAR(200) DEFAULT NULL COMMENT '工作单位',
    company_type VARCHAR(50) DEFAULT NULL COMMENT '单位性质（关联字典）',
    industry VARCHAR(50) DEFAULT NULL COMMENT '所属行业（关联字典）',
    department VARCHAR(100) DEFAULT NULL COMMENT '部门',
    position VARCHAR(100) DEFAULT NULL COMMENT '职务',
    job_title VARCHAR(100) DEFAULT NULL COMMENT '职称',
    work_years INT DEFAULT NULL COMMENT '工作年限',
    monthly_income DECIMAL(18, 2) DEFAULT NULL COMMENT '月收入',
    annual_income DECIMAL(18, 2) DEFAULT NULL COMMENT '年收入',

    -- 联系信息
    company_phone VARCHAR(20) DEFAULT NULL COMMENT '单位电话',
    company_address VARCHAR(200) DEFAULT NULL COMMENT '单位地址',

    -- 时间信息
    entry_date DATE DEFAULT NULL COMMENT '入职日期',
    leave_date DATE DEFAULT NULL COMMENT '离职日期',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_company_name (company_name, deleted) COMMENT '单位名称索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户工作信息表';
```

### 3.4 客户经营信息表（crm_customer_business）

**表说明**：存储零售客户的个体经营信息。

```sql
CREATE TABLE crm_customer_business (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    business_name VARCHAR(200) NOT NULL COMMENT '经营主体名称',
    business_type VARCHAR(50) DEFAULT NULL COMMENT '经营类型（关联字典）',
    industry VARCHAR(50) DEFAULT NULL COMMENT '所属行业（关联字典）',
    business_scope VARCHAR(500) DEFAULT NULL COMMENT '经营范围',
    registered_capital DECIMAL(18, 2) DEFAULT NULL COMMENT '注册资本',
    annual_revenue DECIMAL(18, 2) DEFAULT NULL COMMENT '年营业额',
    employee_count INT DEFAULT NULL COMMENT '员工人数',

    -- 证照信息
    business_license_no VARCHAR(50) DEFAULT NULL COMMENT '营业执照号',
    license_issue_date DATE DEFAULT NULL COMMENT '发证日期',
    license_expiry_date DATE DEFAULT NULL COMMENT '到期日期',

    -- 地址信息
    business_address VARCHAR(200) DEFAULT NULL COMMENT '经营地址',
    registered_address VARCHAR(200) DEFAULT NULL COMMENT '注册地址',

    -- 联系信息
    contact_phone VARCHAR(20) DEFAULT NULL COMMENT '联系电话',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_business_name (business_name, deleted) COMMENT '经营主体名称索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户经营信息表';
```

### 3.5 客户家庭信息表（crm_customer_family）

**表说明**：存储客户的家庭基本信息。

```sql
CREATE TABLE crm_customer_family (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    family_member_count INT DEFAULT NULL COMMENT '家庭成员数量',
    family_annual_income DECIMAL(18, 2) DEFAULT NULL COMMENT '家庭年收入',
    family_assets DECIMAL(18, 2) DEFAULT NULL COMMENT '家庭资产',
    family_liabilities DECIMAL(18, 2) DEFAULT NULL COMMENT '家庭负债',
    housing_status TINYINT DEFAULT NULL COMMENT '住房状况：1-自有，2-租赁，3-其他',
    housing_area DECIMAL(10, 2) DEFAULT NULL COMMENT '住房面积',
    vehicle_count INT DEFAULT NULL COMMENT '车辆数量',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_id (customer_id, deleted) COMMENT '客户ID唯一索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户家庭信息表';
```

### 3.6 客户家庭成员表（crm_customer_family_member）

**表说明**：存储客户的家庭成员详细信息。

```sql
CREATE TABLE crm_customer_family_member (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    family_id BIGINT(20) NOT NULL COMMENT '家庭信息ID（关联crm_customer_family）',
    member_name VARCHAR(100) NOT NULL COMMENT '成员姓名',
    relation_type VARCHAR(50) NOT NULL COMMENT '关系类型（关联字典）',
    gender TINYINT DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    birthday DATE DEFAULT NULL COMMENT '出生日期',
    age INT DEFAULT NULL COMMENT '年龄',
    identity_type VARCHAR(20) DEFAULT NULL COMMENT '证件类型',
    identity_no VARCHAR(100) DEFAULT NULL COMMENT '证件号码（加密）',
    mobile VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
    occupation VARCHAR(100) DEFAULT NULL COMMENT '职业',
    work_unit VARCHAR(200) DEFAULT NULL COMMENT '工作单位',
    annual_income DECIMAL(18, 2) DEFAULT NULL COMMENT '年收入',
    is_customer BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否本行客户',
    related_customer_id BIGINT(20) DEFAULT NULL COMMENT '关联客户ID（如果是本行客户）',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_family_id (family_id, deleted) COMMENT '家庭ID索引',
    KEY idx_relation_type (relation_type, deleted) COMMENT '关系类型索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户家庭成员表';
```

### 3.7 客户偏好表（crm_customer_preference）

**表说明**：存储客户的个人兴趣爱好和投资偏好。

```sql
CREATE TABLE crm_customer_preference (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',

    -- 兴趣爱好
    hobbies VARCHAR(500) DEFAULT NULL COMMENT '兴趣爱好',
    sports VARCHAR(200) DEFAULT NULL COMMENT '运动偏好',
    reading VARCHAR(200) DEFAULT NULL COMMENT '阅读偏好',
    travel VARCHAR(200) DEFAULT NULL COMMENT '旅游偏好',

    -- 投资偏好
    risk_preference TINYINT DEFAULT NULL COMMENT '风险偏好：1-保守，2-稳健，3-平衡，4-积极，5-激进',
    investment_experience VARCHAR(50) DEFAULT NULL COMMENT '投资经验（关联字典）',
    investment_horizon VARCHAR(50) DEFAULT NULL COMMENT '投资期限偏好（关联字典）',
    preferred_products VARCHAR(500) DEFAULT NULL COMMENT '偏好产品类型',
    investment_goal VARCHAR(500) DEFAULT NULL COMMENT '投资目标',

    -- 消费偏好
    consumption_level VARCHAR(50) DEFAULT NULL COMMENT '消费水平（关联字典）',
    consumption_habits VARCHAR(500) DEFAULT NULL COMMENT '消费习惯',

    -- 渠道偏好
    preferred_channel VARCHAR(50) DEFAULT NULL COMMENT '偏好渠道（关联字典）',
    preferred_contact_time VARCHAR(100) DEFAULT NULL COMMENT '偏好联系时间',
    preferred_contact_way VARCHAR(50) DEFAULT NULL COMMENT '偏好联系方式（关联字典）',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_id (customer_id, deleted) COMMENT '客户ID唯一索引',
    KEY idx_risk_preference (risk_preference, deleted) COMMENT '风险偏好索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户偏好表';
```

### 3.8 客户大事记表（crm_customer_event）

**表说明**：记录客户的重大事件信息。

```sql
CREATE TABLE crm_customer_event (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    event_category VARCHAR(50) NOT NULL COMMENT '事件分类（关联字典）',
    event_type VARCHAR(50) NOT NULL COMMENT '事件类型（关联字典）',
    event_title VARCHAR(200) NOT NULL COMMENT '事件标题',
    event_content TEXT DEFAULT NULL COMMENT '事件内容',
    event_date DATE NOT NULL COMMENT '事件日期',
    event_importance TINYINT DEFAULT 3 COMMENT '重要程度：1-非常重要，2-重要，3-一般，4-不重要',
    related_person VARCHAR(100) DEFAULT NULL COMMENT '相关人员',
    related_org VARCHAR(100) DEFAULT NULL COMMENT '相关机构',
    attachment_urls VARCHAR(500) DEFAULT NULL COMMENT '附件URL（多个用逗号分隔）',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, event_date, deleted) COMMENT '客户ID+事件日期索引',
    KEY idx_event_category (event_category, deleted) COMMENT '事件分类索引',
    KEY idx_event_date (event_date, deleted) COMMENT '事件日期索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户大事记表';
```

---

## 4. 对公客户层

### 4.1 对公客户扩展表（crm_company_customer）

**表说明**：存储对公客户（企业客户）的扩展信息。

```sql
CREATE TABLE crm_company_customer (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID（关联crm_customer）',

    -- 基本信息
    unified_social_credit_code VARCHAR(50) DEFAULT NULL COMMENT '统一社会信用代码',
    organization_code VARCHAR(50) DEFAULT NULL COMMENT '组织机构代码',
    tax_number VARCHAR(50) DEFAULT NULL COMMENT '纳税人识别号',
    registered_capital DECIMAL(18, 2) DEFAULT NULL COMMENT '注册资本',
    registered_capital_currency VARCHAR(10) DEFAULT 'CNY' COMMENT '注册资本币种',
    paid_capital DECIMAL(18, 2) DEFAULT NULL COMMENT '实缴资本',

    -- 企业分类
    enterprise_type VARCHAR(50) DEFAULT NULL COMMENT '企业类型（关联字典）',
    enterprise_nature VARCHAR(50) DEFAULT NULL COMMENT '企业性质（关联字典）',
    enterprise_scale VARCHAR(50) DEFAULT NULL COMMENT '企业规模（关联字典）',
    industry_category_1 VARCHAR(50) DEFAULT NULL COMMENT '行业一级分类',
    industry_category_2 VARCHAR(50) DEFAULT NULL COMMENT '行业二级分类',
    industry_category_3 VARCHAR(50) DEFAULT NULL COMMENT '行业三级分类',

    -- 经营信息
    business_scope TEXT DEFAULT NULL COMMENT '经营范围',
    annual_revenue DECIMAL(18, 2) DEFAULT NULL COMMENT '年营业额',
    annual_profit DECIMAL(18, 2) DEFAULT NULL COMMENT '年利润',
    employee_count INT DEFAULT NULL COMMENT '员工人数',
    establish_date DATE DEFAULT NULL COMMENT '成立日期',
    business_term_start DATE DEFAULT NULL COMMENT '营业期限开始',
    business_term_end DATE DEFAULT NULL COMMENT '营业期限结束',
    is_long_term BIT(1) DEFAULT b'0' COMMENT '是否长期有效',

    -- 法人信息
    legal_person_name VARCHAR(100) DEFAULT NULL COMMENT '法定代表人姓名',
    legal_person_identity VARCHAR(50) DEFAULT NULL COMMENT '法人证件号码（加密）',
    legal_person_mobile VARCHAR(20) DEFAULT NULL COMMENT '法人手机号',

    -- 注册地址
    registered_province VARCHAR(50) DEFAULT NULL COMMENT '注册省份',
    registered_city VARCHAR(50) DEFAULT NULL COMMENT '注册城市',
    registered_district VARCHAR(50) DEFAULT NULL COMMENT '注册区县',
    registered_address VARCHAR(200) DEFAULT NULL COMMENT '注册地址',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_id (customer_id, deleted) COMMENT '客户ID唯一索引',
    KEY idx_credit_code (unified_social_credit_code, deleted) COMMENT '信用代码索引',
    KEY idx_enterprise_type (enterprise_type, deleted) COMMENT '企业类型索引',
    KEY idx_industry (industry_category_1, industry_category_2, deleted) COMMENT '行业分类索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户扩展表';
```

### 4.2 组织架构表（crm_company_organization）

**表说明**：存储企业的组织架构信息。

```sql
CREATE TABLE crm_company_organization (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    parent_id BIGINT(20) DEFAULT 0 COMMENT '父节点ID（0表示根节点）',
    org_name VARCHAR(200) NOT NULL COMMENT '组织名称',
    org_code VARCHAR(50) DEFAULT NULL COMMENT '组织编码',
    org_type TINYINT NOT NULL COMMENT '组织类型：1-总公司，2-分公司，3-子公司，4-部门',
    org_level INT NOT NULL DEFAULT 1 COMMENT '组织层级',
    sort_order INT NOT NULL DEFAULT 0 COMMENT '排序',
    leader_name VARCHAR(100) DEFAULT NULL COMMENT '负责人姓名',
    leader_mobile VARCHAR(20) DEFAULT NULL COMMENT '负责人电话',
    address VARCHAR(200) DEFAULT NULL COMMENT '办公地址',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：0-停用，1-正常',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_parent_id (parent_id, deleted) COMMENT '父节点ID索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织架构表';
```

### 4.3 客户地址信息表（crm_company_address）

**表说明**：存储对公客户的地址信息，支持多地址。

```sql
CREATE TABLE crm_company_address (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    address_type TINYINT NOT NULL COMMENT '地址类型：1-注册地址，2-经营地址，3-办公地址，4-联系地址',
    province VARCHAR(50) DEFAULT NULL COMMENT '省份',
    city VARCHAR(50) DEFAULT NULL COMMENT '城市',
    district VARCHAR(50) DEFAULT NULL COMMENT '区县',
    street VARCHAR(100) DEFAULT NULL COMMENT '街道',
    detail_address VARCHAR(200) NOT NULL COMMENT '详细地址',
    postal_code VARCHAR(10) DEFAULT NULL COMMENT '邮政编码',
    longitude DECIMAL(10, 6) DEFAULT NULL COMMENT '经度',
    latitude DECIMAL(10, 6) DEFAULT NULL COMMENT '纬度',
    is_primary BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否主地址',
    contact_person VARCHAR(100) DEFAULT NULL COMMENT '联系人',
    contact_phone VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, address_type, deleted) COMMENT '客户ID+地址类型索引',
    KEY idx_city (city, deleted) COMMENT '城市索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户地址信息表';
```

### 4.4 联系人信息表（crm_company_contact）

**表说明**：存储对公客户的联系人信息。

```sql
CREATE TABLE crm_company_contact (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    contact_type VARCHAR(50) NOT NULL COMMENT '联系人类型（关联字典）',
    contact_name VARCHAR(100) NOT NULL COMMENT '联系人姓名',
    gender TINYINT DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    department VARCHAR(100) DEFAULT NULL COMMENT '部门',
    position VARCHAR(100) DEFAULT NULL COMMENT '职务',
    mobile VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
    phone VARCHAR(20) DEFAULT NULL COMMENT '固定电话',
    email VARCHAR(100) DEFAULT NULL COMMENT '电子邮箱',
    wechat VARCHAR(50) DEFAULT NULL COMMENT '微信号',
    qq VARCHAR(20) DEFAULT NULL COMMENT 'QQ号',
    birthday DATE DEFAULT NULL COMMENT '生日',
    id_card VARCHAR(50) DEFAULT NULL COMMENT '身份证号（加密）',
    is_primary BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否主联系人',
    is_decision_maker BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否决策人',
    hobbies VARCHAR(500) DEFAULT NULL COMMENT '兴趣爱好',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_contact_type (contact_type, deleted) COMMENT '联系人类型索引',
    KEY idx_mobile (mobile, deleted) COMMENT '手机号索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='联系人信息表';
```

### 4.5 股票发行信息表（crm_company_stock）

**表说明**：存储对公客户的股票发行信息。

```sql
CREATE TABLE crm_company_stock (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    stock_code VARCHAR(20) NOT NULL COMMENT '股票代码',
    stock_name VARCHAR(100) NOT NULL COMMENT '股票名称',
    stock_exchange VARCHAR(50) NOT NULL COMMENT '上市交易所',
    listing_date DATE DEFAULT NULL COMMENT '上市日期',
    issue_price DECIMAL(18, 4) DEFAULT NULL COMMENT '发行价格',
    total_shares BIGINT DEFAULT NULL COMMENT '总股本',
    circulating_shares BIGINT DEFAULT NULL COMMENT '流通股本',
    market_value DECIMAL(18, 2) DEFAULT NULL COMMENT '市值',
    industry VARCHAR(100) DEFAULT NULL COMMENT '所属行业',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1-正常，2-停牌，3-退市',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_stock_code (stock_code, deleted) COMMENT '股票代码索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='股票发行信息表';
```

### 4.6 债券发行信息表（crm_company_bond）

**表说明**：存储对公客户的债券发行信息。

```sql
CREATE TABLE crm_company_bond (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    bond_code VARCHAR(20) NOT NULL COMMENT '债券代码',
    bond_name VARCHAR(100) NOT NULL COMMENT '债券名称',
    bond_type VARCHAR(50) NOT NULL COMMENT '债券类型（关联字典）',
    issue_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '发行金额',
    issue_rate DECIMAL(10, 4) DEFAULT NULL COMMENT '发行利率（%）',
    issue_date DATE DEFAULT NULL COMMENT '发行日期',
    maturity_date DATE DEFAULT NULL COMMENT '到期日期',
    bond_term INT DEFAULT NULL COMMENT '债券期限（月）',
    credit_rating VARCHAR(20) DEFAULT NULL COMMENT '信用评级',
    underwriter VARCHAR(200) DEFAULT NULL COMMENT '承销商',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1-正常，2-违约，3-到期',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_bond_code (bond_code, deleted) COMMENT '债券代码索引',
    KEY idx_maturity_date (maturity_date, deleted) COMMENT '到期日期索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='债券发行信息表';
```

### 4.7 客户财务信息表（crm_company_finance）

**表说明**：存储对公客户的财务报表信息。

```sql
CREATE TABLE crm_company_finance (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    report_type TINYINT NOT NULL COMMENT '报表类型：1-资产负债表，2-利润表，3-现金流量表',
    report_period VARCHAR(20) NOT NULL COMMENT '报告期间（如：2024Q1, 2024）',
    report_year INT NOT NULL COMMENT '报告年度',
    report_quarter TINYINT DEFAULT NULL COMMENT '报告季度（1-4）',

    -- 财务数据（JSON格式存储，支持灵活扩展）
    finance_data JSON NOT NULL COMMENT '财务数据（JSON格式）',

    -- 关键指标（冗余存储，便于查询）
    total_assets DECIMAL(18, 2) DEFAULT NULL COMMENT '总资产',
    total_liabilities DECIMAL(18, 2) DEFAULT NULL COMMENT '总负债',
    net_assets DECIMAL(18, 2) DEFAULT NULL COMMENT '净资产',
    operating_revenue DECIMAL(18, 2) DEFAULT NULL COMMENT '营业收入',
    net_profit DECIMAL(18, 2) DEFAULT NULL COMMENT '净利润',
    cash_flow DECIMAL(18, 2) DEFAULT NULL COMMENT '现金流',

    -- 审计信息
    is_audited BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否审计',
    audit_firm VARCHAR(200) DEFAULT NULL COMMENT '审计机构',
    audit_opinion VARCHAR(100) DEFAULT NULL COMMENT '审计意见',

    -- 附件信息
    attachment_urls VARCHAR(500) DEFAULT NULL COMMENT '附件URL（多个用逗号分隔）',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, report_type, report_period, deleted) COMMENT '客户ID+报表类型+期间索引',
    KEY idx_report_year (report_year, report_quarter, deleted) COMMENT '报告年度+季度索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户财务信息表';
```

### 4.8 客户项目信息表（crm_company_project）

**表说明**：存储对公客户的项目信息。

```sql
CREATE TABLE crm_company_project (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    project_name VARCHAR(200) NOT NULL COMMENT '项目名称',
    project_code VARCHAR(50) DEFAULT NULL COMMENT '项目编号',
    project_type VARCHAR(50) DEFAULT NULL COMMENT '项目类型（关联字典）',
    project_scale DECIMAL(18, 2) DEFAULT NULL COMMENT '项目规模',
    project_investment DECIMAL(18, 2) DEFAULT NULL COMMENT '项目投资额',
    project_leader VARCHAR(100) DEFAULT NULL COMMENT '项目负责人',
    project_location VARCHAR(200) DEFAULT NULL COMMENT '项目地点',
    start_date DATE DEFAULT NULL COMMENT '开始日期',
    end_date DATE DEFAULT NULL COMMENT '结束日期',
    completion_rate DECIMAL(5, 2) DEFAULT NULL COMMENT '完成进度（%）',
    project_status TINYINT NOT NULL DEFAULT 1 COMMENT '项目状态：1-筹备，2-在建，3-完工，4-终止',
    project_description TEXT DEFAULT NULL COMMENT '项目描述',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_project_code (project_code, deleted) COMMENT '项目编号索引',
    KEY idx_project_status (project_status, deleted) COMMENT '项目状态索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户项目信息表';
```

### 4.9 客户他行信息表（crm_company_other_bank）

**表说明**：存储对公客户与其他银行的合作信息。

```sql
CREATE TABLE crm_company_other_bank (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    bank_name VARCHAR(100) NOT NULL COMMENT '银行名称',
    bank_type VARCHAR(50) DEFAULT NULL COMMENT '银行类型（关联字典）',
    cooperation_type VARCHAR(50) DEFAULT NULL COMMENT '合作类型（关联字典）',
    account_no VARCHAR(50) DEFAULT NULL COMMENT '账号',
    credit_limit DECIMAL(18, 2) DEFAULT NULL COMMENT '授信额度',
    used_credit DECIMAL(18, 2) DEFAULT NULL COMMENT '已用额度',
    deposit_balance DECIMAL(18, 2) DEFAULT NULL COMMENT '存款余额',
    loan_balance DECIMAL(18, 2) DEFAULT NULL COMMENT '贷款余额',
    cooperation_start_date DATE DEFAULT NULL COMMENT '合作开始日期',
    is_main_bank BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否主办行',
    contact_person VARCHAR(100) DEFAULT NULL COMMENT '对接人',
    contact_phone VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_bank_name (bank_name, deleted) COMMENT '银行名称索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户他行信息表';
```

### 4.10 对公客户股东信息表（crm_company_shareholder）

**表说明**：存储对公客户的股东信息。

```sql
CREATE TABLE crm_company_shareholder (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    shareholder_type TINYINT NOT NULL COMMENT '股东类型：1-自然人，2-法人',
    shareholder_name VARCHAR(200) NOT NULL COMMENT '股东姓名/名称',
    identity_no VARCHAR(50) DEFAULT NULL COMMENT '证件号码（加密）',
    shareholding_ratio DECIMAL(10, 4) DEFAULT NULL COMMENT '持股比例（%）',
    shareholding_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '持股金额',
    investment_date DATE DEFAULT NULL COMMENT '投资日期',
    is_actual_controller BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否实际控制人',
    is_related_customer BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否本行客户',
    related_customer_id BIGINT(20) DEFAULT NULL COMMENT '关联客户ID',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_shareholder_name (shareholder_name, deleted) COMMENT '股东名称索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户股东信息表';
```

### 4.11 对公客户高管信息表（crm_company_executive）

**表说明**：存储对公客户的高管信息（含实控人）。

```sql
CREATE TABLE crm_company_executive (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    executive_type TINYINT NOT NULL COMMENT '高管类型：1-法人，2-董事长，3-总经理，4-副总，5-财务总监，6-其他',
    executive_name VARCHAR(100) NOT NULL COMMENT '姓名',
    gender TINYINT DEFAULT NULL COMMENT '性别：0-未知，1-男，2-女',
    identity_no VARCHAR(50) DEFAULT NULL COMMENT '身份证号（加密）',
    mobile VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    email VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    education VARCHAR(50) DEFAULT NULL COMMENT '学历',
    title VARCHAR(100) DEFAULT NULL COMMENT '职称',
    appointment_date DATE DEFAULT NULL COMMENT '任职日期',
    resignation_date DATE DEFAULT NULL COMMENT '离职日期',
    is_actual_controller BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否实际控制人',
    is_related_customer BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否本行客户',
    related_customer_id BIGINT(20) DEFAULT NULL COMMENT '关联客户ID',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 外部数据字段
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',
    refresh_status TINYINT DEFAULT 0 COMMENT '刷新状态：0-有效，1-过期，2-刷新中',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_executive_name (executive_name, deleted) COMMENT '高管姓名索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户高管信息表';
```

### 4.12 对公客户工商信息表（crm_company_industrial_info）

**表说明**：存储从外部数据源获取的工商信息。

```sql
CREATE TABLE crm_company_industrial_info (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',

    -- 工商基本信息
    company_name VARCHAR(200) NOT NULL COMMENT '企业名称',
    credit_code VARCHAR(50) DEFAULT NULL COMMENT '统一社会信用代码',
    register_status VARCHAR(50) DEFAULT NULL COMMENT '登记状态',
    establish_date DATE DEFAULT NULL COMMENT '成立日期',
    registered_capital VARCHAR(100) DEFAULT NULL COMMENT '注册资本',
    paid_capital VARCHAR(100) DEFAULT NULL COMMENT '实缴资本',
    legal_person VARCHAR(100) DEFAULT NULL COMMENT '法定代表人',
    enterprise_type VARCHAR(100) DEFAULT NULL COMMENT '企业类型',
    industry VARCHAR(100) DEFAULT NULL COMMENT '所属行业',
    business_scope TEXT DEFAULT NULL COMMENT '经营范围',

    -- 变更信息
    change_records JSON DEFAULT NULL COMMENT '变更记录（JSON格式）',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT NULL COMMENT '数据来源',

    -- 缓存控制
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',
    refresh_status TINYINT DEFAULT 0 COMMENT '刷新状态：0-有效，1-过期，2-刷新中',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_customer_id (customer_id, deleted) COMMENT '客户ID唯一索引',
    KEY idx_credit_code (credit_code, deleted) COMMENT '信用代码索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户工商信息表';
```

### 4.13 对公客户水电费信息表（crm_company_utility）

**表说明**：存储对公客户的水电费信息（外部数据）。

```sql
CREATE TABLE crm_company_utility (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    utility_type TINYINT NOT NULL COMMENT '类型：1-水费，2-电费，3-燃气费',
    billing_period VARCHAR(20) NOT NULL COMMENT '账单周期（如：2024-01）',
    billing_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '账单金额',
    usage_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '用量',
    payment_status TINYINT NOT NULL DEFAULT 1 COMMENT '缴费状态：1-未缴，2-已缴，3-逾期',
    payment_date DATE DEFAULT NULL COMMENT '缴费日期',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT NULL COMMENT '数据来源',

    -- 缓存控制
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, utility_type, billing_period, deleted) COMMENT '客户ID+类型+周期索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户水电费信息表';
```

### 4.14 对公客户外部评级表（crm_company_external_rating）

**表说明**：存储对公客户的外部评级信息。

```sql
CREATE TABLE crm_company_external_rating (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    rating_agency VARCHAR(100) NOT NULL COMMENT '评级机构',
    rating_type VARCHAR(50) NOT NULL COMMENT '评级类型（关联字典）',
    rating_level VARCHAR(20) NOT NULL COMMENT '评级等级',
    rating_outlook VARCHAR(20) DEFAULT NULL COMMENT '评级展望',
    rating_date DATE NOT NULL COMMENT '评级日期',
    effective_date DATE DEFAULT NULL COMMENT '生效日期',
    expiry_date DATE DEFAULT NULL COMMENT '失效日期',
    rating_report_url VARCHAR(200) DEFAULT NULL COMMENT '评级报告URL',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT NULL COMMENT '数据来源',

    -- 缓存控制
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',
    refresh_status TINYINT DEFAULT 0 COMMENT '刷新状态：0-有效，1-过期，2-刷新中',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_rating_date (rating_date, deleted) COMMENT '评级日期索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户外部评级表';
```

### 4.15 对公客户风险信息表（crm_company_risk）

**表说明**：存储对公客户的风险信息（外部数据）。

```sql
CREATE TABLE crm_company_risk (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    risk_type VARCHAR(50) NOT NULL COMMENT '风险类型：行政处罚、裁判文书、开庭公告、失信被执行、经营异常等',
    risk_level TINYINT NOT NULL DEFAULT 1 COMMENT '风险等级：1-低，2-中，3-高，4-严重',
    risk_title VARCHAR(200) NOT NULL COMMENT '风险标题',
    risk_content TEXT DEFAULT NULL COMMENT '风险内容',
    risk_date DATE DEFAULT NULL COMMENT '风险发生日期',
    risk_status TINYINT NOT NULL DEFAULT 1 COMMENT '风险状态：1-有效，2-解除',
    related_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '涉及金额',
    punishment_org VARCHAR(200) DEFAULT NULL COMMENT '处罚机关/法院',
    case_no VARCHAR(100) DEFAULT NULL COMMENT '案号',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT NULL COMMENT '数据来源',
    source_url VARCHAR(200) DEFAULT NULL COMMENT '来源URL',

    -- 缓存控制
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',
    refresh_status TINYINT DEFAULT 0 COMMENT '刷新状态：0-有效，1-过期，2-刷新中',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, risk_type, deleted) COMMENT '客户ID+风险类型索引',
    KEY idx_risk_level (risk_level, deleted) COMMENT '风险等级索引',
    KEY idx_risk_date (risk_date, deleted) COMMENT '风险日期索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户风险信息表';
```

### 4.16 对公客户供应链信息表（crm_company_supply_chain）

**表说明**：存储对公客户的供应链上下游信息（外部数据）。

```sql
CREATE TABLE crm_company_supply_chain (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    chain_type TINYINT NOT NULL COMMENT '链条类型：1-上游供应商，2-下游客户',
    related_company_name VARCHAR(200) NOT NULL COMMENT '关联企业名称',
    credit_code VARCHAR(50) DEFAULT NULL COMMENT '统一社会信用代码',
    cooperation_years INT DEFAULT NULL COMMENT '合作年限',
    annual_transaction_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '年交易金额',
    is_core_partner BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否核心合作伙伴',
    is_related_customer BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否本行客户',
    related_customer_id BIGINT(20) DEFAULT NULL COMMENT '关联客户ID',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT NULL COMMENT '数据来源',

    -- 缓存控制
    cache_time DATETIME DEFAULT NULL COMMENT '缓存时间',
    expire_time DATETIME DEFAULT NULL COMMENT '过期时间',
    refresh_status TINYINT DEFAULT 0 COMMENT '刷新状态：0-有效，1-过期，2-刷新中',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, chain_type, deleted) COMMENT '客户ID+链条类型索引',
    KEY idx_related_company (related_company_name, deleted) COMMENT '关联企业索引',
    KEY idx_expire_time (expire_time, deleted) COMMENT '过期时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='对公客户供应链信息表';
```

---

## 5. 客户管理层

### 5.1 客户归属表（crm_customer_assignment）

**表说明**：存储客户的归属信息，支持主协办模式。

```sql
CREATE TABLE crm_customer_assignment (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    assignment_type TINYINT NOT NULL DEFAULT 1 COMMENT '归属类型：1-主办，2-协办',
    org_id BIGINT(20) NOT NULL COMMENT '归属机构ID',
    org_name VARCHAR(100) NOT NULL COMMENT '归属机构名称',
    manager_id BIGINT(20) DEFAULT NULL COMMENT '归属客户经理ID',
    manager_name VARCHAR(100) DEFAULT NULL COMMENT '归属客户经理姓名',
    area_id BIGINT(20) DEFAULT NULL COMMENT '归属片区ID',
    area_name VARCHAR(100) DEFAULT NULL COMMENT '归属片区名称',
    grid_id BIGINT(20) DEFAULT NULL COMMENT '归属网格ID',
    grid_name VARCHAR(100) DEFAULT NULL COMMENT '归属网格名称',
    customer_group_id BIGINT(20) DEFAULT NULL COMMENT '归属客户群ID',
    customer_group_name VARCHAR(100) DEFAULT NULL COMMENT '归属客户群名称',
    assignment_date DATE NOT NULL COMMENT '归属日期',
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, assignment_type, deleted) COMMENT '客户ID+归属类型索引',
    KEY idx_org_id (org_id, deleted) COMMENT '机构ID索引',
    KEY idx_manager_id (manager_id, deleted) COMMENT '客户经理ID索引',
    KEY idx_tenant_manager (tenant_id, manager_id, assignment_type, deleted) COMMENT '租户+客户经理复合索引',
    KEY idx_tenant_org (tenant_id, org_id, deleted) COMMENT '租户+机构复合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户归属表';
```

### 5.2 客户归属历史表（crm_customer_assignment_history）

**表说明**：记录客户归属的变更历史。

```sql
CREATE TABLE crm_customer_assignment_history (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    change_type TINYINT NOT NULL COMMENT '变更类型：1-新增归属，2-变更归属，3-取消归属',

    -- 变更前信息
    old_assignment_type TINYINT DEFAULT NULL COMMENT '原归属类型',
    old_org_id BIGINT(20) DEFAULT NULL COMMENT '原归属机构ID',
    old_org_name VARCHAR(100) DEFAULT NULL COMMENT '原归属机构名称',
    old_manager_id BIGINT(20) DEFAULT NULL COMMENT '原客户经理ID',
    old_manager_name VARCHAR(100) DEFAULT NULL COMMENT '原客户经理姓名',

    -- 变更后信息
    new_assignment_type TINYINT DEFAULT NULL COMMENT '新归属类型',
    new_org_id BIGINT(20) DEFAULT NULL COMMENT '新归属机构ID',
    new_org_name VARCHAR(100) DEFAULT NULL COMMENT '新归属机构名称',
    new_manager_id BIGINT(20) DEFAULT NULL COMMENT '新客户经理ID',
    new_manager_name VARCHAR(100) DEFAULT NULL COMMENT '新客户经理姓名',

    change_reason VARCHAR(500) DEFAULT NULL COMMENT '变更原因',
    change_date DATE NOT NULL COMMENT '变更日期',
    approver VARCHAR(100) DEFAULT NULL COMMENT '审批人',
    approval_date DATE DEFAULT NULL COMMENT '审批日期',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, change_date, deleted) COMMENT '客户ID+变更日期索引',
    KEY idx_old_manager (old_manager_id, deleted) COMMENT '原客户经理索引',
    KEY idx_new_manager (new_manager_id, deleted) COMMENT '新客户经理索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户归属历史表';
```

### 5.3 客户转移记录表（crm_customer_transfer）

**表说明**：记录客户的转移申请和审批流程。

```sql
CREATE TABLE crm_customer_transfer (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    transfer_type TINYINT NOT NULL COMMENT '转移类型：1-机构转移，2-客户经理转移',

    -- 转出信息
    from_org_id BIGINT(20) NOT NULL COMMENT '转出机构ID',
    from_org_name VARCHAR(100) NOT NULL COMMENT '转出机构名称',
    from_manager_id BIGINT(20) DEFAULT NULL COMMENT '转出客户经理ID',
    from_manager_name VARCHAR(100) DEFAULT NULL COMMENT '转出客户经理姓名',

    -- 转入信息
    to_org_id BIGINT(20) NOT NULL COMMENT '转入机构ID',
    to_org_name VARCHAR(100) NOT NULL COMMENT '转入机构名称',
    to_manager_id BIGINT(20) DEFAULT NULL COMMENT '转入客户经理ID',
    to_manager_name VARCHAR(100) DEFAULT NULL COMMENT '转入客户经理姓名',

    transfer_reason VARCHAR(500) NOT NULL COMMENT '转移原因',
    transfer_status TINYINT NOT NULL DEFAULT 1 COMMENT '转移状态：1-待审批，2-已通过，3-已拒绝，4-已撤回',

    -- 审批信息
    approver VARCHAR(100) DEFAULT NULL COMMENT '审批人',
    approval_time DATETIME DEFAULT NULL COMMENT '审批时间',
    approval_opinion VARCHAR(500) DEFAULT NULL COMMENT '审批意见',

    -- 完成信息
    transfer_time DATETIME DEFAULT NULL COMMENT '转移完成时间',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, deleted) COMMENT '客户ID索引',
    KEY idx_from_manager (from_manager_id, deleted) COMMENT '转出客户经理索引',
    KEY idx_to_manager (to_manager_id, deleted) COMMENT '转入客户经理索引',
    KEY idx_status (transfer_status, deleted) COMMENT '转移状态索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户转移记录表';
```

---

## 6. 业务数据层

### 6.1 客户账户信息表（crm_customer_account）

**表说明**：存储客户在本行的各类账户信息。

```sql
CREATE TABLE crm_customer_account (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    account_type INT NOT NULL COMMENT '账户类型：101-活期存款，102-定期存款，201-贷款，301-理财，302-基金，303-保险，304-贵金属，401-信用卡',
    account_no VARCHAR(50) NOT NULL COMMENT '账号',
    account_name VARCHAR(100) DEFAULT NULL COMMENT '账户名称',
    currency VARCHAR(10) NOT NULL DEFAULT 'CNY' COMMENT '币种',
    account_balance DECIMAL(18, 2) DEFAULT NULL COMMENT '账户余额',
    available_balance DECIMAL(18, 2) DEFAULT NULL COMMENT '可用余额',
    frozen_amount DECIMAL(18, 2) DEFAULT 0.00 COMMENT '冻结金额',

    -- 状态信息
    account_status TINYINT NOT NULL DEFAULT 1 COMMENT '账户状态：1-正常，2-冻结，3-销户',
    open_date DATE DEFAULT NULL COMMENT '开户日期',
    close_date DATE DEFAULT NULL COMMENT '销户日期',
    open_org_id BIGINT(20) DEFAULT NULL COMMENT '开户机构ID',
    open_org_name VARCHAR(100) DEFAULT NULL COMMENT '开户机构名称',

    -- 同步信息
    sync_time DATETIME DEFAULT NULL COMMENT '数据同步时间',
    source_system VARCHAR(50) DEFAULT NULL COMMENT '来源系统',
    source_id VARCHAR(100) DEFAULT NULL COMMENT '来源系统ID',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    UNIQUE KEY uk_account_no (account_no, deleted) COMMENT '账号唯一索引',
    KEY idx_customer_id (customer_id, account_type, deleted) COMMENT '客户ID+账户类型索引',
    KEY idx_account_status (account_status, deleted) COMMENT '账户状态索引',
    KEY idx_sync_time (sync_time) COMMENT '同步时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户账户信息表';
```

### 6.2 客户产品持有表（crm_customer_product）

**表说明**：存储客户持有的产品信息。

```sql
CREATE TABLE crm_customer_product (
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT(20) NOT NULL COMMENT '客户ID',
    product_code VARCHAR(50) NOT NULL COMMENT '产品代码',
    product_name VARCHAR(200) NOT NULL COMMENT '产品名称',
    product_type VARCHAR(50) NOT NULL COMMENT '产品类型（关联字典）',
    product_category VARCHAR(50) DEFAULT NULL COMMENT '产品分类（关联字典）',

    -- 持有信息
    holding_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '持有金额/份额',
    holding_unit VARCHAR(20) DEFAULT NULL COMMENT '持有单位',
    purchase_amount DECIMAL(18, 2) DEFAULT NULL COMMENT '买入金额',
    current_value DECIMAL(18, 2) DEFAULT NULL COMMENT '当前价值',
    profit_loss DECIMAL(18, 2) DEFAULT NULL COMMENT '盈亏金额',
    yield_rate DECIMAL(10, 4) DEFAULT NULL COMMENT '收益率（%）',

    -- 时间信息
    purchase_date DATE DEFAULT NULL COMMENT '购买日期',
    maturity_date DATE DEFAULT NULL COMMENT '到期日期',
    product_status TINYINT NOT NULL DEFAULT 1 COMMENT '产品状态：1-持有中，2-已赎回，3-已到期',

    -- 同步信息
    sync_time DATETIME DEFAULT NULL COMMENT '数据同步时间',
    source_system VARCHAR(50) DEFAULT NULL COMMENT '来源系统',

    -- 扩展字段
    ext_json JSON DEFAULT NULL COMMENT '扩展JSON字段',

    -- 基础字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    tenant_id BIGINT(20) NOT NULL DEFAULT 0 COMMENT '租户ID',

    PRIMARY KEY (id),
    KEY idx_customer_id (customer_id, product_type, deleted) COMMENT '客户ID+产品类型索引',
    KEY idx_product_code (product_code, deleted) COMMENT '产品代码索引',
    KEY idx_maturity_date (maturity_date, deleted) COMMENT '到期日期索引',
    KEY idx_sync_time (sync_time) COMMENT '同步时间索引',
    KEY idx_tenant (tenant_id, deleted) COMMENT '租户索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户产品持有表';
```

### 6.3-6.10 业务数据层剩余表（精简版）

**以下为业务数据层剩余8张表的核心字段说明：**

#### 6.3 客户授信信息表（crm_customer_credit）
- 核心字段：customer_id, credit_no（授信编号）, credit_type（授信类型）, credit_amount（授信额度）, used_amount（已用额度）, available_amount（可用额度）, credit_status, approval_date, expiry_date
- 索引：customer_id, credit_no, credit_status, expiry_date
- 同步字段：sync_time, source_system

#### 6.4 客户签约信息表（crm_customer_contract）
- 核心字段：customer_id, contract_no（合同编号）, contract_type（签约类型）, contract_name, contract_amount, sign_date, start_date, end_date, contract_status, attachment_urls
- 索引：customer_id, contract_no, contract_type, contract_status

#### 6.5 客户担保信息表（crm_customer_guarantee）
- 核心字段：customer_id, guarantee_type（担保类型：1-抵押，2-质押，3-保证）, guarantee_no, guarantor_name（担保人/物）, guarantee_value（担保价值）, guarantee_status
- 索引：customer_id, guarantee_type, guarantee_no

#### 6.6 客户交易明细表（crm_customer_transaction）**【分区表】**
- 核心字段：customer_id, account_no, transaction_no, transaction_type, transaction_amount, balance_after, transaction_time, counterparty_name（交易对手）, counterparty_account
- 索引：customer_id + transaction_time, transaction_no, account_no
- **分区策略**：按 transaction_time 月分区，保留12个月数据
- **分区配置示例**：
```sql
PARTITION BY RANGE COLUMNS(transaction_time) (
    PARTITION p202401 VALUES LESS THAN ('2024-02-01'),
    PARTITION p202402 VALUES LESS THAN ('2024-03-01'),
    ...
    PARTITION p202412 VALUES LESS THAN ('2025-01-01'),
    PARTITION pmax VALUES LESS THAN (MAXVALUE)
);
```

#### 6.7 客户业务概览统计表（crm_customer_business_summary）
- 核心字段：customer_id, summary_date（统计日期）, total_assets（总资产）, total_liabilities（总负债）, aum（资产管理规模）, deposit_balance, loan_balance, product_count
- 索引：customer_id + summary_date
- 说明：定时任务每日计算并存储

#### 6.8 客户资金流向表（crm_customer_fund_flow）
- 核心字段：customer_id, flow_date, inflow_amount（流入金额）, outflow_amount（流出金额）, net_flow（净流入）, main_flow_type（主要流向类型）
- 索引：customer_id + flow_date

#### 6.9 客户AUM趋势表（crm_customer_aum_trend）
- 核心字段：customer_id, trend_date, aum_value, aum_change（环比变化）, deposit_aum, wealth_aum, insurance_aum
- 索引：customer_id + trend_date
- 说明：按日/周/月统计AUM趋势数据

#### 6.10 客户产品统计表（crm_customer_product_stat）
- 核心字段：customer_id, stat_date, product_type, product_count（产品数量）, total_amount（总金额）, total_income（总收益）
- 索引：customer_id + stat_date + product_type

---

## 7. 客户评价层（精简版）

#### 7.1 客户评级表（crm_customer_rating）
- 核心字段：customer_id, rating_type（评级类型）, rating_level（评级等级）, rating_score（评分）, rating_date, valid_until, rating_method（评级方法：1-系统自动，2-人工调整）
- 索引：customer_id, rating_level, rating_date

#### 7.2 客户评级历史表（crm_customer_rating_history）
- 核心字段：customer_id, old_rating_level, new_rating_level, change_reason, change_date, change_type（变化类型：1-升级，2-降级，3-维持）
- 索引：customer_id + change_date

#### 7.3 客户贡献度表（crm_customer_contribution）
- 核心字段：customer_id, contribution_period（贡献期间）, total_contribution（综合贡献度）, deposit_contribution, loan_contribution, intermediate_contribution, contribution_rank
- 索引：customer_id, contribution_period, total_contribution

#### 7.4 客户信用评分表（crm_customer_credit_score）
- 核心字段：customer_id, score_type（评分类型）, credit_score（信用分）, score_level, score_date, overdue_count（逾期次数）, max_overdue_days（最大逾期天数）
- 索引：customer_id, score_level, score_date

#### 7.5 客户信用信息表（crm_customer_credit_info）
- 核心字段：customer_id, has_credit_card（是否有信用卡）, credit_card_limit, has_overdue（是否逾期）, overdue_amount, credit_record（信用记录JSON）
- 索引：customer_id, has_overdue

---

## 8. 营销服务层（精简版）

#### 8.1 客户投诉表（crm_customer_complaint）
- 核心字段：customer_id, complaint_no（投诉编号）, complaint_type, complaint_channel（投诉渠道）, complaint_content, complaint_status, handle_result, handler, handle_time
- 索引：customer_id, complaint_no, complaint_status

#### 8.2 客户营销活动表（crm_customer_marketing）
- 核心字段：customer_id, activity_id, activity_name, activity_type, participate_date, participate_channel, marketing_result, conversion_status
- 索引：customer_id, activity_id, participate_date

#### 8.3 客户需求表（crm_customer_demand）
- 核心字段：customer_id, demand_no, demand_type, demand_content, demand_channel, demand_status, handler, handle_result
- 索引：customer_id, demand_no, demand_status

#### 8.4 客户提醒表（crm_customer_reminder）
- 核心字段：customer_id, reminder_type（提醒类型）, reminder_title, reminder_content, reminder_level（重要程度）, reminder_time, is_read, handler
- 索引：customer_id, reminder_type, is_read, reminder_time

#### 8.5 客户接触轨迹表（crm_customer_contact_record）
- 核心字段：customer_id, contact_type（接触方式：1-拜访，2-电话，3-短信，4-邮件，5-微信）, contact_purpose, contact_content, contact_time, contact_person, customer_feedback
- 索引：customer_id + contact_time, contact_type

#### 8.6 产品推荐表（crm_product_recommend）
- 核心字段：customer_id, product_code, product_name, recommend_reason, recommend_score（推荐分数）, recommend_time, recommend_source（推荐来源：1-系统推荐，2-人工推荐）, marketing_status
- 索引：customer_id + recommend_time, product_code

#### 8.7 客户营销计划表（crm_customer_marketing_plan）
- 核心字段：customer_id, plan_name, plan_type, target_product, plan_start_date, plan_end_date, plan_status, executor
- 索引：customer_id, plan_status, plan_start_date

#### 8.8 客户营销效果表（crm_customer_marketing_effect）
- 核心字段：customer_id, marketing_id, effect_type（效果类型）, conversion_amount（转化金额）, roi（投资回报率）, effect_date
- 索引：customer_id, marketing_id, effect_date

---

## 9. 权益积分层（精简版）

#### 9.1 客户积分表（crm_customer_points）
- 核心字段：customer_id, total_points（总积分）, available_points（可用积分）, frozen_points（冻结积分）, accumulated_points（累计获得）, used_points（累计使用）, expire_points（即将过期积分）
- 索引：customer_id

#### 9.2 客户积分明细表（crm_customer_points_detail）
- 核心字段：customer_id, change_type（变动类型：1-获得，2-使用，3-过期）, change_points, balance_after, change_reason, change_time
- 索引：customer_id + change_time

#### 9.3 客户卡券表（crm_customer_coupon）
- 核心字段：customer_id, coupon_code, coupon_name, coupon_type, coupon_amount, use_threshold（使用门槛）, receive_time, expire_time, use_time, coupon_status
- 索引：customer_id, coupon_status, expire_time

#### 9.4 客户礼品订单表（crm_customer_gift_order）
- 核心字段：customer_id, order_no, gift_name, gift_points（所需积分）, order_time, delivery_status, delivery_address, contact_phone
- 索引：customer_id, order_no, delivery_status

---

## 10. 行为数据层（精简版）

#### 10.1 客户线上行为表（crm_customer_online_behavior）**【分区表】**
- 核心字段：customer_id, channel_type（渠道类型：1-手机银行，2-网银，3-微信银行）, behavior_type（行为类型：1-登录，2-浏览，3-点击，4-搜索，5-交易）, behavior_page, behavior_content, behavior_time, session_id, ip_address, device_info
- 索引：customer_id + behavior_time, channel_type, behavior_type
- **分区策略**：按 behavior_time 月分区，保留12个月
- 说明：数据量大，需要定期归档

#### 10.2 客户重要事件表（crm_customer_important_event）
- 核心字段：customer_id, event_type（事件类型：1-首次开户，2-等级升降，3-大额交易，4-产品到期，5-风险预警）, event_title, event_content, event_date, event_importance
- 索引：customer_id + event_date, event_type

#### 10.3 客户统计快照表（crm_customer_statistics_snapshot）**【分区表】**
- 核心字段：customer_id, snapshot_date, snapshot_type（快照类型：1-日快照，2-周快照，3-月快照）, statistics_data（统计数据JSON）, aum_value, asset_trend, product_stat
- 索引：customer_id + snapshot_date + snapshot_type
- **分区策略**：按 snapshot_date 月分区
- 说明：定时任务计算并存储统计快照，提升查询性能

---

## 11. 关系网络层（精简版）

#### 11.1 客户关系表（crm_customer_relation）
- 核心字段：customer_id（客户A）, related_customer_id（客户B）, relation_type（关系类型）, relation_strength（关系强度：1-弱，2-中，3-强）, is_bidirectional（是否双向关系）, discover_source（发现来源：1-系统挖掘，2-人工维护）, discover_time
- 索引：customer_id, related_customer_id, relation_type
- 说明：支持知识图谱展示，用于关系营销

#### 11.2 客户关系类型字典表（crm_customer_relation_type）
- 核心字段：relation_code, relation_name, customer_type_limit（适用客户类型：1-仅零售，2-仅对公，3-通用）, is_system（是否系统预置）, sort_order
- 索引：relation_code
- 预置关系类型示例：
  - 零售客户：配偶、子女、父母、兄弟姐妹、亲属、同事、朋友
  - 对公客户：股东、子公司、母公司、关联企业、供应商、客户、合作伙伴
  - 资金关系：收款方、付款方、上下游

---

## 12. 标签体系层（精简版）

#### 12.1 标签分类表（crm_tag_category）
- 核心字段：parent_id（父分类ID）, category_code, category_name, category_level（层级）, sort_order, status
- 索引：parent_id, category_code
- 示例分类：人口属性、资产属性、行为属性、偏好属性、风险属性

#### 12.2 标签字典表（crm_tag）
- 核心字段：category_id（标签分类ID）, tag_code, tag_name, tag_type（标签类型：1-系统标签，2-自定义标签）, customer_type_limit（适用客户类型）, tag_rule（标签规则JSON）, is_auto（是否自动打标）, status
- 索引：category_id, tag_code, tag_type
- 标签示例：高净值客户、潜力客户、活跃客户、沉睡客户、风险客户

#### 12.3 客户标签关联表（crm_customer_tag）
- 核心字段：customer_id, tag_id, tag_value（标签值）, tag_source（标签来源：1-系统打标，2-人工打标）, tag_time, expire_time, is_valid
- 索引：customer_id, tag_id, is_valid
- 说明：记录客户与标签的关联关系，支持标签有效期管理

---

## 13. 分区表配置详解

### 13.1 分区表列表

项目中有 **3张分区表**：
1. **crm_customer_transaction**（客户交易明细）
2. **crm_customer_online_behavior**（客户线上行为）
3. **crm_customer_statistics_snapshot**（客户统计快照）

### 13.2 分区表创建示例

```sql
-- 示例1：交易明细表分区配置（按月分区）
CREATE TABLE crm_customer_transaction (
    -- 字段定义...
    transaction_time DATETIME NOT NULL COMMENT '交易时间'
    -- 其他字段...
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
PARTITION BY RANGE COLUMNS(transaction_time) (
    PARTITION p202401 VALUES LESS THAN ('2024-02-01'),
    PARTITION p202402 VALUES LESS THAN ('2024-03-01'),
    PARTITION p202403 VALUES LESS THAN ('2024-04-01'),
    PARTITION p202404 VALUES LESS THAN ('2024-05-01'),
    PARTITION p202405 VALUES LESS THAN ('2024-06-01'),
    PARTITION p202406 VALUES LESS THAN ('2024-07-01'),
    PARTITION p202407 VALUES LESS THAN ('2024-08-01'),
    PARTITION p202408 VALUES LESS THAN ('2024-09-01'),
    PARTITION p202409 VALUES LESS THAN ('2024-10-01'),
    PARTITION p202410 VALUES LESS THAN ('2024-11-01'),
    PARTITION p202411 VALUES LESS THAN ('2024-12-01'),
    PARTITION p202412 VALUES LESS THAN ('2025-01-01'),
    PARTITION pmax VALUES LESS THAN (MAXVALUE)
);
```

### 13.3 分区表维护脚本

```sql
-- 添加新分区（每月执行）
ALTER TABLE crm_customer_transaction
ADD PARTITION (PARTITION p202501 VALUES LESS THAN ('2025-02-01'));

-- 删除旧分区（数据归档后执行）
ALTER TABLE crm_customer_transaction DROP PARTITION p202401;

-- 查看分区信息
SELECT
    PARTITION_NAME,
    PARTITION_EXPRESSION,
    PARTITION_DESCRIPTION,
    TABLE_ROWS
FROM information_schema.PARTITIONS
WHERE TABLE_SCHEMA = 'database_name'
  AND TABLE_NAME = 'crm_customer_transaction';
```

### 13.4 分区表最佳实践

1. **分区键选择**：选择查询条件中常用的时间字段
2. **分区粒度**：根据数据量选择（日/周/月）
3. **分区数量**：建议不超过1024个分区
4. **查询优化**：WHERE条件必须包含分区键才能分区裁剪
5. **定期维护**：自动化脚本定期添加新分区、删除旧分区
6. **数据归档**：超期数据定期归档到历史库或大数据平台

---

## 14. 性能优化建议

### 14.1 索引优化

#### 索引设计原则
1. **高频查询字段**优先建立索引
2. **复合索引**：遵循最左前缀原则
3. **索引数量**：单表索引不超过5个
4. **索引选择性**：选择性高的字段优先（区分度 > 30%）
5. **避免冗余索引**：定期检查并删除

#### 推荐复合索引
```sql
-- 租户 + 业务字段复合索引
CREATE INDEX idx_tenant_customer ON crm_customer (tenant_id, customer_no, deleted);
CREATE INDEX idx_tenant_manager ON crm_customer_assignment (tenant_id, manager_id, assignment_type, deleted);

-- 客户 + 时间复合索引
CREATE INDEX idx_customer_time ON crm_customer_transaction (customer_id, transaction_time, deleted);
CREATE INDEX idx_customer_date ON crm_customer_contact_record (customer_id, contact_time, deleted);
```

### 14.2 查询优化

#### 慢查询优化
1. 使用 **EXPLAIN** 分析查询计划
2. 避免 **SELECT *** ，只查询需要的字段
3. 避免深度分页，使用**游标分页**
4. 大数据量统计使用**预计算 + 快照**
5. JOIN查询不超过3张表

#### 分页优化示例
```sql
-- 传统分页（深度分页慢）
SELECT * FROM crm_customer
WHERE tenant_id = 1
ORDER BY id
LIMIT 10000, 10;

-- 游标分页（推荐）
SELECT * FROM crm_customer
WHERE tenant_id = 1 AND id > 10000
ORDER BY id
LIMIT 10;
```

### 14.3 缓存策略

#### Redis缓存设计
```yaml
# 客户基本信息缓存
Key: crm:customer:{customer_id}
Value: JSON (客户基本信息)
TTL: 1小时

# 客户标签缓存
Key: crm:customer:tags:{customer_id}
Value: JSON (标签列表)
TTL: 2小时

# 统计数据缓存
Key: crm:customer:stat:{customer_id}:{date}
Value: JSON (统计数据)
TTL: 24小时（每日更新）

# 热点数据预热
启动时加载高频访问的客户数据
```

### 14.4 读写分离

```yaml
数据源配置:
  主库:
    - 用途: 写操作（INSERT、UPDATE、DELETE）
    - 实例: 主库1台

  从库:
    - 用途: 读操作（SELECT）
    - 实例: 从库2台（读负载均衡）
    - 延迟监控: 主从延迟 < 1秒

路由策略:
  - @Transactional 注解的方法 -> 主库
  - 查询方法 -> 从库（轮询/权重）
  - 实时性要求高的查询 -> 主库
```

### 14.5 批量操作优化

```java
// MyBatis Plus 批量插入（推荐）
saveBatch(List<Entity> entities, int batchSize);

// 配置建议
mybatis-plus:
  configuration:
    default-executor-type: BATCH  # 批量执行器
  global-config:
    db-config:
      batch-size: 1000  # 每批次1000条
```

### 14.6 数据归档策略

| 表类型 | 保留时长 | 归档策略 |
|--------|----------|----------|
| 主数据表 | 永久 | 不归档 |
| 交易明细 | 3年 | 超期归档到历史库 |
| 行为数据 | 1年 | 超期归档到大数据平台 |
| 日志数据 | 6个月 | 超期物理删除 |
| 统计快照 | 2年 | 超期归档 |

---

## 15. 数据安全与权限

### 15.1 敏感数据加密

```java
// 需要加密的字段
- 证件号码：AES-256加密
- 手机号码：部分脱敏显示（138****1234）
- 银行账号：部分脱敏显示
- 财务数据：根据权限决定是否展示

// 加密工具类
@Intercepts(@Signature(type = StatementHandler.class, ...))
public class SensitiveDataInterceptor {
    // 自动加解密拦截器
}
```

### 15.2 数据权限控制

```yaml
权限层级:
  1. 租户隔离: tenant_id 自动过滤
  2. 部门权限: 通过 org_id 控制可访问数据范围
  3. 个人权限: 客户经理只能访问自己的客户
  4. 协办权限: 协办客户经理有限访问权限

实现方式:
  - MyBatis Plus 租户插件
  - Spring Security 数据权限注解
  - 自定义 SQL 拦截器
```

### 15.3 审计日志

所有表都包含审计字段：
- **creator**: 创建人
- **create_time**: 创建时间
- **updater**: 最后更新人
- **update_time**: 最后更新时间

关键操作记录到系统操作日志表（system_operate_log）。

---

## 16. 表结构汇总

### 16.1 完整表清单（65张表）

| 序号 | 表名 | 中文名 | 分区 | 备注 |
|------|------|--------|------|------|
| **客户主体层（2张）** |
| 1 | crm_customer | 客户主表 | ❌ | 核心主表 |
| 2 | crm_customer_config | 客户配置表 | ❌ | 视图配置 |
| **零售客户层（8张）** |
| 3 | crm_retail_customer | 零售客户扩展表 | ❌ | |
| 4 | crm_customer_identity | 客户证件信息表 | ❌ | 支持多证件 |
| 5 | crm_customer_work | 客户工作信息表 | ❌ | |
| 6 | crm_customer_business | 客户经营信息表 | ❌ | |
| 7 | crm_customer_family | 客户家庭信息表 | ❌ | |
| 8 | crm_customer_family_member | 客户家庭成员表 | ❌ | |
| 9 | crm_customer_preference | 客户偏好表 | ❌ | 投资偏好等 |
| 10 | crm_customer_event | 客户大事记表 | ❌ | |
| **对公客户层（15张）** |
| 11 | crm_company_customer | 对公客户扩展表 | ❌ | |
| 12 | crm_company_organization | 组织架构表 | ❌ | 树形结构 |
| 13 | crm_company_address | 客户地址信息表 | ❌ | 多地址 |
| 14 | crm_company_contact | 联系人信息表 | ❌ | |
| 15 | crm_company_stock | 股票发行信息表 | ❌ | |
| 16 | crm_company_bond | 债券发行信息表 | ❌ | |
| 17 | crm_company_finance | 客户财务信息表 | ❌ | 三大报表 |
| 18 | crm_company_project | 客户项目信息表 | ❌ | |
| 19 | crm_company_other_bank | 客户他行信息表 | ❌ | |
| 20 | crm_company_shareholder | 对公客户股东信息表 | ❌ | |
| 21 | crm_company_executive | 对公客户高管信息表 | ❌ | 含实控人 |
| 22 | crm_company_industrial_info | 对公客户工商信息表 | ❌ | 外部数据 |
| 23 | crm_company_utility | 对公客户水电费信息表 | ❌ | 外部数据 |
| 24 | crm_company_external_rating | 对公客户外部评级表 | ❌ | 外部数据 |
| 25 | crm_company_risk | 对公客户风险信息表 | ❌ | 外部数据 |
| 26 | crm_company_supply_chain | 对公客户供应链信息表 | ❌ | 外部数据 |
| **客户管理层（3张）** |
| 27 | crm_customer_assignment | 客户归属表 | ❌ | 主协办 |
| 28 | crm_customer_assignment_history | 客户归属历史表 | ❌ | |
| 29 | crm_customer_transfer | 客户转移记录表 | ❌ | |
| **业务数据层（10张）** |
| 30 | crm_customer_account | 客户账户信息表 | ❌ | 统一账户 |
| 31 | crm_customer_product | 客户产品持有表 | ❌ | |
| 32 | crm_customer_credit | 客户授信信息表 | ❌ | |
| 33 | crm_customer_contract | 客户签约信息表 | ❌ | |
| 34 | crm_customer_guarantee | 客户担保信息表 | ❌ | |
| 35 | crm_customer_transaction | 客户交易明细表 | ✅ | 按月分区 |
| 36 | crm_customer_business_summary | 客户业务概览统计表 | ❌ | 定时快照 |
| 37 | crm_customer_fund_flow | 客户资金流向表 | ❌ | |
| 38 | crm_customer_aum_trend | 客户AUM趋势表 | ❌ | |
| 39 | crm_customer_product_stat | 客户产品统计表 | ❌ | |
| **客户评价层（5张）** |
| 40 | crm_customer_rating | 客户评级表 | ❌ | |
| 41 | crm_customer_rating_history | 客户评级历史表 | ❌ | |
| 42 | crm_customer_contribution | 客户贡献度表 | ❌ | |
| 43 | crm_customer_credit_score | 客户信用评分表 | ❌ | |
| 44 | crm_customer_credit_info | 客户信用信息表 | ❌ | |
| **营销服务层（8张）** |
| 45 | crm_customer_complaint | 客户投诉表 | ❌ | |
| 46 | crm_customer_marketing | 客户营销活动表 | ❌ | |
| 47 | crm_customer_demand | 客户需求表 | ❌ | |
| 48 | crm_customer_reminder | 客户提醒表 | ❌ | |
| 49 | crm_customer_contact_record | 客户接触轨迹表 | ❌ | |
| 50 | crm_product_recommend | 产品推荐表 | ❌ | |
| 51 | crm_customer_marketing_plan | 客户营销计划表 | ❌ | |
| 52 | crm_customer_marketing_effect | 客户营销效果表 | ❌ | |
| **权益积分层（4张）** |
| 53 | crm_customer_points | 客户积分表 | ❌ | |
| 54 | crm_customer_points_detail | 客户积分明细表 | ❌ | |
| 55 | crm_customer_coupon | 客户卡券表 | ❌ | |
| 56 | crm_customer_gift_order | 客户礼品订单表 | ❌ | |
| **行为数据层（3张）** |
| 57 | crm_customer_online_behavior | 客户线上行为表 | ✅ | 按月分区 |
| 58 | crm_customer_important_event | 客户重要事件表 | ❌ | |
| 59 | crm_customer_statistics_snapshot | 客户统计快照表 | ✅ | 按月分区 |
| **关系网络层（2张）** |
| 60 | crm_customer_relation | 客户关系表 | ❌ | 知识图谱 |
| 61 | crm_customer_relation_type | 客户关系类型字典表 | ❌ | |
| **标签体系层（3张）** |
| 62 | crm_tag_category | 标签分类表 | ❌ | 树形结构 |
| 63 | crm_tag | 标签字典表 | ❌ | |
| 64 | crm_customer_tag | 客户标签关联表 | ❌ | |
| 65 | crm_customer_points_detail | 客户积分明细表 | ❌ | 已合并 |

**注**：实际为 **65张表**（含积分明细表）

### 16.2 表依赖关系

```
crm_customer (客户主表)
    ├─ crm_retail_customer (零售客户扩展)
    │   ├─ crm_customer_identity (证件信息)
    │   ├─ crm_customer_work (工作信息)
    │   ├─ crm_customer_family (家庭信息)
    │   │   └─ crm_customer_family_member (家庭成员)
    │   └─ crm_customer_preference (客户偏好)
    │
    ├─ crm_company_customer (对公客户扩展)
    │   ├─ crm_company_organization (组织架构)
    │   ├─ crm_company_address (地址信息)
    │   ├─ crm_company_contact (联系人)
    │   ├─ crm_company_finance (财务信息)
    │   ├─ crm_company_shareholder (股东信息)
    │   └─ crm_company_executive (高管信息)
    │
    ├─ crm_customer_assignment (客户归属)
    │   └─ crm_customer_assignment_history (归属历史)
    │
    ├─ crm_customer_account (账户信息)
    │   └─ crm_customer_transaction (交易明细)
    │
    ├─ crm_customer_product (产品持有)
    ├─ crm_customer_rating (客户评级)
    ├─ crm_customer_tag (客户标签)
    └─ crm_customer_relation (客户关系)
```

---

## 17. 实施建议

### 17.1 分阶段实施

**第一阶段（核心功能）**：
- ✅ 客户主体层（2张表）
- ✅ 零售客户层（8张表）
- ✅ 对公客户层（15张表）
- ✅ 客户管理层（3张表）
- ✅ 账户和产品表（2张表）

**第二阶段（业务功能）**：
- 业务数据层剩余表（8张）
- 客户评价层（5张）
- 标签体系层（3张）
- 关系网络层（2张）

**第三阶段（增值功能）**：
- 营销服务层（8张）
- 权益积分层（4张）
- 行为数据层（3张）

### 17.2 数据迁移建议

```sql
-- 数据迁移步骤
1. 备份原有数据
2. 创建新表结构
3. 数据清洗和转换
4. 分批导入数据（避免锁表）
5. 校验数据完整性
6. 建立索引和分区
7. 性能测试
8. 切换应用

-- 分批导入示例
INSERT INTO crm_customer_new
SELECT * FROM crm_customer_old
WHERE id BETWEEN 1 AND 10000;
-- 继续分批...
```

### 17.3 监控和运维

```yaml
监控指标:
  - 数据库连接数
  - 慢查询数量（> 1秒）
  - 主从延迟时间
  - 表空间使用率
  - 分区表数据分布
  - 索引命中率

定期维护:
  - 每月: 添加新分区，删除旧分区
  - 每季度: 分析表统计信息（ANALYZE TABLE）
  - 每半年: 优化表碎片（OPTIMIZE TABLE）
  - 每年: 审查索引使用情况，删除冗余索引
```

---

## 18. 总结

### 18.1 设计特点总结

1. **架构清晰**：12个功能模块，65张表，职责明确
2. **灵活扩展**：JSON扩展字段 + 预留字段，支持业务灵活扩展
3. **性能优化**：分区表 + 索引优化 + 缓存策略 + 读写分离
4. **数据安全**：多租户隔离 + 敏感数据加密 + 完整审计日志
5. **技术先进**：实时同步 + 外部数据混合缓存 + 定时快照统计

### 18.2 关键技术点

- **多租户隔离**：tenant_id + MyBatis Plus 租户插件
- **数据同步**：sync_time + source_system 实时/准实时同步机制
- **分区表**：3张大表按月分区，保留12个月，自动维护
- **外部数据**：cache_time + expire_time 混合缓存策略
- **统计优化**：定时计算 + 快照存储，避免实时统计

### 18.3 预期效果

- **查询性能**：分区表 + 索引优化，单表千万级数据毫秒级响应
- **并发支持**：读写分离 + 连接池，支持万级并发
- **存储容量**：合理归档策略，控制存储增长
- **可扩展性**：模块化设计，支持业务快速迭代
- **数据安全**：多层权限控制，敏感数据加密保护

### 18.4 文档维护

- **版本控制**：表结构变更记录到版本控制系统
- **变更文档**：每次表结构变更编写变更说明文档
- **定期评审**：每季度评审表结构和索引使用情况
- **持续优化**：根据实际业务情况持续优化设计

---

**文档版本**: v1.0
**编写日期**: 2025-01-24
**适用系统**: 芋道快速开发平台 - 客户画像CRM系统
**数据库**: MySQL 8.0+

---

## 附录A：快速参考

### 核心表速查

```sql
-- 客户主表
SELECT * FROM crm_customer WHERE customer_no = 'C001';

-- 客户归属（主协办）
SELECT * FROM crm_customer_assignment
WHERE customer_id = 1 AND assignment_type = 1;  -- 主办

-- 客户账户
SELECT * FROM crm_customer_account
WHERE customer_id = 1 AND account_status = 1;

-- 客户交易明细（分区表）
SELECT * FROM crm_customer_transaction
WHERE customer_id = 1
  AND transaction_time >= '2024-01-01'
  AND transaction_time < '2024-02-01';

-- 客户标签
SELECT t.tag_name
FROM crm_customer_tag ct
JOIN crm_tag t ON ct.tag_id = t.id
WHERE ct.customer_id = 1 AND ct.is_valid = 1;
```

### 常用统计查询

```sql
-- 客户资产统计
SELECT
    customer_id,
    SUM(account_balance) as total_assets,
    COUNT(*) as account_count
FROM crm_customer_account
WHERE customer_id = 1 AND account_status = 1
GROUP BY customer_id;

-- 客户产品持有统计
SELECT
    product_type,
    COUNT(*) as product_count,
    SUM(current_value) as total_value
FROM crm_customer_product
WHERE customer_id = 1 AND product_status = 1
GROUP BY product_type;
```

---

**文档结束**
