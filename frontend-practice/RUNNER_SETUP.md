# GitLab Runner 故障排查指南

## 问题现象

GitLab CI/CD Pipeline 在 "Getting source from Git repository" 阶段失败：

```
fatal: unable to access 'http://git.ynet.io/belink/ai-agent/ai-coach/frontend-practice.git/':
Failed to connect to git.ynet.io port 80 after 254/32 ms: Could not connect to server
```

## 根本原因

GitLab Runner 在 Docker 容器内无法访问 Git 仓库，原因是 Docker 容器使用了隔离的网络环境。

## 解决方案（已实施）

修改 GitLab Runner 配置文件 `/etc/gitlab-runner/config.toml`，在 `[runners.docker]` 部分添加：

```toml
network_mode = "host"
```

完整配置：

```toml
[[runners]]
  name = "frontend-practice-runner"
  url = "http://git.ynet.io/"
  executor = "docker"
  [runners.docker]
    tls_verify = false
    image = "docker:24.0.5"
    privileged = true
    volumes = ["/var/run/docker.sock:/var/run/docker.sock", "/cache"]
    network_mode = "host"  # 使用主机网络模式
```

## 已执行的操作

1. ✅ 停止 GitLab Runner: `gitlab-runner stop`
2. ✅ 修改配置文件: 添加 `network_mode = "host"`
3. ✅ 启动 GitLab Runner: `gitlab-runner start`
4. ✅ 验证 Runner 状态: `gitlab-runner verify`

## 验证结果

```bash
$ gitlab-runner verify
Running in system-mode.
Verifying runner... is valid  runner=fCU7wrak3
```

Runner 已成功验证！

## 下一步

重新触发 Pipeline，验证构建是否成功。

# Test Pipeline Wed Dec  3 09:47:47 CST 2025
# Final test Wed Dec  3 09:53:33 CST 2025
