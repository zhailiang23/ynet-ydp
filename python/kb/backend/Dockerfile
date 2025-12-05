# 使用 Python 3.12 官方镜像作为基础镜像 (基于 Debian Bookworm 稳定版)
# 使用 DaoCloud 镜像加速
FROM docker.m.daocloud.io/python:3.12-slim-bookworm

# 环境变量：支持 dev, fat, uat, pro
ARG APP_ENV=dev
ENV APP_ENV=${APP_ENV}

# 设置工作目录
WORKDIR /app

# 配置 pip 使用国内镜像加速
RUN pip config set global.index-url https://pypi.tuna.tsinghua.edu.cn/simple

# 禁用 pip 进度条避免线程创建（GitLab Runner 有线程限制）
ENV PIP_PROGRESS_BAR=off
ENV PIP_NO_PYTHON_VERSION_WARNING=1
ENV PIP_DISABLE_PIP_VERSION_CHECK=1

# 禁用 OpenBLAS 多线程避免线程创建失败导致崩溃
ENV OPENBLAS_NUM_THREADS=1
ENV OMP_NUM_THREADS=1

# 跳过系统包安装,避免内存不足错误
# 使用 Python 的 httpx 进行健康检查

# 复制 requirements.txt
COPY requirements.txt .

# 安装 Python 依赖（进度条已通过环境变量禁用）
# 使用远程 API 模式，无需安装 PyTorch 和本地模型依赖
RUN --mount=type=cache,target=/root/.cache/pip \
    pip install -r requirements.txt

# 复制项目文件
COPY . .

# 创建数据目录
RUN mkdir -p /app/data

# 暴露端口
EXPOSE 9080

# 健康检查 (使用 Python,无需 curl)
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD python -c "import httpx; exit(0 if httpx.get('http://localhost:9080/health', timeout=5).status_code == 200 else 1)"

# 启动命令
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "9080"]
