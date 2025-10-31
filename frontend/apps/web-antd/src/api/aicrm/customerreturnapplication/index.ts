import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerReturnApplicationApi {
  /** 客户退回申请信息 */
  export interface CustomerReturnApplication {
    id: number; // 申请ID
    customerId?: number; // 客户ID（关联 crm_customer）
    applicantUserId?: number; // 申请人(客户经理)ID（关联 system_users.id）
    returnToUserId?: number; // 退回给主管ID（关联 system_users.id）
    applyDate?: string | Dayjs; // 申请日期
    returnReason: string; // 退回原因
    processInstanceId: string; // BPM流程实例ID
    processStatus?: number; // 流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
  }
}

/** 查询客户退回申请分页 */
export function getCustomerReturnApplicationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerReturnApplicationApi.CustomerReturnApplication>>(
    '/aicrm/customer-return-application/page',
    { params },
  );
}

/** 查询客户退回申请详情 */
export function getCustomerReturnApplication(id: number) {
  return requestClient.get<AicrmCustomerReturnApplicationApi.CustomerReturnApplication>(
    `/aicrm/customer-return-application/get?id=${id}`,
  );
}

/** 新增客户退回申请 */
export function createCustomerReturnApplication(data: AicrmCustomerReturnApplicationApi.CustomerReturnApplication) {
  return requestClient.post('/aicrm/customer-return-application/create', data);
}

/** 修改客户退回申请 */
export function updateCustomerReturnApplication(data: AicrmCustomerReturnApplicationApi.CustomerReturnApplication) {
  return requestClient.put('/aicrm/customer-return-application/update', data);
}

/** 删除客户退回申请 */
export function deleteCustomerReturnApplication(id: number) {
  return requestClient.delete(`/aicrm/customer-return-application/delete?id=${id}`);
}

/** 批量删除客户退回申请 */
export function deleteCustomerReturnApplicationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-return-application/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户退回申请 */
export function exportCustomerReturnApplication(params: any) {
  return requestClient.download('/aicrm/customer-return-application/export-excel', { params });
}