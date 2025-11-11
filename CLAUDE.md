<!-- OPENSPEC:START -->
# OpenSpec Instructions

These instructions are for AI assistants working in this project.

Always open `@/openspec/AGENTS.md` when the request:
- Mentions planning or proposals (words like proposal, spec, change, plan)
- Introduces new capabilities, breaking changes, architecture shifts, or big performance/security work
- Sounds ambiguous and you need the authoritative spec before coding

Use `@/openspec/AGENTS.md` to learn:
- How to create and apply change proposals
- Spec format and conventions
- Project structure and guidelines

Keep this managed block so 'openspec update' can refresh the instructions.

<!-- OPENSPEC:END -->

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个基于芋道开源框架的企业级**前后端分离**快速开发平台。

- **后端**: JDK 17 + Spring Boot 3.5.5 + MyBatis Plus + Redis + MySQL (单体多模块架构)
- **前端**: Vue 3 + Vite 7 + TypeScript + Pinia (Monorepo 架构，支持 Ant Design Vue / Element Plus / Naive UI)
- **架构**: 前后端完全分离，通过 RESTful API 通信
- **许可**: MIT 开源协议，100% 免费使用

**演示地址**:
- Vue3 + Vben (Ant Design): http://dashboard-vben.yudao.iocoder.cn
- Vue3 + Element Plus: http://dashboard-vue3.yudao.iocoder.cn
- Vue2 + Element UI: http://dashboard.yudao.iocoder.cn

**官方文档**:
- 启动文档: https://doc.iocoder.cn/quick-start/
- 视频教程: https://doc.iocoder.cn/video/

## 项目结构

```
ynet-ydp/
├── backend/              # 后端项目 (Spring Boot)
│   ├── yudao-server/    # 主启动模块
│   ├── yudao-framework/ # 框架层 (18个子模块)
│   ├── yudao-module-*   # 业务模块
│   ├── sql/             # 数据库脚本
│   └── script/          # 部署脚本
└── frontend/            # 前端项目 (Vue 3 Monorepo)
    ├── apps/            # 应用层
    │   ├── web-antd/   # Ant Design Vue 版本 (主应用)
    │   ├── web-ele/    # Element Plus 版本
    │   └── web-naive/  # Naive UI 版本
    ├── packages/        # 共享包
    └── internal/        # 内部工具
```

---

# 后端开发指南

## 技术栈

- **框架**: Spring Boot 3.5.5 (JDK 17/21)
- **数据库**: MySQL 8.0+ (支持 Oracle、PostgreSQL、达梦DM、人大金仓等)
- **ORM**: MyBatis Plus 3.5.14 + MyBatis Plus Join 1.5.4
- **缓存**: Redis 6.0+ (Redisson 3.51.0)
- **权限**: Spring Security 6.5.2 + Token
- **工作流**: Flowable 7.0.1
- **API文档**: Springdoc + Knife4j
- **监控**: SkyWalking 9.5.0 + Spring Boot Admin 3.5.2

## 本地开发环境准备

### 1. 依赖服务

**方式一: 使用 Docker Compose (推荐)**
```bash
cd backend/script/docker
docker-compose up -d mysql redis
```

**方式二: 手动安装**
- MySQL 8.0+ (默认端口 3306)
  - 数据库名: ruoyi-vue-pro
  - 账号密码: root/123456
  - 初始化脚本: `backend/sql/mysql/ruoyi-vue-pro.sql`
- Redis 6.0+ (默认端口 6379)

### 2. 构建和运行

**重要**: 在执行 Maven compile 之前必须先执行 clean

```bash
cd backend

# 清理并编译
mvn clean compile

# 清理并打包
mvn clean package -DskipTests

# 运行测试
mvn clean test

# 启动应用 (本地开发环境)
cd yudao-server
mvn spring-boot:run -Dspring-boot.run.profiles=local

# 或者直接运行主类
# 运行 backend/yudao-server/src/main/java/cn/iocoder/yudao/server/YudaoServerApplication.java
# VM options: -Dspring.profiles.active=local
```

### 3. 访问地址

