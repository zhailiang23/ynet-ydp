import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridCompetitorInfoApi {
  /** 同业信息信息 */
  export interface CompetitorInfo {
    id: number; // 主键ID
    competitorName?: string; // 同业网点名称
    address?: string; // 详细地址
    longitude: number; // 经度
    latitude: number; // 纬度
    businessStrategy?: string; // 主要经营策略
    coreCustomers?: string; // 核心客户群
    productAdvantages?: string; // 产品优势
    productDisadvantages?: string; // 产品劣势
    employeeCode?: string; // 员工工号
    employeeName?: string; // 员工姓名
  }
}

/** 查询同业信息分页 */
export function getCompetitorInfoPage(params: PageParam) {
  return requestClient.get<PageResult<GridCompetitorInfoApi.CompetitorInfo>>(
    '/grid/competitor-info/page',
    { params },
  );
}

/** 查询同业信息详情 */
export function getCompetitorInfo(id: number) {
  return requestClient.get<GridCompetitorInfoApi.CompetitorInfo>(
    `/grid/competitor-info/get?id=${id}`,
  );
}

/** 新增同业信息 */
export function createCompetitorInfo(data: GridCompetitorInfoApi.CompetitorInfo) {
  return requestClient.post('/grid/competitor-info/create', data);
}

/** 修改同业信息 */
export function updateCompetitorInfo(data: GridCompetitorInfoApi.CompetitorInfo) {
  return requestClient.put('/grid/competitor-info/update', data);
}

/** 删除同业信息 */
export function deleteCompetitorInfo(id: number) {
  return requestClient.delete(`/grid/competitor-info/delete?id=${id}`);
}

/** 批量删除同业信息 */
export function deleteCompetitorInfoList(ids: number[]) {
  return requestClient.delete(
    `/grid/competitor-info/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出同业信息 */
export function exportCompetitorInfo(params: any) {
  return requestClient.download('/grid/competitor-info/export-excel', { params });
}