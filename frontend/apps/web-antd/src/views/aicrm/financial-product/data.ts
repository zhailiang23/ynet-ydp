import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CrmFinancialProductApi } from '#/api/crm/product/financial';

import { h } from 'vue';

import { getDictOptions } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

/** 列表页的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'productName',
      label: '产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品名称',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品代码',
      },
    },
    {
      fieldName: 'category',
      label: '产品分类',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_fin_product_category'),
        placeholder: '请选择产品分类',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_fin_risk_level'),
        placeholder: '请选择风险等级',
      },
    },
    {
      fieldName: 'status',
      label: '产品状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('common_status'),
        placeholder: '请选择产品状态',
      },
    },
    {
      fieldName: 'isHot',
      label: '是否热销',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '是', value: 1 },
          { label: '否', value: 0 },
        ],
        placeholder: '请选择',
      },
    },
    {
      fieldName: 'isNew',
      label: '是否新品',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '是', value: 1 },
          { label: '否', value: 0 },
        ],
        placeholder: '请选择',
      },
    },
  ];
}

/** 列表页的表格列定义 */
export function useGridColumns(): VxeTableGridOptions<CrmFinancialProductApi.FinancialProduct>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'productCode',
      title: '产品代码',
      minWidth: 120,
      fixed: 'left',
    },
    {
      field: 'productName',
      title: '产品名称',
      minWidth: 180,
      fixed: 'left',
    },
    {
      field: 'category',
      title: '产品分类',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_fin_product_category' },
      },
    },
    {
      field: 'riskLevel',
      title: '风险等级',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_fin_risk_level' },
      },
    },
    {
      field: 'expectedReturn',
      title: '预期收益率(%)',
      minWidth: 120,
      formatter: ({ cellValue }) => (cellValue ? `${cellValue}%` : '-'),
    },
    {
      field: 'duration',
      title: '产品期限',
      minWidth: 100,
    },
    {
      field: 'minimumInvestment',
      title: '起购金额',
      minWidth: 120,
      formatter: ({ cellValue, row }) => {
        if (!cellValue) return '-';
        return `${cellValue.toLocaleString()}${row.investmentUnit || '元'}`;
      },
    },
    {
      field: 'tags',
      title: '产品标签',
      minWidth: 200,
      slots: { default: 'tags' },
    },
    {
      field: 'isHot',
      title: '热销',
      minWidth: 60,
      formatter: ({ cellValue }) => (cellValue ? '是' : '否'),
    },
    {
      field: 'isNew',
      title: '新品',
      minWidth: 60,
      formatter: ({ cellValue }) => (cellValue ? '是' : '否'),
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 80,
      cellRender: {
        name: 'CellDict',
        props: { type: 'common_status' },
      },
    },
    {
      field: 'sort',
      title: '排序',
      minWidth: 80,
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
