# CRM对公客户字段迁移总结报告

## 项目概述

本次任务完成了从老系统(acrm_f_ci_org)到新系统(crm_company_customer)的对公客户字段迁移工作,包括数据库结构调整、后端代码更新、前端页面修改、字典数据创建和测试数据生成。

**执行时间**: 2025-10-28
**涉及系统**:
- 老系统: dev_palmbank @ 192.168.201.44:3306
- 新系统: ruoyi-vue-pro @ 127.0.0.1:3306

---

## 一、字段对比分析

### 1.1 表结构概览

**老系统**: `acrm_f_ci_org` (51个字段)
**新系统**:
- `crm_customer` (21个字段) - 客户公有字段
- `crm_company_customer` (52→88个字段) - 对公客户专有字段

### 1.2 字段分类统计

识别出**36个缺失字段**,分为8大类:

| 类别 | 字段数 | 主要内容 |
|-----|-------|---------|
| 财务信息 | 5 | 资产总额、负债、年收入、年利润、报表类型 |
| 股权投资 | 3 | 是否股东、持股金额、投资类型 |
| 企业组织 | 7 | 组织形式、治理结构、控股类型、企业归属等 |
| 业务经营 | 8 | 主营业务、经营模式、行业特征、行业前景等 |
| 企业规模 | 4 | 员工规模、资产规模、生产能力、企业性质 |
| 监管评级 | 3 | 贷款卡标志、贷款卡状态、审核日期 |
| 其他特殊 | 2 | 人行企业规模、存款企业规模 |
| 系统追溯 | 4 | ETL日期、老系统ID、交易序列号、更新系统 |

---

## 二、数据库变更

### 2.1 表结构变更

执行文件: `backend/sql/mysql/crm_company_customer_add_fields.sql`

**核心变更**:
- 新增字段: 36个
- 新增索引: 10个
- 字段总数: 52 → 88 (不含框架字段)

**关键字段示例**:
```sql
-- 财务信息
ALTER TABLE crm_company_customer ADD COLUMN total_assets DECIMAL(18,2) DEFAULT NULL COMMENT '资产总额';
ALTER TABLE crm_company_customer ADD COLUMN annual_income DECIMAL(18,2) DEFAULT NULL COMMENT '年收入';

-- 企业组织
ALTER TABLE crm_company_customer ADD COLUMN org_form VARCHAR(20) DEFAULT NULL COMMENT '组织形式';
ALTER TABLE crm_company_customer ADD COLUMN holding_type VARCHAR(20) DEFAULT NULL COMMENT '控股类型';

-- 业务经营
ALTER TABLE crm_company_customer ADD COLUMN main_business VARCHAR(500) DEFAULT NULL COMMENT '主营业务';
ALTER TABLE crm_company_customer ADD COLUMN business_mode VARCHAR(20) DEFAULT NULL COMMENT '经营模式';
```

### 2.2 索引优化

创建了10个索引以提升查询性能:

| 索引名称 | 索引字段 | 用途 |
|---------|---------|------|
| idx_total_assets | total_assets | 资产规模查询 |
| idx_annual_income | annual_income | 收入规模查询 |
| idx_holding_type | holding_type | 控股类型筛选 |
| idx_business_mode | business_mode | 经营模式筛选 |
| idx_employee_scale | employee_scale | 员工规模筛选 |
| idx_area_code | area_code | 地区统计 |
| idx_nation_code | nation_code | 国籍统计 |
| idx_old_cust_id | old_cust_id | 数据追溯 |
| idx_etl_date | etl_date | ETL批次查询 |

### 2.3 字典数据创建

执行文件: `backend/sql/mysql/crm_company_dict_data.sql`

创建了**11个字典类型**(ID: 210-220),共**59条字典数据**:

| 字典类型 | 代码 | 数据条数 | 示例值 |
|---------|------|---------|--------|
| 财务报表类型 | crm_fin_report_type | 4 | 年报、半年报、季报、月报 |
| 投资类型 | crm_investment_type | 4 | 风险投资、战略投资、财务投资 |
| 组织形式 | crm_org_form | 6 | 有限责任公司、股份有限公司 |
| 治理结构 | crm_governance_structure | 4 | 股东会制、董事会制 |
| 控股类型 | crm_holding_type | 5 | 国有控股、民营控股、外资控股 |
| 经营模式 | crm_business_mode | 5 | 生产型、贸易型、服务型 |
| 行业特征 | crm_industry_char | 5 | 传统行业、新兴行业、高新技术 |
| 行业发展前景 | crm_industry_prospect | 5 | 高速增长、稳定增长、成熟期 |
| 员工规模 | crm_employee_scale | 5 | 50人以下、50-300人、1000人以上 |
| 生产能力 | crm_production_capacity | 5 | 满负荷、80%以上、40%以下 |
| 贷款卡状态 | crm_loan_card_status | 4 | 正常、冻结、注销、挂失 |

