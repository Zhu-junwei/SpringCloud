package com.zjw.controller;


import com.zjw.domain.Order;
import com.zjw.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @GetMapping("/list")
    public List<Order> list() {
        return orderService.list();
    }

}
