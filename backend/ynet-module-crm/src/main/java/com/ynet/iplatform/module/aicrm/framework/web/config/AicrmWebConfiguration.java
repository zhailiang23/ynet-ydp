package com.ynet.iplatform.module.aicrm.framework.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * AICRM 模块的 Web 配置类
 *
 * @author 易诚源码
 */
@Configuration
public class AicrmWebConfiguration {

    /**
     * 配置 RestTemplate，用于调用外部 HTTP 服务
     */
    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 连接超时 5 秒
        factory.setReadTimeout(10000);   // 读取超时 10 秒
        return new RestTemplate(factory);
    }

}
