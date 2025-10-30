import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerContributionApi {
  /** 客户贡献度信息表（与客户主表1对1关系）信息 */
  export interface CustomerContribution {
    id: number; // 贡献度主键
    customerId?: number; // 客户ID（关联 crm_customer 主表，1对1关系）
    sequenceNo: number; // 序号
    totalContribution?: number; // 客户综合贡献度
    depositContribution?: number; // 存款贡献度
    loanContribution?: number; // 贷款贡献度
    intermediateContribution?: number; // 中间业务贡献度
    statisticsTime?: string | Dayjs; // 统计时间
    custName: string; // 客户名称（老系统字段）
    orgId: string; // 机构ID（老系统）
    contriDeposit: number; // 存款贡献度（老系统字段名）
    contributionLoan: number; // 贷款贡献度（老系统字段名）
    contributionMid: number; // 中间业务贡献度（老系统字段名）
    contributionCust: number; // 客户综合贡献度（老系统字段名）
    contriBillDiscount: number; // 票据贴现贡献度
    contriInternation: number; // 国际业务贡献度
    etlDate: string | Dayjs; // 数据加载日期（老系统）
    wealthManagementContribution: number; // 理财业务贡献度
    cardContribution: number; // 卡业务贡献度
    settlementContribution: number; // 结算业务贡献度
    electronicBankingContribution: number; // 电子银行贡献度
    contributionRank: number; // 贡献度排名
    contributionLevel: string; // 贡献度等级（字典: aicrm_contribution_level）
    yearOverYearGrowth: number; // 同比增长率（%）
    monthOverMonthGrowth: number; // 环比增长率（%）
    statisticsPeriod: string; // 统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）
    remark: string; // 备注
  }
}

/** 查询客户贡献度信息表（与客户主表1对1关系）分页 */
export function getCustomerContributionPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerContributionApi.CustomerContribution>>(
    '/aicrm/customer-contribution/page',
    { params },
  );
}

/** 查询客户贡献度信息表（与客户主表1对1关系）详情 */
export function getCustomerContribution(id: number) {
  return requestClient.get<AicrmCustomerContributionApi.CustomerContribution>(
    `/aicrm/customer-contribution/get?id=${id}`,
  );
}

/** 新增客户贡献度信息表（与客户主表1对1关系） */
export function createCustomerContribution(data: AicrmCustomerContributionApi.CustomerContribution) {
  return requestClient.post('/aicrm/customer-contribution/create', data);
}

/** 修改客户贡献度信息表（与客户主表1对1关系） */
export function updateCustomerContribution(data: AicrmCustomerContributionApi.CustomerContribution) {
  return requestClient.put('/aicrm/customer-contribution/update', data);
}

/** 删除客户贡献度信息表（与客户主表1对1关系） */
export function deleteCustomerContribution(id: number) {
  return requestClient.delete(`/aicrm/customer-contribution/delete?id=${id}`);
}

/** 批量删除客户贡献度信息表（与客户主表1对1关系） */
export function deleteCustomerContributionList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-contribution/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户贡献度信息表（与客户主表1对1关系） */
export function exportCustomerContribution(params: any) {
  return requestClient.download('/aicrm/customer-contribution/export-excel', { params });
}