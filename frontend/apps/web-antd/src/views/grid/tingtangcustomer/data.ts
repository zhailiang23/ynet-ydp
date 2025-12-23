import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridTingtangCustomerApi } from '#/api/grid/tingtangcustomer';

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
    {
      fieldName: 'phone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系电话',
      },
    },
  ];
}

/** 表单的字段 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      label: 'ID',
      component: 'Input',
      dependencies: {
        show: false,
        triggerFields: ['id'],
      },
    },
    {
      fieldName: 'customerName',
      label: '客户名称',
      component: 'Input',
      rules: 'required',
      componentProps: {
        placeholder: '请输入客户名称',
      },
    },
    {
      fieldName: 'phone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话',
      },
    },
    {
      fieldName: 'address',
      label: '地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入地址',
      },
    },
    {
      fieldName: 'longitude',
      label: '经度',
      component: 'InputNumber',
      rules: 'required',
      componentProps: {
        placeholder: '请输入经度或在地图上选点',
        precision: 6,
        style: { width: '100%' },
      },
    },
    {
      fieldName: 'latitude',
      label: '纬度',
      component: 'InputNumber',
      rules: 'required',
      componentProps: {
        placeholder: '请输入纬度或在地图上选点',
        precision: 6,
        style: { width: '100%' },
      },
    },
    {
      fieldName: 'isArchived',
      label: '是否建档',
      component: 'Switch',
      defaultValue: false,
    },
    {
      fieldName: 'crmCustomerId',
      label: 'CRM客户号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入CRM客户号',
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
      title: 'ID',
      minWidth: 80,
    },
    {
      field: 'customerName',
      title: '客户名称',
      minWidth: 120,
    },
    {
      field: 'gender',
      title: '性别',
      minWidth: 80,
    },
    {
      field: 'customerGroup',
      title: '客群类型',
      minWidth: 120,
    },
    {
      field: 'idCard',
      title: '身份证号',
      minWidth: 160,
    },
    {
      field: 'phone',
      title: '联系电话',
      minWidth: 130,
    },
    {
      field: 'address',
      title: '地址',
      minWidth: 200,
    },
    {
      field: 'longitude',
      title: '经度',
      minWidth: 100,
    },
    {
      field: 'latitude',
      title: '纬度',
      minWidth: 100,
    },
    {
      field: 'isArchived',
      title: '是否建档',
      minWidth: 100,
      cellRender: { name: 'VxeSwitch', props: { disabled: true } },
    },
    {
      field: 'crmCustomerId',
      title: 'CRM客户号',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 160,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
