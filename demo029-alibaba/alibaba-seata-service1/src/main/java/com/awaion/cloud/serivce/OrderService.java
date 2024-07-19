package com.awaion.cloud.serivce;

import com.awaion.cloud.entities.Order;

public interface OrderService {
    /**
     * 创建订单
     */
    void create(Order order);
}
