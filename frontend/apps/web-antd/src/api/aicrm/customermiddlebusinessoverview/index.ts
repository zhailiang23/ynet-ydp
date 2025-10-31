import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerMiddleBusinessOverviewApi {
  /** 客户中间业务概览信息 */
  export interface CustomerMiddleBusinessOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    businessType: string; // 业务类型
    businessCode: string; // 业务代码
    businessName: string; // 业务名称
    currency: string; // 币种
    businessAmount: number; // 业务金额
    transactionAmount: number; // 交易金额
    feeAmount: number; // 手续费金额
    commissionAmount: number; // 佣金金额
    transactionCount: number; // 交易笔数
    amountYearAvg: number; // 本年金额日均
    lastYearAmountYearAvg: number; // 上年金额日均
    amountQuarterAvg: number; // 本季度金额日均
    lastYearAmountQuarterAvg: number; // 上年同期季度金额日均
    amountMonthAvg: number; // 本月金额日均
    lastYearAmountMonthAvg: number; // 上年同期月金额日均
    cumulativeYearAmount: number; // 本年累计金额
    lastYearCumulativeAmount: number; // 上年累计金额
    cumulativeQuarterAmount: number; // 本季度累计金额
    lastYearCumulativeQuarterAmount: number; // 上年同期季度累计金额
    cumulativeMonthAmount: number; // 本月累计金额
    lastYearCumulativeMonthAmount: number; // 上年同期月累计金额
    profitContribution: number; // 利润贡献
    feeIncome: number; // 手续费收入
    commissionIncome: number; // 佣金收入
    remark: string; // 备注
  }
}

/** 查询客户中间业务概览分页 */
export function getCustomerMiddleBusinessOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerMiddleBusinessOverviewApi.CustomerMiddleBusinessOverview>>(
    '/aicrm/customer-middle-business-overview/page',
    { params },
  );
}

/** 查询客户中间业务概览详情 */
export function getCustomerMiddleBusinessOverview(id: number) {
  return requestClient.get<AicrmCustomerMiddleBusinessOverviewApi.CustomerMiddleBusinessOverview>(
    `/aicrm/customer-middle-business-overview/get?id=${id}`,
  );
}

/** 新增客户中间业务概览 */
export function createCustomerMiddleBusinessOverview(data: AicrmCustomerMiddleBusinessOverviewApi.CustomerMiddleBusinessOverview) {
  return requestClient.post('/aicrm/customer-middle-business-overview/create', data);
}

/** 修改客户中间业务概览 */
export function updateCustomerMiddleBusinessOverview(data: AicrmCustomerMiddleBusinessOverviewApi.CustomerMiddleBusinessOverview) {
  return requestClient.put('/aicrm/customer-middle-business-overview/update', data);
}

/** 删除客户中间业务概览 */
export function deleteCustomerMiddleBusinessOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-middle-business-overview/delete?id=${id}`);
}

/** 批量删除客户中间业务概览 */
export function deleteCustomerMiddleBusinessOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-middle-business-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户中间业务概览 */
export function exportCustomerMiddleBusinessOverview(params: any) {
  return requestClient.download('/aicrm/customer-middle-business-overview/export-excel', { params });
}