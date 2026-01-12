import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridStatisticsApi {
  /** 网格统计报表信息 */
  export interface Statistics {
    id: number; // 统计ID
    gridId?: number; // 网格ID
    statDate?: string | Dayjs; // 统计日期
    statType?: string; // 统计类型: DAILY/WEEKLY/MONTHLY
    customerCount: number; // 客户总数
    newCustomerCount: number; // 新增客户数
    activeCustomerCount: number; // 活跃客户数
    activityCount: number; // 活动次数
    loanApplicationCount: number; // 贷款申请数
    loanApprovalCount: number; // 贷款批准数
    loanAmount: number; // 贷款金额
    depositAmount: number; // 存款金额
    performanceScore: number; // 绩效评分
  }
}

/** 查询网格统计报表分页 */
export function getStatisticsPage(params: PageParam) {
  return requestClient.get<PageResult<GridStatisticsApi.Statistics>>(
    '/grid/statistics/page',
    { params },
  );
}

/** 查询网格统计报表详情 */
export function getStatistics(id: number) {
  return requestClient.get<GridStatisticsApi.Statistics>(
    `/grid/statistics/get?id=${id}`,
  );
}

/** 新增网格统计报表 */
export function createStatistics(data: GridStatisticsApi.Statistics) {
  return requestClient.post('/grid/statistics/create', data);
}

/** 修改网格统计报表 */
export function updateStatistics(data: GridStatisticsApi.Statistics) {
  return requestClient.put('/grid/statistics/update', data);
}

/** 删除网格统计报表 */
export function deleteStatistics(id: number) {
  return requestClient.delete(`/grid/statistics/delete?id=${id}`);
}

/** 批量删除网格统计报表 */
export function deleteStatisticsList(ids: number[]) {
  return requestClient.delete(
    `/grid/statistics/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出网格统计报表 */
export function exportStatistics(params: any) {
  return requestClient.download('/grid/statistics/export-excel', { params });
}