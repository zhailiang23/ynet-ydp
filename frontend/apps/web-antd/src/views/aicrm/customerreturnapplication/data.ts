import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerReturnApplicationApi } from '#/api/aicrm/customerreturnapplication';

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
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer）',
      },
    },
    {
      fieldName: 'applicantUserId',
      label: '申请人(客户经理)ID（关联 system_users.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入申请人(客户经理)ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'returnToUserId',
      label: '退回给主管ID（关联 system_users.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入退回给主管ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'applyDate',
      label: '申请日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'returnReason',
      label: '退回原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入退回原因',
      },
    },
    {
      fieldName: 'processInstanceId',
      label: 'BPM流程实例ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入BPM流程实例ID',
      },
    },
    {
      fieldName: 'processStatus',
      label: '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer）',
      },
    },
    {
      fieldName: 'applicantUserId',
      label: '申请人(客户经理)ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入申请人(客户经理)ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'returnToUserId',
      label: '退回给主管ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入退回给主管ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'applyDate',
      label: '申请日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'returnReason',
      label: '退回原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入退回原因',
      },
    },
    {
      fieldName: 'processInstanceId',
      label: 'BPM流程实例ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入BPM流程实例ID',
      },
    },
    {
      fieldName: 'processStatus',
      label: '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerReturnApplicationApi.CustomerReturnApplication>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '申请ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer）',
      minWidth: 120,
    },
    {
      field: 'applicantUserId',
      title: '申请人(客户经理)ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'returnToUserId',
      title: '退回给主管ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'applyDate',
      title: '申请日期',
      minWidth: 120,
    },
    {
      field: 'returnReason',
      title: '退回原因',
      minWidth: 120,
    },
    {
      field: 'processInstanceId',
      title: 'BPM流程实例ID',
      minWidth: 120,
    },
    {
      field: 'processStatus',
      title: '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',
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