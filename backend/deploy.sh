#!/bin/bash
################################################################################
# iplatform-server (backend) 零停机部署脚本
#
# 用途: 在 192.168.153.111 服务器上执行零停机部署
#
# 使用方法:
#   bash deploy.sh <IMAGE_WITH_TAG> <HARBOR_URL> <HARBOR_USER> <HARBOR_PASSWORD> <SPRING_PROFILE>
#
# 示例:
#   bash deploy.sh 192.168.152.56/sysadmin-2025/iplatform-server:123 192.168.152.56 search Search123 fat
#
# 部署流程:
#   1. 登录 Harbor 仓库
#   2. 拉取新镜像
#   3. 启动新容器（使用不同端口）
#   4. 健康检查（检查 Spring Boot Actuator）
#   5. 切换流量（直接替换）
#   6. 停止旧容器
#   7. 清理旧镜像
################################################################################

set -e  # 遇到错误立即退出

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
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

# 参数检查
if [ $# -lt 5 ]; then
    log_error "参数不足！"
    echo "使用方法: bash deploy.sh <IMAGE_WITH_TAG> <HARBOR_URL> <HARBOR_USER> <HARBOR_PASSWORD> <SPRING_PROFILE>"
    echo "示例: bash deploy.sh 192.168.152.56/sysadmin-2025/iplatform-server:123 192.168.152.56 search Search123 fat"
    exit 1
fi

# 部署配置
IMAGE_WITH_TAG="$1"
HARBOR_URL="$2"
HARBOR_USER="$3"
HARBOR_PASSWORD="$4"
SPRING_PROFILE="$5"  # local, dev, fat, uat, pro

CONTAINER_NAME="iplatform-server"
CONTAINER_PORT="48080"
HEALTH_CHECK_PORT="48080"
DEPLOY_DIR="/root/zhailiang/iplatform-server"
LOG_DIR="/root/zhailiang/logs/iplatform-server"
CONFIG_DIR="/root/zhailiang/configs"

# 健康检查配置（Spring Boot 启动较慢，需要更多时间）
MAX_HEALTH_CHECK_ATTEMPTS=60  # 最多尝试 60 次
HEALTH_CHECK_INTERVAL=5       # 每次间隔 5 秒（总共 5 分钟）

################################################################################
# 函数定义
################################################################################

# 健康检查函数（针对 Spring Boot Actuator）
check_container_health() {
    local container_name=$1
    local port=$2
    local max_attempts=$3
    local interval=$4

    log_info "开始健康检查容器: $container_name (端口 $port)"
    log_info "等待 Spring Boot 应用启动..."

    for ((i=1; i<=max_attempts; i++)); do
        # 检查容器是否在运行
        if ! docker ps | grep -q "$container_name"; then
            log_error "容器 $container_name 未在运行"

            # 显示容器日志帮助排查问题
            log_error "容器日志:"
            docker logs --tail 50 "$container_name" 2>&1 || true

            return 1
        fi

        # 健康检查：检查 Spring Boot Actuator health 端点
        # 如果没有启用 Actuator，则检查根路径或 Swagger 文档
        if curl -f -s -o /dev/null http://localhost:$port/actuator/health; then
            log_info "✓ 健康检查通过 - Actuator health 端点正常 (尝试 $i/$max_attempts)"
            return 0
        elif curl -f -s -o /dev/null http://localhost:$port/doc.html; then
            log_info "✓ 健康检查通过 - API 文档端点正常 (尝试 $i/$max_attempts)"
            return 0
        fi

        # 每 10 次尝试显示一次容器日志（用于调试）
        if [ $((i % 10)) -eq 0 ]; then
            log_warning "容器最近日志:"
            docker logs --tail 20 "$container_name" 2>&1 || true
        fi

        log_warning "健康检查失败 (尝试 $i/$max_attempts)，等待 ${interval}s 后重试..."
        sleep $interval
    done

    log_error "✗ 健康检查失败，已尝试 $max_attempts 次（${max_attempts}×${interval}s = $((max_attempts * interval))s）"

    # 显示完整日志
    log_error "完整容器日志:"
    docker logs "$container_name" 2>&1 || true

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
    local keep_count=3

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
log_info "开始部署 iplatform-server (backend)"
log_info "=========================================="
log_info "镜像: $IMAGE_WITH_TAG"
log_info "容器名称: $CONTAINER_NAME"
log_info "端口: $CONTAINER_PORT"
log_info "Spring Profile: $SPRING_PROFILE"
log_info "=========================================="

# 1. 创建必要的目录
log_info "步骤 1: 创建必要的目录"
mkdir -p "$DEPLOY_DIR"
mkdir -p "$CONFIG_DIR"
mkdir -p "$LOG_DIR"

# 2. 登录 Harbor
log_info "步骤 2: 登录 Harbor 仓库"
echo "$HARBOR_PASSWORD" | docker login "$HARBOR_URL" -u "$HARBOR_USER" --password-stdin || {
    log_error "登录 Harbor 失败"
    exit 1
}

# 3. 拉取新镜像
log_info "步骤 3: 拉取最新镜像"
docker pull "$IMAGE_WITH_TAG" || {
    log_error "拉取镜像失败"
    docker logout "$HARBOR_URL"
    exit 1
}

# 4. 停止并重命名旧容器（如果存在）
NEW_CONTAINER_NAME="${CONTAINER_NAME}_new"
BACKUP_CONTAINER_NAME="${CONTAINER_NAME}_backup"

if docker ps | grep -q "$CONTAINER_NAME\$"; then
    log_info "步骤 4: 备份当前运行的容器"

    # 先停止旧容器
    docker stop "$CONTAINER_NAME" || true

    # 重命名为 backup
    docker rename "$CONTAINER_NAME" "$BACKUP_CONTAINER_NAME" || true

    log_info "✓ 旧容器已备份为: $BACKUP_CONTAINER_NAME"
else
    log_info "步骤 4: 未发现运行中的容器，跳过备份"
fi

# 5. 启动新容器
log_info "步骤 5: 启动新容器"
log_info "使用 Spring Profile: $SPRING_PROFILE"

docker run -d \
    --name "$NEW_CONTAINER_NAME" \
    -p "$CONTAINER_PORT:48080" \
    -e SPRING_PROFILES_ACTIVE="$SPRING_PROFILE" \
    -e TZ=Asia/Shanghai \
    -e JAVA_OPTS="-Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom" \
    -v "$LOG_DIR:/iplatform-server/logs" \
    --restart unless-stopped \
    "$IMAGE_WITH_TAG" || {
        log_error "启动新容器失败"
        rollback "$NEW_CONTAINER_NAME" "$BACKUP_CONTAINER_NAME"
        exit 1
    }

log_info "✓ 新容器已启动: $NEW_CONTAINER_NAME"

# 6. 等待容器启动
log_info "步骤 6: 等待 Spring Boot 初始化（30秒）"
sleep 30

# 显示容器状态和日志
log_info "容器状态:"
docker ps | grep "$NEW_CONTAINER_NAME" || true

log_info "容器最近日志:"
docker logs --tail 30 "$NEW_CONTAINER_NAME" 2>&1 || true

# 7. 健康检查
log_info "步骤 7: 执行健康检查（最多 ${MAX_HEALTH_CHECK_ATTEMPTS}×${HEALTH_CHECK_INTERVAL}s = $((MAX_HEALTH_CHECK_ATTEMPTS * HEALTH_CHECK_INTERVAL))s）"
if ! check_container_health "$NEW_CONTAINER_NAME" "$HEALTH_CHECK_PORT" "$MAX_HEALTH_CHECK_ATTEMPTS" "$HEALTH_CHECK_INTERVAL"; then
    log_error "健康检查失败，开始回滚"
    rollback "$NEW_CONTAINER_NAME" "$BACKUP_CONTAINER_NAME"
    exit 1
fi

# 8. 切换容器名称
log_info "步骤 8: 切换容器名称"
docker rename "$NEW_CONTAINER_NAME" "$CONTAINER_NAME" || {
    log_error "重命名容器失败"
    exit 1
}

# 9. 清理备份容器
if docker ps -a | grep -q "$BACKUP_CONTAINER_NAME"; then
    log_info "步骤 9: 清理备份容器"
    docker rm "$BACKUP_CONTAINER_NAME" || true
fi

# 10. 清理旧镜像
log_info "步骤 10: 清理旧镜像"
IMAGE_BASE=$(echo "$IMAGE_WITH_TAG" | cut -d':' -f1)
cleanup_old_images "$IMAGE_BASE"

# 11. 登出 Harbor
log_info "步骤 11: 登出 Harbor"
docker logout "$HARBOR_URL" || true

# 完成
log_info "=========================================="
log_info "✓ 部署完成！"
log_info "=========================================="
log_info "容器名称: $CONTAINER_NAME"
log_info "Spring Profile: $SPRING_PROFILE"
log_info "API 服务: http://$(hostname -I | awk '{print $1}'):$CONTAINER_PORT"
log_info "API 文档: http://$(hostname -I | awk '{print $1}'):$CONTAINER_PORT/doc.html"
log_info "Swagger UI: http://$(hostname -I | awk '{print $1}'):$CONTAINER_PORT/swagger-ui/index.html"
log_info "日志目录: $LOG_DIR"
log_info "=========================================="
log_info "查看容器日志: docker logs -f $CONTAINER_NAME"
log_info "查看容器状态: docker ps | grep $CONTAINER_NAME"
log_info "=========================================="