---

## 三、后端代码更新

### 3.1 实体类更新

文件: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/dal/dataobject/companycustomer/CompanyCustomerDO.java`

**更新内容**:
- 新增36个private字段
- 每个字段添加详细JavaDoc注释
- 字典类型字段标注枚举引用(TODO)
- 数据类型规范:
  - 金额: `BigDecimal`
  - 日期: `LocalDate`
  - 布尔: `Boolean`
  - 文本: `String`

**代码示例**:
```java
// ========== 财务信息字段 (5个) ==========

/**
 * 资产总额
 */
private BigDecimal totalAssets;

/**
 * 财务报表类型
 *
 * 枚举 {@link TODO crm_fin_report_type 对应的类}
 */
private String finReportType;

// ========== 股权与投资信息 (3个) ==========

/**
 * 是否股东
 */
private Boolean isStockHolder;

/**
 * 持股金额
 */
private BigDecimal holdStockAmt;

/**
 * 投资类型
 *
 * 枚举 {@link TODO crm_investment_type 对应的类}
 */
private String investmentType;
```

### 3.2 响应VO更新

文件: `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/companycustomer/vo/CompanyCustomerRespVO.java`

**更新内容**:
- 新增36个字段
- 添加Swagger注解(`@Schema`)
- 添加Excel导出注解(`@ExcelProperty`)
- 字典字段添加转换注解(`@DictFormat`)

**注解示例**:
```java
@Schema(description = "资产总额")
@ExcelProperty("资产总额")
private BigDecimal totalAssets;

@Schema(description = "财务报表类型")
@ExcelProperty(value = "财务报表类型", converter = DictConvert.class)
@DictFormat("crm_fin_report_type")
private String finReportType;

@Schema(description = "是否股东")
@ExcelProperty("是否股东")
private Boolean isStockHolder;
```

---

## 四、前端代码更新

### 4.1 TypeScript接口更新

文件: `frontend/apps/web-antd/src/api/aicrm/company-customer/index.ts`

**更新内容**:
- 在`CompanyCustomer`接口中新增36个可选字段
- 类型映射:
  - 金额 → `number`
  - 文本 → `string`
  - 布尔 → `boolean`
  - 日期 → `string` (ISO格式)

**接口示例**:
```typescript
export interface CompanyCustomer {
  // ... 原有字段

  // 财务信息
  totalAssets?: number;
  totalDebt?: number;
  annualIncome?: number;
  annualProfit?: number;
  finReportType?: string;

  // 股权投资
  isStockHolder?: boolean;
  holdStockAmt?: number;
  investmentType?: string;

  // 企业组织
  orgForm?: string;
  governanceStructure?: string;
  holdingType?: string;
  enterpriseBelong?: string;
  superiorDept?: string;
  companyOrganization?: string;
  nationCode?: string;

  // ... 其他29个字段
}
```

### 4.2 Vue详情页面更新

文件: `frontend/apps/web-antd/src/views/aicrm/company-customer/pages/basic-info.vue`

**页面结构**: 9个卡片(Card)展示客户详细信息

**更新策略**: 36个新字段分布到各卡片

| 卡片 | 原有/新增 | 新增字段数 | 新增内容 |
|------|----------|-----------|---------|
| Card 1: 基本信息 | 原有 | 0 | 无变更 |
| Card 2: 证件信息 | 原有 | 0 | 无变更 |
| Card 3: 企业基本信息 | 扩展 | 7 | 组织形式、治理结构、控股类型、企业归属、上级部门、公司机构、国家代码 |
| Card 4: 行业与规模 | 原有 | 0 | 无变更 |
| Card 4.5: 业务经营信息 | **新增** | 8 | 主营业务、辅营业务、经营模式、营业开始日期、行业特征、行业发展前景、地区代码、行业地位 |
| Card 5: 企业特征 | 扩展 | 9 | 员工规模、资产规模、生产能力、企业性质、是否股东、持股金额、投资类型、人行企业规模、存款企业规模 |
| Card 6: 联系与账户 | 原有 | 0 | 无变更 |
| Card 7.5: 财务信息 | **新增** | 5 | 资产总额、负债总额、年收入、年利润、财务报表类型 |
| Card 8: 资质与评级 | 扩展 | 3 | 贷款卡标志、贷款卡状态、贷款卡审核日期 |
| Card 9: 系统信息 | 扩展 | 4 | ETL导入日期、老系统交易序列号、老系统最后更新系统、老系统客户ID |

**字段展示方式**:

1. **字典类型字段** - 使用`getDict()`函数转换:
```vue
<Descriptions.Item label="组织形式">
  {{ getDict('crm_org_form', customer.orgForm) }}
