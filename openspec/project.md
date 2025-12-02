# Project Context

## Purpose

易诚快速开发平台 (YNet-YDP) 是一个企业级前后端分离的快速开发平台，旨在为企业提供开箱即用的系统功能和业务模块。

**核心目标**:
- 提供完整的系统管理、权限控制、多租户 SaaS 支持
- 支持工作流、支付、CRM、ERP、商城、AI 等完整业务系统
- 100% 开源免费，遵循 MIT 协议
- 基于成熟框架和最佳实践，降低企业开发成本

## Tech Stack

### 后端 (Backend)
- **语言**: Java 17
- **框架**: Spring Boot 3.5.5 (多模块单体架构)
- **数据库**: MySQL 8.0+ (支持 Oracle、PostgreSQL、达梦DM、人大金仓等)
- **ORM**: MyBatis Plus 3.5.14 + MyBatis Plus Join 1.5.4
- **缓存**: Redis 6.0+ + Redisson 3.51.0
- **安全**: Spring Security 6.5.2 + JWT Token
- **工作流**: Flowable 7.0.1
- **API 文档**: Springdoc + Knife4j (OpenAPI 3.0)
- **监控**: SkyWalking 9.5.0 + Spring Boot Admin 3.5.2
- **构建工具**: Maven 3.9.11
- **对象转换**: MapStruct 1.6.3
- **工具类**: Lombok 1.18.38

### 前端 (Frontend)
- **框架**: Vue 3.5.17 (Composition API)
- **构建工具**: Vite 7.1.2
- **架构**: Monorepo (pnpm workspace + Turbo)
- **UI 框架**: 支持三种
  - Ant Design Vue 4.2.6 (主应用)
  - Element Plus 2.10.2
  - Naive UI 2.42.0
- **语言**: TypeScript 5.8.3
- **状态管理**: Pinia 3.0.3
- **路由**: Vue Router 4.5.1
- **HTTP 客户端**: Axios 1.10.0
- **样式**: Tailwind CSS 3.4.17 + PostCSS
- **国际化**: Vue-i18n 11.1.7
- **表单验证**: vee-validate 4.15.1 + zod 3.25.67
- **图表**: ECharts 5.6.0
- **工作流设计器**: BPMN-js

## Project Conventions

### Code Style

**后端 Java 规范**:
- 遵循《阿里巴巴 Java 开发手册》规范
- 使用 Lombok 简化代码 (避免手写 getter/setter)
- 使用 MapStruct 进行 Bean 转换 (DO <-> VO)
- 允许循环依赖 (三层架构特性): `spring.main.allow-circular-references: true`

**命名约定 (后端)**:
- Controller: `xxxController.java`
- Service: `xxxService.java` (接口) + `xxxServiceImpl.java` (实现)
- Mapper: `xxxMapper.java` + `xxxMapper.xml`
- DO (数据库实体): `xxxDO.java`
- VO (视图对象): `xxxRespVO.java` (响应) / `xxxReqVO.java` (请求)
- 常量类: 使用接口定义常量

**前端 TypeScript/Vue 规范**:
- 使用 TypeScript，充分利用类型系统
- 组件优先使用 Composition API
- 遵循 ESLint + Prettier + Stylelint 规范
- 使用 2 空格缩进，UTF-8 编码

**命名约定 (前端)**:
- 组件文件: PascalCase (如 `UserList.vue`)
- API 文件: kebab-case (如 `user-api.ts`)
- Store 文件: kebab-case (如 `auth-store.ts`)
- 工具函数: camelCase

### Architecture Patterns

**后端架构**:

1. **三层架构**:
   ```
   Controller (Web层) -> Service (业务层) -> Mapper (数据访问层) -> DO (数据库实体)
   ```

2. **模块化设计**:
   - `yudao-framework`: 框架层 (可复用的技术组件)
   - `yudao-module-*`: 业务模块 (独立功能模块)
   - `yudao-server`: 聚合层 (整合所有模块)

