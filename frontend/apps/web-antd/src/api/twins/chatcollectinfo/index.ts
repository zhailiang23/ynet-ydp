import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace TwinsChatCollectInfoApi {
  /** 客户留资信息信息 */
  export interface ChatCollectInfo {
    id: number; // 主键
    content: string; // 留资内容 (JSON字符串)
    status: number; // 留资状态 (0=未处理, 1=处理中, 2=处理完成)
    statusText?: string; // 状态文本
    templateId: number; // 留资模板ID
    templateName?: string; // 留资模板名称
    templateDescription?: string; // 留资模板描述
    userId: number; // 提交用户ID
    username?: string; // 提交用户名
    adminId?: number; // 处理客服ID
    adminName?: string; // 处理客服名称
    remark?: string; // 备注
    source?: string; // 来源 (weapp/h5/web等)
    createTime?: string; // 创建时间
    acceptTime?: string; // 受理时间
    finishTime?: string; // 完成时间
    updateTime?: string; // 更新时间
  }
}

/** 查询客户留资信息分页 */
export function getChatCollectInfoPage(params: PageParam) {
  return requestClient.get<PageResult<TwinsChatCollectInfoApi.ChatCollectInfo>>(
    '/twins/chat-collect-info/page',
    { params },
  );
}

/** 查询客户留资信息详情 */
export function getChatCollectInfo(id: number) {
  return requestClient.get<TwinsChatCollectInfoApi.ChatCollectInfo>(
    `/twins/chat-collect-info/get?id=${id}`,
  );
}

/** 新增客户留资信息 */
export function createChatCollectInfo(data: TwinsChatCollectInfoApi.ChatCollectInfo) {
  return requestClient.post('/twins/chat-collect-info/create', data);
}

/** 修改客户留资信息 */
export function updateChatCollectInfo(data: TwinsChatCollectInfoApi.ChatCollectInfo) {
  return requestClient.put('/twins/chat-collect-info/update', data);
}

/** 删除客户留资信息 */
export function deleteChatCollectInfo(id: number) {
  return requestClient.delete(`/twins/chat-collect-info/delete?id=${id}`);
}

/** 批量删除客户留资信息 */
export function deleteChatCollectInfoList(ids: number[]) {
  return requestClient.delete(
    `/twins/chat-collect-info/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户留资信息 */
export function exportChatCollectInfo(params: any) {
  return requestClient.download('/twins/chat-collect-info/export-excel', { params });
}