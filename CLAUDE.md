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

这是一个基于易诚开源框架的企业级**前后端分离**快速开发平台。

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

---

## 版本控制架构

本项目采用 **Monorepo + 多子仓库** 的双重架构：

### 主仓库 (GitHub)
- **仓库地址**: git@github.com:zhailiang23/ynet-ydp.git
- **分支**: feature/intelligent-practice
- **用途**: 统一管理所有代码，便于跨模块开发

### 子仓库 (git.ynet.io)
主仓库的子目录已通过 `git subtree` 推送到独立仓库：

| 子目录 | 仓库地址 |
|--------|---------|
| backend | git@git.ynet.io:belink/sysadmin-2025/backend.git |
| frontend | git@git.ynet.io:belink/sysadmin-2025/frontend.git |
| python | git@git.ynet.io:belink/sysadmin-2025/python.git |
| frontend-practice | git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git |
| chat-service-backend | git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-backend.git |
| chat-service-frontend-manager | git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-frontend-manager.git |
| chat-service-frontend-user | git@git.ynet.io:belink/ai-agent/ai-digital-avatar/chat-service-frontend-user.git |

### 同步工作流

```bash
# 1. 在主仓库修改代码
git add <files>
git commit -m "feat: 新功能"
git push origin feature/intelligent-practice

# 2. 同步到子仓库（可选）
git subtree push --prefix=<subdirectory> git@git.ynet.io:<repo-path> feature/intelligent-practice
```

**示例 - 同步 backend 目录**:
```bash
git subtree push --prefix=backend git@git.ynet.io:belink/sysadmin-2025/backend.git feature/intelligent-practice
```

### 重要说明
- ✅ 主仓库和子仓库**独立存在**，修改主仓库不会自动同步到子仓库
- ⚠️ **推荐在主仓库开发**，然后选择性同步到子仓库
- ⚠️ 避免直接在子仓库修改代码，否则需要手动同步回主仓库
- ℹ️ `git subtree push` 会保留完整的提交历史，但速度较慢

## 项目结构

```
ynet-ydp/
├── backend/                          # 后端项目 (Spring Boot)
│   ├── yudao-server/                # 主启动模块
│   ├── yudao-framework/             # 框架层 (18个子模块)
│   ├── yudao-module-*               # 业务模块
│   ├── sql/                         # 数据库脚本
│   └── script/                      # 部署脚本
├── frontend/                        # 前端项目 (Vue 3 Monorepo)
│   ├── apps/                        # 应用层
│   │   ├── web-antd/               # Ant Design Vue 版本 (主应用)
│   │   ├── web-ele/                # Element Plus 版本
│   │   └── web-naive/              # Naive UI 版本
│   ├── packages/                    # 共享包
│   └── internal/                    # 内部工具
├── python/                          # Python 服务
│   └── kb/                         # 知识库服务
│       └── backend/                # RAG 向量检索服务 (FastAPI + ChromaDB)
│           ├── app/                # 应用代码
│           ├── data/               # 向量数据库存储
│           └── requirements.txt    # Python 依赖
├── frontend-practice/               # 智能陪练前端 (Next.js)
│   ├── app/                        # Next.js App Router 页面
│   ├── components/                 # React 组件
│   ├── lib/                        # API 和工具库
│   └── public/                     # 静态资源
├── chat-service-backend/           # IM 即时通讯后端 (Go + WebSocket)
│   ├── api/                        # API 定义
│   ├── internal/                   # 内部实现 (controller/service/dao)
│   ├── manifest/                   # 配置和部署文件
│   └── database.sql                # 数据库初始化脚本
├── chat-service-frontend-manager/  # IM 客服管理端 (React + Ant Design Pro)
│   ├── src/                        # 源代码
│   │   ├── pages/                 # 页面 (登录/聊天/管理)
│   │   ├── services/              # API 服务层
│   │   └── components/            # 业务组件
│   └── config/                     # Umi 配置
└── chat-service-frontend-user/     # IM 用户端 (Taro + React)
    ├── src/                        # 源代码
    │   ├── pages/                 # 页面 (登录/聊天)
    │   ├── api/                   # API 接口
    │   └── store/                 # MobX 状态管理
    └── config/                     # 环境配置
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

# Python RAG 知识库服务

## 技术栈

- **框架**: FastAPI 0.115.12
- **向量数据库**: ChromaDB
- **嵌入模型**: bge-m3 (本地)
- **Python**: >= 3.10
- **包管理**: uv

## 本地开发环境准备

### 1. 安装依赖

```bash
cd python/kb/backend

# 使用 uv 创建虚拟环境
uv venv

# 激活虚拟环境
source .venv/bin/activate  # Linux/Mac

# 安装依赖
uv pip install -r requirements.txt
```

### 2. 启动 RAG 服务

```bash
cd python/kb/backend
source .venv/bin/activate
uvicorn app.main:app --host 0.0.0.0 --port 9080 --reload
```

服务将在 `http://localhost:9080` 启动（**注意端口 9080，避免与其他服务冲突**）。

### 3. 访问地址

- RAG 服务: http://localhost:9080
- 健康检查: http://localhost:9080/health
- API 文档: http://localhost:9080/docs

## RAG API 端点

### 健康检查

