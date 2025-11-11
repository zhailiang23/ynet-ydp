import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeMaterialApi } from '#/api/aicrm/practicematerial';

import { getDictOptions } from '@vben/hooks';

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
      label: '文件名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入文件名称',
      },
    },
    {
      fieldName: 'fileType',
      label: '文件类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择文件类型',
        options: getDictOptions('aicrm_practice_material_file_type'),
      },
    },
    {
      fieldName: 'fileUrl',
      label: '文件URL',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入文件URL',
      },
    },
    {
      fieldName: 'fileSize',
      label: '文件大小（字节）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入文件大小（字节）',
      },
    },
    {
      fieldName: 'content',
      label: '文件内容(纯文本)',
      component: 'RichTextarea',
    },
    {
      fieldName: 'contentRich',
      label: '文件内容(富文本)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入文件内容(富文本)',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
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
        placeholder: '请选择文件类型',
        options: getDictOptions('aicrm_practice_material_file_type'),
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeMaterialApi.PracticeMaterial>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '文件名称',
      minWidth: 200,
    },
    {
      field: 'fileType',
      title: '文件类型',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_practice_material_file_type' } },
    },
    {
      field: 'fileSize',
      title: '文件大小',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '-';
        const size = Number(cellValue);
        if (size < 1024) return `${size}B`;
        if (size < 1024 * 1024) return `${(size / 1024).toFixed(2)}KB`;
        if (size < 1024 * 1024 * 1024) return `${(size / 1024 / 1024).toFixed(2)}MB`;
        return `${(size / 1024 / 1024 / 1024).toFixed(2)}GB`;
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 150,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}