# GitLab CI/CD 部署指南

## 概述

使用 GitLab 自带的 CI/CD 功能替代 Jenkins，实现推送代码立即自动构建和部署。

## GitLab CI/CD vs Jenkins 对比

| 特性 | GitLab CI/CD | Jenkins |
|------|-------------|---------|
| **集成度** | ✅ 完全集成在 GitLab 中 | ❌ 需要单独部署 |
| **配置方式** | ✅ YAML 文件（`.gitlab-ci.yml`） | ❌ UI 配置 + Jenkinsfile |
| **自动触发** | ✅ 原生支持，无需插件 | ❌ 需要 Generic Webhook Trigger 插件 |
| **可视化** | ✅ 强大的 Pipeline 可视化 | ⚠️ Blue Ocean 插件 |
| **Runner** | ✅ 支持共享/专用 Runner | ❌ 需要配置 Agent |
| **学习曲线** | ✅ 简单易学 | ❌ 较复杂 |
| **维护成本** | ✅ 低（GitLab 统一管理） | ❌ 高（独立维护） |
| **云原生** | ✅ 原生支持 Docker/K8s | ⚠️ 需要配置 |

---

## 工作流程

```
开发者推送代码到 master 分支
    ↓
GitLab 自动触发 CI/CD Pipeline
    ↓
阶段 1: Build - 构建 Docker 镜像
    ↓
阶段 2: Push - 推送到 Harbor 仓库
    ↓
阶段 3: Deploy - SSH 到服务器执行零停机部署
    ↓
阶段 4: Cleanup - 清理旧镜像（可选）
    ↓
部署完成 🎉
```

---

## 前置条件

### 1. GitLab Runner

**选项 A: 使用 GitLab.com 提供的共享 Runner**
- 优点: 无需配置，开箱即用
- 缺点: 有使用配额限制
- 适用: 小型项目、测试环境

**选项 B: 自建 GitLab Runner（推荐）**
- 优点: 无限制，性能可控
- 缺点: 需要一台服务器
- 适用: 生产环境

### 2. GitLab 仓库配置

确保您的代码已推送到：
```
git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git
```

---

## 快速开始（使用共享 Runner）

### 步骤 1: 配置 CI/CD 变量

1. **访问项目设置**
   ```
   https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/settings/ci_cd
   ```

2. **展开 "Variables" 部分**

3. **添加以下变量**（点击 "Add variable"）

   | Key | Value | Protected | Masked | Description |
   |-----|-------|-----------|--------|-------------|
   | `HARBOR_USERNAME` | `search` | ✅ Yes | ❌ No | Harbor 用户名 |
   | `HARBOR_PASSWORD` | `Search123` | ✅ Yes | ✅ Yes | Harbor 密码 |
   | `SSH_PRIVATE_KEY` | `<你的私钥内容>` | ✅ Yes | ✅ Yes | SSH 私钥 |

   **获取 SSH 私钥**:
   ```bash
   # 在本地执行
   cat ~/.ssh/id_rsa
   # 复制输出的全部内容（包括 BEGIN 和 END 行）
   ```

   或者使用密码方式（需要在 `.gitlab-ci.yml` 中修改）：
   ```yaml
   # 添加变量
   DEPLOY_PASSWORD = Ynet@2024

   # 修改部署脚本，使用 sshpass
   - apk add --no-cache sshpass
   - sshpass -p "$DEPLOY_PASSWORD" scp deploy.sh ...
   ```

4. **点击 "Save variables"**

### 步骤 2: 提交 CI/CD 配置文件

配置文件已创建：`frontend-practice/.gitlab-ci.yml`

提交并推送：
```bash
cd /Users/zhailiang/Documents/code/ynet-ydp

# 添加 CI/CD 配置
git add frontend-practice/.gitlab-ci.yml
git add frontend-practice/GITLAB_CI_CD_GUIDE.md

# 提交
git commit -m "feat: 添加 GitLab CI/CD 配置"

# 推送到主仓库
git push origin master

# 同步到子仓库（触发 CI/CD）
git subtree push --prefix=frontend-practice \
    git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git master
```

### 步骤 3: 查看 Pipeline 运行

1. **访问 Pipeline 页面**
   ```
   https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/pipelines
   ```

