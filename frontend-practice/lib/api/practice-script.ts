import { httpClient } from "./request"

export interface PracticeScript {
  id: number
  scriptNo: string
  name: string
  content?: string // AI 生成内容
  contentEdit?: string // 手工编辑内容
  createTime?: string
}

/**
 * 获取练习脚本详情
 */
export async function getPracticeScriptById(id: number): Promise<PracticeScript> {
  return httpClient.get<PracticeScript>(`/aicrm/practice-script/get?id=${id}`)
}
