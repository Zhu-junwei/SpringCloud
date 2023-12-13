package com.zjw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.domain.Storage;
import com.zjw.mapper.StorageMapper;
import com.zjw.service.IStorageService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓储表 服务实现类
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements IStorageService {

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣除存储数量
     */
    @Override
    public ResponseEntity<Boolean> deduct(String commodityCode, int count) {
        // 1. 查询库存记录
        Storage storage = storageMapper.selectOne(new LambdaQueryWrapper<Storage>()
                .eq(Storage::getCommodityCode, commodityCode));

        // 2. 判断库存记录是否存在
        if (storage == null) {
            throw new RuntimeException("库存记录不存在");
        } else if (storage.getCount() < count) {
            throw new RuntimeException("库存不足");
        }
        // 3. 更新库存记录的数量
        storage.setCount(storage.getCount() - count);
        storageMapper.updateById(storage);

        return ResponseEntity.ok(true);
    }
}
