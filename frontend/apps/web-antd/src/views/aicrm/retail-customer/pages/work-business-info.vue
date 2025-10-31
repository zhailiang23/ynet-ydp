<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CustomerWorkInfoApi } from '#/api/aicrm/customerworkinfo';
import type { CustomerBusinessInfoApi } from '#/api/aicrm/customerbusinessinfo';

import { ref, computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Empty, message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerWorkInfoPageApi } from '#/api/aicrm/customerworkinfo';
import { getCustomerBusinessInfoPageApi } from '#/api/aicrm/customerbusinessinfo';

defineOptions({
  name: 'RetailCustomerWorkBusinessInfo',
  components: {
    AEmpty: Empty,
  },
});

const props = defineProps<{
  customerId: number;
}>();

// 工作信息
const workInfoData = ref<CustomerWorkInfoApi.CustomerWorkInfo[]>([]);
const workInfoLoading = ref(false);
const hasWorkInfo = computed(() => workInfoData.value.length > 0);

// 经营信息
const businessInfoData = ref<CustomerBusinessInfoApi.CustomerBusinessInfo[]>([]);
const businessInfoLoading = ref(false);
const hasBusinessInfo = computed(() => businessInfoData.value.length > 0);

// 格式化字典
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return value.toLocaleString('zh-CN', {
    style: 'currency',
    currency: 'CNY',
  });
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return new Date(value).toLocaleDateString('zh-CN');
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化字段
function formatField(value: any) {
  return value || '-';
}

// 工作信息表格配置
const [WorkInfoGrid, workInfoGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'employerName',
        title: '工作单位名称',
        width: 180,
        fixed: 'left',
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'employerType',
        title: '单位类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_employer_type', cellValue),
      },
      {
        field: 'industry',
        title: '所属行业',
        width: 150,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'position',
        title: '职位/职务',
        width: 120,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'positionLevel',
        title: '职级',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_position_level', cellValue),
      },
      {
        field: 'department',
        title: '所在部门',
        width: 120,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'workYears',
        title: '工作年限',
        width: 100,
        align: 'right',
        formatter: ({ cellValue }) => cellValue ? `${cellValue}年` : '-',
      },
      {
        field: 'annualIncome',
        title: '年收入',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'monthlyIncome',
        title: '月收入',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'hasSocialInsurance',
        title: '是否有社保',
        width: 100,
        align: 'center',
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'hasHousingFund',
        title: '是否有公积金',
        width: 120,
        align: 'center',
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'workPhone',
        title: '工作电话',
        width: 130,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'workEmail',
        title: '工作邮箱',
        width: 180,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'createTime',
        title: '创建时间',
        width: 160,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: '100%',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerWorkInfoPageApi({
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
  } as VxeTableGridOptions<CustomerWorkInfoApi.CustomerWorkInfo>,
});

// 经营信息表格配置
const [BusinessInfoGrid, businessInfoGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'businessName',
        title: '经营主体名称',
        width: 180,
        fixed: 'left',
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'businessType',
        title: '经营类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_business_type', cellValue),
      },
      {
        field: 'businessLicenseNo',
        title: '营业执照号',
        width: 180,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'businessScope',
        title: '经营范围',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'industry',
        title: '所属行业',
        width: 150,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'businessScale',
        title: '经营规模',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_business_scale', cellValue),
      },
      {
        field: 'businessStatus',
        title: '经营状态',
        width: 100,
        align: 'center',
        formatter: ({ cellValue }) => getDict('aicrm_business_status', cellValue),
      },
      {
        field: 'registeredCapital',
        title: '注册资本',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'employeeCount',
        title: '员工人数',
        width: 100,
        align: 'right',
        formatter: ({ cellValue }) => cellValue ? `${cellValue}人` : '-',
      },
      {
        field: 'annualRevenue',
        title: '年营业额',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'monthlyRevenue',
        title: '月营业额',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'annualProfit',
        title: '年利润',
        width: 130,
        align: 'right',
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'taxRegistrationNo',
        title: '税务登记号',
        width: 180,
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'isGeneralTaxpayer',
        title: '是否一般纳税人',
        width: 130,
        align: 'center',
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 150,
        showOverflow: 'tooltip',
        formatter: ({ cellValue }) => formatField(cellValue),
      },
      {
        field: 'createTime',
        title: '创建时间',
        width: 160,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: '100%',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerBusinessInfoPageApi({
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
  } as VxeTableGridOptions<CustomerBusinessInfoApi.CustomerBusinessInfo>,
});

// 加载工作信息
async function loadWorkInfo() {
  workInfoLoading.value = true;
  try {
    const result = await getCustomerWorkInfoPageApi({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 100,
    });
    workInfoData.value = result.list || [];
  } catch (error: any) {
    message.error(error.message || '加载工作信息失败');
    workInfoData.value = [];
  } finally {
    workInfoLoading.value = false;
  }
}

// 加载经营信息
async function loadBusinessInfo() {
  businessInfoLoading.value = true;
  try {
    const result = await getCustomerBusinessInfoPageApi({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 100,
    });
    businessInfoData.value = result.list || [];
  } catch (error: any) {
    message.error(error.message || '加载经营信息失败');
    businessInfoData.value = [];
  } finally {
    businessInfoLoading.value = false;
  }
}

// 初始化加载数据
loadWorkInfo();
loadBusinessInfo();

// 暴露刷新方法
defineExpose({
  refreshWorkInfo: () => {
    workInfoGridApi.query();
    loadWorkInfo();
  },
  refreshBusinessInfo: () => {
    businessInfoGridApi.query();
    loadBusinessInfo();
  },
});
</script>

<template>
  <div class="work-business-container">
    <!-- 工作信息表格 -->
    <div class="table-wrapper">
      <template v-if="hasWorkInfo">
        <WorkInfoGrid table-title="工作信息" />
      </template>
      <template v-else>
        <div class="empty-wrapper">
          <a-empty
            v-if="!workInfoLoading"
            description="工作信息不存在"
            :image="Empty.PRESENTED_IMAGE_SIMPLE"
          />
          <a-spin v-else />
        </div>
      </template>
    </div>

    <!-- 经营信息表格 -->
    <div class="table-wrapper">
      <template v-if="hasBusinessInfo">
        <BusinessInfoGrid table-title="经营信息" />
      </template>
      <template v-else>
        <div class="empty-wrapper">
          <a-empty
            v-if="!businessInfoLoading"
            description="经营信息不存在"
            :image="Empty.PRESENTED_IMAGE_SIMPLE"
          />
          <a-spin v-else />
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped>
/* 主容器 - Flex 垂直布局 */
.work-business-container {
  display: flex;
  flex-direction: column;
  height: 100%;
  gap: 16px;
}

/* 表格包装器 - 各占 50% 高度 */
.table-wrapper {
  flex: 1;
  min-height: 0;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

/* 空状态包装器 */
.empty-wrapper {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #fff;
  border-radius: 8px;
}

.dark .empty-wrapper {
  background-color: rgb(20 22 26);
}

/* 数字列右对齐 */
:deep(.vxe-cell--right) {
  font-family: Consolas, Monaco, monospace;
  font-weight: 500;
}
</style>
