import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountDepositApi {
  /** 客户存款账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountDeposit {
    id: number; // 存款账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 账号
    productName?: string; // 产品名称
    accountName?: string; // 户名
    openDate?: string | Dayjs; // 开户日期
    closeDate: string | Dayjs; // 销户日期
    accountStatus?: string; // 账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）
    interestRate: number; // 利率（%）
    depositTerm: string; // 存期（如：1年、6个月、3个月、活期）
    balance?: number; // 余额
    agrNo: string; // 协议号
    productId: string; // 产品ID
    cardNo: string; // 卡号
    depositType: string; // 存款类型（1=活期，2=定期）
    currencyType: string; // 币种（字典: aicrm_currency_type）
    originalAmount: number; // 原始金额（开户金额）
    matureDate: string | Dayjs; // 到期日
    startInterestDate: string | Dayjs; // 起息日
    deptId: number; // 开户机构ID（关联 system_dept.id）
    deptName: string; // 开户机构名称
    managerUserId: number; // 客户经理用户ID（关联 system_users.id）
    monthAvgBalance: number; // 月日均余额
    quarterAvgBalance: number; // 季日均余额
    yearAvgBalance: number; // 年日均余额
    monthTotalIn: number; // 月累计转入
    monthTotalOut: number; // 月累计转出
    remark: string; // 备注
  }
}

/** 查询客户存款账户信息表（零售+对公共用）分页 */
export function getCustomerAccountDepositPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountDepositApi.CustomerAccountDeposit>>(
    '/aicrm/customer-account-deposit/page',
    { params },
  );
}

/** 查询客户存款账户信息表（零售+对公共用）详情 */
export function getCustomerAccountDeposit(id: number) {
  return requestClient.get<AicrmCustomerAccountDepositApi.CustomerAccountDeposit>(
    `/aicrm/customer-account-deposit/get?id=${id}`,
  );
}

/** 新增客户存款账户信息表（零售+对公共用） */
export function createCustomerAccountDeposit(data: AicrmCustomerAccountDepositApi.CustomerAccountDeposit) {
  return requestClient.post('/aicrm/customer-account-deposit/create', data);
}

/** 修改客户存款账户信息表（零售+对公共用） */
export function updateCustomerAccountDeposit(data: AicrmCustomerAccountDepositApi.CustomerAccountDeposit) {
  return requestClient.put('/aicrm/customer-account-deposit/update', data);
}

/** 删除客户存款账户信息表（零售+对公共用） */
export function deleteCustomerAccountDeposit(id: number) {
  return requestClient.delete(`/aicrm/customer-account-deposit/delete?id=${id}`);
}

/** 批量删除客户存款账户信息表（零售+对公共用） */
export function deleteCustomerAccountDepositList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-deposit/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户存款账户信息表（零售+对公共用） */
export function exportCustomerAccountDeposit(params: any) {
  return requestClient.download('/aicrm/customer-account-deposit/export-excel', { params });
}