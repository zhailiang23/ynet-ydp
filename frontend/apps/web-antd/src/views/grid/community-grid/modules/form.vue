<script lang="ts" setup>
import type { CommunityGridApi } from '#/api/grid/community-grid';

import { computed, ref, reactive, onMounted } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, InputNumber, Radio, message } from 'ant-design-vue';
import { createCommunityGrid, getCommunityGrid, updateCommunityGrid } from '#/api/grid/community-grid';
import { useUserStore } from '@vben/stores';
import { $t } from '#/locales';
import GridMapDraw from './grid-map-draw.vue';
import { getSimpleDeptList } from '#/api/system/dept';

const emit = defineEmits(['success']);
const formData = ref<CommunityGridApi.CommunityGrid>();
const userStore = useUserStore();

// 网格边界数据
const gridBoundary = ref<string>('');

// 地图绘制组件引用
const gridMapDrawRef = ref<any>(null);

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['社区网格'])
    : $t('ui.actionTitle.create', ['社区网格']);
});

// 部门列表
const deptList = ref<any[]>([]);

// 加载部门列表
async function loadDeptList() {
  try {
    deptList.value = await getSimpleDeptList();
  } catch (error) {
    console.error('加载部门列表失败:', error);
    message.error('加载部门列表失败');
  }
}

// 组件挂载时加载部门列表
onMounted(() => {
  loadDeptList();
});

// 表单数据
const formState = reactive({
  id: undefined as number | undefined,
  gridCode: '', // 网格编号(后端自动生成,前端只读)
  gridName: '',
  orgId: undefined as number | undefined,
  managerUserId: undefined as number | undefined,
  managerUserName: '',
  teamCount: undefined as number | undefined,
  status: 'ACTIVE' as 'ACTIVE' | 'INACTIVE',
  boundaryGeometry: '',
});

// 表单验证规则
const rules = {
  gridName: [{ required: true, message: '请输入网格名称' }],
  orgId: [{ required: true, message: '请选择归属机构' }],
  teamCount: [
    { required: true, message: '请输入团队人数' },
    { type: 'number', min: 1, message: '团队人数必须大于0' },
  ],
  boundaryGeometry: [{ required: true, message: '请在地图上绘制网格边界' }],
};

// 初始化表单数据
async function initFormData(data?: CommunityGridApi.CommunityGrid) {
  if (!data || !data.id) {
    // 新增时,自动填充当前用户信息
    formState.managerUserId = Number(userStore.userInfo?.userId);
    formState.managerUserName = userStore.userInfo?.nickname || '';

    // 尝试获取用户的部门ID(如果userInfo中有deptId字段)
    const userInfo = userStore.userInfo as any;
    if (userInfo?.deptId) {
      formState.orgId = userInfo.deptId;
    }
    return;
  }

  // 编辑时,加载数据
  try {
    const result = await getCommunityGrid(data.id);
    formState.id = result.id;
    formState.gridCode = result.gridCode || '';
    formState.gridName = result.gridName || '';
    formState.orgId = result.orgId;
    formState.managerUserId = result.managerUserId;
    formState.managerUserName = result.managerUserName || '';
    formState.teamCount = result.teamCount;
    formState.status = (result.status as 'ACTIVE' | 'INACTIVE') || 'ACTIVE';

    // 设置网格边界
    if (result.boundaryGeometry) {
      formState.boundaryGeometry = result.boundaryGeometry;
      gridBoundary.value = result.boundaryGeometry;
    }
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.gridName) {
    message.error('请输入网格名称');
    return false;
  }
  if (!formState.orgId) {
    message.error('请选择归属机构');
    return false;
  }
  if (!formState.teamCount || formState.teamCount < 1) {
    message.error('请输入团队人数(必须大于0)');
    return false;
  }
  if (!formState.boundaryGeometry) {
    message.error('请在地图上绘制网格边界');
    return false;
  }

  // 构建提交数据
  const submitData: any = {
    id: formState.id,
    gridName: formState.gridName,
    orgId: formState.orgId,
    managerUserId: formState.managerUserId,
    teamCount: formState.teamCount,
    status: formState.status,
    boundaryGeometry: formState.boundaryGeometry,
    gridType: 'COMMUNITY', // 固定为社区网格类型
  };

  try {
    await (formState.id ? updateCommunityGrid(submitData) : createCommunityGrid(submitData));
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
      formState.id = undefined;
      formState.gridCode = '';
      formState.gridName = '';
      formState.orgId = undefined;
      formState.managerUserId = undefined;
      formState.managerUserName = '';
      formState.teamCount = undefined;
      formState.status = 'ACTIVE';
      formState.boundaryGeometry = '';
      gridBoundary.value = '';
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<CommunityGridApi.CommunityGrid>();
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
        <GridMapDraw
          ref="gridMapDrawRef"
          v-model="formState.boundaryGeometry"
        />
      </div>

      <!-- 右侧:表单(1/3宽度) -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <Form.Item v-if="formState.id" label="网格编号">
            <Input v-model:value="formState.gridCode" disabled placeholder="由系统自动生成" />
          </Form.Item>

          <Form.Item label="网格名称" name="gridName" :rules="rules.gridName">
            <Input v-model:value="formState.gridName" placeholder="请输入网格名称" />
          </Form.Item>

          <Form.Item label="归属机构" name="orgId" :rules="rules.orgId">
            <a-select
              v-model:value="formState.orgId"
              placeholder="请选择归属机构"
              show-search
              :filter-option="(input: string, option: any) =>
                option.name?.toLowerCase().includes(input.toLowerCase())
              "
            >
              <a-select-option
                v-for="dept in deptList"
                :key="dept.id"
                :value="dept.id"
                :name="dept.name"
              >
                {{ dept.name }}
              </a-select-option>
            </a-select>
          </Form.Item>

          <Form.Item label="责任人">
            <Input v-model:value="formState.managerUserName" disabled placeholder="当前用户" />
          </Form.Item>

          <Form.Item label="团队人数" name="teamCount" :rules="rules.teamCount">
            <InputNumber
              v-model:value="formState.teamCount"
              :min="1"
              class="w-full"
              placeholder="请输入团队人数"
            />
          </Form.Item>

          <Form.Item label="网格状态" name="status">
            <Radio.Group v-model:value="formState.status">
              <Radio value="ACTIVE">正常</Radio>
              <Radio value="INACTIVE">无效</Radio>
            </Radio.Group>
          </Form.Item>

          <Form.Item label="网格范围" name="boundaryGeometry" :rules="rules.boundaryGeometry">
            <div class="text-sm text-gray-500">
              请在左侧地图上点击【开始绘制】按钮,绘制网格边界
            </div>
          </Form.Item>
        </Form>
      </div>
    </div>
  </Modal>
</template>
