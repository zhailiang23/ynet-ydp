/** @type {import('next').NextConfig} */
const nextConfig = {
  // 启用 standalone 输出模式以优化 Docker 镜像大小
  output: 'standalone',
  eslint: {
    ignoreDuringBuilds: true,
  },
  typescript: {
    ignoreBuildErrors: true,
  },
  images: {
    unoptimized: true,
  },
  // 配置实验性功能,增加代理超时时间
  experimental: {
    proxyTimeout: 900000, // 15分钟超时 (毫秒) - AI生成剧本需要较长时间
  },
  async rewrites() {
    return [
      {
        source: '/admin-api/:path*',
        destination: 'http://localhost:48080/admin-api/:path*',
      },
    ]
  },
}

export default nextConfig
