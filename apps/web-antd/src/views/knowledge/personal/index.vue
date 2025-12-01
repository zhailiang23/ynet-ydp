<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { KnowledgeKonwledgeFileApi } from '#/api/knowledge/file';
import type { KnowledgeBaseApi } from '#/api/knowledge/base';

import { computed, onMounted, ref } from 'vue';

import { isTenantEnable } from '@vben/hooks';
import { confirm, Page } from '@vben/common-ui';
import { useAccessStore } from '@vben/stores';
import { isEmpty } from '@vben/utils';

import { Button, message, Spin, Upload, Modal, Input, Card, Tag, Empty } from 'ant-design-vue';
import { UploadOutlined, SearchOutlined } from '@ant-design/icons-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteKonwledgeFile,
  deleteKonwledgeFileList,
  getKonwledgeFilePage,
  retrieveKnowledgeChunks,
} from '#/api/knowledge/file';
import { getOrCreatePersonalBase } from '#/api/knowledge/base';
import { $t } from '#/locales';

const accessStore = useAccessStore();
const tenantEnable = isTenantEnable();

const baseData = ref<KnowledgeBaseApi.Base | null>(null);
const loading = ref(true);

const baseId = computed(() => baseData.value?.id);
const baseName = computed(() => baseData.value?.name || '个人知识库');

/** Upload headers */
const uploadHeaders = computed(() => {
  const headers: Record<string, string> = {};

  // 添加认证token
  if (accessStore.accessToken) {
    headers.Authorization = `Bearer ${accessStore.accessToken}`;
  }

  // 添加租户ID
  if (tenantEnable && accessStore.tenantId) {
    headers['tenant-id'] = accessStore.tenantId;
  }

  return headers;
});

/** 初始化个人知识库 */
async function initPersonalBase() {
  try {
    loading.value = true;
    const base = await getOrCreatePersonalBase();
    baseData.value = base;
    // 刷新表格数据
    if (gridApi) {
      gridApi.query();
    }
  } catch (error) {
    message.error('初始化个人知识库失败');
    console.error(error);
  } finally {
    loading.value = false;
  }
}

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 删除文件 */
async function handleDelete(row: KnowledgeKonwledgeFileApi.KonwledgeFile) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.fileName]),
    duration: 0,
  });
  try {
    await deleteKonwledgeFile(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.fileName]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除文件 */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteKonwledgeFileList(checkedIds.value);
    checkedIds.value = [];
    message.success($t('ui.actionMessage.deleteSuccess'));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);
function handleRowCheckboxChange({
  records,
}: {
  records: KnowledgeKonwledgeFileApi.KonwledgeFile[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 文件上传 */
const uploading = ref(false);
const fileList = ref<any[]>([]);

function beforeUpload(file: File) {
  const isLt50M = file.size / 1024 / 1024 < 50;
  if (!isLt50M) {
    message.error('文件大小不能超过 50MB!');
    return false;
  }
  return true;
}

async function handleUpload(info: any) {
  if (info.file.status === 'uploading') {
    uploading.value = true;
    return;
  }
  if (info.file.status === 'done') {
    uploading.value = false;
    fileList.value = [];
    const response = info.file.response;
    if (response?.code === 0) {
      message.success(`${info.file.name} 文件上传成功`);
      handleRefresh();
    } else {
      message.error(response?.msg || `${info.file.name} 文件上传失败`);
    }
  } else if (info.file.status === 'error') {
    uploading.value = false;
    fileList.value = [];
    message.error(`${info.file.name} 文件上传失败`);
  }
}

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'checkbox', width: 60 },
      { field: 'fileName', title: '文件名称' },
      { field: 'fileType', title: '文件类型', width: 120 },
      {
        field: 'fileSize',
        title: '文件大小',
        width: 120,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '-';
          const size = Number(cellValue);
          if (size < 1024) return `${size} B`;
          if (size < 1024 * 1024) return `${(size / 1024).toFixed(2)} KB`;
          return `${(size / 1024 / 1024).toFixed(2)} MB`;
        },
      },
      {
        field: 'status',
        title: '状态',
        width: 100,
        formatter: ({ cellValue }) => {
          const statusMap: Record<number, string> = {
            0: '处理中',
            1: '处理完成',
            2: '处理失败',
          };
          return statusMap[cellValue as number] || '-';
        },
      },
      {
        field: 'createTime',
        title: '创建时间',
        width: 180,
        formatter: ({ cellValue }) => {
          if (!cellValue) return '-';
          const date = new Date(Number(cellValue));
          const year = date.getFullYear();
          const month = String(date.getMonth() + 1).padStart(2, '0');
          const day = String(date.getDate()).padStart(2, '0');
          const hours = String(date.getHours()).padStart(2, '0');
          const minutes = String(date.getMinutes()).padStart(2, '0');
          const seconds = String(date.getSeconds()).padStart(2, '0');
          return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        },
      },
      {
        title: '操作',
        width: 100,
        fixed: 'right',
        slots: { default: 'actions' },
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          if (!baseId.value) {
            return { list: [], total: 0 };
          }
          return await getKonwledgeFilePage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            baseId: baseId.value,
          } as any);
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
      search: false,
    },
  } as VxeTableGridOptions<KnowledgeKonwledgeFileApi.KonwledgeFile>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});

// 组件挂载时初始化个人知识库
onMounted(() => {
  initPersonalBase();
});

