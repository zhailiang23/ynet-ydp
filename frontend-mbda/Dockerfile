################################################################################
# Dockerfile for frontend-mbda (金融产品展业平台)
#
# 多环境支持: dev, fat, uat, pro
# 通过环境变量 VITE_ENV 控制加载哪个配置文件
#
# 构建命令:
#   docker build --build-arg VITE_ENV=fat -t frontend-mbda:latest .
#
# 运行命令:
#   docker run -d --name frontend-mbda -e VITE_ENV=fat -p 5667:80 frontend-mbda:latest
################################################################################

# 阶段 1: 构建阶段
FROM node:20-alpine AS builder

WORKDIR /workspace

# 配置 npm 使用淘宝镜像源
RUN npm config set registry https://registry.npmmirror.com

# 复制 package.json 和 package-lock.json
COPY package.json package-lock.json ./

# 安装依赖
RUN npm ci

# 复制源代码
COPY . .

# 环境变量 (默认为 dev)
ARG VITE_ENV=dev

# 构建应用 (根据 VITE_ENV 加载对应的 .env 文件)
# 增加 Node.js 内存限制到 4GB
# 使用 build:ci 跳过 TypeScript 类型检查，加快 CI/CD 构建速度
RUN echo "Building for environment: $VITE_ENV" && \
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
    NODE_OPTIONS="--max-old-space-size=4096" npm run build:ci

# 阶段 2: 运行阶段 (使用 Nginx)
# 使用 DaoCloud 国内镜像加速
FROM docker.m.daocloud.io/library/nginx:1.27-alpine

WORKDIR /usr/share/nginx/html

# 删除默认的 nginx 配置和文件
RUN rm -rf /etc/nginx/conf.d/default.conf /usr/share/nginx/html/*

# 复制自定义 nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 从构建阶段复制打包后的文件
COPY --from=builder /workspace/dist /usr/share/nginx/html

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
