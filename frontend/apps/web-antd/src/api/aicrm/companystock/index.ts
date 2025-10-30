import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyStockApi {
  /** 对公客户股票发行人信息信息 */
  export interface CompanyStock {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联crm_customer表）
    stockCode?: string; // 股票代码
    stockName?: string; // 股票简称
    stockType: string; // 股票类型（A股、B股、H股、港股、美股、科创板等）
    listingExchange?: string; // 上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）
    listingDate: string | Dayjs; // 上市日期
    listingStatus: number; // 上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）
    issuePrice: number; // 发行价格
    issueQuantity: number; // 发行数量（股）
    issueAmount: number; // 发行总额（元）
    issuePeRatio: number; // 发行市盈率
    currentPrice: number; // 当前股价
    marketValue: number; // 总市值（元）
    circulatingMarketValue: number; // 流通市值（元）
    totalShares: number; // 总股本（股）
    circulatingShares: number; // 流通股本（股）
    industryCategory: string; // 行业分类（申万行业、证监会行业等）
    sector: string; // 所属板块
    isSt: number; // 是否ST股（0=否 1=是）
    isStar: number; // 是否*ST股（0=否 1=是）
    stockRating: string; // 股票评级（买入、增持、中性、减持、卖出）
    priceUpdateTime: string | Dayjs; // 价格更新时间
    dataSource: string; // 数据来源（手工录入、Wind、同花顺、东方财富等）
    remark: string; // 备注
  }
}

/** 查询对公客户股票发行人信息分页 */
export function getCompanyStockPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyStockApi.CompanyStock>>(
    '/aicrm/company-stock/page',
    { params },
  );
}

/** 查询对公客户股票发行人信息详情 */
export function getCompanyStock(id: number) {
  return requestClient.get<AicrmCompanyStockApi.CompanyStock>(
    `/aicrm/company-stock/get?id=${id}`,
  );
}

/** 新增对公客户股票发行人信息 */
export function createCompanyStock(data: AicrmCompanyStockApi.CompanyStock) {
  return requestClient.post('/aicrm/company-stock/create', data);
}

/** 修改对公客户股票发行人信息 */
export function updateCompanyStock(data: AicrmCompanyStockApi.CompanyStock) {
  return requestClient.put('/aicrm/company-stock/update', data);
}

/** 删除对公客户股票发行人信息 */
export function deleteCompanyStock(id: number) {
  return requestClient.delete(`/aicrm/company-stock/delete?id=${id}`);
}

/** 批量删除对公客户股票发行人信息 */
export function deleteCompanyStockList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-stock/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出对公客户股票发行人信息 */
export function exportCompanyStock(params: any) {
  return requestClient.download('/aicrm/company-stock/export-excel', { params });
}