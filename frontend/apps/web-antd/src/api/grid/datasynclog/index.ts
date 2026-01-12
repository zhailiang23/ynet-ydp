import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridDataSyncLogApi {
  /** 数据同步记录信息 */
  export interface DataSyncLog {
    id: number; // 同步记录ID
    syncType?: string; // 同步类型: ESB_LINGXI/DW_BATCH
    syncDirection?: string; // 同步方向: PULL/PUSH
    entityType?: string; // 实体类型: GRID/CUSTOMER/STATION/ACTIVITY
    entityId: number; // 实体ID
    syncStatus?: string; // 同步状态: SUCCESS/FAILED/PENDING
    syncTime?: string | Dayjs; // 同步时间
    requestData: string; // 请求数据
    responseData: string; // 响应数据
    errorMessage: string; // 错误信息
    retryCount: number; // 重试次数
  }
}

/** 查询数据同步记录分页 */
export function getDataSyncLogPage(params: PageParam) {
  return requestClient.get<PageResult<GridDataSyncLogApi.DataSyncLog>>(
    '/grid/data-sync-log/page',
    { params },
  );
}

/** 查询数据同步记录详情 */
export function getDataSyncLog(id: number) {
  return requestClient.get<GridDataSyncLogApi.DataSyncLog>(
    `/grid/data-sync-log/get?id=${id}`,
  );
}

/** 新增数据同步记录 */
export function createDataSyncLog(data: GridDataSyncLogApi.DataSyncLog) {
  return requestClient.post('/grid/data-sync-log/create', data);
}

/** 修改数据同步记录 */
export function updateDataSyncLog(data: GridDataSyncLogApi.DataSyncLog) {
  return requestClient.put('/grid/data-sync-log/update', data);
}

/** 删除数据同步记录 */
export function deleteDataSyncLog(id: number) {
  return requestClient.delete(`/grid/data-sync-log/delete?id=${id}`);
}

/** 批量删除数据同步记录 */
export function deleteDataSyncLogList(ids: number[]) {
  return requestClient.delete(
    `/grid/data-sync-log/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出数据同步记录 */
export function exportDataSyncLog(params: any) {
  return requestClient.download('/grid/data-sync-log/export-excel', { params });
}