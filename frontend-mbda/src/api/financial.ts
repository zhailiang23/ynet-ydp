/**
 * 金融产品 API
 */
import request from '@/utils/request'
import type { FinancialProduct, ProductPageParams, PageResult, CarouselItem } from '@/types/product'

/**
 * 获取金融产品分页列表（移动端）
 */
export function getFinancialProductPage(params: ProductPageParams): Promise<PageResult<FinancialProduct>> {
  return request({
    url: '/app-api/aicrm/financial-product/page',
    method: 'get',
    params,
  })
}

/**
 * 获取金融产品详情（移动端）
 */
export function getFinancialProduct(id: number): Promise<FinancialProduct> {
  return request({
    url: '/app-api/aicrm/financial-product/get',
    method: 'get',
    params: { id },
  })
}

/**
 * 搜索金融产品（移动端）
 */
export function searchFinancialProduct(params: {
  keyword: string
  pageNo?: number
  pageSize?: number
}): Promise<PageResult<FinancialProduct>> {
  return request({
    url: '/app-api/aicrm/financial-product/search',
    method: 'get',
    params,
  })
}

/**
 * 获取轮播推荐列表（移动端）
 */
export function getCarouselList(): Promise<CarouselItem[]> {
  return request({
    url: '/app-api/aicrm/financial-product/carousel/list',
    method: 'get',
  })
}
