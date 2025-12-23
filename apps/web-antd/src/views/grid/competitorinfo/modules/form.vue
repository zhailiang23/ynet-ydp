<script lang="ts" setup>
import type { GridCompetitorInfoApi } from '#/api/grid/competitorinfo';

import { computed, reactive, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { createCompetitorInfo, getCompetitorInfo, updateCompetitorInfo } from '#/api/grid/competitorinfo';
import { $t } from '#/locales';
import { useUserStore } from '@vben/stores';

import CustomerMapPoint from '../../communitycustomer/modules/customer-map-point.vue';

const emit = defineEmits(['success']);
const formData = ref<GridCompetitorInfoApi.CompetitorInfo>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['同业信息'])
    : $t('ui.actionTitle.create', ['同业信息']);
});

// 表单数据
const formState = reactive<any>({
  competitorName: '',
  address: '',
  longitude: undefined,
  latitude: undefined,
  businessStrategy: '',
  coreCustomers: '',
  productAdvantages: '',
  productDisadvantages: '',
  employeeCode: '',
  employeeName: '',
});

// 地图坐标数据
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
async function initFormData(data: GridCompetitorInfoApi.CompetitorInfo | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getCompetitorInfo(data.id);
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
    // 新建模式：重置表单并填充当前用户信息
    formState.id = undefined;
    formState.competitorName = '';
    formState.address = '';
    formState.longitude = undefined;
    formState.latitude = undefined;
    formState.businessStrategy = '';
    formState.coreCustomers = '';
    formState.productAdvantages = '';
    formState.productDisadvantages = '';
    formState.employeeCode = userStore.userInfo?.username || '';
    formState.employeeName = userStore.userInfo?.nickname || '';

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
  if (!formState.competitorName) {
    message.error('请输入同业网点名称');
    return false;
  }
  if (!formState.address) {
    message.error('请选择地址');
    return false;
  }
  if (!formState.businessStrategy) {
    message.error('请输入主要经营策略');
    return false;
  }
  if (!formState.coreCustomers) {
    message.error('请输入核心客户群');
    return false;
  }
  if (!formState.productAdvantages) {
    message.error('请输入产品优势');
    return false;
  }
  if (!formState.productDisadvantages) {
    message.error('请输入产品劣势');
    return false;
  }

  // 同步坐标数据到 formState
  formState.longitude = coordinates.value.longitude ?? undefined;
  formState.latitude = coordinates.value.latitude ?? undefined;
  formState.address = coordinates.value.address ?? formState.address;

  console.log('=== 提交同业信息数据 ===');
  console.log('coordinates:', coordinates.value);
  console.log('完整 formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateCompetitorInfo(formState);
    } else {
      await createCompetitorInfo(formState);
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
    const data = modalApi.getData<GridCompetitorInfoApi.CompetitorInfo>();
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
          <a-form-item label="同业网点名称" name="competitorName">
            <a-input v-model:value="formState.competitorName" placeholder="请输入同业网点名称" />
          </a-form-item>

          <a-form-item label="地址">
            <a-textarea
              v-model:value="formState.address"
              :rows="3"
              placeholder="点击地图选择位置后自动填充"
            />
          </a-form-item>

          <a-form-item label="主要经营策略" name="businessStrategy">
            <a-textarea
              v-model:value="formState.businessStrategy"
              :rows="3"
              placeholder="请输入主要经营策略"
            />
          </a-form-item>

          <a-form-item label="核心客户群" name="coreCustomers">
            <a-textarea
              v-model:value="formState.coreCustomers"
              :rows="3"
              placeholder="请输入核心客户群"
            />
          </a-form-item>

          <a-form-item label="产品优势" name="productAdvantages">
            <a-textarea
              v-model:value="formState.productAdvantages"
              :rows="3"
              placeholder="请输入产品优势"
            />
          </a-form-item>

          <a-form-item label="产品劣势" name="productDisadvantages">
            <a-textarea
              v-model:value="formState.productDisadvantages"
              :rows="3"
              placeholder="请输入产品劣势"
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
    </div>
  </Modal>
</template>
