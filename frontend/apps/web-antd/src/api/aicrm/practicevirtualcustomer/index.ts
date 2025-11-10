import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmPracticeVirtualCustomerApi {
  /** CRM智能陪练-虚拟客户信息 */
  export interface PracticeVirtualCustomer {
    id: number; // 虚拟客户ID
    name?: string; // 客户姓名
    gender: string; // 性别：字典 aicrm_gender
    age: number; // 年龄
    occupation: string; // 职业
    industry: string; // 所属行业
    personalityType: string; // 性格类型：字典 aicrm_personality_type（如理性型/感性型）
    riskPreference: string; // 风险偏好
    memo: string; // 自定义参数（JSON格式）
  }
}

/** 查询CRM智能陪练-虚拟客户分页 */
export function getPracticeVirtualCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>>(
    '/aicrm/practice-virtual-customer/page',
    { params },
  );
}

/** 查询CRM智能陪练-虚拟客户详情 */
export function getPracticeVirtualCustomer(id: number) {
  return requestClient.get<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>(
    `/aicrm/practice-virtual-customer/get?id=${id}`,
  );
}

/** 新增CRM智能陪练-虚拟客户 */
export function createPracticeVirtualCustomer(data: AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer) {
  return requestClient.post('/aicrm/practice-virtual-customer/create', data);
}

/** 修改CRM智能陪练-虚拟客户 */
export function updatePracticeVirtualCustomer(data: AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer) {
  return requestClient.put('/aicrm/practice-virtual-customer/update', data);
}

/** 删除CRM智能陪练-虚拟客户 */
export function deletePracticeVirtualCustomer(id: number) {
  return requestClient.delete(`/aicrm/practice-virtual-customer/delete?id=${id}`);
}

/** 批量删除CRM智能陪练-虚拟客户 */
export function deletePracticeVirtualCustomerList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/practice-virtual-customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM智能陪练-虚拟客户 */
export function exportPracticeVirtualCustomer(params: any) {
  return requestClient.download('/aicrm/practice-virtual-customer/export-excel', { params });
}