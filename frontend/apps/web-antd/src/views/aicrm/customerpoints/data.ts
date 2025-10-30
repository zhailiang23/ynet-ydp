import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerPointsApi } from '#/api/aicrm/customerpoints';

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
      fieldName: 'availablePoints',
      label: '可用积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用积分',
      },
    },
    {
      fieldName: 'historyTotalGiftPoints',
      label: '历史累计赠送积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入历史累计赠送积分',
      },
    },
    {
      fieldName: 'historyTotalDeductPoints',
      label: '历史累计扣减积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入历史累计扣减积分',
      },
    },
    {
      fieldName: 'historyTotalExpirePoints',
      label: '历史累计失效积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入历史累计失效积分',
      },
    },
    {
      fieldName: 'upcomingExpirePoints',
      label: '即将失效积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入即将失效积分',
      },
    },
    {
      fieldName: 'upcomingExpireDate',
      label: '即将失效积分日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'pointsAccountNo',
      label: '积分账户编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分账户编号',
      },
    },
    {
      fieldName: 'pointsLevel',
      label: '积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      },
    },
    {
      fieldName: 'totalEarnedPoints',
      label: '累计获得积分（包含赠送和交易）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计获得积分（包含赠送和交易）',
      },
    },
    {
      fieldName: 'totalUsedPoints',
      label: '累计使用积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计使用积分',
      },
    },
    {
      fieldName: 'frozenPoints',
      label: '冻结积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入冻结积分',
      },
    },
    {
      fieldName: 'historyTotalTransactionPoints',
      label: '历史累计交易积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入历史累计交易积分',
      },
    },
    {
      fieldName: 'historyTotalRedeemPoints',
      label: '历史累计兑换积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入历史累计兑换积分',
      },
    },
    {
      fieldName: 'pointsBalance',
      label: '积分余额（可用+冻结）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分余额（可用+冻结）',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastTransactionDate',
      label: '最后交易日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastTransactionTime',
      label: '最后交易时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastGiftDate',
      label: '最后赠送日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastRedeemDate',
      label: '最后兑换日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'pointsValidMonths',
      label: '积分有效期（月）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分有效期（月）',
      },
    },
    {
      fieldName: 'isAutoExpire',
      label: '是否自动失效',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'autoExpireRemindDays',
      label: '自动失效提醒天数',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入自动失效提醒天数',
      },
    },
    {
      fieldName: 'yearEarnedPoints',
      label: '本年度获得积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年度获得积分',
      },
    },
    {
      fieldName: 'yearUsedPoints',
      label: '本年度使用积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本年度使用积分',
      },
    },
    {
      fieldName: 'monthEarnedPoints',
      label: '本月获得积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月获得积分',
      },
    },
    {
      fieldName: 'monthUsedPoints',
      label: '本月使用积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本月使用积分',
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
      fieldName: 'availablePoints',
      label: '可用积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可用积分',
      },
    },
    {
      fieldName: 'historyTotalGiftPoints',
      label: '历史累计赠送积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入历史累计赠送积分',
      },
    },
    {
      fieldName: 'historyTotalDeductPoints',
      label: '历史累计扣减积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入历史累计扣减积分',
      },
    },
    {
      fieldName: 'historyTotalExpirePoints',
      label: '历史累计失效积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入历史累计失效积分',
      },
    },
    {
      fieldName: 'upcomingExpirePoints',
      label: '即将失效积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入即将失效积分',
      },
    },
    {
      fieldName: 'upcomingExpireDate',
      label: '即将失效积分日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'pointsAccountNo',
      label: '积分账户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分账户编号',
      },
    },
    {
      fieldName: 'pointsLevel',
      label: '积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      },
    },
    {
      fieldName: 'totalEarnedPoints',
      label: '累计获得积分（包含赠送和交易）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计获得积分（包含赠送和交易）',
      },
    },
    {
      fieldName: 'totalUsedPoints',
      label: '累计使用积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计使用积分',
      },
    },
    {
      fieldName: 'frozenPoints',
      label: '冻结积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入冻结积分',
      },
    },
    {
      fieldName: 'historyTotalTransactionPoints',
      label: '历史累计交易积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入历史累计交易积分',
      },
    },
    {
      fieldName: 'historyTotalRedeemPoints',
      label: '历史累计兑换积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入历史累计兑换积分',
      },
    },
    {
      fieldName: 'pointsBalance',
      label: '积分余额（可用+冻结）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分余额（可用+冻结）',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）',
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
      fieldName: 'lastTransactionDate',
      label: '最后交易日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastTransactionTime',
      label: '最后交易时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastGiftDate',
      label: '最后赠送日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastRedeemDate',
      label: '最后兑换日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'pointsValidMonths',
      label: '积分有效期（月）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分有效期（月）',
      },
    },
    {
      fieldName: 'isAutoExpire',
      label: '是否自动失效',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否自动失效',
      },
    },
    {
      fieldName: 'autoExpireRemindDays',
      label: '自动失效提醒天数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入自动失效提醒天数',
      },
    },
    {
      fieldName: 'yearEarnedPoints',
      label: '本年度获得积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年度获得积分',
      },
    },
    {
      fieldName: 'yearUsedPoints',
      label: '本年度使用积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本年度使用积分',
      },
    },
    {
      fieldName: 'monthEarnedPoints',
      label: '本月获得积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月获得积分',
      },
    },
    {
      fieldName: 'monthUsedPoints',
      label: '本月使用积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本月使用积分',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerPointsApi.CustomerPoints>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '积分主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'availablePoints',
      title: '可用积分',
      minWidth: 120,
    },
    {
      field: 'historyTotalGiftPoints',
      title: '历史累计赠送积分',
      minWidth: 120,
    },
    {
      field: 'historyTotalDeductPoints',
      title: '历史累计扣减积分',
      minWidth: 120,
    },
    {
      field: 'historyTotalExpirePoints',
      title: '历史累计失效积分',
      minWidth: 120,
    },
    {
      field: 'upcomingExpirePoints',
      title: '即将失效积分',
      minWidth: 120,
    },
    {
      field: 'upcomingExpireDate',
      title: '即将失效积分日期',
      minWidth: 120,
    },
    {
      field: 'pointsAccountNo',
      title: '积分账户编号',
      minWidth: 120,
    },
    {
      field: 'pointsLevel',
      title: '积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      minWidth: 120,
    },
    {
      field: 'totalEarnedPoints',
      title: '累计获得积分（包含赠送和交易）',
      minWidth: 120,
    },
    {
      field: 'totalUsedPoints',
      title: '累计使用积分',
      minWidth: 120,
    },
    {
      field: 'frozenPoints',
      title: '冻结积分',
      minWidth: 120,
    },
    {
      field: 'historyTotalTransactionPoints',
      title: '历史累计交易积分',
      minWidth: 120,
    },
    {
      field: 'historyTotalRedeemPoints',
      title: '历史累计兑换积分',
      minWidth: 120,
    },
    {
      field: 'pointsBalance',
      title: '积分余额（可用+冻结）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期',
      minWidth: 120,
    },
    {
      field: 'lastTransactionDate',
      title: '最后交易日期',
      minWidth: 120,
    },
    {
      field: 'lastTransactionTime',
      title: '最后交易时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'lastGiftDate',
      title: '最后赠送日期',
      minWidth: 120,
    },
    {
      field: 'lastRedeemDate',
      title: '最后兑换日期',
      minWidth: 120,
    },
    {
      field: 'pointsValidMonths',
      title: '积分有效期（月）',
      minWidth: 120,
    },
    {
      field: 'isAutoExpire',
      title: '是否自动失效',
      minWidth: 120,
    },
    {
      field: 'autoExpireRemindDays',
      title: '自动失效提醒天数',
      minWidth: 120,
    },
    {
      field: 'yearEarnedPoints',
      title: '本年度获得积分',
      minWidth: 120,
    },
    {
      field: 'yearUsedPoints',
      title: '本年度使用积分',
      minWidth: 120,
    },
    {
      field: 'monthEarnedPoints',
      title: '本月获得积分',
      minWidth: 120,
    },
    {
      field: 'monthUsedPoints',
      title: '本月使用积分',
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