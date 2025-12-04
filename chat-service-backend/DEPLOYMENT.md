# chat-service-backend 多环境部署指南

## 环境配置

项目支持 4 个环境，每个环境有独立的配置文件：

| 环境 | 配置文件 | 说明 |
|------|---------|------|
| **dev** | `manifest/config/config.dev.yaml` | 开发环境 (默认) |
| **fat** | `manifest/config/config.fat.yaml` | 功能验收测试环境 |
| **uat** | `manifest/config/config.uat.yaml` | 用户验收测试环境 |
| **pro** | `manifest/config/config.pro.yaml` | 生产环境 |

## 配置文件说明

### 环境差异

| 配置项 | dev | fat | uat | pro |
|--------|-----|-----|-----|-----|
| **数据库 Debug** | ✅ 开启 | ✅ 开启 | ❌ 关闭 | ❌ 关闭 |
| **日志级别** | all | all | info | error |
| **控制台输出** | ✅ 开启 | ✅ 开启 | ✅ 开启 | ❌ 关闭 |
| **gRPC 服务** | ❌ 关闭 | ❌ 关闭 | ❌ 关闭 | ✅ 开启 |
| **JWT Secret** | 通用密钥 | 通用密钥 | 通用密钥 | 独立密钥 |

### 修改配置

根据实际环境修改对应的配置文件：

```yaml
# 示例：修改 UAT 环境的数据库配置
# 编辑 manifest/config/config.uat.yaml

database:
  default:
    link: "root:password@tcp(192.168.x.x:3306)/dbname?loc=Local&parseTime=true"

redis:
  default:
    address: 192.168.x.x:6379
    pass: your-redis-password
    db: 6
```

## 本地运行

### 使用指定环境配置启动

```bash
# 使用 dev 环境配置（默认）
go run main.go http

# 使用 fat 环境配置
go run main.go http -c manifest/config/config.fat.yaml

# 使用 uat 环境配置
go run main.go http -c manifest/config/config.uat.yaml

# 使用 pro 环境配置
go run main.go http -c manifest/config/config.pro.yaml
```

## Docker 部署

### 构建 Docker 镜像

#### 方法一：使用 Makefile（推荐）

```bash
# 构建 dev 环境镜像（默认）
make image

# 构建 fat 环境镜像
make image ENV=fat

# 构建 uat 环境镜像
make image ENV=uat

# 构建 pro 环境镜像并指定标签
make image ENV=pro TAG=v1.0.0

# 构建并推送到镜像仓库
make image.push ENV=pro TAG=v1.0.0
```

#### 方法二：使用 docker build 命令

```bash
# 构建 dev 环境镜像（默认）
docker build -f manifest/docker/Dockerfile -t chat-service:dev .

# 构建 pro 环境镜像（使用 build-arg）
docker build -f manifest/docker/Dockerfile \
  --build-arg ENV=pro \
  -t chat-service:pro .
```

### 运行 Docker 容器

```bash
# 运行 dev 环境容器
docker run -d --name chat-service-dev \
  -p 8080:8080 \
  chat-service:dev

# 运行 pro 环境容器
docker run -d --name chat-service-pro \
  -p 8080:8080 \
  chat-service:pro

# 或者在运行时覆盖环境变量
docker run -d --name chat-service \
  -p 8080:8080 \
  -e APP_ENV=uat \
  chat-service:latest
```

### 使用 Docker Compose

创建 `docker-compose.yml`：

```yaml
version: '3.8'

services:
  chat-service:
    image: chat-service:${TAG:-latest}
    container_name: chat-service-${ENV:-dev}
    ports:
      - "8080:8080"
    environment:
      - APP_ENV=${ENV:-dev}
    restart: unless-stopped
    volumes:
      - ./storage:/app/storage
```

运行命令：

```bash
# 启动 dev 环境
docker-compose up -d

# 启动 pro 环境
ENV=pro TAG=v1.0.0 docker-compose up -d
```

## Kubernetes 部署

### 部署到 K8s

```bash
# 部署 develop 环境（默认）
make deploy

# 部署 production 环境
make deploy TAG=production ENV=pro
```

### K8s ConfigMap 方式

如果需要在 K8s 中动态管理配置，可以使用 ConfigMap：

```bash
# 创建 ConfigMap
kubectl create configmap chat-service-config-pro \
  --from-file=config.yaml=manifest/config/config.pro.yaml \
  -n your-namespace

# 在 Deployment 中挂载
# manifest/deploy/kustomize/base/deployment.yaml
volumes:
  - name: config
    configMap:
      name: chat-service-config-pro
volumeMounts:
  - name: config
    mountPath: /app/manifest/config/config.pro.yaml
    subPath: config.yaml
```

## 环境验证

### 验证服务启动

```bash
# 健康检查
curl http://localhost:8080/

# 应返回: "hello word"
```

### 验证配置加载

检查日志确认加载的配置文件：

```bash
# Docker 容器日志
docker logs chat-service | grep "config"

# 本地运行日志
tail -f storage/log/*.log | grep "config"
```

### 验证数据库连接

```bash
# 检查数据库连接日志
docker logs chat-service | grep -i "mysql\|database"

# 检查 Redis 连接日志
docker logs chat-service | grep -i "redis"
```

## 常见问题

### 1. 配置文件未找到

**错误**: `config file not found: manifest/config/config.xxx.yaml`

**解决**:
- 确保配置文件存在于正确路径
- 检查 Docker 镜像是否包含配置文件 (`resource/manifest/config/`)

### 2. 环境变量未生效

**错误**: 容器使用了错误的配置文件

**解决**:
- 检查 `APP_ENV` 环境变量是否正确设置
- 重新构建镜像时确保传递了 `--build-arg ENV=xxx`

### 3. 权限问题

**错误**: `permission denied: manifest/config/config.yaml`

**解决**:
```bash
# 修复配置文件权限
chmod 644 chat-service-backend/manifest/config/config.*.yaml
```

## 最佳实践

1. **配置管理**
   - 敏感信息（密码、密钥）不要提交到 Git
   - 使用环境变量或 Secret 管理工具
   - 定期更新生产环境的 JWT Secret

2. **日志管理**
   - 开发/测试环境：使用 `level: all` 方便调试
   - 生产环境：使用 `level: error` 减少日志量
   - 生产环境关闭控制台输出，使用文件日志

3. **性能优化**
   - 生产环境关闭数据库 Debug 模式
   - 根据负载调整 gRPC 和 etcd 配置
   - 启用 Redis 连接池优化

4. **安全加固**
   - 生产环境使用独立的 JWT Secret
   - 限制 API 访问（使用 API Gateway）
   - 定期更新依赖和镜像

## 回滚策略

如果部署出现问题，快速回滚：

```bash
# Docker 回滚到上一个版本
docker stop chat-service-pro
docker rm chat-service-pro
docker run -d --name chat-service-pro \
  -p 8080:8080 \
  chat-service:v0.9.0

# K8s 回滚
kubectl rollout undo deployment/chat-service -n your-namespace
```

## 监控和告警

建议配置以下监控：

- **服务健康检查**: 每 30 秒检查 `/` 端点
- **数据库连接**: 监控连接池状态
- **Redis 连接**: 监控 Redis 可用性
- **日志告警**: 监控 ERROR 级别日志
- **性能指标**: CPU、内存、响应时间

---

## 相关文档

- [GoFrame 官方文档](https://goframe.org/)
- [Docker 多阶段构建](https://docs.docker.com/develop/develop-images/multistage-build/)
- [Kubernetes ConfigMap](https://kubernetes.io/docs/concepts/configuration/configmap/)
