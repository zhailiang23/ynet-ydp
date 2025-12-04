# GitLab CI/CD 部署前检查清单

## 📋 配置文件检查

### ✅ 本地配置文件

- [x] `.gitlab-ci.yml` - GitLab CI/CD 配置文件（13 KB）
- [x] `deploy-gitlab.sh` - 部署脚本（8.8 KB，可执行）
- [x] `manifest/config/config.dev.yaml` - dev 环境配置
- [x] `manifest/config/config.fat.yaml` - fat 环境配置
- [x] `manifest/config/config.uat.yaml` - uat 环境配置
- [x] `manifest/config/config.pro.yaml` - pro 环境配置
- [x] `Dockerfile` - Docker 镜像构建文件（已支持环境参数）
- [x] `Makefile` - 构建工具（已支持 ENV 参数）

### ✅ 文档文件

- [x] `README.md` - 项目说明文档（已更新）
- [x] `DEPLOYMENT.md` - 完整部署文档（6.5 KB）
- [x] `QUICK_START.md` - 快速开始指南（5.0 KB）
- [x] `CHANGELOG_MULTI_ENV.md` - 多环境配置更新日志（4.6 KB）
- [x] `GITLAB_CI_CD_GUIDE.md` - GitLab CI/CD 部署指南（11 KB）
- [x] `GITLAB_CI_CHECKLIST.md` - 本检查清单

## 🔧 GitLab 配置检查

### 需在 GitLab 项目设置的 CI/CD 变量

进入 GitLab 项目 → Settings → CI/CD → Variables，确认已设置：

- [ ] `HARBOR_USER` - Harbor 用户名（示例：`search`）
- [ ] `HARBOR_PASSWORD` - Harbor 密码（示例：`Search123`）
- [ ] `DEPLOY_USER` - 部署服务器用户名（示例：`root`）
- [ ] `DEPLOY_SERVER` - 部署服务器地址（示例：`192.168.153.111`）

**注意**: SSH 私钥已直接嵌入 `.gitlab-ci.yml` 的 `before_script` 中，无需单独配置 `SSH_PRIVATE_KEY` 变量。

### GitLab Runner 配置

- [ ] 确认 GitLab Runner 已安装在 192.168.153.111 服务器
- [ ] 确认 Runner 已注册到 GitLab 项目
- [ ] 确认 Runner 有 `docker` 和 `shell` 标签
- [ ] 确认 Runner 状态为 Active

验证方法：
```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 检查 GitLab Runner 状态
gitlab-runner status

# 查看已注册的 Runner
gitlab-runner list
```

## 🖥️ 服务器环境检查

### 必需软件

SSH 到 192.168.153.111 服务器，检查以下软件是否已安装：

```bash
# 检查 Docker
docker --version

# 检查 Docker Compose
docker-compose --version

# 检查 curl
curl --version

# 检查 SSH
ssh -V
```

### 目录权限

```bash
# 确保部署目录存在并有正确权限
mkdir -p /root/zhailiang/chat-service-backend
mkdir -p /root/zhailiang/configs
mkdir -p /root/zhailiang/logs/chat-service-backend

# 检查目录权限
ls -ld /root/zhailiang/chat-service-backend
ls -ld /root/zhailiang/configs
ls -ld /root/zhailiang/logs
```

### SSH 密钥配置

```bash
# 确认 SSH 公钥已添加到 authorized_keys
cat ~/.ssh/authorized_keys | grep -q "gitlab-ci" && echo "SSH key configured" || echo "SSH key missing"
```

如果未配置，运行：
```bash
# 将 .gitlab-ci.yml 中的私钥对应的公钥添加到服务器
# 公钥内容请从生成私钥的机器获取
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
```

### Harbor 仓库访问

```bash
# 测试 Harbor 连通性
ping -c 3 192.168.152.56

# 测试 Harbor 登录
echo "Search123" | docker login 192.168.152.56 -u search --password-stdin
```

### 端口可用性

```bash
# 检查 8080 端口是否被占用
lsof -i :8080

# 如果被占用，停止占用端口的进程
# kill -9 <PID>
```

## 🚀 部署前验证

### 1. 本地测试构建

在本地测试 Docker 镜像构建：

```bash
# 测试 dev 环境构建
make image ENV=dev TAG=test

# 测试 fat 环境构建
make image ENV=fat TAG=test

# 验证镜像是否创建成功
docker images | grep chat-service-backend
```

### 2. 测试部署脚本

