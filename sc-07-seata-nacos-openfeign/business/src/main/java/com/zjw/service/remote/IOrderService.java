package com.zjw.service.remote;

import com.zjw.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 订单表 远程服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */

@FeignClient(value = "order-service", path = "/order")
public interface IOrderService{

    /**
     * 创建订单
     */
    @PostMapping("/create")
    ResponseEntity<Boolean> create(@RequestBody Order order);

}
