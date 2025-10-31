<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyBondApi } from '#/api/aicrm/companybond';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { SafetyCertificateOutlined, DollarOutlined } from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyBondPage } from '#/api/aicrm/companybond';

defineOptions({
  name: 'CompanyCustomerBondInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

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
  return `● ${label}`;
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
  return `${cellValue}`;
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
    return `● ${status.label}`;
  }

  return cellValue;
}

// 格式化是否可转债
function formatIsConvertible({ cellValue }: any) {
  if (cellValue === 1) {
    return '可转债';
  }
  return '-';
}

// 格式化是否绿色债券
function formatIsGreen({ cellValue }: any) {
  if (cellValue === 1) {
    return '🌱 绿色';
  }
  return '-';
}

// 格式化金额(亿元)
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

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '债券发行信息');

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
        field: 'bondCode',
        title: '债券代码',
        width: 110,
        fixed: 'left',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'bondName',
        title: '债券名称',
        minWidth: 180,
        showOverflow: 'tooltip',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'bondType',
        title: '债券类型',
        width: 120,
        formatter: formatBondType,
        sortable: true,
      },
      {
        field: 'creditRating',
        title: '信用评级',
        width: 100,
        align: 'center',
        formatter: formatCreditRating,
        sortable: true,
      },
      {
        field: 'bondStatus',
        title: '债券状态',
        width: 110,
        align: 'center',
        formatter: formatBondStatus,
        sortable: true,
      },
      {
        field: 'isConvertible',
        title: '可转债',
        width: 90,
        align: 'center',
        formatter: formatIsConvertible,
      },
      {
        field: 'isGreenBond',
        title: '绿色',
        width: 80,
        align: 'center',
        formatter: formatIsGreen,
      },
      {
        field: 'issueAmount',
        title: '发行金额',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'couponRate',
        title: '票面利率',
        width: 110,
        align: 'right',
        formatter: formatPercent,
        sortable: true,
      },
      {
        field: 'termYears',
        title: '期限(年)',
        width: 100,
        align: 'right',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'yieldToMaturity',
        title: '到期收益率',
        width: 120,
        align: 'right',
        formatter: formatPercent,
      },
      {
        field: 'currentPrice',
        title: '当前价格',
        width: 110,
        align: 'right',
        formatter: formatPrice,
      },
      {
        field: 'outstandingAmount',
        title: '未偿余额',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'issueDate',
        title: '发行日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'maturityDate',
        title: '到期日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'interestType',
        title: '计息方式',
        width: 110,
        formatter: formatField,
      },
      {
        field: 'paymentFrequency',
        title: '付息频率',
        width: 110,
        formatter: formatField,
      },
      {
        field: 'ratingAgency',
        title: '评级机构',
        width: 120,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'underwriter',
        title: '主承销商',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'listingExchange',
        title: '上市交易所',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'guaranteeType',
        title: '担保方式',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'guarantor',
        title: '担保方',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'useOfProceeds',
        title: '募集资金用途',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'specialClause',
        title: '特殊条款',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
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
          return await getCompanyBondPage({
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
  } as VxeTableGridOptions<AicrmCompanyBondApi.CompanyBond>,
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
