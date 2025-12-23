import type { PageParam, PageResult } from '@vben/request';
import { requestClient } from '#/api/request';

export namespace LobbyGridApi {
  export interface LobbyGrid {
    id?: number;
    gridCode?: string;
    orgId?: number;
    orgName?: string;
    secondLevelId?: number;
    secondLevelName?: string;
    firstLevelId?: number;
    firstLevelName?: string;
    managementLevel?: string;
    region?: string;
    district?: string;
    locationName?: string;
    longitude?: number;
    latitude?: number;
    outletType?: string;
    residentCount?: number;
    merchantCount?: number;
    radiusMeters?: number;
    creator?: string;
    creatorName?: string;
    createTime?: string;
    boundaryGeometry?: string;  // GeoJSON
  }
}

export function getLobbyGridPage(params: PageParam) {
  return requestClient.get<PageResult<LobbyGridApi.LobbyGrid>>(
    '/grid/lobby-grid/page',
    { params },
  );
}

export function getLobbyGrid(id: number) {
  return requestClient.get<LobbyGridApi.LobbyGrid>(
    '/grid/lobby-grid/get',
    { params: { id } },
  );
}

export function createLobbyGrid(data: LobbyGridApi.LobbyGrid) {
  return requestClient.post<number>('/grid/lobby-grid/create', data);
}

export function updateLobbyGrid(data: LobbyGridApi.LobbyGrid) {
  return requestClient.put<void>('/grid/lobby-grid/update', data);
}

export function deleteLobbyGrid(id: number) {
  return requestClient.delete<void>('/grid/lobby-grid/delete', {
    params: { id },
  });
}
