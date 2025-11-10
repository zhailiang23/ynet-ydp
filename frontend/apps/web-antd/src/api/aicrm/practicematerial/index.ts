import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeMaterialApi {
  /** CRM智能陪练-培训文件信息 */
  export interface PracticeMaterial {
    id: number; // 文件ID
    name?: string; // 文件名称
    fileUrl?: string; // 文件URL
    fileSize: number; // 文件大小（字节）
    content: string; // 文件内容(纯文本)
    contentRich: string; // 文件内容(富文本)
  }
}

/** 查询CRM智能陪练-培训文件分页 */
export function getPracticeMaterialPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeMaterialApi.PracticeMaterial>>(
    '/aicrm/practice-material/page',
    { params },
  );
}

/** 查询CRM智能陪练-培训文件详情 */
export function getPracticeMaterial(id: number) {
  return requestClient.get<AicrmPracticeMaterialApi.PracticeMaterial>(
    `/aicrm/practice-material/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-培训文件 */
export function createPracticeMaterial(data: AicrmPracticeMaterialApi.PracticeMaterial) {
  return requestClient.post('/aicrm/practice-material/create', data);
}

/** 修改CRM智能陪练-培训文件 */
export function updatePracticeMaterial(data: AicrmPracticeMaterialApi.PracticeMaterial) {
  return requestClient.put('/aicrm/practice-material/update', data);
}

/** 删除CRM智能陪练-培训文件 */
export function deletePracticeMaterial(id: number) {
  return requestClient.delete(`/aicrm/practice-material/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-培训文件 */
export function deletePracticeMaterialList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-material/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-培训文件 */
export function exportPracticeMaterial(params: any) {
  return requestClient.download('/aicrm/practice-material/export-excel', { params });
}