<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyContactApi } from '#/api/aicrm/companycontact';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Tag } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import {
  PhoneOutlined,
  MobileOutlined,
  MailOutlined,
  QqOutlined,
  WechatOutlined,
} from '@ant-design/icons-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyContactPage } from '#/api/aicrm/companycontact';

defineOptions({
  name: 'CompanyCustomerContactInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

// 格式化联系方式类型
function formatContactType({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_contact_type', cellValue) || cellValue;
}

// 格式化联系方式类型(带图标)
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

  return `${icon} ${label}`;
}

// 格式化是否首选
function formatIsPrimary({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  if (cellValue === true || cellValue === 1) {
    return '✓ 是';
  }
  return '否';
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
    return `● ${label}`;
  } else if (cellValue === '无效' || cellValue === 'inactive') {
    return `● ${label}`;
  }
  return label;
}

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 格式化联系方式(可点击)
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
        field: 'contactType',
        title: '联系方式类型',
        width: 140,
        formatter: formatContactTypeWithIcon,
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
        field: 'contactPerson',
        title: '联系人姓名',
        width: 120,
        formatter: formatField,
        sortable: true,
      },
      {
        field: 'contactMethod',
        title: '联系方式',
        minWidth: 180,
        formatter: formatContactMethod,
      },
      {
        field: 'contactSeq',
        title: '序号',
        width: 80,
        align: 'center',
        formatter: formatField,
      },
      {
        field: 'contactDesc',
        title: '联系方式描述',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
      {
        field: 'status',
        title: '状态',
        width: 100,
        align: 'center',
        formatter: formatStatus,
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
          return await getCompanyContactPage({
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
  } as VxeTableGridOptions<AicrmCompanyContactApi.CompanyContact>,
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
/* 联系方式链接样式 */
:deep(a) {
  color: #1890ff;
  text-decoration: none;
}

:deep(a:hover) {
  color: #40a9ff;
  text-decoration: underline;
}
</style>
