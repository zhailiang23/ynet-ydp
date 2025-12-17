import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridHuinongMarketingApi } from '#/api/grid/huinongmarketing';

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
      fieldName: 'gridId',
      label: '网格ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格ID',
      },
    },
    {
      fieldName: 'villageName',
      label: '行政村名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行政村名称',
      },
    },
    {
      fieldName: 'villageLocation',
      label: '行政村坐标',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行政村坐标',
      },
    },
    {
      fieldName: 'villageAddress',
      label: '行政村地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行政村地址',
      },
    },
    {
      fieldName: 'registeredPopulation',
      label: '户籍人口数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入户籍人口数',
      },
    },
    {
      fieldName: 'residentPopulation',
      label: '常住人口数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入常住人口数',
      },
    },
    {
      fieldName: 'industrySituation',
      label: '产业情况描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产业情况描述',
      },
    },
    {
      fieldName: 'mainIndustries',
      label: '主要产业 (逗号分隔)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主要产业 (逗号分隔)',
      },
    },
    {
      fieldName: 'demandAnalysis',
      label: '需求分析',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求分析',
      },
    },
    {
      fieldName: 'marketingPlan',
      label: '营销计划',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营销计划',
      },
    },
    {
      fieldName: 'specialistId',
      label: '负责惠农专员ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责惠农专员ID',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建人ID',
      },
    },
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'stationName',
      label: '站点名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入站点名称',
      },
    },
    {
      fieldName: 'stationCode',
      label: '站点编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入站点编号',
      },
    },
    {
      fieldName: 'villageName',
      label: '行政村名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入行政村名称',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridHuinongMarketingApi.HuinongMarketing>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'stationName',
      title: '站点名称',
      minWidth: 150,
    },
    {
      field: 'stationCode',
      title: '站点编号',
      minWidth: 120,
    },
    {
      field: 'villageName',
      title: '行政村名称',
      minWidth: 150,
    },
    {
      field: 'registeredPopulation',
      title: '户籍人口',
      minWidth: 100,
    },
    {
      field: 'creatorName',
      title: '员工姓名（录入人）',
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