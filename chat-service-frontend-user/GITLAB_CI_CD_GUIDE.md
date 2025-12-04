# chat-service-frontend-user GitLab CI/CD 部署指南

## 概述

本项目已配置 GitLab CI/CD 自动化部署流程，支持自动构建 Docker 镜像、推送到 Harbor 仓库、并部署到生产服务器。

## 部署架构

```
GitLab (代码推送)
    ↓
GitLab CI/CD Pipeline
    ↓
├─ Stage 1: build-and-push
│   ├─ 构建 Docker 镜像（使用 FAT 环境配置）
│   └─ 推送到 Harbor (192.168.152.56)
    ↓
├─ Stage 2: deploy-to-fat
│   ├─ SSH 连接到生产服务器 (192.168.153.111)
│   ├─ 执行零停机部署脚本
│   └─ 健康检查
    ↓
└─ Stage 3: cleanup-old-images
    └─ 清理旧镜像（保留最新 3 个）
```

## 部署配置

### 环境信息

| 配置项 | 值 | 说明 |
|--------|-----|------|
| **Harbor 仓库** | 192.168.152.56 | 镜像仓库地址 |
| **Harbor 项目** | ai-digital-avatar | 项目命名空间 |
| **镜像名称** | chat-service-frontend-user | 镜像名 |
| **部署服务器** | 192.168.153.111 | 生产服务器 |
| **部署端口** | 10086 | H5 访问端口 |
| **容器名称** | chat-user | Docker 容器名 |
| **环境配置** | FAT | 使用 config/fat.ts |

### 目录结构

生产服务器上的目录结构：

```
/root/zhailiang/
├── chat-service-frontend-user/     # 部署目录
│   └── deploy.sh                   # 部署脚本
├── configs/                        # 配置目录（预留）
└── logs/                          # 日志目录
    └── chat-service-frontend-user/  # Nginx 日志
```

## 触发部署

### 自动触发

推送代码到 GitLab 的 `master` 分支时自动触发 Pipeline：

```bash
# 方式一：直接推送到 GitLab
git push git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-frontend-user.git master

# 方式二：从主仓库使用 git subtree 推送
cd /path/to/ynet-ydp
git push -f git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-frontend-user.git \
  $(git subtree split --prefix=chat-service-frontend-user master):master
```

### Pipeline 阶段

1. **build-and-push** (约 5-10 分钟)
   - 使用 `docker:24.0.5` 镜像
   - 构建参数：`--build-arg APP_ENV=fat`
   - 推送两个标签：`${CI_PIPELINE_IID}` 和 `latest`

2. **deploy-to-fat** (约 2-3 分钟)
   - SSH 到生产服务器
   - 执行零停机部署
   - 健康检查（最多 30 次，每次间隔 5 秒）

3. **cleanup-old-images** (约 1 分钟)
   - 保留最新 3 个镜像版本
   - 清理旧镜像释放空间

## 零停机部署流程

deploy.sh 脚本实现了零停机部署：

```
1. 拉取新镜像
    ↓
2. 停止旧容器并重命名为 chat-user_backup
    ↓
3. 启动新容器（名为 chat-user_new）
    ↓
4. 健康检查（30 次 × 5 秒）
    ↓
5. ✓ 健康检查通过
    ├─ 重命名 chat-user_new → chat-user
    ├─ 删除 chat-user_backup
    └─ 部署完成

    ✗ 健康检查失败
    ├─ 停止并删除 chat-user_new
    ├─ 启动 chat-user_backup
    └─ 回滚完成
```

## 访问服务

部署成功后，可以通过以下方式访问：

```bash
# HTTP 访问
http://192.168.153.111:10086/

# 健康检查
curl http://192.168.153.111:10086/
```

## 查看部署状态

### 1. GitLab Pipeline 状态

访问 GitLab 项目页面查看 Pipeline 状态：
```
http://git.ynet.io/belink/ai-agent/ai-digital-avatar/chat-service-frontend-user/-/pipelines
```

### 2. 查看容器状态

```bash
# SSH 到生产服务器
ssh root@192.168.153.111

# 查看容器运行状态
docker ps | grep chat-user

# 查看容器日志
docker logs chat-user --tail 100

# 查看 Nginx 日志
tail -f /root/zhailiang/logs/chat-service-frontend-user/access.log
```

### 3. 查看镜像

```bash
# 查看所有镜像
docker images | grep chat-service-frontend-user

# 输出示例
192.168.152.56/ai-digital-avatar/chat-service-frontend-user   123      abc123   2 hours ago   150MB
192.168.152.56/ai-digital-avatar/chat-service-frontend-user   latest   abc123   2 hours ago   150MB
```

## 常见问题

### 1. Pipeline 构建失败

