package com.zjw.service;

import com.zjw.domain.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
public interface IOrderService extends IService<Order> {

    /**
     * 创建订单
     */
    ResponseEntity<Boolean> create(Order order);

}