```bash
# 检查脚本语法
bash -n deploy-gitlab.sh

# 查看脚本帮助
./deploy-gitlab.sh
```

### 3. 验证环境配置文件

```bash
# 检查配置文件语法（YAML 格式）
cat manifest/config/config.fat.yaml

# 确认环境参数正确
grep -E "(database|redis|app)" manifest/config/config.fat.yaml
```

## 📝 提交代码到 GitLab

### 推送到 GitLab 子仓库

使用 `git subtree` 推送到 GitLab：

```bash
# 方法一：使用 git subtree push（可能较慢）
git subtree push --prefix=chat-service-backend \
  git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-backend.git master

# 方法二：使用 git subtree split + force push（推荐）
git push -f git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-backend.git \
  $(git subtree split --prefix=chat-service-backend master):master
```

**注意**: 由于 `.gitlab-ci.yml` 配置了 `workflow.rules`，只有推送到 `master` 分支时才会触发 Pipeline。

## ✅ Pipeline 执行验证

### 1. 查看 Pipeline 状态

推送代码后，访问 GitLab：
- 项目地址: git.ynet.io/belink/ai-agent/ai-digital-avatar/chat-service-backend
- CI/CD → Pipelines
- 查看最新 Pipeline 状态

### 2. 监控 Pipeline 执行

点击 Pipeline ID 查看详细执行日志：

**Stage 1: build-and-push**
- [ ] GoFrame CLI 安装成功
- [ ] Docker 镜像构建成功
- [ ] 镜像推送到 Harbor 成功
- [ ] 镜像标签正确（${CI_PIPELINE_IID} 和 latest）

**Stage 2: deploy-to-fat**
- [ ] SSH 连接成功
- [ ] 部署脚本复制成功
- [ ] Harbor 登录成功
- [ ] 镜像拉取成功
- [ ] 旧容器备份成功
- [ ] 新容器启动成功
- [ ] 健康检查通过
- [ ] 容器重命名成功
- [ ] 访问地址返回 "hello word"

**Stage 3: cleanup-old-images**
- [ ] SSH 连接成功
- [ ] 旧镜像清理成功（保留最新 5 个）

### 3. 验证部署结果

```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 检查容器状态
docker ps | grep chat-service-backend

# 检查容器日志
docker logs -f chat-service-backend --tail 50

# 测试健康检查
curl http://localhost:8080/

# 检查环境变量
docker exec chat-service-backend env | grep APP_ENV
```

### 4. 应用访问验证

```bash
# 从本地测试
curl http://192.168.153.111:8080/

# 应返回: "hello word"
```

## 🐛 常见问题排查

### Pipeline 失败

#### 1. build-and-push 失败

**问题**: GoFrame CLI 安装失败
```bash
# 解决方法：检查 Go 版本和网络
go version  # 应为 1.23+
```

**问题**: Harbor 登录失败
```bash
# 解决方法：验证 Harbor 凭证
echo "Search123" | docker login 192.168.152.56 -u search --password-stdin
```

#### 2. deploy-to-fat 失败

**问题**: SSH 连接失败
```bash
# 解决方法：检查 SSH 密钥和网络
ping 192.168.153.111
ssh -v root@192.168.153.111
```

**问题**: 健康检查失败
```bash
# 解决方法：查看容器日志
docker logs chat-service-backend_new --tail 100

# 检查配置文件
cat /root/zhailiang/configs/chat-service-backend-fat.env
```

#### 3. cleanup-old-images 失败

**问题**: 镜像删除失败
```bash
# 解决方法：手动清理
docker images 192.168.152.56/ai-digital-avatar/chat-service-backend
docker rmi <IMAGE_ID>
```

## 📚 相关文档

- [GitLab CI/CD 完整指南](GITLAB_CI_CD_GUIDE.md)
- [多环境部署文档](DEPLOYMENT.md)
- [快速开始指南](QUICK_START.md)
- [更新日志](CHANGELOG_MULTI_ENV.md)

## ✨ 部署成功标志

- [x] Pipeline 所有阶段状态为 ✅ Passed
- [x] 容器 `chat-service-backend` 在运行中
- [x] `curl http://192.168.153.111:8080/` 返回 "hello word"
- [x] 容器环境变量 `APP_ENV=fat`
- [x] 容器日志无错误信息
- [x] Harbor 仓库有最新镜像

---

**最后更新**: 2025-12-04
**环境**: FAT (功能验收测试环境)
**部署服务器**: 192.168.153.111
