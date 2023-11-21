package com.zjw.controller;

import com.zjw.service.ISysService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统信息
 * @author 朱俊伟
 * @date 2023/11/21 23:53
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @Resource
    private ISysService sysService;

    /**
     * 获取版本号
     */
    @RequestMapping("/getVersion")
    public String getVersion() {
        return sysService.getVersion();
    }

}
