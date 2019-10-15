package com.hex.springcloudorder.service.impl;

import com.hex.springcloudorder.dataobject.OrderDetail;
import com.hex.springcloudorder.dataobject.OrderMaster;
import com.hex.springcloudorder.dto.OrderDTO;
import com.hex.springcloudorder.enums.OrderStatusEnum;
import com.hex.springcloudorder.enums.PayStatusEnum;
import com.hex.springcloudorder.repository.OrderDetailRepository;
import com.hex.springcloudorder.repository.OrderMasterRepository;
import com.hex.springcloudorder.service.OrderService;
import com.hex.springcloudorder.utils.KeyUtil;
import com.hex.springcloudproduct.client.ProductClient;
import com.hex.springcloudproduct.common.DecreaseStockInput;
import com.hex.springcloudproduct.common.ProductInfoOutPut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();

        // 查询商品信息（调用商品服务）
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutPut> productInfoOutPutList = productClient.listForOrder(productIdList);

        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutPut productInfoOutPut : productInfoOutPutList) {
                if (productInfoOutPut.getProductId().equals(orderDetail.getProductId())) {
                    // 单价*数量
                    orderAmount = productInfoOutPut.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfoOutPut, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());

                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }

        }

        // 扣库存（调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList()
                .stream().map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }
}