启动成功后 (默认端口 48080):
- 后端服务: http://localhost:48080
- API 文档 (Swagger): http://localhost:48080/swagger-ui/index.html
- Knife4j 文档: http://localhost:48080/doc.html
- Druid 监控: http://localhost:48080/druid

### 4. 环境配置

环境通过 `spring.profiles.active` 切换:
- `local`: 本地开发环境 (默认端口 48080)
- `dev`: 开发环境
- 配置文件位置: `backend/yudao-server/src/main/resources/application-{profile}.yaml`

**本地开发配置** (`application-local.yaml`):
```yaml
server:
  port: 48080

spring:
  datasource:
    dynamic:
      datasource:
        master:
          url: jdbc:mysql://127.0.0.1:3306/ruoyi-vue-pro?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true&nullCatalogMeansCurrent=true&rewriteBatchedStatements=true
          username: root
          password: 123456
  data:
    redis:
      host: 127.0.0.1
      port: 6379
      database: 0
```

## 后端架构

### 模块结构

采用**三层架构** + **模块化设计**:

```
Controller (Web层)
    ↓
Service (业务层)
    ↓
Mapper (数据访问层)
    ↓
DataObject (DO)
```

**关键模块**:

1. **yudao-dependencies** - Maven BOM 依赖版本管理
2. **yudao-framework** - 技术框架层 (18个子模块)
   - `yudao-common` - 基础 POJO、枚举、工具类
   - `yudao-spring-boot-starter-mybatis` - MyBatis + 多数据源
   - `yudao-spring-boot-starter-redis` - Redis + Redisson
   - `yudao-spring-boot-starter-security` - Spring Security 认证
   - `yudao-spring-boot-starter-web` - Web MVC 增强
   - `yudao-spring-boot-starter-test` - 单元测试基础类
3. **yudao-server** - 服务聚合模块 (主启动)
4. **业务模块** (yudao-module-*)
   - `yudao-module-system` - 系统功能 (用户、角色、权限、租户)
   - `yudao-module-infra` - 基础设施 (文件、日志、配置、代码生成)
   - 其他模块默认注释，按需启用 (member、bpm、pay、mall、crm、erp、ai、iot)

### 启用/禁用业务模块

在 `backend/pom.xml` 中取消注释相应的 `<module>` 标签。

### 代码生成器

访问系统管理 -> 代码生成，可快速生成 CRUD 代码 (Controller、Service、Mapper、DO、VO、SQL、单元测试)。

## 后端测试

### 运行测试

```bash
# 运行所有测试
cd backend
mvn clean test

# 运行特定模块的测试
cd yudao-module-system
mvn test

# 运行单个测试类
mvn test -Dtest=UserServiceImplTest

# 运行单个测试方法
mvn test -Dtest=UserServiceImplTest#testCreateUser
```

**测试框架**: JUnit 5 + Mockito + Spring Boot Test

**测试配置**: 每个模块的 `src/test/resources/application-unit-test.yaml`

**测试基类**: 继承 `BaseDbUnitTest` 或 `BaseRedisUnitTest`

## 后端核心技术点

### 多租户 SaaS
- 默认启用 (`yudao.tenant.enable: true`)
- 透明化封装，自动处理租户隔离
- 每个租户可自定义权限 (租户套餐)

### 数据权限
- 支持部门级、用户级行级权限
- 基于 MyBatis Plus 拦截器实现

### 多数据源
- 使用 Dynamic Datasource 支持主从读写分离
- 配置路径: `spring.datasource.dynamic.datasource`
- 支持多种数据库 (MySQL、PostgreSQL、Oracle、SQL Server、DM、KingBase)

### 工作流引擎
- 基于 Flowable 7.0.1
- 支持 BPMN 设计器 + Simple (仿钉钉) 设计器
- 配置: `flowable.database-schema-update: true`

### WebSocket
- 配置: `yudao.websocket.enable: true`
- 路径: `/infra/ws`
- 支持集群 (local、redis、rocketmq、kafka、rabbitmq)

### API 加密
- 配置: `yudao.api-encrypt.enable: true`
- 支持 AES、RSA 加密算法
- 前后端需使用相同的加密密钥

## 后端开发规范

