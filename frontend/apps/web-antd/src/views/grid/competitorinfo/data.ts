import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridCompetitorInfoApi } from '#/api/grid/competitorinfo';

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
      fieldName: 'competitorName',
      label: '同业网点名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入同业网点名称',
      },
    },
    {
      fieldName: 'address',
      label: '详细地址',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入详细地址',
      },
    },
    {
      fieldName: 'businessStrategy',
      label: '主要经营策略',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入主要经营策略',
        rows: 3,
      },
    },
    {
      fieldName: 'coreCustomers',
      label: '核心客户群',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入核心客户群',
        rows: 3,
      },
    },
    {
      fieldName: 'productAdvantages',
      label: '产品优势',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入产品优势',
        rows: 3,
      },
    },
    {
      fieldName: 'productDisadvantages',
      label: '产品劣势',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入产品劣势',
        rows: 3,
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'competitorName',
      label: '同业网点名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入同业网点名称',
      },
    },
    {
      fieldName: 'address',
      label: '详细地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入详细地址',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridCompetitorInfoApi.CompetitorInfo>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'competitorName',
      title: '同业网点名称',
      minWidth: 150,
    },
    {
      field: 'address',
      title: '地址',
      minWidth: 200,
    },
    {
      field: 'businessStrategy',
      title: '主要经营策略',
      minWidth: 180,
    },
    {
      field: 'coreCustomers',
      title: '核心客户群',
      minWidth: 150,
    },
    {
      field: 'productAdvantages',
      title: '产品优势',
      minWidth: 150,
    },
    {
      field: 'productDisadvantages',
      title: '产品劣势',
      minWidth: 150,
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