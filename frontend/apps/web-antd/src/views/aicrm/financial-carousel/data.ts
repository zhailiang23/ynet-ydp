import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CrmFinancialCarouselApi } from '#/api/crm/product/financial/carousel';

import { getDictOptions } from '@vben/hooks';

/** 列表页的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'title',
      label: '轮播标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入轮播标题',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('common_status'),
        placeholder: '请选择状态',
      },
    },
  ];
}

/** 列表页的表格列定义 */
export function useGridColumns(): VxeTableGridOptions<CrmFinancialCarouselApi.FinancialCarousel>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'title',
      title: '轮播标题',
      minWidth: 180,
      fixed: 'left',
    },
    {
      field: 'subtitle',
      title: '轮播副标题',
      minWidth: 180,
    },
    {
      field: 'imageUrl',
      title: '轮播图片',
      minWidth: 200,
      slots: { default: 'imageUrl' },
    },
    {
      field: 'linkType',
      title: '链接类型',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        const typeMap: Record<string, string> = {
          product: '产品详情',
          url: '外部链接',
        };
        return typeMap[cellValue as string] || cellValue || '-';
      },
    },
    {
      field: 'linkId',
      title: '关联产品ID',
      minWidth: 100,
    },
    {
      field: 'badgeText',
      title: '角标文字',
      minWidth: 100,
    },
    {
      field: 'badgeColor',
      title: '角标颜色',
      minWidth: 100,
      slots: { default: 'badgeColor' },
    },
    {
      field: 'sort',
      title: '排序',
      minWidth: 80,
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
