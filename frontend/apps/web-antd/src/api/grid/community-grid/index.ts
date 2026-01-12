import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace CommunityGridApi {
  /** 社区网格信息 */
  export interface CommunityGrid {
    id?: number; // 网格ID
    gridCode?: string; // 网格编号
    gridName?: string; // 网格名称
    orgId?: number; // 所属机构ID
    orgName?: string; // 所属机构名称
    managerUserId?: number; // 责任人用户ID
    managerUserName?: string; // 责任人姓名
    teamCount?: number; // 团队人数
    status?: string; // 网格状态
    boundaryGeometry?: string; // 网格边界(GeoJSON格式)
    gridType?: string; // 网格类型
    createTime?: string; // 创建时间
  }
}

/** 查询社区网格分页 */
export function getCommunityGridPage(params: PageParam) {
  return requestClient.get<PageResult<CommunityGridApi.CommunityGrid>>(
    '/grid/community-grid/page',
    { params },
  );
}

/** 查询社区网格详情 */
export function getCommunityGrid(id: number) {
  return requestClient.get<CommunityGridApi.CommunityGrid>(
    '/grid/community-grid/get',
    { params: { id } },
  );
}

/** 创建社区网格 */
export function createCommunityGrid(data: CommunityGridApi.CommunityGrid) {
  return requestClient.post<number>('/grid/community-grid/create', data);
}

/** 更新社区网格 */
export function updateCommunityGrid(data: CommunityGridApi.CommunityGrid) {
  return requestClient.put<void>('/grid/community-grid/update', data);
}

/** 删除社区网格 */
export function deleteCommunityGrid(id: number) {
  return requestClient.delete<void>('/grid/community-grid/delete', {
    params: { id },
  });
}
