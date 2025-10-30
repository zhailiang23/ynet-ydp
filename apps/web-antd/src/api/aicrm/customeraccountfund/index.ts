import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountFundApi {
  /** 客户基金账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountFund {
    id: number; // 基金账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 基金账号
    fundCode: string; // 基金代码
    productName?: string; // 基金名称
    accountName?: string; // 户名
    openDate?: string | Dayjs; // 开户日期（首次申购日期）
    closeDate: string | Dayjs; // 销户日期（全部赎回日期）
    accountStatus?: string; // 账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）
    fundType: string; // 基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）
    fundCompany: string; // 基金公司
    riskLevel: string; // 风险等级（字典: aicrm_risk_level）
    currencyType: string; // 币种
    holdingShares?: number; // 持有份额
    purchaseAmount?: number; // 累计申购金额
    currentNav: number; // 当前净值
    currentValue?: number; // 当前市值
    accumulatedIncome: number; // 累计收益
    balance?: number; // 账户余额（现金部分）
    costPrice: number; // 成本价
    profitRate: number; // 收益率（%）
    todayIncome: number; // 今日收益
    yesterdayIncome: number; // 昨日收益
    deptId: number; // 销售机构ID
    deptName: string; // 销售机构名称
    managerUserId: number; // 基金顾问用户ID
    remark: string; // 备注
  }
}

/** 查询客户基金账户信息表（零售+对公共用）分页 */
export function getCustomerAccountFundPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountFundApi.CustomerAccountFund>>(
    '/aicrm/customer-account-fund/page',
    { params },
  );
}

/** 查询客户基金账户信息表（零售+对公共用）详情 */
export function getCustomerAccountFund(id: number) {
  return requestClient.get<AicrmCustomerAccountFundApi.CustomerAccountFund>(
    `/aicrm/customer-account-fund/get?id=${id}`,
  );
}

/** 新增客户基金账户信息表（零售+对公共用） */
export function createCustomerAccountFund(data: AicrmCustomerAccountFundApi.CustomerAccountFund) {
  return requestClient.post('/aicrm/customer-account-fund/create', data);
}

/** 修改客户基金账户信息表（零售+对公共用） */
export function updateCustomerAccountFund(data: AicrmCustomerAccountFundApi.CustomerAccountFund) {
  return requestClient.put('/aicrm/customer-account-fund/update', data);
}

/** 删除客户基金账户信息表（零售+对公共用） */
export function deleteCustomerAccountFund(id: number) {
  return requestClient.delete(`/aicrm/customer-account-fund/delete?id=${id}`);
}

/** 批量删除客户基金账户信息表（零售+对公共用） */
export function deleteCustomerAccountFundList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-fund/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户基金账户信息表（零售+对公共用） */
export function exportCustomerAccountFund(params: any) {
  return requestClient.download('/aicrm/customer-account-fund/export-excel', { params });
}