package com.atguigu.order.service;

import com.atguigu.order.bean.Order;

/**
 * @author Marlboro
 * @date 2026/11/2 15:51
 * @description
 */
public interface OrderService {
    Order createOrder(Long productId, Long userId);
}
