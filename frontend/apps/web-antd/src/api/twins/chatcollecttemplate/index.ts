import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace TwinsChatCollectTemplateApi {
  /** 客户留资模板信息 */
  export interface ChatCollectTemplate {
    id: number; // 主键ID
    name?: string; // 模板名称
    description: string; // 模板描述
    url?: string; // 模板链接
    status?: number; // 状态：0-禁用，1-启用
    sort?: number; // 排序
  }
}

/** 查询客户留资模板分页 */
export function getChatCollectTemplatePage(params: PageParam) {
  return requestClient.get<PageResult<TwinsChatCollectTemplateApi.ChatCollectTemplate>>(
    '/twins/chat-collect-template/page',
    { params },
  );
}

/** 查询客户留资模板详情 */
export function getChatCollectTemplate(id: number) {
  return requestClient.get<TwinsChatCollectTemplateApi.ChatCollectTemplate>(
    `/twins/chat-collect-template/get?id=${id}`,
  );
}

/** 新增客户留资模板 */
export function createChatCollectTemplate(data: TwinsChatCollectTemplateApi.ChatCollectTemplate) {
  return requestClient.post('/twins/chat-collect-template/create', data);
}

/** 修改客户留资模板 */
export function updateChatCollectTemplate(data: TwinsChatCollectTemplateApi.ChatCollectTemplate) {
  return requestClient.put('/twins/chat-collect-template/update', data);
}

/** 删除客户留资模板 */
export function deleteChatCollectTemplate(id: number) {
  return requestClient.delete(`/twins/chat-collect-template/delete?id=${id}`);
}

/** 批量删除客户留资模板 */
export function deleteChatCollectTemplateList(ids: number[]) {
  return requestClient.delete(
    `/twins/chat-collect-template/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户留资模板 */
export function exportChatCollectTemplate(params: any) {
  return requestClient.download('/twins/chat-collect-template/export-excel', { params });
}