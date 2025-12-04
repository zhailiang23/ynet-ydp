FROM loads/alpine:3.8

###############################################################################
#                                INSTALLATION
###############################################################################

# 环境变量：支持 dev, fat, uat, pro
ARG ENV=dev
ENV APP_ENV=${ENV}
ENV WORKDIR=/app

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
