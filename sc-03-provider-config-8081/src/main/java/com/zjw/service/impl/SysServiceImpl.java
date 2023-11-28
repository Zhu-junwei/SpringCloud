package com.zjw.service.impl;

import com.zjw.service.ISysService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * 系统信息
 * @author 朱俊伟
 * @since 2023/11/21 23:55
 */
@Service
@RefreshScope
public class SysServiceImpl implements ISysService {


    @Value("${app.version}")
    private String version;

    @Override
    public String getVersion() {
        return this.version;
    }
}
