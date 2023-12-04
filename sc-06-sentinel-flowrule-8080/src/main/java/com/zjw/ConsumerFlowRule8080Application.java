package com.zjw;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsumerFlowRule8080Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerFlowRule8080Application.class, args);
        //初始化流控规则
//        initFlowRule();
    }

    private static void initFlowRule() {
        FlowRuleManager.loadRules(configQpsFlowRule());
    }

    // QPS 控制效果
    private static List<FlowRule> configQpsFlowRule() {
        List<FlowRule> flowRuleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 资源名
        rule.setResource("get");
        // 针对来源
        rule.setLimitApp("default");
        // 阈值类型 (并发线程数 0: thread count, 1: QPS).
        // RuleConstant.FLOW_GRADE_THREAD 0
        // RuleConstant.FLOW_GRADE_QPS 1
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 单机阈值
        rule.setCount(3);
        flowRuleList.add(rule);
        return flowRuleList;
    }

    // warm up 控制效果
    private static List<FlowRule> configWarmUpFlowRule() {
        List<FlowRule> flowRuleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 资源名
        rule.setResource("get");
        // 针对来源
        rule.setLimitApp("default");
        // 阈值类型 (并发线程数 0: thread count, 1: QPS).
        // RuleConstant.FLOW_GRADE_THREAD 0
        // RuleConstant.FLOW_GRADE_QPS 1
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 单机阈值
        rule.setCount(3);
        // 流控效果
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_WARM_UP);
        // 预热时长 s
        rule.setWarmUpPeriodSec(10);
        flowRuleList.add(rule);
        return flowRuleList;
    }

    // 排队等待 控制效果
    private static List<FlowRule> configPaceFlowRule() {
        List<FlowRule> flowRuleList = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 资源名
        rule.setResource("get");
        // 针对来源
        rule.setLimitApp("default");
        // 阈值类型 (并发线程数 0: thread count, 1: QPS).
        // RuleConstant.FLOW_GRADE_THREAD 0
        // RuleConstant.FLOW_GRADE_QPS 1
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 单机阈值
        rule.setCount(3);
        // 流控效果
        rule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER);
        // 预热时长 20s
        rule.setMaxQueueingTimeMs(20 * 1000);
        flowRuleList.add(rule);
        return flowRuleList;
    }
}
