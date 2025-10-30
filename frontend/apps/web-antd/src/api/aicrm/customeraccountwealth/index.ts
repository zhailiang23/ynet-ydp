import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountWealthApi {
  /** 客户理财账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountWealth {
    id: number; // 理财账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 理财账号
    productCode: string; // 理财产品代码
    productName?: string; // 理财产品名称
    accountName?: string; // 户名
    openDate?: string | Dayjs; // 开户日期（购买日期）
    closeDate: string | Dayjs; // 销户日期（赎回/到期日期）
    accountStatus?: string; // 账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）
    productType: string; // 理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）
    riskLevel: string; // 风险等级（字典: aicrm_risk_level，R1-R5）
    expectedReturnRate: number; // 预期收益率（年化%）
    actualReturnRate: number; // 实际收益率（年化%）
    wealthTerm: string; // 理财期限（如：90天、180天、1年）
    currencyType: string; // 币种
    purchaseAmount?: number; // 购买金额
    currentValue?: number; // 当前市值
    accumulatedIncome: number; // 累计收益
    balance?: number; // 持有份额/余额
    valueDate: string | Dayjs; // 起息日
    matureDate: string | Dayjs; // 到期日
    nextOpenDate: string | Dayjs; // 下次开放日（开放式理财）
    deptId: number; // 销售机构ID
    deptName: string; // 销售机构名称
    managerUserId: number; // 理财顾问用户ID
    remark: string; // 备注
  }
}

/** 查询客户理财账户信息表（零售+对公共用）分页 */
export function getCustomerAccountWealthPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountWealthApi.CustomerAccountWealth>>(
    '/aicrm/customer-account-wealth/page',
    { params },
  );
}

/** 查询客户理财账户信息表（零售+对公共用）详情 */
export function getCustomerAccountWealth(id: number) {
  return requestClient.get<AicrmCustomerAccountWealthApi.CustomerAccountWealth>(
    `/aicrm/customer-account-wealth/get?id=${id}`,
  );
}

/** 新增客户理财账户信息表（零售+对公共用） */
export function createCustomerAccountWealth(data: AicrmCustomerAccountWealthApi.CustomerAccountWealth) {
  return requestClient.post('/aicrm/customer-account-wealth/create', data);
}

/** 修改客户理财账户信息表（零售+对公共用） */
export function updateCustomerAccountWealth(data: AicrmCustomerAccountWealthApi.CustomerAccountWealth) {
  return requestClient.put('/aicrm/customer-account-wealth/update', data);
}

/** 删除客户理财账户信息表（零售+对公共用） */
export function deleteCustomerAccountWealth(id: number) {
  return requestClient.delete(`/aicrm/customer-account-wealth/delete?id=${id}`);
}

/** 批量删除客户理财账户信息表（零售+对公共用） */
export function deleteCustomerAccountWealthList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-wealth/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户理财账户信息表（零售+对公共用） */
export function exportCustomerAccountWealth(params: any) {
  return requestClient.download('/aicrm/customer-account-wealth/export-excel', { params });
}