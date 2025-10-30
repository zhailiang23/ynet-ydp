import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyOtherBankApi {
  /** 对公客户他行信息信息 */
  export interface CompanyOtherBank {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联crm_customer表）
    bankName?: string; // 银行名称
    bankType: string; // 银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）
    branchName: string; // 开户支行名称
    relationshipManager: string; // 他行客户经理姓名
    managerPhone: string; // 他行客户经理电话
    managerEmail: string; // 他行客户经理邮箱
    cooperationType: string; // 合作类型（主办行、协办行、一般合作）
    cooperationStartDate: string | Dayjs; // 合作开始日期
    relationshipDuration: number; // 合作年限
    cooperationStatus: number; // 合作状态（1=合作中 2=已终止 3=暂停合作）
    hasSettlementAccount: number; // 是否有结算账户（0=否 1=是）
    settlementAccountNo: string; // 结算账户账号
    accountBalance: number; // 账户余额（元）
    isMainSettlementBank: number; // 是否主结算行（0=否 1=是）
    dailyAverageBalance: number; // 日均存款（元）
    totalCreditLimit: number; // 授信总额度（元）
    usedCreditLimit: number; // 已用授信额度（元）
    loanBalance: number; // 贷款余额（元）
    depositBalance: number; // 存款余额（元）
    wealthManagementBalance: number; // 理财余额（元）
    businessTypes: string; // 业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）
    mainBusiness: string; // 主要业务
    loanProductName: string; // 贷款产品名称
    loanAmount: number; // 贷款金额（元）
    loanRate: number; // 贷款利率（%）
    loanStartDate: string | Dayjs; // 贷款起始日
    loanMaturityDate: string | Dayjs; // 贷款到期日
    guaranteeType: string; // 担保方式（信用、抵押、质押、保证等）
    collateralInfo: string; // 抵押物信息
    serviceSatisfaction: number; // 服务满意度（1-5星）
    pricingLevel: string; // 价格水平（优惠、市场价、偏高）
    responseSpeed: string; // 响应速度（快、一般、慢）
    customerComment: string; // 客户评价
    competitorAdvantage: string; // 他行优势（为什么客户选择他行）
    competitorDisadvantage: string; // 他行劣势（客户不满意的地方）
    ourOpportunity: string; // 我行机会点（可以从哪些方面切入）
    competitiveStrategy: string; // 竞争策略（如何争取客户份额）
    targetBusiness: string; // 目标业务（希望从他行抢占的业务）
    marketingPriority: number; // 营销优先级（1=高 2=中 3=低）
    contractNo: string; // 合同编号
    contractExpiryDate: string | Dayjs; // 合同到期日
    isDueSoon: number; // 是否即将到期（0=否 1=是，3个月内到期）
    followUpPlan: string; // 跟进计划
    riskWarning: string; // 风险提示（如他行抽贷风险、担保风险等）
    infoSource: string; // 信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）
    infoReliability: number; // 信息可靠性（1=高 2=中 3=低）
    lastUpdateDate: string | Dayjs; // 信息最后更新日期
    remark: string; // 备注
  }
}

/** 查询对公客户他行信息分页 */
export function getCompanyOtherBankPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyOtherBankApi.CompanyOtherBank>>(
    '/aicrm/company-other-bank/page',
    { params },
  );
}

/** 查询对公客户他行信息详情 */
export function getCompanyOtherBank(id: number) {
  return requestClient.get<AicrmCompanyOtherBankApi.CompanyOtherBank>(
    `/aicrm/company-other-bank/get?id=${id}`,
  );
}

/** 新增对公客户他行信息 */
export function createCompanyOtherBank(data: AicrmCompanyOtherBankApi.CompanyOtherBank) {
  return requestClient.post('/aicrm/company-other-bank/create', data);
}

/** 修改对公客户他行信息 */
export function updateCompanyOtherBank(data: AicrmCompanyOtherBankApi.CompanyOtherBank) {
  return requestClient.put('/aicrm/company-other-bank/update', data);
}

/** 删除对公客户他行信息 */
export function deleteCompanyOtherBank(id: number) {
  return requestClient.delete(`/aicrm/company-other-bank/delete?id=${id}`);
}

/** 批量删除对公客户他行信息 */
export function deleteCompanyOtherBankList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-other-bank/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出对公客户他行信息 */
export function exportCompanyOtherBank(params: any) {
  return requestClient.download('/aicrm/company-other-bank/export-excel', { params });
}