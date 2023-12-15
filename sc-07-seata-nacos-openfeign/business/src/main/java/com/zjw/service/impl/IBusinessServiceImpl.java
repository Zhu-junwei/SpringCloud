package com.zjw.service.impl;

import com.zjw.domain.Order;
import com.zjw.service.IBusinessService;
import com.zjw.service.remote.IOrderService;
import com.zjw.service.remote.IStorageService;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author 朱俊伟
 * @since 2023/12/11 22:33
 */
@Service
public class IBusinessServiceImpl implements IBusinessService {

    @Resource
    private IStorageService storageService;

    @Resource
    private IOrderService orderService;

    /**
     * 顾客购买商品
     *
     * @param userId        用户id
     * @param commodityCode 商品编号
     * @param count         商品数量
     * @return 执行结果
     */
    @GlobalTransactional
    @Override
    public ResponseEntity<Boolean> buy(String userId, String commodityCode, Integer count) {

        // 1. 扣减库存
        storageService.deduct(commodityCode, count);

        //演示抛出异常后是否回滚
//        int a = 1/0;

        // 2. 创建订单
        Order order = Order.builder()
                .userId(userId)
                .commodityCode(commodityCode)
                .count(count).build();
        orderService.create(order);

        // 返回结果
        return ResponseEntity.ok(true);
    }
}
