import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsChatAutoMessagesApi } from '#/api/twins/chatautomessages';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 消息类型选项 */
const messageTypeOptions = [
  { label: '文本', value: 'text' },
  { label: '链接卡片', value: 'navigator' },
];

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
      fieldName: 'name',
      label: '消息名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入消息名称',
        maxlength: 32,
      },
    },
    {
      fieldName: 'type',
      label: '消息类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: messageTypeOptions,
        placeholder: '请选择消息类型',
      },
    },
    // 文本类型 - 消息内容
    {
      fieldName: 'content',
      label: '消息内容',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入消息内容',
        rows: 4,
        maxlength: 512,
        showCount: true,
      },
      dependencies: {
        triggerFields: ['type'],
        show: (values) => values.type === 'text',
      },
    },
    // 链接卡片类型 - 卡片标题
    {
      fieldName: 'navigatorTitle',
      label: '卡片标题',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡片标题',
        maxlength: 32,
      },
      dependencies: {
        triggerFields: ['type'],
        show: (values) => values.type === 'navigator',
      },
    },
    // 链接卡片类型 - 跳转链接
    {
      fieldName: 'navigatorUrl',
      label: '跳转链接',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入跳转链接',
        maxlength: 512,
      },
      dependencies: {
        triggerFields: ['type'],
        show: (values) => values.type === 'navigator',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '消息名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入消息名称',
      },
    },
    {
      fieldName: 'type',
      label: '消息类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: messageTypeOptions,
        placeholder: '请选择消息类型',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<TwinsChatAutoMessagesApi.ChatAutoMessages>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '消息名称',
      minWidth: 150,
    },
    {
      field: 'type',
      title: '消息类型',
      minWidth: 100,
      slots: { default: 'type' },
    },
    {
      field: 'content',
      title: '消息内容',
      minWidth: 300,
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