import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';

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
      label: '客户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择证件类型',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件号码',
      },
    },
    {
      fieldName: 'identityName',
      label: '证件上的姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件上的姓名',
      },
    },
    {
      fieldName: 'issueAuthority',
      label: '发证机关',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发证机关',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发证日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'expiryDate',
      label: '有效期至',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否主证件',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'identityAddress',
      label: '证件地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件地址',
      },
    },
    {
      fieldName: 'identityFrontUrl',
      label: '证件正面照片URL',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件正面照片URL',
      },
    },
    {
      fieldName: 'identityBackUrl',
      label: '证件反面照片URL',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件反面照片URL',
      },
    },
    {
      fieldName: 'verificationStatus',
      label: '核验状态',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'verificationTime',
      label: '核验时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'verificationRemark',
      label: '核验备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入核验备注',
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
      label: '客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择证件类型',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件号码',
      },
    },
    {
      fieldName: 'identityName',
      label: '证件上的姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件上的姓名',
      },
    },
    {
      fieldName: 'issueAuthority',
      label: '发证机关',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发证机关',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发证日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'expiryDate',
      label: '有效期至',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否主证件',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否主证件',
      },
    },
    {
      fieldName: 'identityAddress',
      label: '证件地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件地址',
      },
    },
    {
      fieldName: 'identityFrontUrl',
      label: '证件正面照片URL',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件正面照片URL',
      },
    },
    {
      fieldName: 'identityBackUrl',
      label: '证件反面照片URL',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件反面照片URL',
      },
    },
    {
      fieldName: 'verificationStatus',
      label: '核验状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择核验状态',
      },
    },
    {
      fieldName: 'verificationTime',
      label: '核验时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'verificationRemark',
      label: '核验备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入核验备注',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerIdentityApi.CustomerIdentity>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '证件信息主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 120,
    },
    {
      field: 'identityType',
      title: '证件类型',
      minWidth: 120,
    },
    {
      field: 'identityNo',
      title: '证件号码',
      minWidth: 120,
    },
    {
      field: 'identityName',
      title: '证件上的姓名',
      minWidth: 120,
    },
    {
      field: 'issueAuthority',
      title: '发证机关',
      minWidth: 120,
    },
    {
      field: 'issueDate',
      title: '发证日期',
      minWidth: 120,
    },
    {
      field: 'expiryDate',
      title: '有效期至',
      minWidth: 120,
    },
    {
      field: 'isPrimary',
      title: '是否主证件',
      minWidth: 120,
    },
    {
      field: 'identityAddress',
      title: '证件地址',
      minWidth: 120,
    },
    {
      field: 'identityFrontUrl',
      title: '证件正面照片URL',
      minWidth: 120,
    },
    {
      field: 'identityBackUrl',
      title: '证件反面照片URL',
      minWidth: 120,
    },
    {
      field: 'verificationStatus',
      title: '核验状态',
      minWidth: 120,
    },
    {
      field: 'verificationTime',
      title: '核验时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'verificationRemark',
      title: '核验备注',
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