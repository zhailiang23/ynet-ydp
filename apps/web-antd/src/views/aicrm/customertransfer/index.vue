<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerTransferApi } from '#/api/aicrm/customertransfer';

import { Page } from '@vben/common-ui';
import { useVbenModal } from '@vben/common-ui';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  applyForTransfer,
  cancelTransferApplication,
  getCustomerTransferApplicationPage,
} from '#/api/aicrm/customertransfer';
import { $t } from '#/locales';

import { useApplyFormSchema, useGridColumns, useGridFormSchema } from './data';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 提交申请对话框 */
const [ApplyModal, applyModalApi] = useVbenModal({
  onConfirm: async () => {
    try {
      const values = await applyModalApi.form?.validate();
      await applyForTransfer(values as AicrmCustomerTransferApi.CustomerTransferApplicationApplyReq);
      message.success('提交移交申请成功，等待审批');
      applyModalApi.close();
      handleRefresh();
    } catch (error) {
      console.error('提交移交申请失败:', error);
    }
  },
});

/** 打开提交申请对话框 */
function handleApply() {
  applyModalApi.setState({
    isOpen: true,
    title: '提交客户移交申请',
  });
}

/** 取消申请 */
function handleCancel(row: AicrmCustomerTransferApi.CustomerTransferApplication) {
  Modal.confirm({
    title: '确认取消申请',
    content: '确定要取消该移交申请吗？此操作不可恢复。',
    okText: '确认',
    cancelText: '取消',
    onOk: async () => {
      try {
        await cancelTransferApplication(row.id);
        message.success('取消申请成功');
        handleRefresh();
      } catch (error) {
        console.error('取消申请失败:', error);
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
          return await getCustomerTransferApplicationPage({
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
  } as VxeTableGridOptions<AicrmCustomerTransferApi.CustomerTransferApplication>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户移交申请管理">
      <template #toolbar-actions>
        <vxe-button content="提交移交申请" status="primary" @click="handleApply" />
      </template>

      <template #operate="{ row }">
        <TableAction
          :actions="[
            {
              icon: ACTION_ICON.DELETE,
              label: '取消申请',
              onClick: () => handleCancel(row),
              ifShow: row.processStatus === 1,
            },
          ]"
          :max-count="3"
        />
      </template>
    </Grid>

    <ApplyModal>
      <template #default="{ formApi }">
        <component :is="applyModalApi.modalComponent">
          <component
            :is="applyModalApi.formComponent"
            :api="formApi"
            :schema="useApplyFormSchema()"
          />
        </component>
      </template>
    </ApplyModal>
  </Page>
</template>
