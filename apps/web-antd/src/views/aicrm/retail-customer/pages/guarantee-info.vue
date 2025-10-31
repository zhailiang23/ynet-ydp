<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGuaranteeMortgageApi } from '#/api/aicrm/customerguaranteemortgage';
import type { AicrmCustomerGuaranteePledgeApi } from '#/api/aicrm/customerguaranteepledge';
import type { AicrmCustomerGuarantorApi } from '#/api/aicrm/customerguarantor';

import { ref, onMounted } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerGuaranteeMortgagePage } from '#/api/aicrm/customerguaranteemortgage';
import { getCustomerGuaranteePledgePage } from '#/api/aicrm/customerguaranteepledge';
import { getCustomerGuarantorPage } from '#/api/aicrm/customerguarantor';

defineOptions({
  name: 'RetailCustomerGuaranteeInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('mortgage');

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化金额（万元）
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })} 万元`;
}

// 格式化面积
function formatArea(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN')} ㎡`;
}

// 格式化百分比
function formatPercent(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}%`;
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// ========== 抵押物列表配置 ==========
const [MortgageGrid, mortgageGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 60,
        fixed: 'left',
      },
      {
        field: 'collateralNo',
        title: '押品编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'collateralName',
        title: '押品名称',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'collateralType',
        title: '押品类型',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_mortgage_type' },
      },
      },
      {
        field: 'mortgagorName',
        title: '抵押人',
        width: 120,
      },
      {
        field: 'mortgagorType',
        title: '抵押人类型',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantor_type' },
      },
      },
      {
        field: 'relationWithBorrower',
        title: '与被担保人关系',
        width: 140,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_relation_type' },
      },
      },
      {
        field: 'guaranteeAmount',
        title: '担保金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'collateralAddress',
        title: '抵押物地址',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'collateralArea',
        title: '抵押物面积',
        width: 120,
        formatter: ({ cellValue }) => formatArea(cellValue),
        align: 'right',
      },
      {
        field: 'evaluationValue',
        title: '评估价值',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'evaluationDate',
        title: '评估日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'mortgageRate',
        title: '抵押率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'mortgageStatus',
        title: '抵押状态',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_mortgage_status' },
      },
      },
      {
        field: 'mortgageDate',
        title: '抵押登记日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'releaseDate',
        title: '解押日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'managementBranchName',
        title: '管理机构',
        width: 140,
        showOverflow: true,
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerGuaranteeMortgagePage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage>,
});

// ========== 质押物列表配置 ==========
const [PledgeGrid, pledgeGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 60,
        fixed: 'left',
      },
      {
        field: 'collateralNo',
        title: '押品编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'collateralName',
        title: '质押物名称',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'collateralType',
        title: '押品类型',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_pledge_type' },
      },
      },
      {
        field: 'pledgorName',
        title: '质押人',
        width: 120,
      },
      {
        field: 'pledgorType',
        title: '质押人类型',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantor_type' },
      },
      },
      {
        field: 'relationWithBorrower',
        title: '与被担保人关系',
        width: 140,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_relation_type' },
      },
      },
      {
        field: 'guaranteeAmount',
        title: '担保金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'collateralValue',
        title: '质押物价值',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'pledgeRate',
        title: '质押率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'pledgeStatus',
        title: '质押状态',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_pledge_status' },
      },
      },
      {
        field: 'pledgeDate',
        title: '质押登记日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'releaseDate',
        title: '解押日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'managementBranchName',
        title: '管理机构',
        width: 140,
        showOverflow: true,
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerGuaranteePledgePage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge>,
});

// ========== 担保人列表配置 ==========
const [GuarantorGrid, guarantorGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 60,
        fixed: 'left',
      },
      {
        field: 'guarantorNo',
        title: '担保人编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'guarantorName',
        title: '担保人姓名',
        width: 120,
      },
      {
        field: 'guarantorType',
        title: '担保人类型',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantor_type' },
      },
      },
      {
        field: 'relationWithBorrower',
        title: '与被担保人关系',
        width: 140,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_relation_type' },
      },
      },
      {
        field: 'contractNo',
        title: '合同号',
        width: 160,
        showOverflow: true,
      },
      {
        field: 'contractType',
        title: '合同类型',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantor_contract_type' },
      },
      },
      {
        field: 'contractStatus',
        title: '合同状态',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantor_contract_status' },
      },
      },
      {
        field: 'signDate',
        title: '签约日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'guaranteeMethod',
        title: '担保方式',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_guarantee_style' },
      },
      },
      {
        field: 'guaranteeTotalAmount',
        title: '担保总金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'usedAmount',
        title: '已用金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'availableAmount',
        title: '可用金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'currencyCode',
        title: '币种',
        width: 80,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_currency_type' },
      },
      },
      {
        field: 'businessStartDate',
        title: '业务起始日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'businessEndDate',
        title: '业务截止日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerGuarantorPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerGuarantorApi.CustomerGuarantor>,
});

// 组件挂载时加载初始 tab 数据
onMounted(() => {
  setTimeout(() => {
    mortgageGridApi.query();
  }, 100);
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    if (activeTab.value === 'mortgage') {
      mortgageGridApi.query();
    } else if (activeTab.value === 'pledge') {
      pledgeGridApi.query();
    } else if (activeTab.value === 'guarantor') {
      guarantorGridApi.query();
    }
  },
});
</script>

<template>
  <div class="guarantee-info-page">
    <a-tabs v-model:activeKey="activeTab" type="card">
      <!-- 抵押物列表 Tab -->
      <a-tab-pane key="mortgage" tab="抵押物列表">
        <MortgageGrid table-title="抵押物列表" />
      </a-tab-pane>

      <!-- 质押物列表 Tab -->
      <a-tab-pane key="pledge" tab="质押物列表">
        <PledgeGrid table-title="质押物列表" />
      </a-tab-pane>

      <!-- 担保人列表 Tab -->
      <a-tab-pane key="guarantor" tab="担保人列表">
        <GuarantorGrid table-title="担保人列表" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less">
.guarantee-info-page {
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
        background: #fff !important;
      }
    }
  }

}

// Dark 模式样式 - 必须在 scoped 块外面
.dark .guarantee-info-page {
  :deep(.ant-tabs) {
    .ant-tabs-tab {
      &.ant-tabs-tab-active {
        background: rgb(28 30 35) !important;
      }
    }
  }
}
</style>
