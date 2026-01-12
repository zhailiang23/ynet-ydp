# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是基于易诚开源框架 (RuoYi-Vue-Pro) 的企业级快速开发平台，采用 Spring Boot 多模块架构。

- **技术栈**: JDK 17 + Spring Boot 3.5.5 + MyBatis Plus + Redis + MySQL
- **架构类型**: 单体应用（多模块 Maven 项目）
- **当前分支**: master-jdk17（JDK 17/21 版本）
- **启动类**: `iplatform-server/src/main/java/com.ynet.iplatform/server/IplatformServerApplication.java`

## 构建和运行

### 本地开发环境准备

1. **开发环境数据库**
   - **数据库地址**: 192.168.110.41:3306
   - **数据库名**: ynet-iplatform
   - **账号密码**: root/root123
   - MySQL 版本: 8.0+
   - **注意**: 开发环境已切换到远程数据库，无需本地启动 MySQL 和 Redis

2. **开发环境 Redis**
   - 使用远程 Redis 服务（与数据库同服务器）
   - 具体配置见 `application-fat.yaml`

3. **本地测试环境（可选）**
   如果需要本地隔离环境测试，可使用 Docker Compose:
   ```bash
   cd script/docker
   docker-compose up -d mysql redis
   ```
   - 本地数据库: 127.0.0.1:3306/ruoyi-vue-pro
   - 本地 Redis: 127.0.0.1:6379
   - 需要切换 profile 为 `local`

### 构建命令

**重要**: 在执行 Maven compile 之前必须先执行 clean

```bash
# 清理并编译整个项目
mvn clean compile

# 清理并打包
mvn clean package -DskipTests

# 运行单元测试
mvn clean test

# 跳过测试打包
mvn clean package -DskipTests
```

### 运行应用

**开发环境模式（推荐）**:
```bash
# 方式1: 使用 Maven 运行（连接开发环境数据库）
cd iplatform-server
mvn spring-boot:run -Dspring-boot.run.profiles=fat

# 方式2: 直接运行主类
# 运行 IplatformServerApplication.java，使用 VM options: -Dspring.profiles.active=fat
```

**本地测试模式**:
```bash
# 需要先启动 Docker Compose 中的 MySQL 和 Redis
cd script/docker
docker-compose up -d mysql redis

# 然后使用 local profile 启动
cd iplatform-server
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

**访问地址**:
- 后端服务: http://localhost:48080
- API 文档 (Swagger): http://localhost:48080/swagger-ui/index.html
- Knife4j 文档: http://localhost:48080/doc.html
- Druid 监控: http://localhost:48080/druid

### 环境配置

项目支持多环境配置，通过 `spring.profiles.active` 切换:
- `local`: 本地隔离测试环境（使用 Docker Compose）
- `dev`: 开发环境（**推荐日常开发使用**）
- `fat`: 功能验收测试环境
- `uat`: 用户验收测试环境
- `pro`: 生产环境

**配置文件位置**: `iplatform-server/src/main/resources/application-{profile}.yaml`

**开发环境配置** (`application-dev.yaml` 或 `application-fat.yaml`):
- 端口: 48080
- 数据库: jdbc:mysql://192.168.110.41:3306/ynet-iplatform
- 账号密码: root/root123
- **推荐使用**: 日常开发直接连接开发环境数据库

**本地测试配置** (`application-local.yaml`):
- 端口: 48080
- 数据库: jdbc:mysql://127.0.0.1:3306/ruoyi-vue-pro
- Redis: 127.0.0.1:6379
- 账号密码: root/123456
- **适用场景**: 需要本地隔离环境测试时使用

**环境切换方式**:

1. **Maven 运行时指定**:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

2. **直接运行 JAR 时指定**:
   ```bash
   java -Dspring.profiles.active=dev -jar iplatform-server.jar
   ```

3. **Docker 运行时指定**:
   ```bash
   docker run -e SPRING_PROFILES_ACTIVE=dev -p 48080:48080 your-image:tag
   ```

4. **修改主配置文件** (`application.yaml`):
   ```yaml
   spring:
     profiles:
       active: dev  # 修改为目标环境
   ```

## 项目架构

### 模块结构

项目采用三层架构 + 模块化设计:

```
Controller (Web层: @RestController)
    ↓ 调用
Service (业务层: @Service)
    ↓ 调用
Mapper (数据访问层: MyBatis Plus)
    ↓ 操作
