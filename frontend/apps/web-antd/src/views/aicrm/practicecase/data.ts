import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeCaseApi } from '#/api/aicrm/practicecase';

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
      fieldName: 'title',
      label: '案例标题',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入案例标题',
      },
    },
    {
      fieldName: 'content',
      label: '案例详细内容（正文）',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'tags',
      label: '标签（多个标签逗号分隔）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入标签（多个标签逗号分隔）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'title',
      label: '案例标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入案例标题',
      },
    },
    {
      fieldName: 'content',
      label: '案例详细内容（正文）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入案例详细内容（正文）',
      },
    },
    {
      fieldName: 'tags',
      label: '标签（多个标签逗号分隔）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入标签（多个标签逗号分隔）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeCaseApi.PracticeCase>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '案例ID',
      minWidth: 120,
    },
    {
      field: 'title',
      title: '案例标题',
      minWidth: 120,
    },
    {
      field: 'content',
      title: '案例详细内容（正文）',
      minWidth: 120,
    },
    {
      field: 'tags',
      title: '标签（多个标签逗号分隔）',
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