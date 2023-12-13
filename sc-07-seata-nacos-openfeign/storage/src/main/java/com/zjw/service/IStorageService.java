package com.zjw.service;

import com.zjw.domain.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.ResponseEntity;

/**
 * <p>
 * 仓储表 服务类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
public interface IStorageService extends IService<Storage> {

    /**
     * 扣除存储数量
     */
    ResponseEntity<Boolean> deduct(String commodityCode, int count);

}
