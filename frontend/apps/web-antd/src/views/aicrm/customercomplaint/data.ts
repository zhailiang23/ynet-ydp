import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerComplaintApi } from '#/api/aicrm/customercomplaint';

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
      fieldName: 'workOrderNo',
      label: '工单编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工单编号',
      },
    },
    {
      fieldName: 'ecifCustomerNo',
      label: 'ECIF客户号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入ECIF客户号',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'workOrderStatus',
      label: '工单处理状态（字典: aicrm_complaint_status）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'lastProcessTime',
      label: '最近处理时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'complaintChannel',
      label: '投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
      },
    },
    {
      fieldName: 'complaintType',
      label: '投诉类型（字典: aicrm_complaint_type）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择投诉类型（字典: aicrm_complaint_type）',
      },
    },
    {
      fieldName: 'complaintContent',
      label: '投诉内容',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'complaintTime',
      label: '投诉时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'complaintLevel',
      label: '投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
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
      fieldName: 'processResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理结果',
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
      fieldName: 'isReopened',
      label: '是否重新打开',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'reopenTime',
      label: '重新打开时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'reopenReason',
      label: '重新打开原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入重新打开原因',
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
      fieldName: 'workOrderNo',
      label: '工单编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工单编号',
      },
    },
    {
      fieldName: 'ecifCustomerNo',
      label: 'ECIF客户号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入ECIF客户号',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'workOrderStatus',
      label: '工单处理状态（字典: aicrm_complaint_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择工单处理状态（字典: aicrm_complaint_status）',
      },
    },
    {
      fieldName: 'lastProcessTime',
      label: '最近处理时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'complaintChannel',
      label: '投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
      },
    },
    {
      fieldName: 'complaintType',
      label: '投诉类型（字典: aicrm_complaint_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择投诉类型（字典: aicrm_complaint_type）',
      },
    },
    {
      fieldName: 'complaintContent',
      label: '投诉内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入投诉内容',
      },
    },
    {
      fieldName: 'complaintTime',
      label: '投诉时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'complaintLevel',
      label: '投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
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
      fieldName: 'processResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理结果',
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
      fieldName: 'isReopened',
      label: '是否重新打开',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否重新打开',
      },
    },
    {
      fieldName: 'reopenTime',
      label: '重新打开时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'reopenReason',
      label: '重新打开原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入重新打开原因',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerComplaintApi.CustomerComplaint>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '投诉主键',
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
      field: 'workOrderNo',
      title: '工单编号',
      minWidth: 120,
    },
    {
      field: 'ecifCustomerNo',
      title: 'ECIF客户号',
      minWidth: 120,
    },
    {
      field: 'customerName',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'workOrderStatus',
      title: '工单处理状态（字典: aicrm_complaint_status）',
      minWidth: 120,
    },
    {
      field: 'lastProcessTime',
      title: '最近处理时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'complaintChannel',
      title: '投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
      minWidth: 120,
    },
    {
      field: 'complaintType',
      title: '投诉类型（字典: aicrm_complaint_type）',
      minWidth: 120,
    },
    {
      field: 'complaintContent',
      title: '投诉内容',
      minWidth: 120,
    },
    {
      field: 'complaintTime',
      title: '投诉时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'complaintLevel',
      title: '投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
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
      field: 'processResult',
      title: '处理结果',
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
      field: 'isReopened',
      title: '是否重新打开',
      minWidth: 120,
    },
    {
      field: 'reopenTime',
      title: '重新打开时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'reopenReason',
      title: '重新打开原因',
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