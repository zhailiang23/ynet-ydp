package com.ynet.iplatform.module.mp.framework.web.config;

import com.ynet.iplatform.framework.swagger.config.IplatformSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mp 模块的 web 组件的 Configuration
 *
 * @author 易诚源码
 */
@Configuration(proxyBeanMethods = false)
public class MpWebConfiguration {

    /**
     * mp 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi mpGroupedOpenApi() {
        return IplatformSwaggerAutoConfiguration.buildGroupedOpenApi("mp");
    }

}
