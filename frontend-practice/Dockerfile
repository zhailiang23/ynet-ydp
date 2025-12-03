# 使用 Node.js Alpine 镜像
FROM node:20-alpine

# 设置工作目录
WORKDIR /app

# 安装 pnpm
RUN npm install -g pnpm

# 配置 pnpm 使用国内镜像源
RUN pnpm config set registry https://registry.npmmirror.com

# 复制 package 文件
COPY package.json pnpm-lock.yaml ./

# 安装依赖
RUN pnpm install --frozen-lockfile

# 复制源代码
COPY . .

# 构建应用 (standalone 模式)
RUN pnpm run build

# 复制 Next.js standalone 模式所需的静态文件
# standalone 模式不会自动包含 public 和 .next/static，需要手动复制
RUN cp -r public .next/standalone/public && \
    cp -r .next/static .next/standalone/.next/static

# 设置为生产环境
ENV NODE_ENV=production

# 清理不需要的文件以减小镜像大小
RUN rm -rf node_modules .git

# 重新安装仅生产依赖（可选，Next.js standalone 已包含）
# RUN pnpm install --prod --frozen-lockfile

# 暴露端口
EXPOSE 3000

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD node -e "require('http').get('http://localhost:3000/', (res) => { process.exit(res.statusCode === 200 ? 0 : 1); }).on('error', () => process.exit(1));"

# 启动应用（Next.js standalone 模式）
CMD ["node", ".next/standalone/server.js"]
