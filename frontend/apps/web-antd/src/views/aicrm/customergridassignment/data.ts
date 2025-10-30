import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGridAssignmentApi } from '#/api/aicrm/customergridassignment';

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
      fieldName: 'gridId',
      label: '网格ID（关联 crm_grid_info.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格ID（关联 crm_grid_info.id）',
      },
    },
    {
      fieldName: 'assignDate',
      label: '分配日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'assignOperatorId',
      label: '分配操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入分配操作人用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'status',
      label: '归属状态（0=已失效，1=生效中）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      fieldName: 'gridId',
      label: '网格ID（关联 crm_grid_info.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格ID（关联 crm_grid_info.id）',
      },
    },
    {
      fieldName: 'assignDate',
      label: '分配日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'assignOperatorId',
      label: '分配操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入分配操作人用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'status',
      label: '归属状态（0=已失效，1=生效中）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择归属状态（0=已失效，1=生效中）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGridAssignmentApi.CustomerGridAssignment>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '归属网格关系主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '网格ID（关联 crm_grid_info.id）',
      minWidth: 120,
    },
    {
      field: 'assignDate',
      title: '分配日期',
      minWidth: 120,
    },
    {
      field: 'assignOperatorId',
      title: '分配操作人用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '归属状态（0=已失效，1=生效中）',
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