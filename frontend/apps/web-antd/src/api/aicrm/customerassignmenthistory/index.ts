import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAssignmentHistoryApi {
  /** 客户归属调整历史表（零售+对公共用，记录所有归属变更历史）信息 */
  export interface CustomerAssignmentHistory {
    id: number; // 调整历史主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    transferDate?: string | Dayjs; // 调整日期
    transferLevel: string; // 调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）
    transferReason: string; // 调整原因
    beforeAssignmentType: number; // 调整前归属类型（1=主办，2=协办）
    beforeDeptId: number; // 调整前部门ID（关联 system_dept.id）
    beforeUserId: number; // 调整前客户经理用户ID（关联 system_users.id）
    afterAssignmentType: number; // 调整后归属类型（1=主办，2=协办）
    afterDeptId: number; // 调整后部门ID（关联 system_dept.id）
    afterUserId: number; // 调整后客户经理用户ID（关联 system_users.id）
    assignOperatorId: number; // 调整操作人用户ID（关联 system_users.id）
    assignDate: string | Dayjs; // 分配日期
    remark: string; // 备注
  }
}

/** 查询客户归属调整历史表（零售+对公共用，记录所有归属变更历史）分页 */
export function getCustomerAssignmentHistoryPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>>(
    '/aicrm/customer-assignment-history/page',
    { params },
  );
}

/** 查询客户归属调整历史表（零售+对公共用，记录所有归属变更历史）详情 */
export function getCustomerAssignmentHistory(id: number) {
  return requestClient.get<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>(
    `/aicrm/customer-assignment-history/get?id=${id}`,
  );
}

/** 新增客户归属调整历史表（零售+对公共用，记录所有归属变更历史） */
export function createCustomerAssignmentHistory(data: AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory) {
  return requestClient.post('/aicrm/customer-assignment-history/create', data);
}

/** 修改客户归属调整历史表（零售+对公共用，记录所有归属变更历史） */
export function updateCustomerAssignmentHistory(data: AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory) {
  return requestClient.put('/aicrm/customer-assignment-history/update', data);
}

/** 删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史） */
export function deleteCustomerAssignmentHistory(id: number) {
  return requestClient.delete(`/aicrm/customer-assignment-history/delete?id=${id}`);
}

/** 批量删除客户归属调整历史表（零售+对公共用，记录所有归属变更历史） */
export function deleteCustomerAssignmentHistoryList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-assignment-history/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属调整历史表（零售+对公共用，记录所有归属变更历史） */
export function exportCustomerAssignmentHistory(params: any) {
  return requestClient.download('/aicrm/customer-assignment-history/export-excel', { params });
}