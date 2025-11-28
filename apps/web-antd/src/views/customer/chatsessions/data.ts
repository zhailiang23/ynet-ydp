import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CustomerChatSessionsApi } from '#/api/customer/chatsessions';

import { getRangePickerDefaultProps } from '#/utils';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'userName',
      label: '用户',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入用户名',
      },
    },
    {
      fieldName: 'adminName',
      label: '客服',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客服名',
      },
    },
    {
      fieldName: 'queriedAt',
      label: '询问时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'type',
      label: '会话类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '普通会话', value: 0 },
          { label: '转接会话', value: 1 },
        ],
        placeholder: '请选择会话类型',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<CustomerChatSessionsApi.ChatSessions>['columns'] {
  return [
    {
      field: 'id',
      title: 'ID',
      width: 80,
    },
    {
      field: 'userName',
      title: '用户',
      width: 120,
    },
    {
      field: 'adminName',
      title: '客服',
      width: 120,
    },
    {
      field: 'queriedAt',
      title: '询问时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      field: 'acceptedAt',
      title: '接入时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      field: 'brokenAt',
      title: '断开时间',
      minWidth: 160,
      formatter: 'formatDateTime',
    },
    {
      field: 'type',
      title: '会话类型',
      width: 100,
      formatter: ({ cellValue }) => {
        return cellValue === 0 ? '普通会话' : '转接会话';
      },
    },
    {
      field: 'rate',
      title: '评分',
      width: 80,
      formatter: ({ cellValue }) => {
        return cellValue !== null && cellValue !== undefined ? cellValue : '-';
      },
    },
    {
      title: '操作',
      width: 100,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
