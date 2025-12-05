# frontend-practice 多环境部署指南

## 概述

从 2024-12 开始，frontend-practice 支持多环境部署，通过 Docker 构建参数在构建时注入环境配置。

## 问题背景

### 之前的问题

在 FAT 环境（192.168.153.111）部署时，前端调用的 API 地址是 `localhost:48080`，导致无法访问后端服务。

**根本原因**:
- Next.js 的 `NEXT_PUBLIC_*` 环境变量在**构建时**被注入到前端代码中
- 之前使用 `.env.local` 配置 `NEXT_PUBLIC_API_BASE_URL=http://localhost:48080/admin-api`
- 这个值被打包到 Docker 镜像中，运行时无法修改

### 解决方案

采用多环境配置文件 + Docker 构建参数的方式：
1. 为每个环境创建独立的配置文件（`.env.fat`, `.env.uat`, `.env.pro`）
2. Dockerfile 通过 `ARG BUILD_ENV` 接收环境参数
3. 构建时将对应的 `.env.{BUILD_ENV}` 复制为 `.env.production`
4. Next.js 在构建时读取 `.env.production` 并注入环境变量

## 环境配置文件

### 1. FAT 环境 (`.env.fat`)

```bash
# FAT 环境配置
# 部署服务器: 192.168.153.111

# 后端 API 地址 (FAT 环境)
NEXT_PUBLIC_API_BASE_URL=http://192.168.153.111:48080/admin-api

# 默认租户ID
NEXT_PUBLIC_TENANT_ID=1

# Node.js 环境
NODE_ENV=production

# 时区配置
TZ=Asia/Shanghai
```

### 2. UAT 环境 (`.env.uat`)

```bash
# UAT 环境配置
# 部署服务器: TODO (待配置)

# 后端 API 地址 (UAT 环境)
NEXT_PUBLIC_API_BASE_URL=http://YOUR_UAT_SERVER:48080/admin-api

# 默认租户ID
NEXT_PUBLIC_TENANT_ID=1

# Node.js 环境
NODE_ENV=production

# 时区配置
TZ=Asia/Shanghai
```

### 3. 生产环境 (`.env.pro`)

```bash
# 生产环境配置
# 部署服务器: TODO (待配置)

# 后端 API 地址 (生产环境)
NEXT_PUBLIC_API_BASE_URL=http://YOUR_PRO_SERVER:48080/admin-api

# 默认租户ID
NEXT_PUBLIC_TENANT_ID=1

# Node.js 环境
NODE_ENV=production

# 时区配置
TZ=Asia/Shanghai
```

## Dockerfile 关键配置

```dockerfile
# 定义构建参数（用于指定环境）
ARG BUILD_ENV=fat

# 复制源代码和所有环境配置文件
COPY . .

# 根据 BUILD_ENV 参数选择对应的环境配置文件
# 将 .env.{BUILD_ENV} 复制为 .env.production，Next.js 会在构建时读取它
RUN echo "Building for environment: ${BUILD_ENV}" && \
    if [ -f ".env.${BUILD_ENV}" ]; then \
        cp .env.${BUILD_ENV} .env.production; \
        echo "Using .env.${BUILD_ENV} as .env.production"; \
        cat .env.production; \
    else \
        echo "Warning: .env.${BUILD_ENV} not found, using default .env.production"; \
    fi

# 构建应用 (standalone 模式)
RUN pnpm run build
```

## GitLab CI/CD 配置

### FAT 环境自动部署

`.gitlab-ci.yml` 中的构建命令：

```yaml
script:
  - echo "Building Docker image for environment fat..."
  - docker build --build-arg BUILD_ENV=fat -t ${IMAGE_NAME}:${CI_PIPELINE_IID} .
  - docker tag ${IMAGE_NAME}:${CI_PIPELINE_IID} ${IMAGE_NAME}:latest
  - echo "Pushing images to Harbor..."
  - docker push ${IMAGE_NAME}:${CI_PIPELINE_IID}
  - docker push ${IMAGE_NAME}:latest
  - echo "Image build and push completed successfully"
  - echo "Environment fat with API base URL http://192.168.153.111:48080/admin-api"
```

### UAT/生产环境部署（待实现）

如需支持 UAT 或生产环境，修改 `.gitlab-ci.yml`：

```yaml
# 根据分支选择环境
variables:
  BUILD_ENV: |
    if [ "$CI_COMMIT_BRANCH" == "master" ]; then
      echo "fat"
    elif [ "$CI_COMMIT_BRANCH" == "uat" ]; then
      echo "uat"
    elif [ "$CI_COMMIT_BRANCH" == "production" ]; then
      echo "pro"
    else
      echo "fat"
    fi

script:
  - BUILD_ENV_VALUE=$(eval $BUILD_ENV)
  - echo "Building Docker image for environment ${BUILD_ENV_VALUE}..."
  - docker build --build-arg BUILD_ENV=${BUILD_ENV_VALUE} -t ${IMAGE_NAME}:${CI_PIPELINE_IID} .
```

