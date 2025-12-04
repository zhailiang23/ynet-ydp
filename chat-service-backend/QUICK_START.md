# chat-service-backend 快速开始

## 🚀 快速部署（推荐）

使用自动化部署脚本一键部署任意环境：

```bash
# 部署开发环境
./deploy.sh dev

# 部署 FAT 测试环境
./deploy.sh fat

# 部署 UAT 验收环境
./deploy.sh uat

# 部署生产环境（指定版本）
./deploy.sh pro v1.0.0
```

脚本会自动完成：
- ✅ 验证环境配置
- ✅ 构建 Docker 镜像
- ✅ 停止旧容器
- ✅ 启动新容器
- ✅ 健康检查
- ✅ 显示部署信息

## 📋 环境说明

| 环境 | 用途 | 日志级别 | 数据库 Debug | gRPC |
|------|------|----------|-------------|------|
| **dev** | 开发环境 | all | ✅ 开启 | ❌ |
| **fat** | 功能测试 | all | ✅ 开启 | ❌ |
| **uat** | 验收测试 | info | ❌ 关闭 | ❌ |
| **pro** | 生产环境 | error | ❌ 关闭 | ✅ |

## ⚙️ 配置修改

编辑对应环境的配置文件：

```bash
# 编辑 dev 环境配置
vim manifest/config/config.dev.yaml

# 编辑 pro 环境配置
vim manifest/config/config.pro.yaml
```

主要配置项：

```yaml
database:
  default:
    link: "root:password@tcp(host:3306)/dbname?loc=Local&parseTime=true"

redis:
  default:
    address: host:6379
    pass: password
    db: 6
```

## 🛠️ 本地开发

### 使用指定配置启动

```bash
# dev 环境（默认）
go run main.go http

# fat 环境
go run main.go http -c manifest/config/config.fat.yaml

# uat 环境
go run main.go http -c manifest/config/config.uat.yaml

# pro 环境
go run main.go http -c manifest/config/config.pro.yaml
```

### 后台运行

```bash
# 后台启动 dev 环境
nohup go run main.go http > dev.log 2>&1 &

# 查看日志
tail -f dev.log

# 停止服务
pkill -f "go run main.go"
```

## 🐳 Docker 部署

### 方法一：使用 Makefile

```bash
# 构建 dev 环境镜像
make image

# 构建 pro 环境镜像并指定版本
make image ENV=pro TAG=v1.0.0

# 构建并推送到仓库
make image.push ENV=pro TAG=v1.0.0
```

### 方法二：使用 docker build

```bash
# 构建 dev 环境
docker build -f manifest/docker/Dockerfile -t chat-service:dev .

# 构建 pro 环境
docker build -f manifest/docker/Dockerfile \
  --build-arg ENV=pro \
  -t chat-service:pro .
```

### 运行容器

```bash
# 运行 dev 环境
docker run -d --name chat-service-dev \
  -p 8080:8080 \
  chat-service:dev

# 运行 pro 环境
docker run -d --name chat-service-pro \
  -p 8080:8080 \
  -e APP_ENV=pro \
  chat-service:pro
```

## ✅ 验证部署

### 健康检查

```bash
# 检查服务是否启动
curl http://localhost:8080/

# 应返回: "hello word"
```

### 查看日志

```bash
# 本地运行日志
tail -f storage/log/*.log

# Docker 容器日志
docker logs -f chat-service-dev

# 查看最近 100 行日志
docker logs --tail 100 chat-service-dev
```

### 查看数据库连接

```bash
# 检查 MySQL 连接
docker logs chat-service-dev | grep -i mysql

# 检查 Redis 连接
docker logs chat-service-dev | grep -i redis
```

## 🔧 常用命令

### Docker 管理

```bash
# 查看运行中的容器
docker ps | grep chat-service

# 停止容器
docker stop chat-service-dev

# 重启容器
docker restart chat-service-dev

# 删除容器
docker rm -f chat-service-dev

# 进入容器
docker exec -it chat-service-dev sh

# 查看容器详情
docker inspect chat-service-dev
```

### 镜像管理

```bash
# 查看镜像列表
docker images | grep chat-service

# 删除镜像
docker rmi chat-service:dev

# 清理未使用的镜像
docker image prune -a
```

### 日志管理

```bash
# 实时查看日志
docker logs -f chat-service-dev

# 查看最近 N 行日志
docker logs --tail 100 chat-service-dev

# 查看指定时间范围的日志
docker logs --since 10m chat-service-dev

# 清理日志
truncate -s 0 $(docker inspect --format='{{.LogPath}}' chat-service-dev)
```

## 🐛 故障排查

### 服务无法启动

1. 检查配置文件是否存在
```bash
ls -l manifest/config/config.*.yaml
```

2. 检查端口是否被占用
```bash
lsof -i :8080
```

3. 查看详细日志
```bash
docker logs chat-service-dev
```

### 数据库连接失败

1. 检查数据库配置
```bash
cat manifest/config/config.dev.yaml | grep -A 5 database
```

2. 测试数据库连接
```bash
mysql -h host -P 3306 -u root -p -e "SELECT 1;"
```

3. 检查网络连接
```bash
ping database-host
telnet database-host 3306
```

### Redis 连接失败

1. 检查 Redis 配置
```bash
cat manifest/config/config.dev.yaml | grep -A 5 redis
```

2. 测试 Redis 连接
```bash
redis-cli -h host -p 6379 -a password ping
```

## 📚 更多文档

- [完整部署文档](DEPLOYMENT.md) - 详细的部署指南和最佳实践
- [GoFrame 文档](https://goframe.org/) - 框架官方文档
- [Docker 文档](https://docs.docker.com/) - Docker 使用指南

## 🆘 获取帮助

查看部署脚本帮助：
```bash
./deploy.sh --help
```

查看 Makefile 可用命令：
```bash
make help
```

---

**提示**: 生产环境部署前请务必：
- ✅ 修改默认的 JWT Secret
- ✅ 使用强密码保护数据库和 Redis
- ✅ 配置适当的日志级别
- ✅ 启用必要的监控和告警
