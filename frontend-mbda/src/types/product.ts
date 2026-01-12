/**
 * 金融产品相关类型定义
 */

// 金融产品信息
export interface FinancialProduct {
  id?: number
  productCode: string
  productName: string
  catalogId: number
  category: string
  riskLevel: string
  expectedReturn?: number
  returnType?: string
  duration?: string
  durationDays?: number
  minimumInvestment: number
  investmentUnit?: string
  status: number
  isHot?: number
  isNew?: number
  description?: string
  features?: string
  saleChannel?: string
  tags?: string[]
  aiMatchScore?: number
  aiKeywords?: string
  sort?: number
  bannerImage?: string
  createTime?: string
}

// 分页查询参数
export interface ProductPageParams {
  pageNo?: number
  pageSize?: number
  keyword?: string
  category?: string
  tags?: string[]
  riskLevel?: string
  sortBy?: 'hot' | 'return' | 'duration' | 'investment'
  status?: number
}

// 分页响应
export interface PageResult<T> {
  list: T[]
  total: number
}

// 轮播推荐信息
export interface CarouselItem {
  id: number
  title: string
  subtitle?: string
  imageUrl: string
  linkType: 'product' | 'url'
  linkId?: number
  linkUrl?: string
  badgeText?: string
  badgeColor?: string
  sort: number
  status: number
}
