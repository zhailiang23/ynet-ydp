<script setup lang="ts">
import type { CarouselItem } from '@/types/product'

interface Props {
  item: CarouselItem
}

defineProps<Props>()

const emit = defineEmits<{
  (e: 'click'): void
}>()
</script>

<template>
  <div
    class="snap-start shrink-0 w-[280px] h-[140px] rounded-xl relative overflow-hidden group cursor-pointer"
    @click="emit('click')"
  >
    <!-- Background Image -->
    <div
      class="absolute inset-0 bg-cover bg-center transition-transform duration-500 group-hover:scale-105"
      :style="{ backgroundImage: `url(${item.imageUrl})` }"
    >
      <!-- Fallback Gradient if no image -->
      <div
        v-if="!item.imageUrl"
        class="absolute inset-0 bg-gradient-to-br from-blue-500 to-purple-600"
      />
    </div>

    <!-- Content Overlay -->
    <div class="absolute inset-0 bg-gradient-to-t from-black/90 via-black/50 to-transparent p-4 flex flex-col justify-end">
      <!-- Badge -->
      <div v-if="item.badgeText" class="flex items-center gap-1 mb-1">
        <span
          class="text-white text-[10px] font-bold px-1.5 py-0.5 rounded flex items-center gap-1"
          :class="item.badgeColor || 'bg-primary/90'"
          :style="item.badgeColor ? { backgroundColor: item.badgeColor } : {}"
        >
          <svg
            v-if="item.badgeText.includes('AI')"
            class="w-2.5 h-2.5"
            fill="currentColor"
            viewBox="0 0 20 20"
          >
            <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
          </svg>
          {{ item.badgeText }}
        </span>
      </div>

      <!-- Title -->
      <h3 class="text-white font-bold text-lg leading-tight">
        {{ item.title }}
      </h3>

      <!-- Subtitle -->
      <p v-if="item.subtitle" class="text-gray-300 text-xs mt-1">
        {{ item.subtitle }}
      </p>
    </div>
  </div>
</template>
