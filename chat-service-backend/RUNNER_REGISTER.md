# GitLab Runner 注册指南 - chat-service-backend

## 复用 192.168.153.111 上的 Runner

### 方法一：注册为共享 Runner（推荐）

在 GitLab 管理员界面将 Runner 设置为 Shared Runner，这样所有项目都可以使用。

**步骤**:
1. 访问 GitLab Admin Area: http://git.ynet.io/admin
2. 左侧菜单 → CI/CD → Runners
3. 找到 `frontend-practice-runner`
4. 点击 "Edit"
5. 勾选 "Run untagged jobs" (如果需要)
6. 保存

### 方法二：为当前项目单独注册 Runner

**获取 Registration Token**:

1. 访问项目: http://git.ynet.io/belink/ai-agent/ai-digital-avatar/chat-service-backend
2. 左侧菜单 → Settings → CI/CD
3. 展开 "Runners" 部分
4. 复制 "Registration token"

**SSH 到服务器注册**:

```bash
# SSH 到服务器
ssh root@192.168.153.111

# 注册 Runner
gitlab-runner register \
  --non-interactive \
  --url "http://git.ynet.io/" \
  --registration-token "YOUR_REGISTRATION_TOKEN" \
  --executor "docker" \
  --docker-image "docker:24.0.5" \
  --description "chat-service-backend-runner" \
  --tag-list "docker,shell" \
  --run-untagged="true" \
  --locked="false" \
  --docker-privileged="true" \
  --docker-volumes "/var/run/docker.sock:/var/run/docker.sock" \
  --docker-volumes "/cache" \
  --docker-network-mode "host"
```

**参数说明**:
- `--url`: GitLab 地址
- `--registration-token`: 从项目设置中获取
- `--executor`: 使用 docker executor
- `--docker-image`: 默认镜像
- `--tag-list`: Runner 标签（与 .gitlab-ci.yml 中的 tags 对应）
- `--run-untagged`: 允许运行没有标签的任务
- `--docker-privileged`: 允许特权模式（构建 Docker 镜像需要）
- `--docker-volumes`: 挂载 Docker socket
- `--docker-network-mode`: 使用主机网络模式

### 方法三：修改现有 Runner 配置支持多项目

如果 Runner 已经注册为 Group Runner，可以修改配置文件：

```bash
# SSH 到服务器
ssh root@192.168.153.111

# 编辑配置文件
vim /etc/gitlab-runner/config.toml
```

确保配置中 `locked = false`，这样 Runner 可以被多个项目使用：

```toml
[[runners]]
  name = "frontend-practice-runner"
  url = "http://git.ynet.io/"
  token = "..."
  executor = "docker"
  locked = false  # 允许其他项目使用
  [runners.docker]
    tls_verify = false
    image = "docker:24.0.5"
    privileged = true
    volumes = ["/var/run/docker.sock:/var/run/docker.sock", "/cache"]
    network_mode = "host"
```

修改后重启 Runner:
```bash
gitlab-runner restart
```

## 验证 Runner

### 检查 Runner 状态

```bash
# SSH 到服务器
ssh root@192.168.153.111

# 列出所有 Runner
gitlab-runner list

# 验证 Runner
gitlab-runner verify
```

### 在 GitLab Web 界面检查

1. 访问项目: http://git.ynet.io/belink/ai-agent/ai-digital-avatar/chat-service-backend
2. 左侧菜单 → Settings → CI/CD
3. 展开 "Runners" 部分
4. 应该能看到可用的 Runner 列表

**绿色圆点** 表示 Runner 在线且可用。

## 快速注册脚本

如果需要快速注册，将以下内容保存为 `register-runner.sh`:

```bash
#!/bin/bash

# 配置
GITLAB_URL="http://git.ynet.io/"
REGISTRATION_TOKEN="YOUR_TOKEN_HERE"  # 从项目设置中获取
RUNNER_NAME="chat-service-backend-runner"

# 注册 Runner
gitlab-runner register \
  --non-interactive \
  --url "$GITLAB_URL" \
  --registration-token "$REGISTRATION_TOKEN" \
  --executor "docker" \
  --docker-image "docker:24.0.5" \
  --description "$RUNNER_NAME" \
  --tag-list "docker,shell" \
  --run-untagged="true" \
  --locked="false" \
  --docker-privileged="true" \
  --docker-volumes "/var/run/docker.sock:/var/run/docker.sock" \
  --docker-volumes "/cache" \
  --docker-network-mode "host"

# 验证
gitlab-runner verify

# 重启
gitlab-runner restart

echo "Runner registered successfully!"
```

运行:
```bash
chmod +x register-runner.sh
./register-runner.sh
```

## 注意事项

1. **Registration Token 有效期**: 如果 token 过期，需要重新从项目设置中获取
2. **标签匹配**: `.gitlab-ci.yml` 中的 `tags: [docker, shell]` 必须与 Runner 的标签匹配
3. **网络模式**: 必须使用 `network_mode = "host"` 以访问 git.ynet.io
4. **特权模式**: 构建 Docker 镜像需要 `privileged = true`
5. **Docker Socket**: 必须挂载 `/var/run/docker.sock` 以在 Docker 容器中构建 Docker 镜像

## 常见问题

### Runner 显示离线
```bash
# 检查 Runner 服务状态
systemctl status gitlab-runner

# 重启 Runner
gitlab-runner restart
```

### Runner 无法访问 Git 仓库
确保配置中有 `network_mode = "host"`，参考 frontend-practice 的配置。

### Pipeline 卡在 "Pending"
检查 Runner 标签是否匹配：
- `.gitlab-ci.yml` 中的 `tags`
- Runner 注册时的 `--tag-list`

## 推荐方案

**最简单的方法**: 使用方法二为 `chat-service-backend` 单独注册一个 Runner

1. 获取 Registration Token
2. SSH 到 192.168.153.111
3. 运行注册命令
4. 验证 Runner 状态
5. 重新触发 Pipeline

这样可以确保配置与 frontend-practice 完全一致，并且互不干扰。

## ✅ 已完成

- Runner #2 (fCU7wrak3) 已在项目中启用
- 标签: docker, shell
- 状态: 在线（绿色）
- 服务器: 192.168.153.111
