<script lang="ts" setup>
import type { AicrmCompanyAddressApi } from '#/api/aicrm/companyaddress';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined, CheckCircleOutlined } from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyAddressPage } from '#/api/aicrm/companyaddress';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 地址数据
const addresses = ref<AicrmCompanyAddressApi.CompanyAddress[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载地址数据
async function loadAddresses() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyAddressPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      addresses.value = result.list;
    } else {
      addresses.value = [];
      message.info('暂无地址信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载地址信息失败');
    addresses.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化地址类型
function formatAddressType({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_address_type', cellValue) || cellValue;
}

// 格式化是否首选
function formatIsPrimary({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';

  if (cellValue === true || cellValue === 1) {
    return `<span style="color: #52c41a;">
      <svg viewBox="64 64 896 896" style="width: 14px; height: 14px; vertical-align: middle; fill: currentColor;">
        <path d="M512 64C264.6 64 64 264.6 64 512s200.6 448 448 448 448-200.6 448-448S759.4 64 512 64zm193.5 301.7l-210.6 292a31.8 31.8 0 01-51.7 0L318.5 484.9c-3.8-5.3 0-12.7 6.5-12.7h46.9c10.2 0 19.9 4.9 25.9 13.3l71.2 98.8 157.2-218c6-8.3 15.6-13.3 25.9-13.3H699c6.5 0 10.3 7.4 6.5 12.7z"></path>
      </svg>
      是
    </span>`;
  }
  return '<span style="color: #d9d9d9;">否</span>';
}

// 格式化来源系统
function formatSourceSystem({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_source_system', cellValue) || cellValue;
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 格式化完整地址（省市区 + 详细地址）
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

// 统计信息
const statistics = computed(() => {
  const total = addresses.value.length;
  const primary = addresses.value.filter(a => a.isPrimary).length;
  const company = addresses.value.filter(a => a.addressType === '公司地址').length;
  const home = addresses.value.filter(a => a.addressType === '家庭地址').length;
  const other = addresses.value.filter(a => a.addressType === '其他地址').length;

  return { total, primary, company, home, other };
});

// 组件挂载时加载数据
onMounted(() => {
  loadAddresses();
});
</script>

<template>
  <div class="address-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadAddresses">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="addresses.length > 0" class="statistics-bar">
        <a-space :size="24">
          <span>
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>首选:</strong>
            <Tag color="success">{{ statistics.primary }}</Tag>
          </span>
          <span>
            <strong>公司地址:</strong>
            <Tag color="processing">{{ statistics.company }}</Tag>
          </span>
          <span>
            <strong>家庭地址:</strong>
            <Tag color="warning">{{ statistics.home }}</Tag>
          </span>
          <span>
            <strong>其他地址:</strong>
            <Tag>{{ statistics.other }}</Tag>
          </span>
        </a-space>
      </div>

      <!-- 地址列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="addresses"
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
          field="addressType"
          title="地址类型"
          width="120"
          :formatter="formatAddressType"
          sortable
        />
        <vxe-column
          field="isPrimary"
          title="是否首选"
          width="100"
          align="center"
          :formatter="formatIsPrimary"
          sortable
        />
        <vxe-column
          title="完整地址"
          min-width="300"
          show-overflow
          :formatter="formatFullAddress"
        />
        <vxe-column
          field="addressDetail"
          title="详细地址"
          min-width="250"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="postalCode"
          title="邮编"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="provinceCode"
          title="省份"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="cityCode"
          title="城市"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="countyCode"
          title="区县"
          width="100"
          :formatter="formatField"
        />
        <vxe-column
          field="streetName"
          title="街道"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="sourceSystem"
          title="来源系统"
          width="120"
          :formatter="formatSourceSystem"
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
        v-if="!loading && addresses.length === 0"
        description="暂无地址信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.address-info-container {
  height: 100%;
}

.address-info-container :deep(.ant-card-body) {
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
.address-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.address-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.address-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .address-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .address-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}
</style>
