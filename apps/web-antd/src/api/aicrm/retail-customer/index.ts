import type { PageParam, PageResult } from '@vben/request';

import { requestClient } from '#/api/request';

export namespace RetailCustomerApi {
  /** 零售客户信息 */
  export interface RetailCustomer {
    // RetailCustomerDO 字段
    id: number;
    customerId?: number; // 关联的客户主表ID
    nickname?: string; // 昵称/别名
    gender?: number; // 性别(1=男, 2=女, 3=其他)
    birthday?: string; // 出生日期
    idCardType?: string; // 证件类型
    idCardNo?: string; // 证件号码
    nationality?: string; // 国籍
    nativePlace?: string; // 籍贯
    maritalStatus?: string; // 婚姻状况
    education?: string; // 学历
    occupation?: string; // 职业
    employerName?: string; // 工作单位名称
    position?: string; // 职位
    annualIncome?: number; // 年收入
    assets?: number; // 资产总额
    liabilities?: number; // 负债总额

    // VIP信息
    isVip?: boolean; // 是否VIP客户
    vipLevel?: string; // VIP等级
    vipStartDate?: string; // VIP开始日期
    vipEndDate?: string; // VIP到期日期
    vipPoints?: number; // 积分

    // 收入与资产信息
    monthlyIncome?: number; // 月收入
    sourceOfIncome?: string; // 收入来源
    hasHouse?: boolean; // 是否有房产
    hasCar?: boolean; // 是否有车
    hasInsurance?: boolean; // 是否有保险

    // 信誉信息
    hasLoanRecord?: boolean; // 是否有贷款记录
    hasOverdueRecord?: boolean; // 是否有逾期记录
    blacklistFlag?: boolean; // 黑名单标志

    // CustomerDO 共有字段
    customerNo?: string; // 客户编号
    customerType?: number; // 客户类型
    customerName?: string; // 客户名称
    customerLevel?: string; // 客户等级
    customerStatus?: number; // 客户状态
    isHighQuality?: boolean; // 是否优质客户
    isImportant?: boolean; // 是否重要客户
    creditStatus?: string; // 信用状态
    creditLevel?: string; // 信用等级
    creditScore?: number; // 信用评分
    customerSource?: string; // 客户来源
    customerTag?: string; // 客户标签
    remark?: string; // 备注信息
    deptId?: number; // 所属部门ID
    createTime?: string; // 创建时间
    updateTime?: string; // 更新时间
  }
}

/** 查询零售客户详情 */
export function getRetailCustomer(id: number) {
  return requestClient.get<RetailCustomerApi.RetailCustomer>(
    `/crm/retail-customer/get?id=${id}`,
  );
}

/** 查询零售客户分页 */
export function getRetailCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<RetailCustomerApi.RetailCustomer>>(
    '/crm/retail-customer/page',
    { params },
  );
}
