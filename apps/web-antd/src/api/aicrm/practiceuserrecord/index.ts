import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeUserRecordApi {
  /** CRM智能陪练-用户陪练记录信息 */
  export interface PracticeUserRecord {
    id: number; // 记录ID
    courseId?: number; // 关联陪练课程ID
    userId?: number; // 参与用户ID
    vcustomerId?: number; // 虚拟用户ID
    recordNo?: string; // 记录编号（唯一）
    status?: string; // 记录状态：字典 aicrm_record_status
    startTime?: string | Dayjs; // 开始时间
    endTime: string | Dayjs; // 结束时间
    duration: number; // 实际时长（分钟）
    totalScore: number; // 总评分
    dimensionScores: string; // 各维度得分（JSON格式）
    completionRate: number; // 完成进度（%）
    aiSummary: string; // AI 总结评价
    strengths: string; // 优点总结（JSON格式）
    weaknesses: string; // 待改进点（JSON格式）
    recommendations: string; // 改进建议（JSON格式）
  }
}

/** 查询CRM智能陪练-用户陪练记录分页 */
export function getPracticeUserRecordPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeUserRecordApi.PracticeUserRecord>>(
    '/aicrm/practice-user-record/page',
    { params },
  );
}

/** 查询CRM智能陪练-用户陪练记录详情 */
export function getPracticeUserRecord(id: number) {
  return requestClient.get<AicrmPracticeUserRecordApi.PracticeUserRecord>(
    `/aicrm/practice-user-record/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-用户陪练记录 */
export function createPracticeUserRecord(data: AicrmPracticeUserRecordApi.PracticeUserRecord) {
  return requestClient.post('/aicrm/practice-user-record/create', data);
}

/** 修改CRM智能陪练-用户陪练记录 */
export function updatePracticeUserRecord(data: AicrmPracticeUserRecordApi.PracticeUserRecord) {
  return requestClient.put('/aicrm/practice-user-record/update', data);
}

/** 删除CRM智能陪练-用户陪练记录 */
export function deletePracticeUserRecord(id: number) {
  return requestClient.delete(`/aicrm/practice-user-record/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-用户陪练记录 */
export function deletePracticeUserRecordList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-user-record/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-用户陪练记录 */
export function exportPracticeUserRecord(params: any) {
  return requestClient.download('/aicrm/practice-user-record/export-excel', { params });
}