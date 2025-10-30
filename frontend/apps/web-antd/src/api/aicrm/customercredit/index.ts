import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerCreditApi {
  /** 客户授信信息表（零售+对公共用）信息 */
  export interface CustomerCredit {
    id: number; // 授信主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    statisticsDate?: string | Dayjs; // 统计日期
    creditAgreementNo?: string; // 授信协议号
    creditProductType?: string; // 授信品种（字典: aicrm_credit_product_type）
    currencyCode?: string; // 授信币种（字典: aicrm_currency_type）
    creditLimit?: number; // 授信额度（元）
    usedLimit?: number; // 已用额度（元）
    availableLimit?: number; // 可用额度（元）
    usageRatio: number; // 使用比例（%）
    creditStartDate?: string | Dayjs; // 授信起始日
    creditEndDate?: string | Dayjs; // 授信到期日
    creditType: string; // 授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）
    creditStatus?: string; // 授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）
    approveDate: string | Dayjs; // 授信审批日期
    approveAmount: number; // 授信审批金额（元）
    interestRate: number; // 授信利率（%）
    guaranteeType: string; // 担保方式（字典: aicrm_guarantee_type）
    creditPurpose: string; // 授信用途
    approverUserId: number; // 审批人ID（关联 system_users.id）
    approverDeptId: number; // 审批部门ID（关联 system_dept.id）
    managerUserId: number; // 客户经理ID（关联 system_users.id）
    branchId: number; // 授信网点ID（关联 system_dept.id）
    remark: string; // 备注
  }
}

/** 查询客户授信信息表（零售+对公共用）分页 */
export function getCustomerCreditPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerCreditApi.CustomerCredit>>(
    '/aicrm/customer-credit/page',
    { params },
  );
}

/** 查询客户授信信息表（零售+对公共用）详情 */
export function getCustomerCredit(id: number) {
  return requestClient.get<AicrmCustomerCreditApi.CustomerCredit>(
    `/aicrm/customer-credit/get?id=${id}`,
  );
}

/** 新增客户授信信息表（零售+对公共用） */
export function createCustomerCredit(data: AicrmCustomerCreditApi.CustomerCredit) {
  return requestClient.post('/aicrm/customer-credit/create', data);
}

/** 修改客户授信信息表（零售+对公共用） */
export function updateCustomerCredit(data: AicrmCustomerCreditApi.CustomerCredit) {
  return requestClient.put('/aicrm/customer-credit/update', data);
}

/** 删除客户授信信息表（零售+对公共用） */
export function deleteCustomerCredit(id: number) {
  return requestClient.delete(`/aicrm/customer-credit/delete?id=${id}`);
}

/** 批量删除客户授信信息表（零售+对公共用） */
export function deleteCustomerCreditList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-credit/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户授信信息表（零售+对公共用） */
export function exportCustomerCredit(params: any) {
  return requestClient.download('/aicrm/customer-credit/export-excel', { params });
}