<template>
  <div class="relative flex min-h-screen w-full flex-col overflow-hidden pb-24">
    <!-- Top App Bar -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md px-4 py-3 flex items-center justify-between border-b border-gray-200 dark:border-gray-800">
      <button
        class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors"
        @click="router.back()"
      >
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </button>
      <h1 class="text-lg font-bold tracking-tight">海报生成器</h1>
      <button class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </div>

    <!-- Main Content -->
    <main class="flex-1 overflow-y-auto w-full max-w-md mx-auto">
      <!-- Poster Preview -->
      <div class="p-4 flex justify-center bg-gray-100 dark:bg-[#15202b]">
        <div class="relative w-3/4 max-w-[280px] rounded-xl shadow-xl overflow-hidden aspect-poster group transform transition-transform hover:scale-[1.02]">
          <!-- Poster Background -->
          <div
            class="absolute inset-0 bg-cover bg-center"
            :style="{ backgroundImage: `url(${selectedTemplate.image})` }"
          >
            <div class="absolute inset-0 bg-black/30 group-hover:bg-black/20 transition-colors"></div>
          </div>

          <!-- Poster Content -->
          <div class="absolute inset-0 p-6 flex flex-col justify-between text-white z-10 pointer-events-none">
            <!-- Title Section -->
            <div class="space-y-2 mt-8">
              <h2 class="text-2xl font-bold leading-tight drop-shadow-md whitespace-pre-line">{{ posterTitle }}</h2>
              <p class="text-xs opacity-90 drop-shadow">{{ posterSubtitle }}</p>
            </div>

            <!-- Business Card (if enabled) -->
            <div
              v-if="showBusinessCard"
              class="flex items-center gap-3 bg-white/10 backdrop-blur-md p-3 rounded-lg border border-white/20"
            >
              <div class="w-10 h-10 rounded-full bg-gray-200 overflow-hidden">
                <img
                  class="w-full h-full object-cover"
                  :src="businessCard.avatar"
                  alt="Avatar"
                />
              </div>
              <div class="flex-1 min-w-0">
                <p class="text-xs font-bold truncate">{{ businessCard.name }} · {{ businessCard.title }}</p>
                <p class="text-[10px] opacity-80 truncate">{{ businessCard.phone }}</p>
              </div>
              <div class="w-8 h-8 bg-white p-0.5 rounded-sm">
                <div class="w-full h-full bg-black"></div>
              </div>
            </div>
          </div>

          <!-- Fullscreen Button -->
          <button
            class="absolute top-2 right-2 p-2 bg-black/40 backdrop-blur-sm rounded-full text-white hover:bg-primary transition-colors z-20 pointer-events-auto"
            @click="openPreview"
          >
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 8V4m0 0h4M4 4l5 5m11-1V4m0 0h-4m4 0l-5 5M4 16v4m0 0h4m-4 0l5-5m11 5l-5-5m5 5v-4m0 4h-4" />
            </svg>
          </button>
        </div>
      </div>

      <!-- Settings Panel -->
      <div class="px-4 py-6 space-y-8 bg-surface-light dark:bg-surface-dark rounded-t-3xl shadow-[0_-4px_20px_-4px_rgba(0,0,0,0.1)] dark:shadow-none min-h-[500px] -mt-4 relative z-10">
        <!-- Template Selection -->
        <div class="space-y-3">
          <div class="flex items-center justify-between">
            <h3 class="text-base font-bold flex items-center gap-2">
              <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 21a4 4 0 01-4-4V5a2 2 0 012-2h4a2 2 0 012 2v12a4 4 0 01-4 4zm0 0h12a2 2 0 002-2v-4a2 2 0 00-2-2h-2.343M11 7.343l1.657-1.657a2 2 0 012.828 0l2.829 2.829a2 2 0 010 2.828l-8.486 8.485M7 17h.01" />
              </svg>
              选择模板
            </h3>
            <a class="text-xs text-primary font-medium" href="#">查看全部</a>
          </div>

          <!-- Category Filters -->
          <div class="flex gap-2 overflow-x-auto no-scrollbar pb-1">
            <button
              v-for="category in templateCategories"
              :key="category.value"
              class="px-3 py-1.5 rounded-lg text-xs font-medium whitespace-nowrap transition-colors"
              :class="activeCategory === category.value
                ? 'bg-primary text-white shadow-md shadow-primary/20'
                : 'bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-400 hover:bg-gray-200 dark:hover:bg-gray-700'"
              @click="activeCategory = category.value"
            >
              {{ category.label }}
            </button>
          </div>

          <!-- Template Thumbnails -->
          <div class="flex gap-3 overflow-x-auto no-scrollbar pb-2">
            <div
              v-for="template in templates"
              :key="template.id"
              class="relative shrink-0 w-24 aspect-[9/14] rounded-lg overflow-hidden cursor-pointer transition-opacity"
              :class="selectedTemplate.id === template.id
                ? 'border-2 border-primary'
                : 'border border-gray-200 dark:border-gray-700 opacity-70 hover:opacity-100'"
              @click="selectedTemplate = template"
            >
              <img class="w-full h-full object-cover" :src="template.image" :alt="template.name" />
              <div
                v-if="selectedTemplate.id === template.id"
                class="absolute bottom-0 inset-x-0 bg-primary/90 text-[10px] text-white text-center py-0.5 font-medium"
              >
                已选择
              </div>
            </div>

            <!-- Upload Custom Template -->
            <div class="relative shrink-0 w-24 aspect-[9/14] rounded-lg overflow-hidden border border-gray-200 dark:border-gray-700 cursor-pointer opacity-70 hover:opacity-100 transition-opacity bg-gray-100 dark:bg-gray-800 flex items-center justify-center">
              <svg class="w-8 h-8 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </div>
          </div>
        </div>

        <!-- Image Settings -->
        <div class="space-y-3 border-t border-gray-100 dark:border-gray-800 pt-6">
          <h3 class="text-base font-bold flex items-center gap-2">
            <svg class="w-5 h-5 text-purple-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            配图设置
          </h3>
          <div class="grid grid-cols-2 gap-3">
            <button class="flex items-center justify-center gap-2 py-3 border border-dashed border-gray-300 dark:border-gray-600 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors text-sm font-medium text-gray-600 dark:text-gray-300">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 16a4 4 0 01-.88-7.903A5 5 0 1115.9 6L16 6a5 5 0 011 9.9M15 13l-3-3m0 0l-3 3m3-3v12" />
              </svg>
              上传图片
            </button>
            <button class="flex items-center justify-center gap-2 py-3 border border-dashed border-gray-300 dark:border-gray-600 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors text-sm font-medium text-gray-600 dark:text-gray-300">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
              图库选择
            </button>
          </div>
        </div>

        <!-- Content Editor -->
        <div class="space-y-4 border-t border-gray-100 dark:border-gray-800 pt-6 pb-6">
          <div class="flex items-center justify-between">
            <h3 class="text-base font-bold flex items-center gap-2">
              <svg class="w-5 h-5 text-emerald-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
              </svg>
              内容编辑
            </h3>
            <button class="text-xs flex items-center gap-1 text-gray-500 bg-gray-100 dark:bg-gray-800 px-2 py-1 rounded">
              <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
              </svg>
              AI 润色
            </button>
          </div>

          <div class="space-y-3">
            <!-- Title Input -->
            <div>
              <label class="block text-xs font-medium text-gray-500 dark:text-gray-400 mb-1.5 ml-1">海报标题</label>
              <input
                v-model="posterTitle"
                class="block w-full rounded-xl border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-background-dark text-sm focus:border-primary focus:ring-primary/20 dark:text-white"
                placeholder="请输入主标题"
                type="text"
              />
            </div>

            <!-- Subtitle Input -->
            <div>
              <label class="block text-xs font-medium text-gray-500 dark:text-gray-400 mb-1.5 ml-1">副标题 / 描述</label>
              <textarea
                v-model="posterSubtitle"
                class="block w-full rounded-xl border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-background-dark text-sm focus:border-primary focus:ring-primary/20 dark:text-white resize-none"
                placeholder="请输入副标题内容"
                rows="2"
              ></textarea>
            </div>
          </div>

          <!-- Business Card Toggle -->
          <div class="flex items-center justify-between p-3 rounded-xl bg-gray-50 dark:bg-background-dark/50 border border-gray-100 dark:border-gray-800">
            <div class="flex items-center gap-3">
              <div class="w-8 h-8 rounded-full bg-blue-100 dark:bg-blue-900/30 flex items-center justify-center text-primary">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V8a2 2 0 00-2-2h-5m-4 0V5a2 2 0 114 0v1m-4 0a2 2 0 104 0m-5 8a2 2 0 100-4 2 2 0 000 4zm0 0c1.306 0 2.417.835 2.83 2M9 14a3.001 3.001 0 00-2.83 2M15 11h3m-3 4h2" />
                </svg>
              </div>
              <div>
                <p class="text-sm font-bold text-slate-900 dark:text-white">显示名片信息</p>
                <p class="text-[10px] text-gray-500">姓名、电话、二维码</p>
              </div>
            </div>
            <label class="relative inline-flex items-center cursor-pointer">
              <input v-model="showBusinessCard" class="sr-only peer" type="checkbox" />
              <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-primary"></div>
            </label>
          </div>
        </div>
      </div>
    </main>

    <!-- Bottom Action Bar -->
    <div class="fixed bottom-0 w-full bg-surface-light/95 dark:bg-surface-dark/95 backdrop-blur-md border-t border-gray-200 dark:border-gray-800 px-4 py-4 z-50">
      <div class="flex gap-3">
        <button
          class="flex-1 flex items-center justify-center gap-2 py-3.5 rounded-xl bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 text-slate-900 dark:text-white font-semibold shadow-sm active:scale-[0.98] transition-transform"
          @click="openPreview"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z" />
          </svg>
          预览效果
        </button>
        <button
          class="flex-[2] flex items-center justify-center gap-2 py-3.5 rounded-xl bg-primary hover:bg-primary-dark text-white font-bold shadow-lg shadow-primary/30 active:scale-[0.98] transition-transform disabled:opacity-50 disabled:cursor-not-allowed"
          :disabled="isGenerating"
          @click="handleGeneratePoster"
        >
          <svg v-if="!isGenerating" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
          </svg>
          <svg v-else class="w-5 h-5 animate-spin" fill="none" viewBox="0 0 24 24">
            <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
            <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
          </svg>
          {{ isGenerating ? '生成中...' : '一键生成并分享' }}
        </button>
      </div>
    </div>

    <!-- Share Dialog -->
    <div
      v-if="showShareDialog"
      class="fixed inset-0 z-50 flex items-end justify-center bg-black/50 backdrop-blur-sm"
      @click="closeShareDialog"
    >
      <div
        class="w-full max-w-md bg-surface-light dark:bg-surface-dark rounded-t-3xl shadow-xl transform transition-transform"
        @click.stop
      >
        <!-- Generated Poster Preview -->
        <div class="p-6 border-b border-gray-100 dark:border-gray-800">
          <div class="flex items-center justify-between mb-4">
            <h3 class="text-lg font-bold">海报生成成功</h3>
            <button
              class="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-800 transition-colors"
              @click="closeShareDialog"
            >
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          </div>
          <div class="flex justify-center">
            <img
              v-if="generatedImageUrl"
              :src="generatedImageUrl"
              alt="生成的海报"
              class="w-3/4 max-w-[280px] rounded-xl shadow-lg"
            />
          </div>
        </div>

        <!-- Share Options -->
        <div class="p-6 space-y-4">
          <h4 class="text-sm font-semibold text-gray-500 dark:text-gray-400 uppercase tracking-wider">分享到</h4>
          <div class="grid grid-cols-4 gap-4">
            <!-- WeChat -->
            <button
              class="flex flex-col items-center gap-2 p-3 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors"
              @click="shareToWeChat"
            >
              <div class="w-12 h-12 rounded-full bg-green-100 dark:bg-green-900/30 flex items-center justify-center text-green-600 dark:text-green-400">
                <svg class="w-7 h-7" fill="currentColor" viewBox="0 0 24 24">
                  <path d="M8.5 10.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm6 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
                  <path d="M18.5 8.5c-2.5 0-4.5 1.5-4.5 3.5s2 3.5 4.5 3.5c.5 0 1-.1 1.5-.2.5.5 1.5 1 2.5 1-.3-.7-.5-1.5-.5-2 1-.7 1.5-1.7 1.5-2.8 0-2-2-3.5-4.5-3.5z"/>
                  <path d="M12 2C6.5 2 2 5.6 2 10c0 2 1 4 2.5 5.5-.5 1.5-1 3-1.5 4 1.5-.5 3-1.5 4-2 1 .5 2.5 1 4 1 5.5 0 10-3.6 10-8S17.5 2 12 2z"/>
                </svg>
              </div>
              <span class="text-xs font-medium">微信</span>
            </button>

            <!-- Moments -->
            <button
              class="flex flex-col items-center gap-2 p-3 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors"
              @click="shareToMoments"
            >
              <div class="w-12 h-12 rounded-full bg-blue-100 dark:bg-blue-900/30 flex items-center justify-center text-blue-600 dark:text-blue-400">
                <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 01-9 9m9-9a9 9 0 00-9-9m9 9H3m9 9a9 9 0 01-9-9m9 9c1.657 0 3-4.03 3-9s-1.343-9-3-9m0 18c-1.657 0-3-4.03-3-9s1.343-9 3-9m-9 9a9 9 0 019-9" />
                </svg>
              </div>
              <span class="text-xs font-medium">朋友圈</span>
            </button>

            <!-- Download -->
            <button
              class="flex flex-col items-center gap-2 p-3 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors"
              @click="downloadPoster"
            >
              <div class="w-12 h-12 rounded-full bg-purple-100 dark:bg-purple-900/30 flex items-center justify-center text-purple-600 dark:text-purple-400">
                <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-4l-4 4m0 0l-4-4m4 4V4" />
                </svg>
              </div>
              <span class="text-xs font-medium">保存图片</span>
            </button>

            <!-- Copy Link -->
            <button
              class="flex flex-col items-center gap-2 p-3 rounded-xl hover:bg-gray-50 dark:hover:bg-gray-800/50 transition-colors"
              @click="copyLink"
            >
              <div class="w-12 h-12 rounded-full bg-orange-100 dark:bg-orange-900/30 flex items-center justify-center text-orange-600 dark:text-orange-400">
                <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.828 10.172a4 4 0 00-5.656 0l-4 4a4 4 0 105.656 5.656l1.102-1.101m-.758-4.899a4 4 0 005.656 0l4-4a4 4 0 00-5.656-5.656l-1.1 1.1" />
                </svg>
              </div>
              <span class="text-xs font-medium">复制链接</span>
            </button>
          </div>

          <!-- Cancel Button -->
          <button
            class="w-full py-3 rounded-xl bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 font-semibold hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors"
            @click="closeShareDialog"
          >
            取消
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { generatePoster } from '@/api/ai-poster'
import { composePoster } from '@/utils/poster-canvas'

