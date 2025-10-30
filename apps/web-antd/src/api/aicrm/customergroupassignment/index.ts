import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGroupAssignmentApi {
  /** 客户归属客群关系表（只记录关系，客群信息通过关联查询）信息 */
  export interface CustomerGroupAssignment {
    id: number; // 归属客群关系主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    groupId?: number; // 客群ID（关联 crm_customer_group_info.id）
    assignDate?: string | Dayjs; // 分配日期
    assignOperatorId: number; // 分配操作人用户ID（关联 system_users.id）
    status?: number; // 归属状态（0=已失效，1=生效中）
    remark: string; // 备注
    createTime?: string | Dayjs; // 创建时间
    // 以下是联表查询返回的字段
    groupCode?: string; // 客户群编号
    groupName?: string; // 客户群名称
    groupCategory?: string; // 客户群分类
    memberType?: string; // 群级别类型
    customerSource?: string; // 客户来源
    memberCount?: number; // 客户群成员数
    groupCreateTime?: string | Dayjs; // 客户群创建日期
    creatorName?: string; // 创建人
    deptName?: string; // 创建机构
    groupUpdateTime?: string | Dayjs; // 最近修改日期
    updaterName?: string; // 最近修改人
  }
}

/** 查询客户归属客群关系表（只记录关系，客群信息通过关联查询）分页 */
export function getCustomerGroupAssignmentPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment>>(
    '/aicrm/customer-group-assignment/page',
    { params },
  );
}

/** 查询客户归属客群关系表（只记录关系，客群信息通过关联查询）详情 */
export function getCustomerGroupAssignment(id: number) {
  return requestClient.get<AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment>(
    `/aicrm/customer-group-assignment/get?id=${id}`,
  );
}

/** 新增客户归属客群关系表（只记录关系，客群信息通过关联查询） */
export function createCustomerGroupAssignment(data: AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment) {
  return requestClient.post('/aicrm/customer-group-assignment/create', data);
}

/** 修改客户归属客群关系表（只记录关系，客群信息通过关联查询） */
export function updateCustomerGroupAssignment(data: AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment) {
  return requestClient.put('/aicrm/customer-group-assignment/update', data);
}

/** 删除客户归属客群关系表（只记录关系，客群信息通过关联查询） */
export function deleteCustomerGroupAssignment(id: number) {
  return requestClient.delete(`/aicrm/customer-group-assignment/delete?id=${id}`);
}

/** 批量删除客户归属客群关系表（只记录关系，客群信息通过关联查询） */
export function deleteCustomerGroupAssignmentList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-group-assignment/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属客群关系表（只记录关系，客群信息通过关联查询） */
export function exportCustomerGroupAssignment(params: any) {
  return requestClient.download('/aicrm/customer-group-assignment/export-excel', { params });
}