/**
 * 任务管理 API
 */
import request from '@/utils/request'

/** 任务状态枚举 */
export enum TaskStatus {
  PENDING = 0,     // 待处理
  IN_PROGRESS = 1, // 进行中
  COMPLETED = 2,   // 已完成
  CANCELLED = 3,   // 已取消
}

/** 任务优先级枚举 */
export enum TaskPriority {
  P0 = 'P0', // 紧急
  P1 = 'P1', // 重要
  P2 = 'P2', // 普通
  P3 = 'P3', // 低优先级
}

/** 任务类型枚举 */
export enum TaskType {
  ASSET_UPGRADE = 'ASSET_UPGRADE',     // 资产提升
  EXPIRE_REMINDER = 'EXPIRE_REMINDER', // 到期提醒
  COMPLIANCE = 'COMPLIANCE',           // 合规
  MEETING = 'MEETING',                 // 会议
  ADMINISTRATIVE = 'ADMINISTRATIVE',   // 行政
  OTHER = 'OTHER',                     // 其他
}

/** 任务接口 */
export interface Task {
  id: number
  taskType: string
  title: string
  taskTitle?: string         // 任务标题（兼容字段）
  description: string
  taskDescription?: string   // 任务描述（兼容字段）
  priority: string
  comprehensiveScore: number  // 综合评分 (0-10)
  category?: string
  customerId?: number
  customerName?: string
  deadline?: number          // 截止时间（时间戳）
  completedTime?: number     // 完成时间（时间戳）
  businessValue?: string     // 业务价值等级 (high/medium/low)
  expectedRevenue?: number   // 预期收益（元）
  status: number            // 任务状态（0=待办 1=进行中 2=已完成 3=已取消）
  aiGenerated: number       // 是否AI生成（0=否 1=是）
  aiReason?: string         // AI生成原因
  aiSuggestion?: string     // AI建议
  triggerSource?: string    // 触发来源
  isUrgent?: boolean        // 是否紧急
  createTime: number        // 创建时间（时间戳）
  updateTime: number        // 更新时间（时间戳）
}

/** 任务分页查询参数 */
export interface TaskPageParams {
  pageNo?: number
  pageSize?: number
  status?: number           // 任务状态（0=待办 1=进行中 2=已完成 3=已取消）
  priority?: string         // 优先级（P0/P1/P2/P3）
  aiGenerated?: number      // 是否AI生成（0=否 1=是）
  customerName?: string     // 客户姓名（模糊匹配）
  minBusinessValue?: number // 最小业务价值评分
  responsibleUserId?: number // 任务负责人ID
}

/** 分页结果 */
export interface PageResult<T> {
  list: T[]
  total: number
}

/** 任务统计信息 */
export interface TaskStats {
  totalValue: string      // 今日潜在价值
  valueChange: string     // 价值变化百分比
  highPriorityCount: number // 高优先级任务数量
  completedCount: number  // 已完成任务数量
}

/**
 * 获取任务分页列表
 */
export function getTaskPage(params: TaskPageParams): Promise<PageResult<Task>> {
  return request({
    url: '/admin-api/task/task/page',
    method: 'get',
    params,
  })
}

/**
 * 获取任务详情
 */
export function getTask(id: number): Promise<Task> {
  return request({
    url: '/admin-api/task/task/get',
    method: 'get',
    params: { id },
  })
}

/**
 * 创建任务
 */
export function createTask(data: Partial<Task>): Promise<number> {
  return request({
    url: '/admin-api/task/task/create',
    method: 'post',
    data,
  })
}

/**
 * 更新任务
 */
export function updateTask(data: Partial<Task>): Promise<void> {
  return request({
    url: '/admin-api/task/task/update',
    method: 'put',
    data,
  })
}

/**
 * 删除任务
 */
export function deleteTask(id: number): Promise<void> {
  return request({
    url: '/admin-api/task/task/delete',
    method: 'delete',
    params: { id },
  })
}

/**
 * 完成任务
 */
export function completeTask(id: number): Promise<void> {
  return request({
    url: '/admin-api/task/task/complete',
    method: 'post',
    params: { id },
  })
}

/**
 * 获取任务统计信息
 */
export function getTaskStats(): Promise<TaskStats> {
  return request({
    url: '/admin-api/task/task/stats',
    method: 'get',
  })
}

/**
 * 根据客户ID获取任务列表
 */
export function getTasksByCustomerId(customerId: number): Promise<Task[]> {
  return request({
    url: '/admin-api/task/task/list-by-customer-id',
    method: 'get',
    params: { customerId },
  })
}
