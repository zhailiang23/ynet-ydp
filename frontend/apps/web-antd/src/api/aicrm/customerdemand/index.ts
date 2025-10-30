import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerDemandApi {
  /** 客户需求信息信息 */
  export interface CustomerDemand {
    id: number; // 需求主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    sequenceNo: number; // 序号
    demandChannel?: string; // 需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）
    demandContent?: string; // 需求内容
    submitTime?: string | Dayjs; // 提交时间
    isProcessed?: boolean; // 是否处理（0=未处理，1=已处理）
    demandNo?: string; // 需求编号
    demandType?: string; // 需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）
    demandStatus?: string; // 需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）
    demandPriority?: string; // 需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）
    demandSource: string; // 需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）
    handlerUserId: number; // 处理人ID
    handlerUserName: string; // 处理人姓名
    handlerDeptId: number; // 处理部门ID
    handlerDeptName: string; // 处理部门名称
    processStartTime: string | Dayjs; // 开始处理时间
    processEndTime: string | Dayjs; // 处理完成时间
    processDuration: number; // 处理时长（分钟）
    processResult: string; // 处理结果
    customerSatisfaction: number; // 客户满意度（1-5星）
    satisfactionFeedback: string; // 满意度反馈
    followUpRequired?: boolean; // 是否需要跟进
    followUpTime: string | Dayjs; // 跟进时间
    followUpContent: string; // 跟进内容
    relatedProduct: string; // 相关产品
    expectedAmount: number; // 预期金额（元）
    actualAmount: number; // 实际成交金额（元）
    isConverted?: boolean; // 是否已转化
    convertTime: string | Dayjs; // 转化时间
    isClosed?: boolean; // 是否已关闭
    closeTime: string | Dayjs; // 关闭时间
    closeReason: string; // 关闭原因
    remark: string; // 备注
  }
}

/** 查询客户需求信息分页 */
export function getCustomerDemandPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerDemandApi.CustomerDemand>>(
    '/aicrm/customer-demand/page',
    { params },
  );
}

/** 查询客户需求信息详情 */
export function getCustomerDemand(id: number) {
  return requestClient.get<AicrmCustomerDemandApi.CustomerDemand>(
    `/aicrm/customer-demand/get?id=${id}`,
  );
}

/** 新增客户需求信息 */
export function createCustomerDemand(data: AicrmCustomerDemandApi.CustomerDemand) {
  return requestClient.post('/aicrm/customer-demand/create', data);
}

/** 修改客户需求信息 */
export function updateCustomerDemand(data: AicrmCustomerDemandApi.CustomerDemand) {
  return requestClient.put('/aicrm/customer-demand/update', data);
}

/** 删除客户需求信息 */
export function deleteCustomerDemand(id: number) {
  return requestClient.delete(`/aicrm/customer-demand/delete?id=${id}`);
}

/** 批量删除客户需求信息 */
export function deleteCustomerDemandList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-demand/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户需求信息 */
export function exportCustomerDemand(params: any) {
  return requestClient.download('/aicrm/customer-demand/export-excel', { params });
}