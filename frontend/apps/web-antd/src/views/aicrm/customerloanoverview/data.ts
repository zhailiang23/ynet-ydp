import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerLoanOverviewApi } from '#/api/aicrm/customerloanoverview';

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
      fieldName: 'loanType',
      label: '贷款类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择贷款类型',
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
      fieldName: 'loanAmount',
      label: '贷款余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额',
      },
    },
    {
      fieldName: 'loanAccountCount',
      label: '贷款账户数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款账户数',
      },
    },
    {
      fieldName: 'loanCustomerCount',
      label: '贷款客户数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款客户数',
      },
    },
    {
      fieldName: 'normalBusinessBalance',
      label: '正常业务余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入正常业务余额',
      },
    },
    {
      fieldName: 'overdueBalance',
      label: '逾期余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入逾期余额',
      },
    },
    {
      fieldName: 'badLoanBalance',
      label: '不良贷款余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入不良贷款余额',
      },
    },
    {
      fieldName: 'businessBalance',
      label: '业务余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务余额',
      },
    },
    {
      fieldName: 'balanceYearAvg',
      label: '本年余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceYearAvg',
      label: '上年余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年余额日均',
      },
    },
    {
      fieldName: 'balanceQuarterAvg',
      label: '本季度余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceQuarterAvg',
      label: '上年同期季度余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度余额日均',
      },
    },
    {
      fieldName: 'balanceMonthAvg',
      label: '本月余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceMonthAvg',
      label: '上年同期月余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月余额日均',
      },
    },
    {
      fieldName: 'loanAmountTotal',
      label: '贷款金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额',
      },
    },
    {
      fieldName: 'lastYearLoanAmount',
      label: '上年贷款余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年贷款余额',
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
      fieldName: 'creditTotalAmount',
      label: '授信总额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信总额',
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
      fieldName: 'outsideLoanUsage',
      label: '表外贷款使用率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入表外贷款使用率',
      },
    },
    {
      fieldName: 'insideLoanUsage',
      label: '表内贷款使用率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入表内贷款使用率',
      },
    },
    {
      fieldName: 'billLoanUsage',
      label: '票据贷款使用率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入票据贷款使用率',
      },
    },
    {
      fieldName: 'loanProfit',
      label: '贷款利润贡献',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款利润贡献',
      },
    },
    {
      fieldName: 'interestRate',
      label: '贷款利率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款利率',
      },
    },
    {
      fieldName: 'overdueDays',
      label: '逾期天数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入逾期天数',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级',
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
      fieldName: 'loanType',
      label: '贷款类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贷款类型',
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
      fieldName: 'loanAmount',
      label: '贷款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款余额',
      },
    },
    {
      fieldName: 'loanAccountCount',
      label: '贷款账户数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款账户数',
      },
    },
    {
      fieldName: 'loanCustomerCount',
      label: '贷款客户数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款客户数',
      },
    },
    {
      fieldName: 'normalBusinessBalance',
      label: '正常业务余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入正常业务余额',
      },
    },
    {
      fieldName: 'overdueBalance',
      label: '逾期余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入逾期余额',
      },
    },
    {
      fieldName: 'badLoanBalance',
      label: '不良贷款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入不良贷款余额',
      },
    },
    {
      fieldName: 'businessBalance',
      label: '业务余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务余额',
      },
    },
    {
      fieldName: 'balanceYearAvg',
      label: '本年余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceYearAvg',
      label: '上年余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年余额日均',
      },
    },
    {
      fieldName: 'balanceQuarterAvg',
      label: '本季度余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceQuarterAvg',
      label: '上年同期季度余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度余额日均',
      },
    },
    {
      fieldName: 'balanceMonthAvg',
      label: '本月余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月余额日均',
      },
    },
    {
      fieldName: 'lastYearBalanceMonthAvg',
      label: '上年同期月余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月余额日均',
      },
    },
    {
      fieldName: 'loanAmountTotal',
      label: '贷款金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款金额',
      },
    },
    {
      fieldName: 'lastYearLoanAmount',
      label: '上年贷款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年贷款余额',
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
      fieldName: 'creditTotalAmount',
      label: '授信总额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信总额',
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
      fieldName: 'outsideLoanUsage',
      label: '表外贷款使用率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入表外贷款使用率',
      },
    },
    {
      fieldName: 'insideLoanUsage',
      label: '表内贷款使用率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入表内贷款使用率',
      },
    },
    {
      fieldName: 'billLoanUsage',
      label: '票据贷款使用率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入票据贷款使用率',
      },
    },
    {
      fieldName: 'loanProfit',
      label: '贷款利润贡献',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款利润贡献',
      },
    },
    {
      fieldName: 'interestRate',
      label: '贷款利率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款利率',
      },
    },
    {
      fieldName: 'overdueDays',
      label: '逾期天数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入逾期天数',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerLoanOverviewApi.CustomerLoanOverview>['columns'] {
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
      field: 'loanType',
      title: '贷款类型',
      minWidth: 120,
    },
    {
      field: 'currency',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款余额',
      minWidth: 120,
    },
    {
      field: 'loanAccountCount',
      title: '贷款账户数',
      minWidth: 120,
    },
    {
      field: 'loanCustomerCount',
      title: '贷款客户数',
      minWidth: 120,
    },
    {
      field: 'normalBusinessBalance',
      title: '正常业务余额',
      minWidth: 120,
    },
    {
      field: 'overdueBalance',
      title: '逾期余额',
      minWidth: 120,
    },
    {
      field: 'badLoanBalance',
      title: '不良贷款余额',
      minWidth: 120,
    },
    {
      field: 'businessBalance',
      title: '业务余额',
      minWidth: 120,
    },
    {
      field: 'balanceYearAvg',
      title: '本年余额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearBalanceYearAvg',
      title: '上年余额日均',
      minWidth: 120,
    },
    {
      field: 'balanceQuarterAvg',
      title: '本季度余额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearBalanceQuarterAvg',
      title: '上年同期季度余额日均',
      minWidth: 120,
    },
    {
      field: 'balanceMonthAvg',
      title: '本月余额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearBalanceMonthAvg',
      title: '上年同期月余额日均',
      minWidth: 120,
    },
    {
      field: 'loanAmountTotal',
      title: '贷款金额',
      minWidth: 120,
    },
    {
      field: 'lastYearLoanAmount',
      title: '上年贷款余额',
      minWidth: 120,
    },
    {
      field: 'businessAmount',
      title: '业务金额',
      minWidth: 120,
    },
    {
      field: 'creditTotalAmount',
      title: '授信总额',
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
      field: 'outsideLoanUsage',
      title: '表外贷款使用率',
      minWidth: 120,
    },
    {
      field: 'insideLoanUsage',
      title: '表内贷款使用率',
      minWidth: 120,
    },
    {
      field: 'billLoanUsage',
      title: '票据贷款使用率',
      minWidth: 120,
    },
    {
      field: 'loanProfit',
      title: '贷款利润贡献',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '贷款利率',
      minWidth: 120,
    },
    {
      field: 'overdueDays',
      title: '逾期天数',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级',
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