**问题**: Docker 构建超时或失败
**排查**:
```bash
# 检查 GitLab Runner 状态
ssh root@192.168.153.111
docker ps | grep gitlab-runner

# 查看 Runner 日志
journalctl -u gitlab-runner --since "10 minutes ago"
```

**解决**:
- 确保 node:20-alpine 基础镜像可访问
- 检查网络连接
- 查看 .gitlab-ci.yml 中的 before_script 是否正确

### 2. 健康检查失败

**问题**: 部署脚本报告健康检查失败
**排查**:
```bash
# 查看容器日志
docker logs chat-user_new --tail 50

# 手动测试健康检查
curl -v http://localhost:10086/
```

**常见原因**:
- Nginx 配置错误
- 静态文件未正确复制（检查 Dockerfile）
- 端口冲突
- 容器启动时间过长

### 3. FAT 环境配置未生效

**问题**: 服务使用了错误的 API 地址
**排查**:
```bash
# 查看构建日志中的环境变量
# 应该看到 "Environment fat"

# 检查打包后的代码
docker run --rm chat-user cat /usr/share/nginx/html/index.html | grep BASE_URL
```

**解决**:
- 确认 .gitlab-ci.yml 中 `APP_ENV: "fat"` 正确设置
- 确认 Dockerfile 使用 `ARG APP_ENV` 和 `ENV APP_ENV`
- 确认 config/fat.ts 配置正确

### 4. 部署成功但无法访问

**问题**: Pipeline 成功但无法访问服务
**排查**:
```bash
# 检查容器是否运行
docker ps | grep chat-user

# 检查端口监听
netstat -tlnp | grep 10086

# 检查防火墙
firewall-cmd --list-ports
```

**解决**:
- 检查端口是否正确映射（10086:80）
- 确认防火墙允许 10086 端口
- 检查 Nginx 配置

## 手动部署

如果需要手动部署（绕过 CI/CD）：

```bash
# 1. SSH 到生产服务器
ssh root@192.168.153.111

# 2. 登录 Harbor
docker login 192.168.152.56 -u search -p Search123

# 3. 拉取镜像
docker pull 192.168.152.56/ai-digital-avatar/chat-service-frontend-user:latest

# 4. 停止旧容器
docker stop chat-user || true
docker rm chat-user || true

# 5. 启动新容器
docker run -d \
  --name chat-user \
  -p 10086:80 \
  -v /root/zhailiang/logs/chat-service-frontend-user:/var/log/nginx \
  --restart unless-stopped \
  192.168.152.56/ai-digital-avatar/chat-service-frontend-user:latest

# 6. 验证
curl http://localhost:10086/
```

## 配置修改

### 修改环境配置

如果需要修改 FAT 环境的 API 地址，编辑 `config/fat.ts`：

```typescript
import type { UserConfigExport } from "@tarojs/cli";
export default {
  defineConstants: {
    BASE_URL: '"http://新的API地址:8080/api/user"',
    WS_URL: '"ws://新的WebSocket地址:8080/api/user/chat/ws"'
  },
  mini: {},
  h5: {},
} satisfies UserConfigExport<'webpack5'>
```

**重要**: 修改后需要提交并推送到 GitLab 触发重新构建。

### 修改部署端口

如果需要修改部署端口，在 `.gitlab-ci.yml` 中修改：

```yaml
variables:
  CONTAINER_PORT: "新端口号"  # 例如 8080
```

## 回滚到旧版本

```bash
# 1. SSH 到生产服务器
ssh root@192.168.153.111

# 2. 查看可用镜像版本
docker images | grep chat-service-frontend-user

# 3. 停止当前容器
docker stop chat-user
docker rm chat-user

# 4. 启动指定版本
docker run -d \
  --name chat-user \
  -p 10086:80 \
  -v /root/zhailiang/logs/chat-service-frontend-user:/var/log/nginx \
  --restart unless-stopped \
  192.168.152.56/ai-digital-avatar/chat-service-frontend-user:旧版本号

# 5. 验证
curl http://localhost:10086/
```

## 监控和日志

### 容器监控

```bash
# 查看容器资源使用
docker stats chat-user

# 查看容器详细信息
docker inspect chat-user
```

### 日志查看

```bash
# 容器日志
docker logs chat-user --tail 100 -f

# Nginx 访问日志
tail -f /root/zhailiang/logs/chat-service-frontend-user/access.log

# Nginx 错误日志
tail -f /root/zhailiang/logs/chat-service-frontend-user/error.log
```

## 安全说明

- SSH 私钥已嵌入 `.gitlab-ci.yml`，仅限内网使用
- Harbor 使用 HTTP 协议，仅限内网访问
- 生产服务器需要正确配置防火墙规则

## 相关文档

- [多环境配置指南](MULTI_ENV_GUIDE.md)
- [Docker 部署文档](README.md)

## 维护记录

- 2025-12-04: 初始配置，支持 FAT 环境自动化部署
