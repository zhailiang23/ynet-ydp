# 客户详情页字段映射方案

## 数据结构关系

```
CustomerDO (公共表 crm_customer)
    ├── RetailCustomerDO (零售客户扩展表 crm_retail_customer)
    │   └── customerId 外键关联
    └── CompanyCustomerDO (对公客户扩展表 crm_company_customer)
        └── customerId 外键关联
```

---

## 零售客户详情页字段

### 数据来源
- **RetailCustomerRespVO** = RetailCustomerDO 字段 + CustomerDO 字段（通过 customerId 关联）

### 字段分组展示

#### 1. 基本信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 客户编号 | Customer | customerNo | - | |
| 客户类型 | Customer | customerType | crm_customer_type | 显示"零售客户" |
| 客户名称 | Customer | customerName | - | |
| 客户等级 | Customer | customerLevel | crm_customer_level | |
| 客户状态 | Customer | customerStatus | crm_customer_status | 徽章展示 |
| 所属部门 | Customer | deptId | - | 需转换为部门名称 |

#### 2. 零售客户个人信息卡片 (来自 RetailCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 昵称 | RetailCustomer | nickname | - | |
| 性别 | RetailCustomer | gender | system_user_sex | |
| 出生日期 | RetailCustomer | birthday | - | |
| 年龄 | RetailCustomer | age | - | |
| 国籍 | RetailCustomer | nationality | - | |
| 民族 | RetailCustomer | nation | - | |
| 籍贯 | RetailCustomer | nativePlace | - | |
| 职业 | RetailCustomer | occupation | - | |
| 婚姻状态 | RetailCustomer | maritalStatus | - | 需创建字典 |
| 教育程度 | RetailCustomer | education | - | |

#### 3. VIP 信息卡片 (来自 RetailCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 是否VIP客户 | RetailCustomer | isVip | - | 布尔徽章 |
| 是否核心VIP | RetailCustomer | isCoreVip | - | 布尔徽章 |
| VIP等级 | RetailCustomer | vipLevel | - | |
| 是否高净值客户 | RetailCustomer | isHighNetWorth | - | 布尔徽章 |
| 净值类型 | RetailCustomer | netWorthType | - | |

#### 4. 信用信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 信用状态 | Customer | creditStatus | - | 带颜色标签 |
| 信用等级 | Customer | creditLevel | crm_credit_level | |
| 信用评分 | Customer | creditScore | - | 数字，保留2位小数 |

#### 5. 信誉信息卡片 (来自 RetailCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 信誉状态 | RetailCustomer | reputationStatus | - | |
| 信誉级别 | RetailCustomer | reputationLevel | - | |
| 信誉评分 | RetailCustomer | reputationScore | - | |

#### 6. 收入资产信息卡片 (来自 RetailCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 收入水平 | RetailCustomer | incomeLevel | - | |
| 资产水平 | RetailCustomer | assetLevel | - | |

#### 7. 业务信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 客户来源 | Customer | customerSource | crm_customer_source | |
| 客户标签 | Customer | customerTag | - | 标签组展示 |
| 是否优质客户 | Customer | isHighQuality | - | 布尔徽章 |
| 是否重要客户 | Customer | isImportant | - | 布尔徽章 |

#### 8. 其他信息卡片
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 备注 | Customer | remark | - | 多行文本 |
| 创建时间 | Customer | createTime | - | |
| 更新时间 | Customer | updateTime | - | |

---

## 对公客户详情页字段

### 数据来源
- **CompanyCustomerRespVO** = CompanyCustomerDO 字段 + CustomerDO 字段（通过 customerId 关联）

### 字段分组展示

#### 1. 基本信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 客户编号 | Customer | customerNo | - | |
| 客户类型 | Customer | customerType | crm_customer_type | 显示"对公客户" |
| 客户名称 | Customer | customerName | - | 即企业名称 |
| 客户等级 | Customer | customerLevel | crm_customer_level | |
| 客户状态 | Customer | customerStatus | crm_customer_status | 徽章展示 |
| 所属部门 | Customer | deptId | - | 需转换为部门名称 |

