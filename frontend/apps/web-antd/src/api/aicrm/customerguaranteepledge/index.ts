import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGuaranteePledgeApi {
  /** 客户质押物信息表（零售+对公共用）信息 */
  export interface CustomerGuaranteePledge {
    id: number; // 质押物主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    creditId: number; // 授信ID（关联 crm_customer_credit.id）
    collateralNo?: string; // 押品编号
    collateralType?: string; // 押品类型（字典: aicrm_pledge_type）
    guaranteeType?: string; // 担保类型（字典: aicrm_guarantee_method，pledge=质押）
    pledgorIdType: string; // 质押人证件类型（字典: aicrm_identity_type）
    pledgorIdNo: string; // 质押人证件号码（加密）
    pledgorName?: string; // 质押人姓名/名称
    pledgorType?: string; // 质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
    relationWithBorrower: string; // 与被担保人关系（字典: aicrm_relation_type）
    guaranteeAmount?: number; // 担保本金限权金额（万元）
    pledgeRate: number; // 质押率（%）
    collateralName: string; // 质押物名称
    collateralValue: number; // 质押物价值（万元）
    pledgeStatus?: string; // 质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）
    pledgeDate: string | Dayjs; // 质押登记日期
    releaseDate: string | Dayjs; // 解押日期
    managementBranchId: number; // 管理机构ID（关联 system_dept.id）
    managementBranchName: string; // 管理机构名称
    remark: string; // 备注
  }
}

/** 查询客户质押物信息表（零售+对公共用）分页 */
export function getCustomerGuaranteePledgePage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge>>(
    '/aicrm/customer-guarantee-pledge/page',
    { params },
  );
}

/** 查询客户质押物信息表（零售+对公共用）详情 */
export function getCustomerGuaranteePledge(id: number) {
  return requestClient.get<AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge>(
    `/aicrm/customer-guarantee-pledge/get?id=${id}`,
  );
}

/** 新增客户质押物信息表（零售+对公共用） */
export function createCustomerGuaranteePledge(data: AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge) {
  return requestClient.post('/aicrm/customer-guarantee-pledge/create', data);
}

/** 修改客户质押物信息表（零售+对公共用） */
export function updateCustomerGuaranteePledge(data: AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge) {
  return requestClient.put('/aicrm/customer-guarantee-pledge/update', data);
}

/** 删除客户质押物信息表（零售+对公共用） */
export function deleteCustomerGuaranteePledge(id: number) {
  return requestClient.delete(`/aicrm/customer-guarantee-pledge/delete?id=${id}`);
}

/** 批量删除客户质押物信息表（零售+对公共用） */
export function deleteCustomerGuaranteePledgeList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-guarantee-pledge/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户质押物信息表（零售+对公共用） */
export function exportCustomerGuaranteePledge(params: any) {
  return requestClient.download('/aicrm/customer-guarantee-pledge/export-excel', { params });
}