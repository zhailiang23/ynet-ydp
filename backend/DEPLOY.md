# Backend 部署指南

## 概述

本文档说明如何通过 GitLab CI/CD 自动部署 iplatform-server (backend) 到生产服务器。

## 部署架构

```
GitLab Repository (git.ynet.io)
    ↓ (Push to master)
GitLab Runner (192.168.153.111)
    ↓
1. Build Stage: 构建 Spring Boot JAR + Docker 镜像
    ↓
2. Push: 推送镜像到 Harbor (192.168.152.56)
    ↓
3. Deploy Stage: 零停机部署到服务器
    ↓
4. Cleanup Stage: 清理旧镜像
```

## 环境配置

### 支持的环境

| 环境 | Spring Profile | 说明 |
|------|---------------|------|
| 本地开发 | `local` | 本地开发环境 |
| 开发环境 | `dev` | 开发服务器 |
| FAT 环境 | `fat` | 功能验收测试环境（默认部署） |
| UAT 环境 | `uat` | 用户验收测试环境 |
| 生产环境 | `pro` | 生产服务器 |

### 当前部署配置

- **目标服务器**: 192.168.153.111
- **部署环境**: FAT
- **容器名称**: iplatform-server
- **端口**: 48080
- **部署目录**: /root/zhailiang/iplatform-server
- **日志目录**: /root/zhailiang/logs/iplatform-server

## GitLab CI/CD 流程

### Pipeline 阶段

1. **build** - 构建 Spring Boot JAR 和 Docker 镜像
   - 使用 Maven 3.9.6 + OpenJDK 21
   - 配置阿里云 Maven 镜像加速
   - 跳过单元测试 (`-DskipTests`)
   - 构建 Docker 镜像并推送到 Harbor

2. **deploy** - 部署到 FAT 环境
   - SSH 连接到目标服务器
   - 执行 `deploy.sh` 脚本
   - 零停机部署（蓝绿部署策略）
   - 健康检查（最多 5 分钟）

3. **cleanup** - 清理旧镜像
   - 保留最近 5 个版本
   - 删除更旧的镜像

### 触发条件

- **分支**: 仅 `master` 分支
- **方式**: Git push 到 master 分支自动触发

### 关键变量

| 变量 | 值 | 说明 |
|------|-----|------|
| `HARBOR_URL` | 192.168.152.56 | Harbor 镜像仓库地址 |
| `HARBOR_PROJECT` | sysadmin-2025 | Harbor 项目名称 |
| `IMAGE_NAME` | 192.168.152.56/sysadmin-2025/iplatform-server | 完整镜像名称 |
| `DEPLOY_SERVER` | 192.168.153.111 | 部署目标服务器 |
| `SPRING_PROFILE` | fat | 激活的环境配置 |
| `CONTAINER_PORT` | 48080 | 容器端口 |

## 部署脚本说明

### deploy.sh 功能

零停机部署脚本，支持：

1. **蓝绿部署**: 启动新容器 → 健康检查 → 切换流量 → 停止旧容器
2. **自动回滚**: 健康检查失败时自动回滚到旧版本
3. **健康检查**:
   - 检查 Spring Boot Actuator (`/actuator/health`)
   - 或检查 API 文档 (`/doc.html`)
   - 最多尝试 60 次，每次间隔 5 秒（总共 5 分钟）
4. **镜像清理**: 保留最新 3 个版本
5. **详细日志**: 记录每个步骤的执行情况

### 使用方法

手动执行（仅用于调试）:

```bash
ssh root@192.168.153.111
cd /root/zhailiang/iplatform-server

bash deploy.sh \
  192.168.152.56/sysadmin-2025/iplatform-server:123 \
  192.168.152.56 \
  search \
  Search123 \
  fat
```

参数说明:
1. 镜像名称:标签
2. Harbor 地址
3. Harbor 用户名
4. Harbor 密码
5. Spring Profile (local/dev/fat/uat/pro)

## 部署前准备

### 1. 在 GitLab 创建仓库

确保 backend 目录已推送到 GitLab：

```bash
# 如果还没有推送，需要先创建 GitLab 项目
# 项目地址: git@git.ynet.io:belink/sysadmin-2025/backend.git
```

### 2. 配置 GitLab Runner

