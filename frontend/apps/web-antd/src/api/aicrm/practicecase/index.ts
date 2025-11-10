import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeCaseApi {
  /** CRM智能陪练-销售案例信息 */
  export interface PracticeCase {
    id: number; // 案例ID
    title?: string; // 案例标题
    content?: string; // 案例详细内容（正文）
    tags: string; // 标签（多个标签逗号分隔）
  }
}

/** 查询CRM智能陪练-销售案例分页 */
export function getPracticeCasePage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeCaseApi.PracticeCase>>(
    '/aicrm/practice-case/page',
    { params },
  );
}

/** 查询CRM智能陪练-销售案例详情 */
export function getPracticeCase(id: number) {
  return requestClient.get<AicrmPracticeCaseApi.PracticeCase>(
    `/aicrm/practice-case/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-销售案例 */
export function createPracticeCase(data: AicrmPracticeCaseApi.PracticeCase) {
  return requestClient.post('/aicrm/practice-case/create', data);
}

/** 修改CRM智能陪练-销售案例 */
export function updatePracticeCase(data: AicrmPracticeCaseApi.PracticeCase) {
  return requestClient.put('/aicrm/practice-case/update', data);
}

/** 删除CRM智能陪练-销售案例 */
export function deletePracticeCase(id: number) {
  return requestClient.delete(`/aicrm/practice-case/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-销售案例 */
export function deletePracticeCaseList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-case/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-销售案例 */
export function exportPracticeCase(params: any) {
  return requestClient.download('/aicrm/practice-case/export-excel', { params });
}