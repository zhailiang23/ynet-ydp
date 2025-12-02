#!/bin/bash
################################################################################
# 部署服务器初始化脚本
#
# 用途: 在 192.168.153.111 服务器上快速配置部署环境
#
# 使用方法:
#   1. 将此脚本上传到服务器
#   2. chmod +x setup-server.sh
#   3. ./setup-server.sh
################################################################################

set -e

GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m'

echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}frontend-practice 部署环境初始化${NC}"
echo -e "${GREEN}========================================${NC}"

# 1. 创建目录
echo -e "${YELLOW}步骤 1: 创建必要的目录${NC}"
mkdir -p /root/zhailiang/frontend-practice
mkdir -p /root/zhailiang/logs/frontend-practice
mkdir -p /root/zhailiang/configs

# 2. 创建环境变量文件
ENV_FILE="/root/zhailiang/configs/frontend-practice.env"

if [ -f "$ENV_FILE" ]; then
    echo -e "${YELLOW}环境变量文件已存在: $ENV_FILE${NC}"
    echo -e "${YELLOW}是否覆盖? (y/N)${NC}"
    read -r response
    if [[ ! "$response" =~ ^([yY][eE][sS]|[yY])$ ]]; then
        echo "跳过创建环境变量文件"
    else
        create_env_file=true
    fi
else
    create_env_file=true
fi

if [ "$create_env_file" = true ]; then
    echo -e "${YELLOW}步骤 2: 创建环境变量文件${NC}"
    cat > "$ENV_FILE" <<'EOF'
# frontend-practice 生产环境配置
NODE_ENV=production

# 后端 API 地址（请根据实际情况修改）
NEXT_PUBLIC_API_BASE_URL=http://192.168.153.111:48080/admin-api
NEXT_PUBLIC_TENANT_ID=1

# 时区
TZ=Asia/Shanghai
EOF
    chmod 600 "$ENV_FILE"
    echo -e "${GREEN}✓ 环境变量文件已创建: $ENV_FILE${NC}"
fi

# 3. 检查 Docker
echo -e "${YELLOW}步骤 3: 检查 Docker 安装${NC}"
if command -v docker &> /dev/null; then
    echo -e "${GREEN}✓ Docker 已安装 ($(docker --version))${NC}"
else
    echo -e "${YELLOW}Docker 未安装，正在安装...${NC}"
    curl -fsSL https://get.docker.com | bash -s docker
    systemctl start docker
    systemctl enable docker
    echo -e "${GREEN}✓ Docker 安装完成${NC}"
fi

# 4. 检查 curl
echo -e "${YELLOW}步骤 4: 检查 curl 安装${NC}"
if command -v curl &> /dev/null; then
    echo -e "${GREEN}✓ curl 已安装${NC}"
else
    echo -e "${YELLOW}curl 未安装，正在安装...${NC}"
    yum install -y curl || apt-get install -y curl
    echo -e "${GREEN}✓ curl 安装完成${NC}"
fi

# 5. 测试 Harbor 连接
echo -e "${YELLOW}步骤 5: 测试 Harbor 连接${NC}"
echo "Search123" | docker login 192.168.152.56 -u search --password-stdin
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Harbor 登录成功${NC}"
    docker logout 192.168.152.56
else
    echo -e "${YELLOW}⚠ Harbor 登录失败，请检查网络和凭据${NC}"
fi

# 6. 显示配置信息
echo -e "${GREEN}========================================${NC}"
echo -e "${GREEN}✓ 初始化完成！${NC}"
echo -e "${GREEN}========================================${NC}"
echo ""
echo "部署目录: /root/zhailiang/frontend-practice"
echo "日志目录: /root/zhailiang/logs/frontend-practice"
echo "配置文件: /root/zhailiang/configs/frontend-practice.env"
echo ""
echo -e "${YELLOW}下一步:${NC}"
echo "1. 编辑环境变量文件（如果需要）:"
echo "   vi /root/zhailiang/configs/frontend-practice.env"
echo ""
echo "2. 上传 deploy.sh 脚本到部署目录:"
echo "   scp deploy.sh root@192.168.153.111:/root/zhailiang/frontend-practice/"
echo ""
echo "3. 赋予执行权限:"
echo "   chmod +x /root/zhailiang/frontend-practice/deploy.sh"
echo ""
echo "4. 在 Jenkins 中配置流水线任务"
echo ""

exit 0
