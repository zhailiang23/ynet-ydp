import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountLoanApi } from '#/api/aicrm/customeraccountloan';

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
      fieldName: 'accountNo',
      label: '贷款账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款账号',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合同号',
      },
    },
    {
      fieldName: 'agrNo',
      label: '协议号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入协议号',
      },
    },
    {
      fieldName: 'productName',
      label: '贷款产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款产品名称',
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
      fieldName: 'accountName',
      label: '借款人姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入借款人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '放款日期（开户日期）',
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
      label: '结清日期（销户日期）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'contractAmount',
      label: '合同金额（授信额度）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合同金额（授信额度）',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额（实际发放金额）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额（实际发放金额）',
      },
    },
    {
      fieldName: 'balance',
      label: '贷款余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '贷款利率（年化%）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款利率（年化%）',
      },
    },
    {
      fieldName: 'loanTerm',
      label: '贷款期限（月）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款期限（月）',
      },
    },
    {
      fieldName: 'loanTermUnit',
      label: '期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'repaymentMode',
      label: '还款方式（字典: aicrm_repayment_mode）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入还款方式（字典: aicrm_repayment_mode）',
      },
    },
    {
      fieldName: 'loanPurpose',
      label: '贷款用途',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款用途',
      },
    },
    {
      fieldName: 'loanType',
      label: '贷款类型（字典: aicrm_loan_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择贷款类型（字典: aicrm_loan_type）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（字典: aicrm_guarantee_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保方式（字典: aicrm_guarantee_type）',
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
      fieldName: 'fiveLevelClass',
      label: '五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
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
      fieldName: 'overduePrincipal',
      label: '逾期本金',
      component: 'Input',
      componentProps: {
        placeholder: '请输入逾期本金',
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
      fieldName: 'deptId',
      label: '放款机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入放款机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'deptName',
      label: '放款机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入放款机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'monthAvgBalance',
      label: '月日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月日均余额',
      },
    },
    {
      fieldName: 'quarterAvgBalance',
      label: '季日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入季日均余额',
      },
    },
    {
      fieldName: 'yearAvgBalance',
      label: '年日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年日均余额',
      },
    },
    {
      fieldName: 'totalRepaidAmount',
      label: '累计还款金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计还款金额',
      },
    },
    {
      fieldName: 'totalRepaidInterest',
      label: '累计还款利息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计还款利息',
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
      fieldName: 'accountNo',
      label: '贷款账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款账号',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合同号',
      },
    },
    {
      fieldName: 'agrNo',
      label: '协议号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入协议号',
      },
    },
    {
      fieldName: 'productName',
      label: '贷款产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款产品名称',
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
      fieldName: 'accountName',
      label: '借款人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入借款人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '放款日期（开户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '结清日期（销户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）',
      },
    },
    {
      fieldName: 'contractAmount',
      label: '合同金额（授信额度）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合同金额（授信额度）',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额（实际发放金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款金额（实际发放金额）',
      },
    },
    {
      fieldName: 'balance',
      label: '贷款余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款余额',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '贷款利率（年化%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款利率（年化%）',
      },
    },
    {
      fieldName: 'loanTerm',
      label: '贷款期限（月）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款期限（月）',
      },
    },
    {
      fieldName: 'loanTermUnit',
      label: '期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'repaymentMode',
      label: '还款方式（字典: aicrm_repayment_mode）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入还款方式（字典: aicrm_repayment_mode）',
      },
    },
    {
      fieldName: 'loanPurpose',
      label: '贷款用途',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款用途',
      },
    },
    {
      fieldName: 'loanType',
      label: '贷款类型（字典: aicrm_loan_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贷款类型（字典: aicrm_loan_type）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（字典: aicrm_guarantee_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保方式（字典: aicrm_guarantee_type）',
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
      fieldName: 'fiveLevelClass',
      label: '五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
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
      fieldName: 'overduePrincipal',
      label: '逾期本金',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入逾期本金',
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
      fieldName: 'deptId',
      label: '放款机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入放款机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'deptName',
      label: '放款机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入放款机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'monthAvgBalance',
      label: '月日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月日均余额',
      },
    },
    {
      fieldName: 'quarterAvgBalance',
      label: '季日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入季日均余额',
      },
    },
    {
      fieldName: 'yearAvgBalance',
      label: '年日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年日均余额',
      },
    },
    {
      fieldName: 'totalRepaidAmount',
      label: '累计还款金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计还款金额',
      },
    },
    {
      fieldName: 'totalRepaidInterest',
      label: '累计还款利息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计还款利息',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountLoanApi.CustomerAccountLoan>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '贷款账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '贷款账号',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '合同号',
      minWidth: 120,
    },
    {
      field: 'agrNo',
      title: '协议号',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '贷款产品名称',
      minWidth: 120,
    },
    {
      field: 'productId',
      title: '产品ID',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '借款人姓名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '放款日期（开户日期）',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '结清日期（销户日期）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）',
      minWidth: 120,
    },
    {
      field: 'contractAmount',
      title: '合同金额（授信额度）',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款金额（实际发放金额）',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '贷款余额',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '贷款利率（年化%）',
      minWidth: 120,
    },
    {
      field: 'loanTerm',
      title: '贷款期限（月）',
      minWidth: 120,
    },
    {
      field: 'loanTermUnit',
      title: '期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日',
      minWidth: 120,
    },
    {
      field: 'repaymentMode',
      title: '还款方式（字典: aicrm_repayment_mode）',
      minWidth: 120,
    },
    {
      field: 'loanPurpose',
      title: '贷款用途',
      minWidth: 120,
    },
    {
      field: 'loanType',
      title: '贷款类型（字典: aicrm_loan_type）',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保方式（字典: aicrm_guarantee_type）',
      minWidth: 120,
    },
    {
      field: 'businessType',
      title: '业务类型',
      minWidth: 120,
    },
    {
      field: 'fiveLevelClass',
      title: '五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
      minWidth: 120,
    },
    {
      field: 'overdueDays',
      title: '逾期天数',
      minWidth: 120,
    },
    {
      field: 'overduePrincipal',
      title: '逾期本金',
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
      field: 'deptId',
      title: '放款机构ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'deptName',
      title: '放款机构名称',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'monthAvgBalance',
      title: '月日均余额',
      minWidth: 120,
    },
    {
      field: 'quarterAvgBalance',
      title: '季日均余额',
      minWidth: 120,
    },
    {
      field: 'yearAvgBalance',
      title: '年日均余额',
      minWidth: 120,
    },
    {
      field: 'totalRepaidAmount',
      title: '累计还款金额',
      minWidth: 120,
    },
    {
      field: 'totalRepaidInterest',
      title: '累计还款利息',
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