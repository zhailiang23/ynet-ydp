<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyOtherBankApi } from '#/api/aicrm/companyotherbank';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { BankOutlined, DollarOutlined, StarOutlined } from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyOtherBankPage } from '#/api/aicrm/companyotherbank';

defineOptions({
  name: 'CompanyCustomerOtherBankInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

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
  return `● ${label}`;
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
  return `${cellValue}`;
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
    return `● ${status.label}`;
  }

  return cellValue;
}

// 格式化是否主结算行
function formatIsMainSettlement({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined || cellValue === 0) {
    return '否';
  }

  return `⭐ 主结算行`;
}

// 格式化是否有结算账户
function formatHasSettlement({ cellValue }: any) {
  if (cellValue === 1) {
    return '✓ 有';
  }
  return '无';
}

// 格式化服务满意度
function formatSatisfaction({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  const stars = '⭐'.repeat(cellValue);
  let color = '#d9d9d9';
  if (cellValue >= 4) color = '#52c41a';
  else if (cellValue >= 3) color = '#faad14';
  else color = '#ff4d4f';

  return `${stars} ${cellValue}星`;
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
  return `${cellValue}`;
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
  return `${cellValue}`;
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
    return `${reliability.label}`;
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
    return `${priority.label}`;
  }

  return cellValue;
}

// 格式化金额(亿元)
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

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '他行信息');

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
        field: 'bankName',
        title: '银行名称',
        width: 150,
        fixed: 'left',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'bankType',
        title: '银行类型',
        width: 120,
        formatter: formatBankType,
        sortable: true,
      },
      {
        field: 'cooperationType',
        title: '合作类型',
        width: 110,
        align: 'center',
        formatter: formatCooperationType,
        sortable: true,
      },
      {
        field: 'cooperationStatus',
        title: '合作状态',
        width: 110,
        align: 'center',
        formatter: formatCooperationStatus,
        sortable: true,
      },
      {
        field: 'isMainSettlementBank',
        title: '主结算行',
        width: 110,
        align: 'center',
        formatter: formatIsMainSettlement,
      },
      {
        field: 'hasSettlementAccount',
        title: '结算账户',
        width: 110,
        align: 'center',
        formatter: formatHasSettlement,
      },
      {
        field: 'creditLimit',
        title: '授信额度',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'usedCreditLimit',
        title: '已用额度',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'availableCreditLimit',
        title: '可用额度',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'loanBalance',
        title: '贷款余额',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'depositBalance',
        title: '存款余额',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'cooperationStartDate',
        title: '合作开始日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'lastReviewDate',
        title: '上次评审日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'nextReviewDate',
        title: '下次评审日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'maturityDate',
        title: '授信到期日期',
        width: 130,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'accountManager',
        title: '客户经理',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'accountManagerPhone',
        title: '经理电话',
        width: 130,
        formatter: formatField,
      },
      {
        field: 'relationshipLevel',
        title: '关系等级',
        width: 110,
        align: 'center',
        formatter: formatField,
      },
      {
        field: 'serviceSatisfaction',
        title: '服务满意度',
        width: 130,
        align: 'center',
        formatter: formatSatisfaction,
        sortable: true,
      },
      {
        field: 'pricingLevel',
        title: '价格水平',
        width: 110,
        align: 'center',
        formatter: formatPricingLevel,
      },
      {
        field: 'responseSpeed',
        title: '响应速度',
        width: 110,
        align: 'center',
        formatter: formatResponseSpeed,
      },
      {
        field: 'productAdvantage',
        title: '产品优势',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'serviceAdvantage',
        title: '服务优势',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'competitiveDisadvantage',
        title: '我行劣势',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'competitiveAdvantage',
        title: '我行优势',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'marketingOpportunity',
        title: '营销机会',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'marketingPriority',
        title: '营销优先级',
        width: 110,
        align: 'center',
        formatter: formatMarketingPriority,
        sortable: true,
      },
      {
        field: 'informationSource',
        title: '信息来源',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'informationReliability',
        title: '信息可靠性',
        width: 110,
        align: 'center',
        formatter: formatReliability,
      },
      {
        field: 'informationDate',
        title: '信息日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'nextFollowUpDate',
        title: '下次跟进日期',
        width: 130,
        formatter: formatDate,
        sortable: true,
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
          return await getCompanyOtherBankPage({
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
  } as VxeTableGridOptions<AicrmCompanyOtherBankApi.CompanyOtherBank>,
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
