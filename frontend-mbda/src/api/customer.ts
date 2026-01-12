/**
 * 客户 API
 */
import request from '@/utils/request'
import type { Customer, CustomerPageParams, PageResult } from '@/types/customer'

/**
 * 获取客户分页列表（移动端）
 */
export function getCustomerPage(params: CustomerPageParams): Promise<PageResult<Customer>> {
  return request({
    url: '/admin-api/aicrm/customer/page',
    method: 'get',
    params,
  })
}

/**
 * 获取客户详情（移动端）
 */
export function getCustomer(id: number): Promise<Customer> {
  return request({
    url: '/admin-api/aicrm/customer/get',
    method: 'get',
    params: { id },
  })
}

/**
 * 客户识别查询（通过手机号或证件号查询客户）
 */
export interface CustomerSearchParams {
  mobile?: string
  idCardNo?: string
}

export function searchCustomer(params: CustomerSearchParams): Promise<Customer | null> {
  return request({
    url: '/admin-api/aicrm/customer/search',
    method: 'get',
    params,
  })
}
