package com.zjw.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.domain.Depart;
import com.zjw.mapper.DepartMapper;
import com.zjw.service.IDepartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-11-20
 */
@Service
@Slf4j
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements IDepartService {

    @SentinelResource(value = "list", blockHandler = "listFlowFallBack")
    @Override
    public List<Depart> list() {
        return super.list();
    }

    public List<Depart> listFlowFallBack(BlockException e) {
        log.info("流控调用");
        List<Depart> list = new ArrayList<>();
        Depart depart = new Depart();
        depart.setName("listFallBack");
        list.add(depart);
        return  list;
    }
}
