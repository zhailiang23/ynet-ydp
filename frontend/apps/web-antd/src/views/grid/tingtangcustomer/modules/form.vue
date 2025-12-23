<script lang="ts" setup>
import type { GridTingtangCustomerApi } from '#/api/grid/tingtangcustomer';

import { computed, reactive, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { createTingtangCustomer, getTingtangCustomer, updateTingtangCustomer } from '#/api/grid/tingtangcustomer';
import { $t } from '#/locales';

import CustomerMapPoint from '../../communitycustomer/modules/customer-map-point.vue';

const emit = defineEmits(['success']);
const formData = ref<GridTingtangCustomerApi.TingtangCustomer>();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['厅堂客户'])
    : $t('ui.actionTitle.create', ['厅堂客户']);
});

// 表单数据
const formState = reactive<any>({
  customerName: '',
  gender: undefined,
  customerGroup: undefined,
  idCard: '',
  phone: '',
  address: '',
  longitude: undefined,
  latitude: undefined,
  isArchived: false,
  crmCustomerId: '',
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
async function initFormData(data: GridTingtangCustomerApi.TingtangCustomer | null | undefined) {
  if (data?.id) {
    // 编辑模式
    try {
      const result = await getTingtangCustomer(data.id);
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
    // 新建模式：重置表单
    formState.id = undefined;
    formState.customerName = '';
    formState.gender = undefined;
    formState.customerGroup = undefined;
    formState.idCard = '';
    formState.phone = '';
    formState.address = '';
    formState.longitude = undefined;
    formState.latitude = undefined;
    formState.isArchived = false;
    formState.crmCustomerId = '';

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
    message.error('请输入客户名称');
    return false;
  }

  if (!formState.gender) {
    message.error('请选择性别');
    return false;
  }

  if (!formState.customerGroup) {
    message.error('请选择客群类型');
    return false;
  }

  if (!formState.idCard) {
    message.error('请输入身份证号');
    return false;
  }

  // 同步坐标数据到 formState
  formState.longitude = coordinates.value.longitude ?? undefined;
  formState.latitude = coordinates.value.latitude ?? undefined;
  formState.address = coordinates.value.address ?? formState.address;

  // 调试：打印要提交的数据
  console.log('=== 提交厅堂客户数据 ===');
  console.log('coordinates:', coordinates.value);
  console.log('formState.longitude:', formState.longitude);
  console.log('formState.latitude:', formState.latitude);
  console.log('完整 formState:', JSON.stringify(formState, null, 2));

  try {
    if (formState.id) {
      await updateTingtangCustomer(formState);
    } else {
      await createTingtangCustomer(formState);
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
    const data = modalApi.getData<GridTingtangCustomerApi.TingtangCustomer>();
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
          <a-form-item label="客户名称" name="customerName">
            <a-input v-model:value="formState.customerName" placeholder="请输入客户名称" />
          </a-form-item>

          <a-form-item label="性别" name="gender" :rules="[{ required: true, message: '请选择性别' }]">
            <a-radio-group v-model:value="formState.gender">
              <a-radio value="男">男</a-radio>
              <a-radio value="女">女</a-radio>
            </a-radio-group>
          </a-form-item>

          <a-form-item label="客群类型" name="customerGroup" :rules="[{ required: true, message: '请选择客群类型' }]">
            <a-radio-group v-model:value="formState.customerGroup">
              <a-radio value="信贷客户">信贷客户</a-radio>
              <a-radio value="财富客户">财富客户</a-radio>
              <a-radio value="结算客户">结算客户</a-radio>
              <a-radio value="潜力客户">潜力客户</a-radio>
            </a-radio-group>
          </a-form-item>

          <a-form-item label="身份证号" name="idCard" :rules="[{ required: true, message: '请输入身份证号' }]">
            <a-input v-model:value="formState.idCard" placeholder="请输入身份证号" />
          </a-form-item>

          <a-form-item label="联系电话" name="phone">
            <a-input v-model:value="formState.phone" placeholder="请输入联系电话" />
          </a-form-item>

          <a-form-item label="地址">
            <a-textarea
              v-model:value="formState.address"
              :rows="3"
              placeholder="点击地图选择位置后自动填充，也可手动输入"
            />
          </a-form-item>

          <a-form-item label="经度">
            <a-input-number
              v-model:value="formState.longitude"
              :precision="6"
              :style="{ width: '100%' }"
              placeholder="点击地图自动填充"
            />
          </a-form-item>

          <a-form-item label="纬度">
            <a-input-number
              v-model:value="formState.latitude"
              :precision="6"
              :style="{ width: '100%' }"
              placeholder="点击地图自动填充"
            />
          </a-form-item>

          <a-form-item label="是否建档">
            <a-switch v-model:checked="formState.isArchived" />
          </a-form-item>

          <a-form-item label="CRM客户号">
            <a-input v-model:value="formState.crmCustomerId" placeholder="请输入CRM客户号" />
          </a-form-item>
        </a-form>
      </div>
    </div>
  </Modal>
</template>