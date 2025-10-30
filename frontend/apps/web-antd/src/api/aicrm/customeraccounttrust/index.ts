import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountTrustApi {
  /** 客户信托账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountTrust {
    id: number; // 信托账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 信托账号
    trustContractNo: string; // 信托合同号
    productName?: string; // 信托产品名称
    accountName?: string; // 委托人姓名
    openDate?: string | Dayjs; // 成立日期（开户日期）
    closeDate: string | Dayjs; // 终止日期（销户日期）
    accountStatus?: string; // 账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）
    trustType: string; // 信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）
    trustCompany: string; // 信托公司
    trustPurpose: string; // 信托目的
    expectedReturnRate: number; // 预期收益率（年化%）
    trustTerm: string; // 信托期限（如：2年、3年、5年）
    currencyType: string; // 币种
    trustAmount?: number; // 信托金额
    paidAmount: number; // 已支付金额
    currentValue?: number; // 当前价值
    accumulatedIncome: number; // 累计收益
    balance?: number; // 账户余额
    beneficiaryName: string; // 受益人姓名
    beneficiaryRelation: string; // 与委托人关系
    effectiveDate: string | Dayjs; // 生效日期
    matureDate: string | Dayjs; // 到期日
    nextDistributionDate: string | Dayjs; // 下次分配日
    deptId: number; // 销售机构ID
    deptName: string; // 销售机构名称
    managerUserId: number; // 信托顾问用户ID
    remark: string; // 备注
  }
}

/** 查询客户信托账户信息表（零售+对公共用）分页 */
export function getCustomerAccountTrustPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountTrustApi.CustomerAccountTrust>>(
    '/aicrm/customer-account-trust/page',
    { params },
  );
}

/** 查询客户信托账户信息表（零售+对公共用）详情 */
export function getCustomerAccountTrust(id: number) {
  return requestClient.get<AicrmCustomerAccountTrustApi.CustomerAccountTrust>(
    `/aicrm/customer-account-trust/get?id=${id}`,
  );
}

/** 新增客户信托账户信息表（零售+对公共用） */
export function createCustomerAccountTrust(data: AicrmCustomerAccountTrustApi.CustomerAccountTrust) {
  return requestClient.post('/aicrm/customer-account-trust/create', data);
}

/** 修改客户信托账户信息表（零售+对公共用） */
export function updateCustomerAccountTrust(data: AicrmCustomerAccountTrustApi.CustomerAccountTrust) {
  return requestClient.put('/aicrm/customer-account-trust/update', data);
}

/** 删除客户信托账户信息表（零售+对公共用） */
export function deleteCustomerAccountTrust(id: number) {
  return requestClient.delete(`/aicrm/customer-account-trust/delete?id=${id}`);
}

/** 批量删除客户信托账户信息表（零售+对公共用） */
export function deleteCustomerAccountTrustList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-trust/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户信托账户信息表（零售+对公共用） */
export function exportCustomerAccountTrust(params: any) {
  return requestClient.download('/aicrm/customer-account-trust/export-excel', { params });
}