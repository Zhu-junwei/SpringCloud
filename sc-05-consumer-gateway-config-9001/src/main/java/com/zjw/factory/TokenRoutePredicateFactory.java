package com.zjw.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 朱俊伟
 * @since 2023/11/28 12:09
 */
@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<TokenRoutePredicateFactory.Config> {

    public static final String TOKEN = "token";

    public TokenRoutePredicateFactory() {
        super(TokenRoutePredicateFactory.Config.class);
    }


    /**
     * 认证校验逻辑
     */
    @Override
    public Predicate<ServerWebExchange> apply(TokenRoutePredicateFactory.Config config) {
        return serverWebExchange -> {
            // 获取到请求中的所有参数
            MultiValueMap<String, String> queryParams = serverWebExchange.getRequest().getQueryParams();
            List<String> tokenList = queryParams.get("token");
            return tokenList.contains(config.getToken());
        };
    }

    @Getter
    @Setter
    public static class Config {
        private String token;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(TOKEN);
    }
}
