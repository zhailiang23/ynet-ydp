<script lang="ts" setup>
import type { TwinsCustomerAdminApi } from '#/api/twins/customerAdmin';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { checkCustomerAdminExists, createCustomerAdmin, getCustomerAdmin, updateCustomerAdmin } from '#/api/twins/customerAdmin';
import { getSimpleUserList } from '#/api/system/user';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<TwinsCustomerAdminApi.CustomerAdmin>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['客服信息'])
    : $t('ui.actionTitle.create', ['客服信息']);
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
    try {
      const formValues = await formApi.getValues();

      // 编辑模式直接提交
      if (formData.value?.id) {
        await updateCustomerAdmin(formValues as TwinsCustomerAdminApi.CustomerAdmin);
        await modalApi.close();
        emit('success');
        message.success($t('ui.actionMessage.operationSuccess'));
        return;
      }

      // 新增模式：检查用户是否已存在
      const userId = formValues.userId;
      const exists = await checkCustomerAdminExists(userId);
      if (exists) {
        message.warning('该用户已经是客服，无需重复添加');
        return;
      }

      // 获取用户信息
      const users = await getSimpleUserList();
      const selectedUser = users.find(u => u.id === userId);
      if (!selectedUser) {
        message.error('未找到选择的用户');
        return;
      }

      // 构造提交数据
      const data: TwinsCustomerAdminApi.CustomerAdmin = {
        id: 0,
        customerId: selectedUser.id,
        username: selectedUser.nickname,
        password: '123456',
      };

      await createCustomerAdmin(data);
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
    const data = modalApi.getData<TwinsCustomerAdminApi.CustomerAdmin>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getCustomerAdmin(data.id);
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