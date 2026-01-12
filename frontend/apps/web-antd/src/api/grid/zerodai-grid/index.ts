import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace ZerodaiGridApi {
  /** 零贷网格信息 */
  export interface ZerodaiGrid {
    id?: number; // 网格ID
    gridCode?: string; // 网格编号
    gridName?: string; // 网格名称
    gridType?: string; // 网格类型
    orgId?: number; // 所属组织ID
    orgName?: string; // 所属组织名称
    resourceTags?: string; // 资源标签(JSON数组字符串)
    orgType?: string; // 组织类型
    creator?: string; // 创建者ID
    creatorName?: string; // 创建人姓名
    createTime?: string; // 创建时间
    boundaryGeometry?: string; // 网格边界(GeoJSON格式)
  }
}

/** 查询零贷网格分页 */
export function getZerodaiGridPage(params: PageParam) {
  return requestClient.get<PageResult<ZerodaiGridApi.ZerodaiGrid>>(
    '/grid/zerodai-grid/page',
    { params },
  );
}

/** 查询零贷网格详情 */
export function getZerodaiGrid(id: number) {
  return requestClient.get<ZerodaiGridApi.ZerodaiGrid>(
    '/grid/zerodai-grid/get',
    { params: { id } },
  );
}

/** 创建零贷网格 */
export function createZerodaiGrid(data: ZerodaiGridApi.ZerodaiGrid) {
  return requestClient.post<number>('/grid/zerodai-grid/create', data);
}

/** 更新零贷网格 */
export function updateZerodaiGrid(data: ZerodaiGridApi.ZerodaiGrid) {
  return requestClient.put<void>('/grid/zerodai-grid/update', data);
}

/** 删除零贷网格 */
export function deleteZerodaiGrid(id: number) {
  return requestClient.delete<void>('/grid/zerodai-grid/delete', {
    params: { id },
  });
}
