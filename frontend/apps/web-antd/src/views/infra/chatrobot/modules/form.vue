<script lang="ts" setup>
import type { InfraChatRobotApi } from '#/api/infra/chatrobot';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createChatRobot, getChatRobot, updateChatRobot } from '#/api/infra/chatrobot';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<InfraChatRobotApi.ChatRobot>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['对话机器人管理'])
    : $t('ui.actionTitle.create', ['对话机器人管理']);
});

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 80,
  },
  layout: 'horizontal',
  schema: useFormSchema(),
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    // 提交表单
    const formValues = (await formApi.getValues()) as any;

    // 根据平台类型组装 platformConfig
    if (formValues.platformType === 'dingtalk') {
      formValues.platformConfig = JSON.stringify({
        appKey: formValues.dingtalkAppKey,
        appSecret: formValues.dingtalkAppSecret,
      });
      // 删除临时字段
      delete formValues.dingtalkAppKey;
      delete formValues.dingtalkAppSecret;
    }

    const data = formValues as InfraChatRobotApi.ChatRobot;
    try {
      await (formData.value?.id ? updateChatRobot(data) : createChatRobot(data));
      // 关闭并提示
      await modalApi.close();
      emit('success');
      message.success($t('ui.actionMessage.operationSuccess'));
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      formData.value = undefined;
      return;
    }
    // 加载数据
    const data = modalApi.getData<InfraChatRobotApi.ChatRobot>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getChatRobot(data.id);

      // 如果是钉钉平台，拆解 platformConfig 到对应字段
      const formValues: any = { ...formData.value };
      if (formData.value.platformType === 'dingtalk' && formData.value.platformConfig) {
        try {
          const config = JSON.parse(formData.value.platformConfig);
          formValues.dingtalkAppKey = config.appKey;
          formValues.dingtalkAppSecret = config.appSecret;
        } catch (e) {
          console.error('解析平台配置失败:', e);
        }
      }

      // 设置到 values
      await formApi.setValues(formValues);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal :title="getTitle">
    <Form class="mx-4" />
  </Modal>
</template>