<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';

import { ref, computed, watch, onMounted } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { IconifyIcon } from '@vben/icons';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerIdentityPage, updateCustomerIdentity } from '#/api/aicrm/customeridentity';
import { maskIdNumber } from '#/views/aicrm/utils/identity';
import IdentityCardView from '../components/IdentityCardView.vue';

defineOptions({
  name: 'RetailCustomerIdentityList',
});

// 接收 props
const props = defineProps<{
  customerId: number;
}>();

// 视图模式: 'table' | 'card'
type ViewMode = 'table' | 'card';
const STORAGE_KEY = 'identity-list-view-mode';

// 从本地存储读取视图模式偏好
const viewMode = ref<ViewMode>(
  (localStorage.getItem(STORAGE_KEY) as ViewMode) || 'table'
);

// 卡片视图数据
const cardViewData = ref<AicrmCustomerIdentityApi.CustomerIdentity[]>([]);

// 监听视图模式变化,保存到本地存储
watch(viewMode, (newMode) => {
  localStorage.setItem(STORAGE_KEY, newMode);
  if (newMode === 'card') {
    loadCardViewData();
  }
});

/** 切换视图模式 */
function toggleViewMode(mode: ViewMode) {
  viewMode.value = mode;
}

/** 加载卡片视图数据 */
async function loadCardViewData() {
  try {
    const result = await getCustomerIdentityPage({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 100, // 加载所有数据
    });
    cardViewData.value = result.list || [];
  } catch (error: any) {
    message.error(error.message || '加载数据失败');
  }
}

/** 刷新卡片视图 */
function refreshCardView() {
  loadCardViewData();
}

// 组件挂载时,如果默认是卡片视图,则加载数据
onMounted(() => {
  if (viewMode.value === 'card') {
    loadCardViewData();
  }
});

/** 设为默认证件 */
async function handleSetPrimary(row: AicrmCustomerIdentityApi.CustomerIdentity) {
  if (row.isPrimary) {
    message.info('该证件已经是默认证件');
    return;
  }

  try {
    // 调用后端接口设为默认
    await updateCustomerIdentity({
      ...row,
      isPrimary: true,
    });
    message.success('设置成功');
    gridApi.query(); // 刷新列表
  } catch (error: any) {
    message.error(error.message || '设置失败');
  }
}

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'identityNo',
        title: '证件号码',
        minWidth: 180,
        fixed: 'left',
        formatter: ({ cellValue }) => {
          return maskIdNumber(cellValue);
        },
      },
      {
        field: 'identityType',
        title: '证件类型',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return getDictLabel('aicrm_customer_identity_type', cellValue);
        },
      },
      {
        field: 'identityName',
        title: '证件名称',
        minWidth: 120,
      },
      {
        field: 'issueDate',
        title: '签发日期',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return cellValue;
        },
      },
      {
        field: 'expiryDate',
        title: '失效日期',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          // 长期有效特殊处理
          if (cellValue === '9999-12-31') return '长期有效';
          return cellValue;
        },
      },
      {
        field: 'isPrimary',
        title: '有效性',
        minWidth: 100,
        formatter: ({ cellValue, row }) => {
          // 根据失效日期判断有效性
          if (!row.expiryDate) return '未知';
          if (row.expiryDate === '9999-12-31') return '长期有效';

          const today = new Date();
          const expiryDate = new Date(row.expiryDate);
          return expiryDate >= today ? '有效' : '已失效';
        },
      },
      {
        field: 'issueAuthority',
        title: '发证机关',
        minWidth: 180,
        showOverflow: 'tooltip',
      },
      {
        field: 'updateTime',
        title: '证件更新时间',
        minWidth: 180,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return new Date(cellValue).toLocaleString('zh-CN');
        },
      },
      {
        field: 'updater',
        title: '证件更新人',
        minWidth: 120,
      },
      {
        title: '操作',
        width: 120,
        fixed: 'right',
        slots: { default: 'actions' },
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerIdentityPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerIdentityApi.CustomerIdentity>,
});

// 暴露方法供父组件调用
defineExpose({
  refresh: () => {
    if (viewMode.value === 'table') {
      gridApi.query();
    } else {
      refreshCardView();
    }
  },
});
</script>

<template>
  <div class="identity-list-container">
    <!-- 视图切换工具栏 -->
    <div class="view-toolbar">
      <div class="view-toggle-buttons">
        <a-button
          :type="viewMode === 'table' ? 'primary' : 'default'"
          @click="toggleViewMode('table')"
        >
          <template #icon>
            <IconifyIcon icon="ant-design:table-outlined" />
          </template>
          表格视图
        </a-button>
        <a-button
          :type="viewMode === 'card' ? 'primary' : 'default'"
          @click="toggleViewMode('card')"
        >
          <template #icon>
            <IconifyIcon icon="ant-design:appstore-outlined" />
          </template>
          卡片视图
        </a-button>
      </div>
    </div>

    <!-- 表格视图 -->
    <transition name="fade">
      <div v-if="viewMode === 'table'" class="table-view">
        <Grid table-title="客户证件信息">
          <template #actions="{ row }">
            <a-button
              v-if="!row.isPrimary"
              type="link"
              size="small"
              @click="handleSetPrimary(row)"
            >
              设为默认
            </a-button>
            <a-tag v-else color="green" size="small">默认证件</a-tag>
          </template>
        </Grid>
      </div>
    </transition>

    <!-- 卡片视图 -->
    <transition name="fade">
      <div v-if="viewMode === 'card'" class="card-view">
        <IdentityCardView :identities="cardViewData" @refresh="refreshCardView" />
      </div>
    </transition>
  </div>
</template>

<style scoped lang="less">
.identity-list-container {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.view-toolbar {
  padding: 16px;
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
  margin-bottom: 16px;

  .view-toggle-buttons {
    display: flex;
    gap: 8px;
  }
}

.table-view,
.card-view {
  flex: 1;
  overflow: auto;
}

// 视图切换动画
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
