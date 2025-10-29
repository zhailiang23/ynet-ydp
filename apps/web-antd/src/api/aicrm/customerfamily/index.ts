import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerFamilyApi {
  /** 客户家庭信息表（零售客户）信息 */
  export interface CustomerFamily {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联零售客户，唯一）
    familyMemberCount: number; // 家庭人口数
    supportMemberCount: number; // 供养人口数
    laborMemberCount: number; // 劳动力人口数
    childrenCount: number; // 子女数量
    familyAnnualIncome: number; // 家庭年收入（万元）
    familyAnnualIncomeScope: string; // 家庭年收入范围（字典：aicrm_family_income_scope）
    familyAnnualExpenditure: number; // 家庭年支出（万元）
    familyAnnualExpenditureScope: string; // 家庭年支出范围（字典：aicrm_family_expenditure_scope）
    familyDebt: number; // 家庭负债（万元）
    familyTotalAssets: number; // 家庭总资产（万元）
    familyAssetsInfo: string; // 家庭资产信息
    mainIncomeSource: string; // 主要收入来源（字典：aicrm_income_source）
    residenceStatus: string; // 居住状况（字典：aicrm_residence_status）
    houseStatus: string; // 住房状况（字典：aicrm_house_status）
    hasHomeCar: boolean; // 是否有房有车（0-否，1-是）
    isHouseHolder: boolean; // 是否户主（0-否，1-是）
    houseHolderName: string; // 户主姓名
    residenceLocation: string; // 所居住宅多(值)，即居住地点描述
    familyAddress: string; // 家庭地址
    homeTel: string; // 家庭电话
    isHarmony: boolean; // 家庭是否和睦（0-否，1-是）
    isCreditFamily: boolean; // 是否信用家庭（0-否，1-是）
    creditStatus: string; // 信用状况（字典：aicrm_credit_status）
    creditAmount: number; // 授信额度
    familyDebtScope: string; // 家庭负债范围（字典：aicrm_debt_scope）
    debtStatus: string; // 负债状况（字典：aicrm_debt_status）
    familyAdverseRecords: string; // 家庭不良记录
    businessAndScale: string; // 经营及规模
    familyStrength: string; // 家庭实力（字典：aicrm_family_strength）
    remark: string; // 备注
  }
}

/** 查询客户家庭信息表（零售客户）分页 */
export function getCustomerFamilyPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerFamilyApi.CustomerFamily>>(
    '/aicrm/customer-family/page',
    { params },
  );
}

/** 查询客户家庭信息表（零售客户）详情 */
export function getCustomerFamily(id: number) {
  return requestClient.get<AicrmCustomerFamilyApi.CustomerFamily>(
    `/aicrm/customer-family/get?id=${id}`,
  );
}

/** 新增客户家庭信息表（零售客户） */
export function createCustomerFamily(data: AicrmCustomerFamilyApi.CustomerFamily) {
  return requestClient.post('/aicrm/customer-family/create', data);
}

/** 修改客户家庭信息表（零售客户） */
export function updateCustomerFamily(data: AicrmCustomerFamilyApi.CustomerFamily) {
  return requestClient.put('/aicrm/customer-family/update', data);
}

/** 删除客户家庭信息表（零售客户） */
export function deleteCustomerFamily(id: number) {
  return requestClient.delete(`/aicrm/customer-family/delete?id=${id}`);
}

/** 批量删除客户家庭信息表（零售客户） */
export function deleteCustomerFamilyList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-family/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户家庭信息表（零售客户） */
export function exportCustomerFamily(params: any) {
  return requestClient.download('/aicrm/customer-family/export-excel', { params });
}