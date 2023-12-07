package com.zjw.sentinel;

import com.alibaba.csp.sentinel.command.handler.ModifyParamFlowRulesCommandHandler;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 朱俊伟
 * @since 2023/12/07 22:10
 */
public class FileDataSourceInit implements InitFunc {

    @Override
    public void init() throws Exception {
        // 获取当前类的ClassLoader
        ClassLoader classLoader = FileDataSourceInit.class.getClassLoader();
//        File ruleDir = new File(System.getProperty("user.dir") + "/rules");
        File ruleDir = new File(classLoader.getResource("").getPath() + File.separator+"rules");
        if (!ruleDir.exists()) {
            ruleDir.mkdirs();
        }
        // 规则文件路径
        String ruleDirPath = ruleDir.getPath();
        readWriteRuleFileFlow(ruleDirPath,"flow-rule.json");
        readWriteRuleFileDegrade(ruleDirPath,"degrade-rule.json");
        readWriteRuleFileParamFlow(ruleDirPath,"param-rule.json");
        readWriteRuleFileAuthority(ruleDirPath,"authority-rule.json");
        readWriteRuleFileSystem(ruleDirPath,"system-rule.json");
    }


    /**
     * 流控规则
     */
    private void readWriteRuleFileFlow(String rulePath, String ruleFile) throws IOException {
        //创建规则文件
        String ruleFilePath = rulePath + File.separator + ruleFile;
        createRuleFile(ruleFilePath);

        ReadableDataSource<String, List<FlowRule>> ds = new FileRefreshableDataSource<>(
                ruleFilePath, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        FlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<FlowRule>> wds = new FileWritableDataSource<>(ruleFilePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerFlowDataSource(wds);
    }

    /**
     * 熔断规则
     */
    private void readWriteRuleFileDegrade(String rulePath, String ruleFile) throws IOException {
        //创建规则文件
        String ruleFilePath = rulePath + File.separator + ruleFile;
        createRuleFile(ruleFilePath);

        ReadableDataSource<String, List<DegradeRule>> ds = new FileRefreshableDataSource<>(
                ruleFilePath, source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        DegradeRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<DegradeRule>> wds = new FileWritableDataSource<>(ruleFilePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerDegradeDataSource(wds);
    }

    /**
     * 热点规则
     */
    private void readWriteRuleFileParamFlow(String rulePath, String ruleFile) throws IOException {
        //创建规则文件
        String ruleFilePath = rulePath + File.separator + ruleFile;
        createRuleFile(ruleFilePath);

        ReadableDataSource<String, List<ParamFlowRule>> ds = new FileRefreshableDataSource<>(
                ruleFilePath, source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        ParamFlowRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<ParamFlowRule>> wds = new FileWritableDataSource<>(ruleFilePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        ModifyParamFlowRulesCommandHandler.setWritableDataSource(wds);
    }

    /**
     * 系统规则
     */
    private void readWriteRuleFileSystem(String rulePath, String ruleFile) throws IOException {
        //创建规则文件
        String ruleFilePath = rulePath + File.separator + ruleFile;
        createRuleFile(ruleFilePath);

        ReadableDataSource<String, List<SystemRule>> ds = new FileRefreshableDataSource<>(
                ruleFilePath, source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        SystemRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<SystemRule>> wds = new FileWritableDataSource<>(ruleFilePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerSystemDataSource(wds);
    }

    /**
     * 授权规则
     */
    private void readWriteRuleFileAuthority(String rulePath, String ruleFile) throws IOException {
        //创建规则文件
        String ruleFilePath = rulePath + File.separator + ruleFile;
        createRuleFile(ruleFilePath);

        ReadableDataSource<String, List<AuthorityRule>> ds = new FileRefreshableDataSource<>(
                ruleFilePath, source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {})
        );
        // 将可读数据源注册至 FlowRuleManager.
        AuthorityRuleManager.register2Property(ds.getProperty());

        WritableDataSource<List<AuthorityRule>> wds = new FileWritableDataSource<>(ruleFilePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerAuthorityDataSource(wds);
    }

    private void createRuleFile(String ruleFilePath) throws IOException {
        File file = new File(ruleFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    private <T> String encodeJson(T t) {
        return JSON.toJSONString(t);
    }
}