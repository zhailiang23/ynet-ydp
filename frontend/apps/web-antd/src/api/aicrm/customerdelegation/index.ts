import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerDelegationApi {
  /** 客户托管记录信息 */
  export interface CustomerDelegation {
    id: number; // 托管ID
    customerId: number; // 客户ID
    fromUserId: number; // 托管方客户经理ID
    toUserId: number; // 受托方客户经理ID
    startDate: string | Dayjs; // 托管开始日期
    endDate?: string | Dayjs; // 托管结束日期
    delegationStatus: number; // 托管状态（0=已结束，1=托管中，2=已取消）
    reason?: string; // 托管原因
    cancelReason?: string; // 取消原因
    endReason?: string; // 结束原因
    creatorId?: number; // 创建人ID
    createTime?: string | Dayjs; // 创建时间
    updaterId?: number; // 更新人ID
    updateTime?: string | Dayjs; // 更新时间
  }

  /** 创建客户托管请求 */
  export interface CustomerDelegationCreateReq {
    customerId: number; // 客户ID
    toUserId: number; // 受托方客户经理ID
    startDate: string | Dayjs; // 托管开始日期
    endDate?: string | Dayjs; // 托管结束日期
    delegationReason: string; // 托管原因
  }

  /** 结束客户托管请求 */
  export interface CustomerDelegationEndReq {
    id: number; // 托管ID
    endReason?: string; // 结束原因
  }

  /** 客户托管分页查询请求 */
  export interface CustomerDelegationPageReq extends PageParam {
    customerId?: number; // 客户ID
    fromUserId?: number; // 托管方客户经理ID
    toUserId?: number; // 受托方客户经理ID
    delegationStatus?: number; // 托管状态
    startDate?: string | Dayjs; // 开始日期（起）
    endDate?: string | Dayjs; // 开始日期（止）
  }
}

/** 查询客户托管记录分页 */
export function getCustomerDelegationPage(
  params: AicrmCustomerDelegationApi.CustomerDelegationPageReq,
) {
  return requestClient.get<PageResult<AicrmCustomerDelegationApi.CustomerDelegation>>(
    '/aicrm/customer-delegation/page',
    { params },
  );
}

/** 查询客户托管记录详情 */
export function getCustomerDelegation(id: number) {
  return requestClient.get<AicrmCustomerDelegationApi.CustomerDelegation>(
    `/aicrm/customer-delegation/get?id=${id}`,
  );
}

/** 创建客户托管 */
export function createCustomerDelegation(
  data: AicrmCustomerDelegationApi.CustomerDelegationCreateReq,
) {
  return requestClient.post<number>('/aicrm/customer-delegation/create', data);
}

/** 结束客户托管 */
export function endCustomerDelegation(data: AicrmCustomerDelegationApi.CustomerDelegationEndReq) {
  return requestClient.put<boolean>('/aicrm/customer-delegation/end', data);
}

/** 取消客户托管 */
export function cancelCustomerDelegation(id: number) {
  return requestClient.delete<boolean>(`/aicrm/customer-delegation/cancel?id=${id}`);
}
