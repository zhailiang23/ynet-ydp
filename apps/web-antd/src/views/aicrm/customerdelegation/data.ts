import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDelegationApi } from '#/api/aicrm/customerdelegation';

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
      label: '客户ID（关联 crm_customer）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer）',
      },
    },
    {
      fieldName: 'fromUserId',
      label: '托管方客户经理ID（关联 system_users.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入托管方客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'toUserId',
      label: '受托方客户经理ID（关联 system_users.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入受托方客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'startDate',
      label: '托管开始日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endDate',
      label: '托管结束日期（计划）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'actualEndDate',
      label: '实际结束日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'delegationReason',
      label: '托管原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入托管原因',
      },
    },
    {
      fieldName: 'delegationStatus',
      label: '托管状态（0=已结束，1=托管中，2=已取消）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer）',
      },
    },
    {
      fieldName: 'fromUserId',
      label: '托管方客户经理ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入托管方客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'toUserId',
      label: '受托方客户经理ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入受托方客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'startDate',
      label: '托管开始日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'endDate',
      label: '托管结束日期（计划）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'actualEndDate',
      label: '实际结束日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'delegationReason',
      label: '托管原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入托管原因',
      },
    },
    {
      fieldName: 'delegationStatus',
      label: '托管状态（0=已结束，1=托管中，2=已取消）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择托管状态（0=已结束，1=托管中，2=已取消）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerDelegationApi.CustomerDelegation>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '托管ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer）',
      minWidth: 120,
    },
    {
      field: 'fromUserId',
      title: '托管方客户经理ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'toUserId',
      title: '受托方客户经理ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'startDate',
      title: '托管开始日期',
      minWidth: 120,
    },
    {
      field: 'endDate',
      title: '托管结束日期（计划）',
      minWidth: 120,
    },
    {
      field: 'actualEndDate',
      title: '实际结束日期',
      minWidth: 120,
    },
    {
      field: 'delegationReason',
      title: '托管原因',
      minWidth: 120,
    },
    {
      field: 'delegationStatus',
      title: '托管状态（0=已结束，1=托管中，2=已取消）',
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