```bash
GET /health

响应:
{
  "status": "healthy",
  "embedding_model": "BAAI/bge-m3",
  "vector_db": "ChromaDB"
}
```

### 文件上传和向量化

```bash
POST /api/upload
Content-Type: multipart/form-data

参数:
- file: 文件 (支持 PDF、TXT、MD、DOCX)
- kb_id: 知识库 ID (整数)

响应:
{
  "file_id": 123,
  "filename": "document.pdf",
  "kb_id": 1,
  "status": "success"
}
```

### 文本检索

```bash
POST /api/retrieve
Content-Type: application/json

{
  "question": "用户问题",
  "kb_ids": [1, 2, 3],  // 知识库 ID 列表
  "top_k": 5             // 返回最相关的前 N 个文本块
}

响应:
{
  "results": [
    {
      "text": "相关文本内容",
      "score": 0.85,
      "metadata": {
        "file_id": 123,
        "kb_id": 1,
        "chunk_index": 0
      }
    }
  ]
}
```

### 删除文件

```bash
DELETE /api/delete/{file_id}

响应:
{
  "status": "success",
  "message": "File deleted successfully"
}
```

## 与 IM 客服系统集成

RAG 服务已集成到 IM 客服系统（`chat-service-backend`）：

### 集成流程

```
用户发送消息
    ↓
chat-service-backend (Go)
    ↓
查询客服的知识库 ID (system_users.knowledge_base_id)
    ↓
调用 Python RAG 服务 (HTTP POST http://localhost:9080/api/retrieve)
    ↓
RAG 返回相关文本块
    ↓
组装提示词 (系统提示词 + 知识库内容 + 用户消息)
    ↓
调用 AI 生成回复
    ↓
返回给用户
```

### 配置说明

1. **数据库配置** - 在 `system_users` 表中设置 `knowledge_base_id` 字段：
```sql
UPDATE system_users
SET knowledge_base_id = 3
WHERE username = 'your_admin_username';
```

2. **Go 后端配置** - `chat-service-backend` 会自动调用 RAG 服务：
```go
// internal/client/rag_client.go
const ragURL = "http://localhost:9080/api/retrieve"
```

3. **提示词模板** - 确保提示词包含 `{{knowledge}}` 占位符：
```
你是个专业热情的银行客服，请基于以下知识库内容和历史对话信息回答客户问题。

【知识库参考资料】
{{knowledge}}

【历史对话】
{{history}}

【客户问题】
{{message}}

请根据知识库内容回答问题，如果知识库中没有相关信息，则根据你的专业知识回答。
```

## 重要提醒

- ⚠️ RAG 服务使用 **9080 端口**，确保不与其他服务冲突
- ⚠️ 首次启动会下载 bge-m3 嵌入模型（约 2GB），需要良好的网络连接
- ⚠️ 向量数据存储在 `python/kb/backend/data/chroma` 目录
- ✅ 支持自动重新加载（`--reload`），开发时修改代码无需重启服务
- ✅ Go 后端通过 HTTP 调用 RAG 服务，已禁用代理确保本地连接

## 测试 RAG 服务

```bash
# 1. 健康检查
curl http://localhost:9080/health

# 2. 测试检索
curl -X POST http://localhost:9080/api/retrieve \
  -H "Content-Type: application/json" \
  -d '{
    "question": "如何使用知识库？",
    "kb_ids": [3],
    "top_k": 5
  }'
```

---

# 智能陪练前端 (frontend-practice)

## 技术栈

- **框架**: Next.js 15.2.4 (React 19, TypeScript 5)
- **UI 库**: Radix UI + Shadcn/ui
- **样式**: Tailwind CSS 3.4.17
- **表单**: React Hook Form + Zod
- **图表**: Recharts
- **主题**: next-themes (支持暗色模式)
- **包管理**: npm

## 本地开发环境准备

### 1. 环境要求

- Node.js >= 20.10.0
- npm (使用 npm，非 pnpm)

### 2. 安装依赖

```bash
cd frontend-practice
npm install
```

### 3. 环境配置

创建 `.env.local` 文件:
```bash
NEXT_PUBLIC_API_BASE_URL=http://localhost:48080/admin-api
NEXT_PUBLIC_TENANT_ID=1
```

### 4. 启动开发服务器

```bash
npm run dev
```

访问地址: http://localhost:5666

**认证说明**:
- 自动登录: 如果 token 过期或不存在，自动使用 `admin/admin123` 登录
- 便于演示和测试，无需手动登录

## 主要功能

### 核心页面

| 路由 | 功能 | 说明 |
|------|------|------|
| `/` | 首页 | 课程轮播、个性化课程创建、场景选择 |
| `/practice` | 陪练会话 | 实时 AI 对话模拟、评估反馈 |
| `/course` | 课程列表 | 标准课程 + 个性化课程展示 |
| `/course/[id]` | 课程详情 | 课程信息、虚拟客户选择 |
| `/training-results` | 成绩列表 | 练习评分、改进建议、数据分析 |
| `/training-results/[id]` | 成绩详情 | 详细评估结果展示 |
| `/training-files` | 培训资料 | 资料库管理 |
| `/create-personalized-course` | 创建课程 | 创建个性化陪练课程 |

### 核心特性

