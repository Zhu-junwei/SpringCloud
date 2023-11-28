package com.zjw.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 验证URL中是否有token
 * @author 朱俊伟
 * @since 2023/11/28 23:16
 */
@Component
public class URLValidateFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从请求中获取请求参数token的值
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        // 判断token是否为空，为空则拒绝访问，否则放行。
        if(!StringUtils.hasText(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange); // 放行，继续执行下一个过滤器或者路由处理器，也就是请求的目标方法。
    }

    /**
     * 指定定顺序，数字越小越先执行
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
