# frontend-mbda 部署文档

## 多环境配置

项目支持 4 个环境的配置：

| 环境 | 配置文件 | 后端地址 | 用途 |
|------|---------|---------|------|
| dev | `.env.dev` | http://192.168.153.111:48080 | 开发环境 |
| fat | `.env.fat` | http://192.168.153.111:48080 | 功能验收测试环境 |
| uat | `.env.uat` | http://uat-api.example.com | 用户验收测试环境 |
| pro | `.env.pro` | http://prod-api.example.com | 生产环境 |

## GitLab CI/CD 自动部署

### 流水线阶段

1. **build** - 构建 Docker 镜像并推送到 Harbor
2. **deploy** - 部署到目标服务器（FAT 环境）
3. **cleanup** - 清理服务器上的旧镜像（保留最新 5 个）

### 环境变量

在 `.gitlab-ci.yml` 中配置：

```yaml
variables:
  # Harbor 配置
  HARBOR_URL: "192.168.152.56"
  HARBOR_PROJECT: "ai-mobile-terminal"
  HARBOR_USER: "search"
  HARBOR_PASSWORD: "Search123"

  # 部署服务器配置
  DEPLOY_SERVER: "192.168.153.111"
  DEPLOY_USER: "root"

  # 环境配置（部署到 FAT 环境）
  VITE_ENV: "fat"
```

### 触发条件

流水线在 `master` 分支有新的提交时自动触发。

### 部署流程

1. 代码推送到 GitLab master 分支
2. GitLab Runner 拉取代码
3. 构建 Docker 镜像（使用 `.env.fat` 配置）
4. 推送镜像到 Harbor (192.168.152.56)
5. SSH 连接到部署服务器 (192.168.153.111)
6. 执行零停机部署（启动新容器 → 健康检查 → 切换流量 → 停止旧容器）
7. 清理旧镜像（保留最新 5 个版本）

## 本地构建和运行

### 开发环境

```bash
# 安装依赖
npm install

# 启动开发服务器
npm run dev
```

### 构建 Docker 镜像

```bash
# 构建 FAT 环境镜像
docker build --build-arg VITE_ENV=fat -t frontend-mbda:fat .

# 构建其他环境镜像
docker build --build-arg VITE_ENV=dev -t frontend-mbda:dev .
docker build --build-arg VITE_ENV=uat -t frontend-mbda:uat .
docker build --build-arg VITE_ENV=pro -t frontend-mbda:pro .
```

### 运行容器

```bash
# 运行 FAT 环境容器
docker run -d \
  --name frontend-mbda \
  -p 5667:80 \
  -e VITE_ENV=fat \
  -e TZ=Asia/Shanghai \
  --restart unless-stopped \
  frontend-mbda:fat

# 查看容器日志
docker logs -f frontend-mbda

# 查看容器状态
docker ps | grep frontend-mbda
```

## 部署服务器配置

### 端口分配

- **前端服务**: 5667 (Nginx)
- **健康检查**: http://192.168.153.111:5667/health

### 目录结构

```
/root/zhailiang/
├── frontend-mbda/          # 部署脚本目录
│   └── deploy.sh           # 部署脚本
└── logs/
    └── frontend-mbda/      # 日志目录
```

## 健康检查

容器启动后，Nginx 会自动提供健康检查端点：

```bash
# 本地检查
curl http://localhost:5667/health

# 远程检查
curl http://192.168.153.111:5667/health
```

返回 `healthy` 表示服务正常。

## 故障排查

### 查看容器日志

```bash
docker logs -f frontend-mbda
```

### 查看容器状态

```bash
docker ps | grep frontend-mbda
```

### 查看镜像列表

```bash
docker images | grep frontend-mbda
```

### 重新部署

```bash
# SSH 到部署服务器
ssh root@192.168.153.111

# 手动执行部署脚本
bash /root/zhailiang/frontend-mbda/deploy.sh \
  192.168.152.56/ai-mobile-terminal/frontend-mbda:123 \
  192.168.152.56 \
  search \
  Search123 \
  fat
```

## 访问地址

- **FAT 环境**: http://192.168.153.111:5667
- **健康检查**: http://192.168.153.111:5667/health

## 注意事项

1. **SSH 密钥**: 部署脚本中已嵌入 SSH 私钥，请确保私钥安全
2. **Harbor 凭据**: Harbor 密码已硬编码，请注意保密
3. **端口冲突**: 确保 5667 端口未被占用
4. **零停机部署**: 部署过程中服务不会中断
5. **自动回滚**: 如果新容器健康检查失败，会自动回滚到旧版本