- **AI 驱动对话**: 集成 Dify API 生成虚拟客户回复
- **合规评估**: 检测销售话术中的合规问题
- **个性化学习路径**: 支持创建针对特定客户的陪练课程
- **文件上传**: 支持上传培训资料，AI 分析内容生成脚本
- **实时反馈**: 练习结束后立即生成评估 (沟通力、专业性、合规性)
- **暗色模式**: 完整的 Dark Mode 支持
- **响应式设计**: 移动端友好的 UI

## 项目结构

```
frontend-practice/
├── app/                    # Next.js App Router 页面
│   ├── page.tsx           # 首页
│   ├── practice/          # 陪练会话页
│   ├── course/            # 课程相关页面
│   ├── training-results/  # 成绩页面
│   ├── training-files/    # 资料库
│   └── layout.tsx         # 全局布局
├── components/            # React 组件
│   ├── ui/               # UI 组件库 (80+ Radix UI 包装)
│   └── *.tsx             # 业务组件 (27个)
├── lib/                   # 库和工具
│   ├── api/              # API 层 (13个 API 文件)
│   ├── types/            # TypeScript 接口
│   └── utils/            # 工具函数
└── public/               # 静态资源
```

## API 对接

### 关键 API 端点

```typescript
POST   /admin-api/system/auth/login                              // 登录
GET    /admin-api/aicrm/practice-course/page                     // 课程列表
POST   /admin-api/aicrm/practice-course/create-personalized     // 创建个性化课程
POST   /admin-api/aicrm/practice-user-record/create             // 创建练习记录
POST   /admin-api/aicrm/practice-conversation/create            // 记录对话
GET    /admin-api/aicrm/practice-user-record/evaluate?recordId= // 评估练习
```

### 认证机制

- **自动登录**: Token 过期时自动使用 `admin/admin123` 登录
- **Token 存储**: localStorage 中的 `access_token`
- **请求头**:
  - `Authorization: Bearer {token}`
  - `tenant-id: {TENANT_ID}`

## 与 frontend 目录的区别

| 维度 | frontend-practice | frontend |
|------|-------------------|----------|
| **框架** | Next.js 15 (SSR/SSG) | Vue 3 + Vite (SPA) |
| **UI 库** | Radix UI + Shadcn | Ant Design Vue / Element Plus |
| **架构** | 单应用 (App Router) | Monorepo (多应用) |
| **包管理器** | npm | pnpm + Turbo |
| **用途** | **仅限学员陪练功能** | **完整 ERP 系统** |
| **登录方式** | 自动登录 | 手动登录 |

## GitLab CI/CD 部署实践经验

frontend-practice 项目已配置 GitLab CI/CD 自动化部署，以下是关键经验总结：

### 部署架构

- **GitLab 仓库**: git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git
- **GitLab Runner**: 部署在生产服务器 192.168.153.111 上
- **Harbor 镜像仓库**: 192.168.152.56 (HTTP, 非 HTTPS)
- **生产环境**: http://192.168.153.111:3000

### Pipeline 阶段

```yaml
stages:
  - build    # 构建 Docker 镜像并推送到 Harbor
  - deploy   # SSH 到生产服务器执行部署脚本
  - cleanup  # 清理旧镜像（保留最近 5 个版本）
```

### 关键配置要点

#### 1. 使用国内镜像源

**问题**: Alpine Linux 默认镜像源在国内访问慢或无法访问
**解决**: 在 `before_script` 中替换为阿里云镜像

```yaml
before_script:
  - sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories
  - apk add --no-cache openssh-client bash curl
```

#### 2. SSH 私钥直接嵌入脚本

**问题**: 使用 GitLab CI/CD 变量 API 配置 SSH 私钥时，JSON 转义和换行符处理复杂
**解决**: 将 SSH 私钥直接写入 `.gitlab-ci.yml` 的 `before_script` 中

```yaml
before_script:
  - mkdir -p ~/.ssh
  - chmod 700 ~/.ssh
  - |
    cat > ~/.ssh/id_rsa << 'EOFKEY'
    -----BEGIN RSA PRIVATE KEY-----
    [完整的私钥内容]
    -----END RSA PRIVATE KEY-----
    EOFKEY
  - chmod 600 ~/.ssh/id_rsa
  - ssh-keyscan -H $DEPLOY_SERVER >> ~/.ssh/known_hosts
```

**注意事项**:
- 使用 `EOFKEY` 作为 heredoc 分隔符，避免与私钥内容冲突
- 私钥必须包含 `-----BEGIN RSA PRIVATE KEY-----` 和 `-----END RSA PRIVATE KEY-----`
- 设置正确的文件权限（600）

#### 3. 在部署脚本中创建必需的目录

**问题**: 目标服务器可能缺少必需的目录结构
**解决**: 在 `deploy.sh` 脚本中主动创建所有必需目录

```bash
# 部署脚本中的目录检查和创建
log_info "步骤 1: 创建必要的目录"
mkdir -p "$DEPLOY_DIR"
mkdir -p "$CONFIG_DIR"
mkdir -p "$LOG_DIR"

# 如果环境配置文件不存在，创建默认配置
if [ ! -f "$ENV_FILE" ]; then
    log_warning "环境变量文件不存在: $ENV_FILE"
    log_warning "将创建默认环境变量文件，请根据实际情况修改"
    cat > "$ENV_FILE" <<EOF
NODE_ENV=production
PORT=3000
NEXT_PUBLIC_API_BASE_URL=http://localhost:48080/admin-api
NEXT_PUBLIC_TENANT_ID=1
TZ=Asia/Shanghai
EOF
fi
```

