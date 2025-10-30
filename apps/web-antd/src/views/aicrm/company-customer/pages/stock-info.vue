<script lang="ts" setup>
import type { AicrmCompanyStockApi } from '#/api/aicrm/companystock';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined, RiseOutlined, FallOutlined, StockOutlined } from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyStockPage } from '#/api/aicrm/companystock';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 股票数据
const stocks = ref<AicrmCompanyStockApi.CompanyStock[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载股票数据
async function loadStocks() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyStockPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      stocks.value = result.list;
    } else {
      stocks.value = [];
      message.info('暂无股票信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载股票信息失败');
    stocks.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化股票类型
function formatStockType({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_stock_type', cellValue) || cellValue;

  // 根据股票类型返回不同的颜色
  const colorMap: Record<string, string> = {
    'A股': 'red',
    'B股': 'blue',
    'H股': 'green',
    '港股': 'cyan',
    '美股': 'purple',
    '科创板': 'orange',
  };

  const color = colorMap[cellValue] || 'default';
  return `<span style="color: var(--ant-${color}-6);">● ${label}</span>`;
}

// 格式化上市交易所
function formatListingExchange({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_listing_exchange', cellValue) || cellValue;
}

// 格式化上市状态
function formatListingStatus({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const statusMap: Record<number, { label: string; color: string }> = {
    1: { label: '正常上市', color: '#52c41a' },
    2: { label: '暂停上市', color: '#faad14' },
    3: { label: '终止上市', color: '#ff4d4f' },
    4: { label: '退市', color: '#d9d9d9' },
  };

  const status = statusMap[cellValue];
  if (status) {
    return `<span style="color: ${status.color};">● ${status.label}</span>`;
  }

  return getDictLabel('aicrm_listing_status', cellValue) || cellValue;
}

// 格式化股票评级
function formatStockRating({ cellValue }: any) {
  if (!cellValue) return '-';

  const ratingMap: Record<string, string> = {
    '买入': 'red',
    '增持': 'orange',
    '中性': 'blue',
    '减持': 'cyan',
    '卖出': 'default',
  };

  const color = ratingMap[cellValue] || 'default';
  return `<span style="color: var(--ant-${color}-6);">${cellValue}</span>`;
}

// 格式化金额（亿元）
function formatAmount({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  const billion = cellValue / 100000000;
  return `¥${billion.toFixed(2)}亿`;
}

// 格式化股数（万股）
function formatShares({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  const tenThousand = cellValue / 10000;
  return `${tenThousand.toFixed(2)}万`;
}

// 格式化价格
function formatPrice({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  return `¥${Number(cellValue).toFixed(2)}`;
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

// 格式化是否ST
function formatIsSt({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #faad14; font-weight: bold;">ST</span>';
  }
  return '<span style="color: #d9d9d9;">-</span>';
}

// 格式化是否*ST
function formatIsStar({ cellValue }: any) {
  if (cellValue === 1) {
    return '<span style="color: #ff4d4f; font-weight: bold;">*ST</span>';
  }
  return '<span style="color: #d9d9d9;">-</span>';
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '股票发行人信息');

// 统计信息
const statistics = computed(() => {
  const total = stocks.value.length;
  const aStock = stocks.value.filter(s => s.stockType === 'A股').length;
  const stCount = stocks.value.filter(s => s.isSt === 1).length;
  const starCount = stocks.value.filter(s => s.isStar === 1).length;
  const normalCount = stocks.value.filter(s => s.listingStatus === 1).length;

  // 计算总市值
  const totalMarketValue = stocks.value.reduce((sum, s) => sum + (s.marketValue || 0), 0);

  return { total, aStock, stCount, starCount, normalCount, totalMarketValue };
});

// 组件挂载时加载数据
onMounted(() => {
  loadStocks();
});
</script>

<template>
  <div class="stock-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadStocks">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="stocks.length > 0" class="statistics-bar">
        <a-space :size="20">
          <span>
            <StockOutlined style="color: #1890ff" />
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>A股:</strong>
            <Tag color="red">{{ statistics.aStock }}</Tag>
          </span>
          <span>
            <strong>正常上市:</strong>
            <Tag color="success">{{ statistics.normalCount }}</Tag>
          </span>
          <span v-if="statistics.stCount > 0">
            <strong>ST:</strong>
            <Tag color="warning">{{ statistics.stCount }}</Tag>
          </span>
          <span v-if="statistics.starCount > 0">
            <strong>*ST:</strong>
            <Tag color="error">{{ statistics.starCount }}</Tag>
          </span>
          <span>
            <strong>总市值:</strong>
            <Tag color="purple">{{ formatAmount({ cellValue: statistics.totalMarketValue }) }}</Tag>
          </span>
        </a-space>
      </div>

      <!-- 股票列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="stocks"
        :loading="loading"
        :row-config="{ isHover: true }"
        border
        stripe
        show-overflow
        height="auto"
        max-height="calc(100vh - 380px)"
        :sort-config="{ multiple: true }"
      >
        <vxe-column
          field="stockCode"
          title="股票代码"
          width="110"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="stockName"
          title="股票简称"
          width="140"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="stockType"
          title="股票类型"
          width="100"
          :formatter="formatStockType"
          sortable
        />
        <vxe-column
          field="listingExchange"
          title="上市交易所"
          width="110"
          :formatter="formatListingExchange"
        />
        <vxe-column
          field="listingStatus"
          title="上市状态"
          width="110"
          align="center"
          :formatter="formatListingStatus"
          sortable
        />
        <vxe-column
          field="isSt"
          title="ST"
          width="60"
          align="center"
          :formatter="formatIsSt"
        />
        <vxe-column
          field="isStar"
          title="*ST"
          width="70"
          align="center"
          :formatter="formatIsStar"
        />
        <vxe-column
          field="currentPrice"
          title="当前股价"
          width="110"
          align="right"
          :formatter="formatPrice"
          sortable
        />
        <vxe-column
          field="marketValue"
          title="总市值"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="circulatingMarketValue"
          title="流通市值"
          width="130"
          align="right"
          :formatter="formatAmount"
          sortable
        />
        <vxe-column
          field="totalShares"
          title="总股本"
          width="120"
          align="right"
          :formatter="formatShares"
        />
        <vxe-column
          field="circulatingShares"
          title="流通股本"
          width="120"
          align="right"
          :formatter="formatShares"
        />
        <vxe-column
          field="issuePrice"
          title="发行价格"
          width="110"
          align="right"
          :formatter="formatPrice"
        />
        <vxe-column
          field="issuePeRatio"
          title="发行市盈率"
          width="110"
          align="right"
          :formatter="formatField"
        />
        <vxe-column
          field="listingDate"
          title="上市日期"
          width="120"
          :formatter="formatDate"
          sortable
        />
        <vxe-column
          field="industryCategory"
          title="行业分类"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="sector"
          title="所属板块"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="stockRating"
          title="股票评级"
          width="100"
          align="center"
          :formatter="formatStockRating"
        />
        <vxe-column
          field="priceUpdateTime"
          title="价格更新时间"
          width="160"
          :formatter="formatDateTime"
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
        v-if="!loading && stocks.length === 0"
        description="暂无股票信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.stock-info-container {
  height: 100%;
}

.stock-info-container :deep(.ant-card-body) {
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
.stock-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.stock-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.stock-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .stock-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .stock-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 数字列右对齐 */
.stock-info-container :deep(.vxe-cell--right) {
  font-family: 'Consolas', 'Monaco', monospace;
  font-weight: 500;
}
</style>
