/**
 * AI 海报生成 API
 */
import { request } from './request'

export interface PosterGenerateRequest {
  title: string
  subtitle: string
  templateId: number
  showBusinessCard: boolean
  businessCard?: {
    name: string
    title: string
    phone: string
    avatar: string
  }
}

export interface PosterGenerateResponse {
  imageUrl: string
  taskId: string
}

/**
 * 生成海报
 */
export const generatePoster = async (data: PosterGenerateRequest): Promise<PosterGenerateResponse> => {
  // 调用硅基流动 API 生成海报图片
  // 这里需要配置硅基流动的API地址和密钥

  const prompt = buildPrompt(data)

  try {
    // 调用文生图API
    const response = await fetch('https://api.siliconflow.cn/v1/images/generations', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${import.meta.env.VITE_SILICONFLOW_API_KEY}`,
      },
      body: JSON.stringify({
        model: 'black-forest-labs/FLUX.1-schnell',
        prompt: prompt,
        image_size: '576x1024', // 9:16 比例，接近 9:14
        num_inference_steps: 20,
        guidance_scale: 7.5,
      }),
    })

    if (!response.ok) {
      throw new Error(`API 请求失败: ${response.status}`)
    }

    const result = await response.json()

    return {
      imageUrl: result.images[0].url,
      taskId: result.id || Date.now().toString(),
    }
  } catch (error) {
    console.error('生成海报失败:', error)
    throw error
  }
}

/**
 * 构建提示词（只生成背景，不包含文字）
 */
function buildPrompt(data: PosterGenerateRequest): string {
  let prompt = `Create a clean professional financial poster background, 9:14 aspect ratio.

Style: Modern, minimal, professional financial design with smooth gradient background.
Colors: Blue gradient (#137fec to lighter blue), clean and simple.
Layout: Empty space at top for title text, gradient background throughout.`

  if (data.showBusinessCard && data.businessCard) {
    prompt += `
Include a white rounded rectangle card area at the bottom for business card information.
Card should have semi-transparent white background with subtle blur effect.`
  }

  prompt += `

NO TEXT, NO LETTERS, NO NUMBERS - only background design elements and gradient.
Quality: High resolution, professional, clean minimal design.`

  return prompt
}
