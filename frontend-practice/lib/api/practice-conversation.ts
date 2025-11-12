import { httpClient } from "./request"

export interface PracticeConversation {
  id?: number
  recordId: number
  sequenceNo: number
  role: "student" | "virtual_customer" | "system"
  messageContent: string
  messageTime: string
}

/**
 * 消息接口 (用于前端显示)
 */
export interface Message {
  id: string
  sender: "user" | "ai"
  text: string
}

/**
 * 创建练习对话
 */
export async function createPracticeConversation(
  data: Omit<PracticeConversation, "id">,
): Promise<number> {
  return httpClient.post<number>("/aicrm/practice-conversation/create", data)
}

/**
 * 获取某个陪练记录的所有对话
 */
export async function getConversationListByRecordId(
  recordId: number,
): Promise<PracticeConversation[]> {
  return httpClient.get<PracticeConversation[]>(
    `/aicrm/practice-conversation/list-by-record?recordId=${recordId}`,
  )
}
