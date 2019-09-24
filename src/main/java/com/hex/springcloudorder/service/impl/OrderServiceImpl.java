package com.hex.springcloudorder.service.impl;

import com.hex.springcloudorder.dataobject.OrderMaster;
import com.hex.springcloudorder.dto.OrderDTO;
import com.hex.springcloudorder.enums.OrderStatusEnum;
import com.hex.springcloudorder.enums.PayStatusEnum;
import com.hex.springcloudorder.repository.OrderDetailRepository;
import com.hex.springcloudorder.repository.OrderMasterRepository;
import com.hex.springcloudorder.service.OrderService;
import com.hex.springcloudorder.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 2:47 PM
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // TODO 查询商品信息（调用商品服务）

        // TODO 计算总价

        // TODO 扣库存（调用商品服务）

        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
