import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { InfraChatRobotApi } from '#/api/infra/chatrobot';

import { getDictOptions } from '@vben/hooks';
import { DICT_TYPE } from '@vben/constants';

import { getSimpleExternalAgentList } from '#/api/infra/externalagent';

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
      fieldName: 'name',
      label: '机器人名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机器人名称',
      },
    },
    {
      fieldName: 'description',
      label: '机器人描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入机器人描述',
      },
    },
    {
      fieldName: 'platformType',
      label: '对接平台类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions(DICT_TYPE.INFRA_CHAT_ROBOT_PLATFORM_TYPE, 'string'),
        placeholder: '请选择对接平台类型',
      },
    },
    {
      fieldName: 'platformConfig',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    // 钉钉配置字段
    {
      fieldName: 'dingtalkAppKey',
      label: 'AppKey',
      component: 'Input',
      componentProps: {
        placeholder: '请输入应用的 AppKey',
      },
      dependencies: {
        triggerFields: ['platformType'],
        show: (values) => values.platformType === 'dingtalk',
        required: (values) => values.platformType === 'dingtalk',
      },
    },
    {
      fieldName: 'dingtalkAppSecret',
      label: 'AppSecret',
      component: 'InputPassword',
      componentProps: {
        placeholder: '请输入应用的 AppSecret',
      },
      dependencies: {
        triggerFields: ['platformType'],
        show: (values) => values.platformType === 'dingtalk',
        required: (values) => values.platformType === 'dingtalk',
      },
      help: '使用 Stream 模式推送，无需配置回调地址和加密参数',
    },
    {
      fieldName: 'agentId',
      label: '绑定的智能体',
      component: 'ApiSelect',
      componentProps: {
        api: getSimpleExternalAgentList,
        labelField: 'name',
        valueField: 'id',
        placeholder: '请选择绑定的智能体',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Switch',
      componentProps: {
        checkedValue: 1,
        unCheckedValue: 0,
        class: 'w-12',
      },
      defaultValue: 1,
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '机器人名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机器人名称',
      },
    },
    {
      fieldName: 'platformType',
      label: '对接平台类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions(DICT_TYPE.INFRA_CHAT_ROBOT_PLATFORM_TYPE, 'string'),
        placeholder: '请选择对接平台类型',
      },
    },
    {
      fieldName: 'status',
      label: '是否启用',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否启用',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<InfraChatRobotApi.ChatRobot>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '机器人名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '机器人描述',
      minWidth: 120,
    },
    {
      field: 'platformType',
      title: '对接平台类型',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: DICT_TYPE.INFRA_CHAT_ROBOT_PLATFORM_TYPE } },
    },
    {
      field: 'agentName',
      title: '绑定的智能体',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态',
      width: 80,
      align: 'center',
      cellRender: {
        name: 'CellSwitch',
        props: {
          checkedValue: 1,
          unCheckedValue: 0,
        },
      },
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