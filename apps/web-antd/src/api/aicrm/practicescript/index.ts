import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeScriptApi {
  /** CRM智能陪练-陪练剧本表（支持版本控制）信息 */
  export interface PracticeScript {
    id: number; // 剧本版本ID（主键）
    scriptNo?: string; // 剧本编号（标识同一个剧本的不同版本）
    version?: string; // 版本号（如 v1.0, v1.1, v2.0）
    isLatest?: boolean; // 是否最新版本
    parentVersionId: number; // 父版本ID（用于追溯版本历史）
    versionDescription: string; // 版本说明（本次修改的内容）
    status?: string; // 版本状态：字典 aicrm_script_status
    name?: string; // 剧本名称
    description: string; // 剧本描述
    difficultyLevel?: string; // 难度等级：字典 aicrm_difficulty_level
    marketingStep?: string; // 营销环节：字典 aicrm_marketing_step
    caseId: number; // 关联销售案例ID
    caseName?: string; // 关联销售案例名称
    skillId: number; // 关联销售技巧ID
    skillName?: string; // 关联销售技巧名称
    virtualCustomerId: number; // 关联虚拟客户ID
    virtualCustomerName?: string; // 关联虚拟客户名称
    materialIds: string; // 关联培训文件ID列表（多个ID逗号分隔）
    materialNames?: string; // 关联培训文件名称
    content: string; // 剧本内容（AI生成）
    contentEdit: string; // 手工调整内容（用户编辑）
    usageCount: number; // 使用次数（该版本被使用的次数）
  }

  /** 生成剧本内容请求参数 */
  export interface GenerateScriptContentReq {
    scriptId: number; // 剧本ID
    caseId?: number; // 关联案例ID
    materialIds?: string; // 关联培训文件ID列表（多个ID逗号分隔）
    skillId?: number; // 关联销售技巧ID
    marketingStep: string; // 营销环节
    difficultyLevel: string; // 难度等级
    scriptDescription?: string; // 剧本描述
  }
}

/** 查询CRM智能陪练-陪练剧本表（支持版本控制）分页 */
export function getPracticeScriptPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeScriptApi.PracticeScript>>(
    '/aicrm/practice-script/page',
    { params },
  );
}

/** 查询CRM智能陪练-陪练剧本表（支持版本控制）详情 */
export function getPracticeScript(id: number) {
  return requestClient.get<AicrmPracticeScriptApi.PracticeScript>(
    `/aicrm/practice-script/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-陪练剧本表（支持版本控制） */
export function createPracticeScript(data: AicrmPracticeScriptApi.PracticeScript) {
  return requestClient.post('/aicrm/practice-script/create', data);
}

/** 修改CRM智能陪练-陪练剧本表（支持版本控制） */
export function updatePracticeScript(data: AicrmPracticeScriptApi.PracticeScript) {
  return requestClient.put('/aicrm/practice-script/update', data);
}

/** 删除CRM智能陪练-陪练剧本表（支持版本控制） */
export function deletePracticeScript(id: number) {
  return requestClient.delete(`/aicrm/practice-script/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-陪练剧本表（支持版本控制） */
export function deletePracticeScriptList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-script/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-陪练剧本表（支持版本控制） */
export function exportPracticeScript(params: any) {
  return requestClient.download('/aicrm/practice-script/export-excel', { params });
}

/** 获取剧本的版本历史 */
export function getVersionHistory(scriptNo: string) {
  return requestClient.get<AicrmPracticeScriptApi.PracticeScript[]>(
    `/aicrm/practice-script/version-history?scriptNo=${scriptNo}`,
  );
}

/** 生成剧本内容 */
export function generateScriptContent(data: AicrmPracticeScriptApi.GenerateScriptContentReq) {
  return requestClient.post<string>('/aicrm/practice-script/generate-content', data);
}