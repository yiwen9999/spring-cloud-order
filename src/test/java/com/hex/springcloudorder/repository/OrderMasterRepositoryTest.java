package com.hex.springcloudorder.repository;

import com.hex.springcloudorder.SpringCloudOrderApplicationTests;
import com.hex.springcloudorder.dataobject.OrderMaster;
import com.hex.springcloudorder.enums.OrderStatusEnum;
import com.hex.springcloudorder.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends SpringCloudOrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("小玄");
        orderMaster.setBuyerPhone("18611112222");
        orderMaster.setBuyerAddress("北京总部");
        orderMaster.setBuyerOpenid("110101");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}