import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountMetalApi {
  /** 客户贵金属账户信息表（零售+对公共用）信息 */
  export interface CustomerAccountMetal {
    id: number; // 贵金属账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountNo?: string; // 贵金属账号
    productName?: string; // 产品名称
    accountName?: string; // 户名
    openDate?: string | Dayjs; // 开户日期
    closeDate: string | Dayjs; // 销户日期
    accountStatus?: string; // 账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）
    metalType?: string; // 贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）
    metalCategory: string; // 产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）
    productCode: string; // 产品代码
    holdingWeight?: number; // 持有重量（克）
    averageCost: number; // 平均成本（元/克）
    currentPrice: number; // 当前价格（元/克）
    currentValue?: number; // 当前市值（元）
    accumulatedIncome: number; // 累计收益（元）
    profitRate: number; // 收益率（%）
    balance?: number; // 账户余额（元）
    totalBuyWeight: number; // 累计买入重量（克）
    totalBuyAmount: number; // 累计买入金额（元）
    totalSellWeight: number; // 累计卖出重量（克）
    totalSellAmount: number; // 累计卖出金额（元）
    currencyType: string; // 币种
    deptId: number; // 开户机构ID
    deptName: string; // 开户机构名称
    managerUserId: number; // 客户经理用户ID
    remark: string; // 备注
  }
}

/** 查询客户贵金属账户信息表（零售+对公共用）分页 */
export function getCustomerAccountMetalPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountMetalApi.CustomerAccountMetal>>(
    '/aicrm/customer-account-metal/page',
    { params },
  );
}

/** 查询客户贵金属账户信息表（零售+对公共用）详情 */
export function getCustomerAccountMetal(id: number) {
  return requestClient.get<AicrmCustomerAccountMetalApi.CustomerAccountMetal>(
    `/aicrm/customer-account-metal/get?id=${id}`,
  );
}

/** 新增客户贵金属账户信息表（零售+对公共用） */
export function createCustomerAccountMetal(data: AicrmCustomerAccountMetalApi.CustomerAccountMetal) {
  return requestClient.post('/aicrm/customer-account-metal/create', data);
}

/** 修改客户贵金属账户信息表（零售+对公共用） */
export function updateCustomerAccountMetal(data: AicrmCustomerAccountMetalApi.CustomerAccountMetal) {
  return requestClient.put('/aicrm/customer-account-metal/update', data);
}

/** 删除客户贵金属账户信息表（零售+对公共用） */
export function deleteCustomerAccountMetal(id: number) {
  return requestClient.delete(`/aicrm/customer-account-metal/delete?id=${id}`);
}

/** 批量删除客户贵金属账户信息表（零售+对公共用） */
export function deleteCustomerAccountMetalList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-metal/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户贵金属账户信息表（零售+对公共用） */
export function exportCustomerAccountMetal(params: any) {
  return requestClient.download('/aicrm/customer-account-metal/export-excel', { params });
}