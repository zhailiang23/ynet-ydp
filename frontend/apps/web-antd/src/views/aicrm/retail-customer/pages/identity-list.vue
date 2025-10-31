<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';

import { ref, computed, watch, onMounted } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

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
function toggleView() {
  viewMode.value = viewMode.value === 'table' ? 'card' : 'table';
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
  <!-- 表格视图 -->
  <transition name="fade" mode="out-in">
    <Grid v-if="viewMode === 'table'" key="table" table-title="客户证件信息">
      <template #toolbar-tools>
        <a-tooltip title="切换到卡片视图">
          <a-button shape="circle" @click="toggleView">
            <template #icon>
              <IconifyIcon icon="ant-design:appstore-outlined" />
            </template>
          </a-button>
        </a-tooltip>
      </template>
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

    <!-- 卡片视图 -->
    <div v-else key="card" class="card-view-container">
      <div class="card-view-header">
        <h3>客户证件信息</h3>
        <div class="card-view-tools">
          <a-tooltip title="切换到表格视图">
            <a-button shape="circle" @click="toggleView">
              <template #icon>
                <IconifyIcon icon="ant-design:table-outlined" />
              </template>
            </a-button>
          </a-tooltip>
          <a-tooltip title="刷新">
            <a-button shape="circle" @click="refreshCardView">
              <template #icon>
                <IconifyIcon icon="ant-design:reload-outlined" />
              </template>
            </a-button>
          </a-tooltip>
        </div>
      </div>
      <div class="card-view-content">
        <IdentityCardView :identities="cardViewData" @refresh="refreshCardView" />
      </div>
    </div>
  </transition>
</template>

<style scoped lang="less">
// 卡片视图容器
.card-view-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  background: #fff;
}

.card-view-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #f0f0f0;

  h3 {
    margin: 0;
    font-size: 16px;
    font-weight: 600;
  }

  .card-view-tools {
    display: flex;
    gap: 8px;
  }
}

.card-view-content {
  flex: 1;
  overflow: auto;
  padding: 16px;
}

// 视图切换动画
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
