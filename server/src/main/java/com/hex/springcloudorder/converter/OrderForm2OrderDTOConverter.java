package com.hex.springcloudorder.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hex.springcloudorder.dataobject.OrderDetail;
import com.hex.springcloudorder.dto.OrderDTO;
import com.hex.springcloudorder.enums.ResultEnum;
import com.hex.springcloudorder.exception.OrderException;
import com.hex.springcloudorder.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * User: hexuan
 * Date: 2019/9/20
 * Time: 3:59 PM
 */
@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList;
        Gson gson = new Gson();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("【json转换】错误，string{}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}
