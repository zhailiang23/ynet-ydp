import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerReturnApi } from '#/api/aicrm/customerreturn';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'applyUserId',
      label: '申请人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入申请人ID',
      },
    },
    {
      fieldName: 'processStatus',
      label: '审批状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_process_status'),
        placeholder: '请选择审批状态',
      },
    },
    {
      fieldName: 'applyDate',
      label: '申请日期',
      component: 'RangePicker',
      componentProps: getRangePickerDefaultProps(),
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerReturnApi.CustomerReturnApplication>['columns'] {
  return [
    {
      field: 'id',
      title: '申请ID',
      minWidth: 80,
      fixed: 'left',
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 100,
    },
    {
      field: 'applyUserId',
      title: '申请人ID',
      minWidth: 100,
    },
    {
      field: 'applyDate',
      title: '申请日期',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleDateString('zh-CN');
      },
    },
    {
      field: 'returnReason',
      title: '退回原因',
      minWidth: 180,
      showOverflow: 'tooltip',
    },
    {
      field: 'processStatus',
      title: '审批状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_process_status' },
      },
    },
    {
      field: 'processInstanceId',
      title: '流程实例ID',
      minWidth: 180,
      showOverflow: 'tooltip',
    },
    {
      field: 'approverComment',
      title: '审批意见',
      minWidth: 160,
      showOverflow: 'tooltip',
    },
    {
      field: 'approveTime',
      title: '审批时间',
      minWidth: 160,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleString('zh-CN');
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleString('zh-CN');
      },
    },
    {
      field: 'action',
      title: '操作',
      width: 120,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 提交退回申请表单 */
export function useApplyFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'returnReason',
      label: '退回原因',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入退回原因（必填）',
        rows: 4,
        maxlength: 500,
        showCount: true,
      },
    },
  ];
}
