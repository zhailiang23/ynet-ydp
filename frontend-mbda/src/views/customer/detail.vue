<template>
  <div class="min-h-screen bg-background-dark text-white pb-32">
    <!-- 顶部导航栏 -->
    <div class="sticky top-0 z-50 flex items-center bg-background-dark/95 backdrop-blur-md p-4 pb-2 justify-between border-b border-white/5">
      <div class="text-white flex size-12 shrink-0 items-center justify-start cursor-pointer" @click="goBack">
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </div>
      <h2 class="text-white text-lg font-bold leading-tight tracking-[-0.015em] flex-1 text-center pr-12">AI 客户洞察</h2>
      <div class="flex w-12 items-center justify-end">
        <button class="flex items-center justify-center text-white">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 5v.01M12 12v.01M12 19v.01M12 6a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2zm0 7a1 1 0 110-2 1 1 0 010 2z" />
          </svg>
        </button>
      </div>
    </div>

    <div v-if="loading" class="flex items-center justify-center py-20">
      <div class="text-slate-400">加载中...</div>
    </div>

    <div v-else-if="error" class="flex items-center justify-center py-20">
      <div class="text-red-400">{{ error }}</div>
    </div>

    <div v-else-if="customer">
      <!-- 客户基本信息 -->
      <div class="flex p-4 bg-background-dark">
        <div class="flex w-full flex-col gap-4">
          <div class="flex gap-4 items-start">
            <div class="relative">
              <div
                class="bg-center bg-no-repeat aspect-square bg-cover rounded-full h-20 w-20 ring-2 ring-white/10"
                :style="{ backgroundImage: customer.avatar ? `url(${customer.avatar})` : 'none' }"
              >
                <div v-if="!customer.avatar" class="flex items-center justify-center h-full text-2xl font-bold bg-gradient-to-br from-primary to-purple-600">
                  {{ customer.customerName?.charAt(0) }}
                </div>
              </div>
              <div v-if="customer.customerLevel" class="absolute -bottom-1 -right-1 text-[10px] font-bold px-2 py-0.5 rounded-full border-2 border-background-dark"
                :class="{
                  'bg-gradient-to-br from-yellow-400 to-yellow-600 text-black': customer.customerLevel === 'diamond',
                  'bg-gradient-to-br from-purple-400 to-purple-600 text-white': customer.customerLevel === 'vip',
                  'bg-gradient-to-br from-blue-400 to-blue-600 text-white': customer.customerLevel === 'gold',
                  'bg-gradient-to-br from-gray-400 to-gray-600 text-white': customer.customerLevel === 'normal'
                }"
              >
                {{ getLevelLabel(customer.customerLevel) }}
              </div>
            </div>

            <div class="flex flex-col justify-center flex-1">
              <div class="flex justify-between items-start">
                <div>
                  <h1 class="text-white text-2xl font-bold leading-tight tracking-[-0.015em]">{{ customer.customerName }}</h1>
                  <p class="text-[#92adc9] text-sm mt-1">{{ getLevelLabel(customer.customerLevel) }} • ID: {{ customer.id }}</p>
                </div>
              </div>
              <div class="mt-2 flex gap-4">
                <div>
                  <p class="text-[#92adc9] text-xs font-medium uppercase tracking-wider">总资产 (AUM)</p>
                  <p class="text-white text-lg font-bold font-mono">¥{{ formatMoney(customer.aum) }}</p>
                </div>
                <div v-if="customer.potentialValue">
                  <p class="text-[#92adc9] text-xs font-medium uppercase tracking-wider">潜在价值</p>
                  <p class="text-green-400 text-lg font-bold font-mono">¥{{ formatMoney(customer.potentialValue) }}</p>
                </div>
              </div>
            </div>
          </div>

          <div class="flex gap-3 mt-2">
            <button class="flex-1 flex items-center justify-center rounded-lg h-10 px-4 bg-[#233648] hover:bg-[#2f455a] transition-colors text-white text-sm font-bold gap-2">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
              </svg>
              <span>拨打电话</span>
            </button>
            <button class="flex-1 flex items-center justify-center rounded-lg h-10 px-4 bg-primary hover:bg-primary-dark transition-colors text-white text-sm font-bold gap-2">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
              </svg>
              <span>发送消息</span>
            </button>
          </div>
        </div>
      </div>

      <!-- AI 洞察摘要 -->
      <div v-if="customer.analysisConclusion" class="px-4 py-2">
        <div class="relative overflow-hidden rounded-xl bg-gradient-to-br from-[#1e293b] via-[#0f172a] to-[#1e1b4b] border border-primary/30 shadow-lg shadow-primary/5">
          <div class="absolute top-0 right-0 -mt-8 -mr-8 w-32 h-32 bg-primary/20 rounded-full blur-3xl"></div>
          <div class="absolute bottom-0 left-0 -mb-8 -ml-8 w-32 h-32 bg-purple-500/10 rounded-full blur-3xl"></div>
          <div class="relative p-5">
            <div class="flex items-center gap-2 mb-3">
              <div class="flex items-center justify-center w-8 h-8 rounded-full bg-gradient-to-br from-primary to-purple-600 shadow-lg shadow-primary/20">
                <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z" />
                </svg>
              </div>
              <p class="text-blue-200 font-semibold text-sm tracking-wide">AI 洞察摘要</p>
            </div>
            <h3 class="text-white text-lg font-bold leading-tight mb-2">客户分析</h3>
            <p class="text-slate-300 text-sm leading-relaxed mb-4">{{ customer.analysisConclusion }}</p>
            <div class="flex items-center gap-2">
              <div class="h-1.5 w-1.5 rounded-full bg-green-500"></div>
              <p class="text-xs text-slate-400">AI 匹配评分: <span class="text-green-400 font-medium">{{ customer.aiMatchScore }}分</span></p>
            </div>
          </div>
        </div>
      </div>

      <!-- 客户画像标签 -->
      <div class="mt-4">
        <div class="flex items-center justify-between px-4 pb-2">
          <h3 class="text-white text-lg font-bold leading-tight tracking-[-0.015em]">客户画像</h3>
        </div>
        <div class="flex gap-2 px-4 flex-wrap mb-4">
          <div class="flex h-7 items-center justify-center px-3 rounded-full bg-[#233648] border border-white/5">
            <p class="text-blue-200 text-xs font-medium">{{ getRiskLevelLabel(customer.riskLevel) }}</p>
          </div>
          <div class="flex h-7 items-center justify-center px-3 rounded-full bg-[#233648] border border-white/5">
            <p class="text-emerald-200 text-xs font-medium">{{ getLevelLabel(customer.customerLevel) }}</p>
          </div>
          <div v-if="customer.source" class="flex h-7 items-center justify-center px-3 rounded-full bg-[#233648] border border-white/5">
            <p class="text-amber-200 text-xs font-medium">{{ getSourceLabel(customer.source) }}</p>
          </div>
        </div>

        <!-- 关键生活事件 -->
        <div class="px-4 pb-2">
          <p class="text-[#92adc9] text-xs font-semibold uppercase tracking-wider mb-3 pl-1">关键生活事件</p>
          <div class="flex gap-3 overflow-x-auto pb-4 scrollbar-hide snap-x">
            <div class="snap-start shrink-0 w-64 bg-surface-dark rounded-lg p-3 border-l-4 border-primary flex items-start gap-3">
              <div class="bg-primary/10 p-2 rounded-full shrink-0">
                <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v13m0-13V6a2 2 0 112 2h-2zm0 0V5.5A2.5 2.5 0 109.5 8H12zm-7 4h14M5 12a2 2 0 110-4h14a2 2 0 110 4M5 12v7a2 2 0 002 2h10a2 2 0 002-2v-7" />
                </svg>
              </div>
              <div>
                <p class="text-white text-sm font-semibold">定期存款到期</p>
                <p class="text-[#92adc9] text-xs mt-1">1周后</p>
                <p class="text-xs text-primary mt-2 font-medium">建议续配理财</p>
              </div>
            </div>
            <div class="snap-start shrink-0 w-64 bg-surface-dark rounded-lg p-3 border-l-4 border-orange-500 flex items-start gap-3">
              <div class="bg-orange-500/10 p-2 rounded-full shrink-0">
                <svg class="w-5 h-5 text-orange-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                </svg>
              </div>
              <div>
                <p class="text-white text-sm font-semibold">保单续期</p>
                <p class="text-[#92adc9] text-xs mt-1">30天内到期</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 分隔线 -->
      <div class="h-2 bg-[#192633]/50 w-full my-2"></div>

      <!-- 客户任务 -->
      <div class="mt-2">
        <div class="flex items-center gap-2 px-4 pb-4 pt-2">
          <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01" />
          </svg>
          <h3 class="text-white text-lg font-bold leading-tight tracking-[-0.015em]">客户任务</h3>
        </div>

        <!-- 紧急任务区域 -->
        <div v-if="urgentTasks.length > 0" class="flex flex-col w-full px-4">
          <div class="pb-2 pt-2 flex items-center gap-2">
            <svg class="w-5 h-5 text-red-500 animate-pulse" fill="currentColor" viewBox="0 0 24 24">
              <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"/>
            </svg>
            <h3 class="text-white text-base font-bold leading-tight">客户在场，请立即处理！</h3>
          </div>

          <!-- 紧急任务卡片 -->
          <div v-for="task in urgentTasks" :key="task.id" class="pb-3">
            <div
              class="flex flex-col rounded-xl bg-gradient-to-br from-[#1e293b] to-[#0f172a] border border-red-500/30 shadow-lg relative overflow-hidden cursor-pointer hover:border-red-500/50 transition-colors"
              @click="goToTaskDetail(task.id)"
            >
              <!-- 紧急标识条 -->
              <div class="absolute left-0 top-0 bottom-0 w-1.5 bg-red-500"></div>

              <div class="p-4 flex flex-col gap-3">
                <div class="flex justify-between items-start">
                  <div class="flex flex-col gap-1">
                    <div class="flex items-center gap-2">
                      <span class="bg-red-500/20 text-red-400 text-[10px] font-bold px-2 py-0.5 rounded uppercase tracking-wider border border-red-500/20">
                        临柜专属
                      </span>
                      <span class="bg-primary/20 text-primary text-[10px] font-bold px-2 py-0.5 rounded uppercase tracking-wider border border-primary/20">
                        AI 洞察
                      </span>
                    </div>
                    <h4 class="text-white text-lg font-bold mt-1">{{ task.title }}</h4>
                  </div>
                  <svg class="w-6 h-6 text-red-400 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                  </svg>
                </div>

                <p class="text-[#92adc9] text-sm leading-relaxed">
                  {{ task.description }}
                  <br/>
                  <span class="text-xs opacity-70">触发原因: {{ task.triggerSource || 'AI 自动生成' }}</span>
                </p>

                <!-- 操作按钮 -->
                <div class="flex gap-2 mt-2 justify-end" @click.stop>
                  <button class="cursor-pointer items-center justify-center rounded-lg h-8 px-3 bg-surface-highlight hover:bg-[#2f4055] text-white text-xs font-medium border border-gray-700 transition-colors">
                    <span>已完成</span>
                  </button>
                  <button
                    @click="goToTaskDetail(task.id)"
                    class="cursor-pointer items-center justify-center rounded-lg h-8 px-3 bg-primary hover:bg-primary/90 text-white text-xs font-bold shadow-md transition-colors"
                  >
                    <div class="flex items-center gap-1.5">
                      <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                      </svg>
                      <span>去办理</span>
                    </div>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 远程任务区域 -->
        <div v-if="remoteTasks.length > 0" class="flex flex-col w-full px-4 mt-4">
          <div class="pb-2 flex items-center gap-2">
            <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
            </svg>
            <h3 class="text-white text-base font-bold leading-tight">可远程跟进任务</h3>
          </div>

          <div class="flex flex-col gap-3">
            <!-- 远程任务卡片 -->
            <div
              v-for="task in remoteTasks"
              :key="task.id"
              class="bg-surface-dark rounded-xl p-4 border border-gray-800 flex flex-col gap-3 cursor-pointer hover:border-gray-600 transition-colors"
              @click="goToTaskDetail(task.id)"
            >
              <div class="flex justify-between items-start">
                <div>
                  <h4 class="text-white text-base font-bold">{{ task.title }}</h4>
                  <div class="flex items-center gap-2 mt-1">
                    <span class="text-xs text-[#92adc9]">优先级: </span>
                    <span
                      class="text-xs font-medium"
                      :class="{
                        'text-red-400': task.priority === 'P0',
                        'text-yellow-400': task.priority === 'P1',
                        'text-blue-400': task.priority === 'P2',
                        'text-gray-400': task.priority === 'P3'
                      }"
                    >
                      {{ task.priority }}
                    </span>
                    <span class="text-[#92adc9] text-[10px]">•</span>
                    <span class="text-xs text-[#92adc9]">来源: {{ task.triggerSource || 'AI 生成' }}</span>
                  </div>
                </div>
                <div class="bg-primary/10 p-1.5 rounded-lg">
                  <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                  </svg>
                </div>
              </div>

              <div class="h-px bg-gray-700/50 w-full"></div>

              <div class="flex gap-2.5 justify-end" @click.stop>
                <button class="text-[#92adc9] text-xs font-medium hover:text-white flex items-center gap-1">
                  <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
                  </svg>
                  添加到待办
                </button>
                <div class="w-px bg-gray-700 h-3.5 self-center"></div>
                <button
                  @click="goToTaskDetail(task.id)"
                  class="text-primary text-xs font-bold hover:text-primary/80 flex items-center gap-1"
                >
                  <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
                  </svg>
                  去处理
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- 无任务提示 -->
        <div v-if="tasks.length === 0" class="flex items-center justify-center p-8">
          <div class="text-center text-gray-400">
            <svg class="w-16 h-16 mx-auto mb-4 opacity-50" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
            </svg>
            <p class="text-base">暂无任务</p>
          </div>
        </div>
      </div>

      <!-- 推荐行动 -->
      <div class="mt-6 px-4 mb-8">
        <div class="flex items-center gap-2 mb-3">
          <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <h3 class="text-white text-lg font-bold leading-tight">推荐行动</h3>
        </div>
        <div class="flex flex-col gap-3">
          <div class="bg-surface-dark rounded-xl p-4 border border-white/5">
            <div class="flex items-start gap-3">
              <div class="h-12 w-12 rounded-lg bg-blue-900/30 flex items-center justify-center text-primary shrink-0 border border-primary/20">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 12l3-3 3 3 4-4M8 21l4-4 4 4M3 4h18M4 4h16v12a1 1 0 01-1 1H5a1 1 0 01-1-1V4z" />
                </svg>
              </div>
              <div class="flex-1">
                <div class="flex justify-between items-start">
                  <h4 class="text-white text-base font-bold">联系客户跟进</h4>
                  <span class="bg-green-500/20 text-green-400 text-[10px] px-2 py-0.5 rounded font-bold">推荐</span>
                </div>
                <p class="text-[#92adc9] text-xs mt-1">根据 AI 分析，该客户当前处于高意向阶段，建议尽快联系跟进。</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 底部固定按钮 -->
    <div class="fixed bottom-0 left-0 right-0 p-4 bg-gradient-to-t from-background-dark via-background-dark to-transparent pt-8 z-40">
      <button class="w-full h-12 bg-primary hover:bg-blue-600 active:scale-[0.98] transition-all rounded-xl shadow-xl shadow-primary/20 flex items-center justify-center gap-2 text-white font-bold text-sm">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
        </svg>
        <span>添加到待办事项</span>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import type { Customer } from '@/types/customer'