DataObject (数据库实体: DO)
```

**关键模块**:

1. **iplatform-dependencies**: Maven BOM 依赖版本管理
   - 统一管理所有第三方依赖版本
   - 使用 `${revision}` 变量管理项目版本 (2025.10-SNAPSHOT)

2. **iplatform-framework**: 技术框架层（18个子模块）
   - `iplatform-common`: 基础 POJO、枚举、工具类
   - `iplatform-spring-boot-starter-mybatis`: MyBatis + 多数据源
   - `iplatform-spring-boot-starter-redis`: Redis + Redisson
   - `iplatform-spring-boot-starter-security`: Spring Security 认证
   - `iplatform-spring-boot-starter-web`: Web MVC 增强
   - `iplatform-spring-boot-starter-test`: 单元测试基础类

3. **iplatform-server**: 服务聚合模块（主启动模块）

4. **业务模块** (iplatform-module-*):
   - `iplatform-module-system`: 系统功能（用户、角色、权限、租户等）
   - `iplatform-module-infra`: 基础设施（文件、日志、配置、代码生成）
   - 其他业务模块默认注释掉，按需启用:
     - `iplatform-module-member`: 会员中心
     - `iplatform-module-bpm`: 工作流 (Flowable)
     - `iplatform-module-pay`: 支付系统
     - `iplatform-module-mall`: 商城系统
     - `iplatform-module-crm`: CRM 系统
     - `iplatform-module-erp`: ERP 系统
     - `iplatform-module-ai`: AI 大模型
     - `iplatform-module-iot`: 物联网

### 启用/禁用模块

在 `pom.xml` 中取消注释相应的 `<module>` 标签即可启用业务模块。

### 代码生成器

项目内置代码生成器，可快速生成 CRUD 代码:
- 访问: 系统管理 -> 代码生成
- 生成内容: Controller、Service、Mapper、DO、VO、SQL、单元测试
- 配置: `iplatform.codegen` 配置项

## 测试

### 单元测试

**测试框架**: JUnit 5 + Mockito + Spring Boot Test

**运行测试**:
```bash
# 运行所有测试
mvn clean test

# 运行特定模块的测试
cd iplatform-module-system
mvn test

# 运行单个测试类
mvn test -Dtest=UserServiceImplTest

# 运行单个测试方法
mvn test -Dtest=UserServiceImplTest#testCreateUser
```

**测试配置**: 每个模块的测试配置位于 `src/test/resources/application-unit-test.yaml`

**测试基类**: 继承 `BaseDbUnitTest` 或 `BaseRedisUnitTest` 来编写单元测试

## 核心技术点

### 多租户 SaaS

- 默认启用多租户功能 (`iplatform.tenant.enable: true`)
- 透明化多租户封装，自动处理租户隔离
- 每个租户可自定义权限（租户套餐）

### 数据权限

- 支持部门级、用户级的行级数据权限
- 基于 MyBatis Plus 拦截器实现

### 多数据源

- 使用 Dynamic Datasource 支持主从读写分离
- 配置路径: `spring.datasource.dynamic.datasource`
- 支持 MySQL、PostgreSQL、Oracle、SQL Server、达梦DM、人大金仓等

### 工作流引擎

- 基于 Flowable 7.0.1
- 支持 BPMN 设计器 和 Simple（仿钉钉）设计器
- 配置: `flowable.database-schema-update: true`

### WebSocket

- 启用配置: `iplatform.websocket.enable: true`
- 访问路径: `/infra/ws`
- 支持集群模式，消息发送类型可选: local、redis、rocketmq、kafka、rabbitmq

### API 加密

- 配置: `iplatform.api-encrypt.enable: true`
- 支持 AES、RSA 加密算法

### 缓存

- 一级缓存: Spring Cache + Redis
- 二级缓存: Redisson
- 过期时间: 1小时 (可配置)

## 重要配置说明

### Maven 编译配置

项目使用以下注解处理器（按顺序）:
1. Spring Boot Configuration Processor
2. Lombok
3. Lombok MapStruct Binding (关键: 确保 Lombok 生成的 getter/setter 能被 MapStruct 识别)
4. MapStruct Processor

### MyBatis Plus ID 策略

默认使用 "智能" 模式 (`id-type: NONE`):
- MySQL 等数据库: 自动适配为 AUTO (自增)
- Oracle、PostgreSQL 等: 自动适配为 INPUT (手动输入)

### 日志和监控

- **链路追踪**: 支持 SkyWalking 9.5.0
- **应用监控**: Spring Boot Admin 3.5.2
- **数据库监控**: Druid 连接池监控
- **API 日志**: 自动记录所有 RESTful API 访问日志和异常日志

## 常见问题

### 启动问题

如果遇到启动问题，请参考官方文档: https://doc.iocoder.cn/quick-start/

**常见原因**:
1. MySQL 或 Redis 未启动
2. 数据库未初始化或连接配置错误
3. 端口 48080 被占用
4. JDK 版本不是 17 或 21

### 数据库兼容性

项目支持多种数据库，切换时需要:
1. 修改 `application-local.yaml` 中的数据源 URL
2. 执行对应数据库的初始化脚本（位于 `sql/` 目录）
3. 根据数据库类型调整 `mybatis-plus.global-config.db-config.id-type`

### 模块依赖问题

项目允许循环依赖 (`spring.main.allow-circular-references: true`)，这是三层架构的特性。

## 开发规范

- 遵循《阿里巴巴 Java 开发手册》
- 代码注释要详细（项目有 42462 行代码注释）
- 使用 Lombok 简化代码
- 使用 MapStruct 进行 Bean 转换 (DO -> VO)
- VO 命名: xxxRespVO (响应)、xxxReqVO (请求)
- DO 命名: xxxDO (数据库实体)
- 异常处理: 使用自定义异常 + 错误码管理

## 部署

### Docker 部署

#### 构建 Docker 镜像

```bash
# 1. 清理并打包项目
cd backend
mvn clean package -DskipTests

