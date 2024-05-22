package com.zjw.service;

import com.zjw.domain.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 使用Feign 客户端，
 * 声明一个 Feign 客户端，名为 "depart-provider"，用于调用 "depart-provider" 服务
 * value：指定要调用的服务名称，通常是服务在服务注册中心
 * path：指定调用服务的基础路径，在请求 URL 中会自动添加这个路径
 *
 * @author 朱俊伟
 * @since 2023/11/22 12:20
 */
@FeignClient(value = "depart-provider", path = "/depart")
public interface IDepartService {

    /**
     * 保存部门
     */
    @PostMapping("/save")
    boolean save(@RequestBody Depart depart);

    /**
     * 删除部门
     */
    @DeleteMapping("/delete/{id}")
    boolean remove(@PathVariable Long id);

    /**
     * 更新部门
     */
    @PutMapping("/update")
    boolean update(@RequestBody Depart depart);

    /**
     * 根据id查询部门
     */
    @GetMapping("/get/{id}")
    Depart get(@PathVariable Long id);

    /**
     * 查询所有部门
     */
    @GetMapping("/list")
    List<Depart> list();

    @GetMapping("/discovery")
    List<String> discovery();
}
