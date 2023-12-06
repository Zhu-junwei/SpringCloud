package com.zjw;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SentinelGateWay9001Application {

    public static void main(String[] args) {
        SpringApplication.run(SentinelGateWay9001Application.class, args);
        // 初始化阻塞处理器
        initBlockHandler();
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
