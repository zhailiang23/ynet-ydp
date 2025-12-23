<script lang="ts" setup>
import type { GridKeyPersonApi } from '#/api/grid/keyperson';

import { computed, reactive, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { createKeyPerson, getKeyPerson, updateKeyPerson } from '#/api/grid/keyperson';
import { $t } from '#/locales';
import { useUserStore } from '@vben/stores';

const emit = defineEmits(['success']);
const formData = ref<GridKeyPersonApi.KeyPerson>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['关键人信息'])
    : $t('ui.actionTitle.create', ['关键人信息']);
});

// 表单数据
const formState = reactive<any>({
  personName: '',
  organization: '',
  position: '',
  contact: '',
  establishDate: undefined,
  lastMaintainDate: undefined,
  employeeCode: '',
  employeeName: '',
});

// 初始化表单数据
async function initFormData(data: GridKeyPersonApi.KeyPerson | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getKeyPerson(data.id);
      Object.assign(formState, result);
    } catch (error) {
      console.error('加载数据失败:', error);
      message.error('加载数据失败');
    }
  } else {
    // 新建模式：重置表单并填充当前用户信息
    formState.id = undefined;
    formState.personName = '';
    formState.organization = '';
    formState.position = '';
    formState.contact = '';
    formState.establishDate = undefined;
    formState.lastMaintainDate = undefined;
    formState.employeeCode = userStore.userInfo?.username || '';
    formState.employeeName = userStore.userInfo?.nickname || '';
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.personName) {
    message.error('请输入姓名');
    return false;
  }
  if (!formState.organization) {
    message.error('请输入单位/小区');
    return false;
  }
  if (!formState.contact) {
    message.error('请输入联系方式');
    return false;
  }

  console.log('=== 提交关键人信息数据 ===');
  console.log('完整 formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateKeyPerson(formState);
    } else {
      await createKeyPerson(formState);
    }
    return true;
  } catch (error) {
    console.error('提交失败:', error);
    return false;
  }
}

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const success = await handleSubmit();
    if (!success) {
      return;
    }
    await modalApi.close();
    emit('success');
    message.success($t('ui.actionMessage.operationSuccess'));
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时重置数据
      formData.value = undefined;
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<GridKeyPersonApi.KeyPerson>();
    formData.value = data;
    modalApi.lock();
    try {
      await initFormData(data);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal
    :title="getTitle"
    :footer-props="{ okText: '确定', cancelText: '取消' }"
    class="!w-[600px]"
  >
    <div class="p-4">
      <a-form layout="vertical" :model="formState">
        <a-form-item label="姓名" name="personName">
          <a-input v-model:value="formState.personName" placeholder="请输入姓名" />
        </a-form-item>

        <a-form-item label="单位/小区" name="organization">
          <a-input v-model:value="formState.organization" placeholder="请输入单位/小区" />
        </a-form-item>

        <a-form-item label="职务">
          <a-input v-model:value="formState.position" placeholder="请输入职务" />
        </a-form-item>

        <a-form-item label="联系方式" name="contact">
          <a-input v-model:value="formState.contact" placeholder="请输入联系方式" />
        </a-form-item>

        <a-form-item label="建立联系日期">
          <a-date-picker
            v-model:value="formState.establishDate"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
            class="w-full"
            placeholder="请选择建立联系日期"
          />
        </a-form-item>

        <a-form-item label="最新维护日期">
          <a-date-picker
            v-model:value="formState.lastMaintainDate"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
            class="w-full"
            placeholder="请选择最新维护日期"
          />
        </a-form-item>

        <a-form-item label="员工工号">
          <a-input v-model:value="formState.employeeCode" placeholder="当前用户" disabled />
        </a-form-item>

        <a-form-item label="员工姓名">
          <a-input v-model:value="formState.employeeName" placeholder="当前用户" disabled />
        </a-form-item>
      </a-form>
    </div>
  </Modal>
</template>
