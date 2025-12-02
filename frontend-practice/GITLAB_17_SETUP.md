# GitLab 17.1.8 社区版 CI/CD 配置指南

## GitLab 版本信息

- **版本**: 17.1.8 Community Edition
- **发布日期**: 2024年7月
- **CI/CD 功能**: ✅ 完全支持
- **兼容性**: ✅ 配置已针对 17.1.8 优化

---

## GitLab 17.1.8 新特性

### CI/CD 增强功能

1. **性能优化**
   - ✅ 更快的 artifacts 传输（`FF_USE_FASTZIP`）
   - ✅ 优化的缓存压缩（`CACHE_COMPRESSION_LEVEL`）
   - ✅ 改进的 Pipeline 执行速度

2. **改进的 YAML 语法**
   - ✅ 更灵活的 `workflow` 规则
   - ✅ 增强的变量插值
   - ✅ 更好的错误提示

3. **安全增强**
   - ✅ 改进的 secrets 管理
   - ✅ 增强的 Runner 安全性
   - ✅ 更细粒度的权限控制

---

## 快速开始

### 步骤 1: 配置 CI/CD 变量

1. **访问项目设置**
   ```
   https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/settings/ci_cd
   ```

2. **展开 "Variables" 部分**

3. **添加变量**（点击 "Add variable"）

   在 GitLab 17.1.8 中，变量配置界面更加友好：

   **变量 1: Harbor 用户名**
   - Key: `HARBOR_USERNAME`
   - Value: `search`
   - Type: `Variable` (默认)
   - Environment scope: `All (default)`
   - Protect variable: ✅ 勾选
   - Mask variable: ❌ 不勾选（用户名可以显示）
   - Expand variable reference: ✅ 勾选（默认）

   **变量 2: Harbor 密码**
   - Key: `HARBOR_PASSWORD`
   - Value: `Search123`
   - Type: `Variable`
   - Environment scope: `All (default)`
   - Protect variable: ✅ 勾选
   - Mask variable: ✅ 勾选（密码需要遮挡）
   - Expand variable reference: ✅ 勾选

   **变量 3: SSH 私钥**
   - Key: `SSH_PRIVATE_KEY`
   - Value: `<你的 SSH 私钥内容>`
   - Type: `File` （GitLab 17.1+ 支持文件类型）
   - Environment scope: `All (default)`
   - Protect variable: ✅ 勾选
   - Mask variable: ✅ 勾选

   **获取 SSH 私钥**:
   ```bash
   # 在本地执行
   cat ~/.ssh/id_rsa

   # 复制全部内容，包括：
   # -----BEGIN RSA PRIVATE KEY-----
   # ... 密钥内容 ...
   # -----END RSA PRIVATE KEY-----
   ```

4. **点击 "Add variable"** 保存

---

### 步骤 2: 配置 GitLab Runner

#### 选项 A: 使用共享 Runner（快速开始）

GitLab 17.1.8 社区版支持共享 Runner：

1. **检查 Runner 可用性**
   - 访问：`Settings → CI/CD → Runners`
   - 查看 "Available shared runners" 部分
   - 如果有绿色圆点，说明 Runner 可用

2. **如果没有共享 Runner**
   - 请按照下面的 "选项 B" 自建 Runner

#### 选项 B: 自建 GitLab Runner（推荐）

**在部署服务器（192.168.153.111）上安装**：

```bash
# 1. SSH 到部署服务器
ssh root@192.168.153.111

# 2. 下载 GitLab Runner（适用于 GitLab 17.1.8）
curl -L --output /usr/local/bin/gitlab-runner \
    "https://gitlab-runner-downloads.s3.amazonaws.com/latest/binaries/gitlab-runner-linux-amd64"

# 3. 赋予执行权限
chmod +x /usr/local/bin/gitlab-runner

# 4. 创建 GitLab Runner 用户
useradd --comment 'GitLab Runner' \
    --create-home gitlab-runner \
    --shell /bin/bash

# 5. 安装 Runner 服务
gitlab-runner install --user=gitlab-runner \
    --working-directory=/home/gitlab-runner

# 6. 启动 Runner
gitlab-runner start

# 7. 验证安装
gitlab-runner --version
# 应该显示: Version: 17.x.x
```

**注册 Runner（GitLab 17.1.8 新流程）**：

在 GitLab 17.1+，注册流程有所变化：

```bash
# 1. 获取 Registration Token
# 访问: https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/settings/ci_cd
# 展开 "Runners" 部分
# 点击 "New project runner" 按钮
# 选择 "Linux" 平台
# 复制显示的注册命令

# 2. 在服务器上执行注册命令（示例）
gitlab-runner register \
    --url "https://git.ynet.io/" \
    --token "YOUR_REGISTRATION_TOKEN" \
    --executor "docker" \
    --docker-image "docker:24.0.5" \
    --description "frontend-practice-runner" \
    --tag-list "docker,shell" \
    --run-untagged="true" \
    --locked="false" \
    --docker-privileged \
    --docker-volumes "/var/run/docker.sock:/var/run/docker.sock"

# 3. 验证注册
gitlab-runner verify
```

