import { httpClient } from "./request"

export interface PracticeSkill {
  id: number
  name: string
  description?: string
  marketingStep?: string
  difficultyLevel?: string
  category?: string
  tags?: string
  content?: string
  examples?: string
  status?: string
  createTime?: string
  updateTime?: string
}

export interface PracticeSkillPageReq {
  pageNo: number
  pageSize: number
  name?: string
  marketingStep?: string
  difficultyLevel?: string
  category?: string
  status?: string
}

export interface PracticeSkillPageResp {
  total: number
  list: PracticeSkill[]
}

/**
 * 获取销售技巧分页列表
 */
export async function getPracticeSkillPage(params: PracticeSkillPageReq): Promise<PracticeSkillPageResp> {
  return httpClient.get<PracticeSkillPageResp>("/aicrm/practice-skill/page", params)
}

/**
 * 获取所有销售技巧（用于下拉选择）
 */
export async function getPracticeSkillsForSelect(): Promise<PracticeSkill[]> {
  const result = await getPracticeSkillPage({
    pageNo: 1,
    pageSize: 100,
    status: 'published' // 只获取已发布的技巧
  })
  return result.list || []
}