- 遵循《阿里巴巴 Java 开发手册》
- 使用 Lombok 简化代码
- 使用 MapStruct 进行 Bean 转换 (DO -> VO)
- 命名规范:
  - VO: xxxRespVO (响应)、xxxReqVO (请求)
  - DO: xxxDO (数据库实体)
- 使用自定义异常 + 错误码管理

## 后端常见问题

### 启动问题

参考: https://doc.iocoder.cn/quick-start/

常见原因:
1. MySQL 或 Redis 未启动
2. 数据库未初始化
3. 端口 48080 被占用
4. JDK 版本不是 17 或 21

### 数据库切换

1. 修改 `application-local.yaml` 中的数据源 URL
2. 执行对应数据库的初始化脚本 (位于 `sql/` 目录)
3. 调整 `mybatis-plus.global-config.db-config.id-type`

---

# 前端开发指南

## 技术栈

- **Vue**: 3.5.17 (Composition API)
- **构建工具**: Vite 7.1.2
- **UI 框架**: Ant Design Vue 4.2.6 / Element Plus 2.10.2 / Naive UI 2.42.0
- **语言**: TypeScript 5.8.3
- **状态管理**: Pinia 3.0.3
- **路由**: Vue Router 4.5.1
- **HTTP**: Axios 1.10.0
- **样式**: Tailwind CSS 3.4.17
- **国际化**: Vue-i18n 11.1.7
- **表单验证**: vee-validate 4.15.1 + zod 3.25.67
- **图表**: ECharts 5.6.0
- **工作流**: BPMN-js

## 本地开发环境准备

### 1. 环境要求

**必须满足**:
- Node.js >= 20.10.0 (推荐 22.1.0)
- pnpm >= 10.14.0 (强制使用 pnpm，不能使用 npm/yarn)

```bash
# 安装 pnpm
npm install -g pnpm

# 检查版本
node -v   # 应该 >= 20.10.0
pnpm -v   # 应该 >= 10.14.0
```

### 2. 安装依赖

```bash
cd frontend

# 安装所有依赖 (Monorepo)
pnpm install
```

### 3. 启动开发服务器

```bash
# 启动 Ant Design Vue 版本 (主应用，推荐)
pnpm dev:antd

# 或启动 Element Plus 版本
pnpm dev:ele

# 或启动 Naive UI 版本
pnpm dev:naive
```

启动成功后访问: http://localhost:5666

**默认登录账号**:
- 用户名: admin
- 密码: admin123

### 4. 环境配置

前端有三个环境配置文件 (位于 `apps/web-antd/` 等应用目录):

- `.env` - 默认配置 (应用标题、命名空间、加密配置)
- `.env.development` - 开发环境
- `.env.production` - 生产环境

**开发环境关键配置** (`.env.development`):
```bash
VITE_PORT=5666                          # 前端端口
VITE_BASE_URL=http://127.0.0.1:48080   # 后端地址
VITE_GLOB_API_URL=/admin-api            # API 前缀 (会代理到后端)
VITE_APP_DEFAULT_USERNAME=admin         # 默认用户名
VITE_APP_DEFAULT_PASSWORD=admin123      # 默认密码
```

开发环境会自动代理 `/admin-api` 到 `http://localhost:48080/admin-api`，无需修改。

## 前端架构

### Monorepo 结构

项目采用 **Monorepo** 架构，使用 pnpm workspace + Turbo 管理:

```
frontend/
├── apps/              # 应用层 (3个UI框架版本)
├── packages/          # 共享包 (核心库、组件、工具)
├── internal/          # 内部工具 (配置工厂)
└── scripts/           # 构建脚本
```

### 应用结构 (以 web-antd 为例)

```
apps/web-antd/src/
├── api/               # API 模块 (与后端对接)
│   ├── request.ts    # Axios 实例和拦截器
│   ├── core/         # 核心 API (登录、权限)
│   ├── system/       # 系统管理 API
│   ├── infra/        # 基础设施 API
│   ├── bpm/          # 工作流 API
│   ├── crm/          # CRM API
│   ├── erp/          # ERP API
│   ├── mall/         # 商城 API
│   ├── pay/          # 支付 API
│   ├── mp/           # 微信公众号 API
│   ├── member/       # 会员中心 API
│   ├── ai/           # AI 大模型 API
│   └── iot/          # IoT API
├── router/            # 路由配置
│   ├── guard.ts      # 路由守卫 (权限控制)
│   └── routes/       # 路由定义
├── store/             # Pinia Store
├── views/             # 页面视图 (对应后端模块)
├── components/        # 业务组件
└── main.ts            # 应用入口
```