**GitLab 17.1.8 注册界面说明**：
- **Platform**: 选择 `Linux`
- **Tags**: 输入 `docker, shell`（逗号分隔）
- **Run untagged jobs**: ✅ 勾选
- **Protected**: ❌ 不勾选（除非只用于 protected 分支）
- **Configuration**: 保持默认，注册后会显示注册命令

**验证 Runner 状态**：

1. 在 GitLab 项目中查看：
   ```
   Settings → CI/CD → Runners → Project runners
   ```
   应该能看到您的 Runner，显示绿色圆点表示在线

2. 在服务器上查看：
   ```bash
   gitlab-runner status
   # 应该显示: Service is running

   gitlab-runner list
   # 应该列出已注册的 Runner
   ```

---

### 步骤 3: 提交 CI/CD 配置

```bash
cd /Users/zhailiang/Documents/code/ynet-ydp

# 添加配置文件
git add frontend-practice/.gitlab-ci.yml
git add frontend-practice/GITLAB_17_SETUP.md

# 提交
git commit -m "feat: 添加 GitLab 17.1.8 CI/CD 配置"

# 推送到主仓库
git push origin master

# 同步到子仓库（触发 CI/CD）
git subtree push --prefix=frontend-practice \
    git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git master
```

---

### 步骤 4: 查看 Pipeline 执行

**GitLab 17.1.8 Pipeline 视图增强功能**：

1. **访问 Pipeline 页面**
   ```
   https://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice/-/pipelines
   ```

2. **查看执行状态**
   - ✅ 新的可视化界面，更清晰
   - ✅ 实时日志流
   - ✅ 更好的错误高亮

3. **Pipeline 图表**
   - 点击任意 Pipeline
   - 查看 DAG（有向无环图）视图
   - 清晰显示各阶段依赖关系

4. **Job 日志查看**
   - 点击任意 Job
   - 实时查看执行日志
   - 支持日志搜索和过滤

---

## GitLab 17.1.8 特有功能

### 1. 改进的变量管理

**新功能**：
- ✅ 支持 `File` 类型变量（用于 SSH 密钥）
- ✅ 变量可以设置描述
- ✅ 更好的变量搜索和过滤

**使用示例**：
```yaml
# 在 .gitlab-ci.yml 中直接使用文件类型变量
deploy:
  script:
    - chmod 600 $SSH_PRIVATE_KEY
    - ssh -i $SSH_PRIVATE_KEY ...
```

### 2. 增强的 Pipeline 可视化

**新界面特性**：
- ✅ 更快的加载速度
- ✅ 实时更新（无需刷新）
- ✅ 更好的移动端支持
- ✅ 暗色模式支持

### 3. 改进的错误提示

**示例**：
```
❌ Job failed: exit code 1

原因: Docker image not found
建议:
  1. 检查镜像名称是否正确
  2. 确保 Runner 可以访问 Docker Hub
  3. 尝试使用完整的镜像地址
```

### 4. 性能优化

**自动启用的优化**：
```yaml
variables:
  FF_USE_FASTZIP: "true"              # 快速 ZIP 压缩
  ARTIFACT_COMPRESSION_LEVEL: "fast"   # Artifacts 快速压缩
  CACHE_COMPRESSION_LEVEL: "fast"     # Cache 快速压缩
  FF_USE_DIRECT_DOWNLOAD: "true"      # 直接下载优化
```

---

## 完整的 CI/CD 流程

### Pipeline 执行流程

```
推送代码到 master 分支
    ↓
GitLab 17.1.8 检测到 .gitlab-ci.yml
    ↓
分配 Runner（自动选择可用的 Runner）
    ↓
【Build 阶段】(约 3-5 分钟)
  - 拉取代码
  - 构建 Docker 镜像
  - 保存为 artifact
    ↓
【Push 阶段】(约 1-2 分钟)
  - 登录 Harbor
  - 推送镜像
    ↓
【Deploy 阶段】(约 2-3 分钟)
  - SSH 到部署服务器
  - 执行 deploy.sh
  - 零停机部署
  - 健康检查
    ↓
【Cleanup 阶段】(约 1 分钟)
  - 清理旧镜像
    ↓
部署完成！✅
```

---

## 监控和调试

### 1. 查看 Pipeline 状态

**实时监控**：
```
Pipelines → [选择 Pipeline] → 实时查看各 Job 状态
```

**邮件通知**（GitLab 17.1.8 支持）：
```
Settings → Notifications → Custom → Pipeline events
```

### 2. 调试失败的 Job

**在 GitLab 17.1.8 中**：