2. **查看执行状态**
   - 点击最新的 Pipeline
   - 查看每个阶段的执行情况
   - 点击任意 Job 查看详细日志

3. **等待部署完成**
   - Build → Push → Deploy → Cleanup
   - 整个流程约 5-10 分钟

### 步骤 4: 验证部署

部署成功后访问：
```
http://192.168.153.111:3000
```

---

## 自建 GitLab Runner（生产环境推荐）

### 在部署服务器上安装 Runner

```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 1. 下载 GitLab Runner
curl -L --output /usr/local/bin/gitlab-runner \
    https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64

# 赋予执行权限
chmod +x /usr/local/bin/gitlab-runner

# 2. 创建用户
useradd --comment 'GitLab Runner' --create-home gitlab-runner --shell /bin/bash

# 3. 安装并启动服务
gitlab-runner install --user=gitlab-runner --working-directory=/home/gitlab-runner
gitlab-runner start

# 4. 注册 Runner
gitlab-runner register

# 按提示输入:
# GitLab instance URL: https://git.ynet.io
# Registration token: （从 GitLab 项目设置 → CI/CD → Runners 获取）
# Description: frontend-practice-runner
# Tags: docker,shell
# Executor: docker
# Default Docker image: docker:24.0.5
```

**获取 Registration Token**:
1. 访问：https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/settings/ci_cd
2. 展开 "Runners" 部分
3. 复制 "Registration token"

### 验证 Runner 状态

```bash
# 查看 Runner 状态
gitlab-runner status

# 查看已注册的 Runner
gitlab-runner list

# 测试 Runner
gitlab-runner verify
```

在 GitLab 项目中验证：
1. 访问：https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/settings/ci_cd
2. 展开 "Runners" 部分
3. 应该能看到您的 Runner（绿色圆点表示在线）

---

## CI/CD 配置说明

### Pipeline 阶段

`.gitlab-ci.yml` 文件定义了 4 个阶段：

#### 1. Build 阶段
```yaml
build-image:
  stage: build
  script:
    - docker build -t ${IMAGE_NAME}:${CI_PIPELINE_IID} .
```
- 构建 Docker 镜像
- 使用 Pipeline ID 作为镜像标签
- 保存镜像为 tar 文件传递给下一阶段

#### 2. Push 阶段
```yaml
push-to-harbor:
  stage: push
  script:
    - docker push ${IMAGE_NAME}:${CI_PIPELINE_IID}
```
- 登录 Harbor
- 推送镜像到 Harbor 仓库
- 标记为 latest

#### 3. Deploy 阶段
```yaml
deploy-to-production:
  stage: deploy
  script:
    - ssh ${DEPLOY_USER}@${DEPLOY_SERVER} "bash deploy.sh ..."
```
- SSH 到部署服务器
- 执行零停机部署脚本
- 健康检查和自动回滚

#### 4. Cleanup 阶段
```yaml
cleanup-old-images:
  stage: cleanup
  script:
    - ssh ... "docker rmi old_images"
```
- 清理服务器上的旧镜像
- 保留最新 3 个版本

### 内置变量

GitLab 提供丰富的内置变量：

| 变量 | 说明 | 示例 |
|------|------|------|
| `CI_COMMIT_SHORT_SHA` | 提交哈希（短） | `a1b2c3d4` |
| `CI_COMMIT_BRANCH` | 分支名称 | `master` |
| `CI_PIPELINE_IID` | Pipeline ID | `123` |
| `CI_PROJECT_DIR` | 项目目录 | `/builds/project` |

更多变量：https://docs.gitlab.com/ee/ci/variables/predefined_variables.html

---

## 高级配置

### 1. 仅在特定分支触发

已配置为仅 `master` 分支触发：
```yaml
workflow:
  rules:
    - if: $CI_COMMIT_BRANCH == "master"
      when: always
    - when: never
```

### 2. 添加手动审批

在部署前增加手动确认：
```yaml
deploy-to-production:
  stage: deploy
  when: manual  # 需要手动点击才执行
  script: ...
```

### 3. 并行构建

同时构建多个镜像：
```yaml
build-web:
  stage: build
  script:
    - docker build -t web:${CI_PIPELINE_IID} ./web

build-api:
  stage: build
  script:
    - docker build -t api:${CI_PIPELINE_IID} ./api
```

