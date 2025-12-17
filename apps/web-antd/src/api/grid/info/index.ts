import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridInfoApi {
  /** 网格信息信息 */
  export interface Info {
    id: number; // 网格ID
    gridCode?: string; // 网格编号
    gridName?: string; // 网格名称
    gridType?: string; // 网格类型: HUINONG/TINGTANG/COMMUNITY/ZERODAI
    orgId?: number; // 所属机构ID (关联 system_dept)
    parentId: number; // 父网格ID (零贷层级结构使用)
    centerPoint: byte[]; // 网格中心坐标 (经纬度)
    boundaryGeometry: byte[]; // 网格边界 (POLYGON 或 CIRCLE)
    radiusMeters: number; // 网格半径(米) - 惠农固定3000, 厅堂默认1000
    residentCount: number; // 网格常住居民数 (厅堂使用)
    merchantCount: number; // 网格周围商户数 (厅堂使用)
    status: string; // 网格状态: ACTIVE/INACTIVE/PENDING_APPROVAL
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询网格信息分页 */
export function getInfoPage(params: PageParam) {
  return requestClient.get<PageResult<GridInfoApi.Info>>(
    '/grid/info/page',
    { params },
  );
}

/** 查询网格信息详情 */
export function getInfo(id: number) {
  return requestClient.get<GridInfoApi.Info>(
    `/grid/info/get?id=${id}`,
  );
}

/** 新增网格信息 */
export function createInfo(data: GridInfoApi.Info) {
  return requestClient.post('/grid/info/create', data);
}

/** 修改网格信息 */
export function updateInfo(data: GridInfoApi.Info) {
  return requestClient.put('/grid/info/update', data);
}

/** 删除网格信息 */
export function deleteInfo(id: number) {
  return requestClient.delete(`/grid/info/delete?id=${id}`);
}

/** 批量删除网格信息 */
export function deleteInfoList(ids: number[]) {
  return requestClient.delete(
    `/grid/info/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出网格信息 */
export function exportInfo(params: any) {
  return requestClient.download('/grid/info/export-excel', { params });
}