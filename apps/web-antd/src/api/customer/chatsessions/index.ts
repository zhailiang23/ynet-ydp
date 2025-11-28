import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace CustomerChatSessionsApi {
  /** 会话信息 */
  export interface ChatSessions {
    id: number; // 主键
    userId: number; // 用户 id
    queriedAt?: string | Dayjs; // 会话发起时间
    acceptedAt: string | Dayjs; // 客服接入时间
    canceledAt: string | Dayjs; // 会话取消时间
    brokenAt: string | Dayjs; // 中断时间
    adminId?: number; // 客服 id
    type: number; // 会话类型
    rate: number; // 评分
  }

  /** 聊天消息 */
  export interface ChatMessage {
    id: number; // 主键
    userId: number; // 用户 id
    adminId: number; // 客服 id
    type: string; // 消息类型: text, image, video, audio, pdf, navigator, notice
    content: string; // 消息内容
    receivedAt: string; // 接收时间
    source: number; // 消息来源: 0=用户, 1=客服, 2=系统
    sessionId: number; // 会话 id
  }

  /** 会话详情 */
  export interface ChatSessionDetail {
    session: ChatSessions; // 会话信息
    messages: ChatMessage[]; // 消息列表
    total: number; // 消息总数
  }
}

/** 查询会话信息分页 */
export function getChatSessionsPage(params: PageParam) {
  return requestClient.get<PageResult<CustomerChatSessionsApi.ChatSessions>>(
    '/customer/chat-sessions/page',
    { params },
  );
}

/** 查询会话信息详情 */
export function getChatSessions(id: number) {
  return requestClient.get<CustomerChatSessionsApi.ChatSessions>(
    `/customer/chat-sessions/get?id=${id}`,
  );
}

/** 查询会话详情（包含消息列表） */
export function getChatSessionDetail(id: number) {
  return requestClient.get<CustomerChatSessionsApi.ChatSessionDetail>(
    `/customer/chat-sessions/detail?id=${id}`,
  );
}

/** 新增会话信息 */
export function createChatSessions(data: CustomerChatSessionsApi.ChatSessions) {
  return requestClient.post('/customer/chat-sessions/create', data);
}

/** 修改会话信息 */
export function updateChatSessions(data: CustomerChatSessionsApi.ChatSessions) {
  return requestClient.put('/customer/chat-sessions/update', data);
}

/** 删除会话信息 */
export function deleteChatSessions(id: number) {
  return requestClient.delete(`/customer/chat-sessions/delete?id=${id}`);
}

/** 批量删除会话信息 */
export function deleteChatSessionsList(ids: number[]) {
  return requestClient.delete(
    `/customer/chat-sessions/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出会话信息 */
export function exportChatSessions(params: any) {
  return requestClient.download('/customer/chat-sessions/export-excel', { params });
}