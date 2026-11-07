package com.atguigu.order.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Marlboro
 * @date 2026/11/2 16:02
 * @description
 */
@Configuration
public class OrderServiceConfig {

    @LoadBalanced // 注解式开启负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Logger.Level feignlogLevel() {
        // 指定 OpenFeign 发请求时，日志级别为 FULL
        return Logger.Level.FULL;
    }


    // @Bean
    public Retryer retryer() {
        // 重调机制
        return new Retryer.Default();
    }
}