### 4. 环境部署

支持多环境部署：
```yaml
deploy-staging:
  stage: deploy
  script:
    - deploy.sh staging
  environment:
    name: staging
    url: http://staging.example.com

deploy-production:
  stage: deploy
  script:
    - deploy.sh production
  environment:
    name: production
    url: http://production.example.com
  when: manual  # 生产环境需手动确认
```

### 5. 添加通知

部署成功/失败后发送通知：
```yaml
notify-success:
  stage: .post
  script:
    - curl -X POST "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx" \
        -d '{"msgtype":"text","text":{"content":"部署成功！"}}'
  when: on_success

notify-failure:
  stage: .post
  script:
    - curl -X POST "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=xxx" \
        -d '{"msgtype":"text","text":{"content":"部署失败！"}}'
  when: on_failure
```

---

## 故障排查

### Pipeline 失败

1. **查看 Job 日志**
   - 点击失败的 Job
   - 查看详细错误信息

2. **常见问题**

   **Build 阶段失败**:
   ```
   错误: Cannot connect to Docker daemon
   解决: 确保 Runner 配置了 docker:dind 服务
   ```

   **Push 阶段失败**:
   ```
   错误: unauthorized: authentication required
   解决: 检查 HARBOR_USERNAME 和 HARBOR_PASSWORD 变量
   ```

   **Deploy 阶段失败**:
   ```
   错误: Permission denied (publickey)
   解决: 检查 SSH_PRIVATE_KEY 变量是否正确
   ```

### 查看 Runner 日志

```bash
# 在 Runner 服务器上执行
sudo journalctl -u gitlab-runner -f
```

### 重新运行 Pipeline

1. 访问 Pipeline 页面
2. 点击右上角 "Retry" 按钮

---

## 与 Jenkins 方案对比

### 相同点
- ✅ 推送代码立即触发
- ✅ 构建 Docker 镜像
- ✅ 推送到 Harbor
- ✅ 零停机部署
- ✅ 健康检查和回滚

### GitLab CI/CD 的优势
1. **配置简单** - 一个 YAML 文件搞定
2. **原生集成** - 无需安装插件
3. **可视化强** - 清晰的 Pipeline 视图
4. **调试方便** - 详细的日志输出
5. **版本控制** - `.gitlab-ci.yml` 和代码一起管理
6. **免费** - GitLab 社区版免费使用

### Jenkins 的优势
1. **灵活性高** - 插件生态丰富
2. **适合复杂场景** - 多项目、多团队
3. **独立部署** - 不依赖 Git 平台

---

## 推荐配置

### 小型项目（您的情况）
✅ **推荐使用 GitLab CI/CD**
- 配置简单，维护成本低
- 代码和 CI/CD 配置统一管理
- 无需单独维护 Jenkins 服务器

### 大型项目
⚠️ **考虑 Jenkins**
- 多个项目共享构建资源
- 复杂的构建流程和依赖
- 需要与多个 Git 平台集成

---

## 下一步

### 1. 测试 CI/CD

```bash
# 提交测试代码
cd frontend-practice
echo "# Test CI/CD" >> README.md
git add README.md
git commit -m "test: 测试 GitLab CI/CD"

# 推送触发 Pipeline
git subtree push --prefix=frontend-practice \
    git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git master
```

### 2. 查看执行结果

访问：https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/pipelines

### 3. 优化配置

根据实际情况调整：
- 添加测试阶段
- 配置缓存加速构建
- 添加通知机制

---

## 总结

✅ **GitLab CI/CD 完全可以实现您的需求**，而且：
- 配置更简单（一个 YAML 文件）
- 维护成本更低（无需维护 Jenkins）
- 集成更紧密（与 GitLab 原生集成）
- 学习曲线更平缓

**推荐直接使用 GitLab CI/CD！**

---

## 相关链接

- GitLab CI/CD 官方文档: https://docs.gitlab.com/ee/ci/
- `.gitlab-ci.yml` 语法参考: https://docs.gitlab.com/ee/ci/yaml/
- GitLab Runner 文档: https://docs.gitlab.com/runner/
