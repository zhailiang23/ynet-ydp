import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGridHistoryApi } from '#/api/aicrm/customergridhistory';

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
      fieldName: 'adjustDate',
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
      fieldName: 'adjustReason',
      label: '调整原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整原因',
      },
    },
    {
      fieldName: 'beforeGridId',
      label: '调整前网格ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前网格ID',
      },
    },
    {
      fieldName: 'beforeGridCode',
      label: '调整前网格编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前网格编号',
      },
    },
    {
      fieldName: 'beforeGridName',
      label: '调整前网格名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前网格名称',
      },
    },
    {
      fieldName: 'beforeGridType',
      label: '调整前网格类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择调整前网格类型',
      },
    },
    {
      fieldName: 'beforeGridManagerUserId',
      label: '调整前网格管理员用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前网格管理员用户ID',
      },
    },
    {
      fieldName: 'afterGridId',
      label: '调整后网格ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后网格ID',
      },
    },
    {
      fieldName: 'afterGridCode',
      label: '调整后网格编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后网格编号',
      },
    },
    {
      fieldName: 'afterGridName',
      label: '调整后网格名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后网格名称',
      },
    },
    {
      fieldName: 'afterGridType',
      label: '调整后网格类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择调整后网格类型',
      },
    },
    {
      fieldName: 'afterGridManagerUserId',
      label: '调整后网格管理员用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后网格管理员用户ID',
      },
    },
    {
      fieldName: 'adjustOperatorId',
      label: '调整操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整操作人用户ID（关联 system_users.id）',
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
      fieldName: 'adjustDate',
      label: '调整日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'adjustReason',
      label: '调整原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整原因',
      },
    },
    {
      fieldName: 'beforeGridId',
      label: '调整前网格ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前网格ID',
      },
    },
    {
      fieldName: 'beforeGridCode',
      label: '调整前网格编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前网格编号',
      },
    },
    {
      fieldName: 'beforeGridName',
      label: '调整前网格名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前网格名称',
      },
    },
    {
      fieldName: 'beforeGridType',
      label: '调整前网格类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择调整前网格类型',
      },
    },
    {
      fieldName: 'beforeGridManagerUserId',
      label: '调整前网格管理员用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前网格管理员用户ID',
      },
    },
    {
      fieldName: 'afterGridId',
      label: '调整后网格ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后网格ID',
      },
    },
    {
      fieldName: 'afterGridCode',
      label: '调整后网格编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后网格编号',
      },
    },
    {
      fieldName: 'afterGridName',
      label: '调整后网格名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后网格名称',
      },
    },
    {
      fieldName: 'afterGridType',
      label: '调整后网格类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择调整后网格类型',
      },
    },
    {
      fieldName: 'afterGridManagerUserId',
      label: '调整后网格管理员用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后网格管理员用户ID',
      },
    },
    {
      fieldName: 'adjustOperatorId',
      label: '调整操作人用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整操作人用户ID（关联 system_users.id）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGridHistoryApi.CustomerGridHistory>['columns'] {
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
      field: 'adjustDate',
      title: '调整日期',
      minWidth: 120,
    },
    {
      field: 'adjustReason',
      title: '调整原因',
      minWidth: 120,
    },
    {
      field: 'beforeGridId',
      title: '调整前网格ID',
      minWidth: 120,
    },
    {
      field: 'beforeGridCode',
      title: '调整前网格编号',
      minWidth: 120,
    },
    {
      field: 'beforeGridName',
      title: '调整前网格名称',
      minWidth: 120,
    },
    {
      field: 'beforeGridType',
      title: '调整前网格类型',
      minWidth: 120,
    },
    {
      field: 'beforeGridManagerUserId',
      title: '调整前网格管理员用户ID',
      minWidth: 120,
    },
    {
      field: 'afterGridId',
      title: '调整后网格ID',
      minWidth: 120,
    },
    {
      field: 'afterGridCode',
      title: '调整后网格编号',
      minWidth: 120,
    },
    {
      field: 'afterGridName',
      title: '调整后网格名称',
      minWidth: 120,
    },
    {
      field: 'afterGridType',
      title: '调整后网格类型',
      minWidth: 120,
    },
    {
      field: 'afterGridManagerUserId',
      title: '调整后网格管理员用户ID',
      minWidth: 120,
    },
    {
      field: 'adjustOperatorId',
      title: '调整操作人用户ID（关联 system_users.id）',
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