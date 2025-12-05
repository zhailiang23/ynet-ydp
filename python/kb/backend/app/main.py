"""FastAPI 应用主入口"""
import os

# 在导入任何科学计算库之前，强制禁用 OpenBLAS 多线程
# 必须在 import numpy/faiss 之前设置，否则无效
os.environ['OPENBLAS_NUM_THREADS'] = '1'
os.environ['OMP_NUM_THREADS'] = '1'
os.environ['MKL_NUM_THREADS'] = '1'
os.environ['NUMEXPR_NUM_THREADS'] = '1'

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.config import settings
from app.routers import embedding, retrieval

# 创建应用实例
app = FastAPI(
    title="知识库 RAG 服务",
    description="基于 FAISS 的知识库嵌入和召回服务",
    version="1.0.0"
)

# 配置 CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 生产环境应该配置具体的域名
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# 注册路由
app.include_router(embedding.router, prefix="/api")
app.include_router(retrieval.router, prefix="/api")


@app.get("/")
async def root():
    """健康检查端点"""
    return {
        "status": "ok",
        "service": "Knowledge Base RAG Service",
        "version": "1.0.0"
    }


@app.get("/health")
async def health_check():
    """健康检查"""
    return {
        "status": "healthy",
        "embedding_model": settings.EMBEDDING_MODEL_NAME,
        "embedding_remote": settings.EMBEDDING_REMOTE,
        "data_dir": str(settings.DATA_DIR),
        "vector_db_dir": str(settings.VECTOR_DB_DIR)
    }


if __name__ == "__main__":
    import uvicorn

    # 确保数据目录存在
    settings.DATA_DIR.mkdir(parents=True, exist_ok=True)
    settings.VECTOR_DB_DIR.mkdir(parents=True, exist_ok=True)

    # 启动服务
    uvicorn.run(
        "app.main:app",
        host="0.0.0.0",
        port=8001,
        reload=True,
        log_level="info"
    )
