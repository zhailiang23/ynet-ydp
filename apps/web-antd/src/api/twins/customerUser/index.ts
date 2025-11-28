import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace TwinsCustomerUserApi {
  /** 接入用户信息 */
  export interface CustomerUser {
    id: number; // 主键
    username?: string; // 用户名
  }
}

/** 查询接入用户分页 */
export function getCustomerUserPage(params: PageParam) {
  return requestClient.get<PageResult<TwinsCustomerUserApi.CustomerUser>>(
    '/twins/customer-user/page',
    { params },
  );
}

/** 查询接入用户详情 */
export function getCustomerUser(id: number) {
  return requestClient.get<TwinsCustomerUserApi.CustomerUser>(
    `/twins/customer-user/get?id=${id}`,
  );
}

/** 新增接入用户 */
export function createCustomerUser(data: TwinsCustomerUserApi.CustomerUser) {
  return requestClient.post('/twins/customer-user/create', data);
}

/** 修改接入用户 */
export function updateCustomerUser(data: TwinsCustomerUserApi.CustomerUser) {
  return requestClient.put('/twins/customer-user/update', data);
}

/** 删除接入用户 */
export function deleteCustomerUser(id: number) {
  return requestClient.delete(`/twins/customer-user/delete?id=${id}`);
}

/** 批量删除接入用户 */
export function deleteCustomerUserList(ids: number[]) {
  return requestClient.delete(
    `/twins/customer-user/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出接入用户 */
export function exportCustomerUser(params: any) {
  return requestClient.download('/twins/customer-user/export-excel', { params });
}