## 本地构建测试

### 构建不同环境的镜像

```bash
# 构建 FAT 环境镜像
docker build --build-arg BUILD_ENV=fat -t frontend-practice:fat .

# 构建 UAT 环境镜像
docker build --build-arg BUILD_ENV=uat -t frontend-practice:uat .

# 构建生产环境镜像
docker build --build-arg BUILD_ENV=pro -t frontend-practice:pro .
```

### 运行镜像

```bash
# 运行 FAT 环境容器
docker run -d -p 3000:3000 --name frontend-practice-fat frontend-practice:fat

# 验证 API 地址
docker logs frontend-practice-fat 2>&1 | grep -i "api"
```

## 修改环境配置

### 修改 FAT 环境 API 地址

1. 编辑 `.env.fat` 文件：
   ```bash
   NEXT_PUBLIC_API_BASE_URL=http://NEW_SERVER_IP:48080/admin-api
   ```

2. 提交并推送到 GitLab：
   ```bash
   git add frontend-practice/.env.fat
   git commit -m "chore: 更新 FAT 环境 API 地址"
   git push origin master
   ```

3. GitLab CI/CD 会自动触发构建和部署

### 添加新环境

1. 创建新的环境配置文件，例如 `.env.staging`：
   ```bash
   NEXT_PUBLIC_API_BASE_URL=http://STAGING_SERVER:48080/admin-api
   NEXT_PUBLIC_TENANT_ID=1
   NODE_ENV=production
   TZ=Asia/Shanghai
   ```

2. 修改 `.gitlab-ci.yml`，添加新环境的构建逻辑

3. 提交并推送代码

## 验证部署

### 检查容器环境变量

```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 检查容器日志
docker logs frontend-practice --tail 50

# 进入容器检查构建时的环境变量（已经打包到代码中）
docker exec -it frontend-practice sh
# 查看构建日志（如果有保存）
```

### 浏览器验证

1. 访问 http://192.168.153.111:3000
2. 打开浏览器开发者工具 -> Network
3. 检查 API 请求地址是否为 `http://192.168.153.111:48080/admin-api/...`

### 常见问题排查

**问题 1: API 请求仍然是 localhost**
- 原因: 使用了旧镜像
- 解决: 等待新的 Pipeline 完成，确保使用最新镜像

**问题 2: 构建失败，提示找不到 .env.fat**
- 原因: `.env.fat` 文件未提交到 Git
- 解决: `git add frontend-practice/.env.fat && git commit && git push`

**问题 3: API 请求跨域错误**
- 原因: 后端 CORS 配置未允许新的 Origin
- 解决: 在后端配置中添加 `http://192.168.153.111:3000` 到允许的 Origin 列表

## 架构图

```
┌─────────────────────────────────────────────────────────────┐
│                      Git 仓库                               │
│  - .env.fat (192.168.153.111:48080)                        │
│  - .env.uat (待配置)                                        │
│  - .env.pro (待配置)                                        │
└─────────────────────────────────────────────────────────────┘
                            │
                            │ git push
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                   GitLab CI/CD                              │
│  docker build --build-arg BUILD_ENV=fat                     │
└─────────────────────────────────────────────────────────────┘
                            │
                            │ 构建时
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                   Dockerfile                                │
│  1. 接收 BUILD_ENV 参数                                     │
│  2. cp .env.${BUILD_ENV} → .env.production                  │
│  3. pnpm run build (Next.js 读取 .env.production)          │
│  4. 环境变量注入到前端代码                                  │
└─────────────────────────────────────────────────────────────┘
                            │
                            │ 推送镜像
                            ▼
┌─────────────────────────────────────────────────────────────┐
│                   Harbor 镜像仓库                            │
│  192.168.152.56/ai-coach/frontend-practice:xxx              │
└─────────────────────────────────────────────────────────────┘
                            │
                            │ 部署
                            ▼
┌─────────────────────────────────────────────────────────────┐
│              部署服务器 (192.168.153.111)                    │
│  容器内前端代码已包含正确的 API 地址                         │
│  http://192.168.153.111:48080/admin-api                     │
└─────────────────────────────────────────────────────────────┘
```

## 总结

✅ **优点**:
- 环境配置清晰，易于管理
- 构建时注入，避免运行时配置错误
- 支持多环境部署（FAT/UAT/生产）
- GitLab CI/CD 自动化构建和部署

⚠️ **注意事项**:
- 修改配置后必须重新构建镜像
- 环境配置文件必须提交到 Git 仓库
- `NEXT_PUBLIC_*` 变量会暴露在前端代码中，不要存放敏感信息

📝 **最佳实践**:
- API 地址使用 IP 而非域名（避免 DNS 解析问题）
- 不同环境使用不同的分支（master -> FAT, uat -> UAT, production -> 生产）
- 构建日志中会打印使用的环境配置，便于排查问题