const router = useRouter()

// 生成状态
const isGenerating = ref(false)
const generatedImageUrl = ref('')
const showShareDialog = ref(false)

// Template categories
const templateCategories = [
  { value: 'popular', label: '热门推荐' },
  { value: 'festival', label: '节日祝福' },
  { value: 'finance', label: '财经热点' },
  { value: 'product', label: '产品介绍' },
]

const activeCategory = ref('popular')

// Template data
const templates = [
  {
    id: 1,
    name: '稳健理财',
    category: 'popular',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuA-tu0kfbrM_EZ1v2XKiWvGM73_dmOlu0UqG1i0UTXM-sgXAKJ3p0ysBRtZeJBEex_EjQ7zJxranQir5yRZi4osIV-Y2qGKtA-f4CU07vksGNahrZWmq6wOWElEEZmejPyPAZRZ__K7k23ZPeaiN6jTZM1XmaBhm3rYX6_7Bkq-vGAG2ALHsO0Q1JhVCFlVcE_17-0YD5qowa7IVJigzf3a4YA5aqAAFnI_QRJ0khGJJLDwu1cQ4IEsQOQarhIHZKQl2RHHrUGg83TG',
  },
  {
    id: 2,
    name: '商务合作',
    category: 'popular',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDuY5rZJ7fiR3zoFUZjDvy5RtQU1AHDnpj5eiSs_lf6RIAu676jKWhqd9ek2qLQGr__QkVfBGE7gZvrgwG_DnMcL00iuRnkWqMPC1sfy3YmqVtRmaQwIqyTHv0zmiIQgMWwjFnijx4987EpsFMLs-FFcRcpfLftcmhr914tb5QDUkpJViEWgyyTQ8JG8lVmauiMsRGfw4HhOI1vRVb8X3b8NpjUp7xNwYa1tUJuBkg2uO_nBye_xlNqoI4KEw9Ey0LLfWlwUL99BH5v',
  },
  {
    id: 3,
    name: '财富增值',
    category: 'finance',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCGTI0JhQjMdT4ywgS-HICjWk-JV6GI15ZAAZCdMsJ818g9llW6DEByme2kLLgGqY3sQ-sCdnAtrFYrLn6crYCLdSML_UTtWPKU4A-BnKut04JITsNAHd2j1lauPaUeY0ptlVFRpaNG8gyWOYoXKkK7funmyTYefBeKdjsu4MmV0xI2981846hr0Uo_PBWDNGv_DNtC_CZQjlwnLCc3kMjyGvhNvILQbSvBjAIM3Gqo9LZv3fz0ulI6g83U8XnKDyhdZBefIW2KhW3c',
  },
]

