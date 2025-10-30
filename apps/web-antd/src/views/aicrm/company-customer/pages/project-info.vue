<script lang="ts" setup>
import type { AicrmCompanyProjectApi } from '#/api/aicrm/companyproject';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined, ProjectOutlined, DollarOutlined, RiseOutlined } from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyProjectPage } from '#/api/aicrm/companyproject';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 项目数据
const projects = ref<AicrmCompanyProjectApi.CompanyProject[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载项目数据
async function loadProjects() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyProjectPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      projects.value = result.list;
    } else {
      projects.value = [];
      message.info('暂无项目信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载项目信息失败');
    projects.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化项目类型
function formatProjectType({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_project_type', cellValue) || cellValue;

  // 根据项目类型返回不同的颜色
  const colorMap: Record<string, string> = {
    '基建工程': 'blue',
    '房地产开发': 'orange',
    '技术改造': 'purple',
    '研发项目': 'cyan',
    '并购重组': 'magenta',
    '境外投资': 'gold',
    '产能扩张': 'green',
    '环保治理': 'lime',
  };

  const color = colorMap[cellValue] || 'default';
  return `<span style="color: var(--ant-${color}-6);">● ${label}</span>`;
}

// 格式化项目状态
function formatProjectStatus({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const statusMap: Record<number, { label: string; color: string }> = {
    1: { label: '筹备中', color: '#13c2c2' },
    2: { label: '在建', color: '#1890ff' },
    3: { label: '已完工', color: '#52c41a' },
    4: { label: '运营中', color: '#52c41a' },
    5: { label: '暂停', color: '#faad14' },
    6: { label: '终止', color: '#ff4d4f' },
  };

  const status = statusMap[cellValue];
  if (status) {
    return `<span style="color: ${status.color};">● ${status.label}</span>`;
  }

  return cellValue;
}

// 格式化融资状态
function formatFinancingStatus({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const statusMap: Record<number, { label: string; color: string }> = {
    1: { label: '未申请', color: '#d9d9d9' },
    2: { label: '申请中', color: '#1890ff' },
    3: { label: '已批准', color: '#52c41a' },
    4: { label: '已放款', color: '#52c41a' },
    5: { label: '已拒绝', color: '#ff4d4f' },
  };

  const status = statusMap[cellValue];
  if (status) {
    return `<span style="color: ${status.color};">● ${status.label}</span>`;
  }

  return cellValue;
}

// 格式化风险等级
function formatRiskLevel({ cellValue }: any) {
  if (!cellValue) return '-';

  const riskMap: Record<string, string> = {
    '低': '#52c41a',
    '中': '#faad14',
    '高': '#ff4d4f',
  };

  const color = riskMap[cellValue] || '#1890ff';
  return `<span style="color: ${color}; font-weight: bold;">${cellValue}风险</span>`;
}

// 格式化是否重点项目
function formatIsKeyProject({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined || cellValue === 0) {
    return '<span style="color: #d9d9d9;">-</span>';
  }

  const keyMap: Record<number, { label: string; color: string }> = {
    1: { label: '市级重点', color: '#1890ff' },
    2: { label: '省级重点', color: '#722ed1' },
    3: { label: '国家级重点', color: '#ff4d4f' },
  };

  const key = keyMap[cellValue];
  if (key) {
    return `<span style="color: ${key.color}; font-weight: bold;">⭐ ${key.label}</span>`;
  }

  return cellValue;
}

// 格式化营销优先级
function formatMarketingPriority({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const priorityMap: Record<number, { label: string; color: string }> = {
    1: { label: '高', color: '#ff4d4f' },
    2: { label: '中', color: '#faad14' },
    3: { label: '低', color: '#d9d9d9' },
  };

  const priority = priorityMap[cellValue];
  if (priority) {
    return `<span style="color: ${priority.color}; font-weight: bold;">${priority.label}</span>`;
  }

  return cellValue;
}

// 格式化金额（亿元）
function formatAmount({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  const billion = cellValue / 100000000;
  return `¥${billion.toFixed(2)}亿`;
}

// 格式化百分比
function formatPercent({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  return `${Number(cellValue).toFixed(2)}%`;
}

// 格式化日期
function formatDate({ cellValue }: any) {
  if (!cellValue) return '-';
  try {
    return new Date(cellValue).toLocaleDateString('zh-CN');
  } catch {
    return cellValue;
  }
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '客户项目信息');

// 统计信息
const statistics = computed(() => {
  const total = projects.value.length;
  const inProgress = projects.value.filter(p => p.projectStatus === 2).length;
  const completed = projects.value.filter(p => p.projectStatus === 3).length;
  const operating = projects.value.filter(p => p.projectStatus === 4).length;
  const keyProjects = projects.value.filter(p => p.isKeyProject && p.isKeyProject > 0).length;
  const highPriority = projects.value.filter(p => p.marketingPriority === 1).length;

  // 计算总投资额和融资需求
  const totalInvestment = projects.value.reduce((sum, p) => sum + (p.totalInvestment || 0), 0);
  const financingRequirement = projects.value.reduce((sum, p) => sum + (p.financingRequirement || 0), 0);
  const ourBankFinancing = projects.value.reduce((sum, p) => sum + (p.ourBankFinancing || 0), 0);

  // 计算平均进度
  const validProgress = projects.value.filter(p => p.progressRate !== null && p.progressRate !== undefined);
  const avgProgress = validProgress.length > 0
    ? validProgress.reduce((sum, p) => sum + p.progressRate, 0) / validProgress.length
    : 0;

  return {
    total,
    inProgress,
    completed,
    operating,
    keyProjects,
    highPriority,
    totalInvestment,
    financingRequirement,
    ourBankFinancing,
    avgProgress,
  };
});

// 组件挂载时加载数据
onMounted(() => {
  loadProjects();
});
</script>

<template>
  <div class="project-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadProjects">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="projects.length > 0" class="statistics-bar">
        <a-space :size="16" wrap>
          <span>
            <ProjectOutlined style="color: #1890ff" />
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>在建:</strong>
            <Tag color="processing">{{ statistics.inProgress }}</Tag>
          </span>
          <span>
            <strong>已完工:</strong>
            <Tag color="success">{{ statistics.completed }}</Tag>
          </span>
          <span>
            <strong>运营中:</strong>
            <Tag color="success">{{ statistics.operating }}</Tag>
          </span>
          <span v-if="statistics.keyProjects > 0">
            <strong>重点项目:</strong>
            <Tag color="error">⭐ {{ statistics.keyProjects }}</Tag>
          </span>
          <span v-if="statistics.highPriority > 0">
            <strong>高优先级:</strong>
            <Tag color="error">{{ statistics.highPriority }}</Tag>
          </span>
          <span>
            <DollarOutlined style="color: #722ed1" />
            <strong>总投资:</strong>
            <Tag color="purple">{{ formatAmount({ cellValue: statistics.totalInvestment }) }}</Tag>
          </span>
          <span>
            <strong>融资需求:</strong>
            <Tag color="orange">{{ formatAmount({ cellValue: statistics.financingRequirement }) }}</Tag>
          </span>
          <span>
            <strong>我行融资:</strong>
            <Tag color="cyan">{{ formatAmount({ cellValue: statistics.ourBankFinancing }) }}</Tag>
          </span>
          <span>
            <RiseOutlined style="color: #52c41a" />
            <strong>平均进度:</strong>
            <Tag color="success">{{ statistics.avgProgress.toFixed(2) }}%</Tag>
          </span>
        </a-space>
      </div>

      <!-- 项目列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="projects"
        :loading="loading"
        :row-config="{ isHover: true }"
        border
        stripe
        show-overflow
        height="auto"
        max-height="calc(100vh - 400px)"
        :sort-config="{ multiple: true }"
      >
        <vxe-column
          field="projectCode"
          title="项目编号"
          width="140"
          :formatter="formatField"
          sortable
          fixed="left"
        />
        <vxe-column
          field="projectName"
          title="项目名称"
          min-width="200"
          show-overflow
          :formatter="formatField"
          sortable
          fixed="left"
        />
        <vxe-column
          field="projectType"
          title="项目类型"
          width="120"
          :formatter="formatProjectType"
          sortable
        />
        <vxe-column
          field="projectStatus"
          title="项目状态"
          width="110"
          align="center"
          :formatter="formatProjectStatus"
          sortable
        />
        <vxe-column
          field="progressRate"
          title="项目进度"
          width="110"
          align="right"
          :formatter="formatPercent"
          sortable
        />
        <vxe-column
          field="isKeyProject"
          title="重点项目"
          width="120"
          align="center"
          :formatter="formatIsKeyProject"
        />
        <vxe-column
          field="totalInvestment"
          title="总投资额"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="accumulatedInvestment"
          title="累计完成投资"
          width="140"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="financingRequirement"
          title="融资需求"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="financingStatus"
          title="融资状态"
          width="110"
          align="center"
          :formatter="formatFinancingStatus"
          sortable
        />
        <vxe-column
          field="ourBankFinancing"
          title="我行融资"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="otherBankFinancing"
          title="他行融资"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="projectProvince"
          title="项目省份"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="projectCity"
          title="项目城市"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="projectAddress"
          title="项目地址"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="planStartDate"
          title="计划开工日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="actualStartDate"
          title="实际开工日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="planCompleteDate"
          title="计划完工日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="actualCompleteDate"
          title="实际完工日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="constructionPeriod"
          title="建设周期(月)"
          width="120"
          align="right"
          :formatter="formatField"
        />
        <vxe-column
          field="selfFunding"
          title="自有资金"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="bankLoan"
          title="银行贷款"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="bondFinancing"
          title="债券融资"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="equityFinancing"
          title="股权融资"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="governmentSubsidy"
          title="政府补助"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="expectedRevenue"
          title="预计年收入"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="expectedProfit"
          title="预计年利润"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="paybackPeriod"
          title="回收期(年)"
          width="110"
          align="right"
          :formatter="formatField"
        />
        <vxe-column
          field="irrRate"
          title="内部收益率"
          width="120"
          align="right"
          :formatter="formatPercent"
        />
        <vxe-column
          field="riskLevel"
          title="风险等级"
          width="110"
          align="center"
          :formatter="formatRiskLevel"
        />
        <vxe-column
          field="riskFactors"
          title="主要风险因素"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="riskMitigation"
          title="风险缓释措施"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="partners"
          title="合作方"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="contractor"
          title="总承包商"
          width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="projectManager"
          title="项目负责人"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="managerPhone"
          title="负责人电话"
          width="130"
          :formatter="formatField"
        />
        <vxe-column
          field="marketingPriority"
          title="营销优先级"
          width="110"
          align="center"
          :formatter="formatMarketingPriority"
          sortable
        />
        <vxe-column
          field="marketingOpportunity"
          title="营销机会点"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="followUpPlan"
          title="跟进计划"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="nextFollowUpDate"
          title="下次跟进日期"
          width="130"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="dataSource"
          title="数据来源"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="remark"
          title="备注"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
      </vxe-table>

      <!-- 空数据提示 -->
      <a-empty
        v-if="!loading && projects.length === 0"
        description="暂无项目信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.project-info-container {
  height: 100%;
}

.project-info-container :deep(.ant-card-body) {
  padding: 16px;
}

/* 统计信息栏 */
.statistics-bar {
  padding: 12px 16px;
  margin-bottom: 16px;
  background-color: #fafafa;
  border-radius: 4px;
}

.dark .statistics-bar {
  background-color: rgb(25 27 31);
}

/* VxeTable 样式调整 */
.project-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.project-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.project-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .project-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .project-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 数字列右对齐 */
.project-info-container :deep(.vxe-cell--right) {
  font-family: Consolas, Monaco, monospace;
  font-weight: 500;
}
</style>
