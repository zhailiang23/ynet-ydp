import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace TwinsCustomerAdminApi {
  /** 客服信息信息 */
  export interface CustomerAdmin {
    id: number; // 主键
    customerId?: number; // 客户 id (对应用户 id)
    username?: string; // 用户名
    password?: string; // 密码
    createdAt?: string | Dayjs; // 创建时间
    updatedAt?: string | Dayjs; // 修改时间
  }
}

/** 查询客服信息分页 */
export function getCustomerAdminPage(params: PageParam) {
  return requestClient.get<PageResult<TwinsCustomerAdminApi.CustomerAdmin>>(
    '/twins/customer-admin/page',
    { params },
  );
}

/** 查询客服信息详情 */
export function getCustomerAdmin(id: number) {
  return requestClient.get<TwinsCustomerAdminApi.CustomerAdmin>(
    `/twins/customer-admin/get?id=${id}`,
  );
}

/** 新增客服信息 */
export function createCustomerAdmin(data: TwinsCustomerAdminApi.CustomerAdmin) {
  return requestClient.post('/twins/customer-admin/create', data);
}

/** 修改客服信息 */
export function updateCustomerAdmin(data: TwinsCustomerAdminApi.CustomerAdmin) {
  return requestClient.put('/twins/customer-admin/update', data);
}

/** 删除客服信息 */
export function deleteCustomerAdmin(id: number) {
  return requestClient.delete(`/twins/customer-admin/delete?id=${id}`);
}

/** 批量删除客服信息 */
export function deleteCustomerAdminList(ids: number[]) {
  return requestClient.delete(
    `/twins/customer-admin/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客服信息 */
export function exportCustomerAdmin(params: any) {
  return requestClient.download('/twins/customer-admin/export-excel', { params });
}

/** 根据用户ID检查客服是否已存在 */
export function checkCustomerAdminExists(customerId: number) {
  return requestClient.get<boolean>(
    `/twins/customer-admin/check-exists?customerId=${customerId}`,
  );
}