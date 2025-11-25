package com.ynet.iplatform.module.aicrm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Dify 配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "dify")
public class DifyProperties {

    /**
     * Dify API 基础地址
     * 例如: http://192.168.65.165/v1
     */
    private String apiUrl = "http://192.168.65.165/v1";

    /**
     * Dify API 密钥
     * 例如: app-heWY9luKOhh2jKjCZYV2ZUA4
     */
    private String apiKey;

    /**
     * 请求超时时间(毫秒)
     */
    private Integer timeout = 600000; // 默认10分钟

    /**
     * 是否启用 Dify (如果禁用则使用原 AI 服务)
     */
    private Boolean enabled = true;

    /**
     * 用户标识
     */
    private String user = "backend-system";

    /**
     * Dify 对话应用 API 密钥
     * 例如: app-BpOSZNjIowvZbfZEPj1zkAMs
     */
    private String chatApiKey;

    /**
     * Dify 评估应用 API 密钥
     * 例如: app-y9JmU788xSTyHkXoiQnNcsYs
     */
    private String evaluationApiKey;
}
