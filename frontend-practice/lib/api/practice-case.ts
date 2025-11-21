import { httpClient } from "./request"

export interface PracticeCase {
  id: number
  title: string
  description?: string
  marketingStep?: string
  difficultyLevel?: string
  tags?: string
  content?: string
  status?: string
  createTime?: string
  updateTime?: string
}

export interface PracticeCasePageReq {
  pageNo: number
  pageSize: number
  title?: string
  marketingStep?: string
  difficultyLevel?: string
  status?: string
}

export interface PracticeCasePageResp {
  total: number
  list: PracticeCase[]
}

/**
 * 获取销售案例分页列表
 */
export async function getPracticeCasePage(params: PracticeCasePageReq): Promise<PracticeCasePageResp> {
  return httpClient.get<PracticeCasePageResp>("/aicrm/practice-case/page", params)
}

/**
 * 获取所有销售案例（用于下拉选择）
 */
export async function getPracticeCasesForSelect(): Promise<PracticeCase[]> {
  const result = await getPracticeCasePage({
    pageNo: 1,
    pageSize: 100,
    status: 'published' // 只获取已发布的案例
  })
  return result.list || []
}