3. **核心模式**:
   - **多租户 SaaS**: 透明化多租户封装，自动处理租户隔离
   - **数据权限**: 基于 MyBatis Plus 拦截器实现行级权限
   - **多数据源**: Dynamic Datasource 支持主从读写分离
   - **动态路由**: 基于权限动态生成菜单和路由
   - **API 加密**: 可选的 AES/RSA 请求响应加密

4. **异常处理**:
   - 使用自定义异常 + 错误码管理
   - 统一异常处理器 `@RestControllerAdvice`
   - 错误码可在线修改，无需重启服务

**前端架构**:

1. **Monorepo 架构**:
   - `apps/`: 应用层 (3 个 UI 框架版本)
   - `packages/`: 共享包 (核心库、组件、工具)
   - `internal/`: 内部工具 (配置工厂)

2. **页面结构**:
   ```
   View (页面) -> API (请求) -> Store (状态) -> Components (组件)
   ```

3. **API 对接**:
   - RESTful API 通信
   - 统一响应格式: `{ code: 0, data: {...}, msg: "success" }`
   - 自动 Token 刷新机制
   - 请求/响应拦截器处理认证、多租户、加密

4. **权限控制**:
   - 登录后获取用户权限信息
   - 动态生成路由和菜单
   - 路由守卫控制访问权限

### Testing Strategy

**后端测试**:
- **框架**: JUnit 5 + Mockito + Spring Boot Test
- **测试类型**:
  - 单元测试: Service 层业务逻辑测试
  - 集成测试: API 接口测试
- **测试基类**: 继承 `BaseDbUnitTest` (数据库测试) 或 `BaseRedisUnitTest` (Redis 测试)
- **测试配置**: 每个模块独立的 `application-unit-test.yaml`
- **Mock 数据**: 使用 PODAM 8.0.2 生成测试数据
- **覆盖率要求**: 所有功能必须有单元测试保证高质量

**前端测试**:
- **单元测试**: Vitest + @vue/test-utils
- **E2E 测试**: Playwright
- **测试命令**:
  - `pnpm test:unit`: 运行单元测试
  - `pnpm test:e2e`: 运行 E2E 测试

### Git Workflow

**分支策略**:
- `master`: 稳定生产版本 (JDK 8 + Spring Boot 2.7)
- `master-jdk17`: JDK 17/21 版本 (当前开发分支)
- `feature/*`: 功能分支
- `bugfix/*`: 修复分支

**提交规范**:
- 使用语义化提交信息
- 不包含 Claude/AI 相关信息
- 提交前必须通过代码检查

**重要**:
- 不要在修改后直接提交并推送代码，等待明确要求时再推送
- 执行 git 命令时需要忽略代理 (在 `~/.zshrc` 中已设置代理)

## Domain Context

### 业务领域

1. **系统管理**: 用户、角色、权限、部门、岗位、菜单、字典、租户、租户套餐
2. **基础设施**: 代码生成、文件服务、定时任务、数据源、系统接口、API 日志
3. **工作流程**: BPMN 设计器、Simple (仿钉钉) 设计器、流程实例、任务管理
4. **支付系统**: 支付宝、微信支付、支付订单、退款订单
5. **会员中心**: 会员管理、会员等级、会员标签、积分签到
6. **CRM 系统**: 客户、商机、合同、回款、线索
7. **ERP 系统**: 采购、销售、库存、财务、产品
8. **商城系统**: 商品、订单、购物车、营销、优惠券
9. **微信公众号**: 粉丝管理、消息管理、自动回复、菜单管理、素材管理
10. **AI 大模型**: 对话、绘画、音乐、写作、思维导图 (支持 OpenAI、通义千问、文心一言等)
11. **IoT 物联网**: 设备管理、物模型、设备告警

### 核心概念

