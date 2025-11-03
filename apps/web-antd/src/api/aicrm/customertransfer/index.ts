import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerTransferApi {
  /** 客户移交申请信息 */
  export interface CustomerTransferApplication {
    id: number; // 申请ID
    customerId: number; // 客户ID
    applicantUserId: number; // 申请人用户ID
    applicantDeptId: number; // 申请人部门ID
    toUserId: number; // 接收方客户经理ID
    toDeptId: number; // 接收方部门ID
    applyDate: string | Dayjs; // 申请日期
    transferReason: string; // 移交原因
    processInstanceId?: string; // 流程实例ID
    processStatus: number; // 审批状态（1=审批中，2=已通过，3=已拒绝，4=已取消）
    approverComment?: string; // 审批意见
    approveTime?: string | Dayjs; // 审批时间
    createTime?: string | Dayjs; // 创建时间
    updateTime?: string | Dayjs; // 更新时间
  }

  /** 提交客户移交申请请求 */
  export interface CustomerTransferApplicationApplyReq {
    customerId: number; // 客户ID
    toUserId: number; // 接收方客户经理ID
    transferReason: string; // 移交原因
    startUserSelectAssignees?: Record<string, number>; // BPM 用户选择
  }

  /** 客户移交申请分页查询请求 */
  export interface CustomerTransferApplicationPageReq extends PageParam {
    customerId?: number; // 客户ID
    applicantUserId?: number; // 申请人用户ID
    toUserId?: number; // 接收方用户ID
    processStatus?: number; // 审批状态
    applyDate?: string | Dayjs; // 申请日期（起）
    applyDateEnd?: string | Dayjs; // 申请日期（止）
  }
}

/** 查询客户移交申请分页 */
export function getCustomerTransferApplicationPage(
  params: AicrmCustomerTransferApi.CustomerTransferApplicationPageReq,
) {
  return requestClient.get<PageResult<AicrmCustomerTransferApi.CustomerTransferApplication>>(
    '/aicrm/customer-transfer-application/page',
    { params },
  );
}

/** 查询客户移交申请详情 */
export function getCustomerTransferApplication(id: number) {
  return requestClient.get<AicrmCustomerTransferApi.CustomerTransferApplication>(
    `/aicrm/customer-transfer-application/get?id=${id}`,
  );
}

/** 提交客户移交申请 */
export function applyForTransfer(data: AicrmCustomerTransferApi.CustomerTransferApplicationApplyReq) {
  return requestClient.post<number>('/aicrm/customer-transfer-application/apply', data);
}

/** 取消客户移交申请 */
export function cancelTransferApplication(id: number) {
  return requestClient.delete<boolean>(`/aicrm/customer-transfer-application/cancel?id=${id}`);
}
