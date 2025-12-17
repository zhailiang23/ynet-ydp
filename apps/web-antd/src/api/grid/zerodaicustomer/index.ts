import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridZerodaiCustomerApi {
  /** 零贷客户扩展信息 */
  export interface ZerodaiCustomer {
    id: number; // 扩展ID
    customerId?: number; // 客户ID (关联 grid_customer)
    businessType?: string; // 业务类型 (关联字典 aicrm_business_type)
    annualRevenue: number; // 年营业额 (元)
    creditRating: string; // 信用评级
    isApplied: boolean; // 是否进件
    isCompleted: boolean; // 是否完件
    loanAmount: number; // 贷款投放金额 (元)
    loanBalance: number; // 贷款余额 (元)
    archiveProtected: boolean; // 是否受保护
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询零贷客户扩展分页 */
export function getZerodaiCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<GridZerodaiCustomerApi.ZerodaiCustomer>>(
    '/grid/zerodai-customer/page',
    { params },
  );
}

/** 查询零贷客户扩展详情 */
export function getZerodaiCustomer(id: number) {
  return requestClient.get<GridZerodaiCustomerApi.ZerodaiCustomer>(
    `/grid/zerodai-customer/get?id=${id}`,
  );
}

/** 新增零贷客户扩展 */
export function createZerodaiCustomer(data: GridZerodaiCustomerApi.ZerodaiCustomer) {
  return requestClient.post('/grid/zerodai-customer/create', data);
}

/** 修改零贷客户扩展 */
export function updateZerodaiCustomer(data: GridZerodaiCustomerApi.ZerodaiCustomer) {
  return requestClient.put('/grid/zerodai-customer/update', data);
}

/** 删除零贷客户扩展 */
export function deleteZerodaiCustomer(id: number) {
  return requestClient.delete(`/grid/zerodai-customer/delete?id=${id}`);
}

/** 批量删除零贷客户扩展 */
export function deleteZerodaiCustomerList(ids: number[]) {
  return requestClient.delete(
    `/grid/zerodai-customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出零贷客户扩展 */
export function exportZerodaiCustomer(params: any) {
  return requestClient.download('/grid/zerodai-customer/export-excel', { params });
}