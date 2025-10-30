import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerChannelBehaviorApi } from '#/api/aicrm/customerchannelbehavior';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 新增/修改的表单 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer 主表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'operationTime',
      label: '操作时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'operationAction',
      label: '操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
      },
    },
    {
      fieldName: 'operationObject',
      label: '操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
      },
    },
    {
      fieldName: 'currentPageIdentifier',
      label: '当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
      },
    },
    {
      fieldName: 'currentPageCode',
      label: '当前页面名称（页面code，如：index、loan、my_page、act_details）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前页面名称（页面code，如：index、loan、my_page、act_details）',
      },
    },
    {
      fieldName: 'currentPageName',
      label: '当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
      },
    },
    {
      fieldName: 'pageStaySeconds',
      label: '页面停留时间(S)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入页面停留时间(S)',
      },
    },
    {
      fieldName: 'description',
      label: '说明',
      component: 'RichTextarea',
    },
    {
      fieldName: 'behaviorNo',
      label: '行为编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行为编号',
      },
    },
    {
      fieldName: 'channelType',
      label: '渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
      },
    },
    {
      fieldName: 'deviceType',
      label: '设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
      },
    },
    {
      fieldName: 'deviceId',
      label: '设备ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入设备ID',
      },
    },
    {
      fieldName: 'deviceModel',
      label: '设备型号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入设备型号',
      },
    },
    {
      fieldName: 'appVersion',
      label: 'APP版本号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入APP版本号',
      },
    },
    {
      fieldName: 'osVersion',
      label: '操作系统版本',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作系统版本',
      },
    },
    {
      fieldName: 'browserType',
      label: '浏览器类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择浏览器类型',
      },
    },
    {
      fieldName: 'browserVersion',
      label: '浏览器版本',
      component: 'Input',
      componentProps: {
        placeholder: '请输入浏览器版本',
      },
    },
    {
      fieldName: 'ipAddress',
      label: 'IP地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入IP地址',
      },
    },
    {
      fieldName: 'ipLocation',
      label: 'IP归属地',
      component: 'Input',
      componentProps: {
        placeholder: '请输入IP归属地',
      },
    },
    {
      fieldName: 'networkType',
      label: '网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
      },
    },
    {
      fieldName: 'sessionId',
      label: '会话ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入会话ID',
      },
    },
    {
      fieldName: 'sessionStartTime',
      label: '会话开始时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'sessionEndTime',
      label: '会话结束时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'previousPageCode',
      label: '上一页面标识',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上一页面标识',
      },
    },
    {
      fieldName: 'previousPageName',
      label: '上一页面名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上一页面名称',
      },
    },
    {
      fieldName: 'nextPageCode',
      label: '下一页面标识',
      component: 'Input',
      componentProps: {
        placeholder: '请输入下一页面标识',
      },
    },
    {
      fieldName: 'nextPageName',
      label: '下一页面名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入下一页面名称',
      },
    },
    {
      fieldName: 'pageUrl',
      label: '页面URL',
      component: 'Input',
      componentProps: {
        placeholder: '请输入页面URL',
      },
    },
    {
      fieldName: 'pageTitle',
      label: '页面标题',
      component: 'Input',
      componentProps: {
        placeholder: '请输入页面标题',
      },
    },
    {
      fieldName: 'pageParams',
      label: '页面参数（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入页面参数（JSON格式）',
      },
    },
    {
      fieldName: 'operationResult',
      label: '操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
      },
    },
    {
      fieldName: 'operationDetail',
      label: '操作详情',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作详情',
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
      },
    },
    {
      fieldName: 'businessModule',
      label: '业务模块',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务模块',
      },
    },
    {
      fieldName: 'businessFunction',
      label: '业务功能',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务功能',
      },
    },
    {
      fieldName: 'isConversion',
      label: '是否转化（是否产生业务）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'conversionType',
      label: '转化类型（字典: aicrm_conversion_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择转化类型（字典: aicrm_conversion_type）',
      },
    },
    {
      fieldName: 'conversionValue',
      label: '转化价值',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转化价值',
      },
    },
    {
      fieldName: 'referSource',
      label: '来源（外部链接、推广活动等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入来源（外部链接、推广活动等）',
      },
    },
    {
      fieldName: 'referMedium',
      label: '媒介',
      component: 'Input',
      componentProps: {
        placeholder: '请输入媒介',
      },
    },
    {
      fieldName: 'referCampaign',
      label: '推广活动',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推广活动',
      },
    },
    {
      fieldName: 'userAgent',
      label: 'User Agent',
      component: 'Input',
      componentProps: {
        placeholder: '请输入User Agent',
      },
    },
    {
      fieldName: 'screenWidth',
      label: '屏幕宽度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入屏幕宽度',
      },
    },
    {
      fieldName: 'screenHeight',
      label: '屏幕高度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入屏幕高度',
      },
    },
    {
      fieldName: 'viewportWidth',
      label: '视口宽度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入视口宽度',
      },
    },
    {
      fieldName: 'viewportHeight',
      label: '视口高度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入视口高度',
      },
    },
    {
      fieldName: 'isNewVisitor',
      label: '是否新访客',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'visitCount',
      label: '访问次数',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入访问次数',
      },
    },
    {
      fieldName: 'bounceRate',
      label: '跳出率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入跳出率（%）',
      },
    },
    {
      fieldName: 'eventCategory',
      label: '事件分类',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件分类',
      },
    },
    {
      fieldName: 'eventLabel',
      label: '事件标签',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件标签',
      },
    },
    {
      fieldName: 'eventValue',
      label: '事件值',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件值',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer 主表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'operationTime',
      label: '操作时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'operationAction',
      label: '操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
      },
    },
    {
      fieldName: 'operationObject',
      label: '操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
      },
    },
    {
      fieldName: 'currentPageIdentifier',
      label: '当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
      },
    },
    {
      fieldName: 'currentPageCode',
      label: '当前页面名称（页面code，如：index、loan、my_page、act_details）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前页面名称（页面code，如：index、loan、my_page、act_details）',
      },
    },
    {
      fieldName: 'currentPageName',
      label: '当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
      },
    },
    {
      fieldName: 'pageStaySeconds',
      label: '页面停留时间(S)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入页面停留时间(S)',
      },
    },
    {
      fieldName: 'description',
      label: '说明',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入说明',
      },
    },
    {
      fieldName: 'behaviorNo',
      label: '行为编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入行为编号',
      },
    },
    {
      fieldName: 'channelType',
      label: '渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
      },
    },
    {
      fieldName: 'deviceType',
      label: '设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
      },
    },
    {
      fieldName: 'deviceId',
      label: '设备ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入设备ID',
      },
    },
    {
      fieldName: 'deviceModel',
      label: '设备型号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入设备型号',
      },
    },
    {
      fieldName: 'appVersion',
      label: 'APP版本号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入APP版本号',
      },
    },
    {
      fieldName: 'osVersion',
      label: '操作系统版本',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作系统版本',
      },
    },
    {
      fieldName: 'browserType',
      label: '浏览器类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择浏览器类型',
      },
    },
    {
      fieldName: 'browserVersion',
      label: '浏览器版本',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入浏览器版本',
      },
    },
    {
      fieldName: 'ipAddress',
      label: 'IP地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入IP地址',
      },
    },
    {
      fieldName: 'ipLocation',
      label: 'IP归属地',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入IP归属地',
      },
    },
    {
      fieldName: 'networkType',
      label: '网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
      },
    },
    {
      fieldName: 'sessionId',
      label: '会话ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入会话ID',
      },
    },
    {
      fieldName: 'sessionStartTime',
      label: '会话开始时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'sessionEndTime',
      label: '会话结束时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'previousPageCode',
      label: '上一页面标识',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上一页面标识',
      },
    },
    {
      fieldName: 'previousPageName',
      label: '上一页面名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上一页面名称',
      },
    },
    {
      fieldName: 'nextPageCode',
      label: '下一页面标识',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入下一页面标识',
      },
    },
    {
      fieldName: 'nextPageName',
      label: '下一页面名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入下一页面名称',
      },
    },
    {
      fieldName: 'pageUrl',
      label: '页面URL',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入页面URL',
      },
    },
    {
      fieldName: 'pageTitle',
      label: '页面标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入页面标题',
      },
    },
    {
      fieldName: 'pageParams',
      label: '页面参数（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入页面参数（JSON格式）',
      },
    },
    {
      fieldName: 'operationResult',
      label: '操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
      },
    },
    {
      fieldName: 'operationDetail',
      label: '操作详情',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作详情',
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
      },
    },
    {
      fieldName: 'businessModule',
      label: '业务模块',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务模块',
      },
    },
    {
      fieldName: 'businessFunction',
      label: '业务功能',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务功能',
      },
    },
    {
      fieldName: 'isConversion',
      label: '是否转化（是否产生业务）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否转化（是否产生业务）',
      },
    },
    {
      fieldName: 'conversionType',
      label: '转化类型（字典: aicrm_conversion_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择转化类型（字典: aicrm_conversion_type）',
      },
    },
    {
      fieldName: 'conversionValue',
      label: '转化价值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转化价值',
      },
    },
    {
      fieldName: 'referSource',
      label: '来源（外部链接、推广活动等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入来源（外部链接、推广活动等）',
      },
    },
    {
      fieldName: 'referMedium',
      label: '媒介',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入媒介',
      },
    },
    {
      fieldName: 'referCampaign',
      label: '推广活动',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推广活动',
      },
    },
    {
      fieldName: 'userAgent',
      label: 'User Agent',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入User Agent',
      },
    },
    {
      fieldName: 'screenWidth',
      label: '屏幕宽度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入屏幕宽度',
      },
    },
    {
      fieldName: 'screenHeight',
      label: '屏幕高度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入屏幕高度',
      },
    },
    {
      fieldName: 'viewportWidth',
      label: '视口宽度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入视口宽度',
      },
    },
    {
      fieldName: 'viewportHeight',
      label: '视口高度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入视口高度',
      },
    },
    {
      fieldName: 'isNewVisitor',
      label: '是否新访客',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否新访客',
      },
    },
    {
      fieldName: 'visitCount',
      label: '访问次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入访问次数',
      },
    },
    {
      fieldName: 'bounceRate',
      label: '跳出率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入跳出率（%）',
      },
    },
    {
      fieldName: 'eventCategory',
      label: '事件分类',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件分类',
      },
    },
    {
      fieldName: 'eventLabel',
      label: '事件标签',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件标签',
      },
    },
    {
      fieldName: 'eventValue',
      label: '事件值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件值',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注',
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '行为主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'operationTime',
      title: '操作时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'operationAction',
      title: '操作行为（字典: aicrm_operation_action，click=点击，login=登录，browse=浏览，query=查询，submit=提交，download=下载，upload=上传，search=搜索）',
      minWidth: 120,
    },
    {
      field: 'operationObject',
      title: '操作对象（字典: aicrm_operation_object，page=页面，button=按钮，input=输入框，link=链接，menu=菜单，form=表单）',
      minWidth: 120,
    },
    {
      field: 'currentPageIdentifier',
      title: '当前页面标识（如：积分、活动详情页、登录、贷款产品页、我的积分等）',
      minWidth: 120,
    },
    {
      field: 'currentPageCode',
      title: '当前页面名称（页面code，如：index、loan、my_page、act_details）',
      minWidth: 120,
    },
    {
      field: 'currentPageName',
      title: '当前页面名称（中文名称，如：首页、我的贷款、我的、我的积分、饭票新人活动详情页）',
      minWidth: 120,
    },
    {
      field: 'pageStaySeconds',
      title: '页面停留时间(S)',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '说明',
      minWidth: 120,
    },
    {
      field: 'behaviorNo',
      title: '行为编号',
      minWidth: 120,
    },
    {
      field: 'channelType',
      title: '渠道类型（字典: aicrm_channel_type，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，crm=CRM系统，counter=柜台，atm=ATM，call_center=电话银行）',
      minWidth: 120,
    },
    {
      field: 'deviceType',
      title: '设备类型（字典: aicrm_device_type，ios=iOS，android=Android，web=网页，h5=H5，pc=PC）',
      minWidth: 120,
    },
    {
      field: 'deviceId',
      title: '设备ID',
      minWidth: 120,
    },
    {
      field: 'deviceModel',
      title: '设备型号',
      minWidth: 120,
    },
    {
      field: 'appVersion',
      title: 'APP版本号',
      minWidth: 120,
    },
    {
      field: 'osVersion',
      title: '操作系统版本',
      minWidth: 120,
    },
    {
      field: 'browserType',
      title: '浏览器类型',
      minWidth: 120,
    },
    {
      field: 'browserVersion',
      title: '浏览器版本',
      minWidth: 120,
    },
    {
      field: 'ipAddress',
      title: 'IP地址',
      minWidth: 120,
    },
    {
      field: 'ipLocation',
      title: 'IP归属地',
      minWidth: 120,
    },
    {
      field: 'networkType',
      title: '网络类型（字典: aicrm_network_type，wifi=WiFi，4g=4G，5g=5G，ethernet=有线网络）',
      minWidth: 120,
    },
    {
      field: 'sessionId',
      title: '会话ID',
      minWidth: 120,
    },
    {
      field: 'sessionStartTime',
      title: '会话开始时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'sessionEndTime',
      title: '会话结束时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'previousPageCode',
      title: '上一页面标识',
      minWidth: 120,
    },
    {
      field: 'previousPageName',
      title: '上一页面名称',
      minWidth: 120,
    },
    {
      field: 'nextPageCode',
      title: '下一页面标识',
      minWidth: 120,
    },
    {
      field: 'nextPageName',
      title: '下一页面名称',
      minWidth: 120,
    },
    {
      field: 'pageUrl',
      title: '页面URL',
      minWidth: 120,
    },
    {
      field: 'pageTitle',
      title: '页面标题',
      minWidth: 120,
    },
    {
      field: 'pageParams',
      title: '页面参数（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'operationResult',
      title: '操作结果（字典: aicrm_operation_result，success=成功，failed=失败，cancelled=取消）',
      minWidth: 120,
    },
    {
      field: 'operationDetail',
      title: '操作详情',
      minWidth: 120,
    },
    {
      field: 'businessType',
      title: '业务类型（字典: aicrm_behavior_business_type，account=账户，loan=贷款，wealth=理财，transfer=转账，payment=支付，points=积分，activity=活动）',
      minWidth: 120,
    },
    {
      field: 'businessModule',
      title: '业务模块',
      minWidth: 120,
    },
    {
      field: 'businessFunction',
      title: '业务功能',
      minWidth: 120,
    },
    {
      field: 'isConversion',
      title: '是否转化（是否产生业务）',
      minWidth: 120,
    },
    {
      field: 'conversionType',
      title: '转化类型（字典: aicrm_conversion_type）',
      minWidth: 120,
    },
    {
      field: 'conversionValue',
      title: '转化价值',
      minWidth: 120,
    },
    {
      field: 'referSource',
      title: '来源（外部链接、推广活动等）',
      minWidth: 120,
    },
    {
      field: 'referMedium',
      title: '媒介',
      minWidth: 120,
    },
    {
      field: 'referCampaign',
      title: '推广活动',
      minWidth: 120,
    },
    {
      field: 'userAgent',
      title: 'User Agent',
      minWidth: 120,
    },
    {
      field: 'screenWidth',
      title: '屏幕宽度',
      minWidth: 120,
    },
    {
      field: 'screenHeight',
      title: '屏幕高度',
      minWidth: 120,
    },
    {
      field: 'viewportWidth',
      title: '视口宽度',
      minWidth: 120,
    },
    {
      field: 'viewportHeight',
      title: '视口高度',
      minWidth: 120,
    },
    {
      field: 'isNewVisitor',
      title: '是否新访客',
      minWidth: 120,
    },
    {
      field: 'visitCount',
      title: '访问次数',
      minWidth: 120,
    },
    {
      field: 'bounceRate',
      title: '跳出率（%）',
      minWidth: 120,
    },
    {
      field: 'eventCategory',
      title: '事件分类',
      minWidth: 120,
    },
    {
      field: 'eventLabel',
      title: '事件标签',
      minWidth: 120,
    },
    {
      field: 'eventValue',
      title: '事件值',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}