## 前端常用命令

### 开发相关

```bash
# 启动开发服务器
pnpm dev:antd         # Ant Design Vue 版本
pnpm dev:ele          # Element Plus 版本
pnpm dev:naive        # Naive UI 版本

# 构建生产版本
pnpm build:antd       # 构建 Ant Design Vue 版本
pnpm build:ele        # 构建 Element Plus 版本
pnpm build:naive      # 构建 Naive UI 版本

# 构建分析
pnpm build:analyze    # 分析构建大小

# 预览生产构建
cd apps/web-antd
pnpm preview          # 预览构建结果
```

### 代码质量

```bash
# 代码检查
pnpm lint             # ESLint 检查
pnpm format           # 代码格式化
pnpm check            # 运行所有检查 (循环依赖、依赖、类型、拼写)

# 类型检查
pnpm check:type       # TypeScript 类型检查

# 测试
pnpm test:unit        # 单元测试
pnpm test:e2e         # E2E 测试
```

### 其他

```bash
# 清理依赖和构建产物
pnpm clean

# 重新安装依赖
pnpm reinstall

# 更新依赖
pnpm update:deps
```

## 前后端对接

### API 请求配置

前端使用 Axios 与后端通信，配置位于 `apps/web-antd/src/api/request.ts`。

**开发环境**:
- 前端: http://localhost:5666
- 后端: http://localhost:48080
- API 代理: `/admin-api` -> `http://localhost:48080/admin-api`

**生产环境**:
- API: `http://127.0.0.1:48080/admin-api` (需修改为实际后端地址)

### 请求拦截器

自动添加以下请求头:
1. **认证**: `Authorization: Bearer {accessToken}`
2. **国际化**: `Accept-Language: {locale}`
3. **多租户**: `tenant-id: {tenantId}` (启用租户时)
4. **API 加密**: 根据配置进行请求加密

### 响应处理

1. **自动解密**: 根据 `X-Api-Encrypt` 头自动解密响应
2. **Token 刷新**: accessToken 过期时自动使用 refreshToken 刷新
3. **错误处理**: 自动显示错误信息 (message.error)
4. **401 处理**: 自动跳转到登录页

### 认证流程

1. 用户登录 -> 调用 `loginApi()` 获取 `accessToken` 和 `refreshToken`
2. 存储 token 到 Pinia Store (支持加密存储)
3. 获取用户信息和权限 -> 调用 `getAuthPermissionInfoApi()`
4. 动态生成路由和菜单
5. 重定向到首页

## 前端开发规范

- 使用 TypeScript，充分利用类型系统
- 组件使用 Composition API
- 遵循 ESLint + Prettier 规范
- 使用 Pinia 进行状态管理
- API 按模块划分，与后端模块对应
- 路由和菜单通过后端接口动态生成

## 前端常见问题

### 依赖安装问题

**必须使用 pnpm**，项目会强制检查包管理器:
```bash
pnpm install   # 正确
npm install    # 会报错
yarn install   # 会报错
```

### 端口冲突

默认前端端口 5666，如被占用可修改 `.env.development`:
```bash
VITE_PORT=5667  # 改为其他端口
```

### 后端连接失败