在目标服务器 (192.168.153.111) 上安装并注册 GitLab Runner:

```bash
# 安装 GitLab Runner
curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.rpm.sh | bash
yum install gitlab-runner

# 注册 Runner
gitlab-runner register \
  --url https://git.ynet.io/ \
  --token <YOUR_RUNNER_TOKEN> \
  --executor shell \
  --description "backend-runner" \
  --tag-list "docker,shell"
```

### 3. 配置 Harbor 项目

在 Harbor (192.168.152.56) 上创建项目：

- 项目名称: `sysadmin-2025`
- 访问级别: 私有
- 确保用户 `search` 有 push/pull 权限

### 4. 修改环境配置文件

根据实际环境修改配置文件（部署前必须完成）：

**FAT 环境** (`backend/iplatform-server/src/main/resources/application-fat.yaml`):

```yaml
spring:
  datasource:
    dynamic:
      datasource:
        master:
          url: jdbc:mysql://<FAT_DB_HOST>:3306/ruoyi-vue-pro?...
          username: <FAT_DB_USER>
          password: <FAT_DB_PASSWORD>
  data:
    redis:
      host: <FAT_REDIS_HOST>
      port: 6379
      password: <FAT_REDIS_PASSWORD>
```

**其他环境** (`application-uat.yaml`, `application-pro.yaml`): 同样需要修改

## 部署操作

### 方式一：自动部署（推荐）

```bash
# 1. 提交代码到 master 分支
git add .
git commit -m "feat: 添加新功能"
git push origin master

# 2. Pipeline 自动触发，可在 GitLab 查看进度:
# https://git.ynet.io/belink/sysadmin-2025/backend/-/pipelines
```

### 方式二：手动触发

在 GitLab 项目页面:
1. 进入 CI/CD → Pipelines
2. 点击 "Run pipeline"
3. 选择 master 分支
4. 点击 "Run pipeline"

## 部署后验证

### 1. 检查 Pipeline 状态

访问 GitLab Pipeline 页面，确认所有阶段都成功（绿色）。

### 2. 检查容器状态

```bash
ssh root@192.168.153.111

# 查看容器是否运行
docker ps | grep iplatform-server

# 查看容器日志
docker logs -f iplatform-server

# 查看最近 100 行日志
docker logs --tail 100 iplatform-server
```

### 3. 访问应用

- **API 服务**: http://192.168.153.111:48080
- **API 文档 (Knife4j)**: http://192.168.153.111:48080/doc.html
- **Swagger UI**: http://192.168.153.111:48080/swagger-ui/index.html
- **Druid 监控**: http://192.168.153.111:48080/druid

### 4. 验证健康检查

```bash
# 健康检查端点（如果启用了 Actuator）
curl http://192.168.153.111:48080/actuator/health

# 或者访问 API 文档
curl -I http://192.168.153.111:48080/doc.html
```

### 5. 检查环境配置

确认 Spring Profile 已正确激活:

```bash
# 查看容器环境变量
docker exec iplatform-server env | grep SPRING_PROFILES_ACTIVE

# 或查看日志中的启动信息
docker logs iplatform-server 2>&1 | grep "The following profiles are active"
```

## 切换部署环境

如需部署到其他环境（如 UAT 或 PRO），修改 `.gitlab-ci.yml`:

```yaml
variables:
  SPRING_PROFILE: "uat"  # 修改为: uat 或 pro
```

**注意**:
1. 修改前请确保对应的环境配置文件已正确配置
2. 不同环境可能需要不同的服务器，需同时修改 `DEPLOY_SERVER`

## 故障排查

### Pipeline 失败

#### 构建阶段失败

```bash
# 常见原因:
1. Maven 依赖下载失败 → 检查网络连接
2. 编译错误 → 检查代码是否有语法错误
3. Docker 镜像构建失败 → 检查 Dockerfile 语法
```

**解决方法**:
- 查看 Pipeline 日志中的详细错误信息
- 本地执行 `mvn clean package -DskipTests` 验证编译

#### 部署阶段失败

```bash
# 常见原因:
1. SSH 连接失败 → 检查 SSH 私钥配置
2. 镜像拉取失败 → 检查 Harbor 连接和权限
3. 健康检查失败 → 检查应用启动日志
```

