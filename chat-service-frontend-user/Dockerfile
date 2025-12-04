# 阶段 1: 构建阶段
FROM node:20-alpine AS builder

# 环境变量：支持 dev, fat, uat, pro
ARG APP_ENV=dev
ENV APP_ENV=${APP_ENV}

# 设置工作目录
WORKDIR /app

# 复制 package 文件
COPY package*.json ./

# 安装依赖（使用 legacy-peer-deps 解决 webpack 版本冲突）
RUN npm install --legacy-peer-deps

# 复制源代码
COPY . .

# 根据 APP_ENV 环境变量构建 H5 版本
RUN NODE_ENV=production APP_ENV=${APP_ENV} npm run build:h5

# 阶段 2: 运行阶段
FROM nginx:alpine

# 复制构建产物
COPY --from=builder /app/dist /usr/share/nginx/html

# 复制 nginx 配置
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 暴露端口
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=10s --start-period=10s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost/ || exit 1

# 启动 nginx
CMD ["nginx", "-g", "daemon off;"]
