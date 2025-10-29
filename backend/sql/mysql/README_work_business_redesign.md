# 客户工作信息和经营信息表重新设计说明

## 设计原则

**核心原则**: 每张表只包含其核心业务字段,删除通用字段和冗余信息

## 表结构对比

### 一、工作信息表 (crm_customer_work_info)

#### 精简前字段 (36个业务字段)
- 工作类型、单位信息 (4)
- 职位信息 (3)
- 时间信息 (4): start_date, end_date, work_years, is_current
- 地址信息 (4): province, city, district, detail
- 收入信息 (5): annual_income, monthly_income, income_source, social_insurance, housing_fund
- 联系方式 (2): work_phone, work_email
- 核验信息 (3): verification_status, verification_time, verification_remark
- 附件和扩展 (2): attachment_urls, extra_data

#### 精简后字段 (13个业务字段) ✓
- **单位信息 (4)**: employer_name, employer_type, industry, work_years
- **职位信息 (3)**: position, position_level, department
- **收入信息 (2)**: annual_income, monthly_income
- **社保信息 (2)**: has_social_insurance, has_housing_fund
- **联系方式 (2)**: work_phone, work_email

#### 删除的字段
- ❌ work_type (工作类型) - 有工作信息就表示在职
- ❌ start_date, end_date, is_current (时间字段) - 非核心工作信息
- ❌ work_address_* (4个地址字段) - 地址信息应该在客户基础信息表维护
- ❌ income_source (收入来源说明) - 可用remark字段
- ❌ verification_status, verification_time, verification_remark (核验字段) - 通用字段,非工作核心
- ❌ attachment_urls (附件) - 通用字段,可单独建附件表
- ❌ extra_data (扩展数据) - 尽量避免使用JSON字段

### 二、经营信息表 (crm_customer_business_info)

#### 精简前字段 (40个业务字段)
- 经营主体信息 (5)
- 经营范围和规模 (6)
- 地址信息 (4): province, city, district, detail
- 经营数据 (4): annual_revenue, monthly_revenue, annual_profit, production_capacity
- 时间信息 (3): start_date, end_date, is_current
- 联系方式 (3): contact_person, contact_phone, contact_email
- 税务信息 (2)
- 核验信息 (3): verification_status, verification_time, verification_remark
- 附件和扩展 (2): attachment_urls, extra_data
- 其他 (2): business_license_expire_date, registration_date

#### 精简后字段 (14个业务字段) ✓
- **主体信息 (3)**: business_name, business_type, business_license_no
- **经营规模 (6)**: business_scope, industry, business_scale, business_status, registered_capital, employee_count
- **经营数据 (3)**: annual_revenue, monthly_revenue, annual_profit
- **税务信息 (2)**: tax_registration_no, is_general_taxpayer

#### 删除的字段
- ❌ business_license_expire_date, registration_date (证照时间) - 非经营核心数据
- ❌ production_capacity (产能说明) - 可用remark字段
- ❌ start_date, end_date, is_current (时间字段) - 非核心经营信息
- ❌ business_address_* (4个地址字段) - 地址信息应该在客户基础信息表维护
- ❌ contact_person, contact_phone, contact_email (联系方式) - 联系人应该是客户本人
- ❌ verification_status, verification_time, verification_remark (核验字段) - 通用字段,非经营核心
- ❌ attachment_urls (附件) - 通用字段,可单独建附件表
- ❌ extra_data (扩展数据) - 尽量避免使用JSON字段

## 精简效果

| 表名 | 精简前 | 精简后 | 精简率 |
|------|--------|--------|--------|
| 工作信息表 | 36字段 | 13字段 | 64% ↓ |
| 经营信息表 | 40字段 | 14字段 | 65% ↓ |

## 被删除字段的处理建议

### 1. 地址信息
**建议**: 在客户基础信息表 (crm_customer) 中维护
- 家庭地址
- 工作地址 (如需要)
- 经营地址 (如需要)

### 2. 时间范围信息 (start_date, end_date, is_current)
**建议**:
- 如果需要维护多个工作/经营历史,可通过1对多关系实现
- 当前生效的记录可通过业务逻辑判断 (最新的一条记录)
- 或者在客户基础信息表中维护 "当前职业状态" 字段

### 3. 核验信息 (verification_*)
**建议**:
- 如果需要审核流程,可以单独建立审核记录表
- 或者在业务层面通过状态机管理

### 4. 附件信息 (attachment_urls)
**建议**: 建立统一的附件表
```sql
CREATE TABLE crm_customer_attachment (
  id bigint PRIMARY KEY,
  customer_id bigint NOT NULL,
  attachment_type varchar(50), -- 'work_cert', 'business_license', etc.
  file_url varchar(500),
  file_name varchar(200),
  ...
)
```

### 5. 扩展数据 (extra_data JSON)
**建议**:
- 尽量避免使用JSON字段
- 如果确实需要灵活扩展,考虑使用EAV模式或单独的扩展字段表

## 测试数据

创建了15个客户的测试数据:
- **仅职员**: 5人 (客户1-5)
- **仅个体/企业主**: 5人 (客户6-10)
- **两者兼有**: 5人 (客户11-15)

### 两者兼有的典型场景
1. **客户11**: 电商公司市场主管 + 业余经营网店
2. **客户12**: 高校讲师 + 企业管理咨询工作室
3. **客户13**: 国企财务经理 + 家族建材公司股东(持股30%)
4. **客户14**: 外企技术专家 + 创业公司技术入股(持股15%)
5. **客户15**: 食品公司生产总监 + 退休后创业餐饮店

## 相关SQL文件

1. **crm_customer_work_business_redesign.sql** - 精简版表结构定义
2. **crm_customer_work_business_redesign_test_data.sql** - 精简版测试数据
3. ~~crm_customer_work_split.sql~~ (旧版,已废弃)
4. ~~crm_customer_work_business_test_data.sql~~ (旧版,已废弃)
5. ~~crm_customer_work_business_combined_test_data.sql~~ (旧版,已废弃)

## 统计数据

### 工作信息统计
- 总记录数: 10条
- 政府机关: 1人 | 事业单位: 2人 | 国企: 2人 | 民企: 3人 | 外企: 2人
- 平均年收入: 31.1万元
- 高管: 1人 | 总监: 4人 | 经理: 2人

### 经营信息统计
- 总记录数: 10条
- 个体工商户: 4个 | 公司: 5个 | 合伙企业: 1个
- 小型: 6个 | 中型: 4个
- 平均年营收: 773.6万元
- 平均年利润: 113万元
- 一般纳税人: 5个
- 总员工数: 345人

### 客户类型分布
- 仅职员: 5人 (33%)
- 仅个体/企业主: 5人 (33%)
- 两者兼有: 5人 (33%)
- 总客户数: 15人
