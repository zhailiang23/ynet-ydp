/**
 * 营销话术生成 API
 */

export interface MarketingScriptRequest {
  scenario: string // 沟通场景：product_recommend, objection_handling, customer_maintenance, invite_meeting
  customerType: string // 客户画像：high_net_worth, young_professional, entrepreneur, custom
  customCustomerDesc?: string // 自定义客户描述
  keyInfo?: string // 关键信息（产品名称、异议点等）
}

export interface MarketingScript {
  style: string // 话术风格：professional, emotional, friendly, etc.
  styleBadge: string // 风格标签文本
  styleClass: string // 风格标签样式类
  suitableFor: string // 适用场景
  content: string // 话术内容
}

export interface MarketingScriptResponse {
  scripts: MarketingScript[]
  generatedAt: string
}

/**
 * 生成营销话术
 */
export const generateMarketingScript = async (
  data: MarketingScriptRequest
): Promise<MarketingScriptResponse> => {
  try {
    // 构建提示词
    const prompt = buildPrompt(data)

    // 调用硅基流动 API
    const response = await fetch('https://api.siliconflow.cn/v1/chat/completions', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${import.meta.env.VITE_SILICONFLOW_API_KEY}`,
      },
      body: JSON.stringify({
        model: 'Qwen/Qwen2.5-7B-Instruct',
        messages: [
          {
            role: 'system',
            content:
              '你是一个专业的银行营销话术专家，擅长根据不同场景和客户类型生成合适的沟通话术。请严格按照JSON格式返回内容。',
          },
          {
            role: 'user',
            content: prompt,
          },
        ],
        temperature: 0.8,
        max_tokens: 2000,
      }),
    })

    if (!response.ok) {
      throw new Error(`API 请求失败: ${response.status}`)
    }

    const result = await response.json()
    const content = result.choices[0].message.content

    // 解析生成的JSON内容
    const parsedContent = parseGeneratedContent(content)

    return {
      ...parsedContent,
      generatedAt: new Date().toISOString(),
    }
  } catch (error) {
    console.error('生成话术失败:', error)
    throw error
  }
}

/**
 * 构建提示词
 */
function buildPrompt(data: MarketingScriptRequest): string {
  // 场景映射
  const scenarioMap: Record<string, string> = {
    product_recommend: '产品推荐',
    objection_handling: '异议处理',
    customer_maintenance: '客户维护',
    invite_meeting: '邀约见面',
  }

  // 客户类型映射
  const customerTypeMap: Record<string, { name: string; traits: string }> = {
    high_net_worth: {
      name: '高净值客户',
      traits: '注重资产保值、风险偏好低、追求稳健收益、关注长期规划',
    },
    young_professional: {
      name: '年轻白领',
      traits: '追求灵活收益、风险承受能力中等、关注产品便捷性、希望快速增值',
    },
    entrepreneur: {
      name: '企业主',
      traits: '关注现金流、需要灵活调配资金、风险承受能力较强、重视实际收益',
    },
    custom: {
      name: '自定义客户',
      traits: data.customCustomerDesc || '普通客户',
    },
  }

  const scenario = scenarioMap[data.scenario] || '产品推荐'
  const customerInfo = customerTypeMap[data.customerType] || customerTypeMap.high_net_worth
  const keyInfo = data.keyInfo ? `\n关键信息：${data.keyInfo}` : ''

  const prompt = `请为以下场景生成3个不同风格的营销话术：

场景：${scenario}
客户类型：${customerInfo.name}
客户特征：${customerInfo.traits}${keyInfo}

请严格按照以下JSON格式返回内容（不要包含markdown代码块标记）：

{
  "scripts": [
    {
      "style": "professional",
      "styleBadge": "专业顾问风",
      "styleClass": "bg-blue-50 dark:bg-blue-900/40 text-blue-600 dark:text-blue-300",
      "suitableFor": "适合初次接触",
      "content": "话术内容，80-150字，要自然、专业、有说服力。直接称呼客户为'您'或'王总'等，不要使用占位符。"
    },
    {
      "style": "emotional",
      "styleBadge": "情感共鸣风",
      "styleClass": "bg-purple-50 dark:bg-purple-900/40 text-purple-600 dark:text-purple-300",
      "suitableFor": "适合维护关系",
      "content": "话术内容，80-150字，要温暖、真诚、建立情感连接。"
    },
    {
      "style": "friendly",
      "styleBadge": "亲和友好风",
      "styleClass": "bg-green-50 dark:bg-green-900/40 text-green-600 dark:text-green-300",
      "suitableFor": "适合熟悉客户",
      "content": "话术内容，80-150字，要轻松、自然、拉近距离。"
    }
  ]
}

注意事项：
1. 话术要符合银行业规范，不做夸大承诺
2. 语言要简洁、口语化、易懂
3. 针对客户特征和场景量身定制
4. 每个话术风格要明显区别
5. 如果有关键信息（产品名称、异议点等），要自然融入话术中
6. 话术要有明确的行动导向（如邀请了解、预约见面等）
7. 必须严格按照JSON格式返回，不要添加任何markdown标记或其他文本`

  return prompt
}

/**
 * 解析生成的内容
 */
function parseGeneratedContent(content: string): Omit<MarketingScriptResponse, 'generatedAt'> {
  try {
    // 移除可能的 markdown 代码块标记
    let cleanContent = content.trim()
    if (cleanContent.startsWith('```json')) {
      cleanContent = cleanContent.replace(/^```json\n/, '').replace(/\n```$/, '')
    } else if (cleanContent.startsWith('```')) {
      cleanContent = cleanContent.replace(/^```\n/, '').replace(/\n```$/, '')
    }

    const parsed = JSON.parse(cleanContent)

    return {
      scripts: parsed.scripts || [],
    }
  } catch (error) {
    console.error('解析生成内容失败:', error, content)
    // 返回默认值
    return {
      scripts: [
        {
          style: 'professional',
          styleBadge: '专业顾问风',
          styleClass: 'bg-blue-50 dark:bg-blue-900/40 text-blue-600 dark:text-blue-300',
          suitableFor: '适合初次接触',
          content: '生成失败，请重新生成。',
        },
      ],
    }
  }
}