#### 2. 企业证照信息卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 证件类型 | CompanyCustomer | licenseType | - | |
| 证件号码 | CompanyCustomer | licenseNo | - | |
| 统一社会信用代码 | CompanyCustomer | creditCode | - | |
| 组织机构代码 | CompanyCustomer | organizationCode | - | |
| 纳税人识别号 | CompanyCustomer | taxNo | - | |
| 贷款卡号 | CompanyCustomer | loanCardNo | - | |

#### 3. 企业基本信息卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 企业类型 | CompanyCustomer | enterpriseType | - | |
| 企业性质 | CompanyCustomer | enterpriseNature | - | |
| 企业控股类型 | CompanyCustomer | ownershipType | - | |
| 企业经济性质 | CompanyCustomer | economicType | - | |
| 企业规模 | CompanyCustomer | enterpriseScale | - | |
| 注册资本 | CompanyCustomer | registeredCapital | - | 带单位"万元" |
| 注册资本币种 | CompanyCustomer | registeredCapitalCurrency | - | |
| 企业成立日期 | CompanyCustomer | establishDate | - | |
| 营业期限 | CompanyCustomer | businessTerm | - | |

#### 4. 行业分类信息卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 行业分类一级 | CompanyCustomer | industryCategoryL1 | - | |
| 行业分类二级 | CompanyCustomer | industryCategoryL2 | - | |
| 行业分类三级 | CompanyCustomer | industryCategoryL3 | - | |
| 行业分类四级 | CompanyCustomer | industryCategoryL4 | - | |
| 行业代码 | CompanyCustomer | industryCode | - | |

#### 5. 企业特征标识卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 是否上市公司 | CompanyCustomer | isListed | - | 布尔徽章 |
| 是否小微企业 | CompanyCustomer | isSmallEnterprise | - | 布尔徽章 |
| 是否集团客户 | CompanyCustomer | isGroupCustomer | - | 布尔徽章 |
| 是否有进出口权 | CompanyCustomer | isImportExport | - | 布尔徽章 |
| 是否我行关联方 | CompanyCustomer | isRelatedParty | - | 布尔徽章 |
| 是否网银签约客户 | CompanyCustomer | isEbankSigned | - | 布尔徽章 |
| 是否涉农企业 | CompanyCustomer | isAgricultureRelated | - | 布尔徽章 |

#### 6. 账户信息卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 基本账户开户行 | CompanyCustomer | basicAccountBank | - | |
| 基本账户账号 | CompanyCustomer | basicAccountNo | - | |

#### 7. 法定代表人信息卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 法定代表人姓名 | CompanyCustomer | legalPersonName | - | |
| 法定代表人证件类型 | CompanyCustomer | legalPersonIdType | - | |
| 法定代表人证件号码 | CompanyCustomer | legalPersonIdNo | - | 加密显示 |
| 法定代表人联系电话 | CompanyCustomer | legalPersonPhone | - | 加密显示 |

#### 8. 企业资质评级卡片 (来自 CompanyCustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 企业资质 | CompanyCustomer | enterpriseQualification | - | 标签组展示 |
| 管理部门 | CompanyCustomer | managementDept | - | |
| 监管部门 | CompanyCustomer | superviseDept | - | |
| 企业评级 | CompanyCustomer | companyRating | - | |
| 评级机构 | CompanyCustomer | ratingAgency | - | |
| 评级日期 | CompanyCustomer | ratingDate | - | |

#### 9. 信用信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 信用状态 | Customer | creditStatus | - | 带颜色标签 |
| 信用等级 | Customer | creditLevel | crm_credit_level | |
| 信用评分 | Customer | creditScore | - | 数字，保留2位小数 |

#### 10. 业务信息卡片 (来自 CustomerDO)
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 客户来源 | Customer | customerSource | crm_customer_source | |
| 客户标签 | Customer | customerTag | - | 标签组展示 |
| 是否优质客户 | Customer | isHighQuality | - | 布尔徽章 |
| 是否重要客户 | Customer | isImportant | - | 布尔徽章 |

#### 11. 其他信息卡片
| 字段 | 来源表 | 字段名 | 字典类型 | 说明 |
|------|--------|--------|----------|------|
| 备注 | Customer | remark | - | 多行文本 |
| 创建时间 | Customer | createTime | - | |
| 更新时间 | Customer | updateTime | - | |

---

## 后端实现要求

### RetailCustomerRespVO 需要包含的字段

