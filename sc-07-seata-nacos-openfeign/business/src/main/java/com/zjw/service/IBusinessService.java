package com.zjw.service;

import org.springframework.http.ResponseEntity;

/**
 * @author 朱俊伟
 * @since 2023/12/11 22:28
 */
public interface IBusinessService {

    /**
     * 顾客购买商品
     * @param userId 用户id
     * @param commodityCode 商品编号
     * @param count 商品数量
     * @return 执行结果
     */
    ResponseEntity<Boolean> buy(String userId,
                                   String commodityCode,
                                   Integer count);

}
