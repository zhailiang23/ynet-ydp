<script lang="ts" setup>
import type { AicrmCompanyOtherBankApi } from '#/api/aicrm/companyotherbank';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined, BankOutlined, DollarOutlined, StarOutlined } from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyOtherBankPage } from '#/api/aicrm/companyotherbank';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 他行信息数据
const otherBanks = ref<AicrmCompanyOtherBankApi.CompanyOtherBank[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载他行信息数据
async function loadOtherBanks() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyOtherBankPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      otherBanks.value = result.list;
    } else {
      otherBanks.value = [];
      message.info('暂无他行信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载他行信息失败');
    otherBanks.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化银行类型
function formatBankType({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_bank_type', cellValue) || cellValue;

  // 根据银行类型返回不同的颜色
  const colorMap: Record<string, string> = {
    '国有大行': 'red',
    '股份制银行': 'blue',
    '城商行': 'green',
    '农商行': 'orange',
    '外资银行': 'purple',
    '政策性银行': 'cyan',
  };

  const color = colorMap[cellValue] || 'default';
  return `<span style="color: var(--ant-${color}-6);">● ${label}</span>`;
}

// 格式化合作类型
function formatCooperationType({ cellValue }: any) {
  if (!cellValue) return '-';

  const typeMap: Record<string, string> = {
    '主办行': '#ff4d4f',
    '协办行': '#1890ff',
    '一般合作': '#d9d9d9',
  };

  const color = typeMap[cellValue] || '#1890ff';
  return `<span style="color: ${color}; font-weight: bold;">${cellValue}</span>`;
}

// 格式化合作状态
function formatCooperationStatus({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const statusMap: Record<number, { label: string; color: string }> = {
    1: { label: '合作中', color: '#52c41a' },
    2: { label: '已终止', color: '#ff4d4f' },
    3: { label: '暂停合作', color: '#faad14' },
  };

  const status = statusMap[cellValue];
  if (status) {
    return `<span style="color: ${status.color};">● ${status.label}</span>`;
  }

  return cellValue;
}

// 格式化是否主结算行
function formatIsMainSettlement({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined || cellValue === 0) {
    return '<span style="color: #d9d9d9;">否</span>';
  }

  return `<span style="color: #ff4d4f; font-weight: bold;">⭐ 主结算行</span>`;
}

// 格式化是否有结算账户
function formatHasSettlement({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #52c41a;">✓ 有</span>';
  }
  return '<span style="color: #d9d9d9;">无</span>';
}

// 格式化服务满意度
function formatSatisfaction({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const stars = '⭐'.repeat(cellValue);
  let color = '#d9d9d9';
  if (cellValue >= 4) color = '#52c41a';
  else if (cellValue >= 3) color = '#faad14';
  else color = '#ff4d4f';

  return `<span style="color: ${color};">${stars} ${cellValue}星</span>`;
}

// 格式化价格水平
function formatPricingLevel({ cellValue }: any) {
  if (!cellValue) return '-';

  const priceMap: Record<string, string> = {
    '优惠': '#52c41a',
    '市场价': '#1890ff',
    '偏高': '#ff4d4f',
  };

  const color = priceMap[cellValue] || '#1890ff';
  return `<span style="color: ${color};">${cellValue}</span>`;
}

// 格式化响应速度
function formatResponseSpeed({ cellValue }: any) {
  if (!cellValue) return '-';

  const speedMap: Record<string, string> = {
    '快': '#52c41a',
    '一般': '#faad14',
    '慢': '#ff4d4f',
  };

  const color = speedMap[cellValue] || '#1890ff';
  return `<span style="color: ${color};">${cellValue}</span>`;
}

// 格式化信息可靠性
function formatReliability({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const reliabilityMap: Record<number, { label: string; color: string }> = {
    1: { label: '高', color: '#52c41a' },
    2: { label: '中', color: '#faad14' },
    3: { label: '低', color: '#ff4d4f' },
  };

  const reliability = reliabilityMap[cellValue];
  if (reliability) {
    return `<span style="color: ${reliability.color};">${reliability.label}</span>`;
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

// 格式化是否即将到期
function formatIsDueSoon({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #ff4d4f; font-weight: bold;">⚠️ 即将到期</span>';
  }
  return '<span style="color: #d9d9d9;">-</span>';
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
const pageTitle = computed(() => props.title || '客户他行信息');

// 统计信息
const statistics = computed(() => {
  const total = otherBanks.value.length;
  const cooperating = otherBanks.value.filter(b => b.cooperationStatus === 1).length;
  const mainSettlement = otherBanks.value.filter(b => b.isMainSettlementBank === 1).length;
  const hasSettlement = otherBanks.value.filter(b => b.hasSettlementAccount === 1).length;
  const mainCooperation = otherBanks.value.filter(b => b.cooperationType === '主办行').length;
  const dueSoon = otherBanks.value.filter(b => b.isDueSoon === 1).length;
  const highPriority = otherBanks.value.filter(b => b.marketingPriority === 1).length;

  // 计算总额度和余额
  const totalCreditLimit = otherBanks.value.reduce((sum, b) => sum + (b.totalCreditLimit || 0), 0);
  const loanBalance = otherBanks.value.reduce((sum, b) => sum + (b.loanBalance || 0), 0);
  const depositBalance = otherBanks.value.reduce((sum, b) => sum + (b.depositBalance || 0), 0);

  // 计算平均满意度
  const validSatisfaction = otherBanks.value.filter(b => b.serviceSatisfaction !== null && b.serviceSatisfaction !== undefined);
  const avgSatisfaction = validSatisfaction.length > 0
    ? validSatisfaction.reduce((sum, b) => sum + b.serviceSatisfaction, 0) / validSatisfaction.length
    : 0;

  return {
    total,
    cooperating,
    mainSettlement,
    hasSettlement,
    mainCooperation,
    dueSoon,
    highPriority,
    totalCreditLimit,
    loanBalance,
    depositBalance,
    avgSatisfaction,
  };
});

// 组件挂载时加载数据
onMounted(() => {
  loadOtherBanks();
});
</script>

<template>
  <div class="other-bank-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadOtherBanks">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="otherBanks.length > 0" class="statistics-bar">
        <a-space :size="16" wrap>
          <span>
            <BankOutlined style="color: #1890ff" />
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>合作中:</strong>
            <Tag color="success">{{ statistics.cooperating }}</Tag>
          </span>
          <span v-if="statistics.mainSettlement > 0">
            <strong>主结算行:</strong>
            <Tag color="error">⭐ {{ statistics.mainSettlement }}</Tag>
          </span>
          <span>
            <strong>有结算账户:</strong>
            <Tag color="processing">{{ statistics.hasSettlement }}</Tag>
          </span>
          <span v-if="statistics.mainCooperation > 0">
            <strong>主办行:</strong>
            <Tag color="error">{{ statistics.mainCooperation }}</Tag>
          </span>
          <span v-if="statistics.dueSoon > 0">
            <strong>即将到期:</strong>
            <Tag color="warning">⚠️ {{ statistics.dueSoon }}</Tag>
          </span>
          <span v-if="statistics.highPriority > 0">
            <strong>高优先级:</strong>
            <Tag color="error">{{ statistics.highPriority }}</Tag>
          </span>
          <span>
            <DollarOutlined style="color: #722ed1" />
            <strong>总授信额度:</strong>
            <Tag color="purple">{{ formatAmount({ cellValue: statistics.totalCreditLimit }) }}</Tag>
          </span>
          <span>
            <strong>贷款余额:</strong>
            <Tag color="orange">{{ formatAmount({ cellValue: statistics.loanBalance }) }}</Tag>
          </span>
          <span>
            <strong>存款余额:</strong>
            <Tag color="cyan">{{ formatAmount({ cellValue: statistics.depositBalance }) }}</Tag>
          </span>
          <span>
            <StarOutlined style="color: #faad14" />
            <strong>平均满意度:</strong>
            <Tag color="gold">{{ statistics.avgSatisfaction.toFixed(1) }}星</Tag>
          </span>
        </a-space>
      </div>

      <!-- 他行信息列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="otherBanks"
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
          field="bankName"
          title="银行名称"
          width="150"
          :formatter="formatField"
          sortable
          fixed="left"
        />
        <vxe-column
          field="bankType"
          title="银行类型"
          width="120"
          :formatter="formatBankType"
          sortable
        />
        <vxe-column
          field="cooperationType"
          title="合作类型"
          width="110"
          align="center"
          :formatter="formatCooperationType"
          sortable
        />
        <vxe-column
          field="cooperationStatus"
          title="合作状态"
          width="110"
          align="center"
          :formatter="formatCooperationStatus"
          sortable
        />
        <vxe-column
          field="isMainSettlementBank"
          title="主结算行"
          width="110"
          align="center"
          :formatter="formatIsMainSettlement"
        />
        <vxe-column
          field="hasSettlementAccount"
          title="结算账户"
          width="100"
          align="center"
          :formatter="formatHasSettlement"
        />
        <vxe-column
          field="relationshipDuration"
          title="合作年限"
          width="100"
          align="right"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="totalCreditLimit"
          title="授信总额度"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="usedCreditLimit"
          title="已用额度"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="loanBalance"
          title="贷款余额"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="depositBalance"
          title="存款余额"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="wealthManagementBalance"
          title="理财余额"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="dailyAverageBalance"
          title="日均存款"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="mainBusiness"
          title="主要业务"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="loanProductName"
          title="贷款产品"
          width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="loanAmount"
          title="贷款金额"
          width="130"
          align="right"
          :formatter="formatAmount"
        />
        <vxe-column
          field="loanRate"
          title="贷款利率"
          width="110"
          align="right"
          :formatter="formatPercent"
        />
        <vxe-column
          field="loanStartDate"
          title="贷款起始日"
          width="120"
          :formatter="formatDate"
        />
        <vxe-column
          field="loanMaturityDate"
          title="贷款到期日"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="serviceSatisfaction"
          title="服务满意度"
          width="120"
          align="center"
          :formatter="formatSatisfaction"
          sortable
        />
        <vxe-column
          field="pricingLevel"
          title="价格水平"
          width="100"
          align="center"
          :formatter="formatPricingLevel"
        />
        <vxe-column
          field="responseSpeed"
          title="响应速度"
          width="100"
          align="center"
          :formatter="formatResponseSpeed"
        />
        <vxe-column
          field="competitorAdvantage"
          title="他行优势"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="competitorDisadvantage"
          title="他行劣势"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="ourOpportunity"
          title="我行机会点"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="competitiveStrategy"
          title="竞争策略"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="targetBusiness"
          title="目标业务"
          min-width="150"
          show-overflow
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
          field="contractExpiryDate"
          title="合同到期日"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="isDueSoon"
          title="即将到期"
          width="110"
          align="center"
          :formatter="formatIsDueSoon"
        />
        <vxe-column
          field="relationshipManager"
          title="客户经理"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="managerPhone"
          title="经理电话"
          width="130"
          :formatter="formatField"
        />
        <vxe-column
          field="branchName"
          title="开户支行"
          width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="riskWarning"
          title="风险提示"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="infoSource"
          title="信息来源"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="infoReliability"
          title="信息可靠性"
          width="110"
          align="center"
          :formatter="formatReliability"
        />
        <vxe-column
          field="lastUpdateDate"
          title="最后更新日期"
          width="130"
          :formatter="formatDate"
          sortable
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
        v-if="!loading && otherBanks.length === 0"
        description="暂无他行信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.other-bank-info-container {
  height: 100%;
}

.other-bank-info-container :deep(.ant-card-body) {
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
.other-bank-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.other-bank-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.other-bank-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .other-bank-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .other-bank-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 数字列右对齐 */
.other-bank-info-container :deep(.vxe-cell--right) {
  font-family: 'Consolas', 'Monaco', monospace;
  font-weight: 500;
}
</style>
