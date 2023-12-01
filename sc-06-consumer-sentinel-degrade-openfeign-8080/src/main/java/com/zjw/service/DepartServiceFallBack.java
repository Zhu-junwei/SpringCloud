package com.zjw.service;

import com.zjw.domain.Depart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 朱俊伟
 * @since 2023/12/01 21:11
 */
@Component
@Slf4j
@RequestMapping("/fallback/depart")
public class DepartServiceFallBack implements IDepartService{

    @Override
    public boolean save(Depart depart) {
        log.info("save..fallback");
        return false;
    }

    @Override
    public boolean remove(Long id) {
        log.info("remove..fallback");
        return false;
    }

    @Override
    public boolean update(Depart depart) {
        log.info("update..fallback");
        return false;
    }

    @Override
    public Depart get(Long id) {
        log.info("get..fallback");
        return null;
    }

    @Override
    public List<Depart> list() {
        log.info("list..fallback");
        return null;
    }

    @Override
    public List<String> discovery() {
        log.info("discovery..fallback");
        return null;
    }
}