**必需的目录**:
- `/root/zhailiang/frontend-practice/` - 部署脚本和应用目录
- `/root/zhailiang/configs/` - 环境配置文件目录
- `/root/zhailiang/logs/` - 日志文件目录

#### 4. Next.js Standalone 模式静态文件处理

**问题**: Next.js `output: 'standalone'` 模式不会自动复制 `public` 和 `.next/static` 目录
**现象**: 所有静态资源（CSS、JS chunks）返回 404 错误

**解决**: 在 Dockerfile 中手动复制这些文件到 standalone 目录

```dockerfile
# 构建应用 (standalone 模式)
RUN pnpm run build

# 复制 Next.js standalone 模式所需的静态文件
# standalone 模式不会自动包含 public 和 .next/static，需要手动复制
RUN cp -r public .next/standalone/public && \
    cp -r .next/static .next/standalone/.next/static

# 设置为生产环境
ENV NODE_ENV=production
```

**关键点**:
- 必须在构建完成后立即复制
- 目标路径是 `.next/standalone/public` 和 `.next/standalone/.next/static`
- 这是 Next.js standalone 模式的已知行为，不是 bug

### 5. Git Subtree 推送到子仓库

**问题**: frontend-practice 是主仓库的子目录，需要单独推送到 GitLab
**解决**: 使用 `git subtree split` 和 force push

```bash
# 方法一：使用 git subtree push（可能遇到 non-fast-forward）
git subtree push --prefix=frontend-practice \
  git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git master

# 方法二：使用 git subtree split + force push（推荐）
git push -f git@git.ynet.io:belink/ai-agent/ai-coach/frontend-practice.git \
  $(git subtree split --prefix=frontend-practice master):master
```

### 常见问题排查

#### Pipeline 失败 - Alpine 包安装失败
```
ERROR: unable to select packages:
  bash (no such package)
  openssh-client (no such package)
```
**解决**: 添加阿里云镜像源替换命令（见上文）

#### SSH 认证失败
```
Load key "/root/.ssh/id_rsa": error in libcrypto
Permission denied, please try again.
```
**解决**:
1. 检查私钥格式是否完整
2. 确认文件权限为 600
3. 确认公钥已添加到目标服务器 `~/.ssh/authorized_keys`

#### 部署失败 - 目录不存在
```
scp: dest open "/root/zhailiang/frontend-practice/": Failure
deploy.sh:行191: /root/zhailiang/configs/frontend-practice.env: 没有那个文件或目录
```
**解决**:
1. 在目标服务器手动创建目录，或
2. 在部署脚本开头添加目录创建逻辑（推荐）

#### 静态资源 404 错误
```
Failed to load resource: the server responded with a status of 404 (Not Found)
/_next/static/css/*.css
/_next/static/chunks/*.js
```
**解决**: 在 Dockerfile 中添加静态文件复制步骤（见上文）

### 部署验证

Pipeline 成功后，验证部署是否正常：

```bash
# 1. 检查容器状态
ssh root@192.168.153.111 'docker ps | grep frontend-practice'

# 2. 检查应用响应
curl -I http://192.168.153.111:3000/

# 3. 检查容器日志
ssh root@192.168.153.111 'docker logs frontend-practice --tail 50'

# 4. 使用浏览器访问并检查 Chrome DevTools Console
# 确保没有 404 错误，所有静态资源正常加载
```

### 参考文档

- GitLab CI/CD 配置: `frontend-practice/.gitlab-ci.yml`
- 部署脚本: `frontend-practice/deploy.sh`
- Dockerfile: `frontend-practice/Dockerfile`
- GitLab Runner 安装: `frontend-practice/RUNNER_SETUP.md`
- 详细 CI/CD 指南: `frontend-practice/GITLAB_CI_CD_GUIDE.md`

---

# IM 即时通讯后端 (chat-service-backend)

## 技术栈

- **语言**: Go 1.23
- **框架**: GoFrame v2.8.3
- **实时通信**: WebSocket (gorilla/websocket)
- **数据库**: MySQL 8.0+
- **缓存**: Redis 6.0+
- **RPC**: gRPC + Protocol Buffers
- **服务发现**: etcd
- **AI**: LangChain Go (支持 Ollama)

## 本地开发环境准备

### 1. 依赖服务

- MySQL 8.0+
  - 数据库名: `chat`
  - 需要运行 `database.sql` 初始化
- Redis 6.0+
  - 默认数据库: 6 号库
- etcd (仅集群部署时需要)
- Ollama (可选，用于本地 AI)

### 2. 配置文件

```bash
# 复制配置文件
cp manifest/config/config.example.yaml manifest/config/config.yaml

# 编辑配置文件，主要配置项:
# - storage.default: 文件存储方式 (local 或 qiniu)
# - app.jwtSecret: JWT 密钥
# - database.link: MySQL 连接串
# - redis.address: Redis 地址
# - grpc.open: 是否启用 gRPC
# - langchain.open: 是否启用 AI 回复
```

