import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDiscountOverviewApi } from '#/api/aicrm/customerdiscountoverview';

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
      fieldName: 'discountType',
      label: '贴现类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择贴现类型',
      },
    },
    {
      fieldName: 'billNo',
      label: '票据号码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入票据号码',
      },
    },
    {
      fieldName: 'billType',
      label: '票据类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择票据类型',
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
      fieldName: 'billAmount',
      label: '票据金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入票据金额',
      },
    },
    {
      fieldName: 'discountAmount',
      label: '贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴现金额',
      },
    },
    {
      fieldName: 'discountBalance',
      label: '贴现余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴现余额',
      },
    },
    {
      fieldName: 'interestAmount',
      label: '贴息金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴息金额',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实付金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实付金额',
      },
    },
    {
      fieldName: 'discountCount',
      label: '贴现笔数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴现笔数',
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
      fieldName: 'cumulativeYearAmount',
      label: '本年累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年累计贴现金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度累计贴现金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计贴现金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月累计贴现金额',
      },
    },
    {
      fieldName: 'discountRate',
      label: '贴现利率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴现利率',
      },
    },
    {
      fieldName: 'discountDays',
      label: '贴现天数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贴现天数',
      },
    },
    {
      fieldName: 'billIssueDate',
      label: '票据出票日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'billDueDate',
      label: '票据到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'discountDate',
      label: '贴现日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'settleDate',
      label: '结算日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'drawer',
      label: '出票人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入出票人',
      },
    },
    {
      fieldName: 'payee',
      label: '收款人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入收款人',
      },
    },
    {
      fieldName: 'acceptor',
      label: '承兑人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入承兑人',
      },
    },
    {
      fieldName: 'acceptanceBank',
      label: '承兑银行',
      component: 'Input',
      componentProps: {
        placeholder: '请输入承兑银行',
      },
    },
    {
      fieldName: 'discountStatus',
      label: '贴现状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'settlementStatus',
      label: '结算状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      fieldName: 'discountType',
      label: '贴现类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贴现类型',
      },
    },
    {
      fieldName: 'billNo',
      label: '票据号码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入票据号码',
      },
    },
    {
      fieldName: 'billType',
      label: '票据类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择票据类型',
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
      fieldName: 'billAmount',
      label: '票据金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入票据金额',
      },
    },
    {
      fieldName: 'discountAmount',
      label: '贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴现金额',
      },
    },
    {
      fieldName: 'discountBalance',
      label: '贴现余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴现余额',
      },
    },
    {
      fieldName: 'interestAmount',
      label: '贴息金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴息金额',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实付金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实付金额',
      },
    },
    {
      fieldName: 'discountCount',
      label: '贴现笔数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴现笔数',
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
      fieldName: 'cumulativeYearAmount',
      label: '本年累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年累计贴现金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度累计贴现金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月累计贴现金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计贴现金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月累计贴现金额',
      },
    },
    {
      fieldName: 'discountRate',
      label: '贴现利率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴现利率',
      },
    },
    {
      fieldName: 'discountDays',
      label: '贴现天数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贴现天数',
      },
    },
    {
      fieldName: 'billIssueDate',
      label: '票据出票日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'billDueDate',
      label: '票据到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'discountDate',
      label: '贴现日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'settleDate',
      label: '结算日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'drawer',
      label: '出票人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入出票人',
      },
    },
    {
      fieldName: 'payee',
      label: '收款人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入收款人',
      },
    },
    {
      fieldName: 'acceptor',
      label: '承兑人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入承兑人',
      },
    },
    {
      fieldName: 'acceptanceBank',
      label: '承兑银行',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入承兑银行',
      },
    },
    {
      fieldName: 'discountStatus',
      label: '贴现状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贴现状态',
      },
    },
    {
      fieldName: 'settlementStatus',
      label: '结算状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择结算状态',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerDiscountOverviewApi.CustomerDiscountOverview>['columns'] {
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
      field: 'discountType',
      title: '贴现类型',
      minWidth: 120,
    },
    {
      field: 'billNo',
      title: '票据号码',
      minWidth: 120,
    },
    {
      field: 'billType',
      title: '票据类型',
      minWidth: 120,
    },
    {
      field: 'currency',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'billAmount',
      title: '票据金额',
      minWidth: 120,
    },
    {
      field: 'discountAmount',
      title: '贴现金额',
      minWidth: 120,
    },
    {
      field: 'discountBalance',
      title: '贴现余额',
      minWidth: 120,
    },
    {
      field: 'interestAmount',
      title: '贴息金额',
      minWidth: 120,
    },
    {
      field: 'actualAmount',
      title: '实付金额',
      minWidth: 120,
    },
    {
      field: 'discountCount',
      title: '贴现笔数',
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
      field: 'cumulativeYearAmount',
      title: '本年累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeAmount',
      title: '上年累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeQuarterAmount',
      title: '本季度累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeQuarterAmount',
      title: '上年同期季度累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeMonthAmount',
      title: '本月累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeMonthAmount',
      title: '上年同期月累计贴现金额',
      minWidth: 120,
    },
    {
      field: 'discountRate',
      title: '贴现利率',
      minWidth: 120,
    },
    {
      field: 'discountDays',
      title: '贴现天数',
      minWidth: 120,
    },
    {
      field: 'billIssueDate',
      title: '票据出票日期',
      minWidth: 120,
    },
    {
      field: 'billDueDate',
      title: '票据到期日期',
      minWidth: 120,
    },
    {
      field: 'discountDate',
      title: '贴现日期',
      minWidth: 120,
    },
    {
      field: 'settleDate',
      title: '结算日期',
      minWidth: 120,
    },
    {
      field: 'drawer',
      title: '出票人',
      minWidth: 120,
    },
    {
      field: 'payee',
      title: '收款人',
      minWidth: 120,
    },
    {
      field: 'acceptor',
      title: '承兑人',
      minWidth: 120,
    },
    {
      field: 'acceptanceBank',
      title: '承兑银行',
      minWidth: 120,
    },
    {
      field: 'discountStatus',
      title: '贴现状态',
      minWidth: 120,
    },
    {
      field: 'settlementStatus',
      title: '结算状态',
      minWidth: 120,
    },
    {
      field: 'profitContribution',
      title: '利润贡献',
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