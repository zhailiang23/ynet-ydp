import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace KnowledgeBaseApi {
  /** 知识库信息 */
  export interface Base {
    id: number; // 知识库ID
    name?: string; // 知识库名称
    description: string; // 知识库描述
    status?: number; // 状态（0正常 1停用）
  }
}

/** 查询知识库分页 */
export function getBasePage(params: PageParam) {
  return requestClient.get<PageResult<KnowledgeBaseApi.Base>>(
    '/knowledge/base/page',
    { params },
  );
}

/** 查询知识库详情 */
export function getBase(id: number) {
  return requestClient.get<KnowledgeBaseApi.Base>(
    `/knowledge/base/get?id=${id}`,
  );
}

/** 新增知识库 */
export function createBase(data: KnowledgeBaseApi.Base) {
  return requestClient.post('/knowledge/base/create', data);
}

/** 修改知识库 */
export function updateBase(data: KnowledgeBaseApi.Base) {
  return requestClient.put('/knowledge/base/update', data);
}

/** 删除知识库 */
export function deleteBase(id: number) {
  return requestClient.delete(`/knowledge/base/delete?id=${id}`);
}

/** 批量删除知识库 */
export function deleteBaseList(ids: number[]) {
  return requestClient.delete(
    `/knowledge/base/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出知识库 */
export function exportBase(params: any) {
  return requestClient.download('/knowledge/base/export-excel', { params });
}

/** 获取或创建个人知识库 */
export function getOrCreatePersonalBase() {
  return requestClient.get<KnowledgeBaseApi.Base>(
    '/knowledge/base/get-or-create-personal',
  );
}