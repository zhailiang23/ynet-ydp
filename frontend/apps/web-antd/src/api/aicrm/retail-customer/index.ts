import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace RetailCustomerApi {
  /** 零售客户信息 */
  export interface RetailCustomer {
    // RetailCustomerDO 字段
    id: number;
    customerId?: number; // 关联的客户主表ID
    nickname?: string; // 昵称/别名
    usedName?: string; // 曾用名
    surname?: string; // 姓氏
    pinyinName?: string; // 拼音全名
    pinyinAbbr?: string; // 拼音缩写
    personTitle?: string; // 人员称谓
    gender?: number; // 性别(1=男, 2=女, 3=其他)
    birthday?: string; // 出生日期
    birthLocale?: string; // 出生地
    age?: number; // 年龄
    ageRange?: string; // 年龄段
    idCardType?: string; // 证件类型
    idCardNo?: string; // 证件号码
    nationality?: string; // 国籍
    citizenship?: string; // 公民身份
    nation?: string; // 民族
    nativePlace?: string; // 籍贯
    residenceType?: string; // 户籍类型
    residenceDurationType?: string; // 居住时长类型
    domicilePlace?: string; // 户口所在地
    householdType?: string; // 户口类型
    maritalStatus?: string; // 婚姻状况
    healthStatus?: string; // 健康状况
    religion?: string; // 宗教信仰
    politicalStatus?: string; // 政治面貌
    education?: string; // 学历
    degree?: string; // 学位
    graduateSchool?: string; // 毕业学校
    major?: string; // 专业
    graduationDate?: string; // 毕业日期
    occupation?: string; // 职业
    occupationType?: string; // 职业类型
    careerStatus?: string; // 职业状态
    careerStartDate?: string; // 职业开始日期
    employerName?: string; // 工作单位名称
    employerType?: string; // 单位性质
    position?: string; // 职位
    technicalTitle?: string; // 技术职称
    resume?: string; // 简历
    annualIncome?: number; // 年收入
    annualIncomeRange?: string; // 年收入范围
    taxPayment?: string; // 纳税情况
    assets?: number; // 资产总额
    fundHoldings?: number; // 基金持有
    totalInvestment?: number; // 总投资
    investmentNature?: string; // 投资性质
    stockHoldings?: number; // 持股金额
    liabilities?: number; // 负债总额

    // VIP信息
    isVip?: boolean; // 是否VIP客户
    vipLevel?: string; // VIP等级
    vipStartDate?: string; // VIP开始日期
    vipEndDate?: string; // VIP到期日期
    vipPoints?: number; // 积分

    // 收入与资产信息
    monthlyIncome?: number; // 月收入
    sourceOfIncome?: string; // 收入来源
    hasHouse?: boolean; // 是否有房产
    hasCar?: boolean; // 是否有车
    hasInsurance?: boolean; // 是否有保险

    // 信誉信息
    hasLoanRecord?: boolean; // 是否有贷款记录
    hasOverdueRecord?: boolean; // 是否有逾期记录
    blacklistFlag?: boolean; // 黑名单标志
    reputationStatus?: string; // 信誉状态
    reputationLevel?: string; // 信誉级别
    reputationScore?: number; // 信誉综合评分

    // 客户关系信息
    retailCustomerType?: string; // 零售客户类型
    personCustomerType?: string; // 个人客户类型(老系统)
    personConductEval?: string; // 个人行为评价
    customerBankRelation?: string; // 客户银行关系
    companyRelationDegree?: string; // 公司关联度
    individualType?: string; // 个体类型
    isHighEndCustomer?: boolean; // 是否高端户
    isComprehensiveCustomer?: boolean; // 是否综合户
    customerGridCode?: string; // 客户归属网格编号
    qualificationRisk?: string; // 资质/风险
    creditRiskLevel?: string; // 信誉水险等级
    creditRiskRating?: string; // 信誉水险评级
    isPayrollCustomer?: boolean; // 是否我行代发工资客户
    payrollCompanyName?: string; // 代发工资单位名称
    salaryAccountBank?: string; // 工资账户银行
    becomeCustomerDate?: string; // 成为我行客户时间
    establishTrustDate?: string; // 在我行建立信任关系时间

    // 登记信息
    startDate?: string; // 开始日期
    registrationType?: string; // 登记类型
    registrationNo?: string; // 登记号
    registrationStartDate?: string; // 登记开始日期
    registrationEndDate?: string; // 登记结束日期

    // 数据迁移追溯
    etlDate?: string; // ETL导入日期
    oldTxSeqNo?: string; // 老系统交易序列号
    oldCustId?: string; // 老系统客户ID
    oldLastUpdateSys?: string; // 老系统最后更新系统
    isBankModified?: boolean; // 是否本行修改
    extField1?: string; // 扩展字段1
    extField2?: string; // 扩展字段2
    extField3?: string; // 扩展字段3

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

/** 查询零售客户详情 */
export function getRetailCustomer(id: number) {
  return requestClient.get<RetailCustomerApi.RetailCustomer>(
    `/crm/retail-customer/get?id=${id}`,
  );
}

/** 查询零售客户分页 */
export function getRetailCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<RetailCustomerApi.RetailCustomer>>(
    '/crm/retail-customer/page',
    { params },
  );
}
