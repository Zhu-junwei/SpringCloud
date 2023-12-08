package com.zjw.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 朱俊伟
 */
@Configuration
public class SpringConfig {

    @LoadBalanced   //以负载均衡的方法调用
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
