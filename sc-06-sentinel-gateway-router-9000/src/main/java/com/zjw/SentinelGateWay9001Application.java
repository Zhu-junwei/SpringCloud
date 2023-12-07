package com.zjw;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class SentinelGateWay9001Application {

    private static final String API_GROUP1 = "API_group1";

    public static void main(String[] args) {
        SpringApplication.run(SentinelGateWay9001Application.class, args);
        // 初始化阻塞处理器
        initBlockHandler();
        // 初始化网关规则
//        initGatewayRuleRoute();
        initGatewayRuleAPI();
    }

    /**
     * 初始化网关规则 ROUTE
     */
    private static void initGatewayRuleRoute() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        /*
         * 结合文档和sentinel配置页面来写
         * 文档：https://github.com/alibaba/Sentinel/wiki/%E7%BD%91%E5%85%B3%E9%99%90%E6%B5%81
         */
        GatewayFlowRule rule = new GatewayFlowRule();
        // API资源类型 route: RESOURCE_MODE_ROUTE_ID 0, api分组：RESOURCE_MODE_CUSTOM_API_NAME 1
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_ROUTE_ID);
        // 资源名称，可以是网关中的 route 名称或者用户自定义的 API 分组名称
        rule.setResource("get_route");
        // 阈值类型 QPS:FLOW_GRADE_QPS 1 线程数:FLOW_GRADE_THREAD 0
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // QPS阈值
        rule.setCount(3);
        // 统计时间窗口，单位是秒，默认是 1 秒
        rule.setIntervalSec(1);
        // 流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败CONTROL_BEHAVIOR_DEFAULT和匀速排队CONTROL_BEHAVIOR_RATE_LIMITER两种模式，默认是快速失败0。
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 应对突发请求时额外允许的请求数目，默认为int类型 0
        rule.setBurst(0);
        rules.add(rule);

        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 初始化网关规则 API
     */
    private static void initGatewayRuleAPI() {

        //初始化API分组
        initCustomizedApis();

        Set<GatewayFlowRule> rules = new HashSet<>();
        /*
         * 结合文档和sentinel配置页面来写
         * 文档：https://github.com/alibaba/Sentinel/wiki/%E7%BD%91%E5%85%B3%E9%99%90%E6%B5%81
         */
        GatewayFlowRule rule = new GatewayFlowRule();
        // API资源类型 route: RESOURCE_MODE_ROUTE_ID 0, api分组：RESOURCE_MODE_CUSTOM_API_NAME 1
        rule.setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME);
        // 资源名称，可以是网关中的 route 名称或者用户自定义的 API 分组名称
        rule.setResource(API_GROUP1);
        // 阈值类型 QPS:FLOW_GRADE_QPS 1 线程数:FLOW_GRADE_THREAD 0
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // QPS阈值
        rule.setCount(3);
        // 统计时间窗口，单位是秒，默认是 1 秒
        rule.setIntervalSec(1);
        // 流量整形的控制效果，同限流规则的 controlBehavior 字段，目前支持快速失败CONTROL_BEHAVIOR_DEFAULT和匀速排队CONTROL_BEHAVIOR_RATE_LIMITER两种模式，默认是快速失败0。
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        // 应对突发请求时额外允许的请求数目，默认为int类型 0
        rule.setBurst(0);
        rules.add(rule);

        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 初始化API分组
     */
    private static void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition(API_GROUP1)
                //匹配模式 支持精确匹配（PARAM_MATCH_STRATEGY_EXACT）、子串匹配（PARAM_MATCH_STRATEGY_CONTAINS）和正则匹配（PARAM_MATCH_STRATEGY_REGEX）,默认是精确匹配
                .setPredicateItems(new HashSet<>() {{
                    add(new ApiPathPredicateItem().setPattern("/depart/update"));
                    add(new ApiPathPredicateItem().setPattern("/depart/get/.*")
                            .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_REGEX));
                }});
        definitions.add(api1);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

    /**
     * 初始化阻塞处理器
     */
    private static void initBlockHandler() {
        //重定向
//        GatewayCallbackManager.setBlockHandler(new RedirectBlockRequestHandler("https://baidu.com"));
        // 自定义异常结果
        GatewayCallbackManager.setBlockHandler((serverWebExchange, throwable) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("uri", serverWebExchange.getRequest().getURI());
            map.put("msg","访问量过大，稍后请重试");
            map.put("code", HttpStatus.TOO_MANY_REQUESTS.value());

            return ServerResponse.status(HttpStatus.TOO_MANY_REQUESTS)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Mono.just(map), Map.class);
        });
    }


}
