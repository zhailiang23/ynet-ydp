import { requestClient } from '#/api/request';

export namespace AicrmTaskApi {
  /** 任务 */
  export interface Task {
    id?: number;
    taskType: string;
    title: string;
    description?: string;
    priority: string;
    comprehensiveScore?: number;
    category?: string;
    customerId?: number;
    customerName?: string;
    responsibleUserId?: number;
    responsibleUserName?: string;
    deadline?: string;
    completedTime?: string;
    businessValue?: string;
    expectedRevenue?: number;
    status: number;
    aiGenerated?: number;
    aiReason?: string;
    aiSuggestion?: string;
    createTime?: string;
  }

  /** 任务分页参数 */
  export interface TaskPageParam {
    pageNo: number;
    pageSize: number;
    title?: string;
    taskType?: string;
    priority?: string;
    status?: number;
    category?: string;
    businessValue?: string;
    customerId?: number;
    deadlineStart?: string;
    deadlineEnd?: string;
    createTime?: [string, string];
  }
}

/** 获取任务分页 */
export async function getTaskPage(params: AicrmTaskApi.TaskPageParam) {
  return requestClient.get<PageRes<AicrmTaskApi.Task>>('/task/task/page', {
    params,
  });
}

/** 获取任务详情 */
export async function getTask(id: number) {
  return requestClient.get<AicrmTaskApi.Task>('/task/task/get', {
    params: { id },
  });
}

/** 创建任务 */
export async function createTask(data: AicrmTaskApi.Task) {
  return requestClient.post<number>('/task/task/create', data);
}

/** 更新任务 */
export async function updateTask(data: AicrmTaskApi.Task) {
  return requestClient.put<boolean>('/task/task/update', data);
}

/** 删除任务 */
export async function deleteTask(id: number) {
  return requestClient.delete<boolean>('/task/task/delete', {
    params: { id },
  });
}

/** 批量删除任务 */
export async function deleteTaskList(ids: number[]) {
  return requestClient.delete<boolean>('/task/task/delete-list', {
    params: { ids: ids.join(',') },
  });
}
