package com.atguigu.order.config;

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
public class ProductServiceConfig {

    @LoadBalanced // 注解式开启负载均衡
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
