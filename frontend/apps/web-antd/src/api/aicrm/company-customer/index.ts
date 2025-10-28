import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace CompanyCustomerApi {
  /** 对公客户信息 */
  export interface CompanyCustomer {
    // CompanyCustomerDO 字段
    id: number;
    customerId?: number; // 关联的客户主表ID
    enterpriseName?: string; // 企业名称
    unifiedSocialCreditCode?: string; // 统一社会信用代码
    businessLicenseNo?: string; // 营业执照号
    organizationCode?: string; // 组织机构代码
    taxRegistrationNo?: string; // 税务登记号
    registeredCapital?: number; // 注册资本
    paidInCapital?: number; // 实缴资本
    enterpriseType?: string; // 企业类型
    industryCategory?: string; // 行业类别
    businessScope?: string; // 经营范围
    establishmentDate?: string; // 成立日期
    registrationAuthority?: string; // 登记机关
    approvalDate?: string; // 核准日期
    registeredAddress?: string; // 注册地址
    businessAddress?: string; // 经营地址
    postalCode?: string; // 邮政编码
    contactPhone?: string; // 联系电话
    faxNumber?: string; // 传真号码
    email?: string; // 电子邮箱
    website?: string; // 公司网址
    legalRepresentative?: string; // 法定代表人
    legalRepIdCardNo?: string; // 法人身份证号
    legalRepPhone?: string; // 法人联系电话
    actualController?: string; // 实际控制人
    mainShareholders?: string; // 主要股东
    employeeCount?: number; // 员工人数
    annualRevenue?: number; // 年营业额
    netProfit?: number; // 净利润
    totalAssets?: number; // 总资产
    totalLiabilities?: number; // 总负债
    listedStatus?: string; // 上市状态
    stockCode?: string; // 股票代码
    isGroupCompany?: boolean; // 是否集团公司
    parentCompany?: string; // 母公司名称
    subsidiaries?: string; // 子公司列表
    businessPartners?: string; // 主要业务伙伴
    bankName?: string; // 开户银行
    bankAccount?: string; // 银行账号
    taxClassification?: string; // 纳税人类型
    invoiceType?: string; // 发票类型
    paymentTerms?: string; // 付款条件
    cooperationMode?: string; // 合作模式
    hasLongTermContract?: boolean; // 是否有长期合同

    // 财务信息(5个)
    totalAssets?: number; // 资产总额
    totalDebt?: number; // 负债总额
    annualIncome?: number; // 年收入
    annualProfit?: number; // 年利润
    finReportType?: string; // 财务报表类型

    // 股权投资(3个)
    isStockHolder?: boolean; // 是否股东
    holdStockAmt?: number; // 持股金额
    investmentType?: string; // 投资类型

    // 企业组织(7个)
    orgForm?: string; // 组织形式
    governanceStructure?: string; // 治理结构
    holdingType?: string; // 控股类型
    enterpriseBelong?: string; // 企业归属
    superiorDept?: string; // 上级部门
    companyOrganization?: string; // 公司机构
    nationCode?: string; // 国家代码

    // 业务经营(8个)
    mainBusiness?: string; // 主营业务
    minorBusiness?: string; // 辅营业务
    businessMode?: string; // 经营模式
    businessStartDate?: string; // 营业开始日期
    industryCharacter?: string; // 行业特征
    industryDevelopmentProspect?: string; // 行业发展前景
    areaCode?: string; // 地区代码
    industryPosition?: string; // 行业地位

    // 企业规模(4个)
    employeeScale?: string; // 员工规模
    assetsScale?: string; // 资产规模
    productionCapacity?: string; // 生产能力
    enterpriseProperty?: string; // 企业性质

    // 监管评级(3个)
    loanCardFlag?: boolean; // 贷款卡标志
    loanCardStatus?: string; // 贷款卡状态
    loanCardAuditDate?: string; // 贷款卡审核日期

    // 其他特殊(2个)
    enterpriseScalePboc?: string; // 人行企业规模
    enterpriseScaleDeposit?: string; // 存款企业规模

    // 系统追溯(4个)
    etlDate?: string; // ETL导入日期
    oldTxSeqNo?: string; // 老系统交易序列号
    oldLastUpdateSys?: string; // 老系统最后更新系统
    oldCustId?: string; // 老系统客户ID

    // CustomerDO 共有字段
    customerNo?: string; // 客户编号
    customerType?: number; // 客户类型
    customerName?: string; // 客户名称
    customerLevel?: string; // 客户等级
    customerStatus?: number; // 客户状态
    isHighQuality?: boolean; // 是否优质客户
    isImportant?: boolean; // 是否重要客户
    creditStatus?: string; // 信用状态
    creditLevel?: string; // 信用等级
    creditScore?: number; // 信用评分
    customerSource?: string; // 客户来源
    customerTag?: string; // 客户标签
    remark?: string; // 备注信息
    deptId?: number; // 所属部门ID
    createTime?: string; // 创建时间
    updateTime?: string; // 更新时间
  }
}

/** 查询对公客户详情 */
export function getCompanyCustomer(id: number) {
  return requestClient.get<CompanyCustomerApi.CompanyCustomer>(
    `/crm/company-customer/get?id=${id}`,
  );
}

/** 查询对公客户分页 */
export function getCompanyCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<CompanyCustomerApi.CompanyCustomer>>(
    '/crm/company-customer/page',
    { params },
  );
}
