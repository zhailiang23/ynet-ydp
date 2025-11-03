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

  /** 批量分配客户请求 */
  export interface AssignCustomerReq {
    customerIds: number[]; // 客户ID列表
    assignmentType: number; // 归属类型（1=主办，2=协办）
    deptId: number; // 归属部门ID
    userId: number; // 客户经理用户ID
    hasViewRight?: boolean; // 是否有查看权限
    hasMaintainRight?: boolean; // 是否有维护权限
    effectiveDate?: string | Dayjs; // 生效日期
    expiryDate?: string | Dayjs; // 失效日期
    remark?: string; // 备注
  }

  /** 批量移交客户请求 */
  export interface TransferCustomerReq {
    customerIds: number[]; // 客户ID列表
    toUserId: number; // 目标客户经理ID
    transferReason: string; // 移交原因
  }

  /** 批量回收客户请求 */
  export interface ReclaimCustomerReq {
    customerIds: number[]; // 客户ID列表
    reclaimReason: string; // 回收原因
  }

  /** 批量变更主办部门请求 */
  export interface ChangeDeptReq {
    customerIds: number[]; // 客户ID列表
    newDeptId: number; // 新部门ID
    newUserId: number; // 新客户经理ID
    changeReason: string; // 变更原因
  }

  /** 快速认领客户请求 */
  export interface ClaimCustomerReq {
    customerIds: number[]; // 客户ID列表
    remark?: string; // 备注
  }

  /** 我的客户分页请求 */
  export interface MyCustomerPageReq extends PageParam {
    customerNo?: string; // 客户编号
    customerName?: string; // 客户名称
    customerType?: number; // 客户类型
    assignmentType?: number; // 归属类型（1=主办，2=协办）
    customerStatus?: number; // 客户状态
  }

  /** 我的客户响应 */
  export interface MyCustomerResp {
    assignmentId: number; // 归属关系ID
    customerId: number; // 客户ID
    customerNo: string; // 客户编号
    customerName: string; // 客户名称
    customerType: number; // 客户类型(1-零售 2-对公)
    customerStatus: number; // 客户状态
    assignmentType: number; // 归属类型(1-主办 2-协办)
    deptId: number; // 归属部门ID
    deptName?: string; // 归属部门名称
    assignDate: string | Dayjs; // 分配日期
    remark?: string; // 分配备注
    delegateUserName?: string; // 托管用户名称
    delegateStartDate?: string | Dayjs; // 托管开始日期
    delegateEndDate?: string | Dayjs; // 托管结束日期
  }

  /** 托管客户请求 */
  export interface DelegateCustomerReq {
    customerIds: number[]; // 客户ID列表
    delegateToUserId: number; // 托管给的用户ID
    delegateStartDate: string | Dayjs; // 托管开始日期
    delegateEndDate: string | Dayjs; // 托管结束日期
    delegateReason?: string; // 托管原因
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

// ==================== 批量操作接口 ====================

/** 批量分配客户 */
export function assignCustomers(data: AicrmCustomerAssignmentApi.AssignCustomerReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/assign-customers', data);
}

/** 批量移交客户 */
export function transferCustomers(data: AicrmCustomerAssignmentApi.TransferCustomerReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/transfer-customers', data);
}

/** 批量回收客户 */
export function reclaimCustomers(data: AicrmCustomerAssignmentApi.ReclaimCustomerReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/reclaim-customers', data);
}

/** 批量变更主办部门 */
export function changeDept(data: AicrmCustomerAssignmentApi.ChangeDeptReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/change-dept', data);
}

/** 快速认领客户（未分配客户） */
export function claimCustomers(data: AicrmCustomerAssignmentApi.ClaimCustomerReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/claim-customers', data);
}

/** 获取我的客户分页 */
export function getMyCustomerPage(params: AicrmCustomerAssignmentApi.MyCustomerPageReq) {
  return requestClient.get<PageResult<AicrmCustomerAssignmentApi.MyCustomerResp>>(
    '/aicrm/customer-assignment/my-customer-page',
    { params },
  );
}

/** 托管客户 */
export function delegateCustomers(data: AicrmCustomerAssignmentApi.DelegateCustomerReq) {
  return requestClient.post<boolean>('/aicrm/customer-assignment/delegate-customers', data);
}