# 2. 构建 Docker 镜像
cd iplatform-server
docker build -t iplatform-server:latest .
```

#### 运行 Docker 容器（多环境支持）

**方式一：使用环境变量指定环境** (推荐)

```bash
# 开发环境
docker run -d \
  --name iplatform-server-dev \
  -e SPRING_PROFILES_ACTIVE=dev \
  -p 48080:48080 \
  iplatform-server:latest

# FAT 环境
docker run -d \
  --name iplatform-server-fat \
  -e SPRING_PROFILES_ACTIVE=fat \
  -p 48080:48080 \
  iplatform-server:latest

# UAT 环境
docker run -d \
  --name iplatform-server-uat \
  -e SPRING_PROFILES_ACTIVE=uat \
  -p 48080:48080 \
  iplatform-server:latest

# 生产环境
docker run -d \
  --name iplatform-server-pro \
  -e SPRING_PROFILES_ACTIVE=pro \
  -p 48080:48080 \
  iplatform-server:latest
```

**方式二：使用 Docker Compose**

修改 `docker-compose.yml`，添加环境变量：

```yaml
services:
  server:
    image: iplatform-server:latest
    environment:
      - SPRING_PROFILES_ACTIVE=dev  # 修改为目标环境: dev, fat, uat, pro
      - TZ=Asia/Shanghai
    ports:
      - "48080:48080"
```

然后启动：

```bash
# 进入 docker 脚本目录
cd script/docker

# 启动所有服务（MySQL、Redis、后端、前端）
docker-compose up -d

# 查看日志
docker-compose logs -f server
```

#### 环境变量说明

| 环境变量 | 说明 | 默认值 | 可选值 |
|---------|------|--------|--------|
| `SPRING_PROFILES_ACTIVE` | Spring 环境配置 | `dev` | `local`, `dev`, `fat`, `uat`, `pro` |
| `JAVA_OPTS` | JVM 参数 | `-Xms512m -Xmx512m` | 根据需要调整 |
| `TZ` | 时区 | `Asia/Shanghai` | 任意时区 |

#### 多环境配置文件说明

所有环境配置文件位于 `iplatform-server/src/main/resources/`:
- `application-local.yaml` - 本地开发环境
- `application-dev.yaml` - 开发环境
- `application-fat.yaml` - 功能验收测试环境
- `application-uat.yaml` - 用户验收测试环境
- `application-pro.yaml` - 生产环境

**注意**: 部署前请根据实际环境修改对应配置文件中的数据库、Redis 等连接信息。

## 文档资源

- 官方文档: https://doc.iocoder.cn
- 启动文档: https://doc.iocoder.cn/quick-start/
- 视频教程: https://doc.iocoder.cn/video/
- API 文档: http://localhost:48080/doc.html (启动后访问)
