import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridHuinongExtensionApi {
  /** 惠农网格扩展信息 */
  export interface HuinongExtension {
    id: number; // 扩展ID
    gridId?: number; // 网格ID (关联 grid_info)
    isMarketingSite: boolean; // 是否为网格营销站点
    isRequired: boolean; // 是否为必选站点
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询惠农网格扩展分页 */
export function getHuinongExtensionPage(params: PageParam) {
  return requestClient.get<PageResult<GridHuinongExtensionApi.HuinongExtension>>(
    '/grid/huinong-extension/page',
    { params },
  );
}

/** 查询惠农网格扩展详情 */
export function getHuinongExtension(id: number) {
  return requestClient.get<GridHuinongExtensionApi.HuinongExtension>(
    `/grid/huinong-extension/get?id=${id}`,
  );
}

/** 新增惠农网格扩展 */
export function createHuinongExtension(data: GridHuinongExtensionApi.HuinongExtension) {
  return requestClient.post('/grid/huinong-extension/create', data);
}

/** 修改惠农网格扩展 */
export function updateHuinongExtension(data: GridHuinongExtensionApi.HuinongExtension) {
  return requestClient.put('/grid/huinong-extension/update', data);
}

/** 删除惠农网格扩展 */
export function deleteHuinongExtension(id: number) {
  return requestClient.delete(`/grid/huinong-extension/delete?id=${id}`);
}

/** 批量删除惠农网格扩展 */
export function deleteHuinongExtensionList(ids: number[]) {
  return requestClient.delete(
    `/grid/huinong-extension/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出惠农网格扩展 */
export function exportHuinongExtension(params: any) {
  return requestClient.download('/grid/huinong-extension/export-excel', { params });
}