package com.hex.springcloudorder.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hex.springcloudorder.utils.JsonUtil;
import com.hex.springcloudproduct.common.ProductInfoOutPut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: hexuan
 * Date: 2019/10/31
 * Time: 4:53 下午
 */
@Component
@Slf4j
public class ProductInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // message => ProductInfoOutput
        List<ProductInfoOutPut> productInfoOutPutList = (List<ProductInfoOutPut>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutPut>>() {
                });
        log.info("从队列【{}】接收到消息：{}", "productInfo", productInfoOutPutList);

        // 存储到redis中
        for (ProductInfoOutPut productInfoOutPut : productInfoOutPutList) {
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK_TEMPLATE, productInfoOutPut.getProductId()),
                    String.valueOf(productInfoOutPut.getProductStock()));
        }

    }
}
