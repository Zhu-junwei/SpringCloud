package com.zjw.factory;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 朱俊伟
 * @since 2023/11/28 12:09
 */
@Component
public class AuthRoutePredicateFactory extends AbstractRoutePredicateFactory<AuthRoutePredicateFactory.Config> {

    /**
     * DateTime key.
     */
    public static final String USER_NAME_KEY = "username";
    public static final String PASS_WORD_KEY = "password";

    public AuthRoutePredicateFactory() {
        super(Config.class);
    }


    /**
     * 认证校验逻辑
     */
    @Override
    public Predicate<ServerWebExchange> apply(final Config config) {
        return serverWebExchange -> {
            // 获取到请求中的所有header
            HttpHeaders headers = serverWebExchange.getRequest().getHeaders();
            // 校验header中是否包含用户名和密码，如果包含，返回true，否则返回false
            List<String> pwdList = headers.get(config.getUsername());
            return pwdList != null && pwdList.contains(config.getPassword());
        };
    }

    @Getter
    @Setter
    public static class Config {
        private String username;
        private String password;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(USER_NAME_KEY, PASS_WORD_KEY);
    }
}