**关键配置示例**:
```yaml
storage:
  default: 'local'              # 文件存储: local 或 qiniu

app:
  jwtSecret: ""                 # JWT 密钥
  host: "http://127.0.0.1:8080"

server:
  address: ":8080"              # HTTP 监听地址

database:
  default:
    link: "root:123456@tcp(127.0.0.1:3306)/chat"

redis:
  default:
    address: 127.0.0.1:6379
    db: 6

grpc:
  open: true                    # 启用 gRPC

langchain:
  open: true                    # 启用 AI 回复
  model: "qwen:0.5b"           # Ollama 模型
```

### 3. 启动步骤

```bash
# 1. 数据库迁移
go run main.go migrate

# 2. 初始化测试数据
go run main.go fake

# 3. 启动服务
go run main.go http
```

**启动命令说明**:
- `migrate`: 执行 database.sql，创建所有表结构
- `fake`: 生成测试数据 (用户、客服、消息等)
- `init <customerId>`: 初始化特定客户的基本数据
- `http`: 启动 HTTP 和 gRPC 服务

### 4. 访问地址

- HTTP API: http://localhost:8080
- 健康检查: http://localhost:8080/ (返回 "hello word")

## 主要功能

### 核心模块

1. **用户管理**
   - 用户认证 (JWT Token)
   - 用户在线状态管理
   - 用户端 WebSocket 接入

2. **客服功能**
   - 客服登录和会话管理
   - 客服端 WebSocket 接入
   - 转接人工 (支持排队)
   - 客服设置 (欢迎词、离线词)

3. **消息处理**
   - 文字、表情、图片、音频、视频、PDF
   - 消息已读/未读状态
   - 消息存储和查询

4. **自动化**
   - 自动回复消息库
   - 自动规则配置 (关键词匹配)
   - 规则场景管理

5. **AI 功能**
   - LangChain Go 集成
   - 支持 Ollama 本地模型
   - 智能回复生成

6. **文件管理**
   - 本地存储
   - 七牛云 OSS 支持

## 项目结构

```
chat-service-backend/
├── api/                  # API 定义
│   ├── backend/v1/      # 客服系统 API
│   ├── frontend/v1/     # 用户系统 API
│   ├── chat/v1/         # gRPC API
│   └── common.go        # 通用响应结构
├── internal/
│   ├── cmd/            # 命令入口
│   ├── controller/     # HTTP 控制器
│   ├── service/        # 服务层接口
│   ├── logic/          # 业务逻辑实现
│   ├── dao/            # 数据访问层
│   ├── model/          # 数据模型
│   ├── cache/          # 缓存实现
│   ├── grpc/           # gRPC 服务
│   └── util/           # 工具函数
├── manifest/
│   ├── config/         # 配置文件
│   ├── deploy/         # K8s 部署配置
│   └── protobuf/       # Protocol Buffer 定义
├── database.sql         # 数据库初始化脚本
└── main.go             # 程序入口
```

## API 设计

### 响应格式

```go
type NormalRes[T any] struct {
    Code    int  `json:"code"`     // 0=成功，其他=失败
    Data    T    `json:"data"`     // 响应数据
    Success bool `json:"success"`  // 成功标志
}

type ListRes[T any] struct {
    NormalRes[[]T]
    Total int `json:"total"`       // 总数
}
```

### API 路由

```
/api/
├── /user                    # 用户接口
│   ├── /login              # 登录
│   └── /chat (需认证)      # 聊天相关
│       ├── /ws            # WebSocket 连接
│       └── /chat          # 聊天操作
└── /backend                # 客服接口
    ├── /login             # 客服登录
    └── /... (需认证)      # 其他管理接口
```

### 认证机制

- **JWT Token**: 使用 `Authorization: Bearer {token}` 头
- **中间件**:
  - `middleware.UserAuth` - 用户认证
  - `middleware.AdminAuth` - 客服认证
  - `middleware.Cors` - CORS 支持

## 消息处理流程

```
用户发送消息
    ↓
已人工接入? ─[是]→ 转发给对应客服
    ↓[否]
触发自定义规则 → 规则匹配处理
    ↓
是否等待人工接入? ─[是]→ 加入等待列表
    ↓[否]
自动转人工? ─[是]→ 加入等待列表
    ↓[否]
开启AI回复? ─[是]→ AI 根据消息回复
    ↓[否]
流程结束
```

## 集群部署

### gRPC 服务

- 多个 chat-service-backend 实例通过 etcd 服务注册和发现
- 实例间通过 gRPC 进行消息转发
- WebSocket 连接在本地实例处理，但消息可跨实例传递

### Docker 部署

```bash
# 构建镜像
make image

# 推送镜像
make image.push

# 部署到 K8s
make deploy TAG=develop

# 端口转发
make port
```

## 与 yudao 后端的关系

- **独立微服务**: chat-service-backend 是完全独立的微服务
- **数据库独立**: 使用单独的 MySQL 数据库 (`chat`)
- **集成方式**: 通过 HTTP API 或 gRPC 进行通信
- **可选集成**: 可作为独立的客服系统运行

---

# IM 客服管理端 (chat-service-frontend-manager)

## 技术栈

