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
      fieldName: 'customerName',
      label: '客户名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户名称',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridCommunityCustomerApi.CommunityCustomer>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'customerName',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'phone',
      title: '手机号',
      minWidth: 130,
    },
    {
      field: 'idType',
      title: '证件类型',
      minWidth: 100,
    },
    {
      field: 'idNumber',
      title: '证件号',
      minWidth: 180,
    },
    {
      field: 'orgName',
      title: '机构名称',
      minWidth: 150,
    },
    {
      field: 'managerName',
      title: '客户经理姓名',
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