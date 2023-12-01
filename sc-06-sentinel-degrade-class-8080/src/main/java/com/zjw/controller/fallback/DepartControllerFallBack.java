package com.zjw.controller.fallback;

import com.zjw.domain.Depart;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * DepartControllerFallBack
 * @author 朱俊伟
 * @since 2023/12/01 19:56
 */
@Slf4j
public class DepartControllerFallBack {
    /**
     * 服务降级使用的方法
     */
    public static Depart getFallBack(Long id, Throwable t) {
        log.info("id = " + id);
        log.info("throwable = " + t.getMessage());
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return  depart;
    }

    /**
     * 服务降级使用的方法
     */
    public static List<Depart> listFallBack(Throwable t) {
        log.info("listFallBack");
        log.info("throwable = " + t.getMessage());
        Depart depart = new Depart();
        depart.setName("no this depart");
        return List.of(depart);
    }
}
