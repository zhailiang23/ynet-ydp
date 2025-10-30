<script lang="ts" setup>
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message, Tag, Button, Empty, Card } from 'ant-design-vue';
import { IconifyIcon } from '@vben/icons';

import { updateCustomerIdentity } from '#/api/aicrm/customeridentity';
import { maskIdNumber } from '#/views/aicrm/utils/identity';

defineOptions({
  name: 'IdentityCardView',
});

const props = defineProps<{
  identities: AicrmCustomerIdentityApi.CustomerIdentity[];
}>();

const emit = defineEmits<{
  refresh: [];
}>();

/** 证件类型图标映射 */
const identityTypeIcons: Record<string, string> = {
  '1': 'ant-design:idcard-outlined', // 身份证
  '2': 'ant-design:safety-certificate-outlined', // 护照
  '3': 'ant-design:car-outlined', // 驾驶证
  '4': 'ant-design:file-protect-outlined', // 军官证
  '5': 'ant-design:bank-outlined', // 其他
};

/** 获取证件类型图标 */
function getIdentityIcon(type: string) {
  return identityTypeIcons[type] || 'ant-design:file-protect-outlined';
}

/** 获取有效性状态 */
function getValidityStatus(expiryDate: string) {
  if (!expiryDate) return { text: '未知', color: 'default' };
  if (expiryDate === '9999-12-31')
    return { text: '长期有效', color: 'green' };

  const today = new Date();
  const expiry = new Date(expiryDate);
  return expiry >= today
    ? { text: '有效', color: 'green' }
    : { text: '已失效', color: 'red' };
}

/** 设为默认证件 */
async function handleSetPrimary(identity: AicrmCustomerIdentityApi.CustomerIdentity) {
  if (identity.isPrimary) {
    message.info('该证件已经是默认证件');
    return;
  }

  try {
    await updateCustomerIdentity({
      ...identity,
      isPrimary: true,
    });
    message.success('设置成功');
    emit('refresh');
  } catch (error: any) {
    message.error(error.message || '设置失败');
  }
}
</script>

<template>
  <div v-if="identities.length === 0" class="empty-state">
    <Empty description="暂无证件信息" />
  </div>
  <div v-else class="identity-cards-container">
    <Card
      v-for="identity in identities"
      :key="identity.id"
      class="identity-card"
      :hoverable="true"
    >
      <!-- 卡片内容 - 横向布局 -->
      <div class="card-horizontal-layout">
        <!-- 左侧：图标和证件类型 -->
        <div class="card-left">
          <div class="card-icon-wrapper">
            <IconifyIcon
              :icon="getIdentityIcon(identity.identityType)"
              class="card-icon"
            />
          </div>
          <div class="card-type">
            {{ getDictLabel('aicrm_customer_identity_type', identity.identityType) || '未知类型' }}
          </div>
        </div>

        <!-- 右侧：详细信息 -->
        <div class="card-right">
          <!-- 证件号码 -->
          <div class="info-row">
            <span class="info-label">证件号码</span>
            <span class="info-value font-mono">{{ maskIdNumber(identity.identityNo) }}</span>
          </div>

          <!-- 证件名称 -->
          <div v-if="identity.identityName" class="info-row">
            <span class="info-label">证件名称</span>
            <span class="info-value">{{ identity.identityName }}</span>
          </div>

          <!-- 日期信息 -->
          <div class="info-row-group">
            <div class="info-row">
              <span class="info-label">签发日期</span>
              <span class="info-value">{{ identity.issueDate || '-' }}</span>
            </div>
            <div class="info-row">
              <span class="info-label">失效日期</span>
              <span class="info-value">
                {{ identity.expiryDate === '9999-12-31' ? '长期有效' : identity.expiryDate || '-' }}
              </span>
            </div>
          </div>

          <!-- 发证机关 -->
          <div v-if="identity.issueAuthority" class="info-row">
            <span class="info-label">发证机关</span>
            <span class="info-value truncate" :title="identity.issueAuthority">
              {{ identity.issueAuthority }}
            </span>
          </div>

          <!-- 底部：状态标签和操作按钮 -->
          <div class="card-footer">
            <div class="card-tags">
              <Tag :color="getValidityStatus(identity.expiryDate).color">
                {{ getValidityStatus(identity.expiryDate).text }}
              </Tag>
              <Tag v-if="identity.isPrimary" color="green">
                默认证件
              </Tag>
            </div>
            <Button
              v-if="!identity.isPrimary"
              type="link"
              size="small"
              @click="handleSetPrimary(identity)"
            >
              设为默认
            </Button>
          </div>
        </div>
      </div>
    </Card>
  </div>
</template>

<style scoped lang="less">
.empty-state {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 300px;
}

.identity-cards-container {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  @media (max-width: 1400px) {
    grid-template-columns: repeat(2, 1fr);
  }

  @media (max-width: 768px) {
    grid-template-columns: repeat(1, 1fr);
  }
}

.identity-card {
  width: 100%;

  :deep(.ant-card-body) {
    padding: 16px;
  }
}

.card-horizontal-layout {
  display: flex;
  gap: 12px;
  align-items: flex-start;
}

.card-left {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 6px;
  min-width: 60px;
  flex-shrink: 0;
}

.card-icon-wrapper {
  width: 48px;
  height: 48px;
  border-radius: 10px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 3px 10px rgba(102, 126, 234, 0.3);
}

.card-icon {
  font-size: 24px;
  color: white;
}

.card-type {
  font-size: 11px;
  font-weight: 500;
  color: #666;
  text-align: center;
  white-space: nowrap;
}

.card-right {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 8px;
  min-width: 0;
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
  font-size: 12px;

  .info-label {
    color: #999;
    font-weight: 400;
    white-space: nowrap;
    flex-shrink: 0;
    font-size: 12px;
  }

  .info-value {
    color: #333;
    font-weight: 500;
    text-align: right;
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 12px;
  }
}

.info-row-group {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 2px;
  padding-top: 10px;
  border-top: 1px solid #f0f0f0;
}

.card-tags {
  display: flex;
  gap: 6px;
  flex-wrap: wrap;
}

// 响应式调整
@media (max-width: 1200px) {
  .card-horizontal-layout {
    gap: 16px;
  }

  .card-left {
    min-width: 70px;
  }

  .card-icon-wrapper {
    width: 48px;
    height: 48px;
  }

  .card-icon {
    font-size: 24px;
  }
}

@media (max-width: 768px) {
  .card-horizontal-layout {
    gap: 12px;
  }

  .info-row {
    font-size: 12px;
  }
}
</style>
