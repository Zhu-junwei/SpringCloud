package com.zjw;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsumerAuthRule8080Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAuthRule8080Application.class, args);
        //初始化授权规则
        initRule();
    }

    private static void initRule() {
        AuthorityRuleManager.loadRules(configAuthRule());
    }

    // 设置授权规则
    private static List<AuthorityRule> configAuthRule() {
        List<AuthorityRule> ruleList = new ArrayList<>();
        AuthorityRule rule = new AuthorityRule();
        // 资源名
        rule.setResource("get");
        // 针对来源
        rule.setLimitApp("vip,1,2");
        // 设置白名单
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        ruleList.add(rule);
        return ruleList;
    }
}
