import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridKeyPersonApi {
  /** 关键人信息信息 */
  export interface KeyPerson {
    id: number; // 主键ID
    personName?: string; // 姓名
    organization?: string; // 单位/小区
    position: string; // 职务
    contact?: string; // 联系方式
    establishDate: string | Dayjs; // 建立联系日期
    lastMaintainDate: string | Dayjs; // 最新维护日期
    employeeCode?: string; // 员工工号
    employeeName?: string; // 员工姓名
  }
}

/** 查询关键人信息分页 */
export function getKeyPersonPage(params: PageParam) {
  return requestClient.get<PageResult<GridKeyPersonApi.KeyPerson>>(
    '/grid/key-person/page',
    { params },
  );
}

/** 查询关键人信息详情 */
export function getKeyPerson(id: number) {
  return requestClient.get<GridKeyPersonApi.KeyPerson>(
    `/grid/key-person/get?id=${id}`,
  );
}

/** 新增关键人信息 */
export function createKeyPerson(data: GridKeyPersonApi.KeyPerson) {
  return requestClient.post('/grid/key-person/create', data);
}

/** 修改关键人信息 */
export function updateKeyPerson(data: GridKeyPersonApi.KeyPerson) {
  return requestClient.put('/grid/key-person/update', data);
}

/** 删除关键人信息 */
export function deleteKeyPerson(id: number) {
  return requestClient.delete(`/grid/key-person/delete?id=${id}`);
}

/** 批量删除关键人信息 */
export function deleteKeyPersonList(ids: number[]) {
  return requestClient.delete(
    `/grid/key-person/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出关键人信息 */
export function exportKeyPerson(params: any) {
  return requestClient.download('/grid/key-person/export-excel', { params });
}