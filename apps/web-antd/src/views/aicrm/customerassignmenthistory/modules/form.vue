<script lang="ts" setup>
import type { AicrmCustomerAssignmentHistoryApi } from '#/api/aicrm/customerassignmenthistory';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createCustomerAssignmentHistory, getCustomerAssignmentHistory, updateCustomerAssignmentHistory } from '#/api/aicrm/customerassignmenthistory';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['客户归属调整历史表（零售+对公共用，记录所有归属变更历史）'])
    : $t('ui.actionTitle.create', ['客户归属调整历史表（零售+对公共用，记录所有归属变更历史）']);
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
    const data = (await formApi.getValues()) as AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory;
    try {
      await (formData.value?.id ? updateCustomerAssignmentHistory(data) : createCustomerAssignmentHistory(data));
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
    const data = modalApi.getData<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getCustomerAssignmentHistory(data.id);
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