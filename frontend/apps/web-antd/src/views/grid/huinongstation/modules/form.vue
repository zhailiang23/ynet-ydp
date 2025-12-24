<script lang="ts" setup>
import type { GridHuinongStationApi } from '#/api/grid/huinongstation';

import { computed, ref, reactive, onMounted } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, InputNumber, Slider, TreeSelect, message } from 'ant-design-vue';
import { createHuinongStation, getHuinongStation, updateHuinongStation } from '#/api/grid/huinongstation';
import { useUserStore } from '@vben/stores';
import { $t } from '#/locales';
import HuinongstationMapPoint from './huinongstation-map-point.vue';
import { getDeptList } from '#/api/system/dept';
import { handleTree } from '@vben/utils';

const emit = defineEmits(['success']);
const formData = ref<GridHuinongStationApi.HuinongStation>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['惠农站点'])
    : $t('ui.actionTitle.create', ['惠农站点']);
});

// 部门树
const deptTree = ref<any[]>([]);

// 加载部门树
async function loadDeptTree() {
  try {
    const data = await getDeptList();
    deptTree.value = handleTree(data);
  } catch (error) {
    console.error('加载部门列表失败:', error);
    message.error('加载部门列表失败');
  }
}

onMounted(() => {
  loadDeptTree();
});

// 表单数据
const formState = reactive({
  id: undefined as number | undefined,
  stationCode: '',
  stationName: '',
  orgId: undefined as number | undefined,
  address: '',
  longitude: undefined as number | undefined,
  latitude: undefined as number | undefined,
  radiusMeters: 3000,  // 默认 3km
  specialistEmployeeCode: '',
  specialistName: '',
});

// 初始化表单数据
async function initFormData(data?: GridHuinongStationApi.HuinongStation) {
  if (!data || !data.id) {
    // 新增时，尝试获取用户的部门ID和用户信息
    const userInfo = userStore.userInfo as any;
    if (userInfo?.deptId) {
      formState.orgId = userInfo.deptId;
    }
    // 自动填充惠农人员信息
    if (userInfo?.username) {
      formState.specialistEmployeeCode = userInfo.username;
    }
    if (userInfo?.nickname) {
      formState.specialistName = userInfo.nickname;
    }
    return;
  }

  // 编辑时，加载数据
  try {
    const result = await getHuinongStation(data.id);
    Object.assign(formState, result);
    // 编辑时也更新惠农人员信息为当前用户
    const userInfo = userStore.userInfo as any;
    if (userInfo?.username) {
      formState.specialistEmployeeCode = userInfo.username;
    }
    if (userInfo?.nickname) {
      formState.specialistName = userInfo.nickname;
    }
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.stationName) {
    message.error('请输入站点名称');
    return false;
  }
  if (!formState.orgId) {
    message.error('请选择所属机构');
    return false;
  }
  if (!formState.longitude || !formState.latitude) {
    message.error('请在地图上选择位置');
    return false;
  }

  // 构建提交数据（新建时 stationCode 为空，由后端自动生成）
  const submitData: any = {
    id: formState.id,
    stationCode: formState.stationCode || undefined,  // 新建时传 undefined
    stationName: formState.stationName,
    orgId: formState.orgId,
    address: formState.address,
    longitude: formState.longitude,
    latitude: formState.latitude,
    radiusMeters: formState.radiusMeters,
  };

  try {
    await (formState.id ? updateHuinongStation(submitData) : createHuinongStation(submitData));
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
      Object.assign(formState, {
        id: undefined,
        stationCode: '',
        stationName: '',
        orgId: undefined,
        address: '',
        longitude: undefined,
        latitude: undefined,
        radiusMeters: 3000,
        specialistEmployeeCode: '',
        specialistName: '',
      });
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<GridHuinongStationApi.HuinongStation>();
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
  <Modal :title="getTitle" class="!w-[90vw]" :footer-props="{ okText: '确定', cancelText: '取消' }">
    <div class="flex h-[700px] gap-4">
      <!-- 左侧:地图(2/3宽度) -->
      <div class="flex-[2]">
        <HuinongstationMapPoint
          v-model:longitude="formState.longitude"
          v-model:latitude="formState.latitude"
          v-model:address="formState.address"
          :radius-meters="formState.radiusMeters"
        />
      </div>

      <!-- 右侧:表单(1/3宽度) -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <!-- 编辑时显示站点编号（只读） -->
          <Form.Item v-if="formState.id" label="站点编号">
            <Input v-model:value="formState.stationCode" disabled />
          </Form.Item>

          <Form.Item label="站点名称" required>
            <Input v-model:value="formState.stationName" placeholder="请输入站点名称" />
          </Form.Item>

          <Form.Item label="所属机构" required>
            <TreeSelect
              v-model:value="formState.orgId"
              :tree-data="deptTree"
              :field-names="{ label: 'name', value: 'id', children: 'children' }"
              placeholder="请选择所属机构"
              show-search
              tree-default-expand-all
              :filter-tree-node="(inputValue: string, treeNode: any) =>
                treeNode.name?.toLowerCase().includes(inputValue.toLowerCase())
              "
            />
          </Form.Item>

          <Form.Item label="站点地址">
            <Input v-model:value="formState.address" placeholder="点击地图自动填充或手动输入" />
          </Form.Item>

          <Form.Item label="经纬度" required>
            <div class="text-sm text-gray-500">
              请在左侧地图上点击选择站点位置
            </div>
            <div v-if="formState.longitude && formState.latitude" class="text-xs text-gray-400 mt-1">
              经度: {{ formState.longitude?.toFixed(6) }}, 纬度: {{ formState.latitude?.toFixed(6) }}
            </div>
          </Form.Item>

          <Form.Item label="圆形半径（米）" required>
            <Slider
              v-model:value="formState.radiusMeters"
              :min="500"
              :max="10000"
              :step="500"
              :marks="{
                500: '500m',
                3000: '3km',
                5000: '5km',
                10000: '10km'
              }"
            />
            <InputNumber
              v-model:value="formState.radiusMeters"
              :min="500"
              :max="10000"
              :step="500"
              class="w-full mt-2"
              placeholder="请输入半径"
            />
          </Form.Item>

          <Form.Item label="惠农人员工号">
            <Input v-model:value="formState.specialistEmployeeCode" placeholder="请输入惠农人员工号" />
          </Form.Item>

          <Form.Item label="惠农人员姓名">
            <Input v-model:value="formState.specialistName" placeholder="请输入惠农人员姓名" />
          </Form.Item>
        </Form>
      </div>
    </div>
  </Modal>
</template>
