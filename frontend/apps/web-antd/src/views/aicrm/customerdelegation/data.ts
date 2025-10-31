import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDelegationApi } from '#/api/aicrm/customerdelegation';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'fromUserId',
      label: '托管方经理ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入托管方客户经理ID',
      },
    },
    {
      fieldName: 'toUserId',
      label: '受托方经理ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入受托方客户经理ID',
      },
    },
    {
      fieldName: 'delegationStatus',
      label: '托管状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_delegation_status'),
        placeholder: '请选择托管状态',
      },
    },
    {
      fieldName: 'startDate',
      label: '托管开始日期',
      component: 'RangePicker',
      componentProps: getRangePickerDefaultProps(),
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerDelegationApi.CustomerDelegation>['columns'] {
  return [
    {
      field: 'id',
      title: '托管ID',
      minWidth: 80,
      fixed: 'left',
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 100,
    },
    {
      field: 'fromUserId',
      title: '托管方经理ID',
      minWidth: 120,
    },
    {
      field: 'toUserId',
      title: '受托方经理ID',
      minWidth: 120,
    },
    {
      field: 'startDate',
      title: '托管开始日期',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleDateString('zh-CN');
      },
    },
    {
      field: 'endDate',
      title: '托管结束日期',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleDateString('zh-CN');
      },
    },
    {
      field: 'delegationStatus',
      title: '托管状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_delegation_status' },
      },
    },
    {
      field: 'reason',
      title: '托管原因',
      minWidth: 140,
      showOverflow: 'tooltip',
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleString('zh-CN');
      },
    },
    {
      field: 'action',
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 创建托管表单 */
export function useCreateFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'toUserId',
      label: '受托方客户经理ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入受托方客户经理ID',
      },
    },
    {
      fieldName: 'startDate',
      label: '托管开始日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        placeholder: '请选择托管开始日期',
        style: { width: '100%' },
      },
    },
    {
      fieldName: 'endDate',
      label: '托管结束日期',
      component: 'DatePicker',
      componentProps: {
        placeholder: '请选择托管结束日期（可选）',
        style: { width: '100%' },
      },
    },
    {
      fieldName: 'reason',
      label: '托管原因',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入托管原因',
        rows: 3,
      },
    },
  ];
}

/** 结束托管表单 */
export function useEndFormSchema(): VbenFormSchema[] {
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
      fieldName: 'endReason',
      label: '结束原因',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入结束原因',
        rows: 3,
      },
    },
  ];
}
