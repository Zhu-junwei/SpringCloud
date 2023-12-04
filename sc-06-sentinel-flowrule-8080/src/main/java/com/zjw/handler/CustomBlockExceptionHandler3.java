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
 * 自定义异常处理器 返回页面
 * @author 朱俊伟
 * @since 2023/12/03 23:22
 */
//@Component
public class CustomBlockExceptionHandler3 implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        String page = "/default.html";
        if (e instanceof AuthorityException) {
            page = "/authorityException.html";
        } else if (e instanceof DegradeException) {
            page = "/degradeException.html";
        } else if (e instanceof FlowException) {
            page = "/flowException.html";
        } else if (e instanceof ParamFlowException) {
            page = "/paramFlowException.html";
        } else if (e instanceof SystemBlockException) {
            page = "/systemBlockException.html";
        }
        request.getRequestDispatcher(page).forward(request,response);
    }
}
