<script lang="ts" setup>
import type { AicrmCompanyContactApi } from '#/api/aicrm/companycontact';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import {
  ReloadOutlined,
  PhoneOutlined,
  MobileOutlined,
  MailOutlined,
  QqOutlined,
  WechatOutlined,
} from '@ant-design/icons-vue';
import { message, Tag } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyContactPage } from '#/api/aicrm/companycontact';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 联系人数据
const contacts = ref<AicrmCompanyContactApi.CompanyContact[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 加载联系人数据
async function loadContacts() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyContactPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      contacts.value = result.list;
    } else {
      contacts.value = [];
      message.info('暂无联系人信息');
    }
  } catch (error: any) {
    message.error(error.message || '加载联系人信息失败');
    contacts.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化联系方式类型
function formatContactType({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_contact_type', cellValue) || cellValue;
}

// 格式化联系方式类型（带图标）
function formatContactTypeWithIcon({ cellValue }: any) {
  if (!cellValue) return '-';

  const label = getDictLabel('aicrm_contact_type', cellValue) || cellValue;
  let icon = '';
  let color = '#1890ff';

  switch (cellValue) {
    case '手机':
      icon = '📱';
      color = '#52c41a';
      break;
    case '座机':
      icon = '☎️';
      color = '#1890ff';
      break;
    case '邮箱':
      icon = '📧';
      color = '#722ed1';
      break;
    case 'QQ':
      icon = '💬';
      color = '#13c2c2';
      break;
    case '微信':
      icon = '💚';
      color = '#52c41a';
      break;
    default:
      icon = '📞';
  }

  return `<span style="color: ${color};">${icon} ${label}</span>`;
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

// 格式化状态
function formatStatus({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_contact_status', cellValue) || cellValue;

  // 根据状态值返回不同的样式类
  if (cellValue === '有效' || cellValue === 'active') {
    return `<span style="color: #52c41a;">● ${label}</span>`;
  } else if (cellValue === '无效' || cellValue === 'inactive') {
    return `<span style="color: #ff4d4f;">● ${label}</span>`;
  }
  return label;
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 格式化联系方式（可点击）
function formatContactMethod({ cellValue, row }: any) {
  if (!cellValue) return '-';

  const type = row.contactType;
  let href = '';

  // 根据类型生成可点击的链接
  if (type === '手机' || type === '座机') {
    href = `tel:${cellValue}`;
  } else if (type === '邮箱') {
    href = `mailto:${cellValue}`;
  }

  if (href) {
    return `<a href="${href}" style="color: #1890ff; text-decoration: none;">${cellValue}</a>`;
  }

  return cellValue;
}

// 页面标题
const pageTitle = computed(() => props.title || '联系人信息');

// 统计信息
const statistics = computed(() => {
  const total = contacts.value.length;
  const primary = contacts.value.filter(c => c.isPrimary).length;
  const mobile = contacts.value.filter(c => c.contactType === '手机').length;
  const phone = contacts.value.filter(c => c.contactType === '座机').length;
  const email = contacts.value.filter(c => c.contactType === '邮箱').length;
  const qq = contacts.value.filter(c => c.contactType === 'QQ').length;
  const wechat = contacts.value.filter(c => c.contactType === '微信').length;

  return { total, primary, mobile, phone, email, qq, wechat };
});

// 组件挂载时加载数据
onMounted(() => {
  loadContacts();
});
</script>

<template>
  <div class="contact-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadContacts">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <!-- 统计信息 -->
      <div v-if="contacts.length > 0" class="statistics-bar">
        <a-space :size="20">
          <span>
            <strong>总计:</strong>
            <Tag color="blue">{{ statistics.total }}</Tag>
          </span>
          <span>
            <strong>首选:</strong>
            <Tag color="success">{{ statistics.primary }}</Tag>
          </span>
          <span>
            <MobileOutlined style="color: #52c41a" />
            <strong>手机:</strong>
            <Tag color="success">{{ statistics.mobile }}</Tag>
          </span>
          <span>
            <PhoneOutlined style="color: #1890ff" />
            <strong>座机:</strong>
            <Tag color="processing">{{ statistics.phone }}</Tag>
          </span>
          <span>
            <MailOutlined style="color: #722ed1" />
            <strong>邮箱:</strong>
            <Tag color="purple">{{ statistics.email }}</Tag>
          </span>
          <span v-if="statistics.qq > 0">
            <QqOutlined style="color: #13c2c2" />
            <strong>QQ:</strong>
            <Tag color="cyan">{{ statistics.qq }}</Tag>
          </span>
          <span v-if="statistics.wechat > 0">
            <WechatOutlined style="color: #52c41a" />
            <strong>微信:</strong>
            <Tag color="success">{{ statistics.wechat }}</Tag>
          </span>
        </a-space>
      </div>

      <!-- 联系人列表表格 -->
      <vxe-table
        ref="tableRef"
        :data="contacts"
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
          field="contactType"
          title="联系方式类型"
          width="140"
          :formatter="formatContactTypeWithIcon"
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
          field="contactPerson"
          title="联系人姓名"
          width="120"
          :formatter="formatField"
          sortable
        />
        <vxe-column
          field="contactMethod"
          title="联系方式"
          min-width="180"
          :formatter="formatContactMethod"
        />
        <vxe-column
          field="contactSeq"
          title="序号"
          width="80"
          align="center"
          :formatter="formatField"
        />
        <vxe-column
          field="contactDesc"
          title="联系方式描述"
          min-width="150"
          show-overflow
          :formatter="formatField"
        />
        <vxe-column
          field="status"
          title="状态"
          width="100"
          align="center"
          :formatter="formatStatus"
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
        v-if="!loading && contacts.length === 0"
        description="暂无联系人信息"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.contact-info-container {
  height: 100%;
}

.contact-info-container :deep(.ant-card-body) {
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
.contact-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.contact-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.contact-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .contact-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .contact-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 联系方式链接样式 */
.contact-info-container :deep(a) {
  color: #1890ff;
  text-decoration: none;
}

.contact-info-container :deep(a:hover) {
  color: #40a9ff;
  text-decoration: underline;
}
</style>
