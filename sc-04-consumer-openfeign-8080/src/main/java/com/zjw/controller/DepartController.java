package com.zjw.controller;

import com.zjw.domain.Depart;
import com.zjw.service.IDepartService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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
    private IDepartService departService;

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
        return departService.remove(id);
    }

    /**
     * 更新部门
     */
    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart) {
        return departService.update(depart);
    }

    /**
     * 根据id查询部门
     */
    @GetMapping("/get/{id}")
    public Depart get(@PathVariable Long id) {
        return departService.get(id);
    }


    @GetMapping("/list")
    public List<Depart> list() {
        return departService.list();
    }

    @GetMapping("/discovery")
    public List<String> discovery() {
        return departService.discovery();
    }


}
