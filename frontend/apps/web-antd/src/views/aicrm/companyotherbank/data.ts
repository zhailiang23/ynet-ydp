import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyOtherBankApi } from '#/api/aicrm/companyotherbank';

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
      label: '客户ID（关联crm_customer表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联crm_customer表）',
      },
    },
    {
      fieldName: 'bankName',
      label: '银行名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入银行名称',
      },
    },
    {
      fieldName: 'bankType',
      label: '银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
      },
    },
    {
      fieldName: 'branchName',
      label: '开户支行名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户支行名称',
      },
    },
    {
      fieldName: 'relationshipManager',
      label: '他行客户经理姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行客户经理姓名',
      },
    },
    {
      fieldName: 'managerPhone',
      label: '他行客户经理电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行客户经理电话',
      },
    },
    {
      fieldName: 'managerEmail',
      label: '他行客户经理邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行客户经理邮箱',
      },
    },
    {
      fieldName: 'cooperationType',
      label: '合作类型（主办行、协办行、一般合作）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择合作类型（主办行、协办行、一般合作）',
      },
    },
    {
      fieldName: 'cooperationStartDate',
      label: '合作开始日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'relationshipDuration',
      label: '合作年限',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合作年限',
      },
    },
    {
      fieldName: 'cooperationStatus',
      label: '合作状态（1=合作中 2=已终止 3=暂停合作）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'hasSettlementAccount',
      label: '是否有结算账户（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否有结算账户（0=否 1=是）',
      },
    },
    {
      fieldName: 'settlementAccountNo',
      label: '结算账户账号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入结算账户账号',
      },
    },
    {
      fieldName: 'accountBalance',
      label: '账户余额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户余额（元）',
      },
    },
    {
      fieldName: 'isMainSettlementBank',
      label: '是否主结算行（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否主结算行（0=否 1=是）',
      },
    },
    {
      fieldName: 'dailyAverageBalance',
      label: '日均存款（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入日均存款（元）',
      },
    },
    {
      fieldName: 'totalCreditLimit',
      label: '授信总额度（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信总额度（元）',
      },
    },
    {
      fieldName: 'usedCreditLimit',
      label: '已用授信额度（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已用授信额度（元）',
      },
    },
    {
      fieldName: 'loanBalance',
      label: '贷款余额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额（元）',
      },
    },
    {
      fieldName: 'depositBalance',
      label: '存款余额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款余额（元）',
      },
    },
    {
      fieldName: 'wealthManagementBalance',
      label: '理财余额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财余额（元）',
      },
    },
    {
      fieldName: 'businessTypes',
      label: '业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
      },
    },
    {
      fieldName: 'mainBusiness',
      label: '主要业务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主要业务',
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
      label: '贷款金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额（元）',
      },
    },
    {
      fieldName: 'loanRate',
      label: '贷款利率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款利率（%）',
      },
    },
    {
      fieldName: 'loanStartDate',
      label: '贷款起始日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'loanMaturityDate',
      label: '贷款到期日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（信用、抵押、质押、保证等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保方式（信用、抵押、质押、保证等）',
      },
    },
    {
      fieldName: 'collateralInfo',
      label: '抵押物信息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押物信息',
      },
    },
    {
      fieldName: 'serviceSatisfaction',
      label: '服务满意度（1-5星）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入服务满意度（1-5星）',
      },
    },
    {
      fieldName: 'pricingLevel',
      label: '价格水平（优惠、市场价、偏高）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入价格水平（优惠、市场价、偏高）',
      },
    },
    {
      fieldName: 'responseSpeed',
      label: '响应速度（快、一般、慢）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入响应速度（快、一般、慢）',
      },
    },
    {
      fieldName: 'customerComment',
      label: '客户评价',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户评价',
      },
    },
    {
      fieldName: 'competitorAdvantage',
      label: '他行优势（为什么客户选择他行）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行优势（为什么客户选择他行）',
      },
    },
    {
      fieldName: 'competitorDisadvantage',
      label: '他行劣势（客户不满意的地方）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行劣势（客户不满意的地方）',
      },
    },
    {
      fieldName: 'ourOpportunity',
      label: '我行机会点（可以从哪些方面切入）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入我行机会点（可以从哪些方面切入）',
      },
    },
    {
      fieldName: 'competitiveStrategy',
      label: '竞争策略（如何争取客户份额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入竞争策略（如何争取客户份额）',
      },
    },
    {
      fieldName: 'targetBusiness',
      label: '目标业务（希望从他行抢占的业务）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入目标业务（希望从他行抢占的业务）',
      },
    },
    {
      fieldName: 'marketingPriority',
      label: '营销优先级（1=高 2=中 3=低）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营销优先级（1=高 2=中 3=低）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合同编号',
      },
    },
    {
      fieldName: 'contractExpiryDate',
      label: '合同到期日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isDueSoon',
      label: '是否即将到期（0=否 1=是，3个月内到期）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否即将到期（0=否 1=是，3个月内到期）',
      },
    },
    {
      fieldName: 'followUpPlan',
      label: '跟进计划',
      component: 'Input',
      componentProps: {
        placeholder: '请输入跟进计划',
      },
    },
    {
      fieldName: 'riskWarning',
      label: '风险提示（如他行抽贷风险、担保风险等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险提示（如他行抽贷风险、担保风险等）',
      },
    },
    {
      fieldName: 'infoSource',
      label: '信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
      },
    },
    {
      fieldName: 'infoReliability',
      label: '信息可靠性（1=高 2=中 3=低）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信息可靠性（1=高 2=中 3=低）',
      },
    },
    {
      fieldName: 'lastUpdateDate',
      label: '信息最后更新日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
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
      label: '客户ID（关联crm_customer表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联crm_customer表）',
      },
    },
    {
      fieldName: 'bankName',
      label: '银行名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入银行名称',
      },
    },
    {
      fieldName: 'bankType',
      label: '银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
      },
    },
    {
      fieldName: 'branchName',
      label: '开户支行名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户支行名称',
      },
    },
    {
      fieldName: 'relationshipManager',
      label: '他行客户经理姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行客户经理姓名',
      },
    },
    {
      fieldName: 'managerPhone',
      label: '他行客户经理电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行客户经理电话',
      },
    },
    {
      fieldName: 'managerEmail',
      label: '他行客户经理邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行客户经理邮箱',
      },
    },
    {
      fieldName: 'cooperationType',
      label: '合作类型（主办行、协办行、一般合作）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择合作类型（主办行、协办行、一般合作）',
      },
    },
    {
      fieldName: 'cooperationStartDate',
      label: '合作开始日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'relationshipDuration',
      label: '合作年限',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合作年限',
      },
    },
    {
      fieldName: 'cooperationStatus',
      label: '合作状态（1=合作中 2=已终止 3=暂停合作）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择合作状态（1=合作中 2=已终止 3=暂停合作）',
      },
    },
    {
      fieldName: 'hasSettlementAccount',
      label: '是否有结算账户（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否有结算账户（0=否 1=是）',
      },
    },
    {
      fieldName: 'settlementAccountNo',
      label: '结算账户账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入结算账户账号',
      },
    },
    {
      fieldName: 'accountBalance',
      label: '账户余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户余额（元）',
      },
    },
    {
      fieldName: 'isMainSettlementBank',
      label: '是否主结算行（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否主结算行（0=否 1=是）',
      },
    },
    {
      fieldName: 'dailyAverageBalance',
      label: '日均存款（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入日均存款（元）',
      },
    },
    {
      fieldName: 'totalCreditLimit',
      label: '授信总额度（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信总额度（元）',
      },
    },
    {
      fieldName: 'usedCreditLimit',
      label: '已用授信额度（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已用授信额度（元）',
      },
    },
    {
      fieldName: 'loanBalance',
      label: '贷款余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款余额（元）',
      },
    },
    {
      fieldName: 'depositBalance',
      label: '存款余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款余额（元）',
      },
    },
    {
      fieldName: 'wealthManagementBalance',
      label: '理财余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财余额（元）',
      },
    },
    {
      fieldName: 'businessTypes',
      label: '业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
      },
    },
    {
      fieldName: 'mainBusiness',
      label: '主要业务',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入主要业务',
      },
    },
    {
      fieldName: 'loanProductName',
      label: '贷款产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款产品名称',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款金额（元）',
      },
    },
    {
      fieldName: 'loanRate',
      label: '贷款利率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款利率（%）',
      },
    },
    {
      fieldName: 'loanStartDate',
      label: '贷款起始日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'loanMaturityDate',
      label: '贷款到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（信用、抵押、质押、保证等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保方式（信用、抵押、质押、保证等）',
      },
    },
    {
      fieldName: 'collateralInfo',
      label: '抵押物信息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押物信息',
      },
    },
    {
      fieldName: 'serviceSatisfaction',
      label: '服务满意度（1-5星）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入服务满意度（1-5星）',
      },
    },
    {
      fieldName: 'pricingLevel',
      label: '价格水平（优惠、市场价、偏高）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入价格水平（优惠、市场价、偏高）',
      },
    },
    {
      fieldName: 'responseSpeed',
      label: '响应速度（快、一般、慢）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入响应速度（快、一般、慢）',
      },
    },
    {
      fieldName: 'customerComment',
      label: '客户评价',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户评价',
      },
    },
    {
      fieldName: 'competitorAdvantage',
      label: '他行优势（为什么客户选择他行）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行优势（为什么客户选择他行）',
      },
    },
    {
      fieldName: 'competitorDisadvantage',
      label: '他行劣势（客户不满意的地方）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行劣势（客户不满意的地方）',
      },
    },
    {
      fieldName: 'ourOpportunity',
      label: '我行机会点（可以从哪些方面切入）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入我行机会点（可以从哪些方面切入）',
      },
    },
    {
      fieldName: 'competitiveStrategy',
      label: '竞争策略（如何争取客户份额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入竞争策略（如何争取客户份额）',
      },
    },
    {
      fieldName: 'targetBusiness',
      label: '目标业务（希望从他行抢占的业务）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入目标业务（希望从他行抢占的业务）',
      },
    },
    {
      fieldName: 'marketingPriority',
      label: '营销优先级（1=高 2=中 3=低）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营销优先级（1=高 2=中 3=低）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合同编号',
      },
    },
    {
      fieldName: 'contractExpiryDate',
      label: '合同到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isDueSoon',
      label: '是否即将到期（0=否 1=是，3个月内到期）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否即将到期（0=否 1=是，3个月内到期）',
      },
    },
    {
      fieldName: 'followUpPlan',
      label: '跟进计划',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入跟进计划',
      },
    },
    {
      fieldName: 'riskWarning',
      label: '风险提示（如他行抽贷风险、担保风险等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险提示（如他行抽贷风险、担保风险等）',
      },
    },
    {
      fieldName: 'infoSource',
      label: '信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
      },
    },
    {
      fieldName: 'infoReliability',
      label: '信息可靠性（1=高 2=中 3=低）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信息可靠性（1=高 2=中 3=低）',
      },
    },
    {
      fieldName: 'lastUpdateDate',
      label: '信息最后更新日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
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
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyOtherBankApi.CompanyOtherBank>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联crm_customer表）',
      minWidth: 120,
    },
    {
      field: 'bankName',
      title: '银行名称',
      minWidth: 120,
    },
    {
      field: 'bankType',
      title: '银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
      minWidth: 120,
    },
    {
      field: 'branchName',
      title: '开户支行名称',
      minWidth: 120,
    },
    {
      field: 'relationshipManager',
      title: '他行客户经理姓名',
      minWidth: 120,
    },
    {
      field: 'managerPhone',
      title: '他行客户经理电话',
      minWidth: 120,
    },
    {
      field: 'managerEmail',
      title: '他行客户经理邮箱',
      minWidth: 120,
    },
    {
      field: 'cooperationType',
      title: '合作类型（主办行、协办行、一般合作）',
      minWidth: 120,
    },
    {
      field: 'cooperationStartDate',
      title: '合作开始日期',
      minWidth: 120,
    },
    {
      field: 'relationshipDuration',
      title: '合作年限',
      minWidth: 120,
    },
    {
      field: 'cooperationStatus',
      title: '合作状态（1=合作中 2=已终止 3=暂停合作）',
      minWidth: 120,
    },
    {
      field: 'hasSettlementAccount',
      title: '是否有结算账户（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'settlementAccountNo',
      title: '结算账户账号',
      minWidth: 120,
    },
    {
      field: 'accountBalance',
      title: '账户余额（元）',
      minWidth: 120,
    },
    {
      field: 'isMainSettlementBank',
      title: '是否主结算行（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'dailyAverageBalance',
      title: '日均存款（元）',
      minWidth: 120,
    },
    {
      field: 'totalCreditLimit',
      title: '授信总额度（元）',
      minWidth: 120,
    },
    {
      field: 'usedCreditLimit',
      title: '已用授信额度（元）',
      minWidth: 120,
    },
    {
      field: 'loanBalance',
      title: '贷款余额（元）',
      minWidth: 120,
    },
    {
      field: 'depositBalance',
      title: '存款余额（元）',
      minWidth: 120,
    },
    {
      field: 'wealthManagementBalance',
      title: '理财余额（元）',
      minWidth: 120,
    },
    {
      field: 'businessTypes',
      title: '业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
      minWidth: 120,
    },
    {
      field: 'mainBusiness',
      title: '主要业务',
      minWidth: 120,
    },
    {
      field: 'loanProductName',
      title: '贷款产品名称',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款金额（元）',
      minWidth: 120,
    },
    {
      field: 'loanRate',
      title: '贷款利率（%）',
      minWidth: 120,
    },
    {
      field: 'loanStartDate',
      title: '贷款起始日',
      minWidth: 120,
    },
    {
      field: 'loanMaturityDate',
      title: '贷款到期日',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保方式（信用、抵押、质押、保证等）',
      minWidth: 120,
    },
    {
      field: 'collateralInfo',
      title: '抵押物信息',
      minWidth: 120,
    },
    {
      field: 'serviceSatisfaction',
      title: '服务满意度（1-5星）',
      minWidth: 120,
    },
    {
      field: 'pricingLevel',
      title: '价格水平（优惠、市场价、偏高）',
      minWidth: 120,
    },
    {
      field: 'responseSpeed',
      title: '响应速度（快、一般、慢）',
      minWidth: 120,
    },
    {
      field: 'customerComment',
      title: '客户评价',
      minWidth: 120,
    },
    {
      field: 'competitorAdvantage',
      title: '他行优势（为什么客户选择他行）',
      minWidth: 120,
    },
    {
      field: 'competitorDisadvantage',
      title: '他行劣势（客户不满意的地方）',
      minWidth: 120,
    },
    {
      field: 'ourOpportunity',
      title: '我行机会点（可以从哪些方面切入）',
      minWidth: 120,
    },
    {
      field: 'competitiveStrategy',
      title: '竞争策略（如何争取客户份额）',
      minWidth: 120,
    },
    {
      field: 'targetBusiness',
      title: '目标业务（希望从他行抢占的业务）',
      minWidth: 120,
    },
    {
      field: 'marketingPriority',
      title: '营销优先级（1=高 2=中 3=低）',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '合同编号',
      minWidth: 120,
    },
    {
      field: 'contractExpiryDate',
      title: '合同到期日',
      minWidth: 120,
    },
    {
      field: 'isDueSoon',
      title: '是否即将到期（0=否 1=是，3个月内到期）',
      minWidth: 120,
    },
    {
      field: 'followUpPlan',
      title: '跟进计划',
      minWidth: 120,
    },
    {
      field: 'riskWarning',
      title: '风险提示（如他行抽贷风险、担保风险等）',
      minWidth: 120,
    },
    {
      field: 'infoSource',
      title: '信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
      minWidth: 120,
    },
    {
      field: 'infoReliability',
      title: '信息可靠性（1=高 2=中 3=低）',
      minWidth: 120,
    },
    {
      field: 'lastUpdateDate',
      title: '信息最后更新日期',
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