package com.ynet.iplatform.module.infra.framework.file.config;

import com.ynet.iplatform.module.infra.framework.file.core.client.FileClientFactory;
import com.ynet.iplatform.module.infra.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件配置类
 *
 * @author 易诚源码
 */
@Configuration(proxyBeanMethods = false)
public class IplatformFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
