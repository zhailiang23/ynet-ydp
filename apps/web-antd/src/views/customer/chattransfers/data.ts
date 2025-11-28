import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CustomerChatTransfersApi } from '#/api/customer/chattransfers';

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
      fieldName: 'userId',
      label: '被转接用户',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入被转接用户',
      },
    },
    {
      fieldName: 'fromSessionId',
      label: '原会话 id',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原会话 id',
      },
    },
    {
      fieldName: 'toSessionId',
      label: '新会话 id',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入新会话 id',
      },
    },
    {
      fieldName: 'fromAdminId',
      label: '转接人',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转接人',
      },
    },
    {
      fieldName: 'toAdminId',
      label: '转接给',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转接给',
      },
    },
    {
      fieldName: 'customerId',
      label: '租户 id',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入租户 id',
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
    {
      fieldName: 'acceptedAt',
      label: '接受时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'canceledAt',
      label: '取消时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'createdAt',
      label: '创建时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'updatedAt',
      label: '更新时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'userName',
      label: '被转接用户',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入被转接用户名称',
      },
    },
    {
      fieldName: 'fromAdminName',
      label: '转接人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转接人名称',
      },
    },
    {
      fieldName: 'toAdminName',
      label: '转接给',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转接给名称',
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
export function useGridColumns(): VxeTableGridOptions<CustomerChatTransfersApi.ChatTransfers>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'userName',
      title: '被转接用户',
      minWidth: 120,
    },
    {
      field: 'fromAdminName',
      title: '转接人',
      minWidth: 120,
    },
    {
      field: 'toAdminName',
      title: '转接给',
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