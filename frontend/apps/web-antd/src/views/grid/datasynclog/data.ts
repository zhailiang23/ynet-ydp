import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridDataSyncLogApi } from '#/api/grid/datasynclog';

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
      fieldName: 'syncType',
      label: '同步类型: ESB_LINGXI/DW_BATCH',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择同步类型: ESB_LINGXI/DW_BATCH',
      },
    },
    {
      fieldName: 'syncDirection',
      label: '同步方向: PULL/PUSH',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入同步方向: PULL/PUSH',
      },
    },
    {
      fieldName: 'entityType',
      label: '实体类型: GRID/CUSTOMER/STATION/ACTIVITY',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择实体类型: GRID/CUSTOMER/STATION/ACTIVITY',
      },
    },
    {
      fieldName: 'entityId',
      label: '实体ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实体ID',
      },
    },
    {
      fieldName: 'syncStatus',
      label: '同步状态: SUCCESS/FAILED/PENDING',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'syncTime',
      label: '同步时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'requestData',
      label: '请求数据',
      component: 'Input',
      componentProps: {
        placeholder: '请输入请求数据',
      },
    },
    {
      fieldName: 'responseData',
      label: '响应数据',
      component: 'Input',
      componentProps: {
        placeholder: '请输入响应数据',
      },
    },
    {
      fieldName: 'errorMessage',
      label: '错误信息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入错误信息',
      },
    },
    {
      fieldName: 'retryCount',
      label: '重试次数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入重试次数',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'syncType',
      label: '同步类型: ESB_LINGXI/DW_BATCH',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择同步类型: ESB_LINGXI/DW_BATCH',
      },
    },
    {
      fieldName: 'syncDirection',
      label: '同步方向: PULL/PUSH',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入同步方向: PULL/PUSH',
      },
    },
    {
      fieldName: 'entityType',
      label: '实体类型: GRID/CUSTOMER/STATION/ACTIVITY',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择实体类型: GRID/CUSTOMER/STATION/ACTIVITY',
      },
    },
    {
      fieldName: 'entityId',
      label: '实体ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实体ID',
      },
    },
    {
      fieldName: 'syncStatus',
      label: '同步状态: SUCCESS/FAILED/PENDING',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择同步状态: SUCCESS/FAILED/PENDING',
      },
    },
    {
      fieldName: 'syncTime',
      label: '同步时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'requestData',
      label: '请求数据',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入请求数据',
      },
    },
    {
      fieldName: 'responseData',
      label: '响应数据',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入响应数据',
      },
    },
    {
      fieldName: 'errorMessage',
      label: '错误信息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入错误信息',
      },
    },
    {
      fieldName: 'retryCount',
      label: '重试次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入重试次数',
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
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridDataSyncLogApi.DataSyncLog>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '同步记录ID',
      minWidth: 120,
    },
    {
      field: 'syncType',
      title: '同步类型: ESB_LINGXI/DW_BATCH',
      minWidth: 120,
    },
    {
      field: 'syncDirection',
      title: '同步方向: PULL/PUSH',
      minWidth: 120,
    },
    {
      field: 'entityType',
      title: '实体类型: GRID/CUSTOMER/STATION/ACTIVITY',
      minWidth: 120,
    },
    {
      field: 'entityId',
      title: '实体ID',
      minWidth: 120,
    },
    {
      field: 'syncStatus',
      title: '同步状态: SUCCESS/FAILED/PENDING',
      minWidth: 120,
    },
    {
      field: 'syncTime',
      title: '同步时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'requestData',
      title: '请求数据',
      minWidth: 120,
    },
    {
      field: 'responseData',
      title: '响应数据',
      minWidth: 120,
    },
    {
      field: 'errorMessage',
      title: '错误信息',
      minWidth: 120,
    },
    {
      field: 'retryCount',
      title: '重试次数',
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