import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerProductRecommendationApi } from '#/api/aicrm/customerproductrecommendation';

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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品编号',
      },
    },
    {
      fieldName: 'productName',
      label: '产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品名称',
      },
    },
    {
      fieldName: 'productCategoryId',
      label: '产品类目ID（关联 crm_product_category 表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品类目ID（关联 crm_product_category 表）',
      },
    },
    {
      fieldName: 'productType',
      label: '产品类型（字典: aicrm_product_type）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择产品类型（字典: aicrm_product_type）',
      },
    },
    {
      fieldName: 'recommendationNo',
      label: '推荐编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐编号',
      },
    },
    {
      fieldName: 'recommendationType',
      label: '推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
      },
    },
    {
      fieldName: 'recommendationSource',
      label: '推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
      },
    },
    {
      fieldName: 'recommendationReason',
      label: '推荐理由',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐理由',
      },
    },
    {
      fieldName: 'recommendationScore',
      label: '推荐评分（0-100分）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐评分（0-100分）',
      },
    },
    {
      fieldName: 'recommendationDate',
      label: '推荐日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'recommendationTime',
      label: '推荐时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'validStartDate',
      label: '有效开始日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'validEndDate',
      label: '有效结束日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isHotSale',
      label: '是否热销产品',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isMatchCustomer',
      label: '是否匹配客户',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'matchDegree',
      label: '匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',
      },
    },
    {
      fieldName: 'recommendationStatus',
      label: '推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'pushTime',
      label: '推送时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'pushChannel',
      label: '推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
      },
    },
    {
      fieldName: 'viewTime',
      label: '查看时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'viewCount',
      label: '查看次数',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入查看次数',
      },
    },
    {
      fieldName: 'clickTime',
      label: '点击时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'clickCount',
      label: '点击次数',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入点击次数',
      },
    },
    {
      fieldName: 'applyTime',
      label: '申请时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'purchaseTime',
      label: '购买时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'purchaseAmount',
      label: '购买金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入购买金额',
      },
    },
    {
      fieldName: 'rejectTime',
      label: '拒绝时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'rejectReason',
      label: '拒绝原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入拒绝原因',
      },
    },
    {
      fieldName: 'recommenderUserId',
      label: '推荐人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐人ID',
      },
    },
    {
      fieldName: 'recommenderUserName',
      label: '推荐人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐人姓名',
      },
    },
    {
      fieldName: 'recommenderDeptId',
      label: '推荐部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐部门ID',
      },
    },
    {
      fieldName: 'recommenderDeptName',
      label: '推荐部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐部门名称',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率/收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入利率/收益率（%）',
      },
    },
    {
      fieldName: 'termDays',
      label: '期限（天）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入期限（天）',
      },
    },
    {
      fieldName: 'minAmount',
      label: '起购金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入起购金额',
      },
    },
    {
      fieldName: 'maxAmount',
      label: '最大金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最大金额',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'productFeatures',
      label: '产品特点',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品特点',
      },
    },
    {
      fieldName: 'productAdvantage',
      label: '产品优势',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品优势',
      },
    },
    {
      fieldName: 'marketingTheme',
      label: '营销主题',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营销主题',
      },
    },
    {
      fieldName: 'marketingContent',
      label: '营销内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'promotionType',
      label: '促销类型（字典: aicrm_promotion_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择促销类型（字典: aicrm_promotion_type）',
      },
    },
    {
      fieldName: 'promotionContent',
      label: '促销内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'conversionRate',
      label: '转化率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转化率（%）',
      },
    },
    {
      fieldName: 'isEffective',
      label: '是否有效',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'effectivenessScore',
      label: '有效性评分（0-100分）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入有效性评分（0-100分）',
      },
    },
    {
      fieldName: 'feedbackContent',
      label: '反馈内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'feedbackTime',
      label: '反馈时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'priority',
      label: '优先级（数字越大优先级越高）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入优先级（数字越大优先级越高）',
      },
    },
    {
      fieldName: 'displayOrder',
      label: '显示顺序',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入显示顺序',
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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品编号',
      },
    },
    {
      fieldName: 'productName',
      label: '产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品名称',
      },
    },
    {
      fieldName: 'productCategoryId',
      label: '产品类目ID（关联 crm_product_category 表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品类目ID（关联 crm_product_category 表）',
      },
    },
    {
      fieldName: 'productType',
      label: '产品类型（字典: aicrm_product_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择产品类型（字典: aicrm_product_type）',
      },
    },
    {
      fieldName: 'recommendationNo',
      label: '推荐编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐编号',
      },
    },
    {
      fieldName: 'recommendationType',
      label: '推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
      },
    },
    {
      fieldName: 'recommendationSource',
      label: '推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
      },
    },
    {
      fieldName: 'recommendationReason',
      label: '推荐理由',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐理由',
      },
    },
    {
      fieldName: 'recommendationScore',
      label: '推荐评分（0-100分）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐评分（0-100分）',
      },
    },
    {
      fieldName: 'recommendationDate',
      label: '推荐日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'recommendationTime',
      label: '推荐时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'validStartDate',
      label: '有效开始日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'validEndDate',
      label: '有效结束日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isHotSale',
      label: '是否热销产品',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否热销产品',
      },
    },
    {
      fieldName: 'isMatchCustomer',
      label: '是否匹配客户',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否匹配客户',
      },
    },
    {
      fieldName: 'matchDegree',
      label: '匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',
      },
    },
    {
      fieldName: 'recommendationStatus',
      label: '推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）',
      },
    },
    {
      fieldName: 'pushTime',
      label: '推送时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'pushChannel',
      label: '推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
      },
    },
    {
      fieldName: 'viewTime',
      label: '查看时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'viewCount',
      label: '查看次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入查看次数',
      },
    },
    {
      fieldName: 'clickTime',
      label: '点击时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'clickCount',
      label: '点击次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入点击次数',
      },
    },
    {
      fieldName: 'applyTime',
      label: '申请时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'purchaseTime',
      label: '购买时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'purchaseAmount',
      label: '购买金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入购买金额',
      },
    },
    {
      fieldName: 'rejectTime',
      label: '拒绝时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'rejectReason',
      label: '拒绝原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入拒绝原因',
      },
    },
    {
      fieldName: 'recommenderUserId',
      label: '推荐人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐人ID',
      },
    },
    {
      fieldName: 'recommenderUserName',
      label: '推荐人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐人姓名',
      },
    },
    {
      fieldName: 'recommenderDeptId',
      label: '推荐部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐部门ID',
      },
    },
    {
      fieldName: 'recommenderDeptName',
      label: '推荐部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐部门名称',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率/收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入利率/收益率（%）',
      },
    },
    {
      fieldName: 'termDays',
      label: '期限（天）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入期限（天）',
      },
    },
    {
      fieldName: 'minAmount',
      label: '起购金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入起购金额',
      },
    },
    {
      fieldName: 'maxAmount',
      label: '最大金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最大金额',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'productFeatures',
      label: '产品特点',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品特点',
      },
    },
    {
      fieldName: 'productAdvantage',
      label: '产品优势',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品优势',
      },
    },
    {
      fieldName: 'marketingTheme',
      label: '营销主题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营销主题',
      },
    },
    {
      fieldName: 'marketingContent',
      label: '营销内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营销内容',
      },
    },
    {
      fieldName: 'promotionType',
      label: '促销类型（字典: aicrm_promotion_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择促销类型（字典: aicrm_promotion_type）',
      },
    },
    {
      fieldName: 'promotionContent',
      label: '促销内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入促销内容',
      },
    },
    {
      fieldName: 'conversionRate',
      label: '转化率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转化率（%）',
      },
    },
    {
      fieldName: 'isEffective',
      label: '是否有效',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否有效',
      },
    },
    {
      fieldName: 'effectivenessScore',
      label: '有效性评分（0-100分）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入有效性评分（0-100分）',
      },
    },
    {
      fieldName: 'feedbackContent',
      label: '反馈内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入反馈内容',
      },
    },
    {
      fieldName: 'feedbackTime',
      label: '反馈时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'priority',
      label: '优先级（数字越大优先级越高）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入优先级（数字越大优先级越高）',
      },
    },
    {
      fieldName: 'displayOrder',
      label: '显示顺序',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入显示顺序',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerProductRecommendationApi.CustomerProductRecommendation>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '推荐主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'sequenceNo',
      title: '序号',
      minWidth: 120,
    },
    {
      field: 'productCode',
      title: '产品编号',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '产品名称',
      minWidth: 120,
    },
    {
      field: 'productCategoryId',
      title: '产品类目ID（关联 crm_product_category 表）',
      minWidth: 120,
    },
    {
      field: 'productType',
      title: '产品类型（字典: aicrm_product_type）',
      minWidth: 120,
    },
    {
      field: 'recommendationNo',
      title: '推荐编号',
      minWidth: 120,
    },
    {
      field: 'recommendationType',
      title: '推荐类型（字典: aicrm_recommendation_type，intelligent=智能推荐，manual=人工推荐，hot_sale=热销产品，match_customer=匹配客户，cross_sell=交叉销售）',
      minWidth: 120,
    },
    {
      field: 'recommendationSource',
      title: '推荐来源（字典: aicrm_recommendation_source，system_auto=系统自动，ai_analysis=AI分析，manager_recommend=客户经理推荐，data_mining=数据挖掘）',
      minWidth: 120,
    },
    {
      field: 'recommendationReason',
      title: '推荐理由',
      minWidth: 120,
    },
    {
      field: 'recommendationScore',
      title: '推荐评分（0-100分）',
      minWidth: 120,
    },
    {
      field: 'recommendationDate',
      title: '推荐日期',
      minWidth: 120,
    },
    {
      field: 'recommendationTime',
      title: '推荐时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'validStartDate',
      title: '有效开始日期',
      minWidth: 120,
    },
    {
      field: 'validEndDate',
      title: '有效结束日期',
      minWidth: 120,
    },
    {
      field: 'isHotSale',
      title: '是否热销产品',
      minWidth: 120,
    },
    {
      field: 'isMatchCustomer',
      title: '是否匹配客户',
      minWidth: 120,
    },
    {
      field: 'matchDegree',
      title: '匹配度（字典: aicrm_match_degree，high=高度匹配，medium=中等匹配，low=低度匹配）',
      minWidth: 120,
    },
    {
      field: 'recommendationStatus',
      title: '推荐状态（字典: aicrm_recommendation_status，pending=待推送，pushed=已推送，viewed=已查看，clicked=已点击，applied=已申请，purchased=已购买，rejected=已拒绝，expired=已过期）',
      minWidth: 120,
    },
    {
      field: 'pushTime',
      title: '推送时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'pushChannel',
      title: '推送渠道（字典: aicrm_push_channel，mobile_banking=手机银行，wechat=微信银行，sms=短信，email=邮件，call=电话，visit=上门拜访）',
      minWidth: 120,
    },
    {
      field: 'viewTime',
      title: '查看时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'viewCount',
      title: '查看次数',
      minWidth: 120,
    },
    {
      field: 'clickTime',
      title: '点击时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'clickCount',
      title: '点击次数',
      minWidth: 120,
    },
    {
      field: 'applyTime',
      title: '申请时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'purchaseTime',
      title: '购买时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'purchaseAmount',
      title: '购买金额',
      minWidth: 120,
    },
    {
      field: 'rejectTime',
      title: '拒绝时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'rejectReason',
      title: '拒绝原因',
      minWidth: 120,
    },
    {
      field: 'recommenderUserId',
      title: '推荐人ID',
      minWidth: 120,
    },
    {
      field: 'recommenderUserName',
      title: '推荐人姓名',
      minWidth: 120,
    },
    {
      field: 'recommenderDeptId',
      title: '推荐部门ID',
      minWidth: 120,
    },
    {
      field: 'recommenderDeptName',
      title: '推荐部门名称',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '利率/收益率（%）',
      minWidth: 120,
    },
    {
      field: 'termDays',
      title: '期限（天）',
      minWidth: 120,
    },
    {
      field: 'minAmount',
      title: '起购金额',
      minWidth: 120,
    },
    {
      field: 'maxAmount',
      title: '最大金额',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（字典: aicrm_risk_level）',
      minWidth: 120,
    },
    {
      field: 'productFeatures',
      title: '产品特点',
      minWidth: 120,
    },
    {
      field: 'productAdvantage',
      title: '产品优势',
      minWidth: 120,
    },
    {
      field: 'marketingTheme',
      title: '营销主题',
      minWidth: 120,
    },
    {
      field: 'marketingContent',
      title: '营销内容',
      minWidth: 120,
    },
    {
      field: 'promotionType',
      title: '促销类型（字典: aicrm_promotion_type）',
      minWidth: 120,
    },
    {
      field: 'promotionContent',
      title: '促销内容',
      minWidth: 120,
    },
    {
      field: 'conversionRate',
      title: '转化率（%）',
      minWidth: 120,
    },
    {
      field: 'isEffective',
      title: '是否有效',
      minWidth: 120,
    },
    {
      field: 'effectivenessScore',
      title: '有效性评分（0-100分）',
      minWidth: 120,
    },
    {
      field: 'feedbackContent',
      title: '反馈内容',
      minWidth: 120,
    },
    {
      field: 'feedbackTime',
      title: '反馈时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'priority',
      title: '优先级（数字越大优先级越高）',
      minWidth: 120,
    },
    {
      field: 'displayOrder',
      title: '显示顺序',
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