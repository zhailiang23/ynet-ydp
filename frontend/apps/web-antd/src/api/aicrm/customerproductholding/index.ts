import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerProductHoldingApi {
  /** 客户产品持有情况表（客户与产品的多对多关系）信息 */
  export interface CustomerProductHolding {
    id: number; // 产品持有主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    productId?: number; // 产品ID（关联 crm_product_category.id，必须是叶子节点）
    accountNo?: string; // 贷款账号/账号
    receiptNo: string; // 借据编号
    contractNo: string; // 合同编号
    currencyCode: string; // 币种代码
    loanDate: string | Dayjs; // 放款日期（贷款产品）
    openDate: string | Dayjs; // 开户日期（存款、理财等产品）
    matureDate: string | Dayjs; // 到期日期
    contractDate: string | Dayjs; // 合同日期
    branchName: string; // 开户网点名称
    branchId: number; // 开户网点ID（关联 system_dept.id）
    productName?: string; // 产品名称
    holdingAmount?: number; // 持有金额/余额
    originalAmount: number; // 原始金额（初始投资/贷款金额）
    interestRate: number; // 利率/收益率（%）
    holdingStatus?: string; // 持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）
    relatedAccountType: string; // 关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）
    relatedAccountId: number; // 关联账户ID（关联对应的账户表主键）
    remark: string; // 备注
  }
}

/** 查询客户产品持有情况表（客户与产品的多对多关系）分页 */
export function getCustomerProductHoldingPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerProductHoldingApi.CustomerProductHolding>>(
    '/aicrm/customer-product-holding/page',
    { params },
  );
}

/** 查询客户产品持有情况表（客户与产品的多对多关系）详情 */
export function getCustomerProductHolding(id: number) {
  return requestClient.get<AicrmCustomerProductHoldingApi.CustomerProductHolding>(
    `/aicrm/customer-product-holding/get?id=${id}`,
  );
}

/** 新增客户产品持有情况表（客户与产品的多对多关系） */
export function createCustomerProductHolding(data: AicrmCustomerProductHoldingApi.CustomerProductHolding) {
  return requestClient.post('/aicrm/customer-product-holding/create', data);
}

/** 修改客户产品持有情况表（客户与产品的多对多关系） */
export function updateCustomerProductHolding(data: AicrmCustomerProductHoldingApi.CustomerProductHolding) {
  return requestClient.put('/aicrm/customer-product-holding/update', data);
}

/** 删除客户产品持有情况表（客户与产品的多对多关系） */
export function deleteCustomerProductHolding(id: number) {
  return requestClient.delete(`/aicrm/customer-product-holding/delete?id=${id}`);
}

/** 批量删除客户产品持有情况表（客户与产品的多对多关系） */
export function deleteCustomerProductHoldingList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-product-holding/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户产品持有情况表（客户与产品的多对多关系） */
export function exportCustomerProductHolding(params: any) {
  return requestClient.download('/aicrm/customer-product-holding/export-excel', { params });
}