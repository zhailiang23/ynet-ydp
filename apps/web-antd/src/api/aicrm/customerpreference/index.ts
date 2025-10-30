import { requestClient } from '#/api/request';

export namespace AicrmCustomerPreferenceApi {
  /** 客户偏好信息 */
  export interface CustomerPreference {
    id?: number; // 主键ID
    customerId: number; // 客户ID
    electronicChannels?: string; // 电子渠道（多选，逗号分隔）
    otherChannel?: string; // 其他电子渠道
    investmentTypes?: string; // 投资类型（多选，逗号分隔）
    otherInvestmentType?: string; // 其他投资类型
    brandTypes?: string; // 品牌类型（多选，逗号分隔）
    otherBrandType?: string; // 其他品牌类型
    financialServices?: string; // 理财服务（多选，逗号分隔）
    otherFinancialService?: string; // 其他理财服务
    contactMethods?: string; // 联系方式（多选，逗号分隔）
    otherContactMethod?: string; // 其他联系方式
    salonActivities?: string; // 沙龙活动（多选，逗号分隔）
    otherSalonActivity?: string; // 其他沙龙活动
    interestHobbies?: string; // 兴趣爱好（多选，逗号分隔）
    otherInterestHobby?: string; // 其他兴趣爱好
    contactTimes?: string; // 联系时间（多选，逗号分隔）
    otherContactTime?: string; // 其他联系时间
    investmentPeriods?: string; // 投资周期（多选，逗号分隔）
    otherInvestmentPeriod?: string; // 其他投资周期
    createTime?: string; // 创建时间
    updateTime?: string; // 更新时间
  }
}

/** 保存客户偏好（创建或更新） */
export function saveCustomerPreference(
  data: AicrmCustomerPreferenceApi.CustomerPreference,
) {
  return requestClient.post<number>('/aicrm/customer-preference/save', data);
}

/** 根据客户ID获取客户偏好 */
export function getCustomerPreferenceByCustomerId(customerId: number) {
  return requestClient.get<AicrmCustomerPreferenceApi.CustomerPreference>(
    `/aicrm/customer-preference/get-by-customer-id?customerId=${customerId}`,
  );
}

/** 删除客户偏好 */
export function deleteCustomerPreference(id: number) {
  return requestClient.delete<boolean>(
    `/aicrm/customer-preference/delete?id=${id}`,
  );
}
