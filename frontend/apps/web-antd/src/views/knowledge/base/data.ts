import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { KnowledgeBaseApi } from '#/api/knowledge/base';

import { DICT_TYPE } from '@vben/constants';
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
      label: '知识库名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入知识库名称',
      },
    },
    {
      fieldName: 'description',
      label: '知识库描述',
      component: 'Textarea',
      componentProps: {
        rows: 3,
        placeholder: '请输入知识库描述',
      },
    },
    {
      fieldName: 'isPublic',
      component: 'Input',
      defaultValue: true,
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'status',
      label: '是否启用',
      rules: 'required',
      component: 'Switch',
      componentProps: {
        checkedValue: 0,
        unCheckedValue: 1,
        class: 'w-12',
      },
      defaultValue: 0,
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '知识库名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入知识库名称',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
        placeholder: '请选择状态',
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
export function useGridColumns(): VxeTableGridOptions<KnowledgeBaseApi.Base>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '知识库名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '知识库描述',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 120,
      cellRender: {
        name: 'CellDict',
        props: { type: DICT_TYPE.COMMON_STATUS },
      },
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