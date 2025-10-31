import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerOffbalanceOverviewApi } from '#/api/aicrm/customeroffbalanceoverview';

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
      fieldName: 'businessBalance',
      label: '业务余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务余额',
      },
    },
    {
      fieldName: 'guaranteeAmount',
      label: '担保金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保金额',
      },
    },
    {
      fieldName: 'creditAmount',
      label: '授信金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信金额',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已用金额',
      },
    },
    {
      fieldName: 'availableAmount',
      label: '可用金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用金额',
      },
    },
    {
      fieldName: 'businessCount',
      label: '业务笔数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务笔数',
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
      fieldName: 'usageRate',
      label: '使用率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用率',
      },
    },
    {
      fieldName: 'startDate',
      label: '起始日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endDate',
      label: '到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'feeRate',
      label: '费率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入费率',
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
      fieldName: 'businessStatus',
      label: '业务状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
      fieldName: 'businessBalance',
      label: '业务余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务余额',
      },
    },
    {
      fieldName: 'guaranteeAmount',
      label: '担保金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保金额',
      },
    },
    {
      fieldName: 'creditAmount',
      label: '授信金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信金额',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已用金额',
      },
    },
    {
      fieldName: 'availableAmount',
      label: '可用金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可用金额',
      },
    },
    {
      fieldName: 'businessCount',
      label: '业务笔数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务笔数',
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
      fieldName: 'usageRate',
      label: '使用率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用率',
      },
    },
    {
      fieldName: 'startDate',
      label: '起始日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'endDate',
      label: '到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'feeRate',
      label: '费率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入费率',
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
      fieldName: 'businessStatus',
      label: '业务状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择业务状态',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerOffbalanceOverviewApi.CustomerOffbalanceOverview>['columns'] {
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
      field: 'businessBalance',
      title: '业务余额',
      minWidth: 120,
    },
    {
      field: 'guaranteeAmount',
      title: '担保金额',
      minWidth: 120,
    },
    {
      field: 'creditAmount',
      title: '授信金额',
      minWidth: 120,
    },
    {
      field: 'usedAmount',
      title: '已用金额',
      minWidth: 120,
    },
    {
      field: 'availableAmount',
      title: '可用金额',
      minWidth: 120,
    },
    {
      field: 'businessCount',
      title: '业务笔数',
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
      field: 'usageRate',
      title: '使用率',
      minWidth: 120,
    },
    {
      field: 'startDate',
      title: '起始日期',
      minWidth: 120,
    },
    {
      field: 'endDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'effectiveDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'feeRate',
      title: '费率',
      minWidth: 120,
    },
    {
      field: 'feeAmount',
      title: '手续费金额',
      minWidth: 120,
    },
    {
      field: 'businessStatus',
      title: '业务状态',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级',
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