package cn.iocoder.yudao.module.aicrm.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

/**
 * Practice 智能练习模块的 Security 配置
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false, value = "practiceSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("practiceAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // 课程相关的 APP API - 允许匿名访问（学生端）
                registry.requestMatchers(buildAppApi("/aicrm/practice-course/**")).permitAll();
            }

        };
    }

}
