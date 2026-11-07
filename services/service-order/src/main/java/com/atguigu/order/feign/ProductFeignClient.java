package com.atguigu.order.feign;

import com.atguigu.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Marlboro
 * @date 2026/11/6 17:51
 * @description
 */
@FeignClient(value = "service-product") // feign客户端
public interface ProductFeignClient {

    @GetMapping("product/{id}")
    Product getProductById(@PathVariable("id") Long id);
}
