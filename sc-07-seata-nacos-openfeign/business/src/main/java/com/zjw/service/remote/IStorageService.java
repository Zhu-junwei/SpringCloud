package com.zjw.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 仓储表 远程服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@FeignClient(value = "storage-service", path = "/storage")
public interface IStorageService{

    /**
     * 扣除存储数量
     */
    @PostMapping("/deduct")
    ResponseEntity<Boolean> deduct(@RequestParam("commodityCode") String commodityCode,
                                   @RequestParam("count") Integer count);

}
