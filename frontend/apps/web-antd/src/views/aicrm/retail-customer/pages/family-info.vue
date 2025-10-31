<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerFamilyApi } from '#/api/aicrm/customerfamily';
import type { AicrmCustomerFamilyMemberApi } from '#/api/aicrm/customerfamilymember';

import { ref, watch, onMounted } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message, Empty } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { Descriptions } from 'ant-design-vue';

import { IconifyIcon } from '@vben/icons';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  getCustomerFamilyPage,
} from '#/api/aicrm/customerfamily';
import {
  getCustomerFamilyMemberPage
} from '#/api/aicrm/customerfamilymember';
import FamilyMemberCardView from '../components/FamilyMemberCardView.vue';

defineOptions({
  name: 'RetailCustomerFamilyInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('familyInfo');

// 家庭信息
const familyInfo = ref<AicrmCustomerFamilyApi.CustomerFamily | null>(null);

// 家庭成员相关
type ViewMode = 'table' | 'card';
const STORAGE_KEY = 'family-member-list-view-mode';
const viewMode = ref<ViewMode>(
  (localStorage.getItem(STORAGE_KEY) as ViewMode) || 'table'
);
const cardViewData = ref<AicrmCustomerFamilyMemberApi.CustomerFamilyMember[]>([]);

watch(viewMode, (newMode) => {
  localStorage.setItem(STORAGE_KEY, newMode);
  if (newMode === 'card') {
    loadCardViewData();
  }
});

function toggleView() {
  viewMode.value = viewMode.value === 'table' ? 'card' : 'table';
}

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN')} 万元`;
}

// 加载家庭信息
async function loadFamilyInfo() {
  try {
    const result = await getCustomerFamilyPage({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 1,
    });
    if (result.list && result.list.length > 0) {
      familyInfo.value = result.list[0] || null;
    } else {
      familyInfo.value = null;
    }
  } catch (error: any) {
    console.error('加载家庭信息失败:', error);
  }
}

// 加载卡片视图数据
async function loadCardViewData() {
  try {
    const result = await getCustomerFamilyMemberPage({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 100,
    });
    cardViewData.value = result.list || [];
  } catch (error: any) {
    message.error(error.message || '加载数据失败');
  }
}

// 刷新卡片视图
function refreshCardView() {
  loadCardViewData();
}

// 组件挂载
onMounted(() => {
  loadFamilyInfo();
  if (viewMode.value === 'card') {
    loadCardViewData();
  }
});

// VxeTable 配置
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
        field: 'memberName',
        title: '成员姓名',
        minWidth: 120,
        fixed: 'left',
      },
      {
        field: 'relationType',
        title: '家庭关系',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return getDictLabel('aicrm_family_relation', cellValue);
        },
      },
      {
        field: 'gender',
        title: '性别',
        minWidth: 80,
        formatter: ({ cellValue }) => {
          if (cellValue === 1) return '男';
          if (cellValue === 2) return '女';
          return '-';
        },
      },
      {
        field: 'age',
        title: '年龄',
        minWidth: 80,
      },
      {
        field: 'identityNo',
        title: '身份证号',
        minWidth: 180,
      },
      {
        field: 'educationLevel',
        title: '学历',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '-';
          return getDictLabel('aicrm_education_level', cellValue) || '-';
        },
      },
      {
        field: 'company',
        title: '工作单位',
        minWidth: 180,
        showOverflow: 'tooltip',
      },
      {
        field: 'position',
        title: '职务',
        minWidth: 120,
      },
      {
        field: 'mobile',
        title: '手机号码',
        minWidth: 150,
        formatter: ({ cellValue }) => {
          return cellValue || '-';
        },
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerFamilyMemberPage({
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
  } as VxeTableGridOptions<AicrmCustomerFamilyMemberApi.CustomerFamilyMember>,
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    loadFamilyInfo();
    if (activeTab.value === 'memberList') {
      if (viewMode.value === 'table') {
        gridApi.query();
      } else {
        refreshCardView();
      }
    }
  },
});
</script>

