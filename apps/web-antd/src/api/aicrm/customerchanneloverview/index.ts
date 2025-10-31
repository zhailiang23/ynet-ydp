import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerChannelOverviewApi {
  /** 客户渠道业务概览信息 */
  export interface CustomerChannelOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    channelType: string; // 渠道类型
    channelCode: string; // 渠道代码
    channelName: string; // 渠道名称
    accessCount: number; // 访问次数
    loginCount: number; // 登录次数
    transactionCount: number; // 交易笔数
    activeDays: number; // 活跃天数
    transactionAmount: number; // 交易金额
    amountYearAvg: number; // 本年交易金额日均
    lastYearAmountYearAvg: number; // 上年交易金额日均
    amountQuarterAvg: number; // 本季度交易金额日均
    lastYearAmountQuarterAvg: number; // 上年同期季度交易金额日均
    amountMonthAvg: number; // 本月交易金额日均
    lastYearAmountMonthAvg: number; // 上年同期月交易金额日均
    cumulativeYearAmount: number; // 本年累计交易金额
    lastYearCumulativeAmount: number; // 上年累计交易金额
    cumulativeQuarterAmount: number; // 本季度累计交易金额
    lastYearCumulativeQuarterAmount: number; // 上年同期季度累计交易金额
    cumulativeMonthAmount: number; // 本月累计交易金额
    lastYearCumulativeMonthAmount: number; // 上年同期月累计交易金额
    firstAccessTime: string | Dayjs; // 首次访问时间
    lastAccessTime: string | Dayjs; // 最近访问时间
    lastTransactionTime: string | Dayjs; // 最近交易时间
    deviceType: string; // 设备类型
    deviceModel: string; // 设备型号
    osVersion: string; // 操作系统版本
    appVersion: string; // 应用版本
    preferredBusiness: string; // 偏好业务
    usageFrequency: string; // 使用频率
    remark: string; // 备注
  }
}

/** 查询客户渠道业务概览分页 */
export function getCustomerChannelOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerChannelOverviewApi.CustomerChannelOverview>>(
    '/aicrm/customer-channel-overview/page',
    { params },
  );
}

/** 查询客户渠道业务概览详情 */
export function getCustomerChannelOverview(id: number) {
  return requestClient.get<AicrmCustomerChannelOverviewApi.CustomerChannelOverview>(
    `/aicrm/customer-channel-overview/get?id=${id}`,
  );
}

/** 新增客户渠道业务概览 */
export function createCustomerChannelOverview(data: AicrmCustomerChannelOverviewApi.CustomerChannelOverview) {
  return requestClient.post('/aicrm/customer-channel-overview/create', data);
}

/** 修改客户渠道业务概览 */
export function updateCustomerChannelOverview(data: AicrmCustomerChannelOverviewApi.CustomerChannelOverview) {
  return requestClient.put('/aicrm/customer-channel-overview/update', data);
}

/** 删除客户渠道业务概览 */
export function deleteCustomerChannelOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-channel-overview/delete?id=${id}`);
}

/** 批量删除客户渠道业务概览 */
export function deleteCustomerChannelOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-channel-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户渠道业务概览 */
export function exportCustomerChannelOverview(params: any) {
  return requestClient.download('/aicrm/customer-channel-overview/export-excel', { params });
}