```java
// 来自 RetailCustomerDO 的字段（已有）
private Long customerId;
private String nickname;
private Integer gender;
private LocalDate birthday;
private Integer age;
// ... 其他 RetailCustomerDO 字段

// 需要新增：来自 CustomerDO 的字段
private String customerNo;
private Integer customerType;
private String customerName;
private String customerLevel;
private Integer customerStatus;
private Boolean isHighQuality;
private Boolean isImportant;
private String creditStatus;
private String creditLevel;
private BigDecimal creditScore;
private String customerSource;
private String customerTag;
private String remark;
private Long deptId;
private LocalDateTime createTime;
private LocalDateTime updateTime;
```

### CompanyCustomerRespVO 需要包含的字段

```java
// 来自 CompanyCustomerDO 的字段（已有）
private Long customerId;
private String licenseType;
private String licenseNo;
// ... 其他 CompanyCustomerDO 字段

// 需要新增：来自 CustomerDO 的字段
private String customerNo;
private Integer customerType;
private String customerName;
private String customerLevel;
private Integer customerStatus;
private Boolean isHighQuality;
private Boolean isImportant;
private String creditStatus;
private String creditLevel;
private BigDecimal creditScore;
private String customerSource;
private String customerTag;
private String remark;
private Long deptId;
private LocalDateTime createTime;
private LocalDateTime updateTime;
```

### Service 层实现方案

**方案 1: 使用 MyBatis Plus 关联查询（推荐）**

在 Service 的 `getRetailCustomer(Long id)` 和 `getCompanyCustomer(Long id)` 方法中：

1. 查询 RetailCustomerDO 或 CompanyCustomerDO
2. 通过 customerId 关联查询 CustomerDO
3. 使用 MapStruct 或手动合并两个对象的字段到 RespVO

**方案 2: 修改 Mapper XML 使用 JOIN 查询**

在 Mapper XML 中编写 LEFT JOIN 查询语句，一次性查询出所有字段。

---

## 前端实现要求

### API 类型定义

需要在前端定义两个完整的类型：

```typescript
// frontend/apps/web-antd/src/api/aicrm/retail-customer/types.ts
export interface RetailCustomer {
  // RetailCustomer 特有字段
  customerId: number;
  nickname?: string;
  gender?: number;
  birthday?: string;
  age?: number;
  // ... 其他 RetailCustomer 字段

  // Customer 公共字段
  customerNo: string;
  customerType: number;
  customerName: string;
  customerLevel?: string;
  customerStatus: number;
  isHighQuality?: boolean;
  isImportant?: boolean;
  creditStatus?: string;
  creditLevel?: string;
  creditScore?: number;
  customerSource?: string;
  customerTag?: string;
  remark?: string;
  deptId?: number;
  createTime: string;
  updateTime: string;
}
```

```typescript
// frontend/apps/web-antd/src/api/aicrm/company-customer/types.ts
export interface CompanyCustomer {
  // CompanyCustomer 特有字段
  customerId: number;
  licenseType?: string;
  licenseNo?: string;
  creditCode?: string;
  // ... 其他 CompanyCustomer 字段

  // Customer 公共字段
  customerNo: string;
  customerType: number;
  customerName: string;
  customerLevel?: string;
  customerStatus: number;
  isHighQuality?: boolean;
  isImportant?: boolean;
  creditStatus?: string;
  creditLevel?: string;
  creditScore?: number;
  customerSource?: string;
  customerTag?: string;
  remark?: string;
  deptId?: number;
  createTime: string;
  updateTime: string;
}
```

---

## 注意事项

1. **不展示主键 ID**: RetailCustomerDO.id 和 CompanyCustomerDO.id 都不展示在前端
2. **customerId 也不展示**: 这是外键，前端不需要展示
3. **字典转换**: customerType, customerStatus, customerLevel, creditLevel, customerSource 都需要使用字典转换
4. **布尔值徽章**: isHighQuality, isImportant, isVip 等布尔字段使用徽章展示
5. **敏感信息加密**: legalPersonIdNo, legalPersonPhone 需要加密展示（如：133****8888）
6. **部门名称**: deptId 需要转换为部门名称展示
7. **标签展示**: customerTag, enterpriseQualification 等字段（逗号分隔）需要拆分为标签组展示
