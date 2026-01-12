import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridCustomerArchiveProtectionApi } from '#/api/grid/customerarchiveprotection';

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
      label: '客户ID (关联 grid_customer)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID (关联 grid_customer)',
      },
    },
    {
      fieldName: 'protectionType',
      label: '保护类型: CLAIMED/TIME_BASED',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择保护类型: CLAIMED/TIME_BASED',
      },
    },
    {
      fieldName: 'originalManagerId',
      label: '原客户经理ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原客户经理ID',
      },
    },
    {
      fieldName: 'claimTime',
      label: '认领时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'protectionStartTime',
      label: '保护开始时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'protectionEndTime',
      label: '保护结束时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isProtected',
      label: '是否仍受保护',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'releaseReason',
      label: '解除保护原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入解除保护原因',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建人ID',
      },
    },
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID (关联 grid_customer)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID (关联 grid_customer)',
      },
    },
    {
      fieldName: 'protectionType',
      label: '保护类型: CLAIMED/TIME_BASED',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择保护类型: CLAIMED/TIME_BASED',
      },
    },
    {
      fieldName: 'originalManagerId',
      label: '原客户经理ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入原客户经理ID',
      },
    },
    {
      fieldName: 'claimTime',
      label: '认领时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'protectionStartTime',
      label: '保护开始时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'protectionEndTime',
      label: '保护结束时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isProtected',
      label: '是否仍受保护',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否仍受保护',
      },
    },
    {
      fieldName: 'releaseReason',
      label: '解除保护原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入解除保护原因',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建人ID',
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
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridCustomerArchiveProtectionApi.CustomerArchiveProtection>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '保护记录ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID (关联 grid_customer)',
      minWidth: 120,
    },
    {
      field: 'protectionType',
      title: '保护类型: CLAIMED/TIME_BASED',
      minWidth: 120,
    },
    {
      field: 'originalManagerId',
      title: '原客户经理ID',
      minWidth: 120,
    },
    {
      field: 'claimTime',
      title: '认领时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'protectionStartTime',
      title: '保护开始时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'protectionEndTime',
      title: '保护结束时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isProtected',
      title: '是否仍受保护',
      minWidth: 120,
    },
    {
      field: 'releaseReason',
      title: '解除保护原因',
      minWidth: 120,
    },
    {
      field: 'creatorId',
      title: '创建人ID',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'updaterId',
      title: '更新人ID',
      minWidth: 120,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}