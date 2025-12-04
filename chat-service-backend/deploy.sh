#!/bin/bash

###############################################################################
# chat-service-backend 多环境部署脚本
# 用法: ./deploy.sh [环境] [标签]
# 示例:
#   ./deploy.sh dev        # 部署开发环境
#   ./deploy.sh pro v1.0.0 # 部署生产环境，指定版本
###############################################################################

set -e

# 颜色输出
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# 日志函数
log_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

log_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

log_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

log_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# 默认参数
ENV=${1:-dev}
TAG=${2:-latest}
DOCKER_NAME="chat-service"
CONTAINER_NAME="chat-service-${ENV}"

# 验证环境参数
validate_env() {
    case $ENV in
        dev|fat|uat|pro)
            log_info "部署环境: ${ENV}"
            ;;
        *)
            log_error "无效的环境: ${ENV}"
            log_info "支持的环境: dev, fat, uat, pro"
            exit 1
            ;;
    esac
}

# 检查配置文件
check_config() {
    local config_file="manifest/config/config.${ENV}.yaml"
    if [ ! -f "$config_file" ]; then
        log_error "配置文件不存在: $config_file"
        exit 1
    fi
    log_success "配置文件检查通过: $config_file"
}

# 构建 Docker 镜像
build_image() {
    log_info "开始构建 Docker 镜像..."
    log_info "环境: ${ENV}"
    log_info "标签: ${TAG}"

    # 使用 Makefile 构建
    if [ -f "Makefile" ]; then
        make image ENV=${ENV} TAG=${TAG}
        log_success "Docker 镜像构建完成: ${DOCKER_NAME}:${TAG}"
    else
        log_error "Makefile 不存在"
        exit 1
    fi
}

# 停止旧容器
stop_old_container() {
    if docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"; then
        log_warning "停止旧容器: ${CONTAINER_NAME}"
        docker stop ${CONTAINER_NAME} || true
        docker rm ${CONTAINER_NAME} || true
        log_success "旧容器已删除"
    else
        log_info "没有运行中的旧容器"
    fi
}

# 启动新容器
start_container() {
    log_info "启动新容器: ${CONTAINER_NAME}"

    docker run -d \
        --name ${CONTAINER_NAME} \
        -p 8080:8080 \
        -e APP_ENV=${ENV} \
        --restart unless-stopped \
        -v $(pwd)/storage:/app/storage \
        ${DOCKER_NAME}:${TAG}

    log_success "容器启动成功: ${CONTAINER_NAME}"
}

# 健康检查
health_check() {
    log_info "等待服务启动..."
    sleep 5

    local max_retries=10
    local retry=0

    while [ $retry -lt $max_retries ]; do
        if curl -f -s http://localhost:8080/ > /dev/null 2>&1; then
            log_success "健康检查通过！服务正常运行"
            return 0
        fi

        retry=$((retry+1))
        log_warning "健康检查失败，重试 ${retry}/${max_retries}..."
        sleep 3
    done

    log_error "健康检查失败，服务未能正常启动"
    log_info "查看容器日志:"
    docker logs --tail 50 ${CONTAINER_NAME}
    exit 1
}

# 显示部署信息
show_info() {
    echo ""
    log_success "========================================="
    log_success "部署完成！"
    log_success "========================================="
    echo ""
    log_info "环境: ${ENV}"
    log_info "容器名称: ${CONTAINER_NAME}"
    log_info "镜像标签: ${DOCKER_NAME}:${TAG}"
    log_info "服务地址: http://localhost:8080"
    echo ""
    log_info "常用命令:"
    echo "  查看日志: docker logs -f ${CONTAINER_NAME}"
    echo "  停止服务: docker stop ${CONTAINER_NAME}"
    echo "  重启服务: docker restart ${CONTAINER_NAME}"
    echo "  进入容器: docker exec -it ${CONTAINER_NAME} sh"
    echo ""
}

# 主流程
main() {
    log_info "开始部署 chat-service-backend"

    # 1. 验证环境
    validate_env

    # 2. 检查配置文件
    check_config

    # 3. 构建 Docker 镜像
    build_image

    # 4. 停止旧容器
    stop_old_container

    # 5. 启动新容器
    start_container

    # 6. 健康检查
    health_check

    # 7. 显示部署信息
    show_info
}

# 脚本使用说明
usage() {
    echo "用法: $0 [环境] [标签]"
    echo ""
    echo "参数:"
    echo "  环境   - 部署环境 (dev|fat|uat|pro)，默认: dev"
    echo "  标签   - Docker 镜像标签，默认: latest"
    echo ""
    echo "示例:"
    echo "  $0 dev              # 部署开发环境，使用 latest 标签"
    echo "  $0 fat v1.0.0       # 部署 FAT 环境，使用 v1.0.0 标签"
    echo "  $0 pro v1.0.0       # 部署生产环境，使用 v1.0.0 标签"
    echo ""
}

# 检查是否请求帮助
if [ "$1" = "-h" ] || [ "$1" = "--help" ]; then
    usage
    exit 0
fi

# 执行主流程
main
