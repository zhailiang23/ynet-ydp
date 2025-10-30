import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGroupHistoryApi {
  /** 客户归属客群调整历史表（记录调整当时的客群信息快照）信息 */
  export interface CustomerGroupHistory {
    id: number; // 调整历史主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    adjustDate?: string | Dayjs; // 调整日期
    adjustReason: string; // 调整原因
    beforeGroupId: number; // 调整前客群ID
    beforeGroupCode: string; // 调整前客户群编号
    beforeGroupName: string; // 调整前客户群名称
    beforeGroupCategory: string; // 调整前客户群分类
    beforeManagerUserId: number; // 调整前客群管理员用户ID
    afterGroupId: number; // 调整后客群ID
    afterGroupCode: string; // 调整后客户群编号
    afterGroupName: string; // 调整后客户群名称
    afterGroupCategory: string; // 调整后客户群分类
    afterManagerUserId: number; // 调整后客群管理员用户ID
    adjustOperatorId: number; // 调整操作人用户ID（关联 system_users.id）
    remark: string; // 备注
  }
}

/** 查询客户归属客群调整历史表（记录调整当时的客群信息快照）分页 */
export function getCustomerGroupHistoryPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGroupHistoryApi.CustomerGroupHistory>>(
    '/aicrm/customer-group-history/page',
    { params },
  );
}

/** 查询客户归属客群调整历史表（记录调整当时的客群信息快照）详情 */
export function getCustomerGroupHistory(id: number) {
  return requestClient.get<AicrmCustomerGroupHistoryApi.CustomerGroupHistory>(
    `/aicrm/customer-group-history/get?id=${id}`,
  );
}

/** 新增客户归属客群调整历史表（记录调整当时的客群信息快照） */
export function createCustomerGroupHistory(data: AicrmCustomerGroupHistoryApi.CustomerGroupHistory) {
  return requestClient.post('/aicrm/customer-group-history/create', data);
}

/** 修改客户归属客群调整历史表（记录调整当时的客群信息快照） */
export function updateCustomerGroupHistory(data: AicrmCustomerGroupHistoryApi.CustomerGroupHistory) {
  return requestClient.put('/aicrm/customer-group-history/update', data);
}

/** 删除客户归属客群调整历史表（记录调整当时的客群信息快照） */
export function deleteCustomerGroupHistory(id: number) {
  return requestClient.delete(`/aicrm/customer-group-history/delete?id=${id}`);
}

/** 批量删除客户归属客群调整历史表（记录调整当时的客群信息快照） */
export function deleteCustomerGroupHistoryList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-group-history/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属客群调整历史表（记录调整当时的客群信息快照） */
export function exportCustomerGroupHistory(params: any) {
  return requestClient.download('/aicrm/customer-group-history/export-excel', { params });
}