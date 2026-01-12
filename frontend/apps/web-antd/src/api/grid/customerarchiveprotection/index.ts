import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridCustomerArchiveProtectionApi {
  /** 客户档案保护记录信息 */
  export interface CustomerArchiveProtection {
    id: number; // 保护记录ID
    customerId?: number; // 客户ID (关联 grid_customer)
    protectionType?: string; // 保护类型: CLAIMED/TIME_BASED
    originalManagerId?: number; // 原客户经理ID
    claimTime: string | Dayjs; // 认领时间
    protectionStartTime?: string | Dayjs; // 保护开始时间
    protectionEndTime: string | Dayjs; // 保护结束时间
    isProtected: boolean; // 是否仍受保护
    releaseReason: string; // 解除保护原因
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询客户档案保护记录分页 */
export function getCustomerArchiveProtectionPage(params: PageParam) {
  return requestClient.get<PageResult<GridCustomerArchiveProtectionApi.CustomerArchiveProtection>>(
    '/grid/customer-archive-protection/page',
    { params },
  );
}

/** 查询客户档案保护记录详情 */
export function getCustomerArchiveProtection(id: number) {
  return requestClient.get<GridCustomerArchiveProtectionApi.CustomerArchiveProtection>(
    `/grid/customer-archive-protection/get?id=${id}`,
  );
}

/** 新增客户档案保护记录 */
export function createCustomerArchiveProtection(data: GridCustomerArchiveProtectionApi.CustomerArchiveProtection) {
  return requestClient.post('/grid/customer-archive-protection/create', data);
}

/** 修改客户档案保护记录 */
export function updateCustomerArchiveProtection(data: GridCustomerArchiveProtectionApi.CustomerArchiveProtection) {
  return requestClient.put('/grid/customer-archive-protection/update', data);
}

/** 删除客户档案保护记录 */
export function deleteCustomerArchiveProtection(id: number) {
  return requestClient.delete(`/grid/customer-archive-protection/delete?id=${id}`);
}

/** 批量删除客户档案保护记录 */
export function deleteCustomerArchiveProtectionList(ids: number[]) {
  return requestClient.delete(
    `/grid/customer-archive-protection/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户档案保护记录 */
export function exportCustomerArchiveProtection(params: any) {
  return requestClient.download('/grid/customer-archive-protection/export-excel', { params });
}