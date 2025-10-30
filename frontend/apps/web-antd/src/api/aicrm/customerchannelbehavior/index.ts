import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerChannelBehaviorApi {
  /** 客户渠道行为信息信息 */
  export interface CustomerChannelBehavior {
    id: number; // 行为主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    operationTime?: string | Dayjs; // 操作时间
    operationAction?: string; // 操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）
    operationObject?: string; // 操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）
    currentPageIdentifier?: string; // 当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）
    currentPageCode: string; // 当前页面名称（页面code，如：index、loan、my_page、act_details）
    currentPageName?: string; // 当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）
    pageStaySeconds?: number; // 页面停留时间(S)
    description: string; // 说明
    behaviorNo?: string; // 行为编号
    channelType?: string; // 渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）
    deviceType: string; // 设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）
    deviceId: string; // 设备ID
    deviceModel: string; // 设备型号
    appVersion: string; // APP版本号
    osVersion: string; // 操作系统版本
    browserType: string; // 浏览器类型
    browserVersion: string; // 浏览器版本
    ipAddress: string; // IP地址
    ipLocation: string; // IP归属地
    networkType: string; // 网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）
    sessionId: string; // 会话ID
    sessionStartTime: string | Dayjs; // 会话开始时间
    sessionEndTime: string | Dayjs; // 会话结束时间
    previousPageCode: string; // 上一页面标识
    previousPageName: string; // 上一页面名称
    nextPageCode: string; // 下一页面标识
    nextPageName: string; // 下一页面名称
    pageUrl: string; // 页面URL
    pageTitle: string; // 页面标题
    pageParams: string; // 页面参数（JSON格式）
    operationResult: string; // 操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）
    operationDetail: string; // 操作详情
    businessType: string; // 业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）
    businessModule: string; // 业务模块
    businessFunction: string; // 业务功能
    isConversion?: boolean; // 是否转化（是否产生业务）
    conversionType: string; // 转化类型（字典: aicrm_conversion_type）
    conversionValue: number; // 转化价值
    referSource: string; // 来源（外部链接、推广活动等）
    referMedium: string; // 媒介
    referCampaign: string; // 推广活动
    userAgent: string; // User Agent
    screenWidth: number; // 屏幕宽度
    screenHeight: number; // 屏幕高度
    viewportWidth: number; // 视口宽度
    viewportHeight: number; // 视口高度
    isNewVisitor: boolean; // 是否新访客
    visitCount?: number; // 访问次数
    bounceRate: number; // 跳出率（%）
    eventCategory: string; // 事件分类
    eventLabel: string; // 事件标签
    eventValue: string; // 事件值
    remark: string; // 备注
  }
}

/** 查询客户渠道行为信息分页 */
export function getCustomerChannelBehaviorPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior>>(
    '/aicrm/customer-channel-behavior/page',
    { params },
  );
}

/** 查询客户渠道行为信息详情 */
export function getCustomerChannelBehavior(id: number) {
  return requestClient.get<AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior>(
    `/aicrm/customer-channel-behavior/get?id=${id}`,
  );
}

/** 新增客户渠道行为信息 */
export function createCustomerChannelBehavior(data: AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior) {
  return requestClient.post('/aicrm/customer-channel-behavior/create', data);
}

/** 修改客户渠道行为信息 */
export function updateCustomerChannelBehavior(data: AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior) {
  return requestClient.put('/aicrm/customer-channel-behavior/update', data);
}

/** 删除客户渠道行为信息 */
export function deleteCustomerChannelBehavior(id: number) {
  return requestClient.delete(`/aicrm/customer-channel-behavior/delete?id=${id}`);
}

/** 批量删除客户渠道行为信息 */
export function deleteCustomerChannelBehaviorList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-channel-behavior/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户渠道行为信息 */
export function exportCustomerChannelBehavior(params: any) {
  return requestClient.download('/aicrm/customer-channel-behavior/export-excel', { params });
}