<template>
  <div class="family-info-page">
    <a-tabs v-model:activeKey="activeTab" type="card">
      <!-- 家庭信息 Tab -->
      <a-tab-pane key="familyInfo" tab="家庭基本信息">
        <div v-if="familyInfo" class="space-y-4">
          <!-- 卡片 1: 家庭基本信息 -->
          <a-card :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="家庭成员数">
                {{ familyInfo.familyMemberCount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="赡养人数">
                {{ familyInfo.supportMemberCount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="劳动力人数">
                {{ familyInfo.laborMemberCount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="子女数量">
                {{ familyInfo.childrenCount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="是否户主">
                {{ formatBoolean(familyInfo.isHouseHolder) }}
              </Descriptions.Item>
              <Descriptions.Item label="户主姓名">
                {{ familyInfo.houseHolderName || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭是否和睦">
                {{ formatBoolean(familyInfo.isHarmony) }}
              </Descriptions.Item>
              <Descriptions.Item label="是否有房有车">
                {{ formatBoolean(familyInfo.hasHomeCar) }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭实力">
                <DictTag type="aicrm_family_strength" :value="familyInfo.familyStrength" />
              </Descriptions.Item>
              <Descriptions.Item label="家庭住址" :span="3">
                {{ familyInfo.familyAddress || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭电话" :span="3">
                {{ familyInfo.homeTel || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 2: 收入资产信息 -->
          <a-card title="收入资产信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="家庭年收入">
                {{ formatMoney(familyInfo.familyAnnualIncome) }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭年收入范围">
                <DictTag type="aicrm_family_income_scope" :value="familyInfo.familyAnnualIncomeScope" />
              </Descriptions.Item>
              <Descriptions.Item label="主要收入来源">
                <DictTag type="aicrm_income_source" :value="familyInfo.mainIncomeSource" />
              </Descriptions.Item>
              <Descriptions.Item label="家庭年支出">
                {{ formatMoney(familyInfo.familyAnnualExpenditure) }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭年支出范围">
                <DictTag type="aicrm_family_expenditure_scope" :value="familyInfo.familyAnnualExpenditureScope" />
              </Descriptions.Item>
              <Descriptions.Item label="经营业务及规模">
                {{ familyInfo.businessAndScale || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭总资产">
                {{ formatMoney(familyInfo.familyTotalAssets) }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭负债">
                {{ formatMoney(familyInfo.familyDebt) }}
              </Descriptions.Item>
              <Descriptions.Item label="家庭负债范围">
                <DictTag type="aicrm_debt_scope" :value="familyInfo.familyDebtScope" />
              </Descriptions.Item>
              <Descriptions.Item label="家庭资产信息" :span="3">
                {{ familyInfo.familyAssetsInfo || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 3: 信用信息 -->
          <a-card title="信用信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="是否信用家庭">
                {{ formatBoolean(familyInfo.isCreditFamily) }}
              </Descriptions.Item>
              <Descriptions.Item label="信用状况">
                <DictTag type="aicrm_credit_status" :value="familyInfo.creditStatus" />
              </Descriptions.Item>
              <Descriptions.Item label="授信金额">
                {{ formatMoney(familyInfo.creditAmount) }}
              </Descriptions.Item>
              <Descriptions.Item label="负债状况">
                <DictTag type="aicrm_debt_status" :value="familyInfo.debtStatus" />
              </Descriptions.Item>
              <Descriptions.Item label="家庭信息不良记录" :span="2">
                {{ familyInfo.familyAdverseRecords || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>
        </div>
        <div v-else class="empty-state">
          <a-empty description="暂无家庭信息" />
        </div>
      </a-tab-pane>

      <!-- 家庭成员列表 Tab -->
      <a-tab-pane key="memberList" tab="家庭成员信息">
        <transition name="fade" mode="out-in">
          <!-- 表格视图 -->
          <Grid v-if="viewMode === 'table'" key="table" table-title="家庭成员列表">
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </Grid>

          <!-- 卡片视图 -->
          <div v-else key="card" class="card-view-container">
            <div class="card-view-header">
              <h3>家庭成员列表</h3>
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
              <FamilyMemberCardView :members="cardViewData" />
            </div>
          </div>
        </transition>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less">
.family-info-page {
  // 移除 padding，让内容充满整个区域

  // 去掉 tab 的边框
  :deep(.ant-tabs) {
    .ant-tabs-nav {
      margin-bottom: 0;

      &::before {
        border-bottom: none;
      }
    }

    .ant-tabs-content-holder {
      border: none;
    }

    .ant-tabs-tab {
      border: none !important;
      background: transparent !important;

      &.ant-tabs-tab-active {
        background: #fff;
      }
    }
  }
}

// Dark 模式样式 - 必须在 scoped 块外面
.dark .family-info-page {
  :deep(.ant-tabs) {
    .ant-tabs-tab {
      &.ant-tabs-tab-active {
        background: rgb(28 30 35) !important;
      }
    }
  }
}

.space-y-4 {
  display: flex;
  flex-direction: column;
  // 移除 gap，让卡片之间没有间距

  // 第一个卡片左上角改成直角
  :deep(> :first-child) {
    &.ant-card {
      border-top-left-radius: 0 !important;
    }

    .ant-card-body {
      border-top-left-radius: 0 !important;
    }

    .ant-descriptions-view {
      border-top-left-radius: 0 !important;
    }
  }
}

.empty-state {
  padding: 40px;
  text-align: center;
}

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
</style>

<style lang="less">
// Dark 模式 - card-view-container 样式
.dark {
  .card-view-container {
    background: rgb(20 22 26);
  }

  .card-view-header {
    border-bottom-color: rgba(255, 255, 255, 0.1);

    h3 {
      color: rgba(255, 255, 255, 0.85);
    }
  }

  .card-view-content {
    background: rgb(20 22 26);
  }
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