const selectedTemplate = ref(templates[0])

// Poster content
const posterTitle = ref('稳健理财\n首选方案')
const posterSubtitle = ref('中低风险 · 灵活存取')
const showBusinessCard = ref(true)

// Business card info
const businessCard = {
  avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDvtnIgzHophPmiwYzNmXGx6tVaq2NHhCAX9W-s6nbNpN6FWrPH3JbxPXFLeKESlyv5ZcZZAuFe74Q0MB612LCWDmfsnJcqKn54tJTcycjjUp2YG6nuIqnWJEX4zxAA_zwVCcsFRA1E1lAjIMN0JWJf83Lq-xMzfrOQLsovL9i3FG5OklGztK9j0B16YMMQeI-G1unv4XDNVRQva40qhpXVNuIHas7pSvxHJH2oBjMQSkLiwQ2NszT7pbv3sWnpAyshVfevTxjGJuF7',
  name: '王建国',
  title: '资深顾问',
  phone: '138 0000 8888',
}

// Functions
const openPreview = () => {
  if (generatedImageUrl.value) {
    // 打开生成的海报预览
    window.open(generatedImageUrl.value, '_blank')
  } else {
    console.log('打开预览')
  }
}

const handleGeneratePoster = async () => {
  try {
    isGenerating.value = true

    // 1. 调用 AI 生成背景图
    const response = await generatePoster({
      title: posterTitle.value,
      subtitle: posterSubtitle.value,
      templateId: selectedTemplate.value.id,
      showBusinessCard: showBusinessCard.value,
      businessCard: showBusinessCard.value ? businessCard : undefined,
    })

    // 2. 使用 Canvas 合成中文文字
    const finalPosterUrl = await composePoster({
      backgroundUrl: response.imageUrl,
      title: posterTitle.value,
      subtitle: posterSubtitle.value,
      showBusinessCard: showBusinessCard.value,
      businessCard: showBusinessCard.value ? businessCard : undefined,
    })

    generatedImageUrl.value = finalPosterUrl
    showShareDialog.value = true
  } catch (error: any) {
    console.error('生成海报失败:', error)
    alert('生成海报失败: ' + (error.message || '请稍后重试'))
  } finally {
    isGenerating.value = false
  }
}

const closeShareDialog = () => {
  showShareDialog.value = false
}

const shareToWeChat = () => {
  console.log('分享到微信')
  // 微信分享逻辑（实际需要调用微信SDK）
}

const shareToMoments = () => {
  console.log('分享到朋友圈')
  // 朋友圈分享逻辑
}

const downloadPoster = () => {
  if (generatedImageUrl.value) {
    const link = document.createElement('a')
    link.href = generatedImageUrl.value
    link.download = `海报_${Date.now()}.png`
    link.click()
  }
}

const copyLink = () => {
  if (generatedImageUrl.value) {
    navigator.clipboard.writeText(generatedImageUrl.value)
    alert('链接已复制到剪贴板')
  }
}
</script>

<style scoped>
/* Custom scrollbar hiding */
.no-scrollbar::-webkit-scrollbar {
  display: none;
}
.no-scrollbar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}

/* Poster aspect ratio */
.aspect-poster {
  aspect-ratio: 9/14;
}
</style>
