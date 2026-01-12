/**
 * 海报 Canvas 合成工具
 * 将 AI 生成的背景图与中文文字合成为最终海报
 */

export interface PosterConfig {
  backgroundUrl: string
  title: string
  subtitle: string
  showBusinessCard: boolean
  businessCard?: {
    name: string
    title: string
    phone: string
    avatar: string
  }
}

/**
 * 合成海报
 */
export async function composePoster(config: PosterConfig): Promise<string> {
  return new Promise((resolve, reject) => {
    const canvas = document.createElement('canvas')
    const ctx = canvas.getContext('2d')
    if (!ctx) {
      reject(new Error('Canvas context not supported'))
      return
    }

    // 设置画布尺寸 (9:14 比例)
    canvas.width = 576
    canvas.height = 1024

    const bgImage = new Image()
    bgImage.crossOrigin = 'anonymous'

    bgImage.onload = () => {
      try {
        // 1. 绘制背景图
        ctx.drawImage(bgImage, 0, 0, canvas.width, canvas.height)

        // 2. 绘制标题
        drawTitle(ctx, config.title, canvas.width)

        // 3. 绘制副标题
        drawSubtitle(ctx, config.subtitle, canvas.width)

        // 4. 绘制名片信息
        if (config.showBusinessCard && config.businessCard) {
          drawBusinessCard(ctx, config.businessCard, canvas.width, canvas.height)
        }

        // 5. 转换为 Data URL
        const dataUrl = canvas.toDataURL('image/png', 1.0)
        resolve(dataUrl)
      } catch (error) {
        reject(error)
      }
    }

    bgImage.onerror = () => {
      reject(new Error('Failed to load background image'))
    }

    bgImage.src = config.backgroundUrl
  })
}

/**
 * 绘制主标题
 */
function drawTitle(ctx: CanvasRenderingContext2D, title: string, canvasWidth: number) {
  ctx.save()

  // 标题样式
  ctx.fillStyle = '#FFFFFF'
  ctx.font = 'bold 48px -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif'
  ctx.textAlign = 'center'
  ctx.shadowColor = 'rgba(0, 0, 0, 0.3)'
  ctx.shadowBlur = 10
  ctx.shadowOffsetY = 2

  // 处理换行
  const lines = title.split('\n')
  const startY = 180
  const lineHeight = 60

  lines.forEach((line, index) => {
    ctx.fillText(line.trim(), canvasWidth / 2, startY + index * lineHeight)
  })

  ctx.restore()
}

/**
 * 绘制副标题
 */
function drawSubtitle(ctx: CanvasRenderingContext2D, subtitle: string, canvasWidth: number) {
  ctx.save()

  // 副标题样式
  ctx.fillStyle = '#FFFFFF'
  ctx.font = '28px -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif'
  ctx.textAlign = 'center'
  ctx.shadowColor = 'rgba(0, 0, 0, 0.2)'
  ctx.shadowBlur = 8
  ctx.shadowOffsetY = 1

  ctx.fillText(subtitle, canvasWidth / 2, 320)

  ctx.restore()
}

/**
 * 绘制名片信息
 */
function drawBusinessCard(
  ctx: CanvasRenderingContext2D,
  businessCard: { name: string; title: string; phone: string; avatar: string },
  canvasWidth: number,
  canvasHeight: number
) {
  ctx.save()

  // 名片区域
  const cardY = canvasHeight - 280
  const cardHeight = 240
  const padding = 40

  // 绘制半透明背景
  ctx.fillStyle = 'rgba(255, 255, 255, 0.15)'
  ctx.filter = 'blur(10px)'
  roundRect(ctx, padding, cardY, canvasWidth - padding * 2, cardHeight, 20)
  ctx.fill()

  ctx.filter = 'none'

  // 绘制白色边框
  ctx.strokeStyle = 'rgba(255, 255, 255, 0.3)'
  ctx.lineWidth = 2
  roundRect(ctx, padding, cardY, canvasWidth - padding * 2, cardHeight, 20)
  ctx.stroke()

  // 文字样式
  ctx.fillStyle = '#FFFFFF'
  ctx.shadowColor = 'rgba(0, 0, 0, 0.3)'
  ctx.shadowBlur = 4

  // 名字
  ctx.font = 'bold 32px -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif'
  ctx.fillText(businessCard.name, padding + 30, cardY + 60)

  // 职位
  ctx.font = '24px -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif'
  ctx.fillStyle = 'rgba(255, 255, 255, 0.9)'
  ctx.fillText(businessCard.title, padding + 30, cardY + 100)

  // 电话
  ctx.font = 'bold 28px -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif'
  ctx.fillStyle = '#FFFFFF'
  ctx.fillText(businessCard.phone, padding + 30, cardY + 180)

  // 绘制二维码占位符（白色方框）
  const qrSize = 120
  const qrX = canvasWidth - padding - qrSize - 30
  const qrY = cardY + (cardHeight - qrSize) / 2

  ctx.fillStyle = '#FFFFFF'
  ctx.shadowBlur = 8
  roundRect(ctx, qrX, qrY, qrSize, qrSize, 10)
  ctx.fill()

  // QR 码内部装饰（简单的格子图案）
  ctx.fillStyle = '#137fec'
  ctx.shadowBlur = 0
  const gridSize = 8
  const cellSize = qrSize / gridSize
  for (let i = 0; i < gridSize; i++) {
    for (let j = 0; j < gridSize; j++) {
      if ((i + j) % 2 === 0) {
        ctx.fillRect(qrX + i * cellSize, qrY + j * cellSize, cellSize, cellSize)
      }
    }
  }

  ctx.restore()
}

/**
 * 绘制圆角矩形
 */
function roundRect(
  ctx: CanvasRenderingContext2D,
  x: number,
  y: number,
  width: number,
  height: number,
  radius: number
) {
  ctx.beginPath()
  ctx.moveTo(x + radius, y)
  ctx.lineTo(x + width - radius, y)
  ctx.quadraticCurveTo(x + width, y, x + width, y + radius)
  ctx.lineTo(x + width, y + height - radius)
  ctx.quadraticCurveTo(x + width, y + height, x + width - radius, y + height)
  ctx.lineTo(x + radius, y + height)
  ctx.quadraticCurveTo(x, y + height, x, y + height - radius)
  ctx.lineTo(x, y + radius)
  ctx.quadraticCurveTo(x, y, x + radius, y)
  ctx.closePath()
}
