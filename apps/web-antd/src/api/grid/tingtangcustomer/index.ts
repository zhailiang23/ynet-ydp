import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridTingtangCustomerApi {
  /** 厅堂客户扩展信息 */
  export interface TingtangCustomer {
    id: number; // 扩展ID
    customerId?: number; // 客户ID (关联 grid_customer)
    gender: string; // 性别: 男/女
    customerGroup?: string; // 客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)
    hasCrmNumber: boolean; // 是否有CRM客户号 (用于转化统计)
    crmCustomerId: string; // CRM客户号
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询厅堂客户扩展分页 */
export function getTingtangCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<GridTingtangCustomerApi.TingtangCustomer>>(
    '/grid/tingtang-customer/page',
    { params },
  );
}

/** 查询厅堂客户扩展详情 */
export function getTingtangCustomer(id: number) {
  return requestClient.get<GridTingtangCustomerApi.TingtangCustomer>(
    `/grid/tingtang-customer/get?id=${id}`,
  );
}

/** 新增厅堂客户扩展 */
export function createTingtangCustomer(data: GridTingtangCustomerApi.TingtangCustomer) {
  return requestClient.post('/grid/tingtang-customer/create', data);
}

/** 修改厅堂客户扩展 */
export function updateTingtangCustomer(data: GridTingtangCustomerApi.TingtangCustomer) {
  return requestClient.put('/grid/tingtang-customer/update', data);
}

/** 删除厅堂客户扩展 */
export function deleteTingtangCustomer(id: number) {
  return requestClient.delete(`/grid/tingtang-customer/delete?id=${id}`);
}

/** 批量删除厅堂客户扩展 */
export function deleteTingtangCustomerList(ids: number[]) {
  return requestClient.delete(
    `/grid/tingtang-customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出厅堂客户扩展 */
export function exportTingtangCustomer(params: any) {
  return requestClient.download('/grid/tingtang-customer/export-excel', { params });
}