import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridTingtangCustomerApi } from '#/api/grid/tingtangcustomer';

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
      fieldName: 'gender',
      label: '性别: 男/女',
      component: 'Input',
      componentProps: {
        placeholder: '请输入性别: 男/女',
      },
    },
    {
      fieldName: 'customerGroup',
      label: '客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)',
      },
    },
    {
      fieldName: 'hasCrmNumber',
      label: '是否有CRM客户号 (用于转化统计)',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'crmCustomerId',
      label: 'CRM客户号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入CRM客户号',
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
      fieldName: 'gender',
      label: '性别: 男/女',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入性别: 男/女',
      },
    },
    {
      fieldName: 'customerGroup',
      label: '客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)',
      },
    },
    {
      fieldName: 'hasCrmNumber',
      label: '是否有CRM客户号 (用于转化统计)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否有CRM客户号 (用于转化统计)',
      },
    },
    {
      fieldName: 'crmCustomerId',
      label: 'CRM客户号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入CRM客户号',
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
export function useGridColumns(): VxeTableGridOptions<GridTingtangCustomerApi.TingtangCustomer>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '扩展ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID (关联 grid_customer)',
      minWidth: 120,
    },
    {
      field: 'gender',
      title: '性别: 男/女',
      minWidth: 120,
    },
    {
      field: 'customerGroup',
      title: '客群类型 (关联字典 aicrm_customer_group: 信贷客户/财富客户/结算客户/潜力客户)',
      minWidth: 120,
    },
    {
      field: 'hasCrmNumber',
      title: '是否有CRM客户号 (用于转化统计)',
      minWidth: 120,
    },
    {
      field: 'crmCustomerId',
      title: 'CRM客户号',
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