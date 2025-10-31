import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerDiscountOverviewApi {
  /** 客户贴现业务概览信息 */
  export interface CustomerDiscountOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    discountType: string; // 贴现类型
    billNo: string; // 票据号码
    billType: string; // 票据类型
    currency: string; // 币种
    billAmount: number; // 票据金额
    discountAmount: number; // 贴现金额
    discountBalance: number; // 贴现余额
    interestAmount: number; // 贴息金额
    actualAmount: number; // 实付金额
    discountCount: number; // 贴现笔数
    balanceYearAvg: number; // 本年余额日均
    lastYearBalanceYearAvg: number; // 上年余额日均
    balanceQuarterAvg: number; // 本季度余额日均
    lastYearBalanceQuarterAvg: number; // 上年同期季度余额日均
    balanceMonthAvg: number; // 本月余额日均
    lastYearBalanceMonthAvg: number; // 上年同期月余额日均
    cumulativeYearAmount: number; // 本年累计贴现金额
    lastYearCumulativeAmount: number; // 上年累计贴现金额
    cumulativeQuarterAmount: number; // 本季度累计贴现金额
    lastYearCumulativeQuarterAmount: number; // 上年同期季度累计贴现金额
    cumulativeMonthAmount: number; // 本月累计贴现金额
    lastYearCumulativeMonthAmount: number; // 上年同期月累计贴现金额
    discountRate: number; // 贴现利率
    discountDays: number; // 贴现天数
    billIssueDate: string | Dayjs; // 票据出票日期
    billDueDate: string | Dayjs; // 票据到期日期
    discountDate: string | Dayjs; // 贴现日期
    settleDate: string | Dayjs; // 结算日期
    drawer: string; // 出票人
    payee: string; // 收款人
    acceptor: string; // 承兑人
    acceptanceBank: string; // 承兑银行
    discountStatus: string; // 贴现状态
    settlementStatus: string; // 结算状态
    profitContribution: number; // 利润贡献
    remark: string; // 备注
  }
}

/** 查询客户贴现业务概览分页 */
export function getCustomerDiscountOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerDiscountOverviewApi.CustomerDiscountOverview>>(
    '/aicrm/customer-discount-overview/page',
    { params },
  );
}

/** 查询客户贴现业务概览详情 */
export function getCustomerDiscountOverview(id: number) {
  return requestClient.get<AicrmCustomerDiscountOverviewApi.CustomerDiscountOverview>(
    `/aicrm/customer-discount-overview/get?id=${id}`,
  );
}

/** 新增客户贴现业务概览 */
export function createCustomerDiscountOverview(data: AicrmCustomerDiscountOverviewApi.CustomerDiscountOverview) {
  return requestClient.post('/aicrm/customer-discount-overview/create', data);
}

/** 修改客户贴现业务概览 */
export function updateCustomerDiscountOverview(data: AicrmCustomerDiscountOverviewApi.CustomerDiscountOverview) {
  return requestClient.put('/aicrm/customer-discount-overview/update', data);
}

/** 删除客户贴现业务概览 */
export function deleteCustomerDiscountOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-discount-overview/delete?id=${id}`);
}

/** 批量删除客户贴现业务概览 */
export function deleteCustomerDiscountOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-discount-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户贴现业务概览 */
export function exportCustomerDiscountOverview(params: any) {
  return requestClient.download('/aicrm/customer-discount-overview/export-excel', { params });
}