package com.zjw.service.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 帐户表 远程服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@FeignClient(value = "account-service", path = "/account")
public interface IAccountService {

    @PostMapping("/debit")
    ResponseEntity<Boolean> debit(@RequestParam("userId") String userId,
                                 @RequestParam("money") Integer money);

}
