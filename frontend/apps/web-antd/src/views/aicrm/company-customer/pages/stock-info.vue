<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyStockApi } from '#/api/aicrm/companystock';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { FundOutlined } from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyStockPage } from '#/api/aicrm/companystock';

defineOptions({
  name: 'CompanyCustomerStockInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

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
  return `● ${label}`;
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
    return `● ${status.label}`;
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
  return `${cellValue}`;
}

// 格式化金额(亿元)
function formatAmount({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  const billion = cellValue / 100000000;
  return `¥${billion.toFixed(2)}亿`;
}

// 格式化股数(万股)
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
    return 'ST';
  }
  return '-';
}

// 格式化是否*ST
function formatIsStar({ cellValue }: any) {
  if (cellValue === 1) {
    return '*ST';
  }
  return '-';
}

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '股票发行人信息');

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
        field: 'stockCode',
        title: '股票代码',
        width: 110,
        fixed: 'left',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'stockName',
        title: '股票简称',
        width: 140,
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'stockType',
        title: '股票类型',
        width: 100,
        formatter: formatStockType,
        sortable: true,
      },
      {
        field: 'listingExchange',
        title: '上市交易所',
        width: 110,
        formatter: formatListingExchange,
      },
      {
        field: 'listingStatus',
        title: '上市状态',
        width: 110,
        align: 'center',
        formatter: formatListingStatus,
        sortable: true,
      },
      {
        field: 'isSt',
        title: 'ST',
        width: 60,
        align: 'center',
        formatter: formatIsSt,
      },
      {
        field: 'isStar',
        title: '*ST',
        width: 70,
        align: 'center',
        formatter: formatIsStar,
      },
      {
        field: 'currentPrice',
        title: '当前股价',
        width: 110,
        align: 'right',
        formatter: formatPrice,
        sortable: true,
      },
      {
        field: 'marketValue',
        title: '总市值',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'circulatingMarketValue',
        title: '流通市值',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'totalShares',
        title: '总股本',
        width: 120,
        align: 'right',
        formatter: formatShares,
      },
      {
        field: 'circulatingShares',
        title: '流通股本',
        width: 120,
        align: 'right',
        formatter: formatShares,
      },
      {
        field: 'issuePrice',
        title: '发行价格',
        width: 110,
        align: 'right',
        formatter: formatPrice,
      },
      {
        field: 'issuePeRatio',
        title: '发行市盈率',
        width: 110,
        align: 'right',
        formatter: formatField,
      },
      {
        field: 'listingDate',
        title: '上市日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'industryCategory',
        title: '行业分类',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'sector',
        title: '所属板块',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'stockRating',
        title: '股票评级',
        width: 100,
        align: 'center',
        formatter: formatStockRating,
      },
      {
        field: 'priceUpdateTime',
        title: '价格更新时间',
        width: 160,
        formatter: formatDateTime,
      },
      {
        field: 'dataSource',
        title: '数据来源',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCompanyStockPage({
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
    sortConfig: {
      multiple: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCompanyStockApi.CompanyStock>,
});

// 暴露方法供父组件调用
defineExpose({
  refresh: () => {
    gridApi.query();
  },
});
</script>

<template>
  <Grid :table-title="pageTitle" />
</template>

<style scoped>
/* 统计信息栏 */
.statistics-bar {
  padding: 0 16px;
}

/* 数字列右对齐 */
:deep(.vxe-cell--right) {
  font-family: Consolas, Monaco, monospace;
  font-weight: 500;
}
</style>
