import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace CustomerWorkInfoApi {
  /** 客户工作信息 */
  export interface CustomerWorkInfo {
    id: number;
    customerId: number;
    employerName?: string;
    employerType?: string;
    industry?: string;
    position?: string;
    positionLevel?: string;
    department?: string;
    workYears?: number;
    annualIncome?: number;
    monthlyIncome?: number;
    hasSocialInsurance?: boolean;
    hasHousingFund?: boolean;
    workPhone?: string;
    workEmail?: string;
    remark?: string;
    createTime?: string;
  }

  /** 客户工作信息分页请求 */
  export interface CustomerWorkInfoPageReq extends PageParam {
    customerId?: number;
    employerName?: string;
  }
}

/**
 * 获取客户工作信息分页
 */
export function getCustomerWorkInfoPageApi(
  params: CustomerWorkInfoApi.CustomerWorkInfoPageReq,
) {
  return requestClient.get<PageResult<CustomerWorkInfoApi.CustomerWorkInfo>>(
    '/aicrm/customer-work-info/page',
    { params },
  );
}

/**
 * 获取客户工作信息详情
 */
export function getCustomerWorkInfoApi(id: number) {
  return requestClient.get<CustomerWorkInfoApi.CustomerWorkInfo>(
    '/aicrm/customer-work-info/get',
    { params: { id } },
  );
}

/**
 * 创建客户工作信息
 */
export function createCustomerWorkInfoApi(
  data: CustomerWorkInfoApi.CustomerWorkInfo,
) {
  return requestClient.post<number>('/aicrm/customer-work-info/create', data);
}

/**
 * 更新客户工作信息
 */
export function updateCustomerWorkInfoApi(
  data: CustomerWorkInfoApi.CustomerWorkInfo,
) {
  return requestClient.put<boolean>('/aicrm/customer-work-info/update', data);
}

/**
 * 删除客户工作信息
 */
export function deleteCustomerWorkInfoApi(id: number) {
  return requestClient.delete<boolean>('/aicrm/customer-work-info/delete', {
    params: { id },
  });
}

// 向后兼容：导出无 Api 后缀的别名（供旧代码使用）
export const getCustomerWorkInfoPage = getCustomerWorkInfoPageApi;
export const getCustomerWorkInfo = getCustomerWorkInfoApi;
export const createCustomerWorkInfo = createCustomerWorkInfoApi;
export const updateCustomerWorkInfo = updateCustomerWorkInfoApi;
export const deleteCustomerWorkInfo = deleteCustomerWorkInfoApi;
