# 多环境配置更新日志

## 更新时间
2025-12-04

## 更新内容

### 1. 配置文件优化

新增 4 个环境配置文件，支持独立配置：

```
manifest/config/
├── config.dev.yaml     # 开发环境（默认）
├── config.fat.yaml     # 功能验收测试环境
├── config.uat.yaml     # 用户验收测试环境
├── config.pro.yaml     # 生产环境
└── config.example.yaml # 配置示例模板
```

### 2. 环境配置差异

| 配置项 | dev | fat | uat | pro |
|--------|-----|-----|-----|-----|
| 数据库 Debug | ✅ | ✅ | ❌ | ❌ |
| 日志级别 | all | all | info | error |
| 控制台输出 | ✅ | ✅ | ✅ | ❌ |
| gRPC 服务 | ❌ | ❌ | ❌ | ✅ |
| JWT Secret | 通用 | 通用 | 通用 | 独立 |

### 3. Dockerfile 优化

**修改点**:
- 新增 `ARG ENV=dev` 构建参数
- 新增 `ENV APP_ENV=${ENV}` 环境变量
- 修改启动命令：`CMD ./main -c=manifest/config/config.${APP_ENV}.yaml`

**使用方法**:
```bash
# 构建指定环境的镜像
docker build --build-arg ENV=pro -t chat-service:pro .
```

### 4. Makefile 优化

**新增功能**:
- 支持 `ENV` 参数指定构建环境
- 默认环境为 `dev`
- 自动传递环境变量到 Docker 构建

**使用方法**:
```bash
# 构建 dev 环境（默认）
make image

# 构建 pro 环境
make image ENV=pro TAG=v1.0.0
```

### 5. 新增部署脚本

**文件**: `deploy.sh`

**功能**:
- 一键部署任意环境
- 自动验证配置文件
- 自动构建 Docker 镜像
- 自动停止旧容器并启动新容器
- 自动健康检查
- 彩色日志输出

**使用方法**:
```bash
# 查看帮助
./deploy.sh --help

# 部署 dev 环境
./deploy.sh dev

# 部署 pro 环境（指定版本）
./deploy.sh pro v1.0.0
```

### 6. 新增文档

| 文件 | 说明 |
|------|------|
| `DEPLOYMENT.md` | 完整的多环境部署文档 |
| `QUICK_START.md` | 快速开始指南 |
| `CHANGELOG_MULTI_ENV.md` | 本更新日志 |

## 使用示例

### 本地开发

```bash
# 使用 dev 环境配置
go run main.go http

# 使用 pro 环境配置
go run main.go http -c manifest/config/config.pro.yaml
```

### Docker 部署

```bash
# 方法一：使用部署脚本（推荐）
./deploy.sh pro v1.0.0

# 方法二：使用 Makefile
make image ENV=pro TAG=v1.0.0

# 方法三：直接使用 docker build
docker build --build-arg ENV=pro -t chat-service:pro .
docker run -d -p 8080:8080 --name chat-service-pro chat-service:pro
```

## 迁移指南

### 从单环境迁移

**旧方式**:
```bash
# 只有一个 config.yaml
docker build -t chat-service:latest .
docker run -d -p 8080:8080 chat-service:latest
```

**新方式**:
```bash
# 使用环境参数
docker build --build-arg ENV=dev -t chat-service:dev .
docker run -d -p 8080:8080 chat-service:dev

# 或使用部署脚本
./deploy.sh dev
```

### 配置文件迁移

1. 原 `config.yaml` 已复制为 `config.dev.yaml`
2. 根据需要修改其他环境的配置：
   ```bash
   # 修改 FAT 环境配置
   vim manifest/config/config.fat.yaml
   
   # 修改生产环境配置
   vim manifest/config/config.pro.yaml
   ```

## 注意事项

1. **配置文件权限**
   ```bash
   # 确保配置文件有正确的权限
   chmod 644 manifest/config/config.*.yaml
   ```

2. **敏感信息管理**
   - 不要将包含真实密码的配置文件提交到 Git
   - 使用环境变量或 Secret 管理工具
   - 生产环境务必修改默认的 JWT Secret

3. **日志管理**
   - 生产环境建议使用 `level: error`
   - 开发环境使用 `level: all` 方便调试

4. **gRPC 配置**
   - 单机部署建议关闭 gRPC
   - 集群部署必须开启 gRPC 和 etcd

## 回滚方案

如果新版本有问题，可快速回滚：

```bash
# 停止新版本容器
docker stop chat-service-pro
docker rm chat-service-pro

# 启动旧版本容器
docker run -d --name chat-service-pro \
  -p 8080:8080 \
  chat-service:v0.9.0
```

## 常见问题

**Q: 如何验证当前使用的是哪个环境配置？**

A: 查看容器环境变量或日志：
```bash
# 查看环境变量
docker exec chat-service-dev env | grep APP_ENV

# 查看启动日志
docker logs chat-service-dev | grep config
```

**Q: 如何在运行时切换环境？**

A: 需要重新构建和部署：
```bash
./deploy.sh uat  # 切换到 UAT 环境
```

**Q: 配置文件修改后需要重新构建镜像吗？**

A: 是的，配置文件打包在镜像中，修改后需要重新构建。

## 后续计划

- [ ] 支持配置文件热加载
- [ ] 集成 ConfigMap/Secret（K8s 环境）
- [ ] 添加配置文件加密
- [ ] 完善监控和告警配置

---

**相关文档**:
- [快速开始](QUICK_START.md)
- [完整部署文档](DEPLOYMENT.md)
