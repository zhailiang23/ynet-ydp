import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerImportantEventApi } from '#/api/aicrm/customerimportantevent';

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
      label: '客户ID（关联客户主表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联客户主表）',
      },
    },
    {
      fieldName: 'eventName',
      label: '事件名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件名称',
      },
    },
    {
      fieldName: 'eventStatus',
      label: '事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'eventDate',
      label: '事件发生日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'eventContent',
      label: '事件内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'maintainerId',
      label: '维护人ID（关联用户表）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入维护人ID（关联用户表）',
      },
    },
    {
      fieldName: 'maintainerName',
      label: '维护人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入维护人姓名',
      },
    },
    {
      fieldName: 'maintainTime',
      label: '最近维护日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'eventType',
      label: '事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
      },
    },
    {
      fieldName: 'eventLevel',
      label: '事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
      },
    },
    {
      fieldName: 'eventSource',
      label: '事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
      },
    },
    {
      fieldName: 'remindFlag',
      label: '是否提醒（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'remindTime',
      label: '提醒时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'attachmentUrl',
      label: '附件地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入附件地址',
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
      label: '客户ID（关联客户主表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联客户主表）',
      },
    },
    {
      fieldName: 'eventName',
      label: '事件名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件名称',
      },
    },
    {
      fieldName: 'eventStatus',
      label: '事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）',
      },
    },
    {
      fieldName: 'eventDate',
      label: '事件发生日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'eventContent',
      label: '事件内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件内容',
      },
    },
    {
      fieldName: 'maintainerId',
      label: '维护人ID（关联用户表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入维护人ID（关联用户表）',
      },
    },
    {
      fieldName: 'maintainerName',
      label: '维护人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入维护人姓名',
      },
    },
    {
      fieldName: 'maintainTime',
      label: '最近维护日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'eventType',
      label: '事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
      },
    },
    {
      fieldName: 'eventLevel',
      label: '事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
      },
    },
    {
      fieldName: 'eventSource',
      label: '事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
      },
    },
    {
      fieldName: 'remindFlag',
      label: '是否提醒（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否提醒（0-否，1-是）',
      },
    },
    {
      fieldName: 'remindTime',
      label: '提醒时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'attachmentUrl',
      label: '附件地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入附件地址',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerImportantEventApi.CustomerImportantEvent>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联客户主表）',
      minWidth: 120,
    },
    {
      field: 'eventName',
      title: '事件名称',
      minWidth: 120,
    },
    {
      field: 'eventStatus',
      title: '事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）',
      minWidth: 120,
    },
    {
      field: 'eventDate',
      title: '事件发生日期',
      minWidth: 120,
    },
    {
      field: 'eventContent',
      title: '事件内容',
      minWidth: 120,
    },
    {
      field: 'maintainerId',
      title: '维护人ID（关联用户表）',
      minWidth: 120,
    },
    {
      field: 'maintainerName',
      title: '维护人姓名',
      minWidth: 120,
    },
    {
      field: 'maintainTime',
      title: '最近维护日期',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'eventType',
      title: '事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）',
      minWidth: 120,
    },
    {
      field: 'eventLevel',
      title: '事件级别（字典：aicrm_event_level，如：重要、一般、普通）',
      minWidth: 120,
    },
    {
      field: 'eventSource',
      title: '事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）',
      minWidth: 120,
    },
    {
      field: 'remindFlag',
      title: '是否提醒（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'remindTime',
      title: '提醒时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'attachmentUrl',
      title: '附件地址',
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