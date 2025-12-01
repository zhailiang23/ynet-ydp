"""向量存储服务 - 基于 FAISS，支持多知识库隔离"""
from pathlib import Path
from typing import List, Tuple

import faiss
import numpy as np

from app.config import settings


def _index_path_for_kb(kb_id: int) -> Path:
    """获取知识库对应的索引文件路径

    Args:
        kb_id: 知识库 ID

    Returns:
        索引文件路径
    """
    return settings.VECTOR_DB_DIR / f"kb_{kb_id}.index"


def _ids_path_for_kb(kb_id: int) -> Path:
    """获取知识库对应的 ID 映射文件路径

    Args:
        kb_id: 知识库 ID

    Returns:
        ID 映射文件路径
    """
    return settings.VECTOR_DB_DIR / f"kb_{kb_id}.ids.npy"


def create_or_load_index(kb_id: int, dim: int) -> faiss.IndexFlatIP:
    """创建或加载知识库的向量索引

    Args:
        kb_id: 知识库 ID
        dim: 向量维度

    Returns:
        FAISS 索引对象
    """
    path = _index_path_for_kb(kb_id)
    if path.exists():
        index = faiss.read_index(str(path))
        return index  # type: ignore
    index = faiss.IndexFlatIP(dim)
    faiss.write_index(index, str(path))
    return index


def save_index(kb_id: int, index: faiss.IndexFlatIP) -> None:
    """保存知识库的向量索引

    Args:
        kb_id: 知识库 ID
        index: FAISS 索引对象
    """
    path = _index_path_for_kb(kb_id)
    faiss.write_index(index, str(path))


def add_embeddings(
    kb_id: int,
    file_id: int,
    embeddings: np.ndarray,
    chunk_texts: List[str]
) -> None:
    """向知识库添加嵌入向量

    Args:
        kb_id: 知识库 ID
        file_id: 文件 ID
        embeddings: 嵌入向量矩阵，shape: (N, D)
        chunk_texts: 文本分片列表（用于后续检索时返回原文）
    """
    # 加载或创建索引
    index = create_or_load_index(kb_id, embeddings.shape[1])

    # 添加向量
    index.add(embeddings)

    # 保存索引
    save_index(kb_id, index)

    # 保存 chunk 元数据（chunk_id -> (kb_id, file_id, text)）
    ids_path = _ids_path_for_kb(kb_id)
    metadata_path = settings.VECTOR_DB_DIR / f"kb_{kb_id}.metadata.npy"

    # 当前索引中已有的向量数量
    start_idx = index.ntotal - len(chunk_texts)

    # 构建新的元数据：[(kb_id, file_id, chunk_idx, text), ...]
    new_metadata = [
        {
            'kb_id': kb_id,
            'file_id': file_id,
            'chunk_idx': start_idx + i,
            'text': text
        }
        for i, text in enumerate(chunk_texts)
    ]

    # 加载现有元数据
    if metadata_path.exists():
        existing_metadata = list(np.load(metadata_path, allow_pickle=True))
        all_metadata = existing_metadata + new_metadata
    else:
        all_metadata = new_metadata

    # 保存元数据
    np.save(metadata_path, np.array(all_metadata, dtype=object))


def search(
    kb_ids: List[int],
    query_vecs: np.ndarray,
    top_k: int = 10
) -> List[dict]:
    """在指定的知识库中搜索相似文本

    Args:
        kb_ids: 知识库 ID 列表（支持多知识库联合搜索）
        query_vecs: 查询向量矩阵，shape: (N, D)
        top_k: 返回前 K 个结果

    Returns:
        搜索结果列表，每个结果包含：
        - kb_id: 知识库 ID
        - file_id: 文件 ID
        - chunk_idx: 分片索引
        - text: 分片文本
        - score: 相似度分数
    """
    all_results = []

    for kb_id in kb_ids:
        # 加载索引
        index_path = _index_path_for_kb(kb_id)
        if not index_path.exists():
            continue

        index = faiss.read_index(str(index_path))

        # 搜索
        scores, idxs = index.search(query_vecs, top_k)

        # 加载元数据
        metadata_path = settings.VECTOR_DB_DIR / f"kb_{kb_id}.metadata.npy"
        if not metadata_path.exists():
            continue

        metadata = np.load(metadata_path, allow_pickle=True)

        # 构建结果
        for query_idx in range(len(query_vecs)):
            for rank in range(min(top_k, len(idxs[query_idx]))):
                chunk_idx = idxs[query_idx][rank]
                score = float(scores[query_idx][rank])

                if chunk_idx < 0 or chunk_idx >= len(metadata):
                    continue

                meta = metadata[chunk_idx]
                all_results.append({
                    'kb_id': int(meta['kb_id']),
                    'file_id': int(meta['file_id']),
                    'chunk_idx': int(meta['chunk_idx']),
                    'text': str(meta['text']),
                    'score': score
                })

    # 按分数降序排序，返回前 top_k 个
    all_results.sort(key=lambda x: x['score'], reverse=True)
    return all_results[:top_k]


def delete_file_embeddings(kb_id: int, file_id: int) -> int:
    """删除指定文件的所有嵌入向量

    Args:
        kb_id: 知识库 ID
        file_id: 文件 ID

    Returns:
        删除的向量数量
    """
    index_path = _index_path_for_kb(kb_id)
    metadata_path = settings.VECTOR_DB_DIR / f"kb_{kb_id}.metadata.npy"

    # 如果索引或元数据不存在，直接返回
    if not index_path.exists() or not metadata_path.exists():
        return 0

    # 加载元数据
    metadata = list(np.load(metadata_path, allow_pickle=True))

    # 找出需要删除的向量索引
    indices_to_delete = []
    new_metadata = []

    for i, meta in enumerate(metadata):
        if meta['file_id'] == file_id and meta['kb_id'] == kb_id:
            indices_to_delete.append(i)
        else:
            new_metadata.append(meta)

    if not indices_to_delete:
        return 0

    # 加载索引
    index = faiss.read_index(str(index_path))

    # FAISS 不支持直接删除向量，需要重建索引
    # 获取所有向量
    all_vectors = []
    for i in range(index.ntotal):
        if i not in indices_to_delete:
            # 重建向量（从索引中提取）
            vec = index.reconstruct(i)
            all_vectors.append(vec)

    # 如果还有剩余向量，重建索引
    if all_vectors:
        new_index = faiss.IndexFlatIP(index.d)
        new_index.add(np.array(all_vectors))
        save_index(kb_id, new_index)

        # 更新 chunk_idx
        for i, meta in enumerate(new_metadata):
            meta['chunk_idx'] = i

        # 保存新的元数据
        np.save(metadata_path, np.array(new_metadata, dtype=object))
    else:
        # 如果没有剩余向量，删除索引和元数据文件
        if index_path.exists():
            index_path.unlink()
        if metadata_path.exists():
            metadata_path.unlink()

    return len(indices_to_delete)


def delete_kb_index(kb_id: int) -> None:
    """删除知识库的索引和元数据

    Args:
        kb_id: 知识库 ID
    """
    index_path = _index_path_for_kb(kb_id)
    ids_path = _ids_path_for_kb(kb_id)
    metadata_path = settings.VECTOR_DB_DIR / f"kb_{kb_id}.metadata.npy"

    if index_path.exists():
        index_path.unlink()
    if ids_path.exists():
        ids_path.unlink()
    if metadata_path.exists():
        metadata_path.unlink()
