import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGroupHistoryApi } from '#/api/aicrm/customergrouphistory';

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
      fieldName: 'beforeGroupId',
      label: '调整前客群ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客群ID',
      },
    },
    {
      fieldName: 'beforeGroupCode',
      label: '调整前客户群编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客户群编号',
      },
    },
    {
      fieldName: 'beforeGroupName',
      label: '调整前客户群名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客户群名称',
      },
    },
    {
      fieldName: 'beforeGroupCategory',
      label: '调整前客户群分类',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客户群分类',
      },
    },
    {
      fieldName: 'beforeManagerUserId',
      label: '调整前客群管理员用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整前客群管理员用户ID',
      },
    },
    {
      fieldName: 'afterGroupId',
      label: '调整后客群ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客群ID',
      },
    },
    {
      fieldName: 'afterGroupCode',
      label: '调整后客户群编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客户群编号',
      },
    },
    {
      fieldName: 'afterGroupName',
      label: '调整后客户群名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客户群名称',
      },
    },
    {
      fieldName: 'afterGroupCategory',
      label: '调整后客户群分类',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客户群分类',
      },
    },
    {
      fieldName: 'afterManagerUserId',
      label: '调整后客群管理员用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入调整后客群管理员用户ID',
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
      fieldName: 'beforeGroupId',
      label: '调整前客群ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客群ID',
      },
    },
    {
      fieldName: 'beforeGroupCode',
      label: '调整前客户群编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客户群编号',
      },
    },
    {
      fieldName: 'beforeGroupName',
      label: '调整前客户群名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客户群名称',
      },
    },
    {
      fieldName: 'beforeGroupCategory',
      label: '调整前客户群分类',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客户群分类',
      },
    },
    {
      fieldName: 'beforeManagerUserId',
      label: '调整前客群管理员用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整前客群管理员用户ID',
      },
    },
    {
      fieldName: 'afterGroupId',
      label: '调整后客群ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客群ID',
      },
    },
    {
      fieldName: 'afterGroupCode',
      label: '调整后客户群编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客户群编号',
      },
    },
    {
      fieldName: 'afterGroupName',
      label: '调整后客户群名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客户群名称',
      },
    },
    {
      fieldName: 'afterGroupCategory',
      label: '调整后客户群分类',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客户群分类',
      },
    },
    {
      fieldName: 'afterManagerUserId',
      label: '调整后客群管理员用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入调整后客群管理员用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGroupHistoryApi.CustomerGroupHistory>['columns'] {
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
      field: 'beforeGroupId',
      title: '调整前客群ID',
      minWidth: 120,
    },
    {
      field: 'beforeGroupCode',
      title: '调整前客户群编号',
      minWidth: 120,
    },
    {
      field: 'beforeGroupName',
      title: '调整前客户群名称',
      minWidth: 120,
    },
    {
      field: 'beforeGroupCategory',
      title: '调整前客户群分类',
      minWidth: 120,
    },
    {
      field: 'beforeManagerUserId',
      title: '调整前客群管理员用户ID',
      minWidth: 120,
    },
    {
      field: 'afterGroupId',
      title: '调整后客群ID',
      minWidth: 120,
    },
    {
      field: 'afterGroupCode',
      title: '调整后客户群编号',
      minWidth: 120,
    },
    {
      field: 'afterGroupName',
      title: '调整后客户群名称',
      minWidth: 120,
    },
    {
      field: 'afterGroupCategory',
      title: '调整后客户群分类',
      minWidth: 120,
    },
    {
      field: 'afterManagerUserId',
      title: '调整后客群管理员用户ID',
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