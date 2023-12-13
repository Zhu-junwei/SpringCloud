package com.zjw.domain;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author zjw
 * @since 2023-12-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Order implements Serializable {

    /**
     * 自增长主键id
     */
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 商品编号
     */
    private String commodityCode;

    /**
     * 商品数量
     */
    private Integer count;

    /**
     * 金额
     */
    private Integer money;


}
