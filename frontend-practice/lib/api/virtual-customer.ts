import { httpClient } from "./request"
import type { VirtualCustomer, PageParam, PageResult } from "../types/course"

/**
 * 获取虚拟客户分页列表
 */
export async function getVirtualCustomerList(params: PageParam): Promise<PageResult<VirtualCustomer>> {
  const queryString = new URLSearchParams({
    pageNo: params.pageNo.toString(),
    pageSize: params.pageSize.toString(),
  }).toString()

  return httpClient.get<PageResult<VirtualCustomer>>(`/aicrm/practice-virtual-customer/page?${queryString}`)
}

/**
 * 获取虚拟客户详情
 */
export async function getVirtualCustomerById(id: number): Promise<VirtualCustomer> {
  return httpClient.get<VirtualCustomer>(`/aicrm/practice-virtual-customer/get?id=${id}`)
}
