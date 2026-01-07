<script lang="ts" setup>
import type { AicrmTaskApi } from '#/api/aicrm/task';

import { computed, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createTask, getTask, updateTask } from '#/api/aicrm/task';
import { getSimpleCustomerList } from '#/api/aicrm/customer';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmTaskApi.Task>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['AI智能任务'])
    : $t('ui.actionTitle.create', ['AI智能任务']);
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

// 监听客户选择，自动填充客户姓名
const customerList = ref<any[]>([]);

// 加载客户列表（用于自动填充客户姓名）
async function loadCustomerList() {
  try {
    customerList.value = await getSimpleCustomerList();
  } catch (error) {
    console.error('加载客户列表失败:', error);
  }
}

// 监听 customerId 变化，自动填充 customerName
watch(
  () => formApi.values?.customerId,
  async (customerId) => {
    if (!customerId) {
      await formApi.setFieldValue('customerName', '');
      return;
    }

    // 从客户列表中查找对应的客户姓名
    const customer = customerList.value.find((c) => c.id === customerId);
    if (customer) {
      await formApi.setFieldValue('customerName', customer.customerName);
    }
  },
);

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    // 提交表单
    const data = (await formApi.getValues()) as AicrmTaskApi.Task;
    try {
      await (formData.value?.id ? updateTask(data) : createTask(data));
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
    // 加载客户列表
    await loadCustomerList();

    // 加载数据
    const data = modalApi.getData<AicrmTaskApi.Task>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getTask(data.id);
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
