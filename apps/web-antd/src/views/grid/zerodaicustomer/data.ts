import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridZerodaiCustomerApi } from '#/api/grid/zerodaicustomer';

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
      fieldName: 'businessType',
      label: '业务类型 (关联字典 aicrm_business_type)',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择业务类型 (关联字典 aicrm_business_type)',
      },
    },
    {
      fieldName: 'annualRevenue',
      label: '年营业额 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年营业额 (元)',
      },
    },
    {
      fieldName: 'creditRating',
      label: '信用评级',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用评级',
      },
    },
    {
      fieldName: 'isApplied',
      label: '是否进件',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isCompleted',
      label: '是否完件',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款投放金额 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款投放金额 (元)',
      },
    },
    {
      fieldName: 'loanBalance',
      label: '贷款余额 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额 (元)',
      },
    },
    {
      fieldName: 'archiveProtected',
      label: '是否受保护',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      fieldName: 'businessType',
      label: '业务类型 (关联字典 aicrm_business_type)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择业务类型 (关联字典 aicrm_business_type)',
      },
    },
    {
      fieldName: 'annualRevenue',
      label: '年营业额 (元)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年营业额 (元)',
      },
    },
    {
      fieldName: 'creditRating',
      label: '信用评级',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信用评级',
      },
    },
    {
      fieldName: 'isApplied',
      label: '是否进件',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否进件',
      },
    },
    {
      fieldName: 'isCompleted',
      label: '是否完件',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否完件',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款投放金额 (元)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款投放金额 (元)',
      },
    },
    {
      fieldName: 'loanBalance',
      label: '贷款余额 (元)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款余额 (元)',
      },
    },
    {
      fieldName: 'archiveProtected',
      label: '是否受保护',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否受保护',
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
export function useGridColumns(): VxeTableGridOptions<GridZerodaiCustomerApi.ZerodaiCustomer>['columns'] {
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
      field: 'businessType',
      title: '业务类型 (关联字典 aicrm_business_type)',
      minWidth: 120,
    },
    {
      field: 'annualRevenue',
      title: '年营业额 (元)',
      minWidth: 120,
    },
    {
      field: 'creditRating',
      title: '信用评级',
      minWidth: 120,
    },
    {
      field: 'isApplied',
      title: '是否进件',
      minWidth: 120,
    },
    {
      field: 'isCompleted',
      title: '是否完件',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款投放金额 (元)',
      minWidth: 120,
    },
    {
      field: 'loanBalance',
      title: '贷款余额 (元)',
      minWidth: 120,
    },
    {
      field: 'archiveProtected',
      title: '是否受保护',
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