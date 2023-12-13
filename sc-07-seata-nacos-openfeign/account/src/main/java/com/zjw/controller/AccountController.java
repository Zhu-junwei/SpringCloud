package com.zjw.controller;


import com.zjw.domain.Account;
import com.zjw.service.IAccountService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 帐户表 前端控制器
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    IAccountService accountService;

    @PostMapping("/debit")
    public ResponseEntity<Boolean> debit(@RequestParam("userId") String userId,
                                         @RequestParam("money") Integer money){

        // 从用户账户中借出
        accountService.debit(userId, money);

        // 返回结果
        return ResponseEntity.ok(true);
    }

    @GetMapping("/list")
    public List<Account> list() {
        return accountService.list();
    }
}
