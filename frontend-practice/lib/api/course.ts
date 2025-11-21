import { httpClient } from "./request"
import type { Course, PageParam, PageResult } from "../types/course"

/**
 * 获取课程分页列表
 */
export async function getCourseList(params: PageParam): Promise<PageResult<Course>> {
  const queryString = new URLSearchParams({
    pageNo: params.pageNo.toString(),
    pageSize: params.pageSize.toString(),
  }).toString()

  return httpClient.get<PageResult<Course>>(`/aicrm/practice-course/page?${queryString}`)
}

/**
 * 获取课程详情
 */
export async function getCourseById(id: number): Promise<Course> {
  return httpClient.get<Course>(`/aicrm/practice-course/get?id=${id}`)
}

/**
 * 获取标准课程列表
 */
export async function getStandardCourses(): Promise<Course[]> {
  const result = await httpClient.get<PageResult<Course>>(
    `/aicrm/practice-course/page?pageNo=1&pageSize=100&standard=1`
  )
  return result.list || []
}

/**
 * 获取个性化课程列表
 */
export async function getPersonalizedCourses(): Promise<Course[]> {
  const result = await httpClient.get<PageResult<Course>>(
    `/aicrm/practice-course/page?pageNo=1&pageSize=100&standard=0`
  )
  return result.list || []
}
