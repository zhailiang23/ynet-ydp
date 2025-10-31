import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerClaimApi {
  /** 客户认领申请信息 */
  export interface CustomerClaimApplication {
    id: number; // 申请ID
    customerId: number; // 客户ID
    applyUserId: number; // 申请人ID
    applyDate: string | Dayjs; // 申请日期
    applyReason: string; // 申请理由
    processInstanceId?: string; // 流程实例ID
    processStatus: number; // 审批状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
    approverComment?: string; // 审批意见
    approveTime?: string | Dayjs; // 审批时间
    createTime?: string | Dayjs; // 创建时间
    updateTime?: string | Dayjs; // 更新时间
  }

  /** 提交客户认领申请请求 */
  export interface CustomerClaimApplicationApplyReq {
    customerId: number; // 客户ID
    applyReason: string; // 申请理由
    startUserSelectAssignees?: Record<string, number>; // BPM 用户选择
  }

  /** 客户认领申请分页查询请求 */
  export interface CustomerClaimApplicationPageReq extends PageParam {
    customerId?: number; // 客户ID
    applyUserId?: number; // 申请人ID
    processStatus?: number; // 审批状态
    applyDate?: string | Dayjs; // 申请日期（起）
    applyDateEnd?: string | Dayjs; // 申请日期（止）
  }
}

/** 查询客户认领申请分页 */
export function getCustomerClaimApplicationPage(
  params: AicrmCustomerClaimApi.CustomerClaimApplicationPageReq,
) {
  return requestClient.get<PageResult<AicrmCustomerClaimApi.CustomerClaimApplication>>(
    '/aicrm/customer-claim-application/page',
    { params },
  );
}

/** 查询客户认领申请详情 */
export function getCustomerClaimApplication(id: number) {
  return requestClient.get<AicrmCustomerClaimApi.CustomerClaimApplication>(
    `/aicrm/customer-claim-application/get?id=${id}`,
  );
}

/** 提交客户认领申请 */
export function applyForClaim(data: AicrmCustomerClaimApi.CustomerClaimApplicationApplyReq) {
  return requestClient.post<number>('/aicrm/customer-claim-application/apply', data);
}

/** 取消客户认领申请 */
export function cancelClaimApplication(id: number) {
  return requestClient.delete<boolean>(`/aicrm/customer-claim-application/cancel?id=${id}`);
}
