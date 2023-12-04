package com.zjw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.domain.Depart;

import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author zjw
 * @since 2023-11-20
 */
public interface IDepartService extends IService<Depart> {

    @Override
    List<Depart> list();

}
