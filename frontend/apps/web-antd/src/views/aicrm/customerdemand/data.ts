import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDemandApi } from '#/api/aicrm/customerdemand';

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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'demandChannel',
      label: '需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
      },
    },
    {
      fieldName: 'demandContent',
      label: '需求内容',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'submitTime',
      label: '提交时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isProcessed',
      label: '是否处理（0=未处理，1=已处理）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'demandNo',
      label: '需求编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求编号',
      },
    },
    {
      fieldName: 'demandType',
      label: '需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
      },
    },
    {
      fieldName: 'demandStatus',
      label: '需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'demandPriority',
      label: '需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
      },
    },
    {
      fieldName: 'demandSource',
      label: '需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '处理人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '处理人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理人姓名',
      },
    },
    {
      fieldName: 'handlerDeptId',
      label: '处理部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '处理部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理部门名称',
      },
    },
    {
      fieldName: 'processStartTime',
      label: '开始处理时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'processEndTime',
      label: '处理完成时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'processDuration',
      label: '处理时长（分钟）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理时长（分钟）',
      },
    },
    {
      fieldName: 'processResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理结果',
      },
    },
    {
      fieldName: 'customerSatisfaction',
      label: '客户满意度（1-5星）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户满意度（1-5星）',
      },
    },
    {
      fieldName: 'satisfactionFeedback',
      label: '满意度反馈',
      component: 'Input',
      componentProps: {
        placeholder: '请输入满意度反馈',
      },
    },
    {
      fieldName: 'followUpRequired',
      label: '是否需要跟进',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'followUpTime',
      label: '跟进时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'followUpContent',
      label: '跟进内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'relatedProduct',
      label: '相关产品',
      component: 'Input',
      componentProps: {
        placeholder: '请输入相关产品',
      },
    },
    {
      fieldName: 'expectedAmount',
      label: '预期金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预期金额（元）',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实际成交金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际成交金额（元）',
      },
    },
    {
      fieldName: 'isConverted',
      label: '是否已转化',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'convertTime',
      label: '转化时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isClosed',
      label: '是否已关闭',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'closeTime',
      label: '关闭时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'closeReason',
      label: '关闭原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关闭原因',
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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'demandChannel',
      label: '需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
      },
    },
    {
      fieldName: 'demandContent',
      label: '需求内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入需求内容',
      },
    },
    {
      fieldName: 'submitTime',
      label: '提交时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isProcessed',
      label: '是否处理（0=未处理，1=已处理）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否处理（0=未处理，1=已处理）',
      },
    },
    {
      fieldName: 'demandNo',
      label: '需求编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入需求编号',
      },
    },
    {
      fieldName: 'demandType',
      label: '需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
      },
    },
    {
      fieldName: 'demandStatus',
      label: '需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）',
      },
    },
    {
      fieldName: 'demandPriority',
      label: '需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
      },
    },
    {
      fieldName: 'demandSource',
      label: '需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '处理人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '处理人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理人姓名',
      },
    },
    {
      fieldName: 'handlerDeptId',
      label: '处理部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '处理部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理部门名称',
      },
    },
    {
      fieldName: 'processStartTime',
      label: '开始处理时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'processEndTime',
      label: '处理完成时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'processDuration',
      label: '处理时长（分钟）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理时长（分钟）',
      },
    },
    {
      fieldName: 'processResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理结果',
      },
    },
    {
      fieldName: 'customerSatisfaction',
      label: '客户满意度（1-5星）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户满意度（1-5星）',
      },
    },
    {
      fieldName: 'satisfactionFeedback',
      label: '满意度反馈',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入满意度反馈',
      },
    },
    {
      fieldName: 'followUpRequired',
      label: '是否需要跟进',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否需要跟进',
      },
    },
    {
      fieldName: 'followUpTime',
      label: '跟进时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'followUpContent',
      label: '跟进内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入跟进内容',
      },
    },
    {
      fieldName: 'relatedProduct',
      label: '相关产品',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入相关产品',
      },
    },
    {
      fieldName: 'expectedAmount',
      label: '预期金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预期金额（元）',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实际成交金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际成交金额（元）',
      },
    },
    {
      fieldName: 'isConverted',
      label: '是否已转化',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已转化',
      },
    },
    {
      fieldName: 'convertTime',
      label: '转化时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isClosed',
      label: '是否已关闭',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已关闭',
      },
    },
    {
      fieldName: 'closeTime',
      label: '关闭时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeReason',
      label: '关闭原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关闭原因',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerDemandApi.CustomerDemand>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '需求主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'sequenceNo',
      title: '序号',
      minWidth: 120,
    },
    {
      field: 'demandChannel',
      title: '需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
      minWidth: 120,
    },
    {
      field: 'demandContent',
      title: '需求内容',
      minWidth: 120,
    },
    {
      field: 'submitTime',
      title: '提交时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isProcessed',
      title: '是否处理（0=未处理，1=已处理）',
      minWidth: 120,
    },
    {
      field: 'demandNo',
      title: '需求编号',
      minWidth: 120,
    },
    {
      field: 'demandType',
      title: '需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
      minWidth: 120,
    },
    {
      field: 'demandStatus',
      title: '需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）',
      minWidth: 120,
    },
    {
      field: 'demandPriority',
      title: '需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
      minWidth: 120,
    },
    {
      field: 'demandSource',
      title: '需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
      minWidth: 120,
    },
    {
      field: 'handlerUserId',
      title: '处理人ID',
      minWidth: 120,
    },
    {
      field: 'handlerUserName',
      title: '处理人姓名',
      minWidth: 120,
    },
    {
      field: 'handlerDeptId',
      title: '处理部门ID',
      minWidth: 120,
    },
    {
      field: 'handlerDeptName',
      title: '处理部门名称',
      minWidth: 120,
    },
    {
      field: 'processStartTime',
      title: '开始处理时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'processEndTime',
      title: '处理完成时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'processDuration',
      title: '处理时长（分钟）',
      minWidth: 120,
    },
    {
      field: 'processResult',
      title: '处理结果',
      minWidth: 120,
    },
    {
      field: 'customerSatisfaction',
      title: '客户满意度（1-5星）',
      minWidth: 120,
    },
    {
      field: 'satisfactionFeedback',
      title: '满意度反馈',
      minWidth: 120,
    },
    {
      field: 'followUpRequired',
      title: '是否需要跟进',
      minWidth: 120,
    },
    {
      field: 'followUpTime',
      title: '跟进时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'followUpContent',
      title: '跟进内容',
      minWidth: 120,
    },
    {
      field: 'relatedProduct',
      title: '相关产品',
      minWidth: 120,
    },
    {
      field: 'expectedAmount',
      title: '预期金额（元）',
      minWidth: 120,
    },
    {
      field: 'actualAmount',
      title: '实际成交金额（元）',
      minWidth: 120,
    },
    {
      field: 'isConverted',
      title: '是否已转化',
      minWidth: 120,
    },
    {
      field: 'convertTime',
      title: '转化时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isClosed',
      title: '是否已关闭',
      minWidth: 120,
    },
    {
      field: 'closeTime',
      title: '关闭时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'closeReason',
      title: '关闭原因',
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