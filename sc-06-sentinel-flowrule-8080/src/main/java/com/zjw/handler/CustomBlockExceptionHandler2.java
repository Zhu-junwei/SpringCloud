package com.zjw.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * 自定义异常处理器 返回响应流
 * @author 朱俊伟
 * @since 2023/12/03 23:22
 */
@Component
public class CustomBlockExceptionHandler2 implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        // Return 429 (Too Many Requests) by default.
        response.setStatus(429);

        String msg = "";
        if (e instanceof AuthorityException) {
            msg = "Blocked by Sentinel (authority limiting)";
        } else if (e instanceof DegradeException) {
            msg = "Blocked by Sentinel (degrade limiting)";
        } else if (e instanceof FlowException) {
            msg = "Blocked by Sentinel (flow limiting)";
        } else if (e instanceof ParamFlowException) {
            msg = "Blocked by Sentinel (param flow limiting)";
        } else if (e instanceof SystemBlockException) {
            msg = "Blocked by Sentinel (system limiting)";
        } else {
            msg = "Blocked by Sentinel";
        }
        PrintWriter out = response.getWriter();
        out.print(msg);
        out.flush();
        out.close();
    }
}
