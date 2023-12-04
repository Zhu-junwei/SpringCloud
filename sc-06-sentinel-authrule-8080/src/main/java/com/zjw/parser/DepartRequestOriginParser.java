package com.zjw.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 解析请求来源
 * @author 朱俊伟
 * @since 2023/12/04 18:33
 */
@Component
@Slf4j
public class DepartRequestOriginParser implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        String source = request.getParameter("source");
        if(!StringUtils.hasText(source)){
            //设置为默认来源
            source = "guest";
        }
        log.info("source = " + source);
        return source;
    }
}
