import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridHuinongExtensionApi } from '#/api/grid/huinongextension';

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
      label: '网格ID (关联 grid_info)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格ID (关联 grid_info)',
      },
    },
    {
      fieldName: 'isMarketingSite',
      label: '是否为网格营销站点',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isRequired',
      label: '是否为必选站点',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      label: '网格ID (关联 grid_info)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格ID (关联 grid_info)',
      },
    },
    {
      fieldName: 'isMarketingSite',
      label: '是否为网格营销站点',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否为网格营销站点',
      },
    },
    {
      fieldName: 'isRequired',
      label: '是否为必选站点',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否为必选站点',
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
export function useGridColumns(): VxeTableGridOptions<GridHuinongExtensionApi.HuinongExtension>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '扩展ID',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '网格ID (关联 grid_info)',
      minWidth: 120,
    },
    {
      field: 'isMarketingSite',
      title: '是否为网格营销站点',
      minWidth: 120,
    },
    {
      field: 'isRequired',
      title: '是否为必选站点',
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