# 对公客户字段对比分析

## 表结构概览

### 老系统表: acrm_f_ci_org (51个字段)
**数据库**: dev_palmbank (192.168.201.44:3306)

### 新系统表:
1. **crm_customer** (21个字段) - 客户公有字段
2. **crm_company_customer** (52个字段) - 对公客户专有字段

## 字段映射分析

### 1. 老系统字段 -> 新系统crm_customer (公有字段)

| 老系统字段 | 类型 | 新系统字段 | 表 | 说明 |
|-----------|------|-----------|-----|------|
| cust_id | varchar(50) | - | - | 老系统客户ID(保留用于追溯) |
| cust_name | varchar(80) | customer_name | crm_customer | 客户名称 ✅ |
| remark | varchar(200) | remark | crm_customer | 备注信息 ✅ |
| last_update_tm | datetime | update_time | 框架字段 | 更新时间 ✅ |
| last_update_user | varchar(20) | updater | 框架字段 | 更新人 ✅ |

### 2. 老系统字段 -> 新系统crm_company_customer (专有字段)

| 老系统字段 | 类型 | 新系统字段 | 说明 | 状态 |
|-----------|------|-----------|------|------|
| loan_card_no | varchar(32) | loan_card_no | 贷款卡号 | ✅已有 |
| reg_fund_amt | decimal(17,2) | registered_capital | 注册资本 | ✅已有 |
| reg_fund_currency | varchar(10) | registered_capital_currency | 注册资本币种 | ✅已有 |
| build_date | date | establish_date | 成立日期 | ✅已有 |
| economic_type | varchar(20) | economic_type | 经济类型 | ✅已有 |
| ent_scale | varchar(20) | enterprise_scale | 企业规模 | ✅已有 |
| main_industry | varchar(20) | industry_category_l1 | 主行业 | ✅已有 |
| minor_industry | varchar(20) | industry_category_l2 | 次行业 | ✅已有 |
| org_cust_type | varchar(20) | enterprise_type | 机构客户类型 | ✅已有 |

### 3. 缺失字段分析 (需要添加到crm_company_customer)

#### 3.1 财务信息字段 (5个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| total_assets | decimal(17,2) | 资产总额 | 企业总资产 |
| total_debt | decimal(17,2) | 负债总额 | 企业总负债 |
| annual_income | decimal(17,2) | 年收入 | 年度营业收入 |
| annual_profit | decimal(17,2) | 年利润 | 年度利润 |
| fin_rep_type | varchar(20) | 财务报表类型 | 财务报表类型(年报/季报等) |

#### 3.2 股权与投资信息 (3个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| is_stock_holder | varchar(1) | 是否股东 | 是否为其他企业股东 |
| hold_stock_amt | decimal(17,2) | 持股金额 | 对外持股金额 |
| invest_type | varchar(20) | 投资类型 | 投资性质类型 |

#### 3.3 企业组织信息 (7个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| org_form | varchar(20) | 组织形式 | 企业组织形式 |
| govern_structure | varchar(20) | 治理结构 | 公司治理结构 |
| com_hold_type | varchar(20) | 控股类型 | 控股类型(国有/民营/外资等) |
| ent_belong | varchar(100) | 企业归属 | 企业归属单位/集团 |
| super_dept | varchar(60) | 上级部门 | 上级主管部门 |
| comp_org | varchar(80) | 公司机构 | 公司组织机构 |
| nation_code | varchar(20) | 国家代码 | 国籍代码 |

#### 3.4 业务经营信息 (8个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| main_business | varchar(500) | 主营业务 | 企业主营业务描述 |
| minor_business | varchar(500) | 辅营业务 | 企业辅营业务描述 |
| business_mode | varchar(20) | 经营模式 | 企业经营模式 |
| busi_start_date | date | 营业开始日期 | 实际开始营业日期 |
| industry_char | varchar(20) | 行业特征 | 行业特征分类 |
| indu_deve_prospect | varchar(20) | 行业发展前景 | 所属行业发展前景评估 |
| area_code | varchar(20) | 地区代码 | 所属地区代码 |
| industry_position | varchar(80) | 行业地位 | 企业在行业中的地位 |

