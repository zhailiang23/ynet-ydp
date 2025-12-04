FROM alpine:3.18

###############################################################################
#                                INSTALLATION
###############################################################################

# 环境变量：支持 dev, fat, uat, pro
ARG ENV=dev
ENV APP_ENV=${ENV}
ENV WORKDIR=/app

# 使用阿里云镜像源加速包安装
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories && \
    apk update && \
    apk add --no-cache ca-certificates tzdata && \
    rm -rf /var/cache/apk/*

# 设置时区为上海
ENV TZ=Asia/Shanghai

ADD resource                $WORKDIR/
ADD ./temp/linux_amd64/main $WORKDIR/main
RUN chmod +x $WORKDIR/main

###############################################################################
#                                   START
###############################################################################
WORKDIR $WORKDIR

# 根据 APP_ENV 环境变量选择配置文件启动
# 默认使用 dev 环境配置
CMD ./main -c=manifest/config/config.${APP_ENV}.yaml
