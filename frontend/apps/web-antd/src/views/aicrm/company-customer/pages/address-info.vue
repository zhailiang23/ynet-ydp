<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyAddressApi } from '#/api/aicrm/companyaddress';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { CheckCircleOutlined } from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyAddressPage } from '#/api/aicrm/companyaddress';

defineOptions({
  name: 'CompanyCustomerAddressInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

// 格式化地址类型
function formatAddressType({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_address_type', cellValue) || cellValue;
}

// 格式化是否首选
function formatIsPrimary({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  if (cellValue === true || cellValue === 1) {
    return '是';
  }
  return '否';
}

// 格式化来源系统
function formatSourceSystem({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_source_system', cellValue) || cellValue;
}

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 格式化完整地址(省市区 + 详细地址)
function formatFullAddress({ row }: any) {
  const parts = [];

  if (row.provinceCode) parts.push(row.provinceCode);
  if (row.cityCode) parts.push(row.cityCode);
  if (row.countyCode) parts.push(row.countyCode);
  if (row.addressDetail) parts.push(row.addressDetail);

  return parts.length > 0 ? parts.join('') : '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '客户地址信息');

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
        field: 'addressType',
        title: '地址类型',
        width: 120,
        formatter: formatAddressType,
        sortable: true,
      },
      {
        field: 'isPrimary',
        title: '是否首选',
        width: 100,
        align: 'center',
        formatter: formatIsPrimary,
        sortable: true,
      },
      {
        title: '完整地址',
        minWidth: 300,
        showOverflow: 'tooltip',
        formatter: formatFullAddress,
      },
      {
        field: 'addressDetail',
        title: '详细地址',
        minWidth: 250,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'postalCode',
        title: '邮编',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'provinceCode',
        title: '省份',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'cityCode',
        title: '城市',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'countyCode',
        title: '区县',
        width: 100,
        formatter: formatField,
      },
      {
        field: 'streetName',
        title: '街道',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'sourceSystem',
        title: '来源系统',
        width: 120,
        formatter: formatSourceSystem,
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
          return await getCompanyAddressPage({
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
  } as VxeTableGridOptions<AicrmCompanyAddressApi.CompanyAddress>,
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
</style>
