package com.hex.springcloudorder.dto;

import lombok.Data;

/**
 * User: hexuan
 * Date: 2019/9/26
 * Time: 9:53 上午
 */
@Data
public class CartDTO {

    /**
     * 商品id
     */
    private String productId;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
