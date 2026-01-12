import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridZerodaiMarketDescriptionApi {
  /** 零贷市场描述信息 */
  export interface ZerodaiMarketDescription {
    id: number; // 市场描述ID
    gridId?: number; // 网格ID
    marketName?: string; // 市场名称
    marketType: string; // 市场类型 (关联字典 aicrm_market_type)
    location: byte[]; // 市场位置坐标
    address: string; // 市场地址
    businessScope: string; // 经营范围
    merchantCount: number; // 商户数量
    dailyTraffic: number; // 日均客流量
    marketFeatures: string; // 市场特点
    targetCustomers: string; // 目标客户群体
    potentialAnalysis: string; // 潜力分析
    managerId: number; // 客户经理ID
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询零贷市场描述分页 */
export function getZerodaiMarketDescriptionPage(params: PageParam) {
  return requestClient.get<PageResult<GridZerodaiMarketDescriptionApi.ZerodaiMarketDescription>>(
    '/grid/zerodai-market-description/page',
    { params },
  );
}

/** 查询零贷市场描述详情 */
export function getZerodaiMarketDescription(id: number) {
  return requestClient.get<GridZerodaiMarketDescriptionApi.ZerodaiMarketDescription>(
    `/grid/zerodai-market-description/get?id=${id}`,
  );
}

/** 新增零贷市场描述 */
export function createZerodaiMarketDescription(data: GridZerodaiMarketDescriptionApi.ZerodaiMarketDescription) {
  return requestClient.post('/grid/zerodai-market-description/create', data);
}

/** 修改零贷市场描述 */
export function updateZerodaiMarketDescription(data: GridZerodaiMarketDescriptionApi.ZerodaiMarketDescription) {
  return requestClient.put('/grid/zerodai-market-description/update', data);
}

/** 删除零贷市场描述 */
export function deleteZerodaiMarketDescription(id: number) {
  return requestClient.delete(`/grid/zerodai-market-description/delete?id=${id}`);
}

/** 批量删除零贷市场描述 */
export function deleteZerodaiMarketDescriptionList(ids: number[]) {
  return requestClient.delete(
    `/grid/zerodai-market-description/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出零贷市场描述 */
export function exportZerodaiMarketDescription(params: any) {
  return requestClient.download('/grid/zerodai-market-description/export-excel', { params });
}