import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmProductCategoryApi {
  /** 产品类目表（树形结构）信息 */
  export interface ProductCategory {
    id: number; // 产品类目主键
    categoryCode?: string; // 类目编号
    categoryName?: string; // 类目名称
    parentId: number; // 父类目ID（0表示顶级类目）
    categoryLevel?: number; // 类目层级（1=一级，2=二级，3=三级...）
    categoryPath: string; // 类目路径（如：/1/2/3/）
    isLeaf?: boolean; // 是否叶子节点（0=分类目录，1=实际产品）
    productType: string; // 产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）
    productCode: string; // 产品编号
    productDesc: string; // 产品描述
    currencyType: string; // 币种（字典: aicrm_currency_type）
    interestRateMin: number; // 最低利率/收益率（%）
    interestRateMax: number; // 最高利率/收益率（%）
    termMin: number; // 最短期限（天）
    termMax: number; // 最长期限（天）
    minAmount: number; // 起购金额
    maxAmount: number; // 最大金额
    riskLevel: string; // 风险等级（字典: aicrm_risk_level）
    customerType: string; // 适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）
    status?: number; // 状态（0=停用，1=启用）
    sortOrder?: number; // 排序
    remark: string; // 备注
  children?: ProductCategory[];
  }
}

/** 查询产品类目表（树形结构）列表 */
export function getProductCategoryList(params: any) {
  return requestClient.get<AicrmProductCategoryApi.ProductCategory[]>(
    '/aicrm/product-category/list',
    { params },
  );
}

/** 查询产品类目表（树形结构）详情 */
export function getProductCategory(id: number) {
  return requestClient.get<AicrmProductCategoryApi.ProductCategory>(
    `/aicrm/product-category/get?id=${id}`,
  );
}

/** 新增产品类目表（树形结构） */
export function createProductCategory(data: AicrmProductCategoryApi.ProductCategory) {
  return requestClient.post('/aicrm/product-category/create', data);
}

/** 修改产品类目表（树形结构） */
export function updateProductCategory(data: AicrmProductCategoryApi.ProductCategory) {
  return requestClient.put('/aicrm/product-category/update', data);
}

/** 删除产品类目表（树形结构） */
export function deleteProductCategory(id: number) {
  return requestClient.delete(`/aicrm/product-category/delete?id=${id}`);
}

/** 导出产品类目表（树形结构） */
export function exportProductCategory(params: any) {
  return requestClient.download('/aicrm/product-category/export-excel', { params });
}