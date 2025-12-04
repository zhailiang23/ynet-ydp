# GitLab CI/CD 部署指南

## 概述

本项目使用 GitLab CI/CD 自动化部署到 192.168.153.111 服务器。部署流程包括构建 Docker 镜像、推送到 Harbor 仓库、SSH 部署到目标服务器以及零停机切换。

## 架构说明

```
GitLab Repository (git.ynet.io)
    ↓
GitLab Runner (192.168.153.111)
    ↓
Build Docker Image → Push to Harbor (192.168.152.56)
    ↓
Deploy to Server (192.168.153.111) → Zero-downtime deployment
    ↓
Cleanup Old Images (keep latest 5)
```

## 部署环境

| 环境 | 说明 |
|------|------|
| **dev** | 本地开发环境 |
| **fat** | 功能验收测试环境（FAT） - **GitLab CI/CD 默认部署环境** |
| **uat** | 用户验收测试环境 |
| **pro** | 生产环境 |

## GitLab CI/CD 配置

### Pipeline 阶段

```yaml
stages:
  - build    # 构建 Docker 镜像并推送到 Harbor
  - deploy   # SSH 到生产服务器执行部署脚本
  - cleanup  # 清理旧镜像（保留最近 5 个版本）
```

### 环境变量

在 `.gitlab-ci.yml` 中定义：

```yaml
variables:
  HARBOR_URL: "192.168.152.56"
  HARBOR_PROJECT: "ai-digital-avatar"
  IMAGE_NAME: "${HARBOR_URL}/${HARBOR_PROJECT}/chat-service-backend"
  DEPLOY_SERVER: "192.168.153.111"
  APP_ENV: "fat"  # 部署环境：fat, uat, pro
```

### GitLab CI/CD 变量（需在 GitLab 设置）

| 变量名 | 说明 | 示例值 |
|--------|------|--------|
| `HARBOR_USER` | Harbor 仓库用户名 | `search` |
| `HARBOR_PASSWORD` | Harbor 仓库密码 | `Search123` |
| `DEPLOY_USER` | 部署服务器用户名 | `root` |
| `DEPLOY_SERVER` | 部署服务器地址 | `192.168.153.111` |
| `SSH_PRIVATE_KEY` | SSH 私钥（用于免密登录） | `-----BEGIN RSA PRIVATE KEY-----...` |

**设置方法**:
1. 进入 GitLab 项目 → Settings → CI/CD → Variables
2. 点击 "Add Variable"
3. 输入变量名和值
4. 勾选 "Mask variable" (敏感信息)
5. 勾选 "Protect variable" (仅保护分支可用，可选)

## 部署脚本说明

### deploy-gitlab.sh

零停机部署脚本，执行以下步骤：

1. **创建必要目录** - `/root/zhailiang/chat-service-backend`, `/root/zhailiang/configs`, `/root/zhailiang/logs`
2. **登录 Harbor** - 使用 `HARBOR_USER` 和 `HARBOR_PASSWORD`
3. **拉取新镜像** - 从 Harbor 拉取最新镜像
4. **备份旧容器** - 停止并重命名为 `chat-service-backend_backup`
5. **启动新容器** - 使用新镜像启动 `chat-service-backend_new`
6. **健康检查** - 检查 `http://localhost:8080/` 是否返回 "hello"（最多尝试 30 次，间隔 5 秒）
7. **切换容器** - 健康检查通过后，重命名为 `chat-service-backend`
8. **清理备份** - 删除旧容器
9. **清理旧镜像** - 保留最近 5 个镜像版本
10. **登出 Harbor** - 退出 Harbor 登录

**使用方法**:
```bash
bash deploy-gitlab.sh <IMAGE_WITH_TAG> <HARBOR_URL> <HARBOR_USER> <HARBOR_PASSWORD> <APP_ENV>
```

**示例**:
```bash
bash deploy-gitlab.sh \
  192.168.152.56/ai-digital-avatar/chat-service-backend:123 \
  192.168.152.56 \
  search \
  Search123 \
  fat
```

### 健康检查逻辑

```bash
# 检查容器是否返回 "hello word"
curl -f -s http://localhost:8080/ | grep -q "hello"
```

如果健康检查失败，脚本会：
1. 打印容器日志（最后 50 行）
2. 停止并删除新容器
3. 重新启动旧容器（回滚）
4. 退出并报错

### 回滚机制

部署失败时自动回滚：
- 停止新容器 `chat-service-backend_new`
- 删除新容器
- 启动备份容器 `chat-service-backend_backup`
- 恢复服务运行

