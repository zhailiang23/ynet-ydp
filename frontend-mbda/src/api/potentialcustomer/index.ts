import request from '@/api/request'
import type { PotentialCustomer } from '@/types/potentialCustomer'

/**
 * 获取潜客列表（分页）
 */
export function getPotentialCustomerPage(params: {
  insightId?: number
  status?: string
  pageNo?: number
  pageSize?: number
}): Promise<{
  list: PotentialCustomer[]
  total: number
}> {
  return request({
    url: '/app-api/aicrm/potential-customer/page',
    method: 'get',
    params,
  })
}

/**
 * 获取潜客详情
 */
export function getPotentialCustomer(id: number): Promise<PotentialCustomer> {
  return request({
    url: `/app-api/aicrm/potential-customer/get`,
    method: 'get',
    params: { id },
  })
}
