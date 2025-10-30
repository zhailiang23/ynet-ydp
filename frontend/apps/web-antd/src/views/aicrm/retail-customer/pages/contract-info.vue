<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContractApi } from '#/api/aicrm/customercontract';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerContractPage } from '#/api/aicrm/customercontract';

defineOptions({
  name: 'RetailCustomerContractInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  if (value === '9999-12-31') return '长期有效';
  return value;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerContractApi.CustomerContract> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'contractNo',
        title: '签约编号',
        width: 160,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'contractSituation',
        title: '签约情况',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'contractStatus',
        title: '签约状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_contract_status', cellValue),
      },
      {
        field: 'contractDate',
        title: '签约日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'startDate',
        title: '生效日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'endDate',
        title: '失效日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'signChannel',
        title: '签约渠道',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_sign_channel', cellValue),
      },
      {
        field: 'branchName',
        title: '签约机构',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'accountNo',
        title: '关联账号',
        width: 160,
        showOverflow: true,
      },
      {
        field: 'identityType',
        title: '证件类型',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_identity_type', cellValue),
      },
      {
        field: 'identityNo',
        title: '证件号码',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'cancelDate',
        title: '解约日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'cancelReason',
        title: '解约原因',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'remark',
        title: '备注',
        width: 140,
        showOverflow: true,
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerContractPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    pagerConfig: {
      pageSize: 10,
      pageSizes: [10, 20, 50, 100],
    },
    toolbarConfig: {
      refresh: true,
      export: false,
      print: false,
    },
  };

const [Grid, gridApi] = useVbenVxeGrid({ gridOptions });

// 组件挂载时加载数据
onMounted(() => {
  setTimeout(() => {
    gridApi.query();
  }, 100);
});

// 监听 customerId 变化，重新加载数据
watch(
  () => props.customerId,
  () => {
    gridApi.query();
  },
);
</script>

<template>
  <div>
    <Grid table-title="客户签约信息" />
  </div>
</template>
