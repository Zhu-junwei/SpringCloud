package com.zjw.controller;

import cn.hutool.core.util.ArrayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.Map;

/**
 * @author 朱俊伟
 * @since 2023/11/28 13:41
 */
@RestController
@RequestMapping("/info")
public class ShowInfoController {

    @GetMapping("/header")
    public String getHeader(HttpServletRequest request){

        Enumeration<String> headerNames = request.getHeaderNames();
        // 通过headerNames获取所有的请求头
        StringBuilder sb = new StringBuilder();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name).append(":");
            Enumeration<String> headers = request.getHeaders(name);
            while (headers.hasMoreElements()) {
                sb.append(headers.nextElement()).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @GetMapping("/parameter")  // 不加@RequestParam注解，默认会获取所有的参数，如果想获取指定的参数，需要加@RequestParam注解，
    public String getParameter(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder sb = new StringBuilder();
        for (String key : parameterMap.keySet()) {
            sb.append(key).append(":").append(ArrayUtil.join(parameterMap.get(key),"🔚")).append("\n");
        }
        return sb.toString();
    }
}
