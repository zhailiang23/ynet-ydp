# Maven 依赖缓存说明

## 问题背景

默认情况下，每次 GitLab CI/CD 运行时都会创建全新的 Docker 容器，Maven 依赖需要重新下载，导致：
- 首次构建时间长（5-10 分钟下载依赖）
- 重复下载相同的依赖包
- 浪费网络带宽和构建时间

## 解决方案

我们通过 **GitLab CI/CD 缓存机制** 来解决这个问题。

### 1. GitLab Runner 本地缓存配置

**配置文件**: `/etc/gitlab-runner/config.toml` (192.168.153.111 服务器)

```toml
[runners.cache]
  Type = "local"                          # 使用本地缓存（不需要 S3/MinIO）
  Path = "/var/lib/gitlab-runner/cache"   # 缓存存储路径
  Shared = false                          # 不与其他项目共享
  MaxUploadedArchiveSize = 0              # 不限制缓存大小

[runners.docker]
  volumes = [
    "/var/run/docker.sock:/var/run/docker.sock",
    "/cache",
    "/var/lib/gitlab-runner/cache"        # 挂载缓存目录到容器
  ]
```

**关键配置**:
- `Type = "local"`: 使用服务器本地文件系统存储缓存
- `Path`: 缓存存储在 `/var/lib/gitlab-runner/cache` 目录
- `volumes`: 将缓存目录挂载到 Docker 容器内部

### 2. .gitlab-ci.yml 缓存配置

**配置内容**:
```yaml
cache:
  key: "maven-cache"       # 固定缓存键，所有构建共享
  paths:
    - .m2/repository/      # 缓存 Maven 本地仓库
  policy: pull-push        # 每次构建都尝试拉取缓存，结束后更新缓存
```

**关键配置**:
- `key: "maven-cache"`: 使用固定缓存键，所有分支共享依赖
  - 之前使用 `${CI_COMMIT_REF_SLUG}` 会导致每个分支有独立缓存
- `paths: .m2/repository/`: 缓存 Maven 本地仓库目录
- `policy: pull-push`:
  - `pull`: 构建开始时拉取缓存
  - `push`: 构建结束时更新缓存

### 3. Maven 配置

**MAVEN_OPTS 变量**:
```yaml
MAVEN_OPTS: "-Dmaven.repo.local=$CI_PROJECT_DIR/.m2/repository -Dmaven.test.skip=true"
```

- `-Dmaven.repo.local=$CI_PROJECT_DIR/.m2/repository`: 指定 Maven 本地仓库位置
- `$CI_PROJECT_DIR`: GitLab CI/CD 项目根目录（会被缓存系统扫描）

**阿里云镜像加速**:
```xml
<mirror>
  <id>aliyun</id>
  <mirrorOf>central</mirrorOf>
  <name>Aliyun Maven</name>
  <url>https://maven.aliyun.com/repository/public</url>
</mirror>
```

## 工作流程

### 首次构建（无缓存）

```
1. Pipeline 启动
2. GitLab Runner 检查缓存 "maven-cache" → 未找到
3. Maven 开始下载依赖到 .m2/repository/
   - Spring Boot 依赖 (~100 个 JAR)
   - MyBatis Plus 依赖 (~50 个 JAR)
   - 其他第三方库 (~200 个 JAR)
4. 构建完成后，GitLab Runner 压缩 .m2/repository/ 并保存到缓存
5. 缓存存储在 /var/lib/gitlab-runner/cache/maven-cache
```

**耗时**: 约 5-10 分钟（取决于网络速度和依赖数量）

### 后续构建（有缓存）

```
1. Pipeline 启动
2. GitLab Runner 检查缓存 "maven-cache" → 找到
3. 解压缓存到 .m2/repository/
4. Maven 检查依赖:
   - 已存在的依赖 → 跳过下载 ✅
   - 新增/更新的依赖 → 下载 ⬇️
5. 构建完成后，更新缓存（包含新下载的依赖）
```

**耗时**: 约 1-2 分钟（只下载新增/更新的依赖）

## 缓存效果对比

| 构建类型 | Maven 依赖下载 | 构建总时间 | 网络流量 |
|---------|--------------|----------|---------|
| 首次构建 | 全部下载（~350 个 JAR, ~500MB） | 约 10 分钟 | ~500MB |
| 后续构建（无变更） | 跳过下载 | 约 2 分钟 | ~0MB |
| 后续构建（新增依赖） | 只下载新增部分（~10 个 JAR, ~50MB） | 约 3 分钟 | ~50MB |

**性能提升**: 后续构建速度提升 **80%**

## 缓存管理

### 查看缓存

```bash
# SSH 到服务器
ssh root@192.168.153.111

# 查看缓存目录
ls -lh /var/lib/gitlab-runner/cache/

# 查看缓存大小
du -sh /var/lib/gitlab-runner/cache/
```

### 清理缓存

如果依赖损坏或需要强制重新下载：

```bash
# 删除特定项目的缓存
rm -rf /var/lib/gitlab-runner/cache/maven-cache

# 或者通过 GitLab Web UI:
# CI/CD → Pipelines → Clear runner caches
```

### 缓存失效

缓存会在以下情况下失效：
- 手动删除缓存目录
- 修改 `.gitlab-ci.yml` 中的 `cache.key`
- 通过 GitLab UI 清理缓存

## 常见问题

### Q1: 为什么首次构建仍然很慢？

**A**: 首次构建必须下载所有依赖，这是正常的。后续构建会自动使用缓存，速度会大幅提升。

### Q2: 如何验证缓存是否生效？

**A**: 查看 Pipeline 日志：
```
Checking cache for maven-cache...  # 检查缓存
Successfully extracted cache       # 成功解压缓存（说明缓存生效）
```

如果看到 "Downloading..." 的依赖数量很少或没有，说明缓存正常工作。

### Q3: 缓存会占用多少磁盘空间？

**A**: Maven 缓存通常占用 **500MB - 2GB**，取决于项目依赖数量。

### Q4: 多个分支会共享缓存吗？

**A**: 是的！我们使用固定缓存键 `maven-cache`，所有分支共享同一份缓存。这样可以避免重复下载相同的依赖。

### Q5: 如果依赖更新了，缓存会自动更新吗？

**A**: 会的！Maven 会自动检测依赖版本变化：
- 如果 `pom.xml` 中依赖版本更新，Maven 会下载新版本
- 构建结束后，新下载的依赖会自动加入缓存

## 最佳实践

1. **定期清理缓存** (每月一次)
   ```bash
   rm -rf /var/lib/gitlab-runner/cache/maven-cache
   ```
   避免缓存过期依赖或损坏文件

2. **监控缓存大小**
   ```bash
   du -sh /var/lib/gitlab-runner/cache/
   ```
   如果缓存超过 5GB，考虑清理

3. **依赖版本管理**
   - 使用 `<dependencyManagement>` 统一依赖版本
   - 避免使用 `SNAPSHOT` 版本（会频繁更新，导致缓存失效）

4. **使用阿里云镜像**
   - 已配置阿里云 Maven 镜像加速
   - 国内下载速度快，减少首次构建时间

## 参考资料

- [GitLab CI/CD 缓存文档](https://docs.gitlab.com/ee/ci/caching/)
- [GitLab Runner 缓存配置](https://docs.gitlab.com/runner/configuration/advanced-configuration.html#the-runnerscache-section)
- [Maven 本地仓库配置](https://maven.apache.org/guides/mini/guide-configuring-maven.html#configuring-your-local-repository)
