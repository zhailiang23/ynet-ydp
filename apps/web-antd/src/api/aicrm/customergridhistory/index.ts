import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGridHistoryApi {
  /** 客户归属网格调整历史表（记录调整当时的网格信息快照）信息 */
  export interface CustomerGridHistory {
    id: number; // 调整历史主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    adjustDate?: string | Dayjs; // 调整日期
    adjustReason: string; // 调整原因
    beforeGridId: number; // 调整前网格ID
    beforeGridCode: string; // 调整前网格编号
    beforeGridName: string; // 调整前网格名称
    beforeGridType: string; // 调整前网格类型
    beforeGridManagerUserId: number; // 调整前网格管理员用户ID
    afterGridId: number; // 调整后网格ID
    afterGridCode: string; // 调整后网格编号
    afterGridName: string; // 调整后网格名称
    afterGridType: string; // 调整后网格类型
    afterGridManagerUserId: number; // 调整后网格管理员用户ID
    adjustOperatorId: number; // 调整操作人用户ID（关联 system_users.id）
    remark: string; // 备注
  }
}

/** 查询客户归属网格调整历史表（记录调整当时的网格信息快照）分页 */
export function getCustomerGridHistoryPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGridHistoryApi.CustomerGridHistory>>(
    '/aicrm/customer-grid-history/page',
    { params },
  );
}

/** 查询客户归属网格调整历史表（记录调整当时的网格信息快照）详情 */
export function getCustomerGridHistory(id: number) {
  return requestClient.get<AicrmCustomerGridHistoryApi.CustomerGridHistory>(
    `/aicrm/customer-grid-history/get?id=${id}`,
  );
}

/** 新增客户归属网格调整历史表（记录调整当时的网格信息快照） */
export function createCustomerGridHistory(data: AicrmCustomerGridHistoryApi.CustomerGridHistory) {
  return requestClient.post('/aicrm/customer-grid-history/create', data);
}

/** 修改客户归属网格调整历史表（记录调整当时的网格信息快照） */
export function updateCustomerGridHistory(data: AicrmCustomerGridHistoryApi.CustomerGridHistory) {
  return requestClient.put('/aicrm/customer-grid-history/update', data);
}

/** 删除客户归属网格调整历史表（记录调整当时的网格信息快照） */
export function deleteCustomerGridHistory(id: number) {
  return requestClient.delete(`/aicrm/customer-grid-history/delete?id=${id}`);
}

/** 批量删除客户归属网格调整历史表（记录调整当时的网格信息快照） */
export function deleteCustomerGridHistoryList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-grid-history/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户归属网格调整历史表（记录调整当时的网格信息快照） */
export function exportCustomerGridHistory(params: any) {
  return requestClient.download('/aicrm/customer-grid-history/export-excel', { params });
}