<script lang="ts" setup>
import type { AicrmMarketingTaskAssignmentApi } from '#/api/aicrm/marketingtaskassignment';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createMarketingTaskAssignment, getMarketingTaskAssignment, updateMarketingTaskAssignment } from '#/api/aicrm/marketingtaskassignment';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['营销活动任务下发'])
    : $t('ui.actionTitle.create', ['营销活动任务下发']);
});

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 120,
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
    const data = (await formApi.getValues()) as AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment;
    try {
      await (formData.value?.id ? updateMarketingTaskAssignment(data) : createMarketingTaskAssignment(data));
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

    formData.value = modalApi.getData<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>();
    if (formData.value?.id) {
      const data = await getMarketingTaskAssignment(formData.value.id);
      await formApi.setValues(data as any);
    } else {
      await formApi.resetForm();
    }
  },
});

defineExpose({
  modalApi,
});
</script>

<template>
  <Modal :title="getTitle" class="!w-[800px]">
    <Form />
  </Modal>
</template>
