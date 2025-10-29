import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAssignmentApi {
  /** 客户归属关系表（零售+对公共用，支持主协办模式）信息 */
  export interface CustomerAssignment {
    id: number; // 归属关系主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    assignmentType?: number; // 归属类型（1=主办，2=协办）
    deptId?: number; // 归属部门ID（关联 system_dept.id）
    userId?: number; // 客户经理用户ID（关联 system_users.id）
    hasViewRight?: boolean; // 是否有查看权限
    hasMaintainRight?: boolean; // 是否有维护权限
    assignDate?: string | Dayjs; // 分配日期
    effectiveDate: string | Dayjs; // 生效日期
    expiryDate: string | Dayjs; // 失效日期（NULL表示长期有效）
    assignOperatorId: number; // 分配操作人用户ID（关联 system_users.id）
    status?: number; // 归属状态（0=已失效，1=生效中，2=待生效）
    remark: string; // 备注
  }
}

/** 查询客户归属关系表（零售+对公共用，支持主协办模式）分页 */
export function getCustomerAssignmentPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAssignmentApi.CustomerAssignment>>(
    '/aicrm/customer-assignment/page',
    { params },
  );
}

/** 查询客户归属关系表（零售+对公共用，支持主协办模式）详情 */
export function getCustomerAssignment(id: number) {
  return requestClient.get<AicrmCustomerAssignmentApi.CustomerAssignment>(
    `/aicrm/customer-assignment/get?id=${id}`,
  );
}

/** 新增客户归属关系表（零售+对公共用，支持主协办模式） */
export function createCustomerAssignment(data: AicrmCustomerAssignmentApi.CustomerAssignment) {
  return requestClient.post('/aicrm/customer-assignment/create', data);
}

/** 修改客户归属关系表（零售+对公共用，支持主协办模式） */
export function updateCustomerAssignment(data: AicrmCustomerAssignmentApi.CustomerAssignment) {
  return requestClient.put('/aicrm/customer-assignment/update', data);
}

/** 删除客户归属关系表（零售+对公共用，支持主协办模式） */
export function deleteCustomerAssignment(id: number) {
  return requestClient.delete(`/aicrm/customer-assignment/delete?id=${id}`);
}

/** 批量删除客户归属关系表（零售+对公共用，支持主协办模式） */
export function deleteCustomerAssignmentList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-assignment/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属关系表（零售+对公共用，支持主协办模式） */
export function exportCustomerAssignment(params: any) {
  return requestClient.download('/aicrm/customer-assignment/export-excel', { params });
}