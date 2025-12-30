import type { PageResult } from '#/api/model/baseModel';
import { requestClient } from '#/api/request';

export namespace CrmFinancialProductApi {
  /** 金融产品信息 */
  export interface FinancialProduct {
    id?: number;
    productCode: string;
    productName: string;
    catalogId: number;
    category: string;
    riskLevel: string;
    expectedReturn?: number;
    returnType?: string;
    duration?: string;
    durationDays?: number;
    minimumInvestment: number;
    investmentUnit?: string;
    status: number;
    isHot?: number;
    isNew?: number;
    description?: string;
    features?: string;
    saleChannel?: string;
    tags?: string[];
    aiMatchScore?: number;
    aiKeywords?: string;
    sort?: number;
    bannerImage?: string;
    createTime?: Date;
  }

  /** 分页查询参数 */
  export interface PageReqVO {
    pageNo?: number;
    pageSize?: number;
    productName?: string;
    productCode?: string;
    catalogId?: number;
    category?: string;
    riskLevel?: string;
    status?: number;
    isHot?: number;
    isNew?: number;
    createTime?: [Date, Date];
  }
}

/** 查询金融产品分页 */
export async function getFinancialProductPage(params: CrmFinancialProductApi.PageReqVO) {
  return requestClient.get<PageResult<CrmFinancialProductApi.FinancialProduct>>(
    '/aicrm/financial-product/page',
    { params },
  );
}

/** 查询金融产品详情 */
export async function getFinancialProduct(id: number) {
  return requestClient.get<CrmFinancialProductApi.FinancialProduct>(
    `/aicrm/financial-product/get?id=${id}`,
  );
}

/** 新增金融产品 */
export async function createFinancialProduct(data: CrmFinancialProductApi.FinancialProduct) {
  return requestClient.post('/aicrm/financial-product/create', data);
}

/** 修改金融产品 */
export async function updateFinancialProduct(data: CrmFinancialProductApi.FinancialProduct) {
  return requestClient.put('/aicrm/financial-product/update', data);
}

/** 删除金融产品 */
export async function deleteFinancialProduct(id: number) {
  return requestClient.delete(`/aicrm/financial-product/delete?id=${id}`);
}

/** 批量删除金融产品 */
export async function deleteFinancialProductList(ids: number[]) {
  return requestClient.delete(`/aicrm/financial-product/delete-list?ids=${ids.join(',')}`);
}

/** 导出金融产品 Excel */
export async function exportFinancialProductExcel(params: CrmFinancialProductApi.PageReqVO) {
  return requestClient.download('/aicrm/financial-product/export-excel', { params });
}
