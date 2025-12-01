# 知识库 RAG 服务

基于 FAISS 的知识库嵌入和召回服务，支持多知识库隔离。

## 功能特性

- **文件嵌入服务**: 支持 PDF、DOCX、TXT 文件的文本提取、智能分片和向量化存储
- **召回服务**: 支持基于语义相似度的文本召回，支持多知识库联合搜索
- **多知识库隔离**: 每个知识库使用独立的 FAISS 索引
- **灵活的嵌入模型**: 支持本地模型（SentenceTransformer）和远程 API（OpenAI 兼容）
- **智能文本分片**: 基于句子边界的智能分片，避免切断完整句子

## 技术栈

- **Web 框架**: FastAPI
- **向量数据库**: FAISS (IndexFlatIP)
- **嵌入模型**: SentenceTransformer / OpenAI-compatible API
- **文档解析**: pdfplumber, python-docx
- **Python 版本**: >= 3.10

## 快速开始

### 1. 安装依赖

```bash
# 使用 uv 创建虚拟环境
uv venv

# 激活虚拟环境
source .venv/bin/activate  # Linux/Mac
# 或 .venv\Scripts\activate  # Windows

# 安装依赖
uv pip install -r requirements.txt
```

### 2. 配置环境变量

创建 `.env` 文件（可选）:

```bash
# 数据目录
DATA_DIR=./data

# 嵌入模型配置
EMBEDDING_MODEL_NAME=BAAI/bge-small-zh-v1.5
EMBEDDING_REMOTE=false  # true 使用远程 API，false 使用本地模型

# 远程 API 配置（仅当 EMBEDDING_REMOTE=true 时需要）
EMBEDDING_API_BASE=https://api.openai.com/v1
EMBEDDING_API_KEY=your-api-key-here

# 嵌入向量维度（本地模型自动推断，远程 API 需要指定）
EMBEDDING_DIM=512
```

### 3. 启动服务

```bash
# 方式一：直接运行
python app/main.py

# 方式二：使用 uvicorn
uvicorn app.main:app --host 0.0.0.0 --port 8001 --reload
```

服务将在 `http://localhost:8001` 启动。

### 4. 访问 API 文档

- Swagger UI: http://localhost:8001/docs
- ReDoc: http://localhost:8001/redoc
- OpenAPI JSON: http://localhost:8001/openapi.json

## API 使用示例

### 1. 文件嵌入服务

**端点**: `POST /api/embed`

**请求示例**:

```bash
curl -X POST "http://localhost:8001/api/embed" \
  -H "Content-Type: application/json" \
  -d '{
    "file_path": "/path/to/document.pdf",
    "file_id": 123,
    "kb_id": 1,
    "chunk_size": 512,
    "overlap": 64
  }'
```

**请求参数**:
- `file_path` (str): 文件路径（支持 .txt, .pdf, .docx）
- `file_id` (int): 文件 ID（用于元数据关联）
- `kb_id` (int): 知识库 ID（用于多知识库隔离）
- `chunk_size` (int, 默认 512): 每个文本分片的大小（字符数）
- `overlap` (int, 默认 64): 分片之间的重叠字符数

**响应示例**:

```json
{
  "success": true,
  "chunks_created": 42,
  "message": "成功嵌入 42 个文本分片"
}
```

### 2. 召回服务

**端点**: `POST /api/retrieve`

**请求示例**:

```bash
curl -X POST "http://localhost:8001/api/retrieve" \
  -H "Content-Type: application/json" \
  -d '{
    "question": "如何使用 FAISS 进行向量搜索？",
    "kb_ids": [1, 2],
    "top_k": 5
  }'
```

**请求参数**:
- `question` (str): 用户问题
- `kb_ids` (List[int]): 知识库 ID 列表（支持多知识库联合搜索）
- `top_k` (int, 默认 10): 返回前 K 个最相似的结果

**响应示例**:

```json
{
  "success": true,
  "results": [
    {
      "kb_id": 1,
      "file_id": 123,
      "chunk_idx": 5,
      "text": "FAISS 是一个高效的相似度搜索库...",
      "score": 0.8923
    },
    {
      "kb_id": 2,
      "file_id": 456,
      "chunk_idx": 12,
      "text": "向量搜索的基本原理是...",
      "score": 0.8512
    }
  ],
  "message": "成功检索到 2 个相关文本片段"
}
```

### 3. 健康检查