**解决方法**:
```bash
# 1. SSH 到服务器
ssh root@192.168.153.111

# 2. 查看部署脚本日志
cat /root/zhailiang/iplatform-server/deploy.log

# 3. 查看容器日志
docker logs iplatform-server_new 2>&1 | tail -100

# 4. 手动拉取镜像测试
docker pull 192.168.152.56/sysadmin-2025/iplatform-server:latest
```

### 应用启动失败

#### 数据库连接失败

```bash
# 症状: 日志中出现 "Cannot load driver class: com.mysql.cj.jdbc.Driver"
# 或 "Connection refused"

# 解决:
1. 检查配置文件中的数据库地址是否正确
2. 确认数据库服务是否运行
3. 检查网络连通性: telnet <DB_HOST> 3306
4. 验证用户名密码是否正确
```

#### Redis 连接失败

```bash
# 症状: 日志中出现 "Unable to connect to Redis"

# 解决:
1. 检查 Redis 地址和端口
2. 确认 Redis 服务是否运行
3. 检查防火墙规则
4. 验证 Redis 密码（如果有）
```

#### 端口被占用

```bash
# 症状: 容器启动失败，提示端口已被占用

# 解决:
# 查看占用端口的进程
lsof -i:48080

# 停止旧容器
docker stop iplatform-server
docker rm iplatform-server

# 或者修改映射端口
docker run -p 48081:48080 ...
```

### 回滚操作

如果部署后发现问题，可以快速回滚：

#### 方式一：通过 Docker

```bash
ssh root@192.168.153.111

# 1. 停止当前容器
docker stop iplatform-server
docker rm iplatform-server

# 2. 查看可用的旧镜像
docker images | grep iplatform-server

# 3. 使用旧镜像启动容器
docker run -d \
  --name iplatform-server \
  -p 48080:48080 \
  -e SPRING_PROFILES_ACTIVE=fat \
  -e TZ=Asia/Shanghai \
  -v /root/zhailiang/logs/iplatform-server:/iplatform-server/logs \
  --restart unless-stopped \
  192.168.152.56/sysadmin-2025/iplatform-server:<OLD_TAG>
```

#### 方式二：重新部署旧版本

1. 在 GitLab 找到之前成功的 Pipeline
2. 点击 "Retry" 按钮重新运行

## 性能优化

### JVM 参数调整

修改 `deploy.sh` 中的 `JAVA_OPTS`:

```bash
# 当前配置
-e JAVA_OPTS="-Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom"

# 根据服务器内存调整:
# - 4GB 内存服务器: -Xms512m -Xmx2048m
# - 8GB 内存服务器: -Xms1024m -Xmx4096m
# - 16GB 内存服务器: -Xms2048m -Xmx8192m
```

### 容器资源限制

添加 CPU 和内存限制:

```bash
docker run -d \
  --name iplatform-server \
  --memory="4g" \
  --memory-swap="4g" \
  --cpus="2.0" \
  ...
```

## 安全注意事项

1. **敏感信息管理**
   - SSH 私钥嵌入在 `.gitlab-ci.yml` 中（仅限私有仓库）
   - Harbor 密码明文传递（生产环境应使用 GitLab Secret Variables）
   - 数据库密码存储在配置文件中（应使用环境变量或配置中心）

2. **网络安全**
   - 确保 Harbor 仓库配置了防火墙规则
   - 限制 SSH 访问 IP 白名单
   - 使用 HTTPS 访问 API（配置 Nginx 反向代理）

3. **日志管理**
   - 定期清理容器日志: `docker logs --tail 1000 iplatform-server > app.log`
   - 配置日志轮转策略
   - 敏感信息不要记录到日志

## 相关文档

- [后端开发指南](./CLAUDE.md)
- [Dockerfile 说明](./iplatform-server/Dockerfile)
- [GitLab CI/CD 配置](./.gitlab-ci.yml)
- [部署脚本](./deploy.sh)
- [前端部署指南](../frontend-practice/GITLAB_CI_CD_GUIDE.md)

## 联系方式

如遇问题，请联系:
- 运维团队: ops@ynet.io
- 开发团队: dev@ynet.io
- GitLab Issues: https://git.ynet.io/belink/sysadmin-2025/backend/issues
