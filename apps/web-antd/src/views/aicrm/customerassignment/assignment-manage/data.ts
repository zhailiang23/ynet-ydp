import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerNo',
      label: '客户号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户号',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户名称',
      },
    },
    {
      fieldName: 'customerType',
      label: '客户类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_type'),
        placeholder: '请选择客户类型',
      },
    },
    {
      fieldName: 'customerStatus',
      label: '客户状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_status'),
        placeholder: '请选择客户状态',
      },
    },
    {
      fieldName: 'assignmentStatus',
      label: '分配状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '未分配', value: 0 },
          { label: '已分配', value: 1 },
        ],
        placeholder: '请选择分配状态',
      },
    },
    {
      fieldName: 'isValid',
      label: '是否有效',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '是', value: true },
          { label: '否', value: false },
        ],
        placeholder: '请选择',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerApi.Customer>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      type: 'seq',
      field: 'seq',
      title: '序号',
      width: 60,
      fixed: 'left',
    },
    {
      field: 'customerNo',
      title: '客户号',
      minWidth: 140,
      fixed: 'left',
    },
    {
      field: 'customerName',
      title: '客户名称',
      minWidth: 140,
    },
    {
      field: 'customerType',
      title: '客户类型',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_type' },
      },
    },
    {
      field: 'customerStatus',
      title: '客户状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_status' },
      },
    },
    {
      field: 'assignmentStatus',
      title: '分配状态',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        return cellValue === 1 ? '已分配' : '未分配';
      },
    },
    {
      field: 'isValid',
      title: '是否有效客户',
      minWidth: 120,
      formatter: ({ cellValue }) => (cellValue ? '是' : '否'),
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