- **UI 框架**: Ant Design Pro v6 (React 18)
- **框架引擎**: Umi Max v4.4.2
- **语言**: TypeScript 5.6.3
- **样式**: Tailwind CSS 3.4.17 + Less + Antd-style
- **状态管理**: Valtio (proxy-based)
- **国际化**: 内置 i18n (支持 8 种语言)
- **HTTP**: Axios + Ahooks (useRequest)
- **包管理**: npm

## 本地开发环境准备

### 1. 环境要求

- Node.js >= 20.10.0
- npm

### 2. 安装依赖

```bash
cd chat-service-frontend-manager
npm install
```

### 3. 环境配置

后端 API 地址配置在 `config/config.ts`:
```typescript
BASE_URL: 'http://127.0.0.1:8080/api/backend'
WS_URL: 'http://127.0.0.1:8080/api/backend/ws'
```

### 4. 启动开发服务器

```bash
npm start              # 启动开发服务器
npm run start:dev      # 开发环境 (禁用 Mock)
npm run start:no-mock  # 禁用所有 Mock
```

访问地址: http://localhost:8000

**演示账号**:
- 账号: admin0 - admin19
- 密码: admin0 - admin19
- 演示地址: http://120.77.242.145/server

## 主要功能

### 核心页面

| 路由 | 功能 | 说明 |
|------|------|------|
| `/login` | 登录页 | 客服认证入口 |
| `/dashboard` | 仪表板 | 首页统计面板 |
| `/chat` | 客服面板 | **核心实时聊天界面** (WebSocket) |
| `/admin` | 客服管理 | 管理客服账户 |
| `/auto/message` | 快捷回复 | 快捷回复消息库 |
| `/auto/rule` | 自定义规则 | 自动化规则配置 |
| `/auto/system-rule` | 系统规则 | 系统级自动回复规则 |
| `/setting` | 系统设置 | 全局配置 |
| `/transfer` | 转接记录 | 客服转接历史 |
| `/session` | 会话记录 | 聊天会话详情 |
| `/chat-files` | 聊天文件 | 文件管理和下载 |

### 聊天面板核心组件

- **Users**: 客户列表面板
- **Messages**: 消息列表展示
- **InputArea**: 消息输入框
- **Header**: 聊天标题栏
- **LeftMenu**: 左侧快捷菜单
- **CurrentUser**: 当前聊天用户信息
- **可拖拽窗口**: 1080px × 700px

### 状态管理 (Valtio)

```
store/
├── websocket.ts      # WebSocket 连接和消息处理
├── users.ts          # 客户列表状态
├── currentUser.ts    # 当前聊天用户
├── admin.ts          # 在线客服列表
├── adminSetting.ts   # 客服个人设置
├── transfer.ts       # 转接请求状态
├── waitingUsers.ts   # 等待客服的客户列表
└── historySession.ts # 历史会话记录
```

## API 对接

### 主要 API 端点

**认证**:
- `POST /login` - 客服登录
- `GET /current-admin/info` - 获取客服信息
- `GET /current-admin/settings` - 获取客服设置

**实时聊天 (WebSocket)**:
- `POST /ws/chat-user` - 接受客户
- `GET /ws/chat-users` - 获取客户列表
- `DELETE /ws/chat-user/{uid}` - 移除客户
- `POST /ws/read` - 标记已读
- `GET /ws/messages?uid={uid}` - 获取消息

**转接功能**:
- `POST /ws/transfer` - 创建转接
- `POST /ws/transfer/{id}/cancel` - 取消转接
- `GET /transfers` - 转接记录

**会话管理**:
- `GET /chat-sessions` - 会话列表
- `GET /chat-sessions/{id}` - 会话详情
- `POST /chat-sessions/{id}/cancel` - 关闭会话

**文件管理**:
- `GET /chat-files` - 文件列表
- `DELETE /chat-files/{id}` - 删除文件

### WebSocket 消息协议

**消息类型**:
```typescript
type ActionType =
  | 'send-message'         // 发送消息
  | 'user-offline'         // 用户离线
  | 'user-online'          // 用户上线
  | 'receipt'              // 消息回执
  | 'waiting-users'        // 等待列表更新
  | 'admins'               // 客服列表更新
  | 'receive-message'      // 接收新消息
  | 'other-login'          // 异地登录
  | 'user-transfer'        // 用户转接
  | 'read'                 // 消息已读
```

**消息格式**:
```typescript
type Message = {
  type: 'text' | 'navigator' | 'image' | 'audio' | 'video' | 'pdf';
  user_id: number;
  admin_id?: number;
  content: string;
  source: 0 | 1 | 2;      // 0=用户 1=客服 2=系统
  req_id: string;          // 请求唯一 ID
  received_at: string;
  avatar: string;
  is_read: boolean;
}
```

## 管理端特有功能

1. **客服账户管理** - 新增/编辑客服
2. **快捷回复管理** - 创建多种类型的快捷回复
3. **自定义规则** - 配置自动回复规则
4. **会话管理** - 查看所有会话和详情
5. **转接管理** - 处理客服转接请求
6. **文件管理** - 管理聊天文件
7. **系统设置** - 客服头像、背景等配置
8. **实时聊天** - 可拖拽窗口、自动接客
9. **多语言支持** - 8 种语言切换

## 构建和部署

