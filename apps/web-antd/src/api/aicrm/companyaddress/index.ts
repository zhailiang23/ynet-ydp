import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyAddressApi {
  /** CRM对公客户地址信息信息 */
  export interface CompanyAddress {
    id: number; // 主键ID
    customerId?: number; // 客户ID(关联crm_company_customer.customer_id)
    addressType: string; // 地址类型(公司地址、家庭地址、其他地址)
    isPrimary: boolean; // 是否首选项(否、是)
    addressDetail: string; // 详细地址
    postalCode: string; // 邮编
    sourceSystem: string; // 来源系统(ECIF、CRM、零售CRM等)
    countryOrRegion: string; // 国家或地区
    provinceCode: string; // 省份代码
    cityCode: string; // 城市代码
    countyCode: string; // 区县代码
    townCode: string; // 乡镇代码
    townName: string; // 乡镇名称
    streetName: string; // 街道名称
    villageNo: string; // 村组编号
    villageName: string; // 村组名称
    areaCode: string; // 地区代码
    adminZone: string; // 行政区域
    enAddress: string; // 英文地址
    contactMethod: string; // 联系方式类型
    addressLevel: string; // 地址级别
    remark: string; // 备注说明
    etlDate: string | Dayjs; // ETL导入日期
    oldTxSeqNo: string; // 老系统交易序列号
    oldLastUpdateSys: string; // 老系统最后更新系统
    oldLastUpdateUser: string; // 老系统最后更新用户
    oldLastUpdateTm: string | Dayjs; // 老系统最后更新时间
    oldAddressId: string; // 老系统地址ID
  }
}

/** 查询CRM对公客户地址信息分页 */
export function getCompanyAddressPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyAddressApi.CompanyAddress>>(
    '/aicrm/company-address/page',
    { params },
  );
}

/** 查询CRM对公客户地址信息详情 */
export function getCompanyAddress(id: number) {
  return requestClient.get<AicrmCompanyAddressApi.CompanyAddress>(
    `/aicrm/company-address/get?id=${id}`,
  );
}

/** 新增CRM对公客户地址信息 */
export function createCompanyAddress(data: AicrmCompanyAddressApi.CompanyAddress) {
  return requestClient.post('/aicrm/company-address/create', data);
}

/** 修改CRM对公客户地址信息 */
export function updateCompanyAddress(data: AicrmCompanyAddressApi.CompanyAddress) {
  return requestClient.put('/aicrm/company-address/update', data);
}

/** 删除CRM对公客户地址信息 */
export function deleteCompanyAddress(id: number) {
  return requestClient.delete(`/aicrm/company-address/delete?id=${id}`);
}

/** 批量删除CRM对公客户地址信息 */
export function deleteCompanyAddressList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-address/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM对公客户地址信息 */
export function exportCompanyAddress(params: any) {
  return requestClient.download('/aicrm/company-address/export-excel', { params });
}