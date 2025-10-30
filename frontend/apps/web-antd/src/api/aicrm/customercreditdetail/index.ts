import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerCreditDetailApi {
  /** 客户授信使用明细表（零售+对公共用）信息 */
  export interface CustomerCreditDetail {
    id: number; // 授信明细主键
    creditId?: number; // 授信ID（关联 crm_customer_credit.id）
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    loanNo?: string; // 贷款编号/借据号
    contractNo: string; // 合同编号
    loanDate?: string | Dayjs; // 放款日期
    matureDate: string | Dayjs; // 到期日期
    loanAmount?: number; // 贷款金额（元）
    balance?: number; // 贷款余额（元）
    interestRate: number; // 执行利率（%）
    loanStatus?: string; // 贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）
    settleDate: string | Dayjs; // 结清日期
    productName: string; // 贷款产品名称
    productCode: string; // 产品代码
    remark: string; // 备注
  }
}

/** 查询客户授信使用明细表（零售+对公共用）分页 */
export function getCustomerCreditDetailPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerCreditDetailApi.CustomerCreditDetail>>(
    '/aicrm/customer-credit-detail/page',
    { params },
  );
}

/** 查询客户授信使用明细表（零售+对公共用）详情 */
export function getCustomerCreditDetail(id: number) {
  return requestClient.get<AicrmCustomerCreditDetailApi.CustomerCreditDetail>(
    `/aicrm/customer-credit-detail/get?id=${id}`,
  );
}

/** 新增客户授信使用明细表（零售+对公共用） */
export function createCustomerCreditDetail(data: AicrmCustomerCreditDetailApi.CustomerCreditDetail) {
  return requestClient.post('/aicrm/customer-credit-detail/create', data);
}

/** 修改客户授信使用明细表（零售+对公共用） */
export function updateCustomerCreditDetail(data: AicrmCustomerCreditDetailApi.CustomerCreditDetail) {
  return requestClient.put('/aicrm/customer-credit-detail/update', data);
}

/** 删除客户授信使用明细表（零售+对公共用） */
export function deleteCustomerCreditDetail(id: number) {
  return requestClient.delete(`/aicrm/customer-credit-detail/delete?id=${id}`);
}

/** 批量删除客户授信使用明细表（零售+对公共用） */
export function deleteCustomerCreditDetailList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-credit-detail/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户授信使用明细表（零售+对公共用） */
export function exportCustomerCreditDetail(params: any) {
  return requestClient.download('/aicrm/customer-credit-detail/export-excel', { params });
}