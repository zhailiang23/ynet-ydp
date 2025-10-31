import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerClaimApplicationApi {
  /** 客户认领申请信息 */
  export interface CustomerClaimApplication {
    id: number; // 申请ID
    customerId?: number; // 客户ID（关联 crm_customer）
    applicantUserId?: number; // 申请人(客户经理)ID（关联 system_users.id）
    applicantDeptId?: number; // 申请人部门ID（关联 system_dept.id）
    applyDate?: string | Dayjs; // 申请日期
    applyReason: string; // 申请理由
    processInstanceId: string; // BPM流程实例ID
    processStatus?: number; // 流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
  }
}

/** 查询客户认领申请分页 */
export function getCustomerClaimApplicationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerClaimApplicationApi.CustomerClaimApplication>>(
    '/aicrm/customer-claim-application/page',
    { params },
  );
}

/** 查询客户认领申请详情 */
export function getCustomerClaimApplication(id: number) {
  return requestClient.get<AicrmCustomerClaimApplicationApi.CustomerClaimApplication>(
    `/aicrm/customer-claim-application/get?id=${id}`,
  );
}

/** 新增客户认领申请 */
export function createCustomerClaimApplication(data: AicrmCustomerClaimApplicationApi.CustomerClaimApplication) {
  return requestClient.post('/aicrm/customer-claim-application/create', data);
}

/** 修改客户认领申请 */
export function updateCustomerClaimApplication(data: AicrmCustomerClaimApplicationApi.CustomerClaimApplication) {
  return requestClient.put('/aicrm/customer-claim-application/update', data);
}

/** 删除客户认领申请 */
export function deleteCustomerClaimApplication(id: number) {
  return requestClient.delete(`/aicrm/customer-claim-application/delete?id=${id}`);
}

/** 批量删除客户认领申请 */
export function deleteCustomerClaimApplicationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-claim-application/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户认领申请 */
export function exportCustomerClaimApplication(params: any) {
  return requestClient.download('/aicrm/customer-claim-application/export-excel', { params });
}