package com.zjw.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author 朱俊伟
 * @since 2023/11/28 20:27
 */
@Configuration
public class SpringCloudConfig {

    /**
     * ip限流
     */
    @Bean
    public KeyResolver ipKeyResolver(){
        return exchange -> {
            String hostName = exchange.getRequest().getRemoteAddress().getHostName();
            System.out.println("hostName = " + hostName);
            return Mono.just(hostName);
        };
    }
}
