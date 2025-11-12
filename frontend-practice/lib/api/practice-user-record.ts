import { httpClient } from "./request"

export interface PracticeUserRecord {
  id?: number
  courseId: number
  vcustomerId: number
  userId: number
  startTime: string
  status: string
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
