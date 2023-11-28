package com.zjw.factory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * 自定义过滤器工厂
 *
 * @author 朱俊伟
 * @since 2023/11/28 21:39
 */
@Component
public class AddHeaderGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    /**
     * 修改请求头，使用方式：
     * 在配置中添加
     * filters:
     *  - AddHeader=new-color, red
     */
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            ServerHttpRequest httpRequest = exchange.getRequest()
                                                    .mutate()
                                                    .header(config.getName(), config.getValue())
                                                    .build();
            ServerWebExchange webExchange = exchange.mutate()
                    .request(httpRequest)
                    .build();

           return chain.filter(webExchange);
        };
    }
}
