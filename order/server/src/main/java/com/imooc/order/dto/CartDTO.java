package com.imooc.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品的数量
     */
    private Integer productQuantity;
}
