package com.zjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.domain.Account;
import com.zjw.mapper.AccountMapper;
import com.zjw.service.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 帐户表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public ResponseEntity<Boolean> debit(String userId, Integer money) {
        // 1. 锁定账户
        LambdaQueryWrapper<Account> queryWrapper = new LambdaQueryWrapper<Account>()
                .eq(Account::getUserId, userId)
                .select(Account::getId, Account::getMoney);

        Account account = baseMapper.selectOne(queryWrapper);

        // 2. 判断余额
        if (account.getMoney() < money) {
            throw new RuntimeException("余额不足");
        }

        // 3. 更新余额
        account.setMoney(account.getMoney() - money);
        baseMapper.updateById(account);
        return ResponseEntity.ok(true);
    }
}