```bash
# 生产构建
npm run build

# 预览构建
npm run preview

# 分析构建大小
npm run build:analyze

# 代码检查
npm run lint
npm run prettier
```

---

# IM 用户端 (chat-service-frontend-user)

## 技术栈

- **框架**: Taro 4.0.7 (跨平台)
- **UI**: React 18.3.1
- **语言**: TypeScript 5.6.3
- **样式**: Tailwind CSS 3.4.15
- **状态管理**: MobX 6.13.5
- **构建**: Webpack 5.96.1
- **小程序**: weapp-tailwindcss 3.7.0
- **包管理**: npm

## 本地开发环境准备

### 1. 环境要求

- Node.js >= 20.10.0
- npm

### 2. 安装依赖

```bash
cd chat-service-frontend-user
npm install
```

### 3. 环境配置

**开发环境** (`config/dev.ts`):
```typescript
BASE_URL: "http://localhost:8080/api/user"
WS_URL: "ws://localhost:8080/api/user/chat/ws"
```

**生产环境** (`config/prod.ts`):
```typescript
BASE_URL: "http://120.77.242.145:8080/api/user"
WS_URL: "ws://120.77.242.145:8080/api/user/chat/ws"
```

### 4. 启动开发服务器

```bash
# 微信小程序
npm run dev:weapp

# H5
npm run dev:h5

# 支付宝小程序
npm run dev:alipay

# 字节小程序
npm run dev:tt
```

**演示地址**: http://120.77.242.145/mobile

**演示账号**:
- 账号: user1 - user20
- 密码: user1 - user20

## 主要功能

### 核心页面

| 路由 | 功能 | 说明 |
|------|------|------|
| `/pages/login/index` | 登录页 | 用户认证 |
| `/pages/index/index` | 聊天页 | **核心实时聊天界面** |

### 聊天页面组件

- **MessageContainer** - 消息容器 (无限滚动)
- **MessageItem** - 消息项 (支持多种类型)
  - Text - 文本消息
  - Image - 图片消息
  - Video - 视频消息
  - Audio - 音频消息
  - PDF - PDF 文件
  - Navigator - 链接卡片
  - Notice - 系统通知
- **Input** - 消息输入框
- **UserAvatar** - 用户头像

## API 对接

### 主要 API 端点

| 接口 | 方法 | 功能 |
|------|------|------|
| `/login` | POST | 用户登录 |
| `/chat/req-id` | GET | 获取消息 ID |
| `/chat/messages` | GET | 获取历史消息 |
| `/chat/setting` | GET | 获取聊天设置 |
| `/chat/read` | POST | 标记已读 |
| `/chat/messages/:id/rate` | POST | 消息评分 |
| `/chat/files` | POST | 上传文件 |
| `/chat/ws` | WebSocket | WebSocket 连接 |

### WebSocket 消息格式

**发送消息**:
```typescript
{
  action: "send-message",
  data: {
    content: string,
    type: "text" | "image" | "video" | "audio" | "pdf" | "navigator",
    req_id: string,
    source: 0,  // 用户端
    admin_id: 0
  },
  time: number
}
```

**接收消息**:
- `receive-message` - 接收新消息
- `receipt` - 消息投递确认
- `read` - 消息已读通知
- `waiting-user-count` - 等待队列人数

## 用户端特有功能

| 功能 | 说明 |
|------|------|
| **跨平台支持** | 支持微信、支付宝、字节小程序和 H5 |
| **实时通信** | WebSocket 实时消息推送 |
| **消息类型丰富** | 文本、图片、视频、音频、PDF、链接 |
| **历史消息** | 无限滚动加载历史消息 |
| **等待队列** | 显示客服等待队列长度 |
| **已读状态** | 显示消息已读/未读 |
| **文件上传** | 图片选择和上传 |
| **自适应布局** | 针对不同设备优化 |
| **离线重连** | 断线重连提示 |
| **响应式设计** | Tailwind CSS 实现 |

## 构建和部署

```bash
# 构建微信小程序
npm run build:weapp

# 构建 H5
npm run build:h5

# 构建支付宝小程序
npm run build:alipay
```

## 完整客服系统架构

三个目录组成完整的客服系统:

```
客服系统 = chat-service-backend (后端)
        + chat-service-frontend-manager (客服端)
        + chat-service-frontend-user (用户端)
```

**数据流**:
```
用户端 (Taro)
    ↕ WebSocket
chat-service-backend (Go)
    ↕ WebSocket
客服端 (React)
```

**特点**:
- 独立部署,不依赖 yudao 后端
- 使用独立的 MySQL 数据库 (`chat`)
- 支持集群部署 (gRPC + etcd)
- 完整的 WebSocket 实时通信
- 跨平台支持 (小程序 + H5 + Web)

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

### 官方文档
- 易诚官方文档: https://doc.iocoder.cn
- 启动文档: https://doc.iocoder.cn/quick-start/
- 视频教程: https://doc.iocoder.cn/video/
- 后端 API 文档: http://localhost:48080/doc.html (启动后访问)

### 前端框架文档
- Vben 文档: https://doc.vben.pro
- Next.js 文档: https://nextjs.org/docs
- Radix UI 文档: https://www.radix-ui.com/primitives/docs
- Shadcn/ui 文档: https://ui.shadcn.com

