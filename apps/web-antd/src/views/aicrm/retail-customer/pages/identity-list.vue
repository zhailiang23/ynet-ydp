<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerIdentityPage, updateCustomerIdentity } from '#/api/aicrm/customeridentity';
import { maskIdNumber } from '#/views/aicrm/utils/identity';

defineOptions({
  name: 'RetailCustomerIdentityList',
});

// 接收 props
const props = defineProps<{
  customerId: number;
}>();

/** 设为默认证件 */
async function handleSetPrimary(row: AicrmCustomerIdentityApi.CustomerIdentity) {
  if (row.isPrimary) {
    message.info('该证件已经是默认证件');
    return;
  }

  try {
    // 调用后端接口设为默认
    await updateCustomerIdentity({
      ...row,
      isPrimary: true,
    });
    message.success('设置成功');
    gridApi.query(); // 刷新列表
  } catch (error: any) {
    message.error(error.message || '设置失败');
  }
}

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
        field: 'identityNo',
        title: '证件号码',
        minWidth: 180,
        fixed: 'left',
        formatter: ({ cellValue }) => {
          return maskIdNumber(cellValue);
        },
      },
      {
        field: 'identityType',
        title: '证件类型',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return getDictLabel('aicrm_customer_identity_type', cellValue);
        },
      },
      {
        field: 'identityName',
        title: '证件名称',
        minWidth: 120,
      },
      {
        field: 'issueDate',
        title: '签发日期',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return cellValue;
        },
      },
      {
        field: 'expiryDate',
        title: '失效日期',
        minWidth: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          // 长期有效特殊处理
          if (cellValue === '9999-12-31') return '长期有效';
          return cellValue;
        },
      },
      {
        field: 'isPrimary',
        title: '有效性',
        minWidth: 100,
        formatter: ({ cellValue, row }) => {
          // 根据失效日期判断有效性
          if (!row.expiryDate) return '未知';
          if (row.expiryDate === '9999-12-31') return '长期有效';

          const today = new Date();
          const expiryDate = new Date(row.expiryDate);
          return expiryDate >= today ? '有效' : '已失效';
        },
      },
      {
        field: 'issueAuthority',
        title: '发证机关',
        minWidth: 180,
        showOverflow: 'tooltip',
      },
      {
        field: 'updateTime',
        title: '证件更新时间',
        minWidth: 180,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '';
          return new Date(cellValue).toLocaleString('zh-CN');
        },
      },
      {
        field: 'updater',
        title: '证件更新人',
        minWidth: 120,
      },
      {
        title: '操作',
        width: 120,
        fixed: 'right',
        slots: { default: 'actions' },
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerIdentityPage({
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
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerIdentityApi.CustomerIdentity>,
});

// 暴露方法供父组件调用
defineExpose({
  refresh: () => gridApi.query(),
});
</script>

<template>
  <div class="identity-list-container">
    <Grid table-title="客户证件信息">
      <template #actions="{ row }">
        <a-button
          v-if="!row.isPrimary"
          type="link"
          size="small"
          @click="handleSetPrimary(row)"
        >
          设为默认
        </a-button>
        <a-tag v-else color="green" size="small">默认证件</a-tag>
      </template>
    </Grid>
  </div>
</template>

<style scoped lang="less">
.identity-list-container {
  height: 100%;
}
</style>