**端点**: `GET /health`

```bash
curl http://localhost:8001/health
```

**响应示例**:

```json
{
  "status": "healthy",
  "embedding_model": "BAAI/bge-small-zh-v1.5",
  "embedding_remote": false,
  "data_dir": "./data",
  "vector_db_dir": "./data/faiss"
}
```

## 目录结构

```
backend/
├── app/
│   ├── __init__.py
│   ├── main.py              # FastAPI 应用入口
│   ├── config.py            # 配置管理
│   ├── models/
│   │   ├── __init__.py
│   │   └── schemas.py       # Pydantic 数据模型
│   ├── routers/
│   │   ├── __init__.py
│   │   ├── embedding.py     # 文件嵌入 API
│   │   └── retrieval.py     # 召回 API
│   └── services/
│       ├── __init__.py
│       ├── embeddings.py    # 嵌入服务
│       ├── parsers.py       # 文档解析
│       ├── text_splitter.py # 文本分片
│       └── vector_store.py  # 向量存储
├── data/                    # 数据目录（自动创建）
│   └── faiss/              # FAISS 索引和元数据
│       ├── kb_1.index
│       ├── kb_1.metadata.npy
│       └── ...
├── requirements.txt
└── README.md
```

## 架构说明

### 1. 文本处理流程

```
文件 → 解析器 (parsers.py) → 文本
                ↓
文本 → 分片器 (text_splitter.py) → 文本块列表
                ↓
文本块 → 嵌入服务 (embeddings.py) → 向量矩阵
                ↓
向量 + 元数据 → 向量存储 (vector_store.py) → FAISS 索引
```

### 2. 召回流程

```
用户问题 → 嵌入服务 → 查询向量
                ↓
查询向量 + kb_ids → 向量存储 → FAISS 搜索
                ↓
索引 ID → 元数据查询 → 结果列表（文本 + 分数）
```

### 3. 多知识库隔离

- 每个知识库 (kb_id) 使用独立的 FAISS 索引文件
- 索引路径: `data/faiss/kb_{kb_id}.index`
- 元数据路径: `data/faiss/kb_{kb_id}.metadata.npy`
- 支持多知识库联合搜索，结果按分数全局排序

### 4. 嵌入模型配置

**本地模型** (EMBEDDING_REMOTE=false):
- 使用 SentenceTransformer 加载本地模型
- 支持 HuggingFace 模型（如 BAAI/bge-small-zh-v1.5）
- 延迟加载，首次调用时加载模型

**远程 API** (EMBEDDING_REMOTE=true):
- 使用 OpenAI 兼容的嵌入 API
- 需要配置 EMBEDDING_API_BASE 和 EMBEDDING_API_KEY
- 支持任何兼容 OpenAI Embeddings API 的服务

## Python 集成示例

```python
import httpx

# 文件嵌入
async def embed_file(file_path: str, file_id: int, kb_id: int):
    async with httpx.AsyncClient() as client:
        response = await client.post(
            "http://localhost:8001/api/embed",
            json={
                "file_path": file_path,
                "file_id": file_id,
                "kb_id": kb_id,
                "chunk_size": 512,
                "overlap": 64
            }
        )
        return response.json()

# 召回
async def retrieve(question: str, kb_ids: list[int], top_k: int = 5):
    async with httpx.AsyncClient() as client:
        response = await client.post(
            "http://localhost:8001/api/retrieve",
            json={
                "question": question,
                "kb_ids": kb_ids,
                "top_k": top_k
            }
        )
        return response.json()
```

## 注意事项

1. **文件路径**: 文件嵌入服务需要提供可访问的本地文件路径
2. **向量维度**: 本地模型和远程 API 的向量维度必须一致
3. **知识库 ID**: kb_id 用于隔离不同知识库的数据，同一文件可以属于多个知识库
4. **分片参数**: chunk_size 和 overlap 影响检索效果，建议根据实际文档类型调整
5. **数据持久化**: FAISS 索引和元数据存储在 `data/faiss/` 目录，需要定期备份

## 性能优化建议

- 使用 GPU 版本的 FAISS (`faiss-gpu`) 提升搜索性能
- 调整 `chunk_size` 和 `overlap` 以平衡检索精度和性能
- 使用远程嵌入 API 可以减少本地资源消耗
- 对于大规模知识库，考虑使用 FAISS 的索引压缩和量化技术

## 许可证

MIT License