## Pipeline 执行流程

### 1. build-and-push 阶段

```yaml
build-and-push:
  stage: build
  image: golang:1.23-alpine
  before_script:
    - apk add --no-cache bash curl git
    - go install github.com/gogf/gf/cmd/gf/v2@latest
  script:
    - gf docker -p -b "-a amd64 -s linux -p temp" -t ${IMAGE_NAME}:${CI_PIPELINE_IID} -e "ENV=${APP_ENV}"
    - echo "$HARBOR_PASSWORD" | docker login $HARBOR_URL -u $HARBOR_USER --password-stdin
    - docker push ${IMAGE_NAME}:${CI_PIPELINE_IID}
    - docker logout $HARBOR_URL
```

**关键步骤**:
- 安装 GoFrame CLI 工具 (`gf`)
- 使用 `gf docker` 构建 Docker 镜像（指定环境参数 `ENV=fat`）
- 登录 Harbor 仓库
- 推送镜像到 Harbor
- 登出 Harbor

### 2. deploy-to-server 阶段

```yaml
deploy-to-server:
  stage: deploy
  image: alpine:latest
  before_script:
    - apk add --no-cache openssh-client bash
    - mkdir -p ~/.ssh && chmod 700 ~/.ssh
    - echo "$SSH_PRIVATE_KEY" > ~/.ssh/id_rsa && chmod 600 ~/.ssh/id_rsa
    - ssh-keyscan -H $DEPLOY_SERVER >> ~/.ssh/known_hosts
  script:
    - scp deploy-gitlab.sh ${DEPLOY_USER}@${DEPLOY_SERVER}:/root/zhailiang/chat-service-backend/
    - ssh ${DEPLOY_USER}@${DEPLOY_SERVER} "bash /root/zhailiang/chat-service-backend/deploy-gitlab.sh ${IMAGE_NAME}:${CI_PIPELINE_IID} ${HARBOR_URL} ${HARBOR_USER} ${HARBOR_PASSWORD} ${APP_ENV}"
```

**关键步骤**:
- 配置 SSH 免密登录
- 复制部署脚本到目标服务器
- SSH 执行部署脚本

### 3. cleanup-old-images 阶段

```yaml
cleanup-old-images:
  stage: cleanup
  image: alpine:latest
  script:
    - ssh ${DEPLOY_USER}@${DEPLOY_SERVER} "docker images ${IMAGE_NAME} --format '{{.ID}} {{.CreatedAt}}' | sort -rk2 | awk '{print \$1}' | tail -n +6 | xargs -r docker rmi || true"
```

**关键步骤**:
- SSH 到目标服务器
- 获取所有镜像（按创建时间排序）
- 删除除最新 5 个之外的旧镜像

## 服务器配置

### 目录结构

```
/root/zhailiang/
├── chat-service-backend/        # 部署脚本目录
│   └── deploy-gitlab.sh         # 部署脚本
├── configs/                      # 配置文件目录
│   └── chat-service-backend-fat.env  # FAT 环境配置
└── logs/                         # 日志目录
    └── chat-service-backend/    # 应用日志
```

### 环境配置文件

位置: `/root/zhailiang/configs/chat-service-backend-${APP_ENV}.env`

**示例** (`chat-service-backend-fat.env`):
```bash
# chat-service-backend 环境变量配置 (fat)
APP_ENV=fat
TZ=Asia/Shanghai

# 可添加其他环境变量，如:
# DATABASE_URL=...
# REDIS_URL=...
```

**重要**: 首次部署时，脚本会自动创建默认配置文件，但需要根据实际情况手动修改。

### Docker 容器配置

| 配置项 | 值 |
|--------|-----|
| **容器名称** | `chat-service-backend` |
| **端口映射** | `8080:8080` |
| **重启策略** | `unless-stopped` |
| **日志挂载** | `/root/zhailiang/logs/chat-service-backend:/app/storage/log` |
| **环境变量** | 从 `/root/zhailiang/configs/chat-service-backend-fat.env` 加载 |

## 部署验证

### 1. 查看 Pipeline 状态

访问 GitLab 项目 → CI/CD → Pipelines，查看最新 Pipeline 是否成功。

### 2. 检查容器状态

```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 查看容器状态
docker ps | grep chat-service-backend

# 查看容器日志
docker logs -f chat-service-backend
```

### 3. 测试服务

