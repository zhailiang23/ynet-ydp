<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyProjectApi } from '#/api/aicrm/companyproject';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { ProjectOutlined, DollarOutlined, RiseOutlined } from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyProjectPage } from '#/api/aicrm/companyproject';

defineOptions({
  name: 'CompanyCustomerProjectInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

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
  return `${label}`;
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
    return `${status.label}`;
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
    return `${status.label}`;
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
  return `${cellValue}风险`;
}

// 格式化是否重点项目
function formatIsKeyProject({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined || cellValue === 0) {
    return '-';
  }

  const keyMap: Record<number, { label: string; color: string }> = {
    1: { label: '市级重点', color: '#1890ff' },
    2: { label: '省级重点', color: '#722ed1' },
    3: { label: '国家级重点', color: '#ff4d4f' },
  };

  const key = keyMap[cellValue];
  if (key) {
    return `⭐ ${key.label}`;
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
const pageTitle = computed(() => props.title || '客户项目信息');

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
        field: 'projectCode',
        title: '项目编号',
        width: 140,
        fixed: 'left',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'projectName',
        title: '项目名称',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'projectType',
        title: '项目类型',
        width: 120,
        formatter: formatProjectType,
        sortable: true,
      },
      {
        field: 'projectStatus',
        title: '项目状态',
        width: 110,
        align: 'center',
        formatter: formatProjectStatus,
        sortable: true,
      },
      {
        field: 'progressRate',
        title: '项目进度',
        width: 110,
        align: 'right',
        formatter: formatPercent,
        sortable: true,
      },
      {
        field: 'isKeyProject',
        title: '重点项目',
        width: 120,
        align: 'center',
        formatter: formatIsKeyProject,
      },
      {
        field: 'totalInvestment',
        title: '总投资额',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'accumulatedInvestment',
        title: '累计完成投资',
        width: 140,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'financingRequirement',
        title: '融资需求',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'financingStatus',
        title: '融资状态',
        width: 110,
        align: 'center',
        formatter: formatFinancingStatus,
        sortable: true,
      },
      {
        field: 'ourBankFinancing',
        title: '我行融资',
        width: 130,
        align: 'right',
        formatter: formatAmount,
        sortable: true,
      },
      {
        field: 'otherBankFinancing',
        title: '他行融资',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'projectProvince',
        title: '项目省份',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'projectCity',
        title: '项目城市',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'projectAddress',
        title: '项目地址',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'planStartDate',
        title: '计划开工日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'actualStartDate',
        title: '实际开工日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'planCompleteDate',
        title: '计划完工日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'actualCompleteDate',
        title: '实际完工日期',
        width: 120,
        formatter: formatDate,
        sortable: true,
      },
      {
        field: 'constructionPeriod',
        title: '建设周期(月)',
        width: 120,
        align: 'right',
        formatter: formatField,
      },
      {
        field: 'selfFunding',
        title: '自有资金',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'bankLoan',
        title: '银行贷款',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'bondFinancing',
        title: '债券融资',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'equityFinancing',
        title: '股权融资',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'governmentSubsidy',
        title: '政府补助',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'expectedRevenue',
        title: '预计年收入',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'expectedProfit',
        title: '预计年利润',
        width: 130,
        align: 'right',
        formatter: formatAmount,
      },
      {
        field: 'paybackPeriod',
        title: '回收期(年)',
        width: 110,
        align: 'right',
        formatter: formatField,
      },
      {
        field: 'irrRate',
        title: '内部收益率',
        width: 120,
        align: 'right',
        formatter: formatPercent,
      },
      {
        field: 'riskLevel',
        title: '风险等级',
        width: 110,
        align: 'center',
        formatter: formatRiskLevel,
      },
      {
        field: 'riskFactors',
        title: '主要风险因素',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'riskMitigation',
        title: '风险缓释措施',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'partners',
        title: '合作方',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'contractor',
        title: '总承包商',
        width: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'projectManager',
        title: '项目负责人',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'managerPhone',
        title: '负责人电话',
        width: 130,
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
        field: 'marketingOpportunity',
        title: '营销机会点',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'followUpPlan',
        title: '跟进计划',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
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
          return await getCompanyProjectPage({
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
  } as VxeTableGridOptions<AicrmCompanyProjectApi.CompanyProject>,
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
