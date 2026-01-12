import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridHuinongMarketingApi {
  /** 惠农网格营销信息信息 */
  export interface HuinongMarketing {
    id?: number; // 营销信息ID
    stationId?: number; // 站点ID
    stationName?: string; // 站点名称
    stationCode?: string; // 站点编号
    villageName?: string; // 行政村名称
    villageLocation?: string; // 行政村坐标（格式：经度,纬度）
    villageAddress?: string; // 行政村地址
    registeredPopulation?: number; // 户籍人口数
    industrySituation?: string; // 产业情况
    customerType?: string; // 客户类型（JSON数组）
    customerCount?: string; // 客户数量（JSON对象）
    employeeNo?: string; // 员工工号
    creatorName?: string; // 员工姓名（录入人）
    createTime?: string; // 创建时间
  }
}

/** 查询惠农网格营销信息分页 */
export function getHuinongMarketingPage(params: PageParam) {
  return requestClient.get<PageResult<GridHuinongMarketingApi.HuinongMarketing>>(
    '/grid/huinong-marketing/page',
    { params },
  );
}

/** 查询惠农网格营销信息详情 */
export function getHuinongMarketing(id: number) {
  return requestClient.get<GridHuinongMarketingApi.HuinongMarketing>(
    `/grid/huinong-marketing/get?id=${id}`,
  );
}

/** 新增惠农网格营销信息 */
export function createHuinongMarketing(data: GridHuinongMarketingApi.HuinongMarketing) {
  return requestClient.post('/grid/huinong-marketing/create', data);
}

/** 修改惠农网格营销信息 */
export function updateHuinongMarketing(data: GridHuinongMarketingApi.HuinongMarketing) {
  return requestClient.put('/grid/huinong-marketing/update', data);
}

/** 删除惠农网格营销信息 */
export function deleteHuinongMarketing(id: number) {
  return requestClient.delete(`/grid/huinong-marketing/delete?id=${id}`);
}

/** 批量删除惠农网格营销信息 */
export function deleteHuinongMarketingList(ids: number[]) {
  return requestClient.delete(
    `/grid/huinong-marketing/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出惠农网格营销信息 */
export function exportHuinongMarketing(params: any) {
  return requestClient.download('/grid/huinong-marketing/export-excel', { params });
}

/** 热力图请求参数 */
export interface HeatmapReqVO {
  stationType?: 'REQUIRED' | 'OPTIONAL' | ''; // 站点类型过滤
  customerTypes?: string[]; // 客群类型过滤
}

/** 站点统计数据 */
export interface StationStatistics {
  villageCount: number; // 村数
  totalPopulation: number; // 人口
  totalCustomerCount: number; // 客群总数
}

/** 热力图统计数据 */
export interface HeatmapStatisticsVO {
  totalStations: number; // 已开展站点数
  requiredStations: number; // 必选站点数
  optionalStations: number; // 可选站点数
  requiredStatistics: StationStatistics; // 必选站点统计
  optionalStatistics: StationStatistics; // 可选站点统计
  customerTypeStatistics: Record<string, number>; // 九类客群统计
}

/** 热力图数据点 */
export interface HeatmapDataVO {
  longitude: number;
  latitude: number;
  count: number; // 权重
  villageName?: string;
}

/** 获取热力图统计数据 */
export function getHeatmapStatistics(params: HeatmapReqVO) {
  return requestClient.get<HeatmapStatisticsVO>(
    '/grid/huinong-marketing/heatmap/statistics',
    { params },
  );
}

/** 获取热力图数据 */
export function getHeatmapData(params: HeatmapReqVO) {
  return requestClient.get<HeatmapDataVO[]>(
    '/grid/huinong-marketing/heatmap/data',
    { params },
  );
}