package com.zjw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱俊伟
 * @since 2023/11/28 15:41
 */
@RestController
public class FallbackController {

    @GetMapping("/fb")
    public String fallback(){
        return "服务暂不可用";
    }
}
