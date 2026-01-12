import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridZerodaiMarketDescriptionApi } from '#/api/grid/zerodaimarketdescription';

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
      fieldName: 'marketName',
      label: '市场名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入市场名称',
      },
    },
    {
      fieldName: 'marketType',
      label: '市场类型 (关联字典 aicrm_market_type)',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择市场类型 (关联字典 aicrm_market_type)',
      },
    },
    {
      fieldName: 'location',
      label: '市场位置坐标',
      component: 'Input',
      componentProps: {
        placeholder: '请输入市场位置坐标',
      },
    },
    {
      fieldName: 'address',
      label: '市场地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入市场地址',
      },
    },
    {
      fieldName: 'businessScope',
      label: '经营范围',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营范围',
      },
    },
    {
      fieldName: 'merchantCount',
      label: '商户数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入商户数量',
      },
    },
    {
      fieldName: 'dailyTraffic',
      label: '日均客流量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入日均客流量',
      },
    },
    {
      fieldName: 'marketFeatures',
      label: '市场特点',
      component: 'Input',
      componentProps: {
        placeholder: '请输入市场特点',
      },
    },
    {
      fieldName: 'targetCustomers',
      label: '目标客户群体',
      component: 'Input',
      componentProps: {
        placeholder: '请输入目标客户群体',
      },
    },
    {
      fieldName: 'potentialAnalysis',
      label: '潜力分析',
      component: 'Input',
      componentProps: {
        placeholder: '请输入潜力分析',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理ID',
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
      fieldName: 'gridId',
      label: '网格ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格ID',
      },
    },
    {
      fieldName: 'marketName',
      label: '市场名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入市场名称',
      },
    },
    {
      fieldName: 'marketType',
      label: '市场类型 (关联字典 aicrm_market_type)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择市场类型 (关联字典 aicrm_market_type)',
      },
    },
    {
      fieldName: 'location',
      label: '市场位置坐标',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入市场位置坐标',
      },
    },
    {
      fieldName: 'address',
      label: '市场地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入市场地址',
      },
    },
    {
      fieldName: 'businessScope',
      label: '经营范围',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营范围',
      },
    },
    {
      fieldName: 'merchantCount',
      label: '商户数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入商户数量',
      },
    },
    {
      fieldName: 'dailyTraffic',
      label: '日均客流量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入日均客流量',
      },
    },
    {
      fieldName: 'marketFeatures',
      label: '市场特点',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入市场特点',
      },
    },
    {
      fieldName: 'targetCustomers',
      label: '目标客户群体',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入目标客户群体',
      },
    },
    {
      fieldName: 'potentialAnalysis',
      label: '潜力分析',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入潜力分析',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理ID',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建人ID',
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
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridZerodaiMarketDescriptionApi.ZerodaiMarketDescription>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '市场描述ID',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '网格ID',
      minWidth: 120,
    },
    {
      field: 'marketName',
      title: '市场名称',
      minWidth: 120,
    },
    {
      field: 'marketType',
      title: '市场类型 (关联字典 aicrm_market_type)',
      minWidth: 120,
    },
    {
      field: 'location',
      title: '市场位置坐标',
      minWidth: 120,
    },
    {
      field: 'address',
      title: '市场地址',
      minWidth: 120,
    },
    {
      field: 'businessScope',
      title: '经营范围',
      minWidth: 120,
    },
    {
      field: 'merchantCount',
      title: '商户数量',
      minWidth: 120,
    },
    {
      field: 'dailyTraffic',
      title: '日均客流量',
      minWidth: 120,
    },
    {
      field: 'marketFeatures',
      title: '市场特点',
      minWidth: 120,
    },
    {
      field: 'targetCustomers',
      title: '目标客户群体',
      minWidth: 120,
    },
    {
      field: 'potentialAnalysis',
      title: '潜力分析',
      minWidth: 120,
    },
    {
      field: 'managerId',
      title: '客户经理ID',
      minWidth: 120,
    },
    {
      field: 'creatorId',
      title: '创建人ID',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'updaterId',
      title: '更新人ID',
      minWidth: 120,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}