#### 3.5 企业规模信息 (4个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| employee_scale | varchar(20) | 员工规模 | 员工人数规模 |
| assets_scale | varchar(20) | 资产规模 | 资产规模分类 |
| prod_capacity | varchar(20) | 生产能力 | 企业生产能力 |
| ent_property | varchar(20) | 企业性质 | 企业性质分类 |

#### 3.6 监管与评级信息 (3个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| loan_card_flag | varchar(1) | 贷款卡标志 | 是否有贷款卡 |
| loan_card_stat | varchar(20) | 贷款卡状态 | 贷款卡状态(正常/冻结等) |
| load_card_audit_dt | date | 贷款卡审核日期 | 贷款卡审核日期 |

#### 3.7 其他特殊字段 (2个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| ent_scale_rh | varchar(20) | 人行企业规模 | 人民银行定义的企业规模 |
| ent_scale_ck | varchar(20) | 存款企业规模 | 存款口径的企业规模 |

#### 3.8 系统管理与追溯字段 (4个) ⭐需添加
| 字段名 | 类型 | 中文名 | 说明 |
|-------|------|--------|------|
| etl_date | date | ETL导入日期 | 数据迁移导入日期 |
| tx_seq_no | varchar(32) | 交易序列号 | 老系统交易序列号 |
| last_update_sys | varchar(20) | 最后更新系统 | 老系统最后更新系统标识 |
| load_card_pwd | varchar(255) | 贷款卡密码 | 贷款卡密码(加密存储) |

## 字段补充总计

**需要添加的字段总数**: 36个

### 按类别统计:
1. 财务信息: 5个字段
2. 股权投资: 3个字段
3. 企业组织: 7个字段
4. 业务经营: 8个字段
5. 企业规模: 4个字段
6. 监管评级: 3个字段
7. 其他特殊: 2个字段
8. 系统追溯: 4个字段

## 字段类型映射规则

### 数值类型
- `int(11)` (金额) -> `DECIMAL(18,2)` (保持精度)
- `decimal(17,2)` -> `DECIMAL(18,2)` (统一精度)

### 字符类型
- `varchar(n)` -> `VARCHAR(n)` (保持长度)
- 超长文本 -> `VARCHAR(500)` 或 `TEXT`

### 日期类型
- `date` -> `DATE`
- `datetime` -> `DATETIME`

### 布尔类型
- `varchar(1)` (Y/N, 0/1) -> `BIT(1)` 或保持 `VARCHAR(1)`

## 需要创建的字典类型

基于字段分析,以下字段需要字典支持:

1. **crm_fin_report_type** - 财务报表类型 (年报/半年报/季报/月报)
2. **crm_investment_type** - 投资类型 (风险投资/战略投资/财务投资)
3. **crm_org_form** - 组织形式 (有限责任公司/股份有限公司/个人独资/合伙企业)
4. **crm_governance_structure** - 治理结构 (一人有限公司/股东会制/董事会制)
5. **crm_holding_type** - 控股类型 (国有控股/民营控股/外资控股/中外合资)
6. **crm_business_mode** - 经营模式 (生产型/贸易型/服务型/混合型)
7. **crm_industry_char** - 行业特征 (传统行业/新兴行业/高新技术/现代服务)
8. **crm_industry_prospect** - 行业发展前景 (高速增长/稳定增长/缓慢增长/衰退)
9. **crm_employee_scale** - 员工规模 (50人以下/50-300人/300-1000人/1000人以上)
10. **crm_production_capacity** - 生产能力 (满产/80%/60%/40%以下)
11. **crm_loan_card_status** - 贷款卡状态 (正常/冻结/注销)

## 下一步操作

1. ✅ 生成ALTER TABLE语句添加36个字段
2. ✅ 创建11个新字典类型及数据
3. ✅ 更新CompanyCustomerDO.java
4. ✅ 更新CompanyCustomerRespVO.java
5. ✅ 更新前端TypeScript接口
6. ✅ 更新前端详情页面组件
7. ✅ 生成测试数据
