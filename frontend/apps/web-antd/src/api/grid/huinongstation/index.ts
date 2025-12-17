import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridHuinongStationApi {
  /** 惠农站点信息信息 */
  export interface HuinongStation {
    id: number; // 站点ID
    stationCode?: string; // 站点编号
    stationName?: string; // 站点名称
    stationType: string; // 站点类型
    location?: byte[]; // 站点坐标
    address: string; // 站点地址
    gridId: number; // 关联的网格ID
    gridMarketingFlag?: string; // 网格营销站点: REQUIRED/OPTIONAL
    contactPerson: string; // 联系人
    contactPhone: string; // 联系电话
    specialistId: number; // 负责惠农专员ID (关联 system_users)
    status: string; // 站点状态: ACTIVE/INACTIVE
    dataSource: string; // 数据来源: IMPORTED/MANUAL
    importBatch: string; // 导入批次号
    importTime: string | Dayjs; // 导入时间
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }

  /** 惠农站点地图数据 */
  export interface HuinongStationMapData {
    id: number;
    stationName: string;
    stationCode: string;
    longitude: number;
    latitude: number;
    address?: string;
    gridMarketingFlag?: string;
    specialistEmployeeNo?: string;
    specialistName?: string;
    manageBranchName?: string;
    subBranchName?: string;
    specialistLongitude?: number;
    specialistLatitude?: number;
    gridInfo?: {
      gridId: number;
      gridName: string;
      gridCode: string;
      centerLongitude: number;
      centerLatitude: number;
      boundaryGeometry?: string; // GeoJSON 格式
    };
  }

  /** 惠农站点信息导入响应 */
  export interface HuinongStationImportResp {
    createStationCodes: string[]; // 创建成功的站点编号数组
    updateStationCodes: string[]; // 更新成功的站点编号数组
    failureStationCodes: Record<string, string>; // 导入失败的站点集合，key 为站点编号，value 为失败原因
    createdGridCount: number; // 自动创建的网格数量
  }
}

/** 查询惠农站点信息分页 */
export function getHuinongStationPage(params: PageParam) {
  return requestClient.get<PageResult<GridHuinongStationApi.HuinongStation>>(
    '/grid/huinong-station/page',
    { params },
  );
}

/** 查询惠农站点信息详情 */
export function getHuinongStation(id: number) {
  return requestClient.get<GridHuinongStationApi.HuinongStation>(
    `/grid/huinong-station/get?id=${id}`,
  );
}

/** 新增惠农站点信息 */
export function createHuinongStation(data: GridHuinongStationApi.HuinongStation) {
  return requestClient.post('/grid/huinong-station/create', data);
}

/** 修改惠农站点信息 */
export function updateHuinongStation(data: GridHuinongStationApi.HuinongStation) {
  return requestClient.put('/grid/huinong-station/update', data);
}

/** 删除惠农站点信息 */
export function deleteHuinongStation(id: number) {
  return requestClient.delete(`/grid/huinong-station/delete?id=${id}`);
}

/** 批量删除惠农站点信息 */
export function deleteHuinongStationList(ids: number[]) {
  return requestClient.delete(
    `/grid/huinong-station/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出惠农站点信息 */
export function exportHuinongStation(params: any) {
  return requestClient.download('/grid/huinong-station/export-excel', { params });
}

/** 下载惠农站点信息导入模板 */
export function downloadHuinongStationTemplate() {
  return requestClient.download('/grid/huinong-station/get-import-template', {});
}

/** 导入惠农站点信息 */
export function importHuinongStation(file: File, updateSupport: boolean = false) {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('updateSupport', String(updateSupport));
  return requestClient.post<GridHuinongStationApi.HuinongStationImportResp>(
    '/grid/huinong-station/import',
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    },
  );
}

/** 获取惠农站点简单列表（用于下拉选择） */
export function getSimpleHuinongStationList() {
  return requestClient.get<GridHuinongStationApi.HuinongStation[]>(
    '/grid/huinong-station/simple-list',
  );
}

/** 获取惠农站点地图数据 */
export function getHuinongStationMapData(id: number) {
  return requestClient.get<GridHuinongStationApi.HuinongStationMapData>(
    `/grid/huinong-station/map-data?id=${id}`,
  );
}