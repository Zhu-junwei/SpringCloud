package com.zjw.service.impl;

import com.zjw.domain.Order;
import com.zjw.mapper.OrderMapper;
import com.zjw.service.remote.IAccountService;
import com.zjw.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    IAccountService accountService;

    /**
     * 创建订单
     * @param order 订单对象
     * @return 保存后的订单对象
     */
    @Override
    public ResponseEntity<Boolean> create(Order order) {
        // 1.创建订单
        // 计算金额
        order.setMoney(order.getCount() * 2);
        // 保存订单,会将id保存再order对象中
        super.save(order);
        log.info("order = {}", order);

        // 2.扣用户金额
        accountService.debit(order.getUserId(), order.getMoney());
        // 返回结果
        return ResponseEntity.ok(true);
    }
}
