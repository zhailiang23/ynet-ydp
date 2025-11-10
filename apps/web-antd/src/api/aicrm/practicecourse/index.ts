import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeCourseApi {
  /** CRM智能陪练-陪练课程信息 */
  export interface PracticeCourse {
    id: number; // 课程ID
    name?: string; // 课程名称
    description: string; // 课程描述
    scriptId?: number; // 关联陪练剧本ID
    standard: number; // 课程类型 1.标准.0.个人
    hard: number; // 课程复杂度 1.复杂.0.简单
    status?: string; // 课程状态：字典 aicrm_course_status
  }
}

/** 查询CRM智能陪练-陪练课程分页 */
export function getPracticeCoursePage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeCourseApi.PracticeCourse>>(
    '/aicrm/practice-course/page',
    { params },
  );
}

/** 查询CRM智能陪练-陪练课程详情 */
export function getPracticeCourse(id: number) {
  return requestClient.get<AicrmPracticeCourseApi.PracticeCourse>(
    `/aicrm/practice-course/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-陪练课程 */
export function createPracticeCourse(data: AicrmPracticeCourseApi.PracticeCourse) {
  return requestClient.post('/aicrm/practice-course/create', data);
}

/** 修改CRM智能陪练-陪练课程 */
export function updatePracticeCourse(data: AicrmPracticeCourseApi.PracticeCourse) {
  return requestClient.put('/aicrm/practice-course/update', data);
}

/** 删除CRM智能陪练-陪练课程 */
export function deletePracticeCourse(id: number) {
  return requestClient.delete(`/aicrm/practice-course/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-陪练课程 */
export function deletePracticeCourseList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-course/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-陪练课程 */
export function exportPracticeCourse(params: any) {
  return requestClient.download('/aicrm/practice-course/export-excel', { params });
}