### AI & IM 服务文档
- DeepAgents 文档: https://docs.langchain.com/oss/python/deepagents/overview
- GoFrame 文档: https://goframe.org/
- LangChain Go 文档: https://github.com/tmc/langchaingo
- Ant Design Pro 文档: https://pro.ant.design/
- Umi Max 文档: https://umijs.org/
- Taro 文档: https://taro-docs.jd.com/
- MobX 文档: https://mobx.js.org/
- Valtio 文档: https://valtio.pmnd.rs/

## 获取帮助

遇到问题时:
1. 查看官方文档: https://doc.iocoder.cn
2. 查看启动文档: https://doc.iocoder.cn/quick-start/
3. 查看视频教程: https://doc.iocoder.cn/video/
4. 检查浏览器控制台和后端日志
5. 查看各服务的日志文件:
   - **RAG 知识库服务**: `/tmp/python_rag_final.log` 或控制台输出
   - **chat-service-backend**: `./storage/sql/` (WebSocket 日志), `./storage/grpc/` (gRPC 日志)
   - **Spring Boot 后端**: `./logs/` 或控制台输出

## 重要提醒

- 项目是多模块项目,修改后端代码后需要执行 `mvn clean install -pl <module> -am` 才会生效
- frontend-practice 是独立的智能陪练应用,连接到 yudao 后端 API (48080 端口)
- 客服系统由 3 个独立服务组成:
  - `chat-service-backend` (Go 后端, 8080 端口)
  - `chat-service-frontend-manager` (客服端, React)
  - `chat-service-frontend-user` (用户端, Taro)
- chat-service-backend 使用独立的 MySQL 数据库 (`chat`),需要单独初始化 (`database.sql`)
- 客服系统完全独立部署,不依赖 yudao 后端

## 服务端口总览

| 服务 | 端口 | 协议 | 说明 |
|------|------|------|------|
| **主后端服务** | 48080 | HTTP | Spring Boot（yudao-server） |
| **主前端服务** | 5666 | HTTP | Vue 3 + Vite（web-antd） |
| **MySQL** | 3306 | TCP | 数据库 |
| **Redis** | 6379 | TCP | 缓存 |
| **RAG 知识库服务** | 9080 | HTTP | Python FastAPI（python/kb/backend） |
| **IM 客服后端** | 8080 | HTTP/WebSocket | Go（chat-service-backend） |
| **IM 客服管理端** | 8000 | HTTP | React（chat-service-frontend-manager） |
| **IM 用户端 H5** | 10086 | HTTP | Taro H5（chat-service-frontend-user） |
| **陪练前端** | 3000 或 5666 | HTTP | Next.js（frontend-practice） |

### 端口冲突处理

如果端口被占用，按以下方式修改：

**主后端（48080）**:
```yaml
# backend/yudao-server/src/main/resources/application-local.yaml
server:
  port: 48081  # 修改为其他端口
```

**主前端（5666）**:
```bash
# frontend/apps/web-antd/.env.development
VITE_PORT=5667  # 修改为其他端口
```

**RAG 服务（9080）**:
```bash
# 启动时指定端口
cd python/kb/backend
uvicorn app.main:app --host 0.0.0.0 --port 9081
```

**IM 客服后端（8080）**:
```yaml
# chat-service-backend/manifest/config/config.yaml
server:
  address: ":8081"  # 修改为其他端口
```

---

## 完整系统启动顺序

### 主应用 (ERP 系统)
```bash
# 1. 启动 MySQL + Redis
cd backend/script/docker && docker-compose up -d mysql redis

# 2. 启动 yudao 后端 (端口 48080)
cd backend/yudao-server && mvn spring-boot:run -Dspring-boot.run.profiles=local

# 3. 启动管理前端 (端口 5666)
cd frontend && pnpm dev:antd
```

### 智能陪练系统
```bash
# 1. 启动 yudao 后端 (同上, 端口 48080)

# 2. 启动陪练前端 (端口 3000 或 5666)
cd frontend-practice && npm run dev
```

### 客服系统（集成 RAG 知识库）
```bash
# 1. 初始化 chat 数据库
cd chat-service-backend
go run main.go migrate
go run main.go fake

# 2. 启动 RAG 知识库服务 (端口 9080)
cd python/kb/backend
source .venv/bin/activate
uvicorn app.main:app --host 0.0.0.0 --port 9080 --reload

# 3. 启动 chat-service-backend (端口 8080)
cd chat-service-backend
go run main.go http

# 4. 启动客服管理端 (端口 8000)
cd chat-service-frontend-manager && npm start

# 5. 启动用户端 (小程序或 H5, 端口 10086)
cd chat-service-frontend-user && npm run dev:h5
```

### 验证服务状态

```bash
# 检查主后端
curl http://localhost:48080/doc.html

# 检查 RAG 服务
curl http://localhost:9080/health

# 检查 IM 后端
curl http://localhost:8080/

# 检查主前端
open http://localhost:5666

# 检查客服管理端
open http://localhost:8000

# 检查用户端 H5
open http://localhost:10086
```
- 我的 harbor 版本是 v2.4.2-ef2e2e56
- 目前有四套环境 dev(本机), fat, uat, pro
- 192.168.153.111 服务器的用户名是 root, 密码是 Ynet@2024