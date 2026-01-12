import type { PageResult } from '#/api/model/baseModel';

import { requestClient } from '#/api/request';

export namespace CommunityApi {
  /** 社区信息 */
  export interface Community {
    id?: number;
    communityCode?: string;
    communityName: string;
    teamCount?: number;
    managerUserId?: number;
    managerUserName?: string;
    householdCount?: number;
    individualBusinessCount?: number;
    smallEnterpriseCount?: number;
    commercialComplexCount?: number;
    qualityUnitCount?: number;
    score?: number;
    adjustedScore?: number;
    adjustmentReason?: string;
    longitude?: number;
    latitude?: number;
    status: 'ACTIVE' | 'INACTIVE';
    createTime?: string;
  }

  /** 分页请求参数 */
  export interface PageReqVO {
    pageNo?: number;
    pageSize?: number;
    communityCode?: string;
    communityName?: string;
    status?: string;
  }
}

/** 获取社区信息分页 */
export async function getCommunityPage(params: CommunityApi.PageReqVO) {
  return requestClient.get<PageResult<CommunityApi.Community>>('/grid/community/page', {
    params,
  });
}

/** 获取社区信息详情 */
export async function getCommunity(id: number) {
  return requestClient.get<CommunityApi.Community>('/grid/community/get', {
    params: { id },
  });
}

/** 新增社区信息 */
export async function createCommunity(data: CommunityApi.Community) {
  return requestClient.post<number>('/grid/community/create', data);
}

/** 修改社区信息 */
export async function updateCommunity(data: CommunityApi.Community) {
  return requestClient.put<boolean>('/grid/community/update', data);
}

/** 删除社区信息 */
export async function deleteCommunity(id: number) {
  return requestClient.delete<boolean>('/grid/community/delete', {
    params: { id },
  });
}
