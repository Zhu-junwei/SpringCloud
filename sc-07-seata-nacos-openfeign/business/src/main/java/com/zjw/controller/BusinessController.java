package com.zjw.controller;

import com.zjw.service.IBusinessService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 朱俊伟
 * @since 2023/12/11 22:39
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private IBusinessService businessService;
    /**
     * 顾客购买商品
     * @param userId 用户id
     * @param commodityCode 商品编号
     * @param count 商品数量
     * @return 执行结果
     */
    @PostMapping("/buy")
    ResponseEntity<Boolean> buy(@RequestParam("userId") String userId,
                                   @RequestParam("commodityCode") String commodityCode,
                                   @RequestParam("count") Integer count){
        return businessService.buy(userId,commodityCode,count);
    }

}
