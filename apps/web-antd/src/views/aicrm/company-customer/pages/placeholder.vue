<script lang="ts" setup>
import { Empty } from 'ant-design-vue';

defineProps<{
  title: string;
}>();
</script>

<template>
  <a-card :title="title" :bordered="false">
    <Empty description="功能开发中..." />
  </a-card>
</template>
