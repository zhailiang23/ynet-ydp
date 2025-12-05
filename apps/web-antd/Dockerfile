################################################################################
# Dockerfile for web-antd (易诚管理系统前端)
#
# 多环境支持: dev, fat, uat, pro
# 通过环境变量 VITE_ENV 控制加载哪个配置文件
#
# 构建命令 (从 frontend 根目录执行):
#   docker build --build-arg VITE_ENV=fat -t web-antd:latest -f apps/web-antd/Dockerfile .
#
# 运行命令:
#   docker run -d --name web-antd -e VITE_ENV=fat -p 5666:80 web-antd:latest
################################################################################

# 阶段 1: 构建阶段
FROM node:20-alpine AS builder

WORKDIR /workspace

# 设置 pnpm
RUN npm install -g pnpm@10.14.0

# 配置 pnpm 使用淘宝镜像源
RUN pnpm config set registry https://registry.npmmirror.com

# 复制 pnpm workspace 配置文件
COPY pnpm-workspace.yaml package.json pnpm-lock.yaml ./

# 复制所有 package.json (用于依赖解析)
COPY apps/web-antd/package.json ./apps/web-antd/
COPY packages ./packages
COPY internal ./internal

# 安装依赖 (整个 workspace)
# 注意: postinstall 会自动执行 "pnpm -r run stub --if-present"
RUN pnpm install --frozen-lockfile

# 复制 web-antd 源代码
COPY apps/web-antd ./apps/web-antd

# 环境变量 (默认为 dev)
ARG VITE_ENV=dev

# 构建应用 (根据 VITE_ENV 加载对应的 .env 文件)
# 使用 NODE_ENV=development 让 package.json exports 优先使用源码而非 dist
# 这样无需预构建所有依赖包，大幅提升构建速度
RUN cd apps/web-antd && \
    echo "Building for environment: $VITE_ENV" && \
    if [ "$VITE_ENV" = "dev" ]; then \
        cp .env.dev .env.production; \
    elif [ "$VITE_ENV" = "fat" ]; then \
        cp .env.fat .env.production; \
    elif [ "$VITE_ENV" = "uat" ]; then \
        cp .env.uat .env.production; \
    elif [ "$VITE_ENV" = "pro" ]; then \
        cp .env.pro .env.production; \
    else \
        echo "Unknown environment: $VITE_ENV, using dev as default"; \
        cp .env.dev .env.production; \
    fi && \
    NODE_ENV=development pnpm run build

# 阶段 2: 运行阶段 (使用 Nginx)
FROM nginx:1.27-alpine

WORKDIR /usr/share/nginx/html

# 删除默认的 nginx 配置和文件
RUN rm -rf /etc/nginx/conf.d/default.conf /usr/share/nginx/html/*

# 复制自定义 nginx 配置
COPY apps/web-antd/nginx.conf /etc/nginx/conf.d/default.conf

# 从构建阶段复制打包后的文件
COPY --from=builder /workspace/apps/web-antd/dist /usr/share/nginx/html

# 设置时区
ENV TZ=Asia/Shanghai

# 设置环境变量 (仅用于标识，实际构建时已确定)
ENV VITE_ENV=${VITE_ENV:-dev}

# 暴露端口 80
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
    CMD wget --no-verbose --tries=1 --spider http://localhost/health || exit 1

# 启动 Nginx
CMD ["nginx", "-g", "daemon off;"]
