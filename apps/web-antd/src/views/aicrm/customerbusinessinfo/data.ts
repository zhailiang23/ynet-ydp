import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerBusinessInfoApi } from '#/api/aicrm/customerbusinessinfo';

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
      fieldName: 'businessName',
      label: '经营主体名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营主体名称',
      },
    },
    {
      fieldName: 'businessType',
      label: '经营类型（字典: aicrm_business_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择经营类型（字典: aicrm_business_type）',
      },
    },
    {
      fieldName: 'businessLicenseNo',
      label: '营业执照号/统一社会信用代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营业执照号/统一社会信用代码',
      },
    },
    {
      fieldName: 'businessScope',
      label: '经营范围',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营范围',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'businessScale',
      label: '经营规模（字典: aicrm_business_scale）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营规模（字典: aicrm_business_scale）',
      },
    },
    {
      fieldName: 'businessStatus',
      label: '经营状态（字典: aicrm_business_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'registeredCapital',
      label: '注册资本（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入注册资本（元）',
      },
    },
    {
      fieldName: 'employeeCount',
      label: '员工人数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入员工人数',
      },
    },
    {
      fieldName: 'annualRevenue',
      label: '年营业额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年营业额（元）',
      },
    },
    {
      fieldName: 'monthlyRevenue',
      label: '月营业额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月营业额（元）',
      },
    },
    {
      fieldName: 'annualProfit',
      label: '年利润（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年利润（元）',
      },
    },
    {
      fieldName: 'taxRegistrationNo',
      label: '税务登记号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入税务登记号',
      },
    },
    {
      fieldName: 'isGeneralTaxpayer',
      label: '是否一般纳税人',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      fieldName: 'businessName',
      label: '经营主体名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营主体名称',
      },
    },
    {
      fieldName: 'businessType',
      label: '经营类型（字典: aicrm_business_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择经营类型（字典: aicrm_business_type）',
      },
    },
    {
      fieldName: 'businessLicenseNo',
      label: '营业执照号/统一社会信用代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营业执照号/统一社会信用代码',
      },
    },
    {
      fieldName: 'businessScope',
      label: '经营范围',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营范围',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'businessScale',
      label: '经营规模（字典: aicrm_business_scale）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营规模（字典: aicrm_business_scale）',
      },
    },
    {
      fieldName: 'businessStatus',
      label: '经营状态（字典: aicrm_business_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择经营状态（字典: aicrm_business_status）',
      },
    },
    {
      fieldName: 'registeredCapital',
      label: '注册资本（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入注册资本（元）',
      },
    },
    {
      fieldName: 'employeeCount',
      label: '员工人数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入员工人数',
      },
    },
    {
      fieldName: 'annualRevenue',
      label: '年营业额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年营业额（元）',
      },
    },
    {
      fieldName: 'monthlyRevenue',
      label: '月营业额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月营业额（元）',
      },
    },
    {
      fieldName: 'annualProfit',
      label: '年利润（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年利润（元）',
      },
    },
    {
      fieldName: 'taxRegistrationNo',
      label: '税务登记号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入税务登记号',
      },
    },
    {
      fieldName: 'isGeneralTaxpayer',
      label: '是否一般纳税人',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否一般纳税人',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerBusinessInfoApi.CustomerBusinessInfo>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '经营信息主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'businessName',
      title: '经营主体名称',
      minWidth: 120,
    },
    {
      field: 'businessType',
      title: '经营类型（字典: aicrm_business_type）',
      minWidth: 120,
    },
    {
      field: 'businessLicenseNo',
      title: '营业执照号/统一社会信用代码',
      minWidth: 120,
    },
    {
      field: 'businessScope',
      title: '经营范围',
      minWidth: 120,
    },
    {
      field: 'industry',
      title: '所属行业',
      minWidth: 120,
    },
    {
      field: 'businessScale',
      title: '经营规模（字典: aicrm_business_scale）',
      minWidth: 120,
    },
    {
      field: 'businessStatus',
      title: '经营状态（字典: aicrm_business_status）',
      minWidth: 120,
    },
    {
      field: 'registeredCapital',
      title: '注册资本（元）',
      minWidth: 120,
    },
    {
      field: 'employeeCount',
      title: '员工人数',
      minWidth: 120,
    },
    {
      field: 'annualRevenue',
      title: '年营业额（元）',
      minWidth: 120,
    },
    {
      field: 'monthlyRevenue',
      title: '月营业额（元）',
      minWidth: 120,
    },
    {
      field: 'annualProfit',
      title: '年利润（元）',
      minWidth: 120,
    },
    {
      field: 'taxRegistrationNo',
      title: '税务登记号',
      minWidth: 120,
    },
    {
      field: 'isGeneralTaxpayer',
      title: '是否一般纳税人',
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