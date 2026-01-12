<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CrmFinancialProductApi } from '#/api/crm/product/financial';

import { ref } from 'vue';

import { Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message, Modal, Tag } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteFinancialProduct,
  deleteFinancialProductList,
  exportFinancialProductExcel,
  getFinancialProductPage,
} from '#/api/crm/product/financial';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import ProductModal from './product-modal.vue';

const [ProductFormModal, productFormModalApi] = useVbenModal({
  connectedComponent: ProductModal,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增产品 */
function handleCreate() {
  productFormModalApi.open();
}

/** 编辑产品 */
function handleEdit(row: CrmFinancialProductApi.FinancialProduct) {
  productFormModalApi.setData(row).open();
}

/** 删除产品 */
async function handleDelete(id: number) {
  Modal.confirm({
    title: '确认删除',
    content: '确定要删除该金融产品吗？',
    okText: '确认',
    cancelText: '取消',
    async onOk() {
      try {
        await deleteFinancialProduct(id);
        message.success('删除成功');
        handleRefresh();
      } catch (error) {
        message.error('删除失败');
      }
    },
  });
}

/** 导出表格 */
async function handleExport() {
  const hideLoading = message.loading({
    content: '正在导出...',
    duration: 0,
  });
  try {
    const data = await exportFinancialProductExcel(await gridApi.formApi.getValues());
    downloadFileFromBlobPart({ fileName: '金融产品.xls', source: data });
    message.success('导出成功');
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: CrmFinancialProductApi.FinancialProduct[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 批量删除 */
function handleDeleteBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要删除的产品');
    return;
  }

  Modal.confirm({
    title: '确认删除',
    content: `确定要删除选中的 ${checkedIds.value.length} 个产品吗？`,
    okText: '确认',
    cancelText: '取消',
    async onOk() {
      try {
        await deleteFinancialProductList(checkedIds.value);
        message.success('批量删除成功');
        handleRefresh();
      } catch (error) {
        message.error('批量删除失败');
      }
    },
  });
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    columns: useGridColumns(),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getFinancialProductPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            ...formValues,
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
      search: true,
    },
  } as VxeTableGridOptions<CrmFinancialProductApi.FinancialProduct>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <ProductFormModal @success="handleRefresh" />
    <Grid table-title="金融产品列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '新增',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['crm:financial-product:create'],
              onClick: handleCreate,
            },
            {
              label: '批量删除',
              type: 'default',
              icon: ACTION_ICON.DELETE,
              auth: ['crm:financial-product:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
            {
              label: '导出',
              type: 'default',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['crm:financial-product:export'],
              onClick: handleExport,
            },
          ]"
        />
      </template>

      <!-- 标签列自定义渲染 -->
      <template #tags="{ row }">
        <Tag
          v-for="tag in (row.tags || [])"
          :key="tag"
          color="blue"
          style="margin-right: 4px"
        >
          {{ tag }}
        </Tag>
      </template>

      <!-- 操作列 -->
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '编辑',
              type: 'link',
              auth: ['crm:financial-product:update'],
              onClick: () => handleEdit(row),
            },
            {
              label: '删除',
              type: 'link',
              color: 'error',
              auth: ['crm:financial-product:delete'],
              onClick: () => handleDelete(row.id!),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
