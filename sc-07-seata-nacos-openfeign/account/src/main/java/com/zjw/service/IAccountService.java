package com.zjw.service;

import com.zjw.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * 帐户表 服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
public interface IAccountService extends IService<Account> {

    /**
     * 从用户账户中借出
     */
    ResponseEntity<Boolean> debit(String userId, Integer money);

}
