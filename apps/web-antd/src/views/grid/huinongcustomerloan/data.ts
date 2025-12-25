import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridHuinongCustomerLoanApi } from '#/api/grid/huinongcustomerloan';

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
      label: '客户ID (关联 grid_customer)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID (关联 grid_customer)',
      },
    },
    {
      fieldName: 'customerCategory',
      label: '客户大类 (关联字典 aicrm_customer_category)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户大类 (关联字典 aicrm_customer_category)',
      },
    },
    {
      fieldName: 'subdivisionType',
      label: '细分类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择细分类型',
      },
    },
    {
      fieldName: 'businessAddress',
      label: '经营地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营地址',
      },
    },
    {
      fieldName: 'gender',
      label: '性别: 男/女',
      component: 'Input',
      componentProps: {
        placeholder: '请输入性别: 男/女',
      },
    },
    {
      fieldName: 'customerSituation',
      label: '客户情况',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户情况',
      },
    },
    {
      fieldName: 'businessYears',
      label: '经营年限',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营年限',
      },
    },
    {
      fieldName: 'currentFinancing',
      label: '当前融资情况',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前融资情况',
      },
    },
    {
      fieldName: 'creditDemand',
      label: '信贷产品需求',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信贷产品需求',
      },
    },
    {
      fieldName: 'demandMonth',
      label: '需求月份 (1-12)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求月份 (1-12)',
      },
    },
    {
      fieldName: 'demandPeriod',
      label: '需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)',
      },
    },
    {
      fieldName: 'businessProgress',
      label: '业务进展',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务进展',
      },
    },
    {
      fieldName: 'customerSource',
      label: '客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)',
      },
    },
    {
      fieldName: 'isApplied',
      label: '是否进件',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'applyTime',
      label: '进件时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isApproved',
      label: '是否审批通过',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'approveTime',
      label: '审批通过时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'loanProductName',
      label: '贷款产品名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款产品名称',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额 (元)',
      },
    },
    {
      fieldName: 'creditLimit',
      label: '授信额度 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信额度 (元)',
      },
    },
    {
      fieldName: 'loanBalance',
      label: '贷款余额 (元)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额 (元)',
      },
    },
    {
      fieldName: 'overdueStatus',
      label: '逾期状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isFormalCustomer',
      label: '是否正式客户',
      component: 'Switch',
      componentProps: {
        checkedChildren: '是',
        unCheckedChildren: '否',
      },
      defaultValue: false,
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
      fieldName: 'customerName',
      label: '客户姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'phone',
      label: '手机号码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入手机号码',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridHuinongCustomerLoanApi.HuinongCustomerLoan>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'customerName',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'phone',
      title: '手机号码',
      minWidth: 130,
    },
    {
      field: 'gridName',
      title: '所属站点',
      minWidth: 150,
    },
    {
      field: 'customerCategory',
      title: '客户大类',
      minWidth: 120,
    },
    {
      field: 'subdivisionType',
      title: '细分类型',
      minWidth: 120,
    },
    {
      field: 'businessYears',
      title: '经营年限',
      minWidth: 100,
    },
    {
      field: 'currentFinancing',
      title: '当前融资情况',
      minWidth: 150,
    },
    {
      field: 'isFormalCustomer',
      title: '是否正式客户',
      minWidth: 120,
      slots: { default: 'isFormalCustomer' },
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}