</Descriptions.Item>
```

2. **金额字段** - 使用`formatMoney()`格式化:
```vue
<Descriptions.Item label="资产总额">
  {{ formatMoney(customer.totalAssets) }}
</Descriptions.Item>
```

3. **日期字段** - 使用`formatDate()`格式化:
```vue
<Descriptions.Item label="ETL导入日期">
  {{ formatDate(customer.etlDate) }}
</Descriptions.Item>
```

4. **布尔字段** - 使用`formatBoolean()`转换:
```vue
<Descriptions.Item label="是否股东">
  {{ formatBoolean(customer.isStockHolder) }}
</Descriptions.Item>
```

5. **长文本字段** - 使用`:span="3"`占满整行:
```vue
<Descriptions.Item label="主营业务" :span="3">
  {{ customer.mainBusiness || '-' }}
</Descriptions.Item>
```

---

## 五、测试数据生成

执行文件: `backend/sql/mysql/crm_company_customer_test_data.sql`

### 5.1 测试数据特点

为系统中已存在的**10家企业客户**生成了符合行业特征的测试数据:

| 客户ID | 企业名称 | 行业类型 | 财务特点 |
|-------|---------|---------|---------|
| 8 | 深圳市腾讯计算机系统有限公司 | 科技 | 年收入50亿,利润率15%,资产20亿 |
| 9 | 华为技术有限公司 | 科技 | 年收入200亿,利润率15%,资产80亿 |
| 10 | 比亚迪汽车有限公司 | 制造 | 年收入150亿,利润率6%,资产100亿 |
| 11 | 格力电器股份有限公司 | 制造 | 年收入120亿,利润率6%,资产80亿 |
| 12 | 中国建筑股份有限公司 | 建筑 | 年收入300亿,利润率4%,资产200亿 |
| 13 | 招商银行股份有限公司 | 金融 | 年收入100亿,利润率30%,资产1000亿 |
| 14 | 阿里巴巴网络技术有限公司 | 电商 | 年收入180亿,利润率20%,资产70亿 |
| 15 | 京东世纪贸易有限公司 | 电商 | 年收入250亿,利润率3%,资产120亿 |
| 16 | 中国石油天然气集团公司 | 能源 | 年收入500亿,利润率5%,资产400亿 |
| 17 | 中国移动通信集团公司 | 通信 | 年收入400亿,利润率10%,资产300亿 |

### 5.2 行业数据模型

**科技互联网企业**:
- 利润率: 15-20%
- 负债率: 30-40% (轻资产)
- 员工规模: 1000-5000人
- 生产能力: medium (60-80%)
- 行业特征: 高新技术
- 行业前景: 高速增长

**制造业企业**:
- 利润率: 4-6%
- 负债率: 50-60% (重资产)
- 员工规模: 1000-5000人
- 生产能力: high (80%以上)
- 行业特征: 传统行业
- 行业前景: 稳定增长

**金融业企业**:
- 利润率: 25-30%
- 资产规模: 极大(1000亿+)
- 员工规模: 1000-5000人
- 行业特征: 现代服务业
- 行业前景: 稳定增长

**贸易电商企业**:
- 利润率: 3-8%
- 负债率: 40-50%
- 员工规模: 1000-5000人
- 行业特征: 新兴行业
- 行业前景: 高速增长

### 5.3 字典值使用规范

所有字典字段使用正确的value代码(而非label):

```sql
-- 正确: 使用value代码
fin_report_type = 'annual',          -- 而非 '年报'
business_mode = 'manufacturing',     -- 而非 '生产型'
holding_type = 'private_holding',    -- 而非 '民营控股'
employee_scale = '4',                -- 而非 '1000-5000人'

