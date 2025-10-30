import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountLoanApi {
  /** 客户贷款账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountLoan {
    id: number; // 贷款账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 贷款账号
    contractNo: string; // 合同号
    agrNo: string; // 协议号
    productName?: string; // 贷款产品名称
    productId: string; // 产品ID
    accountName?: string; // 借款人姓名
    openDate?: string | Dayjs; // 放款日期（开户日期）
    closeDate: string | Dayjs; // 结清日期（销户日期）
    accountStatus?: string; // 账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）
    contractAmount?: number; // 合同金额（授信额度）
    loanAmount?: number; // 贷款金额（实际发放金额）
    balance?: number; // 贷款余额
    currencyType: string; // 币种（字典: aicrm_currency_type）
    interestRate?: number; // 贷款利率（年化%）
    loanTerm?: number; // 贷款期限（月）
    loanTermUnit: string; // 期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）
    matureDate?: string | Dayjs; // 到期日
    repaymentMode: string; // 还款方式（字典: aicrm_repayment_mode）
    loanPurpose: string; // 贷款用途
    loanType: string; // 贷款类型（字典: aicrm_loan_type）
    guaranteeType: string; // 担保方式（字典: aicrm_guarantee_type）
    businessType: string; // 业务类型
    fiveLevelClass: string; // 五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）
    overdueDays: number; // 逾期天数
    overduePrincipal: number; // 逾期本金
    overdueInterest: number; // 逾期利息
    overdueTimes: number; // 累计逾期次数
    deptId: number; // 放款机构ID（关联 system_dept.id）
    deptName: string; // 放款机构名称
    managerUserId: number; // 客户经理用户ID（关联 system_users.id）
    monthAvgBalance: number; // 月日均余额
    quarterAvgBalance: number; // 季日均余额
    yearAvgBalance: number; // 年日均余额
    totalRepaidAmount: number; // 累计还款金额
    totalRepaidInterest: number; // 累计还款利息
    remark: string; // 备注
  }
}

/** 查询客户贷款账户信息表（零售+对公共用）分页 */
export function getCustomerAccountLoanPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountLoanApi.CustomerAccountLoan>>(
    '/aicrm/customer-account-loan/page',
    { params },
  );
}

/** 查询客户贷款账户信息表（零售+对公共用）详情 */
export function getCustomerAccountLoan(id: number) {
  return requestClient.get<AicrmCustomerAccountLoanApi.CustomerAccountLoan>(
    `/aicrm/customer-account-loan/get?id=${id}`,
  );
}

/** 新增客户贷款账户信息表（零售+对公共用） */
export function createCustomerAccountLoan(data: AicrmCustomerAccountLoanApi.CustomerAccountLoan) {
  return requestClient.post('/aicrm/customer-account-loan/create', data);
}

/** 修改客户贷款账户信息表（零售+对公共用） */
export function updateCustomerAccountLoan(data: AicrmCustomerAccountLoanApi.CustomerAccountLoan) {
  return requestClient.put('/aicrm/customer-account-loan/update', data);
}

/** 删除客户贷款账户信息表（零售+对公共用） */
export function deleteCustomerAccountLoan(id: number) {
  return requestClient.delete(`/aicrm/customer-account-loan/delete?id=${id}`);
}

/** 批量删除客户贷款账户信息表（零售+对公共用） */
export function deleteCustomerAccountLoanList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-loan/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户贷款账户信息表（零售+对公共用） */
export function exportCustomerAccountLoan(params: any) {
  return requestClient.download('/aicrm/customer-account-loan/export-excel', { params });
}