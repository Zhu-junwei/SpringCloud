package com.zjw;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsumerSentinel8080Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerSentinel8080Application.class, args);
        //初始化熔断规则
        initDegradeRule();
    }

    /**
     * 初始化熔断规则
     */
    private static void initDegradeRule() {
        DegradeRuleManager.loadRules(configDegradeRule());
    }

    private static List<DegradeRule> configDegradeRule(){
        List<DegradeRule> degradeRuleList = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        // sentinel资源名称，这里填入value值，@SentinelResource(value = "get", fallback = "getFallBack")
        rule.setResource("get");
        // 熔断策略，默认是慢调用比例，0: average RT, 异常比例 1: exception ratio, 异常数 2: exception count
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);//慢调用比例
//        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO);//异常比例
//        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);//异常数
        // 慢调用比例策略下为最大RT，响应时间,单位ms；异常比例策略下为比例阈值[0.0, 1.0]；异常数策略下为异常数,int
        rule.setCount(800);
        // 比例阈值，默认为1.0，即100%。慢调用比例策略才需要设置
        rule.setSlowRatioThreshold(0.5D);
        // 熔断时长，单位s
        rule.setTimeWindow(10);
        // 最小请求数
        rule.setMinRequestAmount(200);
        // 统计时长，单位ms，默认为1000ms
        rule.setStatIntervalMs(1000);

        degradeRuleList.add(rule);
        return degradeRuleList;
    }
}
