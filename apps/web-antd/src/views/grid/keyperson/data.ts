import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridKeyPersonApi } from '#/api/grid/keyperson';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 新增/修改的表单 (注意: 实际使用的是 form.vue 中的自定义表单，此函数仅保留用于兼容) */
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
      fieldName: 'personName',
      label: '姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入姓名',
      },
    },
    {
      fieldName: 'organization',
      label: '单位/小区',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入单位/小区',
      },
    },
    {
      fieldName: 'position',
      label: '职务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职务',
      },
    },
    {
      fieldName: 'contact',
      label: '联系方式',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式',
      },
    },
    {
      fieldName: 'establishDate',
      label: '建立联系日期',
      component: 'DatePicker',
      componentProps: {
        format: 'YYYY-MM-DD',
        valueFormat: 'YYYY-MM-DD',
      },
    },
    {
      fieldName: 'lastMaintainDate',
      label: '最新维护日期',
      component: 'DatePicker',
      componentProps: {
        format: 'YYYY-MM-DD',
        valueFormat: 'YYYY-MM-DD',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'personName',
      label: '姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入姓名',
      },
    },
    {
      fieldName: 'organization',
      label: '单位/小区',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入单位/小区',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridKeyPersonApi.KeyPerson>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'personName',
      title: '姓名',
      minWidth: 120,
    },
    {
      field: 'organization',
      title: '单位/小区',
      minWidth: 150,
    },
    {
      field: 'position',
      title: '职务',
      minWidth: 120,
    },
    {
      field: 'contact',
      title: '联系方式',
      minWidth: 130,
    },
    {
      field: 'establishDate',
      title: '建立联系日期',
      minWidth: 120,
      formatter: 'formatDate',
    },
    {
      field: 'lastMaintainDate',
      title: '最新维护日期',
      minWidth: 120,
      formatter: 'formatDate',
    },
    {
      field: 'employeeCode',
      title: '员工工号',
      minWidth: 120,
    },
    {
      field: 'employeeName',
      title: '员工姓名',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '录入日期',
      minWidth: 160,
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