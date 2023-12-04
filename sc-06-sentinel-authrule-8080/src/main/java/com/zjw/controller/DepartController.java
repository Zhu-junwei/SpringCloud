package com.zjw.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjw.domain.Depart;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-11-20
 */
@RestController
@RequestMapping("/depart")
@Slf4j
public class DepartController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PROVIDER_URL = "http://depart-provider/depart";

    /**
     * 根据id查询部门
     */
    //发生异常会降级，调用getFallBack方法, 触发流控，会调用流控的getFlowFallBack方法
    @SentinelResource(value = "get", fallback = "getFallBack", blockHandler = "getFlowFallBack")
    @GetMapping("/get/{id}")
    public Depart get(@PathVariable Long id) {
        return restTemplate.getForObject(PROVIDER_URL + "/get/" + id, Depart.class);
    }

    /**
     * 服务流控使用的方法.
     * 需要指定BlockException参数，否则调用降级方法
     */
    public Depart getFlowFallBack(Long id, BlockException e) {
        log.info("id = " + id);
        log.info("exception = " + e.getMessage());
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("flow fall back");
        return  depart;
    }

    /**
     * 服务降级使用的方法
     */
    public Depart getFallBack(Long id, Throwable t) {
        log.info("id = " + id);
        log.info("throwable = " + t.getMessage());
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return  depart;
    }
    /**
     * 查询所有部门
     */
    @SuppressWarnings("unchecked")
    @SentinelResource(value = "list")
    @GetMapping("/list")
    public List<Depart> list() {
        return (List<Depart>)restTemplate.getForObject(PROVIDER_URL + "/list", List.class);
    }

}
