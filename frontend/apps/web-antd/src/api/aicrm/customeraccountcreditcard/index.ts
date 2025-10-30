import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerAccountCreditcardApi {
  /** 客户信用卡账户信息表（仅限零售客户）信息 */
  export interface CustomerAccountCreditcard {
    id: number; // 信用卡账户主键
    customerId?: number; // 客户ID（关联 crm_customer 主表，仅限零售客户）
    accountNo?: string; // 信用卡账号
    cardNo?: string; // 信用卡号（加密存储）
    productName?: string; // 信用卡产品名称
    accountName?: string; // 持卡人姓名
    openDate?: string | Dayjs; // 开卡日期
    closeDate: string | Dayjs; // 销卡日期
    accountStatus?: string; // 账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）
    cardType: string; // 卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）
    cardLevel: string; // 卡等级
    cardBrand: string; // 卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）
    isMainCard?: boolean; // 是否主卡（0=附属卡，1=主卡）
    mainCardNo: string; // 主卡卡号（附属卡关联）
    currencyType: string; // 币种
    creditLimit?: number; // 信用额度
    availableLimit?: number; // 可用额度
    temporaryLimit: number; // 临时额度
    cashLimit: number; // 取现额度
    usedAmount: number; // 已用额度
    balance?: number; // 当前欠款余额
    billingDay: number; // 账单日（每月几号）
    paymentDueDay: number; // 还款日（每月几号）
    currentBillAmount: number; // 本期账单金额
    minPaymentAmount: number; // 最低还款额
    unpaidAmount: number; // 未还金额
    lastPaymentDate: string | Dayjs; // 上次还款日期
    lastPaymentAmount: number; // 上次还款金额
    overdueDays: number; // 逾期天数
    overdueAmount: number; // 逾期金额
    overdueInterest: number; // 逾期利息
    overdueTimes: number; // 累计逾期次数
    totalPoints: number; // 累计积分
    availablePoints: number; // 可用积分
    pointsExpireDate: string | Dayjs; // 积分到期日
    expireDate: string | Dayjs; // 卡片有效期
    activateDate: string | Dayjs; // 激活日期
    lastTransactionDate: string | Dayjs; // 最后交易日期
    deptId: number; // 发卡机构ID
    deptName: string; // 发卡机构名称
    managerUserId: number; // 客户经理用户ID
    remark: string; // 备注
  }
}

/** 查询客户信用卡账户信息表（仅限零售客户）分页 */
export function getCustomerAccountCreditcardPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard>>(
    '/aicrm/customer-account-creditcard/page',
    { params },
  );
}

/** 查询客户信用卡账户信息表（仅限零售客户）详情 */
export function getCustomerAccountCreditcard(id: number) {
  return requestClient.get<AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard>(
    `/aicrm/customer-account-creditcard/get?id=${id}`,
  );
}

/** 新增客户信用卡账户信息表（仅限零售客户） */
export function createCustomerAccountCreditcard(data: AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard) {
  return requestClient.post('/aicrm/customer-account-creditcard/create', data);
}

/** 修改客户信用卡账户信息表（仅限零售客户） */
export function updateCustomerAccountCreditcard(data: AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard) {
  return requestClient.put('/aicrm/customer-account-creditcard/update', data);
}

/** 删除客户信用卡账户信息表（仅限零售客户） */
export function deleteCustomerAccountCreditcard(id: number) {
  return requestClient.delete(`/aicrm/customer-account-creditcard/delete?id=${id}`);
}

/** 批量删除客户信用卡账户信息表（仅限零售客户） */
export function deleteCustomerAccountCreditcardList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-account-creditcard/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户信用卡账户信息表（仅限零售客户） */
export function exportCustomerAccountCreditcard(params: any) {
  return requestClient.download('/aicrm/customer-account-creditcard/export-excel', { params });
}