1. **查看详细日志**
   ```
   Pipeline → [失败的 Job] → 点击查看完整日志
   ```

2. **下载日志**
   ```
   右上角 → Download → 下载日志文件
   ```

3. **重新运行 Job**
   ```
   右上角 → Retry
   ```

4. **调试模式**（需要 Runner 支持）
   ```
   点击 "Debug" 按钮 → 进入交互式调试
   ```

### 3. 性能分析

**查看 Pipeline 性能**：
```
Analytics → CI/CD Analytics → 查看平均执行时间
```

---

## 最佳实践

### 1. Runner 配置优化

**在 `/etc/gitlab-runner/config.toml` 中配置**：
```toml
concurrent = 4  # 并发 Job 数量

[[runners]]
  name = "frontend-practice-runner"
  url = "https://git.ynet.io/"
  token = "YOUR_TOKEN"
  executor = "docker"

  [runners.docker]
    image = "docker:24.0.5"
    privileged = true
    volumes = ["/var/run/docker.sock:/var/run/docker.sock", "/cache"]

  [runners.cache]
    Type = "local"
    Path = "/cache"
    Shared = true
```

### 2. 使用缓存加速构建

**添加到 .gitlab-ci.yml**：
```yaml
build-image:
  cache:
    key: ${CI_COMMIT_REF_SLUG}
    paths:
      - node_modules/
      - .next/cache/
  script:
    - docker build ...
```

### 3. 并行执行

**多个独立任务并行**：
```yaml
build-frontend:
  stage: build
  script:
    - docker build -t frontend ...

build-backend:
  stage: build
  script:
    - docker build -t backend ...
```

---

## 故障排查

### 问题 1: Runner 未注册成功

**症状**：
```
This job is stuck because the project doesn't have any runners online
```

**解决方案**：
```bash
# 在 Runner 服务器上执行
gitlab-runner verify
gitlab-runner restart

# 检查 Runner 状态
gitlab-runner status
```

### 问题 2: Docker 权限问题

**症状**：
```
Cannot connect to the Docker daemon
```

**解决方案**：
```bash
# 确保 gitlab-runner 用户在 docker 组中
sudo usermod -aG docker gitlab-runner

# 重启 Runner
sudo gitlab-runner restart
```

### 问题 3: SSH 连接失败

**症状**：
```
Permission denied (publickey)
```

**解决方案**：
1. 检查 `SSH_PRIVATE_KEY` 变量是否正确
2. 确保私钥格式正确（包含 BEGIN 和 END 行）
3. 在 `.gitlab-ci.yml` 中添加调试：
```yaml
before_script:
  - chmod 600 $SSH_PRIVATE_KEY
  - ssh-keyscan -H $DEPLOY_SERVER >> ~/.ssh/known_hosts
```

---

## 升级说明

### 如果您的 GitLab 版本 < 17.1

**不用担心！配置向下兼容**

只需删除这些 GitLab 17.1+ 特有的变量：
```yaml
# 删除这些行（旧版本不支持）
variables:
  FF_USE_FASTZIP: "true"
  ARTIFACT_COMPRESSION_LEVEL: "fast"
  CACHE_COMPRESSION_LEVEL: "fast"
```

---

## 总结

### GitLab 17.1.8 CI/CD 优势

✅ **原生集成** - 无需额外安装任何工具
✅ **配置简单** - 一个 YAML 文件完成所有配置
✅ **性能优化** - 17.1.8 的性能提升显著
✅ **可视化强** - 清晰的 Pipeline 视图
✅ **调试方便** - 实时日志和错误提示
✅ **社区版免费** - 所有功能完全免费

### 与 Jenkins 对比

| 特性 | GitLab 17.1.8 | Jenkins 2.528.2 |
|------|---------------|-----------------|
| 安装配置 | ✅ 无需安装 | ❌ 需要单独安装 |
| 学习曲线 | ✅ 简单 | ❌ 复杂 |
| 维护成本 | ✅ 低 | ❌ 高 |
| 可视化 | ✅ 原生支持 | ⚠️ 需要插件 |
| 性能 | ✅ 快速 | ⚠️ 一般 |

**推荐使用 GitLab CI/CD！** 🎉

---

## 下一步

1. ✅ 配置 CI/CD 变量
2. ✅ 安装并注册 Runner
3. ✅ 提交 `.gitlab-ci.yml`
4. ✅ 推送代码触发 Pipeline
5. ✅ 查看部署结果

完成这些步骤后，您的 CI/CD 流程就完全自动化了！

---

## 参考资源

- GitLab 17.1 发布说明: https://about.gitlab.com/releases/2024/07/18/gitlab-17-1-released/
- GitLab CI/CD 文档: https://docs.gitlab.com/ee/ci/
- GitLab Runner 文档: https://docs.gitlab.com/runner/
- `.gitlab-ci.yml` 语法: https://docs.gitlab.com/ee/ci/yaml/
