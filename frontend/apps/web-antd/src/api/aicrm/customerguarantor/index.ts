import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGuarantorApi {
  /** 客户担保人信息表（零售+对公共用）信息 */
  export interface CustomerGuarantor {
    id: number; // 担保人主键
    customerId?: number; // 客户ID（被担保人，关联 crm_customer 主表）
    creditId: number; // 授信ID（关联 crm_customer_credit.id）
    contractNo?: string; // 合同号
    contractType: string; // 合同类型（字典: aicrm_guarantor_contract_type）
    contractStatus?: string; // 合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）
    signDate?: string | Dayjs; // 签约日期
    guaranteeType?: string; // 担保类型（字典: aicrm_guarantee_method，guarantee=保证）
    guarantorNo?: string; // 担保人编号
    guarantorName?: string; // 担保人姓名/名称
    currencyCode?: string; // 币种（字典: aicrm_currency_type）
    guaranteeTotalAmount?: number; // 担保总金额（万元）
    businessStartDate: string | Dayjs; // 业务起始日期
    businessEndDate: string | Dayjs; // 业务截止日期
    guarantorType?: string; // 担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
    guarantorIdType: string; // 担保人证件类型（字典: aicrm_identity_type）
    guarantorIdNo: string; // 担保人证件号码（加密）
    relationWithBorrower: string; // 与被担保人关系（字典: aicrm_relation_type）
    guaranteeMethod: string; // 担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）
    usedAmount: number; // 已用担保金额（万元）
    availableAmount: number; // 可用担保金额（万元）
    managerUserId: number; // 客户经理ID（关联 system_users.id）
    branchId: number; // 管理机构ID（关联 system_dept.id）
    remark: string; // 备注
  }
}

/** 查询客户担保人信息表（零售+对公共用）分页 */
export function getCustomerGuarantorPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGuarantorApi.CustomerGuarantor>>(
    '/aicrm/customer-guarantor/page',
    { params },
  );
}

/** 查询客户担保人信息表（零售+对公共用）详情 */
export function getCustomerGuarantor(id: number) {
  return requestClient.get<AicrmCustomerGuarantorApi.CustomerGuarantor>(
    `/aicrm/customer-guarantor/get?id=${id}`,
  );
}

/** 新增客户担保人信息表（零售+对公共用） */
export function createCustomerGuarantor(data: AicrmCustomerGuarantorApi.CustomerGuarantor) {
  return requestClient.post('/aicrm/customer-guarantor/create', data);
}

/** 修改客户担保人信息表（零售+对公共用） */
export function updateCustomerGuarantor(data: AicrmCustomerGuarantorApi.CustomerGuarantor) {
  return requestClient.put('/aicrm/customer-guarantor/update', data);
}

/** 删除客户担保人信息表（零售+对公共用） */
export function deleteCustomerGuarantor(id: number) {
  return requestClient.delete(`/aicrm/customer-guarantor/delete?id=${id}`);
}

/** 批量删除客户担保人信息表（零售+对公共用） */
export function deleteCustomerGuarantorList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-guarantor/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户担保人信息表（零售+对公共用） */
export function exportCustomerGuarantor(params: any) {
  return requestClient.download('/aicrm/customer-guarantor/export-excel', { params });
}