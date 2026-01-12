pipeline {
    agent any

    environment {
        // Harbor 仓库配置
        HARBOR_URL = '192.168.152.56'
        HARBOR_PROJECT = 'ynet-ydp'
        IMAGE_NAME = "${HARBOR_URL}/${HARBOR_PROJECT}/frontend"
        IMAGE_TAG = "${BUILD_NUMBER}"

        // Jenkins 凭据 ID
        HARBOR_CREDENTIALS = credentials('harbor-registry')
    }

    stages {
        stage('检出代码') {
            steps {
                checkout scm
                script {
                    echo "当前分支: ${env.BRANCH_NAME}"
                    echo "提交 ID: ${env.GIT_COMMIT}"
                }
            }
        }

        stage('安装依赖') {
            steps {
                script {
                    sh '''
                        cd frontend
                        pnpm install --frozen-lockfile
                        echo "依赖安装完成"
                    '''
                }
            }
        }

        stage('构建前端') {
            steps {
                script {
                    sh '''
                        cd frontend
                        pnpm build:antd
                        echo "前端构建完成"
                    '''
                }
            }
        }

        stage('构建镜像') {
            steps {
                script {
                    sh """
                        cd frontend
                        docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest .
                        echo "镜像构建完成: ${IMAGE_NAME}:${IMAGE_TAG}"
                    """
                }
            }
        }

        stage('推送镜像') {
            steps {
                script {
                    sh """
                        echo '${HARBOR_CREDENTIALS_PSW}' | docker login ${HARBOR_URL} -u ${HARBOR_CREDENTIALS_USR} --password-stdin
                        docker push ${IMAGE_NAME}:${IMAGE_TAG}
                        docker push ${IMAGE_NAME}:latest
                        echo "镜像推送完成"
                        docker logout ${HARBOR_URL}
                    """
                }
            }
        }

        stage('清理本地镜像') {
            steps {
                script {
                    sh """
                        # 保留最新的 3 个镜像标签
                        docker images ${IMAGE_NAME} --format "{{.Tag}}" | grep -E '^[0-9]+\$' | sort -nr | tail -n +4 | xargs -I {} docker rmi ${IMAGE_NAME}:{} || true
                        echo "本地镜像清理完成"
                    """
                }
            }
        }
    }

    post {
        success {
            echo "✓ Frontend 镜像构建并推送成功"
            echo "镜像: ${IMAGE_NAME}:${IMAGE_TAG}"
        }
        failure {
            echo "✗ Frontend 构建失败"
        }
        always {
            cleanWs()
        }
    }
}