检查:
1. 后端是否已启动 (http://localhost:48080)
2. `.env.development` 中的 `VITE_BASE_URL` 是否正确
3. 网络代理配置是否正确

### 多租户问题

如果不需要多租户，修改 `.env`:
```bash
VITE_APP_TENANT_ENABLE=false
```

---

# 前后端联调

## 完整启动流程

### 1. 启动后端

```bash
# 终端 1: 启动依赖服务 (MySQL + Redis)
cd backend/script/docker
docker-compose up -d mysql redis

# 终端 2: 启动后端服务
cd backend/yudao-server
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

等待后端启动完成，看到 `Started YudaoServerApplication` 日志。

### 2. 启动前端

```bash
# 终端 3: 启动前端
cd frontend
pnpm dev:antd
```

### 3. 访问应用

浏览器访问: http://localhost:5666

- 用户名: admin
- 密码: admin123

### 4. 验证对接

登录成功后，检查:
- 浏览器开发者工具 -> Network -> 查看 API 请求是否正常
- 请求地址应为: `http://localhost:5666/admin-api/...` (自动代理到后端)
- 响应状态码应为 200
- 响应数据格式: `{ code: 0, data: {...}, msg: "success" }`

## API 对接规范

### 请求格式

```typescript
// GET 请求
export const getUserApi = (id: number) => {
  return requestClient.get<UserVO>(`/system/user/get?id=${id}`);
};

// POST 请求 (JSON)
export const createUserApi = (data: UserReqVO) => {
  return requestClient.post<number>('/system/user/create', data);
};

// PUT 请求
export const updateUserApi = (data: UserReqVO) => {
  return requestClient.put<void>('/system/user/update', data);
};

// DELETE 请求
export const deleteUserApi = (id: number) => {
  return requestClient.delete<void>(`/system/user/delete?id=${id}`);
};
```

### 响应格式

后端统一返回格式:
```json
{
  "code": 0,           // 0 表示成功，其他表示失败
  "data": {...},       // 响应数据
  "msg": "操作成功"    // 提示信息
}
```

前端会自动提取 `data` 字段，无需手动处理。

### 分页查询

```typescript
// 分页请求参数
interface PageParam {
  pageNo: number;     // 页码，从 1 开始
  pageSize: number;   // 每页条数
}

// 分页响应
interface PageResult<T> {
  list: T[];          // 数据列表
  total: number;      // 总记录数
}
```

## 部署

### Docker 部署

**后端**:
```bash
cd backend/script/docker
docker-compose up -d
```

**前端**:
```bash
cd frontend

# 构建 Docker 镜像
pnpm build:docker

# 或手动构建
pnpm build:antd
cd apps/web-antd/dist
# 部署到 Nginx
```

### 生产环境配置

**后端**: 修改 `backend/yudao-server/src/main/resources/application-prod.yaml`

**前端**: 修改 `frontend/apps/web-antd/.env.production`
```bash
# 修改后端 API 地址为实际生产地址
VITE_GLOB_API_URL=https://api.yourdomain.com/admin-api
```

---

# AI Agent 服务

## 技术栈

- **框架**: DeepAgents (基于 LangGraph)
- **LLM**: 支持 Anthropic Claude 和 DeepSeek
- **服务**: FastAPI (HTTP 服务)
- **Python**: >= 3.10
- **包管理**: uv

## 本地开发环境准备

### 1. 安装依赖

```bash
cd ai

# 使用 uv 创建虚拟环境
uv venv

# 激活虚拟环境
source .venv/bin/activate  # Linux/Mac
# 或 .venv\Scripts\activate  # Windows

# 安装依赖
uv pip install -r requirements.txt
```

### 2. 配置环境变量

```bash
# 复制配置文件模板
cp .env.example .env

# 编辑 .env 文件，配置以下内容：
# - MODEL_PROVIDER: 模型提供商 (anthropic 或 deepseek)
# - MODEL_NAME: 模型名称
# - ANTHROPIC_API_KEY: Anthropic API Key (使用 Claude 时)
# - DEEPSEEK_API_KEY: DeepSeek API Key (使用 DeepSeek 时)
# - DEEPSEEK_API_BASE: DeepSeek API Base URL (默认: https://api.siliconflow.cn/v1)
```

**当前配置 (DeepSeek-V3.1-Terminus via 硅基流动)**:
```bash
MODEL_PROVIDER=deepseek
MODEL_NAME=deepseek-ai/DeepSeek-V3.1-Terminus
DEEPSEEK_API_KEY=your-api-key-here
DEEPSEEK_API_BASE=https://api.siliconflow.cn/v1
```

### 3. 启动 AI Agent 服务

```bash
cd ai
source .venv/bin/activate
python http_qa_agent.py
```

服务将在 `http://localhost:8000` 启动。

### 4. 访问地址

- AI Agent 服务: http://localhost:8000
- 健康检查: http://localhost:8000/health
- API 文档: http://localhost:8000/docs

## AI Agent 架构

### 服务模式

项目包含两种 Agent 模式:

1. **命令行模式** (`simple_qa_agent.py`) - 交互式问答
2. **HTTP 服务模式** (`http_qa_agent.py`) - RESTful API 服务

### HTTP API 端点

```bash
# 健康检查
GET /health

# 对话接口 (非流式)
POST /chat
Content-Type: application/json
{
  "message": "用户消息",
  "stream": false,
  "virtual_customer_name": "客户名称",
  "virtual_customer_profile": "客户画像"
}

# 对话接口 (流式)
POST /chat
Content-Type: application/json
{
  "message": "用户消息",
  "stream": true,
  "virtual_customer_name": "客户名称",
  "virtual_customer_profile": "客户画像"
}
```

### 模型切换

AI Agent 支持两种模型提供商:

**Anthropic Claude**:
```bash
MODEL_PROVIDER=anthropic
MODEL_NAME=claude-sonnet-4-5-20250929
ANTHROPIC_API_KEY=your-api-key
```

**DeepSeek (通过硅基流动)**:
```bash
MODEL_PROVIDER=deepseek
MODEL_NAME=deepseek-ai/DeepSeek-V3.1-Terminus
DEEPSEEK_API_KEY=your-api-key
DEEPSEEK_API_BASE=https://api.siliconflow.cn/v1
```

### 动态提示词

AI Agent 支持基于剧本、案例、技巧、虚拟客户等数据动态生成系统提示词，实现智能陪练功能。

## 测试 AI Agent

```bash
# 使用测试客户端
cd ai
source .venv/bin/activate
python test_http_client.py

# 或使用 curl 测试
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "你好",
    "stream": false,
    "virtual_customer_name": "张先生"
  }'
```

---

## 项目特定规范

### 后端开发

- **模块隔离**:
  - 业务代码写在 `ynet-module-practice` 模块
  - `yudao-module-crm` 是框架自带模块，可读取但不要修改
  - 修改模块代码后必须执行 `mvn clean install -pl <module> -am` 才能生效

- **字典管理**:
  - 使用 `yudao-module-system` 的字典管理能力，不要硬编码枚举值
  - 字典名称必须以 `AICRM` 开头
  - 字典类型必须以 `aicrm_` 开头
  - 通过 SQL 脚本插入字典数据 (位于 `backend/sql/mysql/`)

- **数据库连接**:
  - 本地开发: `jdbc:mysql://127.0.0.1:3306/ruoyi-vue-pro`
  - 老版本 CRM: `jdbc:mysql://192.168.201.44:3306/dev_palmbank`

### 前端开发

- **组件参考**:
  - 列表页面: 参考 `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` (使用 VxeTable)
  - 详情表单: 参考 `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/basic-info.vue`

- **样式兼容**:
  - 确保所有样式在 light 和 dark 模式下都能正确显示

### OpenSpec 规范

- 使用中文编写 OpenSpec 文件
- 需求描述必须使用 SHALL 或 MUST 等强制性关键字
- 重要变更需创建 proposal 并审核通过后实施

---

## 文档和资源

- 官方文档: https://doc.iocoder.cn
- 启动文档: https://doc.iocoder.cn/quick-start/
- 视频教程: https://doc.iocoder.cn/video/
- 后端 API 文档: http://localhost:48080/doc.html (启动后访问)
- 前端 Vben 文档: https://doc.vben.pro
- DeepAgents 文档: https://docs.langchain.com/oss/python/deepagents/overview

## 获取帮助

遇到问题时:
1. 查看官方文档: https://doc.iocoder.cn
2. 查看启动文档: https://doc.iocoder.cn/quick-start/
3. 查看视频教程: https://doc.iocoder.cn/video/
4. 检查浏览器控制台和后端日志
5. 查看 AI Agent 服务日志 (位于 `/tmp/ai_agent_new.log`)