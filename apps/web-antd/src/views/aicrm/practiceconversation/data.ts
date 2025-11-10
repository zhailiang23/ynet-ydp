import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeConversationApi } from '#/api/aicrm/practiceconversation';

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
      fieldName: 'recordId',
      label: '关联用户陪练记录ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联用户陪练记录ID',
      },
    },
    {
      fieldName: 'sequenceNo',
      label: '对话序号（从1开始）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入对话序号（从1开始）',
      },
    },
    {
      fieldName: 'role',
      label: '发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
      },
    },
    {
      fieldName: 'messageContent',
      label: '消息内容',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'messageTime',
      label: '消息时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'instantScore',
      label: '即时评分（针对学员发言）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入即时评分（针对学员发言）',
      },
    },
    {
      fieldName: 'speechAnalysis',
      label: '话术分析（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入话术分析（JSON格式）',
      },
    },
    {
      fieldName: 'skillEvaluation',
      label: '技巧运用评价（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入技巧运用评价（JSON格式）',
      },
    },
    {
      fieldName: 'emotionTag',
      label: '对话情绪标签：字典 aicrm_emotion_tag',
      component: 'Input',
      componentProps: {
        placeholder: '请输入对话情绪标签：字典 aicrm_emotion_tag',
      },
    },
    {
      fieldName: 'salesIntent',
      label: '销售意图识别：字典 aicrm_sales_intent',
      component: 'Input',
      componentProps: {
        placeholder: '请输入销售意图识别：字典 aicrm_sales_intent',
      },
    },
    {
      fieldName: 'customerReaction',
      label: '客户反应：字典 aicrm_customer_reaction',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户反应：字典 aicrm_customer_reaction',
      },
    },
    {
      fieldName: 'improvementSuggestions',
      label: 'AI改进建议（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入AI改进建议（JSON格式）',
      },
    },
    {
      fieldName: 'recommendedScripts',
      label: '推荐话术（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入推荐话术（JSON格式）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'recordId',
      label: '关联用户陪练记录ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联用户陪练记录ID',
      },
    },
    {
      fieldName: 'sequenceNo',
      label: '对话序号（从1开始）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入对话序号（从1开始）',
      },
    },
    {
      fieldName: 'role',
      label: '发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
      },
    },
    {
      fieldName: 'messageContent',
      label: '消息内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入消息内容',
      },
    },
    {
      fieldName: 'messageTime',
      label: '消息时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'instantScore',
      label: '即时评分（针对学员发言）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入即时评分（针对学员发言）',
      },
    },
    {
      fieldName: 'speechAnalysis',
      label: '话术分析（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入话术分析（JSON格式）',
      },
    },
    {
      fieldName: 'skillEvaluation',
      label: '技巧运用评价（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入技巧运用评价（JSON格式）',
      },
    },
    {
      fieldName: 'emotionTag',
      label: '对话情绪标签：字典 aicrm_emotion_tag',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入对话情绪标签：字典 aicrm_emotion_tag',
      },
    },
    {
      fieldName: 'salesIntent',
      label: '销售意图识别：字典 aicrm_sales_intent',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入销售意图识别：字典 aicrm_sales_intent',
      },
    },
    {
      fieldName: 'customerReaction',
      label: '客户反应：字典 aicrm_customer_reaction',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户反应：字典 aicrm_customer_reaction',
      },
    },
    {
      fieldName: 'improvementSuggestions',
      label: 'AI改进建议（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入AI改进建议（JSON格式）',
      },
    },
    {
      fieldName: 'recommendedScripts',
      label: '推荐话术（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入推荐话术（JSON格式）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeConversationApi.PracticeConversation>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '对话ID',
      minWidth: 120,
    },
    {
      field: 'recordId',
      title: '关联用户陪练记录ID',
      minWidth: 120,
    },
    {
      field: 'sequenceNo',
      title: '对话序号（从1开始）',
      minWidth: 120,
    },
    {
      field: 'role',
      title: '发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
      minWidth: 120,
    },
    {
      field: 'messageContent',
      title: '消息内容',
      minWidth: 120,
    },
    {
      field: 'messageTime',
      title: '消息时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'instantScore',
      title: '即时评分（针对学员发言）',
      minWidth: 120,
    },
    {
      field: 'speechAnalysis',
      title: '话术分析（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'skillEvaluation',
      title: '技巧运用评价（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'emotionTag',
      title: '对话情绪标签：字典 aicrm_emotion_tag',
      minWidth: 120,
    },
    {
      field: 'salesIntent',
      title: '销售意图识别：字典 aicrm_sales_intent',
      minWidth: 120,
    },
    {
      field: 'customerReaction',
      title: '客户反应：字典 aicrm_customer_reaction',
      minWidth: 120,
    },
    {
      field: 'improvementSuggestions',
      title: 'AI改进建议（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'recommendedScripts',
      title: '推荐话术（JSON格式）',
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