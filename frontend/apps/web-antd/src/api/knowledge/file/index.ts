import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace KnowledgeKonwledgeFileApi {
  /** 知识库文件信息 */
  export interface KonwledgeFile {
    id?: number; // 文件ID
    baseId?: number; // 知识库ID
    fileName?: string; // 文件名称
    fileType?: string; // 文件类型
    fileSize?: number; // 文件大小(字节)
    status?: number; // 文件状态(0处理中 1处理完成 2处理失败)
    createTime?: string; // 创建时间
  }

  /** RAG 召回请求 */
  export interface RagRetrieveReq {
    question: string; // 用户问题
    kbIds: number[]; // 知识库 ID 列表
    topK?: number; // 返回结果数量，默认5
  }

  /** RAG 文本分片 */
  export interface RagChunk {
    kbId: number; // 知识库 ID
    fileId: number; // 文件 ID
    chunkIdx: number; // 分片索引
    text: string; // 文本内容
    score: number; // 相似度分数
  }

  /** RAG 召回响应 */
  export interface RagRetrieveResp {
    success: boolean; // 是否成功
    message: string; // 消息
    results: RagChunk[]; // 召回结果列表
  }
}

/** 查询知识库文件分页 */
export function getKonwledgeFilePage(params: PageParam) {
  return requestClient.get<PageResult<KnowledgeKonwledgeFileApi.KonwledgeFile>>(
    '/knowledge/konwledge-file/page',
    { params },
  );
}

/** 查询知识库文件详情 */
export function getKonwledgeFile(id: number) {
  return requestClient.get<KnowledgeKonwledgeFileApi.KonwledgeFile>(
    `/knowledge/konwledge-file/get?id=${id}`,
  );
}

/** 新增知识库文件 */
export function createKonwledgeFile(data: KnowledgeKonwledgeFileApi.KonwledgeFile) {
  return requestClient.post('/knowledge/konwledge-file/create', data);
}

/** 修改知识库文件 */
export function updateKonwledgeFile(data: KnowledgeKonwledgeFileApi.KonwledgeFile) {
  return requestClient.put('/knowledge/konwledge-file/update', data);
}

/** 删除知识库文件 */
export function deleteKonwledgeFile(id: number) {
  return requestClient.delete(`/knowledge/konwledge-file/delete?id=${id}`);
}

/** 批量删除知识库文件 */
export function deleteKonwledgeFileList(ids: number[]) {
  return requestClient.delete(
    `/knowledge/konwledge-file/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出知识库文件 */
export function exportKonwledgeFile(params: any) {
  return requestClient.download('/knowledge/konwledge-file/export-excel', { params });
}

/** RAG 召回测试 */
export function retrieveKnowledgeChunks(data: KnowledgeKonwledgeFileApi.RagRetrieveReq) {
  return requestClient.post<KnowledgeKonwledgeFileApi.RagRetrieveResp>(
    '/knowledge/konwledge-file/retrieve',
    data,
  );
}