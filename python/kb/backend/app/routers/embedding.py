"""文件嵌入服务 API"""
from pathlib import Path

from fastapi import APIRouter, HTTPException

from app.models.schemas import (
    EmbedFileRequest, EmbedFileResponse,
    DeleteFileRequest, DeleteFileResponse
)
from app.services.embeddings import embed_texts
from app.services.parsers import parse_file
from app.services.text_splitter import split_text
from app.services.vector_store import add_embeddings, delete_file_embeddings

router = APIRouter(prefix="/embed", tags=["embedding"])


@router.post("", response_model=EmbedFileResponse)
async def embed_file(request: EmbedFileRequest) -> EmbedFileResponse:
    """文件嵌入服务

    接受文件路径、文件 ID、知识库 ID 和分片参数，
    提取文本、分片、生成嵌入向量并存储到向量数据库。

    Args:
        request: 文件嵌入请求参数

    Returns:
        EmbedFileResponse: 嵌入结果

    Raises:
        HTTPException: 文件不存在或处理失败时抛出
    """
    try:
        # 1. 验证文件是否存在
        file_path = Path(request.file_path)
        if not file_path.exists():
            raise HTTPException(
                status_code=404,
                detail=f"文件不存在: {request.file_path}"
            )

        # 2. 解析文件，提取文本
        try:
            text = parse_file(file_path)
        except ValueError as e:
            raise HTTPException(
                status_code=400,
                detail=f"文件解析失败: {str(e)}"
            )

        # 3. 文本分片
        chunks = split_text(
            text,
            chunk_size=request.chunk_size,
            overlap=request.overlap
        )

        if not chunks:
            raise HTTPException(
                status_code=400,
                detail="文件内容为空或无法分片"
            )

        # 4. 生成嵌入向量
        embeddings = embed_texts(chunks)

        # 5. 存储到向量数据库
        add_embeddings(
            kb_id=request.kb_id,
            file_id=request.file_id,
            embeddings=embeddings,
            chunk_texts=chunks
        )

        return EmbedFileResponse(
            success=True,
            chunks_created=len(chunks),
            message=f"成功嵌入 {len(chunks)} 个文本分片"
        )

    except HTTPException:
        raise
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"嵌入处理失败: {str(e)}"
        )


@router.delete("", response_model=DeleteFileResponse)
async def delete_file(request: DeleteFileRequest) -> DeleteFileResponse:
    """删除文件的向量数据

    从向量数据库中删除指定文件的所有嵌入向量和元数据。

    Args:
        request: 删除文件请求参数

    Returns:
        DeleteFileResponse: 删除结果

    Raises:
        HTTPException: 删除失败时抛出
    """
    try:
        # 调用删除服务
        vectors_deleted = delete_file_embeddings(
            kb_id=request.kb_id,
            file_id=request.file_id
        )

        if vectors_deleted == 0:
            return DeleteFileResponse(
                success=True,
                vectors_deleted=0,
                message="文件向量数据不存在或已删除"
            )

        return DeleteFileResponse(
            success=True,
            vectors_deleted=vectors_deleted,
            message=f"成功删除 {vectors_deleted} 个向量"
        )

    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"删除向量失败: {str(e)}"
        )