/** 召回测试 */
const retrieveModalVisible = ref(false);
const retrieveQuestion = ref('');
const retrieving = ref(false);
const retrieveResults = ref<KnowledgeKonwledgeFileApi.RagChunk[]>([]);

function handleOpenRetrieveModal() {
  if (!baseId.value) {
    message.error('知识库未初始化');
    return;
  }
  retrieveModalVisible.value = true;
  retrieveQuestion.value = '';
  retrieveResults.value = [];
}

async function handleRetrieve() {
  if (!retrieveQuestion.value.trim()) {
    message.error('请输入问题');
    return;
  }
  if (!baseId.value) {
    message.error('知识库未初始化');
    return;
  }

  try {
    retrieving.value = true;
    const result = await retrieveKnowledgeChunks({
      question: retrieveQuestion.value,
      kbIds: [baseId.value],
      topK: 5,
    });

    if (result.success) {
      retrieveResults.value = result.results || [];
      if (retrieveResults.value.length === 0) {
        message.info('未找到相关内容');
      } else {
        message.success(`成功召回 ${retrieveResults.value.length} 个文本分片`);
      }
    } else {
      message.error(result.message || '召回失败');
      retrieveResults.value = [];
    }
  } catch (error: any) {
    message.error(error?.message || '召回失败');
    retrieveResults.value = [];
  } finally {
    retrieving.value = false;
  }
}

function handleCloseRetrieveModal() {
  retrieveModalVisible.value = false;
  retrieveQuestion.value = '';
  retrieveResults.value = [];
}

// 注册组件
const ASpin = Spin;
const AButton = Button;
const AModal = Modal;
const AInput = Input;
const ATextarea = Input.TextArea;
const ACard = Card;
const ATag = Tag;
const AEmpty = Empty;
</script>

<template>
  <Page auto-content-height>
    <div v-if="loading" class="flex h-full items-center justify-center">
      <ASpin size="large" tip="正在加载..." />
    </div>
    <div v-else class="flex flex-col h-full">
      <div class="mb-4 flex items-center justify-between">
        <div class="text-lg font-semibold">
          {{ baseName }} - 文件管理
        </div>
        <div class="flex gap-2">
          <Upload
            v-model:file-list="fileList"
            name="file"
            :action="`/admin-api/knowledge/konwledge-file/upload?baseId=${baseId}`"
            :headers="uploadHeaders"
            :show-upload-list="false"
            :before-upload="beforeUpload"
            @change="handleUpload"
          >
            <AButton type="primary" :loading="uploading">
              <UploadOutlined />
              上传文件
            </AButton>
          </Upload>
          <AButton @click="handleOpenRetrieveModal">
            <SearchOutlined />
            召回测试
          </AButton>
          <AButton
            type="primary"
            danger
            :disabled="isEmpty(checkedIds)"
            @click="handleDeleteBatch"
          >
            <template #icon>
              <component :is="ACTION_ICON.DELETE" />
            </template>
            批量删除
          </AButton>
        </div>
      </div>
      <div class="flex-1 overflow-hidden">
        <Grid>
          <template #actions="{ row }">
            <TableAction
              :actions="[
                {
                  label: $t('common.delete'),
                  type: 'link',
                  danger: true,
                  icon: ACTION_ICON.DELETE,
                  popConfirm: {
                    title: $t('ui.actionMessage.deleteConfirm', [row.fileName]),
                    confirm: handleDelete.bind(null, row),
                  },
                },
              ]"
            />
          </template>
        </Grid>
      </div>
    </div>

    <!-- 召回测试对话框 -->
    <AModal
      v-model:open="retrieveModalVisible"
      title="召回测试"
      width="900px"
      :footer="null"
      @cancel="handleCloseRetrieveModal"
    >
      <div class="space-y-4">
        <div>
          <label class="block text-sm font-medium mb-2">输入问题</label>
          <ATextarea
            v-model:value="retrieveQuestion"
            placeholder="请输入要测试的问题..."
            :rows="3"
            :disabled="retrieving"
          />
        </div>
        <div class="flex justify-end">
          <AButton
            type="primary"
            :loading="retrieving"
            :disabled="!retrieveQuestion.trim()"
            @click="handleRetrieve"
          >
            <SearchOutlined v-if="!retrieving" />
            开始召回
          </AButton>
        </div>
        <div v-if="retrieveResults.length > 0" class="space-y-3">
          <div class="text-sm font-medium">
            召回结果 (共 {{ retrieveResults.length }} 条)
          </div>
          <div class="max-h-96 overflow-y-auto space-y-2">
            <ACard
              v-for="(chunk, index) in retrieveResults"
              :key="index"
              size="small"
              class="hover:shadow-md transition-shadow"
            >
              <div class="space-y-2">
                <div class="flex items-center justify-between">
                  <div class="flex items-center gap-2">
                    <ATag color="blue">文件 ID: {{ chunk.fileId }}</ATag>
                    <ATag color="green">分片 {{ chunk.chunkIdx }}</ATag>
                    <ATag color="orange">相似度: {{ (chunk.score * 100).toFixed(2) }}%</ATag>
                  </div>
                </div>
                <div class="text-sm text-gray-700 dark:text-gray-300 whitespace-pre-wrap">
                  {{ chunk.text }}
                </div>
              </div>
            </ACard>
          </div>
        </div>
        <div v-else-if="!retrieving && retrieveQuestion" class="py-8">
          <AEmpty description="未找到相关内容" />
        </div>
      </div>
    </AModal>
  </Page>
</template>
