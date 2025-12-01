"""配置文件"""
import os
from pathlib import Path
from dotenv import load_dotenv

# 加载.env文件
load_dotenv()


class Settings:
    """应用配置"""

    # 数据目录
    DATA_DIR: Path

    # 向量数据库目录
    VECTOR_DB_DIR: Path

    # 嵌入模型配置
    EMBEDDING_MODEL_NAME: str
    EMBEDDING_REMOTE: bool
    EMBEDDING_API_BASE: str
    EMBEDDING_API_KEY: str

    # 嵌入维度（默认值，会在运行时根据模型调整）
    EMBEDDING_DIM: int

    def __init__(self) -> None:
        # 数据目录配置
        data_dir = os.getenv("DATA_DIR", "./data")
        self.DATA_DIR = Path(data_dir)
        self.DATA_DIR.mkdir(parents=True, exist_ok=True)

        # 向量数据库目录
        self.VECTOR_DB_DIR = self.DATA_DIR / "faiss"
        self.VECTOR_DB_DIR.mkdir(parents=True, exist_ok=True)

        # 嵌入模型配置
        self.EMBEDDING_MODEL_NAME = os.getenv(
            "EMBEDDING_MODEL_NAME",
            "BAAI/bge-small-zh-v1.5"
        )
        self.EMBEDDING_REMOTE = os.getenv("EMBEDDING_REMOTE", "true").lower() == "true"
        self.EMBEDDING_API_BASE = os.getenv("EMBEDDING_API_BASE", "").rstrip("/")
        self.EMBEDDING_API_KEY = os.getenv("EMBEDDING_API_KEY", "")

        # 嵌入维度（bge-small-zh-v1.5 的维度是 512）
        self.EMBEDDING_DIM = int(os.getenv("EMBEDDING_DIM", "512"))


# 全局配置实例
settings = Settings()
