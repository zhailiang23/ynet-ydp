import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerOffbalanceOverviewApi {
  /** 客户表外业务概览信息 */
  export interface CustomerOffbalanceOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    businessType: string; // 业务类型
    businessCode: string; // 业务代码
    businessName: string; // 业务名称
    currency: string; // 币种
    businessAmount: number; // 业务金额
    businessBalance: number; // 业务余额
    guaranteeAmount: number; // 担保金额
    creditAmount: number; // 授信金额
    usedAmount: number; // 已用金额
    availableAmount: number; // 可用金额
    businessCount: number; // 业务笔数
    balanceYearAvg: number; // 本年余额日均
    lastYearBalanceYearAvg: number; // 上年余额日均
    balanceQuarterAvg: number; // 本季度余额日均
    lastYearBalanceQuarterAvg: number; // 上年同期季度余额日均
    balanceMonthAvg: number; // 本月余额日均
    lastYearBalanceMonthAvg: number; // 上年同期月余额日均
    cumulativeYearAmount: number; // 本年累计金额
    lastYearCumulativeAmount: number; // 上年累计金额
    cumulativeQuarterAmount: number; // 本季度累计金额
    lastYearCumulativeQuarterAmount: number; // 上年同期季度累计金额
    cumulativeMonthAmount: number; // 本月累计金额
    lastYearCumulativeMonthAmount: number; // 上年同期月累计金额
    usageRate: number; // 使用率
    startDate: string | Dayjs; // 起始日期
    endDate: string | Dayjs; // 到期日期
    effectiveDate: string | Dayjs; // 生效日期
    feeRate: number; // 费率
    feeAmount: number; // 手续费金额
    businessStatus: string; // 业务状态
    riskLevel: string; // 风险等级
    profitContribution: number; // 利润贡献
    feeIncome: number; // 手续费收入
    remark: string; // 备注
  }
}

/** 查询客户表外业务概览分页 */
export function getCustomerOffbalanceOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerOffbalanceOverviewApi.CustomerOffbalanceOverview>>(
    '/aicrm/customer-offbalance-overview/page',
    { params },
  );
}

/** 查询客户表外业务概览详情 */
export function getCustomerOffbalanceOverview(id: number) {
  return requestClient.get<AicrmCustomerOffbalanceOverviewApi.CustomerOffbalanceOverview>(
    `/aicrm/customer-offbalance-overview/get?id=${id}`,
  );
}

/** 新增客户表外业务概览 */
export function createCustomerOffbalanceOverview(data: AicrmCustomerOffbalanceOverviewApi.CustomerOffbalanceOverview) {
  return requestClient.post('/aicrm/customer-offbalance-overview/create', data);
}

/** 修改客户表外业务概览 */
export function updateCustomerOffbalanceOverview(data: AicrmCustomerOffbalanceOverviewApi.CustomerOffbalanceOverview) {
  return requestClient.put('/aicrm/customer-offbalance-overview/update', data);
}

/** 删除客户表外业务概览 */
export function deleteCustomerOffbalanceOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-offbalance-overview/delete?id=${id}`);
}

/** 批量删除客户表外业务概览 */
export function deleteCustomerOffbalanceOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-offbalance-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户表外业务概览 */
export function exportCustomerOffbalanceOverview(params: any) {
  return requestClient.download('/aicrm/customer-offbalance-overview/export-excel', { params });
}