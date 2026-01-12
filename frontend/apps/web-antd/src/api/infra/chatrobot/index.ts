import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace InfraChatRobotApi {
  /** 对话机器人管理信息 */
  export interface ChatRobot {
    id?: number; // 机器人ID
    name?: string; // 机器人名称
    description: string; // 机器人描述
    platformType?: string; // 对接平台类型
    platformConfig: string; // 平台配置（JSON格式，存储webhook、appKey、appSecret等平台特定配置）
    agentId: number; // 绑定的智能体ID
    agentName?: string; // 绑定的智能体名称
    status?: number; // 是否启用（0=禁用，1=启用）
  }

  /** 对话信息 */
  export interface Conversation {
    id?: number; // 对话ID
    robotId?: number; // 机器人ID
    conversationId?: string; // 钉钉对话ID
    conversationType?: number; // 对话类型：1=单聊，2=群聊
    conversationTitle?: string; // 对话标题
    senderId?: string; // 发送者ID
    senderName?: string; // 发送者昵称
    lastMessageTime?: string; // 最后消息时间
    lastMessageContent?: string; // 最后消息内容
    totalMessageCount?: number; // 消息总数
    unreadCount?: number; // 未读消息数
    status?: number; // 对话状态：0=进行中，1=已关闭
  }

  /** 消息信息 */
  export interface Message {
    id?: number; // 消息ID
    robotId?: number; // 机器人ID
    conversationId?: string; // 钉钉对话ID
    messageId?: string; // 钉钉消息ID
    senderId?: string; // 发送者ID
    senderName?: string; // 发送者昵称
    content?: string; // 消息内容
    messageType?: string; // 消息类型：text, image, file, audio, video
    source?: number; // 消息来源：0=钉钉用户，1=管理员，2=系统
    isRead?: boolean; // 是否已读
    sendTime?: string; // 发送时间
  }

  /** 对话查询参数 */
  export interface ConversationPageReq extends PageParam {
    robotId?: number;
    status?: number;
  }

  /** 消息查询参数 */
  export interface MessagePageReq extends PageParam {
    conversationId?: string;
  }

  /** 发送消息请求 */
  export interface MessageSendReq {
    robotId: number;
    conversationId: string;
    content: string;
    messageType?: string;
  }
}

/** 查询对话机器人管理分页 */
export function getChatRobotPage(params: PageParam) {
  return requestClient.get<PageResult<InfraChatRobotApi.ChatRobot>>(
    '/infra/chat-robot/page',
    { params },
  );
}

/** 查询对话机器人管理详情 */
export function getChatRobot(id: number) {
  return requestClient.get<InfraChatRobotApi.ChatRobot>(
    `/infra/chat-robot/get?id=${id}`,
  );
}

/** 新增对话机器人管理 */
export function createChatRobot(data: InfraChatRobotApi.ChatRobot) {
  return requestClient.post('/infra/chat-robot/create', data);
}

/** 修改对话机器人管理 */
export function updateChatRobot(data: InfraChatRobotApi.ChatRobot) {
  return requestClient.put('/infra/chat-robot/update', data);
}

/** 删除对话机器人管理 */
export function deleteChatRobot(id: number) {
  return requestClient.delete(`/infra/chat-robot/delete?id=${id}`);
}

/** 批量删除对话机器人管理 */
export function deleteChatRobotList(ids: number[]) {
  return requestClient.delete(
    `/infra/chat-robot/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出对话机器人管理 */
export function exportChatRobot(params: any) {
  return requestClient.download('/infra/chat-robot/export-excel', { params });
}

// ==================== 对话管理 API ====================

/** 查询对话分页 */
export function getConversationPage(
  params: InfraChatRobotApi.ConversationPageReq,
) {
  return requestClient.get<PageResult<InfraChatRobotApi.Conversation>>(
    '/infra/chat-robot/conversation/page',
    { params },
  );
}

/** 查询对话详情 */
export function getConversation(id: number) {
  return requestClient.get<InfraChatRobotApi.Conversation>(
    `/infra/chat-robot/conversation/get?id=${id}`,
  );
}

/** 关闭对话 */
export function closeConversation(id: number) {
  return requestClient.put(`/infra/chat-robot/conversation/close?id=${id}`);
}

/** 获取未读消息总数 */
export function getUnreadCount(robotId: number) {
  return requestClient.get<number>(
    `/infra/chat-robot/conversation/unread-count?robotId=${robotId}`,
  );
}

// ==================== 消息管理 API ====================

/** 查询消息分页 */
export function getMessagePage(params: InfraChatRobotApi.MessagePageReq) {
  return requestClient.get<PageResult<InfraChatRobotApi.Message>>(
    '/infra/chat-robot/message/page',
    { params },
  );
}

/** 发送消息 */
export function sendMessage(data: InfraChatRobotApi.MessageSendReq) {
  return requestClient.post('/infra/chat-robot/message/send', data);
}

/** 标记消息已读 */
export function markMessageRead(conversationId: string) {
  return requestClient.put(
    '/infra/chat-robot/message/mark-read',
    { conversationId }, // 将 conversationId 放在 body 中
  );
}

/** 轮询新消息 */
export function pollingMessages(params: {
  conversationId: string;
  lastMessageId: number;
}) {
  return requestClient.get<PageResult<InfraChatRobotApi.Message>>(
    '/infra/chat-robot/message/polling',
    { params },
  );
}