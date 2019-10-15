package com.hex.springcloudorder.service;

import com.hex.springcloudorder.dto.OrderDTO;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 2:42 PM
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);
}
