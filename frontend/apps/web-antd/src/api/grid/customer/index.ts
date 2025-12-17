import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridCustomerApi {
  /** 网格客户档案信息 */
  export interface Customer {
    id: number; // 客户档案ID
    gridId?: number; // 所属网格ID
    customerType?: string; // 客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI
    customerName?: string; // 客户姓名
    idCard: string; // 身份证号 (脱敏: 前3后3)
    phone: string; // 联系电话 (脱敏: 前3后4)
    phoneVerified: boolean; // 手机号是否已二次验证
    address: string; // 详细地址
    location: byte[]; // 客户位置 (经纬度)
    managerId: number; // 客户经理ID (关联 system_users)
    source: string; // 客户来源
    status: string; // 状态: NORMAL/INACTIVE/BLACKLIST
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询网格客户档案分页 */
export function getCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<GridCustomerApi.Customer>>(
    '/grid/customer/page',
    { params },
  );
}

/** 查询网格客户档案详情 */
export function getCustomer(id: number) {
  return requestClient.get<GridCustomerApi.Customer>(
    `/grid/customer/get?id=${id}`,
  );
}

/** 新增网格客户档案 */
export function createCustomer(data: GridCustomerApi.Customer) {
  return requestClient.post<number>('/grid/customer/create', data);
}

/** 修改网格客户档案 */
export function updateCustomer(data: GridCustomerApi.Customer) {
  return requestClient.put('/grid/customer/update', data);
}

/** 删除网格客户档案 */
export function deleteCustomer(id: number) {
  return requestClient.delete(`/grid/customer/delete?id=${id}`);
}

/** 批量删除网格客户档案 */
export function deleteCustomerList(ids: number[]) {
  return requestClient.delete(
    `/grid/customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出网格客户档案 */
export function exportCustomer(params: any) {
  return requestClient.download('/grid/customer/export-excel', { params });
}