import type { Task } from '@/api/task'
import { getCustomer } from '@/api/customer'
import { getTasksByCustomerId } from '@/api/task'

const route = useRoute()
const router = useRouter()

const customer = ref<Customer | null>(null)
const tasks = ref<Task[]>([])
const loading = ref(true)
const error = ref('')

const customerId = ref(Number(route.params.id))

// 模拟字段（Customer 类型没有的字段）
const mockData = computed(() => ({
  aum: customer.value?.creditScore ? Number(customer.value.creditScore) * 100 : 0,
  potentialValue: customer.value?.creditScore ? Number(customer.value.creditScore) * 10 : 0,
  analysisConclusion: customer.value?.remark || '该客户信用评分良好，建议关注资产配置优化机会',
  aiMatchScore: customer.value?.creditScore || 0,
  riskLevel: customer.value?.creditLevel === 'AAA' || customer.value?.creditLevel === 'AA' ? 'AGGRESSIVE' : customer.value?.creditLevel === 'A' ? 'BALANCED' : 'CONSERVATIVE',
  source: customer.value?.customerSource || 'branch'
}))

// 紧急任务（P0 或 isUrgent，且状态为待处理或进行中）
const urgentTasks = computed(() =>
  tasks.value.filter(task =>
    (task.priority === 'P0' || task.isUrgent) &&
    (task.status === 0 || task.status === 1) // 0=待处理, 1=进行中
  )
)

