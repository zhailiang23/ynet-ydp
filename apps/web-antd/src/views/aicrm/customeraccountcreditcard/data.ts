import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountCreditcardApi } from '#/api/aicrm/customeraccountcreditcard';

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
      label: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表，仅限零售客户）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '信用卡账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用卡账号',
      },
    },
    {
      fieldName: 'cardNo',
      label: '信用卡号（加密存储）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用卡号（加密存储）',
      },
    },
    {
      fieldName: 'productName',
      label: '信用卡产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用卡产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '持卡人姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入持卡人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '开卡日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'closeDate',
      label: '销卡日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'cardType',
      label: '卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      },
    },
    {
      fieldName: 'cardLevel',
      label: '卡等级',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡等级',
      },
    },
    {
      fieldName: 'cardBrand',
      label: '卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
      },
    },
    {
      fieldName: 'isMainCard',
      label: '是否主卡（0=附属卡，1=主卡）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'mainCardNo',
      label: '主卡卡号（附属卡关联）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主卡卡号（附属卡关联）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择币种',
      },
    },
    {
      fieldName: 'creditLimit',
      label: '信用额度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用额度',
      },
    },
    {
      fieldName: 'availableLimit',
      label: '可用额度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用额度',
      },
    },
    {
      fieldName: 'temporaryLimit',
      label: '临时额度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入临时额度',
      },
    },
    {
      fieldName: 'cashLimit',
      label: '取现额度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入取现额度',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用额度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已用额度',
      },
    },
    {
      fieldName: 'balance',
      label: '当前欠款余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前欠款余额',
      },
    },
    {
      fieldName: 'billingDay',
      label: '账单日（每月几号）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账单日（每月几号）',
      },
    },
    {
      fieldName: 'paymentDueDay',
      label: '还款日（每月几号）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入还款日（每月几号）',
      },
    },
    {
      fieldName: 'currentBillAmount',
      label: '本期账单金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入本期账单金额',
      },
    },
    {
      fieldName: 'minPaymentAmount',
      label: '最低还款额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最低还款额',
      },
    },
    {
      fieldName: 'unpaidAmount',
      label: '未还金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入未还金额',
      },
    },
    {
      fieldName: 'lastPaymentDate',
      label: '上次还款日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'lastPaymentAmount',
      label: '上次还款金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上次还款金额',
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
      fieldName: 'overdueAmount',
      label: '逾期金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入逾期金额',
      },
    },
    {
      fieldName: 'overdueInterest',
      label: '逾期利息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入逾期利息',
      },
    },
    {
      fieldName: 'overdueTimes',
      label: '累计逾期次数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计逾期次数',
      },
    },
    {
      fieldName: 'totalPoints',
      label: '累计积分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计积分',
      },
    },
    {
      fieldName: 'availablePoints',
      label: '可用积分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用积分',
      },
    },
    {
      fieldName: 'pointsExpireDate',
      label: '积分到期日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'expireDate',
      label: '卡片有效期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'activateDate',
      label: '激活日期',
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
      fieldName: 'deptId',
      label: '发卡机构ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发卡机构ID',
      },
    },
    {
      fieldName: 'deptName',
      label: '发卡机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发卡机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理用户ID',
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
      label: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表，仅限零售客户）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '信用卡账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信用卡账号',
      },
    },
    {
      fieldName: 'cardNo',
      label: '信用卡号（加密存储）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信用卡号（加密存储）',
      },
    },
    {
      fieldName: 'productName',
      label: '信用卡产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信用卡产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '持卡人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入持卡人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '开卡日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '销卡日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）',
      },
    },
    {
      fieldName: 'cardType',
      label: '卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      },
    },
    {
      fieldName: 'cardLevel',
      label: '卡等级',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡等级',
      },
    },
    {
      fieldName: 'cardBrand',
      label: '卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
      },
    },
    {
      fieldName: 'isMainCard',
      label: '是否主卡（0=附属卡，1=主卡）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否主卡（0=附属卡，1=主卡）',
      },
    },
    {
      fieldName: 'mainCardNo',
      label: '主卡卡号（附属卡关联）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入主卡卡号（附属卡关联）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择币种',
      },
    },
    {
      fieldName: 'creditLimit',
      label: '信用额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信用额度',
      },
    },
    {
      fieldName: 'availableLimit',
      label: '可用额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可用额度',
      },
    },
    {
      fieldName: 'temporaryLimit',
      label: '临时额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入临时额度',
      },
    },
    {
      fieldName: 'cashLimit',
      label: '取现额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入取现额度',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已用额度',
      },
    },
    {
      fieldName: 'balance',
      label: '当前欠款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前欠款余额',
      },
    },
    {
      fieldName: 'billingDay',
      label: '账单日（每月几号）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账单日（每月几号）',
      },
    },
    {
      fieldName: 'paymentDueDay',
      label: '还款日（每月几号）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入还款日（每月几号）',
      },
    },
    {
      fieldName: 'currentBillAmount',
      label: '本期账单金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入本期账单金额',
      },
    },
    {
      fieldName: 'minPaymentAmount',
      label: '最低还款额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最低还款额',
      },
    },
    {
      fieldName: 'unpaidAmount',
      label: '未还金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入未还金额',
      },
    },
    {
      fieldName: 'lastPaymentDate',
      label: '上次还款日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'lastPaymentAmount',
      label: '上次还款金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上次还款金额',
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
      fieldName: 'overdueAmount',
      label: '逾期金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入逾期金额',
      },
    },
    {
      fieldName: 'overdueInterest',
      label: '逾期利息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入逾期利息',
      },
    },
    {
      fieldName: 'overdueTimes',
      label: '累计逾期次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计逾期次数',
      },
    },
    {
      fieldName: 'totalPoints',
      label: '累计积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计积分',
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
      fieldName: 'pointsExpireDate',
      label: '积分到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'expireDate',
      label: '卡片有效期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'activateDate',
      label: '激活日期',
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
      fieldName: 'deptId',
      label: '发卡机构ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发卡机构ID',
      },
    },
    {
      fieldName: 'deptName',
      label: '发卡机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发卡机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '信用卡账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '信用卡账号',
      minWidth: 120,
    },
    {
      field: 'cardNo',
      title: '信用卡号（加密存储）',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '信用卡产品名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '持卡人姓名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开卡日期',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '销卡日期',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）',
      minWidth: 120,
    },
    {
      field: 'cardType',
      title: '卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
      minWidth: 120,
    },
    {
      field: 'cardLevel',
      title: '卡等级',
      minWidth: 120,
    },
    {
      field: 'cardBrand',
      title: '卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
      minWidth: 120,
    },
    {
      field: 'isMainCard',
      title: '是否主卡（0=附属卡，1=主卡）',
      minWidth: 120,
    },
    {
      field: 'mainCardNo',
      title: '主卡卡号（附属卡关联）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'creditLimit',
      title: '信用额度',
      minWidth: 120,
    },
    {
      field: 'availableLimit',
      title: '可用额度',
      minWidth: 120,
    },
    {
      field: 'temporaryLimit',
      title: '临时额度',
      minWidth: 120,
    },
    {
      field: 'cashLimit',
      title: '取现额度',
      minWidth: 120,
    },
    {
      field: 'usedAmount',
      title: '已用额度',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '当前欠款余额',
      minWidth: 120,
    },
    {
      field: 'billingDay',
      title: '账单日（每月几号）',
      minWidth: 120,
    },
    {
      field: 'paymentDueDay',
      title: '还款日（每月几号）',
      minWidth: 120,
    },
    {
      field: 'currentBillAmount',
      title: '本期账单金额',
      minWidth: 120,
    },
    {
      field: 'minPaymentAmount',
      title: '最低还款额',
      minWidth: 120,
    },
    {
      field: 'unpaidAmount',
      title: '未还金额',
      minWidth: 120,
    },
    {
      field: 'lastPaymentDate',
      title: '上次还款日期',
      minWidth: 120,
    },
    {
      field: 'lastPaymentAmount',
      title: '上次还款金额',
      minWidth: 120,
    },
    {
      field: 'overdueDays',
      title: '逾期天数',
      minWidth: 120,
    },
    {
      field: 'overdueAmount',
      title: '逾期金额',
      minWidth: 120,
    },
    {
      field: 'overdueInterest',
      title: '逾期利息',
      minWidth: 120,
    },
    {
      field: 'overdueTimes',
      title: '累计逾期次数',
      minWidth: 120,
    },
    {
      field: 'totalPoints',
      title: '累计积分',
      minWidth: 120,
    },
    {
      field: 'availablePoints',
      title: '可用积分',
      minWidth: 120,
    },
    {
      field: 'pointsExpireDate',
      title: '积分到期日',
      minWidth: 120,
    },
    {
      field: 'expireDate',
      title: '卡片有效期',
      minWidth: 120,
    },
    {
      field: 'activateDate',
      title: '激活日期',
      minWidth: 120,
    },
    {
      field: 'lastTransactionDate',
      title: '最后交易日期',
      minWidth: 120,
    },
    {
      field: 'deptId',
      title: '发卡机构ID',
      minWidth: 120,
    },
    {
      field: 'deptName',
      title: '发卡机构名称',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理用户ID',
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