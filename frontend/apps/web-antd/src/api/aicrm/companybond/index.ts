import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyBondApi {
  /** 对公客户债券信息信息 */
  export interface CompanyBond {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联crm_customer表）
    bondCode?: string; // 债券代码
    bondName?: string; // 债券名称
    bondType?: string; // 债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）
    bondSubtype: string; // 债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）
    issueDate: string | Dayjs; // 发行日期
    issueAmount?: number; // 发行金额（元）
    issuePrice: number; // 发行价格（面值百元）
    parValue: number; // 面值（元）
    issueScale: number; // 发行规模（张）
    maturityDate: string | Dayjs; // 到期日期
    termYears: number; // 债券期限（年）
    couponRate: number; // 票面利率（%）
    interestType: string; // 计息方式（固定利率、浮动利率、零息、累进利率等）
    paymentFrequency: string; // 付息频率（年付、半年付、季付、月付等）
    creditRating: string; // 债券信用评级（AAA、AA+、AA等）
    ratingAgency: string; // 评级机构（中诚信、联合资信、大公国际等）
    ratingDate: string | Dayjs; // 评级日期
    underwriter: string; // 主承销商
    listingExchange: string; // 上市交易所（上交所、深交所、银行间市场等）
    listingDate: string | Dayjs; // 上市日期
    bondStatus: number; // 债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）
    guaranteeType: string; // 担保方式（无担保、抵押、质押、保证担保等）
    guarantor: string; // 担保方
    enhancementMeasures: string; // 增信措施
    isConvertible: number; // 是否可转债（0=否 1=是）
    conversionPrice: number; // 转股价格
    conversionStartDate: string | Dayjs; // 转股起始日
    conversionEndDate: string | Dayjs; // 转股截止日
    conversionStockCode: string; // 转股代码
    currentPrice: number; // 当前价格
    yieldToMaturity: number; // 到期收益率（%）
    outstandingAmount: number; // 未偿还余额（元）
    priceUpdateTime: string | Dayjs; // 价格更新时间
    useOfProceeds: string; // 募集资金用途
    isGreenBond: number; // 是否绿色债券（0=否 1=是）
    specialClause: string; // 特殊条款（回售条款、赎回条款、调整票面利率条款等）
    dataSource: string; // 数据来源（手工录入、Wind、中债网、中证网等）
    remark: string; // 备注
  }
}

/** 查询对公客户债券信息分页 */
export function getCompanyBondPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyBondApi.CompanyBond>>(
    '/aicrm/company-bond/page',
    { params },
  );
}

/** 查询对公客户债券信息详情 */
export function getCompanyBond(id: number) {
  return requestClient.get<AicrmCompanyBondApi.CompanyBond>(
    `/aicrm/company-bond/get?id=${id}`,
  );
}

/** 新增对公客户债券信息 */
export function createCompanyBond(data: AicrmCompanyBondApi.CompanyBond) {
  return requestClient.post('/aicrm/company-bond/create', data);
}

/** 修改对公客户债券信息 */
export function updateCompanyBond(data: AicrmCompanyBondApi.CompanyBond) {
  return requestClient.put('/aicrm/company-bond/update', data);
}

/** 删除对公客户债券信息 */
export function deleteCompanyBond(id: number) {
  return requestClient.delete(`/aicrm/company-bond/delete?id=${id}`);
}

/** 批量删除对公客户债券信息 */
export function deleteCompanyBondList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-bond/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出对公客户债券信息 */
export function exportCompanyBond(params: any) {
  return requestClient.download('/aicrm/company-bond/export-excel', { params });
}