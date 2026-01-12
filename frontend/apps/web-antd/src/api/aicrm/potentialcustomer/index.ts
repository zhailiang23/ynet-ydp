import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPotentialCustomerApi {
  /** AI CRM 潜客管理 */
  export interface PotentialCustomer {
    id?: number; // 潜客编号
    customerId?: number; // 关联客户ID
    customerName: string; // 客户姓名
    avatar?: string; // 客户头像URL
    phone?: string; // 手机号码
    idCard?: string; // 身份证号
    riskLevel: string; // 风险等级（AGGRESSIVE=激进型, BALANCED=稳健型, CONSERVATIVE=保守型）
    customerLevel?: string; // 客户等级（A/B/C/D）
    aum: number; // 资产管理规模
    potentialValue?: number; // 潜在价值
    aiMatchScore?: number; // AI 匹配度（0-100）
    analysisConclusion?: string; // AI 分析结论
    insightId?: number; // 洞察ID
    insightTitle?: string; // 洞察标题
    status?: string; // 潜客状态（NEW=新建, FOLLOWING=跟进中, CONVERTED=已转化, LOST=已流失）
    source?: string; // 来源（AI_RECOMMENDATION=AI推荐, MANUAL=手动添加, IMPORT=导入）
    assignedUserId?: number; // 分配给的客户经理ID
    assignedUserName?: string; // 客户经理姓名
    lastContactTime?: string; // 最后联系时间
    nextFollowupTime?: string; // 下次跟进时间
    followupCount?: number; // 跟进次数
    remark?: string; // 备注
    createTime?: string; // 创建时间
    updateTime?: string; // 更新时间
  }

  /** 潜客分页查询参数 */
  export interface PotentialCustomerPageReqVO extends PageParam {
    customerName?: string; // 客户姓名
    riskLevel?: string; // 风险等级
    customerLevel?: string; // 客户等级
    minAiMatchScore?: number; // 最小AI匹配度
    status?: string; // 潜客状态
    source?: string; // 来源
    insightId?: number; // 洞察ID
    assignedUserId?: number; // 分配给的客户经理ID
    createTime?: [Dayjs, Dayjs]; // 创建时间
  }
}

/** 查询潜客分页 */
export function getPotentialCustomerPage(params: AicrmPotentialCustomerApi.PotentialCustomerPageReqVO) {
  return requestClient.get<PageResult<AicrmPotentialCustomerApi.PotentialCustomer>>(
    '/aicrm/potential-customer/page',
    { params },
  );
}

/** 查询潜客详情 */
export function getPotentialCustomer(id: number) {
  return requestClient.get<AicrmPotentialCustomerApi.PotentialCustomer>(
    `/aicrm/potential-customer/get?id=${id}`,
  );
}

/** 新增潜客 */
export function createPotentialCustomer(data: AicrmPotentialCustomerApi.PotentialCustomer) {
  return requestClient.post('/aicrm/potential-customer/create', data);
}

/** 修改潜客 */
export function updatePotentialCustomer(data: AicrmPotentialCustomerApi.PotentialCustomer) {
  return requestClient.put('/aicrm/potential-customer/update', data);
}

/** 删除潜客 */
export function deletePotentialCustomer(id: number) {
  return requestClient.delete(`/aicrm/potential-customer/delete?id=${id}`);
}

/** 批量删除潜客 */
export function deletePotentialCustomerList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/potential-customer/delete-batch?ids=${ids.join(',')}`,
  );
}

/** 导出潜客 Excel */
export function exportPotentialCustomer(params: AicrmPotentialCustomerApi.PotentialCustomerPageReqVO) {
  return requestClient.download('/aicrm/potential-customer/export-excel', { params });
}
