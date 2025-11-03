import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerReturnApi {
  /** 客户退回申请信息 */
  export interface CustomerReturnApplication {
    id: number; // 申请ID
    customerId: number; // 客户ID
    applyUserId: number; // 申请人ID
    applyDate: string | Dayjs; // 申请日期
    returnReason: string; // 退回原因
    processInstanceId?: string; // 流程实例ID
    processStatus: number; // 审批状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
    approverComment?: string; // 审批意见
    approveTime?: string | Dayjs; // 审批时间
    createTime?: string | Dayjs; // 创建时间
    updateTime?: string | Dayjs; // 更新时间
  }

  /** 提交客户退回申请请求 */
  export interface CustomerReturnApplicationApplyReq {
    customerId: number; // 客户ID
    returnToUserId: number; // 退回给主管ID
    returnReason: string; // 退回原因
    startUserSelectAssignees?: Record<string, number>; // BPM 用户选择
  }

  /** 客户退回申请分页查询请求 */
  export interface CustomerReturnApplicationPageReq extends PageParam {
    customerId?: number; // 客户ID
    applyUserId?: number; // 申请人ID
    processStatus?: number; // 审批状态
    applyDate?: string | Dayjs; // 申请日期（起）
    applyDateEnd?: string | Dayjs; // 申请日期（止）
  }
}

/** 查询客户退回申请分页 */
export function getCustomerReturnApplicationPage(
  params: AicrmCustomerReturnApi.CustomerReturnApplicationPageReq,
) {
  return requestClient.get<PageResult<AicrmCustomerReturnApi.CustomerReturnApplication>>(
    '/aicrm/customer-return-application/page',
    { params },
  );
}

/** 查询客户退回申请详情 */
export function getCustomerReturnApplication(id: number) {
  return requestClient.get<AicrmCustomerReturnApi.CustomerReturnApplication>(
    `/aicrm/customer-return-application/get?id=${id}`,
  );
}

/** 提交客户退回申请 */
export function applyForReturn(data: AicrmCustomerReturnApi.CustomerReturnApplicationApplyReq) {
  return requestClient.post<number>('/aicrm/customer-return-application/apply', data);
}

/** 取消客户退回申请 */
export function cancelReturnApplication(id: number) {
  return requestClient.delete<boolean>(`/aicrm/customer-return-application/cancel?id=${id}`);
}
