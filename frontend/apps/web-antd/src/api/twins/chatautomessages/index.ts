import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace TwinsChatAutoMessagesApi {
  /** 客户留资消息信息 */
  export interface ChatAutoMessages {
    id: number; // 主键
    name?: string; // 名称
    type?: string; // 类型
    content?: string; // 内容
  }
}

/** 查询客户留资消息分页 */
export function getChatAutoMessagesPage(params: PageParam) {
  return requestClient.get<PageResult<TwinsChatAutoMessagesApi.ChatAutoMessages>>(
    '/twins/chat-auto-messages/page',
    { params },
  );
}

/** 查询客户留资消息详情 */
export function getChatAutoMessages(id: number) {
  return requestClient.get<TwinsChatAutoMessagesApi.ChatAutoMessages>(
    `/twins/chat-auto-messages/get?id=${id}`,
  );
}

/** 新增客户留资消息 */
export function createChatAutoMessages(data: TwinsChatAutoMessagesApi.ChatAutoMessages) {
  return requestClient.post('/twins/chat-auto-messages/create', data);
}

/** 修改客户留资消息 */
export function updateChatAutoMessages(data: TwinsChatAutoMessagesApi.ChatAutoMessages) {
  return requestClient.put('/twins/chat-auto-messages/update', data);
}

/** 删除客户留资消息 */
export function deleteChatAutoMessages(id: number) {
  return requestClient.delete(`/twins/chat-auto-messages/delete?id=${id}`);
}

/** 批量删除客户留资消息 */
export function deleteChatAutoMessagesList(ids: number[]) {
  return requestClient.delete(
    `/twins/chat-auto-messages/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户留资消息 */
export function exportChatAutoMessages(params: any) {
  return requestClient.download('/twins/chat-auto-messages/export-excel', { params });
}