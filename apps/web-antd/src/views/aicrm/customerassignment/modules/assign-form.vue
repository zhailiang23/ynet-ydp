<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { assignCustomers } from '#/api/aicrm/customerassignment';
import { $t } from '#/locales';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);

const formSchema: VbenFormSchema[] = [
  {
    fieldName: 'assignmentType',
    label: '归属类型',
    rules: 'required',
    component: 'RadioGroup',
    componentProps: {
      options: [
        { label: '主办', value: 1 },
        { label: '协办', value: 2 },
      ],
      optionType: 'button',
      buttonStyle: 'solid',
    },
    defaultValue: 1,
  },
  {
    fieldName: 'deptId',
    label: '归属部门ID',
    rules: 'required',
    component: 'Input',
    componentProps: {
      placeholder: '请输入归属部门ID',
    },
  },
  {
    fieldName: 'userId',
    label: '客户经理ID',
    rules: 'required',
    component: 'Input',
    componentProps: {
      placeholder: '请输入客户经理用户ID',
    },
  },
  {
    fieldName: 'hasViewRight',
    label: '查看权限',
    component: 'Switch',
    defaultValue: true,
  },
  {
    fieldName: 'hasMaintainRight',
    label: '维护权限',
    component: 'Switch',
    defaultValue: true,
  },
  {
    fieldName: 'remark',
    label: '备注',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入备注',
      rows: 3,
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
    // 提交表单
    const values = await formApi.getValues();
    try {
      await assignCustomers({
        customerIds: customerIds.value,
        ...values,
      } as AicrmCustomerAssignmentApi.AssignCustomerReq);
      // 关闭并提示
      await modalApi.close();
      emit('success');
      message.success('批量分配客户成功');
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
    // 加载数据
    const data = modalApi.getData<{ customerIds: number[] }>();
    if (data && data.customerIds) {
      customerIds.value = data.customerIds;
    }
  },
});
</script>

<template>
  <Modal title="批量分配客户" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-blue-50 p-3 text-sm dark:bg-blue-950/30">
        <div class="text-gray-600 dark:text-gray-400">
          将为 <span class="font-bold text-blue-600 dark:text-blue-400">{{ customerIds.length }}</span> 个客户分配归属关系
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
