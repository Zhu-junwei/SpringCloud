package com.zjw.controller;


import com.zjw.domain.Depart;
import com.zjw.service.IDepartService;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class DepartController {

    @Resource
    private IDepartService departService;

    @Resource
    private DiscoveryClient discoveryClient;

    /**
     * 保存部门
     */
    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart) {
        return departService.save(depart);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/delete/{id}")
    public boolean remove(@PathVariable Long id) {
        return departService.removeById(id);
    }

    /**
     * 更新部门
     */
    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart) {
        return departService.updateById(depart);
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/get/{id}")
    public Depart get(@PathVariable Long id) {
        return departService.getById(id);
    }

    /**
     * 查询所有部门
     */
    @GetMapping("/list")
    public List<Depart> list() {
        return departService.list();
    }

    @GetMapping("/discovery")
    public List<String> discovery() {

        List<String> services = discoveryClient.getServices();
        for (String serviceName : services) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance : instances) {
                Map<String,Object> instanceMap = new HashMap<>();
                instanceMap.put("serviceName", serviceName);
                instanceMap.put("serviceId", instance.getServiceId());
                instanceMap.put("instanceId", instance.getInstanceId());
                instanceMap.put("host", instance.getHost());
                instanceMap.put("port", instance.getPort());
                instanceMap.put("scheme", instance.getScheme());
                instanceMap.put("uri", instance.getUri());
                instanceMap.put("metadata", instance.getMetadata());
                System.out.println("instanceMap = " + instanceMap);
            }
        }
        return services;
    }
}
