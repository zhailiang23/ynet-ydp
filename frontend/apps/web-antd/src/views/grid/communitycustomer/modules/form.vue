<script lang="ts" setup>
import type { GridCommunityCustomerApi } from '#/api/grid/communitycustomer';

import { computed, reactive, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { createCommunityCustomer, getCommunityCustomer, updateCommunityCustomer } from '#/api/grid/communitycustomer';
import { $t } from '#/locales';
import { useUserStore } from '@vben/stores';

import CustomerMapPoint from './customer-map-point.vue';

const emit = defineEmits(['success']);
const formData = ref<GridCommunityCustomerApi.CommunityCustomer>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['社区客户'])
    : $t('ui.actionTitle.create', ['社区客户']);
});

// 表单数据
const formState = reactive<any>({
  orgName: '',
  managerName: '',
  customerName: '',
  phone: '',
  idType: '身份证',
  idNumber: '',
  address: '',
  longitude: undefined,
  latitude: undefined,
});

// 地图坐标数据 - 使用 ref 而不是 reactive
const coordinates = ref<{
  longitude: number | null;
  latitude: number | null;
  address?: string | null;
}>({
  longitude: null,
  latitude: null,
  address: null,
});

// 监听坐标变化，实时同步到 formState
watch(
  coordinates,
  (newVal) => {
    if (newVal) {
      formState.longitude = newVal.longitude ?? undefined;
      formState.latitude = newVal.latitude ?? undefined;
      formState.address = newVal.address ?? '';
    }
  },
  { deep: true }
);

// 初始化表单数据
async function initFormData(data: GridCommunityCustomerApi.CommunityCustomer | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getCommunityCustomer(data.id);
      Object.assign(formState, result);

      // 初始化地图坐标
      coordinates.value = {
        longitude: result.longitude ?? null,
        latitude: result.latitude ?? null,
        address: result.address ?? null,
      };
    } catch (error) {
      console.error('加载数据失败:', error);
      message.error('加载数据失败');
    }
  } else {
    // 新建模式：重置并填充当前用户信息
    formState.id = undefined;
    formState.orgName = userStore.userInfo?.deptName || '';
    formState.managerName = userStore.userInfo?.nickname || '';
    formState.managerId = userStore.userInfo?.id || null; // 设置客户经理ID为当前用户ID
    formState.customerName = '';
    formState.phone = '';
    formState.idType = '身份证';
    formState.idNumber = '';
    formState.address = '';
    formState.longitude = undefined;
    formState.latitude = undefined;

    // 重置地图坐标
    coordinates.value = {
      longitude: null,
      latitude: null,
      address: null,
    };
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.customerName) {
    message.error('请输入客户姓名');
    return false;
  }
  if (!formState.phone) {
    message.error('请输入手机号');
    return false;
  }
  // 验证手机号格式
  if (!/^1[3-9]\d{9}$/.test(formState.phone)) {
    message.error('请输入正确的手机号');
    return false;
  }

  // 同步坐标数据到 formState
  formState.longitude = coordinates.value.longitude ?? undefined;
  formState.latitude = coordinates.value.latitude ?? undefined;
  formState.address = coordinates.value.address ?? formState.address;

  // 调试：打印要提交的数据
  console.log('=== 提交客户数据 ===');
  console.log('coordinates:', coordinates.value);
  console.log('formState.longitude:', formState.longitude);
  console.log('formState.latitude:', formState.latitude);
  console.log('完整 formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateCommunityCustomer(formState);
    } else {
      await createCommunityCustomer(formState);
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
    const data = modalApi.getData<GridCommunityCustomerApi.CommunityCustomer>();
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
    class="!w-[90vw]"
  >
    <div class="flex h-[700px] gap-4">
      <!-- 左侧地图区域 (2/3) -->
      <div class="w-2/3">
        <CustomerMapPoint v-model="coordinates" />
      </div>

      <!-- 右侧表单区域 (1/3) -->
      <div class="w-1/3 overflow-y-auto">
        <a-form layout="vertical" :model="formState">
      <a-form-item label="机构名称">
        <a-input v-model:value="formState.orgName" placeholder="当前用户机构" disabled />
      </a-form-item>

      <a-form-item label="客户经理姓名">
        <a-input v-model:value="formState.managerName" placeholder="当前用户" disabled />
      </a-form-item>

      <a-form-item label="客户姓名" name="customerName">
        <a-input v-model:value="formState.customerName" placeholder="请输入客户姓名" />
      </a-form-item>

      <a-form-item label="手机号" name="phone">
        <a-input v-model:value="formState.phone" placeholder="请输入手机号" />
      </a-form-item>

      <a-form-item label="证件类型">
        <a-input v-model:value="formState.idType" disabled />
      </a-form-item>

      <a-form-item label="证件号" name="idNumber">
        <a-input v-model:value="formState.idNumber" placeholder="请输入证件号" />
      </a-form-item>

      <a-form-item label="地址">
        <a-textarea
          v-model:value="formState.address"
          :rows="3"
          placeholder="点击地图选择位置后自动填充，也可手动输入"
        />
      </a-form-item>
    </a-form>
      </div>
    </div>
  </Modal>
</template>
