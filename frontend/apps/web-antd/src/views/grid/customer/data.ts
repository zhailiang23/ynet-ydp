import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridCustomerApi } from '#/api/grid/customer';

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
      label: '所属网格ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属网格ID',
      },
    },
    {
      fieldName: 'customerType',
      label: '客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'idCard',
      label: '身份证号 (脱敏: 前3后3)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入身份证号 (脱敏: 前3后3)',
      },
    },
    {
      fieldName: 'phone',
      label: '联系电话 (脱敏: 前3后4)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话 (脱敏: 前3后4)',
      },
    },
    {
      fieldName: 'phoneVerified',
      label: '手机号是否已二次验证',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'address',
      label: '详细地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入详细地址',
      },
    },
    {
      fieldName: 'location',
      label: '客户位置 (经纬度)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户位置 (经纬度)',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID (关联 system_users)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理ID (关联 system_users)',
      },
    },
    {
      fieldName: 'source',
      label: '客户来源',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户来源',
      },
    },
    {
      fieldName: 'status',
      label: '状态: NORMAL/INACTIVE/BLACKLIST',
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
      label: '所属网格ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属网格ID',
      },
    },
    {
      fieldName: 'customerType',
      label: '客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'idCard',
      label: '身份证号 (脱敏: 前3后3)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入身份证号 (脱敏: 前3后3)',
      },
    },
    {
      fieldName: 'phone',
      label: '联系电话 (脱敏: 前3后4)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系电话 (脱敏: 前3后4)',
      },
    },
    {
      fieldName: 'phoneVerified',
      label: '手机号是否已二次验证',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择手机号是否已二次验证',
      },
    },
    {
      fieldName: 'address',
      label: '详细地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入详细地址',
      },
    },
    {
      fieldName: 'location',
      label: '客户位置 (经纬度)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户位置 (经纬度)',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID (关联 system_users)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理ID (关联 system_users)',
      },
    },
    {
      fieldName: 'source',
      label: '客户来源',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户来源',
      },
    },
    {
      fieldName: 'status',
      label: '状态: NORMAL/INACTIVE/BLACKLIST',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择状态: NORMAL/INACTIVE/BLACKLIST',
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
export function useGridColumns(): VxeTableGridOptions<GridCustomerApi.Customer>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '客户档案ID',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '所属网格ID',
      minWidth: 120,
    },
    {
      field: 'customerType',
      title: '客户类型: HUINONG_LOAN/TINGTANG/COMMUNITY/ZERODAI',
      minWidth: 120,
    },
    {
      field: 'customerName',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'idCard',
      title: '身份证号 (脱敏: 前3后3)',
      minWidth: 120,
    },
    {
      field: 'phone',
      title: '联系电话 (脱敏: 前3后4)',
      minWidth: 120,
    },
    {
      field: 'phoneVerified',
      title: '手机号是否已二次验证',
      minWidth: 120,
    },
    {
      field: 'address',
      title: '详细地址',
      minWidth: 120,
    },
    {
      field: 'location',
      title: '客户位置 (经纬度)',
      minWidth: 120,
    },
    {
      field: 'managerId',
      title: '客户经理ID (关联 system_users)',
      minWidth: 120,
    },
    {
      field: 'source',
      title: '客户来源',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态: NORMAL/INACTIVE/BLACKLIST',
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