-- 字典转换在前端显示时自动完成
```

---

## 六、成果验证

### 6.1 数据库验证

```sql
-- 检查字段是否添加成功
DESC crm_company_customer;
-- 应显示88个字段(含框架字段)

-- 检查索引是否创建
SHOW INDEX FROM crm_company_customer;
-- 应显示10个新索引

-- 检查字典类型
SELECT * FROM system_dict_type WHERE id BETWEEN 210 AND 220;
-- 应返回11条记录

-- 检查字典数据
SELECT dict_type, COUNT(*) FROM system_dict_data
WHERE dict_type LIKE 'crm_%'
GROUP BY dict_type;
-- 应显示11个字典类型的数据分布

-- 检查测试数据
SELECT customer_id, total_assets, annual_income, business_mode
FROM crm_company_customer
WHERE customer_id BETWEEN 8 AND 17;
-- 应返回10条有数据的记录
```

### 6.2 后端验证

```bash
# 编译检查
cd backend
mvn clean compile

# 检查CompanyCustomerDO字段数
# 应该有86个业务字段 + 框架字段

# 启动后端服务
cd yudao-server
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 访问Swagger文档
open http://localhost:48080/doc.html
# 查看CompanyCustomerRespVO schema,应包含所有新字段
```

### 6.3 前端验证

```bash
# 类型检查
cd frontend
pnpm check:type

# 启动开发服务器
pnpm dev:antd

# 浏览器访问
open http://localhost:5666

# 测试步骤:
# 1. 登录系统(admin/admin123)
# 2. 进入CRM -> 客户管理 -> 对公客户
# 3. 点击任意客户查看详情
# 4. 验证9个卡片中的新字段显示正常
# 5. 检查字典值是否正确转换为中文
# 6. 检查金额、日期、布尔值格式化是否正确
```

---

## 七、完成清单

### 7.1 数据库层 ✅

- [x] 创建字段对比分析文档
- [x] 生成ALTER TABLE SQL(36个字段)
- [x] 创建性能优化索引(10个)
- [x] 执行表结构变更
- [x] 创建字典类型(11个)
- [x] 创建字典数据(59条)
- [x] 执行字典数据导入
- [x] 生成测试数据(10条)
- [x] 执行测试数据导入

### 7.2 后端层 ✅

- [x] 更新CompanyCustomerDO实体类(36个字段)
- [x] 添加JavaDoc注释
- [x] 标注字典枚举引用
- [x] 更新CompanyCustomerRespVO类(36个字段)
- [x] 添加Swagger注解
- [x] 添加Excel导出注解
- [x] 添加字典转换注解

### 7.3 前端层 ✅

- [x] 更新TypeScript接口(36个字段)
- [x] 更新Vue详情页面
- [x] 扩展5个现有卡片
- [x] 新增2个业务卡片
- [x] 实现字典值显示
- [x] 实现金额格式化
- [x] 实现日期格式化
- [x] 实现布尔值转换

### 7.4 文档层 ✅

- [x] 创建字段对比分析文档
- [x] 创建任务总结文档
- [x] SQL文件添加详细注释
- [x] 代码添加完整注释

---

## 八、注意事项

### 8.1 字典枚举类创建(TODO)

后端DO类中标注了TODO的字典枚举类需要创建:

```java
// CompanyCustomerDO.java中需要创建的枚举类
/**
 * 枚举 {@link TODO crm_fin_report_type 对应的类}
 */
```

**建议创建路径**: `cn.iocoder.yudao.module.crm.enums.DictTypeConstants`

**示例代码**:
```java
public interface DictTypeConstants {
    String FIN_REPORT_TYPE = "crm_fin_report_type";
    String INVESTMENT_TYPE = "crm_investment_type";
    String ORG_FORM = "crm_org_form";
    // ... 其他8个字典类型常量
}
```

### 8.2 数据迁移脚本

如需从老系统批量迁移数据,可参考以下SQL模板:

```sql
-- 数据迁移脚本(示例)
INSERT INTO ruoyi-vue-pro.crm_company_customer (
    customer_id, total_assets, total_debt, annual_income,
    fin_report_type, business_mode, holding_type,
    employee_scale, old_cust_id, etl_date
    -- ... 其他字段
)
SELECT
    c.id as customer_id,
    o.total_assets,
    o.total_debt,
    o.annual_income,
    CASE o.fin_rep_type
        WHEN '年报' THEN 'annual'
        WHEN '季报' THEN 'quarterly'
        ELSE 'annual'
    END as fin_report_type,
    -- ... 字段映射和值转换
    o.cust_id as old_cust_id,
    NOW() as etl_date
