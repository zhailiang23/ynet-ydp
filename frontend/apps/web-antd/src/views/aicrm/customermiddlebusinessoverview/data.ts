import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerMiddleBusinessOverviewApi } from '#/api/aicrm/customermiddlebusinessoverview';

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
      fieldName: 'customerNo',
      label: '客户编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户编号',
      },
    },
    {
      fieldName: 'statisticsDate',
      label: '统计日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择业务类型',
      },
    },
    {
      fieldName: 'businessCode',
      label: '业务代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务代码',
      },
    },
    {
      fieldName: 'businessName',
      label: '业务名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务名称',
      },
    },
    {
      fieldName: 'currency',
      label: '币种',
      component: 'Input',
      componentProps: {
        placeholder: '请输入币种',
      },
    },
    {
      fieldName: 'businessAmount',
      label: '业务金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务金额',
      },
    },
    {
      fieldName: 'transactionAmount',
      label: '交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易金额',
      },
    },
    {
      fieldName: 'feeAmount',
      label: '手续费金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手续费金额',
      },
    },
    {
      fieldName: 'commissionAmount',
      label: '佣金金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入佣金金额',
      },
    },
    {
      fieldName: 'transactionCount',
      label: '交易笔数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易笔数',
      },
    },
    {
      fieldName: 'amountYearAvg',
      label: '本年金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountYearAvg',
      label: '上年金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年金额日均',
      },
    },
    {
      fieldName: 'amountQuarterAvg',
      label: '本季度金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountQuarterAvg',
      label: '上年同期季度金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度金额日均',
      },
    },
    {
      fieldName: 'amountMonthAvg',
      label: '本月金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountMonthAvg',
      label: '上年同期月金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月金额日均',
      },
    },
    {
      fieldName: 'cumulativeYearAmount',
      label: '本年累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年累计金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度累计金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月累计金额',
      },
    },
    {
      fieldName: 'profitContribution',
      label: '利润贡献',
      component: 'Input',
      componentProps: {
        placeholder: '请输入利润贡献',
      },
    },
    {
      fieldName: 'feeIncome',
      label: '手续费收入',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手续费收入',
      },
    },
    {
      fieldName: 'commissionIncome',
      label: '佣金收入',
      component: 'Input',
      componentProps: {
        placeholder: '请输入佣金收入',
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
      fieldName: 'customerNo',
      label: '客户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户编号',
      },
    },
    {
      fieldName: 'statisticsDate',
      label: '统计日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择业务类型',
      },
    },
    {
      fieldName: 'businessCode',
      label: '业务代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务代码',
      },
    },
    {
      fieldName: 'businessName',
      label: '业务名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务名称',
      },
    },
    {
      fieldName: 'currency',
      label: '币种',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入币种',
      },
    },
    {
      fieldName: 'businessAmount',
      label: '业务金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务金额',
      },
    },
    {
      fieldName: 'transactionAmount',
      label: '交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易金额',
      },
    },
    {
      fieldName: 'feeAmount',
      label: '手续费金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入手续费金额',
      },
    },
    {
      fieldName: 'commissionAmount',
      label: '佣金金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入佣金金额',
      },
    },
    {
      fieldName: 'transactionCount',
      label: '交易笔数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易笔数',
      },
    },
    {
      fieldName: 'amountYearAvg',
      label: '本年金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountYearAvg',
      label: '上年金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年金额日均',
      },
    },
    {
      fieldName: 'amountQuarterAvg',
      label: '本季度金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountQuarterAvg',
      label: '上年同期季度金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度金额日均',
      },
    },
    {
      fieldName: 'amountMonthAvg',
      label: '本月金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountMonthAvg',
      label: '上年同期月金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月金额日均',
      },
    },
    {
      fieldName: 'cumulativeYearAmount',
      label: '本年累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年累计金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度累计金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月累计金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月累计金额',
      },
    },
    {
      fieldName: 'profitContribution',
      label: '利润贡献',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入利润贡献',
      },
    },
    {
      fieldName: 'feeIncome',
      label: '手续费收入',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入手续费收入',
      },
    },
    {
      fieldName: 'commissionIncome',
      label: '佣金收入',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入佣金收入',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerMiddleBusinessOverviewApi.CustomerMiddleBusinessOverview>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 120,
    },
    {
      field: 'customerNo',
      title: '客户编号',
      minWidth: 120,
    },
    {
      field: 'statisticsDate',
      title: '统计日期',
      minWidth: 120,
    },
    {
      field: 'businessType',
      title: '业务类型',
      minWidth: 120,
    },
    {
      field: 'businessCode',
      title: '业务代码',
      minWidth: 120,
    },
    {
      field: 'businessName',
      title: '业务名称',
      minWidth: 120,
    },
    {
      field: 'currency',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'businessAmount',
      title: '业务金额',
      minWidth: 120,
    },
    {
      field: 'transactionAmount',
      title: '交易金额',
      minWidth: 120,
    },
    {
      field: 'feeAmount',
      title: '手续费金额',
      minWidth: 120,
    },
    {
      field: 'commissionAmount',
      title: '佣金金额',
      minWidth: 120,
    },
    {
      field: 'transactionCount',
      title: '交易笔数',
      minWidth: 120,
    },
    {
      field: 'amountYearAvg',
      title: '本年金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountYearAvg',
      title: '上年金额日均',
      minWidth: 120,
    },
    {
      field: 'amountQuarterAvg',
      title: '本季度金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountQuarterAvg',
      title: '上年同期季度金额日均',
      minWidth: 120,
    },
    {
      field: 'amountMonthAvg',
      title: '本月金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountMonthAvg',
      title: '上年同期月金额日均',
      minWidth: 120,
    },
    {
      field: 'cumulativeYearAmount',
      title: '本年累计金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeAmount',
      title: '上年累计金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeQuarterAmount',
      title: '本季度累计金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeQuarterAmount',
      title: '上年同期季度累计金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeMonthAmount',
      title: '本月累计金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeMonthAmount',
      title: '上年同期月累计金额',
      minWidth: 120,
    },
    {
      field: 'profitContribution',
      title: '利润贡献',
      minWidth: 120,
    },
    {
      field: 'feeIncome',
      title: '手续费收入',
      minWidth: 120,
    },
    {
      field: 'commissionIncome',
      title: '佣金收入',
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