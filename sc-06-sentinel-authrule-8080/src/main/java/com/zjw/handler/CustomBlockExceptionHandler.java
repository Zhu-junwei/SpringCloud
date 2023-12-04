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

/**
 * 自定义异常处理器 重定向 适用前后端分离
 * @author 朱俊伟
 * @since 2023/12/03 23:22
 */
//@Component
public class CustomBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String page = "https://www.taobao.com";
        if (e instanceof AuthorityException) {
            page = "https://www.baidu.com";
        } else if (e instanceof DegradeException) {
            page = "https://www.baidu.com";
        } else if (e instanceof FlowException) {
            page = "https://www.baidu.com";
        } else if (e instanceof ParamFlowException) {
            page = "https://www.baidu.com";
        } else if (e instanceof SystemBlockException) {
            page = "https://www.baidu.com";
        }
        response.sendRedirect(page);
    }
}
