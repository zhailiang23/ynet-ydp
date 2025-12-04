# chat-service-frontend-user 多环境配置指南

## 环境配置

项目支持 4 个环境的独立配置：

- `dev` - 开发环境（默认）
- `fat` - 功能验收测试环境
- `uat` - 用户验收测试环境
- `pro` - 生产环境

## 配置文件说明

每个环境对应一个配置文件：

```
config/
├── dev.ts    # 开发环境配置
├── fat.ts    # FAT 环境配置
├── uat.ts    # UAT 环境配置
├── pro.ts    # 生产环境配置
└── index.ts  # 配置入口文件
```

### 配置文件示例

```typescript
import type { UserConfigExport } from "@tarojs/cli";
export default {
  defineConstants: {
    BASE_URL: '"http://localhost:8080/api/user"',
    WS_URL: '"ws://localhost:8080/api/user/chat/ws"'
  },
  mini: {},
  h5: {},
} satisfies UserConfigExport<'webpack5'>
```

**重要提醒**：
- `BASE_URL` 和 `WS_URL` 的值必须使用**双引号包裹**（因为 defineConstants 会直接替换代码中的常量）
- 修改配置时需要同时修改 HTTP 和 WebSocket 地址

## 本地开发

### 开发环境（dev）

```bash
# 启动开发服务器（H5）
npm run dev:h5

# 或其他平台
npm run dev:weapp   # 微信小程序
npm run dev:alipay  # 支付宝小程序
npm run dev:tt      # 字节小程序
```

## 生产构建

### 使用 npm 脚本构建

```bash
# 构建 FAT 环境
npm run build:h5:fat

# 构建 UAT 环境
npm run build:h5:uat

# 构建 PRO 环境
npm run build:h5:pro
```

### 使用环境变量构建

```bash
# 构建 FAT 环境
NODE_ENV=production APP_ENV=fat npm run build:h5

# 构建 UAT 环境
NODE_ENV=production APP_ENV=uat npm run build:h5

# 构建 PRO 环境
NODE_ENV=production APP_ENV=pro npm run build:h5
```

## Docker 构建

### 基本构建

```bash
# 构建 dev 环境镜像（默认）
docker build -t chat-service-frontend-user:dev .

# 构建 FAT 环境镜像
docker build --build-arg APP_ENV=fat -t chat-service-frontend-user:fat .

# 构建 UAT 环境镜像
docker build --build-arg APP_ENV=uat -t chat-service-frontend-user:uat .

# 构建 PRO 环境镜像
docker build --build-arg APP_ENV=pro -t chat-service-frontend-user:pro .
```

### 运行容器

```bash
# 运行 FAT 环境容器
docker run -d -p 8080:80 --name chat-user-fat chat-service-frontend-user:fat

# 运行 PRO 环境容器
docker run -d -p 8080:80 --name chat-user-pro chat-service-frontend-user:pro
```

## 配置说明

### 环境选择逻辑

配置文件的选择逻辑（`config/index.ts`）：

1. 如果 `NODE_ENV=development`，使用 `dev.ts` 配置（本地开发）
2. 如果 `NODE_ENV=production`，根据 `APP_ENV` 环境变量选择：
   - `APP_ENV=fat` → 使用 `fat.ts`
   - `APP_ENV=uat` → 使用 `uat.ts`
   - `APP_ENV=pro` → 使用 `pro.ts`
   - 未设置或其他值 → 使用 `dev.ts`（默认）

### Dockerfile 构建参数

Dockerfile 支持以下构建参数：

```dockerfile
ARG APP_ENV=dev
```

构建时通过 `--build-arg` 传递：

```bash
docker build --build-arg APP_ENV=fat -t image:tag .
```

## 环境配置示例

### dev（开发环境）

```typescript
BASE_URL: '"http://localhost:8080/api/user"',
WS_URL: '"ws://localhost:8080/api/user/chat/ws"'
```

### fat（功能测试环境）

```typescript
BASE_URL: '"http://192.168.153.111:8080/api/user"',
WS_URL: '"ws://192.168.153.111:8080/api/user/chat/ws"'
```

### uat（用户验收测试环境）

```typescript
BASE_URL: '"http://uat-server:8080/api/user"',
WS_URL: '"ws://uat-server:8080/api/user/chat/ws"'
```

### pro（生产环境）

```typescript
BASE_URL: '"http://120.77.242.145:8080/api/user"',
WS_URL: '"ws://120.77.242.145:8080/api/user/chat/ws"'
```

## 验证环境配置

构建完成后，检查打包后的代码中 `BASE_URL` 和 `WS_URL` 是否为预期值：

```bash
# 查看构建产物
cat dist/index.html | grep -o 'BASE_URL.*' | head -1
```

## 常见问题

### 1. 配置未生效

**原因**：`APP_ENV` 环境变量未正确设置
**解决**：确保在构建命令中显式指定 `APP_ENV`

```bash
# 错误示例
npm run build:h5

# 正确示例
NODE_ENV=production APP_ENV=fat npm run build:h5
```

### 2. WebSocket 连接失败

**原因**：`WS_URL` 协议错误（HTTP 服务器使用 `ws://`，HTTPS 服务器使用 `wss://`）
**解决**：根据后端协议修改配置文件中的 `WS_URL`

### 3. Docker 构建使用错误的配置

**原因**：未传递 `--build-arg APP_ENV=xxx` 参数
**解决**：显式指定环境参数

```bash
docker build --build-arg APP_ENV=fat -t image:tag .
```

## 总结

- ✅ 支持 4 个环境配置（dev/fat/uat/pro）
- ✅ 通过 `APP_ENV` 环境变量切换配置
- ✅ Docker 构建支持环境参数
- ✅ 提供便捷的 npm scripts
- ✅ 配置文件独立管理，易于维护
