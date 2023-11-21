package com.zjw.controller;

import com.zjw.domain.Depart;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
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
public class DepartController {

    @Resource
    private RestTemplate restTemplate;

    private static final String PROVIDER_URL = "http://localhost:8081/depart";

    /**
     * 保存部门
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody Depart depart) {
        return restTemplate.postForObject(PROVIDER_URL + "/save", depart, Boolean.class);
    }

    /**
     * 删除部门
     */
    @DeleteMapping("/delete/{id}")
    public void remove(@PathVariable Long id) {
        restTemplate.delete(PROVIDER_URL + "/delete/" + id);
    }

    /**
     * 更新部门
     */
    @PutMapping("/update")
    public void update(@RequestBody Depart depart) {
        restTemplate.put(PROVIDER_URL + "/update", depart);
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/get/{id}")
    public Depart get(@PathVariable Long id) {
        return restTemplate.getForObject(PROVIDER_URL + "/get/" + id, Depart.class);
    }

    /**
     * 查询所有部门
     */
    @SuppressWarnings("unchecked")
    @GetMapping("/list")
    public List<Depart> list() {
        return (List<Depart>)restTemplate.getForObject(PROVIDER_URL + "/list", List.class);
    }

}
