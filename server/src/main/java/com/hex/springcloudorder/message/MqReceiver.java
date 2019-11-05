package com.hex.springcloudorder.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受mq消息
 * User: hexuan
 * Date: 2019/10/24
 * Time: 5:16 下午
 */
@Slf4j
@Component
public class MqReceiver {

    // 1. 需要手动创建队列
    // @RabbitListener(queues = "myQueue")
    // 2. 自动创建队列
    // @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    // 3. 自动创建，Exchange 和 Queue 绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 数码供应商服务 接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder")
    ))
    public void processComputer(String message) {
        log.info("computer MqReceiver:{}", message);
    }

    /**
     * 水果供应商服务 接收消息
     *
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder")
    ))
    public void processFruit(String message) {
        log.info("fruit MqReceiver:{}", message);
    }
}
