package com.zjw.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author 朱俊伟
 * @since 2023/11/22 15:40
 */
@Configuration
public class GatewayConfig {

    /**
     * rewritePath路径替换
     */
    @Bean
    public RouteLocator rewritePathRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("stripPrefixRoute", r -> r.path("/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .rewritePath("/a", "/info"))
                        .uri("http://localhost:8080"))
                .build();
    }

    /**
     * stripPrefix去掉路径前缀
     */
//    @Bean
    public RouteLocator stripPrefixRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("stripPrefixRoute", r -> r.path("/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .stripPrefix(2))
                        .uri("http://localhost:8080"))
                .build();
    }

    /**
     * prefixPath增加路径前缀
     */
//    @Bean
    public RouteLocator prefixpathRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("prefixpathRoute", r -> r.path("/**")
                        .filters(gatewayFilterSpec -> gatewayFilterSpec
                                .prefixPath("/info"))
                        .uri("http://localhost:8080"))
                .build();
    }

    /**
     * addResponseHeader增加响应头
     */
//    @Bean
    public RouteLocator circuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("circuitbreaker_route",
                        r -> r.path("/**")
                                .filters(fs -> fs
                                        .circuitBreaker(config -> {
                                            config.setName("CircuitBreaker");
                                            config.setFallbackUri("forward:/fb");
                                        }))
                                .uri("http://localhost:8080"))
                .build();
    }

    /**
     * addResponseHeader增加响应头
     */
    @Bean
    public RouteLocator addResponseHeader(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_response_header_route",
                        r -> r.path("/**")
                                .filters(fs -> fs
                                        .addResponseHeader("color", "red"))
                                .uri("http://localhost:8080"))
                .build();
    }

    /**
     * addRequestParameter
     */
//    @Bean
    public RouteLocator addRequestParameter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_parameter_route",
                        r -> r.path("/**")
                                .filters(fs -> fs
                                        .addRequestParameter("color", "red"))
                                .uri("http://localhost:8080"))
                .build();
    }

    /**
     * addRequestHeader增加请求头
     */
//    @Bean
    public RouteLocator addRequestHeadersIfNotPresent(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_header_route",
                        r -> r.path("/**")
                                .filters(fs -> fs
                                        .addRequestHeadersIfNotPresent("X-Request-Color:blue"))
                                .uri("http://localhost:8080"))
                .build();
    }

    /**
     * addRequestHeader增加请求头
     */
//    @Bean
    public RouteLocator addRequestHeader(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("add_request_header_route",
                        r -> r.path("/**")
                                .filters(fs -> fs
                                        .addRequestHeader("X-Request-Color", "blue")
                                        .addRequestHeader("X-Request-Color", "red"))
                                .uri("http://localhost:8080"))
                .build();
    }


    /**
     * weight路由
     */
//    @Bean
    public RouteLocator weightRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //配置了一个不存在的，但是它不调用
                .route("weight_route1", r -> r.weight("g1", 5).uri("http://localhost:8081"))
                .route("weight_route2", r -> r.weight("g1", 5).uri("http://localhost:8080"))
                .build();
    }

    /**
     * 查询路由断言
     */
//    @Bean
    public RouteLocator queryRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //查询路由断言
//                .route("method_route",r -> r.query("red").uri("http://localhost:8081"))
                .route("method_route", r -> r.query("color", "blue|green|red").uri("http://localhost:8081"))
                .build();
    }


    /**
     * 主机路由断言
     */
//    @Bean
    public RouteLocator methodRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //指定请求方式
                .route("method_route", r -> r.method(HttpMethod.GET, HttpMethod.POST).uri("http://localhost:8081"))
                .build();
    }


    /**
     * 主机路由断言
     */
//    @Bean
    public RouteLocator hostRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                //指定域名
                .route("host_route", r -> r.host("aaa.com:9000", "localhost:9000").uri("http://localhost:8081"))
                .build();
    }

    //    @Bean
    public RouteLocator headerRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                //header X-Request-Id是数字
                .route("header_route", r -> r.header("X-Request-Id", "\\d+").uri("http://localhost:8081"))
                .build();
    }

    /**
     * 定义路由规则
     *
     * @param builder 路由构建器
     * @return 路由规则
     */
//    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("bd_route", r -> r.path("/bd").uri("https://baidu.com"))
                .route("tb_route", r -> r.path("/tb").uri("https://taobao.com"))
                .route("jd_route", r -> r.path("/jd").uri("https://jd.com"))
                .build();
    }

    /**
     * 定义路由规则
     *
     * @param builder 路由构建器
     * @return 路由规则
     */
//    @Bean
    public RouteLocator afterRouteLocator(RouteLocatorBuilder builder) {

        ZonedDateTime zonedDateTime = LocalDateTime.now().minusDays(3L) //当前时间减3天
                .atZone(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime2 = LocalDateTime.now().plusDays(5L) //当前时间加5天
                .atZone(ZoneId.systemDefault());
        return builder.routes()
                .route("after_route", r -> r.after(zonedDateTime).uri("http://localhost:8081"))
//                .route("before_route", r -> r.before(zonedDateTime2).uri("http://localhost:8081"))
//                .route("between_route", r -> r.between(zonedDateTime,zonedDateTime2).uri("http://localhost:8081"))
                .build();
    }

    //    @Bean
    public RouteLocator cookieRouteLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("cookie_route", r -> r.cookie("city", "shanghai").uri("http://localhost:8081"))
                .build();
    }


}
