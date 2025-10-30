import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerRatingApi {
  /** 客户评级信息表（与客户主表1对1关系）信息 */
  export interface CustomerRating {
    id: number; // 评级主键
    customerId?: number; // 客户ID（关联 crm_customer 主表，1对1关系）
    sequenceNo: number; // 序号
    approvalStatus: string; // 审批状态（字典: aicrm_rating_approval_status）
    ratingDate?: string | Dayjs; // 评级日期
    valueLevel: string; // 价值等级（字典: aicrm_value_level）
    serviceLevel: string; // 服务等级（字典: aicrm_service_level）
    serviceLevelBeforeRisk: string; // 剔除风险前服务等级（字典: aicrm_service_level）
    riskFactors: string; // 风险影响因子内容
    ratingMethod: string; // 评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）
    custGradeId: string; // 评级ID（老系统）
    effectiveDate: string | Dayjs; // 生效日期
    expiredDate: string | Dayjs; // 失效日期
    evaluateDate: string | Dayjs; // 评估日期
    custGrade: string; // 客户等级（老系统字段）
    custGradeType: string; // 客户等级类型
    orgCode: string; // 机构编码
    orgName: string; // 机构名称
    custCnt: number; // 客户数量
    ratingScore: number; // 评级分数
    ratingModelVersion: string; // 评级模型版本
    riskLevel: string; // 风险等级（字典: aicrm_risk_level）
    nextRatingDate: string | Dayjs; // 下次评级日期
    remark: string; // 备注
  }
}

/** 查询客户评级信息表（与客户主表1对1关系）分页 */
export function getCustomerRatingPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerRatingApi.CustomerRating>>(
    '/aicrm/customer-rating/page',
    { params },
  );
}

/** 查询客户评级信息表（与客户主表1对1关系）详情 */
export function getCustomerRating(id: number) {
  return requestClient.get<AicrmCustomerRatingApi.CustomerRating>(
    `/aicrm/customer-rating/get?id=${id}`,
  );
}

/** 新增客户评级信息表（与客户主表1对1关系） */
export function createCustomerRating(data: AicrmCustomerRatingApi.CustomerRating) {
  return requestClient.post('/aicrm/customer-rating/create', data);
}

/** 修改客户评级信息表（与客户主表1对1关系） */
export function updateCustomerRating(data: AicrmCustomerRatingApi.CustomerRating) {
  return requestClient.put('/aicrm/customer-rating/update', data);
}

/** 删除客户评级信息表（与客户主表1对1关系） */
export function deleteCustomerRating(id: number) {
  return requestClient.delete(`/aicrm/customer-rating/delete?id=${id}`);
}

/** 批量删除客户评级信息表（与客户主表1对1关系） */
export function deleteCustomerRatingList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-rating/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户评级信息表（与客户主表1对1关系） */
export function exportCustomerRating(params: any) {
  return requestClient.download('/aicrm/customer-rating/export-excel', { params });
}