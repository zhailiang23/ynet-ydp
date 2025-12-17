import { requestClient } from '#/api/request';

export namespace GridHuinongStationMapApi {
  // 网格信息
  export interface GridInfo {
    gridId: number;
    gridName: string;
    gridCode: string;
    centerLongitude: number;
    centerLatitude: number;
    boundaryGeometry: string; // GeoJSON格式
  }

  // 地图数据
  export interface HuinongStationMapVO {
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
    gridInfo?: GridInfo;
  }

  // 营销信息地图标记
  export interface MarketingMarkerVO {
    id: number;
    villageName: string;
    villageAddress?: string;
    longitude: number;
    latitude: number;
    registeredPopulation?: number;
    residentPopulation?: number;
    mainIndustries?: string;
    industrySituation?: string;
  }

  // 客户地图标记
  export interface CustomerMarkerVO {
    id: number;
    customerId: number;
    customerName: string;
    phone: string;
    longitude: number;
    latitude: number;
    customerCategory?: string;
    subdivisionType?: string;
    businessAddress?: string;
    loanAmount?: number;
    overdueStatus?: string;
  }
}

/**
 * 获取惠农站点地图数据
 */
export function getHuinongStationMapDataApi(id: number) {
  return requestClient.get<GridHuinongStationMapApi.HuinongStationMapVO>(
    `/grid/huinong-station/map-data`,
    {
      params: { id },
    },
  );
}

/**
 * 获取站点下的营销信息地图标记列表
 */
export function getMarketingMarkersApi(stationId: number) {
  return requestClient.get<GridHuinongStationMapApi.MarketingMarkerVO[]>(
    `/grid/huinong-station/map-marketing-markers`,
    {
      params: { stationId },
    },
  );
}

/**
 * 获取站点下的客户地图标记列表
 */
export function getCustomerMarkersApi(stationId: number) {
  return requestClient.get<GridHuinongStationMapApi.CustomerMarkerVO[]>(
    `/grid/huinong-station/map-customer-markers`,
    {
      params: { stationId },
    },
  );
}
