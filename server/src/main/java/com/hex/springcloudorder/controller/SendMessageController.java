package com.hex.springcloudorder.controller;

import com.hex.springcloudorder.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * User: hexuan
 * Date: 2019/10/25
 * Time: 5:25 下午
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @GetMapping("/sendMessage")
    public void process() {
        String message = "now " + new Date();
        streamClient.input().send(MessageBuilder.withPayload(message).build());
    }
}
