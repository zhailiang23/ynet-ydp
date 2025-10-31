import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerChannelOverviewApi } from '#/api/aicrm/customerchanneloverview';

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
      fieldName: 'channelType',
      label: '渠道类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择渠道类型',
      },
    },
    {
      fieldName: 'channelCode',
      label: '渠道代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入渠道代码',
      },
    },
    {
      fieldName: 'channelName',
      label: '渠道名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入渠道名称',
      },
    },
    {
      fieldName: 'accessCount',
      label: '访问次数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入访问次数',
      },
    },
    {
      fieldName: 'loginCount',
      label: '登录次数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入登录次数',
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
      fieldName: 'activeDays',
      label: '活跃天数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活跃天数',
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
      fieldName: 'amountYearAvg',
      label: '本年交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountYearAvg',
      label: '上年交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年交易金额日均',
      },
    },
    {
      fieldName: 'amountQuarterAvg',
      label: '本季度交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountQuarterAvg',
      label: '上年同期季度交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度交易金额日均',
      },
    },
    {
      fieldName: 'amountMonthAvg',
      label: '本月交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountMonthAvg',
      label: '上年同期月交易金额日均',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月交易金额日均',
      },
    },
    {
      fieldName: 'cumulativeYearAmount',
      label: '本年累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年累计交易金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本季度累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期季度累计交易金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计交易金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上年同期月累计交易金额',
      },
    },
    {
      fieldName: 'firstAccessTime',
      label: '首次访问时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastAccessTime',
      label: '最近访问时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastTransactionTime',
      label: '最近交易时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'deviceType',
      label: '设备类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择设备类型',
      },
    },
    {
      fieldName: 'deviceModel',
      label: '设备型号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入设备型号',
      },
    },
    {
      fieldName: 'osVersion',
      label: '操作系统版本',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作系统版本',
      },
    },
    {
      fieldName: 'appVersion',
      label: '应用版本',
      component: 'Input',
      componentProps: {
        placeholder: '请输入应用版本',
      },
    },
    {
      fieldName: 'preferredBusiness',
      label: '偏好业务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入偏好业务',
      },
    },
    {
      fieldName: 'usageFrequency',
      label: '使用频率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用频率',
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
      fieldName: 'channelType',
      label: '渠道类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择渠道类型',
      },
    },
    {
      fieldName: 'channelCode',
      label: '渠道代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入渠道代码',
      },
    },
    {
      fieldName: 'channelName',
      label: '渠道名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入渠道名称',
      },
    },
    {
      fieldName: 'accessCount',
      label: '访问次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入访问次数',
      },
    },
    {
      fieldName: 'loginCount',
      label: '登录次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入登录次数',
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
      fieldName: 'activeDays',
      label: '活跃天数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活跃天数',
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
      fieldName: 'amountYearAvg',
      label: '本年交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountYearAvg',
      label: '上年交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年交易金额日均',
      },
    },
    {
      fieldName: 'amountQuarterAvg',
      label: '本季度交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountQuarterAvg',
      label: '上年同期季度交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度交易金额日均',
      },
    },
    {
      fieldName: 'amountMonthAvg',
      label: '本月交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月交易金额日均',
      },
    },
    {
      fieldName: 'lastYearAmountMonthAvg',
      label: '上年同期月交易金额日均',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月交易金额日均',
      },
    },
    {
      fieldName: 'cumulativeYearAmount',
      label: '本年累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeAmount',
      label: '上年累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年累计交易金额',
      },
    },
    {
      fieldName: 'cumulativeQuarterAmount',
      label: '本季度累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本季度累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeQuarterAmount',
      label: '上年同期季度累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期季度累计交易金额',
      },
    },
    {
      fieldName: 'cumulativeMonthAmount',
      label: '本月累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月累计交易金额',
      },
    },
    {
      fieldName: 'lastYearCumulativeMonthAmount',
      label: '上年同期月累计交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上年同期月累计交易金额',
      },
    },
    {
      fieldName: 'firstAccessTime',
      label: '首次访问时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastAccessTime',
      label: '最近访问时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastTransactionTime',
      label: '最近交易时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'deviceType',
      label: '设备类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择设备类型',
      },
    },
    {
      fieldName: 'deviceModel',
      label: '设备型号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入设备型号',
      },
    },
    {
      fieldName: 'osVersion',
      label: '操作系统版本',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作系统版本',
      },
    },
    {
      fieldName: 'appVersion',
      label: '应用版本',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入应用版本',
      },
    },
    {
      fieldName: 'preferredBusiness',
      label: '偏好业务',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入偏好业务',
      },
    },
    {
      fieldName: 'usageFrequency',
      label: '使用频率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用频率',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerChannelOverviewApi.CustomerChannelOverview>['columns'] {
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
      field: 'channelType',
      title: '渠道类型',
      minWidth: 120,
    },
    {
      field: 'channelCode',
      title: '渠道代码',
      minWidth: 120,
    },
    {
      field: 'channelName',
      title: '渠道名称',
      minWidth: 120,
    },
    {
      field: 'accessCount',
      title: '访问次数',
      minWidth: 120,
    },
    {
      field: 'loginCount',
      title: '登录次数',
      minWidth: 120,
    },
    {
      field: 'transactionCount',
      title: '交易笔数',
      minWidth: 120,
    },
    {
      field: 'activeDays',
      title: '活跃天数',
      minWidth: 120,
    },
    {
      field: 'transactionAmount',
      title: '交易金额',
      minWidth: 120,
    },
    {
      field: 'amountYearAvg',
      title: '本年交易金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountYearAvg',
      title: '上年交易金额日均',
      minWidth: 120,
    },
    {
      field: 'amountQuarterAvg',
      title: '本季度交易金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountQuarterAvg',
      title: '上年同期季度交易金额日均',
      minWidth: 120,
    },
    {
      field: 'amountMonthAvg',
      title: '本月交易金额日均',
      minWidth: 120,
    },
    {
      field: 'lastYearAmountMonthAvg',
      title: '上年同期月交易金额日均',
      minWidth: 120,
    },
    {
      field: 'cumulativeYearAmount',
      title: '本年累计交易金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeAmount',
      title: '上年累计交易金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeQuarterAmount',
      title: '本季度累计交易金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeQuarterAmount',
      title: '上年同期季度累计交易金额',
      minWidth: 120,
    },
    {
      field: 'cumulativeMonthAmount',
      title: '本月累计交易金额',
      minWidth: 120,
    },
    {
      field: 'lastYearCumulativeMonthAmount',
      title: '上年同期月累计交易金额',
      minWidth: 120,
    },
    {
      field: 'firstAccessTime',
      title: '首次访问时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'lastAccessTime',
      title: '最近访问时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'lastTransactionTime',
      title: '最近交易时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'deviceType',
      title: '设备类型',
      minWidth: 120,
    },
    {
      field: 'deviceModel',
      title: '设备型号',
      minWidth: 120,
    },
    {
      field: 'osVersion',
      title: '操作系统版本',
      minWidth: 120,
    },
    {
      field: 'appVersion',
      title: '应用版本',
      minWidth: 120,
    },
    {
      field: 'preferredBusiness',
      title: '偏好业务',
      minWidth: 120,
    },
    {
      field: 'usageFrequency',
      title: '使用频率',
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