<script lang="ts" setup>
import type { AicrmPracticeScriptApi } from '#/api/aicrm/practicescript';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createPracticeScript, generateScriptContent, getPracticeScript, updatePracticeScript } from '#/api/aicrm/practicescript';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmPracticeScriptApi.PracticeScript>();
const isEdit = computed(() => !!formData.value?.id);
const getTitle = computed(() => {
  return isEdit.value
    ? $t('ui.actionTitle.edit', ['CRM智能陪练-陪练剧本表（支持版本控制）'])
    : $t('ui.actionTitle.create', ['CRM智能陪练-陪练剧本表（支持版本控制）']);
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
  schema: [],
  showDefaultActions: false,
});

// 在 formApi 创建后，设置 schema
formApi.setState({
  schema: useFormSchema(formApi, isEdit.value),
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();

    try {
      // 提交表单
      const data = (await formApi.getValues()) as AicrmPracticeScriptApi.PracticeScript;

      if (formData.value?.id) {
        // 编辑模式：直接更新
        await updatePracticeScript(data);
        message.success($t('ui.actionMessage.operationSuccess'));
      } else {
        // 创建模式：先创建记录，然后生成剧本内容
        const hideLoading = message.loading('正在创建剧本并生成内容...', 0);

        try {
          // 1. 创建剧本记录
          const scriptId = await createPracticeScript(data);

          // 2. 自动生成剧本内容
          await generateScriptContent({
            scriptId,
            caseId: data.caseId,
            materialIds: data.materialIds,
            skillId: data.skillId,
            marketingStep: data.marketingStep,
            difficultyLevel: data.difficultyLevel,
            scriptDescription: data.description || '个性化陪练剧本',
          });

          message.success('剧本创建并生成内容成功！');
        } finally {
          hideLoading();
        }
      }

      // 关闭并刷新列表
      await modalApi.close();
      emit('success');
    } catch (error: any) {
      console.error('操作失败:', error);
      message.error(error.message || '操作失败，请重试');
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      formData.value = undefined;
      // 重置 schema 为创建模式
      formApi.setState({
        schema: useFormSchema(formApi, false),
      });
      return;
    }
    // 加载数据
    const data = modalApi.getData<AicrmPracticeScriptApi.PracticeScript>();
    if (!data || !data.id) {
      // 创建模式
      formApi.setState({
        schema: useFormSchema(formApi, false),
      });
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getPracticeScript(data.id);
      // 更新 schema 为编辑模式
      formApi.setState({
        schema: useFormSchema(formApi, true),
      });
      // 设置到 values
      await formApi.setValues(formData.value);
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