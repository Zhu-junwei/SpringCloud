package com.zjw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端控制器
 *
 * @author zjw
 * @since 2023-12-4
 */
@RestController
@Slf4j
public class DepartController {

    /**
     * 测试热点规则
     */
    //发生异常会降级，调用paramFallback方法
    @SentinelResource(value = "param", fallback = "paramFallback")
    @GetMapping("/param")
    public String param(Long id, String name) {
        return "param:" + id + ", " + name;
    }

    public String paramFallback(Long id, String name) {
        return "fallback param:" + id + ", " + name;
    }
}
