import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountInsuranceApi {
  /** 客户保险账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountInsurance {
    id: number; // 保险账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 保单号
    policyNo: string; // 保险单号
    productName?: string; // 保险产品名称
    accountName?: string; // 投保人姓名
    openDate?: string | Dayjs; // 投保日期（开户日期）
    closeDate: string | Dayjs; // 退保日期（销户日期）
    accountStatus?: string; // 账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）
    insuranceType: string; // 保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）
    insuranceCompany: string; // 保险公司
    insuranceTerm: string; // 保险期限（如：终身、20年、至70岁）
    paymentTerm: string; // 缴费期限（如：趸交、5年、10年、20年）
    paymentFrequency: string; // 缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）
    insuredAmount?: number; // 保险金额（保额）
    premium?: number; // 保费（年交保费）
    paidPremium: number; // 已交保费
    cashValue: number; // 现金价值
    balance?: number; // 账户价值（万能险、投连险）
    currencyType: string; // 币种
    insuredName: string; // 被保险人姓名
    insuredRelation: string; // 与投保人关系（字典: aicrm_insured_relation）
    beneficiaryName: string; // 受益人姓名
    effectiveDate: string | Dayjs; // 生效日期
    expireDate: string | Dayjs; // 到期日期
    nextPaymentDate: string | Dayjs; // 下次缴费日期
    deptId: number; // 销售机构ID
    deptName: string; // 销售机构名称
    managerUserId: number; // 保险顾问用户ID
    remark: string; // 备注
  }
}

/** 查询客户保险账户信息表（零售+对公共用）分页 */
export function getCustomerAccountInsurancePage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance>>(
    '/aicrm/customer-account-insurance/page',
    { params },
  );
}

/** 查询客户保险账户信息表（零售+对公共用）详情 */
export function getCustomerAccountInsurance(id: number) {
  return requestClient.get<AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance>(
    `/aicrm/customer-account-insurance/get?id=${id}`,
  );
}

/** 新增客户保险账户信息表（零售+对公共用） */
export function createCustomerAccountInsurance(data: AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance) {
  return requestClient.post('/aicrm/customer-account-insurance/create', data);
}

/** 修改客户保险账户信息表（零售+对公共用） */
export function updateCustomerAccountInsurance(data: AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance) {
  return requestClient.put('/aicrm/customer-account-insurance/update', data);
}

/** 删除客户保险账户信息表（零售+对公共用） */
export function deleteCustomerAccountInsurance(id: number) {
  return requestClient.delete(`/aicrm/customer-account-insurance/delete?id=${id}`);
}

/** 批量删除客户保险账户信息表（零售+对公共用） */
export function deleteCustomerAccountInsuranceList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-insurance/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户保险账户信息表（零售+对公共用） */
export function exportCustomerAccountInsurance(params: any) {
  return requestClient.download('/aicrm/customer-account-insurance/export-excel', { params });
}