// 远程任务（非紧急，且状态为待处理或进行中）
const remoteTasks = computed(() =>
  tasks.value.filter(task =>
    task.priority !== 'P0' &&
    !task.isUrgent &&
    (task.status === 0 || task.status === 1) // 0=待处理, 1=进行中
  )
)

// 格式化金额
function formatMoney(value: number | undefined): string {
  if (!value) return '0'
  if (value >= 10000) {
    return `${(value / 10000).toFixed(1)}万`
  }
  return value.toLocaleString()
}

// 获取客户等级标签
function getLevelLabel(level: string | undefined): string {
  const labels: Record<string, string> = {
    diamond: '钻石客户',
    vip: 'VIP客户',
    gold: '黄金客户',
    normal: '普通客户',
    strategic: '战略客户',
    key: '重点客户',
    general: '普通客户',
    important: '重要客户'
  }
  return labels[level || ''] || '普通客户'
}

// 获取风险偏好标签
function getRiskLevelLabel(level: string | undefined): string {
  const labels: Record<string, string> = {
    CONSERVATIVE: '保守型',
    BALANCED: '稳健型',
    AGGRESSIVE: '激进型'
  }
  return labels[level || ''] || '未知'
}

// 获取来源标签
function getSourceLabel(source: string | undefined): string {
  const labels: Record<string, string> = {
    AI_RECOMMENDATION: 'AI 推荐',
    MANUAL: '手动添加',
    SYSTEM: '系统导入',
    branch: '网点开发',
    referral: '客户推荐',
    marketing: '营销活动'
  }
  return labels[source || ''] || '未知'
}

// 返回上一页
function goBack() {
  router.back()
}

// 跳转到任务详情页
function goToTaskDetail(taskId: number) {
  router.push(`/tasks/${taskId}`)
}

// 加载客户详情和任务列表
async function loadCustomerDetail() {
  try {
    loading.value = true
    error.value = ''

    // 并行加载客户信息和任务列表
    const [customerData, tasksData] = await Promise.all([
      getCustomer(customerId.value),
      getTasksByCustomerId(customerId.value)
    ])

    customer.value = customerData
    tasks.value = tasksData
  } catch (err: any) {
    console.error('加载客户详情失败:', err)
    error.value = err.message || '加载客户详情失败'
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadCustomerDetail()
})
</script>

<style scoped>
/* 自定义样式 */
.scrollbar-hide::-webkit-scrollbar {
  display: none;
}

.scrollbar-hide {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
</style>
