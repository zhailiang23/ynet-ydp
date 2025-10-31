import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace CustomerBusinessInfoApi {
  /** 客户经营信息 */
  export interface CustomerBusinessInfo {
    id: number;
    customerId: number;
    businessName: string;
    businessType?: string;
    businessLicenseNo?: string;
    businessScope?: string;
    industry?: string;
    businessScale?: string;
    businessStatus?: string;
    registeredCapital?: number;
    employeeCount?: number;
    annualRevenue?: number;
    monthlyRevenue?: number;
    annualProfit?: number;
    taxRegistrationNo?: string;
    isGeneralTaxpayer?: boolean;
    remark?: string;
    createTime?: string;
  }

  /** 客户经营信息分页请求 */
  export interface CustomerBusinessInfoPageReq extends PageParam {
    customerId?: number;
    businessName?: string;
  }
}

/**
 * 获取客户经营信息分页
 */
export function getCustomerBusinessInfoPageApi(
  params: CustomerBusinessInfoApi.CustomerBusinessInfoPageReq,
) {
  return requestClient.get<
    PageResult<CustomerBusinessInfoApi.CustomerBusinessInfo>
  >('/aicrm/customer-business-info/page', { params });
}

/**
 * 获取客户经营信息详情
 */
export function getCustomerBusinessInfoApi(id: number) {
  return requestClient.get<CustomerBusinessInfoApi.CustomerBusinessInfo>(
    '/aicrm/customer-business-info/get',
    { params: { id } },
  );
}

/**
 * 创建客户经营信息
 */
export function createCustomerBusinessInfoApi(
  data: CustomerBusinessInfoApi.CustomerBusinessInfo,
) {
  return requestClient.post<number>(
    '/aicrm/customer-business-info/create',
    data,
  );
}

/**
 * 更新客户经营信息
 */
export function updateCustomerBusinessInfoApi(
  data: CustomerBusinessInfoApi.CustomerBusinessInfo,
) {
  return requestClient.put<boolean>(
    '/aicrm/customer-business-info/update',
    data,
  );
}

/**
 * 删除客户经营信息
 */
export function deleteCustomerBusinessInfoApi(id: number) {
  return requestClient.delete<boolean>('/aicrm/customer-business-info/delete', {
    params: { id },
  });
}
