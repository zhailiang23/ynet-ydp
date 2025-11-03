import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';

import { getDictOptions } from '@vben/hooks';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerNo',
      label: '客户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户编号',
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
      fieldName: 'assignmentType',
      label: '归属类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_assignment_type'),
        placeholder: '请选择归属类型',
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
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAssignmentApi.MyCustomerResp>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'customerNo',
      title: '客户编号',
      minWidth: 140,
      fixed: 'left',
    },
    {
      field: 'customerName',
      title: '客户名称',
      minWidth: 140,
      fixed: 'left',
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
      field: 'assignmentType',
      title: '归属类型',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_assignment_type' },
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
      field: 'deptName',
      title: '归属部门',
      minWidth: 120,
    },
    {
      field: 'assignDate',
      title: '分配日期',
      minWidth: 120,
    },
    {
      field: 'delegateUserName',
      title: '托管人',
      minWidth: 120,
    },
    {
      field: 'delegateStartDate',
      title: '托管开始时间',
      minWidth: 120,
    },
    {
      field: 'delegateEndDate',
      title: '托管结束时间',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 140,
    },
    {
      title: '操作',
      width: 250,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
