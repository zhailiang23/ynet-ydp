/**
 * 任务行动 API
 */
import request from '@/utils/request'

export interface TaskAction {
  id?: number
  taskId: number
  actionType: string // CALL-打电话、SMS-发短信、EMAIL-发邮件、VISIT-拜访、MEETING-会议、OTHER-其他
  actionTime: string
  actionUserId: number
  actionUserName?: string
  remark?: string
}

export interface TaskActionCreateRequest {
  taskId: number
  actionType: string
  actionTime: string
  actionUserId: number
  actionUserName?: string
  remark?: string
}

/**
 * 创建任务行动
 */
export const createTaskAction = async (data: TaskActionCreateRequest): Promise<number> => {
  const response = await request.post('/admin-api/aicrm/task-action/create', data)
  return response.data
}

/**
 * 获取任务的行动列表
 */
export const getTaskActionsByTaskId = async (taskId: number): Promise<TaskAction[]> => {
  const response = await request.get(`/admin-api/aicrm/task-action/list-by-task-id`, {
    params: { taskId },
  })
  return response.data
}
