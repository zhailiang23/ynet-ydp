<script lang="ts" setup>
import type { AicrmCompanyBondApi } from '#/api/aicrm/companybond';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined, SafetyCertificateOutlined, DollarOutlined } from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyBondPage } from '#/api/aicrm/companybond';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 债券数据
const bonds = ref<AicrmCompanyBondApi.CompanyBond[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载债券数据
async function loadBonds() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyBondPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      bonds.value = result.list;
    } else {
      bonds.value = [];
      message.info('暂无债券信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载债券信息失败');
    bonds.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化债券类型
function formatBondType({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_bond_type', cellValue) || cellValue;

  // 根据债券类型返回不同的颜色
  const colorMap: Record<string, string> = {
    '国债': 'red',
    '地方政府债': 'orange',
    '金融债': 'blue',
    '企业债': 'green',
    '公司债': 'cyan',
    '中期票据': 'purple',
    '短期融资券': 'magenta',
    '可转债': 'gold',
  };

  const color = colorMap[cellValue] || 'default';
  return `<span style="color: var(--ant-${color}-6);">● ${label}</span>`;
}

// 格式化信用评级
function formatCreditRating({ cellValue }: any) {
  if (!cellValue) return '-';

  // 根据评级返回不同的颜色
  const ratingColors: Record<string, string> = {
    'AAA': '#ff4d4f',
    'AA+': '#ff7a45',
    'AA': '#ffa940',
    'AA-': '#ffc53d',
    'A+': '#52c41a',
    'A': '#73d13d',
  };

  const color = ratingColors[cellValue] || '#1890ff';
  return `<span style="color: ${color}; font-weight: bold;">${cellValue}</span>`;
}

// 格式化债券状态
function formatBondStatus({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const statusMap: Record<number, { label: string; color: string }> = {
    1: { label: '正常', color: '#52c41a' },
    2: { label: '暂停交易', color: '#faad14' },
    3: { label: '提前赎回', color: '#1890ff' },
    4: { label: '违约', color: '#ff4d4f' },
    5: { label: '已到期', color: '#d9d9d9' },
  };

  const status = statusMap[cellValue];
  if (status) {
    return `<span style="color: ${status.color};">● ${status.label}</span>`;
  }

  return cellValue;
}

// 格式化是否可转债
function formatIsConvertible({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #faad14; font-weight: bold;">可转债</span>';
  }
  return '<span style="color: #d9d9d9;">-</span>';
}

// 格式化是否绿色债券
function formatIsGreen({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #52c41a; font-weight: bold;">🌱 绿色</span>';
  }
  return '<span style="color: #d9d9d9;">-</span>';
}

// 格式化金额（亿元）
function formatAmount({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  const billion = cellValue / 100000000;
  return `¥${billion.toFixed(2)}亿`;
}

// 格式化价格
function formatPrice({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  return `¥${Number(cellValue).toFixed(4)}`;
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

// 格式化日期时间
function formatDateTime({ cellValue }: any) {
  if (!cellValue) return '-';
  try {
    return new Date(cellValue).toLocaleString('zh-CN');
  } catch {
    return cellValue;
  }
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '债券发行信息');

// 统计信息
const statistics = computed(() => {
  const total = bonds.value.length;
  const normalCount = bonds.value.filter(b => b.bondStatus === 1).length;
  const convertibleCount = bonds.value.filter(b => b.isConvertible === 1).length;
  const greenCount = bonds.value.filter(b => b.isGreenBond === 1).length;
  const expiredCount = bonds.value.filter(b => b.bondStatus === 5).length;
  const defaultCount = bonds.value.filter(b => b.bondStatus === 4).length;

  // 计算总发行金额和未偿还余额
  const totalIssueAmount = bonds.value.reduce((sum, b) => sum + (b.issueAmount || 0), 0);
  const totalOutstanding = bonds.value.reduce((sum, b) => sum + (b.outstandingAmount || 0), 0);

  // 计算平均票面利率
  const validCoupons = bonds.value.filter(b => b.couponRate !== null && b.couponRate !== undefined);
  const avgCouponRate = validCoupons.length > 0
    ? validCoupons.reduce((sum, b) => sum + b.couponRate, 0) / validCoupons.length
    : 0;

  return {
    total,
    normalCount,
    convertibleCount,
    greenCount,
    expiredCount,
    defaultCount,
    totalIssueAmount,
    totalOutstanding,
    avgCouponRate,
  };
});

// 组件挂载时加载数据
onMounted(() => {
  loadBonds();
});
</script>

<template>
  <div class="bond-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadBonds">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="bonds.length > 0" class="statistics-bar">
        <a-space :size="16" wrap>
          <span>
            <SafetyCertificateOutlined style="color: #1890ff" />
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>正常:</strong>
            <Tag color="success">{{ statistics.normalCount }}</Tag>
          </span>
          <span v-if="statistics.convertibleCount > 0">
            <strong>可转债:</strong>
            <Tag color="warning">{{ statistics.convertibleCount }}</Tag>
          </span>
          <span v-if="statistics.greenCount > 0">
            <strong>绿色债券:</strong>
            <Tag color="success">🌱 {{ statistics.greenCount }}</Tag>
          </span>
          <span v-if="statistics.expiredCount > 0">
            <strong>已到期:</strong>
            <Tag>{{ statistics.expiredCount }}</Tag>
          </span>
          <span v-if="statistics.defaultCount > 0">
            <strong>违约:</strong>
            <Tag color="error">{{ statistics.defaultCount }}</Tag>
          </span>
          <span>
            <DollarOutlined style="color: #722ed1" />
            <strong>发行总额:</strong>
            <Tag color="purple">{{ formatAmount({ cellValue: statistics.totalIssueAmount }) }}</Tag>
          </span>
          <span>
            <strong>未偿余额:</strong>
            <Tag color="cyan">{{ formatAmount({ cellValue: statistics.totalOutstanding }) }}</Tag>
          </span>
          <span>
            <strong>平均利率:</strong>
            <Tag color="orange">{{ statistics.avgCouponRate.toFixed(2) }}%</Tag>
          </span>
        </a-space>
      </div>

      <!-- 债券列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="bonds"
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
          field="bondCode"
          title="债券代码"
          width="110"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="bondName"
          title="债券名称"
          min-width="180"
          show-overflow
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="bondType"
          title="债券类型"
          width="120"
          :formatter="formatBondType"
          sortable
        />
        <vxe-column
          field="creditRating"
          title="信用评级"
          width="100"
          align="center"
          :formatter="formatCreditRating"
          sortable
        />
        <vxe-column
          field="bondStatus"
          title="债券状态"
          width="110"
          align="center"
          :formatter="formatBondStatus"
          sortable
        />
        <vxe-column
          field="isConvertible"
          title="可转债"
          width="90"
          align="center"
          :formatter="formatIsConvertible"
        />
        <vxe-column
          field="isGreenBond"
          title="绿色"
          width="80"
          align="center"
          :formatter="formatIsGreen"
        />
        <vxe-column
          field="issueAmount"
          title="发行金额"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="couponRate"
          title="票面利率"
          width="110"
          align="right"
          :formatter="formatPercent"
          sortable
        />
        <vxe-column
          field="termYears"
          title="期限(年)"
          width="100"
          align="right"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="yieldToMaturity"
          title="到期收益率"
          width="120"
          align="right"
          :formatter="formatPercent"
        />
        <vxe-column
          field="currentPrice"
          title="当前价格"
          width="110"
          align="right"
          :formatter="formatPrice"
        />
        <vxe-column
          field="outstandingAmount"
          title="未偿余额"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="issueDate"
          title="发行日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="maturityDate"
          title="到期日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="interestType"
          title="计息方式"
          width="110"
          :formatter="formatField"
        />
        <vxe-column
          field="paymentFrequency"
          title="付息频率"
          width="110"
          :formatter="formatField"
        />
        <vxe-column
          field="ratingAgency"
          title="评级机构"
          width="120"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="underwriter"
          title="主承销商"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="listingExchange"
          title="上市交易所"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="guaranteeType"
          title="担保方式"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="guarantor"
          title="担保方"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="useOfProceeds"
          title="募集资金用途"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="specialClause"
          title="特殊条款"
          min-width="200"
          show-overflow
          :formatter="formatField"
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
        v-if="!loading && bonds.length === 0"
        description="暂无债券信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.bond-info-container {
  height: 100%;
}

.bond-info-container :deep(.ant-card-body) {
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
.bond-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.bond-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.bond-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .bond-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .bond-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 数字列右对齐 */
.bond-info-container :deep(.vxe-cell--right) {
  font-family: Consolas, Monaco, monospace;
  font-weight: 500;
}
</style>
