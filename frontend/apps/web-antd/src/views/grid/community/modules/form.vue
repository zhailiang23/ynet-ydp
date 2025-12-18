<script lang="ts" setup>
import type { CommunityApi } from '#/api/grid/community';

import { computed, reactive, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { createCommunity, getCommunity, updateCommunity } from '#/api/grid/community';
import { $t } from '#/locales';
import { useUserStore } from '@vben/stores';

import CommunityMapPoint from './community-map-point.vue';

const emit = defineEmits(['success']);
const formData = ref<CommunityApi.Community>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['社区信息'])
    : $t('ui.actionTitle.create', ['社区信息']);
});

// 表单数据
const formState = reactive<CommunityApi.Community>({
  communityName: '',
  status: 'ACTIVE',
  teamCount: undefined,
  managerUserId: undefined,
  managerUserName: '',
  householdCount: undefined,
  individualBusinessCount: undefined,
  smallEnterpriseCount: undefined,
  commercialComplexCount: undefined,
  qualityUnitCount: undefined,
  score: undefined,
  adjustedScore: undefined,
  adjustmentReason: undefined,
  longitude: undefined,
  latitude: undefined,
});

// 地图坐标数据 - 使用 ref 而不是 reactive
const coordinates = ref<{
  longitude: number | null;
  latitude: number | null;
}>({
  longitude: null,
  latitude: null,
});

// 监听坐标变化，实时同步到 formState
watch(
  coordinates,
  (newVal) => {
    if (newVal) {
      formState.longitude = newVal.longitude ?? undefined;
      formState.latitude = newVal.latitude ?? undefined;
    }
  },
  { deep: true }
);

// 初始化表单数据
async function initFormData(data: CommunityApi.Community | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getCommunity(data.id);
      Object.assign(formState, result);

      // 初始化地图坐标
      coordinates.value = {
        longitude: result.longitude ?? null,
        latitude: result.latitude ?? null,
      };
    } catch (error) {
      console.error('加载数据失败:', error);
      message.error('加载数据失败');
    }
  } else {
    // 新建模式：重置并填充当前用户信息
    formState.id = undefined;
    formState.communityName = '';
    formState.status = 'ACTIVE';
    formState.teamCount = undefined;
    formState.householdCount = undefined;
    formState.individualBusinessCount = undefined;
    formState.smallEnterpriseCount = undefined;
    formState.commercialComplexCount = undefined;
    formState.qualityUnitCount = undefined;
    formState.score = undefined;
    formState.adjustedScore = undefined;
    formState.adjustmentReason = undefined;
    formState.longitude = undefined;
    formState.latitude = undefined;
    formState.managerUserId = Number(userStore.userInfo?.userId);
    formState.managerUserName = userStore.userInfo?.nickname || '';

    // 重置地图坐标
    coordinates.value = {
      longitude: null,
      latitude: null,
    };
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.communityName) {
    message.error('请输入社区名称');
    return false;
  }

  // 同步坐标数据到 formState
  formState.longitude = coordinates.value.longitude ?? undefined;
  formState.latitude = coordinates.value.latitude ?? undefined;

  // 调试：打印要提交的数据
  console.log('=== 提交社区数据 ===');
  console.log('coordinates:', coordinates.value);
  console.log('formState.longitude:', formState.longitude);
  console.log('formState.latitude:', formState.latitude);
  console.log('完整 formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateCommunity(formState);
    } else {
      await createCommunity(formState);
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
    const data = modalApi.getData<CommunityApi.Community>();
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
        <CommunityMapPoint v-model="coordinates" />
      </div>

      <!-- 右侧表单区域 (1/3) -->
      <div class="w-1/3 overflow-y-auto">
        <a-form layout="vertical" :model="formState">
      <a-form-item label="社区名称" name="communityName">
        <a-input v-model:value="formState.communityName" placeholder="请输入社区名称" />
      </a-form-item>

      <a-form-item label="责任人姓名">
        <a-input v-model:value="formState.managerUserName" placeholder="当前用户" disabled />
      </a-form-item>

      <a-form-item label="团队人数">
        <a-input-number
          v-model:value="formState.teamCount"
          :min="0"
          class="w-full"
          placeholder="请输入团队人数"
        />
      </a-form-item>

      <a-form-item label="人口（户数）">
        <a-input-number
          v-model:value="formState.householdCount"
          :min="0"
          class="w-full"
          placeholder="请输入人口户数"
        />
      </a-form-item>

      <a-form-item label="个体工商户数">
        <a-input-number
          v-model:value="formState.individualBusinessCount"
          :min="0"
          class="w-full"
          placeholder="请输入个体工商户数"
        />
      </a-form-item>

      <a-form-item label="小微企业数">
        <a-input-number
          v-model:value="formState.smallEnterpriseCount"
          :min="0"
          class="w-full"
          placeholder="请输入小微企业数"
        />
      </a-form-item>

      <a-form-item label="商业综合体数">
        <a-input-number
          v-model:value="formState.commercialComplexCount"
          :min="0"
          class="w-full"
          placeholder="请输入商业综合体数"
        />
      </a-form-item>

      <a-form-item label="优质单位数">
        <a-input-number
          v-model:value="formState.qualityUnitCount"
          :min="0"
          class="w-full"
          placeholder="请输入优质单位数"
        />
      </a-form-item>

      <a-form-item label="评分">
        <a-input-number
          v-model:value="formState.score"
          :min="0"
          :max="100"
          :precision="2"
          class="w-full"
          placeholder="请输入评分（0-100）"
        />
      </a-form-item>

      <a-form-item label="校正评分">
        <a-input-number
          v-model:value="formState.adjustedScore"
          :min="0"
          :max="100"
          :precision="2"
          class="w-full"
          placeholder="请输入校正评分（0-100）"
        />
      </a-form-item>

      <a-form-item label="校正原因">
        <a-textarea
          v-model:value="formState.adjustmentReason"
          :rows="3"
          placeholder="请输入校正原因"
        />
      </a-form-item>

      <a-form-item label="状态" name="status">
        <a-radio-group v-model:value="formState.status">
          <a-radio value="ACTIVE">正常</a-radio>
          <a-radio value="INACTIVE">无效</a-radio>
        </a-radio-group>
      </a-form-item>
    </a-form>
      </div>
    </div>
  </Modal>
</template>
