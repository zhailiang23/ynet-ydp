import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace GridTingtangCustomerApi {
  /** 厅堂客户信息 */
  export interface TingtangCustomer {
    id?: number; // 厅堂客户ID
    customerId?: number; // 客户ID (关联 grid_customer)
    customerName: string; // 客户名称
    gender?: string; // 性别（男、女）
    customerGroup?: string; // 客群类型
    idCard?: string; // 身份证号
    phone?: string; // 联系电话
    address?: string; // 地址
    longitude: number; // 经度
    latitude: number; // 纬度
    isArchived?: boolean; // 是否建档
    crmCustomerId?: string; // CRM客户号
    createTime?: string; // 创建时间
  }

  /** 厅堂客户分页查询参数 */
  export interface TingtangCustomerPageParams extends PageParam {
    customerName?: string; // 客户名称
    phone?: string; // 联系电话
  }
}

/** 查询厅堂客户分页 */
export function getTingtangCustomerPage(
  params: GridTingtangCustomerApi.TingtangCustomerPageParams,
) {
  return requestClient.get<PageResult<GridTingtangCustomerApi.TingtangCustomer>>(
    '/grid/tingtang-customer/page',
    { params },
  );
}

/** 查询厅堂客户详情 */
export function getTingtangCustomer(id: number) {
  return requestClient.get<GridTingtangCustomerApi.TingtangCustomer>(
    `/grid/tingtang-customer/get?id=${id}`,
  );
}

/** 新增厅堂客户 */
export function createTingtangCustomer(
  data: GridTingtangCustomerApi.TingtangCustomer,
) {
  return requestClient.post('/grid/tingtang-customer/create', data);
}

/** 修改厅堂客户 */
export function updateTingtangCustomer(
  data: GridTingtangCustomerApi.TingtangCustomer,
) {
  return requestClient.put('/grid/tingtang-customer/update', data);
}

/** 删除厅堂客户 */
export function deleteTingtangCustomer(id: number) {
  return requestClient.delete(`/grid/tingtang-customer/delete?id=${id}`);
}

/** 批量删除厅堂客户 */
export function deleteTingtangCustomerList(ids: number[]) {
  return requestClient.delete(
    `/grid/tingtang-customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出厅堂客户 */
export function exportTingtangCustomer(params: any) {
  return requestClient.download('/grid/tingtang-customer/export-excel', {
    params,
  });
}
