# 知识库 RAG 服务集成说明

## 概述

本文档说明如何将 Java 后端的知识库文件服务与 Python RAG 服务集成，实现文件上传后自动进行向量化嵌入。

## 架构设计

```
文件上传
    ↓
1. 保存文件到本地存储
2. 保存记录到数据库 (status=0 处理中)
3. 提交事务
    ↓
4. 发布 FileEmbedEvent 事件
    ↓
5. 事务提交后，监听器异步处理
    ↓
6. 调用 Python RAG 服务嵌入文件
    ↓
7. 更新文件状态 (1=成功, 2=失败)
```

## 文件修改清单

### 1. 新增文件

#### 1.1 RAG 服务客户端
**文件**: `com/ynet/iplatform/module/knowledge/client/rag/RagServiceClient.java`

**功能**:
- 调用 Python RAG 服务的文件嵌入接口
- 调用 Python RAG 服务的召回接口
- 健康检查

**配置项**:
- `knowledge.rag.service-url`: RAG 服务地址 (默认: http://localhost:8001)
- `knowledge.rag.timeout`: 超时时间（默认: 60000ms）
- `knowledge.rag.chunk-size`: 默认分片大小（默认: 512）
- `knowledge.rag.overlap`: 默认重叠大小（默认: 64）

#### 1.2 文件嵌入事件
**文件**: `com/ynet/iplatform/module/knowledge/event/FileEmbedEvent.java`

**字段**:
- `fileId`: 文件 ID
- `kbId`: 知识库 ID
- `filePath`: 文件本地路径
- `fileName`: 文件名

#### 1.3 文件嵌入事件监听器
**文件**: `com/ynet/iplatform/module/knowledge/event/FileEmbedEventListener.java`

**功能**:
- 监听 `FileEmbedEvent` 事件
- 在事务提交后异步处理文件嵌入
- 调用 Python RAG 服务
- 更新文件处理状态

**注解**:
- `@Async`: 异步执行
- `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)`: 事务提交后触发

### 2. 修改文件

#### 2.1 知识库文件服务实现
**文件**: `com/ynet/iplatform/module/knowledge/service/file/KonwledgeFileServiceImpl.java`

**主要修改**:

1. **添加依赖注入**:
   ```java
   @Resource
   private FileConfigService fileConfigService;

   @Resource
   private ApplicationEventPublisher eventPublisher;
   ```

2. **修改 uploadFile 方法**:
   - 添加 `@Transactional` 注解
   - 文件状态初始化为 0（处理中）
   - 获取文件本地路径
   - 发布 `FileEmbedEvent` 事件

3. **新增 getLocalFilePath 方法**:
   - 从文件 URL 提取本地文件路径
   - 仅支持本地文件存储（LocalFileClient）
   - 使用反射获取 FileClient 配置

#### 2.2 配置文件
**文件**: `iplatform-server/src/main/resources/application-local.yaml`

**新增配置**:
```yaml
--- #################### 知识库相关配置 ####################

# 知识库 RAG 服务配置
knowledge:
  rag:
    service-url: http://localhost:8001  # Python RAG 服务地址
    timeout: 60000                       # 超时时间（毫秒）
    chunk-size: 512                      # 默认分片大小
    overlap: 64                          # 默认重叠大小
```

## 文件状态说明

文件的 `status` 字段表示处理状态：

| 状态值 | 说明 | 何时设置 |
|-------|------|---------|
| 0 | 处理中 | 文件上传后，事务提交前 |
| 1 | 处理完成 | Python RAG 服务嵌入成功 |
| 2 | 处理失败 | 无法获取本地路径 或 Python RAG 服务调用失败 |

## 使用流程

### 1. 启动 Python RAG 服务

```bash
cd /Users/zhailiang/Documents/code/ynet-ydp/python/kb/backend

# 创建虚拟环境
uv venv
source .venv/bin/activate

# 安装依赖
uv pip install -r requirements.txt

# 启动服务
python app/main.py
```

服务将在 `http://localhost:8001` 启动。

### 2. 配置 Java 后端

确保 `application-local.yaml` 中的配置正确：

```yaml
knowledge:
  rag:
    service-url: http://localhost:8001  # 与 Python 服务地址匹配
```

### 3. 上传文件

通过知识库管理界面上传文件，或调用 API：

```bash
POST /admin-api/knowledge/konwledge-file/upload
Content-Type: multipart/form-data

file: <文件>
baseId: <知识库ID>
```

### 4. 监控处理状态

查询文件列表，检查 `status` 字段：

```bash
GET /admin-api/knowledge/konwledge-file/page?baseId=<知识库ID>
```

## API 调用示例

### 文件嵌入（Python RAG 服务）

```bash
curl -X POST "http://localhost:8001/api/embed" \
  -H "Content-Type: application/json" \
  -d '{
    "file_path": "/path/to/file.pdf",
    "file_id": 123,
    "kb_id": 1,
    "chunk_size": 512,
    "overlap": 64
  }'
```

**响应**:
```json
{
  "success": true,
  "chunks_created": 42,
  "message": "成功嵌入 42 个文本分片"
}
```

### 召回查询（Python RAG 服务）

```bash
curl -X POST "http://localhost:8001/api/retrieve" \
  -H "Content-Type: application/json" \
  -d '{
    "question": "如何使用知识库？",
    "kb_ids": [1, 2],
    "top_k": 5
  }'
```

**响应**:
```json
{
  "success": true,
  "results": [
    {
      "kb_id": 1,
      "file_id": 123,
      "chunk_idx": 5,
      "text": "知识库使用说明...",
      "score": 0.8923
    }
  ],
  "message": "成功检索到 1 个相关文本片段"
}
```

## 日志说明

### Java 后端日志

```
[uploadFile] 发布文件嵌入事件: fileId=123, kbId=1, path=/data/knowledge/xxx.pdf
[handleFileEmbedEvent] 开始处理文件嵌入事件: fileId=123, kbId=1, filePath=/data/knowledge/xxx.pdf
[handleFileEmbedEvent] 文件嵌入成功: fileId=123, kbId=1
```

### Python RAG 服务日志

```
[embedFile] 调用 RAG 服务嵌入文件: url=http://localhost:8001/api/embed, request={...}
[embedFile] RAG 服务响应成功: response={...}
[embedFile] 文件嵌入成功: fileId=123, kbId=1, chunks=42
```

## 异常处理

### 1. Python RAG 服务不可用

- 日志: `[embedFile] RAG 服务连接失败`
- 文件状态: 更新为 2（处理失败）
- 影响: 文件已保存，但未嵌入向量数据库

### 2. 非本地文件存储

- 日志: `[getLocalFilePath] 文件不是本地存储，跳过嵌入`
- 文件状态: 更新为 2（处理失败）
- 原因: 当前仅支持本地文件存储（LocalFileClient）

### 3. 文件解析失败

- 日志: `[embedFile] RAG 服务请求失败`
- 文件状态: 更新为 2（处理失败）
- 原因: 文件格式不支持或文件损坏

## 注意事项

1. **文件存储方式**: 当前仅支持本地文件存储，如果使用 OSS/S3 等云存储，需要先下载文件到本地
2. **文件格式**: Python RAG 服务支持 PDF、DOCX、TXT 格式
3. **异步处理**: 文件嵌入是异步处理，上传接口立即返回，需要轮询 status 字段查看处理状态
4. **事务隔离**: 使用 `@TransactionalEventListener` 确保事件在事务提交后才触发
5. **超时配置**: 大文件处理可能需要调整 `timeout` 配置

## 扩展功能

### 支持云存储

如需支持 OSS/S3 等云存储，需要修改 `getLocalFilePath` 方法：

```java
private String getLocalFilePath(String fileUrl) {
    // 1. 判断是否为本地存储
    if (client instanceof LocalFileClient) {
        // 本地存储，直接返回路径
        return basePath + relativePath;
    } else {
        // 2. 云存储，下载到临时目录
        String tempDir = System.getProperty("java.io.tmpdir");
        String tempFile = tempDir + File.separator + UUID.randomUUID() + fileName;

        // 3. 下载文件
        byte[] content = client.getContent(path);
        FileUtil.writeBytes(content, tempFile);

        return tempFile;
    }
}
```

### 重试机制

在 `FileEmbedEventListener` 中添加重试逻辑：

```java
@Retryable(
    value = Exception.class,
    maxAttempts = 3,
    backoff = @Backoff(delay = 5000)
)
public void handleFileEmbedEvent(FileEmbedEvent event) {
    // ...
}
```

## 参考文档

- Python RAG 服务文档: `/Users/zhailiang/Documents/code/ynet-ydp/python/kb/backend/README.md`
- Spring Event 文档: https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#context-functionality-events
- 知识库管理文档: 待补充
