package com.hex.springcloudorder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexuan
 * Date: 2019/10/22
 * Time: 3:36 下午
 */
@RestController
@RequestMapping("/env")
public class EnvController {

    @Value("${env}")
    private String env;

    @GetMapping("/print")
    public String print(){
        return env;
    }
}
