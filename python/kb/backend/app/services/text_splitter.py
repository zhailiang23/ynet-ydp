"""文本分片服务"""
import re
from typing import List


def split_text(
    text: str,
    chunk_size: int = 512,
    overlap: int = 64
) -> List[str]:
    """将文本分片

    Args:
        text: 要分片的文本
        chunk_size: 每个分片的大小（字符数）
        overlap: 分片之间的重叠字符数

    Returns:
        分片后的文本列表
    """
    if chunk_size <= 0:
        chunk_size = 512
    if overlap < 0:
        overlap = 0

    text = text or ""

    # 句子结束标点符号
    sentence_endings = r'[.!?。！？；;]'

    chunks: List[str] = []
    start = 0
    n = len(text)

    while start < n:
        end = min(start + chunk_size, n)

        # 如果已经到文本末尾，直接取到最后
        if end >= n:
            chunk = text[start:end]
        else:
            # 在指定范围内寻找最后一个句子结束位置
            temp_chunk = text[start:end]

            # 寻找最后一个句子结束符的位置
            matches = list(re.finditer(sentence_endings, temp_chunk))
            if matches:
                # 找到句子结束符，在其后面切分
                last_sentence_end = matches[-1].end()
                chunk = temp_chunk[:last_sentence_end]
                end = start + last_sentence_end
            else:
                # 没找到句子结束符，在空白字符处切分以避免切断单词
                space_pos = temp_chunk.rfind(' ')
                if space_pos > len(temp_chunk) * 0.5:  # 确保不会切分得太短
                    chunk = temp_chunk[:space_pos]
                    end = start + space_pos
                else:
                    # 如果空白符位置太靠前，就按原来的方式切分
                    chunk = temp_chunk

        # 确保没有前导/尾随空白符
        chunk = chunk.strip()
        if chunk:
            chunks.append(chunk)

        if end >= n:
            break

        start = max(0, end - overlap)

    return chunks
