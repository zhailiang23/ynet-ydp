import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerContractApi {
  /** 客户签约信息表（零售+对公共用）信息 */
  export interface CustomerContract {
    id: number; // 签约主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    contractTypeId?: number; // 签约类型ID（关联 crm_contract_type.id）
    contractNo?: string; // 签约账号/签约编号
    contractDate?: string | Dayjs; // 签约日期
    branchId: number; // 签约机构ID（关联 system_dept.id）
    branchName: string; // 签约机构名称
    contractStatus?: string; // 签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）
    contractSituation: string; // 签约情况（具体的签约渠道或产品名称）
    identityType: string; // 证件类型（字典: aicrm_identity_type）
    identityNo: string; // 证件号码（加密存储）
    accountNo: string; // 关联账号
    startDate: string | Dayjs; // 生效日期
    endDate: string | Dayjs; // 失效日期
    signChannel: string; // 签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）
    cancelDate: string | Dayjs; // 解约日期
    cancelReason: string; // 解约原因
    managerUserId: number; // 客户经理ID（关联 system_users.id）
    remark: string; // 备注
  }
}

/** 查询客户签约信息表（零售+对公共用）分页 */
export function getCustomerContractPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerContractApi.CustomerContract>>(
    '/aicrm/customer-contract/page',
    { params },
  );
}

/** 查询客户签约信息表（零售+对公共用）详情 */
export function getCustomerContract(id: number) {
  return requestClient.get<AicrmCustomerContractApi.CustomerContract>(
    `/aicrm/customer-contract/get?id=${id}`,
  );
}

/** 新增客户签约信息表（零售+对公共用） */
export function createCustomerContract(data: AicrmCustomerContractApi.CustomerContract) {
  return requestClient.post('/aicrm/customer-contract/create', data);
}

/** 修改客户签约信息表（零售+对公共用） */
export function updateCustomerContract(data: AicrmCustomerContractApi.CustomerContract) {
  return requestClient.put('/aicrm/customer-contract/update', data);
}

/** 删除客户签约信息表（零售+对公共用） */
export function deleteCustomerContract(id: number) {
  return requestClient.delete(`/aicrm/customer-contract/delete?id=${id}`);
}

/** 批量删除客户签约信息表（零售+对公共用） */
export function deleteCustomerContractList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-contract/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户签约信息表（零售+对公共用） */
export function exportCustomerContract(params: any) {
  return requestClient.download('/aicrm/customer-contract/export-excel', { params });
}