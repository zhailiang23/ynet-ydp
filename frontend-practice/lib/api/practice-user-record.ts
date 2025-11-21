import { httpClient } from "./request"

export interface PracticeUserRecord {
  id?: number
  courseId: number
  courseName?: string
  courseType?: number // 课程类型: 1标准课程 0个性化课程
  vcustomerId: number
  vcustomerName?: string
  userId: number
  userName?: string
  recordNo?: string
  startTime: string
  endTime?: string
  status: string
  duration?: number
  totalScore?: number
  dimensionScores?: string
  completionRate?: number
  aiSummary?: string
  strengths?: string
  weaknesses?: string
  recommendations?: string
  createTime?: string
}

export interface PracticeUserRecordPageReq {
  pageNo?: number
  pageSize?: number
  courseId?: number
  userId?: number
  vcustomerId?: number
  recordNo?: string
  status?: string
  startTime?: string[]
  endTime?: string[]
  duration?: number
  totalScore?: number
  completionRate?: number
  createTime?: string[]
}

export interface PageResult<T> {
  list: T[]
  total: number
}

/**
 * 创建练习用户记录
 */
export async function createPracticeUserRecord(
  data: Omit<PracticeUserRecord, "id">,
): Promise<number> {
  return httpClient.post<number>("/aicrm/practice-user-record/create", data)
}

/**
 * 获取练习用户记录详情
 */
export async function getPracticeUserRecordById(id: number): Promise<PracticeUserRecord> {
  return httpClient.get<PracticeUserRecord>(`/aicrm/practice-user-record/get?id=${id}`)
}

/**
 * 查询用户未完成的练习记录
 * @param courseId 课程ID
 * @param vcustomerId 虚拟客户ID
 * @param userId 用户ID
 */
export async function findUnfinishedRecord(
  courseId: number,
  vcustomerId: number,
  userId: number,
): Promise<PracticeUserRecord | null> {
  try {
    const response = await httpClient.get<PracticeUserRecord>(
      `/aicrm/practice-user-record/find-unfinished?courseId=${courseId}&vcustomerId=${vcustomerId}&userId=${userId}`,
    )
    return response
  } catch (error) {
    // 如果没有找到记录,返回 null
    return null
  }
}

/**
 * 更新练习用户记录 (完成练习)
 * @param id 记录ID
 * @param courseId 课程ID
 * @param vcustomerId 虚拟客户ID
 * @param userId 用户ID
 */
export async function completePracticeUserRecord(
  id: number,
  courseId: number,
  vcustomerId: number,
  userId: number,
): Promise<void> {
  const endTime = new Date().getTime() // 使用时间戳(毫秒)而不是 ISO 字符串
  await httpClient.put<void>("/aicrm/practice-user-record/update", {
    id,
    courseId,
    vcustomerId,
    userId,
    status: "completed",
    endTime,
  })
}

/**
 * 分页查询练习用户记录
 * @param params 查询参数
 */
export async function getPracticeUserRecordPage(
  params: PracticeUserRecordPageReq,
): Promise<PageResult<PracticeUserRecord>> {
  // 构建查询参数
  const queryParams = new URLSearchParams()
  queryParams.set("pageNo", String(params.pageNo || 1))
  queryParams.set("pageSize", String(params.pageSize || 10))

  if (params.courseId) queryParams.set("courseId", String(params.courseId))
  if (params.userId) queryParams.set("userId", String(params.userId))
  if (params.vcustomerId) queryParams.set("vcustomerId", String(params.vcustomerId))
  if (params.recordNo) queryParams.set("recordNo", params.recordNo)
  if (params.status) queryParams.set("status", params.status)
  if (params.duration) queryParams.set("duration", String(params.duration))
  if (params.totalScore) queryParams.set("totalScore", String(params.totalScore))
  if (params.completionRate) queryParams.set("completionRate", String(params.completionRate))

  const url = `/aicrm/practice-user-record/page?${queryParams.toString()}`
  return httpClient.get<PageResult<PracticeUserRecord>>(url)
}
