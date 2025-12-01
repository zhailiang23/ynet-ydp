"""召回服务 API"""
from fastapi import APIRouter, HTTPException

from app.models.schemas import (
    RetrievalRequest,
    RetrievalResponse,
    RetrievalResult,
)
from app.services.embeddings import embed_texts
from app.services.vector_store import search

router = APIRouter(prefix="/retrieve", tags=["retrieval"])


@router.post("", response_model=RetrievalResponse)
async def retrieve(request: RetrievalRequest) -> RetrievalResponse:
    """召回服务

    接受用户问题和知识库 ID 列表，
    对问题进行嵌入，在指定知识库中搜索最相似的文本分片。

    Args:
        request: 召回请求参数

    Returns:
        RetrievalResponse: 召回结果列表

    Raises:
        HTTPException: 处理失败时抛出
    """
    try:
        # 1. 验证知识库 ID 列表
        if not request.kb_ids:
            raise HTTPException(
                status_code=400,
                detail="知识库 ID 列表不能为空"
            )

        # 2. 验证问题不为空
        question = request.question.strip()
        if not question:
            raise HTTPException(
                status_code=400,
                detail="用户问题不能为空"
            )

        # 3. 对问题进行嵌入
        query_embeddings = embed_texts([question])

        # 4. 在向量数据库中搜索
        results = search(
            kb_ids=request.kb_ids,
            query_vecs=query_embeddings,
            top_k=request.top_k
        )

        # 5. 转换为响应格式
        retrieval_results = [
            RetrievalResult(
                kb_id=result['kb_id'],
                file_id=result['file_id'],
                chunk_idx=result['chunk_idx'],
                text=result['text'],
                score=result['score']
            )
            for result in results
        ]

        return RetrievalResponse(
            success=True,
            results=retrieval_results,
            message=f"成功检索到 {len(retrieval_results)} 个相关文本片段"
        )

    except HTTPException:
        raise
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"召回处理失败: {str(e)}"
        )
