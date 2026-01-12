import type { PageResult } from '#/api/model/baseModel';
import { requestClient } from '#/api/request';

export namespace CrmFinancialCarouselApi {
  /** 金融产品轮播信息 */
  export interface FinancialCarousel {
    id?: number;
    title: string;
    subtitle?: string;
    imageUrl: string;
    linkType?: string;
    linkId?: number;
    linkUrl?: string;
    badgeText?: string;
    badgeColor?: string;
    sort: number;
    status: number;
    createTime?: Date;
  }

  /** 分页查询参数 */
  export interface PageReqVO {
    pageNo?: number;
    pageSize?: number;
    title?: string;
    status?: number;
  }
}

/** 查询轮播分页 */
export async function getCarouselPage(params: CrmFinancialCarouselApi.PageReqVO) {
  return requestClient.get<PageResult<CrmFinancialCarouselApi.FinancialCarousel>>(
    '/aicrm/financial-carousel/page',
    { params },
  );
}

/** 查询轮播详情 */
export async function getCarousel(id: number) {
  return requestClient.get<CrmFinancialCarouselApi.FinancialCarousel>(
    `/aicrm/financial-carousel/get?id=${id}`,
  );
}

/** 新增轮播 */
export async function createCarousel(data: CrmFinancialCarouselApi.FinancialCarousel) {
  return requestClient.post('/aicrm/financial-carousel/create', data);
}

/** 修改轮播 */
export async function updateCarousel(data: CrmFinancialCarouselApi.FinancialCarousel) {
  return requestClient.put('/aicrm/financial-carousel/update', data);
}

/** 删除轮播 */
export async function deleteCarousel(id: number) {
  return requestClient.delete(`/aicrm/financial-carousel/delete?id=${id}`);
}

/** 批量删除轮播 */
export async function deleteCarouselList(ids: number[]) {
  return requestClient.delete(`/aicrm/financial-carousel/delete-list?ids=${ids.join(',')}`);
}
