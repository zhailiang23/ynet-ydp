import { httpClient } from "./request"

/**
 * 培训文件类型
 */
export interface PracticeMaterial {
  id: number
  name: string
  fileType: string
  fileUrl: string
  fileSize?: number
  content?: string
  contentRich?: string
  createTime?: string
  courseName?: string
  trainingCount?: number
}

/**
 * 分页参数
 */
export interface PageParam {
  pageNo: number
  pageSize: number
}

/**
 * 分页结果
 */
export interface PageResult<T> {
  list: T[]
  total: number
}

/**
 * 获取培训文件分页列表
 */
export async function getPracticeMaterialList(params: PageParam): Promise<PageResult<PracticeMaterial>> {
  const queryString = new URLSearchParams({
    pageNo: params.pageNo.toString(),
    pageSize: params.pageSize.toString(),
  }).toString()

  return httpClient.get<PageResult<PracticeMaterial>>(`/aicrm/practice-material/page?${queryString}`)
}

/**
 * 获取培训文件详情
 */
export async function getPracticeMaterialById(id: number): Promise<PracticeMaterial> {
  return httpClient.get<PracticeMaterial>(`/aicrm/practice-material/get?id=${id}`)
}
