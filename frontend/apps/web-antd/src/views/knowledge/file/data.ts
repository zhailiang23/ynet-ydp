import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { KnowledgeKonwledgeFileApi } from '#/api/knowledge/file';

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
      fieldName: 'fileName',
      label: '文件名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入文件名称',
      },
    },
    {
      fieldName: 'fileType',
      label: '文件类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择文件类型',
      },
    },
    {
      fieldName: 'status',
      label: '文件状态（0处理中 1处理完成 2处理失败）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择文件状态（0处理中 1处理完成 2处理失败）',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<KnowledgeKonwledgeFileApi.KonwledgeFile>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'fileName',
      title: '文件名称',
      minWidth: 120,
    },
    {
      field: 'fileType',
      title: '文件类型',
      minWidth: 120,
    },
    {
      field: 'fileSize',
      title: '文件大小（字节）',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '文件状态（0处理中 1处理完成 2处理失败）',
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