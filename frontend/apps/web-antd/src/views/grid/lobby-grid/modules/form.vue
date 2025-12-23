<script lang="ts" setup>
import type { LobbyGridApi } from '#/api/grid/lobby-grid';

import { computed, ref, reactive, onMounted } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, InputNumber, Slider, TreeSelect, message } from 'ant-design-vue';
import { createLobbyGrid, getLobbyGrid, updateLobbyGrid } from '#/api/grid/lobby-grid';
import { useUserStore } from '@vben/stores';
import { $t } from '#/locales';
import LobbyMapPoint from './lobby-map-point.vue';
import { getDeptList } from '#/api/system/dept';
import { handleTree } from '@vben/utils';

const emit = defineEmits(['success']);
const formData = ref<LobbyGridApi.LobbyGrid>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['厅堂网格'])
    : $t('ui.actionTitle.create', ['厅堂网格']);
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
  gridCode: '',
  orgId: undefined as number | undefined,
  managementLevel: '',
  region: '',
  district: '',
  locationName: '',
  longitude: undefined as number | undefined,
  latitude: undefined as number | undefined,
  outletType: '',
  residentCount: undefined as number | undefined,
  merchantCount: undefined as number | undefined,
  radiusMeters: 1000,  // 默认 1km
  boundaryGeometry: '',
});

// 初始化表单数据
async function initFormData(data?: LobbyGridApi.LobbyGrid) {
  if (!data || !data.id) {
    // 新增时，尝试获取用户的部门ID
    const userInfo = userStore.userInfo as any;
    if (userInfo?.deptId) {
      formState.orgId = userInfo.deptId;
    }
    return;
  }

  // 编辑时，加载数据
  try {
    const result = await getLobbyGrid(data.id);
    Object.assign(formState, result);
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.orgId) {
    message.error('请选择所属机构');
    return false;
  }
  if (!formState.locationName) {
    message.error('请输入位置名称');
    return false;
  }
  if (!formState.longitude || !formState.latitude) {
    message.error('请在地图上选择位置');
    return false;
  }

  // 构建提交数据
  const submitData: any = {
    id: formState.id,
    orgId: formState.orgId,
    managementLevel: formState.managementLevel,
    region: formState.region,
    district: formState.district,
    locationName: formState.locationName,
    longitude: formState.longitude,
    latitude: formState.latitude,
    outletType: formState.outletType,
    residentCount: formState.residentCount,
    merchantCount: formState.merchantCount,
    radiusMeters: formState.radiusMeters,
    boundaryGeometry: formState.boundaryGeometry,
  };

  try {
    await (formState.id ? updateLobbyGrid(submitData) : createLobbyGrid(submitData));
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
        gridCode: '',
        orgId: undefined,
        managementLevel: '',
        region: '',
        district: '',
        locationName: '',
        longitude: undefined,
        latitude: undefined,
        outletType: '',
        residentCount: undefined,
        merchantCount: undefined,
        radiusMeters: 1000,
        boundaryGeometry: '',
      });
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<LobbyGridApi.LobbyGrid>();
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
        <LobbyMapPoint
          v-model="formState.boundaryGeometry"
          v-model:longitude="formState.longitude"
          v-model:latitude="formState.latitude"
          v-model:location-name="formState.locationName"
          :radius-meters="formState.radiusMeters"
        />
      </div>

      <!-- 右侧:表单(1/3宽度) -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <Form.Item v-if="formState.id" label="网格编号">
            <Input v-model:value="formState.gridCode" disabled placeholder="由系统自动生成" />
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

          <Form.Item label="位置名称" required>
            <Input
              v-model:value="formState.locationName"
              placeholder="点击地图自动获取，也可手动修改"
            />
          </Form.Item>

          <Form.Item label="经纬度" required>
            <div class="text-sm text-gray-500">
              请在左侧地图上点击选择网点位置
            </div>
            <div v-if="formState.longitude && formState.latitude" class="text-xs text-gray-400 mt-1">
              经度: {{ formState.longitude?.toFixed(6) }}, 纬度: {{ formState.latitude?.toFixed(6) }}
            </div>
          </Form.Item>

          <Form.Item label="圆形半径（米）" required>
            <Slider
              v-model:value="formState.radiusMeters"
              :min="100"
              :max="5000"
              :step="100"
              :marks="{
                100: '100m',
                1000: '1km',
                2000: '2km',
                5000: '5km'
              }"
            />
            <InputNumber
              v-model:value="formState.radiusMeters"
              :min="100"
              :max="5000"
              :step="100"
              class="w-full mt-2"
              placeholder="请输入半径"
            />
          </Form.Item>

          <Form.Item label="管理层级">
            <Input v-model:value="formState.managementLevel" placeholder="请输入管理层级（如：一级）" />
          </Form.Item>

          <Form.Item label="所属区域">
            <Input v-model:value="formState.region" placeholder="请输入所属区域（如：郑州市）" />
          </Form.Item>

          <Form.Item label="行政片区">
            <Input v-model:value="formState.district" placeholder="请输入行政片区（如：金水区）" />
          </Form.Item>

          <Form.Item label="网点类型">
            <Input v-model:value="formState.outletType" placeholder="请输入网点类型（如：一级支行）" />
          </Form.Item>

          <Form.Item label="网格常住居民数">
            <InputNumber
              v-model:value="formState.residentCount"
              :min="0"
              class="w-full"
              placeholder="请输入常住居民数"
            />
          </Form.Item>

          <Form.Item label="周围商户数">
            <InputNumber
              v-model:value="formState.merchantCount"
              :min="0"
              class="w-full"
              placeholder="请输入周围商户数"
            />
          </Form.Item>
        </Form>
      </div>
    </div>
  </Modal>
</template>
