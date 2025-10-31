import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerDelegationApi {
  /** 客户托管记录信息 */
  export interface CustomerDelegation {
    id: number; // 托管ID
    customerId?: number; // 客户ID（关联 crm_customer）
    fromUserId?: number; // 托管方客户经理ID（关联 system_users.id）
    toUserId?: number; // 受托方客户经理ID（关联 system_users.id）
    startDate?: string | Dayjs; // 托管开始日期
    endDate: string | Dayjs; // 托管结束日期（计划）
    actualEndDate: string | Dayjs; // 实际结束日期
    delegationReason: string; // 托管原因
    delegationStatus?: number; // 托管状态（0=已结束，1=托管中，2=已取消）
  }
}

/** 查询客户托管记录分页 */
export function getCustomerDelegationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerDelegationApi.CustomerDelegation>>(
    '/aicrm/customer-delegation/page',
    { params },
  );
}

/** 查询客户托管记录详情 */
export function getCustomerDelegation(id: number) {
  return requestClient.get<AicrmCustomerDelegationApi.CustomerDelegation>(
    `/aicrm/customer-delegation/get?id=${id}`,
  );
}

/** 新增客户托管记录 */
export function createCustomerDelegation(data: AicrmCustomerDelegationApi.CustomerDelegation) {
  return requestClient.post('/aicrm/customer-delegation/create', data);
}

/** 修改客户托管记录 */
export function updateCustomerDelegation(data: AicrmCustomerDelegationApi.CustomerDelegation) {
  return requestClient.put('/aicrm/customer-delegation/update', data);
}

/** 删除客户托管记录 */
export function deleteCustomerDelegation(id: number) {
  return requestClient.delete(`/aicrm/customer-delegation/delete?id=${id}`);
}

/** 批量删除客户托管记录 */
export function deleteCustomerDelegationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-delegation/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户托管记录 */
export function exportCustomerDelegation(params: any) {
  return requestClient.download('/aicrm/customer-delegation/export-excel', { params });
}