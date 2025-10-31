<script lang="ts" setup>
import type { AicrmCustomerFamilyMemberApi } from '#/api/aicrm/customerfamilymember';

import { getDictLabel } from '@vben/hooks';

import { Empty, Card } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';
import { IconifyIcon } from '@vben/icons';

import { maskIdNumber } from '#/views/aicrm/utils/identity';

defineOptions({
  name: 'FamilyMemberCardView',
});

const props = defineProps<{
  members: AicrmCustomerFamilyMemberApi.CustomerFamilyMember[];
}>();

/** 获取性别图标 */
function getGenderIcon(gender: number) {
  return gender === 1 ? 'ant-design:man-outlined' : 'ant-design:woman-outlined';
}

/** 获取性别颜色 */
function getGenderColor(gender: number) {
  return gender === 1
    ? 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)'
    : 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)';
}

/** 格式化日期 */
function formatDate(value: any) {
  if (!value) return '-';
  return new Date(value).toLocaleDateString('zh-CN');
}
</script>

<template>
  <div v-if="members.length === 0" class="empty-state">
    <Empty description="暂无家庭成员信息" />
  </div>
  <div v-else class="member-cards-container">
    <Card
      v-for="member in members"
      :key="member.id"
      class="member-card"
      :hoverable="true"
    >
      <!-- 卡片内容 -->
      <div class="card-content">
        <!-- 头像区域 -->
        <div class="avatar-wrapper" :style="{ background: getGenderColor(member.gender) }">
          <IconifyIcon :icon="getGenderIcon(member.gender)" class="avatar-icon" />
        </div>

        <!-- 信息区域 -->
        <div class="member-info">
          <!-- 姓名和关系 -->
          <div class="name-row">
            <span class="member-name">{{ member.memberName || '未知' }}</span>
            <span class="member-relation">
              {{ getDictLabel('aicrm_family_relation', member.relationType) || '-' }}
            </span>
          </div>

          <!-- 性别/年龄 -->
          <div class="info-row">
            <span class="info-label">性别/年龄</span>
            <span class="info-value">
              {{ member.gender === 1 ? '男' : member.gender === 2 ? '女' : '-' }} / {{ member.age || '-' }}岁
            </span>
          </div>

          <!-- 身份证号 -->
          <div class="info-row">
            <span class="info-label">身份证号</span>
            <span class="info-value font-mono">{{ maskIdNumber(member.identityNo) }}</span>
          </div>

          <!-- 学历 -->
          <div v-if="member.educationLevel" class="info-row">
            <span class="info-label">学历</span>
            <span class="info-value">
              {{ getDictLabel('aicrm_education_level', member.educationLevel) || '-' }}
            </span>
          </div>

          <!-- 工作单位 -->
          <div v-if="member.company" class="info-row">
            <span class="info-label">工作单位</span>
            <span class="info-value truncate" :title="member.company">
              {{ member.company }}
            </span>
          </div>

          <!-- 职务 -->
          <div v-if="member.position" class="info-row">
            <span class="info-label">职务</span>
            <span class="info-value">{{ member.position }}</span>
          </div>

          <!-- 手机号码 -->
          <div v-if="member.mobile" class="info-row">
            <span class="info-label">手机号码</span>
            <span class="info-value font-mono">{{ member.mobile }}</span>
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

.member-cards-container {
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

.member-card {
  width: 100%;

  :deep(.ant-card-body) {
    padding: 16px;
  }
}

.card-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.avatar-wrapper {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 3px 10px rgba(102, 126, 234, 0.3);
}

.avatar-icon {
  font-size: 30px;
  color: white;
}

.member-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.name-row {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  padding-bottom: 8px;
  border-bottom: 1px solid #f0f0f0;

  .member-name {
    font-size: 16px;
    font-weight: 600;
    color: #333;
  }

  .member-relation {
    font-size: 12px;
    font-weight: 500;
    color: #666;
    background: #f0f0f0;
    padding: 2px 8px;
    border-radius: 4px;
  }
}

.info-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
  font-size: 12px;

  .info-label {
    color: #999;
    font-weight: 600;
    white-space: nowrap;
    flex-shrink: 0;
    font-size: 12px;
  }

  .info-value {
    color: #333;
    font-weight: 400;
    text-align: right;
    overflow: hidden;
    text-overflow: ellipsis;
    font-size: 12px;
  }
}

.truncate {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.font-mono {
  font-family: 'Courier New', Courier, monospace;
}

// Dark 模式样式 - 必须在 scoped 块外面
.dark {
  .member-card {
    :deep(.ant-card) {
      background: rgb(20 22 26);
      border-color: rgb(48 52 59);
    }
  }

  .name-row {
    border-bottom-color: rgba(255, 255, 255, 0.1);

    .member-name {
      color: #fff;
    }

    .member-relation {
      background: rgba(255, 255, 255, 0.1);
      color: rgba(255, 255, 255, 0.8);
    }
  }

  .info-row {
    .info-label {
      color: rgba(255, 255, 255, 0.5);
    }

    .info-value {
      color: rgba(255, 255, 255, 0.85);
    }
  }
}

@media (max-width: 768px) {
  .avatar-wrapper {
    width: 50px;
    height: 50px;
  }

  .avatar-icon {
    font-size: 25px;
  }

  .name-row {
    .member-name {
      font-size: 14px;
    }

    .member-relation {
      font-size: 11px;
    }
  }
}
</style>
