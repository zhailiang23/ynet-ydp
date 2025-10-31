<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { reclaimCustomers } from '#/api/aicrm/customerassignment';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);

const formSchema: VbenFormSchema[] = [
  {
    fieldName: 'reclaimReason',
    label: '回收原因',
    rules: 'required',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入回收原因（必填）',
      rows: 4,
      maxlength: 500,
      showCount: true,
    },
  },
];

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
      await reclaimCustomers({
        customerIds: customerIds.value,
        ...values,
      } as AicrmCustomerAssignmentApi.ReclaimCustomerReq);
      await modalApi.close();
      emit('success');
      message.success('批量回收客户成功');
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
</script>

<template>
  <Modal title="批量回收客户" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-orange-50 p-3 text-sm dark:bg-orange-950/30">
        <div class="text-gray-600 dark:text-gray-400">
          将回收 <span class="font-bold text-orange-600 dark:text-orange-400">{{ customerIds.length }}</span> 个客户
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
