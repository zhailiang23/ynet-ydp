<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { getSimpleUserList } from '#/api/system/user';
import { transferCustomers } from '#/api/aicrm/customerassignment';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);

const formSchema = computed<VbenFormSchema[]>(() => [
  {
    fieldName: 'toUserId',
    label: '移交给',
    rules: 'required',
    component: 'ApiSelect',
    componentProps: {
      api: getSimpleUserList,
      labelField: 'nickname',
      valueField: 'id',
      placeholder: '请选择目标客户经理',
      showSearch: true,
    },
  },
  {
    fieldName: 'transferReason',
    label: '移交原因',
    rules: 'required',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入移交原因（必填）',
      rows: 4,
      maxlength: 500,
      showCount: true,
    },
  },
]);

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 100,
  },
  layout: 'horizontal',
  schema: formSchema,
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    const values = await formApi.getValues();
    try {
      await transferCustomers({
        customerIds: customerIds.value,
        ...values,
      } as AicrmCustomerAssignmentApi.TransferCustomerReq);
      await modalApi.close();
      emit('success');
      message.success('客户移交成功');
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      customerIds.value = [];
      formApi.resetForm();
      return;
    }
    const data = modalApi.getData<{ customerIds: number[] }>();
    if (data && data.customerIds) {
      customerIds.value = data.customerIds;
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal title="客户移交" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-amber-50 p-3 text-sm dark:bg-amber-950/30">
        <div class="mb-2 font-semibold text-amber-700 dark:text-amber-400">
          ⚠️ 重要提示
        </div>
        <div class="text-gray-600 dark:text-gray-400">
          移交操作会<span class="font-bold text-red-600 dark:text-red-400">永久改变客户归属关系</span>,
          客户将不再归属于您,而是归属于目标客户经理。
        </div>
        <div class="mt-2 text-gray-600 dark:text-gray-400">
          将移交 <span class="font-bold text-amber-600 dark:text-amber-400">{{ customerIds.length }}</span> 个客户
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