- **多租户 (Tenant)**: 每个租户独立的数据和权限配置
- **租户套餐 (Tenant Package)**: 定义租户可访问的菜单、操作、按钮权限
- **数据权限**: 部门级、用户级的行级数据访问控制
- **动态路由**: 根据用户权限动态生成前端路由和菜单
- **工作流**: 支持 BPMN 标准流程和简易流程 (仿钉钉)

## Important Constraints

### 技术约束

1. **JDK 版本**: 必须使用 JDK 17 或 21 (master-jdk17 分支)
2. **Node.js 版本**: >= 20.10.0 (推荐 22.1.0)
3. **包管理器**: 前端强制使用 pnpm >= 10.14.0，不能使用 npm/yarn
4. **数据库**: MySQL 8.0+ (或其他兼容数据库)
5. **Redis**: 必须启用 Redis，系统严重依赖缓存

### 开发约束

1. **Maven 编译**: 执行 `mvn compile` 前必须先执行 `mvn clean`
2. **循环依赖**: 后端允许循环依赖 (三层架构特性)
3. **依赖管理**:
   - 后端使用 `yudao-dependencies` BOM 统一版本
   - 前端使用 pnpm catalog 统一版本
4. **环境变量**:
   - 后端使用 `spring.profiles.active` 切换环境
   - 前端使用 `.env.development` / `.env.production` 配置

### 业务约束

1. **多租户**: 默认启用，如不需要可配置 `yudao.tenant.enable: false`
2. **验证码**: 可配置开关 `VITE_APP_CAPTCHA_ENABLE`
3. **API 加密**: 前后端加密密钥必须一致
4. **文件上传**: 支持本地存储、S3 (MinIO、阿里云、腾讯云、七牛云)

## External Dependencies

### 基础设施

1. **数据库**: MySQL 8.0+ (必需)
   - 默认连接: `jdbc:mysql://127.0.0.1:3306/ruoyi-vue-pro`
   - 默认账号: root/123456
   - 初始化脚本: `backend/sql/mysql/ruoyi-vue-pro.sql`

2. **缓存**: Redis 6.0+ (必需)
   - 默认连接: `127.0.0.1:6379`
   - 用途: 缓存、Session、分布式锁、消息队列

3. **Docker** (可选，推荐用于本地开发):
   - Docker Compose: `backend/script/docker/docker-compose.yml`
   - 包含 MySQL、Redis、后端服务、前端应用

### 第三方服务 (可选)

1. **对象存储**:
   - MinIO (推荐)
   - 阿里云 OSS
   - 腾讯云 COS
   - 七牛云 Kodo
   - AWS S3

2. **短信服务**:
   - 阿里云短信
   - 腾讯云短信

3. **支付**:
   - 支付宝支付
   - 微信支付

4. **微信生态**:
   - 微信公众号
   - 微信小程序
   - 企业微信

5. **AI 大模型**:
   - OpenAI (GPT-4、GPT-3.5)
   - 通义千问 (阿里云)
   - 文心一言 (百度)
   - 智谱 AI
   - 讯飞星火
   - 字节豆包
   - 腾讯混元
   - Midjourney (绘画)
   - Suno (音乐)

6. **监控**:
   - SkyWalking (链路追踪、日志中心)
   - Spring Boot Admin (应用监控)
   - Druid (数据库连接池监控)

### 开发工具

1. **构建工具**:
   - 后端: Maven 3.9.11
   - 前端: pnpm 10.14.0 + Turbo

2. **代码生成**: 内置代码生成器，可生成 Controller、Service、Mapper、VO、SQL、测试代码

3. **API 文档**:
   - Swagger UI: http://localhost:48080/swagger-ui/index.html
   - Knife4j: http://localhost:48080/doc.html

### 环境要求

**开发环境**:
- 后端: JDK 17/21 + Maven 3.9+ + MySQL 8.0+ + Redis 6.0+
- 前端: Node.js 20.10+ + pnpm 10.14+
- 推荐使用 Docker Compose 快速启动依赖服务

**生产环境**:
- 支持 Docker 容器化部署
- 支持传统 JAR 包部署
- 前端支持 Nginx 静态资源部署
