import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridActivityLogApi {
  /** 网格活动记录信息 */
  export interface ActivityLog {
    id: number; // 活动记录ID
    gridId?: number; // 网格ID
    customerId: number; // 客户ID (可选)
    activityType?: string; // 活动类型: VISIT/MARKETING/SURVEY/OTHER
    activityTitle?: string; // 活动标题
    activityContent: string; // 活动内容
    activityDate?: string | Dayjs; // 活动日期
    location: byte[]; // 活动地点坐标
    address: string; // 活动地点地址
    staffId?: number; // 执行人ID (关联 system_users)
    result: string; // 活动结果: SUCCESS/FAILED/PENDING
    attachments: string; // 附件列表 [{url, name, type}]
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询网格活动记录分页 */
export function getActivityLogPage(params: PageParam) {
  return requestClient.get<PageResult<GridActivityLogApi.ActivityLog>>(
    '/grid/activity-log/page',
    { params },
  );
}

/** 查询网格活动记录详情 */
export function getActivityLog(id: number) {
  return requestClient.get<GridActivityLogApi.ActivityLog>(
    `/grid/activity-log/get?id=${id}`,
  );
}

/** 新增网格活动记录 */
export function createActivityLog(data: GridActivityLogApi.ActivityLog) {
  return requestClient.post('/grid/activity-log/create', data);
}

/** 修改网格活动记录 */
export function updateActivityLog(data: GridActivityLogApi.ActivityLog) {
  return requestClient.put('/grid/activity-log/update', data);
}

/** 删除网格活动记录 */
export function deleteActivityLog(id: number) {
  return requestClient.delete(`/grid/activity-log/delete?id=${id}`);
}

/** 批量删除网格活动记录 */
export function deleteActivityLogList(ids: number[]) {
  return requestClient.delete(
    `/grid/activity-log/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出网格活动记录 */
export function exportActivityLog(params: any) {
  return requestClient.download('/grid/activity-log/export-excel', { params });
}