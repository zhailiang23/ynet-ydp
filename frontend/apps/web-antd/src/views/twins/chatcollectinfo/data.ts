import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsChatCollectInfoApi } from '#/api/twins/chatcollectinfo';

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
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'templateId',
      label: '留资模板',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入留资模板',
      },
    },
    {
      fieldName: 'status',
      label: '留资状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择留资状态',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<TwinsChatCollectInfoApi.ChatCollectInfo>['columns'] {
  return [
    {
      field: 'templateName',
      title: '留资模板',
      minWidth: 150,
    },
    {
      field: 'statusText',
      title: '留资状态',
      minWidth: 120,
    },
    {
      field: 'acceptTime',
      title: '受理时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'finishTime',
      title: '完成时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'username',
      title: '提交用户',
      minWidth: 120,
    },
    {
      field: 'adminName',
      title: '处理客服',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
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