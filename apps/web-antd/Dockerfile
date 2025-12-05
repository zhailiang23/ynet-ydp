################################################################################
# Dockerfile for web-antd (易诚管理系统前端)
#
# 多环境支持: dev, fat, uat, pro
# 通过环境变量 VITE_ENV 控制加载哪个配置文件
#
# 构建命令:
#   docker build -t web-antd:latest .
#
# 运行命令:
#   docker run -d --name web-antd -e VITE_ENV=fat -p 5666:80 web-antd:latest
################################################################################

# 阶段 1: 构建阶段
FROM node:20-alpine AS builder

WORKDIR /app

# 设置 pnpm
RUN npm install -g pnpm@10.14.0

# 配置 pnpm 使用淘宝镜像源
RUN pnpm config set registry https://registry.npmmirror.com

# 复制 package.json 和 lockfile
COPY package.json pnpm-lock.yaml ./

# 安装依赖
RUN pnpm install --frozen-lockfile

# 复制项目文件
COPY . .

# 环境变量 (默认为 dev)
ARG VITE_ENV=dev

# 构建应用 (根据 VITE_ENV 加载对应的 .env 文件)
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
    pnpm run build

# 阶段 2: 运行阶段 (使用 Nginx)
FROM nginx:1.27-alpine

WORKDIR /usr/share/nginx/html

# 删除默认的 nginx 配置和文件
RUN rm -rf /etc/nginx/conf.d/default.conf /usr/share/nginx/html/*

# 复制自定义 nginx 配置
COPY <<'EOF' /etc/nginx/conf.d/default.conf
server {
    listen 80;
    server_name localhost;

    # Gzip 压缩
    gzip on;
    gzip_vary on;
    gzip_min_length 1024;
    gzip_comp_level 6;
    gzip_types text/plain text/css text/xml text/javascript application/json application/javascript application/xml+rss application/rss+xml font/truetype font/opentype application/vnd.ms-fontobject image/svg+xml;

    # 根目录
    root /usr/share/nginx/html;
    index index.html;

    # 静态资源缓存 (1 年)
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg|woff|woff2|ttf|eot)$ {
        expires 1y;
        add_header Cache-Control "public, immutable";
    }

    # SPA 路由支持 (Vue Router history 模式)
    location / {
        try_files $uri $uri/ /index.html;
    }

    # 健康检查端点
    location /health {
        access_log off;
        return 200 "healthy\n";
        add_header Content-Type text/plain;
    }
}
EOF

# 从构建阶段复制打包后的文件
COPY --from=builder /app/dist /usr/share/nginx/html

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
