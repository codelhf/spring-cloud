package com.imooc.product.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseStockInput {

    private String productId;

    private Integer productQuantity;
}