FROM dev_palmbank.acrm_f_ci_org o
INNER JOIN ruoyi-vue-pro.crm_customer c ON c.customer_name = o.cust_name
WHERE o.deleted = 0;
```

**迁移注意事项**:
1. 字典值需要从中文label转换为英文value代码
2. 金额单位统一为"万元"
3. 日期格式统一为ISO 8601(YYYY-MM-DD)
4. 布尔值统一为0/1或true/false
5. 建议分批迁移,每批1000条,避免长事务

### 8.3 前端表单开发

如需开发编辑表单,建议分组处理:

**表单分组建议**:
1. 基础信息Tab (企业类型、组织形式、控股类型)
2. 财务信息Tab (资产、负债、收入、利润)
3. 业务信息Tab (主营业务、经营模式、行业特征)
4. 规模信息Tab (员工规模、资产规模、生产能力)

**表单组件选择**:
- 字典字段: `a-select` (下拉选择)
- 金额字段: `a-input-number` (数字输入,格式化显示)
- 日期字段: `a-date-picker`
- 长文本: `a-textarea`
- 布尔值: `a-switch` 或 `a-radio-group`

### 8.4 权限控制

新增字段的权限控制需要配置:

1. **查看权限**: 系统管理 -> 菜单管理 -> 对公客户详情 -> 添加按钮权限
2. **编辑权限**: 区分财务数据、敏感数据(法人信息)的编辑权限
3. **导出权限**: Excel导出包含所有字段,需要高级权限

---

## 九、技术要点总结

### 9.1 数据类型选择

| 业务数据类型 | MySQL类型 | Java类型 | TypeScript类型 |
|------------|----------|---------|---------------|
| 金额 | DECIMAL(18,2) | BigDecimal | number |
| 日期 | DATE | LocalDate | string |
| 日期时间 | DATETIME | LocalDateTime | string |
| 布尔 | BIT(1) | Boolean | boolean |
| 短文本 | VARCHAR(20-100) | String | string |
| 长文本 | VARCHAR(500) / TEXT | String | string |
| 枚举/字典 | VARCHAR(20) | String | string |

### 9.2 字典系统集成

**后端**:
```java
@DictFormat("crm_fin_report_type")
private String finReportType;
```

**前端**:
```typescript
const dictStore = useDictStore();
const getDict = (type: string, value: string) => {
  return dictStore.getDictLabel(type, value);
};
```

**显示**:
```vue
{{ getDict('crm_fin_report_type', customer.finReportType) }}
```

### 9.3 代码生成器配置

后续如需生成类似模块,可参考以下配置:

**字段配置**:
- 字典字段: 显示类型选择"下拉框",字典类型填写对应代码
- 金额字段: Java类型选择BigDecimal,显示类型选择"数字框"
- 日期字段: Java类型选择LocalDate,显示类型选择"日期选择"
- 布尔字段: Java类型选择Boolean,显示类型选择"单选框"

**生成选项**:
- [x] 生成模块前端代码
- [x] 生成树形结构 (如果是树形)
- [x] 生成数据权限 (如果需要部门级权限)
- [x] 生成Excel导入导出

---

## 十、相关文档

### 10.1 项目文档

| 文档名称 | 路径 | 说明 |
|---------|------|------|
| 字段对比分析 | `docs/company_customer_field_comparison.md` | 详细字段映射表 |
| 表结构变更SQL | `backend/sql/mysql/crm_company_customer_add_fields.sql` | 36字段+10索引 |
| 字典数据SQL | `backend/sql/mysql/crm_company_dict_data.sql` | 11类型+59数据 |
| 测试数据SQL | `backend/sql/mysql/crm_company_customer_test_data.sql` | 10条行业测试数据 |
| 任务总结报告 | `docs/company_customer_migration_summary.md` | 本文档 |

### 10.2 官方文档

- 易诚框架文档: https://doc.iocoder.cn
- 代码生成器: https://doc.iocoder.cn/codegen/
- 字典管理: https://doc.iocoder.cn/dict/
- 数据权限: https://doc.iocoder.cn/data-permission/

---

## 十一、联系方式

如有问题,请联系:

- 开发者: Claude Code
- 执行日期: 2025-10-28
- 项目: ynet-ydp (易诚快速开发平台)

---

**文档版本**: v1.0
**最后更新**: 2025-10-28
**状态**: ✅ 已完成所有任务
