package com.atguigu.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Marlboro
 * @date 2026/11/7 21:51
 * @description
 */

//@Component
public class XTokenRequestInterceptor implements RequestInterceptor {


    /**
     * 对请求进行拦截
     * @param template 请求模板
     */
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("XTokenRequestInterceptor ...");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}
