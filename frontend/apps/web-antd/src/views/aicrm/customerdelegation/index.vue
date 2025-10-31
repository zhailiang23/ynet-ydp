<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDelegationApi } from '#/api/aicrm/customerdelegation';

import { Page } from '@vben/common-ui';
import { useVbenModal } from '@vben/common-ui';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  cancelCustomerDelegation,
  createCustomerDelegation,
  endCustomerDelegation,
  getCustomerDelegationPage,
} from '#/api/aicrm/customerdelegation';
import { $t } from '#/locales';

import {
  useCreateFormSchema,
  useEndFormSchema,
  useGridColumns,
  useGridFormSchema,
} from './data';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建托管对话框 */
const [CreateModal, createModalApi] = useVbenModal({
  onConfirm: async () => {
    try {
      const values = await createModalApi.form?.validate();
      await createCustomerDelegation(values as AicrmCustomerDelegationApi.CustomerDelegationCreateReq);
      message.success('创建托管成功');
      createModalApi.close();
      handleRefresh();
    } catch (error) {
      console.error('创建托管失败:', error);
    }
  },
});

/** 结束托管对话框 */
const [EndModal, endModalApi] = useVbenModal({
  onConfirm: async () => {
    try {
      const values = await endModalApi.form?.validate();
      await endCustomerDelegation(values as AicrmCustomerDelegationApi.CustomerDelegationEndReq);
      message.success('结束托管成功');
      endModalApi.close();
      handleRefresh();
    } catch (error) {
      console.error('结束托管失败:', error);
    }
  },
});

/** 打开创建托管对话框 */
function handleCreate() {
  createModalApi.setState({
    isOpen: true,
    title: '创建客户托管',
  });
}

/** 结束托管 */
function handleEnd(row: AicrmCustomerDelegationApi.CustomerDelegation) {
  endModalApi.setState({
    isOpen: true,
    title: '结束客户托管',
  });
  setTimeout(() => {
    endModalApi.form?.setValues({ id: row.id });
  }, 100);
}

/** 取消托管 */
function handleCancel(row: AicrmCustomerDelegationApi.CustomerDelegation) {
  Modal.confirm({
    title: '确认取消托管',
    content: '确定要取消该托管记录吗？此操作不可恢复。',
    okText: '确认',
    cancelText: '取消',
    onOk: async () => {
      try {
        await cancelCustomerDelegation(row.id);
        message.success('取消托管成功');
        handleRefresh();
      } catch (error) {
        console.error('取消托管失败:', error);
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
          return await getCustomerDelegationPage({
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
  } as VxeTableGridOptions<AicrmCustomerDelegationApi.CustomerDelegation>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户托管记录管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '创建托管',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-delegation:create'],
              onClick: handleCreate,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '结束托管',
              type: 'link',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:customer-delegation:update'],
              ifShow: row.delegationStatus === 1,
              onClick: handleEnd.bind(null, row),
            },
            {
              label: '取消托管',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              danger: true,
              auth: ['aicrm:customer-delegation:delete'],
              ifShow: row.delegationStatus === 1,
              onClick: handleCancel.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>

    <!-- 创建托管对话框 -->
    <CreateModal
      :form-schema="useCreateFormSchema()"
      class="!w-[600px]"
      confirm-button-text="创建"
    />

    <!-- 结束托管对话框 -->
    <EndModal
      :form-schema="useEndFormSchema()"
      class="!w-[600px]"
      confirm-button-text="结束"
    />
  </Page>
</template>
