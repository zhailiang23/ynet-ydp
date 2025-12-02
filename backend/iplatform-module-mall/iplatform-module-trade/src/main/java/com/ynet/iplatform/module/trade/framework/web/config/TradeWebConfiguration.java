package com.ynet.iplatform.module.trade.framework.web.config;

import com.ynet.iplatform.framework.swagger.config.IplatformSwaggerAutoConfiguration;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * trade 模块的 web 组件的 Configuration
 *
 * @author 易诚源码
 */
@Configuration(proxyBeanMethods = false)
public class TradeWebConfiguration {

    /**
     * trade 模块的 API 分组
     */
    @Bean
    public GroupedOpenApi tradeGroupedOpenApi() {
        return IplatformSwaggerAutoConfiguration.buildGroupedOpenApi("trade");
    }

}
