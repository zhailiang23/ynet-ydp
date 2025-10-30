import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGridAssignmentApi {
  /** 客户归属网格关系表（只记录关系，网格信息通过关联查询）信息 */
  export interface CustomerGridAssignment {
    id: number; // 归属网格关系主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    gridId?: number; // 网格ID（关联 crm_grid_info.id）
    assignDate?: string | Dayjs; // 分配日期
    assignOperatorId: number; // 分配操作人用户ID（关联 system_users.id）
    status?: number; // 归属状态（0=已失效，1=生效中）
    remark: string; // 备注
  }
}

/** 查询客户归属网格关系表（只记录关系，网格信息通过关联查询）分页 */
export function getCustomerGridAssignmentPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGridAssignmentApi.CustomerGridAssignment>>(
    '/aicrm/customer-grid-assignment/page',
    { params },
  );
}

/** 查询客户归属网格关系表（只记录关系，网格信息通过关联查询）详情 */
export function getCustomerGridAssignment(id: number) {
  return requestClient.get<AicrmCustomerGridAssignmentApi.CustomerGridAssignment>(
    `/aicrm/customer-grid-assignment/get?id=${id}`,
  );
}

/** 新增客户归属网格关系表（只记录关系，网格信息通过关联查询） */
export function createCustomerGridAssignment(data: AicrmCustomerGridAssignmentApi.CustomerGridAssignment) {
  return requestClient.post('/aicrm/customer-grid-assignment/create', data);
}

/** 修改客户归属网格关系表（只记录关系，网格信息通过关联查询） */
export function updateCustomerGridAssignment(data: AicrmCustomerGridAssignmentApi.CustomerGridAssignment) {
  return requestClient.put('/aicrm/customer-grid-assignment/update', data);
}

/** 删除客户归属网格关系表（只记录关系，网格信息通过关联查询） */
export function deleteCustomerGridAssignment(id: number) {
  return requestClient.delete(`/aicrm/customer-grid-assignment/delete?id=${id}`);
}

/** 批量删除客户归属网格关系表（只记录关系，网格信息通过关联查询） */
export function deleteCustomerGridAssignmentList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-grid-assignment/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属网格关系表（只记录关系，网格信息通过关联查询） */
export function exportCustomerGridAssignment(params: any) {
  return requestClient.download('/aicrm/customer-grid-assignment/export-excel', { params });
}