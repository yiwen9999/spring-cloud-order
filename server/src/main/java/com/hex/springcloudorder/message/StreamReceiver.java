package com.hex.springcloudorder.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * User: hexuan
 * Date: 2019/10/25
 * Time: 5:24 下午
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT)
    @SendTo(StreamClient.OUTPUT)
    public Object processInput(Object message) {
        log.info("StreamReceiver input: {}", message);
        return message;
    }

    @StreamListener(StreamClient.OUTPUT)
    public void processOutput(Object message) {
        log.info("StreamReceiver out: {}", message);
    }


}
