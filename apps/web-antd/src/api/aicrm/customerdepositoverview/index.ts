import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerDepositOverviewApi {
  /** 客户存款业务概览信息 */
  export interface CustomerDepositOverview {
    id: number; // 主键ID
    customerId?: number; // 客户ID
    customerNo: string; // 客户编号
    statisticsDate?: string | Dayjs; // 统计日期
    depositType: string; // 存款类型
    currency: string; // 币种
    accountNo: string; // 账号
    cardNo: string; // 卡号
    depositBalance: number; // 存款余额
    businessAmount: number; // 业务金额
    originalAmount: number; // 原币金额
    depositAccountCount: number; // 存款账户数
    accountStatus: string; // 账户状态
    subjectCode: string; // 科目代码
    productId: string; // 产品ID
    balanceYearAvg: number; // 本年余额日均
    realBalanceYearAvg: number; // 本年实际余额日均
    depositCumulativeYear: number; // 本年累计存款
    balanceQuarterAvg: number; // 本季度余额日均
    realBalanceQuarterAvg: number; // 本季度实际余额日均
    depositCumulativeQuarter: number; // 本季度累计存款
    balanceMonthAvg: number; // 本月余额日均
    realBalanceMonthAvg: number; // 本月实际余额日均
    depositCumulativeMonth: number; // 本月累计存款
    monthTotalIn: number; // 月度总流入
    monthTotalOut: number; // 月度总流出
    buyAmount: number; // 购买金额
    interestRate: number; // 存款利率
    ftpPrice: number; // FTP定价
    depositProfit: number; // 存款利润贡献
    openDate: string | Dayjs; // 开户日期
    startInterestDate: string | Dayjs; // 起息日期
    matureDate: string | Dayjs; // 到期日期
    logoutDate: string | Dayjs; // 销户日期
    orgNo: string; // 机构编号
    orgName: string; // 机构名称
    remark: string; // 备注
  }
}

/** 查询客户存款业务概览分页 */
export function getCustomerDepositOverviewPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerDepositOverviewApi.CustomerDepositOverview>>(
    '/aicrm/customer-deposit-overview/page',
    { params },
  );
}

/** 查询客户存款业务概览详情 */
export function getCustomerDepositOverview(id: number) {
  return requestClient.get<AicrmCustomerDepositOverviewApi.CustomerDepositOverview>(
    `/aicrm/customer-deposit-overview/get?id=${id}`,
  );
}

/** 新增客户存款业务概览 */
export function createCustomerDepositOverview(data: AicrmCustomerDepositOverviewApi.CustomerDepositOverview) {
  return requestClient.post('/aicrm/customer-deposit-overview/create', data);
}

/** 修改客户存款业务概览 */
export function updateCustomerDepositOverview(data: AicrmCustomerDepositOverviewApi.CustomerDepositOverview) {
  return requestClient.put('/aicrm/customer-deposit-overview/update', data);
}

/** 删除客户存款业务概览 */
export function deleteCustomerDepositOverview(id: number) {
  return requestClient.delete(`/aicrm/customer-deposit-overview/delete?id=${id}`);
}

/** 批量删除客户存款业务概览 */
export function deleteCustomerDepositOverviewList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-deposit-overview/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户存款业务概览 */
export function exportCustomerDepositOverview(params: any) {
  return requestClient.download('/aicrm/customer-deposit-overview/export-excel', { params });
}