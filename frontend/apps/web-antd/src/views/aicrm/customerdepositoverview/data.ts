import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDepositOverviewApi } from '#/api/aicrm/customerdepositoverview';

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
      fieldName: 'depositType',
      label: '存款类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择存款类型',
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
      fieldName: 'accountNo',
      label: '账号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账号',
      },
    },
    {
      fieldName: 'cardNo',
      label: '卡号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡号',
      },
    },
    {
      fieldName: 'depositBalance',
      label: '存款余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款余额',
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
      fieldName: 'originalAmount',
      label: '原币金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原币金额',
      },
    },
    {
      fieldName: 'depositAccountCount',
      label: '存款账户数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款账户数',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'subjectCode',
      label: '科目代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入科目代码',
      },
    },
    {
      fieldName: 'productId',
      label: '产品ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品ID',
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
      fieldName: 'realBalanceYearAvg',
      label: '本年实际余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeYear',
      label: '本年累计存款',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年累计存款',
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
      fieldName: 'realBalanceQuarterAvg',
      label: '本季度实际余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeQuarter',
      label: '本季度累计存款',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度累计存款',
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
      fieldName: 'realBalanceMonthAvg',
      label: '本月实际余额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeMonth',
      label: '本月累计存款',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月累计存款',
      },
    },
    {
      fieldName: 'monthTotalIn',
      label: '月度总流入',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月度总流入',
      },
    },
    {
      fieldName: 'monthTotalOut',
      label: '月度总流出',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月度总流出',
      },
    },
    {
      fieldName: 'buyAmount',
      label: '购买金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入购买金额',
      },
    },
    {
      fieldName: 'interestRate',
      label: '存款利率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款利率',
      },
    },
    {
      fieldName: 'ftpPrice',
      label: 'FTP定价',
      component: 'Input',
      componentProps: {
        placeholder: '请输入FTP定价',
      },
    },
    {
      fieldName: 'depositProfit',
      label: '存款利润贡献',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款利润贡献',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'startInterestDate',
      label: '起息日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'logoutDate',
      label: '销户日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'orgNo',
      label: '机构编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机构编号',
      },
    },
    {
      fieldName: 'orgName',
      label: '机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机构名称',
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
      fieldName: 'depositType',
      label: '存款类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择存款类型',
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
      fieldName: 'accountNo',
      label: '账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账号',
      },
    },
    {
      fieldName: 'cardNo',
      label: '卡号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡号',
      },
    },
    {
      fieldName: 'depositBalance',
      label: '存款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款余额',
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
      fieldName: 'originalAmount',
      label: '原币金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入原币金额',
      },
    },
    {
      fieldName: 'depositAccountCount',
      label: '存款账户数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款账户数',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态',
      },
    },
    {
      fieldName: 'subjectCode',
      label: '科目代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入科目代码',
      },
    },
    {
      fieldName: 'productId',
      label: '产品ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品ID',
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
      fieldName: 'realBalanceYearAvg',
      label: '本年实际余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeYear',
      label: '本年累计存款',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年累计存款',
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
      fieldName: 'realBalanceQuarterAvg',
      label: '本季度实际余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeQuarter',
      label: '本季度累计存款',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度累计存款',
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
      fieldName: 'realBalanceMonthAvg',
      label: '本月实际余额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月实际余额日均',
      },
    },
    {
      fieldName: 'depositCumulativeMonth',
      label: '本月累计存款',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月累计存款',
      },
    },
    {
      fieldName: 'monthTotalIn',
      label: '月度总流入',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月度总流入',
      },
    },
    {
      fieldName: 'monthTotalOut',
      label: '月度总流出',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月度总流出',
      },
    },
    {
      fieldName: 'buyAmount',
      label: '购买金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入购买金额',
      },
    },
    {
      fieldName: 'interestRate',
      label: '存款利率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款利率',
      },
    },
    {
      fieldName: 'ftpPrice',
      label: 'FTP定价',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入FTP定价',
      },
    },
    {
      fieldName: 'depositProfit',
      label: '存款利润贡献',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款利润贡献',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'startInterestDate',
      label: '起息日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'logoutDate',
      label: '销户日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'orgNo',
      label: '机构编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机构编号',
      },
    },
    {
      fieldName: 'orgName',
      label: '机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机构名称',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerDepositOverviewApi.CustomerDepositOverview>['columns'] {
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
      field: 'depositType',
      title: '存款类型',
      minWidth: 120,
    },
    {
      field: 'currency',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '账号',
      minWidth: 120,
    },
    {
      field: 'cardNo',
      title: '卡号',
      minWidth: 120,
    },
    {
      field: 'depositBalance',
      title: '存款余额',
      minWidth: 120,
    },
    {
      field: 'businessAmount',
      title: '业务金额',
      minWidth: 120,
    },
    {
      field: 'originalAmount',
      title: '原币金额',
      minWidth: 120,
    },
    {
      field: 'depositAccountCount',
      title: '存款账户数',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态',
      minWidth: 120,
    },
    {
      field: 'subjectCode',
      title: '科目代码',
      minWidth: 120,
    },
    {
      field: 'productId',
      title: '产品ID',
      minWidth: 120,
    },
    {
      field: 'balanceYearAvg',
      title: '本年余额日均',
      minWidth: 120,
    },
    {
      field: 'realBalanceYearAvg',
      title: '本年实际余额日均',
      minWidth: 120,
    },
    {
      field: 'depositCumulativeYear',
      title: '本年累计存款',
      minWidth: 120,
    },
    {
      field: 'balanceQuarterAvg',
      title: '本季度余额日均',
      minWidth: 120,
    },
    {
      field: 'realBalanceQuarterAvg',
      title: '本季度实际余额日均',
      minWidth: 120,
    },
    {
      field: 'depositCumulativeQuarter',
      title: '本季度累计存款',
      minWidth: 120,
    },
    {
      field: 'balanceMonthAvg',
      title: '本月余额日均',
      minWidth: 120,
    },
    {
      field: 'realBalanceMonthAvg',
      title: '本月实际余额日均',
      minWidth: 120,
    },
    {
      field: 'depositCumulativeMonth',
      title: '本月累计存款',
      minWidth: 120,
    },
    {
      field: 'monthTotalIn',
      title: '月度总流入',
      minWidth: 120,
    },
    {
      field: 'monthTotalOut',
      title: '月度总流出',
      minWidth: 120,
    },
    {
      field: 'buyAmount',
      title: '购买金额',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '存款利率',
      minWidth: 120,
    },
    {
      field: 'ftpPrice',
      title: 'FTP定价',
      minWidth: 120,
    },
    {
      field: 'depositProfit',
      title: '存款利润贡献',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期',
      minWidth: 120,
    },
    {
      field: 'startInterestDate',
      title: '起息日期',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'logoutDate',
      title: '销户日期',
      minWidth: 120,
    },
    {
      field: 'orgNo',
      title: '机构编号',
      minWidth: 120,
    },
    {
      field: 'orgName',
      title: '机构名称',
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