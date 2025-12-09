import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace InfraExternalAgentApi {
  /** 外部智能体管理信息 */
  export interface ExternalAgent {
    id: number; // 智能体ID
    code?: string; // 智能体编码
    name?: string; // 智能体名称
    description: string; // 智能体描述
    platformType?: string; // 平台类型（dify, hiagent, coze等）
    accessUrl?: string; // 访问URL
    apiKey?: string; // API密钥
    status?: number; // 状态
  }
}

/** 查询外部智能体管理分页 */
export function getExternalAgentPage(params: PageParam) {
  return requestClient.get<PageResult<InfraExternalAgentApi.ExternalAgent>>(
    '/infra/external-agent/page',
    { params },
  );
}

/** 查询外部智能体管理详情 */
export function getExternalAgent(id: number) {
  return requestClient.get<InfraExternalAgentApi.ExternalAgent>(
    `/infra/external-agent/get?id=${id}`,
  );
}

/** 新增外部智能体管理 */
export function createExternalAgent(data: InfraExternalAgentApi.ExternalAgent) {
  return requestClient.post('/infra/external-agent/create', data);
}

/** 修改外部智能体管理 */
export function updateExternalAgent(data: InfraExternalAgentApi.ExternalAgent) {
  return requestClient.put('/infra/external-agent/update', data);
}

/** 删除外部智能体管理 */
export function deleteExternalAgent(id: number) {
  return requestClient.delete(`/infra/external-agent/delete?id=${id}`);
}

/** 批量删除外部智能体管理 */
export function deleteExternalAgentList(ids: number[]) {
  return requestClient.delete(
    `/infra/external-agent/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出外部智能体管理 */
export function exportExternalAgent(params: any) {
  return requestClient.download('/infra/external-agent/export-excel', { params });
}