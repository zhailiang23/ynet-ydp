package com.ynet.iplatform.module.aicrm.framework.security.config;

import com.ynet.iplatform.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * CRM 模块的 Security 配置
 *
 * @author 易诚源码
 */
@Configuration(proxyBeanMethods = false, value = "aicrmSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("aicrmAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 客户认领流程回调接口 - 无需身份验证
                // BPM 流程引擎会在流程结束时自动调用此接口
                registry.requestMatchers(buildAdminApi("/aicrm/customer-claim/callback/**")).permitAll();

                // 移动端 API - 允许匿名访问
                registry.requestMatchers(buildAppApi("/aicrm/potential-customer/**")).permitAll();
                registry.requestMatchers(buildAppApi("/aicrm/task/**")).permitAll();
                registry.requestMatchers(buildAppApi("/aicrm/financial-product/**")).permitAll();
                registry.requestMatchers(buildAppApi("/aicrm/customer/**")).permitAll();
            }

        };
    }

}
