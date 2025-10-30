import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerProductRecommendationApi {
  /** 客户产品推荐信息 */
  export interface CustomerProductRecommendation {
    id: number; // 推荐主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    sequenceNo: number; // 序号
    productCode?: string; // 产品编号
    productName?: string; // 产品名称
    productCategoryId?: number; // 产品类目ID（关联 crm_product_category 表）
    productType?: string; // 产品类型（字典: aicrm_product_type）
    recommendationNo?: string; // 推荐编号
    recommendationType?: string; // 推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）
    recommendationSource?: string; // 推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）
    recommendationReason: string; // 推荐理由
    recommendationScore: number; // 推荐评分（0-100分）
    recommendationDate?: string | Dayjs; // 推荐日期
    recommendationTime?: string | Dayjs; // 推荐时间
    validStartDate?: string | Dayjs; // 有效开始日期
    validEndDate?: string | Dayjs; // 有效结束日期
    isHotSale?: boolean; // 是否热销产品
    isMatchCustomer?: boolean; // 是否匹配客户
    matchDegree: string; // 匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）
    recommendationStatus?: string; // 推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）
    pushTime: string | Dayjs; // 推送时间
    pushChannel: string; // 推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）
    viewTime: string | Dayjs; // 查看时间
    viewCount?: number; // 查看次数
    clickTime: string | Dayjs; // 点击时间
    clickCount?: number; // 点击次数
    applyTime: string | Dayjs; // 申请时间
    purchaseTime: string | Dayjs; // 购买时间
    purchaseAmount: number; // 购买金额
    rejectTime: string | Dayjs; // 拒绝时间
    rejectReason: string; // 拒绝原因
    recommenderUserId: number; // 推荐人ID
    recommenderUserName: string; // 推荐人姓名
    recommenderDeptId: number; // 推荐部门ID
    recommenderDeptName: string; // 推荐部门名称
    interestRate: number; // 利率/收益率（%）
    termDays: number; // 期限（天）
    minAmount: number; // 起购金额
    maxAmount: number; // 最大金额
    riskLevel: string; // 风险等级（字典: aicrm_risk_level）
    productFeatures: string; // 产品特点
    productAdvantage: string; // 产品优势
    marketingTheme: string; // 营销主题
    marketingContent: string; // 营销内容
    promotionType: string; // 促销类型（字典: aicrm_promotion_type）
    promotionContent: string; // 促销内容
    conversionRate: number; // 转化率（%）
    isEffective?: boolean; // 是否有效
    effectivenessScore: number; // 有效性评分（0-100分）
    feedbackContent: string; // 反馈内容
    feedbackTime: string | Dayjs; // 反馈时间
    priority?: number; // 优先级（数字越大优先级越高）
    displayOrder?: number; // 显示顺序
    remark: string; // 备注
  }
}

/** 查询客户产品推荐分页 */
export function getCustomerProductRecommendationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerProductRecommendationApi.CustomerProductRecommendation>>(
    '/aicrm/customer-product-recommendation/page',
    { params },
  );
}

/** 查询客户产品推荐详情 */
export function getCustomerProductRecommendation(id: number) {
  return requestClient.get<AicrmCustomerProductRecommendationApi.CustomerProductRecommendation>(
    `/aicrm/customer-product-recommendation/get?id=${id}`,
  );
}

/** 新增客户产品推荐 */
export function createCustomerProductRecommendation(data: AicrmCustomerProductRecommendationApi.CustomerProductRecommendation) {
  return requestClient.post('/aicrm/customer-product-recommendation/create', data);
}

/** 修改客户产品推荐 */
export function updateCustomerProductRecommendation(data: AicrmCustomerProductRecommendationApi.CustomerProductRecommendation) {
  return requestClient.put('/aicrm/customer-product-recommendation/update', data);
}

/** 删除客户产品推荐 */
export function deleteCustomerProductRecommendation(id: number) {
  return requestClient.delete(`/aicrm/customer-product-recommendation/delete?id=${id}`);
}

/** 批量删除客户产品推荐 */
export function deleteCustomerProductRecommendationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-product-recommendation/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户产品推荐 */
export function exportCustomerProductRecommendation(params: any) {
  return requestClient.download('/aicrm/customer-product-recommendation/export-excel', { params });
}