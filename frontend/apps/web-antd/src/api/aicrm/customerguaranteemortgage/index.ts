import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerGuaranteeMortgageApi {
  /** 客户抵押物信息表（零售+对公共用）信息 */
  export interface CustomerGuaranteeMortgage {
    id: number; // 抵押物主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    creditId: number; // 授信ID（关联 crm_customer_credit.id）
    collateralNo?: string; // 押品编号
    collateralName?: string; // 押品名称
    collateralType?: string; // 押品类型（字典: aicrm_mortgage_type）
    certificateNo: string; // 权证号
    guaranteeType?: string; // 担保类型（字典: aicrm_guarantee_method，mortgage=抵押）
    mortgagorName?: string; // 抵押人姓名/名称
    mortgagorType?: string; // 抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
    relationWithBorrower: string; // 与被担保人关系（字典: aicrm_relation_type）
    guaranteeAmount?: number; // 担保本金限权金额（万元）
    managementBranchId: number; // 押品管理机构ID（关联 system_dept.id）
    managementBranchName: string; // 押品管理机构名称
    mortgagorIdType: string; // 抵押人证件类型（字典: aicrm_identity_type）
    mortgagorIdNo: string; // 抵押人证件号码（加密）
    collateralAddress: string; // 抵押物地址
    collateralArea: number; // 抵押物面积（平方米）
    evaluationValue: number; // 评估价值（万元）
    evaluationDate: string | Dayjs; // 评估日期
    evaluationAgency: string; // 评估机构
    mortgageRate: number; // 抵押率（%）
    mortgageStatus?: string; // 抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）
    mortgageDate: string | Dayjs; // 抵押登记日期
    releaseDate: string | Dayjs; // 解押日期
    remark: string; // 备注
  }
}

/** 查询客户抵押物信息表（零售+对公共用）分页 */
export function getCustomerGuaranteeMortgagePage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage>>(
    '/aicrm/customer-guarantee-mortgage/page',
    { params },
  );
}

/** 查询客户抵押物信息表（零售+对公共用）详情 */
export function getCustomerGuaranteeMortgage(id: number) {
  return requestClient.get<AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage>(
    `/aicrm/customer-guarantee-mortgage/get?id=${id}`,
  );
}

/** 新增客户抵押物信息表（零售+对公共用） */
export function createCustomerGuaranteeMortgage(data: AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage) {
  return requestClient.post('/aicrm/customer-guarantee-mortgage/create', data);
}

/** 修改客户抵押物信息表（零售+对公共用） */
export function updateCustomerGuaranteeMortgage(data: AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage) {
  return requestClient.put('/aicrm/customer-guarantee-mortgage/update', data);
}

/** 删除客户抵押物信息表（零售+对公共用） */
export function deleteCustomerGuaranteeMortgage(id: number) {
  return requestClient.delete(`/aicrm/customer-guarantee-mortgage/delete?id=${id}`);
}

/** 批量删除客户抵押物信息表（零售+对公共用） */
export function deleteCustomerGuaranteeMortgageList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-guarantee-mortgage/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户抵押物信息表（零售+对公共用） */
export function exportCustomerGuaranteeMortgage(params: any) {
  return requestClient.download('/aicrm/customer-guarantee-mortgage/export-excel', { params });
}