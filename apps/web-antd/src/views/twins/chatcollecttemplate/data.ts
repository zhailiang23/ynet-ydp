import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsChatCollectTemplateApi } from '#/api/twins/chatcollecttemplate';

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
      fieldName: 'name',
      label: '模板名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入模板名称',
      },
    },
    {
      fieldName: 'description',
      label: '模板描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入模板描述',
        rows: 3,
      },
    },
    {
      fieldName: 'url',
      label: '模板链接',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入模板链接',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Switch',
      defaultValue: 1,
      componentProps: {
        checkedChildren: '启用',
        unCheckedChildren: '禁用',
        checkedValue: 1,
        unCheckedValue: 0,
        class: 'w-16',
      },
    },
    {
      fieldName: 'sort',
      label: '排序',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入排序',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '模板名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入模板名称',
      },
    },
    {
      fieldName: 'description',
      label: '模板描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入模板描述',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '启用', value: 1 },
          { label: '禁用', value: 0 },
        ],
        placeholder: '请选择状态',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<TwinsChatCollectTemplateApi.ChatCollectTemplate>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '模板名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '模板描述',
      minWidth: 120,
    },
    {
      field: 'url',
      title: '模板链接',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 100,
      slots: { default: 'status' },
    },
    {
      field: 'sort',
      title: '排序',
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