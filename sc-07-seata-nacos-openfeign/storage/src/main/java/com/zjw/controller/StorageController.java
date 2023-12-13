package com.zjw.controller;


import com.zjw.domain.Storage;
import com.zjw.service.IStorageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓储表 前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    IStorageService storageService;

    @PostMapping("/deduct")
    public ResponseEntity<Boolean> deduct(@RequestParam("commodityCode") String commodityCode,
                                          @RequestParam("count") Integer count) {

        // 参数校验
        if (ObjectUtils.isEmpty(commodityCode)) {
            return ResponseEntity.badRequest().body(false);
        }
        if (count < 0) {
            return ResponseEntity.badRequest().body(false);
        }

        // 调用service层方法
        storageService.deduct(commodityCode, count);

        // 返回结果
        return ResponseEntity.ok(true);
    }

    @GetMapping("/list")
    public List<Storage> list() {
       return storageService.list();
    }

}
