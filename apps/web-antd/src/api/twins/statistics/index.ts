import { requestClient } from '#/api/request';

export namespace TwinsStatisticsApi {
  /** 留资类型统计 */
  export interface CollectInfoTypeStats {
    templateId: number;
    name: string;
    total: number;
    monthCount: number;
    lastMonthCount: number;
  }

  /** 员工排行项 */
  export interface EmployeeRankItem {
    adminId: number;
    name: string;
    department: string;
    monthSessions: number;
    monthCollectInfos: number;
  }

  /** 统计概览 */
  export interface OverviewData {
    totalEmployees: number;
    activeAiCount: number;
    totalSessions: number;
    monthSessions: number;
    totalCollectInfos: number;
    monthCollectInfos: number;
    avgSatisfaction: number;
    avgResponseTime: number;
    collectInfoTypeStats: CollectInfoTypeStats[];
    employeeRankList: EmployeeRankItem[];
  }
}

/** 获取统计概览 */
export function getStatisticsOverview() {
  return requestClient.get<TwinsStatisticsApi.OverviewData>(
    '/twins/statistics/overview',
  );
}
