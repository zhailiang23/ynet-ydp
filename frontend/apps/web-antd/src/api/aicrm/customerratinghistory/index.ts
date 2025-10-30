import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerRatingHistoryApi {
  /** 客户评级调整历史信息 */
  export interface CustomerRatingHistory {
    id: number; // 历史主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    ratingId: number; // 评级ID（关联当前评级表）
    sequenceNo: number; // 序号
    approvalStatus: string; // 审批状态（字典: aicrm_rating_approval_status）
    ratingDate?: string | Dayjs; // 评级日期
    valueLevel: string; // 价值等级（字典: aicrm_value_level）
    serviceLevel: string; // 服务等级（字典: aicrm_service_level）
    serviceLevelBeforeRisk: string; // 剔除风险前服务等级（字典: aicrm_service_level）
    riskFactors: string; // 风险影响因子内容
    ratingMethod: string; // 评级方式（字典: aicrm_rating_method）
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
    changeReason: string; // 调整原因
    changeType: string; // 调整类型（字典: aicrm_rating_change_type，upgrade=升级，downgrade=降级，maintain=维持）
    previousValueLevel: string; // 调整前价值等级
    previousServiceLevel: string; // 调整前服务等级
    remark: string; // 备注
  }
}

/** 查询客户评级调整历史分页 */
export function getCustomerRatingHistoryPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerRatingHistoryApi.CustomerRatingHistory>>(
    '/aicrm/customer-rating-history/page',
    { params },
  );
}

/** 查询客户评级调整历史详情 */
export function getCustomerRatingHistory(id: number) {
  return requestClient.get<AicrmCustomerRatingHistoryApi.CustomerRatingHistory>(
    `/aicrm/customer-rating-history/get?id=${id}`,
  );
}

/** 新增客户评级调整历史 */
export function createCustomerRatingHistory(data: AicrmCustomerRatingHistoryApi.CustomerRatingHistory) {
  return requestClient.post('/aicrm/customer-rating-history/create', data);
}

/** 修改客户评级调整历史 */
export function updateCustomerRatingHistory(data: AicrmCustomerRatingHistoryApi.CustomerRatingHistory) {
  return requestClient.put('/aicrm/customer-rating-history/update', data);
}

/** 删除客户评级调整历史 */
export function deleteCustomerRatingHistory(id: number) {
  return requestClient.delete(`/aicrm/customer-rating-history/delete?id=${id}`);
}

/** 批量删除客户评级调整历史 */
export function deleteCustomerRatingHistoryList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-rating-history/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户评级调整历史 */
export function exportCustomerRatingHistory(params: any) {
  return requestClient.download('/aicrm/customer-rating-history/export-excel', { params });
}