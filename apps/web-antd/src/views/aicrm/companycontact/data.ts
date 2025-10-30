import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyContactApi } from '#/api/aicrm/companycontact';

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
      label: '客户ID(关联crm_company_customer.customer_id)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID(关联crm_company_customer.customer_id)',
      },
    },
    {
      fieldName: 'contactType',
      label: '联系方式类型(手机、座机、QQ、微信、邮箱等)',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择联系方式类型(手机、座机、QQ、微信、邮箱等)',
      },
    },
    {
      fieldName: 'contactPerson',
      label: '联系人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系人姓名',
      },
    },
    {
      fieldName: 'contactMethod',
      label: '联系方式(电话号码、QQ号、微信号等)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式(电话号码、QQ号、微信号等)',
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否首选项(否、是)',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'sourceSystem',
      label: '来源系统(ECIF、CRM、零售CRM等)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入来源系统(ECIF、CRM、零售CRM等)',
      },
    },
    {
      fieldName: 'contactSeq',
      label: '联系方式序号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式序号',
      },
    },
    {
      fieldName: 'contactDesc',
      label: '联系方式描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式描述',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'remark',
      label: '备注说明',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注说明',
      },
    },
    {
      fieldName: 'etlDate',
      label: 'ETL导入日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'oldTxSeqNo',
      label: '老系统交易序列号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统交易序列号',
      },
    },
    {
      fieldName: 'oldLastUpdateSys',
      label: '老系统最后更新系统',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统最后更新系统',
      },
    },
    {
      fieldName: 'oldLastUpdateUser',
      label: '老系统最后更新用户',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统最后更新用户',
      },
    },
    {
      fieldName: 'oldLastUpdateTm',
      label: '老系统最后更新时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'oldContactId',
      label: '老系统联系方式ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统联系方式ID',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID(关联crm_company_customer.customer_id)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID(关联crm_company_customer.customer_id)',
      },
    },
    {
      fieldName: 'contactType',
      label: '联系方式类型(手机、座机、QQ、微信、邮箱等)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择联系方式类型(手机、座机、QQ、微信、邮箱等)',
      },
    },
    {
      fieldName: 'contactPerson',
      label: '联系人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系人姓名',
      },
    },
    {
      fieldName: 'contactMethod',
      label: '联系方式(电话号码、QQ号、微信号等)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系方式(电话号码、QQ号、微信号等)',
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否首选项(否、是)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否首选项(否、是)',
      },
    },
    {
      fieldName: 'sourceSystem',
      label: '来源系统(ECIF、CRM、零售CRM等)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入来源系统(ECIF、CRM、零售CRM等)',
      },
    },
    {
      fieldName: 'contactSeq',
      label: '联系方式序号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系方式序号',
      },
    },
    {
      fieldName: 'contactDesc',
      label: '联系方式描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系方式描述',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择状态',
      },
    },
    {
      fieldName: 'remark',
      label: '备注说明',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注说明',
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
      fieldName: 'etlDate',
      label: 'ETL导入日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'oldTxSeqNo',
      label: '老系统交易序列号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统交易序列号',
      },
    },
    {
      fieldName: 'oldLastUpdateSys',
      label: '老系统最后更新系统',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统最后更新系统',
      },
    },
    {
      fieldName: 'oldLastUpdateUser',
      label: '老系统最后更新用户',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统最后更新用户',
      },
    },
    {
      fieldName: 'oldLastUpdateTm',
      label: '老系统最后更新时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'oldContactId',
      label: '老系统联系方式ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统联系方式ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyContactApi.CompanyContact>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID(关联crm_company_customer.customer_id)',
      minWidth: 120,
    },
    {
      field: 'contactType',
      title: '联系方式类型(手机、座机、QQ、微信、邮箱等)',
      minWidth: 120,
    },
    {
      field: 'contactPerson',
      title: '联系人姓名',
      minWidth: 120,
    },
    {
      field: 'contactMethod',
      title: '联系方式(电话号码、QQ号、微信号等)',
      minWidth: 120,
    },
    {
      field: 'isPrimary',
      title: '是否首选项(否、是)',
      minWidth: 120,
    },
    {
      field: 'sourceSystem',
      title: '来源系统(ECIF、CRM、零售CRM等)',
      minWidth: 120,
    },
    {
      field: 'contactSeq',
      title: '联系方式序号',
      minWidth: 120,
    },
    {
      field: 'contactDesc',
      title: '联系方式描述',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注说明',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'etlDate',
      title: 'ETL导入日期',
      minWidth: 120,
    },
    {
      field: 'oldTxSeqNo',
      title: '老系统交易序列号',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateSys',
      title: '老系统最后更新系统',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateUser',
      title: '老系统最后更新用户',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateTm',
      title: '老系统最后更新时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'oldContactId',
      title: '老系统联系方式ID',
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