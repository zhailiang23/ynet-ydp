"""API 请求和响应模型"""
from typing import List, Optional

from pydantic import BaseModel, Field


class EmbedFileRequest(BaseModel):
    """文件嵌入请求"""
    file_path: str = Field(..., description="文件路径")
    file_id: int = Field(..., description="文件 ID")
    kb_id: int = Field(..., description="知识库 ID")
    chunk_size: int = Field(512, description="分片大小（字符数）")
    overlap: int = Field(64, description="分片重叠字符数")


class EmbedFileResponse(BaseModel):
    """文件嵌入响应"""
    success: bool = Field(..., description="是否成功")
    chunks_created: int = Field(..., description="创建的分片数量")
    message: str = Field("", description="提示信息")


class RetrievalRequest(BaseModel):
    """召回请求"""
    question: str = Field(..., description="用户问题")
    kb_ids: List[int] = Field(..., description="知识库 ID 列表")
    top_k: int = Field(10, description="返回前 K 个结果")


class RetrievalResult(BaseModel):
    """召回结果"""
    kb_id: int = Field(..., description="知识库 ID")
    file_id: int = Field(..., description="文件 ID")
    chunk_idx: int = Field(..., description="分片索引")
    text: str = Field(..., description="分片文本内容")
    score: float = Field(..., description="相似度分数")


class RetrievalResponse(BaseModel):
    """召回响应"""
    success: bool = Field(..., description="是否成功")
    results: List[RetrievalResult] = Field(..., description="召回结果列表")
    message: str = Field("", description="提示信息")


class DeleteFileRequest(BaseModel):
    """删除文件向量请求"""
    file_id: int = Field(..., description="文件 ID")
    kb_id: int = Field(..., description="知识库 ID")


class DeleteFileResponse(BaseModel):
    """删除文件向量响应"""
    success: bool = Field(..., description="是否成功")
    vectors_deleted: int = Field(..., description="删除的向量数量")
    message: str = Field("", description="提示信息")
