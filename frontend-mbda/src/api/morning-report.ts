/**
 * 早报生成 API
 */

export interface MorningReportRequest {
  categories: string[] // 选中的分类
  productName?: string // 关注的产品名称
}

export interface MorningReportResponse {
  greeting: string // 早安寄语
  macroNews: Array<{
    title: string
    description: string
  }>
  marketData: Array<{
    name: string
    value: string
    change: string
    isPositive: boolean
  }>
  productAnalysis?: {
    productName: string
    sentiment: '利好' | '中性' | '利空'
    analysis: string
  }
  generatedAt: string
}

/**
 * 生成早报
 */
export const generateMorningReport = async (
  data: MorningReportRequest
): Promise<MorningReportResponse> => {
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
              '你是一个专业的财经分析师，擅长撰写简洁、专业的财经早报。请严格按照JSON格式返回内容。',
          },
          {
            role: 'user',
            content: prompt,
          },
        ],
        temperature: 0.7,
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
    console.error('生成早报失败:', error)
    throw error
  }
}

/**
 * 构建提示词
 */
function buildPrompt(data: MorningReportRequest): string {
  const today = new Date()
  const dateStr = `${today.getMonth() + 1}月${today.getDate()}日`
  const weekDays = ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
  const weekDay = weekDays[today.getDay()]

  let prompt = `请生成一份${dateStr}（${weekDay}）的财经早报，需要包含以下内容分类：${data.categories.join('、')}。`

  if (data.productName) {
    prompt += `\n特别关注产品：${data.productName}`
  }

  prompt += `

请严格按照以下JSON格式返回内容（不要包含markdown代码块标记）：

{
  "greeting": "早安寄语，1-2句话，简洁专业，可以提及当前市场情况和今日建议",
  "macroNews": [
    {
      "title": "宏观新闻标题1",
      "description": "简短描述，1-2句话"
    },
    {
      "title": "宏观新闻标题2",
      "description": "简短描述，1-2句话"
    }
  ],
  "marketData": [
    {
      "name": "上证指数",
      "value": "具体数值",
      "change": "+X.XX%或-X.XX%",
      "isPositive": true或false
    },
    {
      "name": "恒生指数",
      "value": "具体数值",
      "change": "+X.XX%或-X.XX%",
      "isPositive": true或false
    },
    {
      "name": "现货黄金",
      "value": "具体数值",
      "change": "+X.XX%或-X.XX%",
      "isPositive": true或false
    }
  ]${
    data.productName
      ? `,
  "productAnalysis": {
    "productName": "${data.productName}",
    "sentiment": "利好、中性或利空",
    "analysis": "针对该产品的专业分析，2-3句话，包含投资建议"
  }`
      : ''
  }
}

注意事项：
1. 所有内容必须真实、专业、准确
2. 数据尽量接近真实市场情况
3. 分析要有理有据，具有可操作性
4. 语言简洁、专业，符合金融从业者风格
5. 必须严格按照JSON格式返回，不要添加任何markdown标记或其他文本`

  return prompt
}

/**
 * 解析生成的内容
 */
function parseGeneratedContent(content: string): Omit<MorningReportResponse, 'generatedAt'> {
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
      greeting: parsed.greeting || '早安！',
      macroNews: parsed.macroNews || [],
      marketData: parsed.marketData || [],
      productAnalysis: parsed.productAnalysis,
    }
  } catch (error) {
    console.error('解析生成内容失败:', error, content)
    // 返回默认值
    return {
      greeting: '早安！市场行情持续关注中。',
      macroNews: [
        {
          title: '市场动态',
          description: '请稍后重新生成早报。',
        },
      ],
      marketData: [],
    }
  }
}
