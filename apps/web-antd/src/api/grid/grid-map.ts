/**
 * 网格营销综合地图 API
 */
import { requestClient } from '#/api/request';

export namespace GridMapApi {
  /**
   * 网格数据
   */
  export interface GridData {
    id: number;
    gridName: string;
    gridType: string;
    boundaryGeometry: string;
    longitude: number;
    latitude: number;
    managerUserId: number;
  }

  /**
   * 客户数据
   */
  export interface CustomerData {
    id: number;
    customerName: string;
    customerType: string;
    longitude: number;
    latitude: number;
    address: string;
  }

  /**
   * 社区数据
   */
  export interface CommunityData {
    id: number;
    communityName: string;
    longitude: number;
    latitude: number;
    address: string;
  }

  /**
   * 同业数据
   */
  export interface CompetitorData {
    id: number;
    competitorName: string;
    longitude: number;
    latitude: number;
    address: string;
  }

  /**
   * 地图数据请求参数
   */
  export interface MapDataRequest {
    gridTypes?: string[];
    customerTypes?: string[];
    includeCommunity?: boolean;
    includeCompetitor?: boolean;
  }

  /**
   * 地图数据响应
   */
  export interface MapDataResponse {
    grids: GridData[];
    customers: CustomerData[];
    communities: CommunityData[];
    competitors: CompetitorData[];
  }
}

/**
 * 获取综合地图数据
 */
export async function getMapDataApi(params: GridMapApi.MapDataRequest) {
  return requestClient.post<GridMapApi.MapDataResponse>('/grid/map/data', params);
}
