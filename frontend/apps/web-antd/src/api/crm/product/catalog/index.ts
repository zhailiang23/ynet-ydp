import { requestClient } from '#/api/request';

export namespace CrmProductCatalogApi {
  /** 产品目录信息 */
  export interface ProductCatalog {
    id?: number;
    name: string;
    parentId?: number;
    sort: number;
    status: number;
    categoryLevel?: number;
    categoryPath?: string;
    description?: string;
    createTime?: Date;
    children?: ProductCatalog[];
  }
}

/** 查询产品目录（精简）列表 */
export async function getSimpleProductCatalogList() {
  return requestClient.get<CrmProductCatalogApi.ProductCatalog[]>(
    '/aicrm/product-catalog/simple-list',
  );
}

/** 查询产品目录列表 */
export async function getProductCatalogList(params?: any) {
  return requestClient.get<CrmProductCatalogApi.ProductCatalog[]>(
    '/aicrm/product-catalog/list',
    { params },
  );
}

/** 查询产品目录详情 */
export async function getProductCatalog(id: number) {
  return requestClient.get<CrmProductCatalogApi.ProductCatalog>(
    `/aicrm/product-catalog/get?id=${id}`,
  );
}

/** 新增产品目录 */
export async function createProductCatalog(
  data: CrmProductCatalogApi.ProductCatalog,
) {
  return requestClient.post('/aicrm/product-catalog/create', data);
}

/** 修改产品目录 */
export async function updateProductCatalog(
  data: CrmProductCatalogApi.ProductCatalog,
) {
  return requestClient.put('/aicrm/product-catalog/update', data);
}

/** 删除产品目录 */
export async function deleteProductCatalog(id: number) {
  return requestClient.delete(`/aicrm/product-catalog/delete?id=${id}`);
}

/** 批量删除产品目录 */
export async function deleteProductCatalogList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/product-catalog/delete-list?ids=${ids.join(',')}`,
  );
}
