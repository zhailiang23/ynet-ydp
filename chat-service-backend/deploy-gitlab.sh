#!/bin/bash
################################################################################
# chat-service-backend GitLab CI/CD 零停机部署脚本
#
# 用途: 在 192.168.153.111 服务器上执行零停机部署
#
# 使用方法:
#   bash deploy-gitlab.sh <IMAGE_WITH_TAG> <HARBOR_URL> <HARBOR_USER> <HARBOR_PASSWORD> <APP_ENV>
#
# 示例:
#   bash deploy-gitlab.sh 192.168.152.56/ai-digital-avatar/chat-service-backend:123 192.168.152.56 search Search123 fat
#
# 部署流程:
#   1. 登录 Harbor 仓库
#   2. 拉取新镜像
#   3. 启动新容器（使用不同名称）
#   4. 健康检查
#   5. 停止旧容器并切换
#   6. 清理备份容器
#   7. 清理旧镜像
################################################################################

set -e  # 遇到错误立即退出

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 日志函数
log_info() {
    echo -e "${GREEN}[INFO]${NC} $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

log_warning() {
    echo -e "${YELLOW}[WARN]${NC} $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

log_step() {
    echo -e "${BLUE}[STEP]${NC} $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# 参数检查
if [ $# -lt 5 ]; then
    log_error "参数不足！"
    echo "使用方法: bash deploy-gitlab.sh <IMAGE_WITH_TAG> <HARBOR_URL> <HARBOR_USER> <HARBOR_PASSWORD> <APP_ENV>"
    echo "示例: bash deploy-gitlab.sh 192.168.152.56/ai-digital-avatar/chat-service-backend:123 192.168.152.56 search Search123 fat"
    exit 1
fi

# 部署配置
IMAGE_WITH_TAG="$1"
HARBOR_URL="$2"
HARBOR_USER="$3"
HARBOR_PASSWORD="$4"
APP_ENV="$5"

CONTAINER_NAME="chat-service-backend"
CONTAINER_PORT="8080"
HEALTH_CHECK_PORT="8080"
DEPLOY_DIR="/root/zhailiang/chat-service-backend"
CONFIG_DIR="/root/zhailiang/configs"
LOG_DIR="/root/zhailiang/logs/chat-service-backend"

# 健康检查配置
MAX_HEALTH_CHECK_ATTEMPTS=30
HEALTH_CHECK_INTERVAL=5

################################################################################
# 函数定义
################################################################################

# 健康检查函数
check_container_health() {
    local container_name=$1
    local port=$2
    local max_attempts=$3
    local interval=$4

    log_info "开始健康检查容器: $container_name (端口 $port)"

    for ((i=1; i<=max_attempts; i++)); do
        # 检查容器是否在运行
        if ! docker ps | grep -q "$container_name"; then
            log_error "容器 $container_name 未在运行"
            return 1
        fi

        # 健康检查（chat-service-backend 返回 "hello word"）
        if curl -f -s http://localhost:$port/ | grep -q "hello"; then
            log_info "✓ 健康检查通过 (尝试 $i/$max_attempts)"
            return 0
        fi

        log_warning "健康检查失败 (尝试 $i/$max_attempts)，等待 ${interval}s 后重试..."
        sleep $interval
    done

    log_error "✗ 健康检查失败，已尝试 $max_attempts 次"
    return 1
}

# 回滚函数
rollback() {
    local new_container=$1
    local old_container=$2

    log_warning "开始回滚部署..."

    # 停止新容器
    if docker ps -a | grep -q "$new_container"; then
        log_info "停止并删除新容器: $new_container"
        docker stop "$new_container" || true
        docker rm "$new_container" || true
    fi

    # 启动旧容器（如果存在）
    if docker ps -a | grep -q "$old_container"; then
        log_info "重新启动旧容器: $old_container"
        docker start "$old_container" || {
            log_error "无法启动旧容器，服务可能已中断！"
            exit 1
        }
        log_info "✓ 回滚完成，旧容器已恢复运行"
    else
        log_error "旧容器不存在，无法回滚！"
        exit 1
    fi
}

# 清理旧镜像函数
cleanup_old_images() {
    local image_base=$1
    local keep_count=5

    log_info "清理旧镜像（保留最新 $keep_count 个）..."

    # 获取所有镜像（按创建时间排序）
    local images=$(docker images "$image_base" --format "{{.ID}} {{.CreatedAt}}" | sort -rk2 | awk '{print $1}')
    local image_count=$(echo "$images" | grep -v "^$" | wc -l)

    if [ "$image_count" -le "$keep_count" ]; then
        log_info "当前只有 $image_count 个镜像，无需清理"
        return 0
    fi

    # 删除旧镜像
    echo "$images" | grep -v "^$" | tail -n +$((keep_count + 1)) | while read image_id; do
        log_info "删除旧镜像: $image_id"
        docker rmi "$image_id" || true
    done

    log_info "✓ 镜像清理完成"
}

################################################################################
# 主流程
################################################################################

log_info "=========================================="
log_info "开始部署 chat-service-backend"
log_info "=========================================="
log_info "镜像: $IMAGE_WITH_TAG"
log_info "环境: $APP_ENV"
log_info "容器名称: $CONTAINER_NAME"
log_info "端口: $CONTAINER_PORT"
log_info "=========================================="

# 1. 创建必要的目录
log_step "步骤 1: 创建必要的目录"
mkdir -p "$DEPLOY_DIR"
mkdir -p "$CONFIG_DIR"
mkdir -p "$LOG_DIR"

# 如果环境配置文件不存在，创建默认配置（将在服务器上手动配置）
if [ ! -f "$CONFIG_DIR/chat-service-backend-${APP_ENV}.env" ]; then
    log_warning "环境变量文件不存在: $CONFIG_DIR/chat-service-backend-${APP_ENV}.env"
    log_warning "将创建默认环境变量文件，请根据实际情况修改"

    cat > "$CONFIG_DIR/chat-service-backend-${APP_ENV}.env" <<EOF
# chat-service-backend 环境变量配置 (${APP_ENV})
APP_ENV=${APP_ENV}
TZ=Asia/Shanghai
EOF
fi

# 2. 登录 Harbor
log_step "步骤 2: 登录 Harbor 仓库"
echo "$HARBOR_PASSWORD" | docker login "$HARBOR_URL" -u "$HARBOR_USER" --password-stdin || {
    log_error "登录 Harbor 失败"
    exit 1
}

# 3. 拉取新镜像
log_step "步骤 3: 拉取最新镜像"
docker pull "$IMAGE_WITH_TAG" || {
    log_error "拉取镜像失败"
    docker logout "$HARBOR_URL"
    exit 1
}

# 4. 停止并重命名旧容器（如果存在）
NEW_CONTAINER_NAME="${CONTAINER_NAME}_new"
BACKUP_CONTAINER_NAME="${CONTAINER_NAME}_backup"

if docker ps | grep -q "$CONTAINER_NAME\$"; then
    log_step "步骤 4: 备份当前运行的容器"

    # 先停止旧容器
    docker stop "$CONTAINER_NAME" || true

    # 重命名为 backup
    docker rename "$CONTAINER_NAME" "$BACKUP_CONTAINER_NAME" || true

    log_info "✓ 旧容器已备份为: $BACKUP_CONTAINER_NAME"
else
    log_step "步骤 4: 未发现运行中的容器，跳过备份"
fi

# 5. 启动新容器
log_step "步骤 5: 启动新容器"
docker run -d \
    --name "$NEW_CONTAINER_NAME" \
    -p "$CONTAINER_PORT:8080" \
    --env-file "$CONFIG_DIR/chat-service-backend-${APP_ENV}.env" \
    -v "$LOG_DIR:/app/storage/log" \
    --restart unless-stopped \
    "$IMAGE_WITH_TAG" || {
        log_error "启动新容器失败"
        rollback "$NEW_CONTAINER_NAME" "$BACKUP_CONTAINER_NAME"
        exit 1
    }

log_info "✓ 新容器已启动: $NEW_CONTAINER_NAME"

# 6. 等待容器启动
log_step "步骤 6: 等待容器初始化（10秒）"
sleep 10

# 7. 健康检查
log_step "步骤 7: 执行健康检查"
if ! check_container_health "$NEW_CONTAINER_NAME" "$HEALTH_CHECK_PORT" "$MAX_HEALTH_CHECK_ATTEMPTS" "$HEALTH_CHECK_INTERVAL"; then
    log_error "健康检查失败，开始回滚"

    # 查看容器日志以便调试
    log_info "查看容器最后 50 行日志:"
    docker logs --tail 50 "$NEW_CONTAINER_NAME" || true

    rollback "$NEW_CONTAINER_NAME" "$BACKUP_CONTAINER_NAME"
    exit 1
fi

# 8. 切换容器名称
log_step "步骤 8: 切换容器名称"
docker rename "$NEW_CONTAINER_NAME" "$CONTAINER_NAME" || {
    log_error "重命名容器失败"
    exit 1
}

# 9. 清理备份容器
if docker ps -a | grep -q "$BACKUP_CONTAINER_NAME"; then
    log_step "步骤 9: 清理备份容器"
    docker rm "$BACKUP_CONTAINER_NAME" || true
fi

# 10. 清理旧镜像
log_step "步骤 10: 清理旧镜像"
IMAGE_BASE=$(echo "$IMAGE_WITH_TAG" | cut -d':' -f1)
cleanup_old_images "$IMAGE_BASE"

# 11. 登出 Harbor
log_step "步骤 11: 登出 Harbor"
docker logout "$HARBOR_URL" || true

# 完成
log_info "=========================================="
log_info "✓ 部署完成！"
log_info "=========================================="
log_info "容器名称: $CONTAINER_NAME"
log_info "环境: $APP_ENV"
log_info "访问地址: http://$(hostname -I | awk '{print $1}'):$CONTAINER_PORT"
log_info "日志目录: $LOG_DIR"
log_info "配置文件: manifest/config/config.${APP_ENV}.yaml"
log_info "=========================================="
log_info "验证部署:"
echo "  curl http://localhost:$CONTAINER_PORT/"
echo "  docker logs -f $CONTAINER_NAME"
log_info "=========================================="

exit 0
