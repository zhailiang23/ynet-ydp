package com.ynet.iplatform.module.infra.framework.web.config;

import com.ynet.iplatform.framework.swagger.config.IplatformSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * infra 模块的 web 组件的 Configuration
 *
 * @author 易诚源码
 */
@Configuration(proxyBeanMethods = false)
public class InfraWebConfiguration {

    /**
     * infra 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi infraGroupedOpenApi() {
        return IplatformSwaggerAutoConfiguration.buildGroupedOpenApi("infra");
    }

}
