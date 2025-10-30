import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerComplaintApi {
  /** 客户投诉信息信息 */
  export interface CustomerComplaint {
    id: number; // 投诉主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    sequenceNo: number; // 序号
    workOrderNo?: string; // 工单编号
    ecifCustomerNo: string; // ECIF客户号
    customerName?: string; // 客户姓名
    workOrderStatus?: string; // 工单处理状态（字典: aicrm_complaint_status）
    lastProcessTime: string | Dayjs; // 最近处理时间
    complaintChannel?: string; // 投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）
    complaintType?: string; // 投诉类型（字典: aicrm_complaint_type）
    complaintContent?: string; // 投诉内容
    complaintTime?: string | Dayjs; // 投诉时间
    complaintLevel?: string; // 投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）
    handlerUserId: number; // 处理人ID
    handlerUserName: string; // 处理人姓名
    handlerDeptId: number; // 处理部门ID
    handlerDeptName: string; // 处理部门名称
    processResult: string; // 处理结果
    processStartTime: string | Dayjs; // 开始处理时间
    processEndTime: string | Dayjs; // 处理完成时间
    processDuration: number; // 处理时长（分钟）
    customerSatisfaction: number; // 客户满意度（1-5星）
    satisfactionFeedback: string; // 满意度反馈
    isClosed?: boolean; // 是否已关闭
    closeTime: string | Dayjs; // 关闭时间
    isReopened?: boolean; // 是否重新打开
    reopenTime: string | Dayjs; // 重新打开时间
    reopenReason: string; // 重新打开原因
    remark: string; // 备注
  }
}

/** 查询客户投诉信息分页 */
export function getCustomerComplaintPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerComplaintApi.CustomerComplaint>>(
    '/aicrm/customer-complaint/page',
    { params },
  );
}

/** 查询客户投诉信息详情 */
export function getCustomerComplaint(id: number) {
  return requestClient.get<AicrmCustomerComplaintApi.CustomerComplaint>(
    `/aicrm/customer-complaint/get?id=${id}`,
  );
}

/** 新增客户投诉信息 */
export function createCustomerComplaint(data: AicrmCustomerComplaintApi.CustomerComplaint) {
  return requestClient.post('/aicrm/customer-complaint/create', data);
}

/** 修改客户投诉信息 */
export function updateCustomerComplaint(data: AicrmCustomerComplaintApi.CustomerComplaint) {
  return requestClient.put('/aicrm/customer-complaint/update', data);
}

/** 删除客户投诉信息 */
export function deleteCustomerComplaint(id: number) {
  return requestClient.delete(`/aicrm/customer-complaint/delete?id=${id}`);
}

/** 批量删除客户投诉信息 */
export function deleteCustomerComplaintList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-complaint/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户投诉信息 */
export function exportCustomerComplaint(params: any) {
  return requestClient.download('/aicrm/customer-complaint/export-excel', { params });
}