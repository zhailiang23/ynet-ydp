import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeConversationApi {
  /** CRM智能陪练-陪练对话信息 */
  export interface PracticeConversation {
    id: number; // 对话ID
    recordId?: number; // 关联用户陪练记录ID
    sequenceNo?: number; // 对话序号（从1开始）
    role?: string; // 发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）
    messageContent?: string; // 消息内容
    messageTime?: string | Dayjs; // 消息时间
    instantScore: number; // 即时评分（针对学员发言）
    speechAnalysis: string; // 话术分析（JSON格式）
    skillEvaluation: string; // 技巧运用评价（JSON格式）
    emotionTag: string; // 对话情绪标签：字典 aicrm_emotion_tag
    salesIntent: string; // 销售意图识别：字典 aicrm_sales_intent
    customerReaction: string; // 客户反应：字典 aicrm_customer_reaction
    improvementSuggestions: string; // AI改进建议（JSON格式）
    recommendedScripts: string; // 推荐话术（JSON格式）
  }
}

/** 查询CRM智能陪练-陪练对话分页 */
export function getPracticeConversationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeConversationApi.PracticeConversation>>(
    '/aicrm/practice-conversation/page',
    { params },
  );
}

/** 查询CRM智能陪练-陪练对话详情 */
export function getPracticeConversation(id: number) {
  return requestClient.get<AicrmPracticeConversationApi.PracticeConversation>(
    `/aicrm/practice-conversation/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-陪练对话 */
export function createPracticeConversation(data: AicrmPracticeConversationApi.PracticeConversation) {
  return requestClient.post('/aicrm/practice-conversation/create', data);
}

/** 修改CRM智能陪练-陪练对话 */
export function updatePracticeConversation(data: AicrmPracticeConversationApi.PracticeConversation) {
  return requestClient.put('/aicrm/practice-conversation/update', data);
}

/** 删除CRM智能陪练-陪练对话 */
export function deletePracticeConversation(id: number) {
  return requestClient.delete(`/aicrm/practice-conversation/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-陪练对话 */
export function deletePracticeConversationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-conversation/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-陪练对话 */
export function exportPracticeConversation(params: any) {
  return requestClient.download('/aicrm/practice-conversation/export-excel', { params });
}

/** 根据陪练记录ID获取对话列表 */
export function getConversationListByRecordId(recordId: number) {
  return requestClient.get<AicrmPracticeConversationApi.PracticeConversation[]>(
    `/aicrm/practice-conversation/list-by-record?recordId=${recordId}`,
  );
}