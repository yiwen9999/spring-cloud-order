package com.hex.springcloudorder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * User: hexuan
 * Date: 2019/9/23
 * Time: 5:10 下午
 */
@RestController
@Slf4j
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        /**
         * 1. 第一种方式
         * 直接使用 restTemplate，url 写死
         */
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:9001/msg", String.class);

        /**
         * 2. 第二种方式
         * 利用 loadBalancerClient 通过【应用名】获取 url，然后再使用 restTemplate
         */
//        RestTemplate restTemplate = new RestTemplate();
//        ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
//        String url = String.format("http://%s:%s/msg", serviceInstance.getHost(), serviceInstance.getPort());
//        String response = restTemplate.getForObject(url, String.class);

        /**
         * 3. 第三种方式
         * 利用 @LoadBalanced，可在 restTemplate 里使用应用名字
         */
        String response = restTemplate.getForObject("http://PRODUCT/msg",String.class);

        log.info("response={}", response);
        return response;
    }
}
