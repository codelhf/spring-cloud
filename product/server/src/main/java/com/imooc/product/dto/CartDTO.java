package com.imooc.product.dto;

import lombok.Data;

@Data
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
