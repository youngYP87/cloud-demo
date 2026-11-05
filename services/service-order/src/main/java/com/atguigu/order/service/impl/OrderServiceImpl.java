package com.atguigu.order.service.impl;

import com.atguigu.order.bean.Order;
import com.atguigu.order.service.OrderService;
import com.atguigu.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Marlboro
 * @date 2026/11/2 15:52
 * @description
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Override
    public Order createOrder(Long productId, Long userId) {
        Order order = new Order();
        order.setId(1L);
         Product product = getProductFromRemoteLoadBalanceAnnotation(productId);
//        Product product = productFeignClient.getProductById(productId);
        BigDecimal totalAmount = product.getPrice().multiply(new BigDecimal(product.getNum()));
        order.setTotalAmount(totalAmount);
        order.setUserId(userId);
        order.setNickname("zhangsan");
        order.setAddress("尚硅谷");
        order.setProductList(List.of(product));
        return order;
    }

    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        // 远程 url
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/product/" + productId;
        log.info("远程请求: {}", url);
        // 给远程发送请求
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductFromRemoteLoadBalance(Long productId) {
        ServiceInstance choose = loadBalancerClient.choose("service-product");
        // 远程 url
        String url = "http://" + choose.getHost() + ":" + choose.getPort() + "/product/" + productId;
        log.info("远程请求: {}", url);
        // 给远程发送请求
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductFromRemoteLoadBalanceAnnotation(Long productId) {
        // 远程 url
        String url = "http://service-product/product/"  + productId;
        log.info("远程请求: {}", url);
        return restTemplate.getForObject(url, Product.class);
    }


}
