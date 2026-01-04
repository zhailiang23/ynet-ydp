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

      <!-- 多维资产配置诊断 -->
      <div class="mt-2">
        <div class="flex items-center gap-2 px-4 pb-4 pt-2">
          <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z" />
          </svg>
          <h3 class="text-white text-lg font-bold leading-tight tracking-[-0.015em]">多维资产配置诊断</h3>
        </div>

        <div class="px-4 flex flex-col gap-3">
          <!-- AI 综合评分 -->
          <div class="bg-gradient-to-r from-surface-dark to-[#1e293b] rounded-xl p-4 border border-white/10 relative overflow-hidden">
            <div class="flex justify-between items-start z-10 relative">
              <div>
                <p class="text-slate-400 text-xs font-medium uppercase tracking-wider mb-1">AI 综合评分</p>
                <div class="flex items-baseline gap-2">
                  <span class="text-4xl font-bold text-white">72</span>
                  <span class="text-sm text-yellow-400 font-medium">待优化</span>
                </div>
                <p class="text-white text-sm mt-3 font-semibold">存在明显权益类资产缺口</p>
                <p class="text-slate-400 text-xs mt-1">当前配置过于保守，可能无法跑赢长期通胀。</p>
              </div>
              <div class="h-14 w-14 rounded-full border-4 border-yellow-500/30 flex items-center justify-center border-t-yellow-500">
                <svg class="w-6 h-6 text-yellow-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                </svg>
              </div>
            </div>
          </div>

          <!-- 配置缺口与风险敞口 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden" open>
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-blue-500/10 text-blue-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 6l3 1m0 0l-3 9a5.002 5.002 0 006.001 0M6 7l3 9M6 7l6-2m6 2l3-1m-3 1l-3 9a5.002 5.002 0 006.001 0M18 7l3 9m-3-9l-6-2m0-2v2m0 16V5m0 16H9m3 0h3" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">配置缺口与风险敞口</span>
                  <span class="text-slate-500 text-[10px]">权益类偏低 · 固收类过高</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <div class="mt-3 flex gap-4 items-center">
                <div class="flex-1 space-y-3">
                  <div>
                    <div class="flex justify-between text-[10px] text-slate-400 mb-1">
                      <span>权益类 (股票/基金)</span>
                      <span class="text-red-400 font-medium">缺口 20%</span>
                    </div>
                    <div class="h-1.5 w-full bg-white/10 rounded-full overflow-hidden flex">
                      <div class="bg-blue-500 h-full w-[25%]"></div>
                      <div class="bg-blue-500/30 h-full w-[20%] border-l border-dashed border-white/30"></div>
                    </div>
                    <div class="flex justify-between text-[10px] mt-1">
                      <span class="text-white">当前: 25%</span>
                      <span class="text-blue-300">目标: 45%</span>
                    </div>
                  </div>
                  <div>
                    <div class="flex justify-between text-[10px] text-slate-400 mb-1">
                      <span>固收类 (存款/债券)</span>
                      <span class="text-yellow-400 font-medium">过配 15%</span>
                    </div>
                    <div class="h-1.5 w-full bg-white/10 rounded-full overflow-hidden relative">
                      <div class="bg-purple-500 h-full w-[65%]"></div>
                      <div class="absolute right-[35%] h-full w-0.5 bg-white z-10"></div>
                    </div>
                    <div class="flex justify-between text-[10px] mt-1">
                      <span class="text-white">当前: 65%</span>
                      <span class="text-purple-300">目标: 50%</span>
                    </div>
                  </div>
                </div>
              </div>
              <div class="mt-3 bg-blue-500/10 rounded-lg p-2.5 flex gap-2 items-start">
                <svg class="w-4 h-4 text-primary mt-0.5 shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
                </svg>
                <p class="text-xs text-blue-100 leading-relaxed">
                  <span class="font-bold">AI 建议：</span> 建议逐步将定存到期资金转向低波动权益基金，以修复20%的权益缺口，提升组合长期收益弹性。
                </p>
              </div>
            </div>
          </details>

          <!-- 目标达成诊断 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden">
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-green-500/10 text-green-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 21v-4m0 0V5a2 2 0 012-2h6.5l1 1H21l-3 6 3 6h-8.5l-1-1H5a2 2 0 00-2 2zm9-13.5V9" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">目标达成诊断</span>
                  <span class="text-slate-500 text-[10px]">子女教育达成率高</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <div class="space-y-4 mt-3">
                <div>
                  <div class="flex justify-between items-end mb-1">
                    <span class="text-xs text-white font-medium">子女教育金 (5年)</span>
                    <span class="text-xs text-green-400 font-bold">达成概率 92%</span>
                  </div>
                  <div class="h-2 w-full bg-white/10 rounded-full overflow-hidden">
                    <div class="bg-green-500 h-full w-[85%]"></div>
                  </div>
                  <p class="text-[10px] text-slate-400 mt-1">当前进度良好，无需调整。</p>
                </div>
                <div>
                  <div class="flex justify-between items-end mb-1">
                    <span class="text-xs text-white font-medium">品质养老 (15年)</span>
                    <span class="text-xs text-yellow-400 font-bold">达成概率 65%</span>
                  </div>
                  <div class="h-2 w-full bg-white/10 rounded-full overflow-hidden">
                    <div class="bg-yellow-500 h-full w-[55%]"></div>
                  </div>
                  <p class="text-[10px] text-slate-400 mt-1">受通胀影响，需增加增值型资产配置以确保购买力。</p>
                </div>
              </div>
            </div>
          </details>

          <!-- 现金流匹配诊断 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden">
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-teal-500/10 text-teal-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 9V7a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2m2 4h10a2 2 0 002-2v-6a2 2 0 00-2-2H9a2 2 0 00-2 2v6a2 2 0 002 2zm7-5a2 2 0 11-4 0 2 2 0 014 0z" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">现金流匹配诊断</span>
                  <span class="text-slate-500 text-[10px]">流动性极佳</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <p class="text-xs text-slate-300 mt-3 leading-relaxed">
                客户的月度现金流非常健康。考虑到未来12个月没有大额刚性支出，当前持有的现金比例（45%）过高，造成了资金闲置。
              </p>
              <div class="mt-2 flex items-center gap-2">
                <svg class="w-4 h-4 text-teal-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span class="text-xs text-teal-400">建议预留6个月支出作为备用金，其余投入市场。</span>
              </div>
            </div>
          </details>

          <!-- 税务优化诊断 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden">
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-indigo-500/10 text-indigo-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">税务优化诊断</span>
                  <span class="text-slate-500 text-[10px]">存在优化空间</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <p class="text-xs text-slate-300 mt-3">
                您目前的资产配置主要集中在普通应税账户。AI 建议利用年金保险或个人养老金账户的税收递延功能，每年可潜在节省税务支出约 ¥12,000。
              </p>
            </div>
          </details>

          <!-- 行为偏好诊断 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden">
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-pink-500/10 text-pink-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">行为偏好诊断</span>
                  <span class="text-slate-500 text-[10px]">发现"损失厌恶"偏差</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <p class="text-xs text-slate-300 mt-3">
                历史交易数据显示，您在市场下跌5%时赎回频率较高，表明存在较强的"损失厌恶"。建议配置定投策略或保本浮动收益产品，以减少择时冲动。
              </p>
            </div>
          </details>

          <!-- 市场适应性诊断 -->
          <details class="group bg-surface-dark rounded-xl border border-white/5 overflow-hidden">
            <summary class="flex items-center justify-between p-4 cursor-pointer hover:bg-white/5 transition-colors">
              <div class="flex items-center gap-3">
                <div class="p-1.5 rounded-md bg-orange-500/10 text-orange-400">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                  </svg>
                </div>
                <div class="flex flex-col">
                  <span class="text-white font-semibold text-sm">市场适应性诊断</span>
                  <span class="text-slate-500 text-[10px]">牛市踏空风险高</span>
                </div>
              </div>
              <svg class="w-5 h-5 text-slate-400 transition-transform group-open:rotate-180" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
              </svg>
            </summary>
            <div class="px-4 pb-4 pt-0 border-t border-white/5 bg-black/20">
              <div class="grid grid-cols-3 gap-2 mt-3 text-center">
                <div class="bg-white/5 rounded p-2">
                  <p class="text-[10px] text-slate-400">震荡市</p>
                  <p class="text-xs text-green-400 font-bold mt-1">表现优异</p>
                </div>
                <div class="bg-white/5 rounded p-2">
                  <p class="text-[10px] text-slate-400">熊市</p>
                  <p class="text-xs text-green-400 font-bold mt-1">抗跌性强</p>
                </div>
                <div class="bg-white/5 rounded p-2 border border-orange-500/30">
                  <p class="text-[10px] text-slate-400">牛市</p>
                  <p class="text-xs text-orange-400 font-bold mt-1">跑输大盘</p>
                </div>
              </div>
              <p class="text-[10px] text-slate-400 mt-2 text-center">当前配置在牛市环境下收益获取能力较弱。</p>
            </div>
          </details>
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
      <button class="w-full h-12 bg-primary hover:bg-primary-dark active:scale-[0.98] transition-all rounded-xl shadow-xl shadow-primary/20 flex items-center justify-center gap-2 text-white font-bold text-base">
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
import { getCustomer } from '@/api/customer'

const route = useRoute()
const router = useRouter()

const customer = ref<Customer | null>(null)
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

// 加载客户详情
async function loadCustomerDetail() {
  try {
    loading.value = true
    error.value = ''
    const data = await getCustomer(customerId.value)
    customer.value = data
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
