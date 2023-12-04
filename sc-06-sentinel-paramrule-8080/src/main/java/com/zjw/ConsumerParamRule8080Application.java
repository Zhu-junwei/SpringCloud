package com.zjw;

import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsumerParamRule8080Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerParamRule8080Application.class, args);
        //初始化热点规则
        paramRule();
    }

    private static void paramRule() {
        ParamFlowRuleManager.loadRules(configParamFlowRule());
    }

    // 设置授权规则
    private static List<ParamFlowRule> configParamFlowRule() {
        List<ParamFlowRule> ruleList = new ArrayList<>();
        ParamFlowRule rule = new ParamFlowRule();
        // 资源名
        rule.setResource("param");
        // 参数索引
        rule.setParamIdx(0);
        // 单机阈值
        rule.setCount(3);
        // 统计窗口时长 s
        rule.setDurationInSec(1);
        ruleList.add(rule);
        return ruleList;
    }
}
