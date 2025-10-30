import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyProjectApi } from '#/api/aicrm/companyproject';

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
      fieldName: 'projectCode',
      label: '项目编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目编号',
      },
    },
    {
      fieldName: 'projectName',
      label: '项目名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目名称',
      },
    },
    {
      fieldName: 'projectType',
      label: '项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）',
      },
    },
    {
      fieldName: 'projectCategory',
      label: '项目类别（自建、合作、收购、PPP等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目类别（自建、合作、收购、PPP等）',
      },
    },
    {
      fieldName: 'totalInvestment',
      label: '总投资额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入总投资额（元）',
      },
    },
    {
      fieldName: 'registeredCapital',
      label: '注册资本（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入注册资本（元）',
      },
    },
    {
      fieldName: 'projectArea',
      label: '项目占地面积（平方米）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目占地面积（平方米）',
      },
    },
    {
      fieldName: 'buildingArea',
      label: '建筑面积（平方米）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入建筑面积（平方米）',
      },
    },
    {
      fieldName: 'planStartDate',
      label: '计划开工日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'actualStartDate',
      label: '实际开工日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'planCompleteDate',
      label: '计划完工日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'actualCompleteDate',
      label: '实际完工日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'constructionPeriod',
      label: '建设周期（月）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入建设周期（月）',
      },
    },
    {
      fieldName: 'projectStatus',
      label: '项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'progressRate',
      label: '项目进度（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目进度（%）',
      },
    },
    {
      fieldName: 'projectProvince',
      label: '项目所在省份',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目所在省份',
      },
    },
    {
      fieldName: 'projectCity',
      label: '项目所在城市',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目所在城市',
      },
    },
    {
      fieldName: 'projectDistrict',
      label: '项目所在区县',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目所在区县',
      },
    },
    {
      fieldName: 'projectAddress',
      label: '项目详细地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目详细地址',
      },
    },
    {
      fieldName: 'selfFunding',
      label: '自有资金（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入自有资金（元）',
      },
    },
    {
      fieldName: 'bankLoan',
      label: '银行贷款（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入银行贷款（元）',
      },
    },
    {
      fieldName: 'bondFinancing',
      label: '债券融资（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入债券融资（元）',
      },
    },
    {
      fieldName: 'equityFinancing',
      label: '股权融资（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入股权融资（元）',
      },
    },
    {
      fieldName: 'governmentSubsidy',
      label: '政府补助（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入政府补助（元）',
      },
    },
    {
      fieldName: 'otherFunding',
      label: '其他资金（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入其他资金（元）',
      },
    },
    {
      fieldName: 'accumulatedInvestment',
      label: '累计完成投资（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计完成投资（元）',
      },
    },
    {
      fieldName: 'financingRequirement',
      label: '融资需求金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入融资需求金额（元）',
      },
    },
    {
      fieldName: 'financingPurpose',
      label: '融资用途',
      component: 'Input',
      componentProps: {
        placeholder: '请输入融资用途',
      },
    },
    {
      fieldName: 'financingStatus',
      label: '融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'ourBankFinancing',
      label: '我行融资金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入我行融资金额（元）',
      },
    },
    {
      fieldName: 'otherBankFinancing',
      label: '他行融资金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入他行融资金额（元）',
      },
    },
    {
      fieldName: 'partners',
      label: '合作方（多个合作方用逗号分隔）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合作方（多个合作方用逗号分隔）',
      },
    },
    {
      fieldName: 'contractor',
      label: '总承包商',
      component: 'Input',
      componentProps: {
        placeholder: '请输入总承包商',
      },
    },
    {
      fieldName: 'designer',
      label: '设计单位',
      component: 'Input',
      componentProps: {
        placeholder: '请输入设计单位',
      },
    },
    {
      fieldName: 'supervisor',
      label: '监理单位',
      component: 'Input',
      componentProps: {
        placeholder: '请输入监理单位',
      },
    },
    {
      fieldName: 'expectedRevenue',
      label: '预计年收入（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预计年收入（元）',
      },
    },
    {
      fieldName: 'expectedProfit',
      label: '预计年利润（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预计年利润（元）',
      },
    },
    {
      fieldName: 'paybackPeriod',
      label: '投资回收期（年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入投资回收期（年）',
      },
    },
    {
      fieldName: 'irrRate',
      label: '内部收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入内部收益率（%）',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（低、中、高）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级（低、中、高）',
      },
    },
    {
      fieldName: 'riskFactors',
      label: '主要风险因素',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主要风险因素',
      },
    },
    {
      fieldName: 'riskMitigation',
      label: '风险缓释措施',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险缓释措施',
      },
    },
    {
      fieldName: 'isKeyProject',
      label: '是否重点项目（0=否 1=市级 2=省级 3=国家级）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否重点项目（0=否 1=市级 2=省级 3=国家级）',
      },
    },
    {
      fieldName: 'governmentApproval',
      label: '政府批文',
      component: 'Input',
      componentProps: {
        placeholder: '请输入政府批文',
      },
    },
    {
      fieldName: 'policySupport',
      label: '政策支持',
      component: 'Input',
      componentProps: {
        placeholder: '请输入政策支持',
      },
    },
    {
      fieldName: 'environmentalApproval',
      label: '环评批复（0=未申请 1=已批复 2=不需要）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入环评批复（0=未申请 1=已批复 2=不需要）',
      },
    },
    {
      fieldName: 'landApproval',
      label: '用地批复（0=未批复 1=已批复 2=不需要）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入用地批复（0=未批复 1=已批复 2=不需要）',
      },
    },
    {
      fieldName: 'constructionPermit',
      label: '施工许可（0=未办理 1=已办理 2=不需要）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入施工许可（0=未办理 1=已办理 2=不需要）',
      },
    },
    {
      fieldName: 'projectManager',
      label: '项目负责人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入项目负责人',
      },
    },
    {
      fieldName: 'managerPhone',
      label: '负责人电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人电话',
      },
    },
    {
      fieldName: 'managerEmail',
      label: '负责人邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人邮箱',
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
      fieldName: 'marketingOpportunity',
      label: '营销机会点（如贷款、结算、理财、投行等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营销机会点（如贷款、结算、理财、投行等）',
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
      fieldName: 'lastFollowUpDate',
      label: '最后跟进日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'nextFollowUpDate',
      label: '下次跟进日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（客户经理录入、招标网、政府网站、企业公告等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入数据来源（客户经理录入、招标网、政府网站、企业公告等）',
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
      fieldName: 'projectCode',
      label: '项目编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目编号',
      },
    },
    {
      fieldName: 'projectName',
      label: '项目名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目名称',
      },
    },
    {
      fieldName: 'projectType',
      label: '项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）',
      },
    },
    {
      fieldName: 'projectCategory',
      label: '项目类别（自建、合作、收购、PPP等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目类别（自建、合作、收购、PPP等）',
      },
    },
    {
      fieldName: 'totalInvestment',
      label: '总投资额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入总投资额（元）',
      },
    },
    {
      fieldName: 'registeredCapital',
      label: '注册资本（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入注册资本（元）',
      },
    },
    {
      fieldName: 'projectArea',
      label: '项目占地面积（平方米）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目占地面积（平方米）',
      },
    },
    {
      fieldName: 'buildingArea',
      label: '建筑面积（平方米）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入建筑面积（平方米）',
      },
    },
    {
      fieldName: 'planStartDate',
      label: '计划开工日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'actualStartDate',
      label: '实际开工日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'planCompleteDate',
      label: '计划完工日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'actualCompleteDate',
      label: '实际完工日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'constructionPeriod',
      label: '建设周期（月）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入建设周期（月）',
      },
    },
    {
      fieldName: 'projectStatus',
      label: '项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）',
      },
    },
    {
      fieldName: 'progressRate',
      label: '项目进度（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目进度（%）',
      },
    },
    {
      fieldName: 'projectProvince',
      label: '项目所在省份',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目所在省份',
      },
    },
    {
      fieldName: 'projectCity',
      label: '项目所在城市',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目所在城市',
      },
    },
    {
      fieldName: 'projectDistrict',
      label: '项目所在区县',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目所在区县',
      },
    },
    {
      fieldName: 'projectAddress',
      label: '项目详细地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目详细地址',
      },
    },
    {
      fieldName: 'selfFunding',
      label: '自有资金（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入自有资金（元）',
      },
    },
    {
      fieldName: 'bankLoan',
      label: '银行贷款（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入银行贷款（元）',
      },
    },
    {
      fieldName: 'bondFinancing',
      label: '债券融资（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入债券融资（元）',
      },
    },
    {
      fieldName: 'equityFinancing',
      label: '股权融资（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入股权融资（元）',
      },
    },
    {
      fieldName: 'governmentSubsidy',
      label: '政府补助（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入政府补助（元）',
      },
    },
    {
      fieldName: 'otherFunding',
      label: '其他资金（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入其他资金（元）',
      },
    },
    {
      fieldName: 'accumulatedInvestment',
      label: '累计完成投资（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计完成投资（元）',
      },
    },
    {
      fieldName: 'financingRequirement',
      label: '融资需求金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入融资需求金额（元）',
      },
    },
    {
      fieldName: 'financingPurpose',
      label: '融资用途',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入融资用途',
      },
    },
    {
      fieldName: 'financingStatus',
      label: '融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）',
      },
    },
    {
      fieldName: 'ourBankFinancing',
      label: '我行融资金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入我行融资金额（元）',
      },
    },
    {
      fieldName: 'otherBankFinancing',
      label: '他行融资金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入他行融资金额（元）',
      },
    },
    {
      fieldName: 'partners',
      label: '合作方（多个合作方用逗号分隔）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合作方（多个合作方用逗号分隔）',
      },
    },
    {
      fieldName: 'contractor',
      label: '总承包商',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入总承包商',
      },
    },
    {
      fieldName: 'designer',
      label: '设计单位',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入设计单位',
      },
    },
    {
      fieldName: 'supervisor',
      label: '监理单位',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入监理单位',
      },
    },
    {
      fieldName: 'expectedRevenue',
      label: '预计年收入（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预计年收入（元）',
      },
    },
    {
      fieldName: 'expectedProfit',
      label: '预计年利润（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预计年利润（元）',
      },
    },
    {
      fieldName: 'paybackPeriod',
      label: '投资回收期（年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入投资回收期（年）',
      },
    },
    {
      fieldName: 'irrRate',
      label: '内部收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入内部收益率（%）',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（低、中、高）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级（低、中、高）',
      },
    },
    {
      fieldName: 'riskFactors',
      label: '主要风险因素',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入主要风险因素',
      },
    },
    {
      fieldName: 'riskMitigation',
      label: '风险缓释措施',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险缓释措施',
      },
    },
    {
      fieldName: 'isKeyProject',
      label: '是否重点项目（0=否 1=市级 2=省级 3=国家级）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否重点项目（0=否 1=市级 2=省级 3=国家级）',
      },
    },
    {
      fieldName: 'governmentApproval',
      label: '政府批文',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入政府批文',
      },
    },
    {
      fieldName: 'policySupport',
      label: '政策支持',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入政策支持',
      },
    },
    {
      fieldName: 'environmentalApproval',
      label: '环评批复（0=未申请 1=已批复 2=不需要）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入环评批复（0=未申请 1=已批复 2=不需要）',
      },
    },
    {
      fieldName: 'landApproval',
      label: '用地批复（0=未批复 1=已批复 2=不需要）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入用地批复（0=未批复 1=已批复 2=不需要）',
      },
    },
    {
      fieldName: 'constructionPermit',
      label: '施工许可（0=未办理 1=已办理 2=不需要）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入施工许可（0=未办理 1=已办理 2=不需要）',
      },
    },
    {
      fieldName: 'projectManager',
      label: '项目负责人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入项目负责人',
      },
    },
    {
      fieldName: 'managerPhone',
      label: '负责人电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人电话',
      },
    },
    {
      fieldName: 'managerEmail',
      label: '负责人邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人邮箱',
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
      fieldName: 'marketingOpportunity',
      label: '营销机会点（如贷款、结算、理财、投行等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营销机会点（如贷款、结算、理财、投行等）',
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
      fieldName: 'lastFollowUpDate',
      label: '最后跟进日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'nextFollowUpDate',
      label: '下次跟进日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（客户经理录入、招标网、政府网站、企业公告等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入数据来源（客户经理录入、招标网、政府网站、企业公告等）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyProjectApi.CompanyProject>['columns'] {
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
      field: 'projectCode',
      title: '项目编号',
      minWidth: 120,
    },
    {
      field: 'projectName',
      title: '项目名称',
      minWidth: 120,
    },
    {
      field: 'projectType',
      title: '项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）',
      minWidth: 120,
    },
    {
      field: 'projectCategory',
      title: '项目类别（自建、合作、收购、PPP等）',
      minWidth: 120,
    },
    {
      field: 'totalInvestment',
      title: '总投资额（元）',
      minWidth: 120,
    },
    {
      field: 'registeredCapital',
      title: '注册资本（元）',
      minWidth: 120,
    },
    {
      field: 'projectArea',
      title: '项目占地面积（平方米）',
      minWidth: 120,
    },
    {
      field: 'buildingArea',
      title: '建筑面积（平方米）',
      minWidth: 120,
    },
    {
      field: 'planStartDate',
      title: '计划开工日期',
      minWidth: 120,
    },
    {
      field: 'actualStartDate',
      title: '实际开工日期',
      minWidth: 120,
    },
    {
      field: 'planCompleteDate',
      title: '计划完工日期',
      minWidth: 120,
    },
    {
      field: 'actualCompleteDate',
      title: '实际完工日期',
      minWidth: 120,
    },
    {
      field: 'constructionPeriod',
      title: '建设周期（月）',
      minWidth: 120,
    },
    {
      field: 'projectStatus',
      title: '项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）',
      minWidth: 120,
    },
    {
      field: 'progressRate',
      title: '项目进度（%）',
      minWidth: 120,
    },
    {
      field: 'projectProvince',
      title: '项目所在省份',
      minWidth: 120,
    },
    {
      field: 'projectCity',
      title: '项目所在城市',
      minWidth: 120,
    },
    {
      field: 'projectDistrict',
      title: '项目所在区县',
      minWidth: 120,
    },
    {
      field: 'projectAddress',
      title: '项目详细地址',
      minWidth: 120,
    },
    {
      field: 'selfFunding',
      title: '自有资金（元）',
      minWidth: 120,
    },
    {
      field: 'bankLoan',
      title: '银行贷款（元）',
      minWidth: 120,
    },
    {
      field: 'bondFinancing',
      title: '债券融资（元）',
      minWidth: 120,
    },
    {
      field: 'equityFinancing',
      title: '股权融资（元）',
      minWidth: 120,
    },
    {
      field: 'governmentSubsidy',
      title: '政府补助（元）',
      minWidth: 120,
    },
    {
      field: 'otherFunding',
      title: '其他资金（元）',
      minWidth: 120,
    },
    {
      field: 'accumulatedInvestment',
      title: '累计完成投资（元）',
      minWidth: 120,
    },
    {
      field: 'financingRequirement',
      title: '融资需求金额（元）',
      minWidth: 120,
    },
    {
      field: 'financingPurpose',
      title: '融资用途',
      minWidth: 120,
    },
    {
      field: 'financingStatus',
      title: '融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）',
      minWidth: 120,
    },
    {
      field: 'ourBankFinancing',
      title: '我行融资金额（元）',
      minWidth: 120,
    },
    {
      field: 'otherBankFinancing',
      title: '他行融资金额（元）',
      minWidth: 120,
    },
    {
      field: 'partners',
      title: '合作方（多个合作方用逗号分隔）',
      minWidth: 120,
    },
    {
      field: 'contractor',
      title: '总承包商',
      minWidth: 120,
    },
    {
      field: 'designer',
      title: '设计单位',
      minWidth: 120,
    },
    {
      field: 'supervisor',
      title: '监理单位',
      minWidth: 120,
    },
    {
      field: 'expectedRevenue',
      title: '预计年收入（元）',
      minWidth: 120,
    },
    {
      field: 'expectedProfit',
      title: '预计年利润（元）',
      minWidth: 120,
    },
    {
      field: 'paybackPeriod',
      title: '投资回收期（年）',
      minWidth: 120,
    },
    {
      field: 'irrRate',
      title: '内部收益率（%）',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（低、中、高）',
      minWidth: 120,
    },
    {
      field: 'riskFactors',
      title: '主要风险因素',
      minWidth: 120,
    },
    {
      field: 'riskMitigation',
      title: '风险缓释措施',
      minWidth: 120,
    },
    {
      field: 'isKeyProject',
      title: '是否重点项目（0=否 1=市级 2=省级 3=国家级）',
      minWidth: 120,
    },
    {
      field: 'governmentApproval',
      title: '政府批文',
      minWidth: 120,
    },
    {
      field: 'policySupport',
      title: '政策支持',
      minWidth: 120,
    },
    {
      field: 'environmentalApproval',
      title: '环评批复（0=未申请 1=已批复 2=不需要）',
      minWidth: 120,
    },
    {
      field: 'landApproval',
      title: '用地批复（0=未批复 1=已批复 2=不需要）',
      minWidth: 120,
    },
    {
      field: 'constructionPermit',
      title: '施工许可（0=未办理 1=已办理 2=不需要）',
      minWidth: 120,
    },
    {
      field: 'projectManager',
      title: '项目负责人',
      minWidth: 120,
    },
    {
      field: 'managerPhone',
      title: '负责人电话',
      minWidth: 120,
    },
    {
      field: 'managerEmail',
      title: '负责人邮箱',
      minWidth: 120,
    },
    {
      field: 'marketingPriority',
      title: '营销优先级（1=高 2=中 3=低）',
      minWidth: 120,
    },
    {
      field: 'marketingOpportunity',
      title: '营销机会点（如贷款、结算、理财、投行等）',
      minWidth: 120,
    },
    {
      field: 'followUpPlan',
      title: '跟进计划',
      minWidth: 120,
    },
    {
      field: 'lastFollowUpDate',
      title: '最后跟进日期',
      minWidth: 120,
    },
    {
      field: 'nextFollowUpDate',
      title: '下次跟进日期',
      minWidth: 120,
    },
    {
      field: 'dataSource',
      title: '数据来源（客户经理录入、招标网、政府网站、企业公告等）',
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