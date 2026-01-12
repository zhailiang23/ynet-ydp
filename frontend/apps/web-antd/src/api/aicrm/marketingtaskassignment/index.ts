import { requestClient } from '#/api/request';

export namespace AicrmMarketingTaskAssignmentApi {
  /** 营销活动任务下发 */
  export interface MarketingTaskAssignment {
    id?: number;
    taskName: string;
    marketingActivityId: number;
    marketingActivityName?: string;
    startTime: string;
    endTime: string;
    targetType: string;
    targetValue: number;
    taskScript?: string;
    posterUrl?: string;
    assignmentType: string; // 派发类型：customer=客户, cohort=客群
    assignedUserIds?: number[]; // 客户ID集合
    assignedUserCount?: number; // 派发人数（客户）
    assignedCohortIds?: string[]; // 客群ID集合（字符串格式，如 CH20260106112344986807842）
    assignedCohortCount?: number; // 派发数量（客群）
    status?: string;
    remark?: string;
    creator?: string;
    createTime?: string;
    updater?: string;
    updateTime?: string;
  }

  /** 营销活动任务下发分页请求 */
  export interface MarketingTaskAssignmentPageReqVO extends PageParam {
    taskName?: string;
    marketingActivityId?: number;
    startTime?: string[];
    endTime?: string[];
    targetType?: string;
    status?: string;
    createTime?: string[];
  }
}

/** 查询营销活动任务下发分页 */
export function getMarketingTaskAssignmentPage(
  params: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignmentPageReqVO,
) {
  return requestClient.get<PageResult<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>>(
    '/aicrm/marketing-task-assignment/page',
    { params },
  );
}

/** 查询营销活动任务下发详情 */
export function getMarketingTaskAssignment(id: number) {
  return requestClient.get<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>(
    '/aicrm/marketing-task-assignment/get',
    { params: { id } },
  );
}

/** 新增营销活动任务下发 */
export function createMarketingTaskAssignment(
  data: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment,
) {
  return requestClient.post<number>(
    '/aicrm/marketing-task-assignment/create',
    data,
  );
}

/** 修改营销活动任务下发 */
export function updateMarketingTaskAssignment(
  data: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment,
) {
  return requestClient.put<boolean>(
    '/aicrm/marketing-task-assignment/update',
    data,
  );
}

/** 删除营销活动任务下发 */
export function deleteMarketingTaskAssignment(id: number) {
  return requestClient.delete<boolean>(
    '/aicrm/marketing-task-assignment/delete',
    { params: { id } },
  );
}

/** 批量删除营销活动任务下发 */
export function deleteMarketingTaskAssignmentList(ids: number[]) {
  return requestClient.delete<boolean>(
    '/aicrm/marketing-task-assignment/delete-list',
    { params: { ids: ids.join(',') } },
  );
}

/** 导出营销活动任务下发 Excel */
export function exportMarketingTaskAssignment(
  params: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignmentPageReqVO,
) {
  return requestClient.get<Blob>(
    '/aicrm/marketing-task-assignment/export-excel',
    {
      params,
      responseType: 'blob',
    },
  );
}
