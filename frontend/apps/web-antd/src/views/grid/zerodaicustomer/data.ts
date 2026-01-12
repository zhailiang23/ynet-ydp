import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridZerodaiCustomerApi } from '#/api/grid/zerodaicustomer';

import { getDictOptions } from '@vben/hooks';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerName',
      label: '商户名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入商户名称',
      },
    },
    {
      fieldName: 'principalName',
      label: '负责人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人姓名',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridZerodaiCustomerApi.ZerodaiCustomer>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'customerName',
      title: '商户名称',
      minWidth: 150,
    },
    {
      field: 'principalName',
      title: '负责人姓名',
      minWidth: 120,
    },
    {
      field: 'address',
      title: '经营地址',
      minWidth: 200,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}