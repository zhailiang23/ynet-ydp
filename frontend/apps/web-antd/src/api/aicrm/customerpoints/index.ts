import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerPointsApi {
  /** 客户积分信息信息 */
  export interface CustomerPoints {
    id: number; // 积分主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    availablePoints?: number; // 可用积分
    historyTotalGiftPoints?: number; // 历史累计赠送积分
    historyTotalDeductPoints?: number; // 历史累计扣减积分
    historyTotalExpirePoints?: number; // 历史累计失效积分
    upcomingExpirePoints?: number; // 即将失效积分
    upcomingExpireDate: string | Dayjs; // 即将失效积分日期
    pointsAccountNo?: string; // 积分账户编号
    pointsLevel: string; // 积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）
    totalEarnedPoints?: number; // 累计获得积分（包含赠送和交易）
    totalUsedPoints?: number; // 累计使用积分
    frozenPoints?: number; // 冻结积分
    historyTotalTransactionPoints?: number; // 历史累计交易积分
    historyTotalRedeemPoints?: number; // 历史累计兑换积分
    pointsBalance?: number; // 积分余额（可用+冻结）
    accountStatus?: string; // 账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）
    openDate?: string | Dayjs; // 开户日期
    lastTransactionDate: string | Dayjs; // 最后交易日期
    lastTransactionTime: string | Dayjs; // 最后交易时间
    lastGiftDate: string | Dayjs; // 最后赠送日期
    lastRedeemDate: string | Dayjs; // 最后兑换日期
    pointsValidMonths?: number; // 积分有效期（月）
    isAutoExpire?: boolean; // 是否自动失效
    autoExpireRemindDays?: number; // 自动失效提醒天数
    yearEarnedPoints?: number; // 本年度获得积分
    yearUsedPoints?: number; // 本年度使用积分
    monthEarnedPoints?: number; // 本月获得积分
    monthUsedPoints?: number; // 本月使用积分
    remark: string; // 备注
  }
}

/** 查询客户积分信息分页 */
export function getCustomerPointsPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerPointsApi.CustomerPoints>>(
    '/aicrm/customer-points/page',
    { params },
  );
}

/** 查询客户积分信息详情 */
export function getCustomerPoints(id: number) {
  return requestClient.get<AicrmCustomerPointsApi.CustomerPoints>(
    `/aicrm/customer-points/get?id=${id}`,
  );
}

/** 新增客户积分信息 */
export function createCustomerPoints(data: AicrmCustomerPointsApi.CustomerPoints) {
  return requestClient.post('/aicrm/customer-points/create', data);
}

/** 修改客户积分信息 */
export function updateCustomerPoints(data: AicrmCustomerPointsApi.CustomerPoints) {
  return requestClient.put('/aicrm/customer-points/update', data);
}

/** 删除客户积分信息 */
export function deleteCustomerPoints(id: number) {
  return requestClient.delete(`/aicrm/customer-points/delete?id=${id}`);
}

/** 批量删除客户积分信息 */
export function deleteCustomerPointsList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-points/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户积分信息 */
export function exportCustomerPoints(params: any) {
  return requestClient.download('/aicrm/customer-points/export-excel', { params });
}