```bash
# 健康检查
curl http://192.168.153.111:8080/

# 应返回: "hello word"
```

### 4. 查看部署日志

```bash
# 查看应用日志
tail -f /root/zhailiang/logs/chat-service-backend/*.log
```

## 常见问题

### 1. Pipeline 失败 - SSH 连接失败

**原因**: SSH 私钥未配置或格式错误

**解决方法**:
```bash
# 1. 在部署服务器生成 SSH 密钥对（如果没有）
ssh-keygen -t rsa -b 4096 -C "gitlab-ci"

# 2. 将公钥添加到服务器
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys

# 3. 将私钥添加到 GitLab CI/CD 变量 SSH_PRIVATE_KEY
cat ~/.ssh/id_rsa
# 复制完整内容（包括 BEGIN 和 END 行）到 GitLab Variables
```

### 2. 健康检查失败

**原因**: 容器启动失败或配置错误

**排查步骤**:
```bash
# 1. 查看容器日志
docker logs chat-service-backend_new

# 2. 检查容器是否在运行
docker ps -a | grep chat-service-backend

# 3. 检查端口是否被占用
lsof -i :8080

# 4. 检查环境配置文件
cat /root/zhailiang/configs/chat-service-backend-fat.env
```

### 3. 镜像拉取失败

**原因**: Harbor 仓库连接失败或认证失败

**解决方法**:
```bash
# 1. 检查 Harbor 是否可访问
ping 192.168.152.56

# 2. 手动测试登录
echo "Search123" | docker login 192.168.152.56 -u search --password-stdin

# 3. 检查 GitLab CI/CD 变量是否正确设置
# HARBOR_USER, HARBOR_PASSWORD
```

### 4. 部署后访问 404

**原因**: 配置文件路径错误或环境参数未生效

**解决方法**:
```bash
# 1. 检查容器环境变量
docker exec chat-service-backend env | grep APP_ENV

# 2. 检查配置文件是否存在
docker exec chat-service-backend ls -la /app/manifest/config/

# 3. 验证容器启动命令
docker inspect chat-service-backend | grep -A 5 "Cmd"
# 应为: ./main -c=manifest/config/config.fat.yaml
```

## 手动部署（紧急情况）

如果 GitLab CI/CD 无法使用，可手动部署：

```bash
# 1. SSH 到部署服务器
ssh root@192.168.153.111

# 2. 登录 Harbor
echo "Search123" | docker login 192.168.152.56 -u search --password-stdin

# 3. 拉取镜像
docker pull 192.168.152.56/ai-digital-avatar/chat-service-backend:<TAG>

# 4. 停止旧容器
docker stop chat-service-backend
docker rename chat-service-backend chat-service-backend_backup

# 5. 启动新容器
docker run -d \
  --name chat-service-backend \
  -p 8080:8080 \
  --env-file /root/zhailiang/configs/chat-service-backend-fat.env \
  -v /root/zhailiang/logs/chat-service-backend:/app/storage/log \
  --restart unless-stopped \
  192.168.152.56/ai-digital-avatar/chat-service-backend:<TAG>

# 6. 验证部署
curl http://localhost:8080/
docker logs -f chat-service-backend

# 7. 清理备份（如果新容器正常）
docker rm chat-service-backend_backup
```

## 切换部署环境

如果需要切换到其他环境（如 UAT 或 PRO），修改 `.gitlab-ci.yml`:

```yaml
variables:
  APP_ENV: "uat"  # 修改为 uat, pro 等
```

然后提交代码并推送，GitLab CI/CD 会自动使用新的环境配置进行部署。

## 回滚到旧版本

```bash
# 1. SSH 到部署服务器
ssh root@192.168.153.111

# 2. 查看可用镜像
docker images 192.168.152.56/ai-digital-avatar/chat-service-backend

# 3. 停止当前容器
docker stop chat-service-backend
docker rm chat-service-backend

# 4. 启动旧版本
docker run -d \
  --name chat-service-backend \
  -p 8080:8080 \
  --env-file /root/zhailiang/configs/chat-service-backend-fat.env \
  -v /root/zhailiang/logs/chat-service-backend:/app/storage/log \
  --restart unless-stopped \
  192.168.152.56/ai-digital-avatar/chat-service-backend:<OLD_TAG>
```

## 相关文档

- [多环境配置文档](DEPLOYMENT.md)
- [快速开始指南](QUICK_START.md)
- [更新日志](CHANGELOG_MULTI_ENV.md)

---

**最后更新**: 2025-12-04
