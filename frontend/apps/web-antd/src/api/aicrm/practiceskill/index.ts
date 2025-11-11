import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeSkillApi {
  /** CRM智能陪练-销售技巧信息 */
  export interface PracticeSkill {
    id: number; // 技巧ID
    name?: string; // 技巧名称
    category?: string; // 技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧
    scriptTemplate: string; // 话术模板
    complianceRules: number; // 合规规则（培训文件ID）
    relatedProducts: number; // 产品知识（培训文件ID）
    complianceRulesName?: string; // 合规规则文件名称
    relatedProductsName?: string; // 产品知识文件名称
  }
}

/** 查询CRM智能陪练-销售技巧分页 */
export function getPracticeSkillPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeSkillApi.PracticeSkill>>(
    '/aicrm/practice-skill/page',
    { params },
  );
}

/** 查询CRM智能陪练-销售技巧详情 */
export function getPracticeSkill(id: number) {
  return requestClient.get<AicrmPracticeSkillApi.PracticeSkill>(
    `/aicrm/practice-skill/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-销售技巧 */
export function createPracticeSkill(data: AicrmPracticeSkillApi.PracticeSkill) {
  return requestClient.post('/aicrm/practice-skill/create', data);
}

/** 修改CRM智能陪练-销售技巧 */
export function updatePracticeSkill(data: AicrmPracticeSkillApi.PracticeSkill) {
  return requestClient.put('/aicrm/practice-skill/update', data);
}

/** 删除CRM智能陪练-销售技巧 */
export function deletePracticeSkill(id: number) {
  return requestClient.delete(`/aicrm/practice-skill/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-销售技巧 */
export function deletePracticeSkillList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-skill/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-销售技巧 */
export function exportPracticeSkill(params: any) {
  return requestClient.download('/aicrm/practice-skill/export-excel', { params });
}