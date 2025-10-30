import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerTransactionMockApi {
  /** 客户交易明细信息表（Mock数据）信息 */
  export interface CustomerTransactionMock {
    id: number; // 交易主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    accountId: number; // 账户ID（关联账户表）
    transactionDate?: string | Dayjs; // 交易日期
    transactionTime?: localtime; // 交易时间
    branchNo: string; // 交易机构编号
    branchName: string; // 交易机构名称
    originalTranCode: string; // 原交易代码
    cashFlag: string; // 现转标志（字典: aicrm_cash_flag）
    subAccountNo: string; // 子账户编号
    accountNo?: string; // 账户编号
    currencyCode?: string; // 币种（字典: aicrm_currency_type）
    tansNo: string; // 交易流水号
    tradType: string; // 交易类型（字典: aicrm_transaction_type）
    tradMoney?: number; // 交易金额
    acctBal: number; // 账户余额
    tradAbs: string; // 交易摘要
    review: string; // 审核标志
    tradChn: string; // 交易渠道（字典: aicrm_transaction_channel）
    tradTeller: string; // 交易柜员
    handler: string; // 经办人
    advsAcct: string; // 对方账号
    advsAcctName: string; // 对方户名
    contactType: string; // 往来类型
    currTranFlag: string; // 钞汇标志
    loanFlag: string; // 贷款标志
    cost: number; // 手续费
    accountinDate: string | Dayjs; // 记账日期
    transactionStatus?: string; // 交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）
    direction?: string; // 交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）
    remark: string; // 备注
  }
}

/** 查询客户交易明细信息表（Mock数据）分页 */
export function getCustomerTransactionMockPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerTransactionMockApi.CustomerTransactionMock>>(
    '/aicrm/customer-transaction-mock/page',
    { params },
  );
}

/** 查询客户交易明细信息表（Mock数据）详情 */
export function getCustomerTransactionMock(id: number) {
  return requestClient.get<AicrmCustomerTransactionMockApi.CustomerTransactionMock>(
    `/aicrm/customer-transaction-mock/get?id=${id}`,
  );
}

/** 新增客户交易明细信息表（Mock数据） */
export function createCustomerTransactionMock(data: AicrmCustomerTransactionMockApi.CustomerTransactionMock) {
  return requestClient.post('/aicrm/customer-transaction-mock/create', data);
}

/** 修改客户交易明细信息表（Mock数据） */
export function updateCustomerTransactionMock(data: AicrmCustomerTransactionMockApi.CustomerTransactionMock) {
  return requestClient.put('/aicrm/customer-transaction-mock/update', data);
}

/** 删除客户交易明细信息表（Mock数据） */
export function deleteCustomerTransactionMock(id: number) {
  return requestClient.delete(`/aicrm/customer-transaction-mock/delete?id=${id}`);
}

/** 批量删除客户交易明细信息表（Mock数据） */
export function deleteCustomerTransactionMockList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-transaction-mock/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户交易明细信息表（Mock数据） */
export function exportCustomerTransactionMock(params: any) {
  return requestClient.download('/aicrm/customer-transaction-mock/export-excel', { params });
}