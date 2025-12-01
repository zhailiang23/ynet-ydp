"""嵌入服务 - 支持本地和远程嵌入模型"""
from functools import lru_cache
from typing import List

import numpy as np

from app.config import settings

# Lazy import 本地模型
_ST: object | None = None


def _l2_normalize(x: np.ndarray) -> np.ndarray:
    """L2 归一化

    Args:
        x: 输入向量矩阵

    Returns:
        归一化后的向量矩阵
    """
    eps = 1e-12
    norms = np.linalg.norm(x, axis=1, keepdims=True)
    norms = np.maximum(norms, eps)
    return (x / norms).astype("float32")


@lru_cache(maxsize=1)
def _load_local_model(model_name: str | None = None):
    """加载本地嵌入模型（延迟加载）

    Args:
        model_name: 模型名称

    Returns:
        SentenceTransformer 模型实例
    """
    global _ST
    from sentence_transformers import SentenceTransformer

    model_name = model_name or settings.EMBEDDING_MODEL_NAME
    _ST = SentenceTransformer(model_name)
    return _ST


def _embed_local(texts: List[str], model_name: str | None = None) -> np.ndarray:
    """使用本地模型生成嵌入向量

    Args:
        texts: 文本列表
        model_name: 模型名称（可选）

    Returns:
        归一化后的嵌入向量矩阵，shape: (N, D)
    """
    model = _load_local_model(model_name)
    vecs = model.encode(
        texts,
        batch_size=32,
        show_progress_bar=False,
        normalize_embeddings=False
    )
    if not isinstance(vecs, np.ndarray):
        vecs = np.array(vecs)
    return _l2_normalize(vecs)


def _embed_remote(texts: List[str], model_name: str | None = None) -> np.ndarray:
    """使用远程 API 生成嵌入向量（OpenAI 兼容接口）

    Args:
        texts: 文本列表
        model_name: 模型名称（可选）

    Returns:
        归一化后的嵌入向量矩阵，shape: (N, D)

    Raises:
        RuntimeError: API 配置错误时抛出
    """
    from openai import OpenAI

    model_name = model_name or settings.EMBEDDING_MODEL_NAME

    # 检查 API 配置
    if not settings.EMBEDDING_API_BASE or not settings.EMBEDDING_API_KEY:
        raise RuntimeError(
            "嵌入模型 API 配置错误：EMBEDDING_API_BASE 和 EMBEDDING_API_KEY 必须设置"
        )

    # 创建 OpenAI 客户端
    client = OpenAI(
        base_url=settings.EMBEDDING_API_BASE,
        api_key=settings.EMBEDDING_API_KEY
    )

    # 调用嵌入 API
    resp = client.embeddings.create(model=model_name, input=texts)

    # 提取嵌入向量
    vecs = np.array([item.embedding for item in resp.data], dtype="float32")

    return _l2_normalize(vecs)


def embed_texts(texts: List[str], model_name: str | None = None) -> np.ndarray:
    """生成文本嵌入向量

    根据配置自动选择本地模型或远程 API

    Args:
        texts: 文本列表
        model_name: 模型名称（可选，默认使用配置中的模型）

    Returns:
        L2 归一化的 float32 嵌入向量矩阵，shape: (N, D)

    Raises:
        RuntimeError: 远程 API 配置错误时抛出
    """
    if settings.EMBEDDING_REMOTE:
        return _embed_remote(texts, model_name)
    else:
        return _embed_local(texts, model_name)


def get_embedding_dimension() -> int:
    """获取嵌入向量的维度

    Returns:
        嵌入向量维度
    """
    # 使用一个测试文本获取维度
    test_vec = embed_texts(["测试"])
    return test_vec.shape[1]
