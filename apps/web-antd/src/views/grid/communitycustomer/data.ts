import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridCommunityCustomerApi } from '#/api/grid/communitycustomer';

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
      fieldName: 'familyMembers',
      label: '家庭成员数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭成员数',
      },
    },
    {
      fieldName: 'housingType',
      label: '住房类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择住房类型',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月收入 (元)',
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
      fieldName: 'familyMembers',
      label: '家庭成员数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭成员数',
      },
    },
    {
      fieldName: 'housingType',
      label: '住房类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择住房类型',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入 (元)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月收入 (元)',
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
export function useGridColumns(): VxeTableGridOptions<GridCommunityCustomerApi.CommunityCustomer>['columns'] {
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
      field: 'familyMembers',
      title: '家庭成员数',
      minWidth: 120,
    },
    {
      field: 'housingType',
      title: '住房类型',
      minWidth: 120,
    },
    {
      field: 'monthlyIncome',
      title: '月收入 (元)',
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