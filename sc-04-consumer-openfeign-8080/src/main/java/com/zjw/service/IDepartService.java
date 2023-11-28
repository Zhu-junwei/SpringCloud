package com.zjw.service;

import com.zjw.domain.Depart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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
