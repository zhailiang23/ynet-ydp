import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentHistoryApi } from '#/api/aicrm/customerassignmenthistory';

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
      label: '客户ID（关联 crm_customer 主表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'transferDate',
      label: '调整日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'transferLevel',
      label: '调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
      },
    },
    {
      fieldName: 'transferReason',
      label: '调整原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整原因',
      },
    },
    {
      fieldName: 'beforeAssignmentType',
      label: '调整前归属类型（1=主办，2=协办）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择调整前归属类型（1=主办，2=协办）',
      },
    },
    {
      fieldName: 'beforeDeptId',
      label: '调整前部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前部门ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'beforeUserId',
      label: '调整前客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'afterAssignmentType',
      label: '调整后归属类型（1=主办，2=协办）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择调整后归属类型（1=主办，2=协办）',
      },
    },
    {
      fieldName: 'afterDeptId',
      label: '调整后部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后部门ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'afterUserId',
      label: '调整后客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'assignOperatorId',
      label: '调整操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整操作人用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'assignDate',
      label: '分配日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer 主表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'transferDate',
      label: '调整日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'transferLevel',
      label: '调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
      },
    },
    {
      fieldName: 'transferReason',
      label: '调整原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整原因',
      },
    },
    {
      fieldName: 'beforeAssignmentType',
      label: '调整前归属类型（1=主办，2=协办）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择调整前归属类型（1=主办，2=协办）',
      },
    },
    {
      fieldName: 'beforeDeptId',
      label: '调整前部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前部门ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'beforeUserId',
      label: '调整前客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'afterAssignmentType',
      label: '调整后归属类型（1=主办，2=协办）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择调整后归属类型（1=主办，2=协办）',
      },
    },
    {
      fieldName: 'afterDeptId',
      label: '调整后部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后部门ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'afterUserId',
      label: '调整后客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'assignOperatorId',
      label: '调整操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整操作人用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'assignDate',
      label: '分配日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '调整历史主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'transferDate',
      title: '调整日期',
      minWidth: 120,
    },
    {
      field: 'transferLevel',
      title: '调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
      minWidth: 120,
    },
    {
      field: 'transferReason',
      title: '调整原因',
      minWidth: 120,
    },
    {
      field: 'beforeAssignmentType',
      title: '调整前归属类型（1=主办，2=协办）',
      minWidth: 120,
    },
    {
      field: 'beforeDeptId',
      title: '调整前部门ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'beforeUserId',
      title: '调整前客户经理用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'afterAssignmentType',
      title: '调整后归属类型（1=主办，2=协办）',
      minWidth: 120,
    },
    {
      field: 'afterDeptId',
      title: '调整后部门ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'afterUserId',
      title: '调整后客户经理用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'assignOperatorId',
      title: '调整操作人用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'assignDate',
      title: '分配日期',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
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