import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace CustomerChatTransfersApi {
  /** 转接记录信息 */
  export interface ChatTransfers {
    id: number; // 主键
    userId?: number; // 客户 id
    fromSessionId?: number; // 原会话 id
    toSessionId?: number; // 新会话 id
    fromAdminId?: number; // 原 客服 id
    toAdminId?: number; // 新客服 id
    customerId?: number; // 租户 id
    remark: string; // 备注
    acceptedAt: string | Dayjs; // 接受时间
    canceledAt: string | Dayjs; // 取消时间
    createdAt?: string | Dayjs; // 创建时间
    updatedAt?: string | Dayjs; // 更新时间
  }
}

/** 查询转接记录分页 */
export function getChatTransfersPage(params: PageParam) {
  return requestClient.get<PageResult<CustomerChatTransfersApi.ChatTransfers>>(
    '/customer/chat-transfers/page',
    { params },
  );
}

/** 查询转接记录详情 */
export function getChatTransfers(id: number) {
  return requestClient.get<CustomerChatTransfersApi.ChatTransfers>(
    `/customer/chat-transfers/get?id=${id}`,
  );
}

/** 新增转接记录 */
export function createChatTransfers(data: CustomerChatTransfersApi.ChatTransfers) {
  return requestClient.post('/customer/chat-transfers/create', data);
}

/** 修改转接记录 */
export function updateChatTransfers(data: CustomerChatTransfersApi.ChatTransfers) {
  return requestClient.put('/customer/chat-transfers/update', data);
}

/** 删除转接记录 */
export function deleteChatTransfers(id: number) {
  return requestClient.delete(`/customer/chat-transfers/delete?id=${id}`);
}

/** 批量删除转接记录 */
export function deleteChatTransfersList(ids: number[]) {
  return requestClient.delete(
    `/customer/chat-transfers/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出转接记录 */
export function exportChatTransfers(params: any) {
  return requestClient.download('/customer/chat-transfers/export-excel', { params });
}