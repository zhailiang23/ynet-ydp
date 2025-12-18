import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridCommunityCustomerApi {
  /** 社区客户扩展信息 */
  export interface CommunityCustomer {
    id: number; // 扩展ID
    customerId?: number; // 客户ID (关联 grid_customer)
    customerName?: string; // 客户姓名
    phone?: string; // 手机号
    idType?: string; // 证件类型
    idNumber?: string; // 证件号
    orgName?: string; // 机构名称
    managerName?: string; // 客户经理姓名
    familyMembers?: number; // 家庭成员数
    housingType?: string; // 住房类型
    monthlyIncome?: number; // 月收入 (元)
    creatorId?: number; // 创建人ID
    updaterId?: number; // 更新人ID
    createTime?: string; // 创建时间
  }
}

/** 查询社区客户扩展分页 */
export function getCommunityCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<GridCommunityCustomerApi.CommunityCustomer>>(
    '/grid/community-customer/page',
    { params },
  );
}

/** 查询社区客户扩展详情 */
export function getCommunityCustomer(id: number) {
  return requestClient.get<GridCommunityCustomerApi.CommunityCustomer>(
    `/grid/community-customer/get?id=${id}`,
  );
}

/** 新增社区客户扩展 */
export function createCommunityCustomer(data: GridCommunityCustomerApi.CommunityCustomer) {
  return requestClient.post('/grid/community-customer/create', data);
}

/** 修改社区客户扩展 */
export function updateCommunityCustomer(data: GridCommunityCustomerApi.CommunityCustomer) {
  return requestClient.put('/grid/community-customer/update', data);
}

/** 删除社区客户扩展 */
export function deleteCommunityCustomer(id: number) {
  return requestClient.delete(`/grid/community-customer/delete?id=${id}`);
}

/** 批量删除社区客户扩展 */
export function deleteCommunityCustomerList(ids: number[]) {
  return requestClient.delete(
    `/grid/community-customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出社区客户扩展 */
export function exportCommunityCustomer(params: any) {
  return requestClient.download('/grid/community-customer/export-excel', { params });
}