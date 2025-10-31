import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerLoanOverviewApi {
  /** 客户贷款业务概览信息 */
  export interface CustomerLoanOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    loanType: string; // 贷款类型
    currency: string; // 币种
    loanAmount: number; // 贷款余额
    loanAccountCount: number; // 贷款账户数
    loanCustomerCount: number; // 贷款客户数
    normalBusinessBalance: number; // 正常业务余额
    overdueBalance: number; // 逾期余额
    badLoanBalance: number; // 不良贷款余额
    businessBalance: number; // 业务余额
    balanceYearAvg: number; // 本年余额日均
    lastYearBalanceYearAvg: number; // 上年余额日均
    balanceQuarterAvg: number; // 本季度余额日均
    lastYearBalanceQuarterAvg: number; // 上年同期季度余额日均
    balanceMonthAvg: number; // 本月余额日均
    lastYearBalanceMonthAvg: number; // 上年同期月余额日均
    loanAmountTotal: number; // 贷款金额
    lastYearLoanAmount: number; // 上年贷款余额
    businessAmount: number; // 业务金额
    creditTotalAmount: number; // 授信总额
    amountYearAvg: number; // 本年金额日均
    lastYearAmountYearAvg: number; // 上年金额日均
    amountQuarterAvg: number; // 本季度金额日均
    lastYearAmountQuarterAvg: number; // 上年同期季度金额日均
    amountMonthAvg: number; // 本月金额日均
    lastYearAmountMonthAvg: number; // 上年同期月金额日均
    outsideLoanUsage: number; // 表外贷款使用率
    insideLoanUsage: number; // 表内贷款使用率
    billLoanUsage: number; // 票据贷款使用率
    loanProfit: number; // 贷款利润贡献
    interestRate: number; // 贷款利率
    overdueDays: number; // 逾期天数
    riskLevel: string; // 风险等级
    remark: string; // 备注
  }
}

/** 查询客户贷款业务概览分页 */
export function getCustomerLoanOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerLoanOverviewApi.CustomerLoanOverview>>(
    '/aicrm/customer-loan-overview/page',
    { params },
  );
}

/** 查询客户贷款业务概览详情 */
export function getCustomerLoanOverview(id: number) {
  return requestClient.get<AicrmCustomerLoanOverviewApi.CustomerLoanOverview>(
    `/aicrm/customer-loan-overview/get?id=${id}`,
  );
}

/** 新增客户贷款业务概览 */
export function createCustomerLoanOverview(data: AicrmCustomerLoanOverviewApi.CustomerLoanOverview) {
  return requestClient.post('/aicrm/customer-loan-overview/create', data);
}

/** 修改客户贷款业务概览 */
export function updateCustomerLoanOverview(data: AicrmCustomerLoanOverviewApi.CustomerLoanOverview) {
  return requestClient.put('/aicrm/customer-loan-overview/update', data);
}

/** 删除客户贷款业务概览 */
export function deleteCustomerLoanOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-loan-overview/delete?id=${id}`);
}

/** 批量删除客户贷款业务概览 */
export function deleteCustomerLoanOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-loan-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户贷款业务概览 */
export function exportCustomerLoanOverview(params: any) {
  return requestClient.download('/aicrm/customer